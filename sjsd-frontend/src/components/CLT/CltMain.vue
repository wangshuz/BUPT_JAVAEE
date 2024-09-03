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
            <el-col :span="4" v-for="(item, index) in filteredData" :key="index" :offset="index%3 == 0 ? 6 : 2">
                <el-card class="card_item" :body-style="{ padding: '0px'}" @click.native="clickCard(item.id)">
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

        <el-pagination class="pagination" 
        :page-sizes="[12, 15, 18, 21, 24]"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        background layout="sizes, prev, pager, next, jumper, total" :total="1000">
        </el-pagination>

        <search-box :childContent.sync='content' />

        <side-bar :listData="mchType" @changeLabel="changeLabel"/>
        

    </div>
</template>

<script>
import SearchBox from '../SearchBox.vue';
import SideBar from '../SideBar.vue';
import api from '../../api/api.js';

export default {
    components: { SideBar,SearchBox },
    name: "TopPicture",
    props: { },
    data(){
        return {
            label:"0",
            page: 1,
            content: "",
            pageSize:12,
            TopPicture: [
                {
                    "id":"10001",
                    "url":"https://th.bing.com/th?id=OIP.iLWguBQMXDriaDoVC4IBoQHaHa&w=250&h=250&c=8&rs=1&qlt=90&r=0&o=6&dpr=2&pid=3.1&rm=2"
                },
                // {
                //     "id":"10002",
                //     "url":"https://ts1.cn.mm.bing.net/th?id=OIP-C.Ge0ka-likMYd2EIeZjNfTgHaFV&w=294&h=212&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.5&pid=3.1&rm=2"
                // },
                // {
                //     "id":"10003",
                //     "url":"https://img-baofun.zhhainiao.com/fs/3fc54f2b553dc552c23fd2df0ee0159e.jpg"
                // },
            ],
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"薯条",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
                // {
                //     "id":"10001",
                //     "name":"汉堡",
                //     "intro":"第一类",
                //     "label":"1", 
                //     "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                // },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第一类",
            //         "label":"1", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
            //     {
            //         "id":"10001",
            //         "name":"汉堡",
            //         "intro":"第二类",
            //         "label":"2", 
            //         "url":"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            //     },
              
            // ],
            mchIntro:[],
            // mchType:[
            //     {
            //         "id":10001,
            //         "typeName":"全部",
            //         "cb":function(){
            //           this.$emit('changeLabel','0');
            //         },
            //     },
            //     {
            //         "id":10002,
            //         "typeName":"配菜",
            //         "cb":function(){
            //           this.$emit('changeLabel','1');
            //         },
            //     },
            //     {
            //         "id":10003,
            //         "typeName":"西餐",
            //         "cb":function(){
            //           this.$emit('changeLabel','2');
            //         },
            //     },
            //     {
            //         "id":10004,
            //         "typeName":"面点",
            //         "cb":function(){
            //           this.$emit('changeLabel','3');
            //         },
            //     },
            //     {
            //         "id":10005,
            //         "typeName":"素食",
            //         "cb":function(){
            //           this.$emit('changeLabel','4');
            //         },
            //     },
            // ],
            mchType:[]
        };
    
    },
    methods: {
        changeLabel(label)
        {
            this.label = label;
            this.filteredData();
        },
        clickCard(id){
            this.$router.push({ 
                name: 'CltStore',
                query :{
                    id:id
                } 
            }); // 通过路由的名称跳转
        },
        handleCurrentChange: function(val){
            this.page = val;
            this.filteredData();
        },
        handleSizeChange: function(val){
            this.pageSize = val;
        },
        
        
    },
    mounted() {
        api.getMerchantIntros().then(result => {
            this.mchIntro = result.data.data;
        });
        // api.getMerchantTypes().then(result => {
        //     this.mchTypes = result.data.data;
        //     this.filteredData();
        // });
        
    },
    computed: {
        filteredData() {
            let filtered = this.mchIntro;

            if (this.label != '0'||this.content!=="") {
                filtered = filtered.filter(item =>{
                    return(
                        (this.label=='0' || item.label == this.label )&&(this.content==="" || item.name.includes(this.content) || item.intro.includes(this.content))
                    )
                }
                    
                );
            }
            let size = this.pageSize;
            let start = (this.page-1)*size;
            let end = start + size;
            filtered = filtered.slice(start,end);

            return filtered;
        }
    }
}
</script>


<style scoped>
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



/* 分页 */
    .pagination {
        position: relative;
        left:35%
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


</style>
