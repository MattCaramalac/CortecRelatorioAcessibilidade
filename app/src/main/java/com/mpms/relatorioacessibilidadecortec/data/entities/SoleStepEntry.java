package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SoleStepEntry {

    @PrimaryKey(autoGenerate = true)
    int stepID;
    int circID;
    String stepLocation;
    double stepSize;
    int stepQnt;
    double mirrorHeight1;
    Double stepLenght;
    Double mirrorHeight2;
    int stepHasSignaling;
    Integer stepSignalFull;
    Integer stepSignalContrast;
    int stepLowerTactFloor;
    Double lowerTactFloorDist;
    Double lowerTactFloorWidth;
    Integer lowerAntiDrift;
    Integer lowerColorContrast;
    int stepUpperTactFloor;
    Double upperTactFloorDist;
    Double upperTactFloorWidth;
    Integer upperAntiDrift;
    Integer upperColorContrast;
    int hasHandrail;
    Double singleHandrailLength;
    Double singleHandrailHeight;
    Integer hasLeftHandrail;
    Double leftUpHandHeight;
    Double leftLowHandHeight;
    Double leftUpHandStartExtension;
    Double leftUpHandEndExtension;
    Double leftLowHandStartExtension;
    Double leftLowHandEndExtension;
    Integer hasRightHandrail;
    Double rightUpHandHeight;
    Double rightLowHandHeight;
    Double rightUpHandStartExtension;
    Double rightUpHandEndExtension;
    Double rightLowHandStartExtension;
    Double rightLowHandEndExtension;
    Integer hasMiddleHandrail;
    Double middleUpHandHeight;
    Double middleLowHandHeight;
    Double middleUpHandStartExtension;
    Double middleUpHandEndExtension;
    Double middleLowHandStartExtension;
    Double middleLowHandEndExtension;
    String stepObs;
    String stepPhoto;

    public SoleStepEntry(int circID, String stepLocation, double stepSize, int stepQnt, double mirrorHeight1, Double stepLenght, Double mirrorHeight2, int stepHasSignaling,
                         Integer stepSignalFull, Integer stepSignalContrast, int stepLowerTactFloor, Double lowerTactFloorDist, Double lowerTactFloorWidth, Integer lowerAntiDrift,
                         Integer lowerColorContrast, int stepUpperTactFloor, Double upperTactFloorDist, Double upperTactFloorWidth, Integer upperAntiDrift, Integer upperColorContrast,
                         int hasHandrail, Double singleHandrailLength, Double singleHandrailHeight, Integer hasLeftHandrail, Double leftUpHandHeight, Double leftLowHandHeight,
                         Double leftUpHandStartExtension, Double leftUpHandEndExtension, Double leftLowHandStartExtension, Double leftLowHandEndExtension, Integer hasRightHandrail,
                         Double rightUpHandHeight, Double rightLowHandHeight, Double rightUpHandStartExtension, Double rightUpHandEndExtension, Double rightLowHandStartExtension,
                         Double rightLowHandEndExtension, Integer hasMiddleHandrail, Double middleUpHandHeight, Double middleLowHandHeight, Double middleUpHandStartExtension,
                         Double middleUpHandEndExtension, Double middleLowHandStartExtension, Double middleLowHandEndExtension, String stepObs, String stepPhoto) {
        this.circID = circID;
        this.stepLocation = stepLocation;
        this.stepSize = stepSize;
        this.stepQnt = stepQnt;
        this.mirrorHeight1 = mirrorHeight1;
        this.stepLenght = stepLenght;
        this.mirrorHeight2 = mirrorHeight2;
        this.stepHasSignaling = stepHasSignaling;
        this.stepSignalFull = stepSignalFull;
        this.stepSignalContrast = stepSignalContrast;
        this.stepLowerTactFloor = stepLowerTactFloor;
        this.lowerTactFloorDist = lowerTactFloorDist;
        this.lowerTactFloorWidth = lowerTactFloorWidth;
        this.lowerAntiDrift = lowerAntiDrift;
        this.lowerColorContrast = lowerColorContrast;
        this.stepUpperTactFloor = stepUpperTactFloor;
        this.upperTactFloorDist = upperTactFloorDist;
        this.upperTactFloorWidth = upperTactFloorWidth;
        this.upperAntiDrift = upperAntiDrift;
        this.upperColorContrast = upperColorContrast;
        this.hasHandrail = hasHandrail;
        this.singleHandrailLength = singleHandrailLength;
        this.singleHandrailHeight = singleHandrailHeight;
        this.hasLeftHandrail = hasLeftHandrail;
        this.leftUpHandHeight = leftUpHandHeight;
        this.leftLowHandHeight = leftLowHandHeight;
        this.leftUpHandStartExtension = leftUpHandStartExtension;
        this.leftUpHandEndExtension = leftUpHandEndExtension;
        this.leftLowHandStartExtension = leftLowHandStartExtension;
        this.leftLowHandEndExtension = leftLowHandEndExtension;
        this.hasRightHandrail = hasRightHandrail;
        this.rightUpHandHeight = rightUpHandHeight;
        this.rightLowHandHeight = rightLowHandHeight;
        this.rightUpHandStartExtension = rightUpHandStartExtension;
        this.rightUpHandEndExtension = rightUpHandEndExtension;
        this.rightLowHandStartExtension = rightLowHandStartExtension;
        this.rightLowHandEndExtension = rightLowHandEndExtension;
        this.hasMiddleHandrail = hasMiddleHandrail;
        this.middleUpHandHeight = middleUpHandHeight;
        this.middleLowHandHeight = middleLowHandHeight;
        this.middleUpHandStartExtension = middleUpHandStartExtension;
        this.middleUpHandEndExtension = middleUpHandEndExtension;
        this.middleLowHandStartExtension = middleLowHandStartExtension;
        this.middleLowHandEndExtension = middleLowHandEndExtension;
        this.stepObs = stepObs;
        this.stepPhoto = stepPhoto;
    }

    public int getStepID() {
        return stepID;
    }

    public void setStepID(int stepID) {
        this.stepID = stepID;
    }

    public int getCircID() {
        return circID;
    }

    public void setCircID(int circID) {
        this.circID = circID;
    }

    public String getStepLocation() {
        return stepLocation;
    }

    public void setStepLocation(String stepLocation) {
        this.stepLocation = stepLocation;
    }

    public double getStepSize() {
        return stepSize;
    }

    public void setStepSize(double stepSize) {
        this.stepSize = stepSize;
    }

    public int getStepQnt() {
        return stepQnt;
    }

    public void setStepQnt(int stepQnt) {
        this.stepQnt = stepQnt;
    }

    public double getMirrorHeight1() {
        return mirrorHeight1;
    }

    public void setMirrorHeight1(double mirrorHeight1) {
        this.mirrorHeight1 = mirrorHeight1;
    }

    public Double getStepLenght() {
        return stepLenght;
    }

    public void setStepLenght(Double stepLenght) {
        this.stepLenght = stepLenght;
    }

    public Double getMirrorHeight2() {
        return mirrorHeight2;
    }

    public void setMirrorHeight2(Double mirrorHeight2) {
        this.mirrorHeight2 = mirrorHeight2;
    }

    public int getStepHasSignaling() {
        return stepHasSignaling;
    }

    public void setStepHasSignaling(int stepHasSignaling) {
        this.stepHasSignaling = stepHasSignaling;
    }

    public Integer getStepSignalFull() {
        return stepSignalFull;
    }

    public void setStepSignalFull(Integer stepSignalFull) {
        this.stepSignalFull = stepSignalFull;
    }

    public Integer getStepSignalContrast() {
        return stepSignalContrast;
    }

    public void setStepSignalContrast(Integer stepSignalContrast) {
        this.stepSignalContrast = stepSignalContrast;
    }

    public int getStepLowerTactFloor() {
        return stepLowerTactFloor;
    }

    public void setStepLowerTactFloor(int stepLowerTactFloor) {
        this.stepLowerTactFloor = stepLowerTactFloor;
    }

    public Double getLowerTactFloorDist() {
        return lowerTactFloorDist;
    }

    public void setLowerTactFloorDist(Double lowerTactFloorDist) {
        this.lowerTactFloorDist = lowerTactFloorDist;
    }

    public Double getLowerTactFloorWidth() {
        return lowerTactFloorWidth;
    }

    public void setLowerTactFloorWidth(Double lowerTactFloorWidth) {
        this.lowerTactFloorWidth = lowerTactFloorWidth;
    }

    public Integer getLowerAntiDrift() {
        return lowerAntiDrift;
    }

    public void setLowerAntiDrift(Integer lowerAntiDrift) {
        this.lowerAntiDrift = lowerAntiDrift;
    }

    public Integer getLowerColorContrast() {
        return lowerColorContrast;
    }

    public void setLowerColorContrast(Integer lowerColorContrast) {
        this.lowerColorContrast = lowerColorContrast;
    }

    public int getStepUpperTactFloor() {
        return stepUpperTactFloor;
    }

    public void setStepUpperTactFloor(int stepUpperTactFloor) {
        this.stepUpperTactFloor = stepUpperTactFloor;
    }

    public Double getUpperTactFloorDist() {
        return upperTactFloorDist;
    }

    public void setUpperTactFloorDist(Double upperTactFloorDist) {
        this.upperTactFloorDist = upperTactFloorDist;
    }

    public Double getUpperTactFloorWidth() {
        return upperTactFloorWidth;
    }

    public void setUpperTactFloorWidth(Double upperTactFloorWidth) {
        this.upperTactFloorWidth = upperTactFloorWidth;
    }

    public Integer getUpperAntiDrift() {
        return upperAntiDrift;
    }

    public void setUpperAntiDrift(Integer upperAntiDrift) {
        this.upperAntiDrift = upperAntiDrift;
    }

    public Integer getUpperColorContrast() {
        return upperColorContrast;
    }

    public void setUpperColorContrast(Integer upperColorContrast) {
        this.upperColorContrast = upperColorContrast;
    }

    public int getHasHandrail() {
        return hasHandrail;
    }

    public void setHasHandrail(int hasHandrail) {
        this.hasHandrail = hasHandrail;
    }

    public Double getSingleHandrailLength() {
        return singleHandrailLength;
    }

    public void setSingleHandrailLength(Double singleHandrailLength) {
        this.singleHandrailLength = singleHandrailLength;
    }

    public Double getSingleHandrailHeight() {
        return singleHandrailHeight;
    }

    public void setSingleHandrailHeight(Double singleHandrailHeight) {
        this.singleHandrailHeight = singleHandrailHeight;
    }

    public Integer getHasLeftHandrail() {
        return hasLeftHandrail;
    }

    public void setHasLeftHandrail(Integer hasLeftHandrail) {
        this.hasLeftHandrail = hasLeftHandrail;
    }

    public Double getLeftUpHandHeight() {
        return leftUpHandHeight;
    }

    public void setLeftUpHandHeight(Double leftUpHandHeight) {
        this.leftUpHandHeight = leftUpHandHeight;
    }

    public Double getLeftLowHandHeight() {
        return leftLowHandHeight;
    }

    public void setLeftLowHandHeight(Double leftLowHandHeight) {
        this.leftLowHandHeight = leftLowHandHeight;
    }

    public Double getLeftUpHandStartExtension() {
        return leftUpHandStartExtension;
    }

    public void setLeftUpHandStartExtension(Double leftUpHandStartExtension) {
        this.leftUpHandStartExtension = leftUpHandStartExtension;
    }

    public Double getLeftUpHandEndExtension() {
        return leftUpHandEndExtension;
    }

    public void setLeftUpHandEndExtension(Double leftUpHandEndExtension) {
        this.leftUpHandEndExtension = leftUpHandEndExtension;
    }

    public Double getLeftLowHandStartExtension() {
        return leftLowHandStartExtension;
    }

    public void setLeftLowHandStartExtension(Double leftLowHandStartExtension) {
        this.leftLowHandStartExtension = leftLowHandStartExtension;
    }

    public Double getLeftLowHandEndExtension() {
        return leftLowHandEndExtension;
    }

    public void setLeftLowHandEndExtension(Double leftLowHandEndExtension) {
        this.leftLowHandEndExtension = leftLowHandEndExtension;
    }

    public Integer getHasRightHandrail() {
        return hasRightHandrail;
    }

    public void setHasRightHandrail(Integer hasRightHandrail) {
        this.hasRightHandrail = hasRightHandrail;
    }

    public Double getRightUpHandHeight() {
        return rightUpHandHeight;
    }

    public void setRightUpHandHeight(Double rightUpHandHeight) {
        this.rightUpHandHeight = rightUpHandHeight;
    }

    public Double getRightLowHandHeight() {
        return rightLowHandHeight;
    }

    public void setRightLowHandHeight(Double rightLowHandHeight) {
        this.rightLowHandHeight = rightLowHandHeight;
    }

    public Double getRightUpHandStartExtension() {
        return rightUpHandStartExtension;
    }

    public void setRightUpHandStartExtension(Double rightUpHandStartExtension) {
        this.rightUpHandStartExtension = rightUpHandStartExtension;
    }

    public Double getRightUpHandEndExtension() {
        return rightUpHandEndExtension;
    }

    public void setRightUpHandEndExtension(Double rightUpHandEndExtension) {
        this.rightUpHandEndExtension = rightUpHandEndExtension;
    }

    public Double getRightLowHandStartExtension() {
        return rightLowHandStartExtension;
    }

    public void setRightLowHandStartExtension(Double rightLowHandStartExtension) {
        this.rightLowHandStartExtension = rightLowHandStartExtension;
    }

    public Double getRightLowHandEndExtension() {
        return rightLowHandEndExtension;
    }

    public void setRightLowHandEndExtension(Double rightLowHandEndExtension) {
        this.rightLowHandEndExtension = rightLowHandEndExtension;
    }

    public Integer getHasMiddleHandrail() {
        return hasMiddleHandrail;
    }

    public void setHasMiddleHandrail(Integer hasMiddleHandrail) {
        this.hasMiddleHandrail = hasMiddleHandrail;
    }

    public Double getMiddleUpHandHeight() {
        return middleUpHandHeight;
    }

    public void setMiddleUpHandHeight(Double middleUpHandHeight) {
        this.middleUpHandHeight = middleUpHandHeight;
    }

    public Double getMiddleLowHandHeight() {
        return middleLowHandHeight;
    }

    public void setMiddleLowHandHeight(Double middleLowHandHeight) {
        this.middleLowHandHeight = middleLowHandHeight;
    }

    public Double getMiddleUpHandStartExtension() {
        return middleUpHandStartExtension;
    }

    public void setMiddleUpHandStartExtension(Double middleUpHandStartExtension) {
        this.middleUpHandStartExtension = middleUpHandStartExtension;
    }

    public Double getMiddleUpHandEndExtension() {
        return middleUpHandEndExtension;
    }

    public void setMiddleUpHandEndExtension(Double middleUpHandEndExtension) {
        this.middleUpHandEndExtension = middleUpHandEndExtension;
    }

    public Double getMiddleLowHandStartExtension() {
        return middleLowHandStartExtension;
    }

    public void setMiddleLowHandStartExtension(Double middleLowHandStartExtension) {
        this.middleLowHandStartExtension = middleLowHandStartExtension;
    }

    public Double getMiddleLowHandEndExtension() {
        return middleLowHandEndExtension;
    }

    public void setMiddleLowHandEndExtension(Double middleLowHandEndExtension) {
        this.middleLowHandEndExtension = middleLowHandEndExtension;
    }

    public String getStepObs() {
        return stepObs;
    }

    public void setStepObs(String stepObs) {
        this.stepObs = stepObs;
    }

    public String getStepPhoto() {
        return stepPhoto;
    }

    public void setStepPhoto(String stepPhoto) {
        this.stepPhoto = stepPhoto;
    }
}
