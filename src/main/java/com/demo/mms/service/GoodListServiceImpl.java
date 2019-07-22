package com.demo.mms.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.mms.common.domain.GoodList;
import com.demo.mms.dao.GoodListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodListServiceImpl implements GoodListService{
    @Autowired
    GoodListMapper goodListMapper;

    @Override
    public Object getGoodsListByOrderId(Integer orderid) {
        int num = goodListMapper.getNumByOrderId(orderid);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < num; i++) {
            JSONObject tempJ = new JSONObject();
            GoodList tempGoodList = goodListMapper.getTheNthItem(orderid, i);
            tempJ.put("goodId", tempGoodList.getGoodid());
            tempJ.put("goodNum", tempGoodList.getGoodnum());
            jsonArray.add(tempJ);
        }
        return jsonArray;
    }
}
