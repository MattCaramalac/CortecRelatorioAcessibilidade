package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE))
public class EquipmentEntry {

    @PrimaryKey(autoGenerate = true)
    private int equipID;
    private int roomID;
    private String equipName;
    private String equipLocale;
    private double equipHeight;
    private String equipObs;

    public EquipmentEntry(int roomID, String equipName, String equipLocale, double equipHeight, String equipObs) {
        this.roomID = roomID;
        this.equipName = equipName;
        this.equipLocale = equipLocale;
        this.equipHeight = equipHeight;
        this.equipObs = equipObs;
    }

    public int getEquipID() {
        return equipID;
    }

    public void setEquipID(int equipID) {
        this.equipID = equipID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
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