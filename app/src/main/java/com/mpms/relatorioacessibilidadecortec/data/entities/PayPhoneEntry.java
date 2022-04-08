package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {@ForeignKey(entity = ExternalAccess.class, parentColumns = "externalAccessID", childColumns = "extAccessID", onDelete = CASCADE, onUpdate = CASCADE),
                        @ForeignKey(entity = SidewalkEntry.class, parentColumns = "sidewalkID", childColumns = "sidewalkID", onDelete = CASCADE, onUpdate = CASCADE)})
public class PayPhoneEntry {

    @PrimaryKey(autoGenerate = true)
    private int payPhoneID;
    private Integer extAccessID;
    private Integer sidewalkID;

    private String phoneRefPoint;
    private double phoneKeyboardHeight;
    private double phoneHeight;
    private int hasTactileFloor;
    private Integer rightColorTactileFloor;
    private Double tactFloorLength;
    private Double tactFloorWidth;
    private String tactFloorObs;
    private int phoneIsWorking;
    private String payPhoneObs;

    public PayPhoneEntry(Integer extAccessID, Integer sidewalkID, String phoneRefPoint, double phoneKeyboardHeight, double phoneHeight, int hasTactileFloor,
                         Integer rightColorTactileFloor, Double tactFloorLength, Double tactFloorWidth, String tactFloorObs, int phoneIsWorking, String payPhoneObs) {
        this.extAccessID = extAccessID;
        this.sidewalkID = sidewalkID;
        this.phoneRefPoint = phoneRefPoint;
        this.phoneKeyboardHeight = phoneKeyboardHeight;
        this.phoneHeight = phoneHeight;
        this.hasTactileFloor = hasTactileFloor;
        this.rightColorTactileFloor = rightColorTactileFloor;
        this.tactFloorLength = tactFloorLength;
        this.tactFloorWidth = tactFloorWidth;
        this.tactFloorObs = tactFloorObs;
        this.phoneIsWorking = phoneIsWorking;
        this.payPhoneObs = payPhoneObs;
    }

    public int getPayPhoneID() {
        return payPhoneID;
    }

    public void setPayPhoneID(int payPhoneID) {
        this.payPhoneID = payPhoneID;
    }

    public Integer getExtAccessID() {
        return extAccessID;
    }

    public void setExtAccessID(Integer extAccessID) {
        this.extAccessID = extAccessID;
    }

    public Integer getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(Integer sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public String getPhoneRefPoint() {
        return phoneRefPoint;
    }

    public void setPhoneRefPoint(String phoneRefPoint) {
        this.phoneRefPoint = phoneRefPoint;
    }

    public double getPhoneKeyboardHeight() {
        return phoneKeyboardHeight;
    }

    public void setPhoneKeyboardHeight(double phoneKeyboardHeight) {
        this.phoneKeyboardHeight = phoneKeyboardHeight;
    }

    public double getPhoneHeight() {
        return phoneHeight;
    }

    public void setPhoneHeight(double phoneHeight) {
        this.phoneHeight = phoneHeight;
    }

    public int getHasTactileFloor() {
        return hasTactileFloor;
    }

    public void setHasTactileFloor(int hasTactileFloor) {
        this.hasTactileFloor = hasTactileFloor;
    }

    public Integer getRightColorTactileFloor() {
        return rightColorTactileFloor;
    }

    public void setRightColorTactileFloor(Integer rightColorTactileFloor) {
        this.rightColorTactileFloor = rightColorTactileFloor;
    }

    public Double getTactFloorLength() {
        return tactFloorLength;
    }

    public void setTactFloorLength(Double tactFloorLength) {
        this.tactFloorLength = tactFloorLength;
    }

    public Double getTactFloorWidth() {
        return tactFloorWidth;
    }

    public void setTactFloorWidth(Double tactFloorWidth) {
        this.tactFloorWidth = tactFloorWidth;
    }

    public String getTactFloorObs() {
        return tactFloorObs;
    }

    public void setTactFloorObs(String tactFloorObs) {
        this.tactFloorObs = tactFloorObs;
    }

    public int getPhoneIsWorking() {
        return phoneIsWorking;
    }

    public void setPhoneIsWorking(int phoneIsWorking) {
        this.phoneIsWorking = phoneIsWorking;
    }

    public String getPayPhoneObs() {
        return payPhoneObs;
    }

    public void setPayPhoneObs(String payPhoneObs) {
        this.payPhoneObs = payPhoneObs;
    }
}
