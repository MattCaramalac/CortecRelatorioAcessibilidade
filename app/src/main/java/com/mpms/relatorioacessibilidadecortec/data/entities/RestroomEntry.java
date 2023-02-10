package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID", onDelete = CASCADE, onUpdate = CASCADE))
public class RestroomEntry {

    @PrimaryKey(autoGenerate = true)
    private int restroomID;
    private int blockID;
    @ColumnInfo(defaultValue = "0")
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
    private Double upViewMeasureA;
    private Double upViewMeasureB;
    private Double upViewMeasureC;
    private Double upViewMeasureD;
    private String upViewObs;
    private Integer toType;
    private Double toHeightNoSeat;
    private Integer toHasSeat;
    private Double toHeightSeat;
    private Integer toHasSoculo;
    private Double frSoculo;
    private Double latSoculo;
    private Integer socCorners;
    private Integer toHasFrontBar;
    private Double frBarA;
    private Double frBarB;
    private Double frBarC;
    private Double frBarSect;
    private Double frBarDist;
    private Integer toHasWall;
    private Integer hasHorBar;
    private Double horBarD;
    private Double horBarE;
    private Double horBarF;
    private Double horBarDistG;
    private Double horBarSect;
    private Double horBarDist;
    private Integer hasVertBar;
    private Double vertBarH;
    private Double vertBarI;
    private Double vertBarJ;
    private Double vertBarSect;
    private Double vertBarDist;
    private Integer hasSideBar;
    private Double sideBarD;
    private Double sideBarE;
    private Double sideBarDistG;
    private Double sideBarSect;
    private Integer hasArtBar;
    private Double artBarH;
    private Double artBarI;
    private Double artBarJ;
    private Double artBarSect;
    private String toActDesc;
    private Double toActHeight;
    private String toActObs;
    private Integer hasPapHolder;
    private Integer papHolderType;
    private Double papEmbDist;
    private Double papEmbHeight;
    private Integer papSupAlign;
    private Double papSupHeight;
    private String papHoldObs;
    private Integer hasDouche;
    private Double douchePressHeight;
    private Double doucheActHeight;
    private String doucheObs;
    private String toiletObs;
    private Integer hasHanger;
    private Double hangerHeight;
    private String hangerObs;
    private Integer hasObjHold;
    private Integer objHoldCorrect;
    private Double objHoldHeight;
    private String objHoldObs;
    private Integer hasSoapHold;
    private Double soapHoldHeight;
    private String soapHoldObs;
    private Integer hasTowelHold;
    private Double towelHoldHeight;
    private String towelHoldObs;
    private Integer hasEmergencyButton;
    private Double emergencyHeight;
    private String emergencyObs;
    private Integer hasWaterValve;
    private Integer waterValveType;
    private Double waterValveHeight;
    private String waterValveObs;
    private Integer hasWindow;
    private Integer winQnt;
    private String winComType1;
    private Double winComHeight1;
    private String winComType2;
    private Double winComHeight2;
    private String winComType3;
    private Double winComHeight3;
    private String winObs;
    private Integer hasWallMirror;
    private Double wallMirrorLow;
    private Double wallMirrorHigh;
    private String wallMirrorObs;
    private Integer sinkType;
    private Integer hasLowerColSink;
    private Double approxMeasureA;
    private Double approxMeasureB;
    private Double approxMeasureC;
    private Double approxMeasureD;
    private Double approxMeasureE;
    private Integer hasSinkBar;
    private Integer hasLeftFrontHorBar;
    private Double leftFrontHorMeasureA;
    private Double leftFrontHorMeasureB;
    private Double leftFrontHorMeasureC;
    private Double leftFrontHorMeasureD;
    private Double leftFrontHorDiam;
    private Double leftFrontHorDist;
    private String leftFrontHorObs;
    private Integer hasRightSideVertBar;
    private Double rightSideVertMeasureA;
    private Double rightSideVertMeasureB;
    private Double rightSideVertMeasureC;
    private Double rightSideVertMeasureD;
    private Double rightSideVertMeasureE;
    private Double rightSideVertDiam;
    private Double rightSideVertDist;
    private String rightSideVertObs;
    private Integer sinkHasMirror;
    private Double sinkMirrorLow;
    private Double sinkMirrorHigh;
    private String sinkObs;
    private Integer hasUrinal;
    private Integer hasAccessUrinal;
    private Integer urinalType;
    private Double urMeasureA;
    private Double urMeasureB;
    private Double urMeasureC;
    private Double urMeasureD;
    private Double urMeasureE;
    private Double urMeasureF;
    private Double urMeasureG;
    private Double urMeasureH;
    private Double urMeasureI;
    private Double urMeasureJ;
    private Double urMeasureK;
    private Double urMeasureL;
    private Double urMeasureM;
    private String urObs;

