<template class="template">
    <div class="container">
        <div class="block">
            <el-carousel trigger="click">
                <el-carousel-item v-for="(item,index) in TopPicture" :key="index">
                    <img
                        class="TPicture"
                        :src="item.url"
                    />
                </el-carousel-item>
            </el-carousel>
        </div>

        <el-row>
            <el-col :span="4" v-for="(item, index) in cardData" :key="index" :offset="index%3 == 0 ? 6 : 2">
                <el-card class="card_item" :body-style="{ padding: '0px'}">
                    <img :src="item.url" class="image">
                    <div style="padding: 14px;">
                        <span>{{ item.name }}</span>
                        <div class="bottom clearfix">
                            <time class="time">{{ item.intro }}</time>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        
        <side-bar :listData="listData" />
        

    </div>
</template>

<script>
import SideBar from '../SideBar.vue';

export default {
    components: { SideBar },
    name: "TopPicture",
    props: {},
    data(){
        return {
            TopPicture: [
                {
                    "id":"10001",
                    "url":"https://th.bing.com/th?id=OIP.iLWguBQMXDriaDoVC4IBoQHaHa&w=250&h=250&c=8&rs=1&qlt=90&r=0&o=6&dpr=2&pid=3.1&rm=2"
                },
                {
                    "id":"10001",
                    "url":"https://ts1.cn.mm.bing.net/th?id=OIP-C.Ge0ka-likMYd2EIeZjNfTgHaFV&w=294&h=212&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.5&pid=3.1&rm=2"
                },
                {
                    "id":"10001",
                    "url":"https://img-baofun.zhhainiao.com/fs/3fc54f2b553dc552c23fd2df0ee0159e.jpg"
                },

                
            ],
            cardData: [
                {
                    "id":"10001",
                    "name":"汉堡王",
                    "intro":"好吃的汉堡",
                    "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                },
                {
                    "id":"10002",
                    "name":"汉堡王",
                    "intro":"好吃的汉堡",
                    "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                },
                {
                    "id":"10003",
                    "name":"汉堡王",
                    "intro":"好吃的汉堡",
                    "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                },
                {
                    "id":"10004",
                    "name":"汉堡王",
                    "intro":"好吃的汉堡",
                    "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                },
            ],
            listData:[
                {
                    "id":10001,
                    "typeName":"主食",
                    "cb":function(){
                        alert("主食");
                    },
                },
                {
                    "id":10002,
                    "typeName":"配菜",
                    "cb":function(){},
                },
                {
                    "id":10003,
                    "typeName":"西餐",
                    "cb":function(){},
                },
                {
                    "id":10004,
                    "typeName":"面点",
                    "cb":function(){},
                },
                {
                    "id":10005,
                    "typeName":"素食",
                    "cb":function(){},
                },
            ]
        };

    },
    methods(){
    }
}
</script>


<style>
    .container{
        width: 100%;
        height: 90vh;
        background-color: beige;
        overflow: scroll;
    }

    .el-carousel{
        width: 100vw;
        height: auto;
    }


    .TPicture{
        width: 100%;
        height: 100%;
        /* object-fit:cover; */
    }


    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

/* 卡片 */
  .card_item{
    margin:15px;
  }

  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }


/* .side-bar{
  background-color:#CECECE ;
  width:160px;
  height: 75vh;
  position: absolute;
  overflow-x:hidden;
  overflow-y:auto;
  border-radius: 15px;
  left: 5%;
  top: 15%;
  opacity: 0.7;
  z-index: 100;
}

.side-bar::-webkit-scrollbar {
  display: none;
}

.menu-v .el-menu-item{
  opacity: 1;
  font-size: 20px;
  background-color:#CECECE ;
}

.menu-v .el-menu-item.is-active{
  opacity: 1;
  font-size: 20px;
  background-color: #333;
} */

</style>
