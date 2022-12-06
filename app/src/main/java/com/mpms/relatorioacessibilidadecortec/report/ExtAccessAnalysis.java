package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;

import java.util.ArrayList;
import java.util.List;

public class ExtAccessAnalysis implements StandardMeasurements {

    static boolean irregularLock;
    static boolean irregularPhone;
    static boolean irregularObs;
    static boolean irregularStairs;
    static boolean irregularRamp;

    public static void extAccessVerification(List<ExternalAccess> extAccess, List<DoorLockEntry> gateLock, List<GateObsEntry> extObs, List<PayPhoneEntry> extPhone,
                                             List<RampStairsEntry> rStGate, List<RampStairsFlightEntry> rStFlight, List<RampStairsRailingEntry> rStRail,
                                             List<RampStairsHandrailEntry> rStHandrail) {

        String irrPhoneData;
        String irrObsData;
        String irrLockData;
        String irrStairs;
        String irrRamp;

        for (int i = 0; i < extAccess.size(); i++) {
            int check = 0;
            AmbientAnalysis.err = false;
            List<String> extIrregular = new ArrayList<>();
            ExternalAccess access = extAccess.get(i);

            if (access.getFloorIsAccessible() == 0) {
                check++;
                extIrregular.add("O piso não pode ser considerado acessível devido aos seguintes fatores: "
                        + access.getAccessibleFloorObs() + ";");
            }

            if (access.getEntranceType() == 0) {
                if (access.getHasSIA() == 0) {
                    check++;
                    if (access.getObsSIA() != null)
                        extIrregular.add("Ausência de SIA, com as seguintes observações: " + access.getObsSIA() + ";");
                    else
                        extIrregular.add("Ausência de SIA");
                } else if (access.getHasSIA() == 1 && access.getObsSIA() != null)
                    extIrregular.add("Entrada possui SIA, porém devem ser feitas as seguintes observações: " + access.getObsSIA() + ";");

                if (access.getEntranceGateType() == 0) {
                    if (access.getFreeSpaceWidth1() < freeSpaceGeneral) {
                        check++;
                        extIrregular.add("A Largura do vão livre do portão é inferior à " + freeSpaceGeneral + "m;");
                    }
                } else if (access.getEntranceGateType() == 1) {
                    if (access.getFreeSpaceWidth1() < freeSpaceGeneral || access.getFreeSpaceWidth2() < freeSpaceGeneral) {
                        check++;
                        extIrregular.add("A largura do vão livre de uma das folhas do portão é inferior à " + freeSpaceGeneral + "m;");
                    } else if (access.getFreeSpaceWidth1() < freeSpaceGeneral && access.getFreeSpaceWidth2() < freeSpaceGeneral) {
                        check++;
                        extIrregular.add("A largura do vão livre de ambas as folhas do portão é inferior à " + freeSpaceGeneral + "m;");
                    }
                } else if (access.getEntranceGateType() == 2) {
                    if (access.getFreeSpaceWidth1() < freeSpaceGeneral) {
                        check++;
                        extIrregular.add("A Largura do vão livre do portão de tipo " + access.getEntranceGateDesc()
                                + "é inferior à " + freeSpaceGeneral + "m;");
                    }
                }

                if (access.getGateHandleType() == 0) {
                    if (access.getGateHandleHeight() < minDoorHandle) {
                        check++;
                        extIrregular.add("A altura do dispositivo de abertura do portão é inferior à " + minDoorHandle + " m;");
                    } else if (access.getGateHandleHeight() > maxDoorHandle) {
                        check++;
                        extIrregular.add("A altura do dispositivo de abertura do portão é superior à " + maxDoorHandle + " m;");
                    }
                } else if (access.getGateHandleType() == 1) {
                    if (access.getGateHandleHeight() < minDoorHandle) {
                        check++;
                        extIrregular.add("O dispositivo de abertura do portão não é do tipo alavanca e sua altura é inferior à " + minDoorHandle + "m;");
                    } else if (access.getGateHandleHeight() > maxDoorHandle) {
                        check++;
                        extIrregular.add("O dispositivo de abertura do portão não é do tipo alavanca e sua altura é superior à " + maxDoorHandle + " m;");
                    } else {
                        check++;
                        extIrregular.add("O dispositivo de abertura do portão não é do tipo alavanca;");
                    }
                }

                if (gateLock.size() > 0) {
                    for (DoorLockEntry lock : gateLock) {
                        irregularLock = false;
                        if (lock.getExtAccessID() == access.getExternalAccessID()) {
                            StringBuilder builder = new StringBuilder();
                            if (lock.getLockType() > 1) {
                                lockIrregular(builder);
                                builder.append("tipo de dispositivo de travamento não atende aos princípios do desenho universal");
                            }
                            if (lock.getLockHeight() < minDoorHandle) {
                                lockIrregular(builder);
                                builder.append("altura do dispositivo inferior a " + minDoorHandle + " m");
                            } else if (lock.getLockHeight() > maxDoorHandle) {
                                lockIrregular(builder);
                                builder.append("altura do dispositivo superior a " + maxDoorHandle + " m");
                            }

                            if (lock.getLockObs() != null) {
                                lockIrregular(builder);
                                builder.append("devem ser apontadas as seguintes observações: ").append(lock.getLockObs());
                            }

                            if (irregularLock && builder.length() != 0) {
                                check++;
                                builder.replace(45, 46, " " + lock.getLockType() + " ");
                                irrLockData = builder.toString();
                                extIrregular.add(irrLockData);

                            }
                        }
                    }
                }

                if (access.getGateObs() != null) {
                    check++;
                    extIrregular.add("O portão possui as seguintes observações a serem pontuadas: " + access.getGateObs() + ";");
                }

                if (access.getGateHasTracks() == 1) {
                    if (access.getGateTrackHeight() > maxHeightTrack) {
                        check++;
                        extIrregular.add("A altura do trilho do portão é superior à " + maxHeightTrack + "mm;");
                    }
                    if (access.getTrackRampMeasure1() != null && access.getTrackRampMeasure1() > maxSlopePerc ||
                            access.getTrackRampMeasure2() != null && access.getTrackRampMeasure2() > maxSlopePerc ||
                            access.getTrackRampMeasure3() != null && access.getTrackRampMeasure3() > maxSlopePerc ||
                            access.getTrackRampMeasure4() != null && access.getTrackRampMeasure4() > maxSlopePerc) {
                        check++;
                        extIrregular.add("A rampa associada ao trilho do portão possui inclinação superior à " + maxSlopePerc + "%");
                    }
                }

                if (access.getHasFloorGap() == 1) {
                    if (access.getGapMeasure1() != null && access.getGapMeasure1() > maxTrackGapSize ||
                            access.getGapMeasure2() != null && access.getGapMeasure2() > maxTrackGapSize ||
                            access.getGapMeasure3() != null && access.getGapMeasure3() > maxTrackGapSize ||
                            access.getGapMeasure4() != null && access.getGapMeasure4() > maxTrackGapSize) {
                        check++;
                        extIrregular.add("As frestas associadas ao trilho no piso possui largura superior à " + maxTrackGapSize + " mm;");
                    }
                }

                if (access.getGateSillType() > 1 && access.getGateSillType() < 4) {
                    if (access.getGateSillType() == 2) {
                        check++;
                        extIrregular.add("A soleira do portão é composta somente por degrau");
                    } else if (access.getGateSillType() == 3) {
                        if (access.getSillSlopeWidth() < minSillSlopeWidth) {
                            check++;
                            extIrregular.add("A largura da rampa na soleira do portão é inferior a " + minSillSlopeWidth + " m;");
                        }

                        if (access.getSillSlopeHeight() > highestRampHeight) {
                            check++;
                            extIrregular.add("altura da rampa da soleira acima de " + highestRampHeight + " m;");
                        } else if (access.getSillSlopeHeight() <= highestRampHeight && access.getSillSlopeHeight() > medRampHeight) {
                            if (access.getSillSlopeAngle() != null && access.getSillSlopeAngle() > minAngleRamp ||
                                    access.getSillSlopeAngle2() != null && access.getSillSlopeAngle2() > minAngleRamp ||
                                    access.getSillSlopeAngle3() != null && access.getSillSlopeAngle3() > minAngleRamp ||
                                    access.getSillSlopeAngle4() != null && access.getSillSlopeAngle4() > minAngleRamp) {
                                check++;
                                extIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + minAngleRamp + "%");
                            }
                        } else if (access.getSillSlopeHeight() <= medRampHeight && access.getSillSlopeHeight() > lowestRampHeight) {
                            if (access.getSillSlopeAngle() != null && access.getSillSlopeAngle() > medAngleRamp ||
                                    access.getSillSlopeAngle2() != null && access.getSillSlopeAngle2() > medAngleRamp ||
                                    access.getSillSlopeAngle3() != null && access.getSillSlopeAngle3() > medAngleRamp ||
                                    access.getSillSlopeAngle4() != null && access.getSillSlopeAngle4() > medAngleRamp) {
                                check++;
                                extIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                            }
                        } else if (access.getSillSlopeHeight() <= lowestRampHeight) {
                            if (access.getSillSlopeAngle() != null && access.getSillSlopeAngle() < medAngleRamp ||
                                    access.getSillSlopeAngle2() != null && access.getSillSlopeAngle2() < medAngleRamp ||
                                    access.getSillSlopeAngle3() != null && access.getSillSlopeAngle3() < medAngleRamp ||
                                    access.getSillSlopeAngle4() != null && access.getSillSlopeAngle4() < medAngleRamp) {
                                check++;
                                extIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                            } else if (access.getSillSlopeAngle() != null && access.getSillSlopeAngle() > maxAngleRamp ||
                                    access.getSillSlopeAngle2() != null && access.getSillSlopeAngle2() > maxAngleRamp ||
                                    access.getSillSlopeAngle3() != null && access.getSillSlopeAngle3() > maxAngleRamp ||
                                    access.getSillSlopeAngle4() != null && access.getSillSlopeAngle4() > maxAngleRamp) {
                                check++;
                                extIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + maxAngleRamp + "%");
                            }
                        }
                    }
                }

                if (access.getGateSillObs() != null) {
                    check++;
                    extIrregular.add("As seguintes observações devem ser feitas sobre a soleira do portão: " + access.getGateSillObs());
                }

                if (access.getGateHasObstacles() == 1) {
                    if (extObs.size() > 1) {
                        for (GateObsEntry obs : extObs) {
                            irregularObs = false;
                            if (obs.getExtAccessID() == access.getExternalAccessID()) {
                                StringBuilder builder = new StringBuilder();
                                if (obs.getObsHeight() < minSwHeight) { //provisório
                                    obsIrregular(builder);
                                    builder.append("Altura do comando do dispositivo de segurança é inferior à " + minSwHeight + " m");
                                } else if (obs.getObsHeight() > maxSwHeight) { //provisório
                                    obsIrregular(builder);
                                    builder.append("Altura do comando do dispositivo de segurança superior à " + maxSwHeight + " m");
                                }

                                if (obs.getObsWidth() < freeSpaceGeneral) {
                                    obsIrregular(builder);
                                    builder.append("Largura do vão livre do dispositivo de segurança é inferior à " + freeSpaceGeneral + " m");
                                }

                                if (obs.getObsHasSia() == 0) {
                                    obsIrregular(builder);
                                    builder.append("O dispositivo de segurança não possui SIA");
                                }

                                if (obs.getGateObstacleObs() != null) {
                                    obsIrregular(builder);
                                    builder.append("As seguintes observações devem ser feitas em relação a este dispositivo de segurança: ")
                                            .append(obs.getGateObstacleObs());
                                }

                                if (irregularObs && builder.length() != 0) {
                                    check++;
                                    builder.replace(37, 38, " do tipo " + obs.getAccessType() + " e localizado em " + obs.getAccessRefPoint() + ", ");
                                    irrObsData = builder.toString();
                                    extIrregular.add(irrObsData);

                                }
                            }
                        }
                    }
                }

                if (access.getGateHasPayphones() == 1) {
                    if (extPhone.size() > 0) {
                        for (PayPhoneEntry phone : extPhone) {
                            irregularPhone = false;
                            if (phone.getExtAccessID() == access.getExternalAccessID()) {
                                StringBuilder builder = new StringBuilder();
                                if (phone.getPhoneHeight() < minIntTelHeight) {
                                    phoneIrregular(builder);
                                    builder.append("Altura operacional do fone inferior a " + minIntTelHeight + " m");
                                } else if (phone.getPhoneHeight() > minIntTelHeight) {
                                    phoneIrregular(builder);
                                    builder.append("Altura operacional do fone superior a " + maxIntTelHeight + " m");
                                }

                                if (phone.getPhoneKeyboardHeight() < minIntTelHeight) {
                                    phoneIrregular(builder);
                                    builder.append("Altura operacional do teclado inferior a " + minIntTelHeight + " m");
                                } else if (phone.getPhoneKeyboardHeight() > minIntTelHeight) {
                                    phoneIrregular(builder);
                                    builder.append("Altura operacional do teclado superior a " + maxIntTelHeight + " m");
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

                                    if (phone.getPayPhoneObs() != null) {
                                        phoneIrregular(builder);
                                        builder.append("As seguintes observações devem ser feitas sobre este telefone público: ").append(phone.getPayPhoneObs());
                                    }
                                }

                                if (irregularPhone && builder.length() != 0) {
                                    check++;
                                    builder.insert(29, "localizado em " + phone.getPhoneRefPoint() + " ");
                                    irrPhoneData = builder.toString();
                                    extIrregular.add(irrPhoneData);

                                }
                            }
                        }
                    }
                }

                if (access.getGateHasIntercom() == 1) {
                    if (access.getIntercomHeight() < minIntTelHeight) {
                        check++;
                        extIrregular.add("Altura operacional do interfone é inferior à " + minIntTelHeight + "m;");
                    } else if (access.getIntercomHeight() > maxIntTelHeight) {
                        check++;
                        extIrregular.add("Altura operacional do interfone é superior à " + maxIntTelHeight + "m;");
                    }
                }

                if (access.getGateHasStairs() == 1) {
                    List<String> stAnalysis = StairsAnalysis.stairsVerification(access.getExternalAccessID(), rStGate, rStFlight, rStRail, rStHandrail);
                    if (stAnalysis.size() > 0) {
                        extIrregular.addAll(stAnalysis);
                    }

                }

                if (access.getGateHasRamps() == 1) {
                    List<String> rampAnalysis = RampAnalysis.rampVerification(access.getExternalAccessID(), rStGate, rStFlight, rStRail, rStHandrail);
                    if (rampAnalysis.size() > 0)
                        extIrregular.addAll(rampAnalysis);
                }
            } else {
                if (access.getGateHasSoundSign() == 0) {
                    check++;
                    extIrregular.add("Ausência de sinal sonoro intermitente");
                }
            }

            if (check > 0)
                AmbientAnalysis.checkHasAccessHeader();

            if (AmbientAnalysis.err) {
                if (access.getEntranceType() == 0)
                    AmbientAnalysis.extAccessList.add("Entrada social, localizada na " + access.getAccessLocation() + " com as seguintes irregularidades: ");
                else
                    AmbientAnalysis.extAccessList.add("Entrada para Veículos, localizada na " + access.getAccessLocation() + " com as seguintes irregularidades: ");
                AmbientAnalysis.extIrregularities.put(i, extIrregular);

            }

        }
    }

    public static void lockIrregular(StringBuilder builder) {
        if (!irregularLock) {
            irregularLock = true;
            builder.append("Presença de dispositivo de travamento do tipo com as seguintes irregularidades: "); //45 46
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

    public static void obsIrregular(StringBuilder builder) {
        if (!irregularObs) {
            irregularObs = true;
            builder.append("Presença de dispositivo de segurança, com as seguintes irregularidades: ");
        } else {
            builder.append(", ");
        }
    }


}
