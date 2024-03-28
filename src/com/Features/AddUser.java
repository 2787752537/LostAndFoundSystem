package com.Features;

import com.Interface.loginView;
import com.sqlConnection.sqlConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Objects;

public class AddUser {
    public static int isAddUser(String username, String password1, String password2, ButtonGroup bg, char[] ch, String VerificationCode, JFrame frame) {
        String sex = getSelectedButtonText(bg);
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
                    String sql2 = "insert into User1 (nickname,username,password,sex,Identity,Pickups)values" +
                            "('" + username + "','" + username + "','" + password1 + "','"
                            + sex + "','" + "用户" + "','" + 0 + "')";
                    sqlConnection sqlConnection = new sqlConnection();
                    ResultSet res = sqlConnection.sqlQuery(sql1);
                    try {
                        //判断用户是否存在
                        if (res.next()) {
                            Object[] options = new Object[]{"确定"};
                            int respose = JOptionPane.showOptionDialog(frame, "用户名已存在！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                            if (respose == 0) {
                                return 0;
                            }
                        } else {
                            sqlConnection.sqlUpdate(sql2,frame);
                            Object[] options = new Object[]{"确定"};
                            int respose = 0;
                            try {
                                respose = JOptionPane.showOptionDialog(frame, "创建成功!", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(ImageIO.read(Objects.requireNonNull(PasswordOperation.class.getResource("/images/创建成功.png")))), options, options[0]);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (respose == 0) {
                                frame.dispose();
                                new loginView().setFrame();
                                return 1;
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }finally {
                        try {
                            if(res!=null)res.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        sqlConnection.closeSqlConn();
                    }

                } else {
                    Object[] options = new Object[]{"确定"};
                    int respose = JOptionPane.showOptionDialog(frame, "密码与确认密码不一致！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                    if (respose == 0) {
                        return 0;
                    }
                }
            } else {
                Object[] options = new Object[]{"确定"};
                int respose = JOptionPane.showOptionDialog(frame, "验证码不正确！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                if (respose == 0) {
                    return 0;
                }
            }
        } else {
            Object[] options = new Object[]{"确定"};
            int respose = JOptionPane.showOptionDialog(frame, "账号密码不能为空！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
            if (respose == 0) {
                return 0;
            }
        }
        return 0;
    }

    public static String getSelectedButtonText(ButtonGroup bg) {

        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements(); ) {

            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {

                return button.getText();

            }

        }

        return null;

    }
}
