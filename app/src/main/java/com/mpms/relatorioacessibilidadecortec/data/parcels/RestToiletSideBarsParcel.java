package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class RestToiletSideBarsParcel {

    int hasHorSideBar;
    Double horSideMeasureD;
    Double horSideMeasureE;
    Double horMeasureF;
    Double horSideMeasureG;
    Double horSideDiam;
    Double horDist;
    int hasVertArtBar;
    Double vertArtMeasureH;
    Double vertArtMeasureI;
    Double vertArtMeasureJ;
    Double vertArtDiam;
    Double vertDist;

    public RestToiletSideBarsParcel() {
//        Empty Constructor
    }

    public RestToiletSideBarsParcel(int hasHorSideBar, Double horSideMeasureD, Double horSideMeasureE, Double horMeasureF, Double horSideMeasureG, Double horSideDiam,
                                    Double horDist, int hasVertArtBar, Double vertArtMeasureH, Double vertArtMeasureI, Double vertArtMeasureJ, Double vertArtDiam, Double vertDist) {
        this.hasHorSideBar = hasHorSideBar;
        this.horSideMeasureD = horSideMeasureD;
        this.horSideMeasureE = horSideMeasureE;
        this.horMeasureF = horMeasureF;
        this.horSideMeasureG = horSideMeasureG;
        this.horSideDiam = horSideDiam;
        this.horDist = horDist;
        this.hasVertArtBar = hasVertArtBar;
        this.vertArtMeasureH = vertArtMeasureH;
        this.vertArtMeasureI = vertArtMeasureI;
        this.vertArtMeasureJ = vertArtMeasureJ;
        this.vertArtDiam = vertArtDiam;
        this.vertDist = vertDist;
    }

    public int getHasHorSideBar() {
        return hasHorSideBar;
    }

    public void setHasHorSideBar(int hasHorSideBar) {
        this.hasHorSideBar = hasHorSideBar;
    }

    public Double getHorSideMeasureD() {
        return horSideMeasureD;
    }

    public void setHorSideMeasureD(Double horSideMeasureD) {
        this.horSideMeasureD = horSideMeasureD;
    }

    public Double getHorSideMeasureE() {
        return horSideMeasureE;
    }

    public void setHorSideMeasureE(Double horSideMeasureE) {
        this.horSideMeasureE = horSideMeasureE;
    }

    public Double getHorMeasureF() {
        return horMeasureF;
    }

    public void setHorMeasureF(Double horMeasureF) {
        this.horMeasureF = horMeasureF;
    }

    public Double getHorSideMeasureG() {
        return horSideMeasureG;
    }

    public void setHorSideMeasureG(Double horSideMeasureG) {
        this.horSideMeasureG = horSideMeasureG;
    }

    public Double getHorSideDiam() {
        return horSideDiam;
    }

    public void setHorSideDiam(Double horSideDiam) {
        this.horSideDiam = horSideDiam;
    }

    public Double getHorDist() {
        return horDist;
    }

    public void setHorDist(Double horDist) {
        this.horDist = horDist;
    }

    public int getHasVertArtBar() {
        return hasVertArtBar;
    }

    public void setHasVertArtBar(int hasVertArtBar) {
        this.hasVertArtBar = hasVertArtBar;
    }

    public Double getVertArtMeasureH() {
        return vertArtMeasureH;
    }

    public void setVertArtMeasureH(Double vertArtMeasureH) {
        this.vertArtMeasureH = vertArtMeasureH;
    }

    public Double getVertArtMeasureI() {
        return vertArtMeasureI;
    }

    public void setVertArtMeasureI(Double vertArtMeasureI) {
        this.vertArtMeasureI = vertArtMeasureI;
    }

    public Double getVertArtMeasureJ() {
        return vertArtMeasureJ;
    }

    public void setVertArtMeasureJ(Double vertArtMeasureJ) {
        this.vertArtMeasureJ = vertArtMeasureJ;
    }

    public Double getVertArtDiam() {
        return vertArtDiam;
    }

    public void setVertArtDiam(Double vertArtDiam) {
        this.vertArtDiam = vertArtDiam;
    }

    public Double getVertDist() {
        return vertDist;
    }

    public void setVertDist(Double vertDist) {
        this.vertDist = vertDist;
    }
}
