package com.demo.mms.service;

import com.demo.mms.common.domain.Good;

public interface GoodsService {

    int getNumOfGood(String cid);

    Good getTheNthGood(int i);

    Good getGoodByID(String s);
}
