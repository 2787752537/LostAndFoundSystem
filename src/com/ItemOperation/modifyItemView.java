package com.ItemOperation;

import com.Features.FileOperations;
import com.Features.ModifyItems;
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
import java.util.Properties;

public class modifyItemView {
    int width, height;
    JDialog modifyItem;
    BackGroundPanel modifyItemjPanel = null;
    JLabel ItemNameJLabel, PickUpPlaceJLabel, PickUpTimeJLabel, yearJLabel, monthJLabel, dayJLabel, hourJLabel, CurrentItemLocationJLabel,
            contactNumberJLabel, ContactQQJLabel, addPicturesJLabel, ChooseItemTypeJLabel;
    JTextField ItemNamefield, PickUpPlacefield, yearfield, monthfield, dayfield, hourfield, CurrentItemLocationfield, contactNumberfield, ContactQQfield;
    JRadioButton card, Digital, Clothing, book, others;
    JButton modify, cancel, JButtonimage1, JButtonimage2, JButtonimage3, deleteimage1, deleteimage2, deleteimage3;
    JLabel JLabelimage1, JLabelimage2, JLabelimage3;
    //设置选择器
    JFileChooser chooser = new JFileChooser();
    File imagefile1 = null, imagefile2 = null, imagefile3 = null;

