package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/password")
public class PasswordController {
    @Autowired
    private PasswordService passwordService;

    @PostMapping("/update")
    public Result updatePassword(String password, Integer id,Integer flag) { // 调用服务层的方法更新密码
        if(flag==1){
            passwordService.MchPassword(password,id);
        }else {
            passwordService.CltPassword(password, id);
        }
        return Result.success("Password updated successfully");
    }
}
