package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = SchoolEntry.class, parentColumns = "cadID", childColumns = "schoolEntryID",
        onDelete = CASCADE, onUpdate = CASCADE))
public class ParkingLotEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer parkingLotID;
    private Integer schoolEntryID;
    private Integer typeParkingLot;

    private Integer totalParkingVacancy;
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

    private Integer hasElderlyVacancy;
    private Integer totalElderlyVacancy;
    private Integer hasVisualElderlyVertSign;
    private String visualElderlyVertSignObs;
    private Integer hasVisualElderlyHorizSign;
    private Double visualElderlyHorizSignWidth;
    private Double visualElderlyHorizSignLength;
    private String visualElderlyHorizSignObs;
    private Integer hasPictogramElderly;
    private Double pictogramElderlyWidth;
    private Double pictogramElderlyLength;
    private String pictogramElderlyObs;

    public Integer getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(Integer parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public Integer getSchoolEntryID() {
        return schoolEntryID;
    }

    public void setSchoolEntryID(Integer schoolEntryID) {
        this.schoolEntryID = schoolEntryID;
    }

    public Integer getTypeParkingLot() {
        return typeParkingLot;
    }

    public void setTypeParkingLot(Integer typeParkingLot) {
        this.typeParkingLot = typeParkingLot;
    }

    public Integer getTotalParkingVacancy() {
        return totalParkingVacancy;
    }

    public void setTotalParkingVacancy(Integer totalParkingVacancy) {
        this.totalParkingVacancy = totalParkingVacancy;
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

    public Integer getHasElderlyVacancy() {
        return hasElderlyVacancy;
    }

    public void setHasElderlyVacancy(Integer hasElderlyVacancy) {
        this.hasElderlyVacancy = hasElderlyVacancy;
    }

    public Integer getTotalElderlyVacancy() {
        return totalElderlyVacancy;
    }

    public void setTotalElderlyVacancy(Integer totalElderlyVacancy) {
        this.totalElderlyVacancy = totalElderlyVacancy;
    }

    public Integer getHasVisualElderlyVertSign() {
        return hasVisualElderlyVertSign;
    }

    public void setHasVisualElderlyVertSign(Integer hasVisualElderlyVertSign) {
        this.hasVisualElderlyVertSign = hasVisualElderlyVertSign;
    }

    public String getVisualElderlyVertSignObs() {
        return visualElderlyVertSignObs;
    }

    public void setVisualElderlyVertSignObs(String visualElderlyVertSignObs) {
        this.visualElderlyVertSignObs = visualElderlyVertSignObs;
    }

    public Integer getHasVisualElderlyHorizSign() {
        return hasVisualElderlyHorizSign;
    }

    public void setHasVisualElderlyHorizSign(Integer hasVisualElderlyHorizSign) {
        this.hasVisualElderlyHorizSign = hasVisualElderlyHorizSign;
    }

    public Double getVisualElderlyHorizSignWidth() {
        return visualElderlyHorizSignWidth;
    }

    public void setVisualElderlyHorizSignWidth(Double visualElderlyHorizSignWidth) {
        this.visualElderlyHorizSignWidth = visualElderlyHorizSignWidth;
    }

    public Double getVisualElderlyHorizSignLength() {
        return visualElderlyHorizSignLength;
    }

    public void setVisualElderlyHorizSignLength(Double visualElderlyHorizSignLength) {
        this.visualElderlyHorizSignLength = visualElderlyHorizSignLength;
    }

    public String getVisualElderlyHorizSignObs() {
        return visualElderlyHorizSignObs;
    }

    public void setVisualElderlyHorizSignObs(String visualElderlyHorizSignObs) {
        this.visualElderlyHorizSignObs = visualElderlyHorizSignObs;
    }

    public Integer getHasPictogramElderly() {
        return hasPictogramElderly;
    }

    public void setHasPictogramElderly(Integer hasPictogramElderly) {
        this.hasPictogramElderly = hasPictogramElderly;
    }

    public Double getPictogramElderlyWidth() {
        return pictogramElderlyWidth;
    }

    public void setPictogramElderlyWidth(Double pictogramElderlyWidth) {
        this.pictogramElderlyWidth = pictogramElderlyWidth;
    }

    public Double getPictogramElderlyLength() {
        return pictogramElderlyLength;
    }

    public void setPictogramElderlyLength(Double pictogramElderlyLength) {
        this.pictogramElderlyLength = pictogramElderlyLength;
    }

    public String getPictogramElderlyObs() {
        return pictogramElderlyObs;
    }

    public void setPictogramElderlyObs(String pictogramElderlyObs) {
        this.pictogramElderlyObs = pictogramElderlyObs;
    }

    public ParkingLotEntity(Integer schoolEntryID, Integer typeParkingLot, Integer totalParkingVacancy, Integer hasPdmrVacancy,
                            Integer totalPdmrVacancy, Integer hasVisualPdmrVertSign, String visualPdmrVertSignObs,
                            Integer hasVisualPdmrHorizSign, Double visualPdmrHorizSignWidth, Double visualPdmrHorizSignLength,
                            String visualPdmrHorizSignObs, Integer hasPdmrSecurityZone, Double securityZoneWidth, Double securityZoneObs,
                            Integer hasPdmrSia, Double pdmrSiaWidth, Double pdmrSiaLength, String pdmrSiaObs, Integer hasElderlyVacancy,
                            Integer totalElderlyVacancy, Integer hasVisualElderlyVertSign, String visualElderlyVertSignObs,
                            Integer hasVisualElderlyHorizSign, Double visualElderlyHorizSignWidth, Double visualElderlyHorizSignLength,
                            String visualElderlyHorizSignObs, Integer hasPictogramElderly, Double pictogramElderlyWidth,
                            Double pictogramElderlyLength, String pictogramElderlyObs) {
        this.schoolEntryID = schoolEntryID;
        this.typeParkingLot = typeParkingLot;
        this.totalParkingVacancy = totalParkingVacancy;
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
        this.hasElderlyVacancy = hasElderlyVacancy;
        this.totalElderlyVacancy = totalElderlyVacancy;
        this.hasVisualElderlyVertSign = hasVisualElderlyVertSign;
        this.visualElderlyVertSignObs = visualElderlyVertSignObs;
        this.hasVisualElderlyHorizSign = hasVisualElderlyHorizSign;
        this.visualElderlyHorizSignWidth = visualElderlyHorizSignWidth;
        this.visualElderlyHorizSignLength = visualElderlyHorizSignLength;
        this.visualElderlyHorizSignObs = visualElderlyHorizSignObs;
        this.hasPictogramElderly = hasPictogramElderly;
        this.pictogramElderlyWidth = pictogramElderlyWidth;
        this.pictogramElderlyLength = pictogramElderlyLength;
        this.pictogramElderlyObs = pictogramElderlyObs;
    }
}