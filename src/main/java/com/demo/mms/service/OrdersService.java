package com.demo.mms.service;

import com.demo.mms.common.domain.GoodList;
import com.demo.mms.common.domain.Orders;

import java.util.Date;

public interface OrdersService {
    void addGoodList(GoodList goodList);

    void addOrder(int orderID, String userId, Date ordertime, Date sendtime, Date receivetime,
                  String state, String name, String address, String tel, String orderTotal);

    int getOrderNumByUserId(String userId);

    Orders getOrder(int i, String userId);

    String cancelOrder(String orderId);
}
