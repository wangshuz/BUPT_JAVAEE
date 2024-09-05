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

  // 新增分类
  // get
  // return.data.code: 1:新增成功 0:失败
  async addCategory(CategoryName) {
      return apiClient.get(`/category/add?CategoryName=${CategoryName}`)
  },
  // 删除指定商品
  // get
  // return.data.code: 1:删除成功 0:失败
  async deleteProduct(ProductId) {
    return apiClient.get(`/product/delete?ProductId=${ProductId}`)
  },
  // 添加商品
  // post
  // return.data.code: 1:添加成功 0:失败
  async addProduct(Product) {
    return apiClient.post('/product/add', Product )
  },
  // 修改商品信息
  // post
  // 通过软删除再新增的方式实现,注意在新增的时候不要把id拷贝过去
  // return.data.code: 1:修改成功 0:失败
  async updateProduct(Product) {
    return apiClient.post('/product/update', Product )
  },
  
  // 修改商品在售状态
  // get
  // 真修改
  // return.data.code: 1:修改成功 0:失败
  async updateProductStatus(ProductId, status) {
    return apiClient.get(`/product/updateStatus?ProductId=${ProductId}&status=${status}`)
  },
  
};