package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE), @ForeignKey(entity = SidewalkEntry.class, parentColumns = "sidewalkID",
        childColumns = "sidewalkID", onDelete = CASCADE, onUpdate = CASCADE)})
public class ParkingLotEntry {

    @PrimaryKey(autoGenerate = true)
    private int parkingID;
    private int blockID;
    private Integer sidewalkID;
    private int typeParkingLot;
    private Integer parkAccessFloor;
    private String parkFloorObs;
    private Integer hasPCDVacancy;
    private Integer hasElderVacancy;
    private Integer parkAccessRoute;
    private String parkAccessRouteObs;
    private Integer parkingHasStairs;
    private Integer parkingHasRamps;
    private String extParkLocation;

    public ParkingLotEntry(int blockID, Integer sidewalkID, int typeParkingLot, Integer parkAccessFloor, String parkFloorObs, Integer hasPCDVacancy,
                           Integer hasElderVacancy, Integer parkAccessRoute, String parkAccessRouteObs, Integer parkingHasStairs, Integer parkingHasRamps,
                           String extParkLocation) {
        this.blockID = blockID;
        this.sidewalkID = sidewalkID;
        this.typeParkingLot = typeParkingLot;
        this.parkAccessFloor = parkAccessFloor;
        this.parkFloorObs = parkFloorObs;
        this.hasPCDVacancy = hasPCDVacancy;
        this.hasElderVacancy = hasElderVacancy;
        this.parkAccessRoute = parkAccessRoute;
        this.parkAccessRouteObs = parkAccessRouteObs;
        this.parkingHasStairs = parkingHasStairs;
        this.parkingHasRamps = parkingHasRamps;
        this.extParkLocation = extParkLocation;
    }

    public int getParkingID() {
        return parkingID;
    }

    public void setParkingID(int parkingID) {
        this.parkingID = parkingID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public Integer getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(Integer sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public int getTypeParkingLot() {
        return typeParkingLot;
    }

    public void setTypeParkingLot(int typeParkingLot) {
        this.typeParkingLot = typeParkingLot;
    }

    public Integer getParkAccessFloor() {
        return parkAccessFloor;
    }

    public void setParkAccessFloor(Integer parkAccessFloor) {
        this.parkAccessFloor = parkAccessFloor;
    }

    public String getParkFloorObs() {
        return parkFloorObs;
    }

    public void setParkFloorObs(String parkFloorObs) {
        this.parkFloorObs = parkFloorObs;
    }

    public Integer getHasPCDVacancy() {
        return hasPCDVacancy;
    }

    public void setHasPCDVacancy(Integer hasPCDVacancy) {
        this.hasPCDVacancy = hasPCDVacancy;
    }

    public Integer getHasElderVacancy() {
        return hasElderVacancy;
    }

    public void setHasElderVacancy(Integer hasElderVacancy) {
        this.hasElderVacancy = hasElderVacancy;
    }

    public Integer getParkAccessRoute() {
        return parkAccessRoute;
    }

    public void setParkAccessRoute(Integer parkAccessRoute) {
        this.parkAccessRoute = parkAccessRoute;
    }

    public String getParkAccessRouteObs() {
        return parkAccessRouteObs;
    }

    public void setParkAccessRouteObs(String parkAccessRouteObs) {
        this.parkAccessRouteObs = parkAccessRouteObs;
    }

    public Integer getParkingHasStairs() {
        return parkingHasStairs;
    }

    public void setParkingHasStairs(Integer parkingHasStairs) {
        this.parkingHasStairs = parkingHasStairs;
    }

    public Integer getParkingHasRamps() {
        return parkingHasRamps;
    }

    public void setParkingHasRamps(Integer parkingHasRamps) {
        this.parkingHasRamps = parkingHasRamps;
    }

    public String getExtParkLocation() {
        return extParkLocation;
    }

    public void setExtParkLocation(String extParkLocation) {
        this.extParkLocation = extParkLocation;
    }
}