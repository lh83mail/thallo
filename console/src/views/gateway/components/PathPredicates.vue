<template>
  <el-collapse-item title="路径匹配(Path)" name="Path">
    <el-form-item label="patterns">
      <el-input name="patterns" />
    </el-form-item>
    <el-form-item label="完全匹配">
      <el-switch :value="true" name="matchOptionalTrailingSeparator" />
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
  // data() {
  //   return {
  //     item: this.item
  //   }
  // },

  created() {
    console.log('LLLLLL', this.item)
  }
}
</script>
