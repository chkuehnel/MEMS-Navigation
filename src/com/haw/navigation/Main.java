package com.haw.navigation;

public class Main{


    public static void main(String[] args) {

        Organiser organiser = new Organiser();

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
