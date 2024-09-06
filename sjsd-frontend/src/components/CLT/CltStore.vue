<!-- 用户进店界面 -->
<!-- "#/clt/store" -->
<template>
  <div class="container">
    <!-- 左边占位块和分类栏 -->
    <div style="width: 300px;"></div>
    <side-bar :listData="listData" @scrollTo="scrollToCategory"/>
    <!-- 右边部分 -->
    <div class="right-container">
      <!-- 商家详情板块 -->
      <div class="store-info">
        <img class="avatar" :src="avatarURL" alt="merchant_avatar" />
        <div class="store-info-text">
          <p class="title">{{merchant_name}}</p>
          <div class="tags">
            <p>{{type_name}}</p>
            <p>配送费:￥{{delivery_fee}}</p>
            <p>起送费:￥{{minimum_order_amount}}</p>
            <p>打包单价:￥{{packaging_fee_per_item}}</p>
          </div>
          <div class="tags">
            <img src="../../assets/icons/time-icon.svg" alt="time" /><p>{{ opening_hours }}</p>
            <img src="../../assets/icons/phone-icon.svg" alt="phone" /><p>{{ phone_number }}</p>
          </div>
          <div class="tags">
            <img src="../../assets/icons/location-icon.svg" alt="location" /><p>{{ merchant_address }}</p>
          </div>
          <div class="tags">
            <p>商家简介</p>
          </div>
          <div class="tags">
            <p class="description">
              {{ isExpanded ? merchant_description : shortDescription }}
              <el-button 
                type="text" 
                @click="toggleExpand" 
                class="expand-button">
                {{ isExpanded ? '收起' : '展开详情' }}
              </el-button>
            </p>
          </div>
        </div>
      </div>
      <!-- 分割线 -->
      <el-divider></el-divider>
      <!-- 商品展示板块 -->
      <div class="products">
        <!-- 分类块 -->
        <div v-for="category in listData" :key="category.id" :ref="'category-' + category.id" class="products-block">
          <p class="categoryTitle">{{ category.typeName }}</p>
          <!-- 单类商品列表 -->
          <div class="product-list">
            <!-- 商品卡片 -->
            <el-card v-for="(product, idx) in getProductsByCategory(category.id)" :key="idx" class="product-card" shadow="hover" @click.native="openProdectsDetail(product)">
              <img :src="product.image" alt="product" />
              <p>{{ product.name }}</p>
              <p>￥{{ product.price }}</p>
              <!-- 添加删除块 -->
              <div class="add-remove">
                <el-button type="primary" size="mini" @click.stop="removeFromCart(product)">-</el-button>
                <p style="font-size:28px; margin:0 20px 0 20px; padding:0px;">{{ getQuantity(product) }}</p>
                <el-button type="danger" size="mini" @click.stop="addToCart(product)">+</el-button>
              </div>
            </el-card>
          </div>
          <el-divider></el-divider>
        </div>
        <p style="font-size:12px; color:#888888; margin:0; padding:0; align-self:center">已经到底啦~</p>
      </div>

    </div>
    <!-- 购物车按钮 -->
    <el-button class="cart-button" @click="showCart = true">
      <el-badge :value="cartCount" class="item">
        <img src="../../assets/icons/cart-icon.svg" alt="cart" />
      </el-badge>
    </el-button>
    <!-- 购物车弹窗 -->
    <el-dialog :visible.sync="showCart" width="50%" :modal-append-to-body="true" custom-class="cart-dialog">
      <!-- 地址选择区域 -->
      <div class="address-section">
        <p>收货地址: {{addressList.find(address => address.id === current_addressID)?.address}}</p>
        <p>收货人：{{addressList.find(address => address.id === current_addressID)?.name}}</p>
        <p>联系电话：{{addressList.find(address => address.id === current_addressID)?.phone}}</p>
        <el-select v-model="current_addressID" placeholder="请选择地址" style="width:100%">
          <el-option
            v-for="address in addressList"
            :key="address.id"
            :label="`${address.address} - ${address.name} - ${address.phone}`"
            :value="address.id"
          />
        </el-select>
      </div>
      <!-- 购物车内容 -->
      <div class="cart-content">
        <!-- 商品条目 -->
        <div v-for="item in cartItems" :key="item.id" class="cart-item">
          <img :src="item.image" alt="Cart Item">
          <p>{{ item.name }} - {{ item.price }} x {{ item.quantity }}</p>
        </div>
        <!-- 费用部分 -->
        <p>打包费：¥{{ packaging_fee }}</p>
        <p>配送费：¥{{ delivery_fee }}</p>
        <p>总计: ¥{{ total_amount }}</p>
        <!-- 备注区域 -->
        <div class="remark-section">
          <el-input 
            type="textarea" 
            v-model="remark" 
            placeholder="添加订单备注"
            rows="3"
          ></el-input>
        </div>
        <!-- 购物车按钮 -->
        <div class="cart-buttons">
          <el-button @click="clearCart">清空购物车</el-button>
          <el-button @click="submitOrder" :disabled=disableSubmitButton>提交订单</el-button>
        </div>
      </div>
    </el-dialog>
    <!-- 商品详情弹窗 -->
    <el-dialog :visible.sync="showProdectsDetail" width="50%" :modal-append-to-body="true" custom-class="product-detail-dialog">
      <div class="product-detail-content">
        <img :src="selectedProduct.image" alt="Product Image" />
        <div class="product-detail-text">
          <p class="title">{{ selectedProduct.name }}</p>
          <p>￥{{ selectedProduct.price }}</p>
          <p>{{ selectedProduct.description }}</p>
        </div>
      </div>
    </el-dialog>
    <!-- 支付界面 -->
    <el-dialog :visible.sync="visible" width="600px">  <!-- 这是一个整个的大对话框 -->
    <div class="payment-popup">  <!-- 这是一个对话框的内容区域  对话框内只有这一个大的div -->
      <!-- 倒计时 -->
      <div class="countdown"> 
        支付剩余时间还有 {{ countdown }}  秒
      </div>
      
      <!-- 步骤条 -->
      <el-steps :active="activeStep" finish-status="success">
        <!-- activeStep 是一个 Vue.js 数据属性，表示当前步骤的索引（从 0 开始）。
              当 activeStep 的值变化时，步骤条会自动更新以反映当前的步骤。 -->
        <!-- success 是一个预定义的状态，表示步骤完成时显示成功的样式（通常是绿色的对勾）。 -->
        <el-step title="选择支付方式"></el-step>
        <el-step title="扫码支付"></el-step>
        <el-step title="支付完成"></el-step>
      </el-steps>
      
      <!-- 步骤一：选择支付方式 -->
      <div v-if="activeStep === 0">
        <el-select v-model="selectedPayment" placeholder="请选择支付方式">
          <!-- 把用户选择的支付方式和selectedPayment绑定在一起 -->
          <el-option label="支付宝" value="alipay"></el-option>
          <el-option label="微信" value="wechat"></el-option>
        </el-select>
        <el-button type="primary" @click="nextStep">下一步</el-button>
        <!-- 设置按钮为“主要按钮”的样式，用一个强调色表示（比如蓝色） -->
      </div>
      
      <!-- 步骤二：展示二维码 -->
      <div v-if="activeStep === 1">
        <div v-if="selectedPayment === 'wechat'">
          <img src="../../assets/images/payment/weixin.jpg" alt="微信支付二维码" style="width: 200px; height: 200px;"/>
        </div>
        <div v-else-if="selectedPayment === 'alipay'">
          <img src="../../assets/images/payment/alipay.jpg" alt="支付宝支付二维码" style="width: 200px; height: 200px;"/>
        </div>
        <el-button type="primary" @click="simulatePaymentSuccess">完成支付</el-button>
      </div>
      
      <!-- 步骤三：支付成功 -->
      <div v-if="activeStep === 2">
        <el-result
          icon="success"
          title="支付成功"
          sub-title="商家开始备餐。。。">
        </el-result>
        <!--  el-result 是一个组件，用于展示简单的成功或失败的提示信息。
         这里使用了 icon 属性来设置图标，title 属性设置标题，sub-title 属性设置副标题。 -->
      </div>
    </div>
  </el-dialog>
  </div>
