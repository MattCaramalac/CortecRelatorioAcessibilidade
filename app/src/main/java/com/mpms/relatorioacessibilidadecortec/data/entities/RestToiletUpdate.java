package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestToiletUpdate {

    @PrimaryKey(autoGenerate = true)
    private int restroomID;
    private Integer toType;
    private Double toHeightNoSeat;
    private Integer toHasSeat;
    private Double toHeightSeat;
    private Integer toHasSoculo;
    private Double frSoculo;
    private Double latSoculo;
    private Integer socCorners;
    private Integer toHasFrontBar;
    private Double frBarA;
    private Double frBarB;
    private Double frBarC;
    private Double frBarSect;
    private Double frBarDist;
    private Integer toHasWall;
    private Integer hasHorBar;
    private Double horBarD;
    private Double horBarE;
    private Double horBarF;
    private Double horBarDistG;
    private Double horBarSect;
    private Double horBarDist;
    private Integer hasVertBar;
    private Double vertBarH;
    private Double vertBarI;
    private Double vertBarJ;
    private Double vertBarSect;
    private Double vertBarDist;
    private Integer hasArtBar;
    private Double artBarH;
    private Double artBarI;
    private Double artBarJ;
    private Double artBarSect;
    private String toActDesc;
    private Double toActHeight;
    private String toActObs;
    private Integer hasPapHolder;
    private Integer papHolderType;
    private Double papEmbDist;
    private Double papEmbHeight;
    private Integer papSupAlign;
    private Double papSupHeight;
    private String papHoldObs;
    private Integer hasDouche;
    private Double doucheHeight;
    private String doucheObs;
    private String toiletObs;

    public RestToiletUpdate(int restroomID, Integer toType, Double toHeightNoSeat, Integer toHasSeat, Double toHeightSeat, Integer toHasSoculo, Double frSoculo,
                            Double latSoculo, Integer socCorners, Integer toHasFrontBar, Double frBarA, Double frBarB, Double frBarC, Double frBarSect, Double frBarDist,
                            Integer toHasWall, Integer hasHorBar, Double horBarD, Double horBarE, Double horBarF, Double horBarDistG, Double horBarSect, Double horBarDist,
                            Integer hasVertBar, Double vertBarH, Double vertBarI, Double vertBarJ, Double vertBarSect, Double vertBarDist, Integer hasArtBar,
                            Double artBarH, Double artBarI, Double artBarJ, Double artBarSect, String toActDesc, Double toActHeight, String toActObs, Integer hasPapHolder,
                            Integer papHolderType, Double papEmbDist, Double papEmbHeight, Integer papSupAlign, Double papSupHeight, String papHoldObs, Integer hasDouche,
                            Double doucheHeight, String doucheObs, String toiletObs) {
        this.restroomID = restroomID;
        this.toType = toType;
        this.toHeightNoSeat = toHeightNoSeat;
        this.toHasSeat = toHasSeat;
        this.toHeightSeat = toHeightSeat;
        this.toHasSoculo = toHasSoculo;
        this.frSoculo = frSoculo;
        this.latSoculo = latSoculo;
        this.socCorners = socCorners;
        this.toHasFrontBar = toHasFrontBar;
        this.frBarA = frBarA;
        this.frBarB = frBarB;
        this.frBarC = frBarC;
        this.frBarSect = frBarSect;
        this.frBarDist = frBarDist;
        this.toHasWall = toHasWall;
        this.hasHorBar = hasHorBar;
        this.horBarD = horBarD;
        this.horBarE = horBarE;
        this.horBarF = horBarF;
        this.horBarDistG = horBarDistG;
        this.horBarSect = horBarSect;
        this.horBarDist = horBarDist;
        this.hasVertBar = hasVertBar;
        this.vertBarH = vertBarH;
        this.vertBarI = vertBarI;
        this.vertBarJ = vertBarJ;
        this.vertBarSect = vertBarSect;
        this.vertBarDist = vertBarDist;
        this.hasArtBar = hasArtBar;
        this.artBarH = artBarH;
        this.artBarI = artBarI;
        this.artBarJ = artBarJ;
        this.artBarSect = artBarSect;
        this.toActDesc = toActDesc;
        this.toActHeight = toActHeight;
        this.toActObs = toActObs;
        this.hasPapHolder = hasPapHolder;
        this.papHolderType = papHolderType;
        this.papEmbDist = papEmbDist;
        this.papEmbHeight = papEmbHeight;
        this.papSupAlign = papSupAlign;
        this.papSupHeight = papSupHeight;
        this.papHoldObs = papHoldObs;
        this.hasDouche = hasDouche;
        this.doucheHeight = doucheHeight;
        this.doucheObs = doucheObs;
        this.toiletObs = toiletObs;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public Integer getToType() {
        return toType;
    }

    public void setToType(Integer toType) {
        this.toType = toType;
    }

    public Double getToHeightNoSeat() {
        return toHeightNoSeat;
    }

    public void setToHeightNoSeat(Double toHeightNoSeat) {
        this.toHeightNoSeat = toHeightNoSeat;
    }

    public Integer getToHasSeat() {
        return toHasSeat;
    }

    public void setToHasSeat(Integer toHasSeat) {
        this.toHasSeat = toHasSeat;
    }

    public Double getToHeightSeat() {
        return toHeightSeat;
    }

    public void setToHeightSeat(Double toHeightSeat) {
        this.toHeightSeat = toHeightSeat;
    }

    public Integer getToHasSoculo() {
        return toHasSoculo;
    }

    public void setToHasSoculo(Integer toHasSoculo) {
        this.toHasSoculo = toHasSoculo;
    }

    public Double getFrSoculo() {
        return frSoculo;
    }

    public void setFrSoculo(Double frSoculo) {
        this.frSoculo = frSoculo;
    }

    public Double getLatSoculo() {
        return latSoculo;
    }

    public void setLatSoculo(Double latSoculo) {
        this.latSoculo = latSoculo;
    }

    public Integer getSocCorners() {
        return socCorners;
    }

    public void setSocCorners(Integer socCorners) {
        this.socCorners = socCorners;
    }

    public Integer getToHasFrontBar() {
        return toHasFrontBar;
    }

    public void setToHasFrontBar(Integer toHasFrontBar) {
        this.toHasFrontBar = toHasFrontBar;
    }

    public Double getFrBarA() {
        return frBarA;
    }

    public void setFrBarA(Double frBarA) {
        this.frBarA = frBarA;
    }

    public Double getFrBarB() {
        return frBarB;
    }

    public void setFrBarB(Double frBarB) {
        this.frBarB = frBarB;
    }

    public Double getFrBarC() {
        return frBarC;
    }

    public void setFrBarC(Double frBarC) {
        this.frBarC = frBarC;
    }

    public Double getFrBarSect() {
        return frBarSect;
    }

    public void setFrBarSect(Double frBarSect) {
        this.frBarSect = frBarSect;
    }

    public Double getFrBarDist() {
        return frBarDist;
    }

    public void setFrBarDist(Double frBarDist) {
        this.frBarDist = frBarDist;
    }

    public Integer getToHasWall() {
        return toHasWall;
    }

    public void setToHasWall(Integer toHasWall) {
        this.toHasWall = toHasWall;
    }

    public Integer getHasHorBar() {
        return hasHorBar;
    }

    public void setHasHorBar(Integer hasHorBar) {
        this.hasHorBar = hasHorBar;
    }

    public Double getHorBarD() {
        return horBarD;
    }

    public void setHorBarD(Double horBarD) {
        this.horBarD = horBarD;
    }

    public Double getHorBarE() {
        return horBarE;
    }

    public void setHorBarE(Double horBarE) {
        this.horBarE = horBarE;
    }

    public Double getHorBarF() {
        return horBarF;
    }

    public void setHorBarF(Double horBarF) {
        this.horBarF = horBarF;
    }

    public Double getHorBarDistG() {
        return horBarDistG;
    }

    public void setHorBarDistG(Double horBarDistG) {
        this.horBarDistG = horBarDistG;
    }

    public Double getHorBarSect() {
        return horBarSect;
    }

    public void setHorBarSect(Double horBarSect) {
        this.horBarSect = horBarSect;
    }

    public Double getHorBarDist() {
        return horBarDist;
    }

    public void setHorBarDist(Double horBarDist) {
        this.horBarDist = horBarDist;
    }

    public Integer getHasVertBar() {
        return hasVertBar;
    }

    public void setHasVertBar(Integer hasVertBar) {
        this.hasVertBar = hasVertBar;
    }

    public Double getVertBarH() {
        return vertBarH;
    }

    public void setVertBarH(Double vertBarH) {
        this.vertBarH = vertBarH;
    }

    public Double getVertBarI() {
        return vertBarI;
    }

    public void setVertBarI(Double vertBarI) {
        this.vertBarI = vertBarI;
    }

    public Double getVertBarJ() {
        return vertBarJ;
    }

    public void setVertBarJ(Double vertBarJ) {
        this.vertBarJ = vertBarJ;
    }

    public Double getVertBarSect() {
        return vertBarSect;
    }

    public void setVertBarSect(Double vertBarSect) {
        this.vertBarSect = vertBarSect;
    }

    public Double getVertBarDist() {
        return vertBarDist;
    }

    public void setVertBarDist(Double vertBarDist) {
        this.vertBarDist = vertBarDist;
    }

    public Integer getHasArtBar() {
        return hasArtBar;
    }

    public void setHasArtBar(Integer hasArtBar) {
        this.hasArtBar = hasArtBar;
    }

    public Double getArtBarH() {
        return artBarH;
    }

    public void setArtBarH(Double artBarH) {
        this.artBarH = artBarH;
    }

    public Double getArtBarI() {
        return artBarI;
    }

    public void setArtBarI(Double artBarI) {
        this.artBarI = artBarI;
    }

    public Double getArtBarJ() {
        return artBarJ;
    }

    public void setArtBarJ(Double artBarJ) {
        this.artBarJ = artBarJ;
    }

    public Double getArtBarSect() {
        return artBarSect;
    }

    public void setArtBarSect(Double artBarSect) {
        this.artBarSect = artBarSect;
    }

    public String getToActDesc() {
        return toActDesc;
    }

    public void setToActDesc(String toActDesc) {
        this.toActDesc = toActDesc;
    }

    public Double getToActHeight() {
        return toActHeight;
    }

    public void setToActHeight(Double toActHeight) {
        this.toActHeight = toActHeight;
    }

    public String getToActObs() {
        return toActObs;
    }

    public void setToActObs(String toActObs) {
        this.toActObs = toActObs;
    }

    public Integer getHasPapHolder() {
        return hasPapHolder;
    }

    public void setHasPapHolder(Integer hasPapHolder) {
        this.hasPapHolder = hasPapHolder;
    }

    public Integer getPapHolderType() {
        return papHolderType;
    }

    public void setPapHolderType(Integer papHolderType) {
        this.papHolderType = papHolderType;
    }

    public Double getPapEmbDist() {
        return papEmbDist;
    }

    public void setPapEmbDist(Double papEmbDist) {
        this.papEmbDist = papEmbDist;
    }

    public Double getPapEmbHeight() {
        return papEmbHeight;
    }

    public void setPapEmbHeight(Double papEmbHeight) {
        this.papEmbHeight = papEmbHeight;
    }

    public Integer getPapSupAlign() {
        return papSupAlign;
    }

    public void setPapSupAlign(Integer papSupAlign) {
        this.papSupAlign = papSupAlign;
    }

    public Double getPapSupHeight() {
        return papSupHeight;
    }

    public void setPapSupHeight(Double papSupHeight) {
        this.papSupHeight = papSupHeight;
    }

    public String getPapHoldObs() {
        return papHoldObs;
    }

    public void setPapHoldObs(String papHoldObs) {
        this.papHoldObs = papHoldObs;
    }

    public Integer getHasDouche() {
        return hasDouche;
    }

    public void setHasDouche(Integer hasDouche) {
        this.hasDouche = hasDouche;
    }

    public Double getDoucheHeight() {
        return doucheHeight;
    }

    public void setDoucheHeight(Double doucheHeight) {
        this.doucheHeight = doucheHeight;
    }

    public String getDoucheObs() {
        return doucheObs;
    }

    public void setDoucheObs(String doucheObs) {
        this.doucheObs = doucheObs;
    }

    public String getToiletObs() {
        return toiletObs;
    }

    public void setToiletObs(String toiletObs) {
        this.toiletObs = toiletObs;
    }
}
