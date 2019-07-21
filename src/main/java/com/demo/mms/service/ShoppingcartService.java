package com.demo.mms.service;

import com.demo.mms.common.domain.Shoppingcart;

public interface ShoppingcartService {
    String[] getAllProductIDByUserID(String wantUserID);

    String[] getAllProductNumByUserID(String wantUserID);

    Shoppingcart getProductBy2IDs(String wantUserID, String wantProductID);

    void insert(Shoppingcart insertShoppingcart);

    void update(Shoppingcart updateShoppingcart);

    void delete(String wantUserID, String wantProductID);
}
