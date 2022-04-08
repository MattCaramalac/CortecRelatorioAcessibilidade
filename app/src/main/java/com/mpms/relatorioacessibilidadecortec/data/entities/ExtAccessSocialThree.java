package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExtAccessSocialThree {

    @PrimaryKey
    private int externalAccessID;

    private Integer gateSillType;
    private Double sillInclinationHeight;
    private Double sillStepHeight;
    private Integer slopeMeasureQnt;
    private Double sillSlopeAngle;
    private Double sillSlopeAngle2;
    private Double sillSlopeAngle3;
    private Double sillSlopeAngle4;
    private Double sillSlopeWidth;
    private String gateSillObs;
    private Integer gateHasObstacles;
    private Integer gateHasPayphones;
    private Integer gateHasIntercom;
    private Double intercomHeight;
    private String extAccessObs;

    public ExtAccessSocialThree(int externalAccessID, Integer gateSillType, Double sillInclinationHeight, Double sillStepHeight, Integer slopeMeasureQnt, Double sillSlopeAngle,
                                Double sillSlopeAngle2, Double sillSlopeAngle3, Double sillSlopeAngle4, Double sillSlopeWidth, String gateSillObs, Integer gateHasObstacles,
                                Integer gateHasPayphones, Integer gateHasIntercom, Double intercomHeight, String extAccessObs) {
        this.externalAccessID = externalAccessID;
        this.gateSillType = gateSillType;
        this.sillInclinationHeight = sillInclinationHeight;
        this.sillStepHeight = sillStepHeight;
        this.slopeMeasureQnt = slopeMeasureQnt;
        this.sillSlopeAngle = sillSlopeAngle;
        this.sillSlopeAngle2 = sillSlopeAngle2;
        this.sillSlopeAngle3 = sillSlopeAngle3;
        this.sillSlopeAngle4 = sillSlopeAngle4;
        this.sillSlopeWidth = sillSlopeWidth;
        this.gateSillObs = gateSillObs;
        this.gateHasObstacles = gateHasObstacles;
        this.gateHasPayphones = gateHasPayphones;
        this.gateHasIntercom = gateHasIntercom;
        this.intercomHeight = intercomHeight;
        this.extAccessObs = extAccessObs;
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
}


