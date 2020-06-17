<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form v-if="queryBar.propEditors.length > 0" :inline="true" :model="listQuery" class="demo-form-inline">
        <el-form-item v-for="c of queryBar.propEditors" :key="c" :label="c.label">

          <!-- 文本 -->
          <el-input v-if="c.widget === 'input'" v-model="listQuery[c.index]" :placeholder="c.placeholder" clearable />

          <!-- 日期 -->
          <el-date-picker v-if="c.widget === 'date'" v-model="listQuery[c.index]" type="date" :placeholder="c.placeholder" />

          <!-- 日期时间 -->
          <el-date-picker v-if="c.widget === 'datetime'" v-model="listQuery[c.index]" type="datetime" :placeholder="c.placeholder" />

          <!-- 数值 -->
          <el-input-number v-if="c.widget === 'int'" v-model="listQuery[c.index]" :min="1" :max="10" :placeholder="c.placeholder" clearable />

          <!-- 布尔 -->
          <el-switch v-if="c.widget === 'switch'" v-model="listQuery[c.index]" />

          <!-- 日期范围 -->
          <el-date-picker v-if="c.widget === 'daterange'" v-model="listQuery[c.index]" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" />

          <!-- 日期时间范围 -->
          <el-date-picker v-if="c.widget === 'datetimerange'" v-model="listQuery[c.index]" type="datetimerange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" />

          <!-- <el-select v-model="listQuery.importance" placeholder="Imp" clearable style="width: 90px" class="filter-item" >
            <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" />
          </el-select>
           -->

        </el-form-item>
        <el-form-item>
          <el-button type="primary">操作按钮</el-button>
          <el-button type="primary">操作按钮</el-button>
          <el-button type="primary">操作按钮</el-button>
        </el-form-item>
      </el-form>

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
      <el-table-column v-for="c in columns" :key="c[tableKey]" :prop="c.index" :label="c.title" />

      <el-table-column fixed="right" label="操作" width="400">
        <template v-slot:default="scope">
          <el-button type="text" size="small">配置</el-button>
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
  </div>
</template>

<script>
import { loadViewConfig } from '@/api/dynamic-view/dynamic-view'

import Pagination from '@/components/Pagination'
import { DView } from '../parser'

export default {
  components: {
    Pagination
  },

  data() {
    return {
      viewId: null,
      version: '1.0',
      device: 'PC',

      listLoading: false,

      tableKey: 'id',
      columns: [],
      list: [],
      total: 0,

      listQuery: {
        page: 1,
        limit: 20,
        sort: '+id'
      },

      queryBar: {
        propEditors: []
      }
    }
  },

  watch: {
    // 如果路由有变化，会再次执行该方法
    $route: 'onRouteChanged'
  },

  created() {
    this.onRouteChanged()
  },

  methods: {
    loadPage() {
      this.$router.push({
        params: { viewId: 'simpleList' },
        query: { name: Math.random() }
      })
    },

    sortChange() {},

    getList() {
      if (!this.ui || !this.ui.getDataSource()) return

      this.ui
        .getDataSource()
        .load(this.listQuery)
        .then(data => (this.list = data))
    },

    onRouteChanged() {
      this.viewId = this.$route.params.viewId
      loadViewConfig(this.viewId, this.device, this.version).then(d => {
        this.dview = new DView(d)
        this.renderView()
      })
    },

    /**
     * 渲染视图
     */
    renderView() {
      const ui = this.dview.getUI(this.device) // SimpleListPC
      if (!ui) {
        console.error('ui is empty')
        return
      }

      this.columns = ui.columns.map(c => {
        return {
          index: c.index,
          title: c.title
        }
      })

      if (ui.queryBar) {
        this.queryBar.propEditors = ui.queryBar.propEditors.map(e => {
          return {
            label: e.property.label,
            type: e.property.type,
            index: e.index,
            widget: e.widget
          }
        })
      }

      this.ui = ui
      console.log('UI', ui)
      this.getList()
    }
  }
}
</script>

<style>
</style>
