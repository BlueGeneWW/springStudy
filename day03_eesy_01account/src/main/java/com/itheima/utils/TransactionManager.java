package com.itheima.utils;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 * 连接池的好处：把消耗时间获取连接的这一部分放在我们应用加载的一开始。在
 *             web工程中，当我们启动tomcat加载应用时我们创建一些连接，
 *             从而在后续项目运行阶段不再跟数据库获取连接，保证了我们使
 *             用Connection时的执行效率。这个时候如果我们使用服务器，服务器也
 *             会有一个池的技术叫做线程池，它的特点是当tomcat一启动时，会初
 *             始化一大堆的线程放到一个容器中，接下来我们的每一次访问，它都是
 *             从线程池中拿出一个线程给我们使用，那如果是这样的话，线程池中的
 *             线程也和我们连接池中的连接一样，调用close（）方法并不是真正的
 *             关闭，而是把它还回池中。
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

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
            /**
             * 这个close()方法，它并不是真正把我们的连接关了，而是把连接还回连接池中,
             * 照理推断，线程用完了，也不是把线程真正的关了，而是把线程还回线程池中。那如果
             * 是这样的话，这个线程中其实是绑着一个连接的，当我们把连接关闭，线程还回池中时候，
             * 这个线程上是有一个连接的，只不过这个连接已经被关闭了，当我们下次再获取这个线程，
             * 判断这个线程上是否有连接的时候，判断结果一定是有，但这个连接已经不能用了，因为它
             * 已经被close（）过了，因此最后，调用removeConnection()完成解绑。
             */
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();//把连接和线程解绑
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
