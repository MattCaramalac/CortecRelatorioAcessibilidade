package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;

import java.util.ArrayList;
import java.util.List;

public class PlaygroundAnalysis implements StandardMeasurements {

    static boolean irregularPlay;

    public static void playVerification(List<PlaygroundEntry> pList) {
        int i = 0;
        for (PlaygroundEntry play : pList) {
            int check = 0;
            AmbientAnalysis.err = false;
            List<String> pIrregular = new ArrayList<>();

            if (play.getPlayHasGate() == 1) {
                if (play.getPlayGateWidth() < freeSpaceGeneral) {
                    check++;
                    pIrregular.add("A largura do portão de acesso é inferior à " + freeSpaceGeneral + " m;");
                }

                if (play.getGateHasFloorTrack() == 1) {
                    if (play.getGateHasFloorTrack() > maxHeightTrack) {
                        check++;
                        pIrregular.add("A altura do trilho do portão no piso é superior à " + maxHeightTrack + " mm;");
                    }
                    if (play.getGateHasFloorTrack() == 1) {
                        if (play.getRampMeasure1() != null && play.getRampMeasure1() > maxSlopePerc ||
                                play.getRampMeasure2() != null && play.getRampMeasure2() > maxSlopePerc ||
                                play.getRampMeasure3() != null && play.getRampMeasure3() > maxSlopePerc ||
                                play.getRampMeasure4() != null && play.getRampMeasure4() > maxSlopePerc) {
                            check++;
                            pIrregular.add("A rampa associada ao trilho do portão possui inclinação superior à " + maxSlopePerc + "%");
                        }
                    }

                    if (play.getHasFloorGap() == 1) {
                        if (play.getFloorGap1() != null && play.getFloorGap1() > maxTrackGapSize ||
                                play.getFloorGap2() != null && play.getFloorGap2() > maxTrackGapSize ||
                                play.getFloorGap3() != null && play.getFloorGap3() > maxTrackGapSize ||
                                play.getFloorGap4() != null && play.getFloorGap4() > maxTrackGapSize) {
                            check++;
                            pIrregular.add("As frestas associadas ao trilho no piso possui largura superior à " + maxTrackGapSize + " mm;");
                        }
                    }
                }

                if (play.getGateSillType() > 0 && play.getGateSillType() < 4) {
                    if (play.getGateSillType() == 1) {
                        if (play.getHasSillIncl() == 0) {
                            check++;
                            pIrregular.add("A soleira do portão é composta de desnível não vencido por rampa");
                        } else {
                            if (play.getInclAngle1() != null && play.getInclAngle1() > maxSlopePerc ||
                                    play.getInclAngle2() != null && play.getInclAngle2() > maxSlopePerc ||
                                    play.getInclAngle3() != null && play.getInclAngle3() > maxSlopePerc ||
                                    play.getInclAngle4() != null && play.getInclAngle4() > maxSlopePerc) {
                                check++;
                                pIrregular.add("a inclinação da rampa para vencer o desnível é superior à " + maxSlopePerc + "%");
                            }
                        }
                    }
                    else if (play.getGateSillType() == 2) {
                        check++;
                        pIrregular.add("A soleira do portão é composta somente por degrau");
                    } else if (play.getGateSillType() == 3) {
                        if (play.getSlopeWidth() < minSillSlopeWidth) {
                            check++;
                            pIrregular.add("A largura da rampa na soleira do portão é inferior a " + minSillSlopeWidth + " m;");
                        }

                        if (play.getSlopeHeight() > highestRampHeight) {
                            check++;
                            pIrregular.add("altura da rampa da soleira acima de " + highestRampHeight + " m;");
                        } else if (play.getSlopeHeight() <= highestRampHeight && play.getSlopeHeight() > medRampHeight) {
                            if (play.getSlopeAngle1() != null && play.getSlopeAngle1() > minAngleRamp ||
                                    play.getSlopeAngle2() != null && play.getSlopeAngle2() > minAngleRamp ||
                                    play.getSlopeAngle3() != null && play.getSlopeAngle3() > minAngleRamp ||
                                    play.getSlopeAngle4() != null && play.getSlopeAngle4() > minAngleRamp) {
                                check++;
                                pIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + minAngleRamp + "%");
                            }
                        } else if (play.getSlopeHeight() <= medRampHeight && play.getSlopeHeight() > lowestRampHeight) {
                            if (play.getSlopeAngle1() != null && play.getSlopeAngle1() > medAngleRamp ||
                                    play.getSlopeAngle2() != null && play.getSlopeAngle2() > medAngleRamp ||
                                    play.getSlopeAngle3() != null && play.getSlopeAngle3() > medAngleRamp ||
                                    play.getSlopeAngle4() != null && play.getSlopeAngle4() > medAngleRamp) {
                                check++;
                                pIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                            }
                        } else if (play.getSlopeHeight() <= lowestRampHeight) {
                            if (play.getSlopeAngle1() != null && play.getSlopeAngle1() < medAngleRamp ||
                                    play.getSlopeAngle2() != null && play.getSlopeAngle2() < medAngleRamp ||
                                    play.getSlopeAngle3() != null && play.getSlopeAngle3() < medAngleRamp ||
                                    play.getSlopeAngle4() != null && play.getSlopeAngle4() < medAngleRamp) {
                                check++;
                                pIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                            } else if (play.getSlopeAngle1() != null && play.getSlopeAngle1() > maxAngleRamp ||
                                    play.getSlopeAngle2() != null && play.getSlopeAngle2() > maxAngleRamp ||
                                    play.getSlopeAngle3() != null && play.getSlopeAngle3() > maxAngleRamp ||
                                    play.getSlopeAngle4() != null && play.getSlopeAngle4() > maxAngleRamp) {
                                check++;
                                pIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + maxAngleRamp + "%");
                            }
                        }
                    }
                }

                if (play.getSillObs() != null && play.getSillObs().length() > 0) {
                    check++;
                    pIrregular.add("As seguintes observações devem ser feitas sobre a soleira do portão: " + play.getSillObs());
                }
            }



