package com.MainInterfaceSonface;

import com.Features.AchieveNotice;
import com.Interface.loginView;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Noticeface {
    static JPanel Bearer = null;
    static BackGroundPanel locationjPanel = null,locationjPanel1,locationjPanel2;
    static JPanel articlejPanel,personjPanel;
    static JButton Lost, NotRetrieved;
    static JScrollPane locationjsp,articlejsp,personjsp;
    static JLabel articlejLabel, personjLabel, LostjLabel, NotRetrievedjLabel;

    public static JPanel Notice() {
        //创建三个面板，放组件后放入滚动面板
        try {
            locationjPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
            locationjPanel1 = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
            locationjPanel2 = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
            articlejPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
            personjPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //总面板，放三个滚动面板
        Bearer = new JPanel();
        Bearer.setLayout(new GridLayout(1, 3));

        //第一个面板，放地址信息，加入卡片布局器放两个面板，放不同地址信息
        CardLayout locationcl = new CardLayout();
        locationjPanel.setLayout(locationcl);
        locationjPanel1.setLayout(new GridLayout(2000, 1, 10, 10));
        locationjPanel2.setLayout(new GridLayout(2000, 1, 10, 10));
        locationjPanel.add(locationjPanel1);
        locationjPanel.add(locationjPanel2);

        //加两个按钮用于提示和操作
        Lost = new JButton("丢失地排名");
        NotRetrieved = new JButton("未找回地排名");
        //加两个标签
        LostjLabel = new JLabel("丢失地排名");
        NotRetrievedjLabel = new JLabel("未找回地排名");
        LostjLabel.setForeground(Color.blue);
        NotRetrievedjLabel.setForeground(Color.blue);
        //设置按钮属性
        Lost.setFocusPainted(false);
        Lost.setOpaque(false);
        Lost.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        NotRetrieved.setFocusPainted(false);
        NotRetrieved.setOpaque(false);
        NotRetrieved.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //按钮增加监听器
        Lost.addActionListener(e -> locationcl.first(locationjPanel));
        NotRetrieved.addActionListener(e -> locationcl.last(locationjPanel));
        //将按钮组装到盒子中
        Box locationBox1 = Box.createHorizontalBox();
        locationBox1.add(LostjLabel);
        locationBox1.add(Box.createHorizontalStrut(20));
        locationBox1.add(NotRetrieved);
        locationjPanel1.add(locationBox1);

        Box locationBox2 = Box.createHorizontalBox();
        locationBox2.add(Box.createHorizontalStrut(-16));
        locationBox2.add(Lost);
        locationBox2.add(Box.createHorizontalStrut(20));
        locationBox2.add(NotRetrievedjLabel);
        locationjPanel2.add(locationBox2);

        //创建两个标签，提示
        articlejLabel = new JLabel("易丢失物品排名:");
        personjLabel = new JLabel("鸣谢拾取者:");
        articlejPanel.setLayout(new GridLayout(2000, 1, 10, 10));
        GridLayout pgl = new GridLayout(1, 1, 10, 10);
        personjPanel.setLayout(pgl);

        articlejPanel.add(articlejLabel);
        personjPanel.add(personjLabel);

        //创建三个滚动面板，放三个面板
        locationjsp = new JScrollPane();
        articlejsp = new JScrollPane();
        personjsp = new JScrollPane();

        //设置面板属性
        locationjsp.setViewportView(locationjPanel);
        locationjsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        locationjsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        articlejsp.setViewportView(articlejPanel);
        articlejsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        articlejsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        personjsp.setViewportView(personjPanel);
        personjsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        personjsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //将滚动面板放入总面板
        Bearer.add(locationjsp);
        Bearer.add(personjsp);
        Bearer.add(articlejsp);

        ArrayList<String> strs = AchieveNotice.getPerson();
        pgl.setRows(2000);
        for (String s:strs) {
            Box hBox = Box.createHorizontalBox();
            JButton jButton = new JButton("查看他/她 >");
            jButton.setForeground(Color.black);
            jButton.setFocusPainted(false);
            jButton.setOpaque(false);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jButton.addActionListener(e -> System.out.println("按钮" + e.getActionCommand() + "被点击"));

            JLabel jLabel = new JLabel(s);
            jLabel.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
            hBox.add(jLabel);
            hBox.add(Box.createHorizontalGlue());
            hBox.add(jButton);
            personjPanel.add(hBox);
        }


        for (int i = 0; i < 1000; i++) {
            Box hBox = Box.createHorizontalBox();
            JButton jButton = new JButton("查看 >");
            jButton.setForeground(Color.black);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(false);
            jButton.setOpaque(false);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jButton.addActionListener(e -> System.out.println("按钮" + e.getActionCommand() + "被点击"));
            JLabel jLabel = new JLabel("易失物品"+i);
            jLabel.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
            hBox.add(jLabel);
            hBox.add(Box.createHorizontalGlue());
            hBox.add(jButton);
            articlejPanel.add(hBox);
        }


        for (int i = 0; i < 1000; i++) {
            Box hBox = Box.createHorizontalBox();
            JButton jButton = new JButton("查看 >");
            jButton.setForeground(Color.black);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(false);
            jButton.setOpaque(false);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jButton.addActionListener(e -> System.out.println("按钮" + e.getActionCommand() + "被点击"));
            JLabel jLabel = new JLabel("丢失地" + i);
            jLabel.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
            hBox.add(jLabel);
            hBox.add(Box.createHorizontalGlue());
            hBox.add(jButton);
            locationjPanel1.add(hBox);
        }

        for (int i = 0; i < 1000; i++) {
            Box hBox = Box.createHorizontalBox();
            JButton jButton = new JButton("查看 >");
            jButton.setForeground(Color.black);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(false);
            jButton.setOpaque(false);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jButton.addActionListener(e -> System.out.println("按钮" + e.getActionCommand() + "被点击"));
            JLabel jLabel = new JLabel("未找回地" + i);
            jLabel.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
            hBox.add(jLabel);
            hBox.add(Box.createHorizontalGlue());
            hBox.add(jButton);
            locationjPanel2.add(hBox);
        }

        return Bearer;
    }

}
