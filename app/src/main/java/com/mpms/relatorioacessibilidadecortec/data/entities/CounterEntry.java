package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE)})
public class CounterEntry {

    @PrimaryKey(autoGenerate = true)
    private int counterID;
    private Integer roomID;
    private Integer circID;

    private String counterLocation;
    private double counterUpperEdge;
    private double counterLowerEdge;
    private double counterFrontalApprox;
    private String counterObs;
    private Double counterWidth;
    private Double counterFreeWidth;
    private String counterPhoto;

    public CounterEntry(Integer roomID, Integer circID, String counterLocation, double counterUpperEdge, double counterLowerEdge, double counterFrontalApprox, String counterObs,
                        Double counterWidth, Double counterFreeWidth, String counterPhoto) {
        this.roomID = roomID;
        this.circID = circID;
        this.counterLocation = counterLocation;
        this.counterUpperEdge = counterUpperEdge;
        this.counterLowerEdge = counterLowerEdge;
        this.counterFrontalApprox = counterFrontalApprox;
        this.counterObs = counterObs;
        this.counterWidth = counterWidth;
        this.counterFreeWidth = counterFreeWidth;
        this.counterPhoto = counterPhoto;
    }

    public String getCounterPhoto() {
        return counterPhoto;
    }

    public void setCounterPhoto(String counterPhoto) {
        this.counterPhoto = counterPhoto;
    }

    public int getCounterID() {
        return counterID;
    }

    public void setCounterID(int counterID) {
        this.counterID = counterID;
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

    public String getCounterLocation() {
        return counterLocation;
    }

    public void setCounterLocation(String counterLocation) {
        this.counterLocation = counterLocation;
    }

    public double getCounterUpperEdge() {
        return counterUpperEdge;
    }

    public void setCounterUpperEdge(double counterUpperEdge) {
        this.counterUpperEdge = counterUpperEdge;
    }

    public double getCounterLowerEdge() {
        return counterLowerEdge;
    }

    public void setCounterLowerEdge(double counterLowerEdge) {
        this.counterLowerEdge = counterLowerEdge;
    }

    public double getCounterFrontalApprox() {
        return counterFrontalApprox;
    }

    public void setCounterFrontalApprox(double counterFrontalApprox) {
        this.counterFrontalApprox = counterFrontalApprox;
    }

    public String getCounterObs() {
        return counterObs;
    }

    public void setCounterObs(String counterObs) {
        this.counterObs = counterObs;
    }

    public double getCounterWidth() {
        return counterWidth;
    }

    public void setCounterWidth(Double counterWidth) {
        this.counterWidth = counterWidth;
    }

    public double getCounterFreeWidth() {
        return counterFreeWidth;
    }

    public void setCounterFreeWidth(Double counterFreeWidth) {
        this.counterFreeWidth = counterFreeWidth;
    }
}
