package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class FallProtectParcel {

    Double heightLength;
    Integer visualContrast;
    Integer tactileContrast;
    String protectObs;

    public FallProtectParcel() {
//        Empty Contructor
    }

    public FallProtectParcel(Double heightLength, Integer visualContrast, Integer tactileContrast, String protectObs) {
        this.heightLength = heightLength;
        this.visualContrast = visualContrast;
        this.tactileContrast = tactileContrast;
        this.protectObs = protectObs;
    }

    public Double getHeightLength() {
        return heightLength;
    }

    public void setHeightLength(Double heightLength) {
        this.heightLength = heightLength;
    }

    public Integer getVisualContrast() {
        return visualContrast;
    }

    public void setVisualContrast(Integer visualContrast) {
        this.visualContrast = visualContrast;
    }

    public Integer getTactileContrast() {
        return tactileContrast;
    }

    public void setTactileContrast(Integer tactileContrast) {
        this.tactileContrast = tactileContrast;
    }

    public String getProtectObs() {
        return protectObs;
    }

    public void setProtectObs(String protectObs) {
        this.protectObs = protectObs;
    }
}
