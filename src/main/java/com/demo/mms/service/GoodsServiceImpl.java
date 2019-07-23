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
    public int getNumOfGood() {
        return goodMapper.getNumOfGoods();
    }

    @Override
    public Good getTheNthGood(int i) {
        return goodMapper.getTheNthGoodFromDB(i);
    }

    @Override
    public int getNumBySearch(String name) {
        return goodMapper.getNumBySearch(name);
    }

    @Override
    public Good getGoodBySearch(int i, String name) {
        return goodMapper.getGoodBySearch(i,name);
    }

    @Override
    public Good getTheNthGoodByKind(String cid, int i) {
        System.out.println("the cid is:" + cid);
        System.out.println("the i is:" + i);
        return goodMapper.getTheNthGoodByKindFromDB(cid, i);
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
