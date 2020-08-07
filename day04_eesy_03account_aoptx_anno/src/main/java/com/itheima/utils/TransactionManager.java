package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 1.和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 * 2.本例中，如果使用注解aop方式实现事务的控制（不包含环绕通知），执行的顺序是前置通知，最终通知，后置通知或
 *   前置通知，异常通知，后置通知。如果是前置通知 最终通知 后置通知这种顺序，就会出现sql异常，因为执行
 *   最终通知的时候，线程和连接已经解绑，这个时候再执行后置通知的时候，会重新从新获取一个连接绑定到当前线程上，
 *   但是这个连接并没有设置setautocommit(false)，当通过应用程序提交的时候就会报出异常。
 *
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}


    /**
     * 开启事务
     */
    public  void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public  void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public  void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 释放连接
     */
    public  void release(){
        try {
            connectionUtils.getThreadConnection().close();//还回连接池中
            connectionUtils.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            //1.获取 参数
            Object[] args = pjp.getArgs();
            //2.开启事务
            this.beginTransaction();
            //3.执行方法
            rtValue = pjp.proceed(args);
            //4.提交事务
            this.commit();

            //返回结果
            return  rtValue;

        }catch (Throwable e){
            //5.回滚事务
            this.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放资源
            this.release();
        }
    }
}
