package com.ItemOperation;

import com.Features.FileOperations;
import com.Interface.loginView;
import com.MainInterfaceSonface.Articleface;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ItemInformationView {
    int width, height;
    BackGroundPanel jPanel = null;
    JButton imagejButton1 = new JButton();
    JButton imagejButton2 = new JButton();
    JButton imagejButton3 = new JButton();
    JButton contact, re;
    JPanel informationjPanel = new JPanel();
    JLabel ItemName, PickUpPlace, PickUpTime, CurrentItemLocation, contactNumber, ContactQQ;

    public void setItemInformationView(String index, JFrame frame) {
        JDialog ItemInformation = new JDialog(frame, "物品信息", true);
        //得到屏幕像素
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗口大小
        ItemInformation.setBounds((width - 500) / 2, (height - 500) / 2, 500, 500);
        //左上角图标
        try {
            ItemInformation.setIconImage(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/物品信息.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ItemInformation.add(jPanel);
        jPanel.setLayout(null);

        ImageIcon[] bigicon = FileOperations.getImage(index, ItemInformation.getWidth(), ItemInformation.getHeight());
        ImageIcon[] icon = FileOperations.getImage(index, 130, 130);
        if (icon[0] != null) {
            imagejButton1.setIcon(icon[0]);
        } else {
            imagejButton1.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/加图片.png"))));
        }
        imagejButton1.setBounds(10, 0, 150, 150);
        imagejButton1.setFocusPainted(false);
        imagejButton1.setBorderPainted(false);
        imagejButton1.setOpaque(false);
        imagejButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imagejButton1.addActionListener(e -> {
            if (bigicon[0] != null) Bigimage.showimage(ItemInformation.getWidth(),ItemInformation.getHeight(),frame,bigicon[0]);
        });

        if (icon[1] != null) {
            imagejButton2.setIcon(icon[1]);
        } else {
            imagejButton2.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/加图片.png"))));
        }
        imagejButton2.setBounds(170, 0, 150, 150);
        imagejButton2.setFocusPainted(false);
        imagejButton2.setBorderPainted(false);
        imagejButton2.setContentAreaFilled(false);
        imagejButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imagejButton2.addActionListener(e -> {
            if (bigicon[1] != null) Bigimage.showimage(ItemInformation.getWidth(),ItemInformation.getHeight(),frame,bigicon[1]);
        });

        if (icon[2] != null) {
            imagejButton3.setIcon(icon[2]);
        } else {
            imagejButton3.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/加图片.png"))));
        }
        imagejButton3.setBounds(330, 0, 150, 150);
        imagejButton3.setFocusPainted(false);
        imagejButton3.setBorderPainted(false);
        imagejButton3.setContentAreaFilled(false);
        imagejButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jPanel.add(imagejButton1);
        jPanel.add(imagejButton2);
        jPanel.add(imagejButton3);
        imagejButton3.addActionListener(e -> {
            if (bigicon[2] != null) Bigimage.showimage(ItemInformation.getWidth(),ItemInformation.getHeight(),frame,bigicon[2]);
        });
        Properties prop = FileOperations.getItemprop(index);
        String[] times = prop.getProperty("time").split("-");
        ItemName = new JLabel("物品名称: " + prop.getProperty("Item_name"));
        PickUpPlace = new JLabel("拾取地: " + prop.getProperty("pick_up_place"));
        PickUpTime = new JLabel("拾取时间: " +times[0]+"年"+times[1]+"月"+times[2]+"日"+times[3]+"时" );
        CurrentItemLocation = new JLabel("目前物品所在地: " + prop.getProperty("location"));
        contactNumber = new JLabel("联系电话: " + prop.getProperty("contact_number"));
        ContactQQ = new JLabel("联系QQ: " + prop.getProperty("Contact_qq"));

        ItemName.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
        PickUpPlace.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
        PickUpTime.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
        CurrentItemLocation.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
        contactNumber.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
        ContactQQ.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));

        informationjPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        informationjPanel.setBounds(0, 160, 450, 250);
        informationjPanel.setBackground(Color.white);

        informationjPanel.add(ItemName);
        informationjPanel.add(PickUpPlace);
        informationjPanel.add(PickUpTime);
        informationjPanel.add(CurrentItemLocation);
        informationjPanel.add(contactNumber);
        informationjPanel.add(ContactQQ);

        jPanel.add(informationjPanel);

        contact = new JButton("联系发布者");
        contact.setBounds(100, 420, 100, 30);
        contact.setBorderPainted(false);
        contact.setForeground(Color.black);
        contact.setBackground(new Color(182, 213, 227));
        contact.setFocusPainted(false);
        contact.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        re = new JButton("返回");
        re.setBounds(300, 420, 100, 30);
        re.setBorderPainted(false);
        re.setForeground(Color.black);
        re.setBackground(new Color(182, 213, 227));
        re.setFocusPainted(false);
        re.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        re.addActionListener(e -> ItemInformation.dispose());
        jPanel.add(contact);
        jPanel.add(re);

        ItemInformation.setResizable(false);
        ItemInformation.setVisible(true);
    }


}
