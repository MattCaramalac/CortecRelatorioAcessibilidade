package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RestDoorUpdate {
    @PrimaryKey
    private int restroomID;
    private Double doorWidth;
    private Integer hasPict;
    private String pictObs;
    private Integer opDir;
    private String opDirObs;
    private Integer hasCoat;
    private Double coatHeight;
    private String coatObs;
    private Integer hasVertSign;
    private String vertSignObs;
    private Integer sillType;
    private Double sillIncHeight;
    private Double sillStepHeight;
    private Integer sillSlopeQnt;
    private Double sillSlopeAngle1;
    private Double sillSlopeAngle2;
    private Double sillSlopeAngle3;
    private Double sillSlopeAngle4;
    private Double sillSlopeWidth;
    private String sillTypeObs;
    private Integer hasTactSign;
    private String tactSignObs;
    private Integer hasHorHandle;
    private Double handleHeight;
    private Double handleLength;
    private Double handleDiam;
    private String handleObs;

    public RestDoorUpdate(int restroomID, double doorWidth, int hasPict, String pictObs, int opDir, String opDirObs, int hasCoat, Double coatHeight,
                          String coatObs, int hasVertSign, String vertSignObs, int sillType, Double sillIncHeight, Double sillStepHeight,
                          Integer sillSlopeQnt, Double sillSlopeAngle1, Double sillSlopeAngle2, Double sillSlopeAngle3, Double sillSlopeAngle4,
                          Double sillSlopeWidth, String sillTypeObs, int hasTactSign, String tactSignObs, int hasHorHandle, Double handleHeight,
                          Double handleLength, Double handleDiam, String handleObs) {
        this.restroomID = restroomID;
        this.doorWidth = doorWidth;
        this.hasPict = hasPict;
        this.pictObs = pictObs;
        this.opDir = opDir;
        this.opDirObs = opDirObs;
        this.hasCoat = hasCoat;
        this.coatHeight = coatHeight;
        this.coatObs = coatObs;
        this.hasVertSign = hasVertSign;
        this.vertSignObs = vertSignObs;
        this.sillType = sillType;
        this.sillIncHeight = sillIncHeight;
        this.sillStepHeight = sillStepHeight;
        this.sillSlopeQnt = sillSlopeQnt;
        this.sillSlopeAngle1 = sillSlopeAngle1;
        this.sillSlopeAngle2 = sillSlopeAngle2;
        this.sillSlopeAngle3 = sillSlopeAngle3;
        this.sillSlopeAngle4 = sillSlopeAngle4;
        this.sillSlopeWidth = sillSlopeWidth;
        this.sillTypeObs = sillTypeObs;
        this.hasTactSign = hasTactSign;
        this.tactSignObs = tactSignObs;
        this.hasHorHandle = hasHorHandle;
        this.handleHeight = handleHeight;
        this.handleLength = handleLength;
        this.handleDiam = handleDiam;
        this.handleObs = handleObs;
    }

    public int getRestroomID() {
        return restroomID;
    }

    public void setRestroomID(int restroomID) {
        this.restroomID = restroomID;
    }

    public double getDoorWidth() {
        return doorWidth;
    }

    public void setDoorWidth(double doorWidth) {
        this.doorWidth = doorWidth;
    }

    public int getHasPict() {
        return hasPict;
    }

    public void setHasPict(int hasPict) {
        this.hasPict = hasPict;
    }

    public String getPictObs() {
        return pictObs;
    }

    public void setPictObs(String SIAObs) {
        this.pictObs = SIAObs;
    }

    public int getOpDir() {
        return opDir;
    }

    public void setOpDir(int opDir) {
        this.opDir = opDir;
    }

    public String getOpDirObs() {
        return opDirObs;
    }

    public void setOpDirObs(String opDirObs) {
        this.opDirObs = opDirObs;
    }

    public int getHasCoat() {
        return hasCoat;
    }

    public void setHasCoat(int hasCoat) {
        this.hasCoat = hasCoat;
    }

    public Double getCoatHeight() {
        return coatHeight;
    }

    public void setCoatHeight(Double coatHeight) {
        this.coatHeight = coatHeight;
    }

    public String getCoatObs() {
        return coatObs;
    }

    public void setCoatObs(String coatObs) {
        this.coatObs = coatObs;
    }

    public int getHasVertSign() {
        return hasVertSign;
    }

    public void setHasVertSign(int hasVertSign) {
        this.hasVertSign = hasVertSign;
    }

    public String getVertSignObs() {
        return vertSignObs;
    }

    public void setVertSignObs(String vertSignObs) {
        this.vertSignObs = vertSignObs;
    }

    public int getSillType() {
        return sillType;
    }

    public void setSillType(int sillType) {
        this.sillType = sillType;
    }

    public Double getSillIncHeight() {
        return sillIncHeight;
    }

    public void setSillIncHeight(Double sillIncHeight) {
        this.sillIncHeight = sillIncHeight;
    }

    public Double getSillStepHeight() {
        return sillStepHeight;
    }

    public void setSillStepHeight(Double sillStepHeight) {
        this.sillStepHeight = sillStepHeight;
    }

    public Integer getSillSlopeQnt() {
        return sillSlopeQnt;
    }

    public void setSillSlopeQnt(Integer sillSlopeQnt) {
        this.sillSlopeQnt = sillSlopeQnt;
    }

    public Double getSillSlopeAngle1() {
        return sillSlopeAngle1;
    }

    public void setSillSlopeAngle1(Double sillSlopeAngle1) {
        this.sillSlopeAngle1 = sillSlopeAngle1;
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

    public String getSillTypeObs() {
        return sillTypeObs;
    }

    public void setSillTypeObs(String sillTypeObs) {
        this.sillTypeObs = sillTypeObs;
    }

    public int getHasTactSign() {
        return hasTactSign;
    }

    public void setHasTactSign(int hasTactSign) {
        this.hasTactSign = hasTactSign;
    }

    public String getTactSignObs() {
        return tactSignObs;
    }

    public void setTactSignObs(String tactSignObs) {
        this.tactSignObs = tactSignObs;
    }

    public int getHasHorHandle() {
        return hasHorHandle;
    }

    public void setHasHorHandle(int hasHorHandle) {
        this.hasHorHandle = hasHorHandle;
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

    public Double getHandleDiam() {
        return handleDiam;
    }

    public void setHandleDiam(Double handleDiam) {
        this.handleDiam = handleDiam;
    }

    public String getHandleObs() {
        return handleObs;
    }

    public void setHandleObs(String handleObs) {
        this.handleObs = handleObs;
    }
}
