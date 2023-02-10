package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class VertSinkBarParcel {

    boolean frontBar;
    double measureA;
    double measureB;
    double measureC;
    Double measureD;
    Double measureE;
    double diam;
    double dist;
    String obs;

    public VertSinkBarParcel() {
//        Empty Constructor
    }

    public VertSinkBarParcel(boolean frontBar, double measureA, double measureB, double measureC, Double measureD, Double measureE, double diam, double dist, String obs) {
        this.frontBar = frontBar;
        this.measureA = measureA;
        this.measureB = measureB;
        this.measureC = measureC;
        this.measureD = measureD;
        this.measureE = measureE;
        this.diam = diam;
        this.dist = dist;
        this.obs = obs;
    }

    public boolean isFrontBar() {
        return frontBar;
    }

    public void setFrontBar(boolean frontBar) {
        this.frontBar = frontBar;
    }

    public double getMeasureA() {
        return measureA;
    }

    public void setMeasureA(double measureA) {
        this.measureA = measureA;
    }

    public double getMeasureB() {
        return measureB;
    }

    public void setMeasureB(double measureB) {
        this.measureB = measureB;
    }

    public double getMeasureC() {
        return measureC;
    }

    public void setMeasureC(double measureC) {
        this.measureC = measureC;
    }

    public Double getMeasureD() {
        return measureD;
    }

    public void setMeasureD(Double measureD) {
        this.measureD = measureD;
    }

    public Double getMeasureE() {
        return measureE;
    }

    public void setMeasureE(Double measureE) {
        this.measureE = measureE;
    }

    public double getDiam() {
        return diam;
    }

    public void setDiam(double diam) {
        this.diam = diam;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
