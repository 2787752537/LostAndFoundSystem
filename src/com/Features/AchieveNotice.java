package com.Features;

import com.sqlConnection.sqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AchieveNotice {
    //函数读取出有发布失物招领的人
    public static ArrayList<String> getPerson() {
        ArrayList<String> strs = new ArrayList<>();
        sqlConnection sqlcon = new sqlConnection();
        String sql = "select * from User1 where Pickups != 0";
        ResultSet res = sqlcon.sqlQuery(sql);
        try {
            while (res.next()) {
            strs.add(res.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            sqlcon.closeSqlConn();
            if(res!=null) {
                try {
                    res.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return strs;
    }
}
