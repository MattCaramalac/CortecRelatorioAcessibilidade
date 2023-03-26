package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = RampStairsFlightEntry.class, parentColumns = "flightID", childColumns = "flightID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class RampStairsRailingEntry {

    @PrimaryKey(autoGenerate = true)
    private int railingID;
    private int flightID;
    private int railingSide;
    private int hasRailing;
    private Double railingHeight;
    private String railingObs;
    private Integer hasBeacon;
    private Double beaconHeight;
    private String beaconObs;
    private String beaconPhoto;

    public RampStairsRailingEntry(int flightID, int railingSide, int hasRailing, Double railingHeight, String railingObs, Integer hasBeacon,
                                  Double beaconHeight, String beaconObs, String beaconPhoto) {
        this.flightID = flightID;
        this.railingSide = railingSide;
        this.hasRailing = hasRailing;
        this.railingHeight = railingHeight;
        this.railingObs = railingObs;
        this.hasBeacon = hasBeacon;
        this.beaconHeight = beaconHeight;
        this.beaconObs = beaconObs;
        this.beaconPhoto = beaconPhoto;
    }

    public String getBeaconPhoto() {
        return beaconPhoto;
    }

    public void setBeaconPhoto(String beaconPhoto) {
        this.beaconPhoto = beaconPhoto;
    }

    public int getRailingID() {
        return railingID;
    }

    public void setRailingID(int railingID) {
        this.railingID = railingID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getRailingSide() {
        return railingSide;
    }

    public void setRailingSide(int railingSide) {
        this.railingSide = railingSide;
    }

    public int getHasRailing() {
        return hasRailing;
    }

    public void setHasRailing(int hasRailing) {
        this.hasRailing = hasRailing;
    }

    public Double getRailingHeight() {
        return railingHeight;
    }

    public void setRailingHeight(Double railingHeight) {
        this.railingHeight = railingHeight;
    }

    public String getRailingObs() {
        return railingObs;
    }

    public void setRailingObs(String railingObs) {
        this.railingObs = railingObs;
    }

    public Integer getHasBeacon() {
        return hasBeacon;
    }

    public void setHasBeacon(Integer hasBeacon) {
        this.hasBeacon = hasBeacon;
    }

    public Double getBeaconHeight() {
        return beaconHeight;
    }

    public void setBeaconHeight(Double beaconHeight) {
        this.beaconHeight = beaconHeight;
    }

    public String getBeaconObs() {
        return beaconObs;
    }

    public void setBeaconObs(String beaconObs) {
        this.beaconObs = beaconObs;
    }
}
