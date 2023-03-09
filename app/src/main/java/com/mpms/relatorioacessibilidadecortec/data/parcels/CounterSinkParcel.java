package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class CounterSinkParcel {

    Integer hasLowerSink;
    Double approxMeasureB;
    Double approxMeasureC;
    Integer hasSinkBar;
    String sinkObs;

    public CounterSinkParcel() {

    }

    public CounterSinkParcel(Integer hasLowerSink, Double approxMeasureB, Double approxMeasureC, Integer hasSinkBar, String sinkObs) {
        this.hasLowerSink = hasLowerSink;
        this.approxMeasureB = approxMeasureB;
        this.approxMeasureC = approxMeasureC;
        this.hasSinkBar = hasSinkBar;
        this.sinkObs = sinkObs;
    }

    public Integer getHasLowerSink() {
        return hasLowerSink;
    }

    public void setHasLowerSink(Integer hasLowerSink) {
        this.hasLowerSink = hasLowerSink;
    }

    public Double getApproxMeasureB() {
        return approxMeasureB;
    }

    public void setApproxMeasureB(Double approxMeasureB) {
        this.approxMeasureB = approxMeasureB;
    }

    public Double getApproxMeasureC() {
        return approxMeasureC;
    }

    public void setApproxMeasureC(Double approxMeasureC) {
        this.approxMeasureC = approxMeasureC;
    }

    public Integer getHasSinkBar() {
        return hasSinkBar;
    }

    public void setHasSinkBar(Integer hasSinkBar) {
        this.hasSinkBar = hasSinkBar;
    }

    public String getSinkObs() {
        return sinkObs;
    }

    public void setSinkObs(String sinkObs) {
        this.sinkObs = sinkObs;
    }
}
