<template>
  <collapge-item-conainer title="URL查询参数匹配(Weight)" :name="name" @close="fireCloseEvent">
    <el-form-item label="分组">
      <el-input v-model="args.group" />
    </el-form-item>
    <el-form-item label="权重值">
      <el-input-number v-model="args.weight" />
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
      name: 'Weight',
      args: {
        group: undefined,
        weight: 1
      }
    }
  },

  watch: {
    'args.group': function(nv, ov) {
      this.fireUpdate(this.$data)
    },
    'args.weight': function(nv, ov) {
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
