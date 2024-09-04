package buptworker.controller;

import buptworker.pojo.MchIntro;
import buptworker.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
public class CltMainController {
    @RequestMapping("/api/merchant-intros")
    public Result MchIntro(){
        Integer id = 10001;
        String[] name = {"1","2","3","4"};
        Integer[] label = {1,2,3,4};
        String url = "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png";
        List<MchIntro> ret = new ArrayList<MchIntro>();
        for(int i = 0;i < 50;i ++){
            int j = i%4;
            MchIntro mchIntro = new MchIntro(id+i,name[j],"",label[j],url);
            ret.add(mchIntro);
        }
        return Result.success(ret);
    }

    @RequestMapping("/api/merchant-types")
    public Result MchType(){
        String[] types = {};
        return Result.success();
    }

}
