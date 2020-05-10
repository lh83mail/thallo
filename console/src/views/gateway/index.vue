<template>
  <div class="app-container">

    <div class="filter-container">
      <el-button type="primary" icon="el-icon-plus" @click="showEditor">
        增加路由
      </el-button>

      <el-button type="default">
        实施更改
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column prop="routeId" label="路由标识" width="180" />
      <el-table-column prop="uri" label="uri" />
      <el-table-column prop="createAt" label="创建时间" width="180" :formatter="dateFormater" />
      <el-table-column prop="updateAt" label="更新时间" width="180" :formatter="dateFormater" />
      <el-table-column prop="description" label="描述" />
      <el-table-column fixed="right" label="操作" width="400">
        <template v-slot:default="scope">
          <el-button type="text" size="small" @click="showEditor(scope.row)">配置</el-button>
          <el-button type="text" size="small" class="text-danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />

    <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
      <el-form ref="editorFrom" :model="form" :rules="rules" :label-width="formLabelWidth">
        <el-form-item label="路由标识" prop="routeId">
          <el-input v-model="form.routeId" />
        </el-form-item>
        <el-form-item label="URI" prop="uri">
          <el-input v-model="form.uri" />
        </el-form-item>

        <el-row>
          <el-col :span="12">
            <el-form-item label="序号" prop="orders">
              <el-input-number v-model="form.orders" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址状态" prop="status">
              <el-select v-model="form.status" placeholder="选择地址状态">
                <el-option label="可用" value="Y" />
                <el-option label="禁用" value="N" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="描述">
          <el-input v-model="form.description" type="textarea" :autosize="{ minRows: 1, maxRows: 3}" />
        </el-form-item>

        <el-form-item label="路由断言">
          <div class="editor-container">
            <json-editor v-model="form.predicates" />
          </div>
        </el-form-item>
        <el-form-item label="过滤器">
          <div class="editor-container">
            <json-editor v-model="form.filters" />
          </div>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" :disabled="form.valid" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import Pagination from '@/components/Pagination'
import { searchRoutes, getRoute, addRoute, updateRoute } from '@/api/gateway'
import { format } from 'date-fns'
import JsonEditor from '@/components/JsonEditor'

