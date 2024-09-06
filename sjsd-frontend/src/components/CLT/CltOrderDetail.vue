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
            <div id="pdtcard" v-for="(item, index) in info" :key="index" style="background-color: #CECECE; border-radius: 20px; border: 1px solid #000;">
                <!-- 图片部分 -->
                <div>
                    <img id="msgpicture" class="msgcmp" :src="item.picture" alt="商品图片">
                </div>
                <!-- 订单信息部分 -->
                <div id="msgname" class="msgcmp">{{ item.name }}</div>
                <div id="msgprice" class="msgcmp">
                    <span style="font-size: 20px;">单价：</span>
                    <span style="font-size: 30px;">{{ item.price }}</span>
                </div>
                <div id="msgnum" class="msgcmp">x{{ item.num }}</div>
                <div id="msgsum" class="msgcmp">小计：
                    <span style="font-size: 60px;">{{ item.price * item.num }}</span>
                </div>
            </div>
        </div>
        <hr class="h-left-hr">
        <div class="orderID">订单号：{{ orderId }}</div>
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
                <span style="font-size: 60px;">¥{{ totalmoney }}</span>
            </div>
        </div>
        <hr class="bottom_line_hr">
        <div class="bottom-button">
            <span v-if="state === '1'">
                <el-button plain>
                <span style="font-size: 20px; color: red;" @click="handlecancle()">
                    取消订单
                </span>
            </el-button>
            </span>
            <span v-if="state === '1'">
                <el-button plain>
                <span style="font-size: 20px; color: black;" @click="handleaccept()">
                    确认收货
                </span>
            </el-button>
            </span>
            <span v-if="state === '2' || state === '3'">
                <el-button plain v-if="state === '2' || state === '3'">
                <span style="font-size: 20px; color: red;" @click="handledelete()">
                    删除订单
                </span>
            </el-button>
            </span>
            <span v-if="state === '2' || state === '3'">
                <el-button plain v-if="state === '2' || state === '3'">
                <span style="font-size: 20px; color: black;" @click="goToStore();">
                    再来一单
                </span>
            </el-button>
            </span>
            
        </div>
    </div>
