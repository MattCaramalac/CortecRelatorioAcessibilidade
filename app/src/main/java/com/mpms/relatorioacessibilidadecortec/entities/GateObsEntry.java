package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolEntryID",
                onDelete = CASCADE, onUpdate = CASCADE))
public class GateObsEntry {

    @PrimaryKey(autoGenerate = true)
    private int gateObsID;
    private int schoolEntryID;

    private String accessRefPoint;
    private Integer accessibleEntrance;
    private Integer accessType;
    private Double gateDoorWidth;
    private Double barrierHeight;
    private Double barrierWidth;
    private String gateObstacleObs;

    public GateObsEntry(int schoolEntryID, String accessRefPoint, Integer accessibleEntrance, Integer accessType,
                        Double gateDoorWidth, Double barrierHeight, Double barrierWidth, String gateObstacleObs) {
        this.schoolEntryID = schoolEntryID;
        this.accessRefPoint = accessRefPoint;
        this.accessibleEntrance = accessibleEntrance;
        this.accessType = accessType;
        this.gateDoorWidth = gateDoorWidth;
        this.barrierHeight = barrierHeight;
        this.barrierWidth = barrierWidth;
        this.gateObstacleObs = gateObstacleObs;
    }

    public int getGateObsID() {
        return gateObsID;
    }

    public void setGateObsID(int gateObsID) {
        this.gateObsID = gateObsID;
    }

    public int getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(int schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    public String getAccessRefPoint() {
        return accessRefPoint;
    }

    public void setAccessRefPoint(String accessRefPoint) {
        this.accessRefPoint = accessRefPoint;
    }

    public Integer getAccessibleEntrance() {
        return accessibleEntrance;
    }

    public void setAccessibleEntrance(Integer accessibleEntrance) {
        this.accessibleEntrance = accessibleEntrance;
    }

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }

    public Double getGateDoorWidth() {
        return gateDoorWidth;
    }

    public void setGateDoorWidth(Double gateDoorWidth) {
        this.gateDoorWidth = gateDoorWidth;
    }

    public Double getBarrierHeight() {
        return barrierHeight;
    }

    public void setBarrierHeight(Double barrierHeight) {
        this.barrierHeight = barrierHeight;
    }

    public Double getBarrierWidth() {
        return barrierWidth;
    }

    public void setBarrierWidth(Double barrierWidth) {
        this.barrierWidth = barrierWidth;
    }

    public String getGateObstacleObs() {
        return gateObstacleObs;
    }

    public void setGateObstacleObs(String gateObstacleObs) {
        this.gateObstacleObs = gateObstacleObs;
    }
}
