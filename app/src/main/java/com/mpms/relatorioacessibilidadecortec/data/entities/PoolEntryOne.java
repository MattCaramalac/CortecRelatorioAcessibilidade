package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PoolEntryOne {

    @PrimaryKey
    private int poolID;

    private String poolLocation;
    private Integer allowPoolAccess;
    private String poolAccessObs;
    private Integer allowWaterFlow;
    private String waterFlowObs;
    private Integer floorAccessible;
    private String floorAccessObs;
    private Integer poolHasShower;
    private Integer hasAccessShower;
    private String showerObs;
    private Integer poolHasFence;
    private Double fenceHeight;
    private Double fenceGapWidth;
    private Integer fenceHasAutoGate;
    private Double autoGateHandleHeight;
    private Integer gateHasAntiRust;
    private Integer gateAllowVision;
    private String gateObs;
    private String poolPhoto;

    public PoolEntryOne(int poolID, String poolLocation, Integer allowPoolAccess, String poolAccessObs, Integer allowWaterFlow, String waterFlowObs, Integer floorAccessible,
                        String floorAccessObs, Integer poolHasShower, Integer hasAccessShower, String showerObs, Integer poolHasFence, Double fenceHeight, Double fenceGapWidth,
                        Integer fenceHasAutoGate, Double autoGateHandleHeight, Integer gateHasAntiRust, Integer gateAllowVision, String gateObs, String poolPhoto) {
        this.poolID = poolID;
        this.poolLocation = poolLocation;
        this.allowPoolAccess = allowPoolAccess;
        this.poolAccessObs = poolAccessObs;
        this.allowWaterFlow = allowWaterFlow;
        this.waterFlowObs = waterFlowObs;
        this.floorAccessible = floorAccessible;
        this.floorAccessObs = floorAccessObs;
        this.poolHasShower = poolHasShower;
        this.hasAccessShower = hasAccessShower;
        this.showerObs = showerObs;
        this.poolHasFence = poolHasFence;
        this.fenceHeight = fenceHeight;
        this.fenceGapWidth = fenceGapWidth;
        this.fenceHasAutoGate = fenceHasAutoGate;
        this.autoGateHandleHeight = autoGateHandleHeight;
        this.gateHasAntiRust = gateHasAntiRust;
        this.gateAllowVision = gateAllowVision;
        this.gateObs = gateObs;
        this.poolPhoto = poolPhoto;
    }

    public int getPoolID() {
        return poolID;
    }

    public void setPoolID(int poolID) {
        this.poolID = poolID;
    }

    public String getPoolLocation() {
        return poolLocation;
    }

    public void setPoolLocation(String poolLocation) {
        this.poolLocation = poolLocation;
    }

    public Integer getAllowPoolAccess() {
        return allowPoolAccess;
    }

    public void setAllowPoolAccess(Integer allowPoolAccess) {
        this.allowPoolAccess = allowPoolAccess;
    }

    public String getPoolAccessObs() {
        return poolAccessObs;
    }

    public void setPoolAccessObs(String poolAccessObs) {
        this.poolAccessObs = poolAccessObs;
    }

    public Integer getAllowWaterFlow() {
        return allowWaterFlow;
    }

    public void setAllowWaterFlow(Integer allowWaterFlow) {
        this.allowWaterFlow = allowWaterFlow;
    }

    public String getWaterFlowObs() {
        return waterFlowObs;
    }

    public void setWaterFlowObs(String waterFlowObs) {
        this.waterFlowObs = waterFlowObs;
    }

    public Integer getFloorAccessible() {
        return floorAccessible;
    }

    public void setFloorAccessible(Integer floorAccessible) {
        this.floorAccessible = floorAccessible;
    }

    public String getFloorAccessObs() {
        return floorAccessObs;
    }

    public void setFloorAccessObs(String floorAccessObs) {
        this.floorAccessObs = floorAccessObs;
    }

    public Integer getPoolHasShower() {
        return poolHasShower;
    }

    public void setPoolHasShower(Integer poolHasShower) {
        this.poolHasShower = poolHasShower;
    }

    public Integer getHasAccessShower() {
        return hasAccessShower;
    }

    public void setHasAccessShower(Integer hasAccessShower) {
        this.hasAccessShower = hasAccessShower;
    }

    public String getShowerObs() {
        return showerObs;
    }

    public void setShowerObs(String showerObs) {
        this.showerObs = showerObs;
    }

    public Integer getPoolHasFence() {
        return poolHasFence;
    }

    public void setPoolHasFence(Integer poolHasFence) {
        this.poolHasFence = poolHasFence;
    }

    public Double getFenceHeight() {
        return fenceHeight;
    }

    public void setFenceHeight(Double fenceHeight) {
        this.fenceHeight = fenceHeight;
    }

    public Double getFenceGapWidth() {
        return fenceGapWidth;
    }

    public void setFenceGapWidth(Double fenceGapWidth) {
        this.fenceGapWidth = fenceGapWidth;
    }

    public Integer getFenceHasAutoGate() {
        return fenceHasAutoGate;
    }

    public void setFenceHasAutoGate(Integer fenceHasAutoGate) {
        this.fenceHasAutoGate = fenceHasAutoGate;
    }

    public Double getAutoGateHandleHeight() {
        return autoGateHandleHeight;
    }

    public void setAutoGateHandleHeight(Double autoGateHandleHeight) {
        this.autoGateHandleHeight = autoGateHandleHeight;
    }

    public Integer getGateHasAntiRust() {
        return gateHasAntiRust;
    }

    public void setGateHasAntiRust(Integer gateHasAntiRust) {
        this.gateHasAntiRust = gateHasAntiRust;
    }

    public Integer getGateAllowVision() {
        return gateAllowVision;
    }

    public void setGateAllowVision(Integer gateAllowVision) {
        this.gateAllowVision = gateAllowVision;
    }

    public String getGateObs() {
        return gateObs;
    }

    public void setGateObs(String gateObs) {
        this.gateObs = gateObs;
    }

    public String getPoolPhoto() {
        return poolPhoto;
    }

    public void setPoolPhoto(String poolPhoto) {
        this.poolPhoto = poolPhoto;
    }
}
