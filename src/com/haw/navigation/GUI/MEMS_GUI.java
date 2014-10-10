package com.haw.navigation.GUI;

import com.haw.navigation.Communication.SensorDataManager;
import com.haw.navigation.Communication.SerialCommunicationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tower on 27.09.2014.
 */
public class MEMS_GUI {
    private JPanel panel1;

    // Menu
    JRadioButtonMenuItem comPort3;
    JRadioButtonMenuItem comPort4;
    JRadioButtonMenuItem comPort9;

    private JTextField textField_Vx;
    private JTextField textField_Vy;
    private JTextField textField_Vz;
    private JTextField textField_Roll;
    private JTextField textField_Pitch;
    private JTextField textField_Yaw;
    private JTextField textField_q2;
    private JTextField textField_q1;
    private JTextField textField_q3;
    private JTextField textField_q4;
    private JButton startButton;
    private JButton pauseButton;

   private String portName;

    private boolean isRunning = false;

    public void init() {
        JMenuBar bar = getJMenuBar();
        JFrame frame = new JFrame("MEMS_GUI");
        frame.setContentPane(new MEMS_GUI().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addClickListener();
        frame.setJMenuBar(bar);

        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }

    private JMenuBar getJMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("COM-Port");
        bar.add(fileMenu);
        comPort3 = new JRadioButtonMenuItem("COM3");
        comPort4 = new JRadioButtonMenuItem("COM4");
        comPort9 = new JRadioButtonMenuItem("COM9");
        fileMenu.add(comPort3);
        fileMenu.add(comPort4);
        fileMenu.add(comPort9);
        return bar;
    }


    private void addClickListener() {
        comPort3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comPort4.setSelected(false);
                comPort9.setSelected(false);
                setPortName("COM3");
            }
        });
        comPort4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comPort3.setSelected(false);
                comPort9.setSelected(false);
                setPortName("COM4");
            }
        });
        comPort9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comPort3.setSelected(false);
                comPort4.setSelected(false);
                setPortName("COM9");
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = true;
                startCommunication();
            }

        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = false;
            }
        });
    }

    private void startCommunication() {

        SensorDataManager dataManager = new SensorDataManager();
        SerialCommunicationManager runnable = new SerialCommunicationManager(getPortName(), dataManager, false);

        while (isRunning){
            new Thread(runnable).start();
        }
    }



    public String getPortName() {
        if (this.portName == null)
            return portName = "";

        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }
}
