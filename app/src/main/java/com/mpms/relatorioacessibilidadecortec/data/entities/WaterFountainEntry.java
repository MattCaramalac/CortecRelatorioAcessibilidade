package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE), @ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID",
        onDelete = CASCADE, onUpdate = CASCADE)})
public class WaterFountainEntry {

    @PrimaryKey(autoGenerate = true)
    private int waterFountainID;
    private int blockID;
    private Integer roomID;
    private String fountainLocation;
    private Integer fountainType;
    private String fountainTypeObs;

    private Integer allowSideApprox;
    private String sideApproxObs;
    private Double faucetHeight;
    private Integer hasCupHolder;
    private Double cupHolderHeight;

    private Integer hasSpoutsDifferentHeights;
    private Double highestSpoutHeight;
    private Double lowestSpoutHeight;
    private Integer allowFrontApprox;
    private Double frontalApproxDepth;
    private Double frontalApproxLowestSpout;

    private String fountainObs;
    private String fountainPhoto;

    public WaterFountainEntry(int blockID, Integer roomID, String fountainLocation, Integer fountainType, String fountainTypeObs,
                              Integer allowSideApprox, String sideApproxObs, Double faucetHeight,
                              Integer hasCupHolder, Double cupHolderHeight, Integer hasSpoutsDifferentHeights,
                              Double highestSpoutHeight, Double lowestSpoutHeight, Integer allowFrontApprox,
                              Double frontalApproxDepth, Double frontalApproxLowestSpout, String fountainObs, String fountainPhoto) {
        this.blockID = blockID;
        this.roomID = roomID;
        this.fountainLocation = fountainLocation;
        this.fountainType = fountainType;
        this.fountainTypeObs = fountainTypeObs;
        this.allowSideApprox = allowSideApprox;
        this.sideApproxObs = sideApproxObs;
        this.faucetHeight = faucetHeight;
        this.hasCupHolder = hasCupHolder;
        this.cupHolderHeight = cupHolderHeight;
        this.hasSpoutsDifferentHeights = hasSpoutsDifferentHeights;
        this.highestSpoutHeight = highestSpoutHeight;
        this.lowestSpoutHeight = lowestSpoutHeight;
        this.allowFrontApprox = allowFrontApprox;
        this.frontalApproxDepth = frontalApproxDepth;
        this.frontalApproxLowestSpout = frontalApproxLowestSpout;
        this.fountainObs = fountainObs;
        this.fountainPhoto = fountainPhoto;
    }

    public int getWaterFountainID() {
        return waterFountainID;
    }

    public void setWaterFountainID(int waterFountainID) {
        this.waterFountainID = waterFountainID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getFountainLocation() {
        return fountainLocation;
    }

    public void setFountainLocation(String fountainLocation) {
        this.fountainLocation = fountainLocation;
    }

    public Integer getFountainType() {
        return fountainType;
    }

    public void setFountainType(Integer fountainType) {
        this.fountainType = fountainType;
    }

    public Integer getAllowSideApprox() {
        return allowSideApprox;
    }

    public void setAllowSideApprox(Integer allowSideApprox) {
        this.allowSideApprox = allowSideApprox;
    }

    public Double getFaucetHeight() {
        return faucetHeight;
    }

    public void setFaucetHeight(Double faucetHeight) {
        this.faucetHeight = faucetHeight;
    }

    public Integer getHasCupHolder() {
        return hasCupHolder;
    }

    public void setHasCupHolder(Integer hasCupHolder) {
        this.hasCupHolder = hasCupHolder;
    }

    public Double getCupHolderHeight() {
        return cupHolderHeight;
    }

    public void setCupHolderHeight(Double cupHolderHeight) {
        this.cupHolderHeight = cupHolderHeight;
    }

    public Integer getHasSpoutsDifferentHeights() {
        return hasSpoutsDifferentHeights;
    }

    public void setHasSpoutsDifferentHeights(Integer hasSpoutsDifferentHeights) {
        this.hasSpoutsDifferentHeights = hasSpoutsDifferentHeights;
    }

    public Double getHighestSpoutHeight() {
        return highestSpoutHeight;
    }

    public void setHighestSpoutHeight(Double highestSpoutHeight) {
        this.highestSpoutHeight = highestSpoutHeight;
    }

    public Double getLowestSpoutHeight() {
        return lowestSpoutHeight;
    }

    public void setLowestSpoutHeight(Double lowestSpoutHeight) {
        this.lowestSpoutHeight = lowestSpoutHeight;
    }

    public Integer getAllowFrontApprox() {
        return allowFrontApprox;
    }

    public void setAllowFrontApprox(Integer allowFrontApprox) {
        this.allowFrontApprox = allowFrontApprox;
    }

    public Double getFrontalApproxLowestSpout() {
        return frontalApproxLowestSpout;
    }

    public void setFrontalApproxLowestSpout(Double frontalApproxLowestSpout) {
        this.frontalApproxLowestSpout = frontalApproxLowestSpout;
    }

    public String getFountainObs() {
        return fountainObs;
    }

    public void setFountainObs(String fountainObs) {
        this.fountainObs = fountainObs;
    }

    public String getFountainTypeObs() {
        return fountainTypeObs;
    }

    public void setFountainTypeObs(String fountainTypeObs) {
        this.fountainTypeObs = fountainTypeObs;
    }

    public String getSideApproxObs() {
        return sideApproxObs;
    }

    public void setSideApproxObs(String lateralApproxObs) {
        this.sideApproxObs = lateralApproxObs;
    }

    public Double getFrontalApproxDepth() {
        return frontalApproxDepth;
    }

    public void setFrontalApproxDepth(Double frontalApproxDepth) {
        this.frontalApproxDepth = frontalApproxDepth;
    }

    public String getFountainPhoto() {
        return fountainPhoto;
    }

    public void setFountainPhoto(String fountainPhoto) {
        this.fountainPhoto = fountainPhoto;
    }
}
