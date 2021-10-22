package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class,
        parentColumns = "cadID",
        childColumns = "schoolID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class ParkingLotEntry {

    @PrimaryKey(autoGenerate = true)
    private int parkingLotID;
    private int schoolID;
    private int typeParkingLot;
    private String parkingLotFloorType;
    private int hasPCDVacancy;
    private int hasElderVacancy;

    public int getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(int parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolEntryID) {
        this.schoolID = schoolEntryID;
    }

    public int getTypeParkingLot() {
        return typeParkingLot;
    }

    public void setTypeParkingLot(int typeParkingLot) {
        this.typeParkingLot = typeParkingLot;
    }

    public String getParkingLotFloorType() {
        return parkingLotFloorType;
    }

    public void setParkingLotFloorType(String parkingLotFloorType) {
        this.parkingLotFloorType = parkingLotFloorType;
    }

    public int getHasPCDVacancy() {
        return hasPCDVacancy;
    }

    public void setHasPCDVacancy(int hasPCDVacancy) {
        this.hasPCDVacancy = hasPCDVacancy;
    }

    public int getHasElderVacancy() {
        return hasElderVacancy;
    }

    public void setHasElderVacancy(int hasElderVacancy) {
        this.hasElderVacancy = hasElderVacancy;
    }

    public ParkingLotEntry(int schoolID, int typeParkingLot, String parkingLotFloorType, int hasPCDVacancy, int hasElderVacancy) {
        this.schoolID = schoolID;
        this.typeParkingLot = typeParkingLot;
        this.parkingLotFloorType = parkingLotFloorType;
        this.hasPCDVacancy = hasPCDVacancy;
        this.hasElderVacancy = hasElderVacancy;
    }
}