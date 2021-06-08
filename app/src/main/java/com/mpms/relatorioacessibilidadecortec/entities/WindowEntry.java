package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {
        @ForeignKey(entity = SchoolEntry.class,
                parentColumns = "cadID",
                childColumns = "schoolID",
                onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = RoomEntry.class,
                parentColumns = "roomID",
                childColumns = "roomID",
                onDelete = CASCADE, onUpdate = CASCADE)})
public class WindowEntry {

    @PrimaryKey(autoGenerate = true)
    private int windowID;
    private int schoolID;
    private int roomID;
    private String windowLocation;
    private Double windowSillHeight;
    private String windowObs;

    public WindowEntry(int schoolID, int roomID, String windowLocation, Double windowSillHeight, String windowObs) {
        this.schoolID = schoolID;
        this.roomID = roomID;
        this.windowLocation = windowLocation;
        this.windowSillHeight = windowSillHeight;
        this.windowObs = windowObs;
    }

    public int getWindowID() {
        return windowID;
    }

    public void setWindowID(int windowID) {
        this.windowID = windowID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
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

    public Double getWindowSillHeight() {
        return windowSillHeight;
    }

    public void setWindowSillHeight(Double windowSillHeight) {
        this.windowSillHeight = windowSillHeight;
    }

    public String getWindowObs() {
        return windowObs;
    }

    public void setWindowObs(String windowObs) {
        this.windowObs = windowObs;
    }
}
