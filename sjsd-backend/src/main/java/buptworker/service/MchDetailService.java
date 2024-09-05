package buptworker.service;

import buptworker.entity.Merchant;
import buptworker.mapper.MchDetailMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MchDetailService {

    @Autowired
    private MchDetailMapper mchdetailMapper;

    public Merchant getMerchantById(int merchantID) {
        return mchdetailMapper.getMerchantById(merchantID);
    }

    public boolean updateMerchant(Merchant merchant) {
        return mchdetailMapper.updateMerchant(merchant) > 0;
    }

    public boolean updateMerchantAvatar(int merchantID, String avatarURL) {
        return mchdetailMapper.updateMerchantAvatar(merchantID, avatarURL) > 0;
    }

    public List<String> getMerchantTypes() {
        // 假设商家类型是固定的，可以写死或从配置文件中获取
        //return List.of("快餐便当", "汉堡披萨", "奶茶咖啡");
        return mchdetailMapper.getTypeName();
    }
}
