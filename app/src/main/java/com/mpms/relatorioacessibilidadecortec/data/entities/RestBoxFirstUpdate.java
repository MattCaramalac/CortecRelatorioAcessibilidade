package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestBoxFirstUpdate {

    @PrimaryKey
    private int boxID;

    private int typeBox;
    private Double comBoxDoorWidth;
    private Double comBoxFreeDiam;
    private Integer comBoxHasBars;
    private Double comBoxToiletDoorDist;
    private Double comBoxWidth;
    private Integer comBoxHasLeftBar;
    private Double comBoxLeftShapeBarA;
    private Double comBoxLeftShapeBarB;
    private Double comBoxLeftShapeBarC;
    private Double comBoxLeftShapeBarD;
    private Double comBoxLeftShapeBarDiam;
    private Double comBoxLeftShapeBarDist;
    private String comBoxLeftBarObs;
    private Integer comBoxHasRightBar;
    private Double comBoxRightShapeBarA;
    private Double comBoxRightShapeBarB;
    private Double comBoxRightShapeBarC;
    private Double comBoxRightShapeBarD;
    private Double comBoxRightShapeBarDiam;
    private Double comBoxRightShapeBarDist;
    private String comBoxRightBarObs;
    private String comBoxObs;

    private Double upViewLength;
    private Double upViewWidth;
    private Double upViewMeasureA;
    private Double upViewMeasureB;
    private Double upViewMeasureC;
    private Double upViewMeasureD;
    private String upViewObs;
    private Integer restDrain;
    private String restDrainObs;
    private String boxUpperPhoto;

    public RestBoxFirstUpdate(int boxID, int typeBox, Double comBoxDoorWidth, Double comBoxFreeDiam, Integer comBoxHasBars, Double comBoxToiletDoorDist, Double comBoxWidth,
                              Integer comBoxHasLeftBar, Double comBoxLeftShapeBarA, Double comBoxLeftShapeBarB, Double comBoxLeftShapeBarC, Double comBoxLeftShapeBarD,
                              Double comBoxLeftShapeBarDiam, Double comBoxLeftShapeBarDist, String comBoxLeftBarObs, Integer comBoxHasRightBar,
                              Double comBoxRightShapeBarA, Double comBoxRightShapeBarB, Double comBoxRightShapeBarC, Double comBoxRightShapeBarD, Double comBoxRightShapeBarDiam,
                              Double comBoxRightShapeBarDist, String comBoxRightBarObs, String comBoxObs, Double upViewLength, Double upViewWidth,
                              Double upViewMeasureA, Double upViewMeasureB, Double upViewMeasureC, Double upViewMeasureD, String upViewObs, Integer restDrain, String restDrainObs,
                              String boxUpperPhoto) {
        this.boxID = boxID;
        this.typeBox = typeBox;
        this.comBoxDoorWidth = comBoxDoorWidth;
        this.comBoxFreeDiam = comBoxFreeDiam;
        this.comBoxHasBars = comBoxHasBars;
        this.comBoxToiletDoorDist = comBoxToiletDoorDist;
        this.comBoxWidth = comBoxWidth;
        this.comBoxHasLeftBar = comBoxHasLeftBar;
        this.comBoxLeftShapeBarA = comBoxLeftShapeBarA;
        this.comBoxLeftShapeBarB = comBoxLeftShapeBarB;
        this.comBoxLeftShapeBarC = comBoxLeftShapeBarC;
        this.comBoxLeftShapeBarD = comBoxLeftShapeBarD;
        this.comBoxLeftShapeBarDiam = comBoxLeftShapeBarDiam;
        this.comBoxLeftShapeBarDist = comBoxLeftShapeBarDist;
        this.comBoxLeftBarObs = comBoxLeftBarObs;
        this.comBoxHasRightBar = comBoxHasRightBar;
        this.comBoxRightShapeBarA = comBoxRightShapeBarA;
        this.comBoxRightShapeBarB = comBoxRightShapeBarB;
        this.comBoxRightShapeBarC = comBoxRightShapeBarC;
        this.comBoxRightShapeBarD = comBoxRightShapeBarD;
        this.comBoxRightShapeBarDiam = comBoxRightShapeBarDiam;
        this.comBoxRightShapeBarDist = comBoxRightShapeBarDist;
        this.comBoxRightBarObs = comBoxRightBarObs;
        this.comBoxObs = comBoxObs;
        this.upViewLength = upViewLength;
        this.upViewWidth = upViewWidth;
        this.upViewMeasureA = upViewMeasureA;
        this.upViewMeasureB = upViewMeasureB;
        this.upViewMeasureC = upViewMeasureC;
        this.upViewMeasureD = upViewMeasureD;
        this.upViewObs = upViewObs;
        this.restDrain = restDrain;
        this.restDrainObs = restDrainObs;
        this.boxUpperPhoto = boxUpperPhoto;
    }

    public String getBoxUpperPhoto() {
        return boxUpperPhoto;
    }

    public void setBoxUpperPhoto(String boxUpperPhoto) {
        this.boxUpperPhoto = boxUpperPhoto;
    }

    public int getBoxID() {
        return boxID;
    }

    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }

    public int getTypeBox() {
        return typeBox;
    }

    public void setTypeBox(int typeBox) {
        this.typeBox = typeBox;
    }

    public Double getComBoxDoorWidth() {
        return comBoxDoorWidth;
    }

    public void setComBoxDoorWidth(Double comBoxDoorWidth) {
        this.comBoxDoorWidth = comBoxDoorWidth;
    }

    public Double getComBoxFreeDiam() {
        return comBoxFreeDiam;
    }

    public void setComBoxFreeDiam(Double comBoxFreeDiam) {
        this.comBoxFreeDiam = comBoxFreeDiam;
    }

    public Integer getComBoxHasBars() {
        return comBoxHasBars;
    }

    public void setComBoxHasBars(Integer comBoxHasBars) {
        this.comBoxHasBars = comBoxHasBars;
    }

    public Double getComBoxToiletDoorDist() {
        return comBoxToiletDoorDist;
    }

    public void setComBoxToiletDoorDist(Double comBoxToiletDoorDist) {
        this.comBoxToiletDoorDist = comBoxToiletDoorDist;
    }

    public Double getComBoxWidth() {
        return comBoxWidth;
    }

    public void setComBoxWidth(Double comBoxWidth) {
        this.comBoxWidth = comBoxWidth;
    }

    public Integer getComBoxHasLeftBar() {
        return comBoxHasLeftBar;
    }

    public void setComBoxHasLeftBar(Integer comBoxHasLeftBar) {
        this.comBoxHasLeftBar = comBoxHasLeftBar;
    }

    public Double getComBoxLeftShapeBarA() {
        return comBoxLeftShapeBarA;
    }

    public void setComBoxLeftShapeBarA(Double comBoxLeftShapeBarA) {
        this.comBoxLeftShapeBarA = comBoxLeftShapeBarA;
    }

    public Double getComBoxLeftShapeBarB() {
        return comBoxLeftShapeBarB;
    }

    public void setComBoxLeftShapeBarB(Double comBoxLeftShapeBarB) {
        this.comBoxLeftShapeBarB = comBoxLeftShapeBarB;
    }

    public Double getComBoxLeftShapeBarC() {
        return comBoxLeftShapeBarC;
    }

    public void setComBoxLeftShapeBarC(Double comBoxLeftShapeBarC) {
        this.comBoxLeftShapeBarC = comBoxLeftShapeBarC;
    }

    public Double getComBoxLeftShapeBarD() {
        return comBoxLeftShapeBarD;
    }

    public void setComBoxLeftShapeBarD(Double comBoxLeftShapeBarD) {
        this.comBoxLeftShapeBarD = comBoxLeftShapeBarD;
    }

    public Double getComBoxLeftShapeBarDiam() {
        return comBoxLeftShapeBarDiam;
    }

    public void setComBoxLeftShapeBarDiam(Double comBoxLeftShapeBarDiam) {
        this.comBoxLeftShapeBarDiam = comBoxLeftShapeBarDiam;
    }

    public Double getComBoxLeftShapeBarDist() {
        return comBoxLeftShapeBarDist;
    }

    public void setComBoxLeftShapeBarDist(Double comBoxLeftShapeBarDist) {
        this.comBoxLeftShapeBarDist = comBoxLeftShapeBarDist;
    }

    public Integer getComBoxHasRightBar() {
        return comBoxHasRightBar;
    }

    public void setComBoxHasRightBar(Integer comBoxHasRightBar) {
        this.comBoxHasRightBar = comBoxHasRightBar;
    }

    public Double getComBoxRightShapeBarA() {
        return comBoxRightShapeBarA;
    }

    public void setComBoxRightShapeBarA(Double comBoxRightShapeBarA) {
        this.comBoxRightShapeBarA = comBoxRightShapeBarA;
    }

    public Double getComBoxRightShapeBarB() {
        return comBoxRightShapeBarB;
    }

    public void setComBoxRightShapeBarB(Double comBoxRightShapeBarB) {
        this.comBoxRightShapeBarB = comBoxRightShapeBarB;
    }

    public Double getComBoxRightShapeBarC() {
        return comBoxRightShapeBarC;
    }

    public void setComBoxRightShapeBarC(Double comBoxRightShapeBarC) {
        this.comBoxRightShapeBarC = comBoxRightShapeBarC;
    }

    public Double getComBoxRightShapeBarD() {
        return comBoxRightShapeBarD;
    }

    public void setComBoxRightShapeBarD(Double comBoxRightShapeBarD) {
        this.comBoxRightShapeBarD = comBoxRightShapeBarD;
    }

    public Double getComBoxRightShapeBarDiam() {
        return comBoxRightShapeBarDiam;
    }

    public void setComBoxRightShapeBarDiam(Double comBoxRightShapeBarDiam) {
        this.comBoxRightShapeBarDiam = comBoxRightShapeBarDiam;
    }

    public Double getComBoxRightShapeBarDist() {
        return comBoxRightShapeBarDist;
    }

    public void setComBoxRightShapeBarDist(Double comBoxRightShapeBarDist) {
        this.comBoxRightShapeBarDist = comBoxRightShapeBarDist;
    }

    public String getComBoxObs() {
        return comBoxObs;
    }

    public void setComBoxObs(String comBoxObs) {
        this.comBoxObs = comBoxObs;
    }

    public Double getUpViewLength() {
        return upViewLength;
    }

    public void setUpViewLength(Double upViewLength) {
        this.upViewLength = upViewLength;
    }

    public Double getUpViewWidth() {
        return upViewWidth;
    }

    public void setUpViewWidth(Double upViewWidth) {
        this.upViewWidth = upViewWidth;
    }

    public Double getUpViewMeasureA() {
        return upViewMeasureA;
    }

    public void setUpViewMeasureA(Double upViewMeasureA) {
        this.upViewMeasureA = upViewMeasureA;
    }

    public Double getUpViewMeasureB() {
        return upViewMeasureB;
    }

    public void setUpViewMeasureB(Double upViewMeasureB) {
        this.upViewMeasureB = upViewMeasureB;
    }

    public Double getUpViewMeasureC() {
        return upViewMeasureC;
    }

    public void setUpViewMeasureC(Double upViewMeasureC) {
        this.upViewMeasureC = upViewMeasureC;
    }

    public Double getUpViewMeasureD() {
        return upViewMeasureD;
    }

    public void setUpViewMeasureD(Double upViewMeasureD) {
        this.upViewMeasureD = upViewMeasureD;
    }

    public String getUpViewObs() {
        return upViewObs;
    }

    public void setUpViewObs(String upViewObs) {
        this.upViewObs = upViewObs;
    }

    public Integer getRestDrain() {
        return restDrain;
    }

    public void setRestDrain(Integer restDrain) {
        this.restDrain = restDrain;
    }

    public String getRestDrainObs() {
        return restDrainObs;
    }

    public void setRestDrainObs(String restDrainObs) {
        this.restDrainObs = restDrainObs;
    }

    public String getComBoxLeftBarObs() {
        return comBoxLeftBarObs;
    }

    public void setComBoxLeftBarObs(String comBoxLeftBarObs) {
        this.comBoxLeftBarObs = comBoxLeftBarObs;
    }

    public String getComBoxRightBarObs() {
        return comBoxRightBarObs;
    }

    public void setComBoxRightBarObs(String comBoxRightBarObs) {
        this.comBoxRightBarObs = comBoxRightBarObs;
    }
}
