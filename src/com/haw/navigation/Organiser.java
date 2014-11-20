package com.haw.navigation;

import com.haw.navigation.Communication.SensorDataManager;
import com.haw.navigation.GUI.MEMS_GUI;
import com.haw.navigation.Navigation.QuaternionClass;
import com.haw.navigation.Navigation.SensorDataSet;
import com.haw.navigation.Navigation.SpeedWayClass;

/**
 * Created by chkue_000 on 18.10.2014.
 */
public class Organiser implements SensorDataManager.DataAvailableListener,
        SpeedWayClass.ResultAvailableListener{

    private static MEMS_GUI gui;
    private static QuaternionClass quaternionComputer;
    private static SpeedWayClass wayComputer;
    private static SensorDataManager dataManager;
    private SensorDataSet dataSet;

    public Organiser() {
        dataManager = new SensorDataManager(this);
        dataManager.setListener(this);
        quaternionComputer = new QuaternionClass();
        wayComputer = new SpeedWayClass(this);
        gui = new MEMS_GUI(dataManager);
    }

    @Override
    public void newDataAvailable() {
        System.out.println("newDataAvailable called");
        if (!dataManager.isFIFOEmpty()) {
            dataSet = dataManager.getDataSet();
            if (dataSet != null) {
                quaternionComputer.fillDCM(dataSet.getGyroData());
                wayComputer.computeWay(dataSet.getAccData(), quaternionComputer.getAngleData(), quaternionComputer.getDCM());
            }
        }
    }

    @Override
    public void resultAvailable() {
        System.out.println("resultAvailable called.");
        if (dataSet != null) {
            gui.updateLabel(quaternionComputer.getAngleData(), quaternionComputer.getQuaternion(), wayComputer.getNewSpeedWay());
        }
    }
}
