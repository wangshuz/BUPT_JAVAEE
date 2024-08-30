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
          style="background-color: #FFAC00; font-size:17px; height: 100%">
          <el-menu-item index="1" style="font-size:17px">
              <router-link to="/">LOGO</router-link>
          </el-menu-item>
          <el-menu-item index="2" id="photo">
            <el-avatar :size="40" :src="circleUrl"></el-avatar>
          </el-menu-item>
         <el-menu-item index="2" id="photo">
            <el-avatar :size="40" :src="circleUrl"></el-avatar>
          </el-menu-item>
          <el-menu-item index="3" id="name">用户名</el-menu-item>
        </el-menu>
      </el-header>

      <div class="CLT-aside-bar">
          <el-col :span="24">
          <el-menu
            default-active="1"
            class="CLT-menu-v"
            text-color="#000"
            active-text-color="#ffd04b">

            <el-menu-item index="1">
              <i class="el-icon-dish"></i>
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
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-menu-item index="5">
              <i class="el-icon-odometer"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-menu-item index="5">
              <i class="el-icon-odometer"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-menu-item index="5">
              <i class="el-icon-odometer"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-menu-item index="5">
              <i class="el-icon-odometer"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-menu-item index="5">
              <i class="el-icon-odometer"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-menu-item index="5">
              <i class="el-icon-odometer"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-menu-item index="5">
              <i class="el-icon-odometer"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
            <el-menu-item index="5">
              <i class="el-icon-odometer"></i>
              <span slot="title">数据统计</span>
            </el-menu-item>
          </el-menu>
        </el-col>


      </div>

      <el-main>
        <router-view/>    <!-- 组件 -->
      </el-main>


        <el-col :span="12">
          <el-autocomplete
            class="inline-input"
            v-model="state2"
            :fetch-suggestions="querySearch"
            placeholder="请输入内容"
            :trigger-on-focus="false"
            @select="handleSelect"
          ></el-autocomplete>
        </el-col>



    </el-container>
  </div>

</template>

