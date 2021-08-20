package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = RampEntry.class, parentColumns = "rampID",
        childColumns = "rampID", onUpdate = CASCADE, onDelete = CASCADE))
public class FlightRampEntry {

    @PrimaryKey(autoGenerate = true)
    private int flightRampID;
    private int rampID;
    private double rampFlightWidth;
    private int pavementSignRamp;
    private String pavementSignRampObs;
    private int tactileFloorRamp;
    private String tactileFloorRampObs;

    public FlightRampEntry(int rampID, double rampFlightWidth, int pavementSignRamp, String pavementSignRampObs, int tactileFloorRamp, String tactileFloorRampObs) {
        this.rampID = rampID;
        this.rampFlightWidth = rampFlightWidth;
        this.pavementSignRamp = pavementSignRamp;
        this.pavementSignRampObs = pavementSignRampObs;
        this.tactileFloorRamp = tactileFloorRamp;
        this.tactileFloorRampObs = tactileFloorRampObs;
    }

    public int getFlightRampID() {
        return flightRampID;
    }

    public void setFlightRampID(int flightRampID) {
        this.flightRampID = flightRampID;
    }

    public int getRampID() {
        return rampID;
    }

    public void setRampID(int rampID) {
        this.rampID = rampID;
    }

    public double getRampFlightWidth() {
        return rampFlightWidth;
    }

    public void setRampFlightWidth(double rampFlightWidth) {
        this.rampFlightWidth = rampFlightWidth;
    }

    public int getPavementSignRamp() {
        return pavementSignRamp;
    }

    public void setPavementSignRamp(int pavementSignRamp) {
        this.pavementSignRamp = pavementSignRamp;
    }

    public String getPavementSignRampObs() {
        return pavementSignRampObs;
    }

    public void setPavementSignRampObs(String pavementSignRampObs) {
        this.pavementSignRampObs = pavementSignRampObs;
    }

    public int getTactileFloorRamp() {
        return tactileFloorRamp;
    }

    public void setTactileFloorRamp(int tactileFloorRamp) {
        this.tactileFloorRamp = tactileFloorRamp;
    }

    public String getTactileFloorRampObs() {
        return tactileFloorRampObs;
    }

    public void setTactileFloorRampObs(String tactileFloorRampObs) {
        this.tactileFloorRampObs = tactileFloorRampObs;
    }
}
