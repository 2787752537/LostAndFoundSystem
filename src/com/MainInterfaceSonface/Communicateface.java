package com.MainInterfaceSonface;

import com.Interface.loginView;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Communicateface {
    static BackGroundPanel jPanel = null;

    public static JPanel Communicate() {
        try {
            jPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jPanel.setLayout(new BorderLayout());

        JPanel ContactPersonPanel = new JPanel();
        ContactPersonPanel.setBackground(Color.white);
        ContactPersonPanel.setLayout(new GridLayout(1000, 1, 20, 10));
        JScrollPane ContactPersonjsp = new JScrollPane();
        ContactPersonjsp.setViewportView(ContactPersonPanel);
        JPanel newsPanel = new JPanel();
        newsPanel.setBackground(Color.white);
        newsPanel.setLayout(new GridLayout(1000, 1, 20, 10));
        JScrollPane newsjsp = new JScrollPane();
        newsjsp.setViewportView(newsPanel);

        JTextArea newsfield = new JTextArea();
        newsfield.setLineWrap(true);        //激活自动换行功能
        newsfield.setWrapStyleWord(true);            // 激活断行不断字功能
        newsfield.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JButton sendButton = new JButton("发送(Enter)");
        sendButton.setFocusPainted(false);
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sendButton.addActionListener(e -> {

        });

        JPanel newsjPanelCarrier = new JPanel(new BorderLayout());
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalGlue());
        hBox.add(sendButton);
        Box vBox = Box.createVerticalBox();
        vBox.add(newsfield);
        vBox.add(hBox);
        vBox.setPreferredSize(new Dimension(1,100));
        newsjPanelCarrier.add(vBox,BorderLayout.SOUTH);
        newsjPanelCarrier.add(newsjsp);
        ContactPersonjsp.setPreferredSize(new Dimension(130,100));
        Communicateface.jPanel.add(ContactPersonjsp,BorderLayout.WEST);
        Communicateface.jPanel.add(newsjPanelCarrier);

        for (int i = 0; i < 1000; i++) {

            JPanel jPanel3 = new JPanel();
            jPanel3.setBackground(Color.white);

            JButton jButton = new JButton(" >");
            jButton.setForeground(Color.black);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(false);
            jButton.setContentAreaFilled(false);
            jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jButton.addActionListener(e -> System.out.println("按钮联系人" + e.getActionCommand() + "被点击"));
            JLabel jLabel3 = new JLabel("联系人" + i);
            jPanel3.setLayout(new BorderLayout());
            jPanel3.add(jButton, BorderLayout.EAST);
            jPanel3.add(jLabel3, BorderLayout.WEST);
            ContactPersonPanel.add(jPanel3);
        }
        for (int i = 0; i < 1000; i++) {

            JPanel jPanel3 = new JPanel();
            jPanel3.setBackground(new Color(18, 183, 245));
            JLabel jLabel3 = new JLabel("昵称" + i + ": " + "消息" + i);
            jLabel3.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 20));
            jPanel3.setLayout(new BorderLayout());
            jPanel3.add(jLabel3, BorderLayout.WEST);
            newsPanel.add(jPanel3);
        }

        return Communicateface.jPanel;
    }

}
