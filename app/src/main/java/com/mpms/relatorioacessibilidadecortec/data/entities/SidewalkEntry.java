package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class SidewalkEntry {

    @PrimaryKey(autoGenerate = true)
    private int sidewalkID;
    private int blockID;

    private String sidewalkLocation;
    private Integer streetPavement;
    private Integer hasSidewalk;
    private Double sidewalkWidth;
    private Double sideFreeSpaceWidth;
    private String sideMeasureObs;
    private Integer slopeMeasureQnt;
    private Double sideTransSlope1;
    private Double sideTransSlope2;
    private Double sideTransSlope3;
    private Double sideTransSlope4;
    private Double sideTransSlope5;
    private Double sideTransSlope6;
    private Integer hasSpecialFloor;
    private Integer specialFloorRightColor;
    private Double specialTileDirectionWidth;
    private Double specialTileAlertWidth;
    private String specialFloorObs;
    private String sidewalkObs;
    private Integer sideConStatus;
    private String sideConsObs;
    private Integer sideFloorIsAccessible;
    private String accessFloorObs;
    private Integer sidewalkHasLids;
    private String sidewalkLidDesc;
    private Integer hasAerialObstacle;
    private String aerialObstacleDesc;
    private Integer sideHasSlope;
    private Integer sideReqSlopes;
    private Integer sideHasPayphones;
    private String sidewalkObs2;
    private String sidePhotos;
    private String sidePhotos2;

    public SidewalkEntry(int blockID, String sidewalkLocation, Integer streetPavement, Integer hasSidewalk, Double sidewalkWidth, Double sideFreeSpaceWidth,
                         String sideMeasureObs, Integer slopeMeasureQnt, Double sideTransSlope1, Double sideTransSlope2, Double sideTransSlope3, Double sideTransSlope4,
                         Double sideTransSlope5, Double sideTransSlope6, Integer hasSpecialFloor, Integer specialFloorRightColor, Double specialTileDirectionWidth,
                         Double specialTileAlertWidth, String specialFloorObs, String sidewalkObs, Integer sideConStatus, String sideConsObs, Integer sideFloorIsAccessible,
                         String accessFloorObs, Integer sidewalkHasLids, String sidewalkLidDesc, Integer hasAerialObstacle, String aerialObstacleDesc, Integer sideHasSlope,
                         Integer sideReqSlopes, Integer sideHasPayphones, String sidewalkObs2, String sidePhotos, String sidePhotos2) {
        this.blockID = blockID;
        this.sidewalkLocation = sidewalkLocation;
        this.streetPavement = streetPavement;
        this.hasSidewalk = hasSidewalk;
        this.sidewalkWidth = sidewalkWidth;
        this.sideFreeSpaceWidth = sideFreeSpaceWidth;
        this.sideMeasureObs = sideMeasureObs;
        this.slopeMeasureQnt = slopeMeasureQnt;
        this.sideTransSlope1 = sideTransSlope1;
        this.sideTransSlope2 = sideTransSlope2;
        this.sideTransSlope3 = sideTransSlope3;
        this.sideTransSlope4 = sideTransSlope4;
        this.sideTransSlope5 = sideTransSlope5;
        this.sideTransSlope6 = sideTransSlope6;
        this.hasSpecialFloor = hasSpecialFloor;
        this.specialFloorRightColor = specialFloorRightColor;
        this.specialTileDirectionWidth = specialTileDirectionWidth;
        this.specialTileAlertWidth = specialTileAlertWidth;
        this.specialFloorObs = specialFloorObs;
        this.sidewalkObs = sidewalkObs;
        this.sideConStatus = sideConStatus;
        this.sideConsObs = sideConsObs;
        this.sideFloorIsAccessible = sideFloorIsAccessible;
        this.accessFloorObs = accessFloorObs;
        this.sidewalkHasLids = sidewalkHasLids;
        this.sidewalkLidDesc = sidewalkLidDesc;
        this.hasAerialObstacle = hasAerialObstacle;
        this.aerialObstacleDesc = aerialObstacleDesc;
        this.sideHasSlope = sideHasSlope;
        this.sideReqSlopes = sideReqSlopes;
        this.sideHasPayphones = sideHasPayphones;
        this.sidewalkObs2 = sidewalkObs2;
        this.sidePhotos = sidePhotos;
        this.sidePhotos2 = sidePhotos2;
    }

    public String getSidePhotos2() {
        return sidePhotos2;
    }

    public void setSidePhotos2(String sidePhotos2) {
        this.sidePhotos2 = sidePhotos2;
    }

    public int getSidewalkID() {
        return sidewalkID;
    }

    public void setSidewalkID(int sidewalkID) {
        this.sidewalkID = sidewalkID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public String getSidewalkLocation() {
        return sidewalkLocation;
    }

    public void setSidewalkLocation(String sidewalkLocation) {
        this.sidewalkLocation = sidewalkLocation;
    }

    public Integer getStreetPavement() {
        return streetPavement;
    }

    public void setStreetPavement(Integer streetPavement) {
        this.streetPavement = streetPavement;
    }

    public Double getSidewalkWidth() {
        return sidewalkWidth;
    }

    public void setSidewalkWidth(Double sidewalkWidth) {
        this.sidewalkWidth = sidewalkWidth;
    }

    public Double getSideFreeSpaceWidth() {
        return sideFreeSpaceWidth;
    }

    public void setSideFreeSpaceWidth(Double sideFreeSpaceWidth) {
        this.sideFreeSpaceWidth = sideFreeSpaceWidth;
    }

    public String getSideMeasureObs() {
        return sideMeasureObs;
    }

    public void setSideMeasureObs(String sideMeasureObs) {
        this.sideMeasureObs = sideMeasureObs;
    }

    public Integer getSlopeMeasureQnt() {
        return slopeMeasureQnt;
    }

    public void setSlopeMeasureQnt(Integer slopeMeasureQnt) {
        this.slopeMeasureQnt = slopeMeasureQnt;
    }

    public Double getSideTransSlope1() {
        return sideTransSlope1;
    }

    public void setSideTransSlope1(Double sideTransSlope1) {
        this.sideTransSlope1 = sideTransSlope1;
    }

    public Double getSideTransSlope2() {
        return sideTransSlope2;
    }

    public void setSideTransSlope2(Double sideTransSlope2) {
        this.sideTransSlope2 = sideTransSlope2;
    }

    public Double getSideTransSlope3() {
        return sideTransSlope3;
    }

    public void setSideTransSlope3(Double sideTransSlope3) {
        this.sideTransSlope3 = sideTransSlope3;
    }

    public Double getSideTransSlope4() {
        return sideTransSlope4;
    }

    public void setSideTransSlope4(Double sideTransSlope4) {
        this.sideTransSlope4 = sideTransSlope4;
    }

    public Double getSideTransSlope5() {
        return sideTransSlope5;
    }

    public void setSideTransSlope5(Double sideTransSlope5) {
        this.sideTransSlope5 = sideTransSlope5;
    }

    public Double getSideTransSlope6() {
        return sideTransSlope6;
    }

    public void setSideTransSlope6(Double sideTransSlope6) {
        this.sideTransSlope6 = sideTransSlope6;
    }

    public Integer getHasSpecialFloor() {
        return hasSpecialFloor;
    }

    public void setHasSpecialFloor(Integer hasSpecialFloor) {
        this.hasSpecialFloor = hasSpecialFloor;
    }

    public Integer getSpecialFloorRightColor() {
        return specialFloorRightColor;
    }

    public void setSpecialFloorRightColor(Integer specialFloorRightColor) {
        this.specialFloorRightColor = specialFloorRightColor;
    }

    public Double getSpecialTileDirectionWidth() {
        return specialTileDirectionWidth;
    }

    public void setSpecialTileDirectionWidth(Double specialTileDirectionWidth) {
        this.specialTileDirectionWidth = specialTileDirectionWidth;
    }

    public Double getSpecialTileAlertWidth() {
        return specialTileAlertWidth;
    }

    public void setSpecialTileAlertWidth(Double specialTileAlertWidth) {
        this.specialTileAlertWidth = specialTileAlertWidth;
    }

    public String getSpecialFloorObs() {
        return specialFloorObs;
    }

    public void setSpecialFloorObs(String specialFloorObs) {
        this.specialFloorObs = specialFloorObs;
    }

    public Integer getSideFloorIsAccessible() {
        return sideFloorIsAccessible;
    }

    public void setSideFloorIsAccessible(Integer sideFloorIsAccessible) {
        this.sideFloorIsAccessible = sideFloorIsAccessible;
    }

    public String getAccessFloorObs() {
        return accessFloorObs;
    }

    public void setAccessFloorObs(String accessFloorObs) {
        this.accessFloorObs = accessFloorObs;
    }

    public Integer getSideHasSlope() {
        return sideHasSlope;
    }

    public void setSideHasSlope(Integer sideHasSlope) {
        this.sideHasSlope = sideHasSlope;
    }

    public String getSidewalkObs() {
        return sidewalkObs;
    }

    public void setSidewalkObs(String sidewalkObs) {
        this.sidewalkObs = sidewalkObs;
    }

    public Integer getHasAerialObstacle() {
        return hasAerialObstacle;
    }

    public void setHasAerialObstacle(Integer hasAerialObstacle) {
        this.hasAerialObstacle = hasAerialObstacle;
    }

    public String getAerialObstacleDesc() {
        return aerialObstacleDesc;
    }

    public void setAerialObstacleDesc(String aerialObstacleDesc) {
        this.aerialObstacleDesc = aerialObstacleDesc;
    }

    public Integer getSidewalkHasLids() {
        return sidewalkHasLids;
    }

    public void setSidewalkHasLids(Integer sidewalkHasLids) {
        this.sidewalkHasLids = sidewalkHasLids;
    }

    public String getSidewalkLidDesc() {
        return sidewalkLidDesc;
    }

    public void setSidewalkLidDesc(String sidewalkLidDesc) {
        this.sidewalkLidDesc = sidewalkLidDesc;
    }

    public Integer getSideConStatus() {
        return sideConStatus;
    }

    public void setSideConStatus(Integer sideConStatus) {
        this.sideConStatus = sideConStatus;
    }

    public String getSideConsObs() {
        return sideConsObs;
    }

    public void setSideConsObs(String sideConsObs) {
        this.sideConsObs = sideConsObs;
    }

    public Integer getSideHasPayphones() {
        return sideHasPayphones;
    }

    public void setSideHasPayphones(Integer sideHasPayphones) {
        this.sideHasPayphones = sideHasPayphones;
    }

    public Integer getSideReqSlopes() {
        return sideReqSlopes;
    }

    public void setSideReqSlopes(Integer sideReqSlopes) {
        this.sideReqSlopes = sideReqSlopes;
    }

    public Integer getHasSidewalk() {
        return hasSidewalk;
    }

    public void setHasSidewalk(Integer hasSidewalk) {
        this.hasSidewalk = hasSidewalk;
    }

    public String getSidewalkObs2() {
        return sidewalkObs2;
    }

    public void setSidewalkObs2(String sidewalkObs2) {
        this.sidewalkObs2 = sidewalkObs2;
    }

    public String getSidePhotos() {
        return sidePhotos;
    }

    public void setSidePhotos(String sidePhotos) {
        this.sidePhotos = sidePhotos;
    }
}
