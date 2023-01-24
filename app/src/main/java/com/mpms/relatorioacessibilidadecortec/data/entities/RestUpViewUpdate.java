package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestUpViewUpdate {

    @PrimaryKey
    private int restroomID;
    private Double upViewLength;
    private Double upViewWidth;
    private Double upViewMeasureA;
    private Double upViewMeasureB;
    private Double upViewMeasureC;
    private Double upViewMeasureD;
    private Double upViewMeasureE;
    private String upViewObs;

    public RestUpViewUpdate(int restroomID, double upViewLength, double upViewWidth, Double upViewMeasureA, double upViewMeasureB,
                            Double upViewMeasureC, double upViewMeasureD, Double upViewMeasureE, String upViewObs) {
        this.restroomID = restroomID;
        this.upViewLength = upViewLength;
        this.upViewWidth = upViewWidth;
        this.upViewMeasureA = upViewMeasureA;
        this.upViewMeasureB = upViewMeasureB;
        this.upViewMeasureC = upViewMeasureC;
        this.upViewMeasureD = upViewMeasureD;
        this.upViewMeasureE = upViewMeasureE;
        this.upViewObs = upViewObs;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public double getUpViewLength() {
        return upViewLength;
    }

    public void setUpViewLength(double upViewLength) {
        this.upViewLength = upViewLength;
    }

    public double getUpViewWidth() {
        return upViewWidth;
    }

    public void setUpViewWidth(double upViewWidth) {
        this.upViewWidth = upViewWidth;
    }

    public Double getUpViewMeasureA() {
        return upViewMeasureA;
    }

    public void setUpViewMeasureA(Double upViewMeasureA) {
        this.upViewMeasureA = upViewMeasureA;
    }

    public double getUpViewMeasureB() {
        return upViewMeasureB;
    }

    public void setUpViewMeasureB(double upViewMeasureB) {
        this.upViewMeasureB = upViewMeasureB;
    }

    public Double getUpViewMeasureC() {
        return upViewMeasureC;
    }

    public void setUpViewMeasureC(Double upViewMeasureC) {
        this.upViewMeasureC = upViewMeasureC;
    }

    public double getUpViewMeasureD() {
        return upViewMeasureD;
    }

    public void setUpViewMeasureD(double upViewMeasureD) {
        this.upViewMeasureD = upViewMeasureD;
    }

    public Double getUpViewMeasureE() {
        return upViewMeasureE;
    }

    public void setUpViewMeasureE(Double upViewMeasureE) {
        this.upViewMeasureE = upViewMeasureE;
    }

    public String getUpViewObs() {
        return upViewObs;
    }

    public void setUpViewObs(String upViewObs) {
        this.upViewObs = upViewObs;
    }
}
