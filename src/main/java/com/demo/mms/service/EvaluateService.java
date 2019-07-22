package com.demo.mms.service;

import com.alibaba.fastjson.JSONObject;

public interface EvaluateService {
    JSONObject addEvaluate(String userId, String goodId, String evaluation, String stars);

    JSONObject getUserEvaluateList(String userId);

    JSONObject getGoodEvaluateList(String goodId);
}
