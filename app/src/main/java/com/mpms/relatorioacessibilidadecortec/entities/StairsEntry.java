package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID",
        childColumns = "schoolID", onDelete = CASCADE, onUpdate = CASCADE))
public class StairsEntry {

    @PrimaryKey(autoGenerate = true)
    private int stairsID;
    private int schoolID;
    private String stairsLocation;
    private int flightStairsQuantity;

    public StairsEntry(int schoolID, String stairsLocation, int flightStairsQuantity) {
        this.schoolID = schoolID;
        this.stairsLocation = stairsLocation;
        this.flightStairsQuantity = flightStairsQuantity;
    }

    public int getStairsID() {
        return stairsID;
    }

    public void setStairsID(int stairsID) {
        this.stairsID = stairsID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getStairsLocation() {
        return stairsLocation;
    }

    public void setStairsLocation(String stairsLocation) {
        this.stairsLocation = stairsLocation;
    }

    public int getFlightStairsQuantity() {
        return flightStairsQuantity;
    }

    public void setFlightStairsQuantity(int flightStairsQuantity) {
        this.flightStairsQuantity = flightStairsQuantity;
    }
}
