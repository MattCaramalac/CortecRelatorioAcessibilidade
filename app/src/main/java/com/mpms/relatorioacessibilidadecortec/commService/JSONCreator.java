package com.mpms.relatorioacessibilidadecortec.commService;

import androidx.lifecycle.LifecycleOwner;

import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONCreator {

    public int error = 0;

    ViewModelEntry modelEntry;
    int schoolID;
    LifecycleOwner owner;


    public void createJson(JSONObject jObject, SchoolEntry entry) {
        try {
            jObject.put("schoolNameCaps", entry.getSchoolName().toUpperCase());
            jObject.put("cityNameCaps", entry.getNameCity().toUpperCase());
            jObject.put("schoolName", entry.getSchoolName());
            jObject.put("schoolAddress", entry.getSchoolAddress());
            jObject.put("schoolNumber", entry.getAddressNumber());
            jObject.put("schoolNeighbour", entry.getAddressNeighborhood());
            jObject.put("schoolDistrict", entry.getNameDistrict());
            jObject.put("cityName", entry.getNameCity());
            jObject.put("visitDate", entry.getInitialDateInspection());
            jObject.put("responsibleVisit", entry.getNameResponsibleVisit());
            jObject.put("youngAge", String.valueOf(entry.getYoungestStudentAge()));
            jObject.put("ageClassYoung", "(teste) anos");
            jObject.put("oldestAge", String.valueOf(entry.getOldestStudentAge()));
            jObject.put("ageClassOldest", "(teste) anos");
            jObject.put("schoolServices", "(teste) EJA");
            jObject.put("workingHours", "(teste) matutino");
            jObject.put("numberStudents", String.valueOf(entry.getNumberStudents()));
            jObject.put("numberDisabled", String.valueOf(entry.getNumberStudentsPCD()));
            jObject.put("necessityDesc", entry.getWorkersPCDDescription());
            jObject.put("numberWorkers", String.valueOf(entry.getNumberWorkers()));
            jObject.put("librasWorkers", String.valueOf(entry.getNumberWorkersLibras()));
        } catch (JSONException e) {
            e.printStackTrace();
            error = 1;
        }
        error = 0;
    }
}
