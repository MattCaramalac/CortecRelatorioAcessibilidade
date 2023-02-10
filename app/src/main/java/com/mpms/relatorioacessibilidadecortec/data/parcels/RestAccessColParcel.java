package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class RestAccessColParcel {

    int restType;
    String restLocation;
    Integer accessRoute;
    String accessRouteObs;
    Integer intRestroom;
    String intRestObs;
    int antiDriftFloor;
    String antiDriftFloorObs;
    int restDrain;
    String restDrainObs;
    int restSwitch;
    Double switchHeight;
    String switchObs;
    Integer hasEntranceDoor;
    Double doorWidth;
    Integer doorSillType;
    String doorSillObs;


    public RestAccessColParcel() {
//        Empty Constructor
    }

    public RestAccessColParcel(int restType, String restLocation, Integer accessRoute, String accessRouteObs, Integer intRestroom, String intRestObs, int antiDriftFloor,
                               String antiDriftFloorObs, int restDrain, String restDrainObs, int restSwitch, Double switchHeight, String switchObs, Integer hasEntranceDoor,
                               Double doorWidth, Integer doorSillType, String doorSillObs) {
        this.restType = restType;
        this.restLocation = restLocation;
        this.accessRoute = accessRoute;
        this.accessRouteObs = accessRouteObs;
        this.intRestroom = intRestroom;
        this.intRestObs = intRestObs;
        this.antiDriftFloor = antiDriftFloor;
        this.antiDriftFloorObs = antiDriftFloorObs;
        this.restDrain = restDrain;
        this.restDrainObs = restDrainObs;
        this.restSwitch = restSwitch;
        this.switchHeight = switchHeight;
        this.switchObs = switchObs;
        this.hasEntranceDoor = hasEntranceDoor;
        this.doorWidth = doorWidth;
        this.doorSillType = doorSillType;
        this.doorSillObs = doorSillObs;
    }

    public int getRestType() {
        return restType;
    }

    public void setRestType(int restType) {
        this.restType = restType;
    }

    public String getRestLocation() {
        return restLocation;
    }

    public void setRestLocation(String restLocation) {
        this.restLocation = restLocation;
    }

    public Integer getAccessRoute() {
        return accessRoute;
    }

    public void setAccessRoute(Integer accessRoute) {
        this.accessRoute = accessRoute;
    }

    public String getAccessRouteObs() {
        return accessRouteObs;
    }

    public void setAccessRouteObs(String accessRouteObs) {
        this.accessRouteObs = accessRouteObs;
    }

    public Integer getIntRestroom() {
        return intRestroom;
    }

    public void setIntRestroom(Integer intRestroom) {
        this.intRestroom = intRestroom;
    }

    public String getIntRestObs() {
        return intRestObs;
    }

    public void setIntRestObs(String intRestObs) {
        this.intRestObs = intRestObs;
    }

    public int getAntiDriftFloor() {
        return antiDriftFloor;
    }

    public void setAntiDriftFloor(int antiDriftFloor) {
        this.antiDriftFloor = antiDriftFloor;
    }

    public String getAntiDriftFloorObs() {
        return antiDriftFloorObs;
    }

    public void setAntiDriftFloorObs(String antiDriftFloorObs) {
        this.antiDriftFloorObs = antiDriftFloorObs;
    }

    public int getRestDrain() {
        return restDrain;
    }

    public void setRestDrain(int restDrain) {
        this.restDrain = restDrain;
    }

    public String getRestDrainObs() {
        return restDrainObs;
    }

    public void setRestDrainObs(String restDrainObs) {
        this.restDrainObs = restDrainObs;
    }

    public int getRestSwitch() {
        return restSwitch;
    }

    public void setRestSwitch(int restSwitch) {
        this.restSwitch = restSwitch;
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

    public Integer getHasEntranceDoor() {
        return hasEntranceDoor;
    }

    public void setHasEntranceDoor(Integer hasEntranceDoor) {
        this.hasEntranceDoor = hasEntranceDoor;
    }

    public Double getDoorWidth() {
        return doorWidth;
    }

    public void setDoorWidth(Double doorWidth) {
        this.doorWidth = doorWidth;
    }

    public Integer getDoorSillType() {
        return doorSillType;
    }

    public void setDoorSillType(Integer doorSillType) {
        this.doorSillType = doorSillType;
    }

    public String getDoorSillObs() {
        return doorSillObs;
    }

    public void setDoorSillObs(String doorSillObs) {
        this.doorSillObs = doorSillObs;
    }
}
