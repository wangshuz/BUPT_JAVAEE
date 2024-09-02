<!-- 商家首页 -->
 <!-- "#/mch" -->

 <template>
  <div class="home">
    <el-container style="font-size: 17px;">
      <el-header style="background-color: #FFAC00 ; height: 65px;" >
        <el-menu
          :default-active="activeIndex"
          class="MCH-menu-h"
          mode="horizontal"
          @select="handleSelect"
          style="background-color: #FFAC00; font-size:17px; height: 100%" 
        >
          <el-menu-item index="1" style="font-size:17px">
              <router-link to="/">LOGO</router-link>
          </el-menu-item>
          <el-menu-item index="2" id="photo">
            <upload-picture />
          </el-menu-item>
          <el-menu-item index="3" id="name">
            <personal-setting />
          </el-menu-item>
        </el-menu>
      </el-header>

      <el-container class="container">
        <div class="aside">
          <el-aside width="160px" height="100%">
            <el-col :span="24">
              <el-menu
                default-active="1"
                class="MCH-menu-v"
                background-color="#333333"
                text-color="#F8F8FF"
                active-text-color="#ffd04b"
                active-background-color="#CECECE"
              >
                <el-menu-item index="1" @click="handleClick(1)">
                  <i class="el-icon-house"></i>
                  <span>商家首页</span>
                </el-menu-item>
                <el-menu-item index="2" @click="handleClick(2)">
                  <i class="el-icon-tickets"></i>
                  <span>订单管理</span>
                </el-menu-item>
                <el-menu-item index="3" @click="handleClick(3)">
                  <i class="el-icon-menu"></i>
                  <span>菜品管理</span>
                </el-menu-item>
                <el-menu-item index="4" @click="handleClick(4)">
                  <i class="el-icon-setting"></i>
                  <span>商家详情</span>
                </el-menu-item>
                <el-menu-item index="5" @click="handleClick(5)">
                  <i class="el-icon-odometer"></i>
                  <span>数据统计</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-aside>
        </div>

        <el-main class="main">
          <router-view/>    <!-- 组件 -->
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
import PersonalSetting from '../components/PersonalSetting.vue';
import UploadPicture from '../components/UploadPicture.vue';
export default {
  components: { UploadPicture, PersonalSetting },
  data() {
    return {
      
    };
  },
  methods: {
    handleSelect: function () {
      // alert("Select");
    },
    handleClick: function (idx) {
      var path_ = ""
      var query_ = {}
      const current_path = this.$route.path
      switch (idx) {
        case 1:
          path_ = "/mch/main"
          break;
        case 2:
          path_ = "/mch/order"
          break;
        case 3:
          path_ = "/mch/menu"
          break;
        case 4:
          path_ = "/mch/detail"
          break;
        case 5:
          path_ = "/mch/data"
         break;
        
      }
      if(current_path!=path_) {
        this.$router.push(
          {
            path:path_
          }
        )
      }
    },
  },
};
</script>

<style scoped>

.main{
  padding: 0px;
  margin: 0px;
}

.el-menu {
  font-size: 17px;

}

.MCH-menu-v {
  height: 90vh;
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

.MCH-menu-v .el-menu-item.is-active {
  background-color: #CECECE !important;
  color:black !important;
}

.MCH-menu-h .el-menu-item:hover{
  background-color: #FFAC00 !important;
}

.MCH-menu-h .el-menu-item.is-active{
  background-color: #FFAC00 !important;
}

*{
  caret-color: transparent;
}

</style>

