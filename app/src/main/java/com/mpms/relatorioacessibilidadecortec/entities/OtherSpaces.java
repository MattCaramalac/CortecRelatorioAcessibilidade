package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = SchoolEntry.class,
        parentColumns = "cadID",
        childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class OtherSpaces {

    @PrimaryKey(autoGenerate = true)
    private int otherSpacesID;
    private int schoolEntryID;
    private String otherSpaceName;
    private String otherSpaceDescription;
    private int isAccessible;

    public OtherSpaces(int schoolEntryID, String otherSpaceName, String otherSpaceDescription, int isAccessible) {
        this.schoolEntryID = schoolEntryID;
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

    public int getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(int schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
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
