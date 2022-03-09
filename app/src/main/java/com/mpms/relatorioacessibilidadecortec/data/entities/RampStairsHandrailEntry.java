package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = FlightsRampStairsEntry.class, parentColumns = "flightID", childColumns = "flightID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class RampStairsHandrailEntry {

    @PrimaryKey(autoGenerate = true)
    private int handrailID;
    private int flightID;
    private int hasHandrail;
    private Integer handrailPlacement;
    private Double handrailHeight;
    private Double handrailGrip;
    private String handrailObs;
    private Integer handrailHasExtension;
    private Double extensionLength;
    private String extensionObs;

    public RampStairsHandrailEntry(int flightID, int hasHandrail, Integer handrailPlacement, Double handrailHeight, Double handrailGrip,
                                   String handrailObs, Integer handrailHasExtension, Double extensionLength, String extensionObs) {
        this.flightID = flightID;
        this.hasHandrail = hasHandrail;
        this.handrailPlacement = handrailPlacement;
        this.handrailHeight = handrailHeight;
        this.handrailGrip = handrailGrip;
        this.handrailObs = handrailObs;
        this.handrailHasExtension = handrailHasExtension;
        this.extensionLength = extensionLength;
        this.extensionObs = extensionObs;
    }

    public int getHandrailID() {
        return handrailID;
    }

    public void setHandrailID(int handrailID) {
        this.handrailID = handrailID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getHasHandrail() {
        return hasHandrail;
    }

    public void setHasHandrail(int hasHandrail) {
        this.hasHandrail = hasHandrail;
    }

    public Integer getHandrailPlacement() {
        return handrailPlacement;
    }

    public void setHandrailPlacement(Integer handrailPlacement) {
        this.handrailPlacement = handrailPlacement;
    }

    public Double getHandrailHeight() {
        return handrailHeight;
    }

    public void setHandrailHeight(Double handrailHeight) {
        this.handrailHeight = handrailHeight;
    }

    public Double getHandrailGrip() {
        return handrailGrip;
    }

    public void setHandrailGrip(Double handrailGrip) {
        this.handrailGrip = handrailGrip;
    }

    public String getHandrailObs() {
        return handrailObs;
    }

    public void setHandrailObs(String handrailObs) {
        this.handrailObs = handrailObs;
    }

    public Integer getHandrailHasExtension() {
        return handrailHasExtension;
    }

    public void setHandrailHasExtension(Integer handrailHasExtension) {
        this.handrailHasExtension = handrailHasExtension;
    }

    public Double getExtensionLength() {
        return extensionLength;
    }

    public void setExtensionLength(Double extensionLength) {
        this.extensionLength = extensionLength;
    }

    public String getExtensionObs() {
        return extensionObs;
    }

    public void setExtensionObs(String extensionObs) {
        this.extensionObs = extensionObs;
    }
}
