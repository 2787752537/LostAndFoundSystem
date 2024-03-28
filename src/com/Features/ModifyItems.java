package com.Features;

import com.sqlConnection.sqlConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Objects;

public class ModifyItems {
    public static int ModifyItem(String ItemName, String PickUpPlace, String year, String month, String day,
                                 String hour, String CurrentItemLocation, String contactNumber, String ContactQQ, ButtonGroup bg,
                                 File imagefile1, File imagefile2, File imagefile3, String publisher, String select, JFrame frame) {
        //得到被选择的种类
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
        //打包时间
        String time = year + "-" + month + "-" + day + "-" + hour;
        //连接数据库
        sqlConnection sqlcon = new sqlConnection();
        //判段输入内容是否不为空
        if (!(ItemName.equals("") || PickUpPlace.equals("") || year.equals("") || month.equals("") ||
                day.equals("") || hour.equals("") || CurrentItemLocation.equals("") || types == null)) {
            //创建输入流,将图片转为输入流
            FileInputStream fi1 = null;
            FileInputStream fi2 = null;
            FileInputStream fi3 = null;
            //得到图片名称
            String name1 = null;
            String name2 = null;
            String name3 = null;
            try {
                if (imagefile1 != null) {
                    fi1 = new FileInputStream(imagefile1);
                    name1 = imagefile1.getName();
                }
                if (imagefile2 != null) {
                    fi2 = new FileInputStream(imagefile2);
                    name2 = imagefile2.getName();
                }
                if (imagefile3 != null) {
                    fi3 = new FileInputStream(imagefile3);
                    name3 = imagefile3.getName();
                }
                if(contactNumber.equals(""))contactNumber="无";
                if(ContactQQ.equals(""))ContactQQ="无";
                String sql = "update article set Item_name='" + ItemName + "',pick_up_place='" + PickUpPlace + "',time='"
                        + time + "',Types='" + types + "',location='" + CurrentItemLocation + "',contact_number='" + contactNumber +
                        "',Contact_qq='" + ContactQQ + "',publisher='" + publisher + "',image1name='" + name1 +
                        "',image1=" + "?" + ",image2name='" + name2 + "',image2=" + "?" + ",image3name='" + name3 + "',image3=" + "?" +
                        " where index_article =" + select;
                //查看sql语句是否正确
                //System.out.println(sql);
                Object[] options = new Object[]{"确定", "取消"};
                int respose = 0;
                try {
                    respose = JOptionPane.showOptionDialog(frame, "确认要修改吗?", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(ImageIO.read(Objects.requireNonNull(ModifyItems.class.getResource("/images/问号.png")))), options, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (respose == 0) {
                    int i = sqlcon.operatingItem(sql, frame, fi1, fi2, fi3);
                    if (i == 1) {
                        return 1;
                    }
                }
                return 0;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                //释放资源
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
            int respose;
            respose = JOptionPane.showOptionDialog(frame, "请填写完整非选填信息！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
            if (respose == 0) {
                return 0;
            }
        }
        return 0;
    }

    //返回被选中的种类名
    private static String getSelectedButtonText(ButtonGroup bg) {

        //先得到每一个按钮，遍历如果被选中就返回按钮名称
        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements(); ) {

            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {

                return button.getText();

            }

        }

        return null;

    }
}
