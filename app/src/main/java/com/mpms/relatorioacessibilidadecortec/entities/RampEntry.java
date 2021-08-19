package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID",
        childColumns = "schoolID", onDelete = CASCADE, onUpdate = CASCADE))
public class RampEntry {

    @PrimaryKey(autoGenerate = true)
    private int rampID;
    private int schoolID;
    private String rampLocation;
    private int flightRampQuantity;

    public RampEntry(int schoolID, String rampLocation, int flightRampQuantity) {
        this.schoolID = schoolID;
        this.rampLocation = rampLocation;
        this.flightRampQuantity = flightRampQuantity;
    }

    public int getRampID() {
        return rampID;
    }

    public void setRampID(int rampID) {
        this.rampID = rampID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getRampLocation() {
        return rampLocation;
    }

    public void setRampLocation(String rampLocation) {
        this.rampLocation = rampLocation;
    }

    public int getFlightRampQuantity() {
        return flightRampQuantity;
    }

    public void setFlightRampQuantity(int flightRampQuantity) {
        this.flightRampQuantity = flightRampQuantity;
    }
}
