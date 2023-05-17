package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID", onDelete = CASCADE, onUpdate = CASCADE))
public class PoolEntry {

    @PrimaryKey(autoGenerate = true)
    private int poolID;
    private int blockID;
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
    private Integer isSportsPool;
    private Integer hasPavementedSide;
    private Double pavementWidth;
    private Integer isPavementAccess;
    private String pavementObs;
    private Integer usedInCompetitions;
    private Double poolDepth;
    private Integer poolType;
    private Integer poolHasRamp;
    private Integer poolHasStairs;
    private Integer poolHasBench;
    private Integer poolHasEquip;
    private String poolPhoto2;
    private String poolObs;

    public PoolEntry(int blockID, String poolLocation, Integer allowPoolAccess, String poolAccessObs, Integer allowWaterFlow, String waterFlowObs, Integer floorAccessible,
                     String floorAccessObs, Integer poolHasShower, Integer hasAccessShower, String showerObs, Integer poolHasFence, Double fenceHeight,
                     Double fenceGapWidth, Integer fenceHasAutoGate, Double autoGateHandleHeight, Integer gateHasAntiRust, Integer gateAllowVision, String gateObs, String poolPhoto,
                     Integer isSportsPool, Integer hasPavementedSide, Double pavementWidth, Integer isPavementAccess, String pavementObs, Integer usedInCompetitions, Double poolDepth,
                     Integer poolType, Integer poolHasRamp, Integer poolHasStairs, Integer poolHasBench, Integer poolHasEquip, String poolPhoto2, String poolObs) {
        this.blockID = blockID;
        this.poolLocation = poolLocation;
        this.allowPoolAccess = allowPoolAccess;
        this.poolAccessObs = poolAccessObs;
        this.allowWaterFlow = allowWaterFlow;
        this.waterFlowObs = waterFlowObs;
        this.floorAccessible = floorAccessible;
        this.floorAccessObs = floorAccessObs;
        this.poolHasShower = poolHasShower;
        this.showerObs = showerObs;
        this.hasAccessShower = hasAccessShower;
        this.poolHasFence = poolHasFence;
        this.fenceHeight = fenceHeight;
        this.fenceGapWidth = fenceGapWidth;
        this.fenceHasAutoGate = fenceHasAutoGate;
        this.autoGateHandleHeight = autoGateHandleHeight;
        this.gateHasAntiRust = gateHasAntiRust;
        this.gateAllowVision = gateAllowVision;
        this.gateObs = gateObs;
        this.poolPhoto = poolPhoto;
        this.isSportsPool = isSportsPool;
        this.hasPavementedSide = hasPavementedSide;
        this.pavementWidth = pavementWidth;
        this.isPavementAccess = isPavementAccess;
        this.pavementObs = pavementObs;
        this.usedInCompetitions = usedInCompetitions;
        this.poolDepth = poolDepth;
        this.poolType = poolType;
        this.poolHasRamp = poolHasRamp;
        this.poolHasStairs = poolHasStairs;
        this.poolHasBench = poolHasBench;
        this.poolHasEquip = poolHasEquip;
        this.poolPhoto2 = poolPhoto2;
        this.poolObs = poolObs;
    }

    public int getPoolID() {
        return poolID;
    }

    public void setPoolID(int poolID) {
        this.poolID = poolID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
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

    public String getShowerObs() {
        return showerObs;
    }

    public void setShowerObs(String showerObs) {
        this.showerObs = showerObs;
    }

    public Integer getHasAccessShower() {
        return hasAccessShower;
    }

    public void setHasAccessShower(Integer hasAccessShower) {
        this.hasAccessShower = hasAccessShower;
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

    public Integer getIsSportsPool() {
        return isSportsPool;
    }

    public void setIsSportsPool(Integer isSportsPool) {
        this.isSportsPool = isSportsPool;
    }

    public Integer getHasPavementedSide() {
        return hasPavementedSide;
    }

    public void setHasPavementedSide(Integer hasPavementedSide) {
        this.hasPavementedSide = hasPavementedSide;
    }

    public Double getPavementWidth() {
        return pavementWidth;
    }

    public void setPavementWidth(Double pavementWidth) {
        this.pavementWidth = pavementWidth;
    }

    public Integer getIsPavementAccess() {
        return isPavementAccess;
    }

    public void setIsPavementAccess(Integer isPavementAccess) {
        this.isPavementAccess = isPavementAccess;
    }

    public String getPavementObs() {
        return pavementObs;
    }

    public void setPavementObs(String pavementObs) {
        this.pavementObs = pavementObs;
    }

    public Integer getUsedInCompetitions() {
        return usedInCompetitions;
    }

    public void setUsedInCompetitions(Integer usedInCompetitions) {
        this.usedInCompetitions = usedInCompetitions;
    }

    public Double getPoolDepth() {
        return poolDepth;
    }

    public void setPoolDepth(Double poolDepth) {
        this.poolDepth = poolDepth;
    }

    public Integer getPoolType() {
        return poolType;
    }

    public void setPoolType(Integer poolType) {
        this.poolType = poolType;
    }

    public Integer getPoolHasRamp() {
        return poolHasRamp;
    }

    public void setPoolHasRamp(Integer poolHasRamp) {
        this.poolHasRamp = poolHasRamp;
    }

    public Integer getPoolHasStairs() {
        return poolHasStairs;
    }

    public void setPoolHasStairs(Integer poolHasStairs) {
        this.poolHasStairs = poolHasStairs;
    }

    public Integer getPoolHasBench() {
        return poolHasBench;
    }

    public void setPoolHasBench(Integer poolHasBench) {
        this.poolHasBench = poolHasBench;
    }

    public Integer getPoolHasEquip() {
        return poolHasEquip;
    }

    public void setPoolHasEquip(Integer poolHasEquip) {
        this.poolHasEquip = poolHasEquip;
    }

    public String getPoolPhoto2() {
        return poolPhoto2;
    }

    public void setPoolPhoto2(String poolPhoto2) {
        this.poolPhoto2 = poolPhoto2;
    }

    public String getPoolObs() {
        return poolObs;
    }

    public void setPoolObs(String poolObs) {
        this.poolObs = poolObs;
    }
}
