package com.demo.mms.service;

import com.demo.mms.common.domain.User;
import com.demo.mms.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.selectByUserName(name);
    }

    @Override
    public void addNewUser(User newUser) {
        userMapper.insert(newUser);
    }

    @Override
    public User getUserByID(String s) {
        int wantUserIDInt = 0;
        try {
            wantUserIDInt = Integer.valueOf(s).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return userMapper.selectByPrimaryKey(wantUserIDInt);
    }

    @Override
    public void updateUser(User newUser) {
        userMapper.updateByPrimaryKey(newUser);
    }

}
