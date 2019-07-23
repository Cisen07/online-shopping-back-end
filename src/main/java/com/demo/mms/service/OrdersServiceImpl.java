package com.demo.mms.service;

import com.demo.mms.common.domain.GoodList;
import com.demo.mms.common.domain.Orders;
import com.demo.mms.dao.GoodListMapper;
import com.demo.mms.dao.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private GoodListMapper goodListMapper;
//    private EvaluateMapper evaluateMapper;

    @Override
    public void addGoodList(GoodList goodList) {
        goodListMapper.insert(goodList);
    }

    @Override
    public void addOrder(int orderID, String userId, Date ordertime, Date sendtime, Date receivetime,
                         String state, String name, String address, String tel, String orderTotal) {
//        int number = GoodListMapper.getNumOfGoodList(orderid);
//        GoodList tempGoodList;
//        double total = 0;
//        for (int i = 0; i < number; i++) {
//            tempGoodList = GoodListMapper.getGoodItem(i, orderid);
//            int goodid = tempGoodList.getGoodid();
//            double price = 0;
//            int goodnum = tempGoodList.getGoodnum();
//            total = total + goodnum*price;
//        }
//        BigDecimal totalprice =BigDecimal.valueOf(total);
        double orderTotalDouble = 0;
        try {
            orderTotalDouble = Double.valueOf(orderTotal);  //先把orderTotal从String转为Double
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        BigDecimal totalPrice = BigDecimal.valueOf(orderTotalDouble);   //再从Double转为BigDecimal
        Orders order = new Orders();
        order.setOrderid(orderID);
        order.setTotalprice(totalPrice);
        order.setUserid(Integer.valueOf(userId));
        order.setOrdertime(ordertime);
        order.setSendtime(sendtime);
        order.setReceivetime(receivetime);
        order.setState(state);
        order.setName(name);
        order.setAddress(address);
        order.setPhonenum(tel);
        ordersMapper.insertSelective(order);
    }

    @Override
    public int getOrderNumByUserId(String userId) {
        return ordersMapper.getNumOfOrdersByUserId(Integer.valueOf(userId));
    }

    @Override
    public Orders getOrder(int i, String userId) {
        return ordersMapper.selectByUserId(i, Integer.valueOf(userId));
    }

    @Override
    public String cancelOrder(String orderId) {
        Orders tempOrder = ordersMapper.selectByPrimaryKey(Integer.valueOf(orderId));
        if (tempOrder == null) {
            return "订单不存在";
        } else {
            ordersMapper.deleteByPrimaryKey(Integer.valueOf(orderId));
            goodListMapper.deleteByOrderId(Integer.valueOf(orderId));
            return "订单删除成功";
        }
    }

    @Override
    public Orders getOrderById(String orderId) {
        return ordersMapper.selectByPrimaryKey(Integer.valueOf(orderId));
    }

    @Override
    public void update(Orders orders) {
        ordersMapper.updateByPrimaryKey(orders);
    }
}
