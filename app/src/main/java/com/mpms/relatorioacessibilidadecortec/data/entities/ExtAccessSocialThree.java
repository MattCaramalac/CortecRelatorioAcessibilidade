package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExtAccessSocialThree {

    @PrimaryKey
    private int externalAccessID;

    private Integer gateSillType;
    private Double sillInclinationHeight;
    private Integer inclQnt;
    private Double inclAngle1;
    private Double inclAngle2;
    private Double inclAngle3;
    private Double inclAngle4;
    private Double sillStepHeight;
    private Integer slopeMeasureQnt;
    private Double sillSlopeAngle;
    private Double sillSlopeAngle2;
    private Double sillSlopeAngle3;
    private Double sillSlopeAngle4;
    private Double sillSlopeWidth;
    private Double sillSlopeHeight;
    private String gateSillObs;
    private Integer gateHasObstacles;
    private Integer gateHasPayphones;
    private Integer gateHasIntercom;
    private Double intercomHeight;
    private Integer gateHasStairs;
    private Integer gateHasRamps;
    private String extAccessObs;
    private Integer hasSillIncl;
    private String extAccPhotos3;

    public ExtAccessSocialThree(int externalAccessID, Integer gateSillType, Double sillInclinationHeight, Integer inclQnt, Double inclAngle1, Double inclAngle2,
                                Double inclAngle3, Double inclAngle4,Double sillStepHeight, Integer slopeMeasureQnt, Double sillSlopeAngle, Double sillSlopeAngle2,
                                Double sillSlopeAngle3, Double sillSlopeAngle4, Double sillSlopeWidth, Double sillSlopeHeight, String gateSillObs, Integer gateHasObstacles,
                                Integer gateHasPayphones, Integer gateHasIntercom, Double intercomHeight, String extAccessObs, Integer gateHasStairs, Integer gateHasRamps,
                                Integer hasSillIncl, String extAccPhotos3) {
        this.externalAccessID = externalAccessID;
        this.gateSillType = gateSillType;
        this.sillInclinationHeight = sillInclinationHeight;
        this.inclQnt = inclQnt;
        this.inclAngle1 = inclAngle1;
        this.inclAngle2 = inclAngle2;
        this.inclAngle3 = inclAngle3;
        this.inclAngle4 = inclAngle4;
        this.sillStepHeight = sillStepHeight;
        this.slopeMeasureQnt = slopeMeasureQnt;
        this.sillSlopeAngle = sillSlopeAngle;
        this.sillSlopeAngle2 = sillSlopeAngle2;
        this.sillSlopeAngle3 = sillSlopeAngle3;
        this.sillSlopeAngle4 = sillSlopeAngle4;
        this.sillSlopeWidth = sillSlopeWidth;
        this.sillSlopeHeight = sillSlopeHeight;
        this.gateSillObs = gateSillObs;
        this.gateHasObstacles = gateHasObstacles;
        this.gateHasPayphones = gateHasPayphones;
        this.gateHasIntercom = gateHasIntercom;
        this.intercomHeight = intercomHeight;
        this.extAccessObs = extAccessObs;
        this.gateHasStairs = gateHasStairs;
        this.gateHasRamps = gateHasRamps;
        this.hasSillIncl = hasSillIncl;
        this.extAccPhotos3 = extAccPhotos3;
    }

    public int getExternalAccessID() {
        return externalAccessID;
    }

    public void setExternalAccessID(int externalAccessID) {
        this.externalAccessID = externalAccessID;
    }

    public Integer getGateSillType() {
        return gateSillType;
    }

    public void setGateSillType(Integer gateSillType) {
        this.gateSillType = gateSillType;
    }

    public Double getSillInclinationHeight() {
        return sillInclinationHeight;
    }

    public void setSillInclinationHeight(Double sillInclinationHeight) {
        this.sillInclinationHeight = sillInclinationHeight;
    }

    public Integer getInclQnt() {
        return inclQnt;
    }

    public void setInclQnt(Integer inclQnt) {
        this.inclQnt = inclQnt;
    }

    public Double getInclAngle1() {
        return inclAngle1;
    }

    public void setInclAngle1(Double inclAngle1) {
        this.inclAngle1 = inclAngle1;
    }

    public Double getInclAngle2() {
        return inclAngle2;
    }

    public void setInclAngle2(Double inclAngle2) {
        this.inclAngle2 = inclAngle2;
    }

    public Double getInclAngle3() {
        return inclAngle3;
    }

    public void setInclAngle3(Double inclAngle3) {
        this.inclAngle3 = inclAngle3;
    }

    public Double getInclAngle4() {
        return inclAngle4;
    }

    public void setInclAngle4(Double inclAngle4) {
        this.inclAngle4 = inclAngle4;
    }

    public Double getSillStepHeight() {
        return sillStepHeight;
    }

    public void setSillStepHeight(Double sillStepHeight) {
        this.sillStepHeight = sillStepHeight;
    }

    public Integer getSlopeMeasureQnt() {
        return slopeMeasureQnt;
    }

    public void setSlopeMeasureQnt(Integer slopeMeasureQnt) {
        this.slopeMeasureQnt = slopeMeasureQnt;
    }

    public Double getSillSlopeAngle() {
        return sillSlopeAngle;
    }

    public void setSillSlopeAngle(Double sillSlopeAngle) {
        this.sillSlopeAngle = sillSlopeAngle;
    }

    public Double getSillSlopeAngle2() {
        return sillSlopeAngle2;
    }

    public void setSillSlopeAngle2(Double sillSlopeAngle2) {
        this.sillSlopeAngle2 = sillSlopeAngle2;
    }

    public Double getSillSlopeAngle3() {
        return sillSlopeAngle3;
    }

    public void setSillSlopeAngle3(Double sillSlopeAngle3) {
        this.sillSlopeAngle3 = sillSlopeAngle3;
    }

    public Double getSillSlopeAngle4() {
        return sillSlopeAngle4;
    }

    public void setSillSlopeAngle4(Double sillSlopeAngle4) {
        this.sillSlopeAngle4 = sillSlopeAngle4;
    }

    public Double getSillSlopeWidth() {
        return sillSlopeWidth;
    }

    public void setSillSlopeWidth(Double sillSlopeWidth) {
        this.sillSlopeWidth = sillSlopeWidth;
    }

    public String getGateSillObs() {
        return gateSillObs;
    }

    public void setGateSillObs(String gateSillObs) {
        this.gateSillObs = gateSillObs;
    }

    public Integer getGateHasObstacles() {
        return gateHasObstacles;
    }

    public void setGateHasObstacles(Integer gateHasObstacles) {
        this.gateHasObstacles = gateHasObstacles;
    }

    public Integer getGateHasPayphones() {
        return gateHasPayphones;
    }

    public void setGateHasPayphones(Integer gateHasPayphones) {
        this.gateHasPayphones = gateHasPayphones;
    }

    public Integer getGateHasIntercom() {
        return gateHasIntercom;
    }

    public void setGateHasIntercom(Integer gateHasIntercom) {
        this.gateHasIntercom = gateHasIntercom;
    }

    public Double getIntercomHeight() {
        return intercomHeight;
    }

    public void setIntercomHeight(Double intercomHeight) {
        this.intercomHeight = intercomHeight;
    }

    public String getExtAccessObs() {
        return extAccessObs;
    }

    public void setExtAccessObs(String extAccessObs) {
        this.extAccessObs = extAccessObs;
    }

    public Integer getGateHasStairs() {
        return gateHasStairs;
    }

    public void setGateHasStairs(Integer gateHasStairs) {
        this.gateHasStairs = gateHasStairs;
    }

    public Integer getGateHasRamps() {
        return gateHasRamps;
    }

    public void setGateHasRamps(Integer gateHasRamps) {
        this.gateHasRamps = gateHasRamps;
    }

    public Double getSillSlopeHeight() {
        return sillSlopeHeight;
    }

    public void setSillSlopeHeight(Double sillSlopeHeight) {
        this.sillSlopeHeight = sillSlopeHeight;
    }

    public Integer getHasSillIncl() {
        return hasSillIncl;
    }

    public void setHasSillIncl(Integer hasSillIncl) {
        this.hasSillIncl = hasSillIncl;
    }

    public String getExtAccPhotos() {
        return extAccPhotos3;
    }

    public void setExtAccPhotos(String extAccPhotos) {
        this.extAccPhotos3 = extAccPhotos;
    }
}


