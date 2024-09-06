package buptworker.controller;

import buptworker.entity.LoginRequest;
import buptworker.entity.Result;
import buptworker.service.AuthService;
import buptworker.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import buptworker.entity.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class LoginController {

    @Autowired
    private AuthService authService;

    // 用户登录接口
    @PostMapping("/clt/login")
    public Result cltLogin(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        User user = authService.findByCltname(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            // 使用 SessionUtil 创建 session
            SessionUtil.createSession(response, user.getUserId(), "user");
            return Result.success();
        } else {
            return Result.error("用户名或密码错误");
        }
    }

    // 商家登录接口
    @PostMapping("/mch/login")
    public Result mchLogin(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        User user = authService.findByMchname(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            // 使用 SessionUtil 创建 session
            SessionUtil.createSession(response, user.getUserId(), "merchant");
            return Result.success();
        } else {
            return Result.error("用户名或密码错误");
        }
    }


    @RequestMapping("/clt/register")
    public Result registerClt(@RequestBody LoginRequest loginRequest){
        User clt = new User((long)0, loginRequest.getUsername(),loginRequest.getPassword());
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
    public Result registerMch(@RequestBody LoginRequest loginRequest){
        User mch = new User((long)0, loginRequest.getUsername(),loginRequest.getPassword());
        boolean is_OK = authService.registerMch(mch);
        Result ret = null;
        if(is_OK==true){
            ret = Result.success();
        }else{
            ret = Result.error("用户名已存在");
        }
        return ret;
    }

    // 登出接口
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 使用 SessionUtil 删除 session
        SessionUtil.deleteSession(request, response);
        return "Logout successful!";
    }

    // 获取当前用户ID或商家ID
    public SessionUtil.SessionData getSessionData(HttpServletRequest request) {
        return SessionUtil.getSessionData(request);
    }
}
