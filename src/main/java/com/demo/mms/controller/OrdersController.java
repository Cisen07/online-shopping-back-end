package com.demo.mms.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.mms.common.domain.Good;
import com.demo.mms.common.domain.GoodList;
import com.demo.mms.common.domain.Orders;
import com.demo.mms.common.utils.IDGenerator;
import com.demo.mms.service.AddressService;
import com.demo.mms.service.GoodListService;
import com.demo.mms.service.GoodsService;
import com.demo.mms.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    AddressService addressService;
    @Autowired
    GoodListService goodListService;
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/addOrder")
    @ResponseBody
    public Object addOrder(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        String tel = jsonObject.getString("tel");
        String orderTotal = jsonObject.getString("orderTotal");
        JSONArray goodsList = jsonObject.getJSONArray("goodsList");
        int length = goodsList.size();
        int orderID = IDGenerator.getIdInt9();  //生成orderID
        for (int i = 0; i < length; i++) {
            JSONObject tempJ = goodsList.getJSONObject(i);
            GoodList goodList = new GoodList();
            goodList.setGoodid(Integer.valueOf(tempJ.getString("goodId")));
            goodList.setGoodnum(Integer.valueOf(tempJ.getString("goodNum")));
            goodList.setOrderid(orderID);
            ordersService.addGoodList(goodList);
        }
        JSONObject jsonObjectR = new JSONObject();
        boolean whetherAddNewAddress = false;
        if (addressService.findAddress(userId, name, address, tel) == null) {
            addressService.addAddress(userId, name, address, tel);
            whetherAddNewAddress = true;
        }
        Date ordertime = new Date();
        Date sendtime = null;
        Date receivetime = null;
        String state = "待发货";
        ordersService.addOrder(orderID, userId, ordertime, sendtime, receivetime,
                state, name, address, tel, orderTotal);
        try {
            jsonObjectR.put("success", true);
            if (whetherAddNewAddress == true) {
                jsonObjectR.put("message", "成功生成订单, 且自动创建新地址");
            } else {
                jsonObjectR.put("message", "成功生成订单");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjectR;
    }

    @RequestMapping("/orderListNotUsed")
    @ResponseBody
    public Object getOrder(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        ArrayList<JSONObject> resultArrayList = new ArrayList<>();
        JSONObject jsonObjectR = new JSONObject();
        int numOfOrders = ordersService.getOrderNumByUserId(userId);
        if (numOfOrders == 0) {
            jsonObjectR.put("success", false);
            jsonObjectR.put("message", "该用户没有订单");
        }
        for (int i = 0; i < numOfOrders; i++) {
            Orders order = ordersService.getOrder(i, userId);
            JSONObject tempJObject = new JSONObject();
            tempJObject.put("orderId", order.getOrderid());
            tempJObject.put("orderTotal", order.getTotalprice());
            tempJObject.put("orderStatus", order.getState());
            tempJObject.put("createDate", order.getOrdertime());
            tempJObject.put("goodsList", goodListService.getGoodsListByOrderId(order.getOrderid()));
            resultArrayList.add(tempJObject);
        }
        try {
            jsonObjectR.put("success", true);
            jsonObjectR.put("message", "查询成功");
            jsonObjectR.put("result", resultArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjectR;
    }

    @RequestMapping("/orderList")
    @ResponseBody
    public Object getOrder2(@RequestBody JSONObject jsonObject) {    //详细信息版
        String userId = jsonObject.getString("userId");
        ArrayList<JSONObject> resultArrayList = new ArrayList<>();
        JSONObject jsonObjectR = new JSONObject();
        int numOfOrders = ordersService.getOrderNumByUserId(userId);
        if (numOfOrders == 0) {
            jsonObjectR.put("success", false);
            jsonObjectR.put("message", "该用户没有订单");
        }
        for (int i = 0; i < numOfOrders; i++) {
            Orders order = ordersService.getOrder(i, userId);
            JSONObject tempJObject = new JSONObject();
            tempJObject.put("orderId", order.getOrderid());
            tempJObject.put("orderTotal", order.getTotalprice());
            tempJObject.put("orderStatus", order.getState());
            tempJObject.put("createDate", order.getOrdertime());
            int num = goodListService.getNumByOrderId(order.getOrderid());
            JSONArray jsonArray = new JSONArray();
            System.out.println("the num is " + num);
            for (int n = 0; n < num; n++) {
                JSONObject tempJ = new JSONObject();
                GoodList tempGoodList = goodListService.getTheNthItem(order.getOrderid(), n);
                tempJ.put("goodNum", tempGoodList.getGoodnum().toString());
                tempJ.put("goodId", tempGoodList.getGoodid());
                Good tempGood = goodsService.getGoodByID(tempGoodList.getGoodid().toString());
                tempJ.put("subTitle", tempGood.getDescription());
                tempJ.put("price", tempGood.getPrice());
                tempJ.put("picUrl", tempGood.getPicture());
                tempJ.put("kindId", tempGood.getKindid());
                jsonArray.add(tempJ);
            }
//            tempJObject.put("goodsList", goodListService.getGoodsListByOrderId(order.getOrderid()));
            tempJObject.put("goodsList", jsonArray);
            resultArrayList.add(tempJObject);
        }
        try {
            jsonObjectR.put("success", true);
            jsonObjectR.put("message", "查询成功");
            jsonObjectR.put("result", resultArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjectR;
    }

    @RequestMapping("/cancelOrder")
    @ResponseBody
    public Object Order(@RequestBody JSONObject jsonObject) {
        String orderId = jsonObject.getString("orderId");
        String msg = ordersService.cancelOrder(orderId);
        JSONObject jsonObjectR = new JSONObject();
        if (msg.equals("订单不存在")) {
            jsonObjectR.put("success", false);
        } else {
            jsonObjectR.put("success", true);
        }
        jsonObjectR.put("message", msg);
        return jsonObjectR;
    }

    @RequestMapping("/receiveGood") //弃用
    @ResponseBody
    public Object receive(@RequestBody JSONObject jsonObject){
        String orderId = jsonObject.getString("orderId");
        Orders orders = ordersService.getOrderById(orderId);
        orders.setState("待评价");
        ordersService.update(orders);
        JSONObject jsonObjectR = new JSONObject();
        jsonObjectR.put("success",true);
        jsonObjectR.put("message", "已确认收货");
        return jsonObjectR;
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public Object changeStateOfOrder(@RequestBody JSONObject jsonObject){
        String orderId = jsonObject.getString("orderId");
        String request = jsonObject.getString("request");//"收货"Or""退单
        Orders orders = ordersService.getOrderById(orderId);
        String msg;
        if(request.equals("收货")) {
            orders.setState("已收货");
            orders.setReceivetime(new Date());
            msg = "已确认收货";
        }else {
            if(orders.getState().equals("待发货")){
                orders.setState("退单中");
                msg = "已发出退单申请";
            }
            else msg = "已发货，无法退单";
        }
        ordersService.update(orders);
        JSONObject jsonObjectR = new JSONObject();
        jsonObjectR.put("success",true);
        jsonObjectR.put("message", msg);
        return jsonObjectR;
    }
}
