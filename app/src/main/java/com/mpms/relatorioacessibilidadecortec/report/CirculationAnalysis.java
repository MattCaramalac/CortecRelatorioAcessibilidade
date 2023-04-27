package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CirculationEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.EquipmentEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.FallProtectionEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SingleStepEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;
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
import com.mpms.relatorioacessibilidadecortec.report.Components.SingleStepAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.SlopeAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.StairsAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.SwitchAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.TableAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.WindowAnalysis;

import java.util.ArrayList;
import java.util.List;

public class CirculationAnalysis implements StandardMeasurements {

    private static int check;

    public static void circVerification(List<CirculationEntry> circList, List<DoorEntry> circDoorList, List<DoorLockEntry> circLockList, List<SwitchEntry> circSwitchList,
                                        List<WindowEntry> circWindowList, List<TableEntry> circTableList, List<BlackboardEntry> circBoardList, List<FreeSpaceEntry> circFreeSpList,
                                        List<SingleStepEntry> circStepList, List<SlopeEntry> circSlopeList, List<WaterFountainEntry> circFountainList, List<EquipmentEntry> circEquipList,
                                        List<CounterEntry> circCounterList, List<FallProtectionEntry> circProtectList, List<RampStairsEntry> circRampStairsList,
                                        List<RampStairsFlightEntry> circFlightList, List<RampStairsRailingEntry> circRailList, List<RampStairsHandrailEntry> circHandList) {

        int order = 0;

        for (CirculationEntry circ : circList) {
            check = 0;
            AmbientAnalysis.err = false;
            List<String> circIrr = new ArrayList<>();
            circIrr = checkCircIrregularities(circ, circDoorList, circLockList, circSwitchList, circWindowList, circTableList, circBoardList, circFreeSpList, circStepList, circSlopeList,
                    circFountainList, circEquipList, circCounterList, circProtectList, circRampStairsList, circFlightList, circRailList, circHandList);

            if (check > 0) {
                AmbientAnalysis.circList.add("Circulação localizada em " + circ.getCircLocation() + ", com as seguintes irregularidades: ");
                AmbientAnalysis.circIrregular.put(order, circIrr);
            }
        }

        if (AmbientAnalysis.circList.size() == 0)
            AmbientAnalysis.circList.add("Não há cadastro de circulações com irregularidades;");
    }

//    TODO - Criar análise de proteção contra quedas
    public static List<String> checkCircIrregularities(CirculationEntry circ, List<DoorEntry> circDoorList, List<DoorLockEntry> circLockList, List<SwitchEntry> circSwitchList,
                                                       List<WindowEntry> circWindowList, List<TableEntry> circTableList, List<BlackboardEntry> circBoardList, List<FreeSpaceEntry> circFreeSpList,
                                                       List<SingleStepEntry> circStepList, List<SlopeEntry> circSlopeList, List<WaterFountainEntry> circFountainList, List<EquipmentEntry> circEquipList,
                                                       List<CounterEntry> circCounterList, List<FallProtectionEntry> circProtectList, List<RampStairsEntry> circRampStairsList,
                                                       List<RampStairsFlightEntry> circFlightList, List<RampStairsRailingEntry> circRailList, List<RampStairsHandrailEntry> circHandList) {
        List<String> circIrr = new ArrayList<>();

        if (circ.getHasVertSign() == 0) {
            check++;
            circIrr.add("ausência de sinalização visual vertical;");
        }

        if (circ.getHasLooseCarpet() == 1) {
            check++;
            circIrr.add("presença de tapete e/ou capacho solto no piso;");
        }

        if (circ.getAccessFloor() == 0) {
            check++;
            if (circ.getAccessFloorObs() != null && circ.getAccessFloorObs().length() > 0)
                circIrr.add("o piso da não pode ser considerado acessível devido aos seguintes fatores: " + circ.getAccessFloorObs() + ";");
        }

        if (circ.getHasIntercom() == 1) {
            if (circ.getIntercomHeight() < minIntTelHeight) {
                check++;
                circIrr.add("presença de interfone com altura de instalação inferior à " + minIntTelHeight + " m;");
            } else if (circ.getIntercomHeight() > maxIntTelHeight) {
                check++;
                circIrr.add("presença de interfone com altura de instalação superior à " + maxIntTelHeight + " m;");
            }
        }

        if (circ.getHasBioClock() == 1) {
            if (circ.getBioClockHeight() < minPrecisionCom) {
                check++;
                circIrr.add("presença de equipamento de ponto biométrico com altura de instalação inferior à " + minPrecisionCom + " m;");
            } else if (circ.getBioClockHeight() > maxPrecisionCom) {
                check++;
                circIrr.add("presença de equipamento de ponto biométrico com altura de instalação superior à " + maxPrecisionCom + " m;");
            }
        }

        if (circDoorList.size() > 0) {
            List<String> doorRegister = DoorAnalysis.doorCircVerification(circ.getCircID(), circDoorList, circLockList);
            if (doorRegister.size() > 0) {
                check++;
                circIrr.addAll(doorRegister);
            }
        }

        if (circSwitchList.size() > 0) {
            List<String> swErrs = SwitchAnalysis.circSwitchList(circ.getCircID(), circSwitchList);
            if (swErrs.size() > 0) {
                check++;
                circIrr.addAll(swErrs);
            }
        }

        if (circWindowList.size() > 0) {
            List<String> winError = WindowAnalysis.circWinList(circ.getCircID(), circWindowList);
            if (winError.size() > 0) {
                check++;
                circIrr.addAll(winError);
            }
        }

        if (circTableList.size() > 0) {
            List<String> tableErrors = TableAnalysis.circTableList(circ.getCircID(), circTableList);
            if (tableErrors.size() > 0) {
                check++;
                circIrr.addAll(tableErrors);
            }
        }

        if (circBoardList.size() > 0) {
            List<String> boardErrors = BlackboardAnalysis.circBoardList(circ.getCircID(), circBoardList);
            if (boardErrors.size() > 0) {
                check++;
                circIrr.addAll(boardErrors);
            }
        }

        if (circFreeSpList.size() > 0) {
            List<String> fsError = FreeSpaceAnalysis.circSpaceTextList(circ.getCircID(), circFreeSpList);
            if (fsError.size() > 0) {
                check++;
                circIrr.addAll(fsError);
            }
        }

        if (circRampStairsList.size() > 0) {
            List<String> stAnalysis = StairsAnalysis.stairsVerification(circ.getCircID(), circRampStairsList, circFlightList, circRailList, circHandList);
            if (stAnalysis.size() > 0) {
                check++;
                circIrr.addAll(stAnalysis);
            }

            List<String> rampAnalysis = RampAnalysis.rampVerification(circ.getCircID(), circRampStairsList, circFlightList, circRailList, circHandList);
            if (rampAnalysis.size() > 0) {
                check++;
                circIrr.addAll(rampAnalysis);
            }

        }

        if (circCounterList.size() > 0) {
            List<String> counterError = CounterAnalysis.circCounterList(circ.getCircID(), circCounterList);
            if (counterError.size() > 0) {
                check++;
                circIrr.addAll(counterError);
            }
        }

        if (circFountainList.size() > 0) {
            List<String> waterError = FountainAnalysis.roomFountainVerification(circ.getCircID(), circFountainList);
            if (waterError.size() > 0) {
                check++;
                circIrr.addAll(waterError);
            }
        }

        if (circEquipList.size() > 0) {
            List<String> equipError = EquipmentAnalysis.equipList(circ.getCircID(), circEquipList);
            if (equipError.size() > 0) {
                check++;
                circIrr.addAll(equipError);
            }
        }

        if (circSlopeList.size() > 0) {
            List<String> slopeError = SlopeAnalysis.circSlopeList(circ.getCircID(), circSlopeList);
            if (slopeError.size() > 0) {
                check++;
                circIrr.addAll(slopeError);
            }
        }

        if (circStepList.size() > 0) {
            List<String> stepError = SingleStepAnalysis.circStepList(circ.getCircID(), circStepList);
            if (stepError.size() > 0) {
                check++;
                circIrr.addAll(stepError);
            }
        }

        if (circ.getCircObs() != null && circ.getCircObs().length() > 0) {
            check++;
            circIrr.add("as seguintes observações podem ser feitas sobre a circulação em questão: " + circ.getCircObs() + ";");
        }

        if (circ.getCircPhoto() != null && circ.getCircPhoto().length() > 0) {
            check++;
            circIrr.add("Registros fotográficos do local: " + circ.getCircPhoto() + ";");
        }

        return circIrr;
    }
}
