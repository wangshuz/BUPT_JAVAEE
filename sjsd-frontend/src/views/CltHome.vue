<!-- 用户首页 -->
<!-- #/clt -->
 
<template>    
  <div class="home">
    <el-container style="font-size: 17px;">
      <el-header style="background-color: #FFAC00 ; height: 65px;" >
        <el-menu
          default-active="1"
          class="CLT-menu-h"
          mode="horizontal"
          @select="handleSelect"
          style="background-color: #FFAC00; font-size:17px; height: 100%">
          <el-menu-item index="1" style="font-size:17px">
              <router-link to="/clt/main">SJSD</router-link>
          </el-menu-item>

          <el-menu-item index="2" id="photo">
            <upload-picture />
          </el-menu-item>
          <el-menu-item index="3" id="name">
            <personal-setting/>
          </el-menu-item>
          <el-menu-item index="4" id="order">
            <router-link to="/clt/order">订单详情</router-link>
          </el-menu-item>
        </el-menu>
      </el-header>


      <el-main style="padding:0px">
        <router-view/>    <!-- 组件 -->
      </el-main>       



    </el-container>
  </div>

</template>

<script>
import PersonalSetting from '../components/PersonalSetting.vue';
import UploadPicture from '../components/UploadPicture.vue';

  export default {
  components: { PersonalSetting, UploadPicture },
    data() {
      return {
        restaurants: [],
      };
    },
    methods: {
    
    }
  }


</script>

<style scoped>
*{
  caret-color: transparent;
}

#photo {
  left:70%
}

#name {
  left: 70%;
}

#order {
  left:70%
}

</style>
