package com.haw.navigation.GUI;

import com.haw.navigation.Communication.SensorDataManager;
import com.haw.navigation.Communication.SerialCommunicationManager;
import com.haw.navigation.Navigation.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Tower on 27.09.2014.
 */
public class MEMS_GUI extends JFrame implements SerialCommunicationManager.UpdateGuiListener{
    // Menu
    private JRadioButtonMenuItem comPort3;
    private JRadioButtonMenuItem comPort4;
    private JRadioButtonMenuItem comPort9;
    private JPanel panel1;
    private JButton startButton;
    private JLabel statusLabel;
    private JLabel rollLabel;
    private JLabel pitchLabel;
    private JLabel yawLabel;
    private JLabel q1Label;
    private JLabel q2Label;
    private JLabel q3Label;
    private JLabel q4Label;
    private JLabel portStatusLabel;
    private JLabel portInformationLabel;
    private JLabel vxLabel;
    private JLabel vyLabel;
    private JLabel vzLabel;
    private JLabel wayXLabel;
    private JLabel wayYLabel;
    private JLabel wayZLabel;
    private JLabel compass;
    private Boolean isPortSelected = false;
    private String portName;

    private boolean isRunning = false;
    private final SerialCommunicationManager runnable;
    private SensorDataManager dataManager;
    private PrintWriter writer;


    public MEMS_GUI(SensorDataManager dataManager) {
        this.dataManager = dataManager;
        JPanel panel = panel1;
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar bar = getJMenuBar();
        addClickListener();
        setJMenuBar(bar);

        runnable = new SerialCommunicationManager(getPortName(), this.dataManager, false, this);
        updateStatus();

        pack();
        validate();
        setVisible(true);
    }

