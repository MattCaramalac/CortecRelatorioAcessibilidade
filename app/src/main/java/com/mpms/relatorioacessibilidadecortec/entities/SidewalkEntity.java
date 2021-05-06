package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class SidewalkEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer sidewalkID;
    private Integer schoolEntryID;

    private String streetSidewalk;
    private String conservationStatusSidewalk;
    private Double widthSidewalk;
    private Double longitudinalSidewalkSlope;

    private Integer sidewalkHasSpecialFloor;
    private String sidewalkSpecialFloorObs;

    private Integer obligatorySidewalkSlope;
    private Integer sidewalkHasSlope;

    public SidewalkEntity(Integer schoolEntryID, String streetSidewalk, String conservationStatusSidewalk, Double widthSidewalk,
                          Double longitudinalSidewalkSlope, Integer sidewalkHasSpecialFloor, String sidewalkSpecialFloorObs,
                          Integer obligatorySidewalkSlope, Integer sidewalkHasSlope) {
        this.schoolEntryID = schoolEntryID;
        this.streetSidewalk = streetSidewalk;
        this.conservationStatusSidewalk = conservationStatusSidewalk;
        this.widthSidewalk = widthSidewalk;
        this.longitudinalSidewalkSlope = longitudinalSidewalkSlope;
        this.sidewalkHasSpecialFloor = sidewalkHasSpecialFloor;
        this.sidewalkSpecialFloorObs = sidewalkSpecialFloorObs;
        this.obligatorySidewalkSlope = obligatorySidewalkSlope;
        this.sidewalkHasSlope = sidewalkHasSlope;
    }

    public Integer getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(Integer sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public Integer getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(Integer schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    public String getStreetSidewalk() {
        return streetSidewalk;
    }

    public void setStreetSidewalk(String streetSidewalk) {
        this.streetSidewalk = streetSidewalk;
    }

    public String getConservationStatusSidewalk() {
        return conservationStatusSidewalk;
    }

    public void setConservationStatusSidewalk(String conservationStatusSidewalk) {
        this.conservationStatusSidewalk = conservationStatusSidewalk;
    }

    public Double getWidthSidewalk() {
        return widthSidewalk;
    }

    public void setWidthSidewalk(Double widthSidewalk) {
        this.widthSidewalk = widthSidewalk;
    }

    public Double getLongitudinalSidewalkSlope() {
        return longitudinalSidewalkSlope;
    }

    public void setLongitudinalSidewalkSlope(Double longitudinalSidewalkSlope) {
        this.longitudinalSidewalkSlope = longitudinalSidewalkSlope;
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

    public Integer getSidewalkHasSpecialFloor() {
        return sidewalkHasSpecialFloor;
    }

    public void setSidewalkHasSpecialFloor(Integer sidewalkHasSpecialFloor) {
        this.sidewalkHasSpecialFloor = sidewalkHasSpecialFloor;
    }

    public String getSidewalkSpecialFloorObs() {
        return sidewalkSpecialFloorObs;
    }

    public void setSidewalkSpecialFloorObs(String sidewalkSpecialFloorObs) {
        this.sidewalkSpecialFloorObs = sidewalkSpecialFloorObs;
    }
}
