package com.ur.java.demo.designer.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDaoImpl implements IUserDao {
    @Override
    public void add() {
        log.info("添加成员");
    }
}
