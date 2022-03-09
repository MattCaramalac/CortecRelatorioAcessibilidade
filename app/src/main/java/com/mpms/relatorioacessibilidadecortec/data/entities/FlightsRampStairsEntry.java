package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = RampStairsEntry.class, parentColumns = "rampStairsID",
        childColumns = "rampStairsID", onUpdate = CASCADE, onDelete = CASCADE))
public class FlightsRampStairsEntry {

    @PrimaryKey(autoGenerate = true)
    private int flightID;
    private int rampStairsID;
    private Double flightWidth;
    private Integer signPavement;
    private String signPavementObs;
    private Integer tactileFloor;
    private String tactileFloorObs;
    private Integer borderSign;
    private Double borderSignWidth;
    private Integer borderSignIdentifiable;
    private String borderSignObs;

    public FlightsRampStairsEntry(int rampStairsID, Double flightWidth, Integer signPavement, String signPavementObs, Integer tactileFloor,
                                  String tactileFloorObs, Integer borderSign, Double borderSignWidth, Integer borderSignIdentifiable,
                                  String borderSignObs) {
        this.rampStairsID = rampStairsID;
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

    public int getRampStairsID() {
        return rampStairsID;
    }

    public void setRampStairsID(int rampStairsID) {
        this.rampStairsID = rampStairsID;
    }

    public Double getFlightWidth() {
        return flightWidth;
    }

    public void setFlightWidth(Double flightWidth) {
        this.flightWidth = flightWidth;
    }

    public Integer getSignPavement() {
        return signPavement;
    }

    public void setSignPavement(Integer signPavement) {
        this.signPavement = signPavement;
    }

    public String getSignPavementObs() {
        return signPavementObs;
    }

    public void setSignPavementObs(String signPavementObs) {
        this.signPavementObs = signPavementObs;
    }

    public Integer getTactileFloor() {
        return tactileFloor;
    }

    public void setTactileFloor(Integer tactileFloor) {
        this.tactileFloor = tactileFloor;
    }

    public String getTactileFloorObs() {
        return tactileFloorObs;
    }

    public void setTactileFloorObs(String tactileFloorObs) {
        this.tactileFloorObs = tactileFloorObs;
    }

    public Integer getBorderSign() {
        return borderSign;
    }

    public void setBorderSign(Integer borderSign) {
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
