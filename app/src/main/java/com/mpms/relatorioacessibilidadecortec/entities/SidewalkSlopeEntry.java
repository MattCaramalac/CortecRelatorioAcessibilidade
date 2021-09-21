package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SidewalkEntry.class, parentColumns = "sidewalkID", childColumns = "sidewalkEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class SidewalkSlopeEntry {

    @PrimaryKey(autoGenerate = true)
    private int sidewalkSlopeID;
    private int sidewalkID;

    private String slopeLocation;
    private double slopeWidth;
    private double slopeFrontalInclination;
    private double slopeLeftBrimInclination;
    private double slopeRightBrimInclination;
    private int slopeHasTactileFloor;
    private double slopeFreeSpace;
    private String slopeObs;

    public SidewalkSlopeEntry(int sidewalkID, String slopeLocation, double slopeWidth, double slopeFrontalInclination,
                              double slopeLeftBrimInclination, double slopeRightBrimInclination, int slopeHasTactileFloor,
                              double slopeFreeSpace, String slopeObs) {
        this.sidewalkID = sidewalkID;
        this.slopeLocation = slopeLocation;
        this.slopeWidth = slopeWidth;
        this.slopeFrontalInclination = slopeFrontalInclination;
        this.slopeLeftBrimInclination = slopeLeftBrimInclination;
        this.slopeRightBrimInclination = slopeRightBrimInclination;
        this.slopeHasTactileFloor = slopeHasTactileFloor;
        this.slopeFreeSpace = slopeFreeSpace;
        this.slopeObs = slopeObs;
    }

    public int getSidewalkSlopeID() {
        return sidewalkSlopeID;
    }

    public void setSidewalkSlopeID(int sidewalkSlopeID) {
        this.sidewalkSlopeID = sidewalkSlopeID;
    }

    public int getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(int sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public String getSlopeLocation() {
        return slopeLocation;
    }

    public void setSlopeLocation(String slopeLocation) {
        this.slopeLocation = slopeLocation;
    }

    public double getSlopeWidth() {
        return slopeWidth;
    }

    public void setSlopeWidth(double slopeWidth) {
        this.slopeWidth = slopeWidth;
    }

    public double getSlopeFrontalInclination() {
        return slopeFrontalInclination;
    }

    public void setSlopeFrontalInclination(double slopeFrontalInclination) {
        this.slopeFrontalInclination = slopeFrontalInclination;
    }

    public double getSlopeLeftBrimInclination() {
        return slopeLeftBrimInclination;
    }

    public void setSlopeLeftBrimInclination(double slopeLeftBrimInclination) {
        this.slopeLeftBrimInclination = slopeLeftBrimInclination;
    }

    public double getSlopeRightBrimInclination() {
        return slopeRightBrimInclination;
    }

    public void setSlopeRightBrimInclination(double slopeRightBrimInclination) {
        this.slopeRightBrimInclination = slopeRightBrimInclination;
    }

    public int getSlopeHasTactileFloor() {
        return slopeHasTactileFloor;
    }

    public void setSlopeHasTactileFloor(int slopeHasTactileFloor) {
        this.slopeHasTactileFloor = slopeHasTactileFloor;
    }

    public double getSlopeFreeSpace() {
        return slopeFreeSpace;
    }

    public void setSlopeFreeSpace(double slopeFreeSpace) {
        this.slopeFreeSpace = slopeFreeSpace;
    }

    public String getSlopeObs() {
        return slopeObs;
    }

    public void setSlopeObs(String slopeObs) {
        this.slopeObs = slopeObs;
    }
}
