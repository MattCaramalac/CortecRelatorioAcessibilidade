package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE)})
public class EquipmentEntry {

    @PrimaryKey(autoGenerate = true)
    private int equipID;
    private Integer roomID;
    private Integer circID;
    private String equipName;
    private String equipLocale;
    private double equipHeight;
    private String equipObs;
    private String equipPhoto;

    public EquipmentEntry(Integer roomID, Integer circID, String equipName, String equipLocale, double equipHeight, String equipObs, String equipPhoto) {
        this.roomID = roomID;
        this.circID = circID;
        this.equipName = equipName;
        this.equipLocale = equipLocale;
        this.equipHeight = equipHeight;
        this.equipObs = equipObs;
        this.equipPhoto = equipPhoto;
    }

    public String getEquipPhoto() {
        return equipPhoto;
    }

    public void setEquipPhoto(String equipPhoto) {
        this.equipPhoto = equipPhoto;
    }

    public int getEquipID() {
        return equipID;
    }

    public void setEquipID(int equipID) {
        this.equipID = equipID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public Integer getCircID() {
        return circID;
    }

    public void setCircID(Integer circID) {
        this.circID = circID;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getEquipLocale() {
        return equipLocale;
    }

    public void setEquipLocale(String equipLocale) {
        this.equipLocale = equipLocale;
    }

    public double getEquipHeight() {
        return equipHeight;
    }

    public void setEquipHeight(double equipHeight) {
        this.equipHeight = equipHeight;
    }

    public String getEquipObs() {
        return equipObs;
    }

    public void setEquipObs(String equipObs) {
        this.equipObs = equipObs;
    }
}
