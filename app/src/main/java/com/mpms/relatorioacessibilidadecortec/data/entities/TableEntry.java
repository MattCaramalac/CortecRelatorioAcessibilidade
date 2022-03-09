package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {
        @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
                onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = RoomEntry.class,
                parentColumns = "roomID",
                childColumns = "roomID",
                onDelete = CASCADE, onUpdate = CASCADE)})
public class TableEntry {

    @PrimaryKey(autoGenerate = true)
    private int tableID;
    private int blockID;
    private int roomID;
    private int isClassroom;
    private Integer tableType;
    private Double inferiorBorderHeight;
    private Double superiorBorderHeight;
    private Double tableWidth;
    private Double tableFrontalApprox;
    private String tableObs;

    public TableEntry(int blockID, int roomID, int isClassroom, Integer tableType, Double inferiorBorderHeight,
                      Double superiorBorderHeight, Double tableWidth, Double tableFrontalApprox, String tableObs) {
        this.blockID = blockID;
        this.roomID = roomID;
        this.isClassroom = isClassroom;
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

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getIsClassroom() {
        return isClassroom;
    }

    public void setIsClassroom(int isClassroom) {
        this.isClassroom = isClassroom;
    }

    public Integer getTableType() {
        return tableType;
    }

    public void setTableType(Integer tableType) {
        this.tableType = tableType;
    }

    public Double getInferiorBorderHeight() {
        return inferiorBorderHeight;
    }

    public void setInferiorBorderHeight(Double inferiorBorderHeight) {
        this.inferiorBorderHeight = inferiorBorderHeight;
    }

    public Double getSuperiorBorderHeight() {
        return superiorBorderHeight;
    }

    public void setSuperiorBorderHeight(Double superiorBorderHeight) {
        this.superiorBorderHeight = superiorBorderHeight;
    }

    public Double getTableWidth() {
        return tableWidth;
    }

    public void setTableWidth(Double tableWidth) {
        this.tableWidth = tableWidth;
    }

    public Double getTableFrontalApprox() {
        return tableFrontalApprox;
    }

    public void setTableFrontalApprox(Double tableFrontalApprox) {
        this.tableFrontalApprox = tableFrontalApprox;
    }

    public String getTableObs() {
        return tableObs;
    }

    public void setTableObs(String tableObs) {
        this.tableObs = tableObs;
    }
}
