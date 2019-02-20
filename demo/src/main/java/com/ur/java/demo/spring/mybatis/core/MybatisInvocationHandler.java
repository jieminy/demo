package com.ur.java.demo.spring.mybatis.core;

import com.ur.java.demo.spring.mybatis.ano.UrInsert;
import com.ur.java.demo.spring.mybatis.ano.UrParam;
import com.ur.java.demo.spring.mybatis.utils.JdbcUtils;
import com.ur.java.demo.spring.mybatis.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Slf4j
public class MybatisInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取注解sql
        String sql = getSql(method);
        String[] parameter = SqlUtils.sqlInsertParameter(sql);
        //获取绑定参数
        List<Object> params = processParams(method, args, parameter);
        //生成新sql
        String newSql = SqlUtils.parameQuestion(sql, parameter);
        log.info(newSql);
        //执行sql
        JdbcUtils.insert(newSql, false, params);
        return 1;
    }

    private String getSql(Method method) {
        //获取sql
        UrInsert insert = method.getAnnotation(UrInsert.class);
        String sql = "";
        if (insert != null) {
            sql = insert.value();
            log.info(sql);
        }
        return sql;
    }

    private List<Object> processParams(Method method, Object[] args, String[] parameter) {
        Parameter[] parameters = method.getParameters();
        Map<String, Object> paramMapping = new HashMap<>(parameters.length);
        for (int i = 0; i < parameters.length; i++) {
            UrParam urParam = parameters[i].getAnnotation(UrParam.class);
            if (urParam != null) {
                String key = urParam.value();
                paramMapping.put(key, args[i]);
                log.info(key + ": " + args[i]);
            }
        }
        List<Object> params = new ArrayList<>();
        Arrays.asList(parameter).forEach(item -> {
            params.add(paramMapping.get(item));
            log.info(item);
        });
        return params;
    }
}
