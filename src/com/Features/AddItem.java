package com.Features;

import com.sqlConnection.sqlConnection;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

public class AddItem {
    //添加物品函数，参数为物品信息
    public static int addItem(String ItemName, String PickUpPlace, String year, String month, String day,
                              String hour, String CurrentItemLocation, String contactNumber, String ContactQQ, ButtonGroup bg,
                              File imagefile1, File imagefile2, File imagefile3, JFrame frame) {
        String types = getSelectedButtonText(bg);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //判断年月日是否输入合法
        try {
            //得到用户输入时间
            Date date = sdf.parse(year + (month.length() == 1 ? "0" + month : month) +
                    (day.length() == 1 ? "0" + day : day) + (hour.length() == 1 ? "0" + hour : hour) + "0000");
            //判断时间是否在五年内
            if (date.getTime() > new Date().getTime() || date.getTime() < new Date().getTime() -
                    sdf.parse("19750000000000").getTime()) {
                Object[] options = new Object[]{"确定"};
                int respose = JOptionPane.showOptionDialog(frame, "请输入正确时间！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                if (respose == 0) {
                    return 0;
                }
            }
        } catch (ParseException e) {
            Object[] options = new Object[]{"确定"};
            int respose = JOptionPane.showOptionDialog(frame, "请输入正确时间！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
            if (respose == 0) {
                return 0;
            }
        }

        String time = year + "-" + month + "-" + day + "-" + hour;
        //得到登录者的用户名,作为物品的发布者
        Properties prop = FileOperations.getprop("memory.txt");
        String publisher = prop.getProperty("username");

        sqlConnection sqlcon = new sqlConnection();
        if (!(ItemName.equals("") || PickUpPlace.equals("") || year.equals("") || month.equals("") ||
                day.equals("") || hour.equals("") || CurrentItemLocation.equals("") || types == null)) {
            FileInputStream fi1 = null;
            FileInputStream fi2 = null;
            FileInputStream fi3 = null;
            String name1 = null;
            String name2 = null;
            String name3 = null;
            try {
                if (imagefile1 != null) {
                    fi1 = new FileInputStream(imagefile1);
                    name1 = imagefile1.getName();
                }else {
                    name1="无";
                }
                if (imagefile2 != null) {
                    fi2 = new FileInputStream(imagefile2);
                    name2 = imagefile2.getName();
                }else {
                    name2="无";
                }
                if (imagefile3 != null) {
                    fi3 = new FileInputStream(imagefile3);
                    name3 = imagefile3.getName();
                }else {
                    name3="无";
                }
                if(contactNumber.equals(""))contactNumber="无";
                if(ContactQQ.equals(""))ContactQQ="无";
                String sql = "insert into article(Item_name,pick_up_place,time,Types,location,contact_number,Contact_qq" +
                        ",publisher,image1,image1name,image2,image2name,image3,image3name)values('"
                        + ItemName + "','" + PickUpPlace + "','" + time + "','" + types + "','" + CurrentItemLocation
                        + "','" +contactNumber + "','" + ContactQQ + "','" + publisher + "'," + "?"
                        + ",'" + name1 + "'," + "?" + ",'" + name2 + "'," + "?" + ",'" + name3 + "')";
                int i = sqlcon.operatingItem(sql, frame, fi1, fi2, fi3);
                if (i == 1) {
                    //添加成功后用户发布次数加一
                    String sql1 = "select * from User1 where username = '" + publisher + "'";
                    ResultSet res = sqlcon.sqlQuery(sql1);
                    try {
                        res.next();
                        int pickups = res.getInt(6);
                        String sql2 = "update User1 set Pickups ='" + (pickups + 1) + "' where username = '" + publisher + "'";
                        sqlcon.sqlUpdate(sql2, frame);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } finally {
                        try {
                            if (res != null) res.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    return 1;
                }
                return 0;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fi1 != null) fi1.close();
                    if (fi2 != null) fi2.close();
                    if (fi3 != null) fi3.close();
                    sqlcon.closeSqlConn();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Object[] options = new Object[]{"确定"};
            int respose = JOptionPane.showOptionDialog(frame, "请填写完整非选填信息！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
            if (respose == 0) {
                return 0;
            }
        }
        return 0;
    }

    //得到
    private static String getSelectedButtonText(ButtonGroup bg) {

        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements(); ) {

            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {

                return button.getText();

            }

        }

        return null;

    }
}
