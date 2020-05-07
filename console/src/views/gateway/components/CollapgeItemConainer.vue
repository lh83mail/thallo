<template>
  <el-collapse-item name="Path">
    <template slot="title">
      {{ title }}
      <slot name="title" />
      <el-popconfirm title="确定吗？">
        <i slot="reference" class="el-icon-remove ml-sm text-danger width-1" @click.stop="fireCloseEvent" />
      </el-popconfirm>
    </template>
    <slot />
  </el-collapse-item>
</template>

<script>
export default {
  props: {
    title: String
  },
  methods: {
    fireCloseEvent() {
      this.$emit('close')
    }
  }
}
</script>
