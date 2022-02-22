package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class ExternalAccess {

    @PrimaryKey(autoGenerate = true)
    private int externalAccessID;
    private int blockID;

    private String accessLocation;
    private Integer entranceType;
    private String extAccessObs;

    private Integer hasSIA;
    private String obsSIA;
    private String floorType;
    private Double gateWidth;
    private Integer gateHasTracks;
    private Double gateTrackHeight;
    private Integer gateHasTrackRamp;
    private Integer trackRampQuantity;
    private Double trackRampMeasure1;
    private Double trackRampMeasure2;
    private Double trackRampMeasure3;
    private Double trackRampMeasure4;
    private Integer gateSillType;
    private Double sillInclinationHeight;
    private Double sillStepHeight;
    private Double sillSlopeAngle;
    private Double sillSlopeWidth;
    private String gateSillObs;
    private Integer gateHasObstacles;
    private Integer gateHasPayphones;
    private Integer gateHasIntercom;
    private Double intercomHeight;
    private Integer gateHasSoundSign;


    public ExternalAccess(int blockID, String accessLocation, Integer entranceType, String extAccessObs, Integer hasSIA, String obsSIA,
                          String floorType, Double gateWidth, Integer gateHasTracks, Double gateTrackHeight, Integer gateHasTrackRamp,
                          Integer trackRampQuantity, Double trackRampMeasure1, Double trackRampMeasure2, Double trackRampMeasure3,
                          Double trackRampMeasure4, Integer gateSillType, Double sillInclinationHeight, Double sillStepHeight,
                          Double sillSlopeAngle, Double sillSlopeWidth, String gateSillObs, Integer gateHasObstacles,
                          Integer gateHasPayphones, Integer gateHasIntercom, Double intercomHeight, Integer gateHasSoundSign) {
        this.blockID = blockID;
        this.accessLocation = accessLocation;
        this.entranceType = entranceType;
        this.extAccessObs = extAccessObs;
        this.hasSIA = hasSIA;
        this.obsSIA = obsSIA;
        this.floorType = floorType;
        this.gateWidth = gateWidth;
        this.gateHasTracks = gateHasTracks;
        this.gateTrackHeight = gateTrackHeight;
        this.gateHasTrackRamp = gateHasTrackRamp;
        this.trackRampQuantity = trackRampQuantity;
        this.trackRampMeasure1 = trackRampMeasure1;
        this.trackRampMeasure2 = trackRampMeasure2;
        this.trackRampMeasure3 = trackRampMeasure3;
        this.trackRampMeasure4 = trackRampMeasure4;
        this.gateSillType = gateSillType;
        this.sillInclinationHeight = sillInclinationHeight;
        this.sillStepHeight = sillStepHeight;
        this.sillSlopeAngle = sillSlopeAngle;
        this.sillSlopeWidth = sillSlopeWidth;
        this.gateSillObs = gateSillObs;
        this.gateHasObstacles = gateHasObstacles;
        this.gateHasPayphones = gateHasPayphones;
        this.gateHasIntercom = gateHasIntercom;
        this.intercomHeight = intercomHeight;
        this.gateHasSoundSign = gateHasSoundSign;
    }

    public int getExternalAccessID() {
        return externalAccessID;
    }

    public void setExternalAccessID(int externalAccessID) {
        this.externalAccessID = externalAccessID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public String getAccessLocation() {
        return accessLocation;
    }

    public void setAccessLocation(String accessLocation) {
        this.accessLocation = accessLocation;
    }

    public Integer getEntranceType() {
        return entranceType;
    }

    public void setEntranceType(Integer entranceType) {
        this.entranceType = entranceType;
    }

    public String getExtAccessObs() {
        return extAccessObs;
    }

    public void setExtAccessObs(String extAccessObs) {
        this.extAccessObs = extAccessObs;
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

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public Double getGateWidth() {
        return gateWidth;
    }

    public void setGateWidth(Double gateWidth) {
        this.gateWidth = gateWidth;
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

    public Integer getGateSillType() {
        return gateSillType;
    }

    public void setGateSillType(Integer gateSillType) {
        this.gateSillType = gateSillType;
    }

    public Double getSillInclinationHeight() {
        return sillInclinationHeight;
    }

    public void setSillInclinationHeight(Double sillInclinationHeight) {
        this.sillInclinationHeight = sillInclinationHeight;
    }

    public Double getSillStepHeight() {
        return sillStepHeight;
    }

    public void setSillStepHeight(Double sillStepHeight) {
        this.sillStepHeight = sillStepHeight;
    }

    public Double getSillSlopeAngle() {
        return sillSlopeAngle;
    }

    public void setSillSlopeAngle(Double sillSlopeAngle) {
        this.sillSlopeAngle = sillSlopeAngle;
    }

    public Double getSillSlopeWidth() {
        return sillSlopeWidth;
    }

    public void setSillSlopeWidth(Double sillSlopeWidth) {
        this.sillSlopeWidth = sillSlopeWidth;
    }

    public String getGateSillObs() {
        return gateSillObs;
    }

    public void setGateSillObs(String gateSillObs) {
        this.gateSillObs = gateSillObs;
    }

    public Integer getGateHasObstacles() {
        return gateHasObstacles;
    }

    public void setGateHasObstacles(Integer gateHasObstacles) {
        this.gateHasObstacles = gateHasObstacles;
    }

    public Integer getGateHasPayphones() {
        return gateHasPayphones;
    }

    public void setGateHasPayphones(Integer gateHasPayphones) {
        this.gateHasPayphones = gateHasPayphones;
    }

    public Integer getGateHasIntercom() {
        return gateHasIntercom;
    }

    public void setGateHasIntercom(Integer gateHasIntercom) {
        this.gateHasIntercom = gateHasIntercom;
    }

    public Double getIntercomHeight() {
        return intercomHeight;
    }

    public void setIntercomHeight(Double intercomHeight) {
        this.intercomHeight = intercomHeight;
    }

    public Integer getGateHasSoundSign() {
        return gateHasSoundSign;
    }

    public void setGateHasSoundSign(Integer gateHasSoundSign) {
        this.gateHasSoundSign = gateHasSoundSign;
    }
}