            if (play.getAccessibleFloor() == 0 && (play.getAccessFloorObs() != null && play.getAccessFloorObs().length() > 0)) {
                check++;
                pIrregular.add("O piso do parquinho infantil não pode ser considerado acessível pelos seguintes motivos: " + play.getAccessFloorObs());
            }
            else if (play.getAccessibleFloor() == 1 && (play.getAccessFloorObs() != null && play.getAccessFloorObs().length() > 0)) {
                check++;
                pIrregular.add("O piso do parquinho infantil pode ser considerado acessível, mas devem ser feitas as seguintes observações: " + play.getAccessFloorObs());
            }
            else if (play.getAccessibleFloor() == 0) {
                check++;
                pIrregular.add("O piso do parquinho infantil não pode ser considerado acessível");
            }

            if (play.getAccessibleEquip() == 0 && (play.getAccessEquipObs() != null && play.getAccessEquipObs().length() > 0)) {
                check++;
                pIrregular.add("Os brinquedos do parquinho infantil não podem ser considerados acessíveis pelos seguintes motivos: " + play.getAccessEquipObs());
            }
            else if (play.getAccessibleEquip() == 1 && (play.getAccessEquipObs() != null && play.getAccessEquipObs().length() > 0)) {
                check++;
                pIrregular.add("Os brinquedos do parquinho infantil podem ser considerado acessíveis, mas devem ser feitas as seguintes observações: " + play.getAccessEquipObs());
            }
            else if (play.getAccessibleEquip() == 0) {
                check++;
                pIrregular.add("Os brinquedos do parquinho infantil não podem ser considerados acessíveis");
            }

            if (play.getPlaygroundObs() != null && play.getPlaygroundObs().length() > 0) {
                check++;
                pIrregular.add("As seguintes observações devem ser feitas sobre este parquinho infantil: " + play.getPlaygroundObs());
            }

            if (check > 0)
                AmbientAnalysis.checkHelpAreaHeader();

            if (AmbientAnalysis.err) {
                AmbientAnalysis.playList.add("Parquinho infantil, localizado em " + play.getPlayLocation() + ", com as seguintes irregularidades:");
                AmbientAnalysis.playIrregular.put(i, pIrregular);
            }
        }
        if (AmbientAnalysis.playList.size() == 0)
            AmbientAnalysis.playList.add("Não há cadastro de parquinhos infantis com irregularidades;");
    }
}
