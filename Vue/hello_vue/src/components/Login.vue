<template>
  <div id="container">
    <div class="content">
      <span>登陆</span>
      <el-form ref="form" :model="form" label-width="120px">

      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      msg: 'I am H1 page,Welcome to H1'
    }
  }
}
</script>

<style>
#container {
  display: flex;
  align-items: center;
  width: 100%;
  height: 100%;
  justify-content: center;
  color: #333;
}

.content {
  width: 480px;
  height: 512px;
  align-items: center;
  background-color: #b3c0d1;
}
</style>
