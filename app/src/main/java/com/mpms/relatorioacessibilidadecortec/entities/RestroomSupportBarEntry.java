package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = RestroomEntry.class, parentColumns = "restroomID",
        childColumns = "restroomID", onDelete = CASCADE, onUpdate = CASCADE))
public class RestroomSupportBarEntry {

    @PrimaryKey(autoGenerate = true)
    private int supBarID;
    private int restroomID;
    private double supBarDiameter;
    private double supBarMeasureA;
    private double supBarMeasureB;
    private double supBarMeasureC;
    private double supBarMeasureD;
    private double supBarMeasureE;
    private double supBarMeasureF;
    private double supBarMeasureG;
    private double supBarMeasureH;
    private double supBarMeasureI;
    private double supBarMeasureJ;
    private String supBarObs;
    private double toiletHeight;
    private double toiletFlushHeight;
    private int paperHolderType;
    private double paperHolderDistance;
    private double paperHolderHeight;
    private String paperHolderObs;
    private int hasEmergencySignal;
    private Double emergencySignalHeight;
    private String emergencySignalObs;
    private int hasBidet;
    private String bidetObs;

    public RestroomSupportBarEntry(int restroomID, double supBarDiameter, double supBarMeasureA, double supBarMeasureB,
                                   double supBarMeasureC, double supBarMeasureD, double supBarMeasureE, double supBarMeasureF,
                                   double supBarMeasureG, double supBarMeasureH, double supBarMeasureI, double supBarMeasureJ,
                                   String supBarObs, double toiletHeight, double toiletFlushHeight, int paperHolderType,
                                   double paperHolderDistance, double paperHolderHeight, String paperHolderObs,
                                   int hasEmergencySignal, Double emergencySignalHeight, String emergencySignalObs,
                                   int hasBidet, String bidetObs) {
        this.restroomID = restroomID;
        this.supBarDiameter = supBarDiameter;
        this.supBarMeasureA = supBarMeasureA;
        this.supBarMeasureB = supBarMeasureB;
        this.supBarMeasureC = supBarMeasureC;
        this.supBarMeasureD = supBarMeasureD;
        this.supBarMeasureE = supBarMeasureE;
        this.supBarMeasureF = supBarMeasureF;
        this.supBarMeasureG = supBarMeasureG;
        this.supBarMeasureH = supBarMeasureH;
        this.supBarMeasureI = supBarMeasureI;
        this.supBarMeasureJ = supBarMeasureJ;
        this.supBarObs = supBarObs;
        this.toiletHeight = toiletHeight;
        this.toiletFlushHeight = toiletFlushHeight;
        this.paperHolderType = paperHolderType;
        this.paperHolderDistance = paperHolderDistance;
        this.paperHolderHeight = paperHolderHeight;
        this.paperHolderObs = paperHolderObs;
        this.hasEmergencySignal = hasEmergencySignal;
        this.emergencySignalHeight = emergencySignalHeight;
        this.emergencySignalObs = emergencySignalObs;
        this.hasBidet = hasBidet;
        this.bidetObs = bidetObs;
    }

    public int getSupBarID() {
        return supBarID;
    }

