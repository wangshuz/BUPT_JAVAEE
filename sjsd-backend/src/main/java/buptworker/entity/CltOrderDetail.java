package buptworker.entity;

import java.util.List;

public class CltOrderDetail {


//    // state:  1.待收货  2.已完成  3.已取消
//    mchname:'火锅小旋转（北京邮电大学学二四楼店）',
//    mchId:'',
//    info:[
//    {
//        picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
//                name: '六神清爽小火锅',
//            price: '198',
//            num: '2'
//    },
//    {
//        picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
//                name: '六神清爽小火锅',
//            price: '198',
//            num: '1'
//    },
//    {
//        picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
//                name: '六神清爽小火锅',
//            price: '198',
//            num: '1'
//    },
//    {
//        picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
//                name: '六神清爽小火锅',
//            price: '198',
//            num: '1'
//    },
//    {
//        picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
//                name: '六神清爽小火锅',
//            price: '198',
//            num: '1'
//    },
//    {
//        picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
//                name: '六神清爽小火锅',
//            price: '198',
//            num: '1'
//    },
//    {
//        picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
//                name: '六神清爽小火锅',
//            price: '198',
//            num: '1'
//    }
//        ],
//    address:'六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅小火锅六神清爽小火锅',
//    note:'六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅六神清爽小火锅小火锅六神清爽小火锅',
//    packingCharge:'5',
//    deliveryCharge:'5',
//    orderdate:'2024-08-30 10:30:45',
//    predictdate:'2024-08-30 10:30:45',
//    realdate:'2024-08-30 10:30:45',
//    // 1.微信支付  2.支付宝支付
//    payway:'1',
//    state:'2',
//    pdtmoney:'198',
//    totalmoney:'496'

    //private CltOrderDetailItem cltOrderDetailItem;

    private String mchname;//merchant_name
    private String mchId;//merchant_id
    private String address;//address_line
    private String note;//notes
    private String packingCharge;//packaging_fee
    private String deliveryCharge;//delivery_fee
    private String orderdate;//order_date
    private String predictdate;//estimated_delivery_time
    private String realdate;//actual_delivery_time
    private String payway;//payment_method
    private String state;//order_status
    private String pdtmoney;//total_amount - packaging_fee - delivery_fee
    private String totalmoney;//total_amount
    private List<CltOrderDDetailInfo> cltOrderDDetailInfoList;

    public CltOrderDetail(CltOrderDetailItem cltOrderDetailItem, List<CltOrderDDetailInfo> cltOrderDDetailInfoList) {
        this.mchname = cltOrderDetailItem.getMchname();
        this.mchId = cltOrderDetailItem.getMchId();
        this.address = cltOrderDetailItem.getAddress();
        this.note = cltOrderDetailItem.getNote();
        this.packingCharge = cltOrderDetailItem.getPackingCharge();
        this.deliveryCharge = cltOrderDetailItem.getDeliveryCharge();
        this.orderdate = cltOrderDetailItem.getOrderdate();
        this.predictdate = cltOrderDetailItem.getPredictdate();
        this.realdate = cltOrderDetailItem.getRealdate();
        this.payway = cltOrderDetailItem.getPayway();
        this.state = cltOrderDetailItem.getState();
        this.pdtmoney = cltOrderDetailItem.getPdtmoney();
        this.totalmoney = cltOrderDetailItem.getTotalmoney();
        this.cltOrderDDetailInfoList = cltOrderDDetailInfoList;
    }

    public String getMchname() {
        return mchname;
    }

    public void setMchname(String mchname) {
        this.mchname = mchname;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPackingCharge() {
        return packingCharge;
    }

    public void setPackingCharge(String packingCharge) {
        this.packingCharge = packingCharge;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getPredictdate() {
        return predictdate;
    }

    public void setPredictdate(String predictdate) {
        this.predictdate = predictdate;
    }

    public String getRealdate() {
        return realdate;
    }

    public void setRealdate(String realdate) {
        this.realdate = realdate;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPdtmoney() {
        return pdtmoney;
    }

    public void setPdtmoney(String pdtmoney) {
        this.pdtmoney = pdtmoney;
    }

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }

    public List<CltOrderDDetailInfo> getCltOrderDDetailInfoList() {
        return cltOrderDDetailInfoList;
    }

    public void setCltOrderDDetailInfoList(List<CltOrderDDetailInfo> cltOrderDDetailInfoList) {
        this.cltOrderDDetailInfoList = cltOrderDDetailInfoList;
    }
}
