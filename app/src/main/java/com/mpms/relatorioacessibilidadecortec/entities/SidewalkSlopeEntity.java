package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = SchoolEntry.class,
        parentColumns = "cadID",
        childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = SidewalkEntity.class,
        parentColumns = "sidewalkID",
        childColumns = "sidewalkEntryID",
        onDelete = CASCADE, onUpdate = CASCADE)})
public class SidewalkSlopeEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer sidewalkSlopeID;
    private Integer schoolEntryID;
    private Integer sidewalkEntryID;

    private Double sidewalkSlopeLeaning;
    private Double leftEdgeSlopeLeaning;
    private Double rightEdgeSlopeLeaning;
    private Double freeSpaceSlopeEnd;
    private String slopeObs;

    public SidewalkSlopeEntity(Integer schoolEntryID, Integer sidewalkEntryID, Double sidewalkSlopeLeaning,
                               Double leftEdgeSlopeLeaning, Double rightEdgeSlopeLeaning, Double freeSpaceSlopeEnd, String slopeObs) {
        this.schoolEntryID = schoolEntryID;
        this.sidewalkEntryID = sidewalkEntryID;
        this.sidewalkSlopeLeaning = sidewalkSlopeLeaning;
        this.leftEdgeSlopeLeaning = leftEdgeSlopeLeaning;
        this.rightEdgeSlopeLeaning = rightEdgeSlopeLeaning;
        this.freeSpaceSlopeEnd = freeSpaceSlopeEnd;
        this.slopeObs = slopeObs;
    }

    public Integer getSidewalkSlopeID() {
        return sidewalkSlopeID;
    }

    public void setSidewalkSlopeID(Integer sidewalkSlopeID) {
        this.sidewalkSlopeID = sidewalkSlopeID;
    }

    public Integer getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(Integer schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    public Integer getSidewalkEntryID() {
        return sidewalkEntryID;
    }

    public void setSidewalkEntryID(Integer sidewalkEntryID) {
        this.sidewalkEntryID = sidewalkEntryID;
    }

    public Double getSidewalkSlopeLeaning() {
        return sidewalkSlopeLeaning;
    }

    public void setSidewalkSlopeLeaning(Double sidewalkSlopeLeaning) {
        this.sidewalkSlopeLeaning = sidewalkSlopeLeaning;
    }

    public Double getLeftEdgeSlopeLeaning() {
        return leftEdgeSlopeLeaning;
    }

    public void setLeftEdgeSlopeLeaning(Double leftEdgeSlopeLeaning) {
        this.leftEdgeSlopeLeaning = leftEdgeSlopeLeaning;
    }

    public Double getRightEdgeSlopeLeaning() {
        return rightEdgeSlopeLeaning;
    }

    public void setRightEdgeSlopeLeaning(Double rightEdgeSlopeLeaning) {
        this.rightEdgeSlopeLeaning = rightEdgeSlopeLeaning;
    }

    public Double getFreeSpaceSlopeEnd() {
        return freeSpaceSlopeEnd;
    }

    public void setFreeSpaceSlopeEnd(Double freeSpaceSlopeEnd) {
        this.freeSpaceSlopeEnd = freeSpaceSlopeEnd;
    }

    public String getSlopeObs() {
        return slopeObs;
    }

    public void setSlopeObs(String slopeObs) {
        this.slopeObs = slopeObs;
    }
}
