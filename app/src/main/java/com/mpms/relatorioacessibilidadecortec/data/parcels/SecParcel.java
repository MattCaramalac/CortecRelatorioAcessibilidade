package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class SecParcel {

    Integer hasFixedSeats;
    Integer hasPCRSpace;
    Double pcrSpaceWidth;
    Double pcrSpaceDepth;
    String pcrSpaceObs;

    public SecParcel() {
//        Empty Constructor
    }

    public SecParcel(Integer hasFixedSeats, Integer hasPCRSpace, Double pcrSpaceWidth, Double pcrSpaceDepth, String pcrSpaceObs) {
        this.hasFixedSeats = hasFixedSeats;
        this.hasPCRSpace = hasPCRSpace;
        this.pcrSpaceWidth = pcrSpaceWidth;
        this.pcrSpaceDepth = pcrSpaceDepth;
        this.pcrSpaceObs = pcrSpaceObs;
    }

    public Integer getHasFixedSeats() {
        return hasFixedSeats;
    }

    public void setHasFixedSeats(Integer hasFixedSeats) {
        this.hasFixedSeats = hasFixedSeats;
    }

    public Integer getHasPCRSpace() {
        return hasPCRSpace;
    }

    public void setHasPCRSpace(Integer hasPCRSpace) {
        this.hasPCRSpace = hasPCRSpace;
    }

    public Double getPcrSpaceWidth() {
        return pcrSpaceWidth;
    }

    public void setPcrSpaceWidth(Double pcrSpaceWidth) {
        this.pcrSpaceWidth = pcrSpaceWidth;
    }

    public Double getPcrSpaceDepth() {
        return pcrSpaceDepth;
    }

    public void setPcrSpaceDepth(Double pcrSpaceDepth) {
        this.pcrSpaceDepth = pcrSpaceDepth;
    }

    public String getPcrSpaceObs() {
        return pcrSpaceObs;
    }

    public void setPcrSpaceObs(String pcrSpaceObs) {
        this.pcrSpaceObs = pcrSpaceObs;
    }
}
