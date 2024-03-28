package com.NoticeOperation;

import com.Features.FileOperations;
import com.Features.PasswordOperation;
import com.Interface.loginView;
import com.component.BackGroundPanel;
import com.sqlConnection.sqlConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class AddNotice {
    int width, height;
    BackGroundPanel jPanel = null;

    public void setAddNotice(JFrame frame, JMenuItem refresh) {
        JDialog jDialog = new JDialog(frame, "添加启事", true);
        jDialog.setLayout(null);
        //得到屏幕像素
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗口大小
        jDialog.setBounds((width - 500) / 2, (height - 300) / 2, 500, 300);
        //左上角图标
        try {
            jDialog.setIconImage(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/寻物启事.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, jDialog.getWidth(), jDialog.getHeight());
        jDialog.add(jPanel);

        JTextField NameOfLostItemfield = new JTextField(20);
        NameOfLostItemfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        JTextArea NoticeDetailsfield = new JTextArea();
        NoticeDetailsfield.setLineWrap(true);        //激活自动换行功能
        NoticeDetailsfield.setWrapStyleWord(true);            // 激活断行不断字功能
        NoticeDetailsfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 18));
        NoticeDetailsfield.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        JScrollPane jsp =new JScrollPane();
        jsp.setViewportView(NoticeDetailsfield);
        JTextField yearfield = new JTextField(20);
        yearfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        JTextField monthfield = new JTextField(20);
        monthfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        JTextField dayfield = new JTextField(20);
        dayfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        JTextField hourfield = new JTextField(20);
        hourfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        JTextField LostPlacefield = new JTextField(20);
        LostPlacefield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        JTextField contactDetailsfield = new JTextField(20);
        contactDetailsfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));

        JLabel NameOfLostItem = new JLabel("丢失物品名称 :");
        JLabel NoticeDetails = new JLabel("启事详情");
        JLabel LostTime = new JLabel("丢失时间:");
        JLabel year = new JLabel("年");
        JLabel month = new JLabel("月");
        JLabel day = new JLabel("日");
        JLabel hour = new JLabel("时");
        JLabel LostPlace = new JLabel("丢失地:");
        JLabel contactDetails = new JLabel("联系方式:");
        Box NameOfLostItemhBox = Box.createHorizontalBox();
        NameOfLostItemhBox.add(NameOfLostItem);
        NameOfLostItemhBox.add(Box.createHorizontalStrut(10));
        NameOfLostItemhBox.add(NameOfLostItemfield);
        Box LostTimehBox = Box.createHorizontalBox();
        LostTimehBox.add(LostTime);
        LostTimehBox.add(Box.createHorizontalStrut(10));
        LostTimehBox.add(yearfield);
        LostTimehBox.add(Box.createHorizontalStrut(3));
        LostTimehBox.add(year);
        LostTimehBox.add(Box.createHorizontalStrut(3));
        LostTimehBox.add(monthfield);
        LostTimehBox.add(Box.createHorizontalStrut(3));
        LostTimehBox.add(month);
        LostTimehBox.add(Box.createHorizontalStrut(3));
        LostTimehBox.add(dayfield);
        LostTimehBox.add(Box.createHorizontalStrut(3));
        LostTimehBox.add(day);
        LostTimehBox.add(hourfield);
        LostTimehBox.add(Box.createHorizontalStrut(3));
        LostTimehBox.add(hour);
        Box LostPlacehBox = Box.createHorizontalBox();
        LostPlacehBox.add(LostPlace);
        LostPlacehBox.add(Box.createHorizontalStrut(22));
        LostPlacehBox.add(LostPlacefield);
        Box contactDetailshBox = Box.createHorizontalBox();
        contactDetailshBox.add(contactDetails);
        contactDetailshBox.add(Box.createHorizontalStrut(10));
        contactDetailshBox.add(contactDetailsfield);
        Box vBox = Box.createVerticalBox();
        vBox.add(NameOfLostItemhBox);
        vBox.add(LostTimehBox);
        vBox.add(LostPlacehBox);
        vBox.add(contactDetailshBox);
        vBox.setBounds(0, 0, 300, 130);
        jPanel.add(vBox);
        Box NoticeDetailsvBox = Box.createVerticalBox();
        Box hBox = Box.createHorizontalBox();
        hBox.add(NoticeDetails);
        NoticeDetailsvBox.add(hBox);
        NoticeDetailsvBox.add(Box.createVerticalStrut(3));
        NoticeDetailsvBox.add(jsp);
        NoticeDetailsvBox.setBounds(10, 140, 450, 120);
        jPanel.add(NoticeDetailsvBox);

        JButton Add = new JButton("添加");
        Add.setBounds(380, 20, 100, 30);
        Add.setForeground(Color.black);
        Add.setBackground(new Color(182, 213, 227));
        Add.setFocusPainted(false);
        Add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Add.addActionListener(e -> {
            try {
                Integer.parseInt(yearfield.getText().trim());
                Integer.parseInt(monthfield.getText().trim() );
                Integer.parseInt(dayfield.getText().trim());
                Integer.parseInt(hourfield.getText().trim());
            } catch (NumberFormatException e1) {
                Object[] options = new Object[]{"确定"};
                int respose = JOptionPane.showOptionDialog(frame, "请输入正确时间！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                if (respose == 0) {
                    return ;
                }
            }
            String time = yearfield.getText().trim() + "年" + monthfield.getText().trim() + "月" + dayfield.getText().trim() + "日" +
                    hourfield.getText().trim() + "时";
            String sql = "insert into find (Item_name,time,Lost_place,contact_details,Details,publisher)values('" +
                    NameOfLostItemfield.getText().trim() + "','" + time + "','" + LostPlacefield.getText().trim() + "','"
                    + contactDetailsfield.getText().trim() + "','" + contactDetailsfield.getText().trim() + "','" + FileOperations.getprop("memory.txt").getProperty("username") +"')";
            sqlConnection sqlcon = new sqlConnection();
            int i = sqlcon.sqlUpdate(sql, frame);
            sqlcon.closeSqlConn();
            if(i==1){
                Object[] options = new Object[]{"确定"};
                int respose = 0;
                try {
                    respose = JOptionPane.showOptionDialog(frame, "添加成功！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(ImageIO.read(Objects.requireNonNull(PasswordOperation.class.getResource("/images/创建成功.png")))), options, null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if (respose == 0) {
                    refresh.doClick();
                    jDialog.dispose();
                }
            }
        });

        JButton cancel = new JButton("取消");
        cancel.setBounds(380, 80, 100, 30);
        SetProperties(jDialog, Add, cancel, jPanel);

        jDialog.setResizable(false);
        jDialog.setVisible(true);
    }

    public static void SetProperties(JDialog jDialog, JButton add, JButton cancel, BackGroundPanel jPanel) {
        cancel.setForeground(Color.black);
        cancel.setBackground(new Color(182, 213, 227));
        cancel.setFocusPainted(false);
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancel.addActionListener(e -> jDialog.dispose());

        jPanel.add(add);
        jPanel.add(cancel);
    }
}
