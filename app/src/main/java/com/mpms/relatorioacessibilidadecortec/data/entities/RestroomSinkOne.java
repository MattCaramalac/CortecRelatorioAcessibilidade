package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestroomSinkOne {

    @PrimaryKey(autoGenerate = true)
    private int sinkID;
    private Double sinkMeasureA;
    private Double sinkMeasureB;
    private Double sinkMeasureC;
    private Double sinkMeasureD;
    private Double sinkMeasureE;
    private String sinkObsAtoE;
    private Double sinkMeasureF;
    private Double sinkMeasureG;
    private Double sinkMeasureH;
    private String sinkObsFtoH;

    public RestroomSinkOne(int sinkID, Double sinkMeasureA, Double sinkMeasureB, Double sinkMeasureC, Double sinkMeasureD,
                           Double sinkMeasureE, String sinkObsAtoE, Double sinkMeasureF, Double sinkMeasureG, Double sinkMeasureH,
                           String sinkObsFtoH) {
        this.sinkID = sinkID;
        this.sinkMeasureA = sinkMeasureA;
        this.sinkMeasureB = sinkMeasureB;
        this.sinkMeasureC = sinkMeasureC;
        this.sinkMeasureD = sinkMeasureD;
        this.sinkMeasureE = sinkMeasureE;
        this.sinkObsAtoE = sinkObsAtoE;
        this.sinkMeasureF = sinkMeasureF;
        this.sinkMeasureG = sinkMeasureG;
        this.sinkMeasureH = sinkMeasureH;
        this.sinkObsFtoH = sinkObsFtoH;
    }

    public int getSinkID() {
        return sinkID;
    }

    public void setSinkID(int sinkID) {
        this.sinkID = sinkID;
    }

    public Double getSinkMeasureA() {
        return sinkMeasureA;
    }

    public void setSinkMeasureA(Double sinkMeasureA) {
        this.sinkMeasureA = sinkMeasureA;
    }

    public Double getSinkMeasureB() {
        return sinkMeasureB;
    }

    public void setSinkMeasureB(Double sinkMeasureB) {
        this.sinkMeasureB = sinkMeasureB;
    }

    public Double getSinkMeasureC() {
        return sinkMeasureC;
    }

    public void setSinkMeasureC(Double sinkMeasureC) {
        this.sinkMeasureC = sinkMeasureC;
    }

    public Double getSinkMeasureD() {
        return sinkMeasureD;
    }

    public void setSinkMeasureD(Double sinkMeasureD) {
        this.sinkMeasureD = sinkMeasureD;
    }

    public Double getSinkMeasureE() {
        return sinkMeasureE;
    }

    public void setSinkMeasureE(Double sinkMeasureE) {
        this.sinkMeasureE = sinkMeasureE;
    }

    public String getSinkObsAtoE() {
        return sinkObsAtoE;
    }

    public void setSinkObsAtoE(String sinkObsAtoE) {
        this.sinkObsAtoE = sinkObsAtoE;
    }

    public Double getSinkMeasureF() {
        return sinkMeasureF;
    }

    public void setSinkMeasureF(Double sinkMeasureF) {
        this.sinkMeasureF = sinkMeasureF;
    }

    public Double getSinkMeasureG() {
        return sinkMeasureG;
    }

    public void setSinkMeasureG(Double sinkMeasureG) {
        this.sinkMeasureG = sinkMeasureG;
    }

    public Double getSinkMeasureH() {
        return sinkMeasureH;
    }

    public void setSinkMeasureH(Double sinkMeasureH) {
        this.sinkMeasureH = sinkMeasureH;
    }

    public String getSinkObsFtoH() {
        return sinkObsFtoH;
    }

    public void setSinkObsFtoH(String sinkObsFtoH) {
        this.sinkObsFtoH = sinkObsFtoH;
    }
}
