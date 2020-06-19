import { DDParser, Viewport, devices } from './config-parser'
import { viewportRegistry } from './registries'

const TPL_NAME = 'simple-list'
const VERSION = '1.0'

export class DTableColumn extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    const m = this.parent.getDataSource().getModel()
    this.index = c.index
    this.title = c.title ? c.title : m.getProperty(this.index).label
  }
}

/**
 * 属性编辑器
 */
export class PropertyEditor extends DDParser {
  constructor(config, parent, model) {
    super(config, parent)

    this.index = config.index
    this.model = model
    this.property = this.model.getProperty(this.index)
    this.widget = config.widget || this._getDefaultWidget(this.property.type)
  }

  _getDefaultWidget(type) {
    let widget
    switch (type) {
      case 'date':
        widget = 'date'
        break
      case 'datetime':
        widget = 'datetime'
        break
      case 'int':
        widget = 'int'
        break
      case 'boolean':
        widget = 'switch'
        break
      default:
        widget = 'input'
    }
    return widget
  }
}

/**
 * 查询工具栏
 */
export class DFilterBar extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    this.propEditors = (c.properties || []).map(prop => {
      const model = this.parent.getDataSource().getModel()
      return new PropertyEditor(prop, this, model)
    })
  }
}

/**
 * 简单列表
 */
export class SimpleListViewport extends Viewport {
  id = TPL_NAME
  version = VERSION
  device = devices.PC

  parse() {
    super.parse()

    const c = this.config

    console.log('this.getRootView()', this.getRootView())
    this.ds = this.getRootView().getDataSource(c.ds)

    this.pageable = c.pageable
    this.page = c.page
    this.limit = c.limit || 25
    this.columns = (c.columns || []).map(item => new DTableColumn(item, this))

    // 查询工具栏
    if (c.queryBar) {
      this.queryBar = new DFilterBar(c.filterBar, this)
    }

    // 操作
  }

  getDataSource() {
    return this.ds
  }
}

// 注册
viewportRegistry.regist(TPL_NAME, devices.PC, VERSION, SimpleListViewport)
