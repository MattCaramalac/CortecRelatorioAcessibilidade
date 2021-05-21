package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class,
        parentColumns = "cadID",
        childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class ParkingLotEntry {

    @PrimaryKey(autoGenerate = true)
    private Integer parkingLotID;
    private Integer schoolEntryID;
    private Integer typeParkingLot;
    private Integer totalParkingVacancy;
    private String parkingLotFloorType;

    public Integer getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(Integer parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public Integer getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(Integer schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    public Integer getTypeParkingLot() {
        return typeParkingLot;
    }

    public void setTypeParkingLot(Integer typeParkingLot) {
        this.typeParkingLot = typeParkingLot;
    }

    public Integer getTotalParkingVacancy() {
        return totalParkingVacancy;
    }

    public void setTotalParkingVacancy(Integer totalParkingVacancy) {
        this.totalParkingVacancy = totalParkingVacancy;
    }

    public String getParkingLotFloorType() {
        return parkingLotFloorType;
    }

    public void setParkingLotFloorType(String parkingLotFloorType) {
        this.parkingLotFloorType = parkingLotFloorType;
    }

    public ParkingLotEntry(Integer schoolEntryID, Integer typeParkingLot, Integer totalParkingVacancy, String parkingLotFloorType) {
        this.schoolEntryID = schoolEntryID;
        this.typeParkingLot = typeParkingLot;
        this.totalParkingVacancy = totalParkingVacancy;
        this.parkingLotFloorType = parkingLotFloorType;
    }
}