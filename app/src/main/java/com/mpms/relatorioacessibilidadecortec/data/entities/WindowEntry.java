package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE)})
public class WindowEntry {

    @PrimaryKey(autoGenerate = true)
    private int windowID;
    private Integer roomID;
    private Integer circID;
    @ColumnInfo(defaultValue = "Única")
    private String windowLocation;
    @ColumnInfo(defaultValue = "1")
    private int winQnt;
    private String comType1;
    private Double comHeight1;
    private String comType2;
    private Double comHeight2;
    private String comType3;
    private Double comHeight3;
    private String windowObs;
    private String windowPhoto;

    public WindowEntry(Integer roomID, Integer circID, String windowLocation, int winQnt, String comType1, Double comHeight1, String comType2, Double comHeight2, String comType3,
                       Double comHeight3, String windowObs, String windowPhoto) {
        this.roomID = roomID;
        this.circID = circID;
        this.windowLocation = windowLocation;
        this.winQnt = winQnt;
        this.comType1 = comType1;
        this.comHeight1 = comHeight1;
        this.comType2 = comType2;
        this.comHeight2 = comHeight2;
        this.comType3 = comType3;
        this.comHeight3 = comHeight3;
        this.windowObs = windowObs;
        this.windowPhoto = windowPhoto;
    }

    public String getWindowPhoto() {
        return windowPhoto;
    }

    public void setWindowPhoto(String windowPhoto) {
        this.windowPhoto = windowPhoto;
    }

    public int getWindowID() {
        return windowID;
    }

    public void setWindowID(int windowID) {
        this.windowID = windowID;
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

    public String getWindowLocation() {
        return windowLocation;
    }

    public void setWindowLocation(String windowLocation) {
        this.windowLocation = windowLocation;
    }

    public int getWinQnt() {
        return winQnt;
    }

    public void setWinQnt(int winQnt) {
        this.winQnt = winQnt;
    }

    public String getComType1() {
        return comType1;
    }

    public void setComType1(String comType1) {
        this.comType1 = comType1;
    }

    public Double getComHeight1() {
        return comHeight1;
    }

    public void setComHeight1(Double comHeight1) {
        this.comHeight1 = comHeight1;
    }

    public String getComType2() {
        return comType2;
    }

    public void setComType2(String comType2) {
        this.comType2 = comType2;
    }

    public Double getComHeight2() {
        return comHeight2;
    }

    public void setComHeight2(Double comHeight2) {
        this.comHeight2 = comHeight2;
    }

    public String getComType3() {
        return comType3;
    }

    public void setComType3(String comType3) {
        this.comType3 = comType3;
    }

    public Double getComHeight3() {
        return comHeight3;
    }

    public void setComHeight3(Double comHeight3) {
        this.comHeight3 = comHeight3;
    }

    public String getWindowObs() {
        return windowObs;
    }

    public void setWindowObs(String windowObs) {
        this.windowObs = windowObs;
    }
}
