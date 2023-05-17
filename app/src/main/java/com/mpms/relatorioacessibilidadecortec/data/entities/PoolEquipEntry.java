package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = PoolEntry.class, parentColumns = "poolID", childColumns = "poolID", onDelete = CASCADE, onUpdate = CASCADE))
public class PoolEquipEntry {

    @PrimaryKey(autoGenerate = true)
    private int poolEquipID;
    private int poolID;

    private String transfLocation;
    private Double transfMeasureA;
    private Double transfMeasureB;
    private Double transfMeasureC;
    private Double transfMeasureD;
    private Double transfMeasureE;
    private String transfPhoto;
    private String transfObs;

    public PoolEquipEntry(int poolID, String transfLocation, Double transfMeasureA, Double transfMeasureB, Double transfMeasureC, Double transfMeasureD, Double transfMeasureE,
                          String transfPhoto, String transfObs) {
        this.poolID = poolID;
        this.transfLocation = transfLocation;
        this.transfMeasureA = transfMeasureA;
        this.transfMeasureB = transfMeasureB;
        this.transfMeasureC = transfMeasureC;
        this.transfMeasureD = transfMeasureD;
        this.transfMeasureE = transfMeasureE;
        this.transfPhoto = transfPhoto;
        this.transfObs = transfObs;
    }

    public int getPoolEquipID() {
        return poolEquipID;
    }

    public void setPoolEquipID(int poolEquipID) {
        this.poolEquipID = poolEquipID;
    }

    public int getPoolID() {
        return poolID;
    }

    public void setPoolID(int poolID) {
        this.poolID = poolID;
    }

    public String getTransfLocation() {
        return transfLocation;
    }

    public void setTransfLocation(String transfLocation) {
        this.transfLocation = transfLocation;
    }

    public Double getTransfMeasureA() {
        return transfMeasureA;
    }

    public void setTransfMeasureA(Double transfMeasureA) {
        this.transfMeasureA = transfMeasureA;
    }

    public Double getTransfMeasureB() {
        return transfMeasureB;
    }

    public void setTransfMeasureB(Double transfMeasureB) {
        this.transfMeasureB = transfMeasureB;
    }

    public Double getTransfMeasureC() {
        return transfMeasureC;
    }

    public void setTransfMeasureC(Double transfMeasureC) {
        this.transfMeasureC = transfMeasureC;
    }

    public Double getTransfMeasureD() {
        return transfMeasureD;
    }

    public void setTransfMeasureD(Double transfMeasureD) {
        this.transfMeasureD = transfMeasureD;
    }

    public Double getTransfMeasureE() {
        return transfMeasureE;
    }

    public void setTransfMeasureE(Double transfMeasureE) {
        this.transfMeasureE = transfMeasureE;
    }

    public String getTransfObs() {
        return transfObs;
    }

    public void setTransfObs(String transfObs) {
        this.transfObs = transfObs;
    }

    public String getTransfPhoto() {
        return transfPhoto;
    }

    public void setTransfPhoto(String transfPhoto) {
        this.transfPhoto = transfPhoto;
    }
}
