package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID",
                onDelete = CASCADE, onUpdate = CASCADE))
public class DoorEntry {

    @PrimaryKey(autoGenerate = true)
    private int doorID;
    private int roomID;

    private String doorLocation;
    private Double doorWidth;
    private Integer doorHandleType;
    private Double doorHandleHeight;
    private String doorHandleObs;
    private Integer doorHasLocks;
    private Integer doorHasTactileSign;
    private String doorTactileSignObs;
    private Integer doorSillType;
    private Double sillInclinationHeight;
    private Double sillStepHeight;
    private Integer sillSlopeQnt;
    private Double sillSlopeAngle1;
    private Double sillSlopeAngle2;
    private Double sillSlopeAngle3;
    private Double sillSlopeAngle4;
    private Double sillSlopeWidth;
    private Double sillSlopeHeight;
    private String doorSillObs;
    private String doorObs;

    public DoorEntry(int roomID, String doorLocation, Double doorWidth, Integer doorHandleType, Double doorHandleHeight, String doorHandleObs, Integer doorHasLocks,
                     Integer doorHasTactileSign, String doorTactileSignObs, Integer doorSillType, Double sillInclinationHeight, Double sillStepHeight, Integer sillSlopeQnt,
                     Double sillSlopeAngle1, Double sillSlopeAngle2, Double sillSlopeAngle3, Double sillSlopeAngle4, Double sillSlopeWidth,  Double sillSlopeHeight,
                     String doorSillObs, String doorObs) {
        this.roomID = roomID;
        this.doorLocation = doorLocation;
        this.doorWidth = doorWidth;
        this.doorHandleType = doorHandleType;
        this.doorHandleHeight = doorHandleHeight;
        this.doorHandleObs = doorHandleObs;
        this.doorHasLocks = doorHasLocks;
        this.doorHasTactileSign = doorHasTactileSign;
        this.doorTactileSignObs = doorTactileSignObs;
        this.doorSillType = doorSillType;
        this.sillInclinationHeight = sillInclinationHeight;
        this.sillStepHeight = sillStepHeight;
        this.sillSlopeQnt = sillSlopeQnt;
        this.sillSlopeAngle1 = sillSlopeAngle1;
        this.sillSlopeAngle2 = sillSlopeAngle2;
        this.sillSlopeAngle3 = sillSlopeAngle3;
        this.sillSlopeAngle4 = sillSlopeAngle4;
        this.sillSlopeWidth = sillSlopeWidth;
        this.sillSlopeHeight = sillSlopeHeight;
        this.doorSillObs = doorSillObs;
        this.doorObs = doorObs;
    }

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
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

    public Integer getSillSlopeQnt() {
        return sillSlopeQnt;
    }

    public void setSillSlopeQnt(Integer sillSlopeQnt) {
        this.sillSlopeQnt = sillSlopeQnt;
    }

    public Double getSillSlopeAngle1() {
        return sillSlopeAngle1;
    }

    public void setSillSlopeAngle1(Double sillSlopeAngle1) {
        this.sillSlopeAngle1 = sillSlopeAngle1;
    }

    public Double getSillSlopeAngle2() {
        return sillSlopeAngle2;
    }

    public void setSillSlopeAngle2(Double sillSlopeAngle2) {
        this.sillSlopeAngle2 = sillSlopeAngle2;
    }

    public Double getSillSlopeAngle3() {
        return sillSlopeAngle3;
    }

    public void setSillSlopeAngle3(Double sillSlopeAngle3) {
        this.sillSlopeAngle3 = sillSlopeAngle3;
    }

    public Double getSillSlopeAngle4() {
        return sillSlopeAngle4;
    }

    public void setSillSlopeAngle4(Double sillSlopeAngle4) {
        this.sillSlopeAngle4 = sillSlopeAngle4;
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

    public Integer getDoorHandleType() {
        return doorHandleType;
    }

    public void setDoorHandleType(Integer doorHandleType) {
        this.doorHandleType = doorHandleType;
    }

    public Double getDoorHandleHeight() {
        return doorHandleHeight;
    }

    public void setDoorHandleHeight(Double doorHandleHeight) {
        this.doorHandleHeight = doorHandleHeight;
    }

    public String getDoorHandleObs() {
        return doorHandleObs;
    }

    public void setDoorHandleObs(String doorHandleObs) {
        this.doorHandleObs = doorHandleObs;
    }

    public Integer getDoorHasLocks() {
        return doorHasLocks;
    }

    public void setDoorHasLocks(Integer doorHasLocks) {
        this.doorHasLocks = doorHasLocks;
    }

    public Integer getDoorHasTactileSign() {
        return doorHasTactileSign;
    }

    public void setDoorHasTactileSign(Integer doorHasTactileSign) {
        this.doorHasTactileSign = doorHasTactileSign;
    }

    public String getDoorTactileSignObs() {
        return doorTactileSignObs;
    }

    public void setDoorTactileSignObs(String doorTactileSignObs) {
        this.doorTactileSignObs = doorTactileSignObs;
    }

    public String getDoorSillObs() {
        return doorSillObs;
    }

    public void setDoorSillObs(String doorSillObs) {
        this.doorSillObs = doorSillObs;
    }

    public Double getSillSlopeHeight() {
        return sillSlopeHeight;
    }

    public void setSillSlopeHeight(Double sillSlopeHeight) {
        this.sillSlopeHeight = sillSlopeHeight;
    }
}
