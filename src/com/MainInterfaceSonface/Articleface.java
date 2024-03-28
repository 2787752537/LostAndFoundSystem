package com.MainInterfaceSonface;

import com.Features.FileOperations;
import com.Interface.loginView;
import com.ItemOperation.AddItemView;
import com.ItemOperation.HistoryRecordView;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.Objects;

public class Articleface {
    static BackGroundPanel Bearer = null;
    static JTextField Findfield = new JTextField(20);
    static JPanel Item = new JPanel();
    static JButton search, addItem, historyRecord;
    static JComboBox<String> classification = new JComboBox<>();
    static JScrollPane Itemjsp = new JScrollPane();
    static JPopupMenu pop = new JPopupMenu();
    static JMenuItem Refresh = new JMenuItem("刷新             >");

    public static JPanel Article(JFrame frame) {
        //创建承载面板
        try {
            Bearer = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/上图 (2).png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bearer.setLayout(new BorderLayout());
        //创建显示物品面板
        Item.setBackground(Color.white);
        Item.setLayout(new GridLayout(50, 50, 20, 20));
        Articleface.
                //搜索键
                search = new JButton();
        search.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/搜索.png"))));
        search.setFocusPainted(false);
        search.setBorderPainted(false);
        search.setContentAreaFilled(false);
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        search.addActionListener(e -> {
            JPanel searchjpanel = FileOperations.search(Findfield.getText().trim(), "Articleface", pop, frame);
            Itemjsp.setViewportView(searchjpanel);
        });
        //创造搜索文本框
        Findfield.setOpaque(false);
        Findfield.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        Findfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        Findfield.setText("请输入物品信息");
        Findfield.setForeground(new Color(151, 157, 156));
        Findfield.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (Findfield.getText().equals("请输入物品信息")) {
                    //将提示文字清空
                    Findfield.setText("");
                    //将提示文字设置为灰色
                    Findfield.setForeground(Color.black);
                }
            }

            public void focusLost(FocusEvent e) {
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (Findfield.getText().equals("")) {
                    //将提示文字设置为灰色
                    Findfield.setForeground(new Color(151, 157, 156));
                    //显示提示文字
                    Findfield.setText("请输入物品信息");
                }
            }
        });
        //创建增加物品，历史记录按钮
        addItem = new JButton("增加物品");
        addItem.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 15));
        addItem.setBorderPainted(false);
        addItem.setForeground(Color.black);
        addItem.setOpaque(false);
        addItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addItem.addActionListener(e -> new AddItemView().setAddItemView(frame, Refresh));
        historyRecord = new JButton("历史记录");
        historyRecord.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 15));
        historyRecord.setBorderPainted(false);
        historyRecord.setForeground(Color.black);
        historyRecord.setOpaque(false);
        historyRecord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        historyRecord.addActionListener(e -> new HistoryRecordView().setHistoryRecordView(frame));

        classification.addItem("全部");
        classification.addItem("卡");
        classification.addItem("数码");
        classification.addItem("衣物");
        classification.addItem("图书、证书");
        classification.addItem("其他");
        classification.setFocusable(false);
        classification.setBackground(Color.white);
        classification.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 15));
        classification.setForeground(Color.black);
        classification.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Box on1 = Box.createHorizontalBox();
        on1.add(classification);
        on1.add(Box.createHorizontalStrut(20));
        on1.add(historyRecord);
        on1.add(Box.createHorizontalStrut(20));
        on1.add(addItem);
        Box on2 = Box.createHorizontalBox();
        on2.add(Findfield);
        on2.add(Box.createHorizontalStrut(3));
        on2.add(search);
        Box on = Box.createHorizontalBox();
        on.add(on1);
        on.add(Box.createHorizontalGlue());
        on.add(on2);
        Bearer.add(on, BorderLayout.NORTH);

        Refresh.addActionListener(e -> {
            //将文件删除，重新从数据库获取物品信息
            FileOperations.deleteFile();
            FileOperations.createFile();
            //将信息读到新面板
            String Selection = Objects.requireNonNull(classification.getSelectedItem()).toString().trim();
            Item = FileOperations.createItemJpanel(Selection, frame, pop);
            Itemjsp.setViewportView(Item);
        });
        pop.add(Refresh);
        Refresh.doClick();
        classification.addActionListener(e -> {
            //根据选项得到相应面板
            String Selection = Objects.requireNonNull(classification.getSelectedItem()).toString().trim();
            Item = FileOperations.createItemJpanel(Selection, frame, pop);
            Itemjsp.setViewportView(Item);
        });
        Itemjsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Itemjsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Bearer.add(Itemjsp);
        return Bearer;
    }
}
