package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class ExternalAccess {

    @PrimaryKey(autoGenerate = true)
    private int externalAccessID;
    @NonNull
    private Integer schoolEntryID;

    private Integer entranceType;
    private Integer hasSIA;
    private String floorType;
    private Double gateWidth;
    private Double gateTrailHeight;
    private Integer gateHasTrailRamp;
    private Integer gateHasObstacles;
    private Integer gateHasPayphones;

    public ExternalAccess(@NonNull Integer schoolEntryID, Integer entranceType, Integer hasSIA, String floorType,
                          Double gateWidth, Double gateTrailHeight, Integer gateHasTrailRamp, Integer gateHasObstacles,
                          Integer gateHasPayphones) {
        this.schoolEntryID = schoolEntryID;
        this.entranceType = entranceType;
        this.hasSIA = hasSIA;
        this.floorType = floorType;
        this.gateWidth = gateWidth;
        this.gateTrailHeight = gateTrailHeight;
        this.gateHasTrailRamp = gateHasTrailRamp;
        this.gateHasObstacles = gateHasObstacles;
        this.gateHasPayphones = gateHasPayphones;
    }

    public int getExternalAccessID() {
        return externalAccessID;
    }

    public void setExternalAccessID(int externalAccessID) {
        this.externalAccessID = externalAccessID;
    }

    @NonNull
    public Integer getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(@NonNull Integer schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    public Integer getEntranceType() {
        return entranceType;
    }

    public void setEntranceType(Integer entranceType) {
        this.entranceType = entranceType;
    }

    public Integer getHasSIA() {
        return hasSIA;
    }

    public void setHasSIA(Integer hasSIA) {
        this.hasSIA = hasSIA;
    }

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public Double getGateWidth() {
        return gateWidth;
    }

    public void setGateWidth(Double gateWidth) {
        this.gateWidth = gateWidth;
    }

    public Double getGateTrailHeight() {
        return gateTrailHeight;
    }

    public void setGateTrailHeight(Double gateTrailHeight) {
        this.gateTrailHeight = gateTrailHeight;
    }

    public Integer getGateHasTrailRamp() {
        return gateHasTrailRamp;
    }

    public void setGateHasTrailRamp(Integer gateHasTrailRamp) {
        this.gateHasTrailRamp = gateHasTrailRamp;
    }

    public Integer getGateHasObstacles() {
        return gateHasObstacles;
    }

    public void setGateHasObstacles(Integer gateHasObstacles) {
        this.gateHasObstacles = gateHasObstacles;
    }

    public Integer getGateHasPayphones() {
        return gateHasPayphones;
    }

    public void setGateHasPayphones(Integer gateHasPayphones) {
        this.gateHasPayphones = gateHasPayphones;
    }
}
