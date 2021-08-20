package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = StairsEntry.class, parentColumns = "stairsID",
                                    childColumns = "stairsID", onUpdate = CASCADE, onDelete = CASCADE))
public class FlightStairsEntry {

    @PrimaryKey(autoGenerate = true)
    private int flightID;
    private int stairsID;
    private double flightWidth;
    private int signPavement;
    private String signPavementObs;
    private int tactileFloor;
    private String tactileFloorObs;
    private int borderSign;
    private Double borderSignWidth;
    private Integer borderSignIdentifiable;
    private String borderSignObs;

    public FlightStairsEntry(int stairsID, double flightWidth, int signPavement, String signPavementObs, int tactileFloor, String tactileFloorObs,
                             int borderSign, Double borderSignWidth, Integer borderSignIdentifiable, String borderSignObs) {
        this.stairsID = stairsID;
        this.flightWidth = flightWidth;
        this.signPavement = signPavement;
        this.signPavementObs = signPavementObs;
        this.tactileFloor = tactileFloor;
        this.tactileFloorObs = tactileFloorObs;
        this.borderSign = borderSign;
        this.borderSignWidth = borderSignWidth;
        this.borderSignIdentifiable = borderSignIdentifiable;
        this.borderSignObs = borderSignObs;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getStairsID() {
        return stairsID;
    }

    public void setStairsID(int stairsID) {
        this.stairsID = stairsID;
    }

    public double getFlightWidth() {
        return flightWidth;
    }

    public void setFlightWidth(double flightWidth) {
        this.flightWidth = flightWidth;
    }

    public int getSignPavement() {
        return signPavement;
    }

    public void setSignPavement(int signPavement) {
        this.signPavement = signPavement;
    }

    public String getSignPavementObs() {
        return signPavementObs;
    }

    public void setSignPavementObs(String signPavementObs) {
        this.signPavementObs = signPavementObs;
    }

    public int getTactileFloor() {
        return tactileFloor;
    }

    public void setTactileFloor(int tactileFloor) {
        this.tactileFloor = tactileFloor;
    }

    public String getTactileFloorObs() {
        return tactileFloorObs;
    }

    public void setTactileFloorObs(String tactileFloorObs) {
        this.tactileFloorObs = tactileFloorObs;
    }

    public int getBorderSign() {
        return borderSign;
    }

    public void setBorderSign(int borderSign) {
        this.borderSign = borderSign;
    }

    public Double getBorderSignWidth() {
        return borderSignWidth;
    }

    public void setBorderSignWidth(Double borderSignWidth) {
        this.borderSignWidth = borderSignWidth;
    }

    public Integer getBorderSignIdentifiable() {
        return borderSignIdentifiable;
    }

    public void setBorderSignIdentifiable(Integer borderSignIdentifiable) {
        this.borderSignIdentifiable = borderSignIdentifiable;
    }

    public String getBorderSignObs() {
        return borderSignObs;
    }

    public void setBorderSignObs(String borderSignObs) {
        this.borderSignObs = borderSignObs;
    }
}
