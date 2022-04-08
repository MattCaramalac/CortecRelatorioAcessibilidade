package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class WaterFountainEntry {

    @PrimaryKey(autoGenerate = true)
    private int waterFountainID;
    private int blockID;
    private String fountainLocation;
    private Integer typeWaterFountain;
    private String fountainTypeObs;

    private Integer otherAllowSideApproximation;
    private String lateralApproxObs;
    private Double otherFaucetHeight;
    private Integer otherHasCupHolder;
    private Double otherCupHolderHeight;

    private Integer hasSpoutsDifferentHeights;
    private Double highestSpoutHeight;
    private Double lowestSpoutHeight;
    private Integer spoutAllowFrontalApproximation;
    private Double spoutFrontalApproxDepth;
    private Double frontalApproxLowestSpout;

    private String fountainObs;

    public WaterFountainEntry(int blockID, String fountainLocation, Integer typeWaterFountain, String fountainTypeObs, Integer otherAllowSideApproximation, String lateralApproxObs,
                              Double otherFaucetHeight, Integer otherHasCupHolder, Double otherCupHolderHeight, Integer hasSpoutsDifferentHeights, Double highestSpoutHeight,
                              Double lowestSpoutHeight, Integer spoutAllowFrontalApproximation, Double spoutFrontalApproxDepth, Double frontalApproxLowestSpout, String fountainObs) {
        this.blockID = blockID;
        this.fountainLocation = fountainLocation;
        this.typeWaterFountain = typeWaterFountain;
        this.fountainTypeObs = fountainTypeObs;
        this.otherAllowSideApproximation = otherAllowSideApproximation;
        this.lateralApproxObs = lateralApproxObs;
        this.otherFaucetHeight = otherFaucetHeight;
        this.otherHasCupHolder = otherHasCupHolder;
        this.otherCupHolderHeight = otherCupHolderHeight;
        this.hasSpoutsDifferentHeights = hasSpoutsDifferentHeights;
        this.highestSpoutHeight = highestSpoutHeight;
        this.lowestSpoutHeight = lowestSpoutHeight;
        this.spoutAllowFrontalApproximation = spoutAllowFrontalApproximation;
        this.spoutFrontalApproxDepth = spoutFrontalApproxDepth;
        this.frontalApproxLowestSpout = frontalApproxLowestSpout;
        this.fountainObs = fountainObs;
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

    public String getFountainLocation() {
        return fountainLocation;
    }

    public void setFountainLocation(String fountainLocation) {
        this.fountainLocation = fountainLocation;
    }

    public Integer getTypeWaterFountain() {
        return typeWaterFountain;
    }

    public void setTypeWaterFountain(Integer typeWaterFountain) {
        this.typeWaterFountain = typeWaterFountain;
    }

    public Integer getOtherAllowSideApproximation() {
        return otherAllowSideApproximation;
    }

    public void setOtherAllowSideApproximation(Integer otherAllowSideApproximation) {
        this.otherAllowSideApproximation = otherAllowSideApproximation;
    }

    public Double getOtherFaucetHeight() {
        return otherFaucetHeight;
    }

    public void setOtherFaucetHeight(Double otherFaucetHeight) {
        this.otherFaucetHeight = otherFaucetHeight;
    }

    public Integer getOtherHasCupHolder() {
        return otherHasCupHolder;
    }

    public void setOtherHasCupHolder(Integer otherHasCupHolder) {
        this.otherHasCupHolder = otherHasCupHolder;
    }

    public Double getOtherCupHolderHeight() {
        return otherCupHolderHeight;
    }

    public void setOtherCupHolderHeight(Double otherCupHolderHeight) {
        this.otherCupHolderHeight = otherCupHolderHeight;
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

    public Integer getSpoutAllowFrontalApproximation() {
        return spoutAllowFrontalApproximation;
    }

    public void setSpoutAllowFrontalApproximation(Integer spoutAllowFrontalApproximation) {
        this.spoutAllowFrontalApproximation = spoutAllowFrontalApproximation;
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

    public String getLateralApproxObs() {
        return lateralApproxObs;
    }

    public void setLateralApproxObs(String lateralApproxObs) {
        this.lateralApproxObs = lateralApproxObs;
    }

    public Double getSpoutFrontalApproxDepth() {
        return spoutFrontalApproxDepth;
    }

    public void setSpoutFrontalApproxDepth(Double spoutFrontalApproxDepth) {
        this.spoutFrontalApproxDepth = spoutFrontalApproxDepth;
    }
}
