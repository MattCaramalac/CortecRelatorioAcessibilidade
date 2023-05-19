package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.EquipmentEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.report.Components.BlackboardAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.CounterAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.DoorAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.EquipmentAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.FreeSpaceAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.RampAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.StairsAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.SwitchAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.TableAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.WindowAnalysis;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;
import java.util.List;

public class RoomAnalysis implements StandardMeasurements, TagInterface {

    private static int check;

    public static void roomVerification(List<BlockSpaceEntry> blockList, List<RoomEntry> roomList, List<DoorEntry> doorList, List<DoorLockEntry> doorLockList,
                                        List<SwitchEntry> switchList, List<WindowEntry> winList, List<TableEntry> tableList, List<BlackboardEntry> bList,
                                        List<FreeSpaceEntry> fsList, List<RampStairsEntry> rStRoom, List<RampStairsFlightEntry> rStFlight,
                                        List<RampStairsRailingEntry> rStRail, List<RampStairsHandrailEntry> rStHandrail, List<CounterEntry> counterList,
                                        List<WaterFountainEntry> waterList, List<EquipmentEntry> equipList) {

        int helpRoom = 0;

        for (BlockSpaceEntry block : blockList) {
            int blockID = block.getBlockSpaceID();

            for (RoomEntry room : roomList) {
                check = 0;
                AmbientAnalysis.err = false;
                List<String> roomIrr = new ArrayList<>();
                if (room.getBlockID() == blockID) {
                    roomIrr = checkRoomIrregularities(room, doorList, doorLockList, switchList, winList, tableList, bList, fsList, rStRoom,
                            rStFlight, rStRail, rStHandrail, counterList, waterList, equipList);
                }

                String rType = roomTyping(room.getRoomType());

//            Áreas de Apoio = 2
                if (block.getBlockSpaceType() == 2) {
                    if (check > 0)
                        AmbientAnalysis.checkHelpAreaHeader();

                    if (AmbientAnalysis.err) {
                        AmbientAnalysis.helpRoomList.add(rType + room.getRoomLocation() + ", com as seguintes irregularidades: ");
                        AmbientAnalysis.helpRoomIrregular.put(helpRoom, roomIrr);
                        helpRoom++;
                    }
                }
            }
        }

        if (AmbientAnalysis.helpRoomList.size() == 0)
            AmbientAnalysis.helpRoomList.add("Não há cadastro de ambientes de apoio com irregularidades;");
    }

    public static void blockRoomVerification(Integer blockID, List<RoomEntry> roomList, List<DoorEntry> doorList, List<DoorLockEntry> doorLockList,
                                             List<SwitchEntry> switchList, List<WindowEntry> winList, List<TableEntry> tableList, List<BlackboardEntry> bList,
                                             List<FreeSpaceEntry> fsList, List<RampStairsEntry> rStRoom, List<RampStairsFlightEntry> rStFlight,
                                             List<RampStairsRailingEntry> rStRail, List<RampStairsHandrailEntry> rStHandrail, List<CounterEntry> counterList,
                                             List<WaterFountainEntry> waterList, List<EquipmentEntry> equipList) {

        int blockRoom = 0;

        for (int i = 0; i < roomList.size(); i++) {
            check = 0;
            AmbientAnalysis.err = false;
            List<String> roomIrr = new ArrayList<>();
            RoomEntry room = roomList.get(i);
            if (room.getBlockID() == blockID)
                roomIrr = checkRoomIrregularities(room, doorList, doorLockList, switchList, winList, tableList, bList, fsList, rStRoom,
                        rStFlight, rStRail, rStHandrail, counterList, waterList, equipList);

            String rType = roomTyping(room.getRoomType());

            if (check > 0) {
                AmbientAnalysis.blockRoomList.add(rType + room.getRoomLocation() + ", com as seguintes irregularidades: ");
                AmbientAnalysis.blockRoomIrregular.put(blockRoom, roomIrr);
                blockRoom++;
            }
        }
    }

