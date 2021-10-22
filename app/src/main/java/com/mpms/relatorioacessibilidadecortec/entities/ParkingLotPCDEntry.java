package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = ParkingLotEntry.class, parentColumns = "parkingLotID",
                childColumns = "parkingLotID", onDelete = CASCADE, onUpdate = CASCADE))
public class ParkingLotPCDEntry {

    @PrimaryKey(autoGenerate = true)
    private int parkingPcdID;
    private int parkingLotID;

    private int hasVisualPcdVertSign;
    private Double vertPcdSignLength;
    private Double vertPcdSignWidth;
    private String vertPcdSignObs;
    private double pcdVacancyLength;
    private double pcdVacancyWidth;
    private double pcdVacancyLimitWidth;
    private String pcdVacancyObs;
    private int hasSecurityZone;
    private Double securityZoneWidth;
    private String securityZoneObs;
    private int hasPcdSia;
    private Double pcdSiaWidth;
    private Double pcdSiaLength;
    private String pcdSiaObs;

    public int getParkingPcdID() {
        return parkingPcdID;
    }

    public void setParkingPcdID(int parkingPcdID) {
        this.parkingPcdID = parkingPcdID;
    }

    public int getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(int parkingLotID) {
        this.parkingLotID = parkingLotID;
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

    public String getPcdVacancyObs() {
        return pcdVacancyObs;
    }

    public void setPcdVacancyObs(String pcdVacancyObs) {
        this.pcdVacancyObs = pcdVacancyObs;
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

    public ParkingLotPCDEntry(@NonNull Integer parkingLotID, int hasVisualPcdVertSign, Double vertPcdSignLength,
                              Double vertPcdSignWidth, String vertPcdSignObs, double pcdVacancyLength,
                              double pcdVacancyWidth, double pcdVacancyLimitWidth, String pcdVacancyObs,
                              int hasSecurityZone, Double securityZoneWidth, String securityZoneObs,
                              int hasPcdSia, Double pcdSiaWidth, Double pcdSiaLength, String pcdSiaObs) {
        this.parkingLotID = parkingLotID;
        this.hasVisualPcdVertSign = hasVisualPcdVertSign;
        this.vertPcdSignLength = vertPcdSignLength;
        this.vertPcdSignWidth = vertPcdSignWidth;
        this.vertPcdSignObs = vertPcdSignObs;
        this.pcdVacancyLength = pcdVacancyLength;
        this.pcdVacancyWidth = pcdVacancyWidth;
        this.pcdVacancyLimitWidth = pcdVacancyLimitWidth;
        this.pcdVacancyObs = pcdVacancyObs;
        this.hasSecurityZone = hasSecurityZone;
        this.securityZoneWidth = securityZoneWidth;
        this.securityZoneObs = securityZoneObs;
        this.hasPcdSia = hasPcdSia;
        this.pcdSiaWidth = pcdSiaWidth;
        this.pcdSiaLength = pcdSiaLength;
        this.pcdSiaObs = pcdSiaObs;
    }
}
