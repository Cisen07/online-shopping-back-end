package com.demo.mms.dao;

import com.demo.mms.common.domain.Kind;
import org.apache.ibatis.annotations.Param;

public interface KindMapper {
    int deleteByPrimaryKey(String kindid);

    int insert(Kind record);

    int insertSelective(Kind record);

    Kind selectByPrimaryKey(String kindid);

    int updateByPrimaryKeySelective(Kind record);

    int updateByPrimaryKey(Kind record);

    int getNumberOfKinds();

    Kind getTheNthItem(@Param("value")Integer n);
}