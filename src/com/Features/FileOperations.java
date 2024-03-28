package com.Features;

import com.ItemOperation.ItemInformationView;
import com.sqlConnection.sqlConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

public class FileOperations {
    //得到某路径下的prop
    public static Properties getprop(String path) {
        Properties prop = new Properties();
        FileReader fr;
        try {
            File file = new File(path);
            if (!file.exists()) System.out.println(file.createNewFile());
            fr = new FileReader(path);
            prop.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    //将prop存到某路径
    public static void storeprop(Properties prop, String path) {
        FileWriter fw = null;
        try {
            File file = new File(path);
            if (!file.exists()) System.out.println(file.createNewFile());
            fw = new FileWriter(path);
            prop.store(fw, null);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void deleteFile() {
        File file = new File("Itemimages");
        deleteFile(file);
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            //递归,如果无法删除说明目录里有内容，先调用删除删除目录中的内容
            if (!file.delete()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        deleteFile(f);
                    }
                }
                System.out.println(file.delete());
            }
        }
    }

    public static void createFile() {
        //得到数据库中所有物品的信息物品
        String sql = "select * from article";
        sqlConnection sqlcon = new sqlConnection();
        ResultSet res = sqlcon.sqlQuery(sql);

        InputStream inputImage = null;
        OutputStream fos = null;
        byte[] bytes = new byte[1024];
        int len;
        //创建文件夹储存数据库物品内容
        File file = new File("Itemimages");
        if (!file.exists()) System.out.println("Itemimages是否创建: " + file.mkdir());
        try {
            while (res.next()) {
                //在Itemimages下创建文件夹以物品索引作为文件夹名字，以便后续查找
                File file1 = new File(file + "\\" + res.getInt(1));
                if (!file1.exists())
                    System.out.println(file + "\\" + res.getInt(1) + "是否创建: " + file1.mkdir());
                if (res.getBlob(10) != null) {
                    //如果图片存在创建图片文件，将数据库中图片读出
                    File file2 = new File(file1 + "\\" + res.getString(11));
                    try {
                        System.out.println("file2e=" + file2.exists());
                        if (!file2.exists())
                            System.out.println(file1 + "\\" + res.getString(11) + "是否创建: " + file2.createNewFile());
                        //得到图片字节流
                        inputImage = res.getBlob(10).getBinaryStream();
                        //将图片读到文件中
                        fos = new FileOutputStream(file2);
                        while ((len = inputImage.read(bytes)) != -1) {
                            fos.write(bytes, 0, len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (res.getBlob(12) != null) {
                    File file2 = new File(file1 + "\\" + res.getString(13));
                    try {
                        if (!file2.exists())
                            System.out.println(file1 + "\\" + res.getString(13) + "是否创建: " + file2.createNewFile());
                        fos = new FileOutputStream(file2);
                        inputImage = res.getBlob(12).getBinaryStream();
                        while ((len = inputImage.read(bytes)) != -1) {
                            fos.write(bytes, 0, len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (res.getBlob(14) != null) {
                    File file2 = new File(file1 + "\\" + res.getString(15));
                    try {
                        if (!file2.exists()) System.out.println(file2.createNewFile());
                        fos = new FileOutputStream(file2);
                        inputImage = res.getBlob(14).getBinaryStream();
                        while ((len = inputImage.read(bytes)) != -1) {
                            fos.write(bytes, 0, len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //创建数组将物品其他信息储存到文件中
                Properties prop = new Properties();
                prop.setProperty("index", res.getString(1));
                prop.setProperty("Item_name", res.getString(2));
                prop.setProperty("pick_up_place", res.getString(3));
                prop.setProperty("time", res.getString(4));
                prop.setProperty("Types", res.getString(5));
                prop.setProperty("location", res.getString(6));
                prop.setProperty("contact_number", ""+res.getString(7));
                prop.setProperty("Contact_qq", ""+res.getString(8));
                prop.setProperty("publisher", res.getString(9));
                prop.setProperty("image1name",""+ res.getString(11));
                prop.setProperty("image2name",""+ res.getString(13));
                prop.setProperty("image3name",""+ res.getString(15));
                File file2 = new File(file1 + "\\" + res.getInt(1) + ".txt");
                FileWriter fw = null;
                try {
                    fw = new FileWriter(file2);
                    prop.store(fw, null);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fw != null) fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            try {
                if (fos != null) fos.close();
                if (inputImage != null) inputImage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlcon.closeSqlConn();

        }
    }

    public static ImageIcon[] getImage(String index, int w, int h) {
        ImageIcon[] icon = new ImageIcon[3];
        //得到所以物品
        File file = new File("Itemimages");
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                //判断是否是索引的物品，如果是
                if (f.getName().equals(index)) {
                    int i = 0;
                    File[] fs = f.listFiles();
                    if (fs != null) {
                        for (File f1 : fs) {
                            String name = f1.getName();
                            String filePath = f1.getAbsolutePath();
                            //判断是否是图片
                            if (!name.toLowerCase().endsWith(".txt")) {
                                ImageIcon imageIcon = new ImageIcon(filePath);
                                Image image = imageIcon.getImage();
                                image = image.getScaledInstance(w, h, Image.SCALE_FAST);
                                icon[i] = new ImageIcon(image);
                                i++;
                            }
                        }
                    }
                    break;
                }
            }
        }
        return icon;
    }

    public static Properties getItemprop(String index) {
        Properties prop = new Properties();
        File file = new File("Itemimages");
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.getName().equals(index)) {
                    File[] fs = f.listFiles();
                    for (File f1 : fs != null ? fs : new File[0]) {
                        String name = f1.getName();
                        String filePath = f1.getAbsolutePath();
                        if (name.toLowerCase().endsWith(".txt")) {
                            FileReader fr;
                            try {
                                fr = new FileReader(filePath);
                                prop.load(fr);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                }
            }
        }
        return prop;
    }

    public static Box getItemBox(Properties prop, ImageIcon[] icon, JFrame frame, JPopupMenu pop) {
        Box vBox = Box.createVerticalBox();
        if (!prop.isEmpty()) {
            //将按钮名取为物品索引，便于之后操作按钮
            JButton index = new JButton(prop.getProperty("index"));
            System.out.println(prop);
            //设置按钮属性
            index.setForeground(new Color(238, 238, 238));
            index.setFocusPainted(false);
            index.setBorderPainted(false);
            index.setOpaque(false);
            index.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            //添加鼠标右键监听器
            index.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        //创建文件数组
                        //当按钮被右击时记录下被击打的按钮名称，用于其他方法能找到被按的按钮(如删除功能)
                        Properties properties = new Properties();
                        properties.setProperty("Selected", prop.getProperty("index"));
                        storeprop(properties, "Selected.txt");
                        //检查是否正常工作
                        System.out.println(prop.getProperty("index") + "右击打");
                        //添加右击菜单栏
                        pop.show(vBox, e.getX(), e.getY());
                    }
                }
            });
            index.addActionListener(e -> {
                //添加按钮监听器
                //当按钮被左击时更新历史记录
                ArrayList<String> historyArr = read();
                //检查是否正常工作
                System.out.println(historyArr);
                System.out.println("按钮" + e.getActionCommand() + "被点击");
                if (!historyArr.isEmpty()) {
                    for (int j = 0; j < historyArr.size(); j++) {
                        if (historyArr.get(j).equals(e.getActionCommand())) {
                            historyArr.remove(j);
                            break;
                        }
                    }
                }
                historyArr.add(0, e.getActionCommand());
                store(historyArr, frame);
                //打开物品信息显示界面
                new ItemInformationView().setItemInformationView(e.getActionCommand(), frame);
            });
            if (!prop.getProperty("image1name").equals("null") ||
                    !prop.getProperty("image2name").equals("null") ||
                    !prop.getProperty("image3name").equals("null")) {
                index.setIcon(icon[0]);
            } else {
                index.setIcon(new ImageIcon(Objects.requireNonNull(FileOperations.class.getResource("/images/加图片.png"))));
            }
            //标签取名为物品名称
            JLabel jLabel = new JLabel(prop.getProperty("Item_name"));
            vBox.add(index);
            vBox.add(jLabel);
        }
        return vBox;
    }

    public static JPanel createItemJpanel(String selection, JFrame frame, JPopupMenu pop) {
        deleteFile();
        createFile();
        GridLayout gl = new GridLayout();
        JPanel Item = new JPanel(gl);
        Item.setBackground(Color.white);
        Item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    pop.show(Item, e.getX(), e.getY());
                }
            }
        });
        File file = new File("Itemimages");
        if (file.exists()) {
            //将物品文件夹中的物品信息读取出来
            //每个文件代表一个物品
            //先得到物品数组
            File[] files = file.listFiles();
            //判断文件存在且有物品
            if ((files != null ? files.length : 0) != 0) {
                int recording = 0;//一个记录用于控制网格布局器的布局
                for (File f : files) {
                    recording++;
                    //每个物品的信息
                    String filename = f.getName();
                    //创建图片数组用于得到物品图片
                    ImageIcon[] icons = getImage(filename, 130, 130);
                    //创建数组得到物品信息
                    Properties prop = getItemprop(filename);
                    //判断选择返回面板
                    if (selection.equals("全部")) {
                        Item.add(getItemBox(prop, icons, frame, pop));
                    } else {
                        if (prop.getProperty("Types").equals(selection)) {
                            Item.add(getItemBox(prop, icons, frame, pop));
                        }
                    }
                }
                gl.setRows((int) Math.sqrt(recording));
            }
        }
        return Item;
    }

    public static JPanel search(String select, String flag, JPopupMenu pop, JFrame frame) {
        int record = 0;
        boolean b = true;
        deleteFile();
        createFile();
        GridLayout gl = new GridLayout(1, 1, 10, 10);
        JPanel searchjpanel = new JPanel(gl);
        searchjpanel.setBackground(Color.white);
        searchjpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    pop.show(searchjpanel, e.getX(), e.getY());
                }
            }
        });
        ArrayList<String> ranges = new ArrayList<>();
        int w = 0, h = 0;
        switch (flag) {
            case "Articleface" -> {
                File file = new File("Itemimages");
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        ranges.add(f.getName());
                    }
                }
                w = 130;
                h = 130;
            }
            case "history" -> {
                ranges = read();
                w = 310;
                h = 150;
                gl.setRows(1000);
            }
            case "Mine" -> {
                sqlConnection sqlcon = new sqlConnection();
                String sql = "select * from article where publisher = '" + select + "'";
                ResultSet res = sqlcon.sqlQuery(sql);
                try {
                    while (res.next()) {
                        ranges.add(res.getString(1));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    if (res != null) {
                        try {
                            res.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    sqlcon.closeSqlConn();
                }
                gl.setRows((int) Math.sqrt(ranges.size()));
                w = 130;
                h = 130;
            }
        }
        if (!ranges.isEmpty()) {
            for (String s : ranges) {
                Properties prop = getItemprop(s);
                ImageIcon[] icons = getImage(s, w, h);
                if (!flag.equals("Mine")&&!prop.isEmpty()) {
                    record++;
                    b = prop.getProperty("pick_up_place").equals(select) ||
                            prop.getProperty("Item_name").equals(select) ||
                            prop.getProperty("time").equals(select);
                }
                if (b) {
                    searchjpanel.add(getItemBox(prop, icons, frame, pop));
                }
            }
            if (flag.equals("Articleface")) {
                gl.setRows((int) Math.sqrt(record));
            }
        }
        return searchjpanel;
    }

    public static Properties getuserprop() {
        Properties prop = new Properties();
        String sql = "select * from user1 where username ='" + getprop("memory.txt").getProperty("username") + "'";
        sqlConnection sqlcon = new sqlConnection();
        ResultSet res = sqlcon.sqlQuery(sql);
        try {
            res.next();
            prop.setProperty("nickname", res.getString(1));
            prop.setProperty("username", res.getString(2));
            prop.setProperty("password", res.getString(3));
            prop.setProperty("sex", res.getString(4));
            prop.setProperty("Identity", res.getString(5));
            prop.setProperty("Pickups", res.getString(6));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (res != null) res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            sqlcon.closeSqlConn();
        }
        return prop;
    }

    public static void store(ArrayList<String> historyArr, JFrame frame) {
        StringBuilder historys = new StringBuilder();
        for (String s : historyArr) {
            historys.append(s);
            historys.append("-");
        }
        sqlConnection sqlcon = new sqlConnection();
        String sql = "update user1 set history_record='" + historys + "' where  username ='" + getprop("memory.txt").getProperty("username") + "'";
        sqlcon.sqlUpdate(sql, frame);
        sqlcon.closeSqlConn();
    }

    public static ArrayList<String> read() {
        ArrayList<String> historyArr = new ArrayList<>();
        String sql = "select * from user1 where username ='" + getprop("memory.txt").getProperty("username") + "'";
        sqlConnection sqlcon = new sqlConnection();
        ResultSet res = sqlcon.sqlQuery(sql);
        try {
            res.next();
            String history_record = res.getString(7);
            if(history_record!=null) {
                String[] historysplit = history_record.split("-");
                historyArr.addAll(Arrays.asList(historysplit));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (res != null) res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            sqlcon.closeSqlConn();
        }
        return historyArr;
    }


}
