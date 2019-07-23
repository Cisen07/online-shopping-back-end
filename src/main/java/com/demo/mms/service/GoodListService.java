package com.demo.mms.service;

import com.demo.mms.common.domain.GoodList;

public interface GoodListService {
    Object getGoodsListByOrderId(Integer orderid);

    int getNumByOrderId(Integer orderid);

    GoodList getTheNthItem(Integer orderid, int i);
}
