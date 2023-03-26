package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.report.Components.ParkElderAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.ParkPcdAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.RampAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.StairsAnalysis;

import java.util.ArrayList;
import java.util.List;

public class ParkingAnalysis implements StandardMeasurements {

    public static void parkVerification(List<BlockSpaceEntry> blockList, List<ParkingLotEntry> parkList, List<ParkingLotElderlyEntry> elderList, List<ParkingLotPCDEntry> pcdList,
                                 List<RampStairsEntry> rStList, List<RampStairsFlightEntry> rStFlight, List<RampStairsRailingEntry> rStRail,
                                 List<RampStairsHandrailEntry> rStHandrail) {

        int extPark = 0;
        int helpPark = 0;

        for (BlockSpaceEntry block : blockList) {
            int blockID = block.getBlockSpaceID();

            for (int i = 0; i < parkList.size(); i++) {
                int check = 0;
                AmbientAnalysis.err = false;
                List<String> parkIrr = new ArrayList<>();
                ParkingLotEntry park = parkList.get(i);

                if (park.getBlockID() == blockID) {
                    if (park.getHasPCDVacancy() == 0) {
                        check++;
                        parkIrr.add("O estacionamento não possui vaga exclusiva para PCD e PMR");
                    }
                    if (park.getHasElderVacancy() == 0) {
                        check++;
                        parkIrr.add("O estacionamento não possui vaga exclusiva para pessoas idosas");
                    }
                    if (park.getParkingHasStairs() == 1) {
                        List<String> stAnalysis = StairsAnalysis.stairsVerification(park.getParkingID(), rStList, rStFlight, rStRail, rStHandrail);
                        if (stAnalysis.size() > 0) {
                            check++;
                            parkIrr.addAll(stAnalysis);
                        }
                        if (park.getParkingHasRamps() == 1) {
                            List<String> rampAnalysis = RampAnalysis.rampVerification(park.getParkingID(), rStList, rStFlight, rStRail, rStHandrail);
                            if (rampAnalysis.size() > 0) {
                                check++;
                                parkIrr.addAll(rampAnalysis);
                            }
                        }
                    }
                    if (park.getParkAccessFloor() == 0) {
                        check++;
                        parkIrr.add("O estacionamento não possui o piso acessível devido aos seguintes fatores: " + park.getParkFloorObs());
                    }
                    if (park.getParkAccessRoute() == 0) {
                        check++;
                        parkIrr.add("O estacionamento não possui uma rota acessível devido aos seguintes fatores: " + park.getParkAccessRouteObs());
                    }

                    for (int j = 0; j < pcdList.size(); j++) {
                        List<String> pcdErrors = ParkPcdAnalysis.pcdTextList(park.getParkingID(), pcdList);
                        if (pcdErrors.size() > 0) {
                            check++;
                            parkIrr.addAll(pcdErrors);
                        }
                    }

                    for (int j = 0; j < elderList.size(); j++) {
                        List<String> elderErrors = ParkElderAnalysis.elderTextList(park.getParkingID(), elderList);
                        if (elderErrors.size() > 0) {
                            check++;
                            parkIrr.addAll(elderErrors);
                        }
                    }

                    if (park.getParkPhotos() != null) {
                        check++;
                        parkIrr.add("Registros fotográficos estacionamento: " + park.getParkPhotos());
                    }

                    //            Area externa = 1
                    if (block.getBlockSpaceType() == 1) {
                        if (check > 0)
                            AmbientAnalysis.checkHasAccessHeader();
                        if (AmbientAnalysis.err) {
                            AmbientAnalysis.extParkList.add("Estacionamento externo, localizado em " + park.getExtParkLocation() + ", com as seguintes irregularidades: ");
                            AmbientAnalysis.extParkIrregular.put(extPark, parkIrr);
                            extPark++;
                        }
                    }
//            Áreas de Apoio = 2
                    else if (block.getBlockSpaceType() == 2) {
                        if (check > 0)
                            AmbientAnalysis.checkHelpAreaHeader();
                        if (AmbientAnalysis.err) {
                            AmbientAnalysis.helpParkList.add("Estacionamento interno com as seguintes irregularidades: ");
                            AmbientAnalysis.helpParkIrregular.put(helpPark, parkIrr);
                            helpPark++;
                        }
                    }
                }
            }
        }

        if (parkList.size() == 0) {
            AmbientAnalysis.checkHasAccessHeader();
            AmbientAnalysis.extParkList.add("Não há cadastros de estacionamentos externos com irregularidades;");
            AmbientAnalysis.checkHelpAreaHeader();
            AmbientAnalysis.helpParkList.add("Não há cadastros de estacionamentos internos com irregularidades;");
        }
    }
}