</template>
  
<script>
  import { mapState, mapMutations, mapGetters } from 'vuex';
  import SideBar from '../SideBar.vue';
  import api from '../../api/api.js';
  export default {
    components: { SideBar },
    data() {
      return {
        merchantID: 1,
        avatarURL:require('../../assets/images/test/testPicture.png'),
        merchant_name: "爆款饮品店",
        type_name:"快餐便当",
        delivery_fee: 6,
        packaging_fee_per_item: 1,
        minimum_order_amount: 20,
        opening_hours: "10:00-22:00",
        phone_number: "12345678910",
        merchant_address: "北京市海淀区北京邮电大学12345号",
        merchant_description: "此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介此处为商家简介",
        remark: "",
        isExpanded: false,
        showCart: false,
        selectedProduct:{},
        showProdectsDetail: false,
        showPayment:false,
        visible: false,
        countdown: 480, // 倒计时，秒
        activeStep: 0,
        selectedPayment: '',
        listData:[
          
        ],
        categoryList: [],
        products:[],
        
        current_addressID:0,
        
        addressList:[
          
        ],
      };
    },
    computed: {
      shortDescription() {
      const limit = 399; // 设置简短简介的字符限制
      return this.merchant_description.length > limit
        ? this.merchant_description.substring(0, limit) + '...'
        : this.merchant_description;
      },
      ...mapState(['cart', 'merchant_id']),
      ...mapGetters(['cartItems', 'cartTotal', 'cartCount']),
      getQuantity() {
        return (item) => {
          const found = this.cart.find(product => product.id === item.id);
          return found ? found.quantity : 0;
        };
      },
      getProductsByCategory() {
        return (category_id) => {
          return this.products.filter(product => product.category_id === category_id);
        };
      },
      packaging_fee(){
        return Number(this.packaging_fee_per_item) * this.cartCount;
      },
      total_amount(){
        // return this.cartTotal + this.delivery_fee + this.packaging_fee_per_item * this.cartCount;
        return Number(this.cartTotal) + Number(this.delivery_fee) + this.packaging_fee;
      },
      disableSubmitButton()
      {
        return this.cartTotal < this.minimum_order_amount || this.current_addressID==null;
      }
    },
    async created() {
      console.log('Component created');
      // 在实例创建完成后调用，此时，数据观测已完成，属性和方法也已被初始化，但 DOM 还未生成或挂载。
      // 可以在这里进行数据的初始化，如 API 请求
      this.merchantID = this.$route.query.id;
      console.log("商家ID：",this.merchantID);
      if(this.merchantID!=this.merchant_id){
        this.clearCart();
      }
      this.setMerchantID(this.merchantID);
      try {
        // 使用 await 等待异步请求完成
        const response = await api.getMerchantDetails(this.merchantID);
        const detail=response.data.data;
        console.log("商家详情：",detail);
        // 将商家详情逐个赋值
        this.avatarURL = detail.avatarURL; // 假设图片在本地，并且 avatarURL 只是文件名
        this.merchant_name = detail.merchantName;
        this.type_name = detail.typeName;
        this.delivery_fee = detail.deliveryFee;
        this.packaging_fee_per_item = detail.packagingFeePerItem;
        this.minimum_order_amount = detail.minimumOrderAmount;
        this.opening_hours = detail.openingHours;
        this.phone_number = detail.phoneNumber;
        this.merchant_address = detail.merchantAddress;
        this.merchant_description = detail.merchantDescription;

        } catch (error) {
          console.error('获取商家类型失败', error);
        }
      try {
        // 使用 await 等待异步请求完成
        const response = await api.getProductClt(this.merchantID);
        this.products = response.data.data;
        console.log(this.products);
      } catch (error) {
        console.error('获取商品列表失败', error);
      }
      try {
        // 使用 await 等待异步请求完成
        const response = await api.getProTypeClt(this.merchantID);
        this.categoryList = response.data.data;
        console.log(this.categoryList);
        // 复制到ListData中
        this.listData = this.categoryList.map(category => {
          return {
            id: category.category_id,
            typeName: category.category_name,
            cb: () => {
              this.$emit('scrollTo', category.category_id);  // 回调函数，调用滚动事件
              console.log(this)
              console.log('scrollToemit', category.category_id);
            }
          };
        });
      } catch (error) {
        console.error('获取商品类别失败', error);
      }
      try {
        // 使用 await 等待异步请求完成
        const response = await api.getCltAddress();
        this.addressList = response.data.data.allAddress; // 将返回的结果赋值给 temp
        console.log(this.addressList);
        } catch (error) {
        console.error('获取商家类型失败', error);
        }
    },
    mounted() {
      console.log('Component mounted');
      // 在挂载完成后调用。这时组件已经被渲染到页面上，DOM 元素可被访问。
      // 可以在这里进行 DOM 相关操作
      this.$on('scrollTo', this.scrollToCategory);
    },
    methods: {
      toggleExpand() {
      this.isExpanded = !this.isExpanded;
      },
      openProdectsDetail(product){
        console.log("card被点击")
        this.selectedProduct=product;
        this.showProdectsDetail=true;
      },
      submitOrder(){
        // 提交订单按钮
        this.showCart=false;
        this.visible = true;
        startCountdown();
      },
      ...mapMutations(['setMerchantID','addToCart', 'removeFromCart', 'clearCart']),
      scrollToCategory(category_id){
        console.log('scrollToCategory', category_id);
        const element = this.$refs['category-' + category_id][0]; // 注意：如果是v-for生成的ref是数组
        element.scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
      },
      nextStep() {
        if (this.selectedPayment) {  //表面用户已经选择了用什么支付，再点击下一步，就进到activeStep = 1
          this.activeStep = 1;
        } else {
          this.$message.error('请选择支付方式');  
          // 表面用户还没有选择支付方式，$message 方法显示一个错误消息，提示用户“请选择支付方式”。
        }
      },
      simulatePaymentSuccess() {
        this.activeStep = 2;
        // 完成支付的逻辑
        // 构造订单数据
        const order = {
          merchantID: this.merchantID,
          addressId: this.current_addressID,
          paymentMethod: this.selectedPayment === 'alipay' ? '支付宝' : '微信',
          packagingFee: this.packaging_fee,
          deliveryFee: this.delivery_fee,
          orderStatus: '1', // 假设'1'表示订单已提交
          notes: this.remark || '',
          totalAmount: this.total_amount,
        };

        // 构造订单商品项数据
        const itemList = this.cartItems.map(item => ({
          product_id: item.id,
          quantity: item.quantity,
        }));

        const OrderAndOrderItemList = {
          order: order,
          itemList: itemList,
        };

        // 发送订单请求
        api.submitOrder(OrderAndOrderItemList)
          .then(response => {
            if (response.data.code === 1) {
              this.$message.success('支付成功，订单已提交！');
              this.activeStep = 2; // 进入支付完成状态
              this.clearCart(); // 清空购物车
              setTimeout(() => {
                this.visible = false; // 3秒后关闭支付弹窗
              }, 3000);
            } else {
              this.$message.error('支付失败，请重试');
              this.activeStep = 2;
            }
          })
          .catch(() => {
            this.$message.error('提交订单时发生错误，请重试');
          });
      },
      startCountdown() {
        const timer = setInterval(() => {
          if (this.countdown > 0) {
            this.countdown--;
          } else {
            clearInterval(timer);
            this.visible = false;
          }
        }, 1000);
      },
    }
  };
