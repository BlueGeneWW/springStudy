package com.itheima.ui;

import com.itheima.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 * ApplicationContext是一个很智能的接口，它可以根据我们对象是单列的还是多列的决策采用延迟加载
 *                     创建对象还是立即创建对象
 */
public class Client {

    /**
     *
     * main方法是一切应用程序的入口，当main方法结束之后，我们当前的这个应用程序中线程
     * 占据的内存全部释放。如下：这个时候当然也也包含容器，但是这个时候还没来得及调用容器的销毁方法，
     * 就已经把容器的内存给释放了，主线程就已经结束了，所以手动关闭容器。
     *
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取Bean对象
        IAccountService as  = (IAccountService)ac.getBean("accountService");
        //设置bean的作用范围为prototype，比较as和as1
        //IAccountService as1  = (IAccountService)ac.getBean("accountService");
        //System.out.println(as==as1);
        as.saveAccount();

        //手动关闭容器
        //为什么写成ClassPathXmlApplicationContext ac？因为在多态中父类对象（ApplicationContext ac）调用不了子类方法
        ac.close();
    }
}
