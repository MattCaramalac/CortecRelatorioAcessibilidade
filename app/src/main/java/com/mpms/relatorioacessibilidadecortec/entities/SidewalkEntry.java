package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class SidewalkEntry {

    @PrimaryKey(autoGenerate = true)
    private int sidewalkID;
    private int schoolEntryID;

    private String sidewalkLocation;
    private String sidewalkConservationStatus;
    private Double widthSidewalk;
    private Integer sidewalkHasTactileFloor;
    private Integer tactileFloorConservationStatus;
    private String tactileFloorObs;
    private Integer obligatorySidewalkSlope;
    private Integer sidewalkHasSlope;
    private String sidewalkObs;

    public SidewalkEntry(int schoolEntryID, String sidewalkLocation, String sidewalkConservationStatus, Double widthSidewalk,
                         Integer sidewalkHasTactileFloor, Integer tactileFloorConservationStatus, String tactileFloorObs,
                         Integer obligatorySidewalkSlope, Integer sidewalkHasSlope, String sidewalkObs) {
        this.schoolEntryID = schoolEntryID;
        this.sidewalkLocation = sidewalkLocation;
        this.sidewalkConservationStatus = sidewalkConservationStatus;
        this.widthSidewalk = widthSidewalk;
        this.sidewalkHasTactileFloor = sidewalkHasTactileFloor;
        this.tactileFloorConservationStatus = tactileFloorConservationStatus;
        this.tactileFloorObs = tactileFloorObs;
        this.obligatorySidewalkSlope = obligatorySidewalkSlope;
        this.sidewalkHasSlope = sidewalkHasSlope;
        this.sidewalkObs = sidewalkObs;
    }

    public int getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(int sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public int getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(int schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    public String getSidewalkLocation() {
        return sidewalkLocation;
    }

    public void setSidewalkLocation(String sidewalkLocation) {
        this.sidewalkLocation = sidewalkLocation;
    }

    public String getSidewalkConservationStatus() {
        return sidewalkConservationStatus;
    }

    public void setSidewalkConservationStatus(String sidewalkConservationStatus) {
        this.sidewalkConservationStatus = sidewalkConservationStatus;
    }

    public Double getWidthSidewalk() {
        return widthSidewalk;
    }

    public void setWidthSidewalk(Double widthSidewalk) {
        this.widthSidewalk = widthSidewalk;
    }

    public Integer getSidewalkHasTactileFloor() {
        return sidewalkHasTactileFloor;
    }

    public void setSidewalkHasTactileFloor(Integer sidewalkHasTactileFloor) {
        this.sidewalkHasTactileFloor = sidewalkHasTactileFloor;
    }

    public Integer getTactileFloorConservationStatus() {
        return tactileFloorConservationStatus;
    }

    public void setTactileFloorConservationStatus(Integer tactileFloorConservationStatus) {
        this.tactileFloorConservationStatus = tactileFloorConservationStatus;
    }

    public String getTactileFloorObs() {
        return tactileFloorObs;
    }

    public void setTactileFloorObs(String tactileFloorObs) {
        this.tactileFloorObs = tactileFloorObs;
    }

    public Integer getObligatorySidewalkSlope() {
        return obligatorySidewalkSlope;
    }

    public void setObligatorySidewalkSlope(Integer obligatorySidewalkSlope) {
        this.obligatorySidewalkSlope = obligatorySidewalkSlope;
    }

    public Integer getSidewalkHasSlope() {
        return sidewalkHasSlope;
    }

    public void setSidewalkHasSlope(Integer sidewalkHasSlope) {
        this.sidewalkHasSlope = sidewalkHasSlope;
    }

    public String getSidewalkObs() {
        return sidewalkObs;
    }

    public void setSidewalkObs(String sidewalkObs) {
        this.sidewalkObs = sidewalkObs;
    }
}
