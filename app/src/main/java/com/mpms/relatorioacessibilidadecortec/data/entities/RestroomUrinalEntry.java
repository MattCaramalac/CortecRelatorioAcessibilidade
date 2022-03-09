package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = RestroomEntry.class, parentColumns = "restroomID",
        childColumns = "restroomID", onDelete = CASCADE, onUpdate = CASCADE))
public class RestroomUrinalEntry {

    @PrimaryKey(autoGenerate = true)
    private int urinalID;
    private int restroomID;
    private int restroomHasUrinal;
    private Double urinalMeasureA;
    private Double urinalMeasureB;
    private Double urinalMeasureC;
    private Double urinalMeasureD;
    private Double urinalMeasureE;
    private Double urinalMeasureF;
    private Double urinalMeasureG;
    private Double urinalMeasureH;
    private Double urinalMeasureI;
    private Double urinalMeasureJ;
    private Double urinalMeasureK;
    private String urinalObs;

    public RestroomUrinalEntry(int restroomID, int restroomHasUrinal, Double urinalMeasureA, Double urinalMeasureB,
                               Double urinalMeasureC, Double urinalMeasureD, Double urinalMeasureE, Double urinalMeasureF,
                               Double urinalMeasureG, Double urinalMeasureH, Double urinalMeasureI, Double urinalMeasureJ,
                               Double urinalMeasureK, String urinalObs) {
        this.restroomID = restroomID;
        this.restroomHasUrinal = restroomHasUrinal;
        this.urinalMeasureA = urinalMeasureA;
        this.urinalMeasureB = urinalMeasureB;
        this.urinalMeasureC = urinalMeasureC;
        this.urinalMeasureD = urinalMeasureD;
        this.urinalMeasureE = urinalMeasureE;
        this.urinalMeasureF = urinalMeasureF;
        this.urinalMeasureG = urinalMeasureG;
        this.urinalMeasureH = urinalMeasureH;
        this.urinalMeasureI = urinalMeasureI;
        this.urinalMeasureJ = urinalMeasureJ;
        this.urinalMeasureK = urinalMeasureK;
        this.urinalObs = urinalObs;
    }

    public int getUrinalID() {
        return urinalID;
    }

    public void setUrinalID(int urinalID) {
        this.urinalID = urinalID;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public int getRestroomHasUrinal() {
        return restroomHasUrinal;
    }

    public void setRestroomHasUrinal(int restroomHasUrinal) {
        this.restroomHasUrinal = restroomHasUrinal;
    }

    public Double getUrinalMeasureA() {
        return urinalMeasureA;
    }

    public void setUrinalMeasureA(Double urinalMeasureA) {
        this.urinalMeasureA = urinalMeasureA;
    }

    public Double getUrinalMeasureB() {
        return urinalMeasureB;
    }

    public void setUrinalMeasureB(Double urinalMeasureB) {
        this.urinalMeasureB = urinalMeasureB;
    }

    public Double getUrinalMeasureC() {
        return urinalMeasureC;
    }

    public void setUrinalMeasureC(Double urinalMeasureC) {
        this.urinalMeasureC = urinalMeasureC;
    }

    public Double getUrinalMeasureD() {
        return urinalMeasureD;
    }

    public void setUrinalMeasureD(Double urinalMeasureD) {
        this.urinalMeasureD = urinalMeasureD;
    }

    public Double getUrinalMeasureE() {
        return urinalMeasureE;
    }

    public void setUrinalMeasureE(Double urinalMeasureE) {
        this.urinalMeasureE = urinalMeasureE;
    }

    public Double getUrinalMeasureF() {
        return urinalMeasureF;
    }

    public void setUrinalMeasureF(Double urinalMeasureF) {
        this.urinalMeasureF = urinalMeasureF;
    }

    public Double getUrinalMeasureG() {
        return urinalMeasureG;
    }

    public void setUrinalMeasureG(Double urinalMeasureG) {
        this.urinalMeasureG = urinalMeasureG;
    }

    public Double getUrinalMeasureH() {
        return urinalMeasureH;
    }

    public void setUrinalMeasureH(Double urinalMeasureH) {
        this.urinalMeasureH = urinalMeasureH;
    }

    public Double getUrinalMeasureI() {
        return urinalMeasureI;
    }

    public void setUrinalMeasureI(Double urinalMeasureI) {
        this.urinalMeasureI = urinalMeasureI;
    }

    public Double getUrinalMeasureJ() {
        return urinalMeasureJ;
    }

    public void setUrinalMeasureJ(Double urinalMeasureJ) {
        this.urinalMeasureJ = urinalMeasureJ;
    }

    public Double getUrinalMeasureK() {
        return urinalMeasureK;
    }

    public void setUrinalMeasureK(Double urinalMeasureK) {
        this.urinalMeasureK = urinalMeasureK;
    }

    public String getUrinalObs() {
        return urinalObs;
    }

    public void setUrinalObs(String urinalObs) {
        this.urinalObs = urinalObs;
    }
}
