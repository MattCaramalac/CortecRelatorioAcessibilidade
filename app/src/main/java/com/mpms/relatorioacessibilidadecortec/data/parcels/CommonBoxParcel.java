package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class CommonBoxParcel {

    double doorWidth;
    double boxFreeDiam;
    int hasBars;
    Double doorDist;
    Double boxWidth;
    Integer leftHasBarType;
    BoxBarParcel leftParcel;
    Integer rightHasBarType;
    BoxBarParcel rightParcel;
    String boxObs;

    public CommonBoxParcel() {
//        Empty Constructor
    }

    public CommonBoxParcel(double doorWidth, double boxFreeDiam, int hasBars, Double doorDist, Double boxWidth, Integer leftHasBarType, BoxBarParcel leftParcel,
                           Integer rightHasBarType, BoxBarParcel rightParcel, String boxObs) {
        this.doorWidth = doorWidth;
        this.boxFreeDiam = boxFreeDiam;
        this.hasBars = hasBars;
        this.doorDist = doorDist;
        this.boxWidth = boxWidth;
        this.leftHasBarType = leftHasBarType;
        this.leftParcel = leftParcel;
        this.rightHasBarType = rightHasBarType;
        this.rightParcel = rightParcel;
        this.boxObs = boxObs;
    }

    public double getDoorWidth() {
        return doorWidth;
    }

    public void setDoorWidth(double doorWidth) {
        this.doorWidth = doorWidth;
    }

    public double getBoxFreeDiam() {
        return boxFreeDiam;
    }

    public void setBoxFreeDiam(double boxFreeDiam) {
        this.boxFreeDiam = boxFreeDiam;
    }

    public int getHasBars() {
        return hasBars;
    }

    public void setHasBars(int hasBars) {
        this.hasBars = hasBars;
    }

    public Double getDoorDist() {
        return doorDist;
    }

    public void setDoorDist(Double doorDist) {
        this.doorDist = doorDist;
    }

    public Double getBoxWidth() {
        return boxWidth;
    }

    public void setBoxWidth(Double boxWidth) {
        this.boxWidth = boxWidth;
    }

    public Integer getLeftHasBarType() {
        return leftHasBarType;
    }

    public void setLeftHasBarType(Integer leftHasBarType) {
        this.leftHasBarType = leftHasBarType;
    }

    public BoxBarParcel getLeftParcel() {
        return leftParcel;
    }

    public void setLeftParcel(BoxBarParcel leftParcel) {
        this.leftParcel = leftParcel;
    }

    public Integer getRightHasBarType() {
        return rightHasBarType;
    }

    public void setRightHasBarType(Integer rightHasBarType) {
        this.rightHasBarType = rightHasBarType;
    }

    public BoxBarParcel getRightParcel() {
        return rightParcel;
    }

    public void setRightParcel(BoxBarParcel rightParcel) {
        this.rightParcel = rightParcel;
    }

    public String getBoxObs() {
        return boxObs;
    }

    public void setBoxObs(String boxObs) {
        this.boxObs = boxObs;
    }
}
