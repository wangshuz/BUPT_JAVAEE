<template>
    <div class="container">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>数据统计</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleClick(1)">详情>></el-button>
            </div>
            <div class="content-container">
                <div v-for="(item,index) in statisticData" :key="index" class="card_item">
                    <div class="card_item_title"  @click="clickHandler(1)">{{item.name}}</div>
                    <div class="card_item_content">{{ item.value }}</div>
                </div>
            </div>
        </el-card>

        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>订单管理</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleClick(2)">详情>></el-button>
            </div>
            <div class="content-container">
                <div v-for="(item,index) in orderData" :key="index" class="card_item">
                    <div class="card_item_title">{{item.name}}</div>
                    <div class="card_item_content">{{ item.value }}</div>
                </div>
            </div>
        </el-card>

        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>菜品管理</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleClick(3)">详情>></el-button>
            </div>
            <div class="content-container">
                <div v-for="(item,index) in menuData" :key="index" class="card_item">
                    <div class="card_item_title">{{item.name}}</div>
                    <div class="card_item_content">{{ item.value }}</div>
                </div>
            </div>
        </el-card>

        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>商家详情</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleClick(4)">详情>></el-button>
            </div>
            <div class="content-container">
                <div v-for="(item,index) in detailData" :key="index" class="card_item" >
                    <div class="card_item_title">{{item.name}}</div>
                    <div class="card_item_content" style="font-size: 18px;">{{ item.value }}</div>
                </div>
            </div>
        </el-card>
    </div>
</template>

<script>
import api from '../../api/api.js';

export default {
    data(){
        return {
            statisticData: [],
            orderData: [],
            menuData: [],
            detailData: []
        }
    },
    methods:{
        handleClick: function(idx) {
            var path_ = ""
            switch(idx){
                case 1:
                    path_ = "/mch/data"
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
                    break;
            }
            this.$router.push(
                {
                    path:path_
                }
            )

        }

    },
    async created(){
        try {
            // 使用 await 等待异步请求完成
            const response = await api.getCurData();
            this.statisticData = response.data.data;
        
        } catch (error) {
            console.error('获取商家统计数据失败', error);
        }
        try {
            alert(111);
            const response = await api.getMonthlyOrderStats();
            let data = response.data.data;
            this.orderData = [
                {
                    "name":"待接单",
                    // "value":data.pendingOrderCount
                    "value":1
                },
                // {
                //     "name":"待派送",
                //     "value":data.waitingForDeliveryCount
                // },
                // {
                //     "name":"派送中",
                //     "value":data.deliveringCount
                // },
                // {
                //     "name":"已完成",
                //     "value":data.completedCount
                // },
                // {
                //     "name":"已取消",
                //     "value":data.canceledCount
                // },
            ]

        } catch (error) {
            console.error('获取商家订单数据失败', error);
        }
        try {
            const response = await api.getProdStats();
            let data = response.data.data;
            this.menuData = [
                {
                    "name":"菜品总数",
                    "value":data.totalCategoryCount
                },
                {
                    "name":"在售菜品",
                    "value":data.availableProductCount
                },
                {
                    "name":"停售菜品",
                    "value":data.unavailableProductCount
                },
            ]
        
        } catch (error) {
            console.error('获取商家菜品信息失败', error);
        }
        try {
            const response = await api.getMerchantInfo();
            let data = response.data.data;
            this.detailData = [
                {
                    "name":"商家名称",
                    "value":data.merchantName
                },
                {
                    "name":"商家电话",
                    "value":data.phoneNumber
                },
                {
                    "name":"商家地址",
                    "value":data.merchantAddress
                },
                {
                    "name":"营业时间",
                    "value":data.openingHours
                },
            ]
        } catch (error) {
            console.error('获取商家基本信息失败', error);
        }
    }

}

</script>

<style scoped>

.container {
    width: 100%;
    height: 90vh;
    overflow: scroll;
}

.el-card {
    margin:10px;
}

.content-container {
  display: flex;
  flex-direction: row;
  gap: 10px; /* 控制子项之间的间距 */
  justify-content: space-between; /* 平均分布子项 */
}

.card_item {
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* 标题左对齐 */
  border: 1px solid #ccc; /* 边框颜色 */
  padding: 10px;
  width: 220px; /* 设置宽度，视需求调整 */
  height: 120px; /* 设置高度，视需求调整 */
  position: relative; /* 使内容位置定位 */
}

.card_item_title {
  font-size: 20px; /* 标题字号 */
  margin-bottom: 10px; /* 标题与内容之间的间距 */
}

.card_item_content {
  font-size: 40px; /* 内容字号 */
  position: absolute; /* 绝对定位 */
  bottom: 45%; /* 垂直居中 */
  right: 45%; /* 水平居中 */
  transform: translate(50%, 50%); /* 使内容在右下角居中 */
}
</style>