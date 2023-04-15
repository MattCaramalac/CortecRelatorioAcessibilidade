package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = RestroomEntry.class, parentColumns = "restroomID", childColumns = "restID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = RestBoxEntry.class, parentColumns = "boxID", childColumns = "boxID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE)})
public class DoorEntry {

    @PrimaryKey(autoGenerate = true)
    private int doorID;
    private Integer roomID;
    private Integer restID;
    private Integer boxID;
    private Integer circID;

    private String doorLocation;
    private Integer doorType;
    private Double doorWidth1;
    private Double doorWidth2;
    private Integer doorHasPict;
    private String doorPictObs;
    private Integer opDirection;
    private String opDirectionObs;
    private Integer doorHandleType;
    private Double doorHandleHeight;
    private String doorHandleObs;
    private Integer doorHasLocks;
    private Integer doorHasHorBar;
    private Double horBarHeight;
    private Double horBarLength;
    private Double horBarFrameDist;
    private Double horBarDiam;
    private Double horBarDoorDist;
    private String horBarObs;
    private Integer doorHasWindow;
    private Double doorWinInfHeight;
    private Double doorWinSupHeight;
    private Double doorWinWidth;
    private String doorWinObs;
    private Integer doorHasTactSign;
    private Double tactSignHeight;
    private Double tactSignIncl;
    private String tactSignObs;
    private Integer doorSillType;
    private Double inclHeight;
    private Integer hasSillIncl;
    private Integer inclQnt;
    private Double inclAngle1;
    private Double inclAngle2;
    private Double inclAngle3;
    private Double inclAngle4;
    private Double stepHeight;
    private Integer slopeQnt;
    private Double slopeAngle1;
    private Double slopeAngle2;
    private Double slopeAngle3;
    private Double slopeAngle4;
    private Double slopeWidth;
    private Double slopeHeight;
    private String doorSillObs;
    private String doorObs;
    private String doorPhotos;

    public DoorEntry(Integer roomID, Integer restID, Integer boxID, Integer circID, String doorLocation, Integer doorType, Double doorWidth1, Double doorWidth2, Integer doorHasPict, String doorPictObs,
                     Integer opDirection, String opDirectionObs, Integer doorHandleType, Double doorHandleHeight, String doorHandleObs, Integer doorHasLocks,
                     Integer doorHasHorBar, Double horBarHeight, Double horBarLength, Double horBarFrameDist, Double horBarDiam, Double horBarDoorDist, String horBarObs,
                     Integer doorHasWindow, Double doorWinInfHeight, Double doorWinSupHeight, Double doorWinWidth, String doorWinObs, Integer doorHasTactSign,
                     Double tactSignHeight, Double tactSignIncl, String tactSignObs, Integer doorSillType, Double inclHeight, Integer inclQnt, Double inclAngle1,
                     Double inclAngle2, Double inclAngle3, Double inclAngle4, Double stepHeight, Integer slopeQnt, Double slopeAngle1, Double slopeAngle2, Double slopeAngle3,
                     Double slopeAngle4, Double slopeWidth, Double slopeHeight, String doorSillObs, String doorObs, Integer hasSillIncl, String doorPhotos) {
        this.roomID = roomID;
        this.restID = restID;
        this.boxID = boxID;
        this.circID = circID;
        this.doorLocation = doorLocation;
        this.doorType = doorType;
        this.doorWidth1 = doorWidth1;
        this.doorWidth2 = doorWidth2;
        this.doorHasPict = doorHasPict;
        this.doorPictObs = doorPictObs;
        this.opDirection = opDirection;
        this.opDirectionObs = opDirectionObs;
        this.doorHandleType = doorHandleType;
        this.doorHandleHeight = doorHandleHeight;
        this.doorHandleObs = doorHandleObs;
        this.doorHasLocks = doorHasLocks;
        this.doorHasHorBar = doorHasHorBar;
        this.horBarHeight = horBarHeight;
        this.horBarLength = horBarLength;
        this.horBarFrameDist = horBarFrameDist;
        this.horBarDiam = horBarDiam;
        this.horBarDoorDist = horBarDoorDist;
        this.horBarObs = horBarObs;
        this.doorHasWindow = doorHasWindow;
        this.doorWinInfHeight = doorWinInfHeight;
        this.doorWinSupHeight = doorWinSupHeight;
        this.doorWinWidth = doorWinWidth;
        this.doorWinObs = doorWinObs;
        this.doorHasTactSign = doorHasTactSign;
        this.tactSignHeight = tactSignHeight;
        this.tactSignIncl = tactSignIncl;
        this.tactSignObs = tactSignObs;
        this.doorSillType = doorSillType;
        this.inclHeight = inclHeight;
        this.inclQnt = inclQnt;
        this.inclAngle1 = inclAngle1;
        this.inclAngle2 = inclAngle2;
        this.inclAngle3 = inclAngle3;
        this.inclAngle4 = inclAngle4;
        this.stepHeight = stepHeight;
        this.slopeQnt = slopeQnt;
        this.slopeAngle1 = slopeAngle1;
        this.slopeAngle2 = slopeAngle2;
        this.slopeAngle3 = slopeAngle3;
        this.slopeAngle4 = slopeAngle4;
        this.slopeWidth = slopeWidth;
        this.slopeHeight = slopeHeight;
        this.doorSillObs = doorSillObs;
        this.doorObs = doorObs;
        this.hasSillIncl = hasSillIncl;
        this.doorPhotos = doorPhotos;
    }

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public Integer getRestID() {
        return restID;
    }

