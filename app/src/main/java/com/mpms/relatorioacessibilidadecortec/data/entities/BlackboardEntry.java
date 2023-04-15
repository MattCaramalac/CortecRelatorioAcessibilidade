package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE)})
public class BlackboardEntry {

    @PrimaryKey(autoGenerate = true)
    private int boardID;
    private Integer roomID;
    private Integer circID;
    @ColumnInfo(defaultValue = "0")
    private int boardType;
    private String boardLocation;
    private double infBorderHeight;
    private String boardObs;
    private String boardPhoto;

    public BlackboardEntry(Integer roomID, Integer circID, int boardType, String boardLocation, double infBorderHeight, String boardObs, String boardPhoto) {
        this.roomID = roomID;
        this.circID = circID;
        this.boardType = boardType;
        this.boardLocation = boardLocation;
        this.infBorderHeight = infBorderHeight;
        this.boardObs = boardObs;
        this.boardPhoto = boardPhoto;
    }

    public int getBoardID() {
        return boardID;
    }

    public void setBoardID(int boardID) {
        this.boardID = boardID;
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

    public int getBoardType() {
        return boardType;
    }

    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }

    public String getBoardLocation() {
        return boardLocation;
    }

    public void setBoardLocation(String boardLocation) {
        this.boardLocation = boardLocation;
    }

    public double getInfBorderHeight() {
        return infBorderHeight;
    }

    public void setInfBorderHeight(double infBorderHeight) {
        this.infBorderHeight = infBorderHeight;
    }

    public String getBoardObs() {
        return boardObs;
    }

    public void setBoardObs(String boardObs) {
        this.boardObs = boardObs;
    }

    public String getBoardPhoto() {
        return boardPhoto;
    }

    public void setBoardPhoto(String boardPhoto) {
        this.boardPhoto = boardPhoto;
    }
}
