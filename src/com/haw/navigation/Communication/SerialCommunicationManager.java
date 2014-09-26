package com.haw.navigation.Communication;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 * Created by chkue_000 on 26.09.2014.
 */
public class SerialCommunicationManager implements Runnable {

    CommPortIdentifier serialPortId;
    Enumeration enumComm;
    SerialPort serialPort;
    OutputStream outputStream;
    InputStream inputStream;
    Boolean isPortOpen = false;

    int baudrate = 9600;
    int dataBits = SerialPort.DATABITS_8;
    int stopBits = SerialPort.STOPBITS_1;
    int parity = SerialPort.PARITY_NONE;
    String portName = "COM4";
    int secondsRuntime = 20;
    private boolean isOutPut = false;

    @Override
    public void run() {
        Integer secondsRemaining = secondsRuntime;
        if (!openPort(portName))
            return;

        while (secondsRemaining > 0) {
            System.out.println("Seconds remaining: " + secondsRemaining.toString() );
            secondsRemaining--;
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ignored) { }
            if (isOutPut) {
                sendMessageToPort("TEST\n");
            }
        }
        closePort();
    }

    boolean openPort(String portName)
    {
        Boolean foundPort = false;
        if (isPortOpen) {
            System.out.println("Port is already open");
            return false;
        }
        System.out.println("Open Port");
        enumComm = CommPortIdentifier.getPortIdentifiers();
        while(enumComm.hasMoreElements()) {
            serialPortId = (CommPortIdentifier) enumComm.nextElement();
            if (portName.contentEquals(serialPortId.getName())) {
                foundPort = true;
                break;
            }
        }
        if (!foundPort) {
            System.out.println("Port could not be found: " + portName);
            return false;
        }
        try {
            serialPort = (SerialPort) serialPortId.open("Open and Send", 500);
        } catch (PortInUseException e) {
            System.out.println("Port used");
        }
        try {
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) {
            System.out.println("No access to output stream.");
        }

		try {
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
			System.out.println("Keinen Zugriff auf InputStream");
		}
		try {
			serialPort.addEventListener(new serialPortEventListener());
		} catch (TooManyListenersException e) {
			System.out.println("TooManyListenersException für Serialport");
		}
		serialPort.notifyOnDataAvailable(true);

        try {
            serialPort.setSerialPortParams(baudrate, dataBits, stopBits, parity);
        } catch(UnsupportedCommOperationException e) {
            System.out.println("Could not initialize interface");
        }

        isPortOpen = true;
        return true;
    }

    void closePort()
    {
        if (isPortOpen) {
            System.out.println("Close port");
            serialPort.close();
            isPortOpen = false;
        } else {
            System.out.println("Port already closed");
        }
    }

    void sendMessageToPort(String message)
    {
        System.out.println("Send: " + message);
        if (!isPortOpen)
            return;
        try {
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            System.out.println("An error occurred while sending");
        }
    }

    void portDataAvailable() {
        try {
            byte[] data = new byte[150];
            int num;
            while(inputStream.available() > 0) {
                num = inputStream.read(data, 0, data.length);
                System.out.println("Get: "+ new String(data, 0, num));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading message.");
        }
    }

    class serialPortEventListener implements SerialPortEventListener {
        public void serialEvent(SerialPortEvent event) {
            System.out.println("serialPortEventlistener");
            switch (event.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    portDataAvailable();
                    break;
                case SerialPortEvent.BI:
                case SerialPortEvent.CD:
                case SerialPortEvent.CTS:
                case SerialPortEvent.DSR:
                case SerialPortEvent.FE:
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                case SerialPortEvent.PE:
                case SerialPortEvent.RI:
                default:
            }
        }
    }

    public boolean isOutPut() {
        return isOutPut;
    }

    public void setOutPut(boolean isOutPut) {
        this.isOutPut = isOutPut;
    }
}
