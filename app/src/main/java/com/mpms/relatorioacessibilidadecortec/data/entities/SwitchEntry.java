package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE)})
public class SwitchEntry {

    @PrimaryKey(autoGenerate = true)
    private int switchID;
    private Integer roomID;
    private Integer circID;

    private String switchLocation;
    private String switchType;
    private Double switchHeight;
    private String switchObs;
    private String switchPhoto;

    public SwitchEntry(Integer roomID, Integer circID, String switchLocation, String switchType, Double switchHeight, String switchObs, String switchPhoto) {
        this.roomID = roomID;
        this.circID = circID;
        this.switchLocation = switchLocation;
        this.switchType = switchType;
        this.switchHeight = switchHeight;
        this.switchObs = switchObs;
        this.switchPhoto = switchPhoto;
    }

    public String getSwitchPhoto() {
        return switchPhoto;
    }

    public void setSwitchPhoto(String switchPhoto) {
        this.switchPhoto = switchPhoto;
    }

    public int getSwitchID() {
        return switchID;
    }

    public void setSwitchID(int switchID) {
        this.switchID = switchID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public Integer getCircID() {
        return circID;
    }

    public void setCircID(Integer circID) {
        this.circID = circID;
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
