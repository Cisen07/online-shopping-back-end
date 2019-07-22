package com.demo.mms.service;

import com.demo.mms.common.domain.Good;

public interface GoodsService {

    int getNumOfGood(String cid);

    Good getTheNthGoodByKind(String cid, int i);

    Good getGoodByID(String s);

    int getNumOfGood();

    Good getTheNthGood(int i);
}
