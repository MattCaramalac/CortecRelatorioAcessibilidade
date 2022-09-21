package com.mpms.relatorioacessibilidadecortec.commService;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONCreator {

    public int error = 0;
    public JSONObject jObject = new JSONObject();
    public SchoolEntry school;
    List<RoomEntry> room = new ArrayList<>();
    List<ExternalAccess> extList = new ArrayList<>();
    List<ParkingLotEntry> parkList = new ArrayList<>();
    List<PlaygroundEntry> playList = new ArrayList<>();
    List<RampStairsEntry> rStList = new ArrayList<>();
    List<RestroomEntry> restList = new ArrayList<>();
    List<SidewalkEntry> sideList = new ArrayList<>();
    List<WaterFountainEntry> fountList = new ArrayList<>();
    List<BlackboardEntry> boardList = new ArrayList<>();
    List<CounterEntry> counterList = new ArrayList<>();
    List<DoorEntry> doorList = new ArrayList<>();
    List<FreeSpaceEntry> freeList = new ArrayList<>();
    List<SwitchEntry> switchList = new ArrayList<>();
    List<TableEntry> tableList = new ArrayList<>();
    List<WindowEntry> windowList = new ArrayList<>();

    public void createJsonInstance(SchoolEntry entry, List<RoomEntry> room, List<ExternalAccess> extList, List<ParkingLotEntry> parkList, List<PlaygroundEntry> playList,
                                   List<RampStairsEntry> rStList, List<RestroomEntry> restList, List<SidewalkEntry> sideList, List<WaterFountainEntry> fountList,
                                   List<BlackboardEntry> boardList, List<CounterEntry> counterList, List<DoorEntry> doorList, List<FreeSpaceEntry> freeList,
                                   List<SwitchEntry> switchList, List<TableEntry> tableList, List<WindowEntry> windowList) {
        school = entry;
        this.room = room;
        this.extList = extList;
        this.parkList = parkList;
        this.playList = playList;
        this.rStList = rStList;
        this.restList = restList;
        this.sideList = sideList;
        this.fountList = fountList;
        this.boardList = boardList;
        this.counterList = counterList;
        this.doorList = doorList;
        this.freeList = freeList;
        this.switchList = switchList;
        this.tableList = tableList;
        this.windowList = windowList;

    }

//    TODO - Criar os métodos para pegar os dados das listas
    public void createJson() {
        try {
            JSONArray schoolData = new JSONArray();

            JSONObject schoolObj = new JSONObject();

            schoolObj.put("schoolNameCaps", school.getSchoolName().toUpperCase());
            schoolObj.put("cityNameCaps", school.getNameCity().toUpperCase());
            schoolObj.put("schoolName", school.getSchoolName());
            schoolObj.put("schoolAddress", school.getSchoolAddress());
            schoolObj.put("schoolNumber", school.getAddressNumber());
            schoolObj.put("schoolNeighbour", school.getAddressNeighborhood());
            schoolObj.put("schoolDistrict", school.getNameDistrict());
            schoolObj.put("cityName", school.getNameCity());

            if (school.getFinalDateInspection() == null)
                schoolObj.put("visitDate", "Em " + school.getInitialDateInspection());
            else {
                schoolObj.put("visitDate", "Nos dias " + school.getInitialDateInspection() +
                        " e " + school.getFinalDateInspection());
            }
            schoolObj.put("responsibleVisit", school.getNameResponsibleVisit());

            schoolObj.put("youngAge", String.valueOf(school.getYoungestStudentAge()));
            schoolObj.put("oldestAge", String.valueOf(school.getOldestStudentAge()));
            schoolObj.put("ageClassYoung", String.valueOf(school.getMonthYearYoungest()));
            schoolObj.put("ageClassOldest", String.valueOf(school.getMonthYearYoungest()));

            schoolObj.put("schoolServices", "");
            if (school.getHasNursery() != null && school.getHasNursery().equals(1))
                schoolObj.accumulate("schoolServices", "Berçário, ");
            if (school.getHasDayCare() != null && school.getHasDayCare().equals(1))
                schoolObj.accumulate("schoolServices", "Creche, ");
            if (school.getHasMaternal() != null && school.getHasMaternal().equals(1))
                schoolObj.accumulate("schoolServices", "Maternal, ");
            if (school.getHasPreschool() != null && school.getHasPreschool().equals(1))
                schoolObj.accumulate("schoolServices", "Pré-Escola, ");
            if (school.getHasElementaryMiddle() != null && school.getHasElementaryMiddle().equals(1))
                schoolObj.accumulate("schoolServices", "Ensino Fundamental, ");
            if (school.getHasHighSchool() != null && school.getHasHighSchool().equals(1))
                schoolObj.accumulate("schoolServices", "Ensino Médio, ");
            if (school.getHasEja() != null && school.getHasEja().equals(1))
                schoolObj.accumulate("schoolServices", "e E.J.A");

            schoolObj.put("workingHours", "");
            if (school.getHasMorningClasses() != null && school.getHasMorningClasses().equals(1)) {
                schoolObj.accumulate("workingHours", "matutino (" + school.getMorningStart() + "hs às " +
                        school.getMorningEnd() + "hs)");
                if (school.getHasAfternoonClasses().equals(0) && school.getHasEveningClasses().equals(0))
                    schoolObj.accumulate("workingHours", ".");
                else if (school.getHasAfternoonClasses().equals(1) && school.getHasEveningClasses().equals(1))
                    schoolObj.accumulate("workingHours", ",");
                else
                    schoolObj.accumulate("workingHours", " e ");
            }
            if (school.getHasAfternoonClasses() != null && school.getHasAfternoonClasses().equals(1)) {
                schoolObj.accumulate("workingHours", "vespertino (" + school.getAfternoonStart() + "hs às " +
                        school.getAfternoonEnd() + "hs)");
                if (school.getHasEveningClasses().equals(0))
                    schoolObj.accumulate("workingHours", ".");
                else
                    schoolObj.accumulate("workingHours", " e ");
            }
            if (school.getHasEveningClasses() != null && school.getHasEveningClasses().equals(1))
                schoolObj.accumulate("workingHours", "noturno (" + school.getEveningStart() + "hs às " +
                        school.getEveningEnd() + "hs).");

            schoolObj.put("numberStudents", String.valueOf(school.getNumberStudents()));
            schoolObj.put("numberDisabled", String.valueOf(school.getNumberStudentsPCD()));
            schoolObj.put("necessityDesc", school.getWorkersPCDDescription());
            schoolObj.put("numberWorkers", String.valueOf(school.getNumberWorkers()));
            schoolObj.put("librasWorkers", String.valueOf(school.getNumberWorkersLibras()));



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
            schoolObj.put("Rooms", restData);


            schoolData.put(schoolObj);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
