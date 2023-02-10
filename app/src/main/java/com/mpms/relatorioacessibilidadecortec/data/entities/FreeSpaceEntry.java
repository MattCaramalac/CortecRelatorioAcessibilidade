package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = RestroomEntry.class, parentColumns = "restroomID", childColumns = "restID", onDelete = CASCADE, onUpdate = CASCADE)})
public class FreeSpaceEntry {

    @PrimaryKey(autoGenerate = true)
    private int frSpaceID;
    private Integer roomID;
    private Integer restID;
    private String frSpaceLocation;
    private Double obstacleWidth;
    private Double frSpaceWidth;
    private String frSpaceObs;

    public FreeSpaceEntry(Integer roomID, Integer restID, String frSpaceLocation, Double obstacleWidth, Double frSpaceWidth, String frSpaceObs) {
        this.roomID = roomID;
        this.restID = restID;
        this.frSpaceLocation = frSpaceLocation;
        this.obstacleWidth = obstacleWidth;
        this.frSpaceWidth = frSpaceWidth;
        this.frSpaceObs = frSpaceObs;
    }

    public int getFrSpaceID() {
        return frSpaceID;
    }

    public void setFrSpaceID(int fSpaceID) {
        this.frSpaceID = fSpaceID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public Integer getRestID() {
        return restID;
    }

    public void setRestID(Integer restID) {
        this.restID = restID;
    }

    public String getFrSpaceLocation() {
        return frSpaceLocation;
    }

    public void setFrSpaceLocation(String fSpaceLocation) {
        this.frSpaceLocation = fSpaceLocation;
    }

    public Double getObstacleWidth() {
        return obstacleWidth;
    }

    public void setObstacleWidth(Double obstacleWidth) {
        this.obstacleWidth = obstacleWidth;
    }

    public Double getFrSpaceWidth() {
        return frSpaceWidth;
    }

    public void setFrSpaceWidth(Double fSpaceWidth) {
        this.frSpaceWidth = fSpaceWidth;
    }

    public String getFrSpaceObs() {
        return frSpaceObs;
    }

    public void setFrSpaceObs(String fSpaceObs) {
        this.frSpaceObs = fSpaceObs;
    }
}
