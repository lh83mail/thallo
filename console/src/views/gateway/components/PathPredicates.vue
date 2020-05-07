<template>
  <collapge-item-conainer title="路径匹配(Path)" @close="fireCloseEvent">
    <el-form-item label="patterns">
      <el-input v-model="args.patterns" />
    </el-form-item>
    <el-form-item label="完全匹配">
      <el-switch v-model="args.matchOptionalTrailingSeparator" />
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
      name: 'Path',
      args: {
        patterns: undefined,
        matchOptionalTrailingSeparator: true
      }
    }
  },

  watch: {
    'args.patterns': function(nv, ov) {
      this.fireUpdate(this.$data)
    },
    'args.matchOptionalTrailingSeparator': function(nv, ov) {
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
