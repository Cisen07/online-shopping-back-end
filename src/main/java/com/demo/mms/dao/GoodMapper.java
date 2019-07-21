package com.demo.mms.dao;

import com.demo.mms.common.domain.Good;
import org.apache.ibatis.annotations.Param;

public interface GoodMapper {
    int deleteByPrimaryKey(Integer goodid);

    int insert(Good record);

    int insertSelective(Good record);

    Good selectByPrimaryKey(Integer goodid);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKeyWithBLOBs(Good record);

    int updateByPrimaryKey(Good record);

    int getNumOfGoodsByKind(@Param("value")String cid);

    Good getTheNthGoodFromDB(@Param("value")Integer i);
}