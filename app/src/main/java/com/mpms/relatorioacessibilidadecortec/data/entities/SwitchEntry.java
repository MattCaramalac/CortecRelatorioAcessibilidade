package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID",
                onDelete = CASCADE, onUpdate = CASCADE)})
public class SwitchEntry {

    @PrimaryKey(autoGenerate = true)
    private int switchID;
    private int roomID;

    private String switchLocation;
    private String switchType;
    private Double switchHeight;
    private String switchObs;

    public SwitchEntry(int roomID, String switchLocation, String switchType, Double switchHeight, String switchObs) {
        this.roomID = roomID;
        this.switchLocation = switchLocation;
        this.switchType = switchType;
        this.switchHeight = switchHeight;
        this.switchObs = switchObs;
    }

    public int getSwitchID() {
        return switchID;
    }

    public void setSwitchID(int switchID) {
        this.switchID = switchID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getSwitchLocation() {
        return switchLocation;
    }

    public void setSwitchLocation(String switchLocation) {
        this.switchLocation = switchLocation;
    }

    public String getSwitchType() {
        return switchType;
    }

    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }

    public Double getSwitchHeight() {
        return switchHeight;
    }

    public void setSwitchHeight(Double switchHeight) {
        this.switchHeight = switchHeight;
    }

    public String getSwitchObs() {
        return switchObs;
    }

    public void setSwitchObs(String switchObs) {
        this.switchObs = switchObs;
    }
}
