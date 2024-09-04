package buptworker.service.impl;

import buptworker.entity.SalesData;
import buptworker.mapper.MchDataMapper;
import buptworker.service.MchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 21:02]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 21:02]
 * @updateRemark : [说明本次修改内容]
 */

@Component
public class MchDataService_ implements MchDataService {
    @Autowired
    public MchDataMapper mchDataMapper;
    @Override
    public List<SalesData> listSalesData(int merchantId) {
        return mchDataMapper.listSalesData(merchantId);
    }
}
