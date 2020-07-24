package com.itheima.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 *
 * Bean：在计算机英语中，有可重用组件的含义。
 * JavaBean：用java语言编写的可重用组件。
 *      javabean > 实体类 ：实体类只是可重用组件的一部分，业务层和持久层都可以看成是可重用组件
 *
 *   Bean工厂它就是创建我们的service和dao对象的。
 *
 *   第一个：需要一个配置文件来配置我们的service和dao
 *          配置的内容：唯一标识=全限定类名（key=value)
 *   第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 *   我的配置文件可以是xml也可以是properties（properties在解析的时候相对xml来说是相对简单的，spring框架是用xml）
 *   配置文件如果是maven工程的话应该是建在resources里面
 */
public class BeanFactory {

    //定义一个Properties对象
    private static Properties props;

    //定义一个Map,用于存放我们要创建的对象。我们把它称之为容器
    private static Map<String,Object> beans;

    //使用静态代码块为Properties对象赋值，静态代码块只在类加载的时候执行一次
    static {
        try {
            //实例化对象
            props = new Properties();
            //获取properties文件的流对象：
            /**
             * 为什么不用InputStream in =new FileInputStream("路径")?
             *     1.web工程一但部署之后，src没了，相对路径用不了。
             *     2.磁盘绝对路径这个工程中可以用，但是你能保证你的工程以后都有相同磁盘路径吗
             * 所以用BeanFactory.class.getClassLoader()类加载器来获取配置文件的流对象。
             */
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");//最后会成为类根路径下的一个文件
            //这个方法会抛异常，所以try catch
            props.load(in);
            //实例化容器
            beans = new HashMap<String,Object>();
            //取出配置文件中所有的Key，keys()方法返回的是枚举类型
            Enumeration keys = props.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出每个Key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = props.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                //把key和value存入容器中
                beans.put(key,value);
            }
        }catch(Exception e){
            throw new ExceptionInInitializerError("初始化properties失败！");
        }
    }

    /**
     * 根据bean的名称获取对象，这个对象现在是单列的
     * @param beanName
     * @return
     * static方法可以直接用类名来调用
     */
    public static Object getBean(String beanName){
        return beans.get(beanName);
    }

    /**
     * 根据Bean的名称获取bean对象
     * @param beanName
     * @return

    public static Object getBean(String beanName){
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            //System.out.println(beanPath);
            bean = Class.forName(beanPath).newInstance();//每次都会调用默认构造函数创建对象
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }*/
}
