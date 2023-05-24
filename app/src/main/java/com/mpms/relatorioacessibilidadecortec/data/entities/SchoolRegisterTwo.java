package com.mpms.relatorioacessibilidadecortec.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SchoolRegisterTwo {

    @PrimaryKey
    private Integer cadID;
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
    private Integer hasPreschool;
    private String preschoolFirstGrade;
    private String preschoolLastGrade;
    private Integer hasElementary;
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
    private String servicesObs;

    public SchoolRegisterTwo(Integer cadID, Integer hasMorningClasses, String morningStart, String morningEnd, Integer hasAfternoonClasses, String afternoonStart,
                             String afternoonEnd, Integer hasEveningClasses, String eveningStart, String eveningEnd, String workingHoursObs, Integer hasPreschool,
                             String preschoolFirstGrade, String preschoolLastGrade, Integer hasElementary, String elementaryFirstGrade, String elementaryLastGrade,
                             Integer hasMiddleSchool, String middleFirstGrade, String middleLastGrade, Integer hasHighSchool, String highFirstGrade, String highLastGrade,
                             Integer hasEja, String ejaFirstGrade, String ejaLastGrade, String servicesObs) {
        this.cadID = cadID;
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
        this.hasPreschool = hasPreschool;
        this.preschoolFirstGrade = preschoolFirstGrade;
        this.preschoolLastGrade = preschoolLastGrade;
        this.hasElementary = hasElementary;
        this.elementaryFirstGrade = elementaryFirstGrade;
        this.elementaryLastGrade = elementaryLastGrade;
        this.hasMiddleSchool = hasMiddleSchool;
        this.middleFirstGrade = middleFirstGrade;
        this.middleLastGrade = middleLastGrade;
        this.hasHighSchool = hasHighSchool;
        this.highFirstGrade = highFirstGrade;
        this.highLastGrade = highLastGrade;
        this.hasEja = hasEja;
        this.ejaFirstGrade = ejaFirstGrade;
        this.ejaLastGrade = ejaLastGrade;
        this.servicesObs = servicesObs;
    }

    public Integer getCadID() {
        return cadID;
    }

    public void setCadID(Integer cadID) {
        this.cadID = cadID;
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

    public Integer getHasElementary() {
        return hasElementary;
    }

    public void setHasElementary(Integer hasElementary) {
        this.hasElementary = hasElementary;
    }

    public String getElementaryFirstGrade() {
        return elementaryFirstGrade;
    }

    public void setElementaryFirstGrade(String elementaryFirstGrade) {
        this.elementaryFirstGrade = elementaryFirstGrade;
    }

    public String getElementaryLastGrade() {
        return elementaryLastGrade;
    }

    public void setElementaryLastGrade(String elementaryLastGrade) {
        this.elementaryLastGrade = elementaryLastGrade;
    }

    public Integer getHasMiddleSchool() {
        return hasMiddleSchool;
    }

    public void setHasMiddleSchool(Integer hasMiddleSchool) {
        this.hasMiddleSchool = hasMiddleSchool;
    }

    public String getMiddleFirstGrade() {
        return middleFirstGrade;
    }

    public void setMiddleFirstGrade(String middleFirstGrade) {
        this.middleFirstGrade = middleFirstGrade;
    }

    public String getMiddleLastGrade() {
        return middleLastGrade;
    }

    public void setMiddleLastGrade(String middleLastGrade) {
        this.middleLastGrade = middleLastGrade;
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
}
