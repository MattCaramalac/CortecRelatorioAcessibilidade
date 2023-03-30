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
    private Integer hasVertSign;
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
    private Double protectWidthLength;
    private Integer hasVisualContrast;
    private Integer hasTactileContrast;
    private String fallProtectObs;

    private String circPhoto;
    private String circObs;

    public CirculationEntry(int schoolID, String circLocation, Integer hasVertSign, String vertSignObs, Integer hasLooseCarpet, String looseCarpetObs, Integer accessFloor, String accessFloorObs,
                            Integer hasIntercom, Double intercomHeight, String intercomObs, Integer hasBioClock, Double bioClockHeight, String bioClockObs, Integer hasUnevenFloor, Integer unevenHigher,
                            Double unevenHeight, Integer hasSlope, Integer slopeHigher, Double slopeAngle, Integer hasFallProtect, Integer fallProtectType, Double protectWidthLength, Integer hasVisualContrast,
                            Integer hasTactileContrast, String fallProtectObs, String circPhoto, String circObs) {
        this.schoolID = schoolID;
        this.circLocation = circLocation;
        this.hasVertSign = hasVertSign;
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
        this.protectWidthLength = protectWidthLength;
        this.hasVisualContrast = hasVisualContrast;
        this.hasTactileContrast = hasTactileContrast;
        this.fallProtectObs = fallProtectObs;
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

    public Integer getHasVertSign() {
        return hasVertSign;
    }

    public void setHasVertSign(Integer hasVertSign) {
        this.hasVertSign = hasVertSign;
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

    public Double getProtectWidthLength() {
        return protectWidthLength;
    }

    public void setProtectWidthLength(Double protectWidthLength) {
        this.protectWidthLength = protectWidthLength;
    }

    public Integer getHasVisualContrast() {
        return hasVisualContrast;
    }

    public void setHasVisualContrast(Integer hasVisualContrast) {
        this.hasVisualContrast = hasVisualContrast;
    }

    public Integer getHasTactileContrast() {
        return hasTactileContrast;
    }

    public void setHasTactileContrast(Integer hasTactileContrast) {
        this.hasTactileContrast = hasTactileContrast;
    }

    public String getFallProtectObs() {
        return fallProtectObs;
    }

    public void setFallProtectObs(String fallProtectObs) {
        this.fallProtectObs = fallProtectObs;
    }
}
