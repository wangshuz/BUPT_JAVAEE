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
  },
  withCredentials: true,              // 允许发送 Cookie
})



export default {

  // 新增分类
  // get
  // return.data.code: 1:新增成功 0:失败
  async addCategory(categoryName) {
      return apiClient.get(`/api/category/add?categoryName=${categoryName}`)
  },
  // 删除指定商品
  // get
  // return.data.code: 1:删除成功 0:失败
  async deleteProduct(productId) {
    return apiClient.get(`/api/product/delete?productId=${productId}`)
  },


  // product数据结构
  // public class Product {
  //   private Integer id; null
  //   private String name;
  //   private String image;
  //   private Integer category_id; 
  //   private String category_name; null
  //   private Integer price;
  //   private String description;
  //   private Boolean status; null
  //   }
  // 添加商品
  // post
  // return.data.code: 1:添加成功 0:失败
  async addProduct(product) {
    return apiClient.post('/api/product/add', product )
  },
  // 修改商品信息
  // post
  // 通过软删除再新增的方式实现,注意在新增的时候不要把id拷贝过去
  // return.data.code: 1:修改成功 0:失败
  async updapteProduct(product) {
    return apiClient.post('/api/product/update', product )
  },

  // product数据结构
  // public class Product {
  //   private Integer id;
  //   private String name;
  //   private String image;
  //   private Integer category_id;
  //   private String category_name; null
  //   private Integer price;
  //   private String description;
  //   private Boolean status; null
  //   }
  
  // 修改商品在售状态
  // get
  // 真修改
  // return.data.code: 1:修改成功 0:失败
  async updateProductStatus(productId, status) {
    return apiClient.get(`/api/product/updateStatus?ProductId=${productId}&status=${status}`)
  },
  
};