package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class HorSinkBarParcel {

    boolean isLeft;
    double horMeasureA;
    double horMeasureB;
    double horMeasureC;
    double horMeasureD;
    double horDiam;
    String horObs;

    public HorSinkBarParcel() {
//        Empty Constructor
    }

    public HorSinkBarParcel(boolean isLeft, double horMeasureA, double horMeasureB, double horMeasureC, double horMeasureD, double horDiam, String horObs) {
        this.isLeft = isLeft;
        this.horMeasureA = horMeasureA;
        this.horMeasureB = horMeasureB;
        this.horMeasureC = horMeasureC;
        this.horMeasureD = horMeasureD;
        this.horDiam = horDiam;
        this.horObs = horObs;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public double getHorMeasureA() {
        return horMeasureA;
    }

    public void setHorMeasureA(double horMeasureA) {
        this.horMeasureA = horMeasureA;
    }

    public double getHorMeasureB() {
        return horMeasureB;
    }

    public void setHorMeasureB(double horMeasureB) {
        this.horMeasureB = horMeasureB;
    }

    public double getHorMeasureC() {
        return horMeasureC;
    }

    public void setHorMeasureC(double horMeasureC) {
        this.horMeasureC = horMeasureC;
    }

    public double getHorMeasureD() {
        return horMeasureD;
    }

    public void setHorMeasureD(double horMeasureD) {
        this.horMeasureD = horMeasureD;
    }

    public double getHorDiam() {
        return horDiam;
    }

    public void setHorDiam(double horDiam) {
        this.horDiam = horDiam;
    }

    public String getHorObs() {
        return horObs;
    }

    public void setHorObs(String horObs) {
        this.horObs = horObs;
    }
}
