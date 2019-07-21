package com.demo.mms.service;

import com.demo.mms.common.domain.Shoppingcart;
import com.demo.mms.dao.ShoppingcartMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingcartServiceImpl implements ShoppingcartService{
    @Autowired
    ShoppingcartMapper shoppingcartMapper;

    @Override
    public String[] getAllProductIDByUserID(String wantUserID) {
        int wantUserIDInt = 0;
        try {
            wantUserIDInt = Integer.valueOf(wantUserID).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Shoppingcart[] shoppingcartList = shoppingcartMapper.getAllProductByUserID(wantUserIDInt);
        int sizeOfList = shoppingcartList.length;
        String[] stringList = new String[sizeOfList];
        for (int i = 0; i < sizeOfList; i++) {
            stringList[i] = String.valueOf(shoppingcartList[i].getGoodid());
        }
        return stringList;
    }

    @Override
    public String[] getAllProductNumByUserID(String wantUserID) {
        int wantUserIDInt = 0;
        try {
            wantUserIDInt = Integer.valueOf(wantUserID).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Shoppingcart[] shoppingcartList = shoppingcartMapper.getAllProductByUserID(wantUserIDInt);
        int sizeOfList = shoppingcartList.length;
        String[] stringList = new String[sizeOfList];
        for (int i = 0; i < sizeOfList; i++) {
            stringList[i] = String.valueOf(shoppingcartList[i].getGoodnum());
        }
        return stringList;
    }

    @Override
    public Shoppingcart getProductBy2IDs(String wantUserID, String wantProductID) {
        int wantUserIDInt = 0;
        int wantProductIDInt = 0;
        try {
            wantUserIDInt = Integer.valueOf(wantUserID).intValue();
            wantProductIDInt = Integer.valueOf(wantProductID).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return shoppingcartMapper.selectByPrimaryKey2(wantUserIDInt, wantProductIDInt);
    }

    @Override
    public void insert(Shoppingcart insertShoppingcart) {
        shoppingcartMapper.insert(insertShoppingcart);
    }

    @Override
    public void update(Shoppingcart updateShoppingcart) {
        shoppingcartMapper.updateByPrimaryKey(updateShoppingcart);
    }

    @Override
    public void delete(String wantUserID, String wantProductID) {
        int wantUserIDInt = 0;
        int wantProductIDInt = 0;
        try {
            wantUserIDInt = Integer.valueOf(wantUserID).intValue();
            wantProductIDInt = Integer.valueOf(wantProductID).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        shoppingcartMapper.deleteByPrimaryKey2(wantUserIDInt, wantProductIDInt);
    }

}
