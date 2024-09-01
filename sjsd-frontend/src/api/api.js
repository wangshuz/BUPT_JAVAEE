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
