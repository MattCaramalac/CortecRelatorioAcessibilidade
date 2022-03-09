package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class BlockSpaceEntry {

    @PrimaryKey(autoGenerate = true)
    private int blockSpaceID;
    private int schoolID;
    private int blockSpaceType;
    private int blockSpaceNumber;

    public BlockSpaceEntry(int schoolID, int blockSpaceType, int blockSpaceNumber) {
        this.schoolID = schoolID;
        this.blockSpaceType = blockSpaceType;
        this.blockSpaceNumber = blockSpaceNumber;
    }

    public int getBlockSpaceID() {
        return blockSpaceID;
    }

    public void setBlockSpaceID(int blockSpaceID) {
        this.blockSpaceID = blockSpaceID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getBlockSpaceType() {
        return blockSpaceType;
    }

    public void setBlockSpaceType(int blockSpaceType) {
        this.blockSpaceType = blockSpaceType;
    }

    public int getBlockSpaceNumber() {
        return blockSpaceNumber;
    }

    public void setBlockSpaceNumber(int blockSpaceNumber) {
        this.blockSpaceNumber = blockSpaceNumber;
    }
}
