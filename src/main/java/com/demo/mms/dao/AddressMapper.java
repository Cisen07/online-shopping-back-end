package com.demo.mms.dao;

import com.demo.mms.common.domain.AddressKey;
import org.apache.ibatis.annotations.Param;

public interface AddressMapper {
    void deleteAddress(@Param("id")Integer userIDInt, @Param("name")String name, @Param("add")String address, @Param("num") String phoneNum);

    void insertAddress(@Param("id")Integer userIDInt, @Param("nname")String newName, @Param("nadd") String newAddress, @Param("nnum") String newPhoneNum);

    void updateAddress(@Param("userID")Integer userIDInt, @Param("name")String oldName, @Param("address")String oldAddress, @Param("phonenum")String oldPhoneNum,
                       @Param("nname") String newName, @Param("nadd") String newAddress, @Param("nnum") String newPhoneNum);

    AddressKey selectAddress(@Param("userID")Integer id, @Param("name") String oldName, @Param("address") String oldAddress, @Param("phonenum") String oldPhoneNum);

    int getNumberOfAddress(@Param("id")Integer id);

    AddressKey selectAddressItem(@Param("value")Integer n, @Param("id")Integer userid);

    int deleteByPrimaryKey(AddressKey key);

    int insert(AddressKey record);

    int insertSelective(AddressKey record);
}