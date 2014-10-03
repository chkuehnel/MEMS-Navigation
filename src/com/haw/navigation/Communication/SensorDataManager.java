package com.haw.navigation.Communication;

import com.haw.navigation.Navigation.*;

import java.util.ArrayList;

/**
 * Created by chkue_000 on 28.09.2014.
 */
public class SensorDataManager {

    private ArrayList<SensorDataSet> sensorDataSetList;
    private String oldSensorData;

    public SensorDataManager() {
        ArrayList sensorDataSetList = new ArrayList();
        oldSensorData = "";
    }

    public void setSensorData(String sensorData){
        if (sensorData == null){
            return;
        }
        String newSensorData[] = (oldSensorData + sensorData).split("\n");
        for (String aNewSensorData : newSensorData) {
            String splicedSensorData[] = aNewSensorData.split(",");
            if (splicedSensorData.length < 10) {
                oldSensorData = oldSensorData +"," + aNewSensorData;
            } else {
                SensorDataSet dataSet = parseDataFromStringArray(splicedSensorData);
                if (dataSet != null){
                    sensorDataSetList.add(dataSet);
                    System.out.println("sensorDataSetList.size: " + sensorDataSetList.size());
                }
            }
        }
    }

    private SensorDataSet parseDataFromStringArray(String splicedSensorData[]){
        if (splicedSensorData != null) {
            for (String aSplicedSensorData : splicedSensorData) {
                if (aSplicedSensorData == null) {
                    return null;
                }
            }

            Float x = Float.parseFloat(splicedSensorData[0]);
            Float y = Float.parseFloat(splicedSensorData[1]);
            Float z = Float.parseFloat(splicedSensorData[2]);
            AccData accData = new AccData(x, y, z);
            x = Float.parseFloat(splicedSensorData[4]);
            y = Float.parseFloat(splicedSensorData[5]);
            z = Float.parseFloat(splicedSensorData[6]);
            GyroData gyroData = new GyroData(x, y, z);
            x = Float.parseFloat(splicedSensorData[7]);
            y = Float.parseFloat(splicedSensorData[8]);
            z = Float.parseFloat(splicedSensorData[9]);
            MagData magData = new MagData(x, y, z);
            TempData tempData = new TempData(Float.parseFloat(splicedSensorData[3]));

            return new SensorDataSet(accData, gyroData, magData, tempData);
        }
        else return null;
    }

}

