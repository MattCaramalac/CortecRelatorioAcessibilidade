package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class PlaygroundEntry {

    @PrimaryKey(autoGenerate = true)
    private int playID;
    private int blockID;

    private String playLocation;
    private String playFloorType;
    private Double playGateWidth;
    private Integer playGateHasFloorTrack;
    private Double playFloorTrackHeight;
    private Integer playFloorTrackHasRamp;
    private Integer rampMeasureQnt;
    private Double rampMeasure1;
    private Double rampMeasure2;
    private Double rampMeasure3;
    private Double rampMeasure4;
    private Integer hasFloorGap;
    private Integer floorGapQnt;
    private Double floorGap1;
    private Double floorGap2;
    private Double floorGap3;
    private Double floorGap4;
    private Integer playGateSillType;
    private Double inclinationSillHeight;
    private Double stepSillHeight;
    private Integer slopeMeasureQnt;
    private Double slopeSillAngle1;
    private Double slopeSillAngle2;
    private Double slopeSillAngle3;
    private Double slopeSillAngle4;
    private Double slopeSillWidth;
    private Double slopeSillHeight;
    private String sillObs;
    private Integer accessibleFloor;
    private String accessibleFloorObs;
    private Integer accessibleEquip;
    private String accessibleEquipObs;
    private String playgroundObs;

    public PlaygroundEntry(int blockID, String playLocation, String playFloorType, Double playGateWidth, Integer playGateHasFloorTrack, Double playFloorTrackHeight,
                           Integer playFloorTrackHasRamp, Integer rampMeasureQnt, Double rampMeasure1, Double rampMeasure2, Double rampMeasure3, Double rampMeasure4,
                           Integer hasFloorGap, Integer floorGapQnt, Double floorGap1, Double floorGap2, Double floorGap3, Double floorGap4, Integer playGateSillType,
                           Double inclinationSillHeight, Double stepSillHeight, Integer slopeMeasureQnt, Double slopeSillAngle1, Double slopeSillAngle2, Double slopeSillAngle3,
                           Double slopeSillAngle4, Double slopeSillWidth, Double slopeSillHeight, String sillObs, Integer accessibleFloor, String accessibleFloorObs,
                           Integer accessibleEquip, String accessibleEquipObs, String playgroundObs) {
        this.blockID = blockID;
        this.playLocation = playLocation;
        this.playFloorType = playFloorType;
        this.playGateWidth = playGateWidth;
        this.playGateHasFloorTrack = playGateHasFloorTrack;
        this.playFloorTrackHeight = playFloorTrackHeight;
        this.playFloorTrackHasRamp = playFloorTrackHasRamp;
        this.rampMeasureQnt = rampMeasureQnt;
        this.rampMeasure1 = rampMeasure1;
        this.rampMeasure2 = rampMeasure2;
        this.rampMeasure3 = rampMeasure3;
        this.rampMeasure4 = rampMeasure4;
        this.hasFloorGap = hasFloorGap;
        this.floorGapQnt = floorGapQnt;
        this.floorGap1 = floorGap1;
        this.floorGap2 = floorGap2;
        this.floorGap3 = floorGap3;
        this.floorGap4 = floorGap4;
        this.playGateSillType = playGateSillType;
        this.inclinationSillHeight = inclinationSillHeight;
        this.stepSillHeight = stepSillHeight;
        this.slopeMeasureQnt = slopeMeasureQnt;
        this.slopeSillAngle1 = slopeSillAngle1;
        this.slopeSillAngle2 = slopeSillAngle2;
        this.slopeSillAngle3 = slopeSillAngle3;
        this.slopeSillAngle4 = slopeSillAngle4;
        this.slopeSillWidth = slopeSillWidth;
        this.slopeSillHeight = slopeSillHeight;
        this.sillObs = sillObs;
        this.accessibleFloor = accessibleFloor;
        this.accessibleFloorObs = accessibleFloorObs;
        this.accessibleEquip = accessibleEquip;
        this.accessibleEquipObs = accessibleEquipObs;
        this.playgroundObs = playgroundObs;
    }

    public int getPlayID() {
        return playID;
    }

    public void setPlayID(int playID) {
        this.playID = playID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public String getPlayLocation() {
        return playLocation;
    }

    public void setPlayLocation(String playLocation) {
        this.playLocation = playLocation;
    }

    public String getPlayFloorType() {
        return playFloorType;
    }

    public void setPlayFloorType(String playFloorType) {
        this.playFloorType = playFloorType;
    }

    public Double getPlayGateWidth() {
        return playGateWidth;
    }

    public void setPlayGateWidth(Double playGateWidth) {
        this.playGateWidth = playGateWidth;
    }

    public Integer getPlayGateHasFloorTrack() {
        return playGateHasFloorTrack;
    }

    public void setPlayGateHasFloorTrack(Integer playGateHasFloorTrack) {
        this.playGateHasFloorTrack = playGateHasFloorTrack;
    }

    public Double getPlayFloorTrackHeight() {
        return playFloorTrackHeight;
    }

    public void setPlayFloorTrackHeight(Double playFloorTrackHeight) {
        this.playFloorTrackHeight = playFloorTrackHeight;
    }

    public Integer getPlayFloorTrackHasRamp() {
        return playFloorTrackHasRamp;
    }

    public void setPlayFloorTrackHasRamp(Integer playFloorTrackHasRamp) {
        this.playFloorTrackHasRamp = playFloorTrackHasRamp;
    }

    public Integer getRampMeasureQnt() {
        return rampMeasureQnt;
    }

    public void setRampMeasureQnt(Integer rampMeasureQnt) {
        this.rampMeasureQnt = rampMeasureQnt;
    }

    public Double getRampMeasure1() {
        return rampMeasure1;
    }

    public void setRampMeasure1(Double rampMeasure1) {
        this.rampMeasure1 = rampMeasure1;
    }

    public Double getRampMeasure2() {
        return rampMeasure2;
    }

    public void setRampMeasure2(Double rampMeasure2) {
        this.rampMeasure2 = rampMeasure2;
    }

    public Double getRampMeasure3() {
        return rampMeasure3;
    }

    public void setRampMeasure3(Double rampMeasure3) {
        this.rampMeasure3 = rampMeasure3;
    }

    public Double getRampMeasure4() {
        return rampMeasure4;
    }

    public void setRampMeasure4(Double rampMeasure4) {
        this.rampMeasure4 = rampMeasure4;
    }

    public Integer getPlayGateSillType() {
        return playGateSillType;
    }

    public void setPlayGateSillType(Integer playGateSillType) {
        this.playGateSillType = playGateSillType;
    }

    public Double getInclinationSillHeight() {
        return inclinationSillHeight;
    }

    public void setInclinationSillHeight(Double inclinationSillHeight) {
        this.inclinationSillHeight = inclinationSillHeight;
    }

    public Double getStepSillHeight() {
        return stepSillHeight;
    }

    public void setStepSillHeight(Double stepSillHeight) {
        this.stepSillHeight = stepSillHeight;
    }

    public Integer getSlopeMeasureQnt() {
        return slopeMeasureQnt;
    }

    public void setSlopeMeasureQnt(Integer slopeMeasureQnt) {
        this.slopeMeasureQnt = slopeMeasureQnt;
    }

    public Double getSlopeSillAngle1() {
        return slopeSillAngle1;
    }

    public void setSlopeSillAngle1(Double slopeSillAngle1) {
        this.slopeSillAngle1 = slopeSillAngle1;
    }

    public Double getSlopeSillAngle2() {
        return slopeSillAngle2;
    }

    public void setSlopeSillAngle2(Double slopeSillAngle2) {
        this.slopeSillAngle2 = slopeSillAngle2;
    }

    public Double getSlopeSillAngle3() {
        return slopeSillAngle3;
    }

    public void setSlopeSillAngle3(Double slopeSillAngle3) {
        this.slopeSillAngle3 = slopeSillAngle3;
    }

    public Double getSlopeSillAngle4() {
        return slopeSillAngle4;
    }

    public void setSlopeSillAngle4(Double slopeSillAngle4) {
        this.slopeSillAngle4 = slopeSillAngle4;
    }

    public Double getSlopeSillWidth() {
        return slopeSillWidth;
    }

    public void setSlopeSillWidth(Double slopeSillWidth) {
        this.slopeSillWidth = slopeSillWidth;
    }

    public Double getSlopeSillHeight() {
        return slopeSillHeight;
    }

    public void setSlopeSillHeight(Double slopeSillHeight) {
        this.slopeSillHeight = slopeSillHeight;
    }

    public String getSillObs() {
        return sillObs;
    }

    public void setSillObs(String sillObs) {
        this.sillObs = sillObs;
    }

    public Integer getAccessibleFloor() {
        return accessibleFloor;
    }

    public void setAccessibleFloor(Integer accessibleFloor) {
        this.accessibleFloor = accessibleFloor;
    }

    public String getAccessibleFloorObs() {
        return accessibleFloorObs;
    }

    public void setAccessibleFloorObs(String accessibleFloorObs) {
        this.accessibleFloorObs = accessibleFloorObs;
    }

    public Integer getAccessibleEquip() {
        return accessibleEquip;
    }

    public void setAccessibleEquip(Integer accessibleEquip) {
        this.accessibleEquip = accessibleEquip;
    }

    public String getAccessibleEquipObs() {
        return accessibleEquipObs;
    }

    public void setAccessibleEquipObs(String accessibleEquipObs) {
        this.accessibleEquipObs = accessibleEquipObs;
    }

    public String getPlaygroundObs() {
        return playgroundObs;
    }

    public void setPlaygroundObs(String playgroundObs) {
        this.playgroundObs = playgroundObs;
    }

    public Integer getHasFloorGap() {
        return hasFloorGap;
    }

    public void setHasFloorGap(Integer hasFloorGap) {
        this.hasFloorGap = hasFloorGap;
    }

    public Double getFloorGap1() {
        return floorGap1;
    }

    public void setFloorGap1(Double floorGap1) {
        this.floorGap1 = floorGap1;
    }

    public Double getFloorGap2() {
        return floorGap2;
    }

    public void setFloorGap2(Double floorGap2) {
        this.floorGap2 = floorGap2;
    }

    public Double getFloorGap3() {
        return floorGap3;
    }

    public void setFloorGap3(Double floorGap3) {
        this.floorGap3 = floorGap3;
    }

    public Double getFloorGap4() {
        return floorGap4;
    }

    public void setFloorGap4(Double floorGap4) {
        this.floorGap4 = floorGap4;
    }

    public Integer getFloorGapQnt() {
        return floorGapQnt;
    }

    public void setFloorGapQnt(Integer floorGapQnt) {
        this.floorGapQnt = floorGapQnt;
    }
}
