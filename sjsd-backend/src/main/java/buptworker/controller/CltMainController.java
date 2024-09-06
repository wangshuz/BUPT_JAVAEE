package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.CltMainService;
import buptworker.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/3 20:15]
 * @updateUser : [86183]
 * @updateTime : [2024/9/3 20:15]
 * @updateRemark : [说明本次修改内容]
 */

@RestController
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RequestMapping("/api")
public class CltMainController {
    private SessionUtil cookie;
    @Autowired
    private CltMainService cltMainService;

    @RequestMapping("/merchant-intros")
    public Result MchIntro(){
        return Result.success(cltMainService.listMchIntro());
    }

    @RequestMapping("/merchant-type")
    public Result MchType(){
        return Result.success(cltMainService.listMchType());
    }

    @RequestMapping("/cltMain/topPicture")
    public Result topPicture(HttpServletRequest request){
        int cltId = cookie.getUserID(request).intValue();
        return Result.success(cltMainService.listTopPicture(cltId));
    }



}
