package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExtAccessSocialOne {

    @PrimaryKey
    private int externalAccessID;
    private int blockID;

    private String accessLocation;
    private Integer entranceType;
    private Integer floorIsAccessible;
    private String accessibleFloorObs;

    public ExtAccessSocialOne(int externalAccessID, int blockID, String accessLocation, Integer entranceType, Integer floorIsAccessible, String accessibleFloorObs) {
        this.externalAccessID = externalAccessID;
        this.blockID = blockID;
        this.accessLocation = accessLocation;
        this.entranceType = entranceType;
        this.floorIsAccessible = floorIsAccessible;
        this.accessibleFloorObs = accessibleFloorObs;
    }

    public int getExternalAccessID() {
        return externalAccessID;
    }

    public void setExternalAccessID(int externalAccessID) {
        this.externalAccessID = externalAccessID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public String getAccessLocation() {
        return accessLocation;
    }

    public void setAccessLocation(String accessLocation) {
        this.accessLocation = accessLocation;
    }

    public Integer getEntranceType() {
        return entranceType;
    }

    public void setEntranceType(Integer entranceType) {
        this.entranceType = entranceType;
    }

    public Integer getFloorIsAccessible() {
        return floorIsAccessible;
    }

    public void setFloorIsAccessible(Integer floorIsAccessible) {
        this.floorIsAccessible = floorIsAccessible;
    }

    public String getAccessibleFloorObs() {
        return accessibleFloorObs;
    }

    public void setAccessibleFloorObs(String accessibleFloorObs) {
        this.accessibleFloorObs = accessibleFloorObs;
    }
}
