package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class BlackboardEntry {

    @PrimaryKey(autoGenerate = true)
    private int boardID;
    private int roomID;

    private String boardLocation;
    private double infBorderHeight;
    private String boardObs;

    public BlackboardEntry(int roomID, String boardLocation, double infBorderHeight, String boardObs) {
        this.roomID = roomID;
        this.boardLocation = boardLocation;
        this.infBorderHeight = infBorderHeight;
        this.boardObs = boardObs;
    }

    public int getBoardID() {
        return boardID;
    }

    public void setBoardID(int boardID) {
        this.boardID = boardID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
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
}
