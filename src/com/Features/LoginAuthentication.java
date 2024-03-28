package com.Features;

import com.sqlConnection.sqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAuthentication {
    public static int Iscorrect(String username, String password) {
        int flag = 2;
        //TODO sql 语句
        //数据库语句，查找用户名为username的用户
        String sql = "select * from user1 where username='" + username + "'";
        //创建数据库连接
        sqlConnection sqlconn = new sqlConnection();
        //调用查询方法，得到找到的记录表
        ResultSet rs = sqlconn.sqlQuery(sql);
        try {
            while (rs.next()) {
                //得到记录表中的密码
                String pwd = rs.getString(3);
                System.out.println(pwd);
                //得到登录者的身份IDENTITY
                String position = rs.getString(5);
                System.out.println(position);
                //比较用户输入的密码与数据库中的密码是否一样
                if (password.equals(pwd)) {
                    if (position.equals("管理员")) {
                        flag = 0;
                    } else if (position.equals("用户")) {
                        flag = 1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlconn.closeSqlConn();//调用关闭数据库连接对象函数
            try {
                if (rs != null) rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flag;
    }
}
