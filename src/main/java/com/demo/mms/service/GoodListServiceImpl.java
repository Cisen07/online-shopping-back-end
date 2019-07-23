package com.demo.mms.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.mms.common.domain.Good;
import com.demo.mms.common.domain.GoodList;
import com.demo.mms.common.domain.Kind;
import com.demo.mms.dao.GoodListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodListServiceImpl implements GoodListService{
    @Autowired
    GoodListMapper goodListMapper;
    @Autowired
    GoodsService goodsService;
    @Autowired
    KindService kindService;

    @Override
    public Object getGoodsListByOrderId(Integer orderid) {
        int num = goodListMapper.getNumByOrderId(orderid);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < num; i++) {
            JSONObject tempJ = new JSONObject();
            GoodList tempGoodList = goodListMapper.getTheNthItem(orderid, i);
            tempJ.put("goodId", tempGoodList.getGoodid());
//            tempJ.put("subTitle", tempGood.getDescription());
//            tempJ.put("price", tempGood.getPrice());
//            tempJ.put("picUrl", tempGood.getPicture());
//            tempJ.put("kindId", tempGood.getKindid());
            jsonArray.add(tempJ);
        }
        return jsonArray;
    }

    @Override
    public int getNumByOrderId(Integer orderid) {
        return goodListMapper.getNumByOrderId(orderid);
    }

    @Override
    public GoodList getTheNthItem(Integer orderid, int i) {
        return goodListMapper.getTheNthItem(orderid, i);
    }
}
