package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {@ForeignKey(entity = DoorEntry.class, parentColumns = "doorID", childColumns = "doorID", onDelete = CASCADE, onUpdate = CASCADE),
                        @ForeignKey(entity = ExternalAccess.class, parentColumns = "externalAccessID", childColumns = "extAccessID", onDelete = CASCADE, onUpdate = CASCADE)})
public class DoorLockEntry {

    @PrimaryKey(autoGenerate = true)
    private int lockID;
    private Integer doorID;
    private Integer extAccessID;

    private int lockType;
    private String lockDesc;
    private double lockHeight;
    private String lockObs;
    private String lockPhoto;

    public DoorLockEntry(Integer doorID, Integer extAccessID, int lockType, String lockDesc, double lockHeight, String lockObs, String lockPhoto) {
        this.doorID = doorID;
        this.extAccessID = extAccessID;
        this.lockType = lockType;
        this.lockDesc = lockDesc;
        this.lockHeight = lockHeight;
        this.lockObs = lockObs;
        this.lockPhoto = lockPhoto;
    }

    public String getLockPhoto() {
        return lockPhoto;
    }

    public void setLockPhoto(String lockPhoto) {
        this.lockPhoto = lockPhoto;
    }

    public int getLockID() {
        return lockID;
    }

    public void setLockID(int lockID) {
        this.lockID = lockID;
    }

    public Integer getDoorID() {
        return doorID;
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

    public void setDoorID(Integer doorID) {
        this.doorID = doorID;
    }

    public Integer getExtAccessID() {
        return extAccessID;
    }

    public void setExtAccessID(Integer extAccessID) {
        this.extAccessID = extAccessID;
    }
}
