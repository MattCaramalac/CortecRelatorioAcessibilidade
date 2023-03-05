package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class SinkParcel {

    Integer sinkType;
    Double approxMeasureA;
    Double approxMeasureB;
    Double approxMeasureC;
    Double approxMeasureD;
    Double approxMeasureE;
    Integer hasSinkBar;
    Integer hasLeftFrontHorBar;
    Double leftFrontHorMeasureA;
    Double leftFrontHorMeasureB;
    Double leftFrontHorMeasureC;
    Double leftFrontHorMeasureD;
    Double leftFrontHorDiam;
    Double leftFrontHorDist;
    String leftFrontHorObs;
    Integer hasRightSideVertBar;
    Double rightSideVertMeasureA;
    Double rightSideVertMeasureB;
    Double rightSideVertMeasureC;
    Double rightSideVertMeasureD;
    Double rightSideVertMeasureE;
    Double rightSideVertDiam;
    Double rightSideVertDist;
    String rightSideVertObs;
    Integer sinkHasMirror;
    Double sinkMirrorLow;
    Double sinkMirrorHigh;
    String sinkObs;

    public SinkParcel() {
//        Empty Constructor
    }

    public SinkParcel(Integer sinkType, Double approxMeasureA, Double approxMeasureB, Double approxMeasureC, Double approxMeasureD, Double approxMeasureE,
                      Integer hasSinkBar, Integer hasLeftFrontHorBar, Double leftFrontHorMeasureA, Double leftFrontHorMeasureB, Double leftFrontHorMeasureC, Double leftFrontHorMeasureD,
                      Double leftFrontHorDiam, Double leftFrontHorDist, String leftFrontHorObs, Integer hasRightSideVertBar, Double rightSideVertMeasureA, Double rightSideVertMeasureB,
                      Double rightSideVertMeasureC, Double rightSideVertMeasureD, Double rightSideVertMeasureE, Double rightSideVertDiam, Double rightSideVertDist, String rightSideVertObs,
                      Integer sinkHasMirror, Double sinkMirrorLow, Double sinkMirrorHigh, String sinkObs) {
        this.sinkType = sinkType;
        this.approxMeasureA = approxMeasureA;
        this.approxMeasureB = approxMeasureB;
        this.approxMeasureC = approxMeasureC;
        this.approxMeasureD = approxMeasureD;
        this.approxMeasureE = approxMeasureE;
        this.hasSinkBar = hasSinkBar;
        this.hasLeftFrontHorBar = hasLeftFrontHorBar;
        this.leftFrontHorMeasureA = leftFrontHorMeasureA;
        this.leftFrontHorMeasureB = leftFrontHorMeasureB;
        this.leftFrontHorMeasureC = leftFrontHorMeasureC;
        this.leftFrontHorMeasureD = leftFrontHorMeasureD;
        this.leftFrontHorDiam = leftFrontHorDiam;
        this.leftFrontHorDist = leftFrontHorDist;
        this.leftFrontHorObs = leftFrontHorObs;
        this.hasRightSideVertBar = hasRightSideVertBar;
        this.rightSideVertMeasureA = rightSideVertMeasureA;
        this.rightSideVertMeasureB = rightSideVertMeasureB;
        this.rightSideVertMeasureC = rightSideVertMeasureC;
        this.rightSideVertMeasureD = rightSideVertMeasureD;
        this.rightSideVertMeasureE = rightSideVertMeasureE;
        this.rightSideVertDiam = rightSideVertDiam;
        this.rightSideVertDist = rightSideVertDist;
        this.rightSideVertObs = rightSideVertObs;
        this.sinkHasMirror = sinkHasMirror;
        this.sinkMirrorLow = sinkMirrorLow;
        this.sinkMirrorHigh = sinkMirrorHigh;
        this.sinkObs = sinkObs;
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

    public Integer getHasSinkBar() {
        return hasSinkBar;
    }

    public void setHasSinkBar(Integer hasSinkBar) {
        this.hasSinkBar = hasSinkBar;
    }

    public Integer getHasLeftFrontHorBar() {
        return hasLeftFrontHorBar;
    }

    public void setHasLeftFrontHorBar(Integer hasLeftFrontHorBar) {
        this.hasLeftFrontHorBar = hasLeftFrontHorBar;
    }

    public Double getLeftFrontHorMeasureA() {
        return leftFrontHorMeasureA;
    }

    public void setLeftFrontHorMeasureA(Double leftFrontHorMeasureA) {
        this.leftFrontHorMeasureA = leftFrontHorMeasureA;
    }

    public Double getLeftFrontHorMeasureB() {
        return leftFrontHorMeasureB;
    }

    public void setLeftFrontHorMeasureB(Double leftFrontHorMeasureB) {
        this.leftFrontHorMeasureB = leftFrontHorMeasureB;
    }

    public Double getLeftFrontHorMeasureC() {
        return leftFrontHorMeasureC;
    }

    public void setLeftFrontHorMeasureC(Double leftFrontHorMeasureC) {
        this.leftFrontHorMeasureC = leftFrontHorMeasureC;
    }

    public Double getLeftFrontHorMeasureD() {
        return leftFrontHorMeasureD;
    }

    public void setLeftFrontHorMeasureD(Double leftFrontHorMeasureD) {
        this.leftFrontHorMeasureD = leftFrontHorMeasureD;
    }

    public Double getLeftFrontHorDiam() {
        return leftFrontHorDiam;
    }

    public void setLeftFrontHorDiam(Double leftFrontHorDiam) {
        this.leftFrontHorDiam = leftFrontHorDiam;
    }

    public Double getLeftFrontHorDist() {
        return leftFrontHorDist;
    }

    public void setLeftFrontHorDist(Double leftFrontHorDist) {
        this.leftFrontHorDist = leftFrontHorDist;
    }

    public String getLeftFrontHorObs() {
        return leftFrontHorObs;
    }

    public void setLeftFrontHorObs(String leftFrontHorObs) {
        this.leftFrontHorObs = leftFrontHorObs;
    }

    public Integer getHasRightSideVertBar() {
        return hasRightSideVertBar;
    }

    public void setHasRightSideVertBar(Integer hasRightSideVertBar) {
        this.hasRightSideVertBar = hasRightSideVertBar;
    }

    public Double getRightSideVertMeasureA() {
        return rightSideVertMeasureA;
    }

    public void setRightSideVertMeasureA(Double rightSideVertMeasureA) {
        this.rightSideVertMeasureA = rightSideVertMeasureA;
    }

    public Double getRightSideVertMeasureB() {
        return rightSideVertMeasureB;
    }

    public void setRightSideVertMeasureB(Double rightSideVertMeasureB) {
        this.rightSideVertMeasureB = rightSideVertMeasureB;
    }

    public Double getRightSideVertMeasureC() {
        return rightSideVertMeasureC;
    }

    public void setRightSideVertMeasureC(Double rightSideVertMeasureC) {
        this.rightSideVertMeasureC = rightSideVertMeasureC;
    }

    public Double getRightSideVertMeasureD() {
        return rightSideVertMeasureD;
    }

    public void setRightSideVertMeasureD(Double rightSideVertMeasureD) {
        this.rightSideVertMeasureD = rightSideVertMeasureD;
    }

    public Double getRightSideVertMeasureE() {
        return rightSideVertMeasureE;
    }

    public void setRightSideVertMeasureE(Double rightSideVertMeasureE) {
        this.rightSideVertMeasureE = rightSideVertMeasureE;
    }

    public Double getRightSideVertDiam() {
        return rightSideVertDiam;
    }

    public void setRightSideVertDiam(Double rightSideVertDiam) {
        this.rightSideVertDiam = rightSideVertDiam;
    }

    public Double getRightSideVertDist() {
        return rightSideVertDist;
    }

    public void setRightSideVertDist(Double rightSideVertDist) {
        this.rightSideVertDist = rightSideVertDist;
    }

    public String getRightSideVertObs() {
        return rightSideVertObs;
    }

    public void setRightSideVertObs(String rightSideVertObs) {
        this.rightSideVertObs = rightSideVertObs;
    }

    public Integer getSinkHasMirror() {
        return sinkHasMirror;
    }

    public void setSinkHasMirror(Integer sinkHasMirror) {
        this.sinkHasMirror = sinkHasMirror;
    }

    public Double getSinkMirrorLow() {
        return sinkMirrorLow;
    }

    public void setSinkMirrorLow(Double sinkMirrorLow) {
        this.sinkMirrorLow = sinkMirrorLow;
    }

    public Double getSinkMirrorHigh() {
        return sinkMirrorHigh;
    }

    public void setSinkMirrorHigh(Double sinkMirrorHigh) {
        this.sinkMirrorHigh = sinkMirrorHigh;
    }

    public String getSinkObs() {
        return sinkObs;
    }

    public void setSinkObs(String sinkObs) {
        this.sinkObs = sinkObs;
    }
}
