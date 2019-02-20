package com.ur.java.demo.spring.mybatis;

import com.ur.java.demo.spring.mybatis.core.SqlSession;
import com.ur.java.demo.spring.mybatis.mapper.UserMapper;

public class MbMain {
    public static void main(String[] args) {
        UserMapper userMapper = SqlSession.getMapper(UserMapper.class);
        userMapper.insertUser("xiaoxiao", 22);
    }
}
