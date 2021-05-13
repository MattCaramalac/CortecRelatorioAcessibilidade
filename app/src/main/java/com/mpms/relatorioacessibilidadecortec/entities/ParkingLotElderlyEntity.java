package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = SchoolEntry.class,
                parentColumns = "cadID",
                childColumns = "schoolEntryID",
                onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = ParkingLotEntity.class,
                parentColumns = "parkingLotID",
                childColumns = "parkingLotID",
                onDelete = CASCADE, onUpdate = CASCADE)})
public class ParkingLotElderlyEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer parkingElderlyID;
    @NonNull
    private Integer schoolEntryID;
    @NonNull
    private Integer parkingLotID;

    private Integer hasElderlyVacancy;
    private Integer totalElderlyVacancy;
    private Integer hasVisualElderlyVertSign;
    private String visualElderlyVertSignObs;
    private Integer hasVisualElderlyHorizSign;
    private Double visualElderlyHorizSignWidth;
    private Double visualElderlyHorizSignLength;
    private String visualElderlyHorizSignObs;
    private Integer hasPictogramElderly;
    private Double pictogramElderlyWidth;
    private Double pictogramElderlyLength;
    private String pictogramElderlyObs;

    public ParkingLotElderlyEntity(@NonNull Integer schoolEntryID, @NonNull Integer parkingLotID, Integer hasElderlyVacancy,
                                   Integer totalElderlyVacancy, Integer hasVisualElderlyVertSign, String visualElderlyVertSignObs,
                                   Integer hasVisualElderlyHorizSign, Double visualElderlyHorizSignWidth,
                                   Double visualElderlyHorizSignLength, String visualElderlyHorizSignObs, Integer hasPictogramElderly,
                                   Double pictogramElderlyWidth, Double pictogramElderlyLength, String pictogramElderlyObs) {
        this.schoolEntryID = schoolEntryID;
        this.parkingLotID = parkingLotID;
        this.hasElderlyVacancy = hasElderlyVacancy;
        this.totalElderlyVacancy = totalElderlyVacancy;
        this.hasVisualElderlyVertSign = hasVisualElderlyVertSign;
        this.visualElderlyVertSignObs = visualElderlyVertSignObs;
        this.hasVisualElderlyHorizSign = hasVisualElderlyHorizSign;
        this.visualElderlyHorizSignWidth = visualElderlyHorizSignWidth;
        this.visualElderlyHorizSignLength = visualElderlyHorizSignLength;
        this.visualElderlyHorizSignObs = visualElderlyHorizSignObs;
        this.hasPictogramElderly = hasPictogramElderly;
        this.pictogramElderlyWidth = pictogramElderlyWidth;
        this.pictogramElderlyLength = pictogramElderlyLength;
        this.pictogramElderlyObs = pictogramElderlyObs;
    }

    public Integer getParkingElderlyID() {
        return parkingElderlyID;
    }

    public void setParkingElderlyID(Integer parkingElderlyID) {
        this.parkingElderlyID = parkingElderlyID;
    }

    @NonNull
    public Integer getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(@NonNull Integer schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    @NonNull
    public Integer getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(@NonNull Integer parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public Integer getHasElderlyVacancy() {
        return hasElderlyVacancy;
    }

    public void setHasElderlyVacancy(Integer hasElderlyVacancy) {
        this.hasElderlyVacancy = hasElderlyVacancy;
    }

    public Integer getTotalElderlyVacancy() {
        return totalElderlyVacancy;
    }

    public void setTotalElderlyVacancy(Integer totalElderlyVacancy) {
        this.totalElderlyVacancy = totalElderlyVacancy;
    }

    public Integer getHasVisualElderlyVertSign() {
        return hasVisualElderlyVertSign;
    }

    public void setHasVisualElderlyVertSign(Integer hasVisualElderlyVertSign) {
        this.hasVisualElderlyVertSign = hasVisualElderlyVertSign;
    }

    public String getVisualElderlyVertSignObs() {
        return visualElderlyVertSignObs;
    }

    public void setVisualElderlyVertSignObs(String visualElderlyVertSignObs) {
        this.visualElderlyVertSignObs = visualElderlyVertSignObs;
    }

    public Integer getHasVisualElderlyHorizSign() {
        return hasVisualElderlyHorizSign;
    }

    public void setHasVisualElderlyHorizSign(Integer hasVisualElderlyHorizSign) {
        this.hasVisualElderlyHorizSign = hasVisualElderlyHorizSign;
    }

    public Double getVisualElderlyHorizSignWidth() {
        return visualElderlyHorizSignWidth;
    }

    public void setVisualElderlyHorizSignWidth(Double visualElderlyHorizSignWidth) {
        this.visualElderlyHorizSignWidth = visualElderlyHorizSignWidth;
    }

    public Double getVisualElderlyHorizSignLength() {
        return visualElderlyHorizSignLength;
    }

    public void setVisualElderlyHorizSignLength(Double visualElderlyHorizSignLength) {
        this.visualElderlyHorizSignLength = visualElderlyHorizSignLength;
    }

    public String getVisualElderlyHorizSignObs() {
        return visualElderlyHorizSignObs;
    }

    public void setVisualElderlyHorizSignObs(String visualElderlyHorizSignObs) {
        this.visualElderlyHorizSignObs = visualElderlyHorizSignObs;
    }

    public Integer getHasPictogramElderly() {
        return hasPictogramElderly;
    }

    public void setHasPictogramElderly(Integer hasPictogramElderly) {
        this.hasPictogramElderly = hasPictogramElderly;
    }

    public Double getPictogramElderlyWidth() {
        return pictogramElderlyWidth;
    }

    public void setPictogramElderlyWidth(Double pictogramElderlyWidth) {
        this.pictogramElderlyWidth = pictogramElderlyWidth;
    }

    public Double getPictogramElderlyLength() {
        return pictogramElderlyLength;
    }

    public void setPictogramElderlyLength(Double pictogramElderlyLength) {
        this.pictogramElderlyLength = pictogramElderlyLength;
    }

    public String getPictogramElderlyObs() {
        return pictogramElderlyObs;
    }

    public void setPictogramElderlyObs(String pictogramElderlyObs) {
        this.pictogramElderlyObs = pictogramElderlyObs;
    }
}
