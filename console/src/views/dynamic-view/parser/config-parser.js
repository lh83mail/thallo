import { deepClone } from '@/utils'
import request from '@/utils/request'
import { viewportRegistry, commandRegistry } from './registries'

export const devices = {
  PC: 'PC'
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

export class DProperty extends DDParser {
  parse() {
    super.parse()
    const c = this.config

    this.label = c.label || c.name
    this.type = c.type || 'string'
    this.primary = c.primary === true
  }
}

export class DRelation extends DDParser {
  parse() {
    super.parse()
    //
    const c = this.config
    this.left = c.left
    this.right = c.right
    this.join = c.join
    this.conditions = (c.conditions || [])
  }
}

export class DModel extends DDParser {
  parse() {
    super.parse()
    const c = this.config
    //
    this.tableName = c.tableName
    this.idProp = c.idProp
    this.properties = (c.properties || []).map(item => new DProperty(item, this))
    this.relations = (c.relations || []).map(item => new DRelation(item, this))
  }

  getProperty(id) {
    return this.properties.find(p => p.id === id)
  }
}

export class DDataSource extends DDParser {
  parse() {
    super.parse()

    const c = this.config

    this.type = c.type
    this.model = this.parent.getModel(c.model)
    const CommandCls = commandRegistry.getCommand(c.cmd)
    if (CommandCls) {
      this.cmd = new CommandCls(c, this)
    }
  }

  getModel() {
    return this.model
  }

  /**
   * 加载数据
   * @param {*} params
   */
  execute(params) {
    return this.cmd.execute(params)
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

export class Viewport extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    this.version = c.version
    this.device = c.device
    this.title = c.title
    this.subTitle = c.subTitle

    this.params = (this.params || [])
  }
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
    this.ds = (c.dataSources || []).map(item => new DDataSource(item, this))
    const ViewportClass = viewportRegistry.getViewport(c.viewport.id, c.viewport.device, c.viewport.version)
    if (ViewportClass) {
      this.viewport = new ViewportClass(c.viewport, this)
    } else {
      throw new Error('未知的模板类型: ' + c.viewport.id)
    }
  }

  getViewport() {
    return this.viewport
  }

  getModel(id) {
    return this.models.find(u => u.id === id)
  }

  getDataSource(id) {
    return this.ds.find(u => u.id === id)
  }
}

