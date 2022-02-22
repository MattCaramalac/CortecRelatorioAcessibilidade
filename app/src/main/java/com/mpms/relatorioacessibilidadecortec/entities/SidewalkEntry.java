package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class SidewalkEntry {

    @PrimaryKey(autoGenerate = true)
    private int sidewalkID;
    private int blockID;

    private String sidewalkLocation;
    private Integer sidewalkConservationStatus;
    private String sidewalkConservationObs;
    private Double widthSidewalk;
    private Integer sidewalkHasTactileFloor;
    private Integer tactileFloorConservationStatus;
    private String tactileFloorObs;
    private Integer sidewalkHasSlope;
    private String sidewalkObs;

    public SidewalkEntry(int blockID, String sidewalkLocation, Integer sidewalkConservationStatus, String sidewalkConservationObs, Double widthSidewalk,
                         Integer sidewalkHasTactileFloor, Integer tactileFloorConservationStatus, String tactileFloorObs, Integer sidewalkHasSlope,
                         String sidewalkObs) {
        this.blockID = blockID;
        this.sidewalkLocation = sidewalkLocation;
        this.sidewalkConservationStatus = sidewalkConservationStatus;
        this.sidewalkConservationObs = sidewalkConservationObs;
        this.widthSidewalk = widthSidewalk;
        this.sidewalkHasTactileFloor = sidewalkHasTactileFloor;
        this.tactileFloorConservationStatus = tactileFloorConservationStatus;
        this.tactileFloorObs = tactileFloorObs;
        this.sidewalkHasSlope = sidewalkHasSlope;
        this.sidewalkObs = sidewalkObs;
    }

    public int getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(int sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public String getSidewalkLocation() {
        return sidewalkLocation;
    }

    public void setSidewalkLocation(String sidewalkLocation) {
        this.sidewalkLocation = sidewalkLocation;
    }

    public Integer getSidewalkConservationStatus() {
        return sidewalkConservationStatus;
    }

    public void setSidewalkConservationStatus(Integer sidewalkConservationStatus) {
        this.sidewalkConservationStatus = sidewalkConservationStatus;
    }

    public String getSidewalkConservationObs() {
        return sidewalkConservationObs;
    }

    public void setSidewalkConservationObs(String sidewalkConservationObs) {
        this.sidewalkConservationObs = sidewalkConservationObs;
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
