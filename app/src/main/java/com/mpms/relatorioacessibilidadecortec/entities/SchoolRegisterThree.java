package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SchoolRegisterThree {

    @PrimaryKey
    private Integer cadID;
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

    public SchoolRegisterThree(Integer cadID, Integer youngestStudentAge, Integer monthYearYoungest, Integer oldestStudentAge, Integer monthYearOldest,
                               Integer numberStudents, Integer numberStudentsPCD, String studentsPCDDescription, Integer numberWorkers,
                               Integer numberWorkersPCD, String workersPCDDescription, Integer hasWorkersLibras, Integer numberWorkersLibras,
                               String workersLibrasDescription, String initialDateInspection, String finalDateInspection) {
        this.cadID = cadID;
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
        this.workersLibrasDescriptions = workersLibrasDescription;
        this.initialDateInspection = initialDateInspection;
        this.finalDateInspection = finalDateInspection;
    }

    public Integer getCadID() {
        return cadID;
    }

    public void setCadID(Integer cadID) {
        this.cadID = cadID;
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

    public void setWorkersLibrasDescriptions(String workersLibrasDescription) {
        this.workersLibrasDescriptions = workersLibrasDescription;
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
}
