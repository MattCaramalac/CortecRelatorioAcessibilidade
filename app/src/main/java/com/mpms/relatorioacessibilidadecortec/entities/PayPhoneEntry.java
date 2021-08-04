package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

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
    private Double phoneOpHeight;
    private Integer hasTactileFloor;
    private String payPhoneObs;

    public PayPhoneEntry(int externalAccessID, String phoneRefPoint, Double phoneOpHeight, Integer hasTactileFloor, String payPhoneObs) {
        this.externalAccessID = externalAccessID;
        this.phoneRefPoint = phoneRefPoint;
        this.phoneOpHeight = phoneOpHeight;
        this.hasTactileFloor = hasTactileFloor;
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

    public Double getPhoneOpHeight() {
        return phoneOpHeight;
    }

    public void setPhoneOpHeight(Double phoneOpHeight) {
        this.phoneOpHeight = phoneOpHeight;
    }

    public Integer getHasTactileFloor() {
        return hasTactileFloor;
    }

    public void setHasTactileFloor(Integer hasTactileFloor) {
        this.hasTactileFloor = hasTactileFloor;
    }

    public String getPayPhoneObs() {
        return payPhoneObs;
    }

    public void setPayPhoneObs(String payPhoneObs) {
        this.payPhoneObs = payPhoneObs;
    }
}
