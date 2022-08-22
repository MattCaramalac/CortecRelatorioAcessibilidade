package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestSinkUpdate {

    @PrimaryKey(autoGenerate = true)
    private int restroomID;
    private Integer sinkType;
    private Double approxMeasureA;
    private Double approxMeasureB;
    private Double approxMeasureC;
    private Double approxMeasureD;
    private Double approxMeasureE;
    private Integer hasSinkBar;
    private Integer hasLeftBar;
    private Double leftHorMeasureA;
    private Double leftHorMeasureB;
    private Double leftHorMeasureC;
    private Double leftHorMeasureD;
    private Double leftVertMeasureA;
    private Double leftVertMeasureB;
    private Double leftVertMeasureC;
    private Double leftVertMeasureD;
    private Double leftVertMeasureE;
    private Double leftBarDiam;
    private Double leftBarDist;
    private Integer hasRightBar;
    private Double rightHorMeasureA;
    private Double rightHorMeasureB;
    private Double rightHorMeasureC;
    private Double rightHorMeasureD;
    private Double rightVertMeasureA;
    private Double rightBarDiam;
    private Double rightBarDist;
    private Integer sinkHasMirror;
    private Double siMirrorLow;
    private Double siMirrorHigh;
    private String sinkObs;

    public RestSinkUpdate(int restroomID, Integer sinkType, Double approxMeasureA, Double approxMeasureB, Double approxMeasureC, Double approxMeasureD, Double approxMeasureE,
                          Integer hasSinkBar, Integer hasLeftBar, Double leftHorMeasureA, Double leftHorMeasureB, Double leftHorMeasureC, Double leftHorMeasureD, Double leftVertMeasureA,
                          Double leftVertMeasureB, Double leftVertMeasureC, Double leftVertMeasureD, Double leftVertMeasureE, Double leftBarDiam, Double leftBarDist,
                          Integer hasRightBar, Double rightHorMeasureA, Double rightHorMeasureB, Double rightHorMeasureC, Double rightHorMeasureD, Double rightVertMeasureA
                          , Double rightBarDiam, Double rightBarDist, Integer sinkHasMirror, Double siMirrorLow, Double siMirrorHigh, String sinkObs) {
        this.restroomID = restroomID;
        this.sinkType = sinkType;
        this.approxMeasureA = approxMeasureA;
        this.approxMeasureB = approxMeasureB;
        this.approxMeasureC = approxMeasureC;
        this.approxMeasureD = approxMeasureD;
        this.approxMeasureE = approxMeasureE;
        this.hasSinkBar = hasSinkBar;
        this.hasLeftBar = hasLeftBar;
        this.leftHorMeasureA = leftHorMeasureA;
        this.leftHorMeasureB = leftHorMeasureB;
        this.leftHorMeasureC = leftHorMeasureC;
        this.leftHorMeasureD = leftHorMeasureD;
        this.leftVertMeasureA = leftVertMeasureA;
        this.leftVertMeasureB = leftVertMeasureB;
        this.leftVertMeasureC = leftVertMeasureC;
        this.leftVertMeasureD = leftVertMeasureD;
        this.leftVertMeasureE = leftVertMeasureE;
        this.leftBarDiam = leftBarDiam;
        this.leftBarDist = leftBarDist;
        this.hasRightBar = hasRightBar;
        this.rightHorMeasureA = rightHorMeasureA;
        this.rightHorMeasureB = rightHorMeasureB;
        this.rightHorMeasureC = rightHorMeasureC;
        this.rightHorMeasureD = rightHorMeasureD;
        this.rightVertMeasureA = rightVertMeasureA;
        this.rightBarDiam = rightBarDiam;
        this.rightBarDist = rightBarDist;
        this.sinkHasMirror = sinkHasMirror;
        this.siMirrorLow = siMirrorLow;
        this.siMirrorHigh = siMirrorHigh;
        this.sinkObs = sinkObs;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public Integer getSinkType() {
        return sinkType;
    }

    public void setSinkType(Integer sinkType) {
        this.sinkType = sinkType;
    }

    public Double getApproxMeasureA() {
        return approxMeasureA;
    }

    public void setApproxMeasureA(Double approxMeasureA) {
        this.approxMeasureA = approxMeasureA;
    }

    public Double getApproxMeasureB() {
        return approxMeasureB;
    }

    public void setApproxMeasureB(Double approxMeasureB) {
        this.approxMeasureB = approxMeasureB;
    }

    public Double getApproxMeasureC() {
        return approxMeasureC;
    }

    public void setApproxMeasureC(Double approxMeasureC) {
        this.approxMeasureC = approxMeasureC;
    }

    public Double getApproxMeasureD() {
        return approxMeasureD;
    }

    public void setApproxMeasureD(Double approxMeasureD) {
        this.approxMeasureD = approxMeasureD;
    }

    public Double getApproxMeasureE() {
        return approxMeasureE;
    }

    public void setApproxMeasureE(Double approxMeasureE) {
        this.approxMeasureE = approxMeasureE;
    }

    public Integer getHasLeftBar() {
        return hasLeftBar;
    }

    public void setHasLeftBar(Integer hasLeftBar) {
        this.hasLeftBar = hasLeftBar;
    }

    public Double getLeftHorMeasureA() {
        return leftHorMeasureA;
    }

    public void setLeftHorMeasureA(Double leftHorMeasureA) {
        this.leftHorMeasureA = leftHorMeasureA;
    }

    public Double getLeftHorMeasureB() {
        return leftHorMeasureB;
    }

    public void setLeftHorMeasureB(Double leftHorMeasureB) {
        this.leftHorMeasureB = leftHorMeasureB;
    }

    public Double getLeftHorMeasureC() {
        return leftHorMeasureC;
    }

    public void setLeftHorMeasureC(Double leftHorMeasureC) {
        this.leftHorMeasureC = leftHorMeasureC;
    }

    public Double getLeftHorMeasureD() {
        return leftHorMeasureD;
    }

    public void setLeftHorMeasureD(Double leftHorMeasureD) {
        this.leftHorMeasureD = leftHorMeasureD;
    }

    public Double getLeftVertMeasureA() {
        return leftVertMeasureA;
    }

    public void setLeftVertMeasureA(Double leftVertMeasureA) {
        this.leftVertMeasureA = leftVertMeasureA;
    }

    public Double getLeftVertMeasureB() {
        return leftVertMeasureB;
    }

    public void setLeftVertMeasureB(Double leftVertMeasureB) {
        this.leftVertMeasureB = leftVertMeasureB;
    }

    public Double getLeftVertMeasureC() {
        return leftVertMeasureC;
    }

    public void setLeftVertMeasureC(Double leftVertMeasureC) {
        this.leftVertMeasureC = leftVertMeasureC;
    }

    public Double getLeftVertMeasureD() {
        return leftVertMeasureD;
    }

    public void setLeftVertMeasureD(Double leftVertMeasureD) {
        this.leftVertMeasureD = leftVertMeasureD;
    }

    public Double getLeftVertMeasureE() {
        return leftVertMeasureE;
    }

    public void setLeftVertMeasureE(Double leftVertMeasureE) {
        this.leftVertMeasureE = leftVertMeasureE;
    }

    public Double getLeftBarDiam() {
        return leftBarDiam;
    }

    public void setLeftBarDiam(Double leftBarDiam) {
        this.leftBarDiam = leftBarDiam;
    }

    public Double getLeftBarDist() {
        return leftBarDist;
    }

    public void setLeftBarDist(Double leftBarDist) {
        this.leftBarDist = leftBarDist;
    }

    public Integer getHasRightBar() {
        return hasRightBar;
    }

    public void setHasRightBar(Integer hasRightBar) {
        this.hasRightBar = hasRightBar;
    }

    public Double getRightHorMeasureA() {
        return rightHorMeasureA;
    }

    public void setRightHorMeasureA(Double rightHorMeasureA) {
        this.rightHorMeasureA = rightHorMeasureA;
    }

    public Double getRightHorMeasureB() {
        return rightHorMeasureB;
    }

    public void setRightHorMeasureB(Double rightHorMeasureB) {
        this.rightHorMeasureB = rightHorMeasureB;
    }

    public Double getRightHorMeasureC() {
        return rightHorMeasureC;
    }

    public void setRightHorMeasureC(Double rightHorMeasureC) {
        this.rightHorMeasureC = rightHorMeasureC;
    }

    public Double getRightHorMeasureD() {
        return rightHorMeasureD;
    }

    public void setRightHorMeasureD(Double rightHorMeasureD) {
        this.rightHorMeasureD = rightHorMeasureD;
    }

    public Double getRightVertMeasureA() {
        return rightVertMeasureA;
    }

    public void setRightVertMeasureA(Double rightVertMeasureA) {
        this.rightVertMeasureA = rightVertMeasureA;
    }

    public Double getRightBarDiam() {
        return rightBarDiam;
    }

    public void setRightBarDiam(Double rightBarDiam) {
        this.rightBarDiam = rightBarDiam;
    }

    public Double getRightBarDist() {
        return rightBarDist;
    }

    public void setRightBarDist(Double rightBarDist) {
        this.rightBarDist = rightBarDist;
    }

    public String getSinkObs() {
        return sinkObs;
    }

    public Integer getSinkHasMirror() {
        return sinkHasMirror;
    }

    public void setSinkHasMirror(Integer sinkHasMirror) {
        this.sinkHasMirror = sinkHasMirror;
    }

    public Double getSiMirrorLow() {
        return siMirrorLow;
    }

    public void setSiMirrorLow(Double siMirrorLow) {
        this.siMirrorLow = siMirrorLow;
    }

    public Double getSiMirrorHigh() {
        return siMirrorHigh;
    }

    public void setSiMirrorHigh(Double siMirrorHigh) {
        this.siMirrorHigh = siMirrorHigh;
    }

    public void setSinkObs(String sinkObs) {
        this.sinkObs = sinkObs;
    }

    public Integer getHasSinkBar() {
        return hasSinkBar;
    }

    public void setHasSinkBar(Integer hasSinkBar) {
        this.hasSinkBar = hasSinkBar;
    }
}
