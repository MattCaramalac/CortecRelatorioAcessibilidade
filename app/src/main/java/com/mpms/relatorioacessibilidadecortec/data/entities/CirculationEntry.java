package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolID", onDelete = CASCADE, onUpdate = CASCADE))
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

    private String circPhoto;
    private String circObs;

    public CirculationEntry(int schoolID, String circLocation, Integer hasVertSign, String vertSignObs, Integer hasLooseCarpet, String looseCarpetObs, Integer accessFloor, String accessFloorObs,
                            Integer hasIntercom, Double intercomHeight, String intercomObs, Integer hasBioClock, Double bioClockHeight, String bioClockObs, String circPhoto, String circObs) {
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

}
