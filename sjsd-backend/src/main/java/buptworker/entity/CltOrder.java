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
    private CltOrderItem cltOrderItem;
    private List<CltOrderInfo> cltOrderInfoList;


    public CltOrder(CltOrderItem cltOrderItem, List<CltOrderInfo> cltOrderInfoList) {
        this.cltOrderItem = cltOrderItem;
        this.cltOrderInfoList = cltOrderInfoList;
    }

    public CltOrderItem getCltOrderItem() {
        return cltOrderItem;
    }

    public void setCltOrderItem(CltOrderItem cltOrderItem) {
        this.cltOrderItem = cltOrderItem;
    }

    public List<CltOrderInfo> getCltOrderInfoList() {
        return cltOrderInfoList;
    }

    public void setCltOrderInfoList(List<CltOrderInfo> cltOrderInfoList) {
        this.cltOrderInfoList = cltOrderInfoList;
    }
}
