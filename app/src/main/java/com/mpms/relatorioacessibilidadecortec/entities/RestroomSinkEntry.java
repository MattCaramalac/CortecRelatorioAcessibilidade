package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = RestroomEntry.class, parentColumns = "restroomID",
        childColumns = "restroomID", onDelete = CASCADE, onUpdate = CASCADE))
public class RestroomSinkEntry {

    @PrimaryKey(autoGenerate = true)
    private int sinkID;
    private int restroomID;
    private Double sinkMeasureA;
    private Double sinkMeasureB;
    private Double sinkMeasureC;
    private Double sinkMeasureD;
    private Double sinkMeasureE;
    private String sinkObsAtoE;
    private Double sinkMeasureF;
    private Double sinkMeasureG;
    private Double sinkMeasureH;
    private String sinkObsFtoH;
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

    public RestroomSinkEntry(int restroomID, Double sinkMeasureA, Double sinkMeasureB, Double sinkMeasureC,
                             Double sinkMeasureD, Double sinkMeasureE, String sinkObsAtoE, Double sinkMeasureF,
                             Double sinkMeasureG, Double sinkMeasureH, String sinkObsFtoH, Double sinkMeasureI,
                             Double sinkMeasureJ, Double sinkMeasureK, Double sinkMeasureL, Double sinkMeasureM,
                             Double sinkMeasureN, String sinkObsItoN, Double sinkMeasureO, Double sinkMeasureP,
                             Double sinkMeasureQ, Double sinkMeasureR, Double sinkMeasureS, Double sinkMeasureT,
                             String sinkObsOtoT) {
        this.restroomID = restroomID;
        this.sinkMeasureA = sinkMeasureA;
        this.sinkMeasureB = sinkMeasureB;
        this.sinkMeasureC = sinkMeasureC;
        this.sinkMeasureD = sinkMeasureD;
        this.sinkMeasureE = sinkMeasureE;
        this.sinkObsAtoE = sinkObsAtoE;
        this.sinkMeasureF = sinkMeasureF;
        this.sinkMeasureG = sinkMeasureG;
        this.sinkMeasureH = sinkMeasureH;
        this.sinkObsFtoH = sinkObsFtoH;
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

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public Double getSinkMeasureA() {
        return sinkMeasureA;
    }

    public void setSinkMeasureA(Double sinkMeasureA) {
        this.sinkMeasureA = sinkMeasureA;
    }

    public Double getSinkMeasureB() {
        return sinkMeasureB;
    }

    public void setSinkMeasureB(Double sinkMeasureB) {
        this.sinkMeasureB = sinkMeasureB;
    }

    public Double getSinkMeasureC() {
        return sinkMeasureC;
    }

    public void setSinkMeasureC(Double sinkMeasureC) {
        this.sinkMeasureC = sinkMeasureC;
    }

    public Double getSinkMeasureD() {
        return sinkMeasureD;
    }

    public void setSinkMeasureD(Double sinkMeasureD) {
        this.sinkMeasureD = sinkMeasureD;
    }

    public Double getSinkMeasureE() {
        return sinkMeasureE;
    }

    public void setSinkMeasureE(Double sinkMeasureE) {
        this.sinkMeasureE = sinkMeasureE;
    }

    public String getSinkObsAtoE() {
        return sinkObsAtoE;
    }

    public void setSinkObsAtoE(String sinkObsAtoE) {
        this.sinkObsAtoE = sinkObsAtoE;
    }

    public Double getSinkMeasureF() {
        return sinkMeasureF;
    }

    public void setSinkMeasureF(Double sinkMeasureF) {
        this.sinkMeasureF = sinkMeasureF;
    }

    public Double getSinkMeasureG() {
        return sinkMeasureG;
    }

    public void setSinkMeasureG(Double sinkMeasureG) {
        this.sinkMeasureG = sinkMeasureG;
    }

    public Double getSinkMeasureH() {
        return sinkMeasureH;
    }

    public void setSinkMeasureH(Double sinkMeasureH) {
        this.sinkMeasureH = sinkMeasureH;
    }

    public String getSinkObsFtoH() {
        return sinkObsFtoH;
    }

    public void setSinkObsFtoH(String sinkObsFtoH) {
        this.sinkObsFtoH = sinkObsFtoH;
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
