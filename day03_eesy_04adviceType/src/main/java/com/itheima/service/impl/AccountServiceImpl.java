package com.itheima.service.impl;

import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService{

    @Override
    public void saveAccount() {
        System.out.println("执行了保存");
//        int i=1/0;这种情况下后置通知不执行，因为产生了异常，执行的是异常通知,异常通知和后置通知永远只能执行一个。
//        就好比在业务层实现事务的控制中，要么提交（对应后置通知），要么回滚（对应异常通知），只能执行一个。
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新"+i);

    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
