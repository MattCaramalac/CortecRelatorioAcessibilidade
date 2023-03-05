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
    private String comBoxLeftHorObs;
    private Double comBoxLeftVertBarA;
    private Double comBoxLeftVertBarB;
    private Double comBoxLeftVertBarC;
    private Double comBoxLeftVertBarDiam;
    private Double comBoxLeftVertBarDist;
    private String comBoxLeftVertObs;
    private Integer comBoxHasRightBar;
    private Double comBoxRightShapeBarA;
    private Double comBoxRightShapeBarB;
    private Double comBoxRightShapeBarC;
    private Double comBoxRightShapeBarD;
    private Double comBoxRightShapeBarDiam;
    private Double comBoxRightShapeBarDist;
    private String comBoxRightHorObs;
    private Double comBoxRightVertBarA;
    private Double comBoxRightVertBarB;
    private Double comBoxRightVertBarC;
    private Double comBoxRightVertBarDiam;
    private Double comBoxRightVertBarDist;
    private String comBoxRightVertObs;
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

    public RestBoxFirstUpdate(int boxID, int typeBox, Double comBoxDoorWidth, Double comBoxFreeDiam, Integer comBoxHasBars, Double comBoxToiletDoorDist, Double comBoxWidth,
                              Integer comBoxHasLeftBar, Double comBoxLeftShapeBarA, Double comBoxLeftShapeBarB, Double comBoxLeftShapeBarC, Double comBoxLeftShapeBarD,
                              Double comBoxLeftShapeBarDiam, Double comBoxLeftShapeBarDist, String comBoxLeftHorObs, Double comBoxLeftVertBarA, Double comBoxLeftVertBarB,
                              Double comBoxLeftVertBarC, Double comBoxLeftVertBarDiam, Double comBoxLeftVertBarDist, String comBoxLeftVertObs, Integer comBoxHasRightBar,
                              Double comBoxRightShapeBarA, Double comBoxRightShapeBarB, Double comBoxRightShapeBarC, Double comBoxRightShapeBarD, Double comBoxRightShapeBarDiam,
                              Double comBoxRightShapeBarDist, String comBoxRightHorObs, Double comBoxRightVertBarA, Double comBoxRightVertBarB, Double comBoxRightVertBarC,
                              Double comBoxRightVertBarDiam, Double comBoxRightVertBarDist, String comBoxRightVertObs, String comBoxObs, Double upViewLength, Double upViewWidth,
                              Double upViewMeasureA, Double upViewMeasureB, Double upViewMeasureC, Double upViewMeasureD, String upViewObs, Integer restDrain, String restDrainObs) {
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
        this.comBoxLeftHorObs = comBoxLeftHorObs;
        this.comBoxLeftVertBarA = comBoxLeftVertBarA;
        this.comBoxLeftVertBarB = comBoxLeftVertBarB;
        this.comBoxLeftVertBarC = comBoxLeftVertBarC;
        this.comBoxLeftVertBarDiam = comBoxLeftVertBarDiam;
        this.comBoxLeftVertBarDist = comBoxLeftVertBarDist;
        this.comBoxLeftVertObs = comBoxLeftVertObs;
        this.comBoxHasRightBar = comBoxHasRightBar;
        this.comBoxRightShapeBarA = comBoxRightShapeBarA;
        this.comBoxRightShapeBarB = comBoxRightShapeBarB;
        this.comBoxRightShapeBarC = comBoxRightShapeBarC;
        this.comBoxRightShapeBarD = comBoxRightShapeBarD;
        this.comBoxRightShapeBarDiam = comBoxRightShapeBarDiam;
        this.comBoxRightShapeBarDist = comBoxRightShapeBarDist;
        this.comBoxRightHorObs = comBoxRightHorObs;
        this.comBoxRightVertBarA = comBoxRightVertBarA;
        this.comBoxRightVertBarB = comBoxRightVertBarB;
        this.comBoxRightVertBarC = comBoxRightVertBarC;
        this.comBoxRightVertBarDiam = comBoxRightVertBarDiam;
        this.comBoxRightVertBarDist = comBoxRightVertBarDist;
        this.comBoxRightVertObs = comBoxRightVertObs;
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

    public Double getComBoxLeftVertBarA() {
        return comBoxLeftVertBarA;
    }

    public void setComBoxLeftVertBarA(Double comBoxLeftVertBarA) {
        this.comBoxLeftVertBarA = comBoxLeftVertBarA;
    }

    public Double getComBoxLeftVertBarB() {
        return comBoxLeftVertBarB;
    }

    public void setComBoxLeftVertBarB(Double comBoxLeftVertBarB) {
        this.comBoxLeftVertBarB = comBoxLeftVertBarB;
    }

    public Double getComBoxLeftVertBarC() {
        return comBoxLeftVertBarC;
    }

    public void setComBoxLeftVertBarC(Double comBoxLeftVertBarC) {
        this.comBoxLeftVertBarC = comBoxLeftVertBarC;
    }

    public Double getComBoxLeftVertBarDiam() {
        return comBoxLeftVertBarDiam;
    }

    public void setComBoxLeftVertBarDiam(Double comBoxLeftVertBarDiam) {
        this.comBoxLeftVertBarDiam = comBoxLeftVertBarDiam;
    }

    public Double getComBoxLeftVertBarDist() {
        return comBoxLeftVertBarDist;
    }

    public void setComBoxLeftVertBarDist(Double comBoxLeftVertBarDist) {
        this.comBoxLeftVertBarDist = comBoxLeftVertBarDist;
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

    public Double getComBoxRightVertBarA() {
        return comBoxRightVertBarA;
    }

    public void setComBoxRightVertBarA(Double comBoxRightVertBarA) {
        this.comBoxRightVertBarA = comBoxRightVertBarA;
    }

    public Double getComBoxRightVertBarB() {
        return comBoxRightVertBarB;
    }

    public void setComBoxRightVertBarB(Double comBoxRightVertBarB) {
        this.comBoxRightVertBarB = comBoxRightVertBarB;
    }

    public Double getComBoxRightVertBarC() {
        return comBoxRightVertBarC;
    }

    public void setComBoxRightVertBarC(Double comBoxRightVertBarC) {
        this.comBoxRightVertBarC = comBoxRightVertBarC;
    }

    public Double getComBoxRightVertBarDiam() {
        return comBoxRightVertBarDiam;
    }

    public void setComBoxRightVertBarDiam(Double comBoxRightVertBarDiam) {
        this.comBoxRightVertBarDiam = comBoxRightVertBarDiam;
    }

    public Double getComBoxRightVertBarDist() {
        return comBoxRightVertBarDist;
    }

    public void setComBoxRightVertBarDist(Double comBoxRightVertBarDist) {
        this.comBoxRightVertBarDist = comBoxRightVertBarDist;
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

    public String getComBoxLeftHorObs() {
        return comBoxLeftHorObs;
    }

    public void setComBoxLeftHorObs(String comBoxLeftHorObs) {
        this.comBoxLeftHorObs = comBoxLeftHorObs;
    }

    public String getComBoxLeftVertObs() {
        return comBoxLeftVertObs;
    }

    public void setComBoxLeftVertObs(String comBoxLeftVertObs) {
        this.comBoxLeftVertObs = comBoxLeftVertObs;
    }

    public String getComBoxRightHorObs() {
        return comBoxRightHorObs;
    }

    public void setComBoxRightHorObs(String comBoxRightHorObs) {
        this.comBoxRightHorObs = comBoxRightHorObs;
    }

    public String getComBoxRightVertObs() {
        return comBoxRightVertObs;
    }

    public void setComBoxRightVertObs(String comBoxRightVertObs) {
        this.comBoxRightVertObs = comBoxRightVertObs;
    }
}
