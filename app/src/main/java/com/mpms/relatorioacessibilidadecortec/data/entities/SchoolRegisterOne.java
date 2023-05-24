package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SchoolRegisterOne {

    @PrimaryKey
    private int cadID;
    private String schoolName;
    private String schoolAddress;
    private String addressComplement;
    private String addressNumber;
    private String addressNeighborhood;
    private String nameCity;
    private String nameDistrict;
    private String contactPhone1;
    private String contactName1;
    private String contactPhone2;
    private String contactName2;
    private String respName1;
    private String respJob1;
    private String respName2;
    private String respJob2;
    private String nameInspectionTeam;
    private String emailAddress;

    public SchoolRegisterOne(int cadID, String schoolName, String schoolAddress, String addressComplement, String addressNumber, String addressNeighborhood,
                             String nameCity, String nameDistrict, String contactPhone1, String contactName1, String contactPhone2, String contactName2,
                             String respName1, String respJob1, String respName2, String respJob2, String nameInspectionTeam, String emailAddress) {
        this.cadID = cadID;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
        this.addressComplement = addressComplement;
        this.addressNumber = addressNumber;
        this.addressNeighborhood = addressNeighborhood;
        this.nameCity = nameCity;
        this.nameDistrict = nameDistrict;
        this.contactPhone1 = contactPhone1;
        this.contactName1 = contactName1;
        this.contactPhone2 = contactPhone2;
        this.contactName2 = contactName2;
        this.respName1 = respName1;
        this.respJob1 = respJob1;
        this.respName2 = respName2;
        this.respJob2 = respJob2;
        this.nameInspectionTeam = nameInspectionTeam;
        this.emailAddress = emailAddress;
    }

    public int getCadID() {
        return cadID;
    }

    public void setCadID(int cadID) {
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

    public String getContactName1() {
        return contactName1;
    }

    public void setContactName1(String contactName1) {
        this.contactName1 = contactName1;
    }

    public String getContactPhone2() {
        return contactPhone2;
    }

    public void setContactPhone2(String contactPhone2) {
        this.contactPhone2 = contactPhone2;
    }

    public String getContactName2() {
        return contactName2;
    }

    public void setContactName2(String contactName2) {
        this.contactName2 = contactName2;
    }

    public String getRespName1() {
        return respName1;
    }

    public void setRespName1(String respName1) {
        this.respName1 = respName1;
    }

    public String getRespJob1() {
        return respJob1;
    }

    public void setRespJob1(String respJob1) {
        this.respJob1 = respJob1;
    }

    public String getRespName2() {
        return respName2;
    }

    public void setRespName2(String respName2) {
        this.respName2 = respName2;
    }

    public String getRespJob2() {
        return respJob2;
    }

    public void setRespJob2(String respJob2) {
        this.respJob2 = respJob2;
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
}
