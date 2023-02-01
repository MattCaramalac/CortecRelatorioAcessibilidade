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
    private Integer playHasGate;
    private Double playGateWidth;
    private Integer gateHasFloorTrack;
    private Double floorTrackHeight;
    private Integer floorTrackHasRamp;
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
    private Integer gateSillType;
    private Double inclHeight;
    private Integer inclMeasureQnt;
    private Double inclAngle1;
    private Double inclAngle2;
    private Double inclAngle3;
    private Double inclAngle4;
    private Double stepHeight;
    private Integer slopeMeasureQnt;
    private Double slopeAngle1;
    private Double slopeAngle2;
    private Double slopeAngle3;
    private Double slopeAngle4;
    private Double slopeWidth;
    private Double slopeHeight;
    private String sillObs;
    private Integer accessibleFloor;
    private String accessFloorObs;
    private Integer accessibleEquip;
    private String accessEquipObs;
    private String playgroundObs;
    private String playPhotoNumber;

    public PlaygroundEntry(int blockID, String playLocation, Integer playHasGate, Double playGateWidth, Integer gateHasFloorTrack, Double floorTrackHeight, Integer floorTrackHasRamp, Integer rampMeasureQnt,
                           Double rampMeasure1, Double rampMeasure2, Double rampMeasure3, Double rampMeasure4, Integer hasFloorGap, Integer floorGapQnt, Double floorGap1, Double floorGap2, Double floorGap3,
                           Double floorGap4, Integer gateSillType, Double inclHeight, Integer inclMeasureQnt, Double inclAngle1, Double inclAngle2, Double inclAngle3, Double inclAngle4, Double stepHeight,
                           Integer slopeMeasureQnt, Double slopeAngle1, Double slopeAngle2, Double slopeAngle3, Double slopeAngle4, Double slopeWidth, Double slopeHeight, String sillObs, Integer accessibleFloor,
                           String accessFloorObs, Integer accessibleEquip, String accessEquipObs, String playgroundObs, String playPhotoNumber) {
        this.blockID = blockID;
        this.playLocation = playLocation;
        this.playHasGate = playHasGate;
        this.playGateWidth = playGateWidth;
        this.gateHasFloorTrack = gateHasFloorTrack;
        this.floorTrackHeight = floorTrackHeight;
        this.floorTrackHasRamp = floorTrackHasRamp;
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
        this.gateSillType = gateSillType;
        this.inclHeight = inclHeight;
        this.inclMeasureQnt = inclMeasureQnt;
        this.inclAngle1 = inclAngle1;
        this.inclAngle2 = inclAngle2;
        this.inclAngle3 = inclAngle3;
        this.inclAngle4 = inclAngle4;
        this.stepHeight = stepHeight;
        this.slopeMeasureQnt = slopeMeasureQnt;
        this.slopeAngle1 = slopeAngle1;
        this.slopeAngle2 = slopeAngle2;
        this.slopeAngle3 = slopeAngle3;
        this.slopeAngle4 = slopeAngle4;
        this.slopeWidth = slopeWidth;
        this.slopeHeight = slopeHeight;
        this.sillObs = sillObs;
        this.accessibleFloor = accessibleFloor;
        this.accessFloorObs = accessFloorObs;
        this.accessibleEquip = accessibleEquip;
        this.accessEquipObs = accessEquipObs;
        this.playgroundObs = playgroundObs;
        this.playPhotoNumber = playPhotoNumber;
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

    public Integer getPlayHasGate() {
        return playHasGate;
    }

    public void setPlayHasGate(Integer playHasGate) {
        this.playHasGate = playHasGate;
    }

    public Double getPlayGateWidth() {
        return playGateWidth;
    }

    public void setPlayGateWidth(Double playGateWidth) {
        this.playGateWidth = playGateWidth;
    }

    public Integer getGateHasFloorTrack() {
        return gateHasFloorTrack;
    }

    public void setGateHasFloorTrack(Integer gateHasFloorTrack) {
        this.gateHasFloorTrack = gateHasFloorTrack;
    }

    public Double getFloorTrackHeight() {
        return floorTrackHeight;
    }

    public void setFloorTrackHeight(Double floorTrackHeight) {
        this.floorTrackHeight = floorTrackHeight;
    }

    public Integer getFloorTrackHasRamp() {
        return floorTrackHasRamp;
    }

    public void setFloorTrackHasRamp(Integer floorTrackHasRamp) {
        this.floorTrackHasRamp = floorTrackHasRamp;
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

    public Integer getHasFloorGap() {
        return hasFloorGap;
    }

    public void setHasFloorGap(Integer hasFloorGap) {
        this.hasFloorGap = hasFloorGap;
    }

    public Integer getFloorGapQnt() {
        return floorGapQnt;
    }

    public void setFloorGapQnt(Integer floorGapQnt) {
        this.floorGapQnt = floorGapQnt;
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

    public Integer getGateSillType() {
        return gateSillType;
    }

    public void setGateSillType(Integer gateSillType) {
        this.gateSillType = gateSillType;
    }

    public Double getInclHeight() {
        return inclHeight;
    }

    public void setInclHeight(Double inclHeight) {
        this.inclHeight = inclHeight;
    }

    public Integer getInclMeasureQnt() {
        return inclMeasureQnt;
    }

    public void setInclMeasureQnt(Integer inclMeasureQnt) {
        this.inclMeasureQnt = inclMeasureQnt;
    }

    public Double getInclAngle1() {
        return inclAngle1;
    }

    public void setInclAngle1(Double inclAngle1) {
        this.inclAngle1 = inclAngle1;
    }

    public Double getInclAngle2() {
        return inclAngle2;
    }

    public void setInclAngle2(Double inclAngle2) {
        this.inclAngle2 = inclAngle2;
    }

    public Double getInclAngle3() {
        return inclAngle3;
    }

    public void setInclAngle3(Double inclAngle3) {
        this.inclAngle3 = inclAngle3;
    }

    public Double getInclAngle4() {
        return inclAngle4;
    }

    public void setInclAngle4(Double inclAngle4) {
        this.inclAngle4 = inclAngle4;
    }

    public Double getStepHeight() {
        return stepHeight;
    }

    public void setStepHeight(Double stepHeight) {
        this.stepHeight = stepHeight;
    }

    public Integer getSlopeMeasureQnt() {
        return slopeMeasureQnt;
    }

    public void setSlopeMeasureQnt(Integer slopeMeasureQnt) {
        this.slopeMeasureQnt = slopeMeasureQnt;
    }

    public Double getSlopeAngle1() {
        return slopeAngle1;
    }

    public void setSlopeAngle1(Double slopeAngle1) {
        this.slopeAngle1 = slopeAngle1;
    }

    public Double getSlopeAngle2() {
        return slopeAngle2;
    }

    public void setSlopeAngle2(Double slopeAngle2) {
        this.slopeAngle2 = slopeAngle2;
    }

    public Double getSlopeAngle3() {
        return slopeAngle3;
    }

    public void setSlopeAngle3(Double slopeAngle3) {
        this.slopeAngle3 = slopeAngle3;
    }

    public Double getSlopeAngle4() {
        return slopeAngle4;
    }

    public void setSlopeAngle4(Double slopeAngle4) {
        this.slopeAngle4 = slopeAngle4;
    }

    public Double getSlopeWidth() {
        return slopeWidth;
    }

    public void setSlopeWidth(Double slopeWidth) {
        this.slopeWidth = slopeWidth;
    }

    public Double getSlopeHeight() {
        return slopeHeight;
    }

    public void setSlopeHeight(Double slopeHeight) {
        this.slopeHeight = slopeHeight;
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

    public String getAccessFloorObs() {
        return accessFloorObs;
    }

    public void setAccessFloorObs(String accessFloorObs) {
        this.accessFloorObs = accessFloorObs;
    }

    public Integer getAccessibleEquip() {
        return accessibleEquip;
    }

    public void setAccessibleEquip(Integer accessibleEquip) {
        this.accessibleEquip = accessibleEquip;
    }

    public String getAccessEquipObs() {
        return accessEquipObs;
    }

    public void setAccessEquipObs(String accessEquipObs) {
        this.accessEquipObs = accessEquipObs;
    }

    public String getPlaygroundObs() {
        return playgroundObs;
    }

    public void setPlaygroundObs(String playgroundObs) {
        this.playgroundObs = playgroundObs;
    }

    public String getPlayPhotoNumber() {
        return playPhotoNumber;
    }

    public void setPlayPhotoNumber(String playPhotoNumber) {
        this.playPhotoNumber = playPhotoNumber;
    }
}
