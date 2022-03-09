package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SidewalkEntry.class, parentColumns = "sidewalkID", childColumns = "sidewalkID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class SidewalkSlopeEntry {

    @PrimaryKey(autoGenerate = true)
    private int sidewalkSlopeID;
    private int sidewalkID;

    private String slopeLocation;
    private double slopeWidth;
    private int longMeasureQnt;
    private Double longMeasure1;
    private Double longMeasure2;
    private Double longMeasure3;
    private Double longMeasure4;
    private int hasLeftWingSlope;
    private Integer leftWingMeasureQnt;
    private Double leftMeasure1;
    private Double leftMeasure2;
    private Double leftMeasure3;
    private Double leftMeasure4;
    private int hasRightWingSlope;
    private Integer rightWingMeasureQnt;
    private Double rightMeasure1;
    private Double rightMeasure2;
    private Double rightMeasure3;
    private Double rightMeasure4;
    private int hasTactileFloor;
    private String tactileFloorObs;
    private int accessibleSlopeFloor;
    private String accessibleSlopeFloorObs;
    private String slopeObs;

    public SidewalkSlopeEntry(int sidewalkID, String slopeLocation, double slopeWidth, int longMeasureQnt, Double longMeasure1, Double longMeasure2, Double longMeasure3,
                              Double longMeasure4, int hasLeftWingSlope, Integer leftWingMeasureQnt, Double leftMeasure1, Double leftMeasure2, Double leftMeasure3,
                              Double leftMeasure4, int hasRightWingSlope, Integer rightWingMeasureQnt, Double rightMeasure1, Double rightMeasure2, Double rightMeasure3,
                              Double rightMeasure4, int hasTactileFloor, String tactileFloorObs, int accessibleSlopeFloor, String accessibleSlopeFloorObs, String slopeObs) {
        this.sidewalkID = sidewalkID;
        this.slopeLocation = slopeLocation;
        this.slopeWidth = slopeWidth;
        this.longMeasureQnt = longMeasureQnt;
        this.longMeasure1 = longMeasure1;
        this.longMeasure2 = longMeasure2;
        this.longMeasure3 = longMeasure3;
        this.longMeasure4 = longMeasure4;
        this.hasLeftWingSlope = hasLeftWingSlope;
        this.leftWingMeasureQnt = leftWingMeasureQnt;
        this.leftMeasure1 = leftMeasure1;
        this.leftMeasure2 = leftMeasure2;
        this.leftMeasure3 = leftMeasure3;
        this.leftMeasure4 = leftMeasure4;
        this.hasRightWingSlope = hasRightWingSlope;
        this.rightWingMeasureQnt = rightWingMeasureQnt;
        this.rightMeasure1 = rightMeasure1;
        this.rightMeasure2 = rightMeasure2;
        this.rightMeasure3 = rightMeasure3;
        this.rightMeasure4 = rightMeasure4;
        this.hasTactileFloor = hasTactileFloor;
        this.tactileFloorObs = tactileFloorObs;
        this.accessibleSlopeFloor = accessibleSlopeFloor;
        this.accessibleSlopeFloorObs = accessibleSlopeFloorObs;
        this.slopeObs = slopeObs;
    }

    public int getSidewalkSlopeID() {
        return sidewalkSlopeID;
    }

    public void setSidewalkSlopeID(int sidewalkSlopeID) {
        this.sidewalkSlopeID = sidewalkSlopeID;
    }

    public int getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(int sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public String getSlopeLocation() {
        return slopeLocation;
    }

    public void setSlopeLocation(String slopeLocation) {
        this.slopeLocation = slopeLocation;
    }

    public double getSlopeWidth() {
        return slopeWidth;
    }

    public void setSlopeWidth(double slopeWidth) {
        this.slopeWidth = slopeWidth;
    }

    public int getLongMeasureQnt() {
        return longMeasureQnt;
    }

    public void setLongMeasureQnt(int longMeasureQnt) {
        this.longMeasureQnt = longMeasureQnt;
    }

    public Double getLongMeasure1() {
        return longMeasure1;
    }

    public void setLongMeasure1(Double longMeasure1) {
        this.longMeasure1 = longMeasure1;
    }

    public Double getLongMeasure2() {
        return longMeasure2;
    }

    public void setLongMeasure2(Double longMeasure2) {
        this.longMeasure2 = longMeasure2;
    }

    public Double getLongMeasure3() {
        return longMeasure3;
    }

    public void setLongMeasure3(Double longMeasure3) {
        this.longMeasure3 = longMeasure3;
    }

    public Double getLongMeasure4() {
        return longMeasure4;
    }

    public void setLongMeasure4(Double longMeasure4) {
        this.longMeasure4 = longMeasure4;
    }

    public int getHasLeftWingSlope() {
        return hasLeftWingSlope;
    }

    public void setHasLeftWingSlope(int hasLeftWingSlope) {
        this.hasLeftWingSlope = hasLeftWingSlope;
    }

    public Integer getLeftWingMeasureQnt() {
        return leftWingMeasureQnt;
    }

    public void setLeftWingMeasureQnt(Integer leftWingMeasureQnt) {
        this.leftWingMeasureQnt = leftWingMeasureQnt;
    }

    public Double getLeftMeasure1() {
        return leftMeasure1;
    }

    public void setLeftMeasure1(Double leftMeasure1) {
        this.leftMeasure1 = leftMeasure1;
    }

    public Double getLeftMeasure2() {
        return leftMeasure2;
    }

    public void setLeftMeasure2(Double leftMeasure2) {
        this.leftMeasure2 = leftMeasure2;
    }

    public Double getLeftMeasure3() {
        return leftMeasure3;
    }

    public void setLeftMeasure3(Double leftMeasure3) {
        this.leftMeasure3 = leftMeasure3;
    }

    public Double getLeftMeasure4() {
        return leftMeasure4;
    }

    public void setLeftMeasure4(Double leftMeasure4) {
        this.leftMeasure4 = leftMeasure4;
    }

    public int getHasRightWingSlope() {
        return hasRightWingSlope;
    }

    public void setHasRightWingSlope(int hasRightWingSlope) {
        this.hasRightWingSlope = hasRightWingSlope;
    }

    public Integer getRightWingMeasureQnt() {
        return rightWingMeasureQnt;
    }

    public void setRightWingMeasureQnt(Integer rightWingMeasureQnt) {
        this.rightWingMeasureQnt = rightWingMeasureQnt;
    }

    public Double getRightMeasure1() {
        return rightMeasure1;
    }

    public void setRightMeasure1(Double rightMeasure1) {
        this.rightMeasure1 = rightMeasure1;
    }

    public Double getRightMeasure2() {
        return rightMeasure2;
    }

    public void setRightMeasure2(Double rightMeasure2) {
        this.rightMeasure2 = rightMeasure2;
    }

    public Double getRightMeasure3() {
        return rightMeasure3;
    }

    public void setRightMeasure3(Double rightMeasure3) {
        this.rightMeasure3 = rightMeasure3;
    }

    public Double getRightMeasure4() {
        return rightMeasure4;
    }

    public void setRightMeasure4(Double rightMeasure4) {
        this.rightMeasure4 = rightMeasure4;
    }

    public int getHasTactileFloor() {
        return hasTactileFloor;
    }

    public void setHasTactileFloor(int hasTactileFloor) {
        this.hasTactileFloor = hasTactileFloor;
    }

    public String getTactileFloorObs() {
        return tactileFloorObs;
    }

    public void setTactileFloorObs(String tactileFloorObs) {
        this.tactileFloorObs = tactileFloorObs;
    }

    public int getAccessibleSlopeFloor() {
        return accessibleSlopeFloor;
    }

    public void setAccessibleSlopeFloor(int accessibleSlopeFloor) {
        this.accessibleSlopeFloor = accessibleSlopeFloor;
    }

    public String getAccessibleSlopeFloorObs() {
        return accessibleSlopeFloorObs;
    }

    public void setAccessibleSlopeFloorObs(String accessibleSlopeFloorObs) {
        this.accessibleSlopeFloorObs = accessibleSlopeFloorObs;
    }

    public String getSlopeObs() {
        return slopeObs;
    }

    public void setSlopeObs(String slopeObs) {
        this.slopeObs = slopeObs;
    }
}
