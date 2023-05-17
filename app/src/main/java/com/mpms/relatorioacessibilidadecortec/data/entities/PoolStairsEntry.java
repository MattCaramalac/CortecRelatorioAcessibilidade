package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = PoolEntry.class, parentColumns = "poolID", childColumns = "poolID", onDelete = CASCADE, onUpdate = CASCADE))
public class PoolStairsEntry {

    @PrimaryKey(autoGenerate = true)
    private int poolStairsID;
    private int poolID;

    private String stairsLocation;
    private Double stairsWidth;
    private Integer mirrorQnt;
    private Double mirror1;
    private Double mirror2;
    private Double mirror3;
    private Double mirror4;
    private Integer stepQnt;
    private Double step1;
    private Double step2;
    private Double step3;
    private Double step4;
    private Integer stairsHasLeftHand;
    private Double poolLeftUpperHandHeight;
    private Double poolLeftInterHandHeight;
    private Double poolLeftLowerHandHeight;
    private Double poolLeftHandDiam;
    private Double poolLeftHandDist;
    private Integer stairsHasRightHand;
    private Double poolRightUpperHandHeight;
    private Double poolRightInterHandHeight;
    private Double poolRightLowerHandHeight;
    private Double poolRightHandDiam;
    private Double poolRightHandDist;
    private String poolStairsPhoto;
    private String poolStairsObs;

    public PoolStairsEntry(int poolID, String stairsLocation, Double stairsWidth, Integer mirrorQnt, Double mirror1,
                           Double mirror2, Double mirror3, Double mirror4, Integer stepQnt, Double step1, Double step2, Double step3, Double step4, Integer stairsHasLeftHand,
                           Double poolLeftUpperHandHeight, Double poolLeftInterHandHeight, Double poolLeftLowerHandHeight, Double poolLeftHandDiam, Double poolLeftHandDist,
                           Integer stairsHasRightHand, Double poolRightUpperHandHeight, Double poolRightInterHandHeight, Double poolRightLowerHandHeight, Double poolRightHandDiam,
                           Double poolRightHandDist, String poolStairsPhoto, String poolStairsObs) {
        this.poolID = poolID;
        this.stairsWidth = stairsWidth;
        this.mirrorQnt = mirrorQnt;
        this.mirror1 = mirror1;
        this.mirror2 = mirror2;
        this.mirror3 = mirror3;
        this.mirror4 = mirror4;
        this.stepQnt = stepQnt;
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
        this.step4 = step4;
        this.stairsHasLeftHand = stairsHasLeftHand;
        this.poolLeftUpperHandHeight = poolLeftUpperHandHeight;
        this.poolLeftInterHandHeight = poolLeftInterHandHeight;
        this.poolLeftLowerHandHeight = poolLeftLowerHandHeight;
        this.poolLeftHandDiam = poolLeftHandDiam;
        this.poolLeftHandDist = poolLeftHandDist;
        this.stairsHasRightHand = stairsHasRightHand;
        this.poolRightUpperHandHeight = poolRightUpperHandHeight;
        this.poolRightInterHandHeight = poolRightInterHandHeight;
        this.poolRightLowerHandHeight = poolRightLowerHandHeight;
        this.poolRightHandDiam = poolRightHandDiam;
        this.poolRightHandDist = poolRightHandDist;
        this.poolStairsPhoto = poolStairsPhoto;
        this.poolStairsObs = poolStairsObs;
        this.stairsLocation = stairsLocation;
    }

    public int getPoolStairsID() {
        return poolStairsID;
    }

    public void setPoolStairsID(int poolStairsID) {
        this.poolStairsID = poolStairsID;
    }

    public int getPoolID() {
        return poolID;
    }

    public void setPoolID(int poolID) {
        this.poolID = poolID;
    }

    public String getStairsLocation() {
        return stairsLocation;
    }

    public void setStairsLocation(String stairsLocation) {
        this.stairsLocation = stairsLocation;
    }

    public Double getStairsWidth() {
        return stairsWidth;
    }

    public void setStairsWidth(Double stairsWidth) {
        this.stairsWidth = stairsWidth;
    }

    public Integer getMirrorQnt() {
        return mirrorQnt;
    }

    public void setMirrorQnt(Integer mirrorQnt) {
        this.mirrorQnt = mirrorQnt;
    }

    public Double getMirror1() {
        return mirror1;
    }

    public void setMirror1(Double mirror1) {
        this.mirror1 = mirror1;
    }

    public Double getMirror2() {
        return mirror2;
    }

