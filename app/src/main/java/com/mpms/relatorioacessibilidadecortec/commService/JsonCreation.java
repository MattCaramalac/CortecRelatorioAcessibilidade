package com.mpms.relatorioacessibilidadecortec.commService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class JsonCreation {

    private SchoolEntry school;
    private List<BlockSpaceEntry> blocks;
    private List<RoomEntry> roomList;
    private List<ExternalAccess> extList;
    private List<ParkingLotEntry> parkList;
    private List<PlaygroundEntry> playList;
    private List<RestroomEntry> restList;
    private List<SidewalkEntry> sideList;
    private List<WaterFountainEntry> fountList;
    private List<RampStairsEntry> roomStRaList;
    private List<RampStairsEntry> sideStRaList;
    private List<RampStairsEntry> extStRaList;
    private List<RampStairsEntry> parkStRaList;
    private List<BlackboardEntry> boardList;
    private List<CounterEntry> counterList;
    private List<DoorEntry> doorList;
    private List<FreeSpaceEntry> freeList;
    private List<SwitchEntry> switchList;
    private List<TableEntry> tableList;
    private List<WindowEntry> windowList;
    private List<DoorLockEntry> doorLockList;
    private List<DoorLockEntry> gateLockList;
    private List<GateObsEntry> gateList;
    private List<PayPhoneEntry> extPhoneList;
    private List<PayPhoneEntry> sidePhoneList;
    private List<SidewalkSlopeEntry> slopeList;
    private List<RampStairsFlightEntry> roomFlightList;
    private List<RampStairsFlightEntry> sideFlightList;
    private List<RampStairsFlightEntry> extFlightList;
    private List<RampStairsFlightEntry> parkFlightList;
    private List<RampStairsRailingEntry> roomRailList;
    private List<RampStairsRailingEntry> sideRailList;
    private List<RampStairsRailingEntry> extRailList;
    private List<RampStairsRailingEntry> parkRailList;
    private List<RampStairsHandrailEntry> roomHandList;
    private List<RampStairsHandrailEntry> sideHandList;
    private List<RampStairsHandrailEntry> extHandList;
    private List<RampStairsHandrailEntry> parkHandList;

    public JsonCreation(SchoolEntry school, List<BlockSpaceEntry> blocks, List<RoomEntry> roomList, List<ExternalAccess> extList, List<ParkingLotEntry> parkList,
                        List<PlaygroundEntry> playList, List<RestroomEntry> restList, List<SidewalkEntry> sideList, List<WaterFountainEntry> fountList,
                        List<RampStairsEntry> roomStRaList, List<RampStairsEntry> sideStRaList, List<RampStairsEntry> extStRaList, List<RampStairsEntry> parkStRaList,
                        List<BlackboardEntry> boardList, List<CounterEntry> counterList, List<DoorEntry> doorList, List<FreeSpaceEntry> freeList, List<SwitchEntry> switchList,
                        List<TableEntry> tableList, List<WindowEntry> windowList, List<DoorLockEntry> doorLockList, List<DoorLockEntry> gateLockList, List<GateObsEntry> gateList,
                        List<PayPhoneEntry> extPhoneList, List<PayPhoneEntry> sidePhoneList, List<SidewalkSlopeEntry> slopeList, List<RampStairsFlightEntry> roomFlightList,
                        List<RampStairsFlightEntry> sideFlightList, List<RampStairsFlightEntry> extFlightList, List<RampStairsFlightEntry> parkFlightList,
                        List<RampStairsRailingEntry> roomRailList, List<RampStairsRailingEntry> sideRailList, List<RampStairsRailingEntry> extRailList,
                        List<RampStairsRailingEntry> parkRailList, List<RampStairsHandrailEntry> roomHandList, List<RampStairsHandrailEntry> sideHandList,
                        List<RampStairsHandrailEntry> extHandList, List<RampStairsHandrailEntry> parkHandList) {
        this.school = school;
        this.blocks = blocks;
        this.roomList = roomList;
        this.extList = extList;
        this.parkList = parkList;
        this.playList = playList;
        this.restList = restList;
        this.sideList = sideList;
        this.fountList = fountList;
        this.roomStRaList = roomStRaList;
        this.sideStRaList = sideStRaList;
        this.extStRaList = extStRaList;
        this.parkStRaList = parkStRaList;
        this.boardList = boardList;
        this.counterList = counterList;
        this.doorList = doorList;
        this.freeList = freeList;
        this.switchList = switchList;
        this.tableList = tableList;
        this.windowList = windowList;
        this.doorLockList = doorLockList;
        this.gateLockList = gateLockList;
        this.gateList = gateList;
        this.extPhoneList = extPhoneList;
        this.sidePhoneList = sidePhoneList;
        this.slopeList = slopeList;
        this.roomFlightList = roomFlightList;
        this.sideFlightList = sideFlightList;
        this.extFlightList = extFlightList;
        this.parkFlightList = parkFlightList;
        this.roomRailList = roomRailList;
        this.sideRailList = sideRailList;
        this.extRailList = extRailList;
        this.parkRailList = parkRailList;
        this.roomHandList = roomHandList;
        this.sideHandList = sideHandList;
        this.extHandList = extHandList;
        this.parkHandList = parkHandList;
    }

    public String moOrYe(Integer age, Integer date) {
        if ((age == 0 || age == 1) && date == 0)
            return "mês";
        else if ((age == 0 || age == 1) && date == 1)
            return "ano";
        else if (age > 1 && date == 0)
            return "meses";
        else
            return "anos";
    }

    public HashMap<String, String> createJson() {
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

            schoolObj.put("techName", school.getNameInspectionTeam());

            StringBuilder parking = new StringBuilder();
            if (parkList.size() > 0) {
                int extP = 0, intP = 0;
                for (ParkingLotEntry list : parkList) {
                    if (list.getTypeParkingLot() == 1) {
                        extP++;
                    } else {
                        intP++;
                    }
                }
                if (extP == 0) {
                    if (intP == 1)
                        parking.append("contendo apenas 1 (um) estacionamento interno definido");
                    else
                        parking.append("contendo ").append(intP).append("estacionamentos internos definidos");
                } else if (intP == 0)  {
                    if (extP == 1)
                        parking.append("contendo apenas 1 (um) estacionamento externo definido");
                    else
                        parking.append("contendo ").append(intP).append("estacionamentos externos definidos");
                } else {
                    parking.append("contendo ");
                    if (intP == 1)
                        parking.append("1 (um) estacionamento interno e ");
                    else
                        parking.append(intP).append(" estacionamentos internos e ");
                    if (extP == 1)
                        parking.append("1 (um) estacionamento externo definidos");
                    else
                        parking.append(extP).append(" estacionamentos externos definidos");
                }
            } else
                parking.append("sem estacionamentos internos ou externos definidos");
            schoolObj.put("hasParking", parking);

            StringBuilder blockText = new StringBuilder();
            blockText.append("constituído por ");
            int bl = 0;
            boolean hSp = false;
            for(BlockSpaceEntry block : blocks) {
                if (block.getBlockSpaceType() == 0)
                    bl++;
                if (block.getBlockSpaceType() == 2)
                    hSp = true;
            }
            if (bl == 1)
                blockText.append("1 (um) bloco arquitetônico");
            else
                blockText.append(bl).append(" blocos arquitetônicos");
            if (hSp)
                blockText.append(", ladeados por espaços de apoio");
            schoolObj.put("blockQnt", blockText);

            if (school.getFinalDateInspection() == null)
                schoolObj.put("visitDate", "Em " + school.getInitialDateInspection());
            else {
                schoolObj.put("visitDate", "Nos dias " + school.getInitialDateInspection() +
                        " e " + school.getFinalDateInspection());
            }
            schoolObj.put("responsibleVisit", school.getNameResponsibleVisit());

            schoolObj.put("youngAge", String.valueOf(school.getYoungestStudentAge()));
            schoolObj.put("oldestAge", String.valueOf(school.getOldestStudentAge()));
            schoolObj.put("ageClassYoung", moOrYe(school.getYoungestStudentAge(), school.getMonthYearYoungest()));
            schoolObj.put("ageClassOldest", moOrYe(school.getOldestStudentAge(), school.getMonthYearOldest()));

            StringBuilder services = new StringBuilder();
            if (school.getHasNursery() != null && school.getHasNursery().equals(1))
                services.append("Berçário, ");
            if (school.getHasDayCare() != null && school.getHasDayCare().equals(1))
                services.append("Creche, ");
            if (school.getHasMaternal() != null && school.getHasMaternal().equals(1))
                services.append("Maternal, ");
            if (school.getHasPreschool() != null && school.getHasPreschool().equals(1))
                services.append("Pré-Escola, ");
            if (school.getHasElementaryMiddle() != null && school.getHasElementaryMiddle().equals(1))
                services.append("Ensino Fundamental, ");
            if (school.getHasHighSchool() != null && school.getHasHighSchool().equals(1))
                services.append("Ensino Médio, ");
            if (school.getHasEja() != null && school.getHasEja().equals(1))
                services.append("e E.J.A");
            schoolObj.put("schoolServices", services.toString());


            StringBuilder workHour = new StringBuilder();
            int counter = 0;
            counter += school.getHasMorningClasses();
            counter += school.getHasAfternoonClasses();
            counter += school.getHasEveningClasses();

            if (counter == 1)
                workHour.append("no período ");
            else
                workHour.append("nos períodos ");
            if (school.getHasMorningClasses() != null && school.getHasMorningClasses().equals(1)) {
                workHour.append("matutino (").append(school.getMorningStart()).append("hs às ")
                        .append(school.getMorningEnd()).append("hs)");
                if (school.getHasAfternoonClasses().equals(1) && school.getHasEveningClasses().equals(1))
                    workHour.append(",");
                else
                    workHour.append(" e ");
            }
            if (school.getHasAfternoonClasses() != null && school.getHasAfternoonClasses().equals(1)) {
                workHour.append("vespertino (").append(school.getAfternoonStart()).append("hs às ")
                        .append(school.getAfternoonEnd()).append("hs)");
                if (school.getHasEveningClasses().equals(1))
                    workHour.append(" e ");
            }
            if (school.getHasEveningClasses() != null && school.getHasEveningClasses().equals(1))
                workHour.append("noturno (").append(school.getEveningStart()).append("hs às ")
                        .append(school.getEveningEnd()).append("hs)");
            schoolObj.put("workingHours", workHour.toString());

            schoolObj.put("numberStudents", String.valueOf(school.getNumberStudents()));

//            Número Alunos PCD/PMR
            if (school.getNumberStudentsPCD() == 0)
                schoolObj.put("numberDisabled", "nenhum demandava");
            else if (school.getNumberStudentsPCD() == 1)
                schoolObj.put("numberDisabled", "apenas 1 (um) demandava");
            else
                schoolObj.put("numberDisabled", school.getNumberStudentsPCD() + " demandavam");

//            Descrição PCD/PMR alunos
            if (school.getNumberStudentsPCD() == 0)
                schoolObj.put("necessityDesc", "");
            else
                schoolObj.put("necessityDesc", ", a saber, " + school.getStudentsPCDDescription());

//            Funcionários da Escola
            schoolObj.put("numberWorkers", String.valueOf(school.getNumberWorkers()));

//            Funcionários com PCD
            if(school.getNumberWorkersPCD() == 0)
                schoolObj.put("disabledWorkers", "nenhum é portador");
            else if(school.getNumberWorkersPCD() == 1)
                schoolObj.put("disabledWorkers", "apenas 1 (um) é portador");
            else
                schoolObj.put("disabledWorkers", school.getNumberWorkersPCD() + " são portadores");

//            Descrição PCD/PMR funcionários
            if (school.getNumberWorkersPCD() == 0)
                schoolObj.put("workDescPCD", "");
            else
                schoolObj.put("workDescPCD", ", a saber, " + school.getWorkersPCDDescription());



//            Funcionários com Libras
            if (school.getNumberWorkersLibras() == null || school.getNumberWorkersLibras() == 0)
                schoolObj.put("librasWorkers", "nenhum destes possui");
            else if (school.getNumberWorkersLibras() == 1)
                schoolObj.put("librasWorkers", "somente 1 (um) destes possui");
            else
                schoolObj.put("librasWorkers", school.getNumberWorkersLibras() + " destes possuem");


            schoolData.put(schoolObj);

            return new Gson().fromJson(schoolObj.toString(), new TypeToken<HashMap<String, String>>(){}.getType());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

}
