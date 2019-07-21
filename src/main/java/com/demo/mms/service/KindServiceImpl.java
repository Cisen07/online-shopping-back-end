package com.demo.mms.service;

import com.demo.mms.common.domain.Kind;
import com.demo.mms.dao.KindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KindServiceImpl implements KindService {
    @Autowired
    private KindMapper kindMapper;

    @Override
    public int getNumberOfKinds() {
        return kindMapper.getNumberOfKinds();
    }

    @Override
    public Kind getTheNthItem(int i) {
        return kindMapper.getTheNthItem(i);
    }
}
