package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class AdmEquipEntry {

    @PrimaryKey(autoGenerate = true)
    private int admEquipID;
    private int blockID;

    private String admEquipLocation;
    private int hasBellControl;
    private Double bellControlHeight;
    private String bellControlObs;
    private int hasInternalPhone;
    private Double internalPhoneHeight;
    private String internalPhoneObs;
    private int hasBiometricClock;
    private Double biometricClockHeight;
    private String biometricClockObs;

    public AdmEquipEntry(int blockID, String admEquipLocation, int hasBellControl, Double bellControlHeight, String bellControlObs, int hasInternalPhone, Double internalPhoneHeight,
                         String internalPhoneObs, int hasBiometricClock, Double biometricClockHeight, String biometricClockObs) {
        this.blockID = blockID;
        this.admEquipLocation = admEquipLocation;
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

    public int getAdmEquipID() {
        return admEquipID;
    }

    public void setAdmEquipID(int admEquipID) {
        this.admEquipID = admEquipID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public String getAdmEquipLocation() {
        return admEquipLocation;
    }

    public void setAdmEquipLocation(String admEquipLocation) {
        this.admEquipLocation = admEquipLocation;
    }

    public int getHasBellControl() {
        return hasBellControl;
    }

    public void setHasBellControl(int hasBellControl) {
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

    public int getHasInternalPhone() {
        return hasInternalPhone;
    }

    public void setHasInternalPhone(int hasInternalPhone) {
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

    public int getHasBiometricClock() {
        return hasBiometricClock;
    }

    public void setHasBiometricClock(int hasBiometricClock) {
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
