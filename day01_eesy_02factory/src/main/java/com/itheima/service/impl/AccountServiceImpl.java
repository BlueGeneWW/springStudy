package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    //下面一句代码中，如果删除了AccountDaoImpl这个类，会报编译错误
    //private IAccountDao accountDao = new AccountDaoImpl();

    //对比上一句代码，他把控制权交给了工厂，由工厂来完成对象的创建，所以叫IOC（控制反转）。好处：把对象的创建交给框架（如spring）或工厂，降低程序间的依赖
    private IAccountDao accountDao = (IAccountDao)BeanFactory.getBean("accountDao");

    //private int i = 1;定义到方法中去，可以改变单列对象对类成员的影响

    public void  saveAccount(){
        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
