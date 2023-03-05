package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestColFirstUpdate {
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
    private Double upViewLength;
    private Double upViewWidth;
    private Integer hasWindow;
    private Integer winQnt;
    private String winComType1;
    private Double winComHeight1;
    private String winComType2;
    private Double winComHeight2;
    private String winComType3;
    private Double winComHeight3;
    private String winObs;

    public RestColFirstUpdate(int restroomID, int isCollective, Integer restType, String restLocation, Integer collectiveHasDoor, Double entranceWidth, Integer entranceDoorSill,
                              String entranceDoorSillObs, Integer accessRoute, String accessRouteObs, Integer intRestroom, String intRestObs,
                              Integer antiDriftFloor, String antiDriftFloorObs, Integer restDrain, String restDrainObs, Integer restSwitch, Double switchHeight,
                              String switchObs, Double upViewLength, Double upViewWidth, Integer hasWindow, Integer winQnt, String winComType1, Double winComHeight1,
                              String winComType2, Double winComHeight2, String winComType3, Double winComHeight3, String winObs) {
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
        this.upViewLength = upViewLength;
        this.upViewWidth = upViewWidth;
        this.hasWindow = hasWindow;
        this.winQnt = winQnt;
        this.winComType1 = winComType1;
        this.winComHeight1 = winComHeight1;
        this.winComType2 = winComType2;
        this.winComHeight2 = winComHeight2;
        this.winComType3 = winComType3;
        this.winComHeight3 = winComHeight3;
        this.winObs = winObs;
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

    public Integer getCollectiveHasDoor() {
        return collectiveHasDoor;
    }

    public void setCollectiveHasDoor(Integer collectiveHasDoor) {
        this.collectiveHasDoor = collectiveHasDoor;
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

    public String getWinComType1() {
        return winComType1;
    }

    public void setWinComType1(String winComType1) {
        this.winComType1 = winComType1;
    }

    public Double getWinComHeight1() {
        return winComHeight1;
    }

    public void setWinComHeight1(Double winComHeight1) {
        this.winComHeight1 = winComHeight1;
    }

    public String getWinComType2() {
        return winComType2;
    }

    public void setWinComType2(String winComType2) {
        this.winComType2 = winComType2;
    }

    public Double getWinComHeight2() {
        return winComHeight2;
    }

    public void setWinComHeight2(Double winComHeight2) {
        this.winComHeight2 = winComHeight2;
    }

    public String getWinComType3() {
        return winComType3;
    }

    public void setWinComType3(String winComType3) {
        this.winComType3 = winComType3;
    }

    public Double getWinComHeight3() {
        return winComHeight3;
    }

    public void setWinComHeight3(Double winComHeight3) {
        this.winComHeight3 = winComHeight3;
    }

    public String getWinObs() {
        return winObs;
    }

    public void setWinObs(String winObs) {
        this.winObs = winObs;
    }

    public Integer getWinQnt() {
        return winQnt;
    }

    public void setWinQnt(Integer winQnt) {
        this.winQnt = winQnt;
    }

    public Integer getHasWindow() {
        return hasWindow;
    }

    public void setHasWindow(Integer hasWindow) {
        this.hasWindow = hasWindow;
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

    public Double getUpViewLength() {
        return upViewLength;
    }

    public void setUpViewLength(Double upViewLength) {
        this.upViewLength = upViewLength;
    }

    public Double getUpViewWidth() {
        return upViewWidth;
    }

    public void setUpViewWidth(Double upViewWidth) {
        this.upViewWidth = upViewWidth;
    }
}
