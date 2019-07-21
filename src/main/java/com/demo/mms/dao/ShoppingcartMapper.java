package com.demo.mms.dao;

import com.demo.mms.common.domain.Kind;
import com.demo.mms.common.domain.Shoppingcart;
import com.demo.mms.common.domain.ShoppingcartKey;
import org.apache.ibatis.annotations.Param;

public interface ShoppingcartMapper {
    int deleteByPrimaryKey(ShoppingcartKey key);

    int insert(Shoppingcart record);

    int insertSelective(Shoppingcart record);

    Shoppingcart selectByPrimaryKey(ShoppingcartKey key);

    int updateByPrimaryKeySelective(Shoppingcart record);

    int updateByPrimaryKey(Shoppingcart record);

    Shoppingcart[] getAllProductByUserID(@Param("value")Integer wantUserID);

    Shoppingcart selectByPrimaryKey2(@Param("userid")Integer wantUserID, @Param("goodid")Integer wantProductID);

    void deleteByPrimaryKey2(@Param("userid")Integer wantUserID, @Param("goodid")Integer wantProductID);
}