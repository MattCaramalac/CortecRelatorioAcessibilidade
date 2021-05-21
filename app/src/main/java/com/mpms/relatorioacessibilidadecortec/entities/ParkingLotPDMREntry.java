package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {
        @ForeignKey(entity = SchoolEntry.class,
                parentColumns = "cadID",
                childColumns = "schoolEntryID",
                onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = ParkingLotEntry.class,
                parentColumns = "parkingLotID",
                childColumns = "parkingLotID",
                onDelete = CASCADE, onUpdate = CASCADE)})
public class ParkingLotPDMREntry {

    @PrimaryKey(autoGenerate = true)
    private Integer parkingPdmrID;
    @NonNull
    private Integer schoolEntryID;
    @NonNull
    private Integer parkingLotID;

    private Integer hasPdmrVacancy;
    private Integer totalPdmrVacancy;
    private Integer hasVisualPdmrVertSign;
    private String visualPdmrVertSignObs;
    private Integer hasVisualPdmrHorizSign;
    private Double visualPdmrHorizSignWidth;
    private Double visualPdmrHorizSignLength;
    private String visualPdmrHorizSignObs;
    private Integer hasPdmrSecurityZone;
    private Double securityZoneWidth;
    private Double securityZoneObs;
    private Integer hasPdmrSia;
    private Double pdmrSiaWidth;
    private Double pdmrSiaLength;
    private String pdmrSiaObs;

    public ParkingLotPDMREntry(@NonNull Integer schoolEntryID, @NonNull Integer parkingLotID, Integer hasPdmrVacancy,
                               Integer totalPdmrVacancy, Integer hasVisualPdmrVertSign, String visualPdmrVertSignObs,
                               Integer hasVisualPdmrHorizSign, Double visualPdmrHorizSignWidth, Double visualPdmrHorizSignLength,
                               String visualPdmrHorizSignObs, Integer hasPdmrSecurityZone, Double securityZoneWidth, Double securityZoneObs,
                               Integer hasPdmrSia, Double pdmrSiaWidth, Double pdmrSiaLength, String pdmrSiaObs) {
        this.schoolEntryID = schoolEntryID;
        this.parkingLotID = parkingLotID;
        this.hasPdmrVacancy = hasPdmrVacancy;
        this.totalPdmrVacancy = totalPdmrVacancy;
        this.hasVisualPdmrVertSign = hasVisualPdmrVertSign;
        this.visualPdmrVertSignObs = visualPdmrVertSignObs;
        this.hasVisualPdmrHorizSign = hasVisualPdmrHorizSign;
        this.visualPdmrHorizSignWidth = visualPdmrHorizSignWidth;
        this.visualPdmrHorizSignLength = visualPdmrHorizSignLength;
        this.visualPdmrHorizSignObs = visualPdmrHorizSignObs;
        this.hasPdmrSecurityZone = hasPdmrSecurityZone;
        this.securityZoneWidth = securityZoneWidth;
        this.securityZoneObs = securityZoneObs;
        this.hasPdmrSia = hasPdmrSia;
        this.pdmrSiaWidth = pdmrSiaWidth;
        this.pdmrSiaLength = pdmrSiaLength;
        this.pdmrSiaObs = pdmrSiaObs;
    }

    public Integer getParkingPdmrID() {
        return parkingPdmrID;
    }

    public void setParkingPdmrID(Integer parkingPdmrID) {
        this.parkingPdmrID = parkingPdmrID;
    }

    @NonNull
    public Integer getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(@NonNull Integer schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    @NonNull
    public Integer getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(@NonNull Integer parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public Integer getHasPdmrVacancy() {
        return hasPdmrVacancy;
    }

    public void setHasPdmrVacancy(Integer hasPdmrVacancy) {
        this.hasPdmrVacancy = hasPdmrVacancy;
    }

    public Integer getTotalPdmrVacancy() {
        return totalPdmrVacancy;
    }

    public void setTotalPdmrVacancy(Integer totalPdmrVacancy) {
        this.totalPdmrVacancy = totalPdmrVacancy;
    }

    public Integer getHasVisualPdmrVertSign() {
        return hasVisualPdmrVertSign;
    }

    public void setHasVisualPdmrVertSign(Integer hasVisualPdmrVertSign) {
        this.hasVisualPdmrVertSign = hasVisualPdmrVertSign;
    }

    public String getVisualPdmrVertSignObs() {
        return visualPdmrVertSignObs;
    }

    public void setVisualPdmrVertSignObs(String visualPdmrVertSignObs) {
        this.visualPdmrVertSignObs = visualPdmrVertSignObs;
    }

    public Integer getHasVisualPdmrHorizSign() {
        return hasVisualPdmrHorizSign;
    }

    public void setHasVisualPdmrHorizSign(Integer hasVisualPdmrHorizSign) {
        this.hasVisualPdmrHorizSign = hasVisualPdmrHorizSign;
    }

    public Double getVisualPdmrHorizSignWidth() {
        return visualPdmrHorizSignWidth;
    }

    public void setVisualPdmrHorizSignWidth(Double visualPdmrHorizSignWidth) {
        this.visualPdmrHorizSignWidth = visualPdmrHorizSignWidth;
    }

    public Double getVisualPdmrHorizSignLength() {
        return visualPdmrHorizSignLength;
    }

    public void setVisualPdmrHorizSignLength(Double visualPdmrHorizSignLength) {
        this.visualPdmrHorizSignLength = visualPdmrHorizSignLength;
    }

    public String getVisualPdmrHorizSignObs() {
        return visualPdmrHorizSignObs;
    }

    public void setVisualPdmrHorizSignObs(String visualPdmrHorizSignObs) {
        this.visualPdmrHorizSignObs = visualPdmrHorizSignObs;
    }

    public Integer getHasPdmrSecurityZone() {
        return hasPdmrSecurityZone;
    }

    public void setHasPdmrSecurityZone(Integer hasPdmrSecurityZone) {
        this.hasPdmrSecurityZone = hasPdmrSecurityZone;
    }

    public Double getSecurityZoneWidth() {
        return securityZoneWidth;
    }

    public void setSecurityZoneWidth(Double securityZoneWidth) {
        this.securityZoneWidth = securityZoneWidth;
    }

    public Double getSecurityZoneObs() {
        return securityZoneObs;
    }

    public void setSecurityZoneObs(Double securityZoneObs) {
        this.securityZoneObs = securityZoneObs;
    }

    public Integer getHasPdmrSia() {
        return hasPdmrSia;
    }

    public void setHasPdmrSia(Integer hasPdmrSia) {
        this.hasPdmrSia = hasPdmrSia;
    }

    public Double getPdmrSiaWidth() {
        return pdmrSiaWidth;
    }

    public void setPdmrSiaWidth(Double pdmrSiaWidth) {
        this.pdmrSiaWidth = pdmrSiaWidth;
    }

    public Double getPdmrSiaLength() {
        return pdmrSiaLength;
    }

    public void setPdmrSiaLength(Double pdmrSiaLength) {
        this.pdmrSiaLength = pdmrSiaLength;
    }

    public String getPdmrSiaObs() {
        return pdmrSiaObs;
    }

    public void setPdmrSiaObs(String pdmrSiaObs) {
        this.pdmrSiaObs = pdmrSiaObs;
    }
}
