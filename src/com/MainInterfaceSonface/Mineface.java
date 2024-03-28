package com.MainInterfaceSonface;

import com.Features.AddUser;
import com.Features.FileOperations;
import com.Features.PasswordOperation;
import com.Interface.loginView;
import com.ItemOperation.modifyItemView;
import com.component.BackGroundPanel;
import com.sqlConnection.sqlConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Mineface {
    static Properties prop = FileOperations.getuserprop();
    static JRadioButton male = new JRadioButton("男", prop.getProperty("sex").equals("男"));
    static JRadioButton female = new JRadioButton("女", prop.getProperty("sex").equals("女"));
    static JRadioButton secret = new JRadioButton("保密", prop.getProperty("sex").equals("保密"));
    static BackGroundPanel MinejPanel, menu, nickname, Sex, changePassword, FontSettings, feedback;
    static JPopupMenu pop = new JPopupMenu();
    static JMenuItem Refresh = new JMenuItem("刷新             >");
    static JMenuItem delete = new JMenuItem("删除             >");
    static JMenuItem modify = new JMenuItem("修改             >");

    public static JPanel Mine(JFrame frame) {
        JButton[] jButtons = new JButton[7];
        try {
            MinejPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MinejPanel.setLayout(new BorderLayout());

        JScrollPane articlejsp = new JScrollPane();
        JScrollPane noticejsp = new JScrollPane();
        pop.add(Refresh);
        pop.add(delete);
        pop.add(modify);
        modify.addActionListener(e -> new modifyItemView().setmodifyItemView(frame, Refresh, FileOperations.getprop("Selected.txt").getProperty("Selected")));
        Refresh.addActionListener(e -> {
            JPanel articlejPanel = FileOperations.search(prop.getProperty("username"), "Mine", pop, frame);
            articlejsp.setViewportView(articlejPanel);
            String sql1="select * from find where publisher ='" +prop.getProperty("username") + "'";
            JPanel noticejPanel = sqlConnection.getNoticejpanel(sql1,frame,Refresh);
            noticejsp.setViewportView(noticejPanel);
        });
        Refresh.doClick();
        delete.addActionListener(e -> {
            String selected = FileOperations.getprop("Selected.txt").getProperty("Selected");
            System.out.println(selected);
            Properties getprop = FileOperations.getItemprop(selected);
            String sql = "DELETE FROM article WHERE index_article = ?";
            if (prop.getProperty("Identity").equals("管理员")) {
                PromptBox(frame, selected, getprop, sql);
            } else if (prop.getProperty("username").equals(getprop.getProperty("publisher"))) {
                PromptBox(frame, selected, getprop, sql);
            } else {
                Object[] options = new Object[]{"确定"};
                JOptionPane.showOptionDialog(frame, "你不是发布者,无法执行此操作！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
            }
        });

        JLabel articlejLabel = new JLabel("我发布的物品");
        JLabel noticejLabel = new JLabel("我发布的启事");

        Box rightvBox = Box.createVerticalBox();
        Box articlehBox = Box.createHorizontalBox();
        articlehBox.add(articlejLabel);
        Box noticehBox = Box.createHorizontalBox();
        noticehBox.add(noticejLabel);
        rightvBox.add(articlehBox);
        rightvBox.add(articlejsp);
        rightvBox.add(noticehBox);
        rightvBox.add(noticejsp);
        MinejPanel.add(rightvBox);

        JPanel Carrier = new JPanel();
        try {
            menu = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/我的背景.png"))));
            nickname = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
            Sex = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
            changePassword = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
            FontSettings = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
            feedback = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu.setLayout(new GridLayout(7, 1, 10, 3));
        nickname.setLayout(new GridLayout(15, 1, 10, 3));
        Sex.setLayout(new GridLayout(15, 1, 10, 3));
        changePassword.setLayout(new GridLayout(15, 1, 10, 3));
        FontSettings.setLayout(new GridLayout(15, 1, 10, 3));
        feedback.setLayout(new BorderLayout());

        CardLayout cardLayout = new CardLayout();
        Carrier.setLayout(cardLayout);
        Carrier.add("menu", menu);
        Carrier.add("nickname", nickname);
        Carrier.add("Sex", Sex);
        Carrier.add("changePassword", changePassword);
        Carrier.add("FontSettings", FontSettings);
        Carrier.add("feedback", feedback);
        Carrier.setPreferredSize(new Dimension(130,1));
        MinejPanel.add(Carrier, BorderLayout.WEST);

        JButton re = new JButton("返回");
        re.setFocusPainted(false);
        re.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        re.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/返 回.png"))));
        re.setBounds(0, 0, 30, 30);
        re.addActionListener(e -> cardLayout.first(Carrier));
        JLabel NicknamejLabel = new JLabel("昵 称:");
        JTextField Nicknamefield = new JTextField(20);
        Nicknamefield.setText(prop.getProperty("nickname"));
        Nicknamefield.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        JButton[] confirms = new JButton[4];
        for (int i = 0; i < 4; i++) {
            confirms[i] = new JButton("确认");
            confirms[i].setForeground(Color.black);
            confirms[i].setFocusPainted(false);
            confirms[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        JButton NicknamejButton = new JButton("昵称 >");
        NicknamejButton.addActionListener(e -> {
            nickname.add(NicknamejLabel);
            nickname.add(Nicknamefield);
            nickname.add(confirms[0]);
            nickname.add(re);
            cardLayout.show(Carrier, "nickname");
        });
        jButtons[0] = NicknamejButton;
        confirms[0].addActionListener(e -> {
            String nickname = Nicknamefield.getText().trim();
            modify(nickname, "nickname", prop, frame);
            cardLayout.first(Carrier);
        });

        JLabel SexjLabel = new JLabel("性 别:");
        male.setOpaque(false);
        female.setOpaque(false);
        secret.setOpaque(false);
        male.setFocusPainted(false);
        female.setFocusPainted(false);
        secret.setFocusPainted(false);
        getselect();

        male.addActionListener((e) -> getselect());
        female.addActionListener((e) -> getselect());
        secret.addActionListener((e) -> getselect());
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        bg.add(secret);

        JButton SexjButton = new JButton("性别 >");
        SexjButton.addActionListener(e -> {
            Sex.add(SexjLabel);
            Sex.add(male);
            Sex.add(female);
            Sex.add(secret);
            Sex.add(confirms[1]);
            Sex.add(re);
            cardLayout.show(Carrier, "Sex");
        });
        jButtons[1] = SexjButton;
        confirms[1].addActionListener(e -> {
            String sex = AddUser.getSelectedButtonText(bg);
            modify(sex, "sex", prop, frame);
            cardLayout.first(Carrier);
        });

        JLabel oldPassword = new JLabel("原密码:");
        JLabel newPassword = new JLabel("新密码:");
        JLabel confirmPassword = new JLabel("确认密码:");

        JTextField oldPasswordfield = new JTextField(20);
        JPasswordField newPasswordfield = new JPasswordField(20);
        JPasswordField confirmPasswordfield = new JPasswordField(20);
        oldPasswordfield.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        newPasswordfield.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        confirmPasswordfield.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JButton changePasswordjButton = new JButton("修改密码 >");
        changePasswordjButton.addActionListener(e -> {
            changePassword.add(oldPassword);
            changePassword.add(oldPasswordfield);
            changePassword.add(newPassword);
            changePassword.add(newPasswordfield);
            changePassword.add(confirmPassword);
            changePassword.add(confirmPasswordfield);
            changePassword.add(confirms[2]);
            changePassword.add(re);
            cardLayout.show(Carrier, "changePassword");
        });
        jButtons[2] = changePasswordjButton;
        confirms[2].addActionListener(e -> {
            String old = oldPasswordfield.getText().trim();
            String newp = new String(newPasswordfield.getPassword());
            String confirm = new String(confirmPasswordfield.getPassword());
            if (old.equals(prop.getProperty("password"))) {
                if (newp.equals(confirm)) {
                    int i = modify(newp, "password", prop, frame);
                    if (i == 1) {
                        oldPasswordfield.setText("");
                        newPasswordfield.setText("");
                        confirmPasswordfield.setText("");
                        cardLayout.first(Carrier);
                    }
                } else {
                    Object[] options = new Object[]{"确定"};
                    JOptionPane.showOptionDialog(frame, "两次输入密码不相等！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);

                }
            } else {
                Object[] options = new Object[]{"确定"};
                JOptionPane.showOptionDialog(frame, "原密码错误！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);

            }
        });

        JLabel FontSettingsjLabel = new JLabel("等待更新");
        JButton FontSettingsjButton = new JButton("字体设置 >");
        FontSettingsjButton.addActionListener(e -> {
            FontSettings.add(FontSettingsjLabel);
            FontSettings.add(re);
            cardLayout.show(Carrier, "FontSettings");
        });
        jButtons[3] = FontSettingsjButton;

        JTextArea jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);        //激活自动换行功能
        jTextArea.setWrapStyleWord(true);            // 激活断行不断字功能
        jTextArea.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        jTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jTextArea.setText("请详细描述你的问题和建议,我们将及时跟进解决");
        jTextArea.setForeground(new Color(151, 157, 156));
        jTextArea.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (jTextArea.getText().equals("请详细描述你的问题和建议,我们将及时跟进解决")) {
                    //将提示文字清空
                    jTextArea.setText("");
                    //将提示文字设置为灰色
                    jTextArea.setForeground(new Color(151, 157, 156));
                }
            }

            public void focusLost(FocusEvent e) {
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (jTextArea.getText().equals("")) {
                    //将提示文字设置为灰色
                    jTextArea.setForeground(new Color(151, 157, 156));
                    //显示提示文字
                    jTextArea.setText("请详细描述你的问题和建议,我们将及时跟进解决");
                }
            }
        });
        JScrollPane jTextAreajsp = new JScrollPane();
        jTextAreajsp.setViewportView(jTextArea);
        jTextAreajsp.setPreferredSize(new Dimension(1,250));
        JButton feedbackjButton = new JButton("问题反馈 >");
        feedbackjButton.addActionListener(e -> {
            feedback.add(re,BorderLayout.NORTH);
            feedback.add(confirms[3],BorderLayout.SOUTH);
            feedback.add(jTextAreajsp);
            cardLayout.show(Carrier, "feedback");
        });
        jButtons[4] = feedbackjButton;

        JButton signOutjButton = new JButton("退出登录 >");
        jButtons[5] = signOutjButton;
        signOutjButton.addActionListener(e -> {
            Object[] options = new Object[]{"确定", "取消"};
            int respose = JOptionPane.showOptionDialog(frame, "确定要退出吗！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/问号.png"))), options, null);
            if (respose == 0) {
                Properties mprop = FileOperations.getprop("memory.txt");
                mprop.setProperty("automaticLogIn.isSelected()", "false");
                FileOperations.storeprop(mprop, "memory.txt");
                new loginView().setFrame();
                frame.dispose();
            }
        });

        JButton LogoutjButton = new JButton("注销账号 >");
        LogoutjButton.addActionListener(e -> {
            Object[] options = new Object[]{"确定", "取消"};
            int respose = JOptionPane.showOptionDialog(frame, "确定要注销吗！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/问号.png"))), options, null);
            if (respose == 0) {
                Properties mprop = FileOperations.getprop("memory.txt");
                mprop.setProperty("automaticLogIn.isSelected()", "false");
                FileOperations.storeprop(mprop, "memory.txt");
                sqlConnection sqlcon = new sqlConnection();
                String sql = "delete from user1 where username ='" + prop.getProperty("username") + "'";
                sqlcon.sqlUpdate(sql, frame);
                sqlcon.closeSqlConn();
                new loginView().setFrame();
                frame.dispose();
            }
        });
        jButtons[6] = LogoutjButton;
        for (JButton jButton : jButtons) {
            jButton.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 15));
            jButton.setBorderPainted(false);
            jButton.setFocusPainted(false);
            jButton.setForeground(Color.black);
            jButton.setContentAreaFilled(false);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        menu.add(NicknamejButton);
        menu.add(SexjButton);
        menu.add(changePasswordjButton);
        menu.add(FontSettingsjButton);
        menu.add(feedbackjButton);
        menu.add(signOutjButton);
        menu.add(LogoutjButton);

        return MinejPanel;
    }

    private static void PromptBox(JFrame frame, String selected, Properties getprop, String sql) {
        Object[] options = new Object[]{"确定", "取消"};
        int respose = JOptionPane.showOptionDialog(frame, "确认要删除" + getprop.getProperty("Item_name") + "吗?", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/问号.png"))), options, null);
        if (respose == 0) {
            sqlConnection.deletearticle(sql, selected, frame);
            Refresh.doClick();
        }
    }

    private static void getselect() {
        setButton(male, female, secret);
    }

    public static void setButton(JRadioButton male, JRadioButton female, JRadioButton secret) {
        male.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(male.isSelected() ? "/images/选中点.png" : "/images/空心点.png"))));
        female.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(female.isSelected() ? "/images/选中点.png" : "/images/空心点.png"))));
        secret.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(secret.isSelected() ? "/images/选中点.png" : "/images/空心点.png"))));
    }

    private static int modify(String content, String Header, Properties prop, JFrame frame) {
        String sql = "update user1 set " + Header + " ='" + content + "' where username ='" + prop.getProperty("username") + "'";
        sqlConnection sqlcon = new sqlConnection();
        int i = sqlcon.sqlUpdate(sql, frame);
        if (i == 1) {
            prop.setProperty(Header, content);
            Object[] options = new Object[]{"确定"};
            try {
                JOptionPane.showOptionDialog(frame, "修改成功!", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(ImageIO.read(Objects.requireNonNull(PasswordOperation.class.getResource("/images/创建成功.png")))), options, options[0]);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        sqlcon.closeSqlConn();
        return 1;
    }
}
