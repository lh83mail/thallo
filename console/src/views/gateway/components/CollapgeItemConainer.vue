<template>
  <el-collapse-item :name="name">
    <template slot="title">
      {{ title }}
      <slot name="title" />
      <el-popconfirm title="确定吗？" @onConfirm="fireCloseEvent">
        <i slot="reference" class="el-icon-remove ml-sm text-danger width-1" @click.stop="" />
      </el-popconfirm>
    </template>
    <slot />
  </el-collapse-item>
</template>

<script>
export default {
  props: {
    name: String,
    title: String
  },
  methods: {
    fireCloseEvent() {
      this.$emit('close')
    }
  }
}
</script>
