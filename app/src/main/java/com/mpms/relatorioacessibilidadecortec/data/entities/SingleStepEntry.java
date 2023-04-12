package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID",
        onDelete = CASCADE, onUpdate = CASCADE), @ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID",
        onDelete = CASCADE, onUpdate = CASCADE)})
public class SingleStepEntry {

    @PrimaryKey(autoGenerate = true)
    int stepID;
    Integer circID;
    Integer roomID;
    String stepLocation;
    int stepQnt;
    double firstMirror;
    Double stepLength;
    Double secondMirror;
    Double stepWidth;

    int hasLeftHand;
    Double leftHandUpHeight;
    Double leftHandDownHeight;
    Double leftHandLength;
    Double leftHandDiam;
    Double leftHandDist;
    Integer leftHasLowerExt;
    Double leftLowerUpLength;
    Double leftLowerDownLength;
    Integer leftHasUpperExt;
    Double leftUpperUpLength;
    Double leftUpperDownLength;

    Integer hasRightHand;
    Double rightHandUpHeight;
    Double rightHandDownHeight;
    Double rightHandLength;
    Double rightHandDiam;
    Double rightHandDist;
    Integer rightHasLowerExt;
    Double rightLowerUpLength;
    Double rightLowerDownLength;
    Integer rightHasUpperExt;
    Double rightUpperUpLength;
    Double rightUpperDownLength;

    Integer hasMiddleHand;
    Double middleHandUpHeight;
    Double middleHandDownHeight;
    Double middleHandLength;
    Double middleHandDiam;
    Integer middleHasLowerExt;
    Double middleLowerUpLength;
    Double middleLowerDownLength;
    Integer middleHasUpperExt;
    Double middleUpperUpLength;
    Double middleUpperDownLength;

    int stepHasSign;
    Double stepSignWidth;
    Integer stepSignFullApp;
    Integer stepSignMirrorStep;

    int stepHasTactSign;
    Integer hasLowTact;
    Double lowTactDist;
    Double lowTactWidth;
    Integer lowTactAntiDrift;
    Integer lowTactSoilContrast;
    Integer lowTactVisualContrast;
    Integer hasHighTact;
    Double highTactDist;
    Double highTactWidth;
    Integer highTactAntiDrift;
    Integer highTactSoilContrast;
    Integer highTactVisualContrast;

    String stepObs;
    String stepPhoto;

