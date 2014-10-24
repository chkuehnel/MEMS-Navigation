package com.haw.navigation.Communication;

import com.haw.navigation.Navigation.*;

/**
 * Created by chkue_000 on 28.09.2014.
 */
public class SensorDataManager {

    private DataAvailableListener listener;
    private SensorDataSet dataSet;

    public SensorDataManager(DataAvailableListener listener) {
        this.listener = listener;
    }

    public void setSensorData(String sensorData) {
        if (sensorData == null) {
            return;
        }
        String newSensorData[] = (sensorData).split("\n");
        for (String aNewSensorData : newSensorData) {
            String splicedSensorData[] = aNewSensorData.split(",");
            //if (splicedSensorData.length == 10) {
            if (splicedSensorData.length == 9) {
                dataSet = parseDataFromStringArray(splicedSensorData);
                if (dataSet != null) {
                    System.out.println(dataSet.getGyroData().getxGyroData() + " " + dataSet.getGyroData().getyGyroData()
                            + " " + dataSet.getGyroData().getzGyroData());
                }
            }
        }
        listener.newDataAvailable();
    }

    private SensorDataSet parseDataFromStringArray(String splicedSensorData[]) {
        if (splicedSensorData != null) {
            for (String aSplicedSensorData : splicedSensorData) {
                if (aSplicedSensorData == null) {
                    return null;
                }
            }

            Float x = Float.parseFloat(splicedSensorData[0]);
            Float y = Float.parseFloat(splicedSensorData[1]);
            Float z = Float.parseFloat(splicedSensorData[2]);
            GyroData gyroData = new GyroData(x, y, z);
            x = Float.parseFloat(splicedSensorData[3]);
            y = Float.parseFloat(splicedSensorData[4]);
            z = Float.parseFloat(splicedSensorData[5]);
            AccData accData = new AccData(x, y, z);
            x = Float.parseFloat(splicedSensorData[6]);
            y = Float.parseFloat(splicedSensorData[7]);
            z = Float.parseFloat(splicedSensorData[8]);
            MagData magData = new MagData(x, y, z);

            return new SensorDataSet(accData, gyroData, magData);
        } else return null;
    }

    public interface DataAvailableListener{
        public void newDataAvailable();
    }

    public void setListener(DataAvailableListener listener) {
        this.listener = listener;
    }

    public SensorDataSet getDataSet() {
        return dataSet;
    }
}

