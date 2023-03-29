package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SlopeEntry {

    @PrimaryKey(autoGenerate = true)
    int slopeID;
    int circID;
    String slopeLocation;
    double slopeHeight;
    int slopeHasRamp;
    Integer slopeRampQnt;
    Double slopeRampIncl1;
    Double slopeRampIncl2;
    Double slopeRampIncl3;
    Double slopeRampIncl4;
    Double slopeRampIncl5;
    Double slopeRampIncl6;
    String slopeObs;
    String slopePhoto;

    public SlopeEntry(int circID, String slopeLocation, double slopeHeight, int slopeHasRamp, Integer slopeRampQnt, Double slopeRampIncl1, Double slopeRampIncl2, Double slopeRampIncl3,
                      Double slopeRampIncl4, Double slopeRampIncl5, Double slopeRampIncl6, String slopeObs, String slopePhoto) {
        this.circID = circID;
        this.slopeLocation = slopeLocation;
        this.slopeHeight = slopeHeight;
        this.slopeHasRamp = slopeHasRamp;
        this.slopeRampQnt = slopeRampQnt;
        this.slopeRampIncl1 = slopeRampIncl1;
        this.slopeRampIncl2 = slopeRampIncl2;
        this.slopeRampIncl3 = slopeRampIncl3;
        this.slopeRampIncl4 = slopeRampIncl4;
        this.slopeRampIncl5 = slopeRampIncl5;
        this.slopeRampIncl6 = slopeRampIncl6;
        this.slopeObs = slopeObs;
        this.slopePhoto = slopePhoto;
    }

    public int getSlopeID() {
        return slopeID;
    }

    public void setSlopeID(int slopeID) {
        this.slopeID = slopeID;
    }

    public int getCircID() {
        return circID;
    }

    public void setCircID(int circID) {
        this.circID = circID;
    }

    public String getSlopeLocation() {
        return slopeLocation;
    }

    public void setSlopeLocation(String slopeLocation) {
        this.slopeLocation = slopeLocation;
    }

    public double getSlopeHeight() {
        return slopeHeight;
    }

    public void setSlopeHeight(double slopeHeight) {
        this.slopeHeight = slopeHeight;
    }

    public int getSlopeHasRamp() {
        return slopeHasRamp;
    }

    public void setSlopeHasRamp(int slopeHasRamp) {
        this.slopeHasRamp = slopeHasRamp;
    }

    public Integer getSlopeRampQnt() {
        return slopeRampQnt;
    }

    public void setSlopeRampQnt(Integer slopeRampQnt) {
        this.slopeRampQnt = slopeRampQnt;
    }

    public Double getSlopeRampIncl1() {
        return slopeRampIncl1;
    }

    public void setSlopeRampIncl1(Double slopeRampIncl1) {
        this.slopeRampIncl1 = slopeRampIncl1;
    }

    public Double getSlopeRampIncl2() {
        return slopeRampIncl2;
    }

    public void setSlopeRampIncl2(Double slopeRampIncl2) {
        this.slopeRampIncl2 = slopeRampIncl2;
    }

    public Double getSlopeRampIncl3() {
        return slopeRampIncl3;
    }

    public void setSlopeRampIncl3(Double slopeRampIncl3) {
        this.slopeRampIncl3 = slopeRampIncl3;
    }

    public Double getSlopeRampIncl4() {
        return slopeRampIncl4;
    }

    public void setSlopeRampIncl4(Double slopeRampIncl4) {
        this.slopeRampIncl4 = slopeRampIncl4;
    }

    public Double getSlopeRampIncl5() {
        return slopeRampIncl5;
    }

    public void setSlopeRampIncl5(Double slopeRampIncl5) {
        this.slopeRampIncl5 = slopeRampIncl5;
    }

    public Double getSlopeRampIncl6() {
        return slopeRampIncl6;
    }

    public void setSlopeRampIncl6(Double slopeRampIncl6) {
        this.slopeRampIncl6 = slopeRampIncl6;
    }

    public String getSlopeObs() {
        return slopeObs;
    }

    public void setSlopeObs(String slopeObs) {
        this.slopeObs = slopeObs;
    }

    public String getSlopePhoto() {
        return slopePhoto;
    }

    public void setSlopePhoto(String slopePhoto) {
        this.slopePhoto = slopePhoto;
    }
}
