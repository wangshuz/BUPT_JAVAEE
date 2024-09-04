package buptworker.controller;

import buptworker.entity.MchOrder;
import buptworker.entity.MchOrderItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class MchOrderController {

    @GetMapping
    public List<MchOrder> getOrders() {
        List<MchOrder> orders = new ArrayList<>();

        // 创建第一个订单
        MchOrder order1 = new MchOrder();
        order1.setMchname("火锅小旋转（北京邮电大学学二四楼店）");
        order1.setDate("2024-08-30 10:30:45");
        order1.setState("1");
        order1.setMoney("496");

        MchOrderItem item1 = new MchOrderItem();
        item1.setPicture("https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0");
        item1.setName("六神清爽小火锅");
        item1.setPrice("198");
        item1.setNum("1");

        order1.setInfo(Arrays.asList(item1, item1)); // 添加多个相同商品

        // 继续创建更多订单...
        orders.add(order1);

        return orders;
    }


}
