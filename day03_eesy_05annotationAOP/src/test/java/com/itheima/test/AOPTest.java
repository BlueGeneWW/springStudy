package com.itheima.test;

import com.itheima.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 动态代理：不改变源码的基础上，对原有方法的增强。
 * 测试AOP的配置
 * 开发中可能用到aop的情况：记录日志，判断用户登录，统计执行效率，判断是否有权限访问，实现业务层的事务控制。。。。。。（抽取出重复的代码）
 */
public class AOPTest {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        IAccountService as = (IAccountService)ac.getBean("accountService");
        //3.执行方法
        as.saveAccount();
    }
}
