package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class WaterFountainEntry {

    @PrimaryKey(autoGenerate = true)
    private Integer waterFountainID;
    @NonNull
    private Integer schoolEntryID;
    @NonNull
    private Integer typeWaterFountain;

    private Integer otherAllowSideApproximation;
    private Double otherFaucetHeight;
    private Integer otherHasCupHolder;
    private Double otherCupHolderHeight;

    private Integer spoutAllowFrontalApproximation;
    private Double highestSpoutHeight;
    private Double lowestSpoutHeight;
    private Double freeSpaceLowestSpout;

    @NonNull
    public Integer getWaterFountainID() {
        return waterFountainID;
    }

    public void setWaterFountainID(@NonNull Integer waterFountainID) {
        this.waterFountainID = waterFountainID;
    }

    @NonNull
    public Integer getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(@NonNull Integer schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    @NonNull
    public Integer getTypeWaterFountain() {
        return typeWaterFountain;
    }

    public void setTypeWaterFountain(@NonNull Integer typeWaterFountain) {
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

    public Integer getSpoutAllowFrontalApproximation() {
        return spoutAllowFrontalApproximation;
    }

    public void setSpoutAllowFrontalApproximation(Integer spoutAllowFrontalApproximation) {
        this.spoutAllowFrontalApproximation = spoutAllowFrontalApproximation;
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

    public Double getFreeSpaceLowestSpout() {
        return freeSpaceLowestSpout;
    }

    public void setFreeSpaceLowestSpout(Double freeSpaceLowestSpout) {
        this.freeSpaceLowestSpout = freeSpaceLowestSpout;
    }

    public WaterFountainEntry(@NonNull Integer schoolEntryID, @NonNull Integer typeWaterFountain, Integer otherAllowSideApproximation,
                              Double otherFaucetHeight, Integer otherHasCupHolder, Double otherCupHolderHeight,
                              Integer spoutAllowFrontalApproximation, Double highestSpoutHeight, Double lowestSpoutHeight,
                              Double freeSpaceLowestSpout) {
        this.schoolEntryID = schoolEntryID;
        this.typeWaterFountain = typeWaterFountain;
        this.otherAllowSideApproximation = otherAllowSideApproximation;
        this.otherFaucetHeight = otherFaucetHeight;
        this.otherHasCupHolder = otherHasCupHolder;
        this.otherCupHolderHeight = otherCupHolderHeight;
        this.spoutAllowFrontalApproximation = spoutAllowFrontalApproximation;
        this.highestSpoutHeight = highestSpoutHeight;
        this.lowestSpoutHeight = lowestSpoutHeight;
        this.freeSpaceLowestSpout = freeSpaceLowestSpout;
    }
}
