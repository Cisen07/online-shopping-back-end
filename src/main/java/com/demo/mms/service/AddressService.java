package com.demo.mms.service;

import com.demo.mms.common.domain.AddressKey;

public interface AddressService {

    int getNumberOfAddress(String userid);

    AddressKey getAddressItem(int i, String userid);

    AddressKey findAddress(String userId, String oldName, String oldAddress, String oldPhoneNum);

    void update(String userId, String oldName, String oldAddress, String oldPhoneNum,
                String newName, String newAddress, String newPhoneNum);

    void addAddress(String userId, String newName, String newAddress, String newPhoneNum);

    void delAddress(String userId, String name, String address, String phoneNum);
}
