package com.Interface;

import com.Features.AddUser;
import com.MainInterfaceSonface.Mineface;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class RegisterView {
    int width, height;
    //创建窗口对象
    JFrame frame = new JFrame("注册");
    BackGroundPanel jPanel = null;
    JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    JTextField IdText;
    JPasswordField passText1, passText2;
    JTextField VerificationCode;
    JRadioButton male, female, secret;
    char[] ch = new char[8];
    JButton registered, returnLog;


    public void setRegisterView() {
        //设置面板布局方式
        frame.setLayout(null);
        //设置窗口大小
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setBounds((width - 309) / 2, (height - 500) / 2, 309, 500);
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/注册.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setResizable(false);

        //设置面板信息
        try {
            jPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/注册背景.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jPanel.drawLine(100, 70, 250, 70);
        jPanel.drawLine(100, 120, 250, 120);
        jPanel.drawLine(100, 170, 250, 170);
        jPanel.drawLine(100, 270, 250, 270);
        jPanel.setBounds(0, 0, 309, 500);
        jPanel.setLayout(null);

        //面板上标签
        jLabel1 = new JLabel("账  号:");
        jLabel2 = new JLabel("密  码:");
        jLabel3 = new JLabel("确认密码:");
        jLabel4 = new JLabel("性  别:");
        jLabel5 = new JLabel("验证码:");
        jLabel1.setBounds(50, 30, 100, 50);
        jLabel2.setBounds(50, 80, 100, 50);
        jLabel3.setBounds(33, 130, 100, 50);
        jLabel4.setBounds(50, 180, 100, 50);
        jLabel5.setBounds(43, 230, 100, 50);

        //面板上文本框
        IdText = new JTextField(20);
        IdText.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        passText1 = new JPasswordField(20);
        passText1.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 13));
        passText2 = new JPasswordField(20);
        passText2.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 13));
        VerificationCode = new JTextField(20);
        IdText.setBounds(100, 40, 200, 30);
        //去边框
        IdText.setBorder(null);
        //设置透明
        IdText.setOpaque(false);

        passText1.setBounds(100, 90, 200, 30);
        passText1.setOpaque(false);
        passText1.setBorder(null);

        passText2.setBounds(100, 140, 200, 30);
        passText2.setOpaque(false);
        passText2.setBorder(null);

        VerificationCode.setBounds(100, 240, 200, 30);
        VerificationCode.setOpaque(false);
        VerificationCode.setBorder(null);

        male = new JRadioButton("男", false);
        female = new JRadioButton("女", false);
        secret = new JRadioButton("保密", true);
        male.setBounds(90, 193, 57, 30);
        //设置透明
        male.setOpaque(false);
        //去虚线框
        male.setFocusPainted(false);
        //设置文字前的图片
        male.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource("/images/空心点.png"))));
        //
        male.addActionListener((e) -> {
            Mineface.setButton(male, female, secret);
        });

        female.setBounds(150, 193, 57, 30);
        female.setOpaque(false);
        female.setFocusPainted(false);
        female.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource("/images/空心点.png"))));
        female.addActionListener((e) -> {
            Mineface.setButton(male, female, secret);
        });

        secret.setBounds(200, 193, 70, 30);
        secret.setOpaque(false);
        secret.setFocusPainted(false);
        secret.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource("/images/选中点.png"))));
        secret.addActionListener((e) -> {
            Mineface.setButton(male, female, secret);
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        bg.add(secret);

        JLabel cImg = new JLabel("点击刷新验证码");
        cImg.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
        String s0 = "abcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        cImg.setBounds(50, 280, 200, 50);
        jPanel.add(cImg);
        cImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    for (int i = 0; i < 8; i += 2) {
                        int rand = random.nextInt(36);
                        ch[i] = s0.charAt(rand);
                        ch[i + 1] = 32;
                    }
                    cImg.setText(new String(ch));
                }
            }
        });

        registered = new JButton("注册");
        registered.setBounds(30, 360, 60, 50);
        //去边框
        registered.setBorderPainted(false);
        //字体颜色
        registered.setForeground(Color.BLACK);
        //背景颜色
        registered.setBackground(new Color(255, 255, 255));
        //去虚线框
        registered.setFocusPainted(false);
        //光标改变
        registered.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registered.addActionListener((e) -> AddUser.isAddUser(IdText.getText().trim(), new String(passText1.getPassword()),
                new String(passText2.getPassword()), bg, ch, VerificationCode.getText().trim(), frame));

        returnLog = new JButton("返回登录界面");
        returnLog.setBounds(130, 360, 130, 50);
        returnLog.setBorderPainted(false);
        returnLog.setForeground(Color.BLACK);
        returnLog.setBackground(new Color(255, 255, 255));
        returnLog.setFocusPainted(false);
        returnLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnLog.addActionListener((e) -> {
            new loginView().setFrame();
            frame.dispose();
        });


        jPanel.add(returnLog);
        jPanel.add(registered);

        jPanel.add(male);
        jPanel.add(female);
        jPanel.add(secret);

        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jPanel.add(jLabel4);
        jPanel.add(jLabel5);

        jPanel.add(IdText);
        jPanel.add(passText1);
        jPanel.add(passText2);
        jPanel.add(VerificationCode);

        //设置关闭方式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //把面板放在窗口上
        frame.add(jPanel);
        //显示窗口
        frame.setVisible(true);
    }
}
