package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class SingleStepHandParcel {

    Double highHand;
    Double lowHand;
    Double handDiam;
    Double handDist;
    Integer hasLowExtension;
    Double lowUpExtLength;
    Double lowDownExtLength;
    Integer hasHighExtension;
    Double highDownExtLength;
    Double highUpExtLength;


    public SingleStepHandParcel() {
    }

    public SingleStepHandParcel(Double highHand, Double lowHand, Double handDiam, Double handDist, Integer hasLowExtension, Double lowUpExtLength,
                                Double lowDownExtLength, Integer hasHighExtension, Double highDownExtLength, Double highUpExtLength) {
        this.highHand = highHand;
        this.lowHand = lowHand;
        this.handDiam = handDiam;
        this.handDist = handDist;
        this.hasLowExtension = hasLowExtension;
        this.lowUpExtLength = lowUpExtLength;
        this.lowDownExtLength = lowDownExtLength;
        this.hasHighExtension = hasHighExtension;
        this.highDownExtLength = highDownExtLength;
        this.highUpExtLength = highUpExtLength;
    }

    public Double getHighHand() {
        return highHand;
    }

    public void setHighHand(Double highHand) {
        this.highHand = highHand;
    }

    public Double getLowHand() {
        return lowHand;
    }

    public void setLowHand(Double lowHand) {
        this.lowHand = lowHand;
    }

    public Double getHandDiam() {
        return handDiam;
    }

    public void setHandDiam(Double handDiam) {
        this.handDiam = handDiam;
    }

    public Double getHandDist() {
        return handDist;
    }

    public void setHandDist(Double handDist) {
        this.handDist = handDist;
    }

    public Integer getHasLowExtension() {
        return hasLowExtension;
    }

    public void setHasLowExtension(Integer hasLowExtension) {
        this.hasLowExtension = hasLowExtension;
    }

    public Double getLowUpExtLength() {
        return lowUpExtLength;
    }

    public void setLowUpExtLength(Double lowUpExtLength) {
        this.lowUpExtLength = lowUpExtLength;
    }

    public Double getLowDownExtLength() {
        return lowDownExtLength;
    }

    public void setLowDownExtLength(Double lowDownExtLength) {
        this.lowDownExtLength = lowDownExtLength;
    }

    public Integer getHasHighExtension() {
        return hasHighExtension;
    }

    public void setHasHighExtension(Integer hasHighExtension) {
        this.hasHighExtension = hasHighExtension;
    }

    public Double getHighDownExtLength() {
        return highDownExtLength;
    }

    public void setHighDownExtLength(Double highDownExtLength) {
        this.highDownExtLength = highDownExtLength;
    }

    public Double getHighUpExtLength() {
        return highUpExtLength;
    }

    public void setHighUpExtLength(Double highUpExtLength) {
        this.highUpExtLength = highUpExtLength;
    }
}
