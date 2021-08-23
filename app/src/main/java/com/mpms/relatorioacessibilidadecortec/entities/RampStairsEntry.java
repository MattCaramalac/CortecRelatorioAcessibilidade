package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID",
        childColumns = "schoolID", onDelete = CASCADE, onUpdate = CASCADE))
public class RampStairsEntry {

    @PrimaryKey(autoGenerate = true)
    private int rampStairsID;
    private int schoolID;
    private int rampStairsIdentifier;
    private String rampStairsLocation;
    private int flightsQuantity;

    public RampStairsEntry(int schoolID, int rampStairsIdentifier, String rampStairsLocation, int flightsQuantity) {
        this.schoolID = schoolID;
        this.rampStairsIdentifier = rampStairsIdentifier;
        this.rampStairsLocation = rampStairsLocation;
        this.flightsQuantity = flightsQuantity;
    }

    public int getRampStairsID() {
        return rampStairsID;
    }

    public void setRampStairsID(int rampStairsID) {
        this.rampStairsID = rampStairsID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getRampStairsIdentifier() {
        return rampStairsIdentifier;
    }

    public void setRampStairsIdentifier(int rampStairsIdentifier) {
        this.rampStairsIdentifier = rampStairsIdentifier;
    }

    public String getRampStairsLocation() {
        return rampStairsLocation;
    }

    public void setRampStairsLocation(String rampStairsLocation) {
        this.rampStairsLocation = rampStairsLocation;
    }

    public int getFlightsQuantity() {
        return flightsQuantity;
    }

    public void setFlightsQuantity(int flightsQuantity) {
        this.flightsQuantity = flightsQuantity;
    }
}
