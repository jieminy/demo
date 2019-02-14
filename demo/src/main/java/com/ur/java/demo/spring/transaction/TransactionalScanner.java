package com.ur.java.demo.spring.transaction;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class TransactionalScanner {

    private final TransactionalComponent transactionalComponent;

    @Autowired
    public TransactionalScanner(TransactionalComponent transactionalComponent) {
        this.transactionalComponent = transactionalComponent;
        log.info(transactionalComponent.toString());
    }

    @Pointcut(value = "execution(* com.ur.java.demo.spring.transaction.UserDaoImpl.*(..))")
    public void trscut() {
    }

    @Around("trscut()")
    public Object transactionalAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //开启事务
        begin(isAnnoted(joinPoint));
        //执行目标方法
        Object returnVal = joinPoint.proceed();
        //关闭事务
        commit();
        return returnVal;
    }

    @AfterThrowing(value = "trscut()")
    public void trstThrow() {
        rollback();
    }

    private boolean isAnnoted(ProceedingJoinPoint joinPoint) {
        //获取代理对象方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = signature.getMethod();
        return targetMethod.getDeclaredAnnotation(Trst.class) != null;
    }

    /**
     * 开启事务
     *
     * @param isAnnoted 是否加事务注解
     */
    private void begin(boolean isAnnoted) {
        //该方法是否加上注解
        if (isAnnoted) {
            //如果存在事务注解，开启事务
            transactionalComponent.begin();
        }
    }

    /**
     * 提交事务
     */
    private void commit() {
        //如果存在事务状态，提交事务
        transactionalComponent.commit();
    }

    /**
     * 回滚事务
     */
    private void rollback() {
        transactionalComponent.rollback();
    }
}
