package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class VehicleExtAccParcel {

    Integer hasSoundSignal;
    String soundObs;
    String photos;

    public VehicleExtAccParcel() {
//        Empty Constructor
    }

    public VehicleExtAccParcel(Integer hasSoundSignal, String soundObs, String photos) {
        this.hasSoundSignal = hasSoundSignal;
        this.soundObs = soundObs;
        this.photos = photos;
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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
