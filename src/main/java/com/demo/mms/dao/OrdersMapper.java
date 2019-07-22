package com.demo.mms.dao;

import com.demo.mms.common.domain.Orders;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    int getNumOfOrdersByUserId(@Param("id")Integer userId);

    Orders selectByUserId(@Param("value")Integer i, @Param("id")Integer userId);
}