package com.weiwen.jdbc;

import java.sql.*;

/**
 * 程序的耦合
 *   耦合：程序间的依赖关系
 *     包括：
 *       类之之间的依赖
 *       方法之间的依赖
 *   解耦：降低程序间的依赖关系
 *   实际开发中应该做到编译期不依赖，运行时才依赖
 *   解耦的思路：
 *     第一步：使用反射来创建对象，而避免使用new关键字,例如：Class forName("com.mysql.jdbc.Driver")
 *            解除在编译期的依赖;
 *            com.mysql.jdbc.Driver为要创建对象的全限定类名
 *     第二部：通过读取配置文件来获取要创建的对象全限定类名
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        //一种方式：Class forName("com.mysql.jdbc.Driver");
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());//pom文件注释掉mysql依赖后在编译期报错
        //2.获取连接
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bluegene","root","root");
        //3.获取操作数据库的预处理对象
        PreparedStatement pstm=conn.prepareStatement("select * from t_stu");
        //4.执行sql，得到结果集
        ResultSet rs=pstm.executeQuery();
        //5.遍历结果
        while(rs.next()){
            System.out.println(rs.getString("stu_name"));
        }
        //6.释放资源 先开的后关
        rs.close();
        pstm.close();
        conn.close();
    }

    public void test(){
        System.out.println("Git");
    }
}
