package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE)})
public class TableEntry {

    @PrimaryKey(autoGenerate = true)
    private int tableID;
    private int roomID;
    private int roomType;
    private Integer tableType;
    private double inferiorBorderHeight;
    private double superiorBorderHeight;
    private double tableWidth;
    private double tableFrontalApprox;
    private String tableObs;

    public TableEntry(int roomID, int roomType, Integer tableType, double inferiorBorderHeight, double superiorBorderHeight, double tableWidth,
                      double tableFrontalApprox, String tableObs) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.tableType = tableType;
        this.inferiorBorderHeight = inferiorBorderHeight;
        this.superiorBorderHeight = superiorBorderHeight;
        this.tableWidth = tableWidth;
        this.tableFrontalApprox = tableFrontalApprox;
        this.tableObs = tableObs;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public Integer getTableType() {
        return tableType;
    }

    public void setTableType(Integer tableType) {
        this.tableType = tableType;
    }

    public double getInferiorBorderHeight() {
        return inferiorBorderHeight;
    }

    public void setInferiorBorderHeight(double inferiorBorderHeight) {
        this.inferiorBorderHeight = inferiorBorderHeight;
    }

    public double getSuperiorBorderHeight() {
        return superiorBorderHeight;
    }

    public void setSuperiorBorderHeight(double superiorBorderHeight) {
        this.superiorBorderHeight = superiorBorderHeight;
    }

    public double getTableWidth() {
        return tableWidth;
    }

    public void setTableWidth(double tableWidth) {
        this.tableWidth = tableWidth;
    }

    public double getTableFrontalApprox() {
        return tableFrontalApprox;
    }

    public void setTableFrontalApprox(double tableFrontalApprox) {
        this.tableFrontalApprox = tableFrontalApprox;
    }

    public String getTableObs() {
        return tableObs;
    }

    public void setTableObs(String tableObs) {
        this.tableObs = tableObs;
    }
}
