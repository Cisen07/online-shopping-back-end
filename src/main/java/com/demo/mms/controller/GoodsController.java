package com.demo.mms.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.mms.common.domain.Good;
import com.demo.mms.common.domain.Kind;
import com.demo.mms.service.GoodsService;
import com.demo.mms.service.KindService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private KindService kindService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/navList")
    @ResponseBody
    public Object getNavList() {
        JSONObject jsonObjectR = new JSONObject();
        ArrayList<JSONObject> resultArrayList = new ArrayList<>();
        int numberOfKinds = kindService.getNumberOfKinds();
        for (int i = 0; i < numberOfKinds; i++) {
            Kind tempKind = kindService.getTheNthItem(i);
            JSONObject tempJObject = new JSONObject();
            tempJObject.put("kindID", tempKind.getKindid());
            tempJObject.put("kindName", tempKind.getKindname());
            resultArrayList.add(tempJObject);
        }
        try {
            jsonObjectR.put("success", true);
            jsonObjectR.put("message", null);
            jsonObjectR.put("result", resultArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjectR;
    }

    @RequestMapping("/allGoods")
    @ResponseBody
    public Object getAllGoods(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObjectR = new JSONObject();
        ArrayList<JSONObject> resultArrayList = new ArrayList<>();
        String cid = jsonObject.getString("cid");
        if (cid.equals("-1")) {   //此时返回所有商品
            int numOfGoods = goodsService.getNumOfGood();
            if (numOfGoods == 0) {
                jsonObjectR.put("success", false);
                jsonObjectR.put("message", "数据库中没有产品");
            } else {
                for (int i = 0; i < numOfGoods; i++) {
                    Good tempGood = goodsService.getTheNthGood(i);
                    JSONObject tempJObject = new JSONObject();
                    tempJObject.put("productId", tempGood.getGoodid());
                    tempJObject.put("productName", tempGood.getGoodname());
                    tempJObject.put("subTitle", tempGood.getDescription());
                    tempJObject.put("salePrice", tempGood.getPrice());
                    tempJObject.put("picUrl", tempGood.getPicture());
                    resultArrayList.add(tempJObject);
                }
                jsonObjectR.put("result", resultArrayList);
                jsonObjectR.put("message", "成功获得全部产品信息");
                jsonObjectR.put("success", true);
            }
            return jsonObjectR;
        } else {  //这里需要针对某个类给出产品信息
            int numOfGoodsByKinds = goodsService.getNumOfGood(cid);
            if (numOfGoodsByKinds == 0) {
                jsonObjectR.put("success", false);
                jsonObjectR.put("result", "该类别下没有产品");
            } else {
                for (int i = 0; i < numOfGoodsByKinds; i++) {
                    Good tempGood = goodsService.getTheNthGoodByKind(cid, i);
                    JSONObject tempJObject = new JSONObject();
                    tempJObject.put("productId", tempGood.getGoodid());
                    tempJObject.put("productName", tempGood.getGoodname());
                    tempJObject.put("subTitle", tempGood.getDescription());
                    tempJObject.put("salePrice", tempGood.getPrice());
                    tempJObject.put("picUrl", tempGood.getPicture());
                    resultArrayList.add(tempJObject);
                }
                jsonObjectR.put("result", resultArrayList);
                jsonObjectR.put("message", "成功获得产品信息");
                jsonObjectR.put("success", true);
            }
            return jsonObjectR;
        }
    }

    @RequestMapping("/productDet")
    @ResponseBody
    public Object getProductInfoByID(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObjectR = new JSONObject();
        String wantProductID = jsonObject.getString("productId");
        Good wantGood = goodsService.getGoodByID(wantProductID);
        if (wantGood == null) {
            jsonObjectR.put("success", false);
            jsonObjectR.put("message", "该产品不存在");
            jsonObjectR.put("result", null);
        } else {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("productId", wantProductID);
            jsonObject1.put("goodName", wantGood.getGoodname());
            jsonObject1.put("productImg", wantGood.getPicture());
            jsonObject1.put("salePrice", wantGood.getPrice());
            jsonObject1.put("subTitle", wantGood.getDescription());
            jsonObjectR.put("success", true);
            jsonObjectR.put("message", "成功获取该产品详细信息");
            jsonObjectR.put("result", jsonObject1);
        }
        return jsonObjectR;
    }

    @RequestMapping("/searchGood")
    @ResponseBody
    public Object searchgood(@RequestBody JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int num = goodsService.getNumBySearch(name);
        ArrayList<JSONObject> resultArrayList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            System.out.println("test3");
            Good good = goodsService.getGoodBySearch(i, name);
            System.out.println("test4");
            JSONObject tempjsonObject = new JSONObject();
            tempjsonObject.put("goodID", good.getGoodid());
            tempjsonObject.put("name", good.getGoodname());
            tempjsonObject.put("kindID", good.getKindid());
            tempjsonObject.put("price", good.getPrice());
            tempjsonObject.put("description", good.getDescription());
            tempjsonObject.put("picture", good.getPicture());
            resultArrayList.add(tempjsonObject);
        }

        JSONObject jsonObjectR = new JSONObject();
        jsonObjectR.put("success", true);
        jsonObjectR.put("message", null);
        jsonObjectR.put("result", resultArrayList);
        return jsonObjectR;
    }
}
