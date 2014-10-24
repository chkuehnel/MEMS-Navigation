package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 24.10.2014.
 */
public class FixedAngle {
    private double phi;
    private double theta;
    private double psi;

    public FixedAngle(double phi, double theta, double psi) {
        this.phi = phi;
        this.theta = theta;
        this.psi = psi;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getPsi() {
        return psi;
    }

    public void setPsi(double psi) {
        this.psi = psi;
    }
}
