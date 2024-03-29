package com.mpms.relatorioacessibilidadecortec.commService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CirculationEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.EquipmentEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FallProtectionEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolBenchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEquipEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolRampEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SingleStepEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonCreation {

    private final SchoolEntry school;
    //    Blocks
    private final List<BlockSpaceEntry> blockList;
    private final int qntBlocks;
    //    External Area
    private final List<ExternalAccess> extList;
    private final List<DoorLockEntry> gateLockList;
    private final List<GateObsEntry> gateObsList;
    private final List<PayPhoneEntry> extPhoneList;
    private final List<RampStairsEntry> extStRaList;
    private final List<RampStairsFlightEntry> extFlightList;
    private final List<RampStairsRailingEntry> extRailList;
    private final List<RampStairsHandrailEntry> extHandList;
//    Sidewalk
    private final List<SidewalkEntry> sideList;
    private final List<PayPhoneEntry> sidePhoneList;
    private final List<SidewalkSlopeEntry> slopeList;
//    TODO - adicionar as rampas e escadas/degraus simplificados nas calçadas
    private final List<RampStairsEntry> sideStRaList;
    private final List<RampStairsFlightEntry> sideFlightList;
    private final List<RampStairsRailingEntry> sideRailList;
    private final List<RampStairsHandrailEntry> sideHandList;
    //    Help Area
    private final boolean hasHelpSpace;
    private final List<PlaygroundEntry> playList;

    private final List<PoolEntry> pList;
    private final List<PoolRampEntry> poolRampList;
    private final List<PoolStairsEntry> poolStairsList;
    private final List<PoolBenchEntry> poolBenchList;
    private final List<PoolEquipEntry> poolEquipList;
    //    Parking Lots
    private final int extPark;
    private final int intPark;
    private final List<ParkingLotEntry> parkList;
    private final List<RampStairsEntry> parkStRaList;
    private final List<RampStairsFlightEntry> parkFlightList;
    private final List<RampStairsRailingEntry> parkRailList;
    private final List<RampStairsHandrailEntry> parkHandList;
    private final List<ParkingLotElderlyEntry> parkElderList;
    private final List<ParkingLotPCDEntry> parkPcdList;
    //    School Blocks
    private final List<WaterFountainEntry> fountList;

    private final List<RoomEntry> roomList;
    private final List<BlackboardEntry> roomBoardList;
    private final List<CounterEntry> roomCounterList;
    private final List<DoorEntry> roomDoorList;
    private final List<DoorLockEntry> doorLockList;
    private final List<FreeSpaceEntry> roomFrSpList;
    private final List<SwitchEntry> roomSwitchList;
    private final List<TableEntry> roomTableList;
    private final List<WindowEntry> roomWindowList;
    private final List<WaterFountainEntry> roomWater;
    private final List<EquipmentEntry> roomEquipList;
    private final List<SingleStepEntry> roomStepList;
    private final List<SlopeEntry> roomSlopeList;
    private final List<RampStairsEntry> roomStRaList;
    private final List<RampStairsFlightEntry> roomFlightList;
    private final List<RampStairsRailingEntry> roomRailList;
    private final List<RampStairsHandrailEntry> roomHandList;
    //Restrooms
    private final List<RestroomEntry> restList;
    private final List<DoorEntry> restDoorList;
    private final List<FreeSpaceEntry> restFrSpaceList;
    private final List<RestBoxEntry> boxList;
    private final List<DoorEntry> boxDoorList;
    //    Circulations
    private final List<CirculationEntry> circList;
    private final List<DoorEntry> circDoorList;
    private final List<DoorLockEntry> circLockList;
    private final List<SwitchEntry> circSwitchList;
    private final List<WindowEntry> circWindowList;
    private final List<TableEntry> circTableList;
    private final List<BlackboardEntry> circBoardList;
    private final List<FreeSpaceEntry> circFreeSpList;
    private final List<SingleStepEntry> circStepList;
    private final List<SlopeEntry> circSlopeList;
    private final List<WaterFountainEntry> circFountainList;
    private final List<EquipmentEntry> circEquipList;
    private final List<CounterEntry> circCounterList;
    private final List<FallProtectionEntry> circProtectList;
    private final List<RampStairsEntry> circRampStairsList;
    private final List<RampStairsFlightEntry> circFlightList;
    private final List<RampStairsRailingEntry> circRailList;
    private final List<RampStairsHandrailEntry> circHandList;

    public JsonCreation(SchoolEntry school, List<BlockSpaceEntry> blockList, int qntBlocks, List<ExternalAccess> extList, List<DoorLockEntry> gateLockList,
                        List<GateObsEntry> gateObsList, List<PayPhoneEntry> extPhoneList, List<RampStairsEntry> extStRaList, List<RampStairsFlightEntry> extFlightList,
                        List<RampStairsRailingEntry> extRailList, List<RampStairsHandrailEntry> extHandList, List<SidewalkEntry> sideList, List<PayPhoneEntry> sidePhoneList,
                        List<SidewalkSlopeEntry> slopeList, boolean hasHelpSpace, List<PlaygroundEntry> playList, List<PoolEntry> pList, List<PoolRampEntry> poolRampList,
                        List<PoolStairsEntry> poolStairsList, List<PoolBenchEntry> poolBenchList, List<PoolEquipEntry> poolEquipList, int extPark, int intPark,
                        List<ParkingLotEntry> parkList, List<RampStairsEntry> parkStRaList, List<RampStairsFlightEntry> parkFlightList,
                        List<RampStairsRailingEntry> parkRailList, List<RampStairsHandrailEntry> parkHandList, List<ParkingLotElderlyEntry> parkElderList,
                        List<ParkingLotPCDEntry> parkPcdList, List<WaterFountainEntry> fountList, List<RoomEntry> roomList, List<BlackboardEntry> roomBoardList,
                        List<CounterEntry> roomCounterList, List<DoorEntry> roomDoorList, List<DoorLockEntry> doorLockList, List<FreeSpaceEntry> roomFrSpList,
                        List<SwitchEntry> roomSwitchList, List<TableEntry> roomTableList, List<WindowEntry> roomWindowList, List<WaterFountainEntry> roomWater,
                        List<EquipmentEntry> roomEquipList, List<SingleStepEntry> roomStepList, List<SlopeEntry> roomSlopeList, List<RampStairsEntry> roomStRaList,
                        List<RampStairsFlightEntry> roomFlightList, List<RampStairsRailingEntry> roomRailList, List<RampStairsHandrailEntry> roomHandList,
                        List<RestroomEntry> restList, List<DoorEntry> restDoorList, List<FreeSpaceEntry> restFrSpaceList, List<RestBoxEntry> boxList,
                        List<DoorEntry> boxDoorList, List<CirculationEntry> circList, List<DoorEntry> circDoorList, List<DoorLockEntry> circLockList,
                        List<SwitchEntry> circSwitchList, List<WindowEntry> circWindowList, List<TableEntry> circTableList, List<BlackboardEntry> circBoardList,
                        List<FreeSpaceEntry> circFreeSpList, List<SingleStepEntry> circStepList, List<SlopeEntry> circSlopeList, List<WaterFountainEntry> circFountainList,
                        List<EquipmentEntry> circEquipList, List<CounterEntry> circCounterList, List<FallProtectionEntry> circProtectList,
                        List<RampStairsEntry> circRampStairsList, List<RampStairsFlightEntry> circFlightList, List<RampStairsRailingEntry> circRailList,
                        List<RampStairsHandrailEntry> circHandList, List<RampStairsEntry> sideStRaList, List<RampStairsFlightEntry> sideFlightList,
                        List<RampStairsRailingEntry> sideRailList, List<RampStairsHandrailEntry> sideHandList) {
        this.school = school;
        this.blockList = blockList;
        this.qntBlocks = qntBlocks;
        this.extList = extList;
        this.gateLockList = gateLockList;
        this.gateObsList = gateObsList;
        this.extPhoneList = extPhoneList;
        this.extStRaList = extStRaList;
        this.extFlightList = extFlightList;
        this.extRailList = extRailList;
        this.extHandList = extHandList;
        this.sideList = sideList;
        this.sidePhoneList = sidePhoneList;
        this.slopeList = slopeList;
        this.hasHelpSpace = hasHelpSpace;
        this.playList = playList;
        this.pList = pList;
        this.poolRampList = poolRampList;
        this.poolStairsList = poolStairsList;
        this.poolBenchList = poolBenchList;
        this.poolEquipList = poolEquipList;
        this.extPark = extPark;
        this.intPark = intPark;
        this.parkList = parkList;
        this.parkStRaList = parkStRaList;
        this.parkFlightList = parkFlightList;
        this.parkRailList = parkRailList;
        this.parkHandList = parkHandList;
        this.parkElderList = parkElderList;
        this.parkPcdList = parkPcdList;
        this.fountList = fountList;
        this.roomList = roomList;
        this.roomBoardList = roomBoardList;
        this.roomCounterList = roomCounterList;
        this.roomDoorList = roomDoorList;
        this.doorLockList = doorLockList;
        this.roomFrSpList = roomFrSpList;
        this.roomSwitchList = roomSwitchList;
        this.roomTableList = roomTableList;
        this.roomWindowList = roomWindowList;
        this.roomWater = roomWater;
        this.roomEquipList = roomEquipList;
        this.roomStepList = roomStepList;
        this.roomSlopeList = roomSlopeList;
        this.roomStRaList = roomStRaList;
        this.roomFlightList = roomFlightList;
        this.roomRailList = roomRailList;
        this.roomHandList = roomHandList;
        this.restList = restList;
        this.restDoorList = restDoorList;
        this.restFrSpaceList = restFrSpaceList;
        this.boxList = boxList;
        this.boxDoorList = boxDoorList;
        this.circList = circList;
        this.circDoorList = circDoorList;
        this.circLockList = circLockList;
        this.circSwitchList = circSwitchList;
        this.circWindowList = circWindowList;
        this.circTableList = circTableList;
        this.circBoardList = circBoardList;
        this.circFreeSpList = circFreeSpList;
        this.circStepList = circStepList;
        this.circSlopeList = circSlopeList;
        this.circFountainList = circFountainList;
        this.circEquipList = circEquipList;
        this.circCounterList = circCounterList;
        this.circProtectList = circProtectList;
        this.circRampStairsList = circRampStairsList;
        this.circFlightList = circFlightList;
        this.circRailList = circRailList;
        this.circHandList = circHandList;
        this.sideStRaList = sideStRaList;
        this.sideFlightList = sideFlightList;
        this.sideRailList = sideRailList;
        this.sideHandList = sideHandList;
    }

    public List<String> ambListCreator() {

        List<String> ambientList = new ArrayList<>();
        for (int i = 0; i < blockList.size(); i++) {
            BlockSpaceEntry block = blockList.get(i);
            StringBuilder build = new StringBuilder();
            if (block.getBlockSpaceType() == 0) {
                int bib = 0, coord = 0, dir = 0, ref = 0, classroom = 0, techRoom = 0, recRoom = 0, profRoom = 0, sec = 0, accessSan = 0, accColSan = 0,
                        colSan = 0, infSan = 0, water = 0;
                build.append("Bloco ").append(i + 1).append(", contendo: ");
                for (int j = 0; j < roomList.size(); j++) {
                    RoomEntry entry = roomList.get(j);
                    if (block.getBlockSpaceID() == entry.getBlockID()) {
                        switch (entry.getRoomType()) {
                            case 2:
                                bib++;
                                break;
                            case 3:
                                coord++;
                                break;
                            case 4:
                                dir++;
                                break;
                            case 5:
                                ref++;
                                break;
                            case 6:
                                classroom++;
                                break;
                            case 7:
                                techRoom++;
                                break;
                            case 8:
                                recRoom++;
                                break;
                            case 9:
                                profRoom++;
                                break;
                            case 11:
                                sec++;
                                break;
                            case 12:
                                build.append(entry.getRoomLocation()).append(", ");
                        }
                    }
                }

                for (int j = 0; j < restList.size(); j++) {
                    RestroomEntry rest = restList.get(j);
                    if (block.getBlockSpaceID() == rest.getBlockID()) {
                        switch (rest.getRestType()) {
                            case 0:
                                accessSan++;
                                break;
                            case 1:
                                accColSan++;
                                break;
                            case 2:
                                colSan++;
                                break;
                            case 3:
                                infSan++;
                                break;
                        }
                    }
                }

                for (int j = 0; j < fountList.size(); j++) {
                    WaterFountainEntry fountain = fountList.get(j);
                    if (block.getBlockSpaceID() == fountain.getBlockID())
                        water++;
                }

                if (bib == 1)
                    build.append("Biblioteca, ");
                else if (bib > 1)
                    build.append(bib).append(" Bibliotecas, ");

                if (coord == 1)
                    build.append("Coordenação Pedagógica, ");
                else if (coord > 1)
                    build.append(coord).append(" salas de Coordenação Pedagógica, ");

                if (dir == 1)
                    build.append("Sala da Direção, ");
                else if (dir > 1)
                    build.append(dir).append(" salas da Direção, ");

                if (ref == 1)
                    build.append("Refeitório, ");
                else if (ref > 1)
                    build.append(ref).append(" Refeitórios, ");

                if (classroom == 1)
                    build.append(" 01 (uma) Sala de Aula, ");
                else if (classroom > 1)
                    build.append(classroom).append(" Salas de Aula, ");

                if (techRoom == 1)
                    build.append("Sala de Tecnologia, ");
                else if (techRoom > 1)
                    build.append(techRoom).append(" Salas de Tecnologia, ");

                if (recRoom == 1)
                    build.append("Sala de Recursos, ");
                else if (recRoom > 1)
                    build.append(recRoom).append(" Salas de Recursos, ");

                if (profRoom == 1)
                    build.append("Sala dos Professores, ");
                else if (profRoom > 1)
                    build.append(profRoom).append(" Salas dos Professores, ");

                if (sec == 1)
                    build.append("Secretaria, ");
                else if (sec > 1)
                    build.append(sec).append(" salas da Secretaria, ");

                if (accessSan == 1)
                    build.append("1 (um) sanitario accessível independente, ");
                else if (accessSan > 1)
                    build.append(accessSan).append(" sanitários accessíveis independentes, ");

                if (accColSan == 1)
                    build.append("1 (um) sanitario coletivo acessível, ");
                else if (accColSan > 1)
                    build.append(accColSan).append(" sanitários coletivos acessíveis, ");

                if (colSan == 1)
                    build.append("1 (um) sanitario coletivo não acessível, ");
                else if (colSan > 1)
                    build.append(colSan).append(" sanitários coletivos não acessíveis, ");

                if (infSan == 1)
                    build.append("1 (um) sanitario infantil, ");
                else if (infSan > 1)
                    build.append(infSan).append(" sanitários infantis, ");

                if (water == 1)
                    build.append("1 (um) bebedouro.");
                else if (water > 1)
                    build.append(water).append(" bebedouros.");

                ambientList.add(build.toString());
            } else if (block.getBlockSpaceType() == 2) {
                int water = 0, park = 0, play = 0, accSan = 0, colAccSan = 0, colSan = 0, infSan = 0;
                build.append("Espaços de Apoio, sendo: ");

                for (int j = 0; j < fountList.size(); j++) {
                    WaterFountainEntry fountain = fountList.get(j);
                    if (block.getBlockSpaceID() == fountain.getBlockID())
                        water++;
                }

                for (int j = 0; j < parkList.size(); j++) {
                    ParkingLotEntry parking = parkList.get(j);
                    if (block.getBlockSpaceID() == parking.getBlockID())
                        park++;
                }

                for (int j = 0; j < playList.size(); j++) {
                    PlaygroundEntry playground = playList.get(j);
                    if (block.getBlockSpaceID() == playground.getBlockID())
                        play++;
                }

                for (int j = 0; j < restList.size(); j++) {
                    RestroomEntry rest = restList.get(j);
                    if (block.getBlockSpaceID() == rest.getBlockID()) {
                        switch (rest.getRestType()) {
                            case 0:
                                accSan++;
                                break;
                            case 1:
                                colAccSan++;
                                break;
                            case 2:
                                colSan++;
                                break;
                            case 3:
                                infSan++;
                                break;
                        }
                    }

                }

                for (int j = 0; j < roomList.size(); j++) {
                    RoomEntry room = roomList.get(j);
                    if (block.getBlockSpaceID() == room.getBlockID())
                        build.append(room.getRoomLocation()).append(", ");
                }

                if (water == 1)
                    build.append("1 (um) bebedouro, ");
                else if (water > 1) {
                    build.append(water).append(" bebedouros, ");
                }

                if (park == 1)
                    build.append("1 (um) estacionamento interno, ");
                else if (park > 1)
                    build.append(park).append(" estacionamentos internos, ");

                if (play == 1)
                    build.append("playground, ");
                else if (play > 1)
                    build.append(play).append(" playgrounds, ");

                if (accSan == 1)
                    build.append("1 (um) sanitario accessível independente, ");
                else if (accSan > 1)
                    build.append(accSan).append(" sanitários accessíveis independentes, ");

                if (colAccSan == 1)
                    build.append("1 (um) sanitario coletivo acessível, ");
                else if (colAccSan > 1)
                    build.append(colAccSan).append(" sanitários coletivos acessíveis, ");

                if (colSan == 1)
                    build.append("1 (um) sanitario coletivo não acessível, ");
                else if (colSan > 1)
                    build.append(colSan).append(" sanitários coletivos não acessíveis, ");

                if (infSan == 1)
                    build.append("1 (um) sanitario infantil, ");
                else if (infSan > 1)
                    build.append(infSan).append(" sanitários infantis, ");

                ambientList.add(build.toString());
            }

        }
        return ambientList;
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

        int schServQnt = 0;
        int schHourQnt = 0;

        try {
            JSONArray schoolData = new JSONArray();

            JSONObject schoolObj = new JSONObject();

            schoolObj.put("schoolNameCaps", school.getSchoolName().toUpperCase());
            schoolObj.put("cityNameCaps", school.getNameCity().toUpperCase());
            schoolObj.put("schoolName", school.getSchoolName());
            schoolObj.put("schoolAddress", school.getSchoolAddress());
            schoolObj.put("schoolNumber", school.getAddressNumber());
            schoolObj.put("schoolNeighbour", school.getAddressNeighborhood());
            if (school.getNameDistrict() != null && school.getNameDistrict().length() > 0)
                schoolObj.put("schoolDistrict", "distrito de " + school.getNameDistrict() + ",");
            else
                schoolObj.put("schoolDistrict", "");
            schoolObj.put("cityName", school.getNameCity());

            schoolObj.put("techName", school.getNameInspectionTeam());

            StringBuilder parking = new StringBuilder();
            if (extPark > 0 || intPark > 0) {
                if (extPark == 0) {
                    if (intPark == 1)
                        parking.append("contendo apenas 1 (um) estacionamento interno definido");
                    else
                        parking.append("contendo ").append(intPark).append("estacionamentos internos definidos");
                } else if (intPark == 0) {
                    if (extPark == 1)
                        parking.append("contendo apenas 1 (um) estacionamento externo definido");
                    else
                        parking.append("contendo ").append(intPark).append("estacionamentos externos definidos");
                } else {
                    parking.append("contendo ");
                    if (intPark == 1)
                        parking.append("1 (um) estacionamento interno e ");
                    else
                        parking.append(intPark).append("estacionamentos internos e ");
                    if (extPark == 1)
                        parking.append("1 (um) estacionamento externo definidos");
                    else
                        parking.append(extPark).append(" estacionamentos externos definidos");
                }
            } else
                parking.append("sem estacionamentos internos ou externos definidos");
            schoolObj.put("hasParking", parking);

            StringBuilder blockText = new StringBuilder();
            if (qntBlocks == 1)
                blockText.append("1 (um) bloco arquitetônico");
            else
                blockText.append(qntBlocks).append(" blocos arquitetônicos");
            if (hasHelpSpace)
                blockText.append(", ladeados por espaços de apoio");
            schoolObj.put("blockQnt", blockText);

            if (school.getFinalDateInspection() == null || school.getFinalDateInspection().length() == 0
                    || school.getFinalDateInspection().equals(school.getInitialDateInspection()))
                schoolObj.put("visitDate", "Em " + school.getInitialDateInspection());
            else {
                schoolObj.put("visitDate", "Entre os dias " + school.getInitialDateInspection() +
                        " e " + school.getFinalDateInspection());
            }
            schoolObj.put("respVisit1", school.getRespName1());
            schoolObj.put("respJob1", school.getRespJob1());
            if (school.getRespName2() != null) {
                schoolObj.put("respVisit2", " e " + school.getRespName2());
                schoolObj.put("respJob2", ", " + school.getRespJob2());
            } else {
                schoolObj.put("respVisit2", "");
                schoolObj.put("respVisit2", "");
            }


            schoolObj.put("youngAge", String.valueOf(school.getYoungestStudentAge()));
            schoolObj.put("oldestAge", String.valueOf(school.getOldestStudentAge()));
            if (!school.getMonthYearYoungest().equals(school.getMonthYearOldest()))
                schoolObj.put("ageClassYoung", moOrYe(school.getYoungestStudentAge(), school.getMonthYearYoungest()));
            else
                schoolObj.put("ageClassYoung", "");
            schoolObj.put("ageClassOldest", moOrYe(school.getOldestStudentAge(), school.getMonthYearOldest()));


//            Serviços Oferecidos
            int schCounter = 1;

            StringBuilder services = new StringBuilder();
            Integer[] schServices = new Integer[5];
            schServices[0] = school.getHasPreschool();
            schServices[1] = school.getHasElementary();
            schServices[2] = school.getHasMiddleSchool();
            schServices[3] = school.getHasHighSchool();
            schServices[4] = school.getHasEja();

            for (Integer sch : schServices) {
                if (sch != null)
                    schServQnt += sch;
            }

            if (schServices[0] != null && schServices[0].equals(1) && schCounter <= schServQnt) {
                services.append("Educação Infantil");
                if (school.getPreschoolFirstGrade() != null && school.getPreschoolLastGrade() != null)
                    services.append(" (níveis ").append(school.getPreschoolFirstGrade()).append(" ao ").append(school.getPreschoolLastGrade()).append(")");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[1] != null && schServices[1].equals(1) && schCounter <= schServQnt) {
                services.append("Ensino Fundamental I");
                if (school.getElementaryFirstGrade() != null && school.getElementaryLastGrade() != null)
                    services.append(" (do ").append(school.getElementaryFirstGrade()).append("º ao ").append(school.getElementaryLastGrade()).append("º ano)");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[2] != null && schServices[2].equals(1) && schCounter <= schServQnt) {
                services.append("Ensino Fundamental II");
                if (school.getMiddleFirstGrade() != null && school.getMiddleLastGrade() != null)
                    services.append(" (do ").append(school.getMiddleFirstGrade()).append("º ao ").append(school.getMiddleLastGrade()).append("º ano)");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[3] != null && schServices[3].equals(1) && schCounter <= schServQnt) {
                services.append("Ensino Médio");
                if (school.getHighFirstGrade() != null && school.getHighLastGrade() != null)
                    services.append(" (do ").append(school.getHighFirstGrade()).append("º ao ").append(school.getHighLastGrade()).append("º ano)");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[4] != null && schServices[4].equals(1) && schCounter <= schServQnt) {
                services.append("E.J.A");
                if (school.getEjaFirstGrade() != null && school.getEjaLastGrade() != null)
                    services.append(" (níveis ").append(school.getEjaFirstGrade()).append(" ao ").append(school.getEjaLastGrade()).append(")");
                schoolServices(services, schCounter, schServQnt);
            }
            schoolObj.put("schoolServices", services.toString());

            if (school.getServicesObs() != null && school.getServicesObs().length() > 0)
                schoolObj.put("schoolServicesObs", ", " + school.getServicesObs());
            else
                schoolObj.put("schoolServicesObs", "");

//            Horário de Funcionamento
            int hourCounter = 1;

            StringBuilder workHour = new StringBuilder();
            Integer[] schHours = new Integer[3];

            schHours[0] = school.getHasMorningClasses();
            schHours[1] = school.getHasAfternoonClasses();
            schHours[2] = school.getHasEveningClasses();

            for (Integer hour : schHours) {
                if (hour != null)
                    schHourQnt += hour;
            }

            if (schHourQnt == 1)
                workHour.append("no período ");
            else
                workHour.append("nos períodos ");

            if (schHours[0] != null && schHours[0].equals(1) && hourCounter <= schHourQnt) {
                workHour.append("matutino (").append(school.getMorningStart()).append("hs às ")
                        .append(school.getMorningEnd()).append("hs)");
                schoolWorkHour(workHour, hourCounter, schHourQnt);
                hourCounter++;
            }
            if (schHours[1] != null && schHours[1].equals(1) && hourCounter <= schHourQnt) {
                workHour.append("vespertino (").append(school.getAfternoonStart()).append("hs às ")
                        .append(school.getAfternoonEnd()).append("hs)");
                schoolWorkHour(workHour, hourCounter, schHourQnt);
                hourCounter++;
            }
            if (schHours[2] != null && schHours[2].equals(1) && hourCounter <= schHourQnt) {
                workHour.append("noturno (").append(school.getEveningStart()).append("hs às ")
                        .append(school.getEveningEnd()).append("hs)");
                schoolWorkHour(workHour, hourCounter, schHourQnt);
            }
            schoolObj.put("workingHours", workHour.toString());

            if (school.getWorkingHoursObs() != null && school.getWorkingHoursObs().length() > 0)
                schoolObj.put("workingHoursObs", ", " + school.getWorkingHoursObs());
            else
                schoolObj.put("workingHoursObs", "");

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

//            Observações dos Alunos
            if (school.getRegisterStudentObs() != null && school.getRegisterStudentObs().length() > 0)
                schoolObj.put("studentsObs", "Destaca-se que " + school.getRegisterStudentObs());
            else
                schoolObj.put("studentsObs", "");

//            Funcionários da Escola
            schoolObj.put("numberWorkers", String.valueOf(school.getNumberWorkers()));

//            Funcionários com PCD
            if (school.getNumberWorkersPCD() == 0)
                schoolObj.put("disabledWorkers", "nenhum é portador");
            else if (school.getNumberWorkersPCD() == 1)
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

            return new Gson().fromJson(schoolObj.toString(), new TypeToken<HashMap<String, String>>() {
            }.getType());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void schoolServices(StringBuilder builder, int schCounter, int schServQnt) {
        if (schCounter + 1 < schServQnt)
            builder.append(", ");
        else if (schCounter + 1 == schServQnt)
            builder.append(" e ");
    }

    public static void schoolWorkHour(StringBuilder builder, int hourCounter, int schHourQnt) {
        if (hourCounter + 1 < schHourQnt)
            builder.append(", ");
        else if (hourCounter + 1 == schHourQnt)
            builder.append(" e ");
    }

    public SchoolEntry getSchool() {
        return school;
    }

    public int getQntBlocks() {
        return qntBlocks;
    }

    public boolean isHasHelpSpace() {
        return hasHelpSpace;
    }

    public int getExtPark() {
        return extPark;
    }

    public int getIntPark() {
        return intPark;
    }

    public List<BlockSpaceEntry> getBlockList() {
        return blockList;
    }

    public List<RoomEntry> getRoomList() {
        return roomList;
    }

    public List<ExternalAccess> getExtList() {
        return extList;
    }

    public List<PlaygroundEntry> getPlayList() {
        return playList;
    }

    public List<ParkingLotEntry> getParkList() {
        return parkList;
    }

    public List<RestroomEntry> getRestList() {
        return restList;
    }

    public List<SidewalkEntry> getSideList() {
        return sideList;
    }

    public List<WaterFountainEntry> getFountList() {
        return fountList;
    }

    public List<RampStairsEntry> getRoomStRaList() {
        return roomStRaList;
    }

    public List<RampStairsEntry> getSideStRaList() {
        return sideStRaList;
    }

    public List<RampStairsEntry> getExtStRaList() {
        return extStRaList;
    }

    public List<RampStairsEntry> getParkStRaList() {
        return parkStRaList;
    }

    public List<BlackboardEntry> getRoomBoardList() {
        return roomBoardList;
    }

    public List<CounterEntry> getRoomCounterList() {
        return roomCounterList;
    }

    public List<DoorEntry> getRoomDoorList() {
        return roomDoorList;
    }

    public List<FreeSpaceEntry> getRoomFrSpList() {
        return roomFrSpList;
    }

    public List<SwitchEntry> getRoomSwitchList() {
        return roomSwitchList;
    }

    public List<TableEntry> getRoomTableList() {
        return roomTableList;
    }

    public List<WindowEntry> getRoomWindowList() {
        return roomWindowList;
    }

    public List<DoorLockEntry> getDoorLockList() {
        return doorLockList;
    }

    public List<DoorLockEntry> getGateLockList() {
        return gateLockList;
    }

    public List<GateObsEntry> getGateObsList() {
        return gateObsList;
    }

    public List<PayPhoneEntry> getExtPhoneList() {
        return extPhoneList;
    }

    public List<PayPhoneEntry> getSidePhoneList() {
        return sidePhoneList;
    }

    public List<SidewalkSlopeEntry> getSlopeList() {
        return slopeList;
    }

    public List<RampStairsFlightEntry> getRoomFlightList() {
        return roomFlightList;
    }

    public List<RampStairsFlightEntry> getSideFlightList() {
        return sideFlightList;
    }

    public List<RampStairsFlightEntry> getExtFlightList() {
        return extFlightList;
    }

    public List<RampStairsFlightEntry> getParkFlightList() {
        return parkFlightList;
    }

    public List<RampStairsRailingEntry> getRoomRailList() {
        return roomRailList;
    }

    public List<RampStairsRailingEntry> getSideRailList() {
        return sideRailList;
    }

    public List<RampStairsRailingEntry> getExtRailList() {
        return extRailList;
    }

    public List<RampStairsRailingEntry> getParkRailList() {
        return parkRailList;
    }

    public List<RampStairsHandrailEntry> getRoomHandList() {
        return roomHandList;
    }

    public List<RampStairsHandrailEntry> getSideHandList() {
        return sideHandList;
    }

    public List<RampStairsHandrailEntry> getExtHandList() {
        return extHandList;
    }

    public List<RampStairsHandrailEntry> getParkHandList() {
        return parkHandList;
    }

    public List<ParkingLotElderlyEntry> getParkElderList() {
        return parkElderList;
    }

    public List<ParkingLotPCDEntry> getParkPcdList() {
        return parkPcdList;
    }

    public List<WaterFountainEntry> getRoomWater() {
        return roomWater;
    }

    public List<DoorEntry> getRestDoorList() {
        return restDoorList;
    }

    public List<FreeSpaceEntry> getRestFrSpaceList() {
        return restFrSpaceList;
    }

    public List<RestBoxEntry> getBoxList() {
        return boxList;
    }

    public List<DoorEntry> getBoxDoorList() {
        return boxDoorList;
    }

    public List<EquipmentEntry> getRoomEquipList() {
        return roomEquipList;
    }

    public List<CirculationEntry> getCircList() {
        return circList;
    }

    public List<DoorEntry> getCircDoorList() {
        return circDoorList;
    }

    public List<DoorLockEntry> getCircLockList() {
        return circLockList;
    }

    public List<SwitchEntry> getCircSwitchList() {
        return circSwitchList;
    }

    public List<WindowEntry> getCircWindowList() {
        return circWindowList;
    }

    public List<TableEntry> getCircTableList() {
        return circTableList;
    }

    public List<BlackboardEntry> getCircBoardList() {
        return circBoardList;
    }

    public List<FreeSpaceEntry> getCircFreeSpList() {
        return circFreeSpList;
    }

    public List<SingleStepEntry> getCircStepList() {
        return circStepList;
    }

    public List<SlopeEntry> getCircSlopeList() {
        return circSlopeList;
    }

    public List<WaterFountainEntry> getCircFountainList() {
        return circFountainList;
    }

    public List<EquipmentEntry> getCircEquipList() {
        return circEquipList;
    }

    public List<CounterEntry> getCircCounterList() {
        return circCounterList;
    }

    public List<FallProtectionEntry> getCircProtectList() {
        return circProtectList;
    }

    public List<RampStairsEntry> getCircRampStairsList() {
        return circRampStairsList;
    }

    public List<RampStairsFlightEntry> getCircFlightList() {
        return circFlightList;
    }

    public List<RampStairsRailingEntry> getCircRailList() {
        return circRailList;
    }

    public List<RampStairsHandrailEntry> getCircHandList() {
        return circHandList;
    }

    public List<PoolEntry> getpList() {
        return pList;
    }

    public List<PoolRampEntry> getPoolRampList() {
        return poolRampList;
    }

    public List<PoolStairsEntry> getPoolStairsList() {
        return poolStairsList;
    }

    public List<PoolBenchEntry> getPoolBenchList() {
        return poolBenchList;
    }

    public List<PoolEquipEntry> getPoolEquipList() {
        return poolEquipList;
    }

    public List<SingleStepEntry> getRoomStepList() {
        return roomStepList;
    }

    public List<SlopeEntry> getRoomSlopeList() {
        return roomSlopeList;
    }
}
