package com.demo.mms.dao;

import com.demo.mms.common.domain.Evaluate;
import com.demo.mms.common.domain.EvaluateKey;
import org.apache.ibatis.annotations.Param;

public interface EvaluateMapper {
    int deleteByPrimaryKey(EvaluateKey key);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(EvaluateKey key);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKeyWithBLOBs(Evaluate record);

    int updateByPrimaryKey(Evaluate record);

    int updateByPrimaryKey2(@Param("id")Integer userId, @Param("goodid")Integer goodId,
                            @Param("stars")Integer stars, @Param("evaluation")String evaluation);

    int getNumByUserId(@Param("value")Integer userId);

    Evaluate getEvaluateItem(@Param("value")Integer i, @Param("id")Integer userId);

    int getNumByGoodId(@Param("value")Integer goodId);

    Evaluate getEvaluateItem2(@Param("value")Integer i, @Param("id")Integer goodId);
}