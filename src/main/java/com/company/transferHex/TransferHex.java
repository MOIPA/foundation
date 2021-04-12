package com.company.transferHex;

import jxl.read.biff.BiffException;

import javax.swing.*;
import java.io.*;
import java.util.List;

public class TransferHex {

    public static void main(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("信号处理工具");
        // Setting the width and height of frame
        frame.setLocation(750, 400);
        frame.setSize(350, 270);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);


        //初始化一个菜单栏
        JMenuBar menuBar = new JMenuBar();
        //初始化菜单
        JMenu menu1 = new JMenu("author");
        JMenu menu2 = new JMenu("version");
        //把菜单添加到菜单栏
        menuBar.add(menu1);
        menuBar.add(menu2);
        JMenuItem item1 = new JMenuItem("TangRui");
        JMenuItem item2 = new JMenuItem("v1.2");
        menu1.add(item1);
        menu2.add(item2);
        //设置菜单栏
        frame.setJMenuBar(menuBar);

        // 设置界面可见
        frame.setVisible(true);

    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        // 创建 JLabel
        JLabel fileLocation = new JLabel("信号文件地址：");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        fileLocation.setBounds(10, 20, 150, 25);
        panel.add(fileLocation);



        /*
         * 创建信号文件地址文本域
         */
        JTextField signalFileLocationText = new JTextField(20);
        signalFileLocationText.setBounds(150, 20, 165, 25);
        panel.add(signalFileLocationText);

        // 输入修改信号位置文本域
        JLabel posionLabel = new JLabel("对应excel文件位置");
        posionLabel.setBounds(10, 50, 150, 25);
        panel.add(posionLabel);

        /*
         * 修改信号位置输入框
         */
        JTextField excelLocationText = new JTextField(20);
        excelLocationText.setBounds(150, 50, 165, 25);
        panel.add(excelLocationText);

        // 输入修改信号位置文本域
        JLabel offsetLabel = new JLabel("信号：空则默认修改全部");
        offsetLabel.setBounds(10, 80, 150, 25);
        panel.add(offsetLabel);

        /*
         * 修改信号位置输入框
         */
        JTextField targetSignalText = new JTextField(20);
        targetSignalText.setBounds(150, 80, 165, 25);
        panel.add(targetSignalText);

        // 输入修改信号位置文本域
        JLabel udpoffSet = new JLabel("udp偏移量");
        udpoffSet.setBounds(10, 110, 150, 25);
        panel.add(udpoffSet);
        /*
         * 修改信号位置输入框
         */
        JTextField udpOffsetText = new JTextField(20);
        udpOffsetText.setBounds(150, 110, 165, 25);
        panel.add(udpOffsetText);


        // 输入修改信号位置文本域
        JLabel sheetName = new JLabel("sheet名");
        sheetName.setBounds(10, 140, 150, 25);
        panel.add(sheetName);
        /*
         * 修改信号位置输入框
         */
        JTextField sheetNameText = new JTextField(20);
        sheetNameText.setBounds(150, 140, 165, 25);
        panel.add(sheetNameText);

        // 创建修改按钮
        JButton submitButton = new JButton("修改");
        submitButton.setBounds(10, 170, 80, 25);
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            String signalFileLocation = signalFileLocationText.getText();
            String excelLocation = excelLocationText.getText();
            String targetSignal = targetSignalText.getText();
            String udpOffset = udpOffsetText.getText();
            String sheetNm = sheetNameText.getText();

            ExcelFileController excelFileController = new ExcelFileController();
            excelFileController.setFileLocation(excelLocation);
            try {
                excelFileController.readRowsAndColums(sheetNm);
            } catch (Exception biffException) {
                biffException.printStackTrace();
                JOptionPane.showMessageDialog(panel, "打开Excel文件失败", "修改结果", JOptionPane.WARNING_MESSAGE);
            }
            List<String[]> data = excelFileController.getData();
            SignalFileController fc = new SignalFileController();
            fc.setFilePath(signalFileLocation);
            try {
                // init fc
                fc.getFileContentDex();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                JOptionPane.showMessageDialog(panel, "打开信号文件失败", "修改结果", JOptionPane.WARNING_MESSAGE);
            }
            if (!targetSignal.equals("")) {
                data.forEach(x -> {
                    if (x[0].equals(targetSignal)) {
                        fc.changePosition(x[1], Integer.parseInt(x[2]), Integer.parseInt(udpOffset));
                    }
                });
                JOptionPane.showMessageDialog(panel, targetSignal + "信号修改成功", "修改结果", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    data.forEach(x -> {
                        fc.changePosition(x[1], Integer.parseInt(x[2]), Integer.parseInt(udpOffset));
                    });
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(panel, "修改信号失败", "修改结果", JOptionPane.WARNING_MESSAGE);
                }
                JOptionPane.showMessageDialog(panel, "全部信号修改结束", "修改结果", JOptionPane.WARNING_MESSAGE);
            }
            fc.writeFile();
        });

    }

}
