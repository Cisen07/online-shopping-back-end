package com.demo.mms.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.mms.common.domain.Evaluate;
import com.demo.mms.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {
    @Autowired
    private EvaluateService evaluateService;

    @RequestMapping("/addEvaluate")
    @ResponseBody
    public Object addEvaluate(@RequestBody JSONObject jsonObject){
        String userId = jsonObject.getString("userId");
        String goodId = jsonObject.getString("goodId");
        String evaluation = jsonObject.getString("evaluation");
        String stars = jsonObject.getString("stars");
        return evaluateService.addEvaluate(userId, goodId, evaluation, stars);
    }

    @RequestMapping("/userEvaluateList")
    @ResponseBody
    public Object getUserEvaluateList(@RequestBody JSONObject jsonObject){
        String userId = jsonObject.getString("userId");
        return evaluateService.getUserEvaluateList(userId);
    }

    @RequestMapping("/goodEvaluateList")
    @ResponseBody
    public Object getGoodEvaluateList(@RequestBody JSONObject jsonObject){
        String goodId = jsonObject.getString("goodId");
        return evaluateService.getGoodEvaluateList(goodId);
    }
}