    public void setmodifyItemView(JFrame frame, JMenuItem refresh, String select) {
        Properties prop = FileOperations.getItemprop(select);
        if (!prop.getProperty("image1name").equals("无"))
            imagefile1 = new File("Itemimages\\" + select + "\\" + prop.getProperty("image1name"));
        if (!prop.getProperty("image2name").equals("无"))
            imagefile2 = new File("Itemimages\\" + select + "\\" + prop.getProperty("image2name"));
        if (!prop.getProperty("image3name").equals("无"))
            imagefile3 = new File("Itemimages\\" + select + "\\" + prop.getProperty("image3name"));
        ImageIcon[] icons = FileOperations.getImage(select, 130, 130);
        modifyItem = new JDialog(frame, "修改物品", true);
        try {
            modifyItem.setIconImage(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/修 改.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        modifyItem.setResizable(false);
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        modifyItem.setBounds((width - 500) / 2, (height - 700) / 2, 500, 700);
        try {
            modifyItemjPanel = new BackGroundPanel(ImageIO.read(Objects.requireNonNull(loginView.class.getResource("/images/增加背景.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        modifyItemjPanel.setLayout(null);
        modifyItem.add(modifyItemjPanel);

        modifyItemjPanel.drawLine(120, 50, 320, 50);
        modifyItemjPanel.drawLine(120, 110, 320, 110);
        modifyItemjPanel.drawLine(120, 170, 150, 170);
        modifyItemjPanel.drawLine(170, 170, 200, 170);
        modifyItemjPanel.drawLine(220, 170, 250, 170);
        modifyItemjPanel.drawLine(270, 170, 300, 170);
        modifyItemjPanel.drawLine(120, 230, 320, 230);
        modifyItemjPanel.drawLine(120, 290, 320, 290);
        modifyItemjPanel.drawLine(120, 350, 320, 350);


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

        modifyItemjPanel.add(ItemNameJLabel);
        modifyItemjPanel.add(PickUpPlaceJLabel);
        modifyItemjPanel.add(PickUpTimeJLabel);
        modifyItemjPanel.add(yearJLabel);
        modifyItemjPanel.add(monthJLabel);
        modifyItemjPanel.add(dayJLabel);
        modifyItemjPanel.add(hourJLabel);
        modifyItemjPanel.add(CurrentItemLocationJLabel);
        modifyItemjPanel.add(contactNumberJLabel);
        modifyItemjPanel.add(ContactQQJLabel);
        modifyItemjPanel.add(addPicturesJLabel);
        modifyItemjPanel.add(ChooseItemTypeJLabel);

        ItemNamefield = new JTextField(20);
        ItemNamefield.setText(prop.getProperty("Item_name"));
        PickUpPlacefield = new JTextField(20);
        PickUpPlacefield.setText(prop.getProperty("pick_up_place"));
        yearfield = new JTextField(20);
        String[] times = prop.getProperty("time").split("-");
        yearfield.setText(times[0]);
        monthfield = new JTextField(20);
        monthfield.setText(times[1]);
        dayfield = new JTextField(20);
        dayfield.setText(times[2]);
        hourfield = new JTextField(20);
        hourfield.setText(times[3]);
        CurrentItemLocationfield = new JTextField(20);
        CurrentItemLocationfield.setText(prop.getProperty("location"));
        contactNumberfield = new JTextField(20);
        contactNumberfield.setText(prop.getProperty("contact_number"));
        ContactQQfield = new JTextField(20);
        ContactQQfield.setText(prop.getProperty("Contact_qq"));

        AddItemView.field(ItemNamefield, PickUpPlacefield, yearfield, monthfield, dayfield, hourfield, CurrentItemLocationfield, contactNumberfield, ContactQQfield, modifyItemjPanel);

        card = new JRadioButton("卡", prop.getProperty("Types").equals("卡"));
        Digital = new JRadioButton("数码", prop.getProperty("Types").equals("数码"));
        Clothing = new JRadioButton("衣物", prop.getProperty("Types").equals("衣物"));
        book = new JRadioButton("图书、证书", prop.getProperty("Types").equals("图书、证书"));
        others = new JRadioButton("其他", prop.getProperty("Types").equals("其他"));

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
        setSelected();

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

        modifyItemjPanel.add(card);
        modifyItemjPanel.add(Digital);
        modifyItemjPanel.add(Clothing);
        modifyItemjPanel.add(book);
        modifyItemjPanel.add(others);


        JLabelimage1 = new JLabel();
        JButtonimage1 = new JButton();
        if (prop.getProperty("image1name").equals("无")) {
            JLabelimage1.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
            JButtonimage1.setText("添加");
        } else {
            JLabelimage1.setIcon(icons[0]);
            JButtonimage1.setText("修改");
        }
        deleteimage1 = new JButton();
        deleteimage1.setEnabled(false);
        deleteimage1.setIcon(null);
        deleteimage1.setText("          ");
        deleteimage1.setFocusPainted(false);
        deleteimage1.setBorderPainted(false);
        deleteimage1.setOpaque(false);
        deleteimage1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
            int respose = JOptionPane.showOptionDialog(frame, "确认要删除吗?", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/问号.png"))), options, null);
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
        modifyItemjPanel.add(image1vBox1);


        JLabelimage2 = new JLabel();
        JButtonimage2 = new JButton();
        if (prop.getProperty("image2name").equals("无")) {
            JLabelimage2.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
            JButtonimage2.setText("添加");
        } else {
            JLabelimage2.setIcon(icons[1]);
            JButtonimage2.setText("修改");
        }
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
        modifyItemjPanel.add(image2vBox1);

        JLabelimage3 = new JLabel();
        JButtonimage3 = new JButton();
        if (prop.getProperty("image3name").equals("无")) {
            JLabelimage3.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
            JButtonimage3.setText("添加");
        } else {
            JLabelimage3.setIcon(icons[2]);
            JButtonimage3.setText("修改");
        }
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
            int respose = JOptionPane.showOptionDialog(frame, "确认要删除吗?", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/问号.png"))), options, null);
            if (respose == 0) {
                imagefile3 = null;
                JLabelimage3.setIcon(new ImageIcon(Objects.requireNonNull(Articleface.class.getResource("/images/添加图片.png"))));
                deleteimage3.setEnabled(false);
                deleteimage3.setIcon(null);
                deleteimage3.setText("          ");
                JButtonimage3.setText("添加");
            }
        });
        AddItemView.box(JButtonimage3, deleteimage3, JLabelimage3, modifyItemjPanel);

        modify = new JButton("修改");
        AddItemView.addbutton(modify);
        modify.addActionListener(e -> {
            int i = ModifyItems.ModifyItem(ItemNamefield.getText().trim(), PickUpPlacefield.getText().trim(),
                    yearfield.getText().trim(), monthfield.getText().trim(), dayfield.getText().trim(),
                    hourfield.getText().trim(), CurrentItemLocationfield.getText().trim(), contactNumberfield.getText().trim(),
                    ContactQQfield.getText().trim(), bg, imagefile1, imagefile2, imagefile3, prop.getProperty("publisher"), select, frame);
            if (i == 1) modifyItem.dispose();
            refresh.doClick();
        });

        cancel = new JButton("取消");
        cancel.setBounds(270, 620, 100, 30);
        cancel.setBorderPainted(false);
        AddNotice.SetProperties(modifyItem, modify, cancel, modifyItemjPanel);

        modifyItem.setVisible(true);
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
