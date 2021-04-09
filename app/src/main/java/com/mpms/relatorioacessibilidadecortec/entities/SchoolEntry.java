package com.mpms.relatorioacessibilidadecortec.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SchoolEntry {

    @PrimaryKey(autoGenerate = true)
    private Integer cadID;

    @NonNull
    private String schoolName;

    @NonNull
    private String nameDirector;

    @NonNull
    private String nameCity;

    @NonNull
    private String dateInspection;

    @NonNull
    private Integer numberStudents;

    @NonNull
    @ColumnInfo(defaultValue = "0")
    private Integer numberStudentsPcd;

    @NonNull
    private Integer numberWorkers;

    @NonNull
    @ColumnInfo(defaultValue = "0")
    private Integer numberWorkersLibras;

    @NonNull
    @ColumnInfo(defaultValue = "0")
    private Integer numberWorkersPcd;

    @NonNull
    public Integer getCadID() {
        return cadID;
    }

    public void setCadID(@NonNull Integer cadID) {
        this.cadID = cadID;
    }

    @NonNull
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(@NonNull String schoolName) {
        this.schoolName = schoolName;
    }

    @NonNull
    public String getNameDirector() {
        return nameDirector;
    }

    public void setNameDirector(@NonNull String nameDirector) {
        this.nameDirector = nameDirector;
    }

    @NonNull
    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(@NonNull String nameCity) {
        this.nameCity = nameCity;
    }

    @NonNull
    public String getDateInspection() {
        return dateInspection;
    }

    public void setDateInspection(@NonNull String dateInspection) {
        this.dateInspection = dateInspection;
    }

    @NonNull
    public Integer getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(@NonNull Integer numberStudents) {
        this.numberStudents = numberStudents;
    }

    @NonNull
    public Integer getNumberStudentsPcd() {
        return numberStudentsPcd;
    }

    public void setNumberStudentsPcd(@NonNull Integer numberStudentsPcd) {
        this.numberStudentsPcd = numberStudentsPcd;
    }

    @NonNull
    public Integer getNumberWorkers() {
        return numberWorkers;
    }

    public void setNumberWorkers(@NonNull Integer numberWorkers) {
        this.numberWorkers = numberWorkers;
    }

    @NonNull
    public Integer getNumberWorkersLibras() {
        return numberWorkersLibras;
    }

    public void setNumberWorkersLibras(@NonNull Integer numberWorkersLibras) {
        this.numberWorkersLibras = numberWorkersLibras;
    }

    @NonNull
    public Integer getNumberWorkersPcd() {
        return numberWorkersPcd;
    }

    public void setNumberWorkersPcd(@NonNull Integer numberWorkersPcd) {
        this.numberWorkersPcd = numberWorkersPcd;
    }

    public SchoolEntry(@NonNull String schoolName, @NonNull String nameDirector, @NonNull String nameCity,
                       @NonNull String dateInspection, @NonNull Integer numberStudents, @NonNull Integer numberStudentsPcd,
                       @NonNull Integer numberWorkers, @NonNull Integer numberWorkersLibras, @NonNull Integer numberWorkersPcd) {
        this.schoolName = schoolName;
        this.nameDirector = nameDirector;
        this.nameCity = nameCity;
        this.dateInspection = dateInspection;
        this.numberStudents = numberStudents;
        this.numberStudentsPcd = numberStudentsPcd;
        this.numberWorkers = numberWorkers;
        this.numberWorkersLibras = numberWorkersLibras;
        this.numberWorkersPcd = numberWorkersPcd;
    }
}
