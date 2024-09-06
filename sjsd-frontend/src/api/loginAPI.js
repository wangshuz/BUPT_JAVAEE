// 引入 axios 库，axios 是一个基于 Promise 的 HTTP 库，用于发送 HTTP 请求
import axios from 'axios';

/**
 * 创建一个 axios 实例
 * 这个实例可以用来发送 HTTP 请求，并且可以在这里配置基础的 URL 和其他默认设置。
 */
const apiClient = axios.create({
  baseURL:'http://localhost:8081', // 根据你的后端服务地址调整
  timeout: 10000, // 请求超时时间
  withCredentials: true,              // 允许发送 Cookie
})


  // async getMerchantTypes() {
  //   // 发送 GET 请求到 /api/merchant-types 端点
  //   return await apiClient.get(`/merchant-types`);
  // },


export default {
  // 商家登录
  // 返回code: 1:登录成功 0:密码错误或用户不存在
  async loginMerchant(username, password) {
    return await apiClient.post('/api/mch/login', { username, password });
  },

  // 商家注册
  // 返回code: 1:注册成功 0:用户名已存在
  async registerMerchant(username, password) {
    return await apiClient.post('/api/mch/register', { username, password});
  },

  // 用户登录
  // 返回code: 1:登录成功 0:密码错误或用户不存在
  async loginCustomer(username, password) {
    return await apiClient.post('/api/clt/login', { username, password });
  },

  // 用户注册
  // 返回code: 1:注册成功 0:用户名已存在
  async registerCustomer(username, password) {
    return await apiClient.post('/api/clt/register', { username, password });
  },
  
};