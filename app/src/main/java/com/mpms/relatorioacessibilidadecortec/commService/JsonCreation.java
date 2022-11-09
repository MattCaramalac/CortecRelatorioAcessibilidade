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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonCreation {

    private final SchoolEntry school;
    private final int qntBlocks;
    private boolean hasHelpSpace;
    private final int extPark;
    private final int intPark;
    private List<BlockSpaceEntry> blockList;
    private List<RoomEntry> roomList;
    private List<ExternalAccess> extList;
    private List<PlaygroundEntry> playList;
    private List<ParkingLotEntry> parkList;
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

    public JsonCreation(SchoolEntry school, List<BlockSpaceEntry> blockList, List<RoomEntry> roomList, List<ExternalAccess> extList, List<ParkingLotEntry> parkList,
                        List<PlaygroundEntry> playList, List<RestroomEntry> restList, List<SidewalkEntry> sideList, List<WaterFountainEntry> fountList,
                        List<RampStairsEntry> roomStRaList, List<RampStairsEntry> sideStRaList, List<RampStairsEntry> extStRaList, List<RampStairsEntry> parkStRaList,
                        List<BlackboardEntry> boardList, List<CounterEntry> counterList, List<DoorEntry> doorList, List<FreeSpaceEntry> freeList, List<SwitchEntry> switchList,
                        List<TableEntry> tableList, List<WindowEntry> windowList, List<DoorLockEntry> doorLockList, List<DoorLockEntry> gateLockList, List<GateObsEntry> gateList,
                        List<PayPhoneEntry> extPhoneList, List<PayPhoneEntry> sidePhoneList, List<SidewalkSlopeEntry> slopeList, List<RampStairsFlightEntry> roomFlightList,
                        List<RampStairsFlightEntry> sideFlightList, List<RampStairsFlightEntry> extFlightList, List<RampStairsFlightEntry> parkFlightList,
                        List<RampStairsRailingEntry> roomRailList, List<RampStairsRailingEntry> sideRailList, List<RampStairsRailingEntry> extRailList,
                        List<RampStairsRailingEntry> parkRailList, List<RampStairsHandrailEntry> roomHandList, List<RampStairsHandrailEntry> sideHandList,
                        List<RampStairsHandrailEntry> extHandList, List<RampStairsHandrailEntry> parkHandList, int qntBlocks, boolean hasHelpSpace, int extPark, int intPark) {
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
        this.qntBlocks = qntBlocks;
        this.hasHelpSpace = hasHelpSpace;
        this.extPark = extPark;
        this.intPark = intPark;
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
                                build.append(entry.getRoomDescription()).append(", ");
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
            }
            else if (block.getBlockSpaceType() == 2) {
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
                    build.append("1 (um) estacionamento interno");
                else if (park > 1)
                    build.append(park).append(" estacionamentos internos");

                if (play == 1)
                    build.append("Playground, ");
                else if (play > 1)
                    build.append(play).append(" Playgrounds, ");

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
            blockText.append("constituído por ");
            if (qntBlocks == 1)
                blockText.append("1 (um) bloco arquitetônico");
            else
                blockText.append(qntBlocks).append(" blocos arquitetônicos");
            if (hasHelpSpace)
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

    public List<GateObsEntry> getGateList() {
        return gateList;
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
}
