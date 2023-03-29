package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class CirculationEntry {

    @PrimaryKey(autoGenerate = true)
    private int circID;
    private int schoolID;

    private String circLocation;
    private Integer hasVertSing;
    private String vertSignObs;
    private Integer hasLooseCarpet;
    private String looseCarpetObs;
    private Integer accessFloor;
    private String accessFloorObs;

    private Integer hasIntercom;
    private Double intercomHeight;
    private String intercomObs;
    private Integer hasBioClock;
    private Double bioClockHeight;
    private String bioClockObs;

    private Integer hasUnevenFloor;
    private Integer unevenHigher;
    private Double unevenHeight;
    private Integer hasSlope;
    private Integer slopeHigher;
    private Double slopeAngle;
    private Integer hasFallProtect;
    private Integer fallProtectType;
    private Double marginWidth;
    private Integer marginDifferentTexture;
    private Integer marginContrastColor;
    private Double beaconHeight;
    private Integer topBeaconContrast;
    private Double railingHeight;

    private String circPhoto;
    private String circObs;

    public CirculationEntry(int schoolID, String circLocation, Integer hasVertSing, String vertSignObs, Integer hasLooseCarpet, String looseCarpetObs, Integer accessFloor, String accessFloorObs,
                            Integer hasIntercom, Double intercomHeight, String intercomObs, Integer hasBioClock, Double bioClockHeight, String bioClockObs, Integer hasUnevenFloor, Integer unevenHigher,
                            Double unevenHeight, Integer hasSlope, Integer slopeHigher, Double slopeAngle, Integer hasFallProtect, Integer fallProtectType, Double marginWidth, Integer marginDifferentTexture,
                            Integer marginContrastColor, Double beaconHeight, Integer topBeaconContrast, Double railingHeight, String circPhoto, String circObs) {
        this.schoolID = schoolID;
        this.circLocation = circLocation;
        this.hasVertSing = hasVertSing;
        this.vertSignObs = vertSignObs;
        this.hasLooseCarpet = hasLooseCarpet;
        this.looseCarpetObs = looseCarpetObs;
        this.accessFloor = accessFloor;
        this.accessFloorObs = accessFloorObs;
        this.hasIntercom = hasIntercom;
        this.intercomHeight = intercomHeight;
        this.intercomObs = intercomObs;
        this.hasBioClock = hasBioClock;
        this.bioClockHeight = bioClockHeight;
        this.bioClockObs = bioClockObs;
        this.hasUnevenFloor = hasUnevenFloor;
        this.unevenHigher = unevenHigher;
        this.unevenHeight = unevenHeight;
        this.hasSlope = hasSlope;
        this.slopeHigher = slopeHigher;
        this.slopeAngle = slopeAngle;
        this.hasFallProtect = hasFallProtect;
        this.fallProtectType = fallProtectType;
        this.marginWidth = marginWidth;
        this.marginDifferentTexture = marginDifferentTexture;
        this.marginContrastColor = marginContrastColor;
        this.beaconHeight = beaconHeight;
        this.topBeaconContrast = topBeaconContrast;
        this.railingHeight = railingHeight;
        this.circPhoto = circPhoto;
        this.circObs = circObs;
    }

    public int getCircID() {
        return circID;
    }

    public void setCircID(int circID) {
        this.circID = circID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getCircLocation() {
        return circLocation;
    }

    public void setCircLocation(String circLocation) {
        this.circLocation = circLocation;
    }

    public Integer getHasVertSing() {
        return hasVertSing;
    }

    public void setHasVertSing(Integer hasVertSing) {
        this.hasVertSing = hasVertSing;
    }

    public String getVertSignObs() {
        return vertSignObs;
    }

    public void setVertSignObs(String vertSignObs) {
        this.vertSignObs = vertSignObs;
    }

    public Integer getHasLooseCarpet() {
        return hasLooseCarpet;
    }

    public void setHasLooseCarpet(Integer hasLooseCarpet) {
        this.hasLooseCarpet = hasLooseCarpet;
    }

    public String getLooseCarpetObs() {
        return looseCarpetObs;
    }

    public void setLooseCarpetObs(String looseCarpetObs) {
        this.looseCarpetObs = looseCarpetObs;
    }

    public Integer getAccessFloor() {
        return accessFloor;
    }

    public void setAccessFloor(Integer accessFloor) {
        this.accessFloor = accessFloor;
    }

    public String getAccessFloorObs() {
        return accessFloorObs;
    }

    public void setAccessFloorObs(String accessFloorObs) {
        this.accessFloorObs = accessFloorObs;
    }

    public Integer getHasIntercom() {
        return hasIntercom;
    }

    public void setHasIntercom(Integer hasIntercom) {
        this.hasIntercom = hasIntercom;
    }

    public Double getIntercomHeight() {
        return intercomHeight;
    }

    public void setIntercomHeight(Double intercomHeight) {
        this.intercomHeight = intercomHeight;
    }

    public String getIntercomObs() {
        return intercomObs;
    }

    public void setIntercomObs(String intercomObs) {
        this.intercomObs = intercomObs;
    }

    public Integer getHasBioClock() {
        return hasBioClock;
    }

    public void setHasBioClock(Integer hasBioClock) {
        this.hasBioClock = hasBioClock;
    }

    public Double getBioClockHeight() {
        return bioClockHeight;
    }

    public void setBioClockHeight(Double bioClockHeight) {
        this.bioClockHeight = bioClockHeight;
    }

    public String getBioClockObs() {
        return bioClockObs;
    }

    public void setBioClockObs(String bioClockObs) {
        this.bioClockObs = bioClockObs;
    }

    public String getCircPhoto() {
        return circPhoto;
    }

    public void setCircPhoto(String circPhoto) {
        this.circPhoto = circPhoto;
    }

    public String getCircObs() {
        return circObs;
    }

    public void setCircObs(String circObs) {
        this.circObs = circObs;
    }

    public Integer getHasUnevenFloor() {
        return hasUnevenFloor;
    }

    public void setHasUnevenFloor(Integer hasUnevenFloor) {
        this.hasUnevenFloor = hasUnevenFloor;
    }

    public Integer getUnevenHigher() {
        return unevenHigher;
    }

    public void setUnevenHigher(Integer unevenHigher) {
        this.unevenHigher = unevenHigher;
    }

    public Double getUnevenHeight() {
        return unevenHeight;
    }

    public void setUnevenHeight(Double unevenHeight) {
        this.unevenHeight = unevenHeight;
    }

    public Integer getHasSlope() {
        return hasSlope;
    }

    public void setHasSlope(Integer hasSlope) {
        this.hasSlope = hasSlope;
    }

    public Integer getSlopeHigher() {
        return slopeHigher;
    }

    public void setSlopeHigher(Integer slopeHigher) {
        this.slopeHigher = slopeHigher;
    }

    public Double getSlopeAngle() {
        return slopeAngle;
    }

    public void setSlopeAngle(Double slopeAngle) {
        this.slopeAngle = slopeAngle;
    }

    public Integer getHasFallProtect() {
        return hasFallProtect;
    }

    public void setHasFallProtect(Integer hasFallProtect) {
        this.hasFallProtect = hasFallProtect;
    }

    public Integer getFallProtectType() {
        return fallProtectType;
    }

    public void setFallProtectType(Integer fallProtectType) {
        this.fallProtectType = fallProtectType;
    }

    public Double getMarginWidth() {
        return marginWidth;
    }

    public void setMarginWidth(Double marginWidth) {
        this.marginWidth = marginWidth;
    }

    public Integer getMarginDifferentTexture() {
        return marginDifferentTexture;
    }

    public void setMarginDifferentTexture(Integer marginDifferentTexture) {
        this.marginDifferentTexture = marginDifferentTexture;
    }

    public Integer getMarginContrastColor() {
        return marginContrastColor;
    }

    public void setMarginContrastColor(Integer marginContrastColor) {
        this.marginContrastColor = marginContrastColor;
    }

    public Double getBeaconHeight() {
        return beaconHeight;
    }

    public void setBeaconHeight(Double beaconHeight) {
        this.beaconHeight = beaconHeight;
    }

    public Integer getTopBeaconContrast() {
        return topBeaconContrast;
    }

    public void setTopBeaconContrast(Integer topBeaconContrast) {
        this.topBeaconContrast = topBeaconContrast;
    }

    public Double getRailingHeight() {
        return railingHeight;
    }

    public void setRailingHeight(Double railingHeight) {
        this.railingHeight = railingHeight;
    }
}
