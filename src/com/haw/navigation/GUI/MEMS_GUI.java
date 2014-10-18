package com.haw.navigation.GUI;

import com.haw.navigation.Communication.SensorDataManager;
import com.haw.navigation.Communication.SerialCommunicationManager;
import com.haw.navigation.Navigation.GyroData;
import com.haw.navigation.Navigation.Quaternion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Created by Tower on 27.09.2014.
 */
public class MEMS_GUI extends JFrame{
    private JPanel panel1;

    // Menu
    JRadioButtonMenuItem comPort3;
    JRadioButtonMenuItem comPort4;
    JRadioButtonMenuItem comPort9;

    private JButton startButton;
    private JLabel statusLabel;
    private JLabel rollLabel;
    private JLabel pitchLabel;
    private JLabel yawLabel;
    private JLabel q1Label;
    private JLabel q2Label;
    private JLabel q3Label;
    private JLabel q4Label;
    private Boolean isPortSelected = false;
    private String portName;

    private boolean isRunning = false;
    private Thread thread;
    private SerialCommunicationManager runnable;
    private SensorDataManager dataManager;


    public MEMS_GUI() {
        JPanel panel = panel1;
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar bar = getJMenuBar();
        addClickListener();
        setJMenuBar(bar);

        pack();
        validate();
        setVisible(true);
    }

    public JMenuBar getJMenuBar() {
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
                isPortSelected = true;
                comPort4.setSelected(false);
                comPort9.setSelected(false);
                setPortName("COM3");
            }
        });
        comPort4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPortSelected = true;
                comPort3.setSelected(false);
                comPort9.setSelected(false);
                setPortName("COM4");
            }
        });
        comPort9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPortSelected = true;
                comPort3.setSelected(false);
                comPort4.setSelected(false);
                setPortName("COM9");
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning){
                    stopCommunication();
                    statusLabel.setText("Communication stoped.");
                    startButton.setText("Start");
                    isRunning = false;
                }else {
                    if (isPortSelected) {
                        isRunning = true;
                        statusLabel.setText("Connected");
                        startCommunication();
                        isRunning = true;
                        startButton.setText("Stop");
                    } else {
                        statusLabel.setText("Please select PORT!");
                    }
                }
            }
        });

    }

    private void stopCommunication() {
        runnable.setAlive(false);
    }

    private void startCommunication() {
        runnable = new SerialCommunicationManager(getPortName(), dataManager, false);
        thread = new Thread(runnable);
        runnable.setAlive(true);
        runnable.setPortName(portName);
        thread.start();
        /* //Test code to measure communication cycles per second.
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        startButton.doClick();
                    }
                },
                1000
        );
        */
    }



    public String getPortName() {
        if (this.portName == null)
            return portName = "";

        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public void setDataManager(SensorDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void updateLabel(GyroData gyroData, Quaternion quaternion) {
        System.out.println("updateLabel called.");
        DecimalFormat format = new DecimalFormat("#.####");
        rollLabel.setText(format.format(gyroData.getxGyroData()));
        pitchLabel.setText(format.format(gyroData.getyGyroData()));
        yawLabel.setText(format.format(gyroData.getzGyroData()));

        q1Label.setText(format.format(quaternion.getQ1()));
        q2Label.setText(format.format(quaternion.getQ2()));
        q3Label.setText(format.format(quaternion.getQ3()));
        q4Label.setText(format.format(quaternion.getQ4()));

    }
}
