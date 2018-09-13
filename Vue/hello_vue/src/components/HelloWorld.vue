<template>
  <div>
    <el-container>

      <el-aside style="width: 240px">

        <el-menu
          default-active="2"
          text-color="#969696"
          background-color="#2E3748"
          active-text-color="#fff">

          <el-menu-item index="1" class="left-menu-item">
            <i class="el-icon-menu"></i>
            <span slot="title">Lainey</span>
          </el-menu-item>

          <el-menu-item index="2" class="left-menu-item">
            <i class="el-icon-document"></i>
            <span slot="title">文档</span>
          </el-menu-item>

          <el-submenu index="3" class="left-menu-item">
            <template slot="title"><i class="el-icon-location"></i>导航二</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>

          <el-menu-item index="4" class="left-menu-item">
            <i class="el-icon-setting"></i>
            <span slot="title">设置</span>
          </el-menu-item>

        </el-menu>

      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>王小虎</span>
        </el-header>

        <el-main>
          <el-table :data="tableData">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
          </el-table>
        </el-main>

      </el-container>

    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'HelloWorld',
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item)
      }
    }
  }
</script>

<style>

  .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 56px;
  }

  .el-aside {
    background-color: #2E3748;
  }

  .left-menu-item {
    text-align: left;
    margin: 0;
    padding: 0;
  }


</style>
