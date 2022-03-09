package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestroomSinkTwo {

    @PrimaryKey(autoGenerate = true)
    private int sinkID;
    private Double sinkMeasureI;
    private Double sinkMeasureJ;
    private Double sinkMeasureK;
    private Double sinkMeasureL;
    private Double sinkMeasureM;
    private Double sinkMeasureN;
    private String sinkObsItoN;
    private Double sinkMeasureO;
    private Double sinkMeasureP;
    private Double sinkMeasureQ;
    private Double sinkMeasureR;
    private Double sinkMeasureS;
    private Double sinkMeasureT;
    private String sinkObsOtoT;

    public RestroomSinkTwo(int sinkID, Double sinkMeasureI, Double sinkMeasureJ, Double sinkMeasureK, Double sinkMeasureL,
                           Double sinkMeasureM, Double sinkMeasureN, String sinkObsItoN, Double sinkMeasureO, Double sinkMeasureP,
                           Double sinkMeasureQ, Double sinkMeasureR, Double sinkMeasureS, Double sinkMeasureT, String sinkObsOtoT) {
        this.sinkID = sinkID;
        this.sinkMeasureI = sinkMeasureI;
        this.sinkMeasureJ = sinkMeasureJ;
        this.sinkMeasureK = sinkMeasureK;
        this.sinkMeasureL = sinkMeasureL;
        this.sinkMeasureM = sinkMeasureM;
        this.sinkMeasureN = sinkMeasureN;
        this.sinkObsItoN = sinkObsItoN;
        this.sinkMeasureO = sinkMeasureO;
        this.sinkMeasureP = sinkMeasureP;
        this.sinkMeasureQ = sinkMeasureQ;
        this.sinkMeasureR = sinkMeasureR;
        this.sinkMeasureS = sinkMeasureS;
        this.sinkMeasureT = sinkMeasureT;
        this.sinkObsOtoT = sinkObsOtoT;
    }

    public int getSinkID() {
        return sinkID;
    }

    public void setSinkID(int sinkID) {
        this.sinkID = sinkID;
    }

    public Double getSinkMeasureI() {
        return sinkMeasureI;
    }

    public void setSinkMeasureI(Double sinkMeasureI) {
        this.sinkMeasureI = sinkMeasureI;
    }

    public Double getSinkMeasureJ() {
        return sinkMeasureJ;
    }

    public void setSinkMeasureJ(Double sinkMeasureJ) {
        this.sinkMeasureJ = sinkMeasureJ;
    }

    public Double getSinkMeasureK() {
        return sinkMeasureK;
    }

    public void setSinkMeasureK(Double sinkMeasureK) {
        this.sinkMeasureK = sinkMeasureK;
    }

    public Double getSinkMeasureL() {
        return sinkMeasureL;
    }

    public void setSinkMeasureL(Double sinkMeasureL) {
        this.sinkMeasureL = sinkMeasureL;
    }

    public Double getSinkMeasureM() {
        return sinkMeasureM;
    }

    public void setSinkMeasureM(Double sinkMeasureM) {
        this.sinkMeasureM = sinkMeasureM;
    }

    public Double getSinkMeasureN() {
        return sinkMeasureN;
    }

    public void setSinkMeasureN(Double sinkMeasureN) {
        this.sinkMeasureN = sinkMeasureN;
    }

    public String getSinkObsItoN() {
        return sinkObsItoN;
    }

    public void setSinkObsItoN(String sinkObsItoN) {
        this.sinkObsItoN = sinkObsItoN;
    }

    public Double getSinkMeasureO() {
        return sinkMeasureO;
    }

    public void setSinkMeasureO(Double sinkMeasureO) {
        this.sinkMeasureO = sinkMeasureO;
    }

    public Double getSinkMeasureP() {
        return sinkMeasureP;
    }

    public void setSinkMeasureP(Double sinkMeasureP) {
        this.sinkMeasureP = sinkMeasureP;
    }

    public Double getSinkMeasureQ() {
        return sinkMeasureQ;
    }

    public void setSinkMeasureQ(Double sinkMeasureQ) {
        this.sinkMeasureQ = sinkMeasureQ;
    }

    public Double getSinkMeasureR() {
        return sinkMeasureR;
    }

    public void setSinkMeasureR(Double sinkMeasureR) {
        this.sinkMeasureR = sinkMeasureR;
    }

    public Double getSinkMeasureS() {
        return sinkMeasureS;
    }

    public void setSinkMeasureS(Double sinkMeasureS) {
        this.sinkMeasureS = sinkMeasureS;
    }

    public Double getSinkMeasureT() {
        return sinkMeasureT;
    }

    public void setSinkMeasureT(Double sinkMeasureT) {
        this.sinkMeasureT = sinkMeasureT;
    }

    public String getSinkObsOtoT() {
        return sinkObsOtoT;
    }

    public void setSinkObsOtoT(String sinkObsOtoT) {
        this.sinkObsOtoT = sinkObsOtoT;
    }
}
