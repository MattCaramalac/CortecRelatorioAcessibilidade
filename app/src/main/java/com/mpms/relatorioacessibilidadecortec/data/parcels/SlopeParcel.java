package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class SlopeParcel {

    Integer sillSlopeQnt;
    Double sillSlopeAngle1;
    Double sillSlopeAngle2;
    Double sillSlopeAngle3;
    Double sillSlopeAngle4;
    Double sillSlopeWidth;
    Double sillSlopeHeight;

    public SlopeParcel() {
//        Empty Constructor
    }

    public SlopeParcel(Integer sillSlopeQnt, Double sillSlopeAngle1, Double sillSlopeAngle2, Double sillSlopeAngle3, Double sillSlopeAngle4, Double sillSlopeWidth,
                       Double sillSlopeHeight) {
        this.sillSlopeQnt = sillSlopeQnt;
        this.sillSlopeAngle1 = sillSlopeAngle1;
        this.sillSlopeAngle2 = sillSlopeAngle2;
        this.sillSlopeAngle3 = sillSlopeAngle3;
        this.sillSlopeAngle4 = sillSlopeAngle4;
        this.sillSlopeWidth = sillSlopeWidth;
        this.sillSlopeHeight = sillSlopeHeight;
    }

    public Integer getSillSlopeQnt() {
        return sillSlopeQnt;
    }

    public void setSillSlopeQnt(Integer sillSlopeQnt) {
        this.sillSlopeQnt = sillSlopeQnt;
    }

    public Double getSillSlopeAngle1() {
        return sillSlopeAngle1;
    }

    public void setSillSlopeAngle1(Double sillSlopeAngle1) {
        this.sillSlopeAngle1 = sillSlopeAngle1;
    }

    public Double getSillSlopeAngle2() {
        return sillSlopeAngle2;
    }

    public void setSillSlopeAngle2(Double sillSlopeAngle2) {
        this.sillSlopeAngle2 = sillSlopeAngle2;
    }

    public Double getSillSlopeAngle3() {
        return sillSlopeAngle3;
    }

    public void setSillSlopeAngle3(Double sillSlopeAngle3) {
        this.sillSlopeAngle3 = sillSlopeAngle3;
    }

    public Double getSillSlopeAngle4() {
        return sillSlopeAngle4;
    }

    public void setSillSlopeAngle4(Double sillSlopeAngle4) {
        this.sillSlopeAngle4 = sillSlopeAngle4;
    }

    public Double getSillSlopeWidth() {
        return sillSlopeWidth;
    }

    public void setSillSlopeWidth(Double sillSlopeWidth) {
        this.sillSlopeWidth = sillSlopeWidth;
    }

    public Double getSillSlopeHeight() {
        return sillSlopeHeight;
    }

    public void setSillSlopeHeight(Double sillSlopeHeight) {
        this.sillSlopeHeight = sillSlopeHeight;
    }
}
