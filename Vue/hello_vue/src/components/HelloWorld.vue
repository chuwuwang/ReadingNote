<template>
  <div>
    <el-button @click="visible = true">Button</el-button>
    <el-dialog :visible.sync="visible" title="Hello World">
      <p>Try Element</p>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'HelloWorld',
    data() {
      return {
        visible: false,
      }
    }
  }
</script>
