package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
                onDelete = CASCADE, onUpdate = CASCADE))
public class RestroomEntry {

    @PrimaryKey(autoGenerate = true)
    private int restroomID;
    private int blockID;
    private Integer restType;
    private String restLocation;
    private Integer accessRoute;
    private String accessRouteObs;
    private Integer intRestroom;
    private String intRestObs;
    private Integer restDistance;
    private String restDistObs;
    private Integer exEntrance;
    private String exEntObs;
    private Integer antiDriftFloor;
    private String antiDriftFloorObs;
    private Integer restDrain;
    private String restDrainObs;
    private Integer restSwitch;
    private Double switchHeight;
    private String switchObs;
    private Double doorWidth;
    private Integer hasPict;
    private String pictObs;
    private Integer opDir;
    private String opDirObs;
    private Integer hasCoat;
    private Double coatHeight;
    private String coatObs;
    private Integer hasVertSign;
    private String vertSignObs;
    private Integer sillType;
    private Double sillIncHeight;
    private Double sillStepHeight;
    private Integer sillSlopeQnt;
    private Double sillSlopeAngle1;
    private Double sillSlopeAngle2;
    private Double sillSlopeAngle3;
    private Double sillSlopeAngle4;
    private Double sillSlopeWidth;
    private Double sillSlopeHeight;
    private String sillTypeObs;
    private Integer hasTactSign;
    private String tactSignObs;
    private Integer hasHorHandle;
    private Double handleHeight;
    private Double handleLength;
    private Double handleDiam;
    private String handleObs;
    private Double upViewLength;
    private Double upViewWidth;
    private Double upViewMeasureA;
    private Double upViewMeasureB;
    private Double upViewMeasureC;
    private Double upViewMeasureD;
    private Double upViewMeasureE;
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
    private Double doucheHeight;
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
    private Integer hasWallMirror;
    private Double wallMirrorLow;
    private Double wallMirrorHigh;
    private String wallMirrorObs;
    private Integer sinkType;
    private Double approxMeasureA;
    private Double approxMeasureB;
    private Double approxMeasureC;
    private Double approxMeasureD;
    private Double approxMeasureE;
    private Integer hasSinkBar;
    private Integer hasLeftBar;
    private Double leftHorMeasureA;
    private Double leftHorMeasureB;
    private Double leftHorMeasureC;
    private Double leftHorMeasureD;
    private Double leftVertMeasureA;
    private Double leftVertMeasureB;
    private Double leftVertMeasureC;
    private Double leftVertMeasureD;
    private Double leftVertMeasureE;
    private Double leftBarDiam;
    private Double leftBarDist;
    private Integer hasRightBar;
    private Double rightHorMeasureA;
    private Double rightHorMeasureB;
    private Double rightHorMeasureC;
    private Double rightHorMeasureD;
    private Double rightVertMeasureA;
    private Double rightBarDiam;
    private Double rightBarDist;
    private Integer sinkHasMirror;
    private Double siMirrorLow;
    private Double siMirrorHigh;
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

