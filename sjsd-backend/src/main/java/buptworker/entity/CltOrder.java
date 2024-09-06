package buptworker.entity;

import java.util.List;

public class CltOrder {
//    {
//        mchname:'火锅小旋转（北京邮电大学学二四楼店）',
//                info:[
//        {
//            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
//                    name: '六神清爽小火锅',
//                price: '198',
//                num: '2'
//        },
//        {
//            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
//                    name: '六神清爽小火锅',
//                price: '198',
//                num: '2'
//        },
//          ],
//        date:'2024-08-30 10:30:45',
//                state:'1',
//            money:'496'
//    }
    //private CltOrderItem cltOrderItem;
    private String orderId;//order_id
    private String mchId;//merchant_id
    private String mchname;//merchant_name
    private String date;//order_date
    private String state;//order_status
    private String money;//total_amount

    private List<CltOrderInfo> info;

    public CltOrder(CltOrderItem cltOrderItem, List<CltOrderInfo> cltOrderInfoList) {
        this.orderId = cltOrderItem.getOrderId();
        this.mchId = cltOrderItem.getMchId();
        this.mchname = cltOrderItem.getMchname();
        this.date = cltOrderItem.getDate();
        this.state = cltOrderItem.getState();
        this.money = cltOrderItem.getMoney();
        this.info = cltOrderInfoList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchname() {
        return mchname;
    }

    public void setMchname(String mchname) {
        this.mchname = mchname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<CltOrderInfo> getCltOrderInfoList() {
        return info;
    }

    public void setCltOrderInfoList(List<CltOrderInfo> cltOrderInfoList) {
        this.info = cltOrderInfoList;
    }
}
