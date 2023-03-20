package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class RoomEntry {

    @PrimaryKey(autoGenerate = true)
    private int roomID;
    private int blockID;
    private int roomType;

    private String roomLocation;
    private String roomDescription;
    private Integer isWorkRoom;
    private Integer roomHasVertSing;
    private String roomVertSignObs;
    private Integer roomHasLooseCarpet;
    private String looseCarpetObs;
    private Integer roomAccessFloor;
    private String accessFloorObs;

    private Integer libDistShelvesOK;
    private Integer libHasLongCorridors;
    private Integer libHasManeuverArea;
    private Integer libHasPC;
    private Integer libHasAccessPC;

    private Integer secHasFixedSeats;
    private Integer secHasPcrSpace;
    private Double secPcrSpaceWidth;
    private Double secPcrSpaceDepth;
    private String secPCRSpaceObs;

    private Integer hasIntercom;
    private Double intercomHeight;
    private String intercomObs;
    private Integer hasBioClock;
    private Double bioClockHeight;
    private String bioClockObs;

    private String roomPhoto;
    private String roomObs;


    public RoomEntry(int blockID, int roomType, String roomLocation, String roomDescription, Integer isWorkRoom, Integer roomHasVertSing, String roomVertSignObs,
                     Integer roomHasLooseCarpet, String looseCarpetObs, Integer roomAccessFloor, String accessFloorObs, Integer libDistShelvesOK, Integer libHasLongCorridors,
                     Integer libHasManeuverArea, Integer libHasPC, Integer libHasAccessPC, Integer secHasFixedSeats, Integer secHasPcrSpace, Double secPcrSpaceWidth,
                     Double secPcrSpaceDepth, String secPCRSpaceObs, Integer hasIntercom, Double intercomHeight, String intercomObs, Integer hasBioClock,
                     Double bioClockHeight, String bioClockObs, String roomPhoto, String roomObs) {
        this.blockID = blockID;
        this.roomType = roomType;
        this.roomLocation = roomLocation;
        this.roomDescription = roomDescription;
        this.isWorkRoom = isWorkRoom;
        this.roomHasVertSing = roomHasVertSing;
        this.roomVertSignObs = roomVertSignObs;
        this.roomHasLooseCarpet = roomHasLooseCarpet;
        this.looseCarpetObs = looseCarpetObs;
        this.roomAccessFloor = roomAccessFloor;
        this.accessFloorObs = accessFloorObs;
        this.libDistShelvesOK = libDistShelvesOK;
        this.libHasLongCorridors = libHasLongCorridors;
        this.libHasManeuverArea = libHasManeuverArea;
        this.libHasPC = libHasPC;
        this.libHasAccessPC = libHasAccessPC;
        this.secHasFixedSeats = secHasFixedSeats;
        this.secHasPcrSpace = secHasPcrSpace;
        this.secPcrSpaceWidth = secPcrSpaceWidth;
        this.secPcrSpaceDepth = secPcrSpaceDepth;
        this.secPCRSpaceObs = secPCRSpaceObs;
        this.hasIntercom = hasIntercom;
        this.intercomHeight = intercomHeight;
        this.intercomObs = intercomObs;
        this.hasBioClock = hasBioClock;
        this.bioClockHeight = bioClockHeight;
        this.bioClockObs = bioClockObs;
        this.roomPhoto = roomPhoto;
        this.roomObs = roomObs;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Integer getIsWorkRoom() {
        return isWorkRoom;
    }

    public void setIsWorkRoom(Integer isWorkRoom) {
        this.isWorkRoom = isWorkRoom;
    }

    public Integer getRoomHasVertSing() {
        return roomHasVertSing;
    }

    public void setRoomHasVertSing(Integer roomHasVertSing) {
        this.roomHasVertSing = roomHasVertSing;
    }

    public String getRoomVertSignObs() {
        return roomVertSignObs;
    }

    public void setRoomVertSignObs(String roomVertSignObs) {
        this.roomVertSignObs = roomVertSignObs;
    }

    public Integer getRoomHasLooseCarpet() {
        return roomHasLooseCarpet;
    }

    public void setRoomHasLooseCarpet(Integer roomHasLooseCarpet) {
        this.roomHasLooseCarpet = roomHasLooseCarpet;
    }

    public String getLooseCarpetObs() {
        return looseCarpetObs;
    }

    public void setLooseCarpetObs(String looseCarpetObs) {
        this.looseCarpetObs = looseCarpetObs;
    }

    public Integer getRoomAccessFloor() {
        return roomAccessFloor;
    }

    public void setRoomAccessFloor(Integer roomAccessFloor) {
        this.roomAccessFloor = roomAccessFloor;
    }

    public String getAccessFloorObs() {
        return accessFloorObs;
    }

    public void setAccessFloorObs(String accessFloorObs) {
        this.accessFloorObs = accessFloorObs;
    }

    public Integer getLibDistShelvesOK() {
        return libDistShelvesOK;
    }

    public void setLibDistShelvesOK(Integer libDistShelvesOK) {
        this.libDistShelvesOK = libDistShelvesOK;
    }

    public Integer getLibHasLongCorridors() {
        return libHasLongCorridors;
    }

    public void setLibHasLongCorridors(Integer libHasLongCorridors) {
        this.libHasLongCorridors = libHasLongCorridors;
    }

    public Integer getLibHasManeuverArea() {
        return libHasManeuverArea;
    }

    public void setLibHasManeuverArea(Integer libHasManeuverArea) {
        this.libHasManeuverArea = libHasManeuverArea;
    }

    public Integer getLibHasPC() {
        return libHasPC;
    }

    public void setLibHasPC(Integer libHasPC) {
        this.libHasPC = libHasPC;
    }

    public Integer getLibHasAccessPC() {
        return libHasAccessPC;
    }

    public void setLibHasAccessPC(Integer libHasAccessPC) {
        this.libHasAccessPC = libHasAccessPC;
    }

    public Integer getSecHasFixedSeats() {
        return secHasFixedSeats;
    }

    public void setSecHasFixedSeats(Integer secHasFixedSeats) {
        this.secHasFixedSeats = secHasFixedSeats;
    }

    public Integer getSecHasPcrSpace() {
        return secHasPcrSpace;
    }

    public void setSecHasPcrSpace(Integer secHasPcrSpace) {
        this.secHasPcrSpace = secHasPcrSpace;
    }

    public Double getSecPcrSpaceWidth() {
        return secPcrSpaceWidth;
    }

    public void setSecPcrSpaceWidth(Double secPcrSpaceWidth) {
        this.secPcrSpaceWidth = secPcrSpaceWidth;
    }

    public Double getSecPcrSpaceDepth() {
        return secPcrSpaceDepth;
    }

    public void setSecPcrSpaceDepth(Double secPcrSpaceDepth) {
        this.secPcrSpaceDepth = secPcrSpaceDepth;
    }

    public String getSecPCRSpaceObs() {
        return secPCRSpaceObs;
    }

    public void setSecPCRSpaceObs(String secPCRSpaceObs) {
        this.secPCRSpaceObs = secPCRSpaceObs;
    }

    public Integer getHasIntercom() {
        return hasIntercom;
    }

    public void setHasIntercom(Integer hasIntercom) {
        this.hasIntercom = hasIntercom;
    }

    public Double getIntercomHeight() {
        return intercomHeight;
    }

    public void setIntercomHeight(Double intercomHeight) {
        this.intercomHeight = intercomHeight;
    }

    public String getIntercomObs() {
        return intercomObs;
    }

    public void setIntercomObs(String intercomObs) {
        this.intercomObs = intercomObs;
    }

    public Integer getHasBioClock() {
        return hasBioClock;
    }

    public void setHasBioClock(Integer hasBioClock) {
        this.hasBioClock = hasBioClock;
    }

    public Double getBioClockHeight() {
        return bioClockHeight;
    }

    public void setBioClockHeight(Double bioClockHeight) {
        this.bioClockHeight = bioClockHeight;
    }

    public String getBioClockObs() {
        return bioClockObs;
    }

    public void setBioClockObs(String bioClockObs) {
        this.bioClockObs = bioClockObs;
    }

    public String getRoomPhoto() {
        return roomPhoto;
    }

    public void setRoomPhoto(String roomPhoto) {
        this.roomPhoto = roomPhoto;
    }

    public String getRoomObs() {
        return roomObs;
    }

    public void setRoomObs(String roomObs) {
        this.roomObs = roomObs;
    }
}