export default {
  components: {
    Pagination,
    JsonEditor
  },
  data() {
    return {
      tableKey: 'id',
      list: null,

      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id'
      },

      formLabelWidth: '80px',
      dialogFormVisible: false,
      form: {
        id: null,
        uri: '',
        routeId: '',
        predicates: '[]',
        filters: '[]',
        description: null,
        orders: 0,
        status: 'Y'
      },
      defaultTab: 'first',
      // 默认展开的断言面板
      defaultPredicateItem: [],

      rules: {
        routeId: [
          { required: true, message: '请输入路由标识', trigger: 'blur' }
        ],
        uri: [
          { required: true, message: '请输入uri', trigger: 'blur' }
        ]
      }

      // predicatesDefs: [
      //   { name: 'Path', title: '路径匹配(Path)', args: { patterns: undefined, matchOptionalTrailingSeparator: true }},
      //   { name: 'Header', title: '请求头匹配(Header)', args: { name: undefined, regexp: undefined }},
      //   { name: 'Host', title: '主机匹配(Host)', args: { patterns: undefined }},
      //   { name: 'Method', title: '请求方法匹配(Method)', args: { methods: undefined }},
      //   { name: 'Cookie', title: 'Cookie匹配(Cookie)', args: { name: undefined, regexp: undefined }},
      //   { name: 'Between', title: '时间段匹配(Between)', args: { datetime1: undefined, datetime2: undefined }},
      //   { name: 'Before', title: '起始时间匹配(Before)', args: { datetime: undefined }},
      //   { name: 'After', title: '结束时间匹配(After)', args: { datetime: undefined }},
      //   { name: 'Query', title: 'URL查询参数匹配(Query)', args: { param: undefined, regexp: undefined }},
      //   { name: 'RemoteAddr', title: '客户端地址匹配(RemoteAddr)', args: { sources: undefined }},
      //   { name: 'Weight', title: '权重匹配(Weight)', args: { group: undefined, weight: 1 }}
      // ],

      // filterDefs: [
      //   { component: 'BasicFilter', name: 'AddRequestHeader', title: '增加请求头', fields: [{ name: 'name', type: 'string', label: '名称' }, { name: 'value', type: 'string', label: '值' }] },
      //   { component: 'BasicFilter', name: 'AddRequestParameter', title: '增加查询参数', fields: [{ name: 'name', type: 'string', label: '名称' }, { name: 'value', type: 'string', label: '值' }] },
      //   { component: 'BasicFilter', name: 'AddResponseHeader', title: '增加响应请求头', fields: [{ name: 'name', type: 'string', label: '名称' }, { name: 'value', type: 'string', label: '值' }] },
      //   { component: 'BasicFilter', name: 'DedupeResponseHeader', title: '清理重复响应头', fields: [{ name: 'name', type: 'string', label: '名称' }, { name: 'strategy', type: 'string', label: '值', value: 'RETAIN_FIRST' }] },
      // ]
    }
  },

  created() {
    this.getList()
  },

  methods: {
    getList() {
      searchRoutes(this.listQuery)
        .then(res => {
          this.list = res.data
          this.total = res.total

          // this.refreshPredicatesStatus()
        })
        .finally(() => {
          this.listLoading = false
        })
    },

    // refreshPredicatesStatus() {
    //   const checked = (this.form.predicates || []).map(p => p.name)

    //   this.predicatesDefs = this.predicatesDefs.map(d => {
    //     d.checked = checked.includes(d.name)
    //     return d
    //   })
    // },

    dateFormater(row, col, val, idx) {
      return val ? format(val, 'YYYY-MM-DD HH:mm:ss') : '--'
    },

    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },

    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },

    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}` ? 'ascending' : 'descending'
    },

    showEditor(item) {
      this.dialogFormVisible = true
      this.resetForm()
      if (item) {
        getRoute(item.id)
          .then(res => {
            this.form = {
              ...res.data,
              predicates: JSON.parse(res.data.predicates || {}),
              filters: JSON.parse(res.data.filters || [])
            }
          })
      }
    },

    resetForm() {
      this.form = {
        id: null,
        uri: '',
        routeId: '',
        predicates: [],
        filters: [],
        description: null,
        orders: 0,
        status: 'Y'
      }
    },

    onSubmit: function() {
      const params = {
        ...this.form
      }

      let action
      if (params.id) {
        action = updateRoute(params.id, params)
      } else {
        action = addRoute(params)
      }

      action.then(r => {
        this.$message({
          message: '保存成功',
          type: 'success'
        })

        this.dialogFormVisible = false
        this.getList()
      })
    },

    onItemUpdate(item) {
      this.form.predicates = this.form.predicates.map(i => {
        if (i.name === item.name) {
          return item
        }
        return i
      })
    }

    // handlePredicateChecked(cmd) {
    //   const predicates = this.form.predicates || []
    //   predicates.push({
    //     name: cmd.name,
    //     args: { ...cmd.args }
    //   })
    //   this.defaultPredicateItem = [cmd.name]
    //   this.form.predicates = predicates
    //   this.refreshPredicatesStatus()
    // },

    // removePrediate(item) {
    //   this.form.predicates = this.form.predicates.filter(p => p.name !== item.name)
    //   this.defaultPredicateItem = this.defaultPredicateItem.map(n => n !== item.name)
    //   this.refreshPredicatesStatus()
    // },

  }
}
</script>

<style scoped>
.editor-container{
  position: relative;
  height: 100%;
  font-size: 12px;
  line-height: 18px;
}
</style>
