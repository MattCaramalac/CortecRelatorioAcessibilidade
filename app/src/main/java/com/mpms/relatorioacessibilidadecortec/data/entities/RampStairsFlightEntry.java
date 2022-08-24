package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = RampStairsEntry.class, parentColumns = "rampStairsID",
        childColumns = "rampStairsID", onUpdate = CASCADE, onDelete = CASCADE))
public class RampStairsFlightEntry {

    @PrimaryKey(autoGenerate = true)
    private int flightID;
    private int rampStairsID;
    @ColumnInfo(defaultValue = "1")
    private int flightNumber;
    private Double flightWidth;
    private Integer mirrorCounter;
    private Double stairMirror1;
    private Double stairMirror2;
    private Double stairMirror3;
    private Double stairMirror4;
    private Integer stepCounter;
    private Double stairStep1;
    private Double stairStep2;
    private Double stairStep3;
    private Double stairStep4;
    private Integer slopeCounter;
    private Double rampSlope1;
    private Double rampSlope2;
    private Double rampSlope3;
    private Double rampSlope4;
    private Integer signPavement;
    private String signPavementObs;
    private Integer tactileFloor;
    private String tactileFloorObs;
    private Integer borderSign;
    private Double borderSignWidth;
    private Integer borderSignIdentifiable;
    private String borderSignObs;

    public RampStairsFlightEntry(int rampStairsID, int flightNumber, Double flightWidth, Integer mirrorCounter, Double stairMirror1, Double stairMirror2,
                                 Double stairMirror3, Double stairMirror4, Integer stepCounter, Double stairStep1, Double stairStep2, Double stairStep3,
                                 Double stairStep4, Integer slopeCounter, Double rampSlope1, Double rampSlope2, Double rampSlope3, Double rampSlope4,
                                 Integer signPavement, String signPavementObs, Integer tactileFloor, String tactileFloorObs, Integer borderSign,
                                 Double borderSignWidth, Integer borderSignIdentifiable, String borderSignObs) {
        this.rampStairsID = rampStairsID;
        this.flightNumber = flightNumber;
        this.flightWidth = flightWidth;
        this.mirrorCounter = mirrorCounter;
        this.stairMirror1 = stairMirror1;
        this.stairMirror2 = stairMirror2;
        this.stairMirror3 = stairMirror3;
        this.stairMirror4 = stairMirror4;
        this.stepCounter = stepCounter;
        this.stairStep1 = stairStep1;
        this.stairStep2 = stairStep2;
        this.stairStep3 = stairStep3;
        this.stairStep4 = stairStep4;
        this.slopeCounter = slopeCounter;
        this.rampSlope1 = rampSlope1;
        this.rampSlope2 = rampSlope2;
        this.rampSlope3 = rampSlope3;
        this.rampSlope4 = rampSlope4;
        this.signPavement = signPavement;
        this.signPavementObs = signPavementObs;
        this.tactileFloor = tactileFloor;
        this.tactileFloorObs = tactileFloorObs;
        this.borderSign = borderSign;
        this.borderSignWidth = borderSignWidth;
        this.borderSignIdentifiable = borderSignIdentifiable;
        this.borderSignObs = borderSignObs;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getRampStairsID() {
        return rampStairsID;
    }

    public void setRampStairsID(int rampStairsID) {
        this.rampStairsID = rampStairsID;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Double getFlightWidth() {
        return flightWidth;
    }

    public void setFlightWidth(Double flightWidth) {
        this.flightWidth = flightWidth;
    }

    public Double getStairMirror1() {
        return stairMirror1;
    }

    public void setStairMirror1(Double stairMirror1) {
        this.stairMirror1 = stairMirror1;
    }

    public Double getStairMirror2() {
        return stairMirror2;
    }

    public void setStairMirror2(Double stairMirror2) {
        this.stairMirror2 = stairMirror2;
    }

    public Double getStairMirror3() {
        return stairMirror3;
    }

    public void setStairMirror3(Double stairMirror3) {
        this.stairMirror3 = stairMirror3;
    }

    public Double getStairMirror4() {
        return stairMirror4;
    }

    public void setStairMirror4(Double stairMirror4) {
        this.stairMirror4 = stairMirror4;
    }

    public Double getStairStep1() {
        return stairStep1;
    }

    public void setStairStep1(Double stairStep1) {
        this.stairStep1 = stairStep1;
    }

    public Double getStairStep2() {
        return stairStep2;
    }

    public void setStairStep2(Double stairStep2) {
        this.stairStep2 = stairStep2;
    }

    public Double getStairStep3() {
        return stairStep3;
    }

    public void setStairStep3(Double stairStep3) {
        this.stairStep3 = stairStep3;
    }

    public Double getStairStep4() {
        return stairStep4;
    }

    public void setStairStep4(Double stairStep4) {
        this.stairStep4 = stairStep4;
    }

    public Double getRampSlope1() {
        return rampSlope1;
    }

    public void setRampSlope1(Double rampSlope1) {
        this.rampSlope1 = rampSlope1;
    }

    public Double getRampSlope2() {
        return rampSlope2;
    }

    public void setRampSlope2(Double rampSlope2) {
        this.rampSlope2 = rampSlope2;
    }

    public Double getRampSlope3() {
        return rampSlope3;
    }

    public void setRampSlope3(Double rampSlope3) {
        this.rampSlope3 = rampSlope3;
    }

    public Double getRampSlope4() {
        return rampSlope4;
    }

    public void setRampSlope4(Double rampSlope4) {
        this.rampSlope4 = rampSlope4;
    }

    public Integer getSignPavement() {
        return signPavement;
    }

    public void setSignPavement(Integer signPavement) {
        this.signPavement = signPavement;
    }

    public String getSignPavementObs() {
        return signPavementObs;
    }

    public void setSignPavementObs(String signPavementObs) {
        this.signPavementObs = signPavementObs;
    }

    public Integer getTactileFloor() {
        return tactileFloor;
    }

    public void setTactileFloor(Integer tactileFloor) {
        this.tactileFloor = tactileFloor;
    }

    public String getTactileFloorObs() {
        return tactileFloorObs;
    }

    public void setTactileFloorObs(String tactileFloorObs) {
        this.tactileFloorObs = tactileFloorObs;
    }

    public Integer getBorderSign() {
        return borderSign;
    }

    public void setBorderSign(Integer borderSign) {
        this.borderSign = borderSign;
    }

    public Double getBorderSignWidth() {
        return borderSignWidth;
    }

    public void setBorderSignWidth(Double borderSignWidth) {
        this.borderSignWidth = borderSignWidth;
    }

    public Integer getBorderSignIdentifiable() {
        return borderSignIdentifiable;
    }

    public void setBorderSignIdentifiable(Integer borderSignIdentifiable) {
        this.borderSignIdentifiable = borderSignIdentifiable;
    }

    public String getBorderSignObs() {
        return borderSignObs;
    }

    public void setBorderSignObs(String borderSignObs) {
        this.borderSignObs = borderSignObs;
    }

    public Integer getMirrorCounter() {
        return mirrorCounter;
    }

    public void setMirrorCounter(Integer mirrorCounter) {
        this.mirrorCounter = mirrorCounter;
    }

    public Integer getStepCounter() {
        return stepCounter;
    }

    public void setStepCounter(Integer stepCounter) {
        this.stepCounter = stepCounter;
    }

    public Integer getSlopeCounter() {
        return slopeCounter;
    }

    public void setSlopeCounter(Integer slopeCounter) {
        this.slopeCounter = slopeCounter;
    }
}