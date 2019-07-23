package com.demo.mms.service;

import com.demo.mms.common.domain.User;

public interface UserService {
    User findUserByName(String name);

    void addNewUser(User newUser);

    User getUserByID(String s); //暂时没有用到

    void updateUser(User newUser);
}
