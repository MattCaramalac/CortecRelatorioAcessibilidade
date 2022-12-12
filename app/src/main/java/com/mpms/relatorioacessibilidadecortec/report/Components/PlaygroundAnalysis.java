package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.report.AmbientAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

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

            if (play.getPlayGateWidth() < freeSpaceGeneral) {
                check++;
                pIrregular.add("A largura do portão de acesso é inferior à " + freeSpaceGeneral + " m;");
            }

            if (play.getPlayGateHasFloorTrack() == 1) {
                if (play.getPlayFloorTrackHeight() > maxHeightTrack) {
                    check++;
                    pIrregular.add("A altura do trilho do portão no piso é superior à " + maxHeightTrack + " mm;");
                }
                if (play.getPlayGateHasFloorTrack() == 1) {
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

            if (play.getPlayGateSillType() > 1 && play.getPlayGateSillType() < 4) {
                if (play.getPlayGateSillType() == 2) {
                    check++;
                    pIrregular.add("A soleira do portão é composta somente por degrau");
                } else if (play.getPlayGateSillType() == 3) {
                    if (play.getSlopeSillWidth() < minSillSlopeWidth) {
                        check++;
                        pIrregular.add("A largura da rampa na soleira do portão é inferior a " + minSillSlopeWidth + " m;");
                    }

                    if (play.getSlopeSillHeight() > highestRampHeight) {
                        check++;
                        pIrregular.add("altura da rampa da soleira acima de " + highestRampHeight + " m;");
                    } else if (play.getSlopeSillHeight() <= highestRampHeight && play.getSlopeSillHeight() > medRampHeight) {
                        if (play.getSlopeSillAngle1() != null && play.getSlopeSillAngle1() > minAngleRamp ||
                                play.getSlopeSillAngle2() != null && play.getSlopeSillAngle2() > minAngleRamp ||
                                play.getSlopeSillAngle3() != null && play.getSlopeSillAngle3() > minAngleRamp ||
                                play.getSlopeSillAngle4() != null && play.getSlopeSillAngle4() > minAngleRamp) {
                            check++;
                            pIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + minAngleRamp + "%");
                        }
                    } else if (play.getSlopeSillHeight() <= medRampHeight && play.getSlopeSillHeight() > lowestRampHeight) {
                        if (play.getSlopeSillAngle1() != null && play.getSlopeSillAngle1() > medAngleRamp ||
                                play.getSlopeSillAngle2() != null && play.getSlopeSillAngle2() > medAngleRamp ||
                                play.getSlopeSillAngle3() != null && play.getSlopeSillAngle3() > medAngleRamp ||
                                play.getSlopeSillAngle4() != null && play.getSlopeSillAngle4() > medAngleRamp) {
                            check++;
                            pIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                        }
                    } else if (play.getSlopeSillHeight() <= lowestRampHeight) {
                        if (play.getSlopeSillAngle1() != null && play.getSlopeSillAngle1() < medAngleRamp ||
                                play.getSlopeSillAngle2() != null && play.getSlopeSillAngle2() < medAngleRamp ||
                                play.getSlopeSillAngle3() != null && play.getSlopeSillAngle3() < medAngleRamp ||
                                play.getSlopeSillAngle4() != null && play.getSlopeSillAngle4() < medAngleRamp) {
                            check++;
                            pIrregular.add("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                        } else if (play.getSlopeSillAngle1() != null && play.getSlopeSillAngle1() > maxAngleRamp ||
                                play.getSlopeSillAngle2() != null && play.getSlopeSillAngle2() > maxAngleRamp ||
                                play.getSlopeSillAngle3() != null && play.getSlopeSillAngle3() > maxAngleRamp ||
                                play.getSlopeSillAngle4() != null && play.getSlopeSillAngle4() > maxAngleRamp) {
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

            if (play.getAccessibleFloor() == 0 && (play.getAccessibleFloorObs() != null && play.getAccessibleFloorObs().length() > 0)) {
                check++;
                pIrregular.add("O piso do playground não pode ser considerado acessível pelos seguintes motivos: " + play.getAccessibleFloorObs());
            }
            else if (play.getAccessibleFloor() == 1 && (play.getAccessibleFloorObs() != null && play.getAccessibleFloorObs().length() > 0)) {
                check++;
                pIrregular.add("O piso do playground pode ser considerado acessível, mas devem ser feitas as seguintes observações: " + play.getAccessibleFloorObs());
            }
            else if (play.getAccessibleFloor() == 0) {
                check++;
                pIrregular.add("O piso do playground não pode ser considerado acessível");
            }

            if (play.getAccessibleEquip() == 0 && (play.getAccessibleEquipObs() != null && play.getAccessibleEquipObs().length() > 0)) {
                check++;
                pIrregular.add("Os brinquedos do playground não podem ser considerados acessíveis pelos seguintes motivos: " + play.getAccessibleEquipObs());
            }
            else if (play.getAccessibleEquip() == 1 && (play.getAccessibleEquipObs() != null && play.getAccessibleEquipObs().length() > 0)) {
                check++;
                pIrregular.add("Os brinquedos do playground podem ser considerado acessíveis, mas devem ser feitas as seguintes observações: " + play.getAccessibleEquipObs());
            }
            else if (play.getAccessibleEquip() == 0) {
                check++;
                pIrregular.add("Os brinquedos do playground não podem ser considerados acessíveis");
            }

            if (play.getPlaygroundObs() != null && play.getPlaygroundObs().length() > 0) {
                check++;
                pIrregular.add("As seguintes observações devem ser feitas sobre este playground: " + play.getPlaygroundObs());
            }

            if (check > 0)
                AmbientAnalysis.checkHelpAreaHeader();

            if (AmbientAnalysis.err) {
                AmbientAnalysis.playList.add("Playground, localizado em " + play.getPlayLocation() + ", com as seguintes irregularidades:");
                AmbientAnalysis.playIrregular.put(i, pIrregular);
            }


        }
    }
}
