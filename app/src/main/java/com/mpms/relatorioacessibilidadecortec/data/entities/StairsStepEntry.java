package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = FlightsRampStairsEntry.class, parentColumns = "flightID", childColumns = "flightID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class StairsStepEntry {

    @PrimaryKey(autoGenerate = true)
    private int stairsStepID;
    private int flightID;
    private int flightStepNumber;
    private double stepWidth;

    public StairsStepEntry(int flightID, int flightStepNumber, double stepWidth) {
        this.flightID = flightID;
        this.flightStepNumber = flightStepNumber;
        this.stepWidth = stepWidth;
    }

    public int getStairsStepID() {
        return stairsStepID;
    }

    public void setStairsStepID(int stairsStepID) {
        this.stairsStepID = stairsStepID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getFlightStepNumber() {
        return flightStepNumber;
    }

    public void setFlightStepNumber(int flightStepNumber) {
        this.flightStepNumber = flightStepNumber;
    }

    public double getStepWidth() {
        return stepWidth;
    }

    public void setStepWidth(double stepWidth) {
        this.stepWidth = stepWidth;
    }
}
