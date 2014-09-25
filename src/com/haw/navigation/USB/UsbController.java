package com.haw.navigation.USB;

import javax.usb.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chkue_000 on 24.09.2014.
 */
public class UsbController {

    /**
     * gets root hub from javax library
     * @return UsbHub rootHub may be null!
     */
    public UsbHub getRootHub(){
        UsbHub rootHub = null;
        try {
            UsbServices services = UsbHostManager.getUsbServices();
            rootHub = services.getRootUsbHub();
        } catch (UsbException e) {
            e.printStackTrace();
        }
        return rootHub;
    }

    /**
     *
     * @param hub which has to be checked for devices
     * @return ArrayList of devices, may be empty
     */
    public List getDeviceList(UsbHub hub){
        List deviceList = new ArrayList();
        if (hub != null){
            deviceList = hub.getAttachedUsbDevices();
        }
        return deviceList;
    }

    public UsbDevice findDevice(UsbHub hub, short vendorId, short productId)
    {
        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices())
        {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if (desc.idVendor() == vendorId && desc.idProduct() == productId) return device;
            if (device.isUsbHub())
            {
                device = findDevice((UsbHub) device, vendorId, productId);
                if (device != null) return device;
            }
        }
        return null;
    }
}
