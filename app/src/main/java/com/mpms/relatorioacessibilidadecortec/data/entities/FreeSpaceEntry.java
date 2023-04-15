package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = RestroomEntry.class, parentColumns = "restroomID", childColumns = "restID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE)})
public class FreeSpaceEntry {

    @PrimaryKey(autoGenerate = true)
    private int frSpaceID;
    private Integer roomID;
    private Integer restID;
    private Integer circID;
    private String frSpaceLocation;
    private Double obstacleWidth;
    private Double frSpaceWidth;
    private String frSpaceObs;
    private String frSpacePhoto;

    public FreeSpaceEntry(Integer roomID, Integer restID, Integer circID, String frSpaceLocation, Double obstacleWidth, Double frSpaceWidth, String frSpaceObs, String frSpacePhoto) {
        this.roomID = roomID;
        this.restID = restID;
        this.circID = circID;
        this.frSpaceLocation = frSpaceLocation;
        this.obstacleWidth = obstacleWidth;
        this.frSpaceWidth = frSpaceWidth;
        this.frSpaceObs = frSpaceObs;
        this.frSpacePhoto = frSpacePhoto;
    }

    public String getFrSpacePhoto() {
        return frSpacePhoto;
    }

    public void setFrSpacePhoto(String frSpacePhoto) {
        this.frSpacePhoto = frSpacePhoto;
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

    public Integer getCircID() {
        return circID;
    }

    public void setCircID(Integer circID) {
        this.circID = circID;
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
