package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestBoxAccOneUpdate {

    @PrimaryKey
    private int boxID;
    private Integer hasHanger;
    private Double hangerHeight;
    private String hangerObs;
    private Integer hasObjHold;
    private Integer objHoldCorrect;
    private Double objHoldHeight;
    private String objHoldObs;
    private Integer hasSoapHold;
    private Double soapHoldHeight;
    private String soapHoldObs;
    private Integer hasTowelHold;
    private Double towelHoldHeight;
    private String towelHoldObs;

    public RestBoxAccOneUpdate(int boxID, Integer hasHanger, Double hangerHeight, String hangerObs, Integer hasObjHold, Integer objHoldCorrect, Double objHoldHeight,
                               String objHoldObs, Integer hasSoapHold, Double soapHoldHeight, String soapHoldObs, Integer hasTowelHold, Double towelHoldHeight, String towelHoldObs) {
        this.boxID = boxID;
        this.hasHanger = hasHanger;
        this.hangerHeight = hangerHeight;
        this.hangerObs = hangerObs;
        this.hasObjHold = hasObjHold;
        this.objHoldCorrect = objHoldCorrect;
        this.objHoldHeight = objHoldHeight;
        this.objHoldObs = objHoldObs;
        this.hasSoapHold = hasSoapHold;
        this.soapHoldHeight = soapHoldHeight;
        this.soapHoldObs = soapHoldObs;
        this.hasTowelHold = hasTowelHold;
        this.towelHoldHeight = towelHoldHeight;
        this.towelHoldObs = towelHoldObs;
    }

    public int getBoxID() {
        return boxID;
    }

    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }

    public Integer getHasHanger() {
        return hasHanger;
    }

    public void setHasHanger(Integer hasHanger) {
        this.hasHanger = hasHanger;
    }

    public Double getHangerHeight() {
        return hangerHeight;
    }

    public void setHangerHeight(Double hangerHeight) {
        this.hangerHeight = hangerHeight;
    }

    public String getHangerObs() {
        return hangerObs;
    }

    public void setHangerObs(String hangerObs) {
        this.hangerObs = hangerObs;
    }

    public Integer getHasObjHold() {
        return hasObjHold;
    }

    public void setHasObjHold(Integer hasObjHold) {
        this.hasObjHold = hasObjHold;
    }

    public Integer getObjHoldCorrect() {
        return objHoldCorrect;
    }

    public void setObjHoldCorrect(Integer objHoldCorrect) {
        this.objHoldCorrect = objHoldCorrect;
    }

    public Double getObjHoldHeight() {
        return objHoldHeight;
    }

    public void setObjHoldHeight(Double objHoldHeight) {
        this.objHoldHeight = objHoldHeight;
    }

    public String getObjHoldObs() {
        return objHoldObs;
    }

    public void setObjHoldObs(String objHoldObs) {
        this.objHoldObs = objHoldObs;
    }

    public Integer getHasSoapHold() {
        return hasSoapHold;
    }

    public void setHasSoapHold(Integer hasSoapHold) {
        this.hasSoapHold = hasSoapHold;
    }

    public Double getSoapHoldHeight() {
        return soapHoldHeight;
    }

    public void setSoapHoldHeight(Double soapHoldHeight) {
        this.soapHoldHeight = soapHoldHeight;
    }

    public String getSoapHoldObs() {
        return soapHoldObs;
    }

    public void setSoapHoldObs(String soapHoldObs) {
        this.soapHoldObs = soapHoldObs;
    }

    public Integer getHasTowelHold() {
        return hasTowelHold;
    }

    public void setHasTowelHold(Integer hasTowelHold) {
        this.hasTowelHold = hasTowelHold;
    }

    public Double getTowelHoldHeight() {
        return towelHoldHeight;
    }

    public void setTowelHoldHeight(Double towelHoldHeight) {
        this.towelHoldHeight = towelHoldHeight;
    }

    public String getTowelHoldObs() {
        return towelHoldObs;
    }

    public void setTowelHoldObs(String towelHoldObs) {
        this.towelHoldObs = towelHoldObs;
    }
}
