package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {@ForeignKey(entity = RoomEntry.class, parentColumns = "roomID", childColumns = "roomID", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = CirculationEntry.class, parentColumns = "circID", childColumns = "circID", onDelete = CASCADE, onUpdate = CASCADE)})
public class TableEntry {

    @PrimaryKey(autoGenerate = true)
    private int tableID;
    private Integer roomID;
    private Integer circID;
    private int roomType;
    private Integer tableType;
    private double inferiorBorderHeight;
    private double superiorBorderHeight;
    private double tableWidth;
    private double tableFrontalApprox;
    private String tableObs;
    private Double tableFreeWidth;
    private String tableDesc;
    private Integer tableSize;
    private String tablePhoto;

    public TableEntry(Integer roomID, Integer circID, int roomType, Integer tableType, double superiorBorderHeight, double inferiorBorderHeight, double tableWidth,
                      double tableFrontalApprox, String tableObs, Double tableFreeWidth, String tableDesc, Integer tableSize, String tablePhoto) {
        this.roomID = roomID;
        this.circID = circID;
        this.roomType = roomType;
        this.tableType = tableType;
        this.inferiorBorderHeight = inferiorBorderHeight;
        this.superiorBorderHeight = superiorBorderHeight;
        this.tableWidth = tableWidth;
        this.tableFrontalApprox = tableFrontalApprox;
        this.tableObs = tableObs;
        this.tableFreeWidth = tableFreeWidth;
        this.tableDesc = tableDesc;
        this.tableSize = tableSize;
        this.tablePhoto = tablePhoto;
    }

    public String getTablePhoto() {
        return tablePhoto;
    }

    public void setTablePhoto(String tablePhoto) {
        this.tablePhoto = tablePhoto;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
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

    public Integer getRoomID() {
        return roomID;
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

    public Double getTableFreeWidth() {
        return tableFreeWidth;
    }

    public void setTableFreeWidth(Double tableFreeWidth) {
        this.tableFreeWidth = tableFreeWidth;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public Integer getTableSize() {
        return tableSize;
    }

    public void setTableSize(Integer tableSize) {
        this.tableSize = tableSize;
    }
}
