package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys =
        @ForeignKey(entity = ExternalAccess.class,
            parentColumns = "externalAccessID",
            childColumns = "externalAccessID",
            onDelete = CASCADE, onUpdate = CASCADE))
public class PayPhoneEntry {

    @PrimaryKey(autoGenerate = true)
    private int payPhoneID;
    private int externalAccessID;

    private String phoneRefPoint;
    private double phoneKeyboardHeight;
    private double phoneHeight;
    private int hasTactileFloor;
    private int phoneIsWorking;
    private String payPhoneObs;

    public PayPhoneEntry(int externalAccessID, String phoneRefPoint, double phoneKeyboardHeight, double phoneHeight, int hasTactileFloor,
                         int phoneIsWorking, String payPhoneObs) {
        this.externalAccessID = externalAccessID;
        this.phoneRefPoint = phoneRefPoint;
        this.phoneKeyboardHeight = phoneKeyboardHeight;
        this.phoneHeight = phoneHeight;
        this.hasTactileFloor = hasTactileFloor;
        this.phoneIsWorking = phoneIsWorking;
        this.payPhoneObs = payPhoneObs;
    }

    public int getPayPhoneID() {
        return payPhoneID;
    }

    public void setPayPhoneID(int payPhoneID) {
        this.payPhoneID = payPhoneID;
    }

    public int getExternalAccessID() {
        return externalAccessID;
    }

    public void setExternalAccessID(int externalAccessID) {
        this.externalAccessID = externalAccessID;
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
