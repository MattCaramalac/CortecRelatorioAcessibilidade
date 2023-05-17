package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PoolEntryTwo {

    @PrimaryKey
    private int poolID;

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

    public PoolEntryTwo(int poolID, Integer isSportsPool, Integer hasPavementedSide, Double pavementWidth, Integer isPavementAccess,
                        String pavementObs, Integer usedInCompetitions, Double poolDepth, Integer poolType, Integer poolHasRamp,
                        Integer poolHasStairs, Integer poolHasBench, Integer poolHasEquip, String poolPhoto2, String poolObs) {
        this.poolID = poolID;
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
