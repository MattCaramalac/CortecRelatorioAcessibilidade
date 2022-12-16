package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = ParkingLotEntry.class, parentColumns = "parkingID",
                childColumns = "parkID", onDelete = CASCADE, onUpdate = CASCADE))
public class ParkingLotPCDEntry {

    @PrimaryKey(autoGenerate = true)
    private int parkPcdID;
    private int parkID;

    private String pcdVacancyLocal;
    private int vacancyPosition;
    private int hasVisualPcdVertSign;
    private Double vertPcdSignLength;
    private Double vertPcdSignWidth;
    private String vertPcdSignObs;
    private double pcdVacancyLength;
    private double pcdVacancyWidth;
    private double pcdVacancyLimitWidth;
    private int hasSecurityZone;
    private Double securityZoneWidth;
    private String securityZoneObs;
    private int hasPcdSia;
    private Double pcdSiaWidth;
    private Double pcdSiaLength;
    private String pcdSiaObs;
    private String pcdVacancyObs;

    public String getPcdVacancyLocal() {
        return pcdVacancyLocal;
    }

    public void setPcdVacancyLocal(String pcdVacancyLocal) {
        this.pcdVacancyLocal = pcdVacancyLocal;
    }

    public int getVacancyPosition() {
        return vacancyPosition;
    }

    public void setVacancyPosition(int vacancyPosition) {
        this.vacancyPosition = vacancyPosition;
    }

    public int getParkPcdID() {
        return parkPcdID;
    }

    public void setParkPcdID(int parkPcdID) {
        this.parkPcdID = parkPcdID;
    }

    public int getParkID() {
        return parkID;
    }

    public void setParkID(int parkID) {
        this.parkID = parkID;
    }

    public int getHasVisualPcdVertSign() {
        return hasVisualPcdVertSign;
    }

    public void setHasVisualPcdVertSign(int hasVisualPcdVertSign) {
        this.hasVisualPcdVertSign = hasVisualPcdVertSign;
    }

    public Double getVertPcdSignLength() {
        return vertPcdSignLength;
    }

    public void setVertPcdSignLength(Double vertPcdSignLength) {
        this.vertPcdSignLength = vertPcdSignLength;
    }

    public Double getVertPcdSignWidth() {
        return vertPcdSignWidth;
    }

    public void setVertPcdSignWidth(Double vertPcdSignWidth) {
        this.vertPcdSignWidth = vertPcdSignWidth;
    }

    public String getVertPcdSignObs() {
        return vertPcdSignObs;
    }

    public void setVertPcdSignObs(String vertPcdSignObs) {
        this.vertPcdSignObs = vertPcdSignObs;
    }

    public double getPcdVacancyLength() {
        return pcdVacancyLength;
    }

    public void setPcdVacancyLength(double pcdVacancyLength) {
        this.pcdVacancyLength = pcdVacancyLength;
    }

    public double getPcdVacancyWidth() {
        return pcdVacancyWidth;
    }

    public void setPcdVacancyWidth(double pcdVacancyWidth) {
        this.pcdVacancyWidth = pcdVacancyWidth;
    }

    public double getPcdVacancyLimitWidth() {
        return pcdVacancyLimitWidth;
    }

    public void setPcdVacancyLimitWidth(double pcdVacancyLimitWidth) {
        this.pcdVacancyLimitWidth = pcdVacancyLimitWidth;
    }

    public int getHasSecurityZone() {
        return hasSecurityZone;
    }

    public void setHasSecurityZone(int hasSecurityZone) {
        this.hasSecurityZone = hasSecurityZone;
    }

    public Double getSecurityZoneWidth() {
        return securityZoneWidth;
    }

    public void setSecurityZoneWidth(Double securityZoneWidth) {
        this.securityZoneWidth = securityZoneWidth;
    }

    public String getSecurityZoneObs() {
        return securityZoneObs;
    }

    public void setSecurityZoneObs(String securityZoneObs) {
        this.securityZoneObs = securityZoneObs;
    }

    public int getHasPcdSia() {
        return hasPcdSia;
    }

    public void setHasPcdSia(int hasPcdSia) {
        this.hasPcdSia = hasPcdSia;
    }

    public Double getPcdSiaWidth() {
        return pcdSiaWidth;
    }

    public void setPcdSiaWidth(Double pcdSiaWidth) {
        this.pcdSiaWidth = pcdSiaWidth;
    }

    public Double getPcdSiaLength() {
        return pcdSiaLength;
    }

    public void setPcdSiaLength(Double pcdSiaLength) {
        this.pcdSiaLength = pcdSiaLength;
    }

    public String getPcdSiaObs() {
        return pcdSiaObs;
    }

    public void setPcdSiaObs(String pcdSiaObs) {
        this.pcdSiaObs = pcdSiaObs;
    }

    public String getPcdVacancyObs() {
        return pcdVacancyObs;
    }

    public void setPcdVacancyObs(String pcdVacancyObs) {
        this.pcdVacancyObs = pcdVacancyObs;
    }

    public ParkingLotPCDEntry(int parkID, String pcdVacancyLocal, int vacancyPosition, int hasVisualPcdVertSign, Double vertPcdSignLength, Double vertPcdSignWidth,
                              String vertPcdSignObs, double pcdVacancyLength, double pcdVacancyWidth, double pcdVacancyLimitWidth,int hasSecurityZone, Double securityZoneWidth,
                              String securityZoneObs, int hasPcdSia, Double pcdSiaWidth, Double pcdSiaLength, String pcdSiaObs, String pcdVacancyObs) {
        this.parkID = parkID;
        this.pcdVacancyLocal = pcdVacancyLocal;
        this.vacancyPosition = vacancyPosition;
        this.hasVisualPcdVertSign = hasVisualPcdVertSign;
        this.vertPcdSignLength = vertPcdSignLength;
        this.vertPcdSignWidth = vertPcdSignWidth;
        this.vertPcdSignObs = vertPcdSignObs;
        this.pcdVacancyLength = pcdVacancyLength;
        this.pcdVacancyWidth = pcdVacancyWidth;
        this.pcdVacancyLimitWidth = pcdVacancyLimitWidth;
        this.hasSecurityZone = hasSecurityZone;
        this.securityZoneWidth = securityZoneWidth;
        this.securityZoneObs = securityZoneObs;
        this.hasPcdSia = hasPcdSia;
        this.pcdSiaWidth = pcdSiaWidth;
        this.pcdSiaLength = pcdSiaLength;
        this.pcdSiaObs = pcdSiaObs;
        this.pcdVacancyObs = pcdVacancyObs;
    }
}
