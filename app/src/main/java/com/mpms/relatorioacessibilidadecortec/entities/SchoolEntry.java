package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SchoolEntry {

    @PrimaryKey(autoGenerate = true)
    private Integer cadID;
    private String schoolName;
    private String schoolAddress;
    private String addressComplement;
    private String addressNumber;
    private String addressNeighborhood;
    private String nameCity;
    private String nameDirector;
    private String contactPhone1;
    private String contactPhone2;
    private String nameResponsibleVisit;
    private String nameInspectionTeam;
    private Integer hasMorningClasses;
    private String morningStart;
    private String morningEnd;
    private Integer hasAfternoonClasses;
    private String afternoonStart;
    private String afternoonEnd;
    private Integer hasEveningClasses;
    private String eveningStart;
    private String eveningEnd;
    private Integer hasMaternal;
    private String maternalFirstGrade;
    private String maternalLastGrade;
    private Integer hasPreschool;
    private String preschoolFirstGrade;
    private String preschoolLastGrade;
    private Integer hasElementarySchool;
    private String elementaryFirstGrade;
    private String elementaryLastGrade;
    private Integer hasMiddleSchool;
    private String middleFirstGrade;
    private String middleLastGrade;
    private Integer hasHighSchool;
    private String highFirstGrade;
    private String highLastGrade;
    private Integer hasEja;
    private String ejaFirstGrade;
    private String ejaLastGrade;
    private Integer youngestStudent;
    private Integer oldestStudent;
    private Integer numberStudents;
    private Integer numberStudentsPcd;
    private String studentsPcdDescription;
    private Integer numberWorkers;
    private Integer numberWorkersLibras;
    private Integer numberWorkersPcd;
    private String workersPcdDescription;
    private String dateInspection;

    public Integer getCadID() { return cadID; }

    public void setCadID(Integer cadID) {       this.cadID = cadID;    }

    public String getSchoolName() { return schoolName; }

    public void setSchoolName(String schoolName) {        this.schoolName = schoolName;    }

    public String getSchoolAddress() { return schoolAddress;    }

    public void setSchoolAddress(String schoolAddress) {    this.schoolAddress = schoolAddress;    }

    public String getAddressComplement() {  return addressComplement;   }

    public void setAddressComplement(String addressComplement) {    this.addressComplement = addressComplement;    }

    public String getAddressNumber() {   return addressNumber;    }

    public void setAddressNumber(String addressNumber) {   this.addressNumber = addressNumber;    }

    public String getAddressNeighborhood() {   return addressNeighborhood;    }

    public void setAddressNeighborhood(String addressNeighborhood) {    this.addressNeighborhood = addressNeighborhood;    }

    public String getNameCity() {    return nameCity;    }

    public void setNameCity(String nameCity) {   this.nameCity = nameCity;    }

    public String getNameDirector() {    return nameDirector;    }

    public void setNameDirector(String nameDirector) {   this.nameDirector = nameDirector;    }

    public String getContactPhone1() {    return contactPhone1;    }

    public void setContactPhone1(String contactPhone1) {   this.contactPhone1 = contactPhone1;    }

    public String getContactPhone2() {    return contactPhone2;    }

    public void setContactPhone2(String contactPhone2) {    this.contactPhone2 = contactPhone2;    }

    public String getNameResponsibleVisit() {    return nameResponsibleVisit;    }

    public void setNameResponsibleVisit(String nameResponsibleVisit) {   this.nameResponsibleVisit = nameResponsibleVisit;    }

    public String getNameInspectionTeam() {    return nameInspectionTeam;    }

    public void setNameInspectionTeam(String nameInspectionTeam) {   this.nameInspectionTeam = nameInspectionTeam;    }

    public Integer getHasMorningClasses() {    return hasMorningClasses;    }

    public void setHasMorningClasses(Integer hasMorningClasses) {   this.hasMorningClasses = hasMorningClasses;    }

    public String getMorningStart() {   return morningStart;    }

    public void setMorningStart(String morningStart) {    this.morningStart = morningStart;    }

    public String getMorningEnd() {   return morningEnd;    }

    public void setMorningEnd(String morningEnd) {    this.morningEnd = morningEnd;    }

    public Integer getHasAfternoonClasses() {   return hasAfternoonClasses;    }

    public void setHasAfternoonClasses(Integer hasAfternoonClasses) {    this.hasAfternoonClasses = hasAfternoonClasses;    }

    public String getAfternoonStart() {   return afternoonStart;    }

    public void setAfternoonStart(String afternoonStart) {   this.afternoonStart = afternoonStart;    }

    public String getAfternoonEnd() {    return afternoonEnd;    }

    public void setAfternoonEnd(String afternoonEnd) {   this.afternoonEnd = afternoonEnd;    }

    public Integer getHasEveningClasses() {    return hasEveningClasses;    }

    public void setHasEveningClasses(Integer hasEveningClasses) {    this.hasEveningClasses = hasEveningClasses;    }

    public String getEveningStart() {    return eveningStart;    }

    public void setEveningStart(String eveningStart) {   this.eveningStart = eveningStart;    }

    public String getEveningEnd() {   return eveningEnd;    }

    public void setEveningEnd(String eveningEnd) {    this.eveningEnd = eveningEnd;    }

    public Integer getHasMaternal() {   return hasMaternal;    }

    public void setHasMaternal(Integer hasMaternal) {    this.hasMaternal = hasMaternal;    }

    public String getMaternalFirstGrade() {   return maternalFirstGrade;    }

    public void setMaternalFirstGrade(String maternalFirstGrade) {   this.maternalFirstGrade = maternalFirstGrade;    }

    public String getMaternalLastGrade() {    return maternalLastGrade;    }

    public void setMaternalLastGrade(String maternalLastGrade) {    this.maternalLastGrade = maternalLastGrade;    }

    public Integer getHasPreschool() {    return hasPreschool;    }

    public void setHasPreschool(Integer hasPreschool) {    this.hasPreschool = hasPreschool;    }

    public String getPreschoolFirstGrade() {    return preschoolFirstGrade;    }

    public void setPreschoolFirstGrade(String preschoolFirstGrade) {   this.preschoolFirstGrade = preschoolFirstGrade;    }

    public String getPreschoolLastGrade() {   return preschoolLastGrade;    }

    public void setPreschoolLastGrade(String preschoolLastGrade) {    this.preschoolLastGrade = preschoolLastGrade;    }

    public Integer getHasElementarySchool() {    return hasElementarySchool;    }

    public void setHasElementarySchool(Integer hasElementarySchool) {   this.hasElementarySchool = hasElementarySchool;    }

    public String getElementaryFirstGrade() {   return elementaryFirstGrade;    }

    public void setElementaryFirstGrade(String elementaryFirstGrade) {    this.elementaryFirstGrade = elementaryFirstGrade;    }

    public String getElementaryLastGrade() {    return elementaryLastGrade;    }

    public void setElementaryLastGrade(String elementaryLastGrade) {   this.elementaryLastGrade = elementaryLastGrade;    }

    public Integer getHasMiddleSchool() {    return hasMiddleSchool;    }

    public void setHasMiddleSchool(Integer hasMiddleSchool) {    this.hasMiddleSchool = hasMiddleSchool;    }

    public String getMiddleFirstGrade() {  return middleFirstGrade;    }

    public void setMiddleFirstGrade(String middleFirstGrade) {    this.middleFirstGrade = middleFirstGrade;    }

    public String getMiddleLastGrade() {    return middleLastGrade;    }

    public void setMiddleLastGrade(String middleLastGrade) {   this.middleLastGrade = middleLastGrade;    }

    public Integer getHasHighSchool() {   return hasHighSchool;    }

    public void setHasHighSchool(Integer hasHighSchool) {    this.hasHighSchool = hasHighSchool;    }

    public String getHighFirstGrade() {   return highFirstGrade;    }

    public void setHighFirstGrade(String highFirstGrade) {    this.highFirstGrade = highFirstGrade;    }

    public String getHighLastGrade() {    return highLastGrade;    }

    public void setHighLastGrade(String highLastGrade) {    this.highLastGrade = highLastGrade;    }

    public Integer getHasEja() {    return hasEja;    }

    public void setHasEja(Integer hasEja) {   this.hasEja = hasEja;    }

    public String getEjaFirstGrade() {   return ejaFirstGrade;    }

    public void setEjaFirstGrade(String ejaFirstGrade) {    this.ejaFirstGrade = ejaFirstGrade;    }

    public String getEjaLastGrade() {    return ejaLastGrade;    }

    public void setEjaLastGrade(String ejaLastGrade) {   this.ejaLastGrade = ejaLastGrade;    }

    public Integer getNumberStudents() {   return numberStudents;    }

    public void setNumberStudents(Integer numberStudents) {   this.numberStudents = numberStudents;    }

    public Integer getNumberStudentsPcd() {    return numberStudentsPcd;    }

    public void setNumberStudentsPcd(Integer numberStudentsPcd) {   this.numberStudentsPcd = numberStudentsPcd;    }

    public String getStudentsPcdDescription() {    return studentsPcdDescription;    }

    public void setStudentsPcdDescription(String studentsPcdDescription) {  this.studentsPcdDescription = studentsPcdDescription;    }

    public Integer getNumberWorkers() {    return numberWorkers;    }

    public void setNumberWorkers(Integer numberWorkers) {   this.numberWorkers = numberWorkers;    }

    public Integer getNumberWorkersLibras() {   return numberWorkersLibras;    }

    public void setNumberWorkersLibras(Integer numberWorkersLibras) {   this.numberWorkersLibras = numberWorkersLibras;    }

    public Integer getNumberWorkersPcd() {    return numberWorkersPcd;    }

    public void setNumberWorkersPcd(Integer numberWorkersPcd) {   this.numberWorkersPcd = numberWorkersPcd;    }

    public String getWorkersPcdDescription() {   return workersPcdDescription;    }

    public void setWorkersPcdDescription(String workersPcdDescription) {   this.workersPcdDescription = workersPcdDescription;    }

    public String getDateInspection() {    return dateInspection;    }

    public void setDateInspection(String dateInspection) {    this.dateInspection = dateInspection;    }

    public Integer getYoungestStudent() {        return youngestStudent;    }

    public void setYoungestStudent(Integer youngestStudent) {        this.youngestStudent = youngestStudent;    }

    public Integer getOldestStudent() {        return oldestStudent;    }

    public void setOldestStudent(Integer oldestStudent) {        this.oldestStudent = oldestStudent;    }

    public SchoolEntry(String schoolName, String  schoolAddress, String addressComplement, String addressNumber,
                       String addressNeighborhood, String nameCity, String nameDirector, String contactPhone1, String contactPhone2,
                       String nameResponsibleVisit, String nameInspectionTeam, Integer hasMorningClasses, String morningStart,
                       String morningEnd, Integer hasAfternoonClasses, String afternoonStart, String afternoonEnd, Integer hasEveningClasses,
                       String eveningStart, String eveningEnd, Integer hasMaternal, String maternalFirstGrade, String maternalLastGrade,
                       Integer hasPreschool, String preschoolFirstGrade, String preschoolLastGrade, Integer hasElementarySchool,
                       String elementaryFirstGrade, String elementaryLastGrade, Integer hasMiddleSchool, String middleFirstGrade,
                       String middleLastGrade, Integer hasHighSchool, String highFirstGrade, String highLastGrade, Integer hasEja,
                       String ejaFirstGrade, String ejaLastGrade, Integer youngestStudent, Integer oldestStudent, Integer numberStudents,
                       Integer numberStudentsPcd, String studentsPcdDescription, Integer numberWorkers, Integer numberWorkersPcd,
                       String workersPcdDescription, Integer numberWorkersLibras, String dateInspection) {

//        this.cadID = cadID;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
        this.addressComplement = addressComplement;
        this.addressNumber = addressNumber;
        this.addressNeighborhood = addressNeighborhood;
        this.nameCity = nameCity;
        this.nameDirector = nameDirector;
        this.contactPhone1 = contactPhone1;
        this.contactPhone2 = contactPhone2;
        this.nameResponsibleVisit = nameResponsibleVisit;
        this.nameInspectionTeam = nameInspectionTeam;
        this.hasMorningClasses =  hasMorningClasses;
        this.morningStart = morningStart;
        this.morningEnd = morningEnd;
        this.hasAfternoonClasses =  hasAfternoonClasses;
        this.afternoonStart = afternoonStart;
        this.afternoonEnd = afternoonEnd;
        this.hasEveningClasses =  hasEveningClasses;
        this.eveningStart = eveningStart;
        this.eveningEnd = eveningEnd;
        this.hasMaternal =  hasMaternal;
        this.maternalFirstGrade = maternalFirstGrade;
        this.maternalLastGrade = maternalLastGrade;
        this.hasPreschool =  hasPreschool;
        this.preschoolFirstGrade = preschoolFirstGrade;
        this.preschoolLastGrade = preschoolLastGrade;
        this.hasElementarySchool =  hasElementarySchool;
        this.elementaryFirstGrade = elementaryFirstGrade;
        this.elementaryLastGrade = elementaryLastGrade;
        this.hasMiddleSchool =  hasMiddleSchool;
        this.middleFirstGrade = middleFirstGrade;
        this.middleLastGrade = middleLastGrade;
        this.hasHighSchool =  hasHighSchool;
        this.highFirstGrade = highFirstGrade;
        this.highLastGrade = highLastGrade;
        this.hasEja =  hasEja;
        this.ejaFirstGrade = ejaFirstGrade;
        this.ejaLastGrade = ejaLastGrade;
        this.youngestStudent = youngestStudent;
        this.oldestStudent = oldestStudent;
        this.numberStudents =  numberStudents;
        this.numberStudentsPcd =  numberStudentsPcd;
        this.studentsPcdDescription = studentsPcdDescription;
        this.numberWorkers =  numberWorkers;
        this.numberWorkersLibras =  numberWorkersLibras;
        this.numberWorkersPcd =  numberWorkersPcd;
        this.workersPcdDescription = workersPcdDescription;
        this.dateInspection = dateInspection;
    }
}
