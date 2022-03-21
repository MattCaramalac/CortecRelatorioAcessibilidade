package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {@ForeignKey(entity = DoorEntry.class, parentColumns = "doorID", childColumns = "doorID", onDelete = CASCADE, onUpdate = CASCADE)})
public class DoorLockEntry {

    @PrimaryKey(autoGenerate = true)
    private int lockID;
    private int doorID;

    private int lockType;
    private String lockDesc;
    private double lockHeight;
    private String lockObs;

    public DoorLockEntry(int doorID, int lockType, String lockDesc, double lockHeight, String lockObs) {
        this.doorID = doorID;
        this.lockType = lockType;
        this.lockDesc = lockDesc;
        this.lockHeight = lockHeight;
        this.lockObs = lockObs;
    }

    public int getLockID() {
        return lockID;
    }

    public void setLockID(int lockID) {
        this.lockID = lockID;
    }

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public int getLockType() {
        return lockType;
    }

    public void setLockType(int lockType) {
        this.lockType = lockType;
    }

    public String getLockDesc() {
        return lockDesc;
    }

    public void setLockDesc(String lockDesc) {
        this.lockDesc = lockDesc;
    }

    public double getLockHeight() {
        return lockHeight;
    }

    public void setLockHeight(double lockHeight) {
        this.lockHeight = lockHeight;
    }

    public String getLockObs() {
        return lockObs;
    }

    public void setLockObs(String lockObs) {
        this.lockObs = lockObs;
    }
}
