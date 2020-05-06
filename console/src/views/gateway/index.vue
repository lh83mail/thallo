<template>
  <div class="app-container">
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
          <el-button type="text" size="small">过滤器</el-button>
          <el-button type="text" size="small">前置拦截</el-button>
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
      <el-form :model="form" :rules="rules" :label-width="formLabelWidth">
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
        <el-form-item>
          <el-tabs v-model="form.defaultTab">
            <el-tab-pane label="路由断言(Predicates)" name="first">
              <el-collapse>

                <component :is="item.name + 'Predicates'" v-for="item in form.predicates" :key="item.name" :item.sync="item" />
                <!-- <el-collapse-item title="主机名匹配(Host)" name="Host">
                      <el-form-item label="patterns" >
                        <el-input  name="patterns"/>
                      </el-form-item>
                    </el-collapse-item> -->

              </el-collapse>

            </el-tab-pane>
            <el-tab-pane label="过滤器(Filters)" name="second">配置管理</el-tab-pane>
          </el-tabs>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" type="textarea" :autosize="{ minRows: 1, maxRows: 3}" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { searchRoutes } from '@/api/gateway'
import { format } from 'date-fns'
import PathPredicates from './components/PathPredicates'

// TODO 断言动态定义
// const predicatesDefs = [
//   { name: 'Path', args: { patterns: undefined, matchOptionalTrailingSeparator: true }},
//   { name: 'Header', args: { name: undefined, regexp: undefined }},
//   { name: 'Host', args: { patterns: undefined }},
//   { name: 'Method', args: { methods: undefined }},
//   { name: 'Cookie', args: { name: undefined, regexp: undefined }},
//   { name: 'Between', args: { datetime1: undefined, datetime2: undefined }},
//   { name: 'Before', args: { datetime: undefined }},
//   { name: 'After', args: { datetime: undefined }},
//   { name: 'Query', args: { param: undefined, regexp: undefined }},
//   { name: 'RemoteAddr', args: { sources: undefined }},
//   { name: 'Weight', args: { group: undefined, weight: 1 }}
// ]

export default {
  components: { Pagination, PathPredicates },
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
      dialogFormVisible: true,
      form: {
        id: null,
        uri: '',
        routeId: '',
        predicates: [
          { name: 'Path', args: { patterns: '/payable/**', matchOptionalTrailingSeparator: true }}
        ],
        filters: [],
        description: null,
        orders: 0,
        status: 'Y',
        defaultTab: 'first'
      },

      rules: {
        routeId: [
          { required: true, message: '请输入路由标识', trigger: 'blur' }
        ],
        uri: [
          { required: true, message: '请输入uri', trigger: 'blur' }
        ]
      }
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
        })
        .finally(() => {
          this.listLoading = false
        })
    },

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

    onSubmit: function() {
      console.log(this.form)
    },

    onItemUpdate(item) {
      console.log('item changed, ', item)
    }

  }
}
</script>
