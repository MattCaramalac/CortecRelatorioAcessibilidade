package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestroomEntryUpdate {
    @PrimaryKey
    private int restroomID;
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

    public RestroomEntryUpdate(int restroomID, Integer restroomType, String restroomLocation, Integer accessibleRoute, String accessibleRouteObs,
                               Integer integratedRestroom, String integratedRestroomObs, Integer restroomDistance, String restroomDistanceObs,
                               Integer exclusiveEntrance, String exclusiveEntranceObs, Integer antiDriftingFloor, String antiDriftingFloorObs,
                               Integer restroomDrain, String restroomDrainObs, Integer restroomSwitch, Double switchHeight, String switchObs) {
        this.restroomID = restroomID;
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
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
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
}