    public void updateStatus() {

        // portInformationLabel
        ArrayList portList = runnable.getConnectedPorts();
        String portInformation = "No Device detected";
        if (portList.size() == 1) {
            portName = portList.get(0).toString();
            portInformation = "Device detected on " + portName;
            portInformationLabel.setText(portInformation);
            isPortSelected = true;
        }else if (portList.size() > 1){
            portInformation = "Device detected on " + portList.get(0);
            portName = portList.get(0).toString();
            isPortSelected = true;
            for (int i = 1; i < portList.size(); i++){
                portInformation = portInformation + "and " + portList.get(i);
            }
            portInformation = portInformation + ".\n Please select Port in Menu!";
        }
        portInformationLabel.setText(portInformation);

        // portStatusLabel
        String portStatus ="";
        switch (runnable.getPortStatus()){
            case SerialCommunicationManager.PORT_CONNECTED:
                portStatus = "Connection established.";
                break;
            case SerialCommunicationManager.PORT_CLOSED:
                portStatus = "Not connected.";
                break;
            case SerialCommunicationManager.PORT_ERROR:
                portStatus = "ERROR!";
                break;
        }
        portStatusLabel.setText(portStatus);
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
                if (isRunning) {
                    stopCommunication();
                    startButton.setText("Start");
                    isRunning = false;
                } else {
                    if (isPortSelected) {
                        isRunning = true;
                        startCommunication();
                        isRunning = true;
                        startButton.setText("Stop");
                    }
                }
            }
        });
    }

    private void stopCommunication() {
        runnable.setAlive(false);
        writer.close();
    }

    private void startCommunication() {
        Thread thread = new Thread(runnable);
        runnable.setAlive(true);
        runnable.setPortName(portName);
        thread.start();
        makeFile();
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

    private void makeFile() {
        try {
            writer = new PrintWriter("testData.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
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

    public void setDataManager(SensorDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void updateLabel(GyroData gyroData, Quaternion quaternion) {
        System.out.println("updateLabel called.");
        DecimalFormat format = new DecimalFormat("#.####");
        rollLabel.setText(format.format(gyroData.getxGyroData()));
        pitchLabel.setText(format.format(gyroData.getyGyroData()));
        yawLabel.setText(format.format(gyroData.getzGyroData()));

        q1Label.setText(format.format(quaternion.getQ0()));
        q2Label.setText(format.format(quaternion.getQ1()));
        q3Label.setText(format.format(quaternion.getQ2()));
        q4Label.setText(format.format(quaternion.getQ3()));
        updateStatus();
    }

    public void updateLabel(FixedAngle angleData, Quaternion quaternion) {
        System.out.println("updateLabel called.");
        DecimalFormat format = new DecimalFormat("#.####");
        rollLabel.setText(format.format(angleData.getPhi()));
        pitchLabel.setText(format.format(angleData.getTheta()));
        yawLabel.setText(format.format(angleData.getPsi()));

        q1Label.setText(format.format(quaternion.getQ0()));
        q2Label.setText(format.format(quaternion.getQ1()));
        q3Label.setText(format.format(quaternion.getQ2()));
        q4Label.setText(format.format(quaternion.getQ3()));
        updateStatus();
    }

    public void updateLabel(FixedAngle angleData, Quaternion quaternion, SpeedWayData speedWayData, ECompass eCompass) {
        System.out.println("updateLabel called.");
        DecimalFormat format = new DecimalFormat("#.####");
        rollLabel.setText(format.format(angleData.getPhi()));
        pitchLabel.setText(format.format(angleData.getTheta()));
        yawLabel.setText(format.format(angleData.getPsi()));

        q1Label.setText(format.format(quaternion.getQ0()));
        q2Label.setText(format.format(quaternion.getQ1()));
        q3Label.setText(format.format(quaternion.getQ2()));
        q4Label.setText(format.format(quaternion.getQ3()));

        vxLabel.setText(format.format(speedWayData.getSpeedX()));
        vyLabel.setText(format.format(speedWayData.getSpeedY()));
        vzLabel.setText(format.format(speedWayData.getSpeedZ()));

        wayXLabel.setText(format.format(speedWayData.getWayX()));
        wayYLabel.setText(format.format(speedWayData.getWayY()));
        wayZLabel.setText(format.format(speedWayData.getWayZ()));

        compass.setText(format.format(eCompass.getCompass()*180/Math.PI));

        updateStatus();
    }

    public void updateLabel(GyroData angleData, Quaternion quaternion, SpeedWayData speedWayData, ECompass eCompass, SensorDataSet dataSet) {
        System.out.println("updateLabel called.");
        DecimalFormat format = new DecimalFormat("#.####");
        rollLabel.setText(format.format(angleData.getxGyroData()));
        pitchLabel.setText(format.format(angleData.getyGyroData()));
        yawLabel.setText(format.format(angleData.getzGyroData()));

        q1Label.setText(format.format(quaternion.getQ0()));
        q2Label.setText(format.format(quaternion.getQ1()));
        q3Label.setText(format.format(quaternion.getQ2()));
        q4Label.setText(format.format(quaternion.getQ3()));

        vxLabel.setText(format.format(dataSet.getAccData().getxAccData()));
        vyLabel.setText(format.format(dataSet.getAccData().getyAccData()));
        vzLabel.setText(format.format(dataSet.getAccData().getzAccData()));

        wayXLabel.setText(format.format(dataSet.getMagData().getxMagData()));
        wayYLabel.setText(format.format(dataSet.getMagData().getyMagData()));
        wayZLabel.setText(format.format(dataSet.getMagData().getzMagData()));

        compass.setText(format.format(eCompass.getCompass()*180/Math.PI));
/*
        GyroData gyroData = dataSet.getGyroData();
        AccData accData = dataSet.getAccData();
        MagData magData = dataSet.getMagData();

        writer.println(gyroData.getxGyroData() + ";" + gyroData.getyGyroData() + ";" +  gyroData.getzGyroData() + ";" +
                        accData.getxAccData() + ";" +  accData.getyAccData() + ";" +  accData.getzAccData() + ";" +
                magData.getxMagData() + ";" +  magData.getyMagData() + ";" +  magData.getzMagData());
        writer.flush();
*/
        updateStatus();
    }
}
