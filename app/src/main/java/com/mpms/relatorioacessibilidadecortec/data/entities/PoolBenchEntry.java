package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = PoolEntry.class, parentColumns = "poolID", childColumns = "poolID", onDelete = CASCADE, onUpdate = CASCADE))
public class PoolBenchEntry {

    @PrimaryKey(autoGenerate = true)
    private int poolBenchID;
    private int poolID;

    private String benchLocation;
    private Integer benchAccessible;
    private Double benchExtension;
    private Integer benchHasLeftBar;
    private Double benchLeftBarDiam;
    private Double benchLeftBarDist;
    private Integer benchHasRightBar;
    private Double benchRightBarDiam;
    private Double benchRightBarDist;
    private Double benchMeasureA;
    private Double benchMeasureB;
    private Double benchMeasureC;
    private Double benchMeasureD;
    private Double benchMeasureE;
    private Double benchWaterLevel;
    private String benchPhoto;
    private String benchObs;

    public PoolBenchEntry(int poolID, String benchLocation, Integer benchAccessible, Double benchExtension, Integer benchHasLeftBar, Double benchLeftBarDiam, Double benchLeftBarDist,
                          Integer benchHasRightBar, Double benchRightBarDiam, Double benchRightBarDist, Double benchMeasureA, Double benchMeasureB, Double benchMeasureC,
                          Double benchMeasureD, Double benchMeasureE, Double benchWaterLevel, String benchPhoto, String benchObs) {
        this.benchLocation = benchLocation;
        this.poolID = poolID;
        this.benchAccessible = benchAccessible;
        this.benchExtension = benchExtension;
        this.benchHasLeftBar = benchHasLeftBar;
        this.benchLeftBarDiam = benchLeftBarDiam;
        this.benchLeftBarDist = benchLeftBarDist;
        this.benchHasRightBar = benchHasRightBar;
        this.benchRightBarDiam = benchRightBarDiam;
        this.benchRightBarDist = benchRightBarDist;
        this.benchMeasureA = benchMeasureA;
        this.benchMeasureB = benchMeasureB;
        this.benchMeasureC = benchMeasureC;
        this.benchMeasureD = benchMeasureD;
        this.benchMeasureE = benchMeasureE;
        this.benchWaterLevel = benchWaterLevel;
        this.benchPhoto = benchPhoto;
        this.benchObs = benchObs;
    }

    public int getPoolBenchID() {
        return poolBenchID;
    }

    public void setPoolBenchID(int poolBenchID) {
        this.poolBenchID = poolBenchID;
    }

    public int getPoolID() {
        return poolID;
    }

    public void setPoolID(int poolID) {
        this.poolID = poolID;
    }

    public String getBenchLocation() {
        return benchLocation;
    }

    public void setBenchLocation(String benchLocation) {
        this.benchLocation = benchLocation;
    }

    public Integer getBenchAccessible() {
        return benchAccessible;
    }

    public void setBenchAccessible(Integer benchAccessible) {
        this.benchAccessible = benchAccessible;
    }

    public Double getBenchExtension() {
        return benchExtension;
    }

    public void setBenchExtension(Double benchExtension) {
        this.benchExtension = benchExtension;
    }

    public Integer getBenchHasLeftBar() {
        return benchHasLeftBar;
    }

    public void setBenchHasLeftBar(Integer benchHasLeftBar) {
        this.benchHasLeftBar = benchHasLeftBar;
    }

    public Double getBenchLeftBarDiam() {
        return benchLeftBarDiam;
    }

    public void setBenchLeftBarDiam(Double benchLeftBarDiam) {
        this.benchLeftBarDiam = benchLeftBarDiam;
    }

    public Double getBenchLeftBarDist() {
        return benchLeftBarDist;
    }

    public void setBenchLeftBarDist(Double benchLeftBarDist) {
        this.benchLeftBarDist = benchLeftBarDist;
    }

    public Integer getBenchHasRightBar() {
        return benchHasRightBar;
    }

    public void setBenchHasRightBar(Integer benchHasRightBar) {
        this.benchHasRightBar = benchHasRightBar;
    }

    public Double getBenchRightBarDiam() {
        return benchRightBarDiam;
    }

    public void setBenchRightBarDiam(Double benchRightBarDiam) {
        this.benchRightBarDiam = benchRightBarDiam;
    }

    public Double getBenchRightBarDist() {
        return benchRightBarDist;
    }

    public void setBenchRightBarDist(Double benchRightBarDist) {
        this.benchRightBarDist = benchRightBarDist;
    }

    public Double getBenchMeasureA() {
        return benchMeasureA;
    }

    public void setBenchMeasureA(Double benchMeasureA) {
        this.benchMeasureA = benchMeasureA;
    }

    public Double getBenchMeasureB() {
        return benchMeasureB;
    }

    public void setBenchMeasureB(Double benchMeasureB) {
        this.benchMeasureB = benchMeasureB;
    }

    public Double getBenchMeasureC() {
        return benchMeasureC;
    }

    public void setBenchMeasureC(Double benchMeasureC) {
        this.benchMeasureC = benchMeasureC;
    }

    public Double getBenchMeasureD() {
        return benchMeasureD;
    }

    public void setBenchMeasureD(Double benchMeasureD) {
        this.benchMeasureD = benchMeasureD;
    }

    public Double getBenchMeasureE() {
        return benchMeasureE;
    }

    public void setBenchMeasureE(Double benchMeasureE) {
        this.benchMeasureE = benchMeasureE;
    }

    public Double getBenchWaterLevel() {
        return benchWaterLevel;
    }

    public void setBenchWaterLevel(Double benchWaterLevel) {
        this.benchWaterLevel = benchWaterLevel;
    }

    public String getBenchPhoto() {
        return benchPhoto;
    }

    public void setBenchPhoto(String benchPhoto) {
        this.benchPhoto = benchPhoto;
    }

    public String getBenchObs() {
        return benchObs;
    }

    public void setBenchObs(String benchObs) {
        this.benchObs = benchObs;
    }
}
