<template class="template">
    <div class="container">
        <div class="block">
            <el-carousel trigger="click">
                <el-carousel-item v-for="(item,index) in topPicture" :key="index">
                    <img
                        class="TPicture"
                        :src="item.url" 
                        @click="clickCard(item.id)"
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

        <side-bar :listData="mchTypes" @changeLabel="changeLabel"/>
        

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
            label: 0,
            page: 1,
            content: "",
            pageSize:12,
            topPicture: [],
            mchIntro:[],
            mchTypes:[]
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
    async created() {
        try {
            // 使用 await 等待异步请求完成
            const response = await api.getMerchantIntros();
            this.mchIntro = response.data.data;
         
        } catch (error) {
            console.error('获取商家简介失败', error);
        }
        try {
            const response = await api.getMerchantTypes();
            this.mchTypes = [{
                    "id":0,
                    "typeName":"全部",
                    "cb":function(){
                    this.$emit('changeLabel',0);
                    },
                },]
            let types = response.data.data;
            for (let type of types) {
                type.cb = function(){ this.$emit('changeLabel', type.typeId); };
                this.mchTypes.push(type); // 使用 push 而不是 add，假设 mchTypes 是一个数组
            }
             
        } catch (error) {
            console.error('获取商家类型失败', error);
        }
        try {
            const response = await api.getTopPicture();
            this.topPicture = response.data.data;
        } catch (error) {
            console.error('获取推荐图片失败', error);
        }
        
    },
    async mounted() {
        
    },
    computed: {
        filteredData() {
            let filtered = this.mchIntro;

            if (this.label != 0||this.content!="") {
                filtered = filtered.filter(item =>{
                    return(
                        (this.label==0 || item.label == this.label)&&(this.content==="" || item.name.includes(this.content) || item.intro.includes(this.content))
                    )
                });
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
    height: 200px;
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
