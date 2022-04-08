package com.mpms.relatorioacessibilidadecortec.data.entities;

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
    private Integer floorIsAccessible;
    private String accessibleFloorObs;

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
    private Integer gateSillType;
    private Double sillInclinationHeight;
    private Double sillStepHeight;
    private Integer slopeMeasureQnt;
    private Double sillSlopeAngle;
    private Double sillSlopeAngle2;
    private Double sillSlopeAngle3;
    private Double sillSlopeAngle4;
    private Double sillSlopeWidth;
    private String gateSillObs;
    private Integer gateHasObstacles;
    private Integer gateHasPayphones;
    private Integer gateHasIntercom;
    private Double intercomHeight;
    private Integer gateHasSoundSign;

    private String extAccessObs;


    public ExternalAccess(int blockID, String accessLocation, Integer entranceType, Integer floorIsAccessible, String accessibleFloorObs, Integer hasSIA, String obsSIA,
                          Integer entranceGateType, String entranceGateDesc, Double freeSpaceWidth1, Double freeSpaceWidth2, Integer gateHandleType, Double gateHandleHeight,
                          String gateObs, Integer gateHasTracks, Double gateTrackHeight, Integer gateHasTrackRamp, Integer trackRampQuantity, Double trackRampMeasure1,
                          Double trackRampMeasure2, Double trackRampMeasure3, Double trackRampMeasure4, Integer gateSillType, Double sillInclinationHeight, Double sillStepHeight,
                          Integer slopeMeasureQnt, Double sillSlopeAngle, Double sillSlopeAngle2, Double sillSlopeAngle3, Double sillSlopeAngle4, Double sillSlopeWidth,
                          String gateSillObs, Integer gateHasObstacles, Integer gateHasPayphones, Integer gateHasIntercom, Double intercomHeight, Integer gateHasSoundSign,
                          String extAccessObs) {
        this.blockID = blockID;
        this.accessLocation = accessLocation;
        this.entranceType = entranceType;
        this.floorIsAccessible = floorIsAccessible;
        this.accessibleFloorObs = accessibleFloorObs;
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
        this.gateSillType = gateSillType;
        this.sillInclinationHeight = sillInclinationHeight;
        this.sillStepHeight = sillStepHeight;
        this.slopeMeasureQnt = slopeMeasureQnt;
        this.sillSlopeAngle = sillSlopeAngle;
        this.sillSlopeAngle2 = sillSlopeAngle2;
        this.sillSlopeAngle3 = sillSlopeAngle3;
        this.sillSlopeAngle4 = sillSlopeAngle4;
        this.sillSlopeWidth = sillSlopeWidth;
        this.gateSillObs = gateSillObs;
        this.gateHasObstacles = gateHasObstacles;
        this.gateHasPayphones = gateHasPayphones;
        this.gateHasIntercom = gateHasIntercom;
        this.intercomHeight = intercomHeight;
        this.gateHasSoundSign = gateHasSoundSign;
        this.extAccessObs = extAccessObs;
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

    public Integer getFloorIsAccessible() {
        return floorIsAccessible;
    }

    public void setFloorIsAccessible(Integer floorIsAccessible) {
        this.floorIsAccessible = floorIsAccessible;
    }

    public String getAccessibleFloorObs() {
        return accessibleFloorObs;
    }

    public void setAccessibleFloorObs(String accessibleFloorObs) {
        this.accessibleFloorObs = accessibleFloorObs;
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

    public Integer getSlopeMeasureQnt() {
        return slopeMeasureQnt;
    }

    public void setSlopeMeasureQnt(Integer slopeMeasureQnt) {
        this.slopeMeasureQnt = slopeMeasureQnt;
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

    public String getEntranceGateDesc() {
        return entranceGateDesc;
    }

    public void setEntranceGateDesc(String entranceGateDesc) {
        this.entranceGateDesc = entranceGateDesc;
    }
}
