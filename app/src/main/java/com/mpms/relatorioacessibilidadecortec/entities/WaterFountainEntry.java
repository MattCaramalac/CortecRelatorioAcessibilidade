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
    @NonNull
    private Integer waterFountainID;
    @NonNull
    private Integer schoolEntryID;
    @NonNull
    private Double waterFountainHeight;
    @NonNull
    private Double cupHolderHeight;
    @NonNull
    private Double waterFountApproximation;

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
    public Double getWaterFountainHeight() {
        return waterFountainHeight;
    }

    public void setWaterFountainHeight(@NonNull Double waterFountainHeight) {
        this.waterFountainHeight = waterFountainHeight;
    }
    @NonNull
    public Double getCupHolderHeight() {
        return cupHolderHeight;
    }

    public void setCupHolderHeight(@NonNull Double cupHolderHeight) {
        this.cupHolderHeight = cupHolderHeight;
    }
    @NonNull
    public Double getWaterFountApproximation() {
        return waterFountApproximation;
    }

    public void setWaterFountApproximation(@NonNull Double waterFountApproximation) {
        this.waterFountApproximation = waterFountApproximation;
    }

    public WaterFountainEntry(@NonNull Integer schoolEntryID, @NonNull Double waterFountainHeight,
                              @NonNull Double cupHolderHeight, @NonNull Double waterFountApproximation) {
        this.schoolEntryID = schoolEntryID;
        this.waterFountainHeight = waterFountainHeight;
        this.cupHolderHeight = cupHolderHeight;
        this.waterFountApproximation = waterFountApproximation;
    }
}
