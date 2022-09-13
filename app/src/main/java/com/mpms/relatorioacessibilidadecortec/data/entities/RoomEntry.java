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
    private Integer roomHasVertSing;
    private String roomVertSignObs;
    private Integer roomHasLooseCarpet;
    private String looseCarpetObs;
    private Integer roomAccessFloor;
    private String accessFloorObs;

    private Integer libDistShelvesOK;
    private Integer libPcrManeuversOK;
    private Integer libAccessPcOK;

    private Integer secHasFixedSeats;
    private Integer secHasPcrSpace;
    private Double secPcrSpaceWidth;
    private Double secPcrSpaceDepth;
    private String secPCRSpaceObs;

    private String roomObs;

    private Integer roomHasStairs;
    private Integer roomHasRamps;

    private Integer hasBellControl;
    private Double bellControlHeight;
    private String bellControlObs;
    private Integer hasInternalPhone;
    private Double internalPhoneHeight;
    private String internalPhoneObs;
    private Integer hasBiometricClock;
    private Double biometricClockHeight;
    private String biometricClockObs;

    public RoomEntry(int blockID, int roomType, String roomLocation, String roomDescription, Integer roomHasVertSing, String roomVertSignObs, Integer roomHasLooseCarpet,
                     String looseCarpetObs, Integer roomAccessFloor, String accessFloorObs, Integer libDistShelvesOK, Integer libPcrManeuversOK, Integer libAccessPcOK,
                     Integer secHasFixedSeats, Integer secHasPcrSpace, Double secPcrSpaceWidth, Double secPcrSpaceDepth, String secPCRSpaceObs, String roomObs,
                     Integer roomHasStairs, Integer roomHasRamps, Integer hasBellControl, Double bellControlHeight, String bellControlObs, Integer hasInternalPhone,
                     Double internalPhoneHeight, String internalPhoneObs, Integer hasBiometricClock, Double biometricClockHeight, String biometricClockObs) {
        this.blockID = blockID;
        this.roomType = roomType;
        this.roomLocation = roomLocation;
        this.roomDescription = roomDescription;
        this.roomHasVertSing = roomHasVertSing;
        this.roomVertSignObs = roomVertSignObs;
        this.roomHasLooseCarpet = roomHasLooseCarpet;
        this.looseCarpetObs = looseCarpetObs;
        this.roomAccessFloor = roomAccessFloor;
        this.accessFloorObs = accessFloorObs;
        this.libDistShelvesOK = libDistShelvesOK;
        this.libPcrManeuversOK = libPcrManeuversOK;
        this.libAccessPcOK = libAccessPcOK;
        this.secHasFixedSeats = secHasFixedSeats;
        this.secHasPcrSpace = secHasPcrSpace;
        this.secPcrSpaceWidth = secPcrSpaceWidth;
        this.secPcrSpaceDepth = secPcrSpaceDepth;
        this.secPCRSpaceObs = secPCRSpaceObs;
        this.roomObs = roomObs;
        this.roomHasStairs = roomHasStairs;
        this.roomHasRamps = roomHasRamps;
        this.hasBellControl = hasBellControl;
        this.bellControlHeight = bellControlHeight;
        this.bellControlObs = bellControlObs;
        this.hasInternalPhone = hasInternalPhone;
        this.internalPhoneHeight = internalPhoneHeight;
        this.internalPhoneObs = internalPhoneObs;
        this.hasBiometricClock = hasBiometricClock;
        this.biometricClockHeight = biometricClockHeight;
        this.biometricClockObs = biometricClockObs;
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

    public Integer getLibPcrManeuversOK() {
        return libPcrManeuversOK;
    }

    public void setLibPcrManeuversOK(Integer libPcrManeuversOK) {
        this.libPcrManeuversOK = libPcrManeuversOK;
    }

    public Integer getLibAccessPcOK() {
        return libAccessPcOK;
    }

    public void setLibAccessPcOK(Integer libAccessPcOK) {
        this.libAccessPcOK = libAccessPcOK;
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

    public String getRoomObs() {
        return roomObs;
    }

    public void setRoomObs(String roomObs) {
        this.roomObs = roomObs;
    }

    public Integer getRoomHasStairs() {
        return roomHasStairs;
    }

    public void setRoomHasStairs(Integer roomHasStairs) {
        this.roomHasStairs = roomHasStairs;
    }

    public Integer getRoomHasRamps() {
        return roomHasRamps;
    }

    public void setRoomHasRamps(Integer roomHasRamps) {
        this.roomHasRamps = roomHasRamps;
    }

    public Integer getHasBellControl() {
        return hasBellControl;
    }

    public void setHasBellControl(Integer hasBellControl) {
        this.hasBellControl = hasBellControl;
    }

    public Double getBellControlHeight() {
        return bellControlHeight;
    }

    public void setBellControlHeight(Double bellControlHeight) {
        this.bellControlHeight = bellControlHeight;
    }

    public String getBellControlObs() {
        return bellControlObs;
    }

    public void setBellControlObs(String bellControlObs) {
        this.bellControlObs = bellControlObs;
    }

    public Integer getHasInternalPhone() {
        return hasInternalPhone;
    }

    public void setHasInternalPhone(Integer hasInternalPhone) {
        this.hasInternalPhone = hasInternalPhone;
    }

    public Double getInternalPhoneHeight() {
        return internalPhoneHeight;
    }

    public void setInternalPhoneHeight(Double internalPhoneHeight) {
        this.internalPhoneHeight = internalPhoneHeight;
    }

    public String getInternalPhoneObs() {
        return internalPhoneObs;
    }

    public void setInternalPhoneObs(String internalPhoneObs) {
        this.internalPhoneObs = internalPhoneObs;
    }

    public Integer getHasBiometricClock() {
        return hasBiometricClock;
    }

    public void setHasBiometricClock(Integer hasBiometricClock) {
        this.hasBiometricClock = hasBiometricClock;
    }

    public Double getBiometricClockHeight() {
        return biometricClockHeight;
    }

    public void setBiometricClockHeight(Double biometricClockHeight) {
        this.biometricClockHeight = biometricClockHeight;
    }

    public String getBiometricClockObs() {
        return biometricClockObs;
    }

    public void setBiometricClockObs(String biometricClockObs) {
        this.biometricClockObs = biometricClockObs;
    }
}
