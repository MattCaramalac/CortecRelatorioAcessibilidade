package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {
        @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
                onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = RoomEntry.class,
                parentColumns = "roomID",
                childColumns = "roomID",
                onDelete = CASCADE, onUpdate = CASCADE)})
public class WindowEntry {

    @PrimaryKey(autoGenerate = true)
    private int windowID;
    private int blockID;
    private int roomID;
    private String windowLocation;
    private Double windowSillHeight;
    private String windowObs;

    public WindowEntry(int blockID, int roomID, String windowLocation, Double windowSillHeight, String windowObs) {
        this.blockID = blockID;
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

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
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
