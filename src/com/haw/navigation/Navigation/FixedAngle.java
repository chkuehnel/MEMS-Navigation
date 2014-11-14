package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 24.10.2014.
 */
public class FixedAngle {
    private double phi;
    private double theta;
    private double psi;

    private double normPhi;
    private double normTheta;
    private double normPsi;

    public FixedAngle(double phi, double theta, double psi) {
        this.phi = phi;
        this.theta = theta;
        this.psi = psi;

        this.normPhi = phi / 180;
        this.normTheta = theta / 180;
        this.normPsi = psi / 180;
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

    public double getNormPhi() {
        return normPhi;
    }

    public double getNormTheta() {
        return normTheta;
    }

    public double getNormPsi() {
        return normPsi;
    }
}
