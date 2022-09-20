package com.mpms.relatorioacessibilidadecortec.commService;

import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONCreator {

    public int error = 0;
    public JSONObject jObject = new JSONObject();
    public SchoolEntry school;
    ArrayList<List<RestroomEntry>> restroom = new ArrayList<>();
    List<RoomEntry> room = new ArrayList<>();

    public void createJsonInstance(SchoolEntry entry, List<RoomEntry> room) {
        school = entry;
        this.room = room;

    }

    public void createJson() {
        try {
            JSONArray restData = new JSONArray();
            for (int j = 0; j < room.size(); j++) {
                RoomEntry rEntry = room.get(j);
//                    RestData
                JSONObject rest = new JSONObject();
                rest.put("BlockID", rEntry.getBlockID());
                rest.put("RoomID", rEntry.getRoomID());
                rest.put("RoomType", rEntry.getRoomType());
                restData.put(rest);
            }
            jObject.put("Rooms", restData);


            jObject.put("schoolNameCaps", school.getSchoolName().toUpperCase());
            jObject.put("cityNameCaps", school.getNameCity().toUpperCase());
            jObject.put("schoolName", school.getSchoolName());
            jObject.put("schoolAddress", school.getSchoolAddress());
            jObject.put("schoolNumber", school.getAddressNumber());
            jObject.put("schoolNeighbour", school.getAddressNeighborhood());
            jObject.put("schoolDistrict", school.getNameDistrict());
            jObject.put("cityName", school.getNameCity());

            if (school.getFinalDateInspection() == null)
                jObject.put("visitDate", "Em " + school.getInitialDateInspection());
            else {
                jObject.put("visitDate", "Nos dias " + school.getInitialDateInspection() +
                        " e " + school.getFinalDateInspection());
            }
            jObject.put("responsibleVisit", school.getNameResponsibleVisit());

            jObject.put("youngAge", String.valueOf(school.getYoungestStudentAge()));
            jObject.put("oldestAge", String.valueOf(school.getOldestStudentAge()));
            jObject.put("ageClassYoung", String.valueOf(school.getMonthYearYoungest()));
            jObject.put("ageClassOldest", String.valueOf(school.getMonthYearYoungest()));

            jObject.put("schoolServices", "");
            if (school.getHasNursery() != null && school.getHasNursery().equals(1))
                jObject.accumulate("schoolServices", "Berçário, ");
            if (school.getHasDayCare() != null && school.getHasDayCare().equals(1))
                jObject.accumulate("schoolServices", "Creche, ");
            if (school.getHasMaternal() != null && school.getHasMaternal().equals(1))
                jObject.accumulate("schoolServices", "Maternal, ");
            if (school.getHasPreschool() != null && school.getHasPreschool().equals(1))
                jObject.accumulate("schoolServices", "Pré-Escola, ");
            if (school.getHasElementaryMiddle() != null && school.getHasElementaryMiddle().equals(1))
                jObject.accumulate("schoolServices", "Ensino Fundamental, ");
            if (school.getHasHighSchool() != null && school.getHasHighSchool().equals(1))
                jObject.accumulate("schoolServices", "Ensino Médio, ");
            if (school.getHasEja() != null && school.getHasEja().equals(1))
                jObject.accumulate("schoolServices", "e E.J.A");

            jObject.put("workingHours", "");
            if (school.getHasMorningClasses() != null && school.getHasMorningClasses().equals(1)) {
                jObject.accumulate("workingHours", "matutino (" + school.getMorningStart() + "hs às " +
                        school.getMorningEnd() + "hs)");
                if (school.getHasAfternoonClasses().equals(0) && school.getHasEveningClasses().equals(0))
                    jObject.accumulate("workingHours", ".");
                else if (school.getHasAfternoonClasses().equals(1) && school.getHasEveningClasses().equals(1))
                    jObject.accumulate("workingHours", ",");
                else
                    jObject.accumulate("workingHours", " e ");
            }
            if (school.getHasAfternoonClasses() != null && school.getHasAfternoonClasses().equals(1)) {
                jObject.accumulate("workingHours", "vespertino (" + school.getAfternoonStart() + "hs às " +
                        school.getAfternoonEnd() + "hs)");
                if (school.getHasEveningClasses().equals(0))
                    jObject.accumulate("workingHours", ".");
                else
                    jObject.accumulate("workingHours", " e ");
            }
            if (school.getHasEveningClasses() != null && school.getHasEveningClasses().equals(1))
                jObject.accumulate("workingHours", "noturno (" + school.getEveningStart() + "hs às " +
                        school.getEveningEnd() + "hs).");

            jObject.put("numberStudents", String.valueOf(school.getNumberStudents()));
            jObject.put("numberDisabled", String.valueOf(school.getNumberStudentsPCD()));
            jObject.put("necessityDesc", school.getWorkersPCDDescription());
            jObject.put("numberWorkers", String.valueOf(school.getNumberWorkers()));
            jObject.put("librasWorkers", String.valueOf(school.getNumberWorkersLibras()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
