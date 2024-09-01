<!-- 用户订单详情 -->
<!-- "#/clt/order/detail" -->
<template>
    <div>
        <div class="mchname">{{ mchname }} ⟩</div>
        <span class="orderstate" v-if="state === '1'" style="color: orange;">待收货</span>
        <span class="orderstate" v-if="state === '2'" style="color: green;">已完成</span>
        <span class="orderstate" v-if="state === '3'" style="color: red;">已取消</span>
        <hr class="top_line_hr">
        <div class="scroll-box">
            <div id="pdtcard" v-for="(item, index) in info" :key="index" style="background-color: #CECECE; border-radius: 20px;">
                <!-- 图片部分 -->
                <div>
                    <img id="msgpicture" class="msgcmp" :src="item[0]" alt="商品图片">
                </div>
                <!-- 订单信息部分 -->
                <div id="msgname" class="msgcmp">{{ item[1] }}</div>
                <div id="msgprice" class="msgcmp">
                    <span style="font-size: 20px;">单价：</span>
                    <span style="font-size: 30px;">{{ item[2] }}</span>
                </div>
                <div id="msgnum" class="msgcmp">x{{ item[3] }}</div>
                <div id="msgsum" class="msgcmp">小计：
                    <span style="font-size: 60px;">{{ item[3] * item[2] }}</span>
                </div>
            </div>
        </div>
        <hr class="h-left-hr">
        <div class="orderID">订单号：{{ orderID }}</div>
        <div class="address-note">
            <div class="address">配送地址：{{ address }}</div>
            <div class="note">备注：{{ note }}</div>
        </div>
        <hr class="h-right-hr">
        <div class="third-box">
            <div class="packingCharge" style="margin-bottom: 20px;">打包费：¥ {{ packingCharge }}</div>
            <div class="deliveryCharge" style="margin-bottom: 20px;">配送费：¥ {{ deliveryCharge }}</div>
            <div class="pdtmoney" style="margin-bottom: 20px;">菜品小计：¥ {{ pdtmoney }}</div>
            <div class="orderdate" style="margin-bottom: 20px;">下单时间：{{ orderdate }}</div>
            <div class="predictdate" style="margin-bottom: 20px;">预计送达：{{ predictdate }}</div>
            <div class="realdate" style="margin-bottom: 20px;">实际送达：{{ realdate }}</div>
            <div class="payway" style="margin-bottom: 20px;">支付方式：
                <span v-if="payway === '1'">微信支付</span>
                <span v-if="payway === '2'">支付宝支付</span>
            </div>
            <div class="totalmoney">合计：
                <span style="font-size: 100px;">¥{{ totalmoney }}</span>
            </div>
        </div>
        <hr class="bottom_line_hr">
        <div class="bottom-button">
            <el-button plain>
                <span style="font-size: 20px; color: red;">
                    删除订单
                </span>
            </el-button>
            <el-button plain>
                <span style="font-size: 20px; color: black;" @click="goToStore">
                    再来一单
                </span>
            </el-button>
        </div>
    </div>
</template>
<script>
export default {
  data() {
    return {
        orderID: '1332666565499548852',
        // state:  1.待收货  2.已完成  3.已取消
        mchname:'火锅小旋转（北京邮电大学学二四楼店）',
        info:[ 
        [
            'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            '六神清爽小火锅',
            '198',
            '2'
        ],
        [
            'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            '六神清爽小火锅',
            '198',
            '1'
        ],
        [
            'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            '六神清爽小火锅',
            '198',
            '1'
        ],
        [
            'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            '六神清爽小火锅',
            '198',
            '1'
        ],
        [
            'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            '六神清爽小火锅',
            '198',
            '1'
        ],
        [
            'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            '六神清爽小火锅',
            '198',
            '1'
        ],
        [
            'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            '六神清爽小火锅',
            '198',
            '1'
        ],
        ],
        address:'六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅小火锅六神清爽小火锅',
        note:'六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅小火锅六神清爽小火锅',
        packingCharge:'5',
        deliveryCharge:'5',
        orderdate:'2024-08-30 10:30:45',
        predictdate:'2024-08-30 10:30:45',
        realdate:'2024-08-30 10:30:45',
        // 1.微信支付  2.支付宝支付
        payway:'1',
        state:'2',
        pdtmoney:'198',
        totalmoney:'496'
        }
    },
    methods: {
        goToStore() {
            this.$router.push({ name: 'CltStore' }); // 通过路由的名称跳转
        },
    }
}
</script>
<style scoped>
    .mchname {
        font-size: 30px;
        margin-top: 40px;
        margin-left: 3%;
    }
    .orderstate {
        position: absolute;
        font-size: 60px;
        right: 40px;
        top: 80px;
    }
    .top_line_hr {
        width: 95%;
        margin-top: 10px;
        border-top: 3px solid #CECECE;
    }
    .bottom_line_hr {
        width: 95%;
        margin-top: 20px;
        border-top: 3px solid #CECECE;
    }
    .h-left-hr {
        position: absolute;
        height: 680px;
        width: 1px;
        top: 170px;
        left: 620px;
        border-left: 3px dashed #CECECE;
    }
    .h-right-hr {
        position: absolute;
        width: 1px;
        height: 680px;
        left: 1150px;
        top: 170px;
        border-left: 3px dashed #CECECE;
    }
    .scroll-box {
        overflow-x: hidden;
        overflow-y: auto;
        height: 680px;
        width: 600px;
    }
    .msgcmp {
        position: relative;
    }
    #pdtcard {
        height: 200px;
        margin-left: 20px;
    }
    #msgpicture {
        width: 160px;
        height: 160px;
        margin-top: 20px;
        margin-left: 20px;
    }
    #msgname {
        white-space: normal;
        font-size: 30px;
        width: 280px;
        top: -160px;
        left: 200px;
    }
    #msgprice {
        top: -90px;
        left: 200px;
    }
    #msgnum {
        font-size: 30px;
        left: 510px;
        top: -240px;
    }
    #msgsum {
        left: 380px;
        top: -200px;
    }
    .orderID {
        position: absolute;
        top: 200px;
        left: 650px;
        font-size: 30px;
    }
    .address-note {
        position: absolute;
        top: 260px;
        left: 650px;
        font-size: 30px;
        width: 460px;
        white-space: normal;
    }
    .note {
        margin-top: 20px;
    }
    .third-box {
        position: absolute;
        top: 200px;
        left: 1190px;
        font-size: 30px;
    }
    .totalmoney {
        margin-top: 130px;
        margin-left: 100px;
    }
    .bottom-button {
        margin-left: 1350px;
    }
</style>