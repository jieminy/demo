package com.ur.java.demo.spring.transaction;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Trst
    public void add() {
        String sql = "insert into user values ('wangwu')";
//        int i = 1/0;
        jdbcTemplate.execute(sql);
    }

    @Override
    public void del() {
        String sql = "delete from user where name = 'zhangsan'";
        int i = 1 / 0;
        jdbcTemplate.execute(sql);
    }
}
