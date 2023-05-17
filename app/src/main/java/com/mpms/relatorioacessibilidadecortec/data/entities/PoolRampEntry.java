package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = PoolEntry.class, parentColumns = "poolID", childColumns = "poolID", onDelete = CASCADE, onUpdate = CASCADE))
public class PoolRampEntry {

    @PrimaryKey(autoGenerate = true)
    private int poolRampID;
    private int poolID;

    private String poolRampLocale;
    private Integer rampInclQnt;
    private Double rampIncl1;
    private Double rampIncl2;
    private Double rampIncl3;
    private Double rampIncl4;
    private Integer rampAccessFloor;
    private String accessFloorObs;
    private Integer hasLeftHand;
    private Double leftHandHeight;
    private Double leftHandDiam;
    private Double leftHandDist;
    private Integer hasRightHand;
    private Double rightHandHeight;
    private Double rightHandDiam;
    private Double rightHandDist;
    private String poolHandObs;
    private String poolRampPhoto;
    private String poolRampObs;

    public PoolRampEntry(int poolID, String poolRampLocale, Integer rampInclQnt, Double rampIncl1, Double rampIncl2, Double rampIncl3, Double rampIncl4, Integer rampAccessFloor, String accessFloorObs,
                         Integer hasLeftHand, Double leftHandHeight, Double leftHandDiam, Double leftHandDist, Integer hasRightHand, Double rightHandHeight, Double rightHandDiam, Double rightHandDist,
                         String poolHandObs, String poolRampPhoto, String poolRampObs) {
        this.poolID = poolID;
        this.poolRampLocale = poolRampLocale;
        this.rampInclQnt = rampInclQnt;
        this.rampIncl1 = rampIncl1;
        this.rampIncl2 = rampIncl2;
        this.rampIncl3 = rampIncl3;
        this.rampIncl4 = rampIncl4;
        this.rampAccessFloor = rampAccessFloor;
        this.accessFloorObs = accessFloorObs;
        this.hasLeftHand = hasLeftHand;
        this.leftHandHeight = leftHandHeight;
        this.leftHandDiam = leftHandDiam;
        this.leftHandDist = leftHandDist;
        this.hasRightHand = hasRightHand;
        this.rightHandHeight = rightHandHeight;
        this.rightHandDiam = rightHandDiam;
        this.rightHandDist = rightHandDist;
        this.poolHandObs = poolHandObs;
        this.poolRampPhoto = poolRampPhoto;
        this.poolRampObs = poolRampObs;
    }

    public int getPoolRampID() {
        return poolRampID;
    }

    public void setPoolRampID(int poolRampID) {
        this.poolRampID = poolRampID;
    }

    public int getPoolID() {
        return poolID;
    }

    public void setPoolID(int poolID) {
        this.poolID = poolID;
    }

    public String getPoolRampLocale() {
        return poolRampLocale;
    }

    public void setPoolRampLocale(String poolRampLocale) {
        this.poolRampLocale = poolRampLocale;
    }

    public Integer getRampInclQnt() {
        return rampInclQnt;
    }

    public void setRampInclQnt(Integer rampInclQnt) {
        this.rampInclQnt = rampInclQnt;
    }

    public Double getRampIncl1() {
        return rampIncl1;
    }

    public void setRampIncl1(Double rampIncl1) {
        this.rampIncl1 = rampIncl1;
    }

    public Double getRampIncl2() {
        return rampIncl2;
    }

    public void setRampIncl2(Double rampIncl2) {
        this.rampIncl2 = rampIncl2;
    }

    public Double getRampIncl3() {
        return rampIncl3;
    }

    public void setRampIncl3(Double rampIncl3) {
        this.rampIncl3 = rampIncl3;
    }

    public Double getRampIncl4() {
        return rampIncl4;
    }

    public void setRampIncl4(Double rampIncl4) {
        this.rampIncl4 = rampIncl4;
    }

    public Integer getRampAccessFloor() {
        return rampAccessFloor;
    }

    public void setRampAccessFloor(Integer rampAccessFloor) {
        this.rampAccessFloor = rampAccessFloor;
    }

    public String getAccessFloorObs() {
        return accessFloorObs;
    }

    public void setAccessFloorObs(String accessFloorObs) {
        this.accessFloorObs = accessFloorObs;
    }

    public Integer getHasLeftHand() {
        return hasLeftHand;
    }

    public void setHasLeftHand(Integer hasLeftHand) {
        this.hasLeftHand = hasLeftHand;
    }

    public Double getLeftHandHeight() {
        return leftHandHeight;
    }

    public void setLeftHandHeight(Double leftHandHeight) {
        this.leftHandHeight = leftHandHeight;
    }

    public Integer getHasRightHand() {
        return hasRightHand;
    }

    public void setHasRightHand(Integer hasRightHand) {
        this.hasRightHand = hasRightHand;
    }

    public Double getRightHandHeight() {
        return rightHandHeight;
    }

    public void setRightHandHeight(Double rightHandHeight) {
        this.rightHandHeight = rightHandHeight;
    }

    public String getPoolHandObs() {
        return poolHandObs;
    }

    public void setPoolHandObs(String poolHandObs) {
        this.poolHandObs = poolHandObs;
    }

    public String getPoolRampPhoto() {
        return poolRampPhoto;
    }

    public void setPoolRampPhoto(String poolRampPhoto) {
        this.poolRampPhoto = poolRampPhoto;
    }

    public String getPoolRampObs() {
        return poolRampObs;
    }

    public void setPoolRampObs(String poolRampObs) {
        this.poolRampObs = poolRampObs;
    }

    public Double getLeftHandDiam() {
        return leftHandDiam;
    }

    public void setLeftHandDiam(Double leftHandDiam) {
        this.leftHandDiam = leftHandDiam;
    }

    public Double getLeftHandDist() {
        return leftHandDist;
    }

    public void setLeftHandDist(Double leftHandDist) {
        this.leftHandDist = leftHandDist;
    }

    public Double getRightHandDiam() {
        return rightHandDiam;
    }

    public void setRightHandDiam(Double rightHandDiam) {
        this.rightHandDiam = rightHandDiam;
    }

    public Double getRightHandDist() {
        return rightHandDist;
    }

    public void setRightHandDist(Double rightHandDist) {
        this.rightHandDist = rightHandDist;
    }
}