    public SingleStepEntry(Integer circID, Integer roomID, String stepLocation, int stepQnt, double firstMirror, Double stepLength, Double secondMirror, Double stepWidth, int hasLeftHand,
                           Double leftHandUpHeight, Double leftHandDownHeight, Double leftHandLength, Double leftHandDiam, Double leftHandDist, Integer leftHasLowerExt,
                           Double leftLowerUpLength, Double leftLowerDownLength, Integer leftHasUpperExt, Double leftUpperUpLength, Double leftUpperDownLength, Integer hasRightHand,
                           Double rightHandUpHeight, Double rightHandDownHeight, Double rightHandLength, Double rightHandDiam, Double rightHandDist, Integer rightHasLowerExt,
                           Double rightLowerUpLength, Double rightLowerDownLength, Integer rightHasUpperExt, Double rightUpperUpLength, Double rightUpperDownLength,
                           Integer hasMiddleHand, Double middleHandUpHeight, Double middleHandDownHeight, Double middleHandLength, Double middleHandDiam, Integer middleHasLowerExt,
                           Double middleLowerUpLength, Double middleLowerDownLength, Integer middleHasUpperExt, Double middleUpperUpLength, Double middleUpperDownLength,
                           int stepHasSign, Double stepSignWidth, Integer stepSignFullApp, Integer stepSignMirrorStep, int stepHasTactSign, Integer hasLowTact, Double lowTactDist,
                           Double lowTactWidth, Integer lowTactAntiDrift, Integer lowTactSoilContrast, Integer lowTactVisualContrast, Integer hasHighTact, Double highTactDist,
                           Double highTactWidth, Integer highTactAntiDrift, Integer highTactSoilContrast, Integer highTactVisualContrast, String stepObs, String stepPhoto) {
        this.circID = circID;
        this.roomID = roomID;
        this.stepLocation = stepLocation;
        this.stepQnt = stepQnt;
        this.firstMirror = firstMirror;
        this.stepLength = stepLength;
        this.secondMirror = secondMirror;
        this.stepWidth = stepWidth;
        this.hasLeftHand = hasLeftHand;
        this.leftHandUpHeight = leftHandUpHeight;
        this.leftHandDownHeight = leftHandDownHeight;
        this.leftHandLength = leftHandLength;
        this.leftHandDiam = leftHandDiam;
        this.leftHandDist = leftHandDist;
        this.leftHasLowerExt = leftHasLowerExt;
        this.leftLowerUpLength = leftLowerUpLength;
        this.leftLowerDownLength = leftLowerDownLength;
        this.leftHasUpperExt = leftHasUpperExt;
        this.leftUpperUpLength = leftUpperUpLength;
        this.leftUpperDownLength = leftUpperDownLength;
        this.hasRightHand = hasRightHand;
        this.rightHandUpHeight = rightHandUpHeight;
        this.rightHandDownHeight = rightHandDownHeight;
        this.rightHandLength = rightHandLength;
        this.rightHandDiam = rightHandDiam;
        this.rightHandDist = rightHandDist;
        this.rightHasLowerExt = rightHasLowerExt;
        this.rightLowerUpLength = rightLowerUpLength;
        this.rightLowerDownLength = rightLowerDownLength;
        this.rightHasUpperExt = rightHasUpperExt;
        this.rightUpperUpLength = rightUpperUpLength;
        this.rightUpperDownLength = rightUpperDownLength;
        this.hasMiddleHand = hasMiddleHand;
        this.middleHandUpHeight = middleHandUpHeight;
        this.middleHandDownHeight = middleHandDownHeight;
        this.middleHandLength = middleHandLength;
        this.middleHandDiam = middleHandDiam;
        this.middleHasLowerExt = middleHasLowerExt;
        this.middleLowerUpLength = middleLowerUpLength;
        this.middleLowerDownLength = middleLowerDownLength;
        this.middleHasUpperExt = middleHasUpperExt;
        this.middleUpperUpLength = middleUpperUpLength;
        this.middleUpperDownLength = middleUpperDownLength;
        this.stepHasSign = stepHasSign;
        this.stepSignWidth = stepSignWidth;
        this.stepSignFullApp = stepSignFullApp;
        this.stepSignMirrorStep = stepSignMirrorStep;
        this.stepHasTactSign = stepHasTactSign;
        this.hasLowTact = hasLowTact;
        this.lowTactDist = lowTactDist;
        this.lowTactWidth = lowTactWidth;
        this.lowTactAntiDrift = lowTactAntiDrift;
        this.lowTactSoilContrast = lowTactSoilContrast;
        this.lowTactVisualContrast = lowTactVisualContrast;
        this.hasHighTact = hasHighTact;
        this.highTactDist = highTactDist;
        this.highTactWidth = highTactWidth;
        this.highTactAntiDrift = highTactAntiDrift;
        this.highTactSoilContrast = highTactSoilContrast;
        this.highTactVisualContrast = highTactVisualContrast;
        this.stepObs = stepObs;
        this.stepPhoto = stepPhoto;
    }

    public int getStepID() {
        return stepID;
    }

    public void setStepID(int stepID) {
        this.stepID = stepID;
    }

    public Integer getCircID() {
        return circID;
    }

