package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class InclinationParcel {

    Double inclHeight;
    Integer hasInclSlope;
    Integer inclQnt;
    Double inclMeasure1;
    Double inclMeasure2;
    Double inclMeasure3;
    Double inclMeasure4;

    public InclinationParcel() {
//        Empty Constructor
    }

    public InclinationParcel(Double inclHeight, Integer hasInclSlope, Integer inclQnt, Double inclMeasure1, Double inclMeasure2, Double inclMeasure3, Double inclMeasure4) {
        this.inclHeight = inclHeight;
        this.hasInclSlope = hasInclSlope;
        this.inclQnt = inclQnt;
        this.inclMeasure1 = inclMeasure1;
        this.inclMeasure2 = inclMeasure2;
        this.inclMeasure3 = inclMeasure3;
        this.inclMeasure4 = inclMeasure4;
    }

    public Double getInclHeight() {
        return inclHeight;
    }

    public void setInclHeight(Double inclHeight) {
        this.inclHeight = inclHeight;
    }

    public Integer getHasInclSlope() {
        return hasInclSlope;
    }

    public void setHasInclSlope(Integer hasInclSlope) {
        this.hasInclSlope = hasInclSlope;
    }

    public Integer getInclQnt() {
        return inclQnt;
    }

    public void setInclQnt(Integer inclQnt) {
        this.inclQnt = inclQnt;
    }

    public Double getInclMeasure1() {
        return inclMeasure1;
    }

    public void setInclMeasure1(Double inclMeasure1) {
        this.inclMeasure1 = inclMeasure1;
    }

    public Double getInclMeasure2() {
        return inclMeasure2;
    }

    public void setInclMeasure2(Double inclMeasure2) {
        this.inclMeasure2 = inclMeasure2;
    }

    public Double getInclMeasure3() {
        return inclMeasure3;
    }

    public void setInclMeasure3(Double inclMeasure3) {
        this.inclMeasure3 = inclMeasure3;
    }

    public Double getInclMeasure4() {
        return inclMeasure4;
    }

    public void setInclMeasure4(Double inclMeasure4) {
        this.inclMeasure4 = inclMeasure4;
    }
}
