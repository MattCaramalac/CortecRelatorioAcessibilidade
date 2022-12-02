package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;

import java.util.ArrayList;
import java.util.List;

public class SidewalkAnalysis implements StandardMeasurements {

    static boolean irrStRamp;
    static boolean irregularPhone;

    public static void sidewalkVerification(List<SidewalkEntry> sideList, List<RampStairsEntry> sideStRaList, List<RampStairsFlightEntry> sideFlight,
                                            List<PayPhoneEntry> sidePhone, List<SidewalkSlopeEntry> sideSlope) {

        String irrRampData;
        String irrPhoneData;

        for (int i = 0; i < sideList.size(); i++) {
            int check = 0;
            AmbientAnalysis.err = false;
            List<String> sideIrregular = new ArrayList<>();
            SidewalkEntry sideEntry = sideList.get(i);
            if (sideEntry.getStreetPavement() == 1) {
                if (sideEntry.getSidewalkWidth() < sideWidth) {
                    check++;
                    sideIrregular.add("Largura da calçada inferior à 1,90 m;");
                }
                if (sideEntry.getSideFreeSpaceWidth() < freeLaneWidth) {
                    check++;
                    sideIrregular.add("Largura da Faixa Livre da Calçada inferior à 1,20 m;");
                }
            }
            if (sideEntry.getSidewalkObs() != null) {
                check++;
                sideIrregular.add("A calçada possui as seguintes irregularidades: " + sideEntry.getSidewalkObs() + ";");
            }
            if (sideEntry.getSideTransSlope1() != null && sideEntry.getSideTransSlope1() > transSlope ||
                    sideEntry.getSideTransSlope2() != null && sideEntry.getSideTransSlope2() > transSlope ||
                    sideEntry.getSideTransSlope3() != null && sideEntry.getSideTransSlope3() > transSlope ||
                    sideEntry.getSideTransSlope4() != null && sideEntry.getSideTransSlope4() > transSlope ||
                    sideEntry.getSideTransSlope5() != null && sideEntry.getSideTransSlope5() > transSlope ||
                    sideEntry.getSideTransSlope6() != null && sideEntry.getSideTransSlope6() > transSlope) {
                check++;
                sideIrregular.add("A inclinação transversal da superfície ultrapassa o valor máximo permitido de 3%;");
            }
            if (sideEntry.getHasSpecialFloor() == 1) {
                if (sideEntry.getSpecialFloorRightColor() == 0) {
                    check++;
                    sideIrregular.add("O piso tátil não possui cor suficientemente contrastante em relação ao piso;");
                }

                if (sideEntry.getSpecialTileDirectionWidth() < minDirTactWidth) {
                    check++;
                    sideIrregular.add("O piso tátil direcional possui largura inferior à 0,25 m;");
                } else if (sideEntry.getSpecialTileDirectionWidth() > maxDirTactWidth) {
                    check++;
                    sideIrregular.add("O piso tátil direcional possui largura superior à 0,40 m;");
                }

            } else {
                check++;
                sideIrregular.add("A calçada não possui composição de piso tátil direcional e de alerta");
            }

            if (sideEntry.getSpecialFloorObs() != null) {
                check++;
                sideIrregular.add("As seguintes observações podem ser feitas sobre o estado de conservação do piso tátil: "
                        + sideEntry.getSpecialFloorObs() + ";");
            }

            if (sideEntry.getSideConStatus() == 0) {
                check++;
                sideIrregular.add(sideEntry.getSideConsObs() + ";");
            }

            if (sideEntry.getSideFloorIsAccessible() == 0) {
                check++;
                sideIrregular.add("O piso não pode ser considerado acessível, pois " + sideEntry.getAccessFloorObs() + ";");
            }

            if (sideEntry.getSidewalkHasLids() == 1) {
                check++;
                sideIrregular.add("Foi detectada a presença dos seguintes desníveis: " + sideEntry.getSidewalkLidDesc() + ";");
            }

            if (sideEntry.getHasAerialObstacle() == 1) {
                check++;
                sideIrregular.add("Foram detectados os seguintes obstáculos aéreos: " + sideEntry.getAerialObstacleDesc() + ";");
            }

            if (sideEntry.getSideHasSlope() == 0 && sideEntry.getSideReqSlopes() == 1) {
                check++;
                sideIrregular.add("A calçada não possui rebaixamentos por sua extensão;");
            }
            else if (sideEntry.getSideHasSlope() == 0) {
                if (sideSlope.size() > 0) {
                    for (SidewalkSlopeEntry slopes : sideSlope) {
                        if (slopes.getSidewalkID() == sideEntry.getSidewalkID()) {
                            if (slopes.getSlopeWidth() < minSideSlopeWidth) {
                                check++;
                                sideIrregular.add("O rebaixamento localizado em " + slopes.getSlopeLocation() + " possui largura inferior à 1,20 m;");
                            }
                            if (slopes.getLongMeasure1() != null && slopes.getLongMeasure1() > maxSlopeLongAngle ||
                                    slopes.getLongMeasure2() != null && slopes.getLongMeasure2() > maxSlopeLongAngle ||
                                    slopes.getLongMeasure3() != null && slopes.getLongMeasure3() > maxSlopeLongAngle ||
                                    slopes.getLongMeasure4() != null && slopes.getLongMeasure4() > maxSlopeLongAngle) {
                                check++;
                                sideIrregular.add("A inclinação longitudinal do rebaixamento localizado em " + slopes.getSlopeLocation() +
                                        " ultrapassa o valor máximo permitido de 8,33%;");
                            }

                            if (slopes.getLeftMeasure1() != null && slopes.getLeftMeasure1() > maxSlopeLongAngle ||
                                    slopes.getLeftMeasure2() != null && slopes.getLeftMeasure2() > maxSlopeLongAngle ||
                                    slopes.getLeftMeasure3() != null && slopes.getLeftMeasure3() > maxSlopeLongAngle ||
                                    slopes.getLeftMeasure4() != null && slopes.getLeftMeasure4() > maxSlopeLongAngle) {
                                check++;
                                sideIrregular.add("A inclinação longitudinal da aba esquerda do rebaixamento localizado em " + slopes.getSlopeLocation() +
                                        " ultrapassa o valor máximo permitido de 8,33%;");
                            }

                            if (slopes.getRightMeasure1() != null && slopes.getRightMeasure1() > maxSlopeLongAngle ||
                                    slopes.getRightMeasure2() != null && slopes.getRightMeasure2() > maxSlopeLongAngle ||
                                    slopes.getRightMeasure3() != null && slopes.getRightMeasure3() > maxSlopeLongAngle ||
                                    slopes.getRightMeasure4() != null && slopes.getRightMeasure4() > maxSlopeLongAngle) {
                                check++;
                                sideIrregular.add("A inclinação longitudinal da aba direita do rebaixamento localizado em " + slopes.getSlopeLocation() +
                                        " ultrapassa o valor máximo permitido de 8,33%;");
                            }

                            if (slopes.getHasTactileFloor() == 0) {
                                check++;
                                sideIrregular.add("O rebaixamento localizado em " + slopes.getSlopeLocation() + " não possui piso tátil;");
                            } else {
                                if (!slopes.getTactileFloorObs().isEmpty())
                                    sideIrregular.add("O rebaixamento localizado em " + slopes.getSlopeLocation() + " possui piso tátil; entretanto, "
                                            + slopes.getTactileFloorObs() + ";");
                            }

                            if (slopes.getAccessibleSlopeFloor() == 0) {
                                check++;
                                sideIrregular.add("O piso do rebaixamento, localizado em " + slopes.getSlopeLocation() + ", não pode ser considerado" +
                                        "acessível, pois " + slopes.getAccessibleSlopeFloorObs());
                            }

                            if (!slopes.getStreetSlopeObs().isEmpty() && slopes.getStreetSlopeJunction() < 2) {
                                check++;
                                sideIrregular.add("As seguintes observações devem ser pontuadas sobre a junção do rebaixamento com a via pública, " +
                                        "localizada em " + slopes.getSlopeLocation() + ": " + slopes.getStreetSlopeObs() + ";");
                            } else if (!slopes.getStreetSlopeObs().isEmpty() && slopes.getStreetSlopeJunction() == 2) {
                                check++;
                                sideIrregular.add("As junção do rebaixamento com a via pública, localizada em " + slopes.getSlopeLocation() +
                                        ", é feita através de degrau. Além disso, " + slopes.getStreetSlopeObs() + ";");
                            } else if (slopes.getStreetSlopeJunction() == 2) {
                                check++;
                                sideIrregular.add("As junção do rebaixamento com a via pública, localizada em " + slopes.getSlopeLocation() +
                                        ", é feita através de degrau com altura de " + slopes.getStepJunctionHeight() + "cm;");
                            } else if (slopes.getStreetSlopeJunction() == 3) {
                                check++;
                                sideIrregular.add("As seguintes observações devem ser pontuadas sobre a junção do rebaixamento com a via pública, " +
                                        "localizada em " + slopes.getSlopeLocation() + ": " + slopes.getStreetSlopeObs() + ";");
                            }

                            if (!slopes.getSlopeObs().isEmpty()) {
                                check++;
                                sideIrregular.add("As seguintes observações devem ser pontuadas sobre o rebaixamento com a via pública, " +
                                        "localizada em " + slopes.getSlopeLocation() + ": " + slopes.getSlopeObs() + ";");
                            }
                        }
                    }
                }
            }

            if (sideStRaList.size() > 0) {
                for (RampStairsEntry rStairs : sideStRaList) {
                    irrStRamp = false;
                    if (rStairs.getSidewalkID() == sideEntry.getSidewalkID()) {
                        if (rStairs.getRampStairsIdentifier() == 1)
                            sideIrregular.add("A calçada possui degraus/escadas, localizados em " + rStairs.getRampStairsLocation() + ";");
                        else {
                            for (RampStairsFlightEntry flight : sideFlight) {
                                if (flight.getRampStairsID() == rStairs.getRampStairsID()) {
                                    StringBuilder builder = new StringBuilder();
                                    if (flight.getFlightWidth() < minFlightWidth) {
                                        rampTextIrregular(builder);
                                        builder.append("largura livre mínima inferior à 1,20 m");
                                    }

                                    if (flight.getRampHeight() > highestRampHeight) {
                                        rampTextIrregular(builder);
                                        builder.append("altura do desnivel acima de ").append(highestRampHeight).append(" m");
                                    } else if (flight.getRampHeight() <= highestRampHeight && flight.getRampHeight() > medRampHeight) {
                                        if (flight.getRampSlope1() != null && flight.getRampSlope1() > minAngleRamp ||
                                                flight.getRampSlope2() != null && flight.getRampSlope2() > minAngleRamp ||
                                                flight.getRampSlope3() != null && flight.getRampSlope3() > minAngleRamp ||
                                                flight.getRampSlope4() != null && flight.getRampSlope4() > minAngleRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("inclinação da rampa acima do máximo permitido de ").append(minAngleRamp).append("%");
                                        }
                                    } else if (flight.getRampHeight() <= medRampHeight && flight.getRampHeight() > lowestRampHeight) {
                                        if (flight.getRampSlope1() != null && flight.getRampSlope1() > medAngleRamp ||
                                                flight.getRampSlope2() != null && flight.getRampSlope2() > medAngleRamp ||
                                                flight.getRampSlope3() != null && flight.getRampSlope3() > medAngleRamp ||
                                                flight.getRampSlope4() != null && flight.getRampSlope4() > medAngleRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("inclinação da rampa acima do máximo permitido de ").append(medAngleRamp).append("%");
                                        }
                                    } else if (flight.getRampHeight() <= lowestRampHeight) {
                                        if (flight.getRampSlope1() != null && flight.getRampSlope1() < medAngleRamp ||
                                                flight.getRampSlope2() != null && flight.getRampSlope2() < medAngleRamp ||
                                                flight.getRampSlope3() != null && flight.getRampSlope3() < medAngleRamp ||
                                                flight.getRampSlope4() != null && flight.getRampSlope4() < medAngleRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("inclinação da rampa abaixo do mínimo permitido de ").append(medAngleRamp).append("%");
                                        } else if (flight.getRampSlope1() != null && flight.getRampSlope1() > maxAngleRamp ||
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
                                    } else if (flight.getHasLowTactFloor() == 1) {
                                        if (flight.getLowTactWidth() < minTactWidthRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("largura do piso tátil de alerta ao início da rampa inferior à ").append(minTactWidthRamp).append(" m");
                                        } else if (flight.getLowTactWidth() > maxTactWidthRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("largura do piso tátil de alerta ao início da rampa superior à ").append(maxTactWidthRamp).append(" m");
                                        }

                                        if (flight.getLowTactDist() != 0) {
                                            rampTextIrregular(builder);
                                            builder.append("existência de distância entre o piso tátil de alerta e o início da rampa");
                                        }
                                    }

                                    if (flight.getHasUpTactFloor() == 0) {
                                        rampTextIrregular(builder);
                                        builder.append("ausência de piso tátil de alerta ao final da rampa");
                                    } else if (flight.getHasUpTactFloor() == 1) {
                                        if (flight.getUpTactWidth() < minTactWidthRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("largura do piso tátil de alerta ao final da rampa inferior à ").append(minTactWidthRamp).append(" m");
                                        } else if (flight.getUpTactWidth() > maxTactWidthRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("largura do piso tátil de alerta ao final da rampa superior à ").append(maxTactWidthRamp).append(" m");
                                        }

                                        if (flight.getUpTactDist() < minDistTactRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("distância entre o piso tátil de alerta e o final da rampa inferior à ").append(minDistTactRamp).append(" m");
                                        } else if (flight.getUpTactDist() > maxDistTactRamp) {
                                            rampTextIrregular(builder);
                                            builder.append("distância entre o piso tátil de alerta e o final da rampa superior à ").append(maxDistTactRamp).append(" m");
                                        }
                                    }

                                    if (flight.getTactileFloorObs() != null) {
                                        rampTextIrregular(builder);
                                        builder.append("observações sobre o piso tátil: ").append(flight.getTactileFloorObs());
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


                                    if (irrStRamp && builder.length() != 0) {
                                        check++;
                                        builder.replace(17, 18,", localizada em " + rStairs.getRampStairsLocation() + ", ");
                                        irrRampData = builder.toString();
                                        sideIrregular.add(irrRampData);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (sidePhone.size() > 0) {
                for (PayPhoneEntry phone : sidePhone) {
                    irregularPhone = false;
                    if (phone.getSidewalkID() == sideEntry.getSidewalkID()) {
                        StringBuilder builder = new StringBuilder();
                        if (phone.getPhoneHeight() < minIntTelHeight) {
                            phoneIrregular(builder);
                            builder.append("Altura operacional do fone inferior a " + minIntTelHeight + " m");
                        }
                        else if (phone.getPhoneHeight() > minIntTelHeight) {
                            phoneIrregular(builder);
                            builder.append("Altura operacional do fone superior a " + maxIntTelHeight + " m");
                        }

                        if (phone.getPhoneKeyboardHeight() < minIntTelHeight) {
                            phoneIrregular(builder);
                            builder.append("Altura operacional do teclado inferior a " + minIntTelHeight + " m");
                        }
                        else if (phone.getPhoneKeyboardHeight() > minIntTelHeight) {
                            phoneIrregular(builder);
                            builder.append("Altura operacional do teclado superior a " + maxIntTelHeight + " m");
                        }

                        if (phone.getHasTactileFloor() == 0) {
                            phoneIrregular(builder);
                            builder.append("o telefone não possui piso tátil em seu entorno");
                        }
                        else {
                            if (phone.getRightColorTactileFloor() == 0) {
                                phoneIrregular(builder);
                                builder.append("o piso tátil não possui cor contrastante em relação ao piso");
                            }

                            if (phone.getTactFloorDist() > distAlertTactSuspObj) {
                                phoneIrregular(builder);
                                builder.append("o piso tátil está à uma distância diferente de " + distAlertTactSuspObj
                                        + "m em relação ao limite da projeção do telefone");
                            }

                            if (phone.getTactFloorWidth() < minWidthAlertTactSuspObj) {
                                phoneIrregular(builder);
                                builder.append("a largura do piso tátil é menor que " + minWidthAlertTactSuspObj + "m");
                            }
                            else if (phone.getTactFloorWidth() > maxWidthAlertTactSuspObj) {
                                phoneIrregular(builder);
                                builder.append("a largura do piso tátil é menor que " + maxWidthAlertTactSuspObj + "m");
                            }

                            if (phone.getPhoneIsWorking() == 0) {
                                phoneIrregular(builder);
                                builder.append("o telefone público está inoperante");
                            }

                            if (phone.getPayPhoneObs() != null) {
                                phoneIrregular(builder);
                                builder.append("As seguintes observações devem ser feitas sobre este telefone público: ").append(phone.getPayPhoneObs());
                            }
                        }

                        if (irregularPhone && builder.length() != 0) {
                            check++;
                            builder.replace(28, 29, ", localizado em " + phone.getPhoneRefPoint() + ", ");
                            irrPhoneData = builder.toString();
                            sideIrregular.add(irrPhoneData);

                        }
                    }
                }
            }

            if (check > 0)
                AmbientAnalysis.checkHasAccessHeader();

            if (AmbientAnalysis.err) {
                AmbientAnalysis.sideLocationList.add("Calçada, ao longo da " + sideEntry.getSidewalkLocation() + ", com:");
                AmbientAnalysis.sideIrregularities.put(i, sideIrregular);
            }
        }
    }

    public static void rampTextIrregular(StringBuilder builder) {
        if (!irrStRamp) {
            irrStRamp = true;
            builder.append("Presença de rampa com as seguintes irregularidades: ");
        } else {
            builder.append(", ");
        }
    }

    public static void phoneIrregular(StringBuilder builder) {
        if (!irregularPhone) {
            irregularPhone = true;
            builder.append("Presença de telefone público com as seguintes irregularidades: ");
        } else {
            builder.append(", ");
        }
    }
}
