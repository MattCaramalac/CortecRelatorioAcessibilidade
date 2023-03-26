package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class VehicleExtAccParcel {

    Integer hasSoundSignal;
    String soundObs;

    public VehicleExtAccParcel() {
//        Empty Constructor
    }

    public VehicleExtAccParcel(Integer hasSoundSignal, String soundObs) {
        this.hasSoundSignal = hasSoundSignal;
        this.soundObs = soundObs;
    }

    public Integer getHasSoundSignal() {
        return hasSoundSignal;
    }

    public void setHasSoundSignal(Integer hasSoundSignal) {
        this.hasSoundSignal = hasSoundSignal;
    }

    public String getSoundObs() {
        return soundObs;
    }

    public void setSoundObs(String soundObs) {
        this.soundObs = soundObs;
    }
}
