package com.Interface;

import com.Features.PasswordOperation;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class RetrievePasswordView {
    //创建窗口对象
    JFrame frame = new JFrame("忘记密码");
    int width, height;
    //找回密码面板
    BackGroundPanel RetrievePasswordPanel = null;
    //修改密码面板
    BackGroundPanel ChangePasswordPanel = null;
    JPanel jPanel = new JPanel();
    JButton RetrievePasswordButton, ChangePasswordButton;
    JLabel ChangejLabel1, ChangejLabel2, ChangejLabel3, ChangejLabel4, RetrievejLabel1, RetrievejLabel2;
    JTextField Changefield1, Changefield4, Retrievefield1, Retrievefield2;
    JPasswordField Changefield2, Changefield3;
    char[] Changech = new char[8];
    char[] Retrievech = new char[8];
    JButton determine, returnLog, determine1, returnLog1;

    public void setView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        //得到屏幕像素
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗口大小
        frame.setBounds((width - 309) / 2, (height - 500) / 2, 309, 500);
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/忘记密码.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //修改密码面板
        try {
            RetrievePasswordPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        RetrievePasswordPanel.drawLine(100, 70, 250, 70);
        RetrievePasswordPanel.drawLine(100, 120, 250, 120);
        RetrievePasswordPanel.setLayout(null);

        //找回密码面板
        try {
            ChangePasswordPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChangePasswordPanel.drawLine(100, 70, 250, 70);
        ChangePasswordPanel.drawLine(100, 120, 250, 120);
        ChangePasswordPanel.drawLine(100, 170, 250, 170);
        ChangePasswordPanel.drawLine(100, 220, 250, 220);
        ChangePasswordPanel.setLayout(null);

        //总面板
        CardLayout cardLayout = new CardLayout();
        jPanel.setLayout(cardLayout);
        jPanel.setBounds(0, 32, 309, 460);
        jPanel.add(ChangePasswordPanel);
        jPanel.add(RetrievePasswordPanel);

        //窗口内按钮，用于切换面板
        RetrievePasswordButton = new JButton("重置密码");
        RetrievePasswordButton.setBounds(0, 0, 100, 30);
        RetrievePasswordButton.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 15));
        RetrievePasswordButton.setBorderPainted(false);
        RetrievePasswordButton.setFocusPainted(false);
        RetrievePasswordButton.setForeground(Color.blue);
        RetrievePasswordButton.setContentAreaFilled(false);
        RetrievePasswordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ChangePasswordButton = new JButton("找回密码");
        ChangePasswordButton.setBounds(200, 0, 100, 30);
        ChangePasswordButton.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 15));
        ChangePasswordButton.setBorderPainted(false);
        ChangePasswordButton.setFocusPainted(false);
        ChangePasswordButton.setForeground(Color.black);
        ChangePasswordButton.setContentAreaFilled(false);
        ChangePasswordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //添加监听器实现面板切换功能
        RetrievePasswordButton.addActionListener(e -> {
            RetrievePasswordButton.setForeground(Color.blue);
            ChangePasswordButton.setForeground(Color.black);
            cardLayout.first(jPanel);
        });
        ChangePasswordButton.addActionListener(e -> {
            RetrievePasswordButton.setForeground(Color.black);
            ChangePasswordButton.setForeground(Color.blue);
            cardLayout.last(jPanel);
        });

        //将按钮添加入窗口
        frame.add(RetrievePasswordButton);
        frame.add(ChangePasswordButton);
        //把面板放在窗口上
        frame.add(jPanel);


        //创建修改密码面板的标签
        ChangejLabel1 = new JLabel("我的账号:");
        ChangejLabel2 = new JLabel("新密码:");
        ChangejLabel3 = new JLabel("确认密码:");
        ChangejLabel4 = new JLabel("验证码:");
        //创建找回密码面板的标签
        RetrievejLabel1 = new JLabel("我的账号:");
        RetrievejLabel2 = new JLabel("验证码:");

        ChangejLabel1.setBounds(33, 30, 100, 50);
        ChangejLabel2.setBounds(43, 80, 100, 50);
        ChangejLabel3.setBounds(33, 130, 100, 50);
        ChangejLabel4.setBounds(43, 180, 100, 50);

        RetrievejLabel1.setBounds(33, 30, 100, 50);
        RetrievejLabel2.setBounds(43, 90, 100, 50);

        //修改密码文本框
        Changefield1 = new JTextField(20);
        Changefield1.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        Changefield2 = new JPasswordField(20);
        Changefield2.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 13));
        Changefield3 = new JPasswordField(20);
        Changefield3.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 13));
        Changefield4 = new JTextField(20);
        Changefield4.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));

        //找回密码文本框
        Retrievefield1 = new JTextField(20);
        Retrievefield1.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        Retrievefield2 = new JTextField(20);
        Retrievefield2.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));

        Changefield1.setBounds(100, 40, 200, 30);
        Changefield1.setBackground(null);
        Changefield1.setOpaque(false);
        Changefield1.setBorder(null);
        Changefield2.setBounds(100, 90, 200, 30);
        Changefield2.setBackground(null);
        Changefield2.setOpaque(false);
        Changefield2.setBorder(null);
        Changefield3.setBounds(100, 140, 200, 30);
        Changefield3.setBackground(null);
        Changefield3.setOpaque(false);
        Changefield3.setBorder(null);
        Changefield4.setBounds(100, 190, 200, 30);
        Changefield4.setBackground(null);
        Changefield4.setOpaque(false);
        Changefield4.setBorder(null);

        Retrievefield1.setBounds(100, 40, 200, 30);
        Retrievefield1.setBackground(null);
        Retrievefield1.setOpaque(false);
        Retrievefield1.setBorder(null);
        Retrievefield2.setBounds(100, 90, 200, 30);
        Retrievefield2.setBackground(null);
        Retrievefield2.setOpaque(false);
        Retrievefield2.setBorder(null);

        JLabel ChangecImg = new JLabel("点击刷新验证码");
        ChangecImg.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
        String s0 = "abcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        ChangecImg.setBounds(50, 230, 200, 50);
        ChangePasswordPanel.add(ChangecImg);
        ChangecImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    for (int i = 0; i < 8; i += 2) {
                        int rand = random.nextInt(36);
                        Changech[i] = s0.charAt(rand);
                        Changech[i + 1] = 32;
                    }
                    ChangecImg.setText(new String(Changech));
                }
            }
        });

        JLabel RetrievecImg = new JLabel("点击刷新验证码");
        RetrievecImg.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
        RetrievecImg.setBounds(50, 130, 200, 50);
        RetrievePasswordPanel.add(RetrievecImg);
        RetrievecImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    for (int i = 0; i < 8; i += 2) {
                        int rand = random.nextInt(36);
                        Retrievech[i] = s0.charAt(rand);
                        Retrievech[i + 1] = 32;
                    }
                    RetrievecImg.setText(new String(Retrievech));
                }
            }
        });

        determine = new JButton("确定");
        returnLog = new JButton("返回登录界面");
        determine.setBounds(30, 360, 60, 50);
        returnLog.setBounds(130, 360, 130, 50);

        determine.addActionListener((e -> PasswordOperation.changePassword(Changefield1.getText().trim(),new String(Changefield2.getPassword()),new String(Changefield3.getPassword()),Changech, Changefield4.getText().trim(),frame)));
        returnLog.addActionListener((e) -> {
            new loginView().setFrame();
            frame.dispose();
        });

        determine.setBorderPainted(false);
        determine.setForeground(Color.BLACK);
        determine.setBackground(Color.lightGray);
        determine.setFocusPainted(false);
        determine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        returnLog.setBorderPainted(false);
        returnLog.setForeground(Color.BLACK);
        returnLog.setBackground(Color.lightGray);
        returnLog.setFocusPainted(false);
        returnLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        determine1 = new JButton("确定");
        returnLog1 = new JButton("返回登录界面");
        determine1.setBounds(30, 360, 60, 50);
        returnLog1.setBounds(130, 360, 130, 50);

        determine1.addActionListener((e) -> PasswordOperation.getPassword(Retrievefield1.getText().trim(),Retrievech,Retrievefield2.getText().trim(),frame));
        returnLog1.addActionListener((e) -> {
            new loginView().setFrame();
            frame.dispose();
        });

        determine1.setBorderPainted(false);
        determine1.setForeground(Color.BLACK);
        determine1.setBackground(Color.lightGray);
        determine1.setFocusPainted(false);
        determine1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        returnLog1.setBorderPainted(false);
        returnLog1.setForeground(Color.BLACK);
        returnLog1.setBackground(Color.lightGray);
        returnLog1.setFocusPainted(false);
        returnLog1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        ChangePasswordPanel.add(returnLog);
        ChangePasswordPanel.add(determine);

        ChangePasswordPanel.add(ChangejLabel1);
        ChangePasswordPanel.add(ChangejLabel2);
        ChangePasswordPanel.add(ChangejLabel3);
        ChangePasswordPanel.add(ChangejLabel4);

        ChangePasswordPanel.add(Changefield1);
        ChangePasswordPanel.add(Changefield2);
        ChangePasswordPanel.add(Changefield3);
        ChangePasswordPanel.add(Changefield4);

        RetrievePasswordPanel.add(RetrievejLabel1);
        RetrievePasswordPanel.add(RetrievejLabel2);
        RetrievePasswordPanel.add(Retrievefield1);
        RetrievePasswordPanel.add(Retrievefield2);
        RetrievePasswordPanel.add(returnLog1);
        RetrievePasswordPanel.add(determine1);


        frame.setResizable(false);
        frame.setVisible(true);


    }
}
