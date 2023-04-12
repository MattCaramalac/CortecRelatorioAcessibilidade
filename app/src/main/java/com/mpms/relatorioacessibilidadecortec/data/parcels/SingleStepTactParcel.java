package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class SingleStepTactParcel {

    Integer lowTact;
    Double lowDist;
    Double lowWidth;
    Integer lowAntiDrift;
    Integer lowTactContrast;
    Integer lowVisualContrast;
    Integer upTact;
    Double upDist;
    Double upWidth;
    Integer upAntiDrift;
    Integer upTactContrast;
    Integer upVisualContrast;

    public SingleStepTactParcel() {
    }

    public SingleStepTactParcel(Integer lowTact, Double lowDist, Double lowWidth, Integer lowAntiDrift, Integer lowTactContrast, Integer lowVisualContrast, Integer upTact,
                                Double upDist, Double upWidth, Integer upAntiDrift, Integer upTactContrast, Integer upVisualContrast) {
        this.lowTact = lowTact;
        this.lowDist = lowDist;
        this.lowWidth = lowWidth;
        this.lowAntiDrift = lowAntiDrift;
        this.lowTactContrast = lowTactContrast;
        this.lowVisualContrast = lowVisualContrast;
        this.upTact = upTact;
        this.upDist = upDist;
        this.upWidth = upWidth;
        this.upAntiDrift = upAntiDrift;
        this.upTactContrast = upTactContrast;
        this.upVisualContrast = upVisualContrast;
    }

    public Integer getLowTact() {
        return lowTact;
    }

    public void setLowTact(Integer lowTact) {
        this.lowTact = lowTact;
    }

    public Double getLowDist() {
        return lowDist;
    }

    public void setLowDist(Double lowDist) {
        this.lowDist = lowDist;
    }

    public Double getLowWidth() {
        return lowWidth;
    }

    public void setLowWidth(Double lowWidth) {
        this.lowWidth = lowWidth;
    }

    public Integer getLowAntiDrift() {
        return lowAntiDrift;
    }

    public void setLowAntiDrift(Integer lowAntiDrift) {
        this.lowAntiDrift = lowAntiDrift;
    }

    public Integer getLowTactContrast() {
        return lowTactContrast;
    }

    public void setLowTactContrast(Integer lowTactContrast) {
        this.lowTactContrast = lowTactContrast;
    }

    public Integer getLowVisualContrast() {
        return lowVisualContrast;
    }

    public void setLowVisualContrast(Integer lowVisualContrast) {
        this.lowVisualContrast = lowVisualContrast;
    }

    public Integer getUpTact() {
        return upTact;
    }

    public void setUpTact(Integer upTact) {
        this.upTact = upTact;
    }

    public Double getUpDist() {
        return upDist;
    }

    public void setUpDist(Double upDist) {
        this.upDist = upDist;
    }

    public Double getUpWidth() {
        return upWidth;
    }

    public void setUpWidth(Double upWidth) {
        this.upWidth = upWidth;
    }

    public Integer getUpAntiDrift() {
        return upAntiDrift;
    }

    public void setUpAntiDrift(Integer upAntiDrift) {
        this.upAntiDrift = upAntiDrift;
    }

    public Integer getUpTactContrast() {
        return upTactContrast;
    }

    public void setUpTactContrast(Integer upTactContrast) {
        this.upTactContrast = upTactContrast;
    }

    public Integer getUpVisualContrast() {
        return upVisualContrast;
    }

    public void setUpVisualContrast(Integer upVisualContrast) {
        this.upVisualContrast = upVisualContrast;
    }
}
