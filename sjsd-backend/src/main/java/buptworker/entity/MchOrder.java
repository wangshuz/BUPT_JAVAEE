package buptworker.entity;

import java.util.List;

public class MchOrder {
    public MchOrder(MchOrderDetail mchOrderDetail, List<MchOrderItem> info) {
        this.mchOrderDetail = mchOrderDetail;
        this.info = info;
    }

    //    {
//        orderID:'1332666559995544599',
//                username:'不爱吃海底捞',
//            phonenumber:'13655263459',
//            location:'北京邮电大学学十三公寓美团外卖柜',
//            ordertime:'2024-08-30 10:30:45',
//            perdicttime:'2024-08-30 11:00:45',
//            realtime:'2024-08-30 10:55:45',
//            note:'不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜',
//            info:[
//        {
//            name: '六神清爽小火锅',
//                    price: '198',
//                num: '2'
//        },
//
//        {
//            name: '六神清爽小火锅',
//                    price: '198',
//                num: '1'
//        }
//                        ],
//        packingCharge:'5',
//                deliveryCharge:'5',
//            totalmchpricenote:'9999',
//            // payway: 1.微信支付  2.支付宝支付
//            payway:'1',
//            money:'154',
//            orderstate:'1'
//    }
    private MchOrderDetail mchOrderDetail;
    private List<MchOrderItem> info;


    public List<MchOrderItem> getInfo() {
        return info;
    }

    public void setInfo(List<MchOrderItem> info) {
        this.info = info;
    }

    public MchOrderDetail getMchOrderDetail() {
        return mchOrderDetail;
    }

    public void setMchOrderDetail(MchOrderDetail mchOrderDetail) {
        this.mchOrderDetail = mchOrderDetail;
    }




}
