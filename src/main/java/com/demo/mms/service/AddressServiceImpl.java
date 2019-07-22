package com.demo.mms.service;

import com.demo.mms.common.domain.AddressKey;
import com.demo.mms.dao.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public int getNumberOfAddress(String userid) {
        int id = 0;
        try {
            id = Integer.valueOf(userid).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return addressMapper.getNumberOfAddress(id);
    }

    @Override
    public AddressKey getAddressItem(int i, String userid) {
        int id = 0;
        try {
            id = Integer.valueOf(userid).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return addressMapper.selectAddressItem(i, id);
    }

    @Override
    public AddressKey findAddress(String userId, String oldName, String oldAddress, String oldPhoneNum) {
        int id = 0;
        try {
            id = Integer.valueOf(userId).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        AddressKey tempAdd = addressMapper.selectAddress(id, oldName, oldAddress, oldPhoneNum);
        return tempAdd;
    }

    @Override
    public void update(String userId, String oldName, String oldAddress, String oldPhoneNum,
                       String newName, String newAddress, String newPhoneNum) {
        int userIDInt = 0;
        try {
            userIDInt = Integer.valueOf(userId).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        addressMapper.updateAddress(userIDInt, oldName, oldAddress, oldPhoneNum, newName,newAddress,newPhoneNum);
    }

    @Override
    public void addAddress(String userId, String newName, String newAddress, String newPhoneNum) {
        int userIDInt = 0;
        try {
            userIDInt = Integer.valueOf(userId).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        addressMapper.insertAddress(userIDInt,newName,newAddress,newPhoneNum);
    }

    @Override
    public void delAddress(String userId, String name, String address, String phoneNum) {
        int userIDInt = 0;
        try {
            userIDInt = Integer.valueOf(userId).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        addressMapper.deleteAddress(userIDInt,name,address,phoneNum);
    }
}
