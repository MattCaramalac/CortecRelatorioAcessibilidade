package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolID",
                onDelete = CASCADE, onUpdate = CASCADE))
public class RestroomEntry {

    @PrimaryKey(autoGenerate = true)
    private int restroomID;
    private int schoolID;
    private Integer restroomType;
    private String restroomLocation;
    private Integer accessibleRoute;
    private String accessibleRouteObs;
    private Integer integratedRestroom;
    private String integratedRestroomObs;
    private Integer restroomDistance;
    private String restroomDistanceObs;
    private Integer exclusiveEntrance;
    private String exclusiveEntranceObs;
    private Integer antiDriftingFloor;
    private String antiDriftingFloorObs;
    private Integer restroomDrain;
    private String restroomDrainObs;
    private Integer restroomSwitch;
    private Double switchHeight;
    private String switchObs;
    private Double doorWidth;
    private Integer doorSIA;
    private String doorSIAObs;
    private Integer doorExtOp;
    private String doorExtOpObs;
    private Integer doorVertSign;
    private String doorVertSignObs;
    private Integer doorSillType;
    private Double inclinationSillHeight;
    private Double stepSillHeight;
    private Double slopeSillInclination;
    private Double slopeSillWidth;
    private String doorSillTypeObs;
    private Integer doorTactileSign;
    private String doorTactileSignObs;
    private Integer doorIntCoating;
    private String doorIntCoatingObs;
    private Integer doorHorizontalHandle;
    private Double handleHeight;
    private Double handleLength;
    private String handleObs;

