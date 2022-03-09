package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = BlockSpaceEntry.class, parentColumns = "blockSpaceID", childColumns = "blockID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class OtherSpaces {

    @PrimaryKey(autoGenerate = true)
    private int otherSpacesID;
    private int blockID;
    private String otherSpaceName;
    private String otherSpaceDescription;
    private int isAccessible;

    public OtherSpaces(int blockID, String otherSpaceName, String otherSpaceDescription, int isAccessible) {
        this.blockID = blockID;
        this.otherSpaceName = otherSpaceName;
        this.otherSpaceDescription = otherSpaceDescription;
        this.isAccessible = isAccessible;
    }

    public int getOtherSpacesID() {
        return otherSpacesID;
    }

    public void setOtherSpacesID(int otherSpacesID) {
        this.otherSpacesID = otherSpacesID;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int schoolEntryID) {
        this.blockID = schoolEntryID;
    }

    public String getOtherSpaceName() {
        return otherSpaceName;
    }

    public void setOtherSpaceName(String otherSpaceName) {
        this.otherSpaceName = otherSpaceName;
    }

    public String getOtherSpaceDescription() {
        return otherSpaceDescription;
    }

    public void setOtherSpaceDescription(String otherSpaceDescription) {
        this.otherSpaceDescription = otherSpaceDescription;
    }

    public int getIsAccessible() {
        return isAccessible;
    }

    public void setIsAccessible(int isAccessible) {
        this.isAccessible = isAccessible;
    }
}
