<template>
  <collapge-item-conainer title="客户端地址匹配(RemoteAddr)" :name="name" @close="fireCloseEvent">
    <el-form-item label="客户端地址">
      <el-input v-model="args.sources" />
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
      name: 'RemoteAddr',
      args: {
        sources: undefined
      }
    }
  },

  watch: {
    'args.sources': function(nv, ov) {
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
