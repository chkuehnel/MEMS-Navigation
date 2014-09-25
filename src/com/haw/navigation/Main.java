package com.haw.navigation;

import com.haw.navigation.USB.UsbController;

import javax.usb.UsbDevice;
import javax.usb.UsbException;
import javax.usb.UsbHub;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Main {
    // defines special USB device, here we need specific flyduino IDs
    private static final Short vendorId = 0x090C;
    private static final Short productId = 0x1000;

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello World!");
        UsbController controller = new UsbController();
        UsbHub rootHub = controller.getRootHub();
        System.out.println("rootHub number of ports:" + rootHub.getNumberOfPorts());


        UsbDevice device = controller.findDevice(rootHub, vendorId, productId);

        List deviceList = rootHub.getAttachedUsbDevices();
        System.out.println("deviceList, number ob devices: " + deviceList.size());
        System.out.println("device: " + deviceList.get(0).toString());
    }
}
