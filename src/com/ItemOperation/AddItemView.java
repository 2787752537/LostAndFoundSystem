package com.ItemOperation;

import com.Features.AddItem;
import com.Interface.loginView;
import com.MainInterfaceSonface.Articleface;
import com.NoticeOperation.AddNotice;
import com.component.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class AddItemView {
    int width, height;
    JDialog addItem;
    BackGroundPanel addItemjPanel = null;
    JLabel ItemNameJLabel, PickUpPlaceJLabel, PickUpTimeJLabel, yearJLabel, monthJLabel, dayJLabel, hourJLabel, CurrentItemLocationJLabel,
            contactNumberJLabel, ContactQQJLabel, addPicturesJLabel, ChooseItemTypeJLabel;
    JTextField ItemNamefield, PickUpPlacefield, yearfield, monthfield, dayfield, hourfield, CurrentItemLocationfield, contactNumberfield, ContactQQfield;
    JRadioButton card, Digital, Clothing, book, others;
    JButton add, cancel, JButtonimage1, JButtonimage2, JButtonimage3, deleteimage1, deleteimage2, deleteimage3;
    JLabel JLabelimage1, JLabelimage2, JLabelimage3;
    //设置选择器
    JFileChooser chooser = new JFileChooser();
    File imagefile1 = null, imagefile2 = null, imagefile3 = null;


    public void setAddItemView(JFrame frame, JMenuItem refresh) {
        addItem = new JDialog(frame, "增加物品", true);
        try {
            addItem.setIconImage(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/增加.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addItem.setResizable(false);
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        addItem.setBounds((width - 500) / 2, (height - 700) / 2, 500, 700);
        try {
            addItemjPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/增加背景.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addItemjPanel.setLayout(null);
        addItem.add(addItemjPanel);

        addItemjPanel.drawLine(120, 50, 320, 50);
        addItemjPanel.drawLine(120, 110, 320, 110);
        addItemjPanel.drawLine(120, 170, 150, 170);
        addItemjPanel.drawLine(170, 170, 200, 170);
        addItemjPanel.drawLine(220, 170, 250, 170);
        addItemjPanel.drawLine(270, 170, 300, 170);
        addItemjPanel.drawLine(120, 230, 320, 230);
        addItemjPanel.drawLine(120, 290, 320, 290);
        addItemjPanel.drawLine(120, 350, 320, 350);


        ItemNameJLabel = new JLabel("物品名称:");
        PickUpPlaceJLabel = new JLabel("拾取地:");
        PickUpTimeJLabel = new JLabel("拾取时间:");
        yearJLabel = new JLabel("年");
        monthJLabel = new JLabel("月");
        dayJLabel = new JLabel("日");
        hourJLabel = new JLabel("时");
        CurrentItemLocationJLabel = new JLabel("目前物品所在地:");
        contactNumberJLabel = new JLabel("联系电话(选填):");
        ContactQQJLabel = new JLabel("联系QQ(选填):");
        addPicturesJLabel = new JLabel("添加图片(1-3张):");
        ChooseItemTypeJLabel = new JLabel("选择物品类型(可多选):");

        ItemNameJLabel.setBounds(58, 20, 100, 30);
        PickUpPlaceJLabel.setBounds(71, 80, 100, 30);
        PickUpTimeJLabel.setBounds(58, 140, 100, 30);
        yearJLabel.setBounds(153, 140, 20, 30);
        monthJLabel.setBounds(203, 140, 20, 30);
        dayJLabel.setBounds(253, 140, 20, 30);
        hourJLabel.setBounds(303, 140, 20, 30);
        CurrentItemLocationJLabel.setBounds(20, 200, 100, 30);
        contactNumberJLabel.setBounds(24, 260, 100, 30);
        ContactQQJLabel.setBounds(33, 320, 100, 30);
        addPicturesJLabel.setBounds(20, 400, 100, 30);
        ChooseItemTypeJLabel.setBounds(350, 20, 150, 30);

        addItemjPanel.add(ItemNameJLabel);
        addItemjPanel.add(PickUpPlaceJLabel);
        addItemjPanel.add(PickUpTimeJLabel);
        addItemjPanel.add(yearJLabel);
        addItemjPanel.add(monthJLabel);
        addItemjPanel.add(dayJLabel);
        addItemjPanel.add(hourJLabel);
        addItemjPanel.add(CurrentItemLocationJLabel);
        addItemjPanel.add(contactNumberJLabel);
        addItemjPanel.add(ContactQQJLabel);
        addItemjPanel.add(addPicturesJLabel);
        addItemjPanel.add(ChooseItemTypeJLabel);

        ItemNamefield = new JTextField(20);
        PickUpPlacefield = new JTextField(20);
        yearfield = new JTextField(20);
        monthfield = new JTextField(20);
        dayfield = new JTextField(20);
        hourfield = new JTextField(20);
        CurrentItemLocationfield = new JTextField(20);
        contactNumberfield = new JTextField(20);
        ContactQQfield = new JTextField(20);

        field(ItemNamefield, PickUpPlacefield, yearfield, monthfield, dayfield, hourfield, CurrentItemLocationfield, contactNumberfield, ContactQQfield, addItemjPanel);

        card = new JRadioButton("卡", false);
        Digital = new JRadioButton("数码", false);
        Clothing = new JRadioButton("衣物", false);
        book = new JRadioButton("图书、证书", false);
        others = new JRadioButton("其他", false);
        setSelected();
        card.setOpaque(false);
        card.setFocusPainted(false);
        card.addActionListener((e) -> setSelected());

        Digital.setOpaque(false);
        Digital.setFocusPainted(false);
        Digital.addActionListener((e) -> setSelected());

        Clothing.setOpaque(false);
        Clothing.setFocusPainted(false);
        Clothing.addActionListener((e) -> setSelected());

        book.setOpaque(false);
        book.setFocusPainted(false);
        book.addActionListener((e) -> setSelected());
        others.setOpaque(false);
        others.setFocusPainted(false);
        others.addActionListener((e) -> setSelected());

        card.setBounds(350, 80, 100, 30);
        Digital.setBounds(350, 140, 100, 30);
        Clothing.setBounds(350, 200, 100, 30);
        book.setBounds(350, 260, 100, 30);
        others.setBounds(350, 320, 100, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(card);
        bg.add(Digital);
        bg.add(Clothing);
        bg.add(book);
        bg.add(others);

        addItemjPanel.add(card);
        addItemjPanel.add(Digital);
        addItemjPanel.add(Clothing);
        addItemjPanel.add(book);
        addItemjPanel.add(others);


        JLabelimage1 = new JLabel();
        JLabelimage1.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
        deleteimage1 = new JButton();
        deleteimage1.setEnabled(false);
        deleteimage1.setIcon(null);
        deleteimage1.setText("          ");
        deleteimage1.setFocusPainted(false);
        deleteimage1.setBorderPainted(false);
        deleteimage1.setOpaque(false);
        deleteimage1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JButtonimage1 = new JButton();
        JButtonimage1.setText("添加");
        JButtonimage1.setFocusPainted(false);
        JButtonimage1.setBorderPainted(false);
        JButtonimage1.setOpaque(false);
        JButtonimage1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JButtonimage1.addActionListener(e -> {
            //设为单选
            chooser.setMultiSelectionEnabled(false);
            //是否打开文件选择框()里面是设定在哪里打开,返回状态
            int refc = chooser.showOpenDialog(null);
            //如果有文件被选择
            if (refc == JFileChooser.APPROVE_OPTION) {
                imagefile1 = chooser.getSelectedFile();
                String name = imagefile1.getName();
                if (name.toLowerCase().endsWith(".gif") ||
                        name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".bmp") ||
                        name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".jpeg")) {
                    String filePath = imagefile1.getAbsolutePath();
                    ImageIcon imageIcon = new ImageIcon(filePath);
                    Image image = imageIcon.getImage();
                    image = image.getScaledInstance(130, 130, Image.SCALE_FAST);
                    ImageIcon icon = new ImageIcon(image);
                    JLabelimage1.setIcon(icon);
                    deleteimage1.setEnabled(true);
                    JButtonimage1.setText("修改");
                    deleteimage1.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/删除选项.png"))));
                } else {
                    Object[] options = new Object[]{"确定"};
                    JOptionPane.showOptionDialog(frame, "请输入正确格式！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                }
            }
        });
        deleteimage1.addActionListener(e -> {
            Object[] options = new Object[]{"确定", "取消"};
            int respose = JOptionPane.showOptionDialog(frame, "确认要删除吗！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if (respose == 0) {
                imagefile1 = null;
                JLabelimage1.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
                deleteimage1.setEnabled(false);
                deleteimage1.setIcon(null);
                deleteimage1.setText("          ");
                JButtonimage1.setText("添加");
            }
        });
        Box image1hBox1 = Box.createHorizontalBox();
        image1hBox1.add(JButtonimage1);
        image1hBox1.add(Box.createHorizontalStrut(30));
        image1hBox1.add(deleteimage1);
        Box image1hBox2 = Box.createHorizontalBox();
        image1hBox2.add(JLabelimage1);
        Box image1vBox1 = Box.createVerticalBox();
        image1vBox1.add(image1hBox1);
        image1vBox1.add(image1hBox2);
        image1vBox1.setBounds(10, 430, 150, 170);
        addItemjPanel.add(image1vBox1);


        JLabelimage2 = new JLabel();
        JLabelimage2.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
        deleteimage2 = new JButton();
        deleteimage2.setIcon(null);
        deleteimage2.setText("          ");
        deleteimage2.setFocusPainted(false);
        deleteimage2.setBorderPainted(false);
        deleteimage2.setOpaque(false);
        deleteimage2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JButtonimage2 = new JButton();
        JButtonimage2.setText("添加");
        JButtonimage2.setFocusPainted(false);
        JButtonimage2.setBorderPainted(false);
        JButtonimage2.setOpaque(false);
        JButtonimage2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JButtonimage2.addActionListener(e -> {
            //设为单选
            chooser.setMultiSelectionEnabled(false);
            //是否打开文件选择框()里面是设定在哪里打开,返回状态
            int refc = chooser.showOpenDialog(null);
            //如果有文件被选择
            if (refc == JFileChooser.APPROVE_OPTION) {
                imagefile2 = chooser.getSelectedFile();
                String name = imagefile1.getName();
                if (name.toLowerCase().endsWith(".gif") ||
                        name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".bmp") ||
                        name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".jpeg")) {
                    String filePath = imagefile2.getAbsolutePath();
                    ImageIcon imageIcon = new ImageIcon(filePath);
                    Image image = imageIcon.getImage();
                    image = image.getScaledInstance(130, 130, Image.SCALE_FAST);
                    ImageIcon icon = new ImageIcon(image);
                    JLabelimage2.setIcon(icon);
                    deleteimage2.setEnabled(true);
                    JButtonimage2.setText("修改");
                    deleteimage2.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/删除选项.png"))));
                } else {
                    Object[] options = new Object[]{"确定"};
                    JOptionPane.showOptionDialog(frame, "请输入正确格式！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                }
            }
        });
        deleteimage2.addActionListener(e -> {
            Object[] options = new Object[]{"确定", "取消"};
            int respose = JOptionPane.showOptionDialog(frame, "确认要删除吗?", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/问号.png"))), options, null);
            if (respose == 0) {
                imagefile2 = null;
                JLabelimage2.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
                deleteimage2.setEnabled(false);
                deleteimage2.setIcon(null);
                deleteimage2.setText("          ");
                JButtonimage2.setText("添加");
            }
        });
        Box image2hBox1 = Box.createHorizontalBox();
        image2hBox1.add(JButtonimage2);
        image2hBox1.add(Box.createHorizontalStrut(30));
        image2hBox1.add(deleteimage2);
        Box image2hBox2 = Box.createHorizontalBox();
        image2hBox2.add(JLabelimage2);
        Box image2vBox1 = Box.createVerticalBox();
        image2vBox1.add(image2hBox1);
        image2vBox1.add(image2hBox2);
        image2vBox1.setBounds(170, 430, 150, 170);
        addItemjPanel.add(image2vBox1);

        JLabelimage3 = new JLabel();
        JLabelimage3.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
        JButtonimage3 = new JButton();
        JButtonimage3.setText("添加");
        JButtonimage3.setFocusPainted(false);
        JButtonimage3.setBorderPainted(false);
        JButtonimage3.setOpaque(false);
        JButtonimage3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteimage3 = new JButton();
        deleteimage3.setText("          ");
        deleteimage3.setFocusPainted(false);
        deleteimage3.setFocusPainted(false);
        deleteimage3.setBorderPainted(false);
        deleteimage3.setOpaque(false);
        deleteimage3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JButtonimage3.addActionListener(e -> {
            //设为单选
            chooser.setMultiSelectionEnabled(false);
            //是否打开文件选择框()里面是设定在哪里打开,返回状态
            int refc = chooser.showOpenDialog(null);
            //如果有文件被选择
            if (refc == JFileChooser.APPROVE_OPTION) {
                imagefile3 = chooser.getSelectedFile();
                String name = imagefile1.getName();
                if (name.toLowerCase().endsWith(".gif") ||
                        name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".bmp") ||
                        name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".jpeg")) {
                    String filePath = imagefile3.getAbsolutePath();
                    ImageIcon imageIcon = new ImageIcon(filePath);
                    Image image = imageIcon.getImage();
                    image = image.getScaledInstance(130, 130, Image.SCALE_FAST);
                    ImageIcon icon = new ImageIcon(image);
                    JLabelimage3.setIcon(icon);
                    deleteimage3.setEnabled(true);
                    JButtonimage3.setText("修改");
                    deleteimage3.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/删除选项.png"))));
                } else {
                    Object[] options = new Object[]{"确定"};
                    JOptionPane.showOptionDialog(frame, "请输入正确格式！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);

                }
            }
        });
        deleteimage3.addActionListener(e -> {
            Object[] options = new Object[]{"确定", "取消"};
            int respose = JOptionPane.showOptionDialog(frame, "确认要删除吗！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if (respose == 0) {
                imagefile3 = null;
                JLabelimage3.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
                deleteimage3.setEnabled(false);
                deleteimage3.setIcon(null);
                deleteimage3.setText("          ");
                JButtonimage3.setText("添加");
            }
        });
        box(JButtonimage3, deleteimage3, JLabelimage3, addItemjPanel);

        add = new JButton("添加");
        addbutton(add);
        add.addActionListener(e -> {
            int i = AddItem.addItem(ItemNamefield.getText().trim(), PickUpPlacefield.getText().trim(),
                    yearfield.getText().trim(), monthfield.getText().trim(), dayfield.getText().trim(),
                    hourfield.getText().trim(), CurrentItemLocationfield.getText().trim(), contactNumberfield.getText().trim(),
                    ContactQQfield.getText().trim(), bg, imagefile1, imagefile2, imagefile3, frame);
            if (i == 1) addItem.dispose();
            refresh.doClick();
        });

        cancel = new JButton("取消");
        cancel.setBounds(270, 620, 100, 30);
        cancel.setBorderPainted(false);
        AddNotice.SetProperties(addItem, add, cancel, addItemjPanel);

        addItem.setVisible(true);
    }

    static void addbutton(JButton add) {
        add.setBounds(120, 620, 100, 30);
        add.setBorderPainted(false);
        add.setForeground(Color.black);
        add.setBackground(new Color(182, 213, 227));
        add.setFocusPainted(false);
        add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    static void box(JButton jButtonimage3, JButton deleteimage3, JLabel jLabelimage3, BackGroundPanel addItemjPanel) {
        Box image3hBox1 = Box.createHorizontalBox();
        image3hBox1.add(jButtonimage3);
        image3hBox1.add(Box.createHorizontalStrut(30));
        image3hBox1.add(deleteimage3);
        Box image3hBox2 = Box.createHorizontalBox();
        image3hBox2.add(jLabelimage3);
        Box image3vBox1 = Box.createVerticalBox();
        image3vBox1.add(image3hBox1);
        image3vBox1.add(image3hBox2);
        image3vBox1.setBounds(330, 430, 150, 170);
        addItemjPanel.add(image3vBox1);
    }

    static void field(JTextField itemNamefield, JTextField pickUpPlacefield, JTextField yearfield, JTextField monthfield, JTextField dayfield, JTextField hourfield, JTextField currentItemLocationfield, JTextField contactNumberfield, JTextField contactQQfield, BackGroundPanel addItemjPanel) {
        itemNamefield.setBounds(120, 20, 200, 30);
        pickUpPlacefield.setBounds(120, 80, 200, 30);
        yearfield.setBounds(120, 140, 30, 30);
        monthfield.setBounds(178, 140, 22, 30);
        dayfield.setBounds(228, 140, 22, 30);
        hourfield.setBounds(278, 140, 22, 30);
        currentItemLocationfield.setBounds(120, 200, 200, 30);
        contactNumberfield.setBounds(120, 260, 200, 30);
        contactQQfield.setBounds(120, 320, 200, 30);

        itemNamefield.setBackground(null);
        itemNamefield.setOpaque(false);
        itemNamefield.setBorder(null);
        itemNamefield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        pickUpPlacefield.setBackground(null);
        pickUpPlacefield.setOpaque(false);
        pickUpPlacefield.setBorder(null);
        pickUpPlacefield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        yearfield.setBackground(null);
        yearfield.setOpaque(false);
        yearfield.setBorder(null);
        yearfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        monthfield.setBackground(null);
        monthfield.setOpaque(false);
        monthfield.setBorder(null);
        monthfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        dayfield.setBackground(null);
        dayfield.setOpaque(false);
        dayfield.setBorder(null);
        dayfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        hourfield.setBackground(null);
        hourfield.setOpaque(false);
        hourfield.setBorder(null);
        hourfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        currentItemLocationfield.setBackground(null);
        currentItemLocationfield.setOpaque(false);
        currentItemLocationfield.setBorder(null);
        currentItemLocationfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        contactNumberfield.setBackground(null);
        contactNumberfield.setOpaque(false);
        contactNumberfield.setBorder(null);
        contactNumberfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));
        contactQQfield.setBackground(null);
        contactQQfield.setOpaque(false);
        contactQQfield.setBorder(null);
        contactQQfield.setFont(new Font("华光楷体_CNKI", Font.PLAIN, 16));

        addItemjPanel.add(itemNamefield);
        addItemjPanel.add(pickUpPlacefield);
        addItemjPanel.add(yearfield);
        addItemjPanel.add(monthfield);
        addItemjPanel.add(dayfield);
        addItemjPanel.add(hourfield);
        addItemjPanel.add(currentItemLocationfield);
        addItemjPanel.add(contactNumberfield);
        addItemjPanel.add(contactQQfield);
    }

    private void setSelected() {
        card.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(
                card.isSelected() ? "/images/选中点.png" : "/images/空心点.png"))));
        Digital.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(
                Digital.isSelected() ? "/images/选中点.png" : "/images/空心点.png"))));
        Clothing.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(
                Clothing.isSelected() ? "/images/选中点.png" : "/images/空心点.png"))));
        book.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(
                book.isSelected() ? "/images/选中点.png" : "/images/空心点.png"))));
        others.setIcon(new ImageIcon(Objects.requireNonNull(loginView.class.getResource(
                others.isSelected() ? "/images/选中点.png" : "/images/空心点.png"))));
    }


}
