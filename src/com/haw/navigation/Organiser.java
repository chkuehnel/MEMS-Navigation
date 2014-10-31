package com.haw.navigation;

import com.haw.navigation.Communication.SensorDataManager;
import com.haw.navigation.GUI.MEMS_GUI;
import com.haw.navigation.Navigation.QuaternionClass;
import com.haw.navigation.Navigation.SensorDataSet;

/**
 * Created by chkue_000 on 18.10.2014.
 */
public class Organiser implements SensorDataManager.DataAvailableListener,
        QuaternionClass.ResultAvailableListener{

    private static MEMS_GUI gui;
    private static QuaternionClass quaternionComputer;
    private static SensorDataManager dataManager;

    public Organiser() {
        dataManager = new SensorDataManager(this);
        dataManager.setListener(this);
        quaternionComputer = new QuaternionClass(this);
        gui = new MEMS_GUI(dataManager);
    }

    @Override
    public void newDataAvailable() {
        System.out.println("newDataAvailable called");
        SensorDataSet dataSet = dataManager.getDataSet();
        if (dataSet != null){
            quaternionComputer.fillDCM(dataSet.getGyroData());
        }
    }

    @Override
    public void resultAvailable() {
        System.out.println("resultAvailable called.");
        SensorDataSet dataSet = dataManager.getDataSet();
        if (dataSet != null) {
            //
            gui.updateLabel(dataSet.getGyroData(), quaternionComputer.getAngleData(),quaternionComputer.getQuaternion());
            //gui.updateLabel(quaternionComputer.getAngleData(), quaternionComputer.getQuaternion());
        }
    }
}
