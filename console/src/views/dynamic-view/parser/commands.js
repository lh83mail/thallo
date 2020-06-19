import { DDParser } from './config-parser'
import { commandRegistry } from './registries'
import request from '@/utils/request'

/**
 * 列表查询
 */
export class PagedQueryCommand extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    this.inputs = c.inputs
  }

  execute(params) {
    const view = this.getRootView()

    const url = `${process.env.VUE_APP_GATEWAY_BASE_API}/dyn-view/commands/${view.id}/${view.version}/${this.parent.id}`

    return request.post(url, params).then(res => res.data)
  }
}

/**
 * 插入命令
 */
export class InsertCommand extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    this.inputs = c.inputs
  }

  execute(params) {
    const view = this.getRootView()

    const url = `${process.env.VUE_APP_GATEWAY_BASE_API}/dyn-view/commands/${view.id}/${view.version}/${this.parent.id}`

    return request.post(url, params)
  }
}

/**
 * 更新命令
 */
export class UpdateCommand extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    this.inputs = c.inputs
  }

  execute(params) {
    console.log(':::::::::', params)
  }
}

export class LocalDataSourceCommand extends DDParser {
  parse() {
    super.parse()

    const c = this.config
    this.inputs = c.inputs
  }

  execute(params) {
    console.log(':::::::::', params)
  }
}

commandRegistry.regist('pagedQuery', PagedQueryCommand)
commandRegistry.regist('basicInsert', InsertCommand)
commandRegistry.regist('basicUpdate', UpdateCommand)
commandRegistry.regist('localDataSource', LocalDataSourceCommand)
