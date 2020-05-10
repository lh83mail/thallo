<template>
  <collapge-item-conainer title="URL查询参数匹配(Query)" :name="name" @close="fireCloseEvent">
    <el-form-item label="参数名">
      <el-input v-model="args.param" />
    </el-form-item>
    <el-form-item label="值">
      <el-input v-model="args.regexp" />
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
      name: 'Query',
      args: {
        param: undefined,
        regexp: undefined
      }
    }
  },

  watch: {
    'args.param': function(nv, ov) {
      this.fireUpdate(this.$data)
    },
    'args.regexp': function(nv, ov) {
      this.fireUpdate(this.$data)
    }
  },
  created() {
    this.name = this.item.name
    this.args = { ...this.item.args }
  },

  methods: {
    fireCloseEvent() {
      this.$emit('close')
    },
    fireUpdate(item) {
      this.$emit('update:item', item)
    }
  }
}
</script>
