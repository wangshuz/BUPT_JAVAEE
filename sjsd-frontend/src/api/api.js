// 引入 axios 库，axios 是一个基于 Promise 的 HTTP 库，用于发送 HTTP 请求
import axios from 'axios';

/**
 * 创建一个 axios 实例
 * 
 * 这个实例可以用来发送 HTTP 请求，并且可以在这里配置基础的 URL 和其他默认设置。
 */
// const apiClient = axios.create({
//   // 设置基础 URL，所有请求都会基于这个 URL
//   baseURL: 'https://api.example.com',
//   // 设置请求的超时时间（10秒）
//   timeout: 10000,
//   // 设置默认的请求头，表示发送的内容是 JSON 格式
//   headers: {
//     'Content-Type': 'application/json'
//   }
// });
const apiClient = axios.create({
  // baseURL:'http://localhost:8080/', // 根据你的后端服务地址调整
  // timeout: 10000, // 请求超时时间
  // headers: {
  //   'Content-Type': 'multipart/form-data'
  // }
})
/**
 * 导出一个包含 API 请求方法的对象
 * 
 * 这里只包含一个示例方法，用于获取商品列表。
 */
export default {
  /**
   * 获取商品列表
   * 
   * 这是一个示例方法，用于从服务器获取商品列表。
   * 它发送一个 GET 请求到 `/items` 这个端点，并返回一个 Promise 对象。
   * 
   * @returns {Promise} 包含商品列表的 Promise 对象
   */
//   getItems() {
//     // 发送 GET 请求到 /items 端点
//     return apiClient.get('/items');
//   }
  /**
   * 获取商家信息
   * 
   * 获取特定商家的所有详细信息。
   * 
   * @param {number} merchantId - 商家的唯一标识符
   * @returns {Promise} 包含商家信息的 Promise 对象
   */
  getMerchantDetails(merchantId) {
    // 发送 GET 请求到 /api/merchants/{merchant_id} 端点
    return apiClient.get(`/api/merchants/${merchantId}`);
  },

  /**
   * 更新商家信息
   * 
   * 更新特定商家的详细信息。
   * 
   * @param {number} merchantId - 商家的唯一标识符
   * @param {Object} merchantData - 包含商家信息的对象
   * @returns {Promise} 包含操作结果的 Promise 对象
   */
  updateMerchantDetails(merchantId, merchantData) {
    // 发送 PUT 请求到 /api/merchants/{merchant_id} 端点
    return apiClient.put(`/api/merchants/${merchantId}`, merchantData);
  },

  /**
   * 上传商家头像或图片
   * 
   * 上传商家的头像或店铺图片。
   * 
   * @param {number} merchantId - 商家的唯一标识符
   * @param {FormData} formData - 包含文件数据的 FormData 对象
   * @returns {Promise} 包含操作结果的 Promise 对象
   */
  uploadMerchantImage(merchantId, formData) {
    // 发送 POST 请求到 /api/merchants/{merchant_id}/upload-avatar 或 /upload-image 端点
    return apiClient.post(`/api/merchants/${merchantId}/upload-avatar`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  },




  /**
   * 获取商家简介信息
   * 
   * @returns {Promise} 包含商家简介信息的 List
   */
  getMerchantIntro(){
    return apiClient.get(`/api/merchant-intros`)
  },

  /**
   * 获取商家类型选项
   * 
   * 获取商家类型的选项列表。
   * 
   * 
   * @returns {Promise} 包含商家类型选项列表的 Promise 对象
   */
  getMerchantTypes() {
    // 发送 GET 请求到 /api/merchant-types 端点
    return apiClient.get(`/api/merchant-types`);
  },

  /**
   * 获取商家详细信息
   * 
   * @param {number} merchantId - 商家的唯一标识符
   * @returns {Promise} 包含商家详情
   */
  getMerchantDetails(merchantId){
    return apiClient.get(`/api/merchantInfo?merchantId=${merchantId}`);
  },

  /**
   * 获取特定商家的商品列表
   * 
   * @param {number} merchantId - 商家的唯一标识符
   * @returns {Promise} 包含商家的所有商品
   */
  getProductClt(merchantId){
    return apiClient.get(`/api/productClt?merchantId=${merchantId}`);
  },

  /**
   * 获取特定商家的商品分类
   * 
   * @param {number} merchantId
   * @returns {Promise} 包含所有分类列表
   */
  getProType(merchantId){
    return apiClient.get(`/api/proType?merchantId=${merchantId}`)
  },


  /**
   * 获取用户地址
   * 
   * @param {number} cltId - 用户唯一标识符
   * @returns {Promise} 包含默认地址和全部地址列表
   */
  getCltAddress(cltId){
    return apiClient.get(`/api/cltAddress?cltId=${cltId}`);
  },


  getOrders(mchId){
      return apiClient.get(`/api/getOrders?mchId=${mchId}`);
  },

  updateOrderStatus(orderId, status){
      return apiClient.get(`/api/updateOrderStatus?orderId=${orderId}&status=${status}`);
  },
  
  deleteOrder(orderId){
      return apiClient.get(`/api/deleteOrder?orderId=${orderId}`);
  },

  getCltOrders(userId){
      return apiClient.get(`/api/getCltOrders?userId=${userId}`);
  },

  updateCltOrderStatus(orderId, status){
      return apiClient.get(`/api/updateCltOrderStatus?orderId=${orderId}&status=${status}`);
  },

  deleteCltOrder(orderId){
    return apiClient.get(`/api/deleteCltOrder?orderId=${orderId}`);
  },

  /**
   * 获取商家统计数据
   * 
   * @param {number} merchantId
   * @return {Promise}
   */
  getMchData(merchantId){
    return apiClient.get(`/api/salesData?merchantId=${merchantId}`);
  }

  /* 
  function().then(item=>{
      item.data.data 为所需数据
    })
  */

    
  // 商家登录
    loginMerchant(username, password) {
      return apiClient.post('/api/merchants/login', { username, password });
    },

    // 商家注册
    registerMerchant(username, password, type) {
      return apiClient.post('/api/merchants/register', { username, password});
    },

    // 用户登录
    loginCustomer(username, password) {
      return apiClient.post('/api/customers/login', { username, password });
    },

    // 用户注册
    registerCustomer(username, password) {
      return apiClient.post('/api/customers/register', { username, password });
    },





};


/**
 * 使用示例：
 * 
 * import api from './path/to/api';
 * 
 * api.getItems()
 *   .then(response => {
 *     // 在这里处理成功响应，response.data 包含服务器返回的数据
 *     console.log('商品列表:', response.data);
 *   })
 *   .catch(error => {
 *     // 在这里处理错误响应
 *     console.error('获取商品列表时出错:', error);
 *   });
 */
