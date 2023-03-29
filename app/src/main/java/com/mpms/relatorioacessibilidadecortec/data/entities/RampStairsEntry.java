package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = ExternalAccess.class, parentColumns = "externalAccessID", childColumns = "extAccessID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = SidewalkEntry.class, parentColumns = "sidewalkID", childColumns = "sidewalkID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = ParkingLotEntry.class, parentColumns = "parkingID", childColumns = "parkingID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE)})
public class RampStairsEntry {

    @PrimaryKey(autoGenerate = true)
    private int rampStairsID;
    private Integer extAccessID;
    private Integer sidewalkID;
    private Integer parkingID;
    private Integer roomID;
    private Integer circID;
    private int rampStairsIdentifier;
    private String rampStairsLocation;
    private String rampStairsPhoto;

    public RampStairsEntry(Integer extAccessID, Integer sidewalkID, Integer parkingID, Integer roomID, Integer circID, int rampStairsIdentifier,
                           String rampStairsLocation, String rampStairsPhoto) {
        this.extAccessID = extAccessID;
        this.sidewalkID = sidewalkID;
        this.parkingID = parkingID;
        this.roomID = roomID;
        this.rampStairsIdentifier = rampStairsIdentifier;
        this.rampStairsLocation = rampStairsLocation;
        this.rampStairsPhoto = rampStairsPhoto;
    }

    public String getRampStairsPhoto() {
        return rampStairsPhoto;
    }

    public void setRampStairsPhoto(String rampStairsPhoto) {
        this.rampStairsPhoto = rampStairsPhoto;
    }

    public int getRampStairsID() {
        return rampStairsID;
    }

    public void setRampStairsID(int rampStairsID) {
        this.rampStairsID = rampStairsID;
    }

    public Integer getExtAccessID() {
        return extAccessID;
    }

    public void setExtAccessID(Integer extAccessID) {
        this.extAccessID = extAccessID;
    }

    public Integer getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(Integer sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public Integer getParkingID() {
        return parkingID;
    }

    public void setParkingID(Integer parkingID) {
        this.parkingID = parkingID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public int getRampStairsIdentifier() {
        return rampStairsIdentifier;
    }

    public void setRampStairsIdentifier(int rampStairsIdentifier) {
        this.rampStairsIdentifier = rampStairsIdentifier;
    }

    public String getRampStairsLocation() {
        return rampStairsLocation;
    }

    public void setRampStairsLocation(String rampStairsLocation) {
        this.rampStairsLocation = rampStairsLocation;
    }

}
