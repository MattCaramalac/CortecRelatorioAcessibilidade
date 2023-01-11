package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.report.Components.BlackboardAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.CounterAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.DoorAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.FreeSpaceAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.RampAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.StairsAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.SwitchAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.TableAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.WindowAnalysis;

import java.util.ArrayList;
import java.util.List;

public class RoomAnalysis implements StandardMeasurements {

    private static int check;

    public static void roomVerification(List<BlockSpaceEntry> blockList, List<RoomEntry> roomList, List<DoorEntry> doorList, List<DoorLockEntry> doorLockList,
                                        List<SwitchEntry> switchList, List<WindowEntry> winList, List<TableEntry> tableList, List<BlackboardEntry> bList,
                                        List<FreeSpaceEntry> fsList, List<RampStairsEntry> rStRoom, List<RampStairsFlightEntry> rStFlight,
                                        List<RampStairsRailingEntry> rStRail, List<RampStairsHandrailEntry> rStHandrail, List<CounterEntry> counterList) {

        int extRoom = 0;
        int helpRoom = 0;

        for (BlockSpaceEntry block : blockList) {
            int blockID = block.getBlockSpaceID();

            for (int i = 0; i < roomList.size(); i++) {
                check = 0;
                AmbientAnalysis.err = false;
                List<String> roomIrr = new ArrayList<>();
                RoomEntry room = roomList.get(i);
                if (room.getBlockID() == blockID) {
                    roomIrr = checkRoomIrregularities(room, doorList, doorLockList, switchList, winList, tableList, bList, fsList, rStRoom,
                            rStFlight, rStRail, rStHandrail, counterList);
                }

//            Area externa = 1
                if (block.getBlockSpaceType() == 1) {

                    if (check > 0)
                        AmbientAnalysis.checkHasAccessHeader();

                    if (AmbientAnalysis.err) {
                        AmbientAnalysis.extRoomList.add(room.getRoomLocation() + ", com as seguintes irregularidades: ");
                        AmbientAnalysis.extRoomIrregular.put(extRoom, roomIrr);
                        extRoom++;
                    }

                }
//            Áreas de Apoio = 2
                else if (block.getBlockSpaceType() == 2) {
                    if (check > 0)
                        AmbientAnalysis.checkHelpAreaHeader();

                    if (AmbientAnalysis.err) {
                        AmbientAnalysis.helpRoomList.add(room.getRoomLocation() + ", com as seguintes irregularidades: ");
                        AmbientAnalysis.helpRoomIrregular.put(helpRoom, roomIrr);
                        helpRoom++;
                    }
                }


            }
        }
    }

    public static void blockRoomVerification(Integer blockID, List<RoomEntry> roomList, List<DoorEntry> doorList, List<DoorLockEntry> doorLockList,
                                             List<SwitchEntry> switchList, List<WindowEntry> winList, List<TableEntry> tableList, List<BlackboardEntry> bList,
                                             List<FreeSpaceEntry> fsList, List<RampStairsEntry> rStRoom, List<RampStairsFlightEntry> rStFlight,
                                             List<RampStairsRailingEntry> rStRail, List<RampStairsHandrailEntry> rStHandrail, List<CounterEntry> counterList) {

        int blockRoom = 0;

        for (int i = 0; i < roomList.size(); i++) {
            check = 0;
            AmbientAnalysis.err = false;
            List<String> roomIrr = new ArrayList<>();
            RoomEntry room = roomList.get(i);
            if (room.getBlockID() == blockID)
                roomIrr = checkRoomIrregularities(room, doorList, doorLockList, switchList, winList, tableList, bList, fsList, rStRoom,
                        rStFlight, rStRail, rStHandrail, counterList);

            if (check > 0) {
                AmbientAnalysis.blockRoomList.add(room.getRoomLocation() + ", com as seguintes irregularidades: ");
                AmbientAnalysis.blockRoomIrregular.put(blockRoom, roomIrr);
                blockRoom++;
            }
        }
    }