    public RestroomEntry(int blockID, int isCollective, Integer restType, String restLocation, Integer collectiveHasDoor, Double entranceWidth, Integer entranceDoorSill,
                         String entranceDoorSillObs, Integer accessRoute,
                         String accessRouteObs, Integer intRestroom, String intRestObs, Integer antiDriftFloor, String antiDriftFloorObs, Integer restDrain,
                         String restDrainObs, Integer restSwitch, Double switchHeight, String switchObs, Double upViewLength, Double upViewWidth, Double upViewMeasureA,
                         Double upViewMeasureB, Double upViewMeasureC, Double upViewMeasureD, String upViewObs, Integer toType, Double toHeightNoSeat, Integer toHasSeat,
                         Double toHeightSeat, Integer toHasSoculo, Double frSoculo, Double latSoculo, Integer socCorners, Integer toHasFrontBar, Double frBarA, Double frBarB,
                         Double frBarC, Double frBarSect, Double frBarDist, Integer toHasWall, Integer hasHorBar, Double horBarD, Double horBarE, Double horBarF,
                         Double horBarDistG, Double horBarSect, Double horBarDist, Integer hasVertBar, Double vertBarH, Double vertBarI, Double vertBarJ, Double vertBarSect,
                         Double vertBarDist,  Integer hasSideBar, Double sideBarD, Double sideBarE, Double sideBarDistG, Double sideBarSect,
                         Integer hasArtBar, Double artBarH, Double artBarI, Double artBarJ, Double artBarSect, String toActDesc, Double toActHeight,
                         String toActObs, Integer hasPapHolder, Integer papHolderType, Double papEmbDist, Double papEmbHeight, Integer papSupAlign, Double papSupHeight,
                         String papHoldObs, Integer hasDouche, Double douchePressHeight, Double doucheActHeight, String doucheObs, String toiletObs, Integer hasHanger,
                         Double hangerHeight, String hangerObs, Integer hasObjHold, Integer objHoldCorrect, Double objHoldHeight, String objHoldObs, Integer hasSoapHold,
                         Double soapHoldHeight, String soapHoldObs, Integer hasTowelHold, Double towelHoldHeight, String towelHoldObs, Integer hasEmergencyButton,
                         Double emergencyHeight, String emergencyObs, Integer hasWaterValve, Integer waterValveType, Double waterValveHeight, String waterValveObs,
                         Integer hasWindow, Integer winQnt, String winComType1, Double winComHeight1, String winComType2, Double winComHeight2, String winComType3, Double winComHeight3,
                         String winObs, Integer hasWallMirror, Double wallMirrorLow, Double wallMirrorHigh, String wallMirrorObs, Integer sinkType, Integer hasLowerColSink,Double approxMeasureA,
                         Double approxMeasureB, Double approxMeasureC, Double approxMeasureD, Double approxMeasureE, Integer hasSinkBar, Integer hasLeftFrontHorBar,
                         Double leftFrontHorMeasureA, Double leftFrontHorMeasureB, Double leftFrontHorMeasureC, Double leftFrontHorMeasureD,
                         Double leftFrontHorDiam, Double leftFrontHorDist, String leftFrontHorObs, Integer hasRightSideVertBar, Double rightSideVertMeasureA,
                         Double rightSideVertMeasureB, Double rightSideVertMeasureC, Double rightSideVertMeasureD, Double rightSideVertMeasureE, Double rightSideVertDiam,
                         Double rightSideVertDist, String rightSideVertObs, Integer sinkHasMirror, Double sinkMirrorLow, Double sinkMirrorHigh, String sinkObs, Integer hasUrinal,
                         Integer hasAccessUrinal, Integer urinalType, Double urMeasureA, Double urMeasureB, Double urMeasureC, Double urMeasureD, Double urMeasureE,
                         Double urMeasureF, Double urMeasureG, Double urMeasureH, Double urMeasureI, Double urMeasureJ, Double urMeasureK, Double urMeasureL, Double urMeasureM,
                         String urObs) {
        this.blockID = blockID;
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
        this.upViewMeasureA = upViewMeasureA;
        this.upViewMeasureB = upViewMeasureB;
        this.upViewMeasureC = upViewMeasureC;
        this.upViewMeasureD = upViewMeasureD;
        this.upViewObs = upViewObs;
        this.toType = toType;
        this.toHeightNoSeat = toHeightNoSeat;
        this.toHasSeat = toHasSeat;
        this.toHeightSeat = toHeightSeat;
        this.toHasSoculo = toHasSoculo;
        this.frSoculo = frSoculo;
        this.latSoculo = latSoculo;
        this.socCorners = socCorners;
        this.toHasFrontBar = toHasFrontBar;
        this.frBarA = frBarA;
        this.frBarB = frBarB;
        this.frBarC = frBarC;
        this.frBarSect = frBarSect;
        this.frBarDist = frBarDist;
        this.toHasWall = toHasWall;
        this.hasHorBar = hasHorBar;
        this.horBarD = horBarD;
        this.horBarE = horBarE;
        this.horBarF = horBarF;
        this.horBarDistG = horBarDistG;
        this.horBarSect = horBarSect;
        this.horBarDist = horBarDist;
        this.hasVertBar = hasVertBar;
        this.vertBarH = vertBarH;
        this.vertBarI = vertBarI;
        this.vertBarJ = vertBarJ;
        this.vertBarSect = vertBarSect;
        this.vertBarDist = vertBarDist;
        this.hasSideBar =  hasSideBar;
        this.sideBarD = sideBarD;
        this.sideBarE = sideBarE;
        this.sideBarDistG = sideBarDistG;
        this.sideBarSect = sideBarSect;
        this.hasArtBar = hasArtBar;
        this.artBarH = artBarH;
        this.artBarI = artBarI;
        this.artBarJ = artBarJ;
        this.artBarSect = artBarSect;
        this.toActDesc = toActDesc;
        this.toActHeight = toActHeight;
        this.toActObs = toActObs;
        this.hasPapHolder = hasPapHolder;
        this.papHolderType = papHolderType;
        this.papEmbDist = papEmbDist;
        this.papEmbHeight = papEmbHeight;
        this.papSupAlign = papSupAlign;
        this.papSupHeight = papSupHeight;
        this.papHoldObs = papHoldObs;
        this.hasDouche = hasDouche;
        this.douchePressHeight = douchePressHeight;
        this.doucheActHeight = doucheActHeight;
        this.doucheObs = doucheObs;
        this.toiletObs = toiletObs;
        this.hasHanger = hasHanger;
        this.hangerHeight = hangerHeight;
        this.hangerObs = hangerObs;
        this.hasObjHold = hasObjHold;
        this.objHoldCorrect = objHoldCorrect;
        this.objHoldHeight = objHoldHeight;
        this.objHoldObs = objHoldObs;
        this.hasSoapHold = hasSoapHold;
        this.soapHoldHeight = soapHoldHeight;
        this.soapHoldObs = soapHoldObs;
        this.hasTowelHold = hasTowelHold;
        this.towelHoldHeight = towelHoldHeight;
        this.towelHoldObs = towelHoldObs;
        this.hasEmergencyButton = hasEmergencyButton;
        this.emergencyHeight = emergencyHeight;
        this.emergencyObs = emergencyObs;
        this.hasWaterValve = hasWaterValve;
        this.waterValveType = waterValveType;
        this.waterValveHeight = waterValveHeight;
        this.waterValveObs = waterValveObs;
        this.hasWindow = hasWindow;
        this.winQnt = winQnt;
        this.winComType1 = winComType1;
        this.winComHeight1 = winComHeight1;
        this.winComType2 = winComType2;
        this.winComHeight2 = winComHeight2;
        this.winComType3 = winComType3;
        this.winComHeight3 = winComHeight3;
        this.winObs = winObs;
        this.hasWallMirror = hasWallMirror;
        this.wallMirrorLow = wallMirrorLow;
        this.wallMirrorHigh = wallMirrorHigh;
        this.wallMirrorObs = wallMirrorObs;
        this.sinkType = sinkType;
        this.hasLowerColSink = hasLowerColSink;
        this.approxMeasureA = approxMeasureA;
        this.approxMeasureB = approxMeasureB;
        this.approxMeasureC = approxMeasureC;
        this.approxMeasureD = approxMeasureD;
        this.approxMeasureE = approxMeasureE;
        this.hasSinkBar = hasSinkBar;
        this.hasLeftFrontHorBar = hasLeftFrontHorBar;
        this.leftFrontHorMeasureA = leftFrontHorMeasureA;
        this.leftFrontHorMeasureB = leftFrontHorMeasureB;
        this.leftFrontHorMeasureC = leftFrontHorMeasureC;
        this.leftFrontHorMeasureD = leftFrontHorMeasureD;
        this.leftFrontHorDiam = leftFrontHorDiam;
        this.leftFrontHorDist = leftFrontHorDist;
        this.leftFrontHorObs = leftFrontHorObs;
        this.hasRightSideVertBar = hasRightSideVertBar;
        this.rightSideVertMeasureA = rightSideVertMeasureA;
        this.rightSideVertMeasureB = rightSideVertMeasureB;
        this.rightSideVertMeasureC = rightSideVertMeasureC;
        this.rightSideVertMeasureD = rightSideVertMeasureD;
        this.rightSideVertMeasureE = rightSideVertMeasureE;
        this.rightSideVertDiam = rightSideVertDiam;
        this.rightSideVertDist = rightSideVertDist;
        this.rightSideVertObs = rightSideVertObs;
        this.sinkHasMirror = sinkHasMirror;
        this.sinkMirrorLow = sinkMirrorLow;
        this.sinkMirrorHigh = sinkMirrorHigh;
        this.sinkObs = sinkObs;
        this.hasUrinal = hasUrinal;
        this.hasAccessUrinal = hasAccessUrinal;
        this.urinalType = urinalType;
        this.urMeasureA = urMeasureA;
        this.urMeasureB = urMeasureB;
        this.urMeasureC = urMeasureC;
        this.urMeasureD = urMeasureD;
        this.urMeasureE = urMeasureE;
        this.urMeasureF = urMeasureF;
        this.urMeasureG = urMeasureG;
        this.urMeasureH = urMeasureH;
        this.urMeasureI = urMeasureI;
        this.urMeasureJ = urMeasureJ;
        this.urMeasureK = urMeasureK;
        this.urMeasureL = urMeasureL;
        this.urMeasureM = urMeasureM;
        this.urObs = urObs;
    }

