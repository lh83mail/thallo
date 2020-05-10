<template>
  <collapge-item-conainer title="Http请求头匹配(Request)" :name="name" @close="fireCloseEvent">
    <el-form-item label="名称">
      <el-input v-model="args.name" />
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
      name: 'Header',
      args: {
        name: undefined,
        regexp: undefined
      }
    }
  },

  watch: {
    'args.name': function(nv, ov) {
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
