<template>
  <collapge-item-conainer title="时间段匹配(Between)" :name="name" @close="fireCloseEvent">
    <el-form-item label="选择范围">
      <el-date-picker
        v-model="timeRange"
        type="datetimerange"
        :picker-options="pickerOptions"
        range-separator="至"
        start-placeholder="开始时间"
        end-placeholder="结束时间"
        align="right"
      />
    </el-form-item>
  </collapge-item-conainer>
</template>

<script>
import CollapgeItemConainer from '../CollapgeItemConainer'

export default {
  components: {
    CollapgeItemConainer
  },
  props: {
    item: {
      type: Object,
      required: true
    }
  },

  data() {
    return {
      name: 'Between',
      timeRange: [],
      args: {
        datetime1: undefined,
        datetime2: undefined
      },
      pickerOptions: {
        shortcuts: [{
          text: '一周内',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '一个月内(30天)',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '三个月内(90天)',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      }
    }
  },

  watch: {
    'timeRange': function(nv, ov) {
      this.fireUpdate(this.$data)
    }
  },
  created() {
    this.name = this.item.name
    this.args = { ...this.item.args }
    this.timeRange = [this.item.args.datetime1, this.item.args.datetime2]
  },

  methods: {
    fireCloseEvent() {
      this.$emit('close')
    },
    fireUpdate(item) {
      console.log('>>>>', this.timeRange)
      this.$emit('update:item', {
        name: item.name,
        args: {
          datetime1: this.timeRange.length > 0 ? this.timeRange[0] : undefined,
          datetime2: this.timeRange.length > 1 ? this.timeRange[1] : undefined
        }
      })
    }
  }
}
</script>