<script>

  export default {
    data() {
      return {
        restaurants: [],
        state1: '',
        state2: ''
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
          { "value": "Hot honey 首尔炸鸡（仙霞路）", "address": "上海市长宁区淞虹路661号" },
          { "value": "新旺角茶餐厅", "address": "上海市普陀区真北路988号创邑金沙谷6号楼113" },
          { "value": "泷千家(天山西路店)", "address": "天山西路438号" },
          { "value": "胖仙女纸杯蛋糕（上海凌空店）", "address": "上海市长宁区金钟路968号1幢18号楼一层商铺18-101" },
          { "value": "贡茶", "address": "上海市长宁区金钟路633号" },
          { "value": "豪大大香鸡排超级奶爸", "address": "上海市嘉定区曹安公路曹安路1685号" },
          { "value": "茶芝兰（奶茶，手抓饼）", "address": "上海市普陀区同普路1435号" },
          { "value": "十二泷町", "address": "上海市北翟路1444弄81号B幢-107" },
          { "value": "星移浓缩咖啡", "address": "上海市嘉定区新郁路817号" },
          { "value": "阿姨奶茶/豪大大", "address": "嘉定区曹安路1611号" },
          { "value": "新麦甜四季甜品炸鸡", "address": "嘉定区曹安公路2383弄55号" },
          { "value": "Monica摩托主题咖啡店", "address": "嘉定区江桥镇曹安公路2409号1F，2383弄62号1F" },
          { "value": "浮生若茶（凌空soho店）", "address": "上海长宁区金钟路968号9号楼地下一层" },
          { "value": "NONO JUICE  鲜榨果汁", "address": "上海市长宁区天山西路119号" },
          { "value": "CoCo都可(北新泾店）", "address": "上海市长宁区仙霞西路" },
          { "value": "快乐柠檬（神州智慧店）", "address": "上海市长宁区天山西路567号1层R117号店铺" },
          { "value": "Merci Paul cafe", "address": "上海市普陀区光复西路丹巴路28弄6号楼819" },
          { "value": "猫山王（西郊百联店）", "address": "上海市长宁区仙霞西路88号第一层G05-F01-1-306" },
          { "value": "枪会山", "address": "上海市普陀区棕榈路" },
          { "value": "纵食", "address": "元丰天山花园(东门) 双流路267号" },
          { "value": "钱记", "address": "上海市长宁区天山西路" },
          { "value": "壹杯加", "address": "上海市长宁区通协路" },
          { "value": "唦哇嘀咖", "address": "上海市长宁区新泾镇金钟路999号2幢（B幢）第01层第1-02A单元" },
          { "value": "爱茜茜里(西郊百联)", "address": "长宁区仙霞西路88号1305室" },
          { "value": "爱茜茜里(近铁广场)", "address": "上海市普陀区真北路818号近铁城市广场北区地下二楼N-B2-O2-C商铺" },
          { "value": "鲜果榨汁（金沙江路和美广店）", "address": "普陀区金沙江路2239号金沙和美广场B1-10-6" },
          { "value": "开心丽果（缤谷店）", "address": "上海市长宁区威宁路天山路341号" },
          { "value": "超级鸡车（丰庄路店）", "address": "上海市嘉定区丰庄路240号" },
          { "value": "妙生活果园（北新泾店）", "address": "长宁区新渔路144号" },
          { "value": "香宜度麻辣香锅", "address": "长宁区淞虹路148号" },
          { "value": "凡仔汉堡（老真北路店）", "address": "上海市普陀区老真北路160号" },
          { "value": "港式小铺", "address": "上海市长宁区金钟路968号15楼15-105室" },
          { "value": "蜀香源麻辣香锅（剑河路店）", "address": "剑河路443-1" },
          { "value": "北京饺子馆", "address": "长宁区北新泾街道天山西路490-1号" },
          { "value": "饭典*新简餐（凌空SOHO店）", "address": "上海市长宁区金钟路968号9号楼地下一层9-83室" },
          { "value": "焦耳·川式快餐（金钟路店）", "address": "上海市金钟路633号地下一层甲部" },
          { "value": "动力鸡车", "address": "长宁区仙霞西路299弄3号101B" },
          { "value": "浏阳蒸菜", "address": "天山西路430号" },
          { "value": "四海游龙（天山西路店）", "address": "上海市长宁区天山西路" },
          { "value": "樱花食堂（凌空店）", "address": "上海市长宁区金钟路968号15楼15-105室" },
          { "value": "壹分米客家传统调制米粉(天山店)", "address": "天山西路428号" },
          { "value": "福荣祥烧腊（平溪路店）", "address": "上海市长宁区协和路福泉路255弄57-73号" },
          { "value": "速记黄焖鸡米饭", "address": "上海市长宁区北新泾街道金钟路180号1层01号摊位" },
          { "value": "红辣椒麻辣烫", "address": "上海市长宁区天山西路492号" },
          { "value": "(小杨生煎)西郊百联餐厅", "address": "长宁区仙霞西路88号百联2楼" },
          { "value": "阳阳麻辣烫", "address": "天山西路389号" },
          { "value": "南拳妈妈龙虾盖浇饭", "address": "普陀区金沙江路1699号鑫乐惠美食广场A13" }
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


.CLT-aside-bar{
  background-color:#CECECE ;
  width:160px;
  height: 80vh;
  position: absolute;
  overflow-x:hidden;
  overflow-y:auto;
  border-radius: 15px;
  left: 5%;
  top: 15%;
  opacity: 0.7;
}

.CLT-menu-v .el-menu-item{
  opacity: 1;
  font-size: 20px;
  background-color:#CECECE ;
}

.CLT-menu-v .el-menu-item.is-active{
  opacity: 1;
  font-size: 20px;
  background-color: #333;
}



.CLT-aside-bar::-webkit-scrollbar {
  	display: none;
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
