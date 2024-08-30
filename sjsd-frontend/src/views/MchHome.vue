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
            <el-avatar :size="40" :src="circleUrl"></el-avatar>
          </el-menu-item>
          <el-menu-item index="3" id="name">用户名</el-menu-item>
        </el-menu>
      </el-header>

      <el-container class="container">
        <div class="aside">
          <el-aside width="200px" height="100%">
            <el-col :span="24">
              <el-menu
                default-active="1"
                class="MCH-menu-v"
                background-color="#333333"
                text-color="#F8F8FF"
                active-text-color="#ffd04b"
                active-background-color="#CECECE"
              >
                <el-menu-item index="1">
                  <i class="el-icon-house"></i>
                  <span slot="title">商家首页</span>
                </el-menu-item>
                <el-menu-item index="2">
                  <i class="el-icon-tickets"></i>
                  <span slot="title">订单管理</span>
                </el-menu-item>
                <el-menu-item index="3">
                  <i class="el-icon-menu"></i>
                  <span slot="title">菜品管理</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <i class="el-icon-setting"></i>
                  <span slot="title">商家详情</span>
                </el-menu-item>
                <el-menu-item index="5">
                  <i class="el-icon-odometer"></i>
                  <span slot="title">>数据统计</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-aside>
        </div>

        <el-main>
          <!-- 核心区域代码 -->
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
export default {
  data() {
    return {
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
    };
  },
  methods: {
    handleSelect: function () {
      // alert("Select");
    },
  },
};
</script>

<style>
.el-menu {
  font-size: 17px;
}

.MCH-menu-v {
  height: 90vh;
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

.MCH-menu-v .el-menu-item.is-active {
  background-color: #CECECE !important;
  color:black !important;
}

</style>

