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
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { searchRoutes } from '@/api/gateway'
import { format } from 'date-fns'

export default {
  components: { Pagination },
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
    }
  }
}
</script>
