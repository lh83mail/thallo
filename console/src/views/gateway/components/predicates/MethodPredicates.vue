<template>
  <collapge-item-conainer title="请求头方法匹配(Method)" :name="name" @close="fireCloseEvent">
    <el-form-item label="patterns">
      <el-select v-model="args.methods" multiple placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>

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
      name: 'Method',
      args: {
        methods: undefined
      },
      options: [{
        value: 'GET',
        label: 'GET'
      }, {
        value: 'POST',
        label: 'POST'
      }, {
        value: 'DELETE',
        label: 'DELETE'
      }, {
        value: 'PUT',
        label: 'PUT'
      }, {
        value: 'PATCH',
        label: 'PATCH'
      }, {
        value: 'OPTION',
        label: 'OPTION'
      }]
    }
  },

  watch: {
    'args.methods': function(nv, ov) {
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
