package buptworker.controller;

import buptworker.entity.LoginRequest;
import buptworker.entity.Result;
import buptworker.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import buptworker.entity.User;
import buptworker.entity.Merchant;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author : Rui
 * @version : [v1.0]
 * @description : [cookie部分]
 * @createTime : [2024/9/5 4:29]
 * @updateRemark : [说明本次修改内容]
 * Rui的留言：
 * User和 Merchant除了 controller其他类都还没写,等白天再实现，我把 cookie的部分写一下
 * 另外,我咋觉得一个 controller就够了?controller要分吗
 *
 * 关于 cookie：
 * 登录时,后端会生成sessionID保存cookie中，同时服务器也存储了 sessionID和对应的用户 ID和用户类型
 * 生成的 cookie将包含在 respond中,浏览器会自动保存，并在该根网址下所有http请求中自动发送该 cookie
 * 后端处理每个请求时，都先调用 getSessionDataFromRequest来获取 ID和 type
 * 前端不需要再做修改,登录后所有请求后端都知道你的用户 id和身份类型, get数据时不需要主动发送 id
 *
 * 关于 post请求处理：
 * 对于大多数需要处理 JSON 格式请求体的 POST 请求，通常我们会创建一个 Java 类来表示该请求的数据结构。
 * 在 Spring Boot 中，使用 @RequestBody 注解时，Spring 会将 JSON 请求体自动转换为 Java 对象，
 * 因此需要有对应的类来接收和处理这些数据。不过，并不是所有的 POST 请求都必须定义一个类。
 * 如果你的请求体是简单的结构（如单个字段），也可以使用简单的类型如 String、Integer、Map 等，而不必定义一个专门的类。
// */

@CrossOrigin(origins = "http://localhost:8080")  // 允许 http://localhost:8080 的跨域请求
//部署到服务器上后要修改允许跨域的源，不改成全部因为允许全部后不能使用cookie。
@RestController
public class LoginController {
    @Autowired
    private AuthService authService;

    // 用来存储 sessionID 对应的用户或商家ID及其类型
    private static final Map<String, SessionData> sessionMap = new HashMap<>();

    // 用户登录接口
    @PostMapping("/clt/login")
    public Result cltLogin(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        User user = authService.findByCltname(loginRequest.getUsername(), loginRequest.getPassword());
        Result ret = null;

        if (user != null) {
            // 生成 sessionId
            String sessionId = UUID.randomUUID().toString();
            sessionMap.put(sessionId, new SessionData(user.getUserId(), "user"));

            // 设置 Cookie
            Cookie cookie = new Cookie("sessionId", sessionId);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            ret = Result.success();
        } else {
            ret = Result.error("用户名或密码错误");
        }
        return ret;
    }

    @PostMapping("/mch/login")
    public Result mchLogin(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        User user = authService.findByMchname(loginRequest.getUsername(), loginRequest.getPassword());
        Result ret = null;

        if (user != null) {
            // 生成 sessionId
            String sessionId = UUID.randomUUID().toString();
            sessionMap.put(sessionId, new SessionData(user.getUserId(), "user"));

            // 设置 Cookie
            Cookie cookie = new Cookie("sessionId", sessionId);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            ret = Result.success();
        } else {
            ret = Result.error("用户名或密码错误");
        }
        return ret;
    }

    // 登出接口
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    // 删除 sessionMap 中的 sessionId
                    sessionMap.remove(cookie.getValue());

                    // 清除 Cookie
                    Cookie sessionCookie = new Cookie("sessionId", null);
                    sessionCookie.setMaxAge(0);
                    sessionCookie.setPath("/");
                    response.addCookie(sessionCookie);
                    return "Logout successful!";
                }
            }
        }
        return "No active session found";
    }

    @RequestMapping("/clt/register")
    public Result registerClt(@RequestBody User clt){
        boolean is_OK = authService.registerClt(clt);
        Result ret = null;
        if(is_OK==true){
            ret = Result.success();
        }else{
            ret = Result.error("用户名已存在");
        }
        return ret;
    }

    @RequestMapping("/mch/register")
    public Result registerMch(@RequestBody User mch){
        boolean is_OK = authService.registerMch(mch);
        Result ret = null;
        if(is_OK==true){
            ret = Result.success();
        }else{
            ret = Result.error("用户名已存在");
        }
        return ret;
    }


    // 获取当前用户ID或商家ID的方法
    public SessionData getSessionDataFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();
                    return sessionMap.get(sessionId);
                }
            }
        }
        return null;
    }

    // 内部类保存 sessionId 对应的用户ID或商家ID及其类型
    private static class SessionData {
        private Long id;
        private String type; // user or merchant

        public SessionData(Long id, String type) {
            this.id = id;
            this.type = type;
        }

        public Long getId() {
            return id;
        }

        public String getType() {
            return type;
        }
    }
}
