<!-- 用户首页 -->
<!-- #/clt -->
 
<template>
  <div class="home">
    <el-container style="font-size: 17px;">
      <el-header style="background-color: #FFAC00 ; height: 65px;" >
        <el-menu
          :default-active="activeIndex"
          class="CLT-menu-h"
          mode="horizontal"
          @select="handleSelect"
          style="background-color: #FFAC00; font-size:17px; height: 100%" 
        >
          <el-menu-item index="1" style="font-size:17px">
              <router-link to="/">LOGO</router-link>
          </el-menu-item>
          <el-menu-item index="2" id="photo">
            <el-avatar :size="40" :src="circleUrl"></el-avatar>
          </el-menu-item>
          <el-menu-item index="3" id="name">用户名</el-menu-item>
        </el-menu>
      </el-header>

      <el-container class="container">
        <div class="aside">
          <el-aside width="200px" height="100%">
            <el-col :span="24">
              

            </el-col>
          </el-aside>
        </div>

        <el-main>
          <router-view/>    <!-- 组件 -->
        </el-main>

      </el-container>
    </el-container>
  </div>

</template>


<script>

</script>


<style scoped>
.el-menu {
  font-size: 17px;
}

.el-menu{
  font-size:17px;
}

.el-menu-item {
  font-size:17px
}

#photo {
  left: 75%;
}

#name {
  left: 75%;
}


.CLT-menu-h .el-menu-item:hover{
  background-color: #FFAC00 !important;
}

.MCH-menu-h .el-menu-item.is-active{
  background-color: #FFAC00 !important;
}
</style>
