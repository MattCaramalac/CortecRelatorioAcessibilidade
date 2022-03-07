package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class RampStairsEntry {

    @PrimaryKey(autoGenerate = true)
    private int rampStairsID;
    private int blockID;
    private int rampStairsIdentifier;
    private String rampStairsLocation;
    private int flightsQuantity;

    public RampStairsEntry(int blockID, int rampStairsIdentifier, String rampStairsLocation, int flightsQuantity) {
        this.blockID = blockID;
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

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int schoolID) {
        this.blockID = schoolID;
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
