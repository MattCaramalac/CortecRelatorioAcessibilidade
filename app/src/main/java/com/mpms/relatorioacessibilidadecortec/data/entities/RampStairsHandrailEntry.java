package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = RampStairsFlightEntry.class, parentColumns = "flightID", childColumns = "flightID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class RampStairsHandrailEntry {

    @PrimaryKey(autoGenerate = true)
    private int handrailID;
    private int flightID;
    private int handrailPlacement;
    @ColumnInfo(defaultValue = "1")
    private int hasHandrail;
    private Double handrailHeight;
    private Double handrailGrip;
    private Double handrailDist;
    private Integer hasInitExtension;
    private Double initExtLength;
    private Integer hasFinalExtension;
    private Double finalExtLength;
    private String handrailObs;


    public RampStairsHandrailEntry(int flightID, int handrailPlacement, int hasHandrail, Double handrailHeight, Double handrailGrip, Double handrailDist,
                                   Integer hasInitExtension, Double initExtLength, Integer hasFinalExtension, Double finalExtLength, String handrailObs) {
        this.flightID = flightID;
        this.handrailPlacement = handrailPlacement;
        this.hasHandrail = hasHandrail;
        this.handrailHeight = handrailHeight;
        this.handrailGrip = handrailGrip;
        this.handrailDist = handrailDist;
        this.handrailObs = handrailObs;
        this.hasInitExtension = hasInitExtension;
        this.initExtLength = initExtLength;
        this.hasFinalExtension = hasFinalExtension;
        this.finalExtLength = finalExtLength;
    }

    public int getHandrailID() {
        return handrailID;
    }

    public void setHandrailID(int handrailID) {
        this.handrailID = handrailID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getHandrailPlacement() {
        return handrailPlacement;
    }

    public void setHandrailPlacement(int handrailPlacement) {
        this.handrailPlacement = handrailPlacement;
    }

    public int getHasHandrail() {
        return hasHandrail;
    }

    public void setHasHandrail(int hasHandrail) {
        this.hasHandrail = hasHandrail;
    }

    public Double getHandrailHeight() {
        return handrailHeight;
    }

    public void setHandrailHeight(Double handrailHeight) {
        this.handrailHeight = handrailHeight;
    }

    public Double getHandrailGrip() {
        return handrailGrip;
    }

    public void setHandrailGrip(Double handrailGrip) {
        this.handrailGrip = handrailGrip;
    }

    public Double getHandrailDist() {
        return handrailDist;
    }

    public void setHandrailDist(Double handrailDist) {
        this.handrailDist = handrailDist;
    }

    public String getHandrailObs() {
        return handrailObs;
    }

    public void setHandrailObs(String handrailObs) {
        this.handrailObs = handrailObs;
    }

    public Integer getHasInitExtension() {
        return hasInitExtension;
    }

    public void setHasInitExtension(Integer hasInitExtension) {
        this.hasInitExtension = hasInitExtension;
    }

    public Double getInitExtLength() {
        return initExtLength;
    }

    public void setInitExtLength(Double initExtLength) {
        this.initExtLength = initExtLength;
    }

    public Integer getHasFinalExtension() {
        return hasFinalExtension;
    }

    public void setHasFinalExtension(Integer hasFinalExtension) {
        this.hasFinalExtension = hasFinalExtension;
    }

    public Double getFinalExtLength() {
        return finalExtLength;
    }

    public void setFinalExtLength(Double finalExtLength) {
        this.finalExtLength = finalExtLength;
    }

}
