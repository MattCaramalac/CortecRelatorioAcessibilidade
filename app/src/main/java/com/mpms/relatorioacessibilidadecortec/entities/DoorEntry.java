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
public class DoorEntry {

    @PrimaryKey(autoGenerate = true)
    private int doorID;
    private int schoolID;
    private int roomID;
    private String doorLocation;
    private Double doorWidth;
    private Integer doorSillType;
    private Double sillInclinationHeight;
    private Double sillStepHeight;
    private Double sillSlopeHeight;
    private Double sillSlopeWidth;
    private String doorObs;

    public DoorEntry(int schoolID, int roomID, String doorLocation, Double doorWidth, Integer doorSillType, Double sillInclinationHeight,
                     Double sillStepHeight, Double sillSlopeHeight, Double sillSlopeWidth, String doorObs) {
        this.schoolID = schoolID;
        this.roomID = roomID;
        this.doorLocation = doorLocation;
        this.doorWidth = doorWidth;
        this.doorSillType = doorSillType;
        this.sillInclinationHeight = sillInclinationHeight;
        this.sillStepHeight = sillStepHeight;
        this.sillSlopeHeight = sillSlopeHeight;
        this.sillSlopeWidth = sillSlopeWidth;
        this.doorObs = doorObs;
    }

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
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

    public String getDoorLocation() {
        return doorLocation;
    }

    public void setDoorLocation(String doorLocation) {
        this.doorLocation = doorLocation;
    }

    public Double getDoorWidth() {
        return doorWidth;
    }

    public void setDoorWidth(Double doorWidth) {
        this.doorWidth = doorWidth;
    }

    public Integer getDoorSillType() {
        return doorSillType;
    }

    public void setDoorSillType(Integer doorSillType) {
        this.doorSillType = doorSillType;
    }

    public Double getSillInclinationHeight() {
        return sillInclinationHeight;
    }

    public void setSillInclinationHeight(Double sillInclinationHeight) {
        this.sillInclinationHeight = sillInclinationHeight;
    }

    public Double getSillStepHeight() {
        return sillStepHeight;
    }

    public void setSillStepHeight(Double sillStepHeight) {
        this.sillStepHeight = sillStepHeight;
    }

    public Double getSillSlopeHeight() {
        return sillSlopeHeight;
    }

    public void setSillSlopeHeight(Double sillSlopeHeight) {
        this.sillSlopeHeight = sillSlopeHeight;
    }

    public Double getSillSlopeWidth() {
        return sillSlopeWidth;
    }

    public void setSillSlopeWidth(Double sillSlopeWidth) {
        this.sillSlopeWidth = sillSlopeWidth;
    }

    public String getDoorObs() {
        return doorObs;
    }

    public void setDoorObs(String doorObs) {
        this.doorObs = doorObs;
    }
}