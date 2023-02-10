package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestEntranceUpdate {
    @PrimaryKey
    private int restroomID;
    private int isCollective;
    private Integer restType;
    private String restLocation;
    private Integer collectiveHasDoor;
    private Double entranceWidth;
    private Integer entranceDoorSill;
    private String entranceDoorSillObs;
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

    public RestEntranceUpdate(int restroomID, int isCollective, Integer restType, String restLocation, Integer collectiveHasDoor, Double entranceWidth,
                              Integer entranceDoorSill, String entranceDoorSillObs, Integer accessRoute, String accessRouteObs, Integer intRestroom,
                              String intRestObs, Integer antiDriftFloor, String antiDriftFloorObs, Integer restDrain, String restDrainObs, Integer restSwitch,
                              Double switchHeight, String switchObs) {
        this.restroomID = restroomID;
        this.isCollective = isCollective;
        this.restType = restType;
        this.restLocation = restLocation;
        this.collectiveHasDoor = collectiveHasDoor;
        this.entranceWidth = entranceWidth;
        this.entranceDoorSill = entranceDoorSill;
        this.entranceDoorSillObs = entranceDoorSillObs;
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
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public Integer getRestType() {
        return restType;
    }

    public void setRestType(Integer restType) {
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

    public void setIntRestObs(String intRestroomObs) {
        this.intRestObs = intRestroomObs;
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

    public int getIsCollective() {
        return isCollective;
    }

    public void setIsCollective(int isCollective) {
        this.isCollective = isCollective;
    }

    public Integer getCollectiveHasDoor() {
        return collectiveHasDoor;
    }

    public void setCollectiveHasDoor(Integer collectiveHasDoor) {
        this.collectiveHasDoor = collectiveHasDoor;
    }

    public Double getEntranceWidth() {
        return entranceWidth;
    }

    public void setEntranceWidth(Double entranceWidth) {
        this.entranceWidth = entranceWidth;
    }

    public Integer getEntranceDoorSill() {
        return entranceDoorSill;
    }

    public void setEntranceDoorSill(Integer entranceDoorSill) {
        this.entranceDoorSill = entranceDoorSill;
    }

    public String getEntranceDoorSillObs() {
        return entranceDoorSillObs;
    }

    public void setEntranceDoorSillObs(String entranceDoorSillObs) {
        this.entranceDoorSillObs = entranceDoorSillObs;
    }
}