    public void setMirror2(Double mirror2) {
        this.mirror2 = mirror2;
    }

    public Double getMirror3() {
        return mirror3;
    }

    public void setMirror3(Double mirror3) {
        this.mirror3 = mirror3;
    }

    public Double getMirror4() {
        return mirror4;
    }

    public void setMirror4(Double mirror4) {
        this.mirror4 = mirror4;
    }

    public Integer getStepQnt() {
        return stepQnt;
    }

    public void setStepQnt(Integer stepQnt) {
        this.stepQnt = stepQnt;
    }

    public Double getStep1() {
        return step1;
    }

    public void setStep1(Double step1) {
        this.step1 = step1;
    }

    public Double getStep2() {
        return step2;
    }

    public void setStep2(Double step2) {
        this.step2 = step2;
    }

    public Double getStep3() {
        return step3;
    }

    public void setStep3(Double step3) {
        this.step3 = step3;
    }

    public Double getStep4() {
        return step4;
    }

    public void setStep4(Double step4) {
        this.step4 = step4;
    }

    public Integer getStairsHasLeftHand() {
        return stairsHasLeftHand;
    }

    public void setStairsHasLeftHand(Integer stairsHasLeftHand) {
        this.stairsHasLeftHand = stairsHasLeftHand;
    }

    public Double getPoolLeftUpperHandHeight() {
        return poolLeftUpperHandHeight;
    }

    public void setPoolLeftUpperHandHeight(Double poolLeftUpperHandHeight) {
        this.poolLeftUpperHandHeight = poolLeftUpperHandHeight;
    }

    public Double getPoolLeftInterHandHeight() {
        return poolLeftInterHandHeight;
    }

    public void setPoolLeftInterHandHeight(Double poolLeftInterHandHeight) {
        this.poolLeftInterHandHeight = poolLeftInterHandHeight;
    }

    public Double getPoolLeftLowerHandHeight() {
        return poolLeftLowerHandHeight;
    }

    public void setPoolLeftLowerHandHeight(Double poolLeftLowerHandHeight) {
        this.poolLeftLowerHandHeight = poolLeftLowerHandHeight;
    }

    public Double getPoolLeftHandDiam() {
        return poolLeftHandDiam;
    }

    public void setPoolLeftHandDiam(Double poolLeftHandDiam) {
        this.poolLeftHandDiam = poolLeftHandDiam;
    }

    public Double getPoolLeftHandDist() {
        return poolLeftHandDist;
    }

    public void setPoolLeftHandDist(Double poolLeftHandDist) {
        this.poolLeftHandDist = poolLeftHandDist;
    }

    public Integer getStairsHasRightHand() {
        return stairsHasRightHand;
    }

    public void setStairsHasRightHand(Integer stairsHasRightHand) {
        this.stairsHasRightHand = stairsHasRightHand;
    }

    public Double getPoolRightUpperHandHeight() {
        return poolRightUpperHandHeight;
    }

    public void setPoolRightUpperHandHeight(Double poolRightUpperHandHeight) {
        this.poolRightUpperHandHeight = poolRightUpperHandHeight;
    }

    public Double getPoolRightInterHandHeight() {
        return poolRightInterHandHeight;
    }

    public void setPoolRightInterHandHeight(Double poolRightInterHandHeight) {
        this.poolRightInterHandHeight = poolRightInterHandHeight;
    }

    public Double getPoolRightLowerHandHeight() {
        return poolRightLowerHandHeight;
    }

    public void setPoolRightLowerHandHeight(Double poolRightLowerHandHeight) {
        this.poolRightLowerHandHeight = poolRightLowerHandHeight;
    }

    public Double getPoolRightHandDiam() {
        return poolRightHandDiam;
    }

    public void setPoolRightHandDiam(Double poolRightHandDiam) {
        this.poolRightHandDiam = poolRightHandDiam;
    }

    public Double getPoolRightHandDist() {
        return poolRightHandDist;
    }

    public void setPoolRightHandDist(Double poolRightHandDist) {
        this.poolRightHandDist = poolRightHandDist;
    }

    public String getPoolStairsObs() {
        return poolStairsObs;
    }

    public void setPoolStairsObs(String poolStairsObs) {
        this.poolStairsObs = poolStairsObs;
    }

    public String getPoolStairsPhoto() {
        return poolStairsPhoto;
    }

    public void setPoolStairsPhoto(String poolStairsPhoto) {
        this.poolStairsPhoto = poolStairsPhoto;
    }
}
