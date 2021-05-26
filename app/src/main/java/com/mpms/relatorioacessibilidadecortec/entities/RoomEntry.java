package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class,
        parentColumns = "cadID",
        childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class RoomEntry {

    @PrimaryKey(autoGenerate = true)
    private Integer roomID;
    private Integer schoolEntryID;
    private Integer roomType;
    private Integer roomHasVisualVertSing;
    private String roomObsVisualVertSign;
    private Integer roomHasTactileSing;
    private String roomObsTactileSign;

    private Integer libraryDistanceShelvesOK;
    private Integer libraryPcrManeuversOK;
    private Integer libraryAccessiblePcOK;

    private Integer cafeteriaTurnAroundPossible;

    private Double classroomBlackboardHeight;

    private Integer secretFixedSeats;
    private Integer secretHasPcrSpace;
    private Double secretWidthPcrSpace;
    private Double secretLengthPcrSpace;
    private Integer secretTurnAroundPossible;

    public RoomEntry(Integer schoolEntryID, Integer roomType, Integer roomHasVisualVertSing, String roomObsVisualVertSign,
                     Integer roomHasTactileSing, String roomObsTactileSign, Integer libraryDistanceShelvesOK,
                     Integer libraryPcrManeuversOK, Integer libraryAccessiblePcOK, Integer cafeteriaTurnAroundPossible,
                     Double classroomBlackboardHeight, Integer secretFixedSeats, Integer secretHasPcrSpace,
                     Double secretWidthPcrSpace, Double secretLengthPcrSpace, Integer secretTurnAroundPossible) {
        this.schoolEntryID = schoolEntryID;
        this.roomType = roomType;
        this.roomHasVisualVertSing = roomHasVisualVertSing;
        this.roomObsVisualVertSign = roomObsVisualVertSign;
        this.roomHasTactileSing = roomHasTactileSing;
        this.roomObsTactileSign = roomObsTactileSign;
        this.libraryDistanceShelvesOK = libraryDistanceShelvesOK;
        this.libraryPcrManeuversOK = libraryPcrManeuversOK;
        this.libraryAccessiblePcOK = libraryAccessiblePcOK;
        this.cafeteriaTurnAroundPossible = cafeteriaTurnAroundPossible;
        this.classroomBlackboardHeight = classroomBlackboardHeight;
        this.secretFixedSeats = secretFixedSeats;
        this.secretHasPcrSpace = secretHasPcrSpace;
        this.secretWidthPcrSpace = secretWidthPcrSpace;
        this.secretLengthPcrSpace = secretLengthPcrSpace;
        this.secretTurnAroundPossible = secretTurnAroundPossible;
    }

    @NonNull
    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(@NonNull Integer roomID) {
        this.roomID = roomID;
    }

    public Integer getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(Integer schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
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

    public Integer getCafeteriaTurnAroundPossible() {
        return cafeteriaTurnAroundPossible;
    }

    public void setCafeteriaTurnAroundPossible(Integer cafeteriaTurnAroundPossible) {
        this.cafeteriaTurnAroundPossible = cafeteriaTurnAroundPossible;
    }

    public Double getClassroomBlackboardHeight() {
        return classroomBlackboardHeight;
    }

    public void setClassroomBlackboardHeight(Double classroomBlackboardHeight) {
        this.classroomBlackboardHeight = classroomBlackboardHeight;
    }

    public Integer getSecretFixedSeats() {
        return secretFixedSeats;
    }

    public void setSecretFixedSeats(Integer secretFixedSeats) {
        this.secretFixedSeats = secretFixedSeats;
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

    public Integer getSecretTurnAroundPossible() {
        return secretTurnAroundPossible;
    }

    public void setSecretTurnAroundPossible(Integer secretTurnAroundPossible) {
        this.secretTurnAroundPossible = secretTurnAroundPossible;
    }
}
