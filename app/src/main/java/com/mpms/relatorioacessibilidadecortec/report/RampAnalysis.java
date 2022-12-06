package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;

import java.util.ArrayList;
import java.util.List;

public class RampAnalysis implements StandardMeasurements {

    static boolean irregularRamps;
    static boolean irregularFlight;

    public static List<String> rampVerification(int ambientID, List<RampStairsEntry> rStList, List<RampStairsFlightEntry> rStFlight, List<RampStairsRailingEntry> rStRail,
                                                List<RampStairsHandrailEntry> rStHandrail) {

        List<String> rampText = new ArrayList<>();

        if (rStList.size() > 0) {
            for (RampStairsEntry ramps : rStList) {
                StringBuilder rampBuilder = new StringBuilder();
                irregularRamps = false;
                String analysis = null;
                if (ramps.getRampStairsIdentifier() == 2) {
                    if (ramps.getExtAccessID() != null && ramps.getExtAccessID() == ambientID ||
                            ramps.getSidewalkID() != null && ramps.getSidewalkID() == ambientID ||
                            ramps.getParkingID() != null && ramps.getParkingID() == ambientID ||
                            ramps.getRoomID() != null && ramps.getRoomID() == ambientID)
                        analysis = flightText(ramps.getRampStairsID(), rStFlight, rStRail, rStHandrail);
                }
                if (analysis != null && analysis.length() != 0) {
                    rampBuilder.append(analysis);
                    rampIrregular(rampBuilder);
                    rampBuilder.replace(33, 34, ramps.getRampStairsLocation());
                    rampText.add(rampBuilder.toString());
                }
            }
        }
        return rampText;
    }

    public static void rampIrregular(StringBuilder builder) {
        if (!irregularRamps) {
            irregularRamps = true;
            builder.replace(0, 1, "Presença de rampa, localizada em x, com as seguintes irregularidades: "); //34,35
        } else {
            builder.append(", ");
        }
    }

    public static void flightIrregular(StringBuilder builder) {
        if (!irregularFlight) {
            irregularFlight = true;
            builder.append("No lance de nºx, "); //14,15
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
                    rampIrregular(builder);
                    builder.append("largura livre mínima inferior à 1,20 m");
                }
//                                        TODO - trocar nome para flightHeight
                if (flight.getRampHeight() > highestRampHeight) {
                    flightIrregular(builder);
                    builder.append("altura do desnivel acima de ").append(highestRampHeight).append(" m");
                } else if (flight.getRampHeight() <= highestRampHeight && flight.getRampHeight() > medRampHeight) {
                    if (flight.getRampSlope1() != null && flight.getRampSlope1() > minAngleRamp ||
                            flight.getRampSlope2() != null && flight.getRampSlope2() > minAngleRamp ||
                            flight.getRampSlope3() != null && flight.getRampSlope3() > minAngleRamp ||
                            flight.getRampSlope4() != null && flight.getRampSlope4() > minAngleRamp) {
                        flightIrregular(builder);
                        builder.append("inclinação da rampa acima do máximo permitido de ").append(minAngleRamp).append("%");
                    }
                } else if (flight.getRampHeight() <= medRampHeight && flight.getRampHeight() > lowestRampHeight) {
                    if (flight.getRampSlope1() != null && flight.getRampSlope1() > medAngleRamp ||
                            flight.getRampSlope2() != null && flight.getRampSlope2() > medAngleRamp ||
                            flight.getRampSlope3() != null && flight.getRampSlope3() > medAngleRamp ||
                            flight.getRampSlope4() != null && flight.getRampSlope4() > medAngleRamp) {
                        flightIrregular(builder);
                        builder.append("inclinação da rampa acima do máximo permitido de ").append(medAngleRamp).append("%");
                    }
                } else if (flight.getRampHeight() <= lowestRampHeight) {
                    if (flight.getRampSlope1() != null && flight.getRampSlope1() < medAngleRamp ||
                            flight.getRampSlope2() != null && flight.getRampSlope2() < medAngleRamp ||
                            flight.getRampSlope3() != null && flight.getRampSlope3() < medAngleRamp ||
                            flight.getRampSlope4() != null && flight.getRampSlope4() < medAngleRamp) {
                        flightIrregular(builder);
                        builder.append("inclinação da rampa abaixo do mínimo permitido de ").append(medAngleRamp).append("%");
                    } else if (flight.getRampSlope1() != null && flight.getRampSlope1() > maxAngleRamp ||
                            flight.getRampSlope2() != null && flight.getRampSlope2() > maxAngleRamp ||
                            flight.getRampSlope3() != null && flight.getRampSlope3() > maxAngleRamp ||
                            flight.getRampSlope4() != null && flight.getRampSlope4() > maxAngleRamp) {
                        flightIrregular(builder);
                        builder.append("inclinação da rampa acima do máximo permitido de ").append(maxAngleRamp).append("%");
                    }
                }

                if (flight.getHasLowTactFloor() == 0) {
                    flightIrregular(builder);
                    builder.append("ausência de piso tátil de alerta ao início da rampa");
                } else if (flight.getHasLowTactFloor() == 1) {
                    if (flight.getLowTactWidth() < minTactWidthRamp) {
                        flightIrregular(builder);
                        builder.append("largura do piso tátil de alerta ao início da rampa inferior à ").append(minTactWidthRamp).append(" m");
                    } else if (flight.getLowTactWidth() > maxTactWidthRamp) {
                        flightIrregular(builder);
                        builder.append("largura do piso tátil de alerta ao início da rampa superior à ").append(maxTactWidthRamp).append(" m");
                    }

                    if (flight.getLowTactDist() != 0) {
                        flightIrregular(builder);
                        builder.append("existência de distância entre o piso tátil de alerta e o início da rampa");
                    }
                }

                if (flight.getHasUpTactFloor() == 0) {
                    flightIrregular(builder);
                    builder.append("ausência de piso tátil de alerta ao final da rampa");
                } else if (flight.getHasUpTactFloor() == 1) {
                    if (flight.getUpTactWidth() < minTactWidthRamp) {
                        flightIrregular(builder);
                        builder.append("largura do piso tátil de alerta ao final da rampa inferior à ").append(minTactWidthRamp).append(" m");
                    } else if (flight.getUpTactWidth() > maxTactWidthRamp) {
                        flightIrregular(builder);
                        builder.append("largura do piso tátil de alerta ao final da rampa superior à ").append(maxTactWidthRamp).append(" m");
                    }

                    if (flight.getUpTactDist() < minDistTactRamp) {
                        flightIrregular(builder);
                        builder.append("distância entre o piso tátil de alerta e o final da rampa inferior à ").append(minDistTactRamp).append(" m");
                    } else if (flight.getUpTactDist() > maxDistTactRamp) {
                        flightIrregular(builder);
                        builder.append("distância entre o piso tátil de alerta e o final da rampa superior à ").append(maxDistTactRamp).append(" m");
                    }
                }

                String handRail = HandRailAnalysis.handRailVerification(flight.getFlightID(), rStRail, rStHandrail);
                if (handRail != null) {
                    flightIrregular(builder);
                    builder.append(handRail);
                }

                if (flight.getTactileFloorObs() != null) {
                    flightIrregular(builder);
                    builder.append("observações sobre o piso tátil: ").append(flight.getTactileFloorObs());
                }

                if (flight.getHasInterLevel() == 1) {
                    if (flight.getInterLevelLength() < minInterLength) {
                        flightIrregular(builder);
                        builder.append("comprimento do patamar intermediário inferior à ").append(minInterLength).append(" m");
                    }
                }

                if (flight.getFlightObs() != null) {
                    flightIrregular(builder);
                    builder.append(flight.getFlightObs());
                }


                if (irregularFlight && builder.length() != 0) {
                    builder.replace(14, 15, String.valueOf(flight.getFlightNumber()));
                    flightText.append(builder.toString());
                }
            }
        }
        return flightText.toString();
    }

}