    public RestroomEntry(int schoolID, Integer restroomType, String restroomLocation, Integer accessibleRoute, String accessibleRouteObs,
                         Integer integratedRestroom, String integratedRestroomObs, Integer restroomDistance, String restroomDistanceObs,
                         Integer exclusiveEntrance, String exclusiveEntranceObs, Integer antiDriftingFloor, String antiDriftingFloorObs,
                         Integer restroomDrain, String restroomDrainObs, Integer restroomSwitch, Double switchHeight, String switchObs,
                         Double doorWidth, Integer doorSIA, String doorSIAObs, Integer doorExtOp, String doorExtOpObs, Integer doorVertSign,
                         String doorVertSignObs, Integer doorSillType, Double inclinationSillHeight, Double stepSillHeight, Double slopeSillInclination,
                         Double slopeSillWidth, String doorSillTypeObs, Integer doorTactileSign, String doorTactileSignObs, Integer doorIntCoating,
                         String doorIntCoatingObs, Integer doorHorizontalHandle, Double handleHeight, Double handleLength, String handleObs) {
        this.schoolID = schoolID;
        this.restroomType = restroomType;
        this.restroomLocation = restroomLocation;
        this.accessibleRoute = accessibleRoute;
        this.accessibleRouteObs = accessibleRouteObs;
        this.integratedRestroom = integratedRestroom;
        this.integratedRestroomObs = integratedRestroomObs;
        this.restroomDistance = restroomDistance;
        this.restroomDistanceObs = restroomDistanceObs;
        this.exclusiveEntrance = exclusiveEntrance;
        this.exclusiveEntranceObs = exclusiveEntranceObs;
        this.antiDriftingFloor = antiDriftingFloor;
        this.antiDriftingFloorObs = antiDriftingFloorObs;
        this.restroomDrain = restroomDrain;
        this.restroomDrainObs = restroomDrainObs;
        this.restroomSwitch = restroomSwitch;
        this.switchHeight = switchHeight;
        this.switchObs = switchObs;
        this.doorWidth = doorWidth;
        this.doorSIA = doorSIA;
        this.doorSIAObs = doorSIAObs;
        this.doorExtOp = doorExtOp;
        this.doorExtOpObs = doorExtOpObs;
        this.doorVertSign = doorVertSign;
        this.doorVertSignObs = doorVertSignObs;
        this.doorSillType = doorSillType;
        this.inclinationSillHeight = inclinationSillHeight;
        this.stepSillHeight = stepSillHeight;
        this.slopeSillInclination = slopeSillInclination;
        this.slopeSillWidth = slopeSillWidth;
        this.doorSillTypeObs = doorSillTypeObs;
        this.doorTactileSign = doorTactileSign;
        this.doorTactileSignObs = doorTactileSignObs;
        this.doorIntCoating = doorIntCoating;
        this.doorIntCoatingObs = doorIntCoatingObs;
        this.doorHorizontalHandle = doorHorizontalHandle;
        this.handleHeight = handleHeight;
        this.handleLength = handleLength;
        this.handleObs = handleObs;
    }

//    public RestroomEntry(int schoolID, Integer restroomType, String restroomLocation, Integer accessibleRoute, String accessibleRouteObs,
//                         Integer integratedRestroom, String integratedRestroomObs, Integer restroomDistance, String restroomDistanceObs,
//                         Integer exclusiveEntrance, String exclusiveEntranceObs, Integer antiDriftingFloor, String antiDriftingFloorObs,
//                         Integer restroomDrain, String restroomDrainObs, Integer restroomSwitch, Double switchHeight, String switchObs) {
//        this.schoolID = schoolID;
//        this.restroomType = restroomType;
//        this.restroomLocation = restroomLocation;
//        this.accessibleRoute = accessibleRoute;
//        this.accessibleRouteObs = accessibleRouteObs;
//        this.integratedRestroom = integratedRestroom;
//        this.integratedRestroomObs = integratedRestroomObs;
//        this.restroomDistance = restroomDistance;
//        this.restroomDistanceObs = restroomDistanceObs;
//        this.exclusiveEntrance = exclusiveEntrance;
//        this.exclusiveEntranceObs = exclusiveEntranceObs;
//        this.antiDriftingFloor = antiDriftingFloor;
//        this.antiDriftingFloorObs = antiDriftingFloorObs;
//        this.restroomDrain = restroomDrain;
//        this.restroomDrainObs = restroomDrainObs;
//        this.restroomSwitch = restroomSwitch;
//        this.switchHeight = switchHeight;
//        this.switchObs = switchObs;
//    }
//
//    public RestroomEntry(Double doorWidth, Integer doorSIA, String doorSIAObs, Integer doorExtOp, String doorExtOpObs,
//                         Integer doorVertSign, String doorVertSignObs, Integer doorSillType, Double inclinationSillHeight,
//                         Double stepSillHeight, Double slopeSillInclination, Double slopeSillWidth, String doorSillTypeObs,
//                         Integer doorTactileSign, String doorTactileSignObs, Integer doorIntCoating, String doorIntCoatingObs,
//                         Integer doorHorizontalHandle, Double handleHeight, Double handleLength, String handleObs) {
//        this.doorWidth = doorWidth;
//        this.doorSIA = doorSIA;
//        this.doorSIAObs = doorSIAObs;
//        this.doorExtOp = doorExtOp;
//        this.doorExtOpObs = doorExtOpObs;
//        this.doorVertSign = doorVertSign;
//        this.doorVertSignObs = doorVertSignObs;
//        this.doorSillType = doorSillType;
//        this.inclinationSillHeight = inclinationSillHeight;
//        this.stepSillHeight = stepSillHeight;
//        this.slopeSillInclination = slopeSillInclination;
//        this.slopeSillWidth = slopeSillWidth;
//        this.doorSillTypeObs = doorSillTypeObs;
//        this.doorTactileSign = doorTactileSign;
//        this.doorTactileSignObs = doorTactileSignObs;
//        this.doorIntCoating = doorIntCoating;
//        this.doorIntCoatingObs = doorIntCoatingObs;
//        this.doorHorizontalHandle = doorHorizontalHandle;
//        this.handleHeight = handleHeight;
//        this.handleLength = handleLength;
//        this.handleObs = handleObs;
//    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public Integer getRestroomType() {
        return restroomType;
    }

    public void setRestroomType(Integer restroomType) {
        this.restroomType = restroomType;
    }

    public String getRestroomLocation() {
        return restroomLocation;
    }

    public void setRestroomLocation(String restroomLocation) {
        this.restroomLocation = restroomLocation;
    }

    public Integer getAccessibleRoute() {
        return accessibleRoute;
    }

    public void setAccessibleRoute(Integer accessibleRoute) {
        this.accessibleRoute = accessibleRoute;
    }

    public String getAccessibleRouteObs() {
        return accessibleRouteObs;
    }

    public void setAccessibleRouteObs(String accessibleRouteObs) {
        this.accessibleRouteObs = accessibleRouteObs;
    }

    public Integer getIntegratedRestroom() {
        return integratedRestroom;
    }

    public void setIntegratedRestroom(Integer integratedRestroom) {
        this.integratedRestroom = integratedRestroom;
    }

    public String getIntegratedRestroomObs() {
        return integratedRestroomObs;
    }

    public void setIntegratedRestroomObs(String integratedRestroomObs) {
        this.integratedRestroomObs = integratedRestroomObs;
    }

    public Integer getRestroomDistance() {
        return restroomDistance;
    }

    public void setRestroomDistance(Integer restroomDistance) {
        this.restroomDistance = restroomDistance;
    }

    public String getRestroomDistanceObs() {
        return restroomDistanceObs;
    }

    public void setRestroomDistanceObs(String restroomDistanceObs) {
        this.restroomDistanceObs = restroomDistanceObs;
    }

    public Integer getExclusiveEntrance() {
        return exclusiveEntrance;
    }

    public void setExclusiveEntrance(Integer exclusiveEntrance) {
        this.exclusiveEntrance = exclusiveEntrance;
    }

    public String getExclusiveEntranceObs() {
        return exclusiveEntranceObs;
    }

    public void setExclusiveEntranceObs(String exclusiveEntranceObs) {
        this.exclusiveEntranceObs = exclusiveEntranceObs;
    }

    public Integer getAntiDriftingFloor() {
        return antiDriftingFloor;
    }

    public void setAntiDriftingFloor(Integer antiDriftingFloor) {
        this.antiDriftingFloor = antiDriftingFloor;
    }

    public String getAntiDriftingFloorObs() {
        return antiDriftingFloorObs;
    }

    public void setAntiDriftingFloorObs(String antiDriftingFloorObs) {
        this.antiDriftingFloorObs = antiDriftingFloorObs;
    }

    public Integer getRestroomDrain() {
        return restroomDrain;
    }

    public void setRestroomDrain(Integer restroomDrain) {
        this.restroomDrain = restroomDrain;
    }

    public String getRestroomDrainObs() {
        return restroomDrainObs;
    }

    public void setRestroomDrainObs(String restroomDrainObs) {
        this.restroomDrainObs = restroomDrainObs;
    }

    public Integer getRestroomSwitch() {
        return restroomSwitch;
    }

    public void setRestroomSwitch(Integer restroomSwitch) {
        this.restroomSwitch = restroomSwitch;
    }

    public Double getSwitchHeight() {
        return switchHeight;
    }

    public void setSwitchHeight(Double switchHeight) {
        this.switchHeight = switchHeight;
    }

    public String getSwitchObs() {
        return switchObs;
    }

    public void setSwitchObs(String switchObs) {
        this.switchObs = switchObs;
    }

    public Double getDoorWidth() {
        return doorWidth;
    }

    public void setDoorWidth(Double doorWidth) {
        this.doorWidth = doorWidth;
    }

    public Integer getDoorSIA() {
        return doorSIA;
    }

    public void setDoorSIA(Integer doorSIA) {
        this.doorSIA = doorSIA;
    }

    public String getDoorSIAObs() {
        return doorSIAObs;
    }

    public void setDoorSIAObs(String doorSIAObs) {
        this.doorSIAObs = doorSIAObs;
    }

    public Integer getDoorExtOp() {
        return doorExtOp;
    }

    public void setDoorExtOp(Integer doorExtOp) {
        this.doorExtOp = doorExtOp;
    }

    public String getDoorExtOpObs() {
        return doorExtOpObs;
    }

    public void setDoorExtOpObs(String doorExtOpObs) {
        this.doorExtOpObs = doorExtOpObs;
    }

    public Integer getDoorVertSign() {
        return doorVertSign;
    }

    public void setDoorVertSign(Integer doorVertSign) {
        this.doorVertSign = doorVertSign;
    }

    public String getDoorVertSignObs() {
        return doorVertSignObs;
    }

    public void setDoorVertSignObs(String doorVertSignObs) {
        this.doorVertSignObs = doorVertSignObs;
    }

    public Integer getDoorSillType() {
        return doorSillType;
    }

    public void setDoorSillType(Integer doorSillType) {
        this.doorSillType = doorSillType;
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

    public Double getSlopeSillInclination() {
        return slopeSillInclination;
    }

    public void setSlopeSillInclination(Double slopeSillInclination) {
        this.slopeSillInclination = slopeSillInclination;
    }

    public Double getSlopeSillWidth() {
        return slopeSillWidth;
    }

    public void setSlopeSillWidth(Double slopeSillWidth) {
        this.slopeSillWidth = slopeSillWidth;
    }

    public String getDoorSillTypeObs() {
        return doorSillTypeObs;
    }

    public void setDoorSillTypeObs(String doorSillTypeObs) {
        this.doorSillTypeObs = doorSillTypeObs;
    }

    public Integer getDoorTactileSign() {
        return doorTactileSign;
    }

    public void setDoorTactileSign(Integer doorTactileSign) {
        this.doorTactileSign = doorTactileSign;
    }

    public String getDoorTactileSignObs() {
        return doorTactileSignObs;
    }

    public void setDoorTactileSignObs(String doorTactileSignObs) {
        this.doorTactileSignObs = doorTactileSignObs;
    }

    public Integer getDoorIntCoating() {
        return doorIntCoating;
    }

    public void setDoorIntCoating(Integer doorIntCoating) {
        this.doorIntCoating = doorIntCoating;
    }

    public String getDoorIntCoatingObs() {
        return doorIntCoatingObs;
    }

    public void setDoorIntCoatingObs(String doorIntCoatingObs) {
        this.doorIntCoatingObs = doorIntCoatingObs;
    }

    public Integer getDoorHorizontalHandle() {
        return doorHorizontalHandle;
    }

    public void setDoorHorizontalHandle(Integer doorHorizontalHandle) {
        this.doorHorizontalHandle = doorHorizontalHandle;
    }

    public Double getHandleHeight() {
        return handleHeight;
    }

    public void setHandleHeight(Double handleHeight) {
        this.handleHeight = handleHeight;
    }

    public Double getHandleLength() {
        return handleLength;
    }

    public void setHandleLength(Double handleLength) {
        this.handleLength = handleLength;
    }

    public String getHandleObs() {
        return handleObs;
    }

    public void setHandleObs(String handleObs) {
        this.handleObs = handleObs;
    }
}
