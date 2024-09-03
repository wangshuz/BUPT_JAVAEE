<!-- 用户进店界面 -->
<!-- "#/clt/store" -->
<template>
  <div class="container">
    <!-- 左边占位块和分类栏 -->
    <div style="width: 300px;"></div>
    <side-bar :listData="listData" />
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
      <div class="cart-content">
        <div v-for="item in cartItems" :key="item.id" class="cart-item">
          <img :src="item.image" alt="Cart Item">
          <p>{{ item.name }} - {{ item.price }} x {{ item.quantity }}</p>
        </div>
        <p>总计: ¥{{ cartTotal }}</p>
        <div class="cart-buttons">
          <el-button @click="clearCart">清空购物车</el-button>
          <el-button @click="submitOrder">提交订单</el-button>
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
  </div>
</template>
  
<script>
  import { mapState, mapMutations, mapGetters } from 'vuex';
  import SideBar from '../SideBar.vue';
  export default {
    components: { SideBar },
    data() {
      return {
        merchant_id: 1,
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
        isExpanded: false,
        showCart: false,
        selectedProduct:{},
        showProdectsDetail: false,
        listData:[
          {
              "id":10001,
              "typeName":"分类1",
              "cb":function(){
                  alert("分类1");
              },
          },
          {
              "id":10002,
              "typeName":"分类2",
              "cb":function(){},
          },
          {
              "id":10003,
              "typeName":"分类3",
              "cb":function(){},
          },
          {
              "id":10004,
              "typeName":"分类4",
              "cb":function(){},
          },
          {
              "id":10005,
              "typeName":"分类5",
              "cb":function(){},
          },
        ],
        products:[
          { id: 1, name: '霸王龙1', price: 99.99, image: require('../../assets/images/test/testPicture.png'),   category_id: 10001, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 2, name: '霸王龙2', price: 9.99, image: require('../../assets/images/test/testPicture.png'),    category_id: 10002, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 3, name: '霸王龙3', price: 999.9, image: require('../../assets/images/test/testPicture.png'),   category_id: 10003, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 4, name: '霸王龙4', price: 99.99, image: require('../../assets/images/test/testPicture.png'),   category_id: 10004, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 5, name: '霸王龙5', price: 99.99, image: require('../../assets/images/test/testPicture.png'),   category_id: 10005, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 6, name: '霸王龙6', price: 99.99, image: require('../../assets/images/test/testPicture.png'),   category_id: 10001, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 7, name: '霸王龙7', price: 99.99, image: require('../../assets/images/test/testPicture.png'),   category_id: 10002, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 8, name: '霸王龙8', price: 99.99, image: require('../../assets/images/test/testPicture.png'),   category_id: 10003, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 9, name: '霸王龙9', price: 99.99, image: require('../../assets/images/test/testPicture.png'),   category_id: 10004, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 10, name: '霸王龙10', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10005, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 11, name: '霸王龙11', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10001, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 12, name: '霸王龙12', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10002, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 13, name: '霸王龙13', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10003, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 14, name: '霸王龙14', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10004, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 15, name: '霸王龙15', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10005, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 16, name: '霸王龙16', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10001, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 17, name: '霸王龙17', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10002, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 18, name: '霸王龙18', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10003, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 19, name: '霸王龙19', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10004, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 20, name: '霸王龙20', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10005, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 21, name: '霸王龙21', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10001, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 22, name: '霸王龙22', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10002, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 23, name: '霸王龙23', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10003, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 24, name: '霸王龙24', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10004, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 25, name: '霸王龙25', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10005, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 26, name: '霸王龙26', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10001, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 27, name: '霸王龙27', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10002, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 28, name: '霸王龙28', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10003, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 29, name: '霸王龙29', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10004, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
          { id: 30, name: '霸王龙30', price: 99.99, image: require('../../assets/images/test/testPicture.png'), category_id: 10005, description: '此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介此处为商品简介' },
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
      }
    },
    created() {
      console.log('Component created');
      // 在实例创建完成后调用，此时，数据观测已完成，属性和方法也已被初始化，但 DOM 还未生成或挂载。
      // 可以在这里进行数据的初始化，如 API 请求
      if(this.$route.params.id!=this.merchant_id){
        this.clearCart();
      }
      this.setMerchantID(this.$route.params.id);
    },
    mounted() {
      console.log('Component mounted');
      // 在挂载完成后调用。这时组件已经被渲染到页面上，DOM 元素可被访问。
      // 可以在这里进行 DOM 相关操作
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
        // 提交订单的逻辑
      },
      ...mapMutations(['setMerchantID','addToCart', 'removeFromCart', 'clearCart']),
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
</style>

<!--
购物车total计算
地址
备注
导航跳转
搜索框
网络请求数据
-->