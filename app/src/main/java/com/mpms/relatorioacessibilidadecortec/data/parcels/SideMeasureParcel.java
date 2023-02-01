package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class SideMeasureParcel {

    Double sidewalkWidth;
    Double sideFreeSpaceWidth;
    String sideMeasureObs;
    Integer slopeMeasureQnt;
    Double sideTransSlope1;
    Double sideTransSlope2;
    Double sideTransSlope3;
    Double sideTransSlope4;
    Double sideTransSlope5;
    Double sideTransSlope6;
    Integer hasSpecialFloor;
    Integer specialFloorRightColor;
    Double specialTileDirectionWidth;
    Double specialTileAlertWidth;
    String specialFloorObs;


    public SideMeasureParcel() {
//        empty constructor
    }

    public SideMeasureParcel(Double sidewalkWidth, Double sideFreeSpaceWidth, String sideMeasureObs, Integer slopeMeasureQnt, Double sideTransSlope1,
                             Double sideTransSlope2, Double sideTransSlope3, Double sideTransSlope4, Double sideTransSlope5, Double sideTransSlope6, Integer hasSpecialFloor,
                             Integer specialFloorRightColor, Double specialTileDirectionWidth, Double specialTileAlertWidth, String specialFloorObs) {
        this.sidewalkWidth = sidewalkWidth;
        this.sideFreeSpaceWidth = sideFreeSpaceWidth;
        this.sideMeasureObs = sideMeasureObs;
        this.slopeMeasureQnt = slopeMeasureQnt;
        this.sideTransSlope1 = sideTransSlope1;
        this.sideTransSlope2 = sideTransSlope2;
        this.sideTransSlope3 = sideTransSlope3;
        this.sideTransSlope4 = sideTransSlope4;
        this.sideTransSlope5 = sideTransSlope5;
        this.sideTransSlope6 = sideTransSlope6;
        this.hasSpecialFloor = hasSpecialFloor;
        this.specialFloorRightColor = specialFloorRightColor;
        this.specialTileDirectionWidth = specialTileDirectionWidth;
        this.specialTileAlertWidth = specialTileAlertWidth;
        this.specialFloorObs = specialFloorObs;

    }

    public Double getSidewalkWidth() {
        return sidewalkWidth;
    }

    public void setSidewalkWidth(Double sidewalkWidth) {
        this.sidewalkWidth = sidewalkWidth;
    }

    public Double getSideFreeSpaceWidth() {
        return sideFreeSpaceWidth;
    }

    public void setSideFreeSpaceWidth(Double sideFreeSpaceWidth) {
        this.sideFreeSpaceWidth = sideFreeSpaceWidth;
    }

    public String getSideMeasureObs() {
        return sideMeasureObs;
    }

    public void setSideMeasureObs(String sideMeasureObs) {
        this.sideMeasureObs = sideMeasureObs;
    }

    public Integer getSlopeMeasureQnt() {
        return slopeMeasureQnt;
    }

    public void setSlopeMeasureQnt(Integer slopeMeasureQnt) {
        this.slopeMeasureQnt = slopeMeasureQnt;
    }

    public Double getSideTransSlope1() {
        return sideTransSlope1;
    }

    public void setSideTransSlope1(Double sideTransSlope1) {
        this.sideTransSlope1 = sideTransSlope1;
    }

    public Double getSideTransSlope2() {
        return sideTransSlope2;
    }

    public void setSideTransSlope2(Double sideTransSlope2) {
        this.sideTransSlope2 = sideTransSlope2;
    }

    public Double getSideTransSlope3() {
        return sideTransSlope3;
    }

    public void setSideTransSlope3(Double sideTransSlope3) {
        this.sideTransSlope3 = sideTransSlope3;
    }

    public Double getSideTransSlope4() {
        return sideTransSlope4;
    }

    public void setSideTransSlope4(Double sideTransSlope4) {
        this.sideTransSlope4 = sideTransSlope4;
    }

    public Double getSideTransSlope5() {
        return sideTransSlope5;
    }

    public void setSideTransSlope5(Double sideTransSlope5) {
        this.sideTransSlope5 = sideTransSlope5;
    }

    public Double getSideTransSlope6() {
        return sideTransSlope6;
    }

    public void setSideTransSlope6(Double sideTransSlope6) {
        this.sideTransSlope6 = sideTransSlope6;
    }

    public Integer getHasSpecialFloor() {
        return hasSpecialFloor;
    }

    public void setHasSpecialFloor(Integer hasSpecialFloor) {
        this.hasSpecialFloor = hasSpecialFloor;
    }

    public Integer getSpecialFloorRightColor() {
        return specialFloorRightColor;
    }

    public void setSpecialFloorRightColor(Integer specialFloorRightColor) {
        this.specialFloorRightColor = specialFloorRightColor;
    }

    public Double getSpecialTileDirectionWidth() {
        return specialTileDirectionWidth;
    }

    public void setSpecialTileDirectionWidth(Double specialTileDirectionWidth) {
        this.specialTileDirectionWidth = specialTileDirectionWidth;
    }

    public Double getSpecialTileAlertWidth() {
        return specialTileAlertWidth;
    }

    public void setSpecialTileAlertWidth(Double specialTileAlertWidth) {
        this.specialTileAlertWidth = specialTileAlertWidth;
    }

    public String getSpecialFloorObs() {
        return specialFloorObs;
    }

    public void setSpecialFloorObs(String specialFloorObs) {
        this.specialFloorObs = specialFloorObs;
    }
}
