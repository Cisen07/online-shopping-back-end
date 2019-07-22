package com.demo.mms.service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.mms.common.domain.Evaluate;
import com.demo.mms.common.domain.EvaluateKey;
import com.demo.mms.dao.EvaluateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EvaluateServiceImpl implements EvaluateService{
    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public JSONObject addEvaluate(String userId, String goodId, String evaluation, String stars) {
        EvaluateKey tempEvaluateKey = new EvaluateKey();
        tempEvaluateKey.setGoodid(Integer.valueOf(goodId));
        tempEvaluateKey.setUserid(Integer.valueOf(userId));
        Evaluate tempEvaluate = new Evaluate();
        JSONObject jsonObjectR = new JSONObject();
        if (evaluateMapper.selectByPrimaryKey(tempEvaluateKey) != null){
            tempEvaluate.setEvaluation(evaluation);
            tempEvaluate.setStars(Integer.valueOf(stars));
            evaluateMapper.updateByPrimaryKey2(Integer.valueOf(userId), Integer.valueOf(goodId),
                    Integer.valueOf(stars), evaluation);
            jsonObjectR.put("message", "用户评价已更改");
            jsonObjectR.put("success", true);
        } else {
            tempEvaluate.setUserid(Integer.valueOf(userId));
            tempEvaluate.setGoodid(Integer.valueOf(goodId));
            tempEvaluate.setEvaluation(evaluation);
            tempEvaluate.setStars(Integer.valueOf(stars));
            evaluateMapper.insert(tempEvaluate);
            jsonObjectR.put("message", "用户对该产品评价完成");
            jsonObjectR.put("success", true);
        }
        return jsonObjectR;
    }

    @Override
    public JSONObject getUserEvaluateList(String userId) {
        int num = evaluateMapper.getNumByUserId(Integer.valueOf(userId));
        ArrayList<JSONObject> evaluationArrayList = new ArrayList<>();
        for(int i = 0; i < num; i++){
            Evaluate tempEvaluate = evaluateMapper.getEvaluateItem(i, Integer.valueOf(userId));
            JSONObject tempJObject = new JSONObject();
            tempJObject.put("goodId", tempEvaluate.getGoodid());
            tempJObject.put("stars",tempEvaluate.getStars());
            tempJObject.put("evaluation",tempEvaluate.getEvaluation());
            evaluationArrayList.add(tempJObject);
        }
        JSONObject jsonObjectR = new JSONObject();
        try {
            jsonObjectR.put("success", true);
            jsonObjectR.put("message", "查询成功");
            jsonObjectR.put("result", evaluationArrayList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjectR;
    }

    @Override
    public JSONObject getGoodEvaluateList(String goodId) {
        int num = evaluateMapper.getNumByGoodId(Integer.valueOf(goodId));
        ArrayList<JSONObject> evaluationArrayList = new ArrayList<>();
        for(int i = 0; i < num; i++){
            Evaluate tempEvaluate = evaluateMapper.getEvaluateItem2(i, Integer.valueOf(goodId));
            JSONObject tempJObject = new JSONObject();
            tempJObject.put("userId", tempEvaluate.getUserid());
            tempJObject.put("stars",tempEvaluate.getStars());
            tempJObject.put("evaluation",tempEvaluate.getEvaluation());
            evaluationArrayList.add(tempJObject);
        }
        JSONObject jsonObjectR = new JSONObject();
        try {
            jsonObjectR.put("success", true);
            jsonObjectR.put("message", "查询成功");
            jsonObjectR.put("result", evaluationArrayList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjectR;
    }
}