    public void setRestID(Integer restID) {
        this.restID = restID;
    }

    public Integer getBoxID() {
        return boxID;
    }

    public void setBoxID(Integer boxID) {
        this.boxID = boxID;
    }

    public Integer getCircID() {
        return circID;
    }

    public void setCircID(Integer circID) {
        this.circID = circID;
    }

    public String getDoorLocation() {
        return doorLocation;
    }

    public void setDoorLocation(String doorLocation) {
        this.doorLocation = doorLocation;
    }

    public Integer getDoorType() {
        return doorType;
    }

    public void setDoorType(Integer doorType) {
        this.doorType = doorType;
    }

    public Double getDoorWidth1() {
        return doorWidth1;
    }

    public void setDoorWidth1(Double doorWidth1) {
        this.doorWidth1 = doorWidth1;
    }

    public Double getDoorWidth2() {
        return doorWidth2;
    }

    public void setDoorWidth2(Double doorWidth2) {
        this.doorWidth2 = doorWidth2;
    }

    public Integer getDoorHasPict() {
        return doorHasPict;
    }

    public void setDoorHasPict(Integer doorHasPict) {
        this.doorHasPict = doorHasPict;
    }

    public String getDoorPictObs() {
        return doorPictObs;
    }

    public void setDoorPictObs(String doorPictObs) {
        this.doorPictObs = doorPictObs;
    }

    public Integer getDoorHandleType() {
        return doorHandleType;
    }

    public void setDoorHandleType(Integer doorHandleType) {
        this.doorHandleType = doorHandleType;
    }

    public Double getDoorHandleHeight() {
        return doorHandleHeight;
    }

    public void setDoorHandleHeight(Double doorHandleHeight) {
        this.doorHandleHeight = doorHandleHeight;
    }

    public String getDoorHandleObs() {
        return doorHandleObs;
    }

    public void setDoorHandleObs(String doorHandleObs) {
        this.doorHandleObs = doorHandleObs;
    }

    public Integer getDoorHasLocks() {
        return doorHasLocks;
    }

    public void setDoorHasLocks(Integer doorHasLocks) {
        this.doorHasLocks = doorHasLocks;
    }

    public Integer getDoorHasHorBar() {
        return doorHasHorBar;
    }

    public void setDoorHasHorBar(Integer doorHasHorBar) {
        this.doorHasHorBar = doorHasHorBar;
    }

    public Double getHorBarHeight() {
        return horBarHeight;
    }

    public void setHorBarHeight(Double horBarHeight) {
        this.horBarHeight = horBarHeight;
    }

    public Double getHorBarLength() {
        return horBarLength;
    }

    public void setHorBarLength(Double horBarLength) {
        this.horBarLength = horBarLength;
    }

    public Double getHorBarFrameDist() {
        return horBarFrameDist;
    }

    public void setHorBarFrameDist(Double horBarFrameDist) {
        this.horBarFrameDist = horBarFrameDist;
    }

    public Double getHorBarDiam() {
        return horBarDiam;
    }

    public void setHorBarDiam(Double horBarDiam) {
        this.horBarDiam = horBarDiam;
    }

    public Double getHorBarDoorDist() {
        return horBarDoorDist;
    }

    public void setHorBarDoorDist(Double horBarDoorDist) {
        this.horBarDoorDist = horBarDoorDist;
    }

    public String getHorBarObs() {
        return horBarObs;
    }

    public void setHorBarObs(String horBarObs) {
        this.horBarObs = horBarObs;
    }

    public Integer getDoorHasTactSign() {
        return doorHasTactSign;
    }

    public void setDoorHasTactSign(Integer doorHasTactSign) {
        this.doorHasTactSign = doorHasTactSign;
    }

    public Double getTactSignHeight() {
        return tactSignHeight;
    }

    public void setTactSignHeight(Double tactSignHeight) {
        this.tactSignHeight = tactSignHeight;
    }

    public Double getTactSignIncl() {
        return tactSignIncl;
    }

    public void setTactSignIncl(Double tactSignIncl) {
        this.tactSignIncl = tactSignIncl;
    }

    public String getTactSignObs() {
        return tactSignObs;
    }

