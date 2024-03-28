package com.Interface;

import com.Features.FileOperations;
import com.Features.LoginAuthentication;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class loginView {
    private JFrame frame = null;
    private BackGroundPanel jPanel = null;
    private JLabel prompt = null;
    private static final JTextField Usefield = new JTextField();
    private static final JPasswordField passfield = new JPasswordField();
    private static JCheckBox RememberPassword = new JCheckBox();
    private static JCheckBox automaticLogIn = new JCheckBox();
    private static Properties prop = new Properties();
    private static int aflag = 0;

    public void setFrame() {
        prop = FileOperations.getprop("memory.txt");
        if (!prop.isEmpty()) {
            Usefield.setText(prop.getProperty("username"));
            if (prop.getProperty("RememberPassword.isSelected()").equals("true")) {
                passfield.setText(prop.getProperty("password"));
                RememberPassword = new JCheckBox("记住密码", new ImageIcon(Objects.requireNonNull(loginView.class.getResource("/images/带勾框(1).png"))));
                RememberPassword.setSelected(true);
            } else {
                RememberPassword = new JCheckBox("记住密码", new ImageIcon(Objects.requireNonNull(loginView.class.getResource("/images/框.png"))));
            }
            if (prop.getProperty("automaticLogIn.isSelected()").equals("true")) {
                automaticLogIn = new JCheckBox("自动登录", new ImageIcon(Objects.requireNonNull(loginView.class.getResource("/images/带勾框(1).png"))));
                automaticLogIn.setSelected(true);
                aflag = 1;
            } else {
                automaticLogIn = new JCheckBox("自动登录", new ImageIcon(Objects.requireNonNull(loginView.class.getResource("/images/框.png"))));
                aflag = 0;
            }
        }

        //创建窗口对象
        frame = new JFrame("失物招领系统");
        //得到屏幕像素
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗口大小
        frame.setBounds((width - 485) / 2, (height - 300) / 2, 485, 300);
        //左上角图标
        //frame.setIconImage(ImageIO.read(new File("/images/失物招领 (2).png")));
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/失物招领 (2).png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置关闭方式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //创建一个可以装图片的容器，在把图片放到容器中，在把容器放到窗口
        // JLabel label1 = new JLabel(new ImageIcon(loginView.class.getResource("/images/左图 (2).png")));
        //JLabel label2=new JLabel(new ImageIcon(loginView.class.getResource("/images/背景3.png")));
        //frame.add(label2,"West");
        // frame.add(label1, "West");
        //创建面板对象，用自定义对象

        try {
            jPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/背景.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //在面板上划线，用自定义类
        jPanel.drawLine(110, 82, 310, 82);
        jPanel.drawLine(110, 132, 310, 132);
        //设置面板布局方式
        jPanel.setLayout(null);
        //把面板放在窗口上
        frame.add(jPanel);
        //创建用户密码图标
        JLabel userIcon = new JLabel(new ImageIcon(Objects.requireNonNull(loginView.class.getResource("/images/用户 (1).png"))));
        JLabel passwordIcon = new JLabel(new ImageIcon(Objects.requireNonNull(loginView.class.getResource("/images/密码.png"))));
        //设置触碰图片可显示提示文本
        //创建提示标签
        prompt = new JLabel("");
        jPanel.add(prompt);
        //图标设置鼠标监听，当鼠标移入时显示提示文本
        userIcon.addMouseListener(new MouseAdapter() {
            @Override//鼠标移入事件
            public void mouseEntered(MouseEvent e) {
                //鼠标移入，给标签添加提示内容
                prompt.setText("账号");
                //设置标签位置
                prompt.setBounds(e.getX() + 60, e.getY() + 30, 50, 50);
            }

            @Override//鼠标移出事件
            public void mouseExited(MouseEvent e) {
                //鼠标移出，将标签内容置空
                prompt.setText("");
            }
        });
        passwordIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                prompt.setText("密码");
                prompt.setBounds(e.getX() + 60, e.getY() + 80, 50, 50);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                prompt.setText("");
            }
        });
        //设置触碰图片可显示提示文本
        //UserIcon.setToolTipText("用户");
        //PasswordIcon.setToolTipText("密码");

        //设置图标位置
        userIcon.setBounds(70, 50, 32, 32);
        passwordIcon.setBounds(70, 100, 32, 32);
        //将图标添加到面板中
        jPanel.add(userIcon);
        jPanel.add(passwordIcon);

        //设置位置
        Usefield.setBounds(120, 50, 200, 32);
        //设置背景透明
        Usefield.setOpaque(false);
        //去掉边框
        Usefield.setBorder(null);
        //文本框内字体
        Usefield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        passfield.setBounds(120, 100, 200, 32);
        passfield.setOpaque(false);
        passfield.setBorder(null);
        passfield.setFont(new Font("宋体", Font.PLAIN, 16));
        jPanel.add(Usefield);
        jPanel.add(passfield);

        //设置位置
        RememberPassword.setBounds(320, 100, 150, 30);
        //不显示虚线框
        RememberPassword.setFocusPainted(false);
        //不显示边框
        RememberPassword.setBorderPainted(false);
        //背景设置透明
        RememberPassword.setContentAreaFilled(false);
        //设置字体
        RememberPassword.setFont(new Font("宋体", Font.PLAIN, 14));
        //监听该按钮，来改变按钮前的图片，表示勾选情况
        RememberPassword.addActionListener((e) -> RememberPassword.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(RememberPassword.isSelected() ? "/images/带勾框(1).png" : "/images/框.png")))));
        jPanel.add(RememberPassword);

        automaticLogIn.setBounds(320, 50, 150, 30);
        automaticLogIn.setBorderPainted(false);
        automaticLogIn.setOpaque(false);
        automaticLogIn.setFocusPainted(false);
        automaticLogIn.setFont(new Font("宋体", Font.PLAIN, 14));
        automaticLogIn.addActionListener((e) -> automaticLogIn.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(automaticLogIn.isSelected() ? "/images/带勾框(1).png" : "/images/框.png")))));
        jPanel.add(automaticLogIn);

        JButton found = new JButton("忘记密码");
        //设置字体
        found.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 14));
        //去掉边框
        found.setBorderPainted(false);
        //设置字体颜色
        found.setForeground(Color.blue);
        //光标监听
        found.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //设置按钮透明
        found.setOpaque(false);
        found.setBounds(265, 200, 100, 30);
        jPanel.add(found);

        JButton registered = new JButton("注册账号");
        registered.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 14));
        registered.setBorderPainted(false);
        registered.setForeground(Color.blue);
        registered.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registered.setOpaque(false);
        registered.setBounds(70, 200, 100, 30);

        registered.addActionListener((e) -> {
            new RegisterView().setRegisterView();
            frame.dispose();
        });

        found.addActionListener((e) -> {
            new RetrievePasswordView().setView();
            frame.dispose();
        });

        jPanel.add(registered);

        //创建登录按钮
        JButton login = new JButton("登    录");
        jPanel.add(login);
        //frame.getRootPane().setDefaultButton(login);
        //设置位置
        login.setBounds(112, 150, 200, 40);
        //设置背景颜色
        login.setBackground(new Color(92, 194, 253));
        //设置字体
        login.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
        //设置字体颜色
        login.setForeground(Color.WHITE);
        //边框不显示
        login.setBorderPainted(false);
        //虚线框不显示
        login.setFocusPainted(false);
        //设置光标触碰按钮后变形状
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //设置按钮监听
        login.addActionListener(e -> {
            String username = Usefield.getText().trim();
            String password = new String(passfield.getPassword());
            prop.setProperty("username", username);
            prop.setProperty("password", password);
            prop.setProperty("automaticLogIn.isSelected()", String.valueOf(automaticLogIn.isSelected()));
            prop.setProperty("RememberPassword.isSelected()", String.valueOf(RememberPassword.isSelected()));
            FileOperations.storeprop(prop,"memory.txt");
            int flag = LoginAuthentication.Iscorrect(username, password);
            if (flag == 0) {
                new MainInterface().setMainInterface();
                frame.dispose();
            } else if (flag == 1) {
                new MainInterface().setMainInterface();
                frame.dispose();
            } else {
                Object[] options = new Object[]{"确定"};
                JOptionPane.showOptionDialog(frame, "用户名或密码错误！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            }
        });
        //按enter健等于按按钮
        frame.getRootPane().setDefaultButton(login);

        //显示窗口
        frame.setResizable(false);
        frame.setVisible(true);
        if (aflag == 1) {
            //frame.setVisible(false);
            login.doClick();
        }  //frame.setVisible(true);

    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new loginView().setFrame();
    }
}
