package com.mpms.relatorioacessibilidadecortec.report.Ambients;

import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.report.AmbientAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

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
            if (sideEntry.getHasSidewalk() == 0) {
                check++;
                sideIrregular.add("A via não possui calçada;");
            } else if (sideEntry.getHasSidewalk() == 1) {
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
                } else if (sideEntry.getSideHasSlope() == 0) {
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

                                if (!slopes.getStreetSlopeObs().isEmpty() && slopes.getStreetSlopeObs().length() > 0 && slopes.getStreetSlopeJunction() == 0) {
                                    check++;
                                    sideIrregular.add("As seguintes observações devem ser pontuadas sobre a junção do rebaixamento com a via pública, " +
                                            "localizada em " + slopes.getSlopeLocation() + ": " + slopes.getStreetSlopeObs() + ";");
                                } else if (!slopes.getStreetSlopeObs().isEmpty() && slopes.getStreetSlopeObs().length() > 0 && slopes.getStreetSlopeJunction() > 0) {
                                    check++;
                                    sideIrregular.add("A junção do rebaixamento com a via pública, localizada em " + slopes.getSlopeLocation() +
                                            ", possuem desnível com o leito carroçável. Além disso, " + slopes.getStreetSlopeObs() + ";");
                                }  else if (slopes.getStreetSlopeJunction() > 0) {
                                    check++;
                                    sideIrregular.add("A junção do rebaixamento com a via pública, localizada em " + slopes.getSlopeLocation() +
                                            ", possuem desnível com o leito carroçável;");
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

                if (sidePhone.size() > 0) {
                    for (PayPhoneEntry phone : sidePhone) {
                        irregularPhone = false;
                        if (phone.getSidewalkID() == sideEntry.getSidewalkID()) {
                            StringBuilder builder = new StringBuilder();
                            if (phone.getPhoneHeight() < minIntTelHeight) {
                                phoneIrregular(builder);
                                builder.append("altura operacional do fone inferior a " + minIntTelHeight + " m");
                            } else if (phone.getPhoneHeight() > minIntTelHeight) {
                                phoneIrregular(builder);
                                builder.append("altura operacional do fone superior a " + maxIntTelHeight + " m");
                            }

                            if (phone.getPhoneKeyboardHeight() < minIntTelHeight) {
                                phoneIrregular(builder);
                                builder.append("altura operacional do teclado inferior a " + minIntTelHeight + " m");
                            } else if (phone.getPhoneKeyboardHeight() > minIntTelHeight) {
                                phoneIrregular(builder);
                                builder.append("altura operacional do teclado superior a " + maxIntTelHeight + " m");
                            }

                            if (phone.getHasTactileFloor() == 0) {
                                phoneIrregular(builder);
                                builder.append("o telefone não possui piso tátil em seu entorno");
                            } else {
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
                                } else if (phone.getTactFloorWidth() > maxWidthAlertTactSuspObj) {
                                    phoneIrregular(builder);
                                    builder.append("a largura do piso tátil é menor que " + maxWidthAlertTactSuspObj + "m");
                                }

                                if (phone.getPhoneIsWorking() == 0) {
                                    phoneIrregular(builder);
                                    builder.append("o telefone público está inoperante");
                                }

                                if (phone.getPayPhoneObs() != null && phone.getPayPhoneObs().length() > 0) {
                                    phoneIrregular(builder);
                                    builder.append("as seguintes observações devem ser feitas sobre este telefone público: ").append(phone.getPayPhoneObs());
                                }
                            }

                            if (phone.getPhonePhoto() != null) {
                                phoneIrregular(builder);
                                builder.append("Registros fotográficos Telefone: ").append(phone.getPhonePhoto());
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
            }

            if (sideEntry.getSidePhotos() != null) {
                check++;
                sideIrregular.add("Registros fotográficos calçada 1: " + sideEntry.getSidePhotos());
            }
            if (sideEntry.getSidePhotos2() != null) {
                check++;
                sideIrregular.add("Registros fotográficos calçada 2: " + sideEntry.getSidePhotos2());
            }

            if (check > 0)
                AmbientAnalysis.checkHasAccessHeader();

            if (AmbientAnalysis.err) {
                AmbientAnalysis.sideLocationList.add("Calçada, ao longo da " + sideEntry.getSidewalkLocation() + ", com:");
                AmbientAnalysis.sideIrregular.put(i, sideIrregular);
            }
        }

        if (AmbientAnalysis.sideLocationList.size() == 0)
            AmbientAnalysis.sideLocationList.add("Não há cadastro de calçadas com irregularidades;");
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
