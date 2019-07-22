package com.demo.mms.service;

import com.demo.mms.common.domain.Good;
import com.demo.mms.dao.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodMapper goodMapper;

    @Override
    public int getNumOfGood(String cid) {
        return goodMapper.getNumOfGoodsByKind(cid);
    }

    @Override
    public Good getTheNthGood(int i) {
        return goodMapper.getTheNthGoodFromDB(i);
    }

    @Override
    public Good getGoodByID(String s) {
        int wantUserIDInt = 0;
        try {
            wantUserIDInt = Integer.valueOf(s).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return goodMapper.selectByPrimaryKey(wantUserIDInt);
    }
}