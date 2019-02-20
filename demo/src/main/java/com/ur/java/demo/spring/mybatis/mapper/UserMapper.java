package com.ur.java.demo.spring.mybatis.mapper;

import com.ur.java.demo.spring.mybatis.ano.UrInsert;
import com.ur.java.demo.spring.mybatis.ano.UrParam;

public interface UserMapper {
    @UrInsert("Insert into user values (#{name},#{age})")
    int insertUser(@UrParam("name") String name, @UrParam("age") int age);
}
