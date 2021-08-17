package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class CounterEntry {

    @PrimaryKey(autoGenerate = true)
    private int counterID;
    private int roomID;

    private String counterLocation;
    private double counterUpperEdge;
    private double counterLowerEdge;
    private double counterFrontalApprox;
    private String counterObs;

    public CounterEntry(int roomID, String counterLocation, double counterUpperEdge, double counterLowerEdge, double counterFrontalApprox, String counterObs) {
        this.roomID = roomID;
        this.counterLocation = counterLocation;
        this.counterUpperEdge = counterUpperEdge;
        this.counterLowerEdge = counterLowerEdge;
        this.counterFrontalApprox = counterFrontalApprox;
        this.counterObs = counterObs;
    }


    public int getCounterID() {
        return counterID;
    }

    public void setCounterID(int counterID) {
        this.counterID = counterID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
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
}
