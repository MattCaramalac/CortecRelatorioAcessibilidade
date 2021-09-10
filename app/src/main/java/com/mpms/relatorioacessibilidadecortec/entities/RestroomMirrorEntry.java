package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = RestroomEntry.class, parentColumns = "restroomID",
        childColumns = "restroomID", onDelete = CASCADE, onUpdate = CASCADE))
public class RestroomMirrorEntry {

    @PrimaryKey(autoGenerate = true)
    private int mirrorID;
    private int restroomID;
    private int restroomHasMirror;
    private Double mirrorMeasureA;
    private Double mirrorMeasureB;
    private String mirrorObs;

    public RestroomMirrorEntry(int restroomID, int restroomHasMirror, Double mirrorMeasureA, Double mirrorMeasureB, String mirrorObs) {
        this.restroomID = restroomID;
        this.restroomHasMirror = restroomHasMirror;
        this.mirrorMeasureA = mirrorMeasureA;
        this.mirrorMeasureB = mirrorMeasureB;
        this.mirrorObs = mirrorObs;
    }

    public int getMirrorID() {
        return mirrorID;
    }

    public void setMirrorID(int mirrorID) {
        this.mirrorID = mirrorID;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public int getRestroomHasMirror() {
        return restroomHasMirror;
    }

    public void setRestroomHasMirror(int restroomHasMirror) {
        this.restroomHasMirror = restroomHasMirror;
    }

    public Double getMirrorMeasureA() {
        return mirrorMeasureA;
    }

    public void setMirrorMeasureA(Double mirrorMeasureA) {
        this.mirrorMeasureA = mirrorMeasureA;
    }

    public Double getMirrorMeasureB() {
        return mirrorMeasureB;
    }

    public void setMirrorMeasureB(Double mirrorMeasureB) {
        this.mirrorMeasureB = mirrorMeasureB;
    }

    public String getMirrorObs() {
        return mirrorObs;
    }

    public void setMirrorObs(String mirrorObs) {
        this.mirrorObs = mirrorObs;
    }
}
