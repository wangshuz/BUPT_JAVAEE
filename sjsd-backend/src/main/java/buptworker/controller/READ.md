1. 导入需要的库
```java
import jakarta.servlet.http.HttpServletRequest;
import buptworker.util.SessionUtil;
```
2. 所有controller前添加以下允许跨域的行
```java
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
```
3. 在你的controller中实例化一个SessionUtil
```java
private SessionUtil cookie;
```
4. 在所有请求的响应函数中添加参数```HttpServletRequest request```
示例：
```java
@GetMapping("/merchants")
public ResponseEntity<Merchant> getMerchant(HttpServletRequest request) {
    // 实现代码
    // ...
}
```
5. 在响应函数的实现中，通过```cookie.getUserID(request).intValue()```获得int类型的当前用户id
完整示例：
```java
// 定义一个固定的 merchant_id
private int defaultMerchantID = 5;  // 修改为cookie！！！
private SessionUtil cookie;

// 获取商家信息 API
@GetMapping("/merchants")
public ResponseEntity<Merchant> getMerchant(HttpServletRequest request) {
    defaultMerchantID = cookie.getUserID(request).intValue();
    // 使用默认的 merchantID 获取商家信息
    Merchant merchant = mchdetailService.getMerchantById(defaultMerchantID);
    if (merchant != null) {
        return ResponseEntity.ok(merchant);
    } else {
        return ResponseEntity.notFound().build();
    }
}
```