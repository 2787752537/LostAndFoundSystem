package com.ItemOperation;

import com.Features.FileOperations;
import com.Interface.loginView;
import com.MainInterfaceSonface.Articleface;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class HistoryRecordView {
    int width, height;
    BackGroundPanel jPanel = null;
    JButton clean = new JButton("清空历史记录");
    JTextField field = new JTextField(20);
    JButton search = new JButton();
    JButton re = new JButton();
    JScrollPane jsp = new JScrollPane();
    static JPopupMenu pop = new JPopupMenu();
    static JMenuItem Refresh = new JMenuItem("刷新             >");

    public void setHistoryRecordView(JFrame frame) {
        JDialog history = new JDialog(frame, "历史记录", true);
        history.setLayout(null);
        history.getContentPane().setBackground(Color.white);
        //得到屏幕像素
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗口大小
        history.setBounds((width - 370) / 2, (height - 600) / 2, 370, 600);
        //左上角图标
        try {
            history.setIconImage(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/历史记录.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        field.setBounds(230, 0, 90, 30);
        SetTextField(field);
        history.add(field);

        clean.setFocusPainted(false);
        clean.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clean.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 15));
        clean.setBounds(40, 0, 150, 30);
        clean.addActionListener(e -> {
            Object[] options = new Object[]{"确定", "取消"};
            int respose = JOptionPane.showOptionDialog(frame, "确定要清空历史记录吗！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
            if (respose == 0) {
                ArrayList<String> null0 = new ArrayList<>();
                FileOperations.store(null0,frame);
                Refresh.doClick();
            }
        });
        history.add(clean);

        re.setFocusPainted(false);
        re.setBorderPainted(false);
        re.setContentAreaFilled(false);
        re.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        re.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/返 回.png"))));
        re.setBounds(0, 0, 30, 30);
        re.addActionListener(e -> history.dispose());

        history.add(re);

        search.setFocusPainted(false);
        search.setBorderPainted(false);
        search.setContentAreaFilled(false);
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        search.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/搜索 (1).png"))));
        search.setBounds(310, 0, 50, 30);
        search.addActionListener(e -> {
            JPanel searchjpanel = FileOperations.search(field.getText().trim(), "history", pop, frame);
            jsp.setViewportView(searchjpanel);
        });
        history.add(search);

        jsp.setBounds(0, 32, 360, 600);
        jsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        history.add(jsp);

        pop.add(Refresh);
        Refresh.addActionListener(e1 -> {
            jPanel = getjpanel(pop,frame);
            jsp.setViewportView(jPanel);
        });
        Refresh.doClick();
        history.setResizable(false);
        history.setVisible(true);
    }

    public static void SetTextField(JTextField field) {
        field.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        field.setText("请输入物品信息");
        field.setForeground(new Color(151, 157, 156));
        field.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (field.getText().equals("请输入物品信息")) {
                    //将提示文字清空
                    field.setText("");
                    //将提示文字设置为灰色
                    field.setForeground(Color.black);
                }
            }

            public void focusLost(FocusEvent e) {
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (field.getText().equals("")) {
                    //将提示文字设置为灰色
                    field.setForeground(new Color(151, 157, 156));
                    //显示提示文字
                    field.setText("请输入物品信息");
                }
            }
        });
    }

    private BackGroundPanel getjpanel(JPopupMenu pop, JFrame frame) {
        BackGroundPanel jPanel = null;
        try {
            jPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/白色.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BackGroundPanel finalJPanel = jPanel;
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("右击打");
                    pop.show(finalJPanel, e.getX(), e.getY());
                }
            }
        });
        GridLayout gl = new GridLayout(1000, 1, 20, 20);
        jPanel.setLayout(gl);
        ArrayList<String> v = FileOperations.read();
        gl.setRows(v.size()+1);
        for (String s : v) {
            Properties prop = FileOperations.getItemprop(s);
            //System.out.println(prop);
            ImageIcon[] icon = FileOperations.getImage(s, 310, 150);
            jPanel.add(FileOperations.getItemBox(prop, icon, frame, HistoryRecordView.pop));
        }
        return jPanel;
    }
}
