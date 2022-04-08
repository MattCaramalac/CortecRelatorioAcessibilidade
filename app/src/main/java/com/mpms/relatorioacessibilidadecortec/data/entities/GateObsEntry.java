package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = ExternalAccess.class, parentColumns = "externalAccessID", childColumns = "externalAccessID", onDelete = CASCADE, onUpdate = CASCADE))
public class GateObsEntry {

    @PrimaryKey(autoGenerate = true)
    private int gateObsID;
    private int externalAccessID;

    private String accessRefPoint;
    private Integer accessType;
    private Double gateDoorHeight;
    private Double gateDoorWidth;
    private Double barrierHeight;
    private Double barrierWidth;
    private Integer obsHasSia;
    private String gateObstacleObs;

    public GateObsEntry(int externalAccessID, String accessRefPoint, Integer accessType, Double gateDoorHeight, Double gateDoorWidth, Double barrierHeight,
                        Double barrierWidth, Integer obsHasSia, String gateObstacleObs) {
        this.externalAccessID = externalAccessID;
        this.accessRefPoint = accessRefPoint;
        this.accessType = accessType;
        this.gateDoorHeight = gateDoorHeight;
        this.gateDoorWidth = gateDoorWidth;
        this.barrierHeight = barrierHeight;
        this.barrierWidth = barrierWidth;
        this.obsHasSia = obsHasSia;
        this.gateObstacleObs = gateObstacleObs;
    }

    public int getGateObsID() {
        return gateObsID;
    }

    public void setGateObsID(int gateObsID) {
        this.gateObsID = gateObsID;
    }

    public int getExternalAccessID() {
        return externalAccessID;
    }

    public void setExternalAccessID(int externalAccessID) {
        this.externalAccessID = externalAccessID;
    }

    public String getAccessRefPoint() {
        return accessRefPoint;
    }

    public void setAccessRefPoint(String accessRefPoint) {
        this.accessRefPoint = accessRefPoint;
    }

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }

    public Double getGateDoorHeight() {
        return gateDoorHeight;
    }

    public void setGateDoorHeight(Double gateDoorHeight) {
        this.gateDoorHeight = gateDoorHeight;
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

    public Integer getObsHasSia() {
        return obsHasSia;
    }

    public void setObsHasSia(Integer obsHasSia) {
        this.obsHasSia = obsHasSia;
    }

    public String getGateObstacleObs() {
        return gateObstacleObs;
    }

    public void setGateObstacleObs(String gateObstacleObs) {
        this.gateObstacleObs = gateObstacleObs;
    }
}
