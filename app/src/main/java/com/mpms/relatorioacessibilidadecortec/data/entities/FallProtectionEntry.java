package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE))
public class FallProtectionEntry {

    @PrimaryKey(autoGenerate = true)
    private int protectID;
    private Integer circID;

    private String protectLocal;
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

    private String protectPhoto;
    private String protectObs;

    public FallProtectionEntry(Integer circID, String protectLocal, Integer unevenHigher, Double unevenHeight, Integer hasSlope, Integer slopeHigher, Double slopeAngle, Integer hasFallProtect,
                               Integer fallProtectType, Double protectWidthLength, Integer hasVisualContrast, Integer hasTactileContrast, String fallProtectObs,
                               String protectPhoto, String protectObs) {
        this.protectLocal = protectLocal;
        this.circID = circID;
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
        this.protectPhoto = protectPhoto;
        this.protectObs = protectObs;
    }

    public String getProtectLocal() {
        return protectLocal;
    }

    public void setProtectLocal(String protectLocal) {
        this.protectLocal = protectLocal;
    }

    public int getProtectID() {
        return protectID;
    }

    public void setProtectID(int protectID) {
        this.protectID = protectID;
    }

    public Integer getCircID() {
        return circID;
    }

    public void setCircID(Integer circID) {
        this.circID = circID;
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

    public String getProtectPhoto() {
        return protectPhoto;
    }

    public void setProtectPhoto(String protectPhoto) {
        this.protectPhoto = protectPhoto;
    }

    public String getProtectObs() {
        return protectObs;
    }

    public void setProtectObs(String protectObs) {
        this.protectObs = protectObs;
    }
}
