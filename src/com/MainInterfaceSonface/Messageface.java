package com.MainInterfaceSonface;

import com.ItemOperation.HistoryRecordView;
import com.NoticeOperation.AddNotice;
import com.component.BackGroundPanel;
import com.sqlConnection.sqlConnection;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Messageface {
    static JPanel find = new JPanel();
    static BackGroundPanel information = null;
    static JTextField field = new JTextField(20);
    static JButton IncreaseNotice = new JButton("增加启事 >");
    static JPopupMenu pop = new JPopupMenu();
    static JMenuItem Refresh = new JMenuItem("刷新             >");
    static JButton search = new JButton();
    static JScrollPane jsp = new JScrollPane();

    public static JPanel Message(JFrame frame) {
        find.setLayout(new BorderLayout());

        field.setOpaque(false);
        HistoryRecordView.SetTextField(field);
        IncreaseNotice.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 15));
        IncreaseNotice.setFocusPainted(false);
        IncreaseNotice.setForeground(Color.black);
        IncreaseNotice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        IncreaseNotice.addActionListener(e -> new AddNotice().setAddNotice(frame,Refresh));
        search.setFocusPainted(false);
        search.setBorderPainted(false);
        search.setContentAreaFilled(false);
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        search.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/搜索.png"))));
        search.addActionListener(e -> {
            String text = field.getText().trim();
            String sql;
            if (!text.equals("请输入物品信息")) {
                sql = "select * from find where Item_name ='" + text + "' or " + "time ='" + text + "' or " + "Lost_place ='" + text +
                        "' or " + "contact_details ='" + text + "' or " + "Details ='" + text + "' or " +"publisher ='" + text +"'";
                //System.out.println(sql);
            } else {
                sql = "select * from find";
                //System.out.println("全部");
            }
            information = sqlConnection.getNoticejpanel(sql,frame, Refresh);
            jsp.setViewportView(information);
        });
        Box on1 = Box.createHorizontalBox();
        on1.add(IncreaseNotice);
        Box on2 = Box.createHorizontalBox();
        on2.add(field);
        on2.add(Box.createHorizontalStrut(3));
        on2.add(search);
        Box on = Box.createHorizontalBox();
        on.add(on1);
        on.add(Box.createHorizontalGlue());
        on.add(on2);
        find.add(on, BorderLayout.NORTH);
        pop.add(Refresh);
        Refresh.addActionListener(e -> search.doClick());
        search.doClick();
        jsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        find.add(jsp);


        return find;
    }
}
