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

    private CltOrderDetailItem cltOrderDetailItem;
    private List<CltOrderDDetailInfo> cltOrderDDetailInfoList;

    public CltOrderDetail(CltOrderDetailItem cltOrderDetailItem, List<CltOrderDDetailInfo> cltOrderDDetailInfoList) {
        this.cltOrderDetailItem = cltOrderDetailItem;
        this.cltOrderDDetailInfoList = cltOrderDDetailInfoList;
    }

    public CltOrderDetailItem getCltOrderDetailItem() {
        return cltOrderDetailItem;
    }

    public void setCltOrderDetailItem(CltOrderDetailItem cltOrderDetailItem) {
        this.cltOrderDetailItem = cltOrderDetailItem;
    }

    public List<CltOrderDDetailInfo> getCltOrderDDetailInfoList() {
        return cltOrderDDetailInfoList;
    }

    public void setCltOrderDDetailInfoList(List<CltOrderDDetailInfo> cltOrderDDetailInfoList) {
        this.cltOrderDDetailInfoList = cltOrderDDetailInfoList;
    }
}
