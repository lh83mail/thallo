<template>
  <el-collapse-item title="路径匹配(Path)" name="Path">
    <el-form-item label="patterns">
      <el-input v-model="args.patterns" />
    </el-form-item>
    <el-form-item label="完全匹配">
      <el-switch v-model="args.matchOptionalTrailingSeparator" />
    </el-form-item>
  </el-collapse-item>
</template>

<script>
export default {
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
      console.log('>>>>', nv)
      this.$emit('update:item',
        { name: 'Path', args: { ...this.$data, patterns: nv }})
    },
    'args.matchOptionalTrailingSeparator': function(nv, ov) {
      console.log('>>>>', nv)
      this.$emit('update:item',
        { name: 'Path', args: { ...this.$data, patterns: nv }})
    }
  },

  created() {
    this.name = this.item.name
    this.args = { ... this.item.args }
  }
}
</script>
