package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SchoolRegisterOne {

    @PrimaryKey
    private Integer cadID;
    private String schoolName;
    private String schoolAddress;
    private String addressComplement;
    private String addressNumber;
    private String addressNeighborhood;
    private String nameCity;
    private String nameDistrict;
    private String contactPhone1;
    private String contactPhone2;
    private String nameResponsibleVisit;
    private String nameInspectionTeam;
    private String emailAddress;
    private String contactName1;
    private String contactName2;

    public SchoolRegisterOne(Integer schoolID, String schoolName, String schoolAddress, String addressComplement, String addressNumber,
                             String addressNeighborhood, String nameCity, String nameDistrict, String contactPhone1, String contactPhone2,
                             String nameResponsibleVisit, String nameInspectionTeam, String emailAddress, String contactName1, String contactName2) {
        this.cadID = schoolID;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
        this.addressComplement = addressComplement;
        this.addressNumber = addressNumber;
        this.addressNeighborhood = addressNeighborhood;
        this.nameCity = nameCity;
        this.nameDistrict = nameDistrict;
        this.contactPhone1 = contactPhone1;
        this.contactPhone2 = contactPhone2;
        this.nameResponsibleVisit = nameResponsibleVisit;
        this.nameInspectionTeam = nameInspectionTeam;
        this.emailAddress = emailAddress;
        this.contactName1 = contactName1;
        this.contactName2 = contactName2;
    }

    public Integer getCadID() {
        return cadID;
    }

    public void setCadID(Integer cadID) {
        this.cadID = cadID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressNeighborhood() {
        return addressNeighborhood;
    }

    public void setAddressNeighborhood(String addressNeighborhood) {
        this.addressNeighborhood = addressNeighborhood;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict) {
        this.nameDistrict = nameDistrict;
    }

    public String getContactPhone1() {
        return contactPhone1;
    }

    public void setContactPhone1(String contactPhone1) {
        this.contactPhone1 = contactPhone1;
    }

    public String getContactPhone2() {
        return contactPhone2;
    }

    public void setContactPhone2(String contactPhone2) {
        this.contactPhone2 = contactPhone2;
    }

    public String getNameResponsibleVisit() {
        return nameResponsibleVisit;
    }

    public void setNameResponsibleVisit(String nameResponsibleVisit) {
        this.nameResponsibleVisit = nameResponsibleVisit;
    }

    public String getNameInspectionTeam() {
        return nameInspectionTeam;
    }

    public void setNameInspectionTeam(String nameInspectionTeam) {
        this.nameInspectionTeam = nameInspectionTeam;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactName1() {
        return contactName1;
    }

    public void setContactName1(String contactName1) {
        this.contactName1 = contactName1;
    }

    public String getContactName2() {
        return contactName2;
    }

    public void setContactName2(String contactName2) {
        this.contactName2 = contactName2;
    }
}
