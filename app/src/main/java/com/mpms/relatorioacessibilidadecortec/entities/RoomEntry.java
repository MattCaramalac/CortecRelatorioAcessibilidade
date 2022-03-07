package com.mpms.relatorioacessibilidadecortec.entities;

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
    private Integer roomHasVisualVertSing;
    private String roomObsVisualVertSign;
    private Integer roomHasTactileSing;
    private String roomObsTactileSign;
    private Integer roomHasLooseCarpet;
    private String looseCarpetObs;

    private Integer libraryDistanceShelvesOK;
    private Integer libraryPcrManeuversOK;
    private Integer libraryAccessiblePcOK;

    private Integer secretHasFixedSeats;
    private Integer secretHasPcrSpace;
    private Double secretWidthPcrSpace;
    private Double secretLengthPcrSpace;
    private String secretPCRSpaceObs;

    private String roomObs;

    public RoomEntry(int blockID, int roomType, String roomLocation, Integer roomHasVisualVertSing, String roomObsVisualVertSign,
                     Integer roomHasTactileSing, String roomObsTactileSign, Integer roomHasLooseCarpet, String looseCarpetObs,
                     Integer libraryDistanceShelvesOK, Integer libraryPcrManeuversOK, Integer libraryAccessiblePcOK, Integer secretHasFixedSeats,
                     Integer secretHasPcrSpace, Double secretWidthPcrSpace, Double secretLengthPcrSpace, String secretPCRSpaceObs, String roomObs) {
        this.blockID = blockID;
        this.roomType = roomType;
        this.roomLocation = roomLocation;
        this.roomHasVisualVertSing = roomHasVisualVertSing;
        this.roomObsVisualVertSign = roomObsVisualVertSign;
        this.roomHasTactileSing = roomHasTactileSing;
        this.roomObsTactileSign = roomObsTactileSign;
        this.roomHasLooseCarpet = roomHasLooseCarpet;
        this.looseCarpetObs = looseCarpetObs;
        this.libraryDistanceShelvesOK = libraryDistanceShelvesOK;
        this.libraryPcrManeuversOK = libraryPcrManeuversOK;
        this.libraryAccessiblePcOK = libraryAccessiblePcOK;
        this.secretHasFixedSeats = secretHasFixedSeats;
        this.secretHasPcrSpace = secretHasPcrSpace;
        this.secretWidthPcrSpace = secretWidthPcrSpace;
        this.secretLengthPcrSpace = secretLengthPcrSpace;
        this.secretPCRSpaceObs = secretPCRSpaceObs;
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

    public void setBlockID(int schoolEntryID) {
        this.blockID = schoolEntryID;
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

    public Integer getRoomHasVisualVertSing() {
        return roomHasVisualVertSing;
    }

    public void setRoomHasVisualVertSing(Integer roomHasVisualVertSing) {
        this.roomHasVisualVertSing = roomHasVisualVertSing;
    }

    public String getRoomObsVisualVertSign() {
        return roomObsVisualVertSign;
    }

    public void setRoomObsVisualVertSign(String roomObsVisualVertSign) {
        this.roomObsVisualVertSign = roomObsVisualVertSign;
    }

    public Integer getRoomHasTactileSing() {
        return roomHasTactileSing;
    }

    public void setRoomHasTactileSing(Integer roomHasTactileSing) {
        this.roomHasTactileSing = roomHasTactileSing;
    }

    public String getRoomObsTactileSign() {
        return roomObsTactileSign;
    }

    public void setRoomObsTactileSign(String roomObsTactileSign) {
        this.roomObsTactileSign = roomObsTactileSign;
    }

    public Integer getLibraryDistanceShelvesOK() {
        return libraryDistanceShelvesOK;
    }

    public void setLibraryDistanceShelvesOK(Integer libraryDistanceShelvesOK) {
        this.libraryDistanceShelvesOK = libraryDistanceShelvesOK;
    }

    public Integer getLibraryPcrManeuversOK() {
        return libraryPcrManeuversOK;
    }

    public void setLibraryPcrManeuversOK(Integer libraryPcrManeuversOK) {
        this.libraryPcrManeuversOK = libraryPcrManeuversOK;
    }

    public Integer getLibraryAccessiblePcOK() {
        return libraryAccessiblePcOK;
    }

    public void setLibraryAccessiblePcOK(Integer libraryAccessiblePcOK) {
        this.libraryAccessiblePcOK = libraryAccessiblePcOK;
    }

    public Integer getSecretHasFixedSeats() {
        return secretHasFixedSeats;
    }

    public void setSecretHasFixedSeats(Integer secretHasFixedSeats) {
        this.secretHasFixedSeats = secretHasFixedSeats;
    }

    public Integer getSecretHasPcrSpace() {
        return secretHasPcrSpace;
    }

    public void setSecretHasPcrSpace(Integer secretHasPcrSpace) {
        this.secretHasPcrSpace = secretHasPcrSpace;
    }

    public Double getSecretWidthPcrSpace() {
        return secretWidthPcrSpace;
    }

    public void setSecretWidthPcrSpace(Double secretWidthPcrSpace) {
        this.secretWidthPcrSpace = secretWidthPcrSpace;
    }

    public Double getSecretLengthPcrSpace() {
        return secretLengthPcrSpace;
    }

    public void setSecretLengthPcrSpace(Double secretLengthPcrSpace) {
        this.secretLengthPcrSpace = secretLengthPcrSpace;
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

    public String getRoomObs() {
        return roomObs;
    }

    public void setRoomObs(String roomObs) {
        this.roomObs = roomObs;
    }

    public String getSecretPCRSpaceObs() {
        return secretPCRSpaceObs;
    }

    public void setSecretPCRSpaceObs(String secretPCRSpaceObs) {
        this.secretPCRSpaceObs = secretPCRSpaceObs;
    }
}
