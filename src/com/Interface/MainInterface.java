package com.Interface;

import com.MainInterfaceSonface.*;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainInterface {
    int width, height;
    JFrame frame = new JFrame("失物招领主界面");
    BackGroundPanel left = null;
    JButton notice, article, message, communicate, mine;
    JPanel right = null;
    JPanel NoticePanel, ArticlePanel, MessagePanel, CommunicatePanel, MinePanel;

    public void setMainInterface() {
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/失物招领 (1).png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到屏幕像素
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setBounds((width - 700) / 2, (height - 432) / 2, 700, 432);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            left = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/主背景左1.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        left.setLayout(new FlowLayout());

        notice = new JButton("通知");
        notice.setForeground(Color.red);
        SetProperties(notice);

        Box noticehBox = Box.createHorizontalBox();
        noticehBox.add(Box.createHorizontalStrut(-13));
        noticehBox.add(notice);
        article = new JButton("物品");
        article.setForeground(Color.blue);
        SetProperties(article);

        article.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Box articlehBox = Box.createHorizontalBox();
        articlehBox.add(Box.createHorizontalStrut(50));
        articlehBox.add(article);
        message = new JButton("寻物");
        message.setForeground(Color.blue);
        SetProperties(message);

        Box messagehBox = Box.createHorizontalBox();
        messagehBox.add(Box.createHorizontalStrut(-13));
        messagehBox.add(message);
        communicate = new JButton("交流");
        communicate.setForeground(Color.blue);
        SetProperties(communicate);

        Box communicatehBox = Box.createHorizontalBox();
        communicatehBox.add(Box.createHorizontalStrut(50));
        communicatehBox.add(communicate);
        mine = new JButton("我的");
        mine.setForeground(Color.blue);
        SetProperties(mine);

        Box minehBox = Box.createHorizontalBox();
        minehBox.add(Box.createHorizontalStrut(-13));
        minehBox.add(mine);
        Box xBox = Box.createVerticalBox();
        xBox.add(noticehBox);
        xBox.add(Box.createVerticalStrut(30));
        xBox.add(articlehBox);
        xBox.add(Box.createVerticalStrut(30));
        xBox.add(messagehBox);
        xBox.add(Box.createVerticalStrut(30));
        xBox.add(communicatehBox);
        xBox.add(Box.createVerticalStrut(30));
        xBox.add(minehBox);
        xBox.add(Box.createVerticalStrut(30));
        left.add(xBox);
        frame.add(left, BorderLayout.WEST);

        CardLayout cl = new CardLayout();
        right = new JPanel(cl);
        NoticePanel = Noticeface.Notice();
        ArticlePanel = Articleface.Article(frame);
        MessagePanel = Messageface.Message(frame);
        CommunicatePanel = Communicateface.Communicate();
        MinePanel = Mineface.Mine(frame);

        right.add("1",NoticePanel);
        right.add("2",ArticlePanel);
        right.add("3",MessagePanel);
        right.add("4",CommunicatePanel);
        right.add("5",MinePanel);
        frame.add(right);

        notice.addActionListener(e -> {
            cl.first(right);
            notice.setForeground(Color.red);
            article.setForeground(Color.blue);
            message.setForeground(Color.blue);
            communicate.setForeground(Color.blue);
            mine.setForeground(Color.blue);
        });
        article.addActionListener(e -> {
            cl.show(right,"2");
            notice.setForeground(Color.blue);
            article.setForeground(Color.red);
            message.setForeground(Color.blue);
            communicate.setForeground(Color.blue);
            mine.setForeground(Color.blue);
        });
        message.addActionListener(e -> {
            cl.show(right,"3");
            notice.setForeground(Color.blue);
            article.setForeground(Color.blue);
            message.setForeground(Color.red);
            communicate.setForeground(Color.blue);
            mine.setForeground(Color.blue);
        });
        communicate.addActionListener(e -> {
            cl.show(right,"4");
            notice.setForeground(Color.blue);
            article.setForeground(Color.blue);
            message.setForeground(Color.blue);
            communicate.setForeground(Color.red);
            mine.setForeground(Color.blue);
        });
        mine.addActionListener(e -> {
            cl.last(right);
            notice.setForeground(Color.blue);
            article.setForeground(Color.blue);
            message.setForeground(Color.blue);
            communicate.setForeground(Color.blue);
            mine.setForeground(Color.red);
        });
        frame.setVisible(true);
    }

    private void SetProperties(JButton jButton) {
        jButton.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 23));
        jButton.setBorderPainted(false);
        jButton.setFocusPainted(false);
        jButton.setContentAreaFilled(false);
        jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
