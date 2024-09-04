package buptworker.controller;

import buptworker.pojo.MchIntro;
import buptworker.pojo.Result;
import buptworker.service.CltMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
public class CltMainController {
    @Autowired
    private CltMainService cltMainService;

    @RequestMapping("/merchant-intros")
    public Result MchIntro(){
        return Result.success(cltMainService.listMchIntro());
    }

    @RequestMapping("/merchant-types")
    public Result MchType(){
        return Result.success(cltMainService.listMchType());
    }

}
