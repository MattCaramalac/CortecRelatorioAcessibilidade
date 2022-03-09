package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = RestroomEntry.class, parentColumns = "restroomID",
        childColumns = "restroomID", onDelete = CASCADE, onUpdate = CASCADE))
public class RestroomUpViewEntry {

    @PrimaryKey(autoGenerate = true)
    private int upViewID;
    private int restroomID;
    private double upViewMeasureA;
    private double upViewMeasureB;
    private double upViewMeasureC;
    private double upViewMeasureD;
    private double upViewMeasureE;
    private String upViewObs;

    public RestroomUpViewEntry(int restroomID, double upViewMeasureA, double upViewMeasureB, double upViewMeasureC,
                               double upViewMeasureD, double upViewMeasureE, String upViewObs) {
        this.restroomID = restroomID;
        this.upViewMeasureA = upViewMeasureA;
        this.upViewMeasureB = upViewMeasureB;
        this.upViewMeasureC = upViewMeasureC;
        this.upViewMeasureD = upViewMeasureD;
        this.upViewMeasureE = upViewMeasureE;
        this.upViewObs = upViewObs;
    }

    public int getUpViewID() {
        return upViewID;
    }

    public void setUpViewID(int upViewID) {
        this.upViewID = upViewID;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public double getUpViewMeasureA() {
        return upViewMeasureA;
    }

    public void setUpViewMeasureA(double upViewMeasureA) {
        this.upViewMeasureA = upViewMeasureA;
    }

    public double getUpViewMeasureB() {
        return upViewMeasureB;
    }

    public void setUpViewMeasureB(double upViewMeasureB) {
        this.upViewMeasureB = upViewMeasureB;
    }

    public double getUpViewMeasureC() {
        return upViewMeasureC;
    }

    public void setUpViewMeasureC(double upViewMeasureC) {
        this.upViewMeasureC = upViewMeasureC;
    }

    public double getUpViewMeasureD() {
        return upViewMeasureD;
    }

    public void setUpViewMeasureD(double upViewMeasureD) {
        this.upViewMeasureD = upViewMeasureD;
    }

    public double getUpViewMeasureE() {
        return upViewMeasureE;
    }

    public void setUpViewMeasureE(double upViewMeasureE) {
        this.upViewMeasureE = upViewMeasureE;
    }

    public String getUpViewObs() {
        return upViewObs;
    }

    public void setUpViewObs(String upViewObs) {
        this.upViewObs = upViewObs;
    }
}