    public void setTactSignObs(String tactSignObs) {
        this.tactSignObs = tactSignObs;
    }

    public Integer getDoorSillType() {
        return doorSillType;
    }

    public void setDoorSillType(Integer doorSillType) {
        this.doorSillType = doorSillType;
    }

    public Double getInclHeight() {
        return inclHeight;
    }

    public void setInclHeight(Double inclHeight) {
        this.inclHeight = inclHeight;
    }

    public Integer getInclQnt() {
        return inclQnt;
    }

    public void setInclQnt(Integer inclQnt) {
        this.inclQnt = inclQnt;
    }

    public Double getInclAngle1() {
        return inclAngle1;
    }

    public void setInclAngle1(Double inclAngle1) {
        this.inclAngle1 = inclAngle1;
    }

    public Double getInclAngle2() {
        return inclAngle2;
    }

    public void setInclAngle2(Double inclAngle2) {
        this.inclAngle2 = inclAngle2;
    }

    public Double getInclAngle3() {
        return inclAngle3;
    }

    public void setInclAngle3(Double inclAngle3) {
        this.inclAngle3 = inclAngle3;
    }

    public Double getInclAngle4() {
        return inclAngle4;
    }

    public void setInclAngle4(Double inclAngle4) {
        this.inclAngle4 = inclAngle4;
    }

    public Double getStepHeight() {
        return stepHeight;
    }

    public void setStepHeight(Double stepHeight) {
        this.stepHeight = stepHeight;
    }

    public Integer getSlopeQnt() {
        return slopeQnt;
    }

    public void setSlopeQnt(Integer slopeQnt) {
        this.slopeQnt = slopeQnt;
    }

    public Double getSlopeAngle1() {
        return slopeAngle1;
    }

    public void setSlopeAngle1(Double slopeAngle1) {
        this.slopeAngle1 = slopeAngle1;
    }

    public Double getSlopeAngle2() {
        return slopeAngle2;
    }

    public void setSlopeAngle2(Double slopeAngle2) {
        this.slopeAngle2 = slopeAngle2;
    }

    public Double getSlopeAngle3() {
        return slopeAngle3;
    }

    public void setSlopeAngle3(Double slopeAngle3) {
        this.slopeAngle3 = slopeAngle3;
    }

    public Double getSlopeAngle4() {
        return slopeAngle4;
    }

    public void setSlopeAngle4(Double slopeAngle4) {
        this.slopeAngle4 = slopeAngle4;
    }

    public Double getSlopeWidth() {
        return slopeWidth;
    }

    public void setSlopeWidth(Double slopeWidth) {
        this.slopeWidth = slopeWidth;
    }

    public Double getSlopeHeight() {
        return slopeHeight;
    }

    public void setSlopeHeight(Double slopeHeight) {
        this.slopeHeight = slopeHeight;
    }

    public String getDoorSillObs() {
        return doorSillObs;
    }

    public void setDoorSillObs(String doorSillObs) {
        this.doorSillObs = doorSillObs;
    }

    public String getDoorObs() {
        return doorObs;
    }

    public void setDoorObs(String doorObs) {
        this.doorObs = doorObs;
    }

    public Integer getOpDirection() {
        return opDirection;
    }

    public void setOpDirection(Integer opDirection) {
        this.opDirection = opDirection;
    }

    public String getOpDirectionObs() {
        return opDirectionObs;
    }

    public void setOpDirectionObs(String opDirectionObs) {
        this.opDirectionObs = opDirectionObs;
    }

    public Integer getDoorHasWindow() {
        return doorHasWindow;
    }

    public void setDoorHasWindow(Integer doorHasWindow) {
        this.doorHasWindow = doorHasWindow;
    }

    public Double getDoorWinInfHeight() {
        return doorWinInfHeight;
    }

    public void setDoorWinInfHeight(Double doorWinInfHeight) {
        this.doorWinInfHeight = doorWinInfHeight;
    }

    public Double getDoorWinSupHeight() {
        return doorWinSupHeight;
    }

    public void setDoorWinSupHeight(Double doorWinSupHeight) {
        this.doorWinSupHeight = doorWinSupHeight;
    }

    public Double getDoorWinWidth() {
        return doorWinWidth;
    }

    public void setDoorWinWidth(Double doorWinWidth) {
        this.doorWinWidth = doorWinWidth;
    }

    public String getDoorWinObs() {
        return doorWinObs;
    }

    public void setDoorWinObs(String doorWinObs) {
        this.doorWinObs = doorWinObs;
    }

    public Integer getHasSillIncl() {
        return hasSillIncl;
    }

    public void setHasSillIncl(Integer hasSillIncl) {
        this.hasSillIncl = hasSillIncl;
    }

    public String getDoorPhotos() {
        return doorPhotos;
    }

    public void setDoorPhotos(String doorPhotos) {
        this.doorPhotos = doorPhotos;
    }
}
