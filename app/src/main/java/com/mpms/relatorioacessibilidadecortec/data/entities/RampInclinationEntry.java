package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = FlightsRampStairsEntry.class, parentColumns = "flightID", childColumns = "flightID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class RampInclinationEntry {

    @PrimaryKey(autoGenerate = true)
    private int rampInclinationID;
    private int flightID;
    private int flightInclinationNumber;
    private double inclinationValue;

    public RampInclinationEntry(int flightID, int flightInclinationNumber, double inclinationValue) {
        this.flightID = flightID;
        this.flightInclinationNumber = flightInclinationNumber;
        this.inclinationValue = inclinationValue;
    }

    public int getRampInclinationID() {
        return rampInclinationID;
    }

    public void setRampInclinationID(int rampInclinationID) {
        this.rampInclinationID = rampInclinationID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getFlightInclinationNumber() {
        return flightInclinationNumber;
    }

    public void setFlightInclinationNumber(int flightInclinationNumber) {
        this.flightInclinationNumber = flightInclinationNumber;
    }

    public double getInclinationValue() {
        return inclinationValue;
    }

    public void setInclinationValue(double inclinationValue) {
        this.inclinationValue = inclinationValue;
    }
}
