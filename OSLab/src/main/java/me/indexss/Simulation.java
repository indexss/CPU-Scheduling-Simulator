package me.indexss;


import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO：2023.5.27 写完了对象及其方法 明日完成界面
//TODO: 2023.5.28 写完了界面 没写监听器 而且CPU和RAM耦合度太高

public class Simulation extends JFrame {
    public static int pidCount = 0;

    public static int[] finishFlag = new int[6];

    public static JTextField CPU1Jtf = null;
    public static JTextField CPU2Jtf = null;

    public static int insertCount = 0;
    public static JTextField pcbOpJtf = null;
    public static PCB pcb1 = null;
    public static PCB pcb2 = null;
    public static PCB pcb3 = null;
    public static PCB pcb4 = null;
    public static PCB pcb5 = null;
    public static PCB pcb6 = null;
    public static RAM ram = null;
    public static Storage storage = new Storage();
    public static CPU cpu = new CPU();
    public static int[] cpuPid = new int[6];
    public Simulation(){
        this.setSize(650,700);
//        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
//        setUndecorated(true);
        setLocationRelativeTo(null);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        this.setTitle("CPU调度模拟");
        JLabel titleJlb = new JLabel("CPU调度模拟");
        titleJlb.setFont(new Font("仿宋", Font.BOLD, 22));
        titleJlb.setBounds(182,0,500,50);
        contentPane.add(titleJlb);

        JLabel newPCBJlb = new JLabel("———\uD83C\uDF9E\uFE0E 新建进程———");
        newPCBJlb.setBounds(15,40,300,30);
        newPCBJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(newPCBJlb);

        JLabel pidOpJlb = new JLabel("pid操作： ");
        pidOpJlb.setBounds(268,235,200,30);
        pidOpJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(pidOpJlb);

        JLabel newRAMJlb = new JLabel("———\uD83D\uDCBD 内存设置———");
        newRAMJlb.setBounds(270,40,300,30);
        newRAMJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(newRAMJlb);

        JLabel CPU1Jlb = new JLabel("————-CPU1-————");
        CPU1Jlb.setBounds(15,560,300,30);
        CPU1Jlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(CPU1Jlb);

        CPU1Jtf = new JTextField();
        CPU1Jtf.setBounds(70,600,100,60);
        CPU1Jtf.setFont(new Font("仿宋", NORMAL, 25));
        CPU1Jtf.setEditable(false);
        contentPane.add(CPU1Jtf);

        JLabel CPU2Jlb = new JLabel("————-CPU2-————");
        CPU2Jlb.setBounds(270,560,300,30);
        CPU2Jlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(CPU2Jlb);

        CPU2Jtf = new JTextField();
        CPU2Jtf.setBounds(325,600,100,60);
        CPU2Jtf.setFont(new Font("仿宋", NORMAL, 25));
        CPU2Jtf.setEditable(false);
        contentPane.add(CPU2Jtf);

        JLabel pcbStatJlb = new JLabel("——————————PCB状态展示——————————");
        pcbStatJlb.setBounds(15,320,500,30);
        pcbStatJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(pcbStatJlb);

        JLabel daoNumJlb = new JLabel("内存道数： ");
        daoNumJlb.setBounds(270,75,200,30);
        daoNumJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(daoNumJlb);

        JLabel RAMBigJlb = new JLabel("内存大小： ");
        RAMBigJlb.setBounds(270,115,200,30);
        RAMBigJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(RAMBigJlb);

        JTextField daoNumJtf = new JTextField();
        daoNumJtf.setBounds(355,78,130,25);
        contentPane.add(daoNumJtf);

        pcbOpJtf = new JTextField();
        pcbOpJtf.setBounds(355,238,130,25);
        contentPane.add(pcbOpJtf);

        JTextField RAMBigJtf = new JTextField();
        RAMBigJtf.setBounds(355,118,130,25);
        contentPane.add(RAMBigJtf);

        JButton guaqiJB = new JButton("挂起");
        guaqiJB.setBounds(268, 278,105,25);
        contentPane.add(guaqiJB);

        JButton jieguaJB = new JButton("解挂");
        jieguaJB.setBounds(380, 278,105,25);
        contentPane.add(jieguaJB);

        JButton setRAMJB = new JButton("设置内存");
        setRAMJB.setBounds(268,158,217,25);
        contentPane.add(setRAMJB);

        JButton RAMVisJB = new JButton("可视化内存空间");
        RAMVisJB.setBounds(268,198,217,25);
        contentPane.add(RAMVisJB);

        JLabel pidJlb = new JLabel("pid： ");
        pidJlb.setBounds(15,75,200,30);
        pidJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(pidJlb);

        JLabel needTimeJlb = new JLabel("所需时间： ");
        needTimeJlb.setBounds(15,115,200,30);
        needTimeJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(needTimeJlb);

        JLabel priorityJlb = new JLabel("优先级： ");
        priorityJlb.setBounds(15,155,200,30);
        priorityJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(priorityJlb);

        JLabel attributeJlb = new JLabel("前驱： ");
        attributeJlb.setBounds(15,195,200,30);
        attributeJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(attributeJlb);

        JLabel ramUseJlb = new JLabel("内存占用： ");
        ramUseJlb.setBounds(15,235,200,30);
        ramUseJlb.setFont(new Font("仿宋", NORMAL, 18));
        contentPane.add(ramUseJlb);


        JTextField pidJtf = new JTextField();
        pidJtf.setBounds(100,78,130,25);
        contentPane.add(pidJtf);

        JTextField needTimeJtf = new JTextField();
        needTimeJtf.setBounds(100,118,130,25);
        contentPane.add(needTimeJtf);

        JTextField priorityJtf = new JTextField();
        priorityJtf.setBounds(100,158,130,25);
        contentPane.add(priorityJtf);

        JTextField attrbuteJtf = new JTextField();
        attrbuteJtf.setBounds(100,198,130,25);
        contentPane.add(attrbuteJtf);

        JTextField ramUseJtf = new JTextField();
        ramUseJtf.setBounds(100,238,130,25);
        contentPane.add(ramUseJtf);

        JButton newPCBJB = new JButton("新建进程");
        newPCBJB.setBounds(13,278,217,25);
        contentPane.add(newPCBJB);

        JButton runJB = new JButton("▶ 运行");
        runJB.setBounds(197,600,100,60);
        contentPane.add(runJB);

        Object[] columnNames = {"pid", "need_time", "priority", "precursor","statement", "ram","wait_time"};
        Object[][] rowData = {};
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15,355,465,200);
        contentPane.add(scrollPane);

