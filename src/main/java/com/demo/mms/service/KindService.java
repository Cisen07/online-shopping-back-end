package com.demo.mms.service;

import com.demo.mms.common.domain.Kind;

public interface KindService {
    int getNumberOfKinds();

    Kind getTheNthItem(int i);
}
