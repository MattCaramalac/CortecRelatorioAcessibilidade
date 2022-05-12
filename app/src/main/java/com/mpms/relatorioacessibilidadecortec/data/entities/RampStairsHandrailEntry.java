package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = RampStairsFlightEntry.class, parentColumns = "flightID", childColumns = "flightID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class RampStairsHandrailEntry {

    @PrimaryKey(autoGenerate = true)
    private int handrailID;
    private int flightID;
    private int handrailPlacement;
    private double handrailHeight;
    private double handrailGrip;
    private String handrailObs;
    private int hasInitExtension;
    private Double initExtLength;
    private int hasFinalExtension;
    private Double finalExtLength;
    private String extensionObs;

    public RampStairsHandrailEntry(int flightID, int handrailPlacement, double handrailHeight, double handrailGrip, String handrailObs,
                                   int hasInitExtension, Double initExtLength, int hasFinalExtension, Double finalExtLength, String extensionObs) {
        this.flightID = flightID;
        this.handrailPlacement = handrailPlacement;
        this.handrailHeight = handrailHeight;
        this.handrailGrip = handrailGrip;
        this.handrailObs = handrailObs;
        this.hasInitExtension = hasInitExtension;
        this.initExtLength = initExtLength;
        this.hasFinalExtension = hasFinalExtension;
        this.finalExtLength = finalExtLength;
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

    public int getHandrailPlacement() {
        return handrailPlacement;
    }

    public void setHandrailPlacement(int handrailPlacement) {
        this.handrailPlacement = handrailPlacement;
    }

    public double getHandrailHeight() {
        return handrailHeight;
    }

    public void setHandrailHeight(double handrailHeight) {
        this.handrailHeight = handrailHeight;
    }

    public double getHandrailGrip() {
        return handrailGrip;
    }

    public void setHandrailGrip(double handrailGrip) {
        this.handrailGrip = handrailGrip;
    }

    public String getHandrailObs() {
        return handrailObs;
    }

    public void setHandrailObs(String handrailObs) {
        this.handrailObs = handrailObs;
    }

    public int getHasInitExtension() {
        return hasInitExtension;
    }

    public void setHasInitExtension(int hasInitExtension) {
        this.hasInitExtension = hasInitExtension;
    }

    public Double getInitExtLength() {
        return initExtLength;
    }

    public void setInitExtLength(Double initExtLength) {
        this.initExtLength = initExtLength;
    }

    public int getHasFinalExtension() {
        return hasFinalExtension;
    }

    public void setHasFinalExtension(int hasFinalExtension) {
        this.hasFinalExtension = hasFinalExtension;
    }

    public Double getFinalExtLength() {
        return finalExtLength;
    }

    public void setFinalExtLength(Double finalExtLength) {
        this.finalExtLength = finalExtLength;
    }

    public String getExtensionObs() {
        return extensionObs;
    }

    public void setExtensionObs(String extensionObs) {
        this.extensionObs = extensionObs;
    }
}
