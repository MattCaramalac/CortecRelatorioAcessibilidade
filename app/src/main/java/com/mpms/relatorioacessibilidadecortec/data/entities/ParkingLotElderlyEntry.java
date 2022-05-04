package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys =
        @ForeignKey(entity = ParkingLotEntry.class, parentColumns = "parkingID", childColumns = "parkingLotID",
                onDelete = CASCADE, onUpdate = CASCADE))
public class ParkingLotElderlyEntry {

    @PrimaryKey(autoGenerate = true)
    private int parkingElderlyID;
    private int parkingLotID;

    private int hasElderlyVertSign;
    private Double elderlyVertSignLength;
    private Double elderlyVertSingWidth;
    private String elderlyVertSignObs;
    private double elderlyVacancyLength;
    private double elderlyVacancyWidth;
    private double elderlyVacancyLimiterWidth;
    private String elderlyVacancyObs;
    private int hasElderlyFloorIndicator;
    private Double floorIndicatorLength;
    private Double floorIndicatorWidth;
    private String floorIndicatorObs;

    public int getParkingElderlyID() {
        return parkingElderlyID;
    }

    public void setParkingElderlyID(int parkingElderlyID) {
        this.parkingElderlyID = parkingElderlyID;
    }

    public int getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(int parkingLotID) {
        this.parkingLotID = parkingLotID;
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

    public Double getFloorIndicatorLength() {
        return floorIndicatorLength;
    }

    public void setFloorIndicatorLength(Double floorIndicatorLength) {
        this.floorIndicatorLength = floorIndicatorLength;
    }

    public Double getFloorIndicatorWidth() {
        return floorIndicatorWidth;
    }

    public void setFloorIndicatorWidth(Double floorIndicatorWidth) {
        this.floorIndicatorWidth = floorIndicatorWidth;
    }

    public String getFloorIndicatorObs() {
        return floorIndicatorObs;
    }

    public void setFloorIndicatorObs(String floorIndicatorObs) {
        this.floorIndicatorObs = floorIndicatorObs;
    }

    public ParkingLotElderlyEntry(int parkingLotID, int hasElderlyVertSign, Double elderlyVertSignLength,
                                  Double elderlyVertSingWidth, String elderlyVertSignObs, double elderlyVacancyLength,
                                  double elderlyVacancyWidth, double elderlyVacancyLimiterWidth, String elderlyVacancyObs,
                                  int hasElderlyFloorIndicator, Double floorIndicatorLength, Double floorIndicatorWidth,
                                  String floorIndicatorObs) {
        this.parkingLotID = parkingLotID;
        this.hasElderlyVertSign = hasElderlyVertSign;
        this.elderlyVertSignLength = elderlyVertSignLength;
        this.elderlyVertSingWidth = elderlyVertSingWidth;
        this.elderlyVertSignObs = elderlyVertSignObs;
        this.elderlyVacancyLength = elderlyVacancyLength;
        this.elderlyVacancyWidth = elderlyVacancyWidth;
        this.elderlyVacancyLimiterWidth = elderlyVacancyLimiterWidth;
        this.elderlyVacancyObs = elderlyVacancyObs;
        this.hasElderlyFloorIndicator = hasElderlyFloorIndicator;
        this.floorIndicatorLength = floorIndicatorLength;
        this.floorIndicatorWidth = floorIndicatorWidth;
        this.floorIndicatorObs = floorIndicatorObs;
    }
}
