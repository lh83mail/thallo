import { templateRegistry, DDParser, DUI, devices } from './config-parser'

const TPL_NAME = 'simple-list'
const VERSION = 1

// templateArgs: {
//   ds: 'user-list',   // 目标数据源ID
//   pageable: true,    // 分页显示,默认true
//   limit: 25,         // 分页大小
//   /** 表格列 */
//   columns: [
//      {index: 'name'},
//      {index: 'birthday', title: '用户生日'},  // 覆盖默认标题
//      {index: 'gender'},
//   ]
// }
// }
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
export class DQueryBar extends DDParser {
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
export class SimpleListPC extends DUI {
  id = TPL_NAME
  version = VERSION
  device = 'pc'

  applyArgs(args) {
    this.ds = this.getRootView().getDataSource(args.ds)
    this.pageable = args.pageable
    this.limit = args.limit || 25
    this.columns = (args.columns || []).map(item => new DTableColumn(item, this))

    // 查询工具栏
    if (args.queryBar) {
      this.queryBar = new DQueryBar(args.queryBar, this)
    }
  }

  getDataSource() {
    return this.ds
  }
}

// 注册
templateRegistry.regist(TPL_NAME, devices.PC, VERSION, SimpleListPC)
