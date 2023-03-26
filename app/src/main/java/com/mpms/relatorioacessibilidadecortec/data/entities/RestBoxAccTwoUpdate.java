package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestBoxAccTwoUpdate {

    @PrimaryKey
    private int boxID;
    private Integer hasEmergencyButton;
    private Double emergencyHeight;
    private String emergencyObs;
    private Integer hasWaterValve;
    private Integer waterValveType;
    private Double waterValveHeight;
    private String waterValveObs;
    private Integer hasWallMirror;
    private Double wallMirrorLow;
    private Double wallMirrorHigh;
    private String wallMirrorObs;
    private String boxAccessPhoto2;

    public RestBoxAccTwoUpdate(int boxID, Integer hasEmergencyButton, Double emergencyHeight, String emergencyObs, Integer hasWaterValve, Integer waterValveType,
                               Double waterValveHeight, String waterValveObs, Integer hasWallMirror, Double wallMirrorLow, Double wallMirrorHigh, String wallMirrorObs,
                               String boxAccessPhoto2) {
        this.boxID = boxID;
        this.hasEmergencyButton = hasEmergencyButton;
        this.emergencyHeight = emergencyHeight;
        this.emergencyObs = emergencyObs;
        this.hasWaterValve = hasWaterValve;
        this.waterValveType = waterValveType;
        this.waterValveHeight = waterValveHeight;
        this.waterValveObs = waterValveObs;
        this.hasWallMirror = hasWallMirror;
        this.wallMirrorLow = wallMirrorLow;
        this.wallMirrorHigh = wallMirrorHigh;
        this.wallMirrorObs = wallMirrorObs;
        this.boxAccessPhoto2 = boxAccessPhoto2;
    }

    public int getBoxID() {
        return boxID;
    }

    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }

    public Integer getHasEmergencyButton() {
        return hasEmergencyButton;
    }

    public void setHasEmergencyButton(Integer hasEmergencyButton) {
        this.hasEmergencyButton = hasEmergencyButton;
    }

    public Double getEmergencyHeight() {
        return emergencyHeight;
    }

    public void setEmergencyHeight(Double emergencyHeight) {
        this.emergencyHeight = emergencyHeight;
    }

    public String getEmergencyObs() {
        return emergencyObs;
    }

    public void setEmergencyObs(String emergencyObs) {
        this.emergencyObs = emergencyObs;
    }

    public Integer getHasWaterValve() {
        return hasWaterValve;
    }

    public void setHasWaterValve(Integer hasWaterValve) {
        this.hasWaterValve = hasWaterValve;
    }

    public Integer getWaterValveType() {
        return waterValveType;
    }

    public void setWaterValveType(Integer waterValveType) {
        this.waterValveType = waterValveType;
    }

    public Double getWaterValveHeight() {
        return waterValveHeight;
    }

    public void setWaterValveHeight(Double waterValveHeight) {
        this.waterValveHeight = waterValveHeight;
    }

    public String getWaterValveObs() {
        return waterValveObs;
    }

    public void setWaterValveObs(String waterValveObs) {
        this.waterValveObs = waterValveObs;
    }

    public Integer getHasWallMirror() {
        return hasWallMirror;
    }

    public void setHasWallMirror(Integer hasWallMirror) {
        this.hasWallMirror = hasWallMirror;
    }

    public Double getWallMirrorLow() {
        return wallMirrorLow;
    }

    public void setWallMirrorLow(Double wallMirrorLow) {
        this.wallMirrorLow = wallMirrorLow;
    }

    public Double getWallMirrorHigh() {
        return wallMirrorHigh;
    }

    public void setWallMirrorHigh(Double wallMirrorHigh) {
        this.wallMirrorHigh = wallMirrorHigh;
    }

    public String getWallMirrorObs() {
        return wallMirrorObs;
    }

    public void setWallMirrorObs(String wallMirrorObs) {
        this.wallMirrorObs = wallMirrorObs;
    }

    public String getBoxAccessPhoto2() {
        return boxAccessPhoto2;
    }

    public void setBoxAccessPhoto2(String boxAccessPhoto2) {
        this.boxAccessPhoto2 = boxAccessPhoto2;
    }
}
