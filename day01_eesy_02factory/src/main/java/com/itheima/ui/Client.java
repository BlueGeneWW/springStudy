package com.itheima.ui;

import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    /**
     * 1.单例对象是说从始至终只有一个对象实例，对象只被创建了一次，
     *   从而类中的成员也就只会初始化一次：servlet就是一个单例对象
     *
     * 2.多例对象，对象被创建多次，执行效率没有多例对象高
     */
    public static void main(String[] args) {
        //IAccountService as = new AccountServiceImpl();
        for(int i=0;i<5;i++) {
            IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(as);//多例对象
            as.saveAccount();
        }

    }
}
