package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SidewalkEntryTwo {

    @PrimaryKey
    private int sidewalkID;

    private Integer sideFloorIsAccessible;
    private String accessFloorObs;
    private Integer sideHasSlope;
    private String sidewalkObs;
    private Integer hasAerialObstacle;
    private String aerialObstacleDesc;
    private Integer sidewalkHasLids;
    private String sidewalkLidDesc;
    private Integer sideConStatus;
    private String sideConsObs;
    private Integer sideHasPayphones;
    private Integer sideHasStairs;
    private Integer sideHasRamps;
    private Integer sideReqSlopes;

    public SidewalkEntryTwo(int sidewalkID, Integer sideFloorIsAccessible, String accessFloorObs, Integer sideHasSlope, String sidewalkObs,
                            Integer hasAerialObstacle, String aerialObstacleDesc, Integer sidewalkHasLids, String sidewalkLidDesc, Integer sideConStatus,
                            String sideConsObs, Integer sideHasPayphones, Integer sideHasStairs, Integer sideHasRamps, Integer sideReqSlopes) {
        this.sidewalkID = sidewalkID;
        this.sideFloorIsAccessible = sideFloorIsAccessible;
        this.accessFloorObs = accessFloorObs;
        this.sideHasSlope = sideHasSlope;
        this.sidewalkObs = sidewalkObs;
        this.hasAerialObstacle = hasAerialObstacle;
        this.aerialObstacleDesc = aerialObstacleDesc;
        this.sidewalkHasLids = sidewalkHasLids;
        this.sidewalkLidDesc = sidewalkLidDesc;
        this.sideConStatus = sideConStatus;
        this.sideConsObs = sideConsObs;
        this.sideHasPayphones = sideHasPayphones;
        this.sideHasStairs = sideHasStairs;
        this.sideHasRamps = sideHasRamps;
        this.sideReqSlopes = sideReqSlopes;
    }

    public int getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(int sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public Integer getSideFloorIsAccessible() {
        return sideFloorIsAccessible;
    }

    public void setSideFloorIsAccessible(Integer sideFloorIsAccessible) {
        this.sideFloorIsAccessible = sideFloorIsAccessible;
    }

    public String getAccessFloorObs() {
        return accessFloorObs;
    }

    public void setAccessFloorObs(String accessFloorObs) {
        this.accessFloorObs = accessFloorObs;
    }

    public Integer getSideHasSlope() {
        return sideHasSlope;
    }

    public void setSideHasSlope(Integer sideHasSlope) {
        this.sideHasSlope = sideHasSlope;
    }

    public String getSidewalkObs() {
        return sidewalkObs;
    }

    public void setSidewalkObs(String sidewalkObs) {
        this.sidewalkObs = sidewalkObs;
    }

    public Integer getHasAerialObstacle() {
        return hasAerialObstacle;
    }

    public void setHasAerialObstacle(Integer hasAerialObstacle) {
        this.hasAerialObstacle = hasAerialObstacle;
    }

    public String getAerialObstacleDesc() {
        return aerialObstacleDesc;
    }

    public void setAerialObstacleDesc(String aerialObstacleDesc) {
        this.aerialObstacleDesc = aerialObstacleDesc;
    }

    public Integer getSidewalkHasLids() {
        return sidewalkHasLids;
    }

    public void setSidewalkHasLids(Integer sidewalkHasLids) {
        this.sidewalkHasLids = sidewalkHasLids;
    }

    public String getSidewalkLidDesc() {
        return sidewalkLidDesc;
    }

    public void setSidewalkLidDesc(String sidewalkLidDesc) {
        this.sidewalkLidDesc = sidewalkLidDesc;
    }

    public Integer getSideConStatus() {
        return sideConStatus;
    }

    public void setSideConStatus(Integer sideConStatus) {
        this.sideConStatus = sideConStatus;
    }

    public String getSideConsObs() {
        return sideConsObs;
    }

    public void setSideConsObs(String sideConsObs) {
        this.sideConsObs = sideConsObs;
    }

    public Integer getSideHasPayphones() {
        return sideHasPayphones;
    }

    public void setSideHasPayphones(Integer sideHasPayphones) {
        this.sideHasPayphones = sideHasPayphones;
    }

    public Integer getSideHasStairs() {
        return sideHasStairs;
    }

    public void setSideHasStairs(Integer sideHasStairs) {
        this.sideHasStairs = sideHasStairs;
    }

    public Integer getSideHasRamps() {
        return sideHasRamps;
    }

    public void setSideHasRamps(Integer sideHasRamps) {
        this.sideHasRamps = sideHasRamps;
    }

    public Integer getSideReqSlopes() {
        return sideReqSlopes;
    }

    public void setSideReqSlopes(Integer sideReqSlopes) {
        this.sideReqSlopes = sideReqSlopes;
    }
}
