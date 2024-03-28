package com.sqlConnection;

import com.Features.FileOperations;
import com.Features.ModifyItems;
import com.Features.PasswordOperation;
import com.Interface.loginView;
import com.MainInterfaceSonface.Articleface;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class sqlConnection {
    //声明连接数据库对象
    static Connection ct = null;
    //声明预处理SQL语句对象
    static PreparedStatement ps = null;
    //声明数据库结果集的数据表，通常通过执行查询数据库的语句生成。
    ResultSet rs = null;
//
//    static String password = "Yb2787752537";加载驱动语句
////    static String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
////    String dburl = "jdbc:oracle:thin:system/Yb2787752537@localhost:1521:orcl";
////    另一种连接方式oci写法
////    String dburl = "jdbc:oracle:oci:@orcl";
//
////    //oracle连接
////    static String dburl = "jdbc:oracle:thin:@localhost:1521:orcl";
////    //用户名
////    static String userName = "C##LOSTANDFOUND";
////    //密码

    //MySQL连接
    static String dburl = "jdbc:mysql://localhost:3306/lostandfound?serverTimezone=UTC";
    //用户名
    static String userName = "root";
    //密码
    static String password = "root";

    public static BackGroundPanel getNoticejpanel(String sql, JFrame frame, JMenuItem Refresh) {
        sqlConnection sqlcon = new sqlConnection();
        ResultSet res = sqlcon.sqlQuery(sql);
        BackGroundPanel information = null;
        try {
            information = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GridLayout gl = new GridLayout(1, 1, 20, 10);
        if (information != null) {
            information.setLayout(gl);
        }
        int i = 0;
        try {
            while (res.next()) {
                String index_find = res.getString(1);
                i++;
                Box VBox = Box.createVerticalBox();
                JButton index = new JButton("联系失主 >");
                index.setForeground(Color.black);
                index.setFocusPainted(false);
                index.setOpaque(false);
                index.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                String details = res.getString(6);
                StringBuilder substring = new StringBuilder();
                int j = 0;
                for (; (j + 45) < details.length(); j += 45) {
                    substring.append(details, j, j + 45);
                    substring.append("<br/>");
                }
                substring.append(details, j, details.length());
                JLabel jLabel = new JLabel("<html>丢失物品名称:" + res.getString(2) +
                        "<br/>丢失时间:" + res.getString(3) + "<br/>丢失地:" + res.getString(4) +
                        "<br/>联系方式:" + res.getString(5) + "<br/>启事详情:" + substring);
                jLabel.setSize(50, 100);
                JPanel jPanel = new JPanel();
                jPanel.setBackground(new Color(202, 255, 233));
                jLabel.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 17));
                jPanel.add(jLabel);
                JButton delete = new JButton("删除     >");
                delete.setFocusPainted(false);
                delete.addActionListener(e -> deletemessage(index_find, frame, Refresh));
                Box hBox = Box.createHorizontalBox();
                hBox.add(index);
                hBox.add(Box.createHorizontalGlue());
                hBox.add(delete);
                VBox.add(jPanel);
                VBox.add(hBox);
                gl.setRows(i);
                if (information != null) {
                    information.add(VBox);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            sqlcon.closeSqlConn();
        }
        return information;
    }

    private static void deletemessage(String selected, JFrame frame, JMenuItem Refresh) {
        sqlConnection sqlcon = new sqlConnection();
        String sql = "select * from find where index_find = '" + selected + "'";
        ResultSet res = sqlcon.sqlQuery(sql);
        Properties prop = FileOperations.getuserprop();
        try {
            res.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql1 = "DELETE FROM find WHERE index_find = '" + selected + "'";
        try {
            if (prop.getProperty("Identity").equals("管理员")) {
                Object[] options = new Object[]{"确定", "取消"};
                int respose;
                respose = JOptionPane.showOptionDialog(frame, "确认要删除" + res.getString(2) + "吗?", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/问号.png"))), options, null);
                if (respose == 0) {
                    sqlcon.sqlUpdate(sql1, frame);
                    Refresh.doClick();
                }
            } else {
                if (prop.getProperty("username").equals(res.getString(7))) {
                    Object[] options = new Object[]{"确定", "取消"};
                    int respose = 0;
                    try {
                        respose = JOptionPane.showOptionDialog(frame, "确认要删除" + res.getString(2) + "吗?", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/问号.png"))), options, null);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (respose == 0) {
                        sqlcon.sqlUpdate(sql1, frame);
                        Refresh.doClick();
                    }
                } else {
                    Object[] options = new Object[]{"确定"};
                    JOptionPane.showOptionDialog(frame, "你不是发布者,无法执行此操作！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            sqlcon.closeSqlConn();
        }
    }

    public static void deletearticle(String sql, String selected, JFrame frame) {

        System.out.println(sql);
        try {
            //Class.forName(driver);
            ct = DriverManager.getConnection(dburl, userName, password);
            ps = ct.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(selected));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //执行增删改SQL语句的方法，返回值是一个整数，指示受影响的行数
        try {
            ps.executeUpdate();
        } catch (SQLException throwables) {
            Object[] options = new Object[]{"确定"};
            int respose = JOptionPane.showOptionDialog(frame, "操作出错!", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
            if (respose == 0) {
                return;
            }
        }
        ArrayList<String> historyArr = FileOperations.read();
        historyArr.remove(selected);
        FileOperations.store(historyArr, frame);

        Object[] options = new Object[]{"确定"};
        try {
            JOptionPane.showOptionDialog(frame, "操作成功!", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(ImageIO.read(Objects.requireNonNull(PasswordOperation.class.getResource("/images/创建成功.png")))), options, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 用于执行查询语句返回记录表
    public ResultSet sqlQuery(String sql) {
        try {
            //加载驱动
            //Class.forName(driver);
            //连接数据库，ct不为空则可代表已经连接的数据库
            ct = DriverManager.getConnection(dburl, userName, password);
            //对象是一个预编译的SQL语句。
            //一个SQL语句的预编译并存储在一个PreparedStatement对象。此对象可以被用来有效地执行此语句多次。
            ps = ct.prepareStatement(sql);
            //执行查询语句的方法，返回一个数据表
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int operatingItem(String sql, JFrame frame, FileInputStream fi1, FileInputStream fi2, FileInputStream fi3) {
        try {
            //加载驱动
            //固定写法加载驱动，现在可以不用写系统自动驱动
            //Class.forName(driver);
            //System.out.println(sql);
            ct = DriverManager.getConnection(dburl, userName, password);
            ps = ct.prepareStatement(sql);
            ps.setBlob(1, fi1);
            ps.setBlob(2, fi2);
            ps.setBlob(3, fi3);
            //执行增删改SQL语句的方法，返回值是一个整数，指示受影响的行数
            int i = ps.executeUpdate();
            if (i == 1) {
                Object[] options = new Object[]{"确定"};
                int respose = JOptionPane.showOptionDialog(frame, "操作成功!", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(ImageIO.read(Objects.requireNonNull(ModifyItems.class.getResource("/images/创建成功.png")))), options, null);
                if (respose == 0) {
                    return 1;
                }
            } else {
                Object[] options = new Object[]{"确定"};
                int respose = JOptionPane.showOptionDialog(frame, "操作失败!", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                if (respose == 0) {
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    //定义一个用于执行增加修改的SQL语句函数
    public int sqlUpdate(String sql, JFrame frame) {
        try {
            //加载驱动
            //固定写法加载驱动，现在可以不用写系统自动驱动
            //Class.forName(driver);

            ct = DriverManager.getConnection(dburl, userName, password);
            ps = ct.prepareStatement(sql);
            //执行增删改SQL语句的方法，返回值是一个整数，指示受影响的行数int i
            int i = 0;
            try {
                i = ps.executeUpdate();
            } catch (SQLException throwables) {
                Object[] options = new Object[]{"确定"};
                int respose = JOptionPane.showOptionDialog(frame, "操作出错!", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                if (respose == 0) {
                    return 0;
                }
            }
            if (i == 1) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //实现关闭数据库连接的功能
    public void closeSqlConn() {
        try {
            //先开后关

            //关闭记录表
            if (rs != null) rs.close();
            //关闭预处理SQL语句对象
            if (ps != null) ps.close();
            //关闭连接数据库对象
            if (ct != null) ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