        Object[] columnNames1 = {"ram_map"};
        Object[][] rowData1 = {};
        DefaultTableModel model1 = new DefaultTableModel(rowData1, columnNames1);
        JTable table1 = new JTable(model1);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        scrollPane1.setBounds(500,10,135,310);
        contentPane.add(scrollPane1);

        Object[] columnNames2 = {"storage_map"};
        Object[][] rowData2 = {};
        DefaultTableModel model2 = new DefaultTableModel(rowData2, columnNames2);
        JTable table2 = new JTable(model2);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setBounds(500,470,135,90);
        contentPane.add(scrollPane2);

        Object[] columnNames3 = {"suspend_queue"};
        Object[][] rowData3 = {};
        DefaultTableModel model3 = new DefaultTableModel(rowData3, columnNames3);
        JTable table3 = new JTable(model3);
        JScrollPane scrollPane3 = new JScrollPane(table3);
        scrollPane3.setBounds(500,570,135,90);
        contentPane.add(scrollPane3);

        Object[] columnNames4 = {"ready_queue"};
        Object[][] rowData4 = {};
        DefaultTableModel model4 = new DefaultTableModel(rowData4, columnNames4);
        JTable table4 = new JTable(model4);
        JScrollPane scrollPane4 = new JScrollPane(table4);
        scrollPane4.setBounds(500,332,135,130);
        contentPane.add(scrollPane4);

        JButton insertRamJB = new JButton("载入内存");
        insertRamJB.setBounds(500,630,135,29);
//        contentPane.add(insertRamJB);



        newPCBJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pidCount++;
                try {
                    int pid = Integer.parseInt(pidJtf.getText());
                    int needTime = Integer.parseInt(needTimeJtf.getText());
                    double priority = Double.parseDouble(priorityJtf.getText());
                    int attribute = Integer.parseInt(attrbuteJtf.getText());
                    int ramUse = Integer.parseInt(ramUseJtf.getText());
//                    int attr = Integer.parseInt(attrbuteJtf.getText());

                    if(pidCount == 1){
                        pcb1 = new PCB(pid, needTime, priority, 0, attribute, ramUse);
                        storage.insertStorage(pcb1);
                        pcb1.printInfo();
                    }
                    if(pidCount == 2){
                        pcb2 = new PCB(pid, needTime, priority, 0, attribute, ramUse);
                        storage.insertStorage(pcb2);
                        pcb2.printInfo();
                    }
                    if(pidCount == 3){
                        pcb3 = new PCB(pid, needTime, priority, 0, attribute, ramUse);
                        storage.insertStorage(pcb3);
                        pcb3.printInfo();
                    }
                    if(pidCount == 4){
                        pcb4 = new PCB(pid, needTime, priority, 0, attribute, ramUse);
                        storage.insertStorage(pcb4);
                        pcb4.printInfo();
                    }
                    if(pidCount == 5){
                        pcb5 = new PCB(pid, needTime, priority, 0, attribute, ramUse);
                        storage.insertStorage(pcb5);
                        pcb5.printInfo();
                    }
                    if(pidCount == 6){
                        pcb6 = new PCB(pid, needTime, priority,0, attribute, ramUse);
                        storage.insertStorage(pcb6);
                        pcb6.printInfo();
                    }
                    // 清空文本框
                    pidJtf.setText("");
                    needTimeJtf.setText("");
                    priorityJtf.setText("");
                    attrbuteJtf.setText("");
                    ramUseJtf.setText("");
                    model.setRowCount(0);
                    if(pidCount >= 1){
                        model.addRow(new Object[]{pcb1.getPID(), pcb1.getRequireTime(), pcb1.getPriority(), pcb1.getAttribute(),pcb1.getStat(), pcb1.getSize(), pcb1.getWaitTime()});
                    }
                    if(pidCount >= 2){
                        model.addRow(new Object[]{pcb2.getPID(), pcb2.getRequireTime(), pcb2.getPriority(), pcb2.getAttribute(),pcb2.getStat(), pcb2.getSize(), pcb2.getWaitTime()});
                    }
                    if(pidCount >= 3){
                        model.addRow(new Object[]{pcb3.getPID(), pcb3.getRequireTime(), pcb3.getPriority(), pcb3.getAttribute(),pcb3.getStat(), pcb3.getSize(), pcb3.getWaitTime()});
                    }
                    if(pidCount >= 4){
                        model.addRow(new Object[]{pcb4.getPID(), pcb4.getRequireTime(), pcb4.getPriority(), pcb4.getAttribute(),pcb4.getStat(), pcb4.getSize(), pcb4.getWaitTime()});
                    }
                    if(pidCount >= 5){
                        model.addRow(new Object[]{pcb5.getPID(), pcb5.getRequireTime(), pcb5.getPriority(), pcb5.getAttribute(),pcb5.getStat(), pcb5.getSize(), pcb5.getWaitTime()});
                    }
                    if(pidCount == 6){
                        model.addRow(new Object[]{pcb6.getPID(), pcb6.getRequireTime(), pcb6.getPriority(), pcb6.getAttribute(),pcb6.getStat(), pcb6.getSize(), pcb6.getWaitTime()});
                    }
                    table.repaint();

                } catch (NumberFormatException ex) {
                    // 处理转换失败的情况
                    System.out.println("输入的内容无法转换为整数");
                }
                // 清空现有的表格数据
                model2.setRowCount(0);
                for(PCB pcb : storage.storageList){
                    model2.addRow(new Object[]{pcb.getPID()});
//                    System.out.println("========"+pcb.getPID());
                }
                // 设置新的模型以更新JTable的内容
                table2.setModel(model2);
            }
        });
        setRAMJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(RAMBigJtf.getText());
                int daoNum = Integer.parseInt(daoNumJtf.getText());
                RAMBigJtf.setText("");
                daoNumJtf.setText("");
