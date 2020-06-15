import { deepClone } from '@/utils'
import request from '@/utils/request'

class TemplateRegistry {
  templates = {}

  regist(name, device, version, template) {
    const key = this._wrapKey(name, device, version)
    if (!this.templates[key]) {
      this.templates[key] = template
    }
  }

  getTemplate(name, device, version) {
    return this.templates[this._wrapKey(name, device, version)]
  }

  _wrapKey(name, device, version) {
    return `${name}-${device}-${version}`
  }
}

export const templateRegistry = new TemplateRegistry()
export const devices = {
  PC: 'pc'
}

export class DDParser {
  constructor(config, parent) {
    this.config = config || {}
    this.parent = parent

    this.parse()
  }

  parse() {
    const c = this.config
    this.id = c.id
    this.doc = c.doc
  }

  getRootView() {
    if (this._rootView != null) {
      return this._rootView
    }

    if (this instanceof DView) {
      return this
    }

    if (this.parent) {
      let root = this.parent
      while (!!root && !(root instanceof DView)) {
        root = root.parent
      }
      this._rootView = root

      return this._rootView
    }

    return null
  }
}

export class DDataProvider extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    this.type = c.type

    switch (this.type) {
      case 'local':
        this.data = c.data || []
        break
      case 'http':
        this.http = {
          method: c.method || 'GET',
          url: c.url
        }
        break
      default:
        throw new Error('不支持的数据提供器: ' + this.type)
    }
  }

  load(params) {
    switch (this.type) {
      case 'local':
        return Promise.resolve(this.data.map(d => deepClone(d)))
      case 'http':
        return request({
          ...this.http,
          params
        }).then(res => res.data)
      default:
        return Promise.resolve([])
    }
  }
}

export class DDataSource extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    this.model = this.parent.getModel(c.model)
    this.provider = new DDataProvider(c.provider, this)
  }

  getModel() {
    return this.model
  }

  /**
   * 加载数据
   * @param {*} params
   */
  load(params) {
    if (!this.provider) {
      this.data = []
      return Promise.resolve(this.data)
    }

    const promise = this.provider.load(params)
      .then(data => { this.data = data })
      .then(_ => this.data)

    /* eslint-disable-next-line */
    return promise // eslint-disable-line
  }

  // getById(id) {}

  // deleteById(id) {}

  // getByIds(ids) {

  // }

  // save() {

  // }

  // list() {}

  // loadPage() {}
}

export class DProperty extends DDParser {
  // constructor(config, parent) {
  //   super(config, parent)
  // }

  parse() {
    super.parse()
    const c = this.config

    this.label = c.label || c.name
    this.type = c.type || 'string'
  }
}

export class DModel extends DDParser {
  // constructor(config, parent) {
  //   super(config, parent)
  // }

  parse() {
    super.parse()
    const c = this.config
    //
    this.idProp = c.idProp
    this.properties = (c.properties || []).map(item => new DProperty(item, this))
  }

  getProperty(id) {
    return this.properties.find(p => p.id === id)
  }
}

export class DTemplate extends DDParser {
}

export class DUI extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    this.version = c.version
    this.device = c.device

    this.applyArgs(c.templateArgs)
  }

  applyArgs(args) {}
}

/**
 * 视图配置
 */
export class DView extends DDParser {
  constructor(config) {
    super(config, null)
  }

  parse() {
    super.parse()

    const c = this.config

    this.version = c.version || '1.0'
    this.models = (c.models || []).map(item => new DModel(item, this))
    this.ds = (c.ds || []).map(item => new DDataSource(item, this))
    this.ui = (c.ui || []).map(item => {
      const TplClass = templateRegistry.getTemplate(item.id, item.device, item.version)
      if (TplClass) {
        return new TplClass(item, this)
      } else {
        throw new Error('未知的模板类型: ' + item.id)
      }
    })
  }

  getUI(device) {
    console.log(device, this.ui)
    return this.ui.find(u => u.device === device)
  }

  getModel(id) {
    return this.models.find(u => u.id === id)
  }

  getDataSource(id) {
    return this.ds.find(u => u.id === id)
  }
}

