package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SidewalkEntryOne {

    @PrimaryKey
    private int sidewalkID;

    private String sidewalkLocation;
    private Integer streetPavement;
    private Double sidewalkWidth;
    private Double sideFreeSpaceWidth;
    private String sideMeasureObs;
    private Integer slopeMeasureQnt;
    private Double sideTransSlope1;
    private Double sideTransSlope2;
    private Double sideTransSlope3;
    private Double sideTransSlope4;
    private Double sideTransSlope5;
    private Double sideTransSlope6;
    private Integer hasSpecialFloor;
    private Integer specialFloorRightColor;
    private Double specialTileDirectionLength;
    private Double specialTileDirectionWidth;
    private Double specialTileAlertLength;
    private Double specialTileAlertWidth;
    private String specialFloorObs;

    public SidewalkEntryOne(int sidewalkID, String sidewalkLocation, Integer streetPavement, Double sidewalkWidth, Double sideFreeSpaceWidth,
                            String sideMeasureObs, Integer slopeMeasureQnt, Double sideTransSlope1, Double sideTransSlope2, Double sideTransSlope3,
                            Double sideTransSlope4, Double sideTransSlope5, Double sideTransSlope6, Integer hasSpecialFloor, Integer specialFloorRightColor,
                            Double specialTileDirectionLength, Double specialTileDirectionWidth, Double specialTileAlertLength, Double specialTileAlertWidth,
                            String specialFloorObs) {
        this.sidewalkID = sidewalkID;
        this.sidewalkLocation = sidewalkLocation;
        this.streetPavement = streetPavement;
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
        this.specialTileDirectionLength = specialTileDirectionLength;
        this.specialTileDirectionWidth = specialTileDirectionWidth;
        this.specialTileAlertLength = specialTileAlertLength;
        this.specialTileAlertWidth = specialTileAlertWidth;
        this.specialFloorObs = specialFloorObs;
    }

    public int getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(int sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public String getSidewalkLocation() {
        return sidewalkLocation;
    }

    public void setSidewalkLocation(String sidewalkLocation) {
        this.sidewalkLocation = sidewalkLocation;
    }

    public Integer getStreetPavement() {
        return streetPavement;
    }

    public void setStreetPavement(Integer streetPavement) {
        this.streetPavement = streetPavement;
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

    public Double getSpecialTileDirectionLength() {
        return specialTileDirectionLength;
    }

    public void setSpecialTileDirectionLength(Double specialTileDirectionLength) {
        this.specialTileDirectionLength = specialTileDirectionLength;
    }

    public Double getSpecialTileDirectionWidth() {
        return specialTileDirectionWidth;
    }

    public void setSpecialTileDirectionWidth(Double specialTileDirectionWidth) {
        this.specialTileDirectionWidth = specialTileDirectionWidth;
    }

    public Double getSpecialTileAlertLength() {
        return specialTileAlertLength;
    }

    public void setSpecialTileAlertLength(Double specialTileAlertLength) {
        this.specialTileAlertLength = specialTileAlertLength;
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