//                System.out.println(size);
//                System.out.println(daoNum);
                ram = new RAM(daoNum, size);
                int[] ramMap = ram.ramMap;
                // Create a new DefaultTableModel with "ram" as the column name
                DefaultTableModel model1 = new DefaultTableModel(new Object[][]{}, new Object[]{"ram"});

                // Populate the model with data from ramMap[]
                for (int i = 0; i < ramMap.length; i++) {
                    model1.addRow(new Object[]{ramMap[i]});
                }

                // Set the new model to table1
                table1.setModel(model1);
            }
        });

        RAMVisJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] ramMap = ram.ramMap;
                // Create a new DefaultTableModel with "ram" as the column name
                DefaultTableModel model1 = new DefaultTableModel(new Object[][]{}, new Object[]{"ram"});

                // Populate the model with data from ramMap[]
                for (int i = 0; i < ramMap.length; i++) {
                    model1.addRow(new Object[]{ramMap[i]});
                }

                // Set the new model to table1
                table1.setModel(model1);
            }
        });


        runJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpu.work();
                System.out.println("123321123321123321123321");
                // 清空现有的表格数据
                model2.setRowCount(0);
                for(PCB pcb : storage.storageList){
                    model2.addRow(new Object[]{pcb.getPID()});
//                    System.out.println("========"+pcb.getPID());
                }
                // 设置新的模型以更新JTable的内容
                table2.setModel(model2);
                RAMVisJB.doClick();
                model.setRowCount(0);
                if(pidCount >= 1){
                    model.addRow(new Object[]{pcb1.getPID(), pcb1.getRequireTime(), pcb1.getPriority(), pcb1.getAttribute(),pcb1.getStat(), pcb1.getSize(), pcb1.getWaitTime()});
                    if(pcb1.getStat() == 2){
                        cpuPid[0] = 1;
                    } else {
                        cpuPid[0] = 0;
                    }
                    if(pcb1.getRequireTime() == 0){
                        finishFlag[0] = pcb1.getPID();
                    } else {
                        finishFlag[0] = 0;
                    }
                }
                if(pidCount >= 2){
                    model.addRow(new Object[]{pcb2.getPID(), pcb2.getRequireTime(), pcb2.getPriority(), pcb2.getAttribute(),pcb2.getStat(), pcb2.getSize(), pcb2.getWaitTime()});
                    if(pcb2.getStat() == 2){
                        cpuPid[1] = 1;
                    } else {
                        cpuPid[1] = 0;
                    }
                    if(pcb2.getRequireTime() == 0){
                        finishFlag[1] = pcb2.getPID();
                    } else {
                        finishFlag[1] = 0;
                    }
                }
                if(pidCount >= 3){
                    model.addRow(new Object[]{pcb3.getPID(), pcb3.getRequireTime(), pcb3.getPriority(), pcb3.getAttribute(),pcb3.getStat(), pcb3.getSize(), pcb3.getWaitTime()});
                    if(pcb3.getStat() == 2){
                        cpuPid[2] = 1;
                    } else {
                        cpuPid[2] = 0;
                    }
                    if(pcb3.getRequireTime() == 0){
                        finishFlag[2] = pcb3.getPID();
                    } else {
                        finishFlag[2] = 0;
                    }
                }
                if(pidCount >= 4){
                    model.addRow(new Object[]{pcb4.getPID(), pcb4.getRequireTime(), pcb4.getPriority(), pcb4.getAttribute(),pcb4.getStat(), pcb4.getSize(), pcb4.getWaitTime()});
                    if(pcb4.getStat() == 2){
                        cpuPid[3] = 1;
                    } else {
                        cpuPid[3] = 0;
                    }
                    if(pcb4.getRequireTime() == 0){
                        finishFlag[3] = pcb4.getPID();
                    } else {
                        finishFlag[3] = 0;
                    }
                }
                if(pidCount >= 5){
                    model.addRow(new Object[]{pcb5.getPID(), pcb5.getRequireTime(), pcb5.getPriority(), pcb5.getAttribute(),pcb5.getStat(), pcb5.getSize(), pcb5.getWaitTime()});
                    if(pcb5.getStat() == 2){
                        cpuPid[4] = 1;
                    } else {
                        cpuPid[4] = 0;
                    }
                    if(pcb5.getRequireTime() == 0){
                        finishFlag[4] = pcb5.getPID();
                    } else {
                        finishFlag[4] = 0;
                    }
                }
                if(pidCount == 6){
                    if(pcb6.getStat() == 2){
                        cpuPid[5] = 1;
                    } else {
                        cpuPid[5] = 0;
                    }
                    model.addRow(new Object[]{pcb6.getPID(), pcb6.getRequireTime(), pcb6.getPriority(), pcb6.getAttribute(),pcb6.getStat(), pcb6.getSize(), pcb6.getWaitTime()});
                    if(pcb6.getRequireTime() == 0){
                        finishFlag[5] = pcb6.getPID();
                    } else {
                        finishFlag[5] = 0;
                    }
                }
                table.repaint();
                int count = 0;
                int i1;
                int i2;
                for(int i = 0; i < 6; i++){
                    if(cpuPid[i] == 1){
                        count++;
                    }
                }
                if(count == 1){
                    for(int i = 0; i<6;i++){
                        if (cpuPid[i] == 1){
                            i1 = i;
                            if(i == 0){
                                CPU1Jtf.setText(pcb1.getPID()+"");
                                CPU2Jtf.setText("");
                            }
                            if(i == 1){
                                CPU1Jtf.setText(pcb2.getPID()+"");
                                CPU2Jtf.setText("");
                            }
                            if(i == 2){
                                CPU1Jtf.setText(pcb3.getPID()+"");
                                CPU2Jtf.setText("");
                            }
                            if(i == 3){
                                CPU1Jtf.setText(pcb4.getPID()+"");
                                CPU2Jtf.setText("");
                            }
                            if(i == 4){
                                CPU1Jtf.setText(pcb5.getPID()+"");
                                CPU2Jtf.setText("");
                            }
                            if(i == 5){
                                CPU1Jtf.setText(pcb6.getPID()+"");
                                CPU2Jtf.setText("");
                            }
                        }
                    }
                }
                if(count == 0){
                    CPU1Jtf.setText("");
                    CPU2Jtf.setText("");
                }
                if(count == 2){
                    int i3 = 0;
                    int i4 = 0;
                    boolean flag = false;
                    for(int i = 0; i < 6; i++){
                        if(cpuPid[i] == 1 && flag == false){
                            i3 = i;
                            flag = true;
                        }
                        if(cpuPid[i] == 1 && flag == true){
                            i4 = i;
                        }
                    }
                    if(i3 == 0){
                        CPU1Jtf.setText(pcb1.getPID()+"");
                    }
                    if(i3 == 1){
                        CPU1Jtf.setText(pcb2.getPID()+"");
                    }
                    if(i3 == 2){
                        CPU1Jtf.setText(pcb3.getPID()+"");
                    }
                    if(i3 == 3){
                        CPU1Jtf.setText(pcb4.getPID()+"");
                    }
                    if(i3 == 4){
                        CPU1Jtf.setText(pcb5.getPID()+"");
                    }
                    if(i3 == 5){
                        CPU1Jtf.setText(pcb6.getPID()+"");
                    }
                    if(i4 == 0){
                        CPU2Jtf.setText(pcb1.getPID()+"");
                    }
                    if(i4 == 1){
                        CPU2Jtf.setText(pcb2.getPID()+"");
                    }
                    if(i4 == 2){
                        CPU2Jtf.setText(pcb3.getPID()+"");
                    }
                    if(i4 == 3){
                        CPU2Jtf.setText(pcb4.getPID()+"");
                    }
                    if(i4 == 4){
                        CPU2Jtf.setText(pcb5.getPID()+"");
                    }
                    if(i4 == 5){
                        CPU2Jtf.setText(pcb6.getPID()+"");
                    }
                }
                model4.setRowCount(0);
                for(PCB pcb : ram.readyQueue){
                    model4.addRow(new Object[]{pcb.getPID()});
                }
            }

        });

        jieguaJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pid = Integer.parseInt(pcbOpJtf.getText());
                for(PCB pcb : storage.waitList){
                    if(pcb.getPID() == pid){
//                        ram.removeRAM(pcb);
                        storage.removeWaitList(pcb);
//                        storage.insertStorage(pcb);
//                        storage.insertWaitList(pcb);
                        if(ram.procNum >= ram.roadNum){
                            storage.insertStorage(pcb);
                        } else {
                            ram.insertRAM(pcb);
                        }
                    }
                }
                model.setRowCount(0);
                if(pidCount >= 1){
                    model.addRow(new Object[]{pcb1.getPID(), pcb1.getRequireTime(), pcb1.getPriority(), pcb1.getAttribute(),pcb1.getStat(), pcb1.getSize(), pcb1.getWaitTime()});
                }
                if(pidCount >= 2){
                    model.addRow(new Object[]{pcb2.getPID(), pcb2.getRequireTime(), pcb2.getPriority(), pcb2.getAttribute(),pcb2.getStat(), pcb2.getSize(), pcb2.getWaitTime()});
                }
                if(pidCount >= 3){
                    model.addRow(new Object[]{pcb3.getPID(), pcb3.getRequireTime(), pcb3.getPriority(), pcb3.getAttribute(),pcb3.getStat(), pcb3.getSize(), pcb3.getWaitTime()});
                }
                if(pidCount >= 4){
                    model.addRow(new Object[]{pcb4.getPID(), pcb4.getRequireTime(), pcb4.getPriority(), pcb4.getAttribute(),pcb4.getStat(), pcb4.getSize(), pcb4.getWaitTime()});
                }
                if(pidCount >= 5){
                    model.addRow(new Object[]{pcb5.getPID(), pcb5.getRequireTime(), pcb5.getPriority(), pcb5.getAttribute(),pcb5.getStat(), pcb5.getSize(), pcb5.getWaitTime()});
                }
                if(pidCount == 6){
                    model.addRow(new Object[]{pcb6.getPID(), pcb6.getRequireTime(), pcb6.getPriority(), pcb6.getAttribute(),pcb6.getStat(), pcb6.getSize(), pcb6.getWaitTime()});
                }
                table.repaint();
                model2.setRowCount(0);
                for(PCB pcb : storage.storageList){
                    model2.addRow(new Object[]{pcb.getPID()});
//                    System.out.println("========"+pcb.getPID());
                }
                // 设置新的模型以更新JTable的内容
                table2.setModel(model2);
                RAMVisJB.doClick();
                model3.setRowCount(0);
                for(PCB pcb : storage.waitList){
                    model3.addRow(new Object[]{pcb.getPID()});
                }

                model4.setRowCount(0);
                for(PCB pcb : ram.readyQueue){
                    model4.addRow(new Object[]{pcb.getPID()});
                }
            }
        });

        guaqiJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pid = Integer.parseInt(pcbOpJtf.getText());
                for(PCB pcb : ram.readyQueue){
                    if(pcb.getPID() == pid){
                        ram.removeRAM(pcb);
                        pcb.setStat(0);
//                        storage.insertStorage(pcb);
                        storage.insertWaitList(pcb);
                    }
                }
                model.setRowCount(0);
                if(pidCount >= 1){
                    model.addRow(new Object[]{pcb1.getPID(), pcb1.getRequireTime(), pcb1.getPriority(), pcb1.getAttribute(),pcb1.getStat(), pcb1.getSize(), pcb1.getWaitTime()});
                }
                if(pidCount >= 2){
                    model.addRow(new Object[]{pcb2.getPID(), pcb2.getRequireTime(), pcb2.getPriority(), pcb2.getAttribute(),pcb2.getStat(), pcb2.getSize(), pcb2.getWaitTime()});
                }
                if(pidCount >= 3){
                    model.addRow(new Object[]{pcb3.getPID(), pcb3.getRequireTime(), pcb3.getPriority(), pcb3.getAttribute(),pcb3.getStat(), pcb3.getSize(), pcb3.getWaitTime()});
                }
                if(pidCount >= 4){
                    model.addRow(new Object[]{pcb4.getPID(), pcb4.getRequireTime(), pcb4.getPriority(), pcb4.getAttribute(),pcb4.getStat(), pcb4.getSize(), pcb4.getWaitTime()});
                }
                if(pidCount >= 5){
                    model.addRow(new Object[]{pcb5.getPID(), pcb5.getRequireTime(), pcb5.getPriority(), pcb5.getAttribute(),pcb5.getStat(), pcb5.getSize(), pcb5.getWaitTime()});
                }
                if(pidCount == 6){
                    model.addRow(new Object[]{pcb6.getPID(), pcb6.getRequireTime(), pcb6.getPriority(), pcb6.getAttribute(),pcb6.getStat(), pcb6.getSize(), pcb6.getWaitTime()});
                }
                table.repaint();
                model2.setRowCount(0);
                for(PCB pcb : storage.storageList){
                    model2.addRow(new Object[]{pcb.getPID()});
//                    System.out.println("========"+pcb.getPID());
                }
                // 设置新的模型以更新JTable的内容
                table2.setModel(model2);
                RAMVisJB.doClick();

                model3.setRowCount(0);
                for(PCB pcb : storage.waitList){
                    model3.addRow(new Object[]{pcb.getPID()});
                }
                model4.setRowCount(0);
                for(PCB pcb : ram.readyQueue){
                    model4.addRow(new Object[]{pcb.getPID()});
                }
            }
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }



    public static void main(String[] args) {
//        FlatDarkLaf.setup();
        FlatDraculaIJTheme.setup();
//        FlatNightOwlIJTheme.setup();
//        FlatCarbonIJTheme.setup();
//        FlatArcOrangeIJTheme.setup();
        System.setProperty( "apple.awt.application.appearance", "system" );
        new Simulation();
    }
}
