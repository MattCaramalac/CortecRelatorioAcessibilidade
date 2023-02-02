package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class StepParcel {

    Double stepHeight;

    public StepParcel() {
//        Empty Constructor
    }

    public StepParcel(Double stepHeight) {
        this.stepHeight = stepHeight;
    }

    public Double getStepHeight() {
        return stepHeight;
    }

    public void setStepHeight(Double stepHeight) {
        this.stepHeight = stepHeight;
    }
}
