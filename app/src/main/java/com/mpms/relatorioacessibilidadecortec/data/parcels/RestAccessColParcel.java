package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class RestAccessColParcel {

    Integer restGender;
    String restLocation;
    Double notAccessLength;
    Double notAccessWidth;
    Integer collectiveHasDoor;
    Integer accessRoute;
    String accessRouteObs;
    Integer intRestroom;
    String intRestObs;
    Integer antiDriftFloor;
    String antiDriftFloorObs;
    Integer restDrain;
    String restDrainObs;
    Double notAccEntranceWidth;
    Integer notAccEntranceSill;
    String notAccEntranceObs;
    Integer restSwitch;
    Double switchHeight;
    String switchObs;
    Integer hasWindow;
    Integer winQnt;
    String winComType1;
    Double winComHeight1;
    String winComType2;
    Double winComHeight2;
    String winComType3;
    Double winComHeight3;
    String winObs;
    String firstPhoto;


    public RestAccessColParcel() {
//        Empty Constructor
    }

    public RestAccessColParcel(Integer restGender, String restLocation, Double notAccessLength, Double notAccessWidth, Integer collectiveHasDoor, Integer accessRoute,
                               String accessRouteObs, Integer intRestroom, String intRestObs, Integer antiDriftFloor, String antiDriftFloorObs, Integer restDrain,
                               String restDrainObs, Double notAccEntranceWidth, Integer notAccEntranceSill, String notAccEntranceObs, Integer restSwitch, Double switchHeight,
                               String switchObs, Integer hasWindow, Integer winQnt, String winComType1, Double winComHeight1, String winComType2, Double winComHeight2,
                               String winComType3, Double winComHeight3, String winObs, String firstPhoto) {
        this.restGender = restGender;
        this.restLocation = restLocation;
        this.notAccessLength = notAccessLength;
        this.notAccessWidth = notAccessWidth;
        this.collectiveHasDoor = collectiveHasDoor;
        this.accessRoute = accessRoute;
        this.accessRouteObs = accessRouteObs;
        this.intRestroom = intRestroom;
        this.intRestObs = intRestObs;
        this.antiDriftFloor = antiDriftFloor;
        this.antiDriftFloorObs = antiDriftFloorObs;
        this.restDrain = restDrain;
        this.restDrainObs = restDrainObs;
        this.notAccEntranceWidth = notAccEntranceWidth;
        this.notAccEntranceSill = notAccEntranceSill;
        this.notAccEntranceObs = notAccEntranceObs;
        this.restSwitch = restSwitch;
        this.switchHeight = switchHeight;
        this.switchObs = switchObs;
        this.hasWindow = hasWindow;
        this.winQnt = winQnt;
        this.winComType1 = winComType1;
        this.winComHeight1 = winComHeight1;
        this.winComType2 = winComType2;
        this.winComHeight2 = winComHeight2;
        this.winComType3 = winComType3;
        this.winComHeight3 = winComHeight3;
        this.winObs = winObs;
        this.firstPhoto = firstPhoto;
    }

    public String getFirstPhoto() {
        return firstPhoto;
    }

    public void setFirstPhoto(String firstPhoto) {
        this.firstPhoto = firstPhoto;
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

    public Double getNotAccessLength() {
        return notAccessLength;
    }

    public void setNotAccessLength(Double notAccessLength) {
        this.notAccessLength = notAccessLength;
    }

    public Double getNotAccessWidth() {
        return notAccessWidth;
    }

    public void setNotAccessWidth(Double notAccessWidth) {
        this.notAccessWidth = notAccessWidth;
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

    public Double getNotAccEntranceWidth() {
        return notAccEntranceWidth;
    }

    public void setNotAccEntranceWidth(Double notAccEntranceWidth) {
        this.notAccEntranceWidth = notAccEntranceWidth;
    }

    public Integer getNotAccEntranceSill() {
        return notAccEntranceSill;
    }

    public void setNotAccEntranceSill(Integer notAccEntranceSill) {
        this.notAccEntranceSill = notAccEntranceSill;
    }

    public String getNotAccEntranceObs() {
        return notAccEntranceObs;
    }

    public void setNotAccEntranceObs(String notAccEntranceObs) {
        this.notAccEntranceObs = notAccEntranceObs;
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

    public Integer getHasWindow() {
        return hasWindow;
    }

    public void setHasWindow(Integer hasWindow) {
        this.hasWindow = hasWindow;
    }

    public Integer getWinQnt() {
        return winQnt;
    }

    public void setWinQnt(Integer winQnt) {
        this.winQnt = winQnt;
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

    public Integer getCollectiveHasDoor() {
        return collectiveHasDoor;
    }

    public void setCollectiveHasDoor(Integer collectiveHasDoor) {
        this.collectiveHasDoor = collectiveHasDoor;
    }
}
