package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SchoolRegisterThree {

    @PrimaryKey
    private Integer cadID;
    private Integer youngestStudent;
    private Integer oldestStudent;
    private Integer numberStudents;
    private Integer numberStudentsPcd;
    private String studentsPcdDescription;
    private Integer numberWorkers;
    private Integer numberWorkersPcd;
    private String workersPcdDescription;
    private Integer numberWorkersLibras;
    private String dateInspection;
    private String dateInspectionEnd;

    public SchoolRegisterThree(Integer cadID, Integer youngestStudent, Integer oldestStudent, Integer numberStudents, Integer numberStudentsPcd,
                               String studentsPcdDescription, Integer numberWorkers, Integer numberWorkersPcd, String workersPcdDescription,
                               Integer numberWorkersLibras, String startingDateInspection, String dateInspectionEnd) {
        this.cadID = cadID;
        this.youngestStudent = youngestStudent;
        this.oldestStudent = oldestStudent;
        this.numberStudents = numberStudents;
        this.numberStudentsPcd = numberStudentsPcd;
        this.studentsPcdDescription = studentsPcdDescription;
        this.numberWorkers = numberWorkers;
        this.numberWorkersPcd = numberWorkersPcd;
        this.workersPcdDescription = workersPcdDescription;
        this.numberWorkersLibras = numberWorkersLibras;
        this.dateInspection = startingDateInspection;
        this.dateInspectionEnd = dateInspectionEnd;
    }

    public Integer getCadID() {
        return cadID;
    }

    public void setCadID(Integer cadID) {
        this.cadID = cadID;
    }

    public Integer getYoungestStudent() {
        return youngestStudent;
    }

    public void setYoungestStudent(Integer youngestStudent) {
        this.youngestStudent = youngestStudent;
    }

    public Integer getOldestStudent() {
        return oldestStudent;
    }

    public void setOldestStudent(Integer oldestStudent) {
        this.oldestStudent = oldestStudent;
    }

    public Integer getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(Integer numberStudents) {
        this.numberStudents = numberStudents;
    }

    public Integer getNumberStudentsPcd() {
        return numberStudentsPcd;
    }

    public void setNumberStudentsPcd(Integer numberStudentsPcd) {
        this.numberStudentsPcd = numberStudentsPcd;
    }

    public String getStudentsPcdDescription() {
        return studentsPcdDescription;
    }

    public void setStudentsPcdDescription(String studentsPcdDescription) {
        this.studentsPcdDescription = studentsPcdDescription;
    }

    public Integer getNumberWorkers() {
        return numberWorkers;
    }

    public void setNumberWorkers(Integer numberWorkers) {
        this.numberWorkers = numberWorkers;
    }

    public Integer getNumberWorkersPcd() {
        return numberWorkersPcd;
    }

    public void setNumberWorkersPcd(Integer numberWorkersPcd) {
        this.numberWorkersPcd = numberWorkersPcd;
    }

    public String getWorkersPcdDescription() {
        return workersPcdDescription;
    }

    public void setWorkersPcdDescription(String workersPcdDescription) {
        this.workersPcdDescription = workersPcdDescription;
    }

    public Integer getNumberWorkersLibras() {
        return numberWorkersLibras;
    }

    public void setNumberWorkersLibras(Integer numberWorkersLibras) {
        this.numberWorkersLibras = numberWorkersLibras;
    }

    public String getDateInspection() {
        return dateInspection;
    }

    public void setDateInspection(String dateInspection) {
        this.dateInspection = dateInspection;
    }

    public String getDateInspectionEnd() {
        return dateInspectionEnd;
    }

    public void setDateInspectionEnd(String dateInspectionEnd) {
        this.dateInspectionEnd = dateInspectionEnd;
    }
}
