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
public class FreeSpaceEntry {

    @PrimaryKey(autoGenerate = true)
    private int freeSpaceID;
    private int schoolID;
    private int roomID;
    private String freeSpaceLocation;
    private Double freeSpaceWidth;
    private  String freeSpaceObs;

    public FreeSpaceEntry(int schoolID, int roomID, String freeSpaceLocation, Double freeSpaceWidth, String freeSpaceObs) {
        this.schoolID = schoolID;
        this.roomID = roomID;
        this.freeSpaceLocation = freeSpaceLocation;
        this.freeSpaceWidth = freeSpaceWidth;
        this.freeSpaceObs = freeSpaceObs;
    }

    public int getFreeSpaceID() {
        return freeSpaceID;
    }

    public void setFreeSpaceID(int freeSpaceID) {
        this.freeSpaceID = freeSpaceID;
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

    public String getFreeSpaceLocation() {
        return freeSpaceLocation;
    }

    public void setFreeSpaceLocation(String freeSpaceLocation) {
        this.freeSpaceLocation = freeSpaceLocation;
    }

    public Double getFreeSpaceWidth() {
        return freeSpaceWidth;
    }

    public void setFreeSpaceWidth(Double freeSpaceWidth) {
        this.freeSpaceWidth = freeSpaceWidth;
    }

    public String getFreeSpaceObs() {
        return freeSpaceObs;
    }

    public void setFreeSpaceObs(String freeSpaceObs) {
        this.freeSpaceObs = freeSpaceObs;
    }
}
