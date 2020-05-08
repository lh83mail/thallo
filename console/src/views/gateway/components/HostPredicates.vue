<template>
  <collapge-item-conainer title="主机匹配(Host)" :name="name" @close="fireCloseEvent">
    <el-form-item label="主机名">
      <el-input v-model="args.patterns" />
    </el-form-item>
  </collapge-item-conainer>
</template>

<script>
import CollapgeItemConainer from './CollapgeItemConainer'

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
      name: 'Host',
      args: {
        patterns: undefined
      }
    }
  },

  watch: {
    'args.patterns': function(nv, ov) {
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
