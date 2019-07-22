package com.demo.mms.dao;

import com.alibaba.fastjson.JSONObject;
import com.demo.mms.common.domain.GoodList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface GoodListMapper {
    int insert(GoodList record);

    int insertSelective(GoodList record);

    int getNumByOrderId(@Param("value")Integer orderid);

    GoodList getTheNthItem(@Param("id")Integer orderid, @Param("value")Integer i);

    void deleteByOrderId(@Param("value")Integer orderId);
}