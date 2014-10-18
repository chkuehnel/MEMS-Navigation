package com.haw.navigation;

import com.haw.navigation.Communication.SensorDataManager;
import com.haw.navigation.GUI.MEMS_GUI;
import com.haw.navigation.Navigation.QuaternionClass;
import com.haw.navigation.Navigation.SensorDataSet;

import java.util.ArrayList;

/**
 * Created by chkue_000 on 18.10.2014.
 */
public class Organistor implements SensorDataManager.DataAvailableListener,
        QuaternionClass.ResultAvailableListener{

    static MEMS_GUI gui;
    static QuaternionClass quaternionComputer;
    static SensorDataManager dataManager;

    public Organistor() {
        dataManager = new SensorDataManager(this);
        dataManager.setListener(this);
        quaternionComputer = new QuaternionClass(this);
        gui = new MEMS_GUI();
        gui.setDataManager(dataManager);
    }

    @Override
    public void newDataAvailable() {
        System.out.println("newDataAvailable called");
        ArrayList sensorData = dataManager.getSensorDataSetList();
        SensorDataSet dataSet = (SensorDataSet) sensorData.get(sensorData.size() - 1);
        if (dataSet != null){
            quaternionComputer.fillDCM(dataSet.getGyroData());
        }
    }

    @Override
    public void resultAvailable() {
        System.out.println("resultAvailable called.");
        ArrayList sensorData = dataManager.getSensorDataSetList();
        SensorDataSet dataSet = (SensorDataSet) sensorData.get(sensorData.size() - 1);
        if (dataSet != null) {
            gui.updateLabel(dataSet.getGyroData(), quaternionComputer.getQuaternion());
        }
    }
}
