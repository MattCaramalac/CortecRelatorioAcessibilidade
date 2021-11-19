package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class WaterFountainEntry {

    @PrimaryKey(autoGenerate = true)
    private int waterFountainID;
    private int schoolEntryID;
    private String fountainLocation;
    private Integer typeWaterFountain;

    private Integer otherAllowSideApproximation;
    private Double otherFaucetHeight;
    private Integer otherHasCupHolder;
    private Double otherCupHolderHeight;

    private Integer hasSpoutsDifferentHeights;
    private Double highestSpoutHeight;
    private Double lowestSpoutHeight;
    private Integer spoutAllowFrontalApproximation;
    private Double frontalApproxLowestSpout;

    private String fountainObs;

    public WaterFountainEntry(int schoolEntryID, String fountainLocation, Integer typeWaterFountain, Integer otherAllowSideApproximation,
                              Double otherFaucetHeight, Integer otherHasCupHolder, Double otherCupHolderHeight, Integer hasSpoutsDifferentHeights,
                              Double highestSpoutHeight, Double lowestSpoutHeight, Integer spoutAllowFrontalApproximation,
                              Double frontalApproxLowestSpout, String fountainObs) {
        this.schoolEntryID = schoolEntryID;
        this.fountainLocation = fountainLocation;
        this.typeWaterFountain = typeWaterFountain;
        this.otherAllowSideApproximation = otherAllowSideApproximation;
        this.otherFaucetHeight = otherFaucetHeight;
        this.otherHasCupHolder = otherHasCupHolder;
        this.otherCupHolderHeight = otherCupHolderHeight;
        this.hasSpoutsDifferentHeights = hasSpoutsDifferentHeights;
        this.highestSpoutHeight = highestSpoutHeight;
        this.lowestSpoutHeight = lowestSpoutHeight;
        this.spoutAllowFrontalApproximation = spoutAllowFrontalApproximation;
        this.frontalApproxLowestSpout = frontalApproxLowestSpout;
        this.fountainObs = fountainObs;
    }

    public int getWaterFountainID() {
        return waterFountainID;
    }

    public void setWaterFountainID(int waterFountainID) {
        this.waterFountainID = waterFountainID;
    }

    public int getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(int schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
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
}