    public void setCircID(Integer circID) {
        this.circID = circID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getStepLocation() {
        return stepLocation;
    }

    public void setStepLocation(String stepLocation) {
        this.stepLocation = stepLocation;
    }

    public int getStepQnt() {
        return stepQnt;
    }

    public void setStepQnt(int stepQnt) {
        this.stepQnt = stepQnt;
    }

    public double getFirstMirror() {
        return firstMirror;
    }

    public void setFirstMirror(double firstMirror) {
        this.firstMirror = firstMirror;
    }

    public Double getStepLength() {
        return stepLength;
    }

    public void setStepLength(Double stepLength) {
        this.stepLength = stepLength;
    }

    public Double getSecondMirror() {
        return secondMirror;
    }

    public void setSecondMirror(Double secondMirror) {
        this.secondMirror = secondMirror;
    }

    public Double getStepWidth() {
        return stepWidth;
    }

    public void setStepWidth(Double stepWidth) {
        this.stepWidth = stepWidth;
    }

    public int getHasLeftHand() {
        return hasLeftHand;
    }

    public void setHasLeftHand(int hasLeftHand) {
        this.hasLeftHand = hasLeftHand;
    }

    public Double getLeftHandUpHeight() {
        return leftHandUpHeight;
    }

    public void setLeftHandUpHeight(Double leftHandUpHeight) {
        this.leftHandUpHeight = leftHandUpHeight;
    }

    public Double getLeftHandDownHeight() {
        return leftHandDownHeight;
    }

    public void setLeftHandDownHeight(Double leftHandDownHeight) {
        this.leftHandDownHeight = leftHandDownHeight;
    }

    public Double getLeftHandLength() {
        return leftHandLength;
    }

    public void setLeftHandLength(Double leftHandLength) {
        this.leftHandLength = leftHandLength;
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

    public Integer getLeftHasLowerExt() {
        return leftHasLowerExt;
    }

    public void setLeftHasLowerExt(Integer leftHasLowerExt) {
        this.leftHasLowerExt = leftHasLowerExt;
    }

    public Double getLeftLowerUpLength() {
        return leftLowerUpLength;
    }

    public void setLeftLowerUpLength(Double leftLowerUpLength) {
        this.leftLowerUpLength = leftLowerUpLength;
    }

    public Double getLeftLowerDownLength() {
        return leftLowerDownLength;
    }

    public void setLeftLowerDownLength(Double leftLowerDownLength) {
        this.leftLowerDownLength = leftLowerDownLength;
    }

    public Integer getLeftHasUpperExt() {
        return leftHasUpperExt;
    }

    public void setLeftHasUpperExt(Integer leftHasUpperExt) {
        this.leftHasUpperExt = leftHasUpperExt;
    }

    public Double getLeftUpperUpLength() {
        return leftUpperUpLength;
    }

    public void setLeftUpperUpLength(Double leftUpperUpLength) {
        this.leftUpperUpLength = leftUpperUpLength;
    }

    public Double getLeftUpperDownLength() {
        return leftUpperDownLength;
    }

    public void setLeftUpperDownLength(Double leftUpperDownLength) {
        this.leftUpperDownLength = leftUpperDownLength;
    }

    public Integer getHasRightHand() {
        return hasRightHand;
    }

    public void setHasRightHand(Integer hasRightHand) {
        this.hasRightHand = hasRightHand;
    }

    public Double getRightHandUpHeight() {
        return rightHandUpHeight;
    }

    public void setRightHandUpHeight(Double rightHandUpHeight) {
        this.rightHandUpHeight = rightHandUpHeight;
    }

    public Double getRightHandDownHeight() {
        return rightHandDownHeight;
    }

    public void setRightHandDownHeight(Double rightHandDownHeight) {
        this.rightHandDownHeight = rightHandDownHeight;
    }

    public Double getRightHandLength() {
        return rightHandLength;
    }

    public void setRightHandLength(Double rightHandLength) {
        this.rightHandLength = rightHandLength;
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

    public Integer getRightHasLowerExt() {
        return rightHasLowerExt;
    }

    public void setRightHasLowerExt(Integer rightHasLowerExt) {
        this.rightHasLowerExt = rightHasLowerExt;
    }

    public Double getRightLowerUpLength() {
        return rightLowerUpLength;
    }

    public void setRightLowerUpLength(Double rightLowerUpLength) {
        this.rightLowerUpLength = rightLowerUpLength;
    }

    public Double getRightLowerDownLength() {
        return rightLowerDownLength;
    }

    public void setRightLowerDownLength(Double rightLowerDownLength) {
        this.rightLowerDownLength = rightLowerDownLength;
    }

    public Integer getRightHasUpperExt() {
        return rightHasUpperExt;
    }

    public void setRightHasUpperExt(Integer rightHasUpperExt) {
        this.rightHasUpperExt = rightHasUpperExt;
    }

    public Double getRightUpperUpLength() {
        return rightUpperUpLength;
    }

    public void setRightUpperUpLength(Double rightUpperUpLength) {
        this.rightUpperUpLength = rightUpperUpLength;
    }

    public Double getRightUpperDownLength() {
        return rightUpperDownLength;
    }

    public void setRightUpperDownLength(Double rightUpperDownLength) {
        this.rightUpperDownLength = rightUpperDownLength;
    }

    public Integer getHasMiddleHand() {
        return hasMiddleHand;
    }

    public void setHasMiddleHand(Integer hasMiddleHand) {
        this.hasMiddleHand = hasMiddleHand;
    }

    public Double getMiddleHandUpHeight() {
        return middleHandUpHeight;
    }

    public void setMiddleHandUpHeight(Double middleHandUpHeight) {
        this.middleHandUpHeight = middleHandUpHeight;
    }

    public Double getMiddleHandDownHeight() {
        return middleHandDownHeight;
    }

    public void setMiddleHandDownHeight(Double middleHandDownHeight) {
        this.middleHandDownHeight = middleHandDownHeight;
    }

    public Double getMiddleHandLength() {
        return middleHandLength;
    }

    public void setMiddleHandLength(Double middleHandLength) {
        this.middleHandLength = middleHandLength;
    }

    public Double getMiddleHandDiam() {
        return middleHandDiam;
    }

    public void setMiddleHandDiam(Double middleHandDiam) {
        this.middleHandDiam = middleHandDiam;
    }

    public Integer getMiddleHasLowerExt() {
        return middleHasLowerExt;
    }

    public void setMiddleHasLowerExt(Integer middleHasLowerExt) {
        this.middleHasLowerExt = middleHasLowerExt;
    }

    public Double getMiddleLowerUpLength() {
        return middleLowerUpLength;
    }

    public void setMiddleLowerUpLength(Double middleLowerUpLength) {
        this.middleLowerUpLength = middleLowerUpLength;
    }

    public Double getMiddleLowerDownLength() {
        return middleLowerDownLength;
    }

    public void setMiddleLowerDownLength(Double middleLowerDownLength) {
        this.middleLowerDownLength = middleLowerDownLength;
    }

    public Integer getMiddleHasUpperExt() {
        return middleHasUpperExt;
    }

    public void setMiddleHasUpperExt(Integer middleHasUpperExt) {
        this.middleHasUpperExt = middleHasUpperExt;
    }

    public Double getMiddleUpperUpLength() {
        return middleUpperUpLength;
    }

    public void setMiddleUpperUpLength(Double middleUpperUpLength) {
        this.middleUpperUpLength = middleUpperUpLength;
    }

    public Double getMiddleUpperDownLength() {
        return middleUpperDownLength;
    }

    public void setMiddleUpperDownLength(Double middleUpperDownLength) {
        this.middleUpperDownLength = middleUpperDownLength;
    }

    public int getStepHasSign() {
        return stepHasSign;
    }

    public void setStepHasSign(int stepHasSign) {
        this.stepHasSign = stepHasSign;
    }

    public Double getStepSignWidth() {
        return stepSignWidth;
    }

    public void setStepSignWidth(Double stepSignWidth) {
        this.stepSignWidth = stepSignWidth;
    }

    public Integer getStepSignFullApp() {
        return stepSignFullApp;
    }

    public void setStepSignFullApp(Integer stepSignFullApp) {
        this.stepSignFullApp = stepSignFullApp;
    }

    public Integer getStepSignMirrorStep() {
        return stepSignMirrorStep;
    }

    public void setStepSignMirrorStep(Integer stepSignMirrorStep) {
        this.stepSignMirrorStep = stepSignMirrorStep;
    }

    public int getStepHasTactSign() {
        return stepHasTactSign;
    }

    public void setStepHasTactSign(int stepHasTactSign) {
        this.stepHasTactSign = stepHasTactSign;
    }

    public Integer getHasLowTact() {
        return hasLowTact;
    }

    public void setHasLowTact(Integer hasLowTact) {
        this.hasLowTact = hasLowTact;
    }

    public Double getLowTactDist() {
        return lowTactDist;
    }

    public void setLowTactDist(Double lowTactDist) {
        this.lowTactDist = lowTactDist;
    }

    public Double getLowTactWidth() {
        return lowTactWidth;
    }

    public void setLowTactWidth(Double lowTactWidth) {
        this.lowTactWidth = lowTactWidth;
    }

    public Integer getLowTactAntiDrift() {
        return lowTactAntiDrift;
    }

    public void setLowTactAntiDrift(Integer lowTactAntiDrift) {
        this.lowTactAntiDrift = lowTactAntiDrift;
    }

    public Integer getLowTactSoilContrast() {
        return lowTactSoilContrast;
    }

    public void setLowTactSoilContrast(Integer lowTactSoilContrast) {
        this.lowTactSoilContrast = lowTactSoilContrast;
    }

    public Integer getLowTactVisualContrast() {
        return lowTactVisualContrast;
    }

    public void setLowTactVisualContrast(Integer lowTactVisualContrast) {
        this.lowTactVisualContrast = lowTactVisualContrast;
    }

    public Integer getHasHighTact() {
        return hasHighTact;
    }

    public void setHasHighTact(Integer hasHighTact) {
        this.hasHighTact = hasHighTact;
    }

    public Double getHighTactDist() {
        return highTactDist;
    }

    public void setHighTactDist(Double highTactDist) {
        this.highTactDist = highTactDist;
    }

    public Double getHighTactWidth() {
        return highTactWidth;
    }

    public void setHighTactWidth(Double highTactWidth) {
        this.highTactWidth = highTactWidth;
    }

    public Integer getHighTactAntiDrift() {
        return highTactAntiDrift;
    }

    public void setHighTactAntiDrift(Integer highTactAntiDrift) {
        this.highTactAntiDrift = highTactAntiDrift;
    }

    public Integer getHighTactSoilContrast() {
        return highTactSoilContrast;
    }

    public void setHighTactSoilContrast(Integer highTactSoilContrast) {
        this.highTactSoilContrast = highTactSoilContrast;
    }

    public Integer getHighTactVisualContrast() {
        return highTactVisualContrast;
    }

    public void setHighTactVisualContrast(Integer highTactVisualContrast) {
        this.highTactVisualContrast = highTactVisualContrast;
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
