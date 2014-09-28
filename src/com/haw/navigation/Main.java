package com.haw.navigation;

import com.haw.navigation.Communication.SensorDataManager;
import com.haw.navigation.Communication.SerialCommunicationManager;
import gnu.io.*;

import java.util.Enumeration;

public class Main {

    public static void main(String[] args) {
        System.out.println("Program started");
        SensorDataManager dataManager = new SensorDataManager();
        //System.out.println(java.library.path);
        CommPortIdentifier serialPortId;
        //static CommPortIdentifier sSerialPortId;
        Enumeration enumComm;
        //SerialPort serialPort;

        enumComm = CommPortIdentifier.getPortIdentifiers();
        while (enumComm.hasMoreElements()) {
            serialPortId = (CommPortIdentifier) enumComm.nextElement();
            if(serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(serialPortId.getName());
            }
        }

        SerialCommunicationManager runnable = new SerialCommunicationManager("COM3", dataManager, false);
        new Thread(runnable).start();
        runnable.closePort();

        System.out.println("Finished successfully");
    }
}
