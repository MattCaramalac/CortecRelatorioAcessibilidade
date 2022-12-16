package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys =
        @ForeignKey(entity = ParkingLotEntry.class, parentColumns = "parkingID", childColumns = "parkID",
                onDelete = CASCADE, onUpdate = CASCADE))
public class ParkingLotElderlyEntry {

    @PrimaryKey(autoGenerate = true)
    private int parkElderID;
    private int parkID;

    private String elderVacLocation;
    private int hasElderlyVertSign;
    private Double elderlyVertSignLength;
    private Double elderlyVertSingWidth;
    private String elderlyVertSignObs;
    private double elderlyVacancyLength;
    private double elderlyVacancyWidth;
    private double elderlyVacancyLimiterWidth;
    private String elderlyVacancyObs;
    private int hasElderlyFloorIndicator;
    private Double floorIndicatorHeight;
    private String floorIndicatorObs;

    public int getParkElderID() {
        return parkElderID;
    }

    public void setParkElderID(int parkElderID) {
        this.parkElderID = parkElderID;
    }

    public int getParkID() {
        return parkID;
    }

    public void setParkID(int parkID) {
        this.parkID = parkID;
    }

    public int getHasElderlyVertSign() {
        return hasElderlyVertSign;
    }

    public void setHasElderlyVertSign(int hasElderlyVertSign) {
        this.hasElderlyVertSign = hasElderlyVertSign;
    }

    public Double getElderlyVertSignLength() {
        return elderlyVertSignLength;
    }

    public void setElderlyVertSignLength(Double elderlyVertSignLength) {
        this.elderlyVertSignLength = elderlyVertSignLength;
    }

    public Double getElderlyVertSingWidth() {
        return elderlyVertSingWidth;
    }

    public void setElderlyVertSingWidth(Double elderlyVertSingWidth) {
        this.elderlyVertSingWidth = elderlyVertSingWidth;
    }

    public String getElderlyVertSignObs() {
        return elderlyVertSignObs;
    }

    public void setElderlyVertSignObs(String elderlyVertSignObs) {
        this.elderlyVertSignObs = elderlyVertSignObs;
    }

    public double getElderlyVacancyLength() {
        return elderlyVacancyLength;
    }

    public void setElderlyVacancyLength(double elderlyVacancyLength) {
        this.elderlyVacancyLength = elderlyVacancyLength;
    }

    public double getElderlyVacancyWidth() {
        return elderlyVacancyWidth;
    }

    public void setElderlyVacancyWidth(double elderlyVacancyWidth) {
        this.elderlyVacancyWidth = elderlyVacancyWidth;
    }

    public double getElderlyVacancyLimiterWidth() {
        return elderlyVacancyLimiterWidth;
    }

    public void setElderlyVacancyLimiterWidth(double elderlyVacancyLimiterWidth) {
        this.elderlyVacancyLimiterWidth = elderlyVacancyLimiterWidth;
    }

    public String getElderlyVacancyObs() {
        return elderlyVacancyObs;
    }

    public void setElderlyVacancyObs(String elderlyVacancyObs) {
        this.elderlyVacancyObs = elderlyVacancyObs;
    }

    public int getHasElderlyFloorIndicator() {
        return hasElderlyFloorIndicator;
    }

    public void setHasElderlyFloorIndicator(int hasElderlyFloorIndicator) {
        this.hasElderlyFloorIndicator = hasElderlyFloorIndicator;
    }

    public String getFloorIndicatorObs() {
        return floorIndicatorObs;
    }

    public void setFloorIndicatorObs(String floorIndicatorObs) {
        this.floorIndicatorObs = floorIndicatorObs;
    }

    public String getElderVacLocation() {
        return elderVacLocation;
    }

    public void setElderVacLocation(String elderVacLocation) {
        this.elderVacLocation = elderVacLocation;
    }

    public Double getFloorIndicatorHeight() {
        return floorIndicatorHeight;
    }

    public void setFloorIndicatorHeight(Double floorIndicatorHeight) {
        this.floorIndicatorHeight = floorIndicatorHeight;
    }

    public ParkingLotElderlyEntry(int parkID, String elderVacLocation, int hasElderlyVertSign, Double elderlyVertSignLength, Double elderlyVertSingWidth,
                                  String elderlyVertSignObs, double elderlyVacancyLength, double elderlyVacancyWidth, double elderlyVacancyLimiterWidth,
                                  String elderlyVacancyObs, int hasElderlyFloorIndicator, Double floorIndicatorHeight, String floorIndicatorObs) {
        this.parkID = parkID;
        this.elderVacLocation = elderVacLocation;
        this.hasElderlyVertSign = hasElderlyVertSign;
        this.elderlyVertSignLength = elderlyVertSignLength;
        this.elderlyVertSingWidth = elderlyVertSingWidth;
        this.elderlyVertSignObs = elderlyVertSignObs;
        this.elderlyVacancyLength = elderlyVacancyLength;
        this.elderlyVacancyWidth = elderlyVacancyWidth;
        this.elderlyVacancyLimiterWidth = elderlyVacancyLimiterWidth;
        this.elderlyVacancyObs = elderlyVacancyObs;
        this.hasElderlyFloorIndicator = hasElderlyFloorIndicator;
        this.floorIndicatorHeight = floorIndicatorHeight;
        this.floorIndicatorObs = floorIndicatorObs;
    }
}
