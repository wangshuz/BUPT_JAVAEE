package buptworker.service.impl;

import buptworker.mapper.CltMainMapper;
import buptworker.entity.MchIntro;
import buptworker.entity.MchType;
import buptworker.service.CltMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/3 21:00]
 * @updateUser : [86183]
 * @updateTime : [2024/9/3 21:00]
 * @updateRemark : [说明本次修改内容]
 */

@Component
public class CltMainService_ implements CltMainService {

    @Autowired
    private CltMainMapper cltMainMapper;

    @Override
    public List<MchIntro> listMchIntro() {
        return cltMainMapper.listMchIntro();
    }

    @Override
    public List<MchType> listMchType() {
        return cltMainMapper.listMchType();
    }

}
