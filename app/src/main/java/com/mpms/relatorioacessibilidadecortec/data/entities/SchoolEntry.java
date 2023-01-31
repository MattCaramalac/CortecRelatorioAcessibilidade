package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class SchoolEntry implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int cadID;
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
    private Integer hasMorningClasses;
    private String morningStart;
    private String morningEnd;
    private Integer hasAfternoonClasses;
    private String afternoonStart;
    private String afternoonEnd;
    private Integer hasEveningClasses;
    private String eveningStart;
    private String eveningEnd;
    private String workingHoursObs;
    private Integer hasNursery;
    private Integer hasDayCare;
    private Integer hasMaternal;
    private String maternalFirstGrade;
    private String maternalLastGrade;
    private Integer hasPreschool;
    private String preschoolFirstGrade;
    private String preschoolLastGrade;
    private Integer hasElementaryMiddle;
    private String elementaryMiddleFirstGrade;
    private String elementaryMiddleLastGrade;
    private Integer hasHighSchool;
    private String highFirstGrade;
    private String highLastGrade;
    private Integer hasEja;
    private String ejaFirstGrade;
    private String ejaLastGrade;
    private String servicesObs;
    private Integer youngestStudentAge;
    private Integer monthYearYoungest;
    private Integer oldestStudentAge;
    private Integer monthYearOldest;
    private Integer numberStudents;
    private Integer numberStudentsPCD;
    private String studentsPCDDescription;
    private Integer numberWorkers;
    private Integer numberWorkersPCD;
    private String workersPCDDescription;
    private Integer hasWorkersLibras;
    private Integer numberWorkersLibras;
    private String workersLibrasDescriptions;
    private String initialDateInspection;
    private String finalDateInspection;
    private String emailAddress;
    private String contactName1;
    private String contactName2;
    private String registerStudentObs;

    public SchoolEntry(String schoolName, String schoolAddress, String addressComplement, String addressNumber, String addressNeighborhood,
                       String nameCity, String nameDistrict, String contactPhone1, String contactPhone2, String nameResponsibleVisit,
                       String nameInspectionTeam, Integer hasMorningClasses, String morningStart, String morningEnd, Integer hasAfternoonClasses,
                       String afternoonStart, String afternoonEnd, Integer hasEveningClasses, String eveningStart, String eveningEnd, String workingHoursObs,
                       Integer hasNursery, Integer hasDayCare, Integer hasMaternal, String maternalFirstGrade, String maternalLastGrade, Integer hasPreschool,
                       String preschoolFirstGrade, String preschoolLastGrade, Integer hasElementaryMiddle, String elementaryMiddleFirstGrade,
                       String elementaryMiddleLastGrade, Integer hasHighSchool, String highFirstGrade, String highLastGrade, Integer hasEja, String ejaFirstGrade,
                       String ejaLastGrade, String servicesObs, Integer youngestStudentAge, Integer monthYearYoungest, Integer oldestStudentAge, Integer monthYearOldest,
                       Integer numberStudents, Integer numberStudentsPCD, String studentsPCDDescription, Integer numberWorkers, Integer numberWorkersPCD,
                       String workersPCDDescription, Integer hasWorkersLibras, Integer numberWorkersLibras, String workersLibrasDescriptions,
                       String initialDateInspection, String finalDateInspection, String emailAddress, String contactName1, String contactName2, String registerStudentObs) {
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
        this.hasMorningClasses = hasMorningClasses;
        this.morningStart = morningStart;
        this.morningEnd = morningEnd;
        this.hasAfternoonClasses = hasAfternoonClasses;
        this.afternoonStart = afternoonStart;
        this.afternoonEnd = afternoonEnd;
        this.hasEveningClasses = hasEveningClasses;
        this.eveningStart = eveningStart;
        this.eveningEnd = eveningEnd;
        this.workingHoursObs = workingHoursObs;
        this.hasNursery = hasNursery;
        this.hasDayCare = hasDayCare;
        this.hasMaternal = hasMaternal;
        this.maternalFirstGrade = maternalFirstGrade;
        this.maternalLastGrade = maternalLastGrade;
        this.hasPreschool = hasPreschool;
        this.preschoolFirstGrade = preschoolFirstGrade;
        this.preschoolLastGrade = preschoolLastGrade;
        this.hasElementaryMiddle = hasElementaryMiddle;
        this.elementaryMiddleFirstGrade = elementaryMiddleFirstGrade;
        this.elementaryMiddleLastGrade = elementaryMiddleLastGrade;
        this.hasHighSchool = hasHighSchool;
        this.highFirstGrade = highFirstGrade;
        this.highLastGrade = highLastGrade;
        this.hasEja = hasEja;
        this.ejaFirstGrade = ejaFirstGrade;
        this.ejaLastGrade = ejaLastGrade;
        this.servicesObs = servicesObs;
        this.youngestStudentAge = youngestStudentAge;
        this.monthYearYoungest = monthYearYoungest;
        this.oldestStudentAge = oldestStudentAge;
        this.monthYearOldest = monthYearOldest;
        this.numberStudents = numberStudents;
        this.numberStudentsPCD = numberStudentsPCD;
        this.studentsPCDDescription = studentsPCDDescription;
        this.numberWorkers = numberWorkers;
        this.numberWorkersPCD = numberWorkersPCD;
        this.workersPCDDescription = workersPCDDescription;
        this.hasWorkersLibras = hasWorkersLibras;
        this.numberWorkersLibras = numberWorkersLibras;
        this.workersLibrasDescriptions = workersLibrasDescriptions;
        this.initialDateInspection = initialDateInspection;
        this.finalDateInspection = finalDateInspection;
        this.emailAddress = emailAddress;
        this.contactName1 = contactName1;
        this.contactName2 = contactName2;
        this.registerStudentObs = registerStudentObs;
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

    public Integer getHasMorningClasses() {
        return hasMorningClasses;
    }

    public void setHasMorningClasses(Integer hasMorningClasses) {
        this.hasMorningClasses = hasMorningClasses;
    }

    public String getMorningStart() {
        return morningStart;
    }

    public void setMorningStart(String morningStart) {
        this.morningStart = morningStart;
    }

    public String getMorningEnd() {
        return morningEnd;
    }

    public void setMorningEnd(String morningEnd) {
        this.morningEnd = morningEnd;
    }

    public Integer getHasAfternoonClasses() {
        return hasAfternoonClasses;
    }

    public void setHasAfternoonClasses(Integer hasAfternoonClasses) {
        this.hasAfternoonClasses = hasAfternoonClasses;
    }

    public String getAfternoonStart() {
        return afternoonStart;
    }

    public void setAfternoonStart(String afternoonStart) {
        this.afternoonStart = afternoonStart;
    }

    public String getAfternoonEnd() {
        return afternoonEnd;
    }

    public void setAfternoonEnd(String afternoonEnd) {
        this.afternoonEnd = afternoonEnd;
    }

    public Integer getHasEveningClasses() {
        return hasEveningClasses;
    }

    public void setHasEveningClasses(Integer hasEveningClasses) {
        this.hasEveningClasses = hasEveningClasses;
    }

    public String getEveningStart() {
        return eveningStart;
    }

    public void setEveningStart(String eveningStart) {
        this.eveningStart = eveningStart;
    }

    public String getEveningEnd() {
        return eveningEnd;
    }

    public void setEveningEnd(String eveningEnd) {
        this.eveningEnd = eveningEnd;
    }

    public String getWorkingHoursObs() {
        return workingHoursObs;
    }

    public void setWorkingHoursObs(String workingHoursObs) {
        this.workingHoursObs = workingHoursObs;
    }

    public Integer getHasNursery() {
        return hasNursery;
    }

    public void setHasNursery(Integer hasNursery) {
        this.hasNursery = hasNursery;
    }

    public Integer getHasDayCare() {
        return hasDayCare;
    }

    public void setHasDayCare(Integer hasDayCare) {
        this.hasDayCare = hasDayCare;
    }

    public Integer getHasMaternal() {
        return hasMaternal;
    }

    public void setHasMaternal(Integer hasMaternal) {
        this.hasMaternal = hasMaternal;
    }

    public String getMaternalFirstGrade() {
        return maternalFirstGrade;
    }

    public void setMaternalFirstGrade(String maternalFirstGrade) {
        this.maternalFirstGrade = maternalFirstGrade;
    }

    public String getMaternalLastGrade() {
        return maternalLastGrade;
    }

    public void setMaternalLastGrade(String maternalLastGrade) {
        this.maternalLastGrade = maternalLastGrade;
    }

    public Integer getHasPreschool() {
        return hasPreschool;
    }

    public void setHasPreschool(Integer hasPreschool) {
        this.hasPreschool = hasPreschool;
    }

    public String getPreschoolFirstGrade() {
        return preschoolFirstGrade;
    }

    public void setPreschoolFirstGrade(String preschoolFirstGrade) {
        this.preschoolFirstGrade = preschoolFirstGrade;
    }

    public String getPreschoolLastGrade() {
        return preschoolLastGrade;
    }

    public void setPreschoolLastGrade(String preschoolLastGrade) {
        this.preschoolLastGrade = preschoolLastGrade;
    }

    public Integer getHasElementaryMiddle() {
        return hasElementaryMiddle;
    }

    public void setHasElementaryMiddle(Integer hasElementaryMiddle) {
        this.hasElementaryMiddle = hasElementaryMiddle;
    }

    public String getElementaryMiddleFirstGrade() {
        return elementaryMiddleFirstGrade;
    }

    public void setElementaryMiddleFirstGrade(String elementaryMiddleFirstGrade) {
        this.elementaryMiddleFirstGrade = elementaryMiddleFirstGrade;
    }

    public String getElementaryMiddleLastGrade() {
        return elementaryMiddleLastGrade;
    }

    public void setElementaryMiddleLastGrade(String elementaryMiddleLastGrade) {
        this.elementaryMiddleLastGrade = elementaryMiddleLastGrade;
    }

    public Integer getHasHighSchool() {
        return hasHighSchool;
    }

    public void setHasHighSchool(Integer hasHighSchool) {
        this.hasHighSchool = hasHighSchool;
    }

    public String getHighFirstGrade() {
        return highFirstGrade;
    }

    public void setHighFirstGrade(String highFirstGrade) {
        this.highFirstGrade = highFirstGrade;
    }

    public String getHighLastGrade() {
        return highLastGrade;
    }

    public void setHighLastGrade(String highLastGrade) {
        this.highLastGrade = highLastGrade;
    }

    public Integer getHasEja() {
        return hasEja;
    }

    public void setHasEja(Integer hasEja) {
        this.hasEja = hasEja;
    }

    public String getEjaFirstGrade() {
        return ejaFirstGrade;
    }

    public void setEjaFirstGrade(String ejaFirstGrade) {
        this.ejaFirstGrade = ejaFirstGrade;
    }

    public String getEjaLastGrade() {
        return ejaLastGrade;
    }

    public void setEjaLastGrade(String ejaLastGrade) {
        this.ejaLastGrade = ejaLastGrade;
    }

    public String getServicesObs() {
        return servicesObs;
    }

    public void setServicesObs(String servicesObs) {
        this.servicesObs = servicesObs;
    }

    public Integer getYoungestStudentAge() {
        return youngestStudentAge;
    }

    public void setYoungestStudentAge(Integer youngestStudentAge) {
        this.youngestStudentAge = youngestStudentAge;
    }

    public Integer getMonthYearYoungest() {
        return monthYearYoungest;
    }

    public void setMonthYearYoungest(Integer monthYearYoungest) {
        this.monthYearYoungest = monthYearYoungest;
    }

    public Integer getOldestStudentAge() {
        return oldestStudentAge;
    }

    public void setOldestStudentAge(Integer oldestStudentAge) {
        this.oldestStudentAge = oldestStudentAge;
    }

    public Integer getMonthYearOldest() {
        return monthYearOldest;
    }

    public void setMonthYearOldest(Integer monthYearOldest) {
        this.monthYearOldest = monthYearOldest;
    }

    public Integer getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(Integer numberStudents) {
        this.numberStudents = numberStudents;
    }

    public Integer getNumberStudentsPCD() {
        return numberStudentsPCD;
    }

    public void setNumberStudentsPCD(Integer numberStudentsPCD) {
        this.numberStudentsPCD = numberStudentsPCD;
    }

    public String getStudentsPCDDescription() {
        return studentsPCDDescription;
    }

    public void setStudentsPCDDescription(String studentsPCDDescription) {
        this.studentsPCDDescription = studentsPCDDescription;
    }

    public Integer getNumberWorkers() {
        return numberWorkers;
    }

    public void setNumberWorkers(Integer numberWorkers) {
        this.numberWorkers = numberWorkers;
    }

    public Integer getNumberWorkersPCD() {
        return numberWorkersPCD;
    }

    public void setNumberWorkersPCD(Integer numberWorkersPCD) {
        this.numberWorkersPCD = numberWorkersPCD;
    }

    public String getWorkersPCDDescription() {
        return workersPCDDescription;
    }

    public void setWorkersPCDDescription(String workersPCDDescription) {
        this.workersPCDDescription = workersPCDDescription;
    }

    public Integer getHasWorkersLibras() {
        return hasWorkersLibras;
    }

    public void setHasWorkersLibras(Integer hasWorkersLibras) {
        this.hasWorkersLibras = hasWorkersLibras;
    }

    public Integer getNumberWorkersLibras() {
        return numberWorkersLibras;
    }

    public void setNumberWorkersLibras(Integer numberWorkersLibras) {
        this.numberWorkersLibras = numberWorkersLibras;
    }

    public String getWorkersLibrasDescriptions() {
        return workersLibrasDescriptions;
    }

    public void setWorkersLibrasDescriptions(String workersLibrasDescriptions) {
        this.workersLibrasDescriptions = workersLibrasDescriptions;
    }

    public String getInitialDateInspection() {
        return initialDateInspection;
    }

    public void setInitialDateInspection(String initialDateInspection) {
        this.initialDateInspection = initialDateInspection;
    }

    public String getFinalDateInspection() {
        return finalDateInspection;
    }

    public void setFinalDateInspection(String finalDateInspection) {
        this.finalDateInspection = finalDateInspection;
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

    public String getRegisterStudentObs() {
        return registerStudentObs;
    }

    public void setRegisterStudentObs(String registerStudentObs) {
        this.registerStudentObs = registerStudentObs;
    }
}