    public String getWinObs() {
        return winObs;
    }

    public void setWinObs(String winObs) {
        this.winObs = winObs;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public int getIsCollective() {
        return isCollective;
    }

    public void setIsCollective(int isCollective) {
        this.isCollective = isCollective;
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

    public Double getEntranceWidth() {
        return entranceWidth;
    }

    public void setEntranceWidth(Double entranceWidth) {
        this.entranceWidth = entranceWidth;
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

    public Double getUpViewMeasureA() {
        return upViewMeasureA;
    }

    public void setUpViewMeasureA(Double upViewMeasureA) {
        this.upViewMeasureA = upViewMeasureA;
    }

    public Double getUpViewMeasureB() {
        return upViewMeasureB;
    }

    public void setUpViewMeasureB(Double upViewMeasureB) {
        this.upViewMeasureB = upViewMeasureB;
    }

    public Double getUpViewMeasureC() {
        return upViewMeasureC;
    }

    public void setUpViewMeasureC(Double upViewMeasureC) {
        this.upViewMeasureC = upViewMeasureC;
    }

    public Double getUpViewMeasureD() {
        return upViewMeasureD;
    }

    public void setUpViewMeasureD(Double upViewMeasureD) {
        this.upViewMeasureD = upViewMeasureD;
    }

    public String getUpViewObs() {
        return upViewObs;
    }

    public void setUpViewObs(String upViewObs) {
        this.upViewObs = upViewObs;
    }

    public Integer getToType() {
        return toType;
    }

    public void setToType(Integer toType) {
        this.toType = toType;
    }

    public Double getToHeightNoSeat() {
        return toHeightNoSeat;
    }

    public void setToHeightNoSeat(Double toHeightNoSeat) {
        this.toHeightNoSeat = toHeightNoSeat;
    }

    public Integer getToHasSeat() {
        return toHasSeat;
    }

    public void setToHasSeat(Integer toHasSeat) {
        this.toHasSeat = toHasSeat;
    }

    public Double getToHeightSeat() {
        return toHeightSeat;
    }

    public void setToHeightSeat(Double toHeightSeat) {
        this.toHeightSeat = toHeightSeat;
    }

    public Integer getToHasSoculo() {
        return toHasSoculo;
    }

    public void setToHasSoculo(Integer toHasSoculo) {
        this.toHasSoculo = toHasSoculo;
    }

    public Double getFrSoculo() {
        return frSoculo;
    }

    public void setFrSoculo(Double frSoculo) {
        this.frSoculo = frSoculo;
    }

    public Double getLatSoculo() {
        return latSoculo;
    }

    public void setLatSoculo(Double latSoculo) {
        this.latSoculo = latSoculo;
    }

    public Integer getSocCorners() {
        return socCorners;
    }

    public void setSocCorners(Integer socCorners) {
        this.socCorners = socCorners;
    }

    public Integer getToHasFrontBar() {
        return toHasFrontBar;
    }

    public void setToHasFrontBar(Integer toHasFrontBar) {
        this.toHasFrontBar = toHasFrontBar;
    }

    public Double getFrBarA() {
        return frBarA;
    }

    public void setFrBarA(Double frBarA) {
        this.frBarA = frBarA;
    }

    public Double getFrBarB() {
        return frBarB;
    }

    public void setFrBarB(Double frBarB) {
        this.frBarB = frBarB;
    }

    public Double getFrBarC() {
        return frBarC;
    }

    public void setFrBarC(Double frBarC) {
        this.frBarC = frBarC;
    }

    public Double getFrBarSect() {
        return frBarSect;
    }

    public void setFrBarSect(Double frBarSect) {
        this.frBarSect = frBarSect;
    }

    public Double getFrBarDist() {
        return frBarDist;
    }

    public void setFrBarDist(Double frBarDist) {
        this.frBarDist = frBarDist;
    }

    public Integer getToHasWall() {
        return toHasWall;
    }

    public void setToHasWall(Integer toHasWall) {
        this.toHasWall = toHasWall;
    }

    public Integer getHasHorBar() {
        return hasHorBar;
    }

    public void setHasHorBar(Integer hasHorBar) {
        this.hasHorBar = hasHorBar;
    }

    public Double getHorBarD() {
        return horBarD;
    }

    public void setHorBarD(Double horBarD) {
        this.horBarD = horBarD;
    }

    public Double getHorBarE() {
        return horBarE;
    }

    public void setHorBarE(Double horBarE) {
        this.horBarE = horBarE;
    }

    public Double getHorBarF() {
        return horBarF;
    }

    public void setHorBarF(Double horBarF) {
        this.horBarF = horBarF;
    }

    public Double getHorBarDistG() {
        return horBarDistG;
    }

    public void setHorBarDistG(Double horBarDistG) {
        this.horBarDistG = horBarDistG;
    }

    public Double getHorBarSect() {
        return horBarSect;
    }

    public void setHorBarSect(Double horBarSect) {
        this.horBarSect = horBarSect;
    }

    public Double getHorBarDist() {
        return horBarDist;
    }

    public void setHorBarDist(Double horBarDist) {
        this.horBarDist = horBarDist;
    }

    public Integer getHasVertBar() {
        return hasVertBar;
    }

    public void setHasVertBar(Integer hasVertBar) {
        this.hasVertBar = hasVertBar;
    }

    public Double getVertBarH() {
        return vertBarH;
    }

    public void setVertBarH(Double vertBarH) {
        this.vertBarH = vertBarH;
    }

    public Double getVertBarI() {
        return vertBarI;
    }

    public void setVertBarI(Double vertBarI) {
        this.vertBarI = vertBarI;
    }

    public Double getVertBarJ() {
        return vertBarJ;
    }

    public void setVertBarJ(Double vertBarJ) {
        this.vertBarJ = vertBarJ;
    }

    public Double getVertBarSect() {
        return vertBarSect;
    }

    public void setVertBarSect(Double vertBarSect) {
        this.vertBarSect = vertBarSect;
    }

    public Double getVertBarDist() {
        return vertBarDist;
    }

    public void setVertBarDist(Double vertBarDist) {
        this.vertBarDist = vertBarDist;
    }

    public Integer getHasArtBar() {
        return hasArtBar;
    }

    public void setHasArtBar(Integer hasArtBar) {
        this.hasArtBar = hasArtBar;
    }

    public Double getArtBarH() {
        return artBarH;
    }

    public void setArtBarH(Double artBarH) {
        this.artBarH = artBarH;
    }

    public Double getArtBarI() {
        return artBarI;
    }

    public void setArtBarI(Double artBarI) {
        this.artBarI = artBarI;
    }

    public Double getArtBarJ() {
        return artBarJ;
    }

    public void setArtBarJ(Double artBarJ) {
        this.artBarJ = artBarJ;
    }

    public Double getArtBarSect() {
        return artBarSect;
    }

    public void setArtBarSect(Double artBarSect) {
        this.artBarSect = artBarSect;
    }

    public String getToActDesc() {
        return toActDesc;
    }

    public void setToActDesc(String toActDesc) {
        this.toActDesc = toActDesc;
    }

    public Double getToActHeight() {
        return toActHeight;
    }

    public void setToActHeight(Double toActHeight) {
        this.toActHeight = toActHeight;
    }

    public String getToActObs() {
        return toActObs;
    }

    public void setToActObs(String toActObs) {
        this.toActObs = toActObs;
    }

    public Integer getHasPapHolder() {
        return hasPapHolder;
    }

    public void setHasPapHolder(Integer hasPapHolder) {
        this.hasPapHolder = hasPapHolder;
    }

    public Integer getPapHolderType() {
        return papHolderType;
    }

    public void setPapHolderType(Integer papHolderType) {
        this.papHolderType = papHolderType;
    }

    public Double getPapEmbDist() {
        return papEmbDist;
    }

    public void setPapEmbDist(Double papEmbDist) {
        this.papEmbDist = papEmbDist;
    }

    public Double getPapEmbHeight() {
        return papEmbHeight;
    }

    public void setPapEmbHeight(Double papEmbHeight) {
        this.papEmbHeight = papEmbHeight;
    }

    public Integer getPapSupAlign() {
        return papSupAlign;
    }

    public void setPapSupAlign(Integer papSupAlign) {
        this.papSupAlign = papSupAlign;
    }

    public Double getPapSupHeight() {
        return papSupHeight;
    }

    public void setPapSupHeight(Double papSupHeight) {
        this.papSupHeight = papSupHeight;
    }

    public String getPapHoldObs() {
        return papHoldObs;
    }

    public void setPapHoldObs(String papHoldObs) {
        this.papHoldObs = papHoldObs;
    }

    public Integer getHasDouche() {
        return hasDouche;
    }

    public void setHasDouche(Integer hasDouche) {
        this.hasDouche = hasDouche;
    }

    public Double getDouchePressHeight() {
        return douchePressHeight;
    }

    public void setDouchePressHeight(Double douchePressHeight) {
        this.douchePressHeight = douchePressHeight;
    }

    public Double getDoucheActHeight() {
        return doucheActHeight;
    }

    public void setDoucheActHeight(Double doucheActHeight) {
        this.doucheActHeight = doucheActHeight;
    }

    public String getDoucheObs() {
        return doucheObs;
    }

    public void setDoucheObs(String doucheObs) {
        this.doucheObs = doucheObs;
    }

    public String getToiletObs() {
        return toiletObs;
    }

    public void setToiletObs(String toiletObs) {
        this.toiletObs = toiletObs;
    }

    public Integer getHasHanger() {
        return hasHanger;
    }

    public void setHasHanger(Integer hasHanger) {
        this.hasHanger = hasHanger;
    }

    public Double getHangerHeight() {
        return hangerHeight;
    }

    public void setHangerHeight(Double hangerHeight) {
        this.hangerHeight = hangerHeight;
    }

    public String getHangerObs() {
        return hangerObs;
    }

    public void setHangerObs(String hangerObs) {
        this.hangerObs = hangerObs;
    }

    public Integer getHasObjHold() {
        return hasObjHold;
    }

    public void setHasObjHold(Integer hasObjHold) {
        this.hasObjHold = hasObjHold;
    }

    public Integer getObjHoldCorrect() {
        return objHoldCorrect;
    }

    public void setObjHoldCorrect(Integer objHoldCorrect) {
        this.objHoldCorrect = objHoldCorrect;
    }

    public Double getObjHoldHeight() {
        return objHoldHeight;
    }

    public void setObjHoldHeight(Double objHoldHeight) {
        this.objHoldHeight = objHoldHeight;
    }

    public String getObjHoldObs() {
        return objHoldObs;
    }

    public void setObjHoldObs(String objHoldObs) {
        this.objHoldObs = objHoldObs;
    }

    public Integer getHasSoapHold() {
        return hasSoapHold;
    }

    public void setHasSoapHold(Integer hasSoapHold) {
        this.hasSoapHold = hasSoapHold;
    }

    public Double getSoapHoldHeight() {
        return soapHoldHeight;
    }

    public void setSoapHoldHeight(Double soapHoldHeight) {
        this.soapHoldHeight = soapHoldHeight;
    }

    public String getSoapHoldObs() {
        return soapHoldObs;
    }

    public void setSoapHoldObs(String soapHoldObs) {
        this.soapHoldObs = soapHoldObs;
    }

    public Integer getHasTowelHold() {
        return hasTowelHold;
    }

    public void setHasTowelHold(Integer hasTowelHold) {
        this.hasTowelHold = hasTowelHold;
    }

    public Double getTowelHoldHeight() {
        return towelHoldHeight;
    }

    public void setTowelHoldHeight(Double towelHoldHeight) {
        this.towelHoldHeight = towelHoldHeight;
    }

    public String getTowelHoldObs() {
        return towelHoldObs;
    }

    public void setTowelHoldObs(String towelHoldObs) {
        this.towelHoldObs = towelHoldObs;
    }

    public Integer getHasEmergencyButton() {
        return hasEmergencyButton;
    }

    public void setHasEmergencyButton(Integer hasEmergencyButton) {
        this.hasEmergencyButton = hasEmergencyButton;
    }

    public Double getEmergencyHeight() {
        return emergencyHeight;
    }

    public void setEmergencyHeight(Double emergencyHeight) {
        this.emergencyHeight = emergencyHeight;
    }

    public String getEmergencyObs() {
        return emergencyObs;
    }

    public void setEmergencyObs(String emergencyObs) {
        this.emergencyObs = emergencyObs;
    }

    public Integer getHasWaterValve() {
        return hasWaterValve;
    }

    public void setHasWaterValve(Integer hasWaterValve) {
        this.hasWaterValve = hasWaterValve;
    }

    public Integer getWaterValveType() {
        return waterValveType;
    }

    public void setWaterValveType(Integer waterValveType) {
        this.waterValveType = waterValveType;
    }

    public Double getWaterValveHeight() {
        return waterValveHeight;
    }

    public void setWaterValveHeight(Double waterValveHeight) {
        this.waterValveHeight = waterValveHeight;
    }

    public String getWaterValveObs() {
        return waterValveObs;
    }

    public void setWaterValveObs(String waterValveObs) {
        this.waterValveObs = waterValveObs;
    }

    public Integer getHasWindow() {
        return hasWindow;
    }

    public void setHasWindow(Integer hasWindow) {
        this.hasWindow = hasWindow;
    }

    public Integer getHasWallMirror() {
        return hasWallMirror;
    }

    public void setHasWallMirror(Integer hasWallMirror) {
        this.hasWallMirror = hasWallMirror;
    }

    public Double getWallMirrorLow() {
        return wallMirrorLow;
    }

    public void setWallMirrorLow(Double wallMirrorLow) {
        this.wallMirrorLow = wallMirrorLow;
    }

    public Double getWallMirrorHigh() {
        return wallMirrorHigh;
    }

    public void setWallMirrorHigh(Double wallMirrorHigh) {
        this.wallMirrorHigh = wallMirrorHigh;
    }

    public String getWallMirrorObs() {
        return wallMirrorObs;
    }

    public void setWallMirrorObs(String wallMirrorObs) {
        this.wallMirrorObs = wallMirrorObs;
    }

    public Integer getSinkType() {
        return sinkType;
    }

    public void setSinkType(Integer sinkType) {
        this.sinkType = sinkType;
    }

    public Double getApproxMeasureA() {
        return approxMeasureA;
    }

    public void setApproxMeasureA(Double approxMeasureA) {
        this.approxMeasureA = approxMeasureA;
    }

    public Double getApproxMeasureB() {
        return approxMeasureB;
    }

    public void setApproxMeasureB(Double approxMeasureB) {
        this.approxMeasureB = approxMeasureB;
    }

    public Double getApproxMeasureC() {
        return approxMeasureC;
    }

    public void setApproxMeasureC(Double approxMeasureC) {
        this.approxMeasureC = approxMeasureC;
    }

    public Double getApproxMeasureD() {
        return approxMeasureD;
    }

    public void setApproxMeasureD(Double approxMeasureD) {
        this.approxMeasureD = approxMeasureD;
    }

    public Double getApproxMeasureE() {
        return approxMeasureE;
    }

    public void setApproxMeasureE(Double approxMeasureE) {
        this.approxMeasureE = approxMeasureE;
    }

    public Integer getHasSinkBar() {
        return hasSinkBar;
    }

    public void setHasSinkBar(Integer hasSinkBar) {
        this.hasSinkBar = hasSinkBar;
    }

    public Integer getHasLeftFrontHorBar() {
        return hasLeftFrontHorBar;
    }

    public void setHasLeftFrontHorBar(Integer hasLeftFrontHorBar) {
        this.hasLeftFrontHorBar = hasLeftFrontHorBar;
    }

    public Double getLeftFrontHorMeasureA() {
        return leftFrontHorMeasureA;
    }

    public void setLeftFrontHorMeasureA(Double leftFrontHorMeasureA) {
        this.leftFrontHorMeasureA = leftFrontHorMeasureA;
    }

    public Double getLeftFrontHorMeasureB() {
        return leftFrontHorMeasureB;
    }

    public void setLeftFrontHorMeasureB(Double leftFrontHorMeasureB) {
        this.leftFrontHorMeasureB = leftFrontHorMeasureB;
    }

    public Double getLeftFrontHorMeasureC() {
        return leftFrontHorMeasureC;
    }

    public void setLeftFrontHorMeasureC(Double leftFrontHorMeasureC) {
        this.leftFrontHorMeasureC = leftFrontHorMeasureC;
    }

    public Double getLeftFrontHorMeasureD() {
        return leftFrontHorMeasureD;
    }

    public void setLeftFrontHorMeasureD(Double leftFrontHorMeasureD) {
        this.leftFrontHorMeasureD = leftFrontHorMeasureD;
    }

    public Double getLeftFrontHorDiam() {
        return leftFrontHorDiam;
    }

    public void setLeftFrontHorDiam(Double leftFrontHorDiam) {
        this.leftFrontHorDiam = leftFrontHorDiam;
    }

    public Double getLeftFrontHorDist() {
        return leftFrontHorDist;
    }

    public void setLeftFrontHorDist(Double leftFrontHorDist) {
        this.leftFrontHorDist = leftFrontHorDist;
    }

    public String getLeftFrontHorObs() {
        return leftFrontHorObs;
    }

    public void setLeftFrontHorObs(String leftFrontHorObs) {
        this.leftFrontHorObs = leftFrontHorObs;
    }

    public Integer getHasRightSideVertBar() {
        return hasRightSideVertBar;
    }

    public void setHasRightSideVertBar(Integer hasRightSideVertBar) {
        this.hasRightSideVertBar = hasRightSideVertBar;
    }

    public Double getRightSideVertMeasureA() {
        return rightSideVertMeasureA;
    }

    public void setRightSideVertMeasureA(Double rightSideVertMeasureA) {
        this.rightSideVertMeasureA = rightSideVertMeasureA;
    }

    public Double getRightSideVertMeasureB() {
        return rightSideVertMeasureB;
    }

    public void setRightSideVertMeasureB(Double rightSideVertMeasureB) {
        this.rightSideVertMeasureB = rightSideVertMeasureB;
    }

    public Double getRightSideVertMeasureC() {
        return rightSideVertMeasureC;
    }

    public void setRightSideVertMeasureC(Double rightSideVertMeasureC) {
        this.rightSideVertMeasureC = rightSideVertMeasureC;
    }

    public Double getRightSideVertMeasureD() {
        return rightSideVertMeasureD;
    }

    public void setRightSideVertMeasureD(Double rightSideVertMeasureD) {
        this.rightSideVertMeasureD = rightSideVertMeasureD;
    }

    public Double getRightSideVertMeasureE() {
        return rightSideVertMeasureE;
    }

    public void setRightSideVertMeasureE(Double rightSideVertMeasureE) {
        this.rightSideVertMeasureE = rightSideVertMeasureE;
    }

    public Double getRightSideVertDiam() {
        return rightSideVertDiam;
    }

    public void setRightSideVertDiam(Double rightSideVertDiam) {
        this.rightSideVertDiam = rightSideVertDiam;
    }

    public Double getRightSideVertDist() {
        return rightSideVertDist;
    }

    public void setRightSideVertDist(Double rightSideVertDist) {
        this.rightSideVertDist = rightSideVertDist;
    }

    public String getRightSideVertObs() {
        return rightSideVertObs;
    }

    public void setRightSideVertObs(String rightSideVertObs) {
        this.rightSideVertObs = rightSideVertObs;
    }

    public Integer getSinkHasMirror() {
        return sinkHasMirror;
    }

    public void setSinkHasMirror(Integer sinkHasMirror) {
        this.sinkHasMirror = sinkHasMirror;
    }

    public Double getSinkMirrorLow() {
        return sinkMirrorLow;
    }

    public void setSinkMirrorLow(Double sinkMirrorLow) {
        this.sinkMirrorLow = sinkMirrorLow;
    }

    public Double getSinkMirrorHigh() {
        return sinkMirrorHigh;
    }

    public void setSinkMirrorHigh(Double sinkMirrorHigh) {
        this.sinkMirrorHigh = sinkMirrorHigh;
    }

    public String getSinkObs() {
        return sinkObs;
    }

    public void setSinkObs(String sinkObs) {
        this.sinkObs = sinkObs;
    }

    public Integer getHasUrinal() {
        return hasUrinal;
    }

    public void setHasUrinal(Integer hasUrinal) {
        this.hasUrinal = hasUrinal;
    }

    public Integer getHasAccessUrinal() {
        return hasAccessUrinal;
    }

    public void setHasAccessUrinal(Integer hasAccessUrinal) {
        this.hasAccessUrinal = hasAccessUrinal;
    }

    public Integer getUrinalType() {
        return urinalType;
    }

    public void setUrinalType(Integer urinalType) {
        this.urinalType = urinalType;
    }

    public Double getUrMeasureA() {
        return urMeasureA;
    }

    public void setUrMeasureA(Double urMeasureA) {
        this.urMeasureA = urMeasureA;
    }

    public Double getUrMeasureB() {
        return urMeasureB;
    }

    public void setUrMeasureB(Double urMeasureB) {
        this.urMeasureB = urMeasureB;
    }

    public Double getUrMeasureC() {
        return urMeasureC;
    }

    public void setUrMeasureC(Double urMeasureC) {
        this.urMeasureC = urMeasureC;
    }

    public Double getUrMeasureD() {
        return urMeasureD;
    }

    public void setUrMeasureD(Double urMeasureD) {
        this.urMeasureD = urMeasureD;
    }

    public Double getUrMeasureE() {
        return urMeasureE;
    }

    public void setUrMeasureE(Double urMeasureE) {
        this.urMeasureE = urMeasureE;
    }

    public Double getUrMeasureF() {
        return urMeasureF;
    }

    public void setUrMeasureF(Double urMeasureF) {
        this.urMeasureF = urMeasureF;
    }

    public Double getUrMeasureG() {
        return urMeasureG;
    }

    public void setUrMeasureG(Double urMeasureG) {
        this.urMeasureG = urMeasureG;
    }

    public Double getUrMeasureH() {
        return urMeasureH;
    }

    public void setUrMeasureH(Double urMeasureH) {
        this.urMeasureH = urMeasureH;
    }

    public Double getUrMeasureI() {
        return urMeasureI;
    }

    public void setUrMeasureI(Double urMeasureI) {
        this.urMeasureI = urMeasureI;
    }

    public Double getUrMeasureJ() {
        return urMeasureJ;
    }

    public void setUrMeasureJ(Double urMeasureJ) {
        this.urMeasureJ = urMeasureJ;
    }

    public Double getUrMeasureK() {
        return urMeasureK;
    }

    public void setUrMeasureK(Double urMeasureK) {
        this.urMeasureK = urMeasureK;
    }

    public Double getUrMeasureL() {
        return urMeasureL;
    }

    public void setUrMeasureL(Double urMeasureL) {
        this.urMeasureL = urMeasureL;
    }

    public Double getUrMeasureM() {
        return urMeasureM;
    }

    public void setUrMeasureM(Double urMeasureM) {
        this.urMeasureM = urMeasureM;
    }

    public String getUrObs() {
        return urObs;
    }

    public void setUrObs(String urObs) {
        this.urObs = urObs;
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

    public Integer getHasLowerColSink() {
        return hasLowerColSink;
    }

    public void setHasLowerColSink(Integer hasLowerColSink) {
        this.hasLowerColSink = hasLowerColSink;
    }

    public Integer getHasSideBar() {
        return hasSideBar;
    }

    public void setHasSideBar(Integer hasSideBar) {
        this.hasSideBar = hasSideBar;
    }

    public Double getSideBarD() {
        return sideBarD;
    }

    public void setSideBarD(Double sideBarD) {
        this.sideBarD = sideBarD;
    }

    public Double getSideBarE() {
        return sideBarE;
    }

    public void setSideBarE(Double sideBarE) {
        this.sideBarE = sideBarE;
    }

    public Double getSideBarDistG() {
        return sideBarDistG;
    }

    public void setSideBarDistG(Double sideBarDistG) {
        this.sideBarDistG = sideBarDistG;
    }

    public Double getSideBarSect() {
        return sideBarSect;
    }

    public void setSideBarSect(Double sideBarSect) {
        this.sideBarSect = sideBarSect;
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
}
