package com.Features;

import com.Interface.loginView;
import com.sqlConnection.sqlConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PasswordOperation {
    public static void getPassword(String username, char[] ch, String VerificationCode, JFrame frame) {
        //得到生成的验证码
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < ch.length; i += 2) {
            s.append(ch[i]);
        }
        if (!username.equals("")) {
            if (s.toString().equals(VerificationCode)) {
                String sql = "select * from User1 where username='" + username + "'";
                sqlConnection sqlConnection = new sqlConnection();
                ResultSet res = sqlConnection.sqlQuery(sql);
                try {
                    if (res.next()) {
                        String pwd = res.getString(3);
                        Object[] options = new Object[]{"确定"};
                        int respose = 0;
                        try {
                            respose = JOptionPane.showOptionDialog(frame, "你的密码是:" + pwd, "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(ImageIO.read(Objects.requireNonNull(PasswordOperation.class.getResource("/images/得到密码.png")))), options, options[0]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (respose == 0) {
                            frame.dispose();
                            new loginView().setFrame();
                        }
                    } else {
                        Object[] options = new Object[]{"确定"};
                        int respose = JOptionPane.showOptionDialog(frame, "用户不存在！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                        System.out.println(respose);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    try {
                        if (res != null) res.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    sqlConnection.closeSqlConn();
                }

            } else {
                Object[] options = new Object[]{"确定"};
                JOptionPane.showOptionDialog(frame, "验证码不正确！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
            }
        } else {
            Object[] options = new Object[]{"确定"};
            JOptionPane.showOptionDialog(frame, "请输入用户名！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
        }
    }

    public static void changePassword(String username, String password1, String password2, char[] ch, String VerificationCode, JFrame frame) {
        //得到生成的验证码
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < ch.length; i += 2) {
            s.append(ch[i]);
        }
        //判断账号密码是否为空
        if ((!username.equals("")) && (!password1.equals(""))) {
            //判断验证码是否正确
            if (s.toString().equals(VerificationCode)) {
                //判断两次输入密码是否一致
                if (password1.equals(password2)) {
                    //添加语句
                    String sql1 = "select * from User1 where username='" + username + "'";
                    String sql2 = "update User1 set password='" + password1 + "' where username='" + username + "'";
                    sqlConnection sqlConnection = new sqlConnection();
                    ResultSet res = sqlConnection.sqlQuery(sql1);
                    try {
                        if (res.next()) {
                            sqlConnection.sqlUpdate(sql2, frame);
                            Object[] options = new Object[]{"确定"};
                            int respose = 0;
                            try {
                                respose = JOptionPane.showOptionDialog(frame, "修改密码成功!", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(ImageIO.read(Objects.requireNonNull(PasswordOperation.class.getResource("/images/得到密码.png")))), options, options[0]);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (respose == 0) {
                                frame.dispose();
                                new loginView().setFrame();
                            }
                        } else {
                            Object[] options = new Object[]{"确定"};
                            JOptionPane.showOptionDialog(frame, "用户不存在！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } finally {
                        try {
                            if (res != null) res.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        sqlConnection.closeSqlConn();
                    }

                } else {
                    Object[] options = new Object[]{"确定"};
                    JOptionPane.showOptionDialog(frame, "密码与确认密码不一致！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                }
            } else {
                Object[] options = new Object[]{"确定"};
                JOptionPane.showOptionDialog(frame, "验证码不正确！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
            }
        } else {
            Object[] options = new Object[]{"确定"};
            JOptionPane.showOptionDialog(frame, "账号密码不能为空！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
        }
    }
}
