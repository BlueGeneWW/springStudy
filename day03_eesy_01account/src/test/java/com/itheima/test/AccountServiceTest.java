package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    /**
     * 用了动态代理技术之后，我们让重复的代码都消失了（对比AccountServiceImpl类和AccountServiceImpl_OLD类），
     * 让方法之间的依赖也解除了，同时实现了开发时候的效率问题
     *
     */
    @Autowired
    @Qualifier("proxyAccountService")
    private  IAccountService as;//这个对象是bean工厂返回的代理对象

    @Test
    public  void testTransfer(){
        //as.transfer("aaa","bbb",100f);
        //as.findAllAccount();

        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts){
            System.out.println(account);
        }
    }

}