</template>
<script>
import api from '../../api/api.js';
export default {
  data() {
    return {
        userId:'1',
        orderId: '1332666565499548852',
        // state:  1.待收货  2.已完成  3.已取消
        mchname:'火锅小旋转（北京邮电大学学二四楼店）',
        mchId:'',
        info:[ 
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '1'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '1'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '1'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '1'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '1'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '1'
        }
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
        state:'1',
        pdtmoney:'198',
        totalmoney:'496'
        }
    },
    methods: {
        goToStore() {
            this.$router.push({ 
                name: 'CltStore',
                query: { mchId : this.mchId }  // 传递数据
             }); // 通过路由的名称跳转
        },
        goToOrder()
        {
            this.$router.push({ 
                name: 'CltOrder'
             }); // 通过路由的名称跳转
        },
        async handledelete(){
            await this.deleteCltOrderDetail(this.orderId);
            this.goToOrder();

        },
        async handleaccept()
        {
            await this.changeCltOrderRealtime(this.orderId);
            await this.changeCltOrderDetailStatus(this.orderId, '4');
        },
        async handlecancle()
        {
            await this.changeCltOrderDetailStatus(this.orderId, '5');
        },
        handleonemoretime()
        {
            this.goToStore();
        },
        async fetchOrderDetail() {
          //getCltOrderDetail(orderId){
          //    return apiClient.get(`/api/getCltOrderDetail?orderId=${orderId}`);
          //},
          try{
            const response = (await api.getCltOrderDetail(this.orderId)).data.data;
            this.mchname = response.mchname;
            this.mchId = response.mchId;
            
            this.address = response.address;
            this.note = response.note;
            this.packingCharge = response.packingCharge;
            this.deliveryCharge = response.deliveryCharge;
            this.orderdate = response.orderdate;
            this.predictdate = response.predictdate;
            this.realdate = response.realdate;
            this.payway = response.payway;
            this.state = response.state;
            this.pdtmoney = response.pdtmoney;
            this.totalmoney = response.totalmoney;
            this.info = response.cltOrderDDetailInfoList;
          }
          catch(error)
          {
            console.error('获取订单列表时出错:', error);
          }
        //   api.getCltOrderDetail(this.orderId)
        //   .then(response => {
        //   this.tableData = response.data.data;
        //   })
        //   .catch(error => {
        //   console.error('获取订单列表时出错:', error);
        //   });
      },
      async changeCltOrderDetailStatus(orderId, status) {
          //updateCltOrderDetailStatus(orderId, status){
          //    return apiClient.get(`/api/updateCltOrderDetailStatus?orderId=${orderId}&status=${status}`);
          //},
          try{
            const response = await api.updateCltOrderDetailStatus(orderId, status);
            console.log(response);
            await this.fetchOrderDetail();
          }
          catch(error)
          {
            console.error('更新订单状态时出错:', error);
          }
        //   api.updateCltOrderDetailStatus(orderId, status)
        //   .then(response => {
        //   console.log(response.data);
        //   this.fetchOrderDetail(); // 更新状态后重新获取订单列表
        //   })
        //   .catch(error => {
        //   console.error('更新订单状态时出错:', error);
        //   });
      },
      async deleteCltOrderDetail(orderId) {
          //deleteCltOrderDetail(orderId){
          //    return apiClient.get(`/api/deleteCltOrderDetail?orderId=${orderId}`);
          //},
          try{
            const response = await api.deleteCltOrderDetail(orderId);
            console.log(response.data);
            await this.fetchOrderDetail(); // 删除订单后重新获取订单列表
          }
          catch(error)
          {
            console.error('删除订单时出错:', error);
          }
        //   api.deleteCltOrderDetail(orderId)
        //   .then(response => {
        //   console.log(response.data);
        //   this.fetchOrderDetail(); // 删除订单后重新获取订单列表
        //   })
        //   .catch(error => {
        //   console.error('删除订单时出错:', error);
        //   });
      },
      async changeCltOrderRealtime(orderId){
        //changeCltOrderRealtime(orderId, time){
          //    return apiClient.get(`/api/changeCltOrderRealtime?orderId=${orderId}&time=${time}`);
          //},
          try{
            console.log(this.getCurrentTime());
            const response = await api.changeCltOrderRealtime(orderId, this.getCurrentTime());
            console.log(response);
            await this.fetchCltOrders(); // 更新状态后重新获取订单列表
          }
          catch(error)
          {
            console.error('更新送达时间时出错:', error);
          }
        //   const now = new Date();
        //   api.changeCltOrderRealtime(orderId, now.toString())
        //   .then(response => {
        //   console.log(response.data);
        //   this.fetchCltOrders(); // 更新状态后重新获取订单列表
        //   })
        //   .catch(error => {
        //   console.error('更新送达时间时出错:', error);
        //   });
      },
      getCurrentTime() {
        const now = new Date();
        
        const year = now.getFullYear();
        const month = String(now.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要+1
        const day = String(now.getDate()).padStart(2, '0');
        
        const hours = String(now.getHours()).padStart(2, '0');
        const minutes = String(now.getMinutes()).padStart(2, '0');
        const seconds = String(now.getSeconds()).padStart(2, '0');
        
        return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
        }
    },
    async mounted()
    {
        this.orderId = this.$route.query.orderId;
        await this.fetchOrderDetail();
        // 每隔5秒钟刷新数据
      console.log('数据获取完成，启动定时器');
                this.interval = setInterval(async () => {
                    console.log('定时器触发'); // 确认定时器是否被触发
                this.currentTime = new Date().toString();
                console.log(this.currentTime);
                await this.fetchOrderDetail();
                }, 3000); // 5000 毫秒 = 5秒
    },
    beforeDestroy() {
            // 销毁组件前清除定时器，防止内存泄漏
            clearInterval(this.interval);
        },
    // async created()
    // {
        
    //     await fetchOrderDetail();
    // }
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
    }
    .bottom-button {
        margin-left: 1350px;
    }
</style>