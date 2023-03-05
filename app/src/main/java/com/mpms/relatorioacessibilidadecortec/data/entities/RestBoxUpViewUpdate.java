package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;

@Entity
public class RestBoxUpViewUpdate {
    private int boxID;
    private Double upViewLength;
    private Double upViewWidth;
    private Double upViewMeasureA;
    private Double upViewMeasureB;
    private Double upViewMeasureC;
    private Double upViewMeasureD;
    private String upViewObs;
    private Integer restDrain;
    private String restDrainObs;

    public RestBoxUpViewUpdate(int boxID, Double upViewLength, Double upViewWidth, Double upViewMeasureA, Double upViewMeasureB, Double upViewMeasureC,
                               Double upViewMeasureD, String upViewObs, Integer restDrain, String restDrainObs) {
        this.boxID = boxID;
        this.upViewLength = upViewLength;
        this.upViewWidth = upViewWidth;
        this.upViewMeasureA = upViewMeasureA;
        this.upViewMeasureB = upViewMeasureB;
        this.upViewMeasureC = upViewMeasureC;
        this.upViewMeasureD = upViewMeasureD;
        this.upViewObs = upViewObs;
        this.restDrain = restDrain;
        this.restDrainObs = restDrainObs;
    }

    public int getBoxID() {
        return boxID;
    }

    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }

    public Double getUpViewLength() {
        return upViewLength;
    }

    public void setUpViewLength(Double upViewLength) {
        this.upViewLength = upViewLength;
    }

    public Double getUpViewWidth() {
        return upViewWidth;
    }

    public void setUpViewWidth(Double upViewWidth) {
        this.upViewWidth = upViewWidth;
    }

    public Double getUpViewMeasureA() {
        return upViewMeasureA;
    }

    public void setUpViewMeasureA(Double upViewMeasureA) {
        this.upViewMeasureA = upViewMeasureA;
    }

    public Double getUpViewMeasureB() {
        return upViewMeasureB;
    }

    public void setUpViewMeasureB(Double upViewMeasureB) {
        this.upViewMeasureB = upViewMeasureB;
    }

    public Double getUpViewMeasureC() {
        return upViewMeasureC;
    }

    public void setUpViewMeasureC(Double upViewMeasureC) {
        this.upViewMeasureC = upViewMeasureC;
    }

    public Double getUpViewMeasureD() {
        return upViewMeasureD;
    }

    public void setUpViewMeasureD(Double upViewMeasureD) {
        this.upViewMeasureD = upViewMeasureD;
    }

    public String getUpViewObs() {
        return upViewObs;
    }

    public void setUpViewObs(String upViewObs) {
        this.upViewObs = upViewObs;
    }

    public Integer getRestDrain() {
        return restDrain;
    }

    public void setRestDrain(Integer restDrain) {
        this.restDrain = restDrain;
    }

    public String getRestDrainObs() {
        return restDrainObs;
    }

    public void setRestDrainObs(String restDrainObs) {
        this.restDrainObs = restDrainObs;
    }
}
