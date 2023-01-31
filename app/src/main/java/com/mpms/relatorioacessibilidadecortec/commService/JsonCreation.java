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
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonCreation {

    private final SchoolEntry school;
    private final int qntBlocks;
    private final boolean hasHelpSpace;
    private final int extPark;
    private final int intPark;
    private final List<BlockSpaceEntry> blockList;
    private final List<RoomEntry> roomList;
    private final List<ExternalAccess> extList; //*
    private final List<PlaygroundEntry> playList;
    private final List<ParkingLotEntry> parkList;
    private final List<ParkingLotElderlyEntry> elderList;
    private final List<ParkingLotPCDEntry> pcdList;
    private final List<RestroomEntry> restList;
    private final List<SidewalkEntry> sideList; //*
    private final List<WaterFountainEntry> fountList;
    private final List<RampStairsEntry> roomStRaList;
    private final List<RampStairsEntry> sideStRaList; //*
    private final List<RampStairsEntry> extStRaList; //*
    private final List<RampStairsEntry> parkStRaList;
    private final List<BlackboardEntry> boardList;
    private final List<CounterEntry> counterList;
    private final List<DoorEntry> doorList;
    private final List<FreeSpaceEntry> freeList;
    private final List<SwitchEntry> switchList;
    private final List<TableEntry> tableList;
    private final List<WindowEntry> windowList;
    private final List<DoorLockEntry> doorLockList;
    private final List<DoorLockEntry> gateLockList; //*
    private final List<GateObsEntry> gateObsList; //*
    private final List<PayPhoneEntry> extPhoneList; //*
    private final List<PayPhoneEntry> sidePhoneList; //*
    private final List<SidewalkSlopeEntry> slopeList;//*
    private final List<RampStairsFlightEntry> roomFlightList;
    private final List<RampStairsFlightEntry> sideFlightList; //*
    private final List<RampStairsFlightEntry> extFlightList; //*
    private final List<RampStairsFlightEntry> parkFlightList;
    private final List<RampStairsRailingEntry> roomRailList;
    private final List<RampStairsRailingEntry> sideRailList; //*
    private final List<RampStairsRailingEntry> extRailList; //*
    private final List<RampStairsRailingEntry> parkRailList;
    private final List<RampStairsHandrailEntry> roomHandList;
    private final List<RampStairsHandrailEntry> sideHandList; //*
    private final List<RampStairsHandrailEntry> extHandList; //*
    private final List<RampStairsHandrailEntry> parkHandList;
    private final List<WaterFountainEntry> roomWater;

    public JsonCreation(SchoolEntry school, List<BlockSpaceEntry> blockList, List<RoomEntry> roomList, List<ExternalAccess> extList, List<ParkingLotEntry> parkList,
                        List<PlaygroundEntry> playList, List<RestroomEntry> restList, List<SidewalkEntry> sideList, List<WaterFountainEntry> fountList,
                        List<RampStairsEntry> roomStRaList, List<RampStairsEntry> sideStRaList, List<RampStairsEntry> extStRaList, List<RampStairsEntry> parkStRaList,
                        List<BlackboardEntry> boardList, List<CounterEntry> counterList, List<DoorEntry> doorList, List<FreeSpaceEntry> freeList, List<SwitchEntry> switchList,
                        List<TableEntry> tableList, List<WindowEntry> windowList, List<DoorLockEntry> doorLockList, List<DoorLockEntry> gateLockList, List<GateObsEntry> gateObsList,
                        List<PayPhoneEntry> extPhoneList, List<PayPhoneEntry> sidePhoneList, List<SidewalkSlopeEntry> slopeList, List<RampStairsFlightEntry> roomFlightList,
                        List<RampStairsFlightEntry> sideFlightList, List<RampStairsFlightEntry> extFlightList, List<RampStairsFlightEntry> parkFlightList,
                        List<RampStairsRailingEntry> roomRailList, List<RampStairsRailingEntry> sideRailList, List<RampStairsRailingEntry> extRailList,
                        List<RampStairsRailingEntry> parkRailList, List<RampStairsHandrailEntry> roomHandList, List<RampStairsHandrailEntry> sideHandList,
                        List<RampStairsHandrailEntry> extHandList, List<RampStairsHandrailEntry> parkHandList, int qntBlocks, boolean hasHelpSpace, int extPark, int intPark,
                        List<ParkingLotElderlyEntry> elderList, List<ParkingLotPCDEntry> pcdList, List<WaterFountainEntry> roomWater) {
        this.school = school;
        this.blockList = blockList;
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
        this.gateObsList = gateObsList;
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
        this.qntBlocks = qntBlocks;
        this.hasHelpSpace = hasHelpSpace;
        this.extPark = extPark;
        this.intPark = intPark;
        this.elderList = elderList;
        this.pcdList = pcdList;
        this.roomWater = roomWater;
    }

    public List<String> ambListCreator() {

        List<String> ambientList = new ArrayList<>();
        for (int i = 0; i < blockList.size(); i++) {
            BlockSpaceEntry block = blockList.get(i);
            StringBuilder build = new StringBuilder();
            if (block.getBlockSpaceType() == 0) {
                int bib = 0, coord = 0, dir = 0, ref = 0, classroom = 0, techRoom = 0, recRoom = 0, profRoom = 0, sec = 0, mBan = 0, fBan = 0, famBan = 0, infBan = 0, water = 0;
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
                        if (rest.getRestType() == 0)
                            mBan++;
                        else if (rest.getRestType() == 1)
                            fBan++;
                        else if (rest.getRestType() == 2)
                            famBan++;
                        else
                            infBan++;
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
                    build.append(sec).append(" salas da Secretaria");

                if (mBan == 1)
                    build.append("1 (um) sanitario masculino, ");
                else if (mBan > 1)
                    build.append(mBan).append(" sanitários masculinos, ");

                if (fBan == 1)
                    build.append("1 (um) sanitario feminino, ");
                else if (fBan > 1)
                    build.append(fBan).append(" sanitários femininos, ");

                if (famBan == 1)
                    build.append("1 (um) sanitario familiar, ");
                else if (famBan > 1)
                    build.append(famBan).append(" sanitários familiar, ");

                if (infBan == 1)
                    build.append("1 (um) sanitario infantil, ");
                else if (infBan > 1)
                    build.append(infBan).append(" sanitários infantis, ");

                if (water == 1)
                    build.append("1 (um) bebedouro.");
                else if (water > 1)
                    build.append(water).append(" bebedouros.");

                ambientList.add(build.toString());
            } else if (block.getBlockSpaceType() == 2) {
                int water = 0, park = 0, play = 0, mBan = 0, fBan = 0, famBan = 0, infBan = 0, other = 0;
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
                        if (rest.getRestType() == 0)
                            mBan++;
                        else if (rest.getRestType() == 1)
                            fBan++;
                        else if (rest.getRestType() == 2)
                            famBan++;
                        else
                            infBan++;
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

                if (mBan == 1)
                    build.append("1 (um) sanitario masculino, ");
                else if (mBan > 1)
                    build.append(mBan).append(" sanitários masculinos, ");

                if (fBan == 1)
                    build.append("1 (um) sanitario feminino, ");
                else if (fBan > 1)
                    build.append(fBan).append(" sanitários femininos, ");

                if (famBan == 1)
                    build.append("1 (um) sanitario familiar, ");
                else if (famBan > 1)
                    build.append(famBan).append(" sanitários familiar, ");

                if (infBan == 1)
                    build.append("1 (um) sanitario infantil, ");
                else if (infBan > 1)
                    build.append(infBan).append(" sanitários infantis, ");

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
            schoolObj.put("responsibleVisit", school.getNameResponsibleVisit());

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
            Integer[] schServices = new Integer[7];
            schServices[0] = school.getHasNursery();
            schServices[1] = school.getHasDayCare();
            schServices[2] = school.getHasMaternal();
            schServices[3] = school.getHasPreschool();
            schServices[4] = school.getHasElementaryMiddle();
            schServices[5] = school.getHasHighSchool();
            schServices[6] = school.getHasEja();

            for (Integer sch : schServices) {
                if (sch != null)
                    schServQnt += sch;
            }

            if (schServices[0] != null && schServices[0].equals(1) && schCounter <= schServQnt) {
                services.append("Berçário");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[1] != null && schServices[1].equals(1) && schCounter <= schServQnt) {
                services.append("Creche");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[2] != null && schServices[2].equals(1) && schCounter <= schServQnt) {
                services.append("Maternal");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[3] != null && schServices[3].equals(1) && schCounter <= schServQnt) {
                services.append("Pré-Escola");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[4] != null && schServices[4].equals(1) && schCounter <= schServQnt) {
                services.append("Ensino Fundamental");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[5] != null && schServices[5].equals(1) && schCounter <= schServQnt) {
                services.append("Ensino Médio");
                schoolServices(services, schCounter, schServQnt);
                schCounter++;
            }
            if (schServices[6] != null && schServices[6].equals(1) && schCounter <= schServQnt) {
                services.append("E.J.A");
                schoolServices(services, schCounter, schServQnt);
            }
            schoolObj.put("schoolServices", services.toString());

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

    public List<BlackboardEntry> getBoardList() {
        return boardList;
    }

    public List<CounterEntry> getCounterList() {
        return counterList;
    }

    public List<DoorEntry> getDoorList() {
        return doorList;
    }

    public List<FreeSpaceEntry> getFreeList() {
        return freeList;
    }

    public List<SwitchEntry> getSwitchList() {
        return switchList;
    }

    public List<TableEntry> getTableList() {
        return tableList;
    }

    public List<WindowEntry> getWindowList() {
        return windowList;
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

    public List<ParkingLotElderlyEntry> getElderList() {
        return elderList;
    }

    public List<ParkingLotPCDEntry> getPcdList() {
        return pcdList;
    }

    public List<WaterFountainEntry> getRoomWater() {
        return roomWater;
    }
}
