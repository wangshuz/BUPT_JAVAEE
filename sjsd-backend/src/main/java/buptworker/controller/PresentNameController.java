package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.PresentNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class PresentNameController {
    @Autowired
    private PresentNameService presentNameService;


    // 用来存储 sessionID 对应的用户或商家ID及其类型
    @RequestMapping("/api/PresentName")
    public Result PresentName(Integer id,Integer flag){
        if (flag==1)
        {
            return Result.success(presentNameService.ListMchPresentName(id));
        }
        else
        {
            return Result.success(presentNameService.ListCltPresentName(id));
        }

    }

}
