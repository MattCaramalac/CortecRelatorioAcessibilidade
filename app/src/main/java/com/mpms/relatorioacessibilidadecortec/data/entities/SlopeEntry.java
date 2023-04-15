package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID",
        onDelete = CASCADE, onUpdate = CASCADE), @ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID",
        onDelete = CASCADE, onUpdate = CASCADE)})
public class SlopeEntry {

    @PrimaryKey(autoGenerate = true)
    int slopeID;
    Integer circID;
    Integer roomID;
    String slopeLocation;
    double slopeHeight;
    int slopeHasRamp;
    Integer slopeRampQnt;
    Double inclAngle1;
    Double inclAngle2;
    Double inclAngle3;
    Double inclAngle4;
    String slopeObs;
    String slopePhoto;

    public SlopeEntry(Integer circID, Integer roomID, String slopeLocation, double slopeHeight, int slopeHasRamp, Integer slopeRampQnt, Double inclAngle1, Double inclAngle2, Double inclAngle3,
                      Double inclAngle4, String slopeObs, String slopePhoto) {
        this.circID = circID;
        this.roomID = roomID;
        this.slopeLocation = slopeLocation;
        this.slopeHeight = slopeHeight;
        this.slopeHasRamp = slopeHasRamp;
        this.slopeRampQnt = slopeRampQnt;
        this.inclAngle1 = inclAngle1;
        this.inclAngle2 = inclAngle2;
        this.inclAngle3 = inclAngle3;
        this.inclAngle4 = inclAngle4;
        this.slopeObs = slopeObs;
        this.slopePhoto = slopePhoto;
    }

    public int getSlopeID() {
        return slopeID;
    }

    public void setSlopeID(int slopeID) {
        this.slopeID = slopeID;
    }

    public Integer getCircID() {
        return circID;
    }

    public void setCircID(Integer circID) {
        this.circID = circID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getSlopeLocation() {
        return slopeLocation;
    }

    public void setSlopeLocation(String slopeLocation) {
        this.slopeLocation = slopeLocation;
    }

    public double getSlopeHeight() {
        return slopeHeight;
    }

    public void setSlopeHeight(double slopeHeight) {
        this.slopeHeight = slopeHeight;
    }

    public int getSlopeHasRamp() {
        return slopeHasRamp;
    }

    public void setSlopeHasRamp(int slopeHasRamp) {
        this.slopeHasRamp = slopeHasRamp;
    }

    public Integer getSlopeRampQnt() {
        return slopeRampQnt;
    }

    public void setSlopeRampQnt(Integer slopeRampQnt) {
        this.slopeRampQnt = slopeRampQnt;
    }

    public Double getInclAngle1() {
        return inclAngle1;
    }

    public void setInclAngle1(Double inclAngle1) {
        this.inclAngle1 = inclAngle1;
    }

    public Double getInclAngle2() {
        return inclAngle2;
    }

    public void setInclAngle2(Double inclAngle2) {
        this.inclAngle2 = inclAngle2;
    }

    public Double getInclAngle3() {
        return inclAngle3;
    }

    public void setInclAngle3(Double inclAngle3) {
        this.inclAngle3 = inclAngle3;
    }

    public Double getInclAngle4() {
        return inclAngle4;
    }

    public void setInclAngle4(Double inclAngle4) {
        this.inclAngle4 = inclAngle4;
    }

    public String getSlopeObs() {
        return slopeObs;
    }

    public void setSlopeObs(String slopeObs) {
        this.slopeObs = slopeObs;
    }

    public String getSlopePhoto() {
        return slopePhoto;
    }

    public void setSlopePhoto(String slopePhoto) {
        this.slopePhoto = slopePhoto;
    }
}
