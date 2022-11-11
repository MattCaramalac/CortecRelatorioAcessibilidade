package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;

import java.util.ArrayList;
import java.util.List;

public class SidewalkAnalysis implements StandardMeasurements {

    static boolean irregularRamp;

    public static void sidewalkVerification(List<SidewalkEntry> sideList, List<RampStairsEntry> sideStRaList, List<RampStairsFlightEntry> sideFlight,
                                            List<PayPhoneEntry> sidePhone, List<SidewalkSlopeEntry> sideSlope) {

        String irrRampData = null;

        for (int i = 0; i < sideList.size(); i++) {
            List<String> sideIrregular = new ArrayList<>();
            boolean err = false, noSlopes = false;
            SidewalkEntry sideEntry = sideList.get(i);
            if (sideEntry.getStreetPavement() == 1) {
                if (sideEntry.getSidewalkWidth() < sideWidth) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("Largura da calçada inferior à 1,90 m;");
                }
                if (sideEntry.getSideFreeSpaceWidth() < freeLaneWidth) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("Largura da Faixa Livre da Calçada inferior à 1,20 m;");
                }
            }
            if (sideEntry.getSideTransSlope1() != null && sideEntry.getSideTransSlope1() > 3.0 ||
                    sideEntry.getSideTransSlope2() != null && sideEntry.getSideTransSlope2() > 3.0 ||
                    sideEntry.getSideTransSlope3() != null && sideEntry.getSideTransSlope3() > 3.0 ||
                    sideEntry.getSideTransSlope4() != null && sideEntry.getSideTransSlope4() > 3.0 ||
                    sideEntry.getSideTransSlope5() != null && sideEntry.getSideTransSlope5() > 3.0 ||
                    sideEntry.getSideTransSlope6() != null && sideEntry.getSideTransSlope6() > 3.0) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add("A inclinação transversal da superfície ultrapassa o valor máximo permitido de 3%;");
            }
            if (sideEntry.getHasSpecialFloor() == 1) {
                if (sideEntry.getSpecialFloorRightColor() == 0) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("O piso tátil não possui cor suficientemente contrastante em relação ao piso;");
                }

                if (sideEntry.getSpecialTileDirectionWidth() < minDirTactWidth) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("O piso tátil direcional possui largura inferior à 0,25 m;");
                } else if (sideEntry.getSpecialTileDirectionWidth() > maxDirTactWidth) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("O piso tátil direcional possui largura superior à 0,40 m;");
                }

                if (sideEntry.getSpecialFloorObs() != null) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add(sideEntry.getSpecialFloorObs() + ";");
                }
            }

            if (sideEntry.getSideConStatus() == 0) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add(sideEntry.getSideConsObs() + ";");
            }

            if (sideEntry.getSideFloorIsAccessible() == 0) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add("O piso não pode ser considerado acessível, pois " + sideEntry.getAccessFloorObs() + ";");
            }

            if (sideEntry.getSidewalkHasLids() == 1) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add(sideEntry.getSidewalkLidDesc() + ";");
            }

            if (sideEntry.getHasAerialObstacle() == 1) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add("Os seguintes obstáculos aéreos: " + sideEntry.getAerialObstacleDesc() + ";");
            }

            if (sideSlope.size() > 0) {
                for (SidewalkSlopeEntry slopes : sideSlope) {
                    if (slopes.getSidewalkID() == sideEntry.getSidewalkID()) {
                        if (slopes.getSlopeWidth() < minSideSlopeWidth) {
                            checkHasSideHeader();
                            err = true;
                            sideIrregular.add("O rebaixamento localizado em " + slopes.getSlopeLocation() + " possui largura inferior à 1,20 m;");
                        }
                    }

                    if (slopes.getLongMeasure1() != null && slopes.getLongMeasure1() > maxSlopeLongAngle ||
                            slopes.getLongMeasure2() != null && slopes.getLongMeasure2() > maxSlopeLongAngle ||
                            slopes.getLongMeasure3() != null && slopes.getLongMeasure3() > maxSlopeLongAngle ||
                            slopes.getLongMeasure4() != null && slopes.getLongMeasure4() > maxSlopeLongAngle) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("A inclinação longitudinal do rebaixamento localizado em " + slopes.getSlopeLocation() +
                                " ultrapassa o valor máximo permitido de 8,33%;");
                    }

                    if (slopes.getLeftMeasure1() != null && slopes.getLeftMeasure1() > maxSlopeLongAngle ||
                            slopes.getLeftMeasure2() != null && slopes.getLeftMeasure2() > maxSlopeLongAngle ||
                            slopes.getLeftMeasure3() != null && slopes.getLeftMeasure3() > maxSlopeLongAngle ||
                            slopes.getLeftMeasure4() != null && slopes.getLeftMeasure4() > maxSlopeLongAngle) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("A inclinação longitudinal da aba esquerda do rebaixamento localizado em " + slopes.getSlopeLocation() +
                                " ultrapassa o valor máximo permitido de 8,33%;");
                    }

                    if (slopes.getRightMeasure1() != null && slopes.getRightMeasure1() > maxSlopeLongAngle ||
                            slopes.getRightMeasure2() != null && slopes.getRightMeasure2() > maxSlopeLongAngle ||
                            slopes.getRightMeasure3() != null && slopes.getRightMeasure3() > maxSlopeLongAngle ||
                            slopes.getRightMeasure4() != null && slopes.getRightMeasure4() > maxSlopeLongAngle) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("A inclinação longitudinal da aba direita do rebaixamento localizado em " + slopes.getSlopeLocation() +
                                " ultrapassa o valor máximo permitido de 8,33%;");
                    }

                    if (slopes.getHasTactileFloor() == 0) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("O rebaixamento localizado em " + slopes.getSlopeLocation() + " não possui piso tátil;");
                    } else {
                        if (!slopes.getTactileFloorObs().isEmpty())
                            sideIrregular.add("O rebaixamento localizado em " + slopes.getSlopeLocation() + " possui piso tátil; entretanto, "
                                    + slopes.getTactileFloorObs() + ";");
                    }

                    if (slopes.getAccessibleSlopeFloor() == 0) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("O piso do rebaixamento, localizado em " + slopes.getSlopeLocation() + ", não pode ser considerado" +
                                "acessível, pois " + slopes.getAccessibleSlopeFloorObs());
                    }

                    if (!slopes.getStreetSlopeObs().isEmpty() && slopes.getStreetSlopeJunction() < 2) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("As seguintes observações devem ser pontuadas sobre a junção do rebaixamento com a via pública, " +
                                "localizada em " + slopes.getSlopeLocation() + ": " + slopes.getStreetSlopeObs() + ";");
                    } else if (!slopes.getStreetSlopeObs().isEmpty() && slopes.getStreetSlopeJunction() == 2) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("As junção do rebaixamento com a via pública, localizada em " + slopes.getSlopeLocation() +
                                ", é feita através de degrau. Além disso, " + slopes.getStreetSlopeObs() + ";");
                    } else if (slopes.getStreetSlopeJunction() == 2) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("As junção do rebaixamento com a via pública, localizada em " + slopes.getSlopeLocation() +
                                ", é feita através de degrau com altura de " + slopes.getStepJunctionHeight() + "cm;");
                    } else if (slopes.getStreetSlopeJunction() == 3) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("As seguintes observações devem ser pontuadas sobre a junção do rebaixamento com a via pública, " +
                                "localizada em " + slopes.getSlopeLocation() + ": " + slopes.getStreetSlopeObs() + ";");
                    }

                    if (!slopes.getSlopeObs().isEmpty()) {
                        checkHasSideHeader();
                        err = true;
                        sideIrregular.add("As seguintes observações devem ser pontuadas sobre o rebaixamento com a via pública, " +
                                "localizada em " + slopes.getSlopeLocation() + ": " + slopes.getSlopeObs() + ";");
                    }


                }
            } else {
                checkHasSideHeader();
                noSlopes = true;
//                AmbientAnalysis.sideLocationList.add("Ausência de rebaixamentos de calçada em todas as esquinas ao entorno da escola");
            }

            if (sideStRaList.size() > 0) {
                for (RampStairsEntry rStairs : sideStRaList) {
                    if (rStairs.getSidewalkID() == sideEntry.getSidewalkID()) {
                        if (rStairs.getRampStairsIdentifier() == 1)
                            sideIrregular.add("A calçada possui degraus/escadas, localizados em " + rStairs.getRampStairsLocation() + ";");
                        else {
                            for (RampStairsFlightEntry flight : sideFlight) {
                                if (flight.getRampStairsID() == rStairs.getRampStairsID()) {
                                    irregularRamp = false;
                                    StringBuilder builder = new StringBuilder();
                                    if (flight.getFlightWidth() < minFlightWidth) {
                                        rampTextIrregular(builder);
                                        builder.append("largura livre mínima inferior à 1,20 m");
                                    }

                                    if (flight.getRampHeight() > highestRampHeight) {
                                        rampTextIrregular(builder);
                                        builder.append("altura do desnivel acima de ").append(highestRampHeight).append(" m");
                                    }
                                    else if (flight.getRampHeight() <= highestRampHeight && flight.getRampHeight() > medRampHeight) {
                                        if (flight.getRampSlope1() != null && flight.getRampSlope1() > minAngleRamp ||
                                                flight.getRampSlope2() != null && flight.getRampSlope2() > minAngleRamp ||
                                                flight.getRampSlope3() != null && flight.getRampSlope3() > minAngleRamp ||
                                                flight.getRampSlope4() != null && flight.getRampSlope4() > minAngleRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("inclinação da rampa acima do máximo permitido de ").append(minAngleRamp).append("%");
                                        }
                                    }
                                    else if (flight.getRampHeight() <= medRampHeight && flight.getRampHeight() > lowestRampHeight) {
                                        if (flight.getRampSlope1() != null && flight.getRampSlope1() > medAngleRamp ||
                                                flight.getRampSlope2() != null && flight.getRampSlope2() > medAngleRamp ||
                                                flight.getRampSlope3() != null && flight.getRampSlope3() > medAngleRamp ||
                                                flight.getRampSlope4() != null && flight.getRampSlope4() > medAngleRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("inclinação da rampa acima do máximo permitido de ").append(medAngleRamp).append("%");
                                        }
                                    }
                                    else if (flight.getRampHeight() <= lowestRampHeight) {
                                        if (flight.getRampSlope1() != null && flight.getRampSlope1() < medAngleRamp ||
                                                flight.getRampSlope2() != null && flight.getRampSlope2() < medAngleRamp ||
                                                flight.getRampSlope3() != null && flight.getRampSlope3() < medAngleRamp ||
                                                flight.getRampSlope4() != null && flight.getRampSlope4() < medAngleRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("inclinação da rampa abaixo do mínimo permitido de ").append(medAngleRamp).append("%");
                                        }
                                        else if (flight.getRampSlope1() != null && flight.getRampSlope1() > maxAngleRamp ||
                                                flight.getRampSlope2() != null && flight.getRampSlope2() > maxAngleRamp ||
                                                flight.getRampSlope3() != null && flight.getRampSlope3() > maxAngleRamp ||
                                                flight.getRampSlope4() != null && flight.getRampSlope4() > maxAngleRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("inclinação da rampa acima do máximo permitido de ").append(maxAngleRamp).append("%");
                                        }
                                    }

                                    if (flight.getHasLowTactFloor() == 0) {
                                        rampTextIrregular(builder);
                                        builder.append("ausência de piso tátil de alerta ao início da rampa");
                                    }
                                    else if (flight.getHasLowTactFloor() == 1) {
                                        if (flight.getLowTactWidth() < minTactWidthRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("largura do piso tátil de alerta ao início da rampa inferior à ").append(minTactWidthRamp).append(" m");
                                        }
                                        else if (flight.getLowTactWidth() > maxTactWidthRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("largura do piso tátil de alerta ao início da rampa superior à ").append(maxTactWidthRamp).append(" m");
                                        }

                                        if (flight.getLowTactDist() != 0) {
                                            rampTextIrregular(builder);
                                            builder.append("distância entre o piso tátil de alerta e o início da rampa");
                                        }
                                    }

                                    if (flight.getHasUpTactFloor() == 0) {
                                        rampTextIrregular(builder);
                                        builder.append("ausência de piso tátil de alerta ao final da rampa");
                                    }
                                    else if (flight.getHasUpTactFloor() == 1) {
                                        if (flight.getUpTactWidth() < minTactWidthRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("largura do piso tátil de alerta ao final da rampa inferior à ").append(minTactWidthRamp).append(" m");
                                        }
                                        else if (flight.getUpTactWidth() > maxTactWidthRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("largura do piso tátil de alerta ao final da rampa superior à ").append(maxTactWidthRamp).append(" m");
                                        }

                                        if (flight.getUpTactDist() < minDistTactRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("distância entre o piso tátil de alerta e o final da rampa inferior à ").append(minDistTactRamp).append(" m");
                                        }
                                        else if (flight.getUpTactDist() > maxDistTactRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("distância entre o piso tátil de alerta e o final da rampa superior à ").append(maxDistTactRamp).append(" m");
                                        }
                                    }

                                    if (flight.getHasInterLevel() == 1) {
                                        if (flight.getInterLevelLength() < minInterLength) {
                                            rampTextIrregular(builder);
                                            builder.append("comprimento do patamar intermediário inferior à ").append(minInterLength).append(" m");
                                        }
                                    }

                                    if (flight.getFlightObs() != null) {
                                        rampTextIrregular(builder);
                                        builder.append(flight.getFlightObs());
                                    }

                                    irrRampData = builder.toString();
                                }

                            }
                        }
                    }
                }
            }

            if (err)
                AmbientAnalysis.sideIrregularities.put(i, sideIrregular);
            AmbientAnalysis.sideLocationList.add("Calçada, ao longo da " + sideEntry.getSidewalkLocation() + ", com:");

            if (noSlopes)
                AmbientAnalysis.sideLocationList.add("Ausência de rebaixamentos de calçada em todas as esquinas ao entorno da escola");

            if (irregularRamp && irrRampData != null)
                AmbientAnalysis.sideLocationList.add(irrRampData);
        }
    }

    public static void checkHasSideHeader() {
        for (int i = 0; i < AmbientAnalysis.placeType.size(); i++) {
            if (AmbientAnalysis.placeType.get(i).equals(AmbientAnalysis.ACCESS_TITLE))
                return;
        }
        AmbientAnalysis.placeType.add(AmbientAnalysis.ACCESS_TITLE);
    }

    public static void rampTextIrregular(StringBuilder builder) {
        if (!irregularRamp) {
            irregularRamp = true;
            builder.append("Presença de rampa com as seguintes irregularidades: ");
        } else {
            builder.append(", ");
        }
    }
}