    public static List<String> checkRoomIrregularities(RoomEntry room, List<DoorEntry> doorList, List<DoorLockEntry> doorLockList,
                                                       List<SwitchEntry> switchList, List<WindowEntry> winList, List<TableEntry> tableList, List<BlackboardEntry> bList,
                                                       List<FreeSpaceEntry> fsList, List<RampStairsEntry> rStRoom, List<RampStairsFlightEntry> rStFlight,
                                                       List<RampStairsRailingEntry> rStRail, List<RampStairsHandrailEntry> rStHandrail, List<CounterEntry> counterList,
                                                       List<WaterFountainEntry> waterList, List<EquipmentEntry> equipList) {
        List<String> roomIrr = new ArrayList<>();
        if (room.getRoomType() != NUM_OTHER || (room.getRoomType() == NUM_OTHER && room.getIsWorkRoom() == 0)) {
            if (room.getRoomHasVertSing() == 0) {
                check++;
                roomIrr.add("ausência de sinalização visual vertical;");
            } else if (room.getRoomHasVertSing() == 1 && room.getRoomVertSignObs() != null) {
                check++;
                roomIrr.add("presença de sinalização visual vertical com as seguintes observações: " + room.getRoomVertSignObs() + ";");
            }

            if (room.getHasTactSign() == 0) {
                check++;
                roomIrr.add("ausência de sinalização tátil");
            } else if (room.getHasTactSign() == 1) {
                if (room.getTactSignHeight() != null && room.getTactSignHeight() < doorTactSignMinHeight) {
                    check++;
                    roomIrr.add("a altura de instalação da sinalização tátil é inferior à " + doorTactSignMinHeight + " m;");
                } else if (room.getTactSignHeight() != null &&  room.getTactSignHeight() > doorTactSignMaxHeight) {
                    check++;
                    roomIrr.add("a altura de instalação da sinalização tátil é superior à " + doorTactSignMaxHeight + " m;");
                } else {
                    if (room.getTactSignIncl() != null && room.getTactSignIncl() < doorTactSignMinAngle) {
                        check++;
                        roomIrr.add("o ângulo do plano inclinado da sinalização tátil é inferior à " + doorTactSignMinAngle + "º;");
                    } else if (room.getTactSignIncl() != null && room.getTactSignIncl() > doorTactSignMaxAngle) {
                        check++;
                        roomIrr.add("o ângulo do plano inclinado da sinalização tátil é superior à " + doorTactSignMaxAngle + "º;");
                    }
                }
            }
            if (room.getTactSignObs() != null && room.getTactSignObs().length() > 0)
                roomIrr.add("as seguintes observações devem ser feitas sobre a sinalização tátil da porta: " + room.getTactSignObs().trim() + ";");

            if (room.getRoomHasLooseCarpet() == 1) {
                check++;
                roomIrr.add("presença de tapete e/ou capacho soltos");
            } else if (room.getRoomHasLooseCarpet() == 1 && room.getLooseCarpetObs() != null) {
                check++;
                roomIrr.add("presença de tapete e/ou capacho soltos, com as seguintes observações: " + room.getLooseCarpetObs() + ";");
            }

            if (room.getRoomAccessFloor() == 0) {
                check++;
                if (room.getAccessFloorObs() != null && room.getAccessFloorObs().length() > 0)
                    roomIrr.add("o piso não pode ser considerado acessível, pois " + room.getAccessFloorObs() + ";");
            }

            if (doorList.size() > 0) {
                List<String> doorRegister = DoorAnalysis.doorVerification(room.getRoomID(), doorList, doorLockList);
                if (doorRegister.size() > 0) {
                    check++;
                    roomIrr.addAll(doorRegister);
                }
            }

            if (switchList.size() > 0) {
                List<String> swErrs = SwitchAnalysis.switchList(room.getRoomID(), switchList);
                if (swErrs.size() > 0) {
                    check++;
                    roomIrr.addAll(swErrs);
                }
            }

            if (winList.size() > 0) {
                List<String> winError = WindowAnalysis.winTextList(room.getRoomID(), winList);
                if (winError.size() > 0) {
                    check++;
                    roomIrr.addAll(winError);
                }
            }

            if (tableList.size() > 0) {
                List<String> tableErrors = TableAnalysis.tableTextList(room.getRoomID(), tableList);
                if (tableErrors.size() > 0) {
                    check++;
                    roomIrr.addAll(tableErrors);
                }
            }

            if (bList.size() > 0) {
                List<String> boardErrors = BlackboardAnalysis.boardTextList(room.getRoomID(), bList);
                if (boardErrors.size() > 0) {
                    check++;
                    roomIrr.addAll(boardErrors);
                }
            }

            if (fsList.size() > 0) {
                List<String> fsError = FreeSpaceAnalysis.spaceTextList(room.getRoomID(), fsList);
                if (fsError.size() > 0) {
                    check++;
                    roomIrr.addAll(fsError);
                }
            }

            if (rStRoom.size() > 0) {
                List<String> stAnalysis = StairsAnalysis.stairsVerification(room.getRoomID(), rStRoom, rStFlight, rStRail, rStHandrail);
                if (stAnalysis.size() > 0) {
                    check++;
                    roomIrr.addAll(stAnalysis);
                }

                List<String> rampAnalysis = RampAnalysis.rampVerification(room.getRoomID(), rStRoom, rStFlight, rStRail, rStHandrail);
                if (rampAnalysis.size() > 0) {
                    check++;
                    roomIrr.addAll(rampAnalysis);
                }

            }

            if (counterList.size() > 0) {
                List<String> counterError = CounterAnalysis.counterTextList(room.getRoomID(), counterList);
                if (counterError.size() > 0) {
                    check++;
                    roomIrr.addAll(counterError);
                }
            }

            if (waterList.size() > 0) {
                List<String> waterError = FountainAnalysis.roomFountainVerification(room.getRoomID(), waterList);
                if (waterError.size() > 0) {
                    check++;
                    roomIrr.addAll(waterError);
                }
            }

            if (equipList.size() > 0) {
                List<String> equipError = EquipmentAnalysis.equipList(room.getRoomID(), equipList);
                if (equipError.size() > 0) {
                    check++;
                    roomIrr.addAll(equipError);
                }
            }

            if (room.getRoomType() == NUM_LIB) {
                if (room.getLibDistShelvesOK() != null && room.getLibDistShelvesOK() == 0) {
                    check++;
                    roomIrr.add("a distância entre as estantes de livros é inferior à 0.90 m;");
                }
                if (room.getLibHasLongCorridors() != null && room.getLibHasLongCorridors() == 1 &&
                        room.getLibHasManeuverArea() != null && room.getLibHasManeuverArea() == 0) {
                    check++;
                    roomIrr.add("existência de estantes com pelo menos 15 metros de comprimento sem a presença de bolsões para manobras de PCR;");
                }
                if (room.getLibHasPC() != null && room.getLibHasPC() == 1 &&
                        room.getLibHasAccessPC() != null && room.getLibHasAccessPC() == 0) {
                    check++;
                    roomIrr.add("menos de 5% dos terminais de consulta e/ou computadores presentes na biblioteca são acessíveis para PCR e PMR;");
                }
            }

            if (room.getRoomType() == NUM_SEC) {
                if (room.getSecHasFixedSeats() == 1) {
                    if (room.getSecHasPcrSpace() == 0) {
                        check++;
                        roomIrr.add("a secretaria não apresenta espaço sinalizado para PCR ao lado dos bancos fixos instalados no local;");
                    }
                    else {
                        if (room.getSecPcrSpaceWidth() < widthPCR) {
                            check++;
                            roomIrr.add("o espaço reservado para PCR possui largura inferior à " + widthPCR + " m;");
                        }
                        if (room.getSecPcrSpaceDepth() < lengthPCR) {
                            check++;
                            roomIrr.add("o espaço reservado para PCR possui comprimento inferior à " + lengthPCR + " m;");
                        }
                    }
                }
                if (room.getSecPCRSpaceObs() != null && room.getSecPCRSpaceObs().length() > 0) {
                    check++;
                    roomIrr.add("as seguintes observações podem ser realizadas sobre o espaço reservado para PCR: " + room.getSecPCRSpaceObs() + ";");
                }
            }

            if (room.getRoomType() == NUM_COORD || room.getRoomType() == NUM_DIR || room.getRoomType() == NUM_TEACHER || room.getRoomType() == NUM_SEC) {
                if (room.getHasIntercom() != null && room.getHasIntercom() == 1) {
                    if (room.getIntercomHeight() < minIntTelHeight) {
                        check++;
                        if (room.getIntercomObs() != null)
                            roomIrr.add("a altura do interfone é inferior à " + minIntTelHeight + " m, com as seguintes observações: "
                                    + room.getIntercomObs() + ";");
                        else
                            roomIrr.add("a altura do interfone é inferior à " + minIntTelHeight + " m;");
                    } else if (room.getIntercomHeight() > maxIntTelHeight) {
                        check++;
                        if (room.getIntercomObs() != null)
                            roomIrr.add("a altura do interfone é superior à " + maxIntTelHeight + " m, com as seguintes observações: "
                                    + room.getIntercomObs() + ";");
                        else
                            roomIrr.add("a altura do interfone é superior à " + minIntTelHeight + " m;");
                    } else {
                        if (room.getIntercomObs() != null) {
                            roomIrr.add("a altura do interfone está de acordo com a norma, porém possui as seguintes observações: "
                                    + room.getIntercomObs() + ";");
                        }
                    }
                }
            }

            if (room.getHasBioClock() != null && room.getHasBioClock() == 1) {
                if (room.getBioClockHeight() < minPrecisionCom) {
                    check++;
                    if (room.getBioClockObs() != null)
                        roomIrr.add("a altura do ponto biométrico é inferior à " + minPrecisionCom + " m, com as seguintes observações: "
                                + room.getBioClockObs() + ";");
                    else
                        roomIrr.add("a altura do ponto biométrico é inferior à " + minPrecisionCom + " m;");
                } else if (room.getBioClockHeight() > maxPrecisionCom) {
                    check++;
                    if (room.getBioClockObs() != null)
                        roomIrr.add("a altura do ponto biométrico é superior à " + maxPrecisionCom + " m, com as seguintes observações: "
                                + room.getBioClockObs() + ";");
                    else
                        roomIrr.add("a altura do ponto biométrico é superior à " + maxPrecisionCom + " m;");
                } else {
                    if (room.getBioClockObs() != null)
                        roomIrr.add("a altura do ponto biométrico está de acordo com a norma, porém possui as seguintes observações: "
                                + room.getBioClockObs() + ";");
                }
            }
        }


        if (room.getRoomObs() != null && room.getRoomObs().length() > 0) {
            check++;
            roomIrr.add("as seguintes observações podem ser feitas sobre este ambiente: " + room.getRoomObs() +";");
        }

        if (room.getRoomPhoto() != null) {
            check++;
            roomIrr.add("Registros fotográficos ambiente: " + room.getRoomPhoto());
        }

        return roomIrr;
    }

    public static String roomTyping(int i) {
        switch (i) {
            case NUM_LIB:
                return "Biblioteca - ";
            case NUM_COORD:
                return "Coordenação - ";
            case NUM_DIR:
                return "Diretoria - ";
            case NUM_CAFE:
                return "Refeitório - ";
            case NUM_CLASS:
                return "Sala de Aula - ";
            case NUM_TECH:
                return "Sala de Tecnologia -";
            case NUM_RESOURCE:
                return "Sala de Recursos - ";
            case NUM_TEACHER:
                return "Sala dos Professores - ";
            case NUM_SEC:
                return "Secretaria - ";
            default:
                return "";
        }
    }
}

