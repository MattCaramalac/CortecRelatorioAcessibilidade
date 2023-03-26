package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExtAccessSocialTwo {

    @PrimaryKey
    private int externalAccessID;

    private Integer hasSIA;
    private String obsSIA;
    private Integer entranceGateType;
    private String entranceGateDesc;
    private Double freeSpaceWidth1;
    private Double freeSpaceWidth2;
    private Integer gateHandleType;
    private Double gateHandleHeight;
    private String gateObs;
    private Integer gateHasTracks;
    private Double gateTrackHeight;
    private Integer gateHasTrackRamp;
    private Integer trackRampQuantity;
    private Double trackRampMeasure1;
    private Double trackRampMeasure2;
    private Double trackRampMeasure3;
    private Double trackRampMeasure4;

    private Integer hasFloorGap;
    private Integer gapCounter;
    private Double gapMeasure1;
    private Double gapMeasure2;
    private Double gapMeasure3;
    private Double gapMeasure4;

    private String extAccPhotos2;

    public ExtAccessSocialTwo(int externalAccessID, Integer hasSIA, String obsSIA, Integer entranceGateType, String entranceGateDesc, Double freeSpaceWidth1,
                              Double freeSpaceWidth2, Integer gateHandleType, Double gateHandleHeight, String gateObs, Integer gateHasTracks, Double gateTrackHeight,
                              Integer gateHasTrackRamp, Integer trackRampQuantity, Double trackRampMeasure1, Double trackRampMeasure2, Double trackRampMeasure3,
                              Double trackRampMeasure4, Integer hasFloorGap, Integer gapCounter, Double gapMeasure1, Double gapMeasure2,
                              Double gapMeasure3, Double gapMeasure4, String extAccPhotos2) {
        this.externalAccessID = externalAccessID;
        this.hasSIA = hasSIA;
        this.obsSIA = obsSIA;
        this.entranceGateType = entranceGateType;
        this.entranceGateDesc = entranceGateDesc;
        this.freeSpaceWidth1 = freeSpaceWidth1;
        this.freeSpaceWidth2 = freeSpaceWidth2;
        this.gateHandleType = gateHandleType;
        this.gateHandleHeight = gateHandleHeight;
        this.gateObs = gateObs;
        this.gateHasTracks = gateHasTracks;
        this.gateTrackHeight = gateTrackHeight;
        this.gateHasTrackRamp = gateHasTrackRamp;
        this.trackRampQuantity = trackRampQuantity;
        this.trackRampMeasure1 = trackRampMeasure1;
        this.trackRampMeasure2 = trackRampMeasure2;
        this.trackRampMeasure3 = trackRampMeasure3;
        this.trackRampMeasure4 = trackRampMeasure4;
        this.hasFloorGap = hasFloorGap;
        this.gapCounter = gapCounter;
        this.gapMeasure1 = gapMeasure1;
        this.gapMeasure2 = gapMeasure2;
        this.gapMeasure3 = gapMeasure3;
        this.gapMeasure4 = gapMeasure4;
        this.extAccPhotos2 = extAccPhotos2;
    }

    public int getExternalAccessID() {
        return externalAccessID;
    }

    public void setExternalAccessID(int externalAccessID) {
        this.externalAccessID = externalAccessID;
    }

    public Integer getHasSIA() {
        return hasSIA;
    }

    public void setHasSIA(Integer hasSIA) {
        this.hasSIA = hasSIA;
    }

    public String getObsSIA() {
        return obsSIA;
    }

    public void setObsSIA(String obsSIA) {
        this.obsSIA = obsSIA;
    }

    public Integer getEntranceGateType() {
        return entranceGateType;
    }

    public void setEntranceGateType(Integer entranceGateType) {
        this.entranceGateType = entranceGateType;
    }

    public Double getFreeSpaceWidth1() {
        return freeSpaceWidth1;
    }

    public void setFreeSpaceWidth1(Double freeSpaceWidth1) {
        this.freeSpaceWidth1 = freeSpaceWidth1;
    }

    public Double getFreeSpaceWidth2() {
        return freeSpaceWidth2;
    }

    public void setFreeSpaceWidth2(Double freeSpaceWidth2) {
        this.freeSpaceWidth2 = freeSpaceWidth2;
    }

    public Integer getGateHandleType() {
        return gateHandleType;
    }

    public void setGateHandleType(Integer gateHandleType) {
        this.gateHandleType = gateHandleType;
    }

    public Double getGateHandleHeight() {
        return gateHandleHeight;
    }

    public void setGateHandleHeight(Double gateHandleHeight) {
        this.gateHandleHeight = gateHandleHeight;
    }

    public String getGateObs() {
        return gateObs;
    }

    public void setGateObs(String gateObs) {
        this.gateObs = gateObs;
    }

    public Integer getGateHasTracks() {
        return gateHasTracks;
    }

    public void setGateHasTracks(Integer gateHasTracks) {
        this.gateHasTracks = gateHasTracks;
    }

    public Double getGateTrackHeight() {
        return gateTrackHeight;
    }

    public void setGateTrackHeight(Double gateTrackHeight) {
        this.gateTrackHeight = gateTrackHeight;
    }

    public Integer getGateHasTrackRamp() {
        return gateHasTrackRamp;
    }

    public void setGateHasTrackRamp(Integer gateHasTrackRamp) {
        this.gateHasTrackRamp = gateHasTrackRamp;
    }

    public Integer getTrackRampQuantity() {
        return trackRampQuantity;
    }

    public void setTrackRampQuantity(Integer trackRampQuantity) {
        this.trackRampQuantity = trackRampQuantity;
    }

    public Double getTrackRampMeasure1() {
        return trackRampMeasure1;
    }

    public void setTrackRampMeasure1(Double trackRampMeasure1) {
        this.trackRampMeasure1 = trackRampMeasure1;
    }

    public Double getTrackRampMeasure2() {
        return trackRampMeasure2;
    }

    public void setTrackRampMeasure2(Double trackRampMeasure2) {
        this.trackRampMeasure2 = trackRampMeasure2;
    }

    public Double getTrackRampMeasure3() {
        return trackRampMeasure3;
    }

    public void setTrackRampMeasure3(Double trackRampMeasure3) {
        this.trackRampMeasure3 = trackRampMeasure3;
    }

    public Double getTrackRampMeasure4() {
        return trackRampMeasure4;
    }

    public void setTrackRampMeasure4(Double trackRampMeasure4) {
        this.trackRampMeasure4 = trackRampMeasure4;
    }

    public String getEntranceGateDesc() {
        return entranceGateDesc;
    }

    public void setEntranceGateDesc(String entranceGateDesc) {
        this.entranceGateDesc = entranceGateDesc;
    }

    public Integer getHasFloorGap() {
        return hasFloorGap;
    }

    public void setHasFloorGap(Integer hasFloorGap) {
        this.hasFloorGap = hasFloorGap;
    }

    public Integer getGapCounter() {
        return gapCounter;
    }

    public void setGapCounter(Integer gapCounter) {
        this.gapCounter = gapCounter;
    }

    public Double getGapMeasure1() {
        return gapMeasure1;
    }

    public void setGapMeasure1(Double gapMeasure1) {
        this.gapMeasure1 = gapMeasure1;
    }

    public Double getGapMeasure2() {
        return gapMeasure2;
    }

    public void setGapMeasure2(Double gapMeasure2) {
        this.gapMeasure2 = gapMeasure2;
    }

    public Double getGapMeasure3() {
        return gapMeasure3;
    }

    public void setGapMeasure3(Double gapMeasure3) {
        this.gapMeasure3 = gapMeasure3;
    }

    public Double getGapMeasure4() {
        return gapMeasure4;
    }

    public void setGapMeasure4(Double gapMeasure4) {
        this.gapMeasure4 = gapMeasure4;
    }

    public String getExtAccPhotos2() {
        return extAccPhotos2;
    }

    public void setExtAccPhotos2(String extAccPhotos2) {
        this.extAccPhotos2 = extAccPhotos2;
    }
}
