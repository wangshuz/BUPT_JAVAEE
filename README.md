# BUPT_JAVAEE
北邮软工大三上暑期小学期javaee项目————舌尖速递外卖平台（sjsd）
待办：
前端网络请求
router守卫
后端接口
服务器部署

订单数据约束（订单ID、订单状态、支付方式）

订单ID最好长一些

订单状态为      1.待接单  2.待派送  3.派送中  4.已完成  5.已取消

支付方式为      1.微信支付  2.支付宝支付


## api DEMO
### api.js
```javascript
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
  baseURL:'http://localhost:8081', // 根据你的后端服务地址调整
  timeout: 10000, // 请求超时时间
})
/**
 * 导出一个包含 API 请求方法的对象
 * 
 * 这里只包含一个示例方法，用于获取商品列表。
 */
export default {


  /**
   * 获取商家类型选项
   * 
   * 获取商家类型的选项列表。
   * 
   * 
   * @returns {Promise} 包含商家类型选项列表的 Promise 对象
   */
  async getMerchantTypes() {
    // 发送 GET 请求到 /api/merchant-types 端点
    return await apiClient.get(`/merchant-types`);
  },



};
```
### MchMain.vue
```vue
<template class="template">
    <div class="container">
        {{ temp.data }}

    </div>
</template>

<script>

import api from '../../api/api.js';

export default {

    name: "TopPicture",
    props: { },
    data(){
        return {
            temp: null,
            
        };
    
    },
    async created() {
        try {
        // 使用 await 等待异步请求完成
        const response = await api.getMerchantTypes();
        this.temp = response; // 将返回的结果赋值给 temp
        } catch (error) {
        console.error('获取商家类型失败', error);
        }
    },
    
}
</script>


<style scoped>
    


</style>
```

### 后端MchMainController.java
```java
package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.MchMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/3 20:15]
 * @updateUser : [86183]
 * @updateTime : [2024/9/3 20:15]
 * @updateRemark : [说明本次修改内容]
 */

@RestController
@CrossOrigin(origins = "http://localhost:8080")  // 允许 http://localhost:8080 的跨域请求
public class MchMainController {
    @Autowired
    private MchMainService mchMainService;

    @RequestMapping("/merchant-types")
    public Result MchType(){
        return Result.success(mchMainService.listMchType());
    }

}
```