</script>
  
<style spoced>
  .container {
    display: flex;
    justify-content: flex-start;
    height: 650px;
  }
  .right-container {
    display: flex;
    flex-direction: column;
    flex-grow: 1;
    max-width: 1050px;
    justify-content: flex-start;
    padding:50px 50px 0 0;
  }
  .store-info {
    display:flex;
    flex-direction:row;
    justify-content:flex-start;
    padding: 0;
    font-size: 10px;
    color:#929292;
  }
  .store-info-text {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    margin-left: 20px;
  }
  .avatar {
    width: 200px;
    height: 200px;
    object-fit: fill;
  }
  .title {
    font-size: 28px;
    font-weight: bold;
    color:black;
    margin: 0px;
  }
  .tags{
    display:flex;
    flex-direction:row;
    justify-content:flex-start;
    align-items:center;
    margin: 0px;
    padding: 5px 0 0 0;
  }
  .tags p{
    margin: 0 15px 0 0;
  }
  .tags img{
    width: 14px;
    height: 14px;
    margin: 0 5px 0 0;
  }
  .expand-button {
  color: #409eff;
  cursor: pointer;
  font-size: 10px;
  padding: 0;
  }
  .description{
    width:800px;
    line-height: 1.5;
    letter-spacing: 1px;
  }
  .products{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    padding: 0;
  }
  .products-block{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
  }
  .categoryTitle{
    font-size: 28px;
    font-weight: bold;
    color: black;
    margin: 0 0 20px 0;
  }
  .product-list{
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    padding: 0;
    column-gap: 20px;
    row-gap: 30px;
  }
  .product-card{
    width: 240px;
    height:360px;
    margin: 0;
    padding: 0;
  }
  .el-card__body{
    padding: 0;
    padding-top: 20px;
    display:flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
  }
  .product-card img{
    width: 200px;
    height:200px;
  }
  .product-card p{
    padding:0;
    padding-top:5px;
    margin: 0;
    font-size: 17px;
    color:#000000;
  }
  .add-remove{
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    width:200px;
    padding: 20px;
  }
  .cart-button,
  .cart-button:hover,
  .cart-button:active,
  .cart-button:focus{
  position: fixed;
  bottom: 50px;
  right: 50px;
  background-color: transparent;
  border: none;
  box-shadow: none;
  color: inherit;
  }
  .product-detail-dialog,
  .cart-dialog {
  backdrop-filter: blur(50px);
  }
  .address-section {
    margin-bottom: 0;
  }
  .address-section p {
    margin-top: 0;
  }

  .remark-section {
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .cart-buttons,
  .cart-item{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
  }
  .cart-item img{
    width: 50px;
    height: 50px;
  }
  .product-detail-content{
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;
  }
  .product-detail-content img{
    width:300px;
    height:300px;
  }
  .payment-popup {
  text-align: center;
  }
  .countdown {
    font-size: 20px;
    margin-bottom: 20px;
  }
</style>

<!--
导航跳转
搜索框
网络请求数据
-->