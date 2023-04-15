package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class StairsAnalysis implements StandardMeasurements {

    static boolean irregularStairs;
    static boolean irregularFlight;

    public static List<String> stairsVerification(int ambientID, List<RampStairsEntry> rStList, List<RampStairsFlightEntry> rStFlight, List<RampStairsRailingEntry> rStRail,
                                                  List<RampStairsHandrailEntry> rStHandrail) {

        List<String> stairsText = new ArrayList<>();

        if (rStList.size() > 0) {
            for (RampStairsEntry stairs : rStList) {
                StringBuilder stairsBuilder = new StringBuilder();
                irregularStairs = false;
                String analysis = null;
                if (stairs.getRampStairsIdentifier() == 1) {
                    if (stairs.getExtAccessID() != null && stairs.getExtAccessID() == ambientID ||
                            stairs.getParkingID() != null && stairs.getParkingID() == ambientID ||
                            stairs.getRoomID() != null && stairs.getRoomID() == ambientID ||
                            stairs.getCircID() != null && stairs.getCircID() == ambientID)
                        analysis = flightText(stairs.getRampStairsID(), rStFlight, rStRail, rStHandrail);
                }
                if (analysis != null && analysis.length() != 0) {
                    stairsBuilder.append(analysis);
                    stairsIrregular(stairsBuilder);
                    stairsBuilder.replace(34, 35, stairs.getRampStairsLocation());
                    stairsText.add(stairsBuilder.toString());
                }
            }
        }
        return stairsText;
    }

    public static void stairsIrregular(StringBuilder builder) {
        if (!irregularStairs) {
            irregularStairs = true;
            builder.replace(0, 1, "Presença de escada, localizada em x, com as seguintes irregularidades: "); //34,35
        } else {
            builder.append(", ");
        }
    }

    public static void flightIrregular(StringBuilder builder) {
        if (!irregularFlight) {
            irregularFlight = true;
            builder.append("No lance de nºx: "); //14,15
        } else {
            builder.append(", ");
        }
    }

    public static String flightText(int rStID, List<RampStairsFlightEntry> rStFlight, List<RampStairsRailingEntry> rStRail,
                                    List<RampStairsHandrailEntry> rStHandrail) {
        StringBuilder flightText = new StringBuilder();
        for (RampStairsFlightEntry flight : rStFlight) {
            irregularFlight = false;
            StringBuilder builder = new StringBuilder();
            if (flight.getRampStairsID() == rStID) {
                if (flight.getFlightWidth() < minFlightWidth) {
                    flightIrregular(builder);
                    builder.append("a largura livre mínima inferior à 1,20 m");
                }
                if (flight.getRampHeight() > maxHeightUntilInterLevel) {
                    flightIrregular(builder);
                    builder.append("a altura do desnível é superior ao máximo permitido de " + maxHeightUntilInterLevel + " m");
                }

                if (flight.getStairMirror1() != null && flight.getStairMirror1() < minStepHeight ||
                        flight.getStairMirror2() != null && flight.getStairMirror2() < minStepHeight ||
                        flight.getStairMirror3() != null && flight.getStairMirror3() < minStepHeight ||
                        flight.getStairMirror4() != null && flight.getStairMirror4() < minStepHeight) {
                    flightIrregular(builder);
                    builder.append("a altura dos espelhos é inferior ao mínimo permitido de ").append(minStepHeight).append(" m");
                } else if (flight.getStairMirror1() != null && flight.getStairMirror1() > maxStepHeight ||
                        flight.getStairMirror2() != null && flight.getStairMirror2() > maxStepHeight ||
                        flight.getStairMirror3() != null && flight.getStairMirror3() > maxStepHeight ||
                        flight.getStairMirror4() != null && flight.getStairMirror4() > maxStepHeight) {
                    flightIrregular(builder);
                    builder.append("a altura dos espelhos é superior ao máximo permitido de ").append(maxStepHeight).append(" m");
                }

                if (!flight.getStairMirror1().equals(flight.getStairMirror2()) ||
                        !flight.getStairMirror1().equals(flight.getStairMirror3()) ||
                        !flight.getStairMirror1().equals(flight.getStairMirror4())) {
                    flightIrregular(builder);
                    builder.append("as alturas dos espelhos são divergentes entre si");
                }

                if (flight.getStairStep1() != null && flight.getStairStep1() < minStepWidth ||
                        flight.getStairStep2() != null && flight.getStairStep2() < minStepWidth ||
                        flight.getStairStep3() != null && flight.getStairStep3() < minStepWidth ||
                        flight.getStairStep4() != null && flight.getStairStep4() < minStepWidth) {
                    flightIrregular(builder);
                    builder.append("o comprimento dos pisos é inferior ao mínimo permitido de ").append(minStepWidth).append(" m");
                } else if (flight.getStairStep1() != null && flight.getStairStep1() > maxStepWidth ||
                        flight.getStairStep2() != null && flight.getStairStep2() > maxStepWidth ||
                        flight.getStairStep3() != null && flight.getStairStep3() > maxStepWidth ||
                        flight.getStairStep4() != null && flight.getStairStep4() > maxStepWidth) {
                    flightIrregular(builder);
                    builder.append("a largura dos pisos é superior ao máximo permitido de ").append(maxStepWidth).append(" m");
                }

                if (flight.getStairStep2() != null && !flight.getStairStep1().equals(flight.getStairStep2()) ||
                        (flight.getStairStep3() != null && !flight.getStairStep1().equals(flight.getStairStep3())) ||
                        (flight.getStairStep4() != null && !flight.getStairStep1().equals(flight.getStairStep4()))) {
                    flightIrregular(builder);
                    builder.append("as larguras dos pisos são divergentes entre si");
                }

                if (flight.getSignPavement() == 0) {
                    flightIrregular(builder);
                    builder.append("não há sinalização tátil de pavimento nos niveis mais baixo e mais alto do lance");
                } else if (flight.getSignPavement() == 1 && flight.getSignPavementObs() != null) {
                    flightIrregular(builder);
                    builder.append("há sinalização tátil de pavimento, porém devem ser feitas as seguintes observaões: ")
                            .append(flight.getSignPavementObs());
                } else if (flight.getSignPavement() == 2 && flight.getSignPavementObs() != null) {
                    flightIrregular(builder);
                    builder.append("a sinalização tátil de pavimento não é aplicavel, porém devem ser feitas as seguintes observaões: ")
                            .append(flight.getSignPavementObs());
                }

                Double frequentStep = getMostFrequentValue(flight);

                if (flight.getHasLowTactFloor() == 0) {
                    flightIrregular(builder);
                    builder.append("o lance não possui sinalização tátil de alerta em seu nível mais baixo");
                } else {
                    if (flight.getLowTactDist() > frequentStep) {
                        flightIrregular(builder);
                        builder.append("a distância da sinalização tátil de alerta até a base da escadaria é superior ao máximo permitido");
                    }

                    if (flight.getLowTactWidth() < minWidthTactFloorStairs) {
                        flightIrregular(builder);
                        builder.append("a largura do piso tátil de alerta na base da escadaria é inferior a " + minWidthTactFloorStairs + "m");
                    }

                    if (flight.getLowTactDist() + flight.getLowTactWidth() < minSumTactDistStairs) {
                        flightIrregular(builder);
                        builder.append("a largura da composição de piso tátil de alerta com a distância até o espelho do degrau inferior é menor que "
                                + minSumTactDistStairs + " m");
                    } else if (flight.getLowTactDist() + flight.getLowTactWidth() > maxSumTactDistStairs) {
                        flightIrregular(builder);
                        builder.append("a largura da composição de piso tátil de alerta com a distância até o espelho do degrau inferior é maior que "
                                + maxSumTactDistStairs + " m");
                    }
                }

                if (flight.getHasUpTactFloor() == 0) {
                    flightIrregular(builder);
                    builder.append("o lance não possui sinalização tátil de alerta em seu nível mais alto");
                } else {
                    if (flight.getUpTactDist() < frequentStep) {
                        flightIrregular(builder);
                        builder.append("a distância do final do lance até a sinalização tátil de alerta é inferior ao mínimo recomendado");
                    }

                    if (flight.getUpTactWidth() < minWidthTactFloorStairs) {
                        flightIrregular(builder);
                        builder.append("a largura do piso tátil de alerta na base da escadaria é inferior a " + minWidthTactFloorStairs + "m");
                    }

                    if (flight.getUpTactDist() + flight.getUpTactWidth() < minSumTactDistStairs) {
                        flightIrregular(builder);
                        builder.append("a largura da composição da distância do último espelho do lance com o piso tátil de alerta superior é menor que "
                                + minSumTactDistStairs + " m");
                    } else if (flight.getUpTactDist() + flight.getUpTactWidth() > maxSumTactDistStairs) {
                        flightIrregular(builder);
                        builder.append("a largura da composição da distância do último espelho do lance com o piso tátil de alerta superior é maior que "
                                + maxSumTactDistStairs + " m");
                    }
                }

                if (flight.getTactileFloorObs() != null) {
                    flightIrregular(builder);
                    builder.append("as seguintes observações devem ser pontuadas referente ao piso tátil de alerta deste lance: ")
                            .append(flight.getTactileFloorObs());
                }

                if (flight.getHasInterLevel() == 1) {
                    if (flight.getInterLevelLength() < minLengthStairsInterLevel) {
                        flightIrregular(builder);
                        builder.append("o comprimento do patamar intermediário é inferior ao mínimo definido em norma de " + minLengthStairsInterLevel + " m");
                    }
                    if (flight.getInterLevelObs() != null) {
                        flightIrregular(builder);
                        builder.append("as seguintes observações devem ser pontuadas sobre o patamar ao final do lance: ").append(flight.getInterLevelObs());
                    }
                }

                String handRail = HandRailAnalysis.handRailVerification(flight.getFlightID(), rStRail, rStHandrail);
                if (handRail != null) {
                    flightIrregular(builder);
                    builder.append(handRail);
                }

                if (flight.getBorderSign() != null) {
                    if (flight.getBorderSign() == 0) {
                        flightIrregular(builder);
                        builder.append("os degraus não possuem sinalização visual de borda");
                    } else if (flight.getBorderSign() == 1) {
                        if (flight.getBorderSignWidth() < minBorderSignWidth) {
                            flightIrregular(builder);
                            builder.append("largura da sinalização de borda dos degraus é inferior à " + minBorderSignWidth + " cm");
                        }

                        if (flight.getBorderSignLength() < minBorderSignLength) {
                            flightIrregular(builder);
                            builder.append("comprimento da sinalização de borda dos degraus é inferior à " + minBorderSignLength + " cm");
                        }

                        if (flight.getBorderSignIdentifiable() == 0) {
                            flightIrregular(builder);
                            builder.append("a sinalização de borda das escadas não é identificável");
                        }
                    }
                }

                if (irregularFlight && builder.length() != 0) {
                    builder.replace(14, 15, String.valueOf(flight.getFlightNumber()));
                    flightText.append(builder.toString());
                }
            }
        }
        return flightText.toString();
    }

    static int countOccurrences(Double[] list, Double targetValue) {
        int count = 0;
        if (targetValue != null) {
            for (Double aDouble : list) {
                if (aDouble != null && aDouble.equals(targetValue)) {
                    count++;
                }
            }
        }
        return count;
    }


    static Double getMostFrequentValue(RampStairsFlightEntry rStFlight) {
        Double[] list = new Double[4];
        list[0] = rStFlight.getStairStep1();
        list[1] = rStFlight.getStairStep2();
        list[2] = rStFlight.getStairStep3();
        list[3] = rStFlight.getStairStep4();
        int mostFrequentCount = 0;
        double mostFrequentValue = 0;
        for (Double value : list) {
            if (value != null) {
                int count = countOccurrences(list, value);
                if (count > mostFrequentCount) {
                    mostFrequentCount = count;
                    mostFrequentValue = value;
                }
            }
        }
        return mostFrequentValue;
    }
}
