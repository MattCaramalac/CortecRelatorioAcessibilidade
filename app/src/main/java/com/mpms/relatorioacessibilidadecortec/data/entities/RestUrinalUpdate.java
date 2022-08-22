package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestUrinalUpdate {

    @PrimaryKey
    private int restroomID;
    private Integer hasUrinal;
    private Integer hasAccessUrinal;
    private Integer urinalType;
    private Double urMeasureA;
    private Double urMeasureB;
    private Double urMeasureC;
    private Double urMeasureD;
    private Double urMeasureE;
    private Double urMeasureF;
    private Double urMeasureG;
    private Double urMeasureH;
    private Double urMeasureI;
    private Double urMeasureJ;
    private Double urMeasureK;
    private Double urMeasureL;
    private Double urMeasureM;
    private String urObs;

    public RestUrinalUpdate(int restroomID, Integer hasUrinal, Integer hasAccessUrinal, Integer urinalType, Double urMeasureA, Double urMeasureB, Double urMeasureC, Double urMeasureD,
                            Double urMeasureE, Double urMeasureF, Double urMeasureG, Double urMeasureH, Double urMeasureI, Double urMeasureJ, Double urMeasureK, Double urMeasureL,
                            Double urMeasureM, String urObs) {
        this.restroomID = restroomID;
        this.hasUrinal = hasUrinal;
        this.hasAccessUrinal = hasAccessUrinal;
        this.urinalType = urinalType;
        this.urMeasureA = urMeasureA;
        this.urMeasureB = urMeasureB;
        this.urMeasureC = urMeasureC;
        this.urMeasureD = urMeasureD;
        this.urMeasureE = urMeasureE;
        this.urMeasureF = urMeasureF;
        this.urMeasureG = urMeasureG;
        this.urMeasureH = urMeasureH;
        this.urMeasureI = urMeasureI;
        this.urMeasureJ = urMeasureJ;
        this.urMeasureK = urMeasureK;
        this.urMeasureL = urMeasureL;
        this.urMeasureM = urMeasureM;
        this.urObs = urObs;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public Integer getHasUrinal() {
        return hasUrinal;
    }

    public void setHasUrinal(Integer hasUrinal) {
        this.hasUrinal = hasUrinal;
    }

    public Integer getHasAccessUrinal() {
        return hasAccessUrinal;
    }

    public void setHasAccessUrinal(Integer hasAccessUrinal) {
        this.hasAccessUrinal = hasAccessUrinal;
    }

    public Integer getUrinalType() {
        return urinalType;
    }

    public void setUrinalType(Integer urinalType) {
        this.urinalType = urinalType;
    }

    public Double getUrMeasureA() {
        return urMeasureA;
    }

    public void setUrMeasureA(Double urMeasureA) {
        this.urMeasureA = urMeasureA;
    }

    public Double getUrMeasureB() {
        return urMeasureB;
    }

    public void setUrMeasureB(Double urMeasureB) {
        this.urMeasureB = urMeasureB;
    }

    public Double getUrMeasureC() {
        return urMeasureC;
    }

    public void setUrMeasureC(Double urMeasureC) {
        this.urMeasureC = urMeasureC;
    }

    public Double getUrMeasureD() {
        return urMeasureD;
    }

    public void setUrMeasureD(Double urMeasureD) {
        this.urMeasureD = urMeasureD;
    }

    public Double getUrMeasureE() {
        return urMeasureE;
    }

    public void setUrMeasureE(Double urMeasureE) {
        this.urMeasureE = urMeasureE;
    }

    public Double getUrMeasureF() {
        return urMeasureF;
    }

    public void setUrMeasureF(Double urMeasureF) {
        this.urMeasureF = urMeasureF;
    }

    public Double getUrMeasureG() {
        return urMeasureG;
    }

    public void setUrMeasureG(Double urMeasureG) {
        this.urMeasureG = urMeasureG;
    }

    public Double getUrMeasureH() {
        return urMeasureH;
    }

    public void setUrMeasureH(Double urMeasureH) {
        this.urMeasureH = urMeasureH;
    }

    public Double getUrMeasureI() {
        return urMeasureI;
    }

    public void setUrMeasureI(Double urMeasureI) {
        this.urMeasureI = urMeasureI;
    }

    public Double getUrMeasureJ() {
        return urMeasureJ;
    }

    public void setUrMeasureJ(Double urMeasureJ) {
        this.urMeasureJ = urMeasureJ;
    }

    public Double getUrMeasureK() {
        return urMeasureK;
    }

    public void setUrMeasureK(Double urMeasureK) {
        this.urMeasureK = urMeasureK;
    }

    public Double getUrMeasureL() {
        return urMeasureL;
    }

    public void setUrMeasureL(Double urMeasureL) {
        this.urMeasureL = urMeasureL;
    }

    public Double getUrMeasureM() {
        return urMeasureM;
    }

    public void setUrMeasureM(Double urMeasureM) {
        this.urMeasureM = urMeasureM;
    }

    public String getUrObs() {
        return urObs;
    }

    public void setUrObs(String urObs) {
        this.urObs = urObs;
    }
}
