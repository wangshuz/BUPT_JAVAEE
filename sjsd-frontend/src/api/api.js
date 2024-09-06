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
  baseURL: 'http://localhost:8081/', // 根据你的后端服务地址调整
  timeout: 10000, // 请求超时时间
})
/**
 * 导出一个包含 API 请求方法的对象
 * 
 * 这里只包含一个示例方法，用于获取商品列表。
 */
export default {
  /**
    * 获取商家信息
    * 
    * 获取特定商家的所有详细信息。
    * 
    * @param {number} merchantId - 商家的唯一标识符
    * @returns {Promise} 包含商家信息的 Promise 对象
    */
  getMerchantDetailChange() {
    // 发送 GET 请求到 /api/merchants端点
    return apiClient.get(`/api/merchants`);
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
  updateMerchantDetails(merchantData) {
    // 发送 PUT 请求到 /api/merchants/{merchant_id} 端点
    return apiClient.put(`/api/merchants`, merchantData);
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
    return apiClient.post(`/api/merchants/upload-avatar`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  },

  /**
   * 获取商家类型选项
   * 
   * 获取商家类型的选项列表。
   * 
   * 
   * @returns {Promise} 包含商家类型选项列表的 Promise 对象
   */
  getMerchantTypeChange() {
    // 发送 GET 请求到 /api/merchant-types 端点
    return apiClient.get(`/api/merchant-types`);
  },

     /**
   * 获取商家类型 (用户首页)
   * 
   * 获取商家类型的选项列表。
   * 
   * @returns {Promise} 包含商家类型选项列表的 Promise 对象
   */
     getMerchantTypes() {
      return apiClient.get(`/api/merchant-type`);
    },

  /**
   * 获取商家简介信息
   * 
   * @returns {Promise} 包含商家简介信息的 List
   */
  async getMerchantIntros() {
    return apiClient.get(`/api/merchant-intros`)
  },

  /**
   * 获取商家详细信息
   * 
   * @param {number} merchantId - 商家的唯一标识符
   * @returns {Promise} 包含商家详情
   */
  async getMerchantDetails(merchantId) {
    return apiClient.get(`/api/merchantInfo?merchantId=${merchantId}`);
  },

  /**
   * 获取特定商家的商品信息（商家端）
   * 
   * @returns {Promise} Promise 
   */
  async getProductMch() {
    return apiClient.get(`/api/productMch`)
  },


  /**
   * 获取特定商家的商品列表(用户端)
   * 
   * @param {number} merchantId - 商家的唯一标识符z
   * @returns {Promise} 包含商家的所有商品
   */
  async getProductClt() {
    return apiClient.get(`/api/productClt?merchantId=${merchantId}`);
  },

  /**
   * 获取特定商家的商品分类
   * 
   * @returns {Promise} 包含所有分类列表
   */
  async getProType() {
    return apiClient.get(`/api/proType`)
  },
  
 /**
   * 获取顶部推荐图片
   * 
   * @returns {Promise}
   */
 async getTopPicture(){
  return apiClient.get(`/api/cltMain/topPicture`)
},

  /**
   * 获取用户地址
   * 
   * @param {number} cltId - 用户唯一标识符
   * @returns {Promise} 包含默认地址和全部地址列表
   */
  async getCltAddress(cltId) {
    return apiClient.get(`/api/cltAddress?cltId=${cltId}`);
  },


  async getOrders() {
    return apiClient.get(`/api/getOrders?`);
  },

  async updateOrderStatus(orderId, status) {
    return apiClient.get(`/api/updateOrderStatus?orderId=${orderId}&status=${status}`);
  },

  async deleteOrder(orderId) {
    return apiClient.get(`/api/deleteOrder?orderId=${orderId}`);
  },

  async getCltOrders() {
    return apiClient.get(`/api/getCltOrders`);
  },

  async updateCltOrderStatus(orderId, status) {
    return apiClient.get(`/api/updateCltOrderStatus?orderId=${orderId}&status=${status}`);
  },

  async deleteCltOrder(orderId) {
    return apiClient.get(`/api/deleteCltOrder?orderId=${orderId}`);
  },

  /**
   * 获取商家统计数据
   * 
   * @param {number} merchantId
   * @return {Promise}
   */
  async getMchData(merchantId) {
    return apiClient.get(`/api/salesData?merchantId=${merchantId}`);
  },

  async getCltOrderDetail(orderId) {
    return apiClient.get(`/api/getCltOrderDetail?orderId=${orderId}`);
  },

  async updateCltOrderDetailStatus(orderId, status) {
    return apiClient.get(`/api/updateCltOrderDetailStatus?orderId=${orderId}&status=${status}`);
  },

  async deleteCltOrderDetail(orderId) {
    return apiClient.get(`/api/deleteCltOrderDetail?orderId=${orderId}`);
  },

  async changeCltOrderRealtime(orderId, time) {
    return apiClient.get(`/api/changeCltOrderRealtime?orderId=${orderId}&time=${time}`);
  },

  /**
   * 获取商家数据（首页）
   *
   * @returns {Promise} Promise
   */
  async getCurData() {
    return apiClient.get(`/api/curData`)
  },

  /**
   * 获取商家订单数据（首页）
   * 
   * 
   * @returns {Promise} Promise
   */
  async getMonthlyOrderStats() {
    return apiClient.get(`/api/monthlyOrderStats`)
  },

  /**
   * 获取商家菜品数据（首页）
   * 
   * @returns {Promise} Promise
   */
  async getProdStats() {
    return apiClient.get(`/api/prodStats`)
  },

  /**
   * 获取商家信息（首页）
   * 
   * @returns {PromiseM} Promise
   */
  async getMerchantInfo() {
    return apiClient.get(`/api/merchantInfo`)
  },

  async changeCltOrderRealtime(orderId, time) {
    return apiClient.get(`/api/changeCltOrderRealtime?orderId=${orderId}&time=${time}`);
  },

  /**
   * 更改密码
   *
   * @param {String} password - 新密码
   * @param {Number} id - 用户或者商家的id
   * @param {Number} flag - 商家是1，用户是0
   * @returns {Promise} - 返回的结果
   */
  async updatePassword(password, id, flag) {
      // console.log(password, id, flag);
      return apiClient.get(`/api/updatePassword?password=${password}&id=${id}&flag=${flag}`);
  },
  /*
  *
  * 展示用户名
  * @param {Number} id - 用户或者商家的id
  * @param {Number} flag - 商家是1，用户是0
  */
  // getPresentName(id, flag) {
  //   return apiClient.get('/api/PresentName', { params: { id, flag } });
  // },
  
  async getPresentName(id, flag) {
    return apiClient.get(`/api/PresentName?id=${id}&flag=${flag}`);
  },


  uploadImage(merchantId, formData) {
    // 发送 POST 请求到 /api/merchants/{merchant_id}/upload-avatar 或 /upload-image 端点
    return apiClient.post(`/api/merchants/upload-avatar`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
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