    public void setSupBarID(int supBarID) {
        this.supBarID = supBarID;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public double getSupBarDiameter() {
        return supBarDiameter;
    }

    public void setSupBarDiameter(double supBarDiameter) {
        this.supBarDiameter = supBarDiameter;
    }

    public double getSupBarMeasureA() {
        return supBarMeasureA;
    }

    public void setSupBarMeasureA(double supBarMeasureA) {
        this.supBarMeasureA = supBarMeasureA;
    }

    public double getSupBarMeasureB() {
        return supBarMeasureB;
    }

    public void setSupBarMeasureB(double supBarMeasureB) {
        this.supBarMeasureB = supBarMeasureB;
    }

    public double getSupBarMeasureC() {
        return supBarMeasureC;
    }

    public void setSupBarMeasureC(double supBarMeasureC) {
        this.supBarMeasureC = supBarMeasureC;
    }

    public double getSupBarMeasureD() {
        return supBarMeasureD;
    }

    public void setSupBarMeasureD(double supBarMeasureD) {
        this.supBarMeasureD = supBarMeasureD;
    }

    public double getSupBarMeasureE() {
        return supBarMeasureE;
    }

    public void setSupBarMeasureE(double supBarMeasureE) {
        this.supBarMeasureE = supBarMeasureE;
    }

    public double getSupBarMeasureF() {
        return supBarMeasureF;
    }

    public void setSupBarMeasureF(double supBarMeasureF) {
        this.supBarMeasureF = supBarMeasureF;
    }

    public double getSupBarMeasureG() {
        return supBarMeasureG;
    }

    public void setSupBarMeasureG(double supBarMeasureG) {
        this.supBarMeasureG = supBarMeasureG;
    }

    public double getSupBarMeasureH() {
        return supBarMeasureH;
    }

    public void setSupBarMeasureH(double supBarMeasureH) {
        this.supBarMeasureH = supBarMeasureH;
    }

    public double getSupBarMeasureI() {
        return supBarMeasureI;
    }

    public void setSupBarMeasureI(double supBarMeasureI) {
        this.supBarMeasureI = supBarMeasureI;
    }

    public double getSupBarMeasureJ() {
        return supBarMeasureJ;
    }

    public void setSupBarMeasureJ(double supBarMeasureJ) {
        this.supBarMeasureJ = supBarMeasureJ;
    }

    public String getSupBarObs() {
        return supBarObs;
    }

    public void setSupBarObs(String supBarObs) {
        this.supBarObs = supBarObs;
    }

    public double getToiletHeight() {
        return toiletHeight;
    }

    public void setToiletHeight(double toiletHeight) {
        this.toiletHeight = toiletHeight;
    }

    public double getToiletFlushHeight() {
        return toiletFlushHeight;
    }

    public void setToiletFlushHeight(double toiletFlushHeight) {
        this.toiletFlushHeight = toiletFlushHeight;
    }

    public int getPaperHolderType() {
        return paperHolderType;
    }

    public void setPaperHolderType(int paperHolderType) {
        this.paperHolderType = paperHolderType;
    }

    public double getPaperHolderDistance() {
        return paperHolderDistance;
    }

    public void setPaperHolderDistance(double paperHolderDistance) {
        this.paperHolderDistance = paperHolderDistance;
    }

    public double getPaperHolderHeight() {
        return paperHolderHeight;
    }

    public void setPaperHolderHeight(double paperHolderHeight) {
        this.paperHolderHeight = paperHolderHeight;
    }

    public String getPaperHolderObs() {
        return paperHolderObs;
    }

    public void setPaperHolderObs(String paperHolderObs) {
        this.paperHolderObs = paperHolderObs;
    }

    public int getHasEmergencySignal() {
        return hasEmergencySignal;
    }

    public void setHasEmergencySignal(int hasEmergencySignal) {
        this.hasEmergencySignal = hasEmergencySignal;
    }

    public Double getEmergencySignalHeight() {
        return emergencySignalHeight;
    }

    public void setEmergencySignalHeight(Double emergencySignalHeight) {
        this.emergencySignalHeight = emergencySignalHeight;
    }

    public String getEmergencySignalObs() {
        return emergencySignalObs;
    }

    public void setEmergencySignalObs(String emergencySignalObs) {
        this.emergencySignalObs = emergencySignalObs;
    }

    public int getHasBidet() {
        return hasBidet;
    }

    public void setHasBidet(int hasBidet) {
        this.hasBidet = hasBidet;
    }

    public String getBidetObs() {
        return bidetObs;
    }

    public void setBidetObs(String bidetObs) {
        this.bidetObs = bidetObs;
    }
}
