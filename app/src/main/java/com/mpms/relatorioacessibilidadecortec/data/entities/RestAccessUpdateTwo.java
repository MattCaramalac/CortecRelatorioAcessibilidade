package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestAccessUpdateTwo {

    @PrimaryKey
    private int restroomID;
    private Integer hasEmergencyButton;
    private Double emergencyHeight;
    private String emergencyObs;
    private Integer hasWaterValve;
    private Integer waterValveType;
    private Double waterValveHeight;
    private String waterValveObs;
    private Integer hasWindow;
    private Integer winQnt;
    private String winComType1;
    private Double winComHeight1;
    private String winComType2;
    private Double winComHeight2;
    private String winComType3;
    private Double winComHeight3;
    private String winObs;
    private Integer hasWallMirror;
    private Double wallMirrorLow;
    private Double wallMirrorHigh;
    private String wallMirrorObs;
    private String restAccessPhoto2;

    public RestAccessUpdateTwo(int restroomID, Integer hasEmergencyButton, Double emergencyHeight, String emergencyObs, Integer hasWaterValve, Integer waterValveType,
                               Double waterValveHeight, String waterValveObs, Integer hasWindow, Integer winQnt, String winComType1, Double winComHeight1, String winComType2,
                               Double winComHeight2, String winComType3, Double winComHeight3, String winObs, Integer hasWallMirror, Double wallMirrorLow, Double wallMirrorHigh,
                               String wallMirrorObs, String restAccessPhoto2) {
        this.restroomID = restroomID;
        this.hasEmergencyButton = hasEmergencyButton;
        this.emergencyHeight = emergencyHeight;
        this.emergencyObs = emergencyObs;
        this.hasWaterValve = hasWaterValve;
        this.waterValveType = waterValveType;
        this.waterValveHeight = waterValveHeight;
        this.waterValveObs = waterValveObs;
        this.hasWindow = hasWindow;
        this.winQnt = winQnt;
        this.winComType1 = winComType1;
        this.winComHeight1 = winComHeight1;
        this.winComType2 = winComType2;
        this.winComHeight2 = winComHeight2;
        this.winComType3 = winComType3;
        this.winComHeight3 = winComHeight3;
        this.winObs = winObs;
        this.hasWallMirror = hasWallMirror;
        this.wallMirrorLow = wallMirrorLow;
        this.wallMirrorHigh = wallMirrorHigh;
        this.wallMirrorObs = wallMirrorObs;
        this.restAccessPhoto2 = restAccessPhoto2;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
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

    public Integer getHasWindow() {
        return hasWindow;
    }

    public void setHasWindow(Integer hasWindow) {
        this.hasWindow = hasWindow;
    }

    public Integer getWinQnt() {
        return winQnt;
    }

    public void setWinQnt(Integer winQnt) {
        this.winQnt = winQnt;
    }

    public String getWinComType1() {
        return winComType1;
    }

    public void setWinComType1(String winComType1) {
        this.winComType1 = winComType1;
    }

    public Double getWinComHeight1() {
        return winComHeight1;
    }

    public void setWinComHeight1(Double winComHeight1) {
        this.winComHeight1 = winComHeight1;
    }

    public String getWinComType2() {
        return winComType2;
    }

    public void setWinComType2(String winComType2) {
        this.winComType2 = winComType2;
    }

    public Double getWinComHeight2() {
        return winComHeight2;
    }

    public void setWinComHeight2(Double winComHeight2) {
        this.winComHeight2 = winComHeight2;
    }

    public String getWinComType3() {
        return winComType3;
    }

    public void setWinComType3(String winComType3) {
        this.winComType3 = winComType3;
    }

    public Double getWinComHeight3() {
        return winComHeight3;
    }

    public void setWinComHeight3(Double winComHeight3) {
        this.winComHeight3 = winComHeight3;
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

    public String getWinObs() {
        return winObs;
    }

    public void setWinObs(String winObs) {
        this.winObs = winObs;
    }

    public String getRestAccessPhoto2() {
        return restAccessPhoto2;
    }

    public void setRestAccessPhoto2(String restAccessPhoto2) {
        this.restAccessPhoto2 = restAccessPhoto2;
    }
}
