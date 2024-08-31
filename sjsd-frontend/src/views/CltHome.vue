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
              <router-link to="/">LOGO</router-link>
          </el-menu-item>


          <el-menu-item index="4" class="search">
            <el-col :span="12">
              <i class="el-icon-search" style="font-size: 30px;"></i>
              <el-autocomplete
                class="inline-input"
                v-model="state2"
                :fetch-suggestions="querySearch"
                placeholder="请输入"
                :trigger-on-focus="false"
                @select="handleSelect"
              ></el-autocomplete>
            </el-col>
          </el-menu-item>

         <el-menu-item index="2" id="photo">
            <el-avatar :size="40" :src="circleUrl"></el-avatar>
          </el-menu-item>
          <el-menu-item index="3" id="name">用户名</el-menu-item>
        </el-menu>
      </el-header>


      <el-main style="padding:0px">
        <router-view/>    <!-- 组件 -->
      </el-main>       



    </el-container>
  </div>

</template>

<script>

  export default {
    data() {
      return {
        restaurants: [],
        state1: '',
        state2: '',
        circleUrl:
          "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      };
    },
    methods: {
      querySearch(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      loadAll() {
        return [
          { "value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号" },
        ];
      },
      handleSelect(item) {
        console.log(item);
      }
    },
    mounted() {
      this.restaurants = this.loadAll();
    }
  }


</script>

<style scoped>
*{
  caret-color: transparent;
}

.search{
  left:30%;
  
}

.inline-input{
  width:200px;
  caret-color: #333;
}

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
  left:50%
}

#name {
  left: 50%;
}


.CLT-menu-h .el-menu-item:hover{
  background-color: #FFAC00 !important;
}

.CLT-menu-h .el-menu-item.is-active{
  background-color: #FFAC00 !important;
}
</style>
