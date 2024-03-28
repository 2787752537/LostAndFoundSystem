package com.ItemOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bigimage {
    static int chang = 400, kuang = 400;

    public static void showimage(int w, int h, JFrame frame, ImageIcon bi) {
        JPanel jPanel = new JPanel();
        chang = w;
        kuang = h;
        JDialog bigimage = new JDialog(frame, "", true);
        bigimage.setUndecorated(true);
        //得到屏幕像素
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗口大小
        bigimage.setBounds((width - w) / 2, (height - h) / 2, w, h);
        JLabel jLabel = new JLabel(bi);
        jPanel.add(jLabel);
        bigimage.add(jPanel);
        bigimage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON1){
                    bigimage.dispose();
                }
            }
        });
        bigimage.addMouseWheelListener(e -> {
            if (e.getWheelRotation() == 1) {
                chang += 1;
                kuang += 1;
                bigimage.setBounds((width - chang) / 2, (height - kuang) / 2, chang, kuang);
                Image image = bi.getImage();
                image = image.getScaledInstance(chang, kuang, Image.SCALE_FAST);
                ImageIcon icon = new ImageIcon(image);
                jLabel.setIcon(icon);
                bigimage.add(jPanel);
                bigimage.validate();
                System.out.println("滑轮向前。。。。");
            }
            if (e.getWheelRotation() == -1) {
                chang -= 1;
                kuang -= 1;
                bigimage.setBounds((width - chang) / 2, (height - kuang) / 2, chang, kuang);
                bigimage.setSize(chang, kuang);
                Image image = bi.getImage();
                image = image.getScaledInstance(chang, kuang, Image.SCALE_FAST);
                ImageIcon icon = new ImageIcon(image);
                jLabel.setIcon(icon);
                bigimage.add(jPanel);
                bigimage.validate();
                System.out.println("滑轮向后....");
            }
        });
        bigimage.setResizable(false);
        bigimage.setVisible(true);
    }
}
