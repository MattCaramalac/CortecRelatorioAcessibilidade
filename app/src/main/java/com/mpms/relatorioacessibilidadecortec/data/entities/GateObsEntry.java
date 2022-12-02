package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = ExternalAccess.class, parentColumns = "externalAccessID", childColumns = "extAccessID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class GateObsEntry {

    @PrimaryKey(autoGenerate = true)
    private int gateObsID;
    private int extAccessID;

    private String accessRefPoint;
    private Integer accessType;
    private Double obsHeight;
    private Double obsWidth;
    private Integer obsHasSia;
    private String gateObstacleObs;

    public GateObsEntry(int extAccessID, String accessRefPoint, Integer accessType, Double obsHeight, Double obsWidth, Integer obsHasSia, String gateObstacleObs) {
        this.extAccessID = extAccessID;
        this.accessRefPoint = accessRefPoint;
        this.accessType = accessType;
        this.obsHeight = obsHeight;
        this.obsWidth = obsWidth;
        this.obsHasSia = obsHasSia;
        this.gateObstacleObs = gateObstacleObs;
    }

    public int getGateObsID() {
        return gateObsID;
    }

    public void setGateObsID(int gateObsID) {
        this.gateObsID = gateObsID;
    }

    public int getExtAccessID() {
        return extAccessID;
    }

    public void setExtAccessID(int extAccessID) {
        this.extAccessID = extAccessID;
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

    public Double getObsHeight() {
        return obsHeight;
    }

    public void setObsHeight(Double obsHeight) {
        this.obsHeight = obsHeight;
    }

    public Double getObsWidth() {
        return obsWidth;
    }

    public void setObsWidth(Double obsWidth) {
        this.obsWidth = obsWidth;
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