    public static List<String> checkRoomIrregularities(RoomEntry room, List<DoorEntry> doorList, List<DoorLockEntry> doorLockList,
                                                       List<SwitchEntry> switchList, List<WindowEntry> winList, List<TableEntry> tableList, List<BlackboardEntry> bList,
                                                       List<FreeSpaceEntry> fsList, List<RampStairsEntry> rStRoom, List<RampStairsFlightEntry> rStFlight,
                                                       List<RampStairsRailingEntry> rStRail, List<RampStairsHandrailEntry> rStHandrail, List<CounterEntry> counterList) {
        List<String> roomIrr = new ArrayList<>();
        if (room.getRoomHasVertSing() == 0) {
            check++;
            roomIrr.add("ausência de sinalização visual vertical;");
        } else if (room.getRoomHasVertSing() == 1 && room.getRoomVertSignObs() != null) {
            check++;
            roomIrr.add("presença de sinalização visual vertical com as seguintes observações: " + room.getRoomVertSignObs() + ";");
        }

        if (room.getRoomHasLooseCarpet() == 1) {
            check++;
            roomIrr.add("presença de tapete e/ou capacho soltos");
        } else if (room.getRoomHasLooseCarpet() == 1 && room.getLooseCarpetObs() != null) {
            check++;
            roomIrr.add("presença de tapete e/ou capacho soltos, com as seguintes observações: " + room.getLooseCarpetObs() + ";");
        }

        if (room.getRoomAccessFloor() == 0) {
            check++;
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

        if (room.getHasBellControl() != null && room.getHasBellControl() == 1) {
            if (room.getBellControlHeight() < minDbHeight) {
                check++;
                if (room.getBellControlObs() != null)
                    roomIrr.add("a altura do comando do sinal sonoro é inferior à " + minDbHeight + " m, com as seguintes observações: "
                            + room.getBellControlObs() + ";");
                else
                    roomIrr.add("a altura do comando do sinal sonoro é inferior à " + minDbHeight + " m;");
            } else if (room.getBellControlHeight() > maxDbHeight) {
                check++;
                if (room.getBellControlObs() != null)
                    roomIrr.add("a altura do comando do sinal sonoro é superior à " + minDbHeight + " m, com as seguintes observações: "
                            + room.getBellControlObs() + ";");
                else
                    roomIrr.add("a altura do comando do sinal sonoro é é superior à " + minDbHeight + " m;");
            } else {
                if (room.getBellControlObs() != null) {
                    check++;
                    roomIrr.add("a altura do comando do sinal sonoro está de acordo com a norma, porém possui as seguintes observações: "
                            + room.getBellControlObs() + ";");
                }
            }
        }

        if (room.getHasInternalPhone() != null && room.getHasInternalPhone() == 1) {
            if (room.getInternalPhoneHeight() < minIntTelHeight) {
                check++;
                if (room.getInternalPhoneObs() != null)
                    roomIrr.add("a altura do interfone é inferior à " + minIntTelHeight + " m, com as seguintes observações: "
                            + room.getInternalPhoneObs() + ";");
                else
                    roomIrr.add("a altura do interfone é inferior à " + minIntTelHeight + " m;");
            } else if (room.getInternalPhoneHeight() > maxIntTelHeight) {
                check++;
                if (room.getInternalPhoneObs() != null)
                    roomIrr.add("a altura do interfone é superior à " + maxIntTelHeight + " m, com as seguintes observações: "
                            + room.getInternalPhoneObs() + ";");
                else
                    roomIrr.add("a altura do interfone é superior à " + minIntTelHeight + " m;");
            } else {
                if (room.getInternalPhoneObs() != null) {
                    roomIrr.add("a altura do interfone está de acordo com a norma, porém possui as seguintes observações: "
                            + room.getInternalPhoneObs() + ";");
                }
            }
        }

        if (room.getHasBiometricClock() != null && room.getHasBiometricClock() == 1) {
            if (room.getBiometricClockHeight() < minPrecisionCom) {
                check++;
                if (room.getBiometricClockObs() != null)
                    roomIrr.add("a altura do ponto biométrico é inferior à " + minPrecisionCom + " m, com as seguintes observações: "
                            + room.getBiometricClockObs() + ";");
                else
                    roomIrr.add("a altura do ponto biométrico é inferior à " + minPrecisionCom + " m;");
            } else if (room.getBiometricClockHeight() > maxPrecisionCom) {
                check++;
                if (room.getBiometricClockObs() != null)
                    roomIrr.add("a altura do ponto biométrico é superior à " + maxPrecisionCom + " m, com as seguintes observações: "
                            + room.getBiometricClockObs() + ";");
                else
                    roomIrr.add("a altura do ponto biométrico é superior à " + maxPrecisionCom + " m;");
            } else {
                if (room.getBiometricClockObs() != null)
                    roomIrr.add("a altura do ponto biométrico está de acordo com a norma, porém possui as seguintes observações: "
                            + room.getBiometricClockObs() + ";");
            }
        }
        return roomIrr;
    }
}

