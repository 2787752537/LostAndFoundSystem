package com.component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class BackGroundPanel extends JPanel {
    //声明图片
    private final Image backicon;
    //创建数组得到要画线的地方
    private final Vector<Integer> x1 = new Vector<>();
    private final Vector<Integer> y1 = new Vector<>();
    private final Vector<Integer> x2 = new Vector<>();
    private final Vector<Integer> y2 = new Vector<>();

    public BackGroundPanel(BufferedImage backicon) {
        this.backicon = backicon;
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        this.x1.add(x1);
        this.y1.add(y1);
        this.x2.add(x2);
        this.y2.add(y2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制背景
        g.drawImage(backicon, 0, 0, this.getWidth(), this.getHeight(), null);
        //绘制面板上的线
        for (int i = 0; i < x1.size(); i++) {
            g.drawLine(this.x1.get(i), this.y1.get(i), this.x2.get(i), this.y2.get(i));
        }
    }
}
