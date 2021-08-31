package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestroomDoorUpdate {
    @PrimaryKey
    private int restroomID;
    private Double doorWidth;
    private Integer doorSIA;
    private String doorSIAObs;
    private Integer doorExtOp;
    private String doorExtOpObs;
    private Integer doorVertSign;
    private String doorVertSignObs;
    private Integer doorSillType;
    private Double inclinationSillHeight;
    private Double stepSillHeight;
    private Double slopeSillInclination;
    private Double slopeSillWidth;
    private String doorSillTypeObs;
    private Integer doorTactileSign;
    private String doorTactileSignObs;
    private Integer doorIntCoating;
    private String doorIntCoatingObs;
    private Integer doorHorizontalHandle;
    private Double handleHeight;
    private Double handleLength;
    private String handleObs;

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public Double getDoorWidth() {
        return doorWidth;
    }

    public void setDoorWidth(Double doorWidth) {
        this.doorWidth = doorWidth;
    }

    public Integer getDoorSIA() {
        return doorSIA;
    }

    public void setDoorSIA(Integer doorSIA) {
        this.doorSIA = doorSIA;
    }

    public String getDoorSIAObs() {
        return doorSIAObs;
    }

    public void setDoorSIAObs(String doorSIAObs) {
        this.doorSIAObs = doorSIAObs;
    }

    public Integer getDoorExtOp() {
        return doorExtOp;
    }

    public void setDoorExtOp(Integer doorExtOp) {
        this.doorExtOp = doorExtOp;
    }

    public String getDoorExtOpObs() {
        return doorExtOpObs;
    }

    public void setDoorExtOpObs(String doorExtOpObs) {
        this.doorExtOpObs = doorExtOpObs;
    }

    public Integer getDoorVertSign() {
        return doorVertSign;
    }

    public void setDoorVertSign(Integer doorVertSign) {
        this.doorVertSign = doorVertSign;
    }

    public String getDoorVertSignObs() {
        return doorVertSignObs;
    }

    public void setDoorVertSignObs(String doorVertSignObs) {
        this.doorVertSignObs = doorVertSignObs;
    }

    public Integer getDoorSillType() {
        return doorSillType;
    }

    public void setDoorSillType(Integer doorSillType) {
        this.doorSillType = doorSillType;
    }

    public Double getInclinationSillHeight() {
        return inclinationSillHeight;
    }

    public void setInclinationSillHeight(Double inclinationSillHeight) {
        this.inclinationSillHeight = inclinationSillHeight;
    }

    public Double getStepSillHeight() {
        return stepSillHeight;
    }

    public void setStepSillHeight(Double stepSillHeight) {
        this.stepSillHeight = stepSillHeight;
    }

    public Double getSlopeSillInclination() {
        return slopeSillInclination;
    }

    public void setSlopeSillInclination(Double slopeSillInclination) {
        this.slopeSillInclination = slopeSillInclination;
    }

    public Double getSlopeSillWidth() {
        return slopeSillWidth;
    }

    public void setSlopeSillWidth(Double slopeSillWidth) {
        this.slopeSillWidth = slopeSillWidth;
    }

    public String getDoorSillTypeObs() {
        return doorSillTypeObs;
    }

    public void setDoorSillTypeObs(String doorSillTypeObs) {
        this.doorSillTypeObs = doorSillTypeObs;
    }

    public Integer getDoorTactileSign() {
        return doorTactileSign;
    }

    public void setDoorTactileSign(Integer doorTactileSign) {
        this.doorTactileSign = doorTactileSign;
    }

    public String getDoorTactileSignObs() {
        return doorTactileSignObs;
    }

    public void setDoorTactileSignObs(String doorTactileSignObs) {
        this.doorTactileSignObs = doorTactileSignObs;
    }

    public Integer getDoorIntCoating() {
        return doorIntCoating;
    }

    public void setDoorIntCoating(Integer doorIntCoating) {
        this.doorIntCoating = doorIntCoating;
    }

    public String getDoorIntCoatingObs() {
        return doorIntCoatingObs;
    }

    public void setDoorIntCoatingObs(String doorIntCoatingObs) {
        this.doorIntCoatingObs = doorIntCoatingObs;
    }

    public Integer getDoorHorizontalHandle() {
        return doorHorizontalHandle;
    }

    public void setDoorHorizontalHandle(Integer doorHorizontalHandle) {
        this.doorHorizontalHandle = doorHorizontalHandle;
    }

    public Double getHandleHeight() {
        return handleHeight;
    }

    public void setHandleHeight(Double handleHeight) {
        this.handleHeight = handleHeight;
    }

    public Double getHandleLength() {
        return handleLength;
    }

    public void setHandleLength(Double handleLength) {
        this.handleLength = handleLength;
    }

    public String getHandleObs() {
        return handleObs;
    }

    public void setHandleObs(String handleObs) {
        this.handleObs = handleObs;
    }
}
