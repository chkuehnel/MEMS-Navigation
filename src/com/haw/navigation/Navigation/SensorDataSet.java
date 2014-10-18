package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 28.09.2014.
 */
public class SensorDataSet {

    private AccData accData;
    private GyroData gyroData;
    private MagData magData;

    public SensorDataSet(AccData accData, GyroData gyroData, MagData magData) {
        this.accData = accData;
        this.gyroData = gyroData;
        this.magData = magData;
    }

    public SensorDataSet() {
    }

    public AccData getAccData() {
        return accData;
    }

    public void setAccData(AccData accData) {
        this.accData = accData;
    }

    public GyroData getGyroData() {
        return gyroData;
    }

    public void setGyroData(GyroData gyroData) {
        this.gyroData = gyroData;
    }

    public MagData getMagData() {
        return magData;
    }

    public void setMagData(MagData magData) {
        this.magData = magData;
    }
}
