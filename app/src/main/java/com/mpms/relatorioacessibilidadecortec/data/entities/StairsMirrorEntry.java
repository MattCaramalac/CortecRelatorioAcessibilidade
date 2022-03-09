package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = FlightsRampStairsEntry.class, parentColumns = "flightID", childColumns = "flightID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class StairsMirrorEntry {

    @PrimaryKey(autoGenerate = true)
    private int stairsMirrorID;
    private int flightID;
    private int flightMirrorNumber;
    private double mirrorHeight;

    public StairsMirrorEntry(int flightID, int flightMirrorNumber, double mirrorHeight) {
        this.flightID = flightID;
        this.flightMirrorNumber = flightMirrorNumber;
        this.mirrorHeight = mirrorHeight;
    }

    public int getStairsMirrorID() {
        return stairsMirrorID;
    }

    public void setStairsMirrorID(int stairsMirrorID) {
        this.stairsMirrorID = stairsMirrorID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getFlightMirrorNumber() {
        return flightMirrorNumber;
    }

    public void setFlightMirrorNumber(int flightMirrorNumber) {
        this.flightMirrorNumber = flightMirrorNumber;
    }

    public double getMirrorHeight() {
        return mirrorHeight;
    }

    public void setMirrorHeight(double mirrorHeight) {
        this.mirrorHeight = mirrorHeight;
    }
}
