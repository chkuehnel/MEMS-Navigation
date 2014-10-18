package com.haw.navigation;

import com.haw.navigation.Communication.SensorDataManager;
import com.haw.navigation.Communication.SerialCommunicationManager;
import com.haw.navigation.GUI.MEMS_GUI;
import com.haw.navigation.Navigation.QuaternionClass;
import gnu.io.*;

import java.util.Enumeration;

public class Main{


    public static void main(String[] args) {

        Organistor organistor = new Organistor();

        System.out.println("Program started");

        //System.out.println(java.library.path);
  /*      CommPortIdentifier serialPortId;
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
*/


        System.out.println("Finished successfully");
    }
}
