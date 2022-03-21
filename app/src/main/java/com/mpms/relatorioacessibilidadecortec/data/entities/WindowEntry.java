package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE)})
public class WindowEntry {

    @PrimaryKey(autoGenerate = true)
    private int windowID;
    private int roomID;
    private String windowLocation;
    private Double windowCommandHeight;
    private String windowObs;

    public WindowEntry(int roomID, String windowLocation, Double windowCommandHeight, String windowObs) {
        this.roomID = roomID;
        this.windowLocation = windowLocation;
        this.windowCommandHeight = windowCommandHeight;
        this.windowObs = windowObs;
    }

    public int getWindowID() {
        return windowID;
    }

    public void setWindowID(int windowID) {
        this.windowID = windowID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getWindowLocation() {
        return windowLocation;
    }

    public void setWindowLocation(String windowLocation) {
        this.windowLocation = windowLocation;
    }

    public Double getWindowCommandHeight() {
        return windowCommandHeight;
    }

    public void setWindowCommandHeight(Double windowCommandHeight) {
        this.windowCommandHeight = windowCommandHeight;
    }

    public String getWindowObs() {
        return windowObs;
    }

    public void setWindowObs(String windowObs) {
        this.windowObs = windowObs;
    }
}
