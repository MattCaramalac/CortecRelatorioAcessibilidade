package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestAccessEntranceUpdate {

    @PrimaryKey
    private int restroomID;
    private int restType;
    private Integer restGender;
    private String restLocation;
    private Integer accessRoute;
    private String accessRouteObs;
    private Integer intRestroom;
    private String intRestObs;
    private Integer antiDriftFloor;
    private String antiDriftFloorObs;
    private Integer restDrain;
    private String restDrainObs;
    private Integer restSwitch;
    private Double switchHeight;
    private String switchObs;
    private String restFirstPhoto;

    public RestAccessEntranceUpdate(int restroomID, int restType, Integer restGender, String restLocation, Integer accessRoute, String accessRouteObs, Integer intRestroom,
                                    String intRestObs, Integer antiDriftFloor, String antiDriftFloorObs, Integer restDrain, String restDrainObs, Integer restSwitch,
                                    Double switchHeight, String switchObs, String restFirstPhoto) {
        this.restroomID = restroomID;
        this.restType = restType;
        this.restGender = restGender;
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
        this.restFirstPhoto = restFirstPhoto;
    }

    public String getRestFirstPhoto() {
        return restFirstPhoto;
    }

    public void setRestFirstPhoto(String restFirstPhoto) {
        this.restFirstPhoto = restFirstPhoto;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public int getRestType() {
        return restType;
    }

    public void setRestType(int restType) {
        this.restType = restType;
    }

    public Integer getRestGender() {
        return restGender;
    }

    public void setRestGender(Integer restGender) {
        this.restGender = restGender;
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

    public Integer getAntiDriftFloor() {
        return antiDriftFloor;
    }

    public void setAntiDriftFloor(Integer antiDriftFloor) {
        this.antiDriftFloor = antiDriftFloor;
    }

    public String getAntiDriftFloorObs() {
        return antiDriftFloorObs;
    }

    public void setAntiDriftFloorObs(String antiDriftFloorObs) {
        this.antiDriftFloorObs = antiDriftFloorObs;
    }

    public Integer getRestDrain() {
        return restDrain;
    }

    public void setRestDrain(Integer restDrain) {
        this.restDrain = restDrain;
    }

    public String getRestDrainObs() {
        return restDrainObs;
    }

    public void setRestDrainObs(String restDrainObs) {
        this.restDrainObs = restDrainObs;
    }

    public Integer getRestSwitch() {
        return restSwitch;
    }

    public void setRestSwitch(Integer restSwitch) {
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
}
