// 引入 axios 库，axios 是一个基于 Promise 的 HTTP 库，用于发送 HTTP 请求
import axios from 'axios';

/**
 * 创建一个 axios 实例
 * 这个实例可以用来发送 HTTP 请求，并且可以在这里配置基础的 URL 和其他默认设置。
 */
const apiClient = axios.create({
  baseURL:'http://localhost:8081', // 根据你的后端服务地址调整
  timeout: 10000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json',
  }
})



export default {
  // OrderAndOrderItemList数据结构
  // const OrderAndOrderItemList = {
  //   order: order,
  //   itemsList: []
  // };
  // 其中order数据结构:
  // const order = {
  //    merchant_id INT,
  //    address_id INT,
  //    payment_method VARCHAR(50) = '支付宝',
  //    packaging_fee DECIMAL(10, 2) NOT NULL,
  //    delivery_fee DECIMAL(10, 2) NOT NULL,
  //    order_status VARCHAR(50) = '1',
  //    notes TEXT,
  //    total_amount DECIMAL(10, 2) NOT NULL,
  // }
  // 其中orderItems数据结构:
  //  const orderItems = {
  //    product_id INT,
  //    quantity INT NOT NULL,
  //  }
  
  
  // 提交订单
  // post
  // return.data.code: 1:提交成功 0:失败
  async submitOrder(OrderAndOrderItemList) {
    return apiClient.post('/api/order/submit', OrderAndOrderItemList)
  },

};