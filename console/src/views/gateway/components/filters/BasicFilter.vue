<template>
  <collapge-item-conainer :title="innerTitle" :name="itemName" @close="fireCloseEvent">
    <el-form-item v-for="f in fields" :key="f.name" :label="f.label">
      <el-input v-if="f.type === 'string'" v-model="args[f.name]" />
      <el-input-number v-if="f.type === 'number'" v-model="args[f.name]" />
      <el-switch v-if="f.type === 'boolean'" v-model="args[f.name]" />
      <el-date-picker v-if="f.type === 'datetime'" v-model="args[f.name]" type="datetime" />
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
    itemName: {
      type: String,
      required: true
    },
    name: {
      type: String,
      required: true
    },
    title: {
      type: String,
      required: true
    },
    fields: {
      type: Array,
      required: true
    }
  },

  data() {
    return {
      args: {}
    }
  },

  computed: {
    innerTitle() {
      return `${this.title}(${this.name})`
    }
  },

  created() {
    this.fields.forEach(f => {
      this.$set(this.args, f.name, f.value)
    })
    this.$watch('args', (newVal, oldVal) => this.fireUpdate(), { deep: true })
  },

  methods: {
    fireCloseEvent() {
      this.$emit('close')
    },
    fireUpdate() {
      const data = {
        name: this.name,
        args: this.args
      }
      this.$emit('change', data)
    }
  }
}
</script>
