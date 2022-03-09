package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class PlaygroundEntry {

    @PrimaryKey(autoGenerate = true)
    private int playgroundID;
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
    private Integer playGateSillType;
    private Double inclinationSillHeight;
    private Double stepSillHeight;
    private Double slopeSillAngle;
    private Double slopeSillWidth;
    private String sillObs;
    private Integer accessibleFloor;
    private String accessibleFloorObs;
    private Integer accessibleEquip;
    private String accessibleEquipObs;
    private String playgroundObs;

    public PlaygroundEntry(int blockID, String playLocation, String playFloorType, Double playGateWidth, Integer playGateHasFloorTrack, Double playFloorTrackHeight, Integer playFloorTrackHasRamp,
                           Integer rampMeasureQnt, Double rampMeasure1, Double rampMeasure2, Double rampMeasure3, Double rampMeasure4, Integer playGateSillType, Double inclinationSillHeight,
                           Double stepSillHeight, Double slopeSillAngle, Double slopeSillWidth, String sillObs, Integer accessibleFloor, String accessibleFloorObs, Integer accessibleEquip,
                           String accessibleEquipObs, String playgroundObs) {
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
        this.playGateSillType = playGateSillType;
        this.inclinationSillHeight = inclinationSillHeight;
        this.stepSillHeight = stepSillHeight;
        this.slopeSillAngle = slopeSillAngle;
        this.slopeSillWidth = slopeSillWidth;
        this.sillObs = sillObs;
        this.accessibleFloor = accessibleFloor;
        this.accessibleFloorObs = accessibleFloorObs;
        this.accessibleEquip = accessibleEquip;
        this.accessibleEquipObs = accessibleEquipObs;
        this.playgroundObs = playgroundObs;
    }

    public int getPlaygroundID() {
        return playgroundID;
    }

    public void setPlaygroundID(int playgroundID) {
        this.playgroundID = playgroundID;
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

    public Double getSlopeSillAngle() {
        return slopeSillAngle;
    }

    public void setSlopeSillAngle(Double slopeSillAngle) {
        this.slopeSillAngle = slopeSillAngle;
    }

    public Double getSlopeSillWidth() {
        return slopeSillWidth;
    }

    public void setSlopeSillWidth(Double slopeSillWidth) {
        this.slopeSillWidth = slopeSillWidth;
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
}