    public RestroomEntry(int blockID, Integer restType, String restLocation, Integer accessRoute, String accessRouteObs, Integer intRestroom, String intRestObs, Integer restDistance,
                         String restDistObs, Integer exEntrance, String exEntObs, Integer antiDriftFloor, String antiDriftFloorObs, Integer restDrain, String restDrainObs, Integer restSwitch,
                         Double switchHeight, String switchObs, Double doorWidth, Integer hasPict, String pictObs, Integer opDir, String opDirObs, Integer hasCoat, Double coatHeight, String coatObs,
                         Integer hasVertSign, String vertSignObs, Integer sillType, Double sillIncHeight, Double sillStepHeight, Integer sillSlopeQnt, Double sillSlopeAngle1, Double sillSlopeAngle2,
                         Double sillSlopeAngle3, Double sillSlopeAngle4, Double sillSlopeWidth, Double sillSlopeHeight, String sillTypeObs, Integer hasTactSign, String tactSignObs, Integer hasHorHandle, Double handleHeight,
                         Double handleLength, Double handleDiam, String handleObs, Double upViewLength, Double upViewWidth, Double upViewMeasureA, Double upViewMeasureB, Double upViewMeasureC,
                         Double upViewMeasureD, Double upViewMeasureE, String upViewObs, Integer toType, Double toHeightNoSeat, Integer toHasSeat, Double toHeightSeat, Integer toHasSoculo, Double frSoculo,
                         Double latSoculo, Integer socCorners, Integer toHasFrontBar, Double frBarA, Double frBarB, Double frBarC, Double frBarSect, Double frBarDist, Integer toHasWall, Integer hasHorBar,
                         Double horBarD, Double horBarE, Double horBarF, Double horBarDistG, Double horBarSect, Double horBarDist, Integer hasVertBar, Double vertBarH, Double vertBarI, Double vertBarJ,
                         Double vertBarSect, Double vertBarDist, Integer hasArtBar, Double artBarH, Double artBarI, Double artBarJ, Double artBarSect, String toActDesc, Double toActHeight, String toActObs,
                         Integer hasPapHolder, Integer papHolderType, Double papEmbDist, Double papEmbHeight, Integer papSupAlign, Double papSupHeight, String papHoldObs, Integer hasDouche, Double doucheHeight,
                         String doucheObs, String toiletObs, Integer hasHanger, Double hangerHeight, String hangerObs, Integer hasObjHold, Integer objHoldCorrect, Double objHoldHeight, String objHoldObs,
                         Integer hasSoapHold, Double soapHoldHeight, String soapHoldObs, Integer hasTowelHold, Double towelHoldHeight, String towelHoldObs, Integer hasWallMirror, Double wallMirrorLow,
                         Double wallMirrorHigh, String wallMirrorObs, Integer sinkType, Double approxMeasureA, Double approxMeasureB, Double approxMeasureC, Double approxMeasureD, Double approxMeasureE,
                         Integer hasSinkBar, Integer hasLeftBar, Double leftHorMeasureA, Double leftHorMeasureB, Double leftHorMeasureC, Double leftHorMeasureD, Double leftVertMeasureA, Double leftVertMeasureB,
                         Double leftVertMeasureC, Double leftVertMeasureD, Double leftVertMeasureE, Double leftBarDiam, Double leftBarDist, Integer hasRightBar, Double rightHorMeasureA, Double rightHorMeasureB,
                         Double rightHorMeasureC, Double rightHorMeasureD, Double rightVertMeasureA, Double rightBarDiam, Double rightBarDist, Integer sinkHasMirror, Double siMirrorLow, Double siMirrorHigh,
                         String sinkObs, Integer hasUrinal, Integer hasAccessUrinal, Integer urinalType, Double urMeasureA, Double urMeasureB, Double urMeasureC, Double urMeasureD, Double urMeasureE,
                         Double urMeasureF, Double urMeasureG, Double urMeasureH, Double urMeasureI, Double urMeasureJ, Double urMeasureK, Double urMeasureL, Double urMeasureM, String urObs) {
        this.blockID = blockID;
        this.restType = restType;
        this.restLocation = restLocation;
        this.accessRoute = accessRoute;
        this.accessRouteObs = accessRouteObs;
        this.intRestroom = intRestroom;
        this.intRestObs = intRestObs;
        this.restDistance = restDistance;
        this.restDistObs = restDistObs;
        this.exEntrance = exEntrance;
        this.exEntObs = exEntObs;
        this.antiDriftFloor = antiDriftFloor;
        this.antiDriftFloorObs = antiDriftFloorObs;
        this.restDrain = restDrain;
        this.restDrainObs = restDrainObs;
        this.restSwitch = restSwitch;
        this.switchHeight = switchHeight;
        this.switchObs = switchObs;
        this.doorWidth = doorWidth;
        this.hasPict = hasPict;
        this.pictObs = pictObs;
        this.opDir = opDir;
        this.opDirObs = opDirObs;
        this.hasCoat = hasCoat;
        this.coatHeight = coatHeight;
        this.coatObs = coatObs;
        this.hasVertSign = hasVertSign;
        this.vertSignObs = vertSignObs;
        this.sillType = sillType;
        this.sillIncHeight = sillIncHeight;
        this.sillStepHeight = sillStepHeight;
        this.sillSlopeQnt = sillSlopeQnt;
        this.sillSlopeAngle1 = sillSlopeAngle1;
        this.sillSlopeAngle2 = sillSlopeAngle2;
        this.sillSlopeAngle3 = sillSlopeAngle3;
        this.sillSlopeAngle4 = sillSlopeAngle4;
        this.sillSlopeWidth = sillSlopeWidth;
        this.sillSlopeHeight = sillSlopeHeight;
        this.sillTypeObs = sillTypeObs;
        this.hasTactSign = hasTactSign;
        this.tactSignObs = tactSignObs;
        this.hasHorHandle = hasHorHandle;
        this.handleHeight = handleHeight;
        this.handleLength = handleLength;
        this.handleDiam = handleDiam;
        this.handleObs = handleObs;
        this.upViewLength = upViewLength;
        this.upViewWidth = upViewWidth;
        this.upViewMeasureA = upViewMeasureA;
        this.upViewMeasureB = upViewMeasureB;
        this.upViewMeasureC = upViewMeasureC;
        this.upViewMeasureD = upViewMeasureD;
        this.upViewMeasureE = upViewMeasureE;
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
        this.doucheHeight = doucheHeight;
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
        this.hasWallMirror = hasWallMirror;
        this.wallMirrorLow = wallMirrorLow;
        this.wallMirrorHigh = wallMirrorHigh;
        this.wallMirrorObs = wallMirrorObs;
        this.sinkType = sinkType;
        this.approxMeasureA = approxMeasureA;
        this.approxMeasureB = approxMeasureB;
        this.approxMeasureC = approxMeasureC;
        this.approxMeasureD = approxMeasureD;
        this.approxMeasureE = approxMeasureE;
        this.hasSinkBar = hasSinkBar;
        this.hasLeftBar = hasLeftBar;
        this.leftHorMeasureA = leftHorMeasureA;
        this.leftHorMeasureB = leftHorMeasureB;
        this.leftHorMeasureC = leftHorMeasureC;
        this.leftHorMeasureD = leftHorMeasureD;
        this.leftVertMeasureA = leftVertMeasureA;
        this.leftVertMeasureB = leftVertMeasureB;
        this.leftVertMeasureC = leftVertMeasureC;
        this.leftVertMeasureD = leftVertMeasureD;
        this.leftVertMeasureE = leftVertMeasureE;
        this.leftBarDiam = leftBarDiam;
        this.leftBarDist = leftBarDist;
        this.hasRightBar = hasRightBar;
        this.rightHorMeasureA = rightHorMeasureA;
        this.rightHorMeasureB = rightHorMeasureB;
        this.rightHorMeasureC = rightHorMeasureC;
        this.rightHorMeasureD = rightHorMeasureD;
        this.rightVertMeasureA = rightVertMeasureA;
        this.rightBarDiam = rightBarDiam;
        this.rightBarDist = rightBarDist;
        this.sinkHasMirror = sinkHasMirror;
        this.siMirrorLow = siMirrorLow;
        this.siMirrorHigh = siMirrorHigh;
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

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int schoolID) {
        this.blockID = schoolID;
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

    public void setIntRestObs(String intRestObs) {
        this.intRestObs = intRestObs;
    }

    public Integer getRestDistance() {
        return restDistance;
    }

    public void setRestDistance(Integer restDistance) {
        this.restDistance = restDistance;
    }

    public String getRestDistObs() {
        return restDistObs;
    }

    public void setRestDistObs(String restDistObs) {
        this.restDistObs = restDistObs;
    }

    public Integer getExEntrance() {
        return exEntrance;
    }

    public void setExEntrance(Integer exEntrance) {
        this.exEntrance = exEntrance;
    }

    public String getExEntObs() {
        return exEntObs;
    }

    public void setExEntObs(String exEntObs) {
        this.exEntObs = exEntObs;
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

    public Double getDoorWidth() {
        return doorWidth;
    }

    public void setDoorWidth(Double doorWidth) {
        this.doorWidth = doorWidth;
    }

    public Integer getHasPict() {
        return hasPict;
    }

    public void setHasPict(Integer hasPict) {
        this.hasPict = hasPict;
    }

    public String getPictObs() {
        return pictObs;
    }

    public void setPictObs(String pictObs) {
        this.pictObs = pictObs;
    }

    public Integer getOpDir() {
        return opDir;
    }

    public void setOpDir(Integer opDir) {
        this.opDir = opDir;
    }

    public String getOpDirObs() {
        return opDirObs;
    }

    public void setOpDirObs(String opDirObs) {
        this.opDirObs = opDirObs;
    }

    public Integer getHasCoat() {
        return hasCoat;
    }

    public void setHasCoat(Integer hasCoat) {
        this.hasCoat = hasCoat;
    }

    public Double getCoatHeight() {
        return coatHeight;
    }

    public void setCoatHeight(Double coatHeight) {
        this.coatHeight = coatHeight;
    }

    public String getCoatObs() {
        return coatObs;
    }

    public void setCoatObs(String coatObs) {
        this.coatObs = coatObs;
    }

    public Integer getHasVertSign() {
        return hasVertSign;
    }

    public void setHasVertSign(Integer hasVertSign) {
        this.hasVertSign = hasVertSign;
    }

    public String getVertSignObs() {
        return vertSignObs;
    }

    public void setVertSignObs(String vertSignObs) {
        this.vertSignObs = vertSignObs;
    }

    public Integer getSillType() {
        return sillType;
    }

    public void setSillType(Integer sillType) {
        this.sillType = sillType;
    }

    public Double getSillIncHeight() {
        return sillIncHeight;
    }

    public void setSillIncHeight(Double sillIncHeight) {
        this.sillIncHeight = sillIncHeight;
    }

    public Double getSillStepHeight() {
        return sillStepHeight;
    }

    public void setSillStepHeight(Double sillStepHeight) {
        this.sillStepHeight = sillStepHeight;
    }

    public Integer getSillSlopeQnt() {
        return sillSlopeQnt;
    }

    public void setSillSlopeQnt(Integer sillSlopeQnt) {
        this.sillSlopeQnt = sillSlopeQnt;
    }

    public Double getSillSlopeAngle1() {
        return sillSlopeAngle1;
    }

    public void setSillSlopeAngle1(Double sillSlopeAngle1) {
        this.sillSlopeAngle1 = sillSlopeAngle1;
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

    public Double getSillSlopeWidth() {
        return sillSlopeWidth;
    }

    public void setSillSlopeWidth(Double sillSlopeWidth) {
        this.sillSlopeWidth = sillSlopeWidth;
    }

    public String getSillTypeObs() {
        return sillTypeObs;
    }

    public void setSillTypeObs(String sillTypeObs) {
        this.sillTypeObs = sillTypeObs;
    }

    public Integer getHasTactSign() {
        return hasTactSign;
    }

    public void setHasTactSign(Integer hasTactSign) {
        this.hasTactSign = hasTactSign;
    }

    public String getTactSignObs() {
        return tactSignObs;
    }

    public void setTactSignObs(String tactSignObs) {
        this.tactSignObs = tactSignObs;
    }

    public Integer getHasHorHandle() {
        return hasHorHandle;
    }

    public void setHasHorHandle(Integer hasHorHandle) {
        this.hasHorHandle = hasHorHandle;
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

    public Double getHandleDiam() {
        return handleDiam;
    }

    public void setHandleDiam(Double handleDiam) {
        this.handleDiam = handleDiam;
    }

    public String getHandleObs() {
        return handleObs;
    }

    public void setHandleObs(String handleObs) {
        this.handleObs = handleObs;
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

    public Double getUpViewMeasureE() {
        return upViewMeasureE;
    }

    public void setUpViewMeasureE(Double upViewMeasureE) {
        this.upViewMeasureE = upViewMeasureE;
    }

    public String getUpViewObs() {
        return upViewObs;
    }

    public void setUpViewObs(String upViewObs) {
        this.upViewObs = upViewObs;
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

    public Double getHorBarDistG() {
        return horBarDistG;
    }

    public void setHorBarDistG(Double horBarDistG) {
        this.horBarDistG = horBarDistG;
    }

    public Double getVertBarJ() {
        return vertBarJ;
    }

    public void setVertBarJ(Double vertBarJ) {
        this.vertBarJ = vertBarJ;
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

    public String getToiletObs() {
        return toiletObs;
    }

    public void setToiletObs(String toiletObs) {
        this.toiletObs = toiletObs;
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

    public Integer getHasDouche() {
        return hasDouche;
    }

    public void setHasDouche(Integer hasDouche) {
        this.hasDouche = hasDouche;
    }

    public Double getDoucheHeight() {
        return doucheHeight;
    }

    public void setDoucheHeight(Double doucheHeight) {
        this.doucheHeight = doucheHeight;
    }

    public String getDoucheObs() {
        return doucheObs;
    }

    public void setDoucheObs(String doucheObs) {
        this.doucheObs = doucheObs;
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

    public Integer getHasLeftBar() {
        return hasLeftBar;
    }

    public void setHasLeftBar(Integer hasLeftBar) {
        this.hasLeftBar = hasLeftBar;
    }

    public Double getLeftHorMeasureA() {
        return leftHorMeasureA;
    }

    public void setLeftHorMeasureA(Double leftHorMeasureA) {
        this.leftHorMeasureA = leftHorMeasureA;
    }

    public Double getLeftHorMeasureB() {
        return leftHorMeasureB;
    }

    public void setLeftHorMeasureB(Double leftHorMeasureB) {
        this.leftHorMeasureB = leftHorMeasureB;
    }

    public Double getLeftHorMeasureC() {
        return leftHorMeasureC;
    }

    public void setLeftHorMeasureC(Double leftHorMeasureC) {
        this.leftHorMeasureC = leftHorMeasureC;
    }

    public Double getLeftHorMeasureD() {
        return leftHorMeasureD;
    }

    public void setLeftHorMeasureD(Double leftHorMeasureD) {
        this.leftHorMeasureD = leftHorMeasureD;
    }

    public Double getLeftVertMeasureA() {
        return leftVertMeasureA;
    }

    public void setLeftVertMeasureA(Double leftVertMeasureA) {
        this.leftVertMeasureA = leftVertMeasureA;
    }

    public Double getLeftVertMeasureB() {
        return leftVertMeasureB;
    }

    public void setLeftVertMeasureB(Double leftVertMeasureB) {
        this.leftVertMeasureB = leftVertMeasureB;
    }

    public Double getLeftVertMeasureC() {
        return leftVertMeasureC;
    }

    public void setLeftVertMeasureC(Double leftVertMeasureC) {
        this.leftVertMeasureC = leftVertMeasureC;
    }

    public Double getLeftVertMeasureD() {
        return leftVertMeasureD;
    }

    public void setLeftVertMeasureD(Double leftVertMeasureD) {
        this.leftVertMeasureD = leftVertMeasureD;
    }

    public Double getLeftVertMeasureE() {
        return leftVertMeasureE;
    }

    public void setLeftVertMeasureE(Double leftVertMeasureE) {
        this.leftVertMeasureE = leftVertMeasureE;
    }

    public Double getLeftBarDiam() {
        return leftBarDiam;
    }

    public void setLeftBarDiam(Double leftBarDiam) {
        this.leftBarDiam = leftBarDiam;
    }

    public Double getLeftBarDist() {
        return leftBarDist;
    }

    public void setLeftBarDist(Double leftBarDist) {
        this.leftBarDist = leftBarDist;
    }

    public Integer getHasRightBar() {
        return hasRightBar;
    }

    public void setHasRightBar(Integer hasRightBar) {
        this.hasRightBar = hasRightBar;
    }

    public Double getRightHorMeasureA() {
        return rightHorMeasureA;
    }

    public void setRightHorMeasureA(Double rightHorMeasureA) {
        this.rightHorMeasureA = rightHorMeasureA;
    }

    public Double getRightHorMeasureB() {
        return rightHorMeasureB;
    }

    public void setRightHorMeasureB(Double rightHorMeasureB) {
        this.rightHorMeasureB = rightHorMeasureB;
    }

    public Double getRightHorMeasureC() {
        return rightHorMeasureC;
    }

    public void setRightHorMeasureC(Double rightHorMeasureC) {
        this.rightHorMeasureC = rightHorMeasureC;
    }

    public Double getRightHorMeasureD() {
        return rightHorMeasureD;
    }

    public void setRightHorMeasureD(Double rightHorMeasureD) {
        this.rightHorMeasureD = rightHorMeasureD;
    }

    public Double getRightVertMeasureA() {
        return rightVertMeasureA;
    }

    public void setRightVertMeasureA(Double rightVertMeasureA) {
        this.rightVertMeasureA = rightVertMeasureA;
    }

    public Double getRightBarDiam() {
        return rightBarDiam;
    }

    public void setRightBarDiam(Double rightBarDiam) {
        this.rightBarDiam = rightBarDiam;
    }

    public Double getRightBarDist() {
        return rightBarDist;
    }

    public void setRightBarDist(Double rightBarDist) {
        this.rightBarDist = rightBarDist;
    }

    public Integer getSinkHasMirror() {
        return sinkHasMirror;
    }

    public void setSinkHasMirror(Integer sinkHasMirror) {
        this.sinkHasMirror = sinkHasMirror;
    }

    public Double getSiMirrorLow() {
        return siMirrorLow;
    }

    public void setSiMirrorLow(Double siMirrorLow) {
        this.siMirrorLow = siMirrorLow;
    }

    public Double getSiMirrorHigh() {
        return siMirrorHigh;
    }

    public void setSiMirrorHigh(Double siMirrorHigh) {
        this.siMirrorHigh = siMirrorHigh;
    }

    public String getSinkObs() {
        return sinkObs;
    }

    public void setSinkObs(String sinkObs) {
        this.sinkObs = sinkObs;
    }

    public Integer getHasSinkBar() {
        return hasSinkBar;
    }

    public void setHasSinkBar(Integer hasSinkBar) {
        this.hasSinkBar = hasSinkBar;
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

    public Integer getToType() {
        return toType;
    }

    public void setToType(Integer toType) {
        this.toType = toType;
    }

    public Double getSillSlopeHeight() {
        return sillSlopeHeight;
    }

    public void setSillSlopeHeight(Double sillSlopeHeight) {
        this.sillSlopeHeight = sillSlopeHeight;
    }
}
