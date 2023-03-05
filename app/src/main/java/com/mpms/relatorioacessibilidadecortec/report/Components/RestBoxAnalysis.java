package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class RestBoxAnalysis implements StandardMeasurements {

    static boolean irregularBox;
    static boolean irrSoculo;
    static int nBox = 0;

    public static List<String> boxTextList(int restID, List<RestBoxEntry> boxList, List<DoorEntry> boxDoor) {

        List<String> bList = new ArrayList<>();
        nBox = 0;
        if (boxList.size() > 0) {
            for (RestBoxEntry box : boxList) {
                irregularBox = false;
                irrSoculo = false;
                String analysis = null;
                if (box.getRestID() == restID) {
                    nBox++;
                    analysis = boxText(box, boxDoor);
                }
                if (analysis != null && analysis.length() > 0)
                    bList.add(analysis);
            }
        }
        return bList;
    }

    private static String boxText(RestBoxEntry box, List<DoorEntry> boxDoor) {
        StringBuilder builder = new StringBuilder();
        if (box.getTypeBox() == 0) {
            if (box.getComBoxDoorWidth() < freeSpaceGeneral) {
                irrText(builder);
                builder.append("a largura do vão livre da porta do box é inferior à " + freeSpaceGeneral + " m");
            }
            if (box.getComBoxFreeDiam() < minFreeDiamComBox) {
                irrText(builder);
                builder.append("a área livre do box possui diâmetro inferior à " + minFreeDiamComBox + " m");
            }
            if (box.getComBoxHasBars() == 1) {
                if (box.getComBoxWidth() < minComBoxWidth) {
                    irrText(builder);
                    builder.append("a largura do box é inferior à " + minComBoxWidth + " m");
                }
                if (box.getComBoxToiletDoorDist() < minComBoxDoorToiletDist) {
                    irrText(builder);
                    builder.append("a distância entre a louça sanitária e a porta do box é inferior à " + minComBoxDoorToiletDist + " m");
                }
                if (box.getComBoxHasLeftBar() == 0) {
                    if (box.getComBoxLeftShapeBarA() < minComBoxBarLength) {
                        irrText(builder);
                        builder.append("o comprimento da parte horizontal da barra em L à esquerda é inferior à " + minComBoxBarLength + " m");
                    }
                    if (box.getComBoxLeftShapeBarB() < minComBoxBarLength) {
                        irrText(builder);
                        builder.append("o comprimento da parte vertical da barra em L à esquerda  é inferior à " + minComBoxBarLength + " m");
                    }
                    if (box.getComBoxLeftShapeBarC() != comBoxBarHeight) {
                        irrText(builder);
                        builder.append("a altura do piso até a parte horizontal da barra à esquerda é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxLeftShapeBarD() != comBoxToiletBarDist) {
                        irrText(builder);
                        builder.append("a distância entre o final da louça sanitária e a curvatura da barra à esquerda é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxLeftShapeBarDiam() < minHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra à esquerda é inferior à " + minHandleGrip + " mm");
                    } else if (box.getComBoxLeftShapeBarDiam() > maxHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra à esquerda é superior à " + maxHandleGrip + " mm");
                    }
                    if (box.getComBoxLeftShapeBarDist() < minDistHandrail) {
                        irrText(builder);
                        builder.append("a distância entre a superfície da barra à esquerda e a parede do box é inferior à  " + minDistHandrail + " mm");
                    }
                    if (box.getComBoxLeftHorObs() != null && box.getComBoxLeftHorObs().length() > 0) {
                        irrText(builder);
                        builder.append("as seguintes observações podem ser feitas sobre a barra em L à esquerda: ").append(box.getComBoxLeftHorObs());
                    }
                } else if (box.getComBoxHasLeftBar() == 1) {
                    if (box.getComBoxLeftShapeBarA() < minComBoxBarLength) {
                        irrText(builder);
                        builder.append("o comprimento da barra horizontal à esquerda é inferior à " + minComBoxBarLength + " m");
                    }
                    if (box.getComBoxLeftShapeBarB() != comBoxBarHeight) {
                        irrText(builder);
                        builder.append("a altura do piso até a barra horizontal à esquerda é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxLeftShapeBarC() != comBoxToiletBarDist) {
                        irrText(builder);
                        builder.append("a distância entre o final da louça sanitária e o final da barra horizontal à esquerda é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxLeftShapeBarDiam() < minHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra horizontal à esquerda é inferior à " + minHandleGrip + " mm");
                    } else if (box.getComBoxLeftShapeBarDiam() > maxHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra horizontal à esquerda é superior à " + maxHandleGrip + " mm");
                    }
                    if (box.getComBoxLeftShapeBarDist() < minDistHandrail) {
                        irrText(builder);
                        builder.append("a distância entre a superfície da barra horizontal à esquerda e a parede do box é inferior à  " + minDistHandrail + " mm");
                    }
                    if (box.getComBoxLeftHorObs() != null && box.getComBoxLeftHorObs().length() > 0) {
                        irrText(builder);
                        builder.append("as seguintes observações podem ser feitas sobre a barra horizontal à esquerda: ").append(box.getComBoxLeftHorObs());
                    }

                    if (box.getComBoxLeftVertBarA() < minComBoxBarLength) {
                        irrText(builder);
                        builder.append("o comprimento da barra vertical à esquerda é inferior à " + minComBoxBarLength + " m");
                    }
                    if (box.getComBoxLeftVertBarB() != comBoxBarHeight) {
                        irrText(builder);
                        builder.append("a altura do piso até a barra vertical à esquerda é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxLeftVertBarC() != comBoxToiletBarDist) {
                        irrText(builder);
                        builder.append("a distância entre o final da louça sanitária e o eixo da barra vertical à esquerda é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxLeftVertBarDiam() < minHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra vertical à esquerda é inferior à " + minHandleGrip + " mm");
                    } else if (box.getComBoxLeftVertBarDiam() > maxHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra vertical à esquerda é superior à " + maxHandleGrip + " mm");
                    }
                    if (box.getComBoxLeftVertBarDist() < minDistHandrail) {
                        irrText(builder);
                        builder.append("a distância entre a superfície da barra vertical à esquerda e a parede do box é inferior à  " + minDistHandrail + " mm");
                    }
                    if (box.getComBoxLeftVertObs() != null && box.getComBoxLeftVertObs().length() > 0) {
                        irrText(builder);
                        builder.append("as seguintes observações podem ser feitas sobre a barra vertical à esquerda: ").append(box.getComBoxLeftHorObs());
                    }

                } else if (box.getComBoxHasLeftBar() == 2) {
                    irrText(builder);
                    builder.append("a barra de apoio esquerda do box não está de acordo com a norma");
                } else {
                    irrText(builder);
                    builder.append("o box não possui barra de apoio lateral esquerdo");
                }

                if (box.getComBoxHasRightBar() == 0) {
                    if (box.getComBoxRightShapeBarA() < minComBoxBarLength) {
                        irrText(builder);
                        builder.append("o comprimento da parte horizontal da barra em L à direita é inferior à " + minComBoxBarLength + " m");
                    }
                    if (box.getComBoxRightShapeBarB() < minComBoxBarLength) {
                        irrText(builder);
                        builder.append("o comprimento da parte vertical da barra em L à direita é inferior à " + minComBoxBarLength + " m");
                    }
                    if (box.getComBoxRightShapeBarC() != comBoxBarHeight) {
                        irrText(builder);
                        builder.append("a altura do piso até a parte horizontal da barra à direita é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxRightShapeBarD() != comBoxToiletBarDist) {
                        irrText(builder);
                        builder.append("a distância entre o final da louça sanitária e a curvatura da barra  à direita é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxRightShapeBarDiam() < minHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra  à direita é inferior à " + minHandleGrip + " mm");
                    } else if (box.getComBoxRightShapeBarDiam() > maxHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra à direita é superior à " + maxHandleGrip + " mm");
                    }
                    if (box.getComBoxRightShapeBarDist() < minDistHandrail) {
                        irrText(builder);
                        builder.append("a distância entre a superfície da barra à direita e a parede do box é inferior à  " + minDistHandrail + " mm");
                    }
                    if (box.getComBoxRightHorObs() != null && box.getComBoxRightHorObs().length() > 0) {
                        irrText(builder);
                        builder.append("as seguintes observações podem ser feitas sobre a barra em L à direita: ").append(box.getComBoxRightHorObs());
                    }
                } else if (box.getComBoxHasRightBar() == 1) {
                    if (box.getComBoxRightShapeBarA() < minComBoxBarLength) {
                        irrText(builder);
                        builder.append("o comprimento da barra horizontal à direita é inferior à " + minComBoxBarLength + " m");
                    }
                    if (box.getComBoxRightShapeBarB() != comBoxBarHeight) {
                        irrText(builder);
                        builder.append("a altura do piso até a barra horizontal à direita é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxRightShapeBarC() != comBoxToiletBarDist) {
                        irrText(builder);
                        builder.append("a distância entre o final da louça sanitária e o final da barra horizontal à direita é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxRightShapeBarDiam() < minHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra horizontal à direita é inferior à " + minHandleGrip + " mm");
                    } else if (box.getComBoxRightShapeBarDiam() > maxHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra horizontal à direita é superior à " + maxHandleGrip + " mm");
                    }
                    if (box.getComBoxRightShapeBarDist() < minDistHandrail) {
                        irrText(builder);
                        builder.append("a distância entre a superfície da barra horizontal à direita e a parede do box é inferior à  " + minDistHandrail + " mm");
                    }
                    if (box.getComBoxRightHorObs() != null && box.getComBoxRightHorObs().length() > 0) {
                        irrText(builder);
                        builder.append("as seguintes observações podem ser feitas sobre a barra horizontal à direita: ").append(box.getComBoxRightHorObs());
                    }

                    if (box.getComBoxRightVertBarA() < minComBoxBarLength) {
                        irrText(builder);
                        builder.append("o comprimento da barra vertical à direita é inferior à " + minComBoxBarLength + " m");
                    }
                    if (box.getComBoxRightVertBarB() != comBoxBarHeight) {
                        irrText(builder);
                        builder.append("a altura do piso até a barra vertical à direita é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxRightVertBarC() != comBoxToiletBarDist) {
                        irrText(builder);
                        builder.append("a distância entre o final da louça sanitária e o eixo da barra vertical à direita é diferente de" + comBoxBarHeight + " m");
                    }
                    if (box.getComBoxRightVertBarDiam() < minHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra vertical à direita é inferior à " + minHandleGrip + " mm");
                    } else if (box.getComBoxRightVertBarDiam() > maxHandleGrip) {
                        irrText(builder);
                        builder.append("o diâmetro da seção transversal da barra vertical à direita é superior à " + maxHandleGrip + " mm");
                    }
                    if (box.getComBoxRightVertBarDist() < minDistHandrail) {
                        irrText(builder);
                        builder.append("a distância entre a superfície da barra vertical à direita e a parede do box é inferior à  " + minDistHandrail + " mm");
                    }
                    if (box.getComBoxRightVertObs() != null && box.getComBoxRightVertObs().length() > 0) {
                        irrText(builder);
                        builder.append("as seguintes observações podem ser feitas sobre a barra vertical à direita: ").append(box.getComBoxRightHorObs());
                    }

                } else if (box.getComBoxHasRightBar() == 2) {
                    irrText(builder);
                    builder.append("a barra de apoio à direita não está de acordo com a norma");
                } else {
                    irrText(builder);
                    builder.append("o box não possui barra de apoio lateral à direita");
                }
            }
        } else {
            if (box.getUpViewMeasureA() < minDistToiletWall) {
                irrText(builder);
                builder.append("distância entre o sanitário e a parede lateral é inferior à " + minDistToiletWall + " m");
            }
            if (box.getUpViewMeasureB() != null && box.getUpViewMeasureB() > maxManUnderToilet) {
                irrText(builder);
                builder.append("a área de manobra utiliza mais do que " + maxManUnderToilet + " m sob a bacia sanitária");
            }
            if (box.getUpViewMeasureC() < maneuverArea) {
                irrText(builder);
                builder.append("a área de manobra possui diâmetro inferior à " + maneuverArea + " m");
            }
            if (box.getUpViewMeasureD() != null && box.getUpViewMeasureD() > maxManUnderSink) {
                irrText(builder);
                builder.append("a área de manobra utiliza mais do que " + maxManUnderSink + " m sob o lavatório");
            }
            if (box.getUpViewObs() != null && box.getUpViewObs().length() > 0) {
                irrText(builder);
                builder.append("as seguintes observações podem ser feitas sobre a vista superior do sanitário: ").append(box.getUpViewObs());
            }

            if (box.getRestDrain() == 1) {
                irrText(builder);
                builder.append("presença de grelhas e/ou ralos na área de manobra;");
            } else if (box.getRestDrain() == 0 && box.getRestDrainObs() != null && box.getRestDrainObs().length() > 0) {
                irrText(builder);
                builder.append("presença de grelhas e/ou ralos na área de manobra e as seguintes observações devem ser feitas: ").append(box.getRestDrainObs());
            } else if (box.getRestDrain() == 1 && box.getRestDrainObs() != null && box.getRestDrainObs().length() > 0) {
                irrText(builder);
                builder.append("presença de piso antiderrapante, mas possui as seguintes observações: ").append(box.getRestDrainObs());
            }

            if (boxDoor.size() > 0) {
                List<String> doorBox = DoorAnalysis.restBoxDoorVerification(box.getBoxID(), boxDoor, true);
                if (doorBox.size() > 0) {
                    irrText(builder);
                    builder.append(doorBox.get(0));
                }
            }

            int toiletType = box.getToType();

            if (toiletType != 1 && box.getToHasSoculo() == 1) {
                StringBuilder builder2 = new StringBuilder();
                if (box.getFrSoculo() > maxSoculo) {
                    soculoIrregular(builder2);
                    builder2.append("projeção frontal superior à " + maxSoculo + " m");
                }
                if (box.getLatSoculo() > maxSoculo) {
                    soculoIrregular(builder2);
                    builder2.append("projeção lateral superior à " + maxSoculo + " m");
                }
                if (box.getSocCorners() == 1) {
                    soculoIrregular(builder2);
                    builder2.append("presença de cantos vivos");
                }

                if (builder2.toString().length() > 0) {
                    irrText(builder2);
                    builder.append(builder2.toString());
                }
            }

            if (box.getToHeightNoSeat() < minToiletHeightAdult) {
                irrText(builder);
                builder.append("a altura da bacia sanitária é inferior à " + minToiletHeightAdult + " m");
            } else if (box.getToHeightNoSeat() > maxToiletHeightAdult) {
                irrText(builder);
                builder.append("a altura da bacia sanitária é superior à " + maxToiletHeightAdult + " m");
            }

            if (box.getToHasSeat() == 0) {
                irrText(builder);
                builder.append("a bacia sanitária não possui assento");
            } else if (box.getToHeightSeat() > maxToiletHeightAdultSeat) {
                irrText(builder);
                builder.append("a altura da bacia sanitária com assento é superior à " + maxToiletHeightAdultSeat + " m");
            }

            if (box.getToHasFrontBar() == 0) {
                irrText(builder);
                builder.append("ausência da barra de apoio posterior");
            } else {
                if (toiletType == 2 && box.getFrBarA() < frBarHeightAdult) {
                    irrText(builder);
                    builder.append("a altura da barra de apoio posterior está abaixo de " + frBarHeightAdult + " m");
                } else if (toiletType == 2 && box.getFrBarA() > frBarMaxHeightAdult) {
                    irrText(builder);
                    builder.append("a altura da barra de apoio posterior está acima de " + frBarMaxHeightAdult + " m");
                } else if (toiletType != 2 && box.getFrBarA() != frBarHeightAdult) {
                    irrText(builder);
                    builder.append("a altura da barra de apoio posterior é diferente de " + frBarHeightAdult + " m");
                }

                if (box.getFrBarB() < frBarMinLength) {
                    irrText(builder);
                    builder.append("o comprimento da barra de apoio posterior é inferior à " + frBarMinLength + " m");
                }

                if (box.getFrBarSect() < minHandrailGrip) {
                    irrText(builder);
                    builder.append("a seção transversal da barra de apoio posterior é inferior à " + minHandrailGrip + " mm");
                } else if (box.getFrBarSect() > maxHandrailGrip) {
                    irrText(builder);
                    builder.append("a seção transversal da barra de apoio posterior é superior à " + maxHandrailGrip + " mm");
                }

                if (box.getFrBarDist() < minDistHandrail) {
                    irrText(builder);
                    builder.append("a distância da parede até a barra de apoio posterior é inferior à " + minDistHandrail + " mm");
                }

                if (box.getFrBarC() != frBarToiletAxisAdult) {
                    irrText(builder);
                    builder.append("a distância entre o eixo da bacia sanitária até a extremidade da barra de apoio posterior em direção à parede lateral " +
                            "é diferente de " + frBarToiletAxisAdult + " m;");
                }
            }

            if (box.getToHasWall() == 0) {
                if (box.getHasSideBar() == 0) {
                    irrText(builder);
                    builder.append("o sanitário não possui barra reta lateral fixa");
                } else {
                    if (box.getSideBarD() < fixSideBarMinLengthToilet) {
                        irrText(builder);
                        builder.append("o comprimento mínimo da barra lateral fixa à frente da bacia sanitária é inferior à " + fixSideBarMinLengthToilet + " m");
                    }

                    if (box.getSideBarE() != frBarHeightAdult) {
                        irrText(builder);
                        builder.append("a altura da barra lateral fixa é diferente de " + frBarHeightAdult + " m");
                    }

                    if (box.getSideBarDistG() != sideBarToiletAxisAdult) {
                        irrText(builder);
                        builder.append("a distância do eixo da bacia sanitária até a barra lateral fixa é diferente de " + sideBarToiletAxisAdult + " m");
                    }

                    if (box.getSideBarSect() < minHandrailGrip) {
                        irrText(builder);
                        builder.append("a seção transversal da barra lateral fixa é inferior à " + minHandrailGrip + " mm");
                    } else if (box.getSideBarSect() > maxHandrailGrip) {
                        irrText(builder);
                        builder.append("a seção transversal da barra lateral fixa é superior à " + maxHandrailGrip + " mm");
                    }
                }

                if (box.getHasArtBar() == 0) {
                    irrText(builder);
                    builder.append("a bacia sanitária não possui barra lateral articulada");
                } else if (box.getHasArtBar() == 1) {
                    if (box.getArtBarH() != frBarHeightAdult) {
                        irrText(builder);
                        builder.append("a altura da barra lateral fixa é diferente de " + frBarHeightAdult + " m");
                    }

                    if (box.getArtBarI() < artSideBarMinLengthToilet) {
                        irrText(builder);
                        builder.append("o comprimento mínimo da barra lateral articulada à frente da bacia sanitária é inferior à " + artSideBarMinLengthToilet + " m");
                    }

                    if (box.getArtBarJ() != sideBarToiletAxisAdult) {
                        irrText(builder);
                        builder.append("a distância do eixo da bacia sanitária até a barra lateral articulada é diferente de " + sideBarToiletAxisAdult + " m");
                    }

                    if (box.getArtBarSect() < minHandrailGrip) {
                        irrText(builder);
                        builder.append("a seção transversal da barra lateral articulada é inferior à " + minHandrailGrip + " mm");
                    } else if (box.getArtBarSect() > maxHandrailGrip) {
                        irrText(builder);
                        builder.append("a seção transversal da barra lateral articulada é superior à " + maxHandrailGrip + " mm");
                    }
                }
            } else {
                if (box.getHasHorBar() == 0) {
                    irrText(builder);
                    builder.append("o sanitário não possui barra reta lateral horizontal");
                } else {
                    if (box.getHorBarD() != frBarHeightAdult) {
                        irrText(builder);
                        builder.append("a altura da barra lateral horizontal é diferente de " + frBarHeightAdult + " m");
                    }

                    if (box.getHorBarE() < frBarMinLength) {
                        irrText(builder);
                        builder.append("o comprimento da barra lateral horizontal é inferior à " + frBarMinLength + " m");
                    }

                    if (box.getHorBarF() != wallFixBarDistToiletEndBar) {
                        irrText(builder);
                        builder.append("o comprimento da barra lateral horizontal à frente da bacia sanitária é diferente de " + wallFixBarDistToiletEndBar + " m");
                    }

                    if (box.getHorBarDistG() != sideBarToiletAxisAdult) {
                        irrText(builder);
                        builder.append("a distância do eixo da bacia sanitária até a barra lateral horizontal é diferente de " + sideBarToiletAxisAdult + " m");
                    }

                    if (box.getHorBarSect() < minHandrailGrip) {
                        irrText(builder);
                        builder.append("a seção transversal da barra lateral horizontal é inferior à " + minHandrailGrip + " mm");
                    } else if (box.getHorBarSect() > maxHandrailGrip) {
                        irrText(builder);
                        builder.append("a seção transversal da barra lateral horizontal é superior à " + maxHandrailGrip + " mm");
                    }

                    if (box.getHorBarDist() < minDistHandrail) {
                        irrText(builder);
                        builder.append("a distância da barra lateral horizontal até a parede é inferior à " + minDistHandrail + " mm");
                    }
                }

                if (box.getHasVertBar() == 0) {
                    irrText(builder);
                    builder.append("o sanitário não possui a barra lateral de apoio vertical");
                } else {
                    if (box.getVertBarH() != wallFixBarVertDistToilet) {
                        irrText(builder);
                        builder.append("a distância entre a bacia sanitária e a barra lateral de apoio vertical é diferente de " + wallFixBarVertDistToilet + " m");
                    }

                    if (box.getVertBarJ() < wallFixBarVertMinLength) {
                        irrText(builder);
                        builder.append("o comprimento da barra lateral de apoio vertical é inferior a " + wallFixBarVertMinLength + " m");
                    }

                    if (box.getVertBarI() != wallFixBarDistInterBar) {
                        irrText(builder);
                        builder.append("a distância entre as barras laterais de apoio vertical e horizontal é diferente de " + wallFixBarDistInterBar + " m");
                    }

                    if (box.getVertBarSect() < minHandrailGrip) {
                        irrText(builder);
                        builder.append("a seção transversal da barra lateral vertical é inferior à " + minHandrailGrip + " mm");
                    } else if (box.getVertBarSect() > maxHandrailGrip) {
                        irrText(builder);
                        builder.append("a seção transversal da barra lateral vertical é superior à " + maxHandrailGrip + " mm");
                    }

                    if (box.getVertBarDist() < minDistHandrail) {
                        irrText(builder);
                        builder.append("a distância da barra lateral vertical até a parede é inferior à " + minDistHandrail + " mm");
                    }
                }
            }

            if (toiletType == 2 && box.getToActHeight() < minActionHeight) {
                irrText(builder);
                builder.append("a altura do acionamento da descarga, do tipo ").append(box.getToActDesc()).append(", está abaixo de " + minActionHeight + " m");
            } else if (toiletType == 2 && box.getToActHeight() > maxActionHeight) {
                irrText(builder);
                builder.append("a altura do acionamento da descarga, do tipo ").append(box.getToActDesc()).append(", está acima de " + maxActionHeight + " m");
            } else if (box.getToActHeight() > toiletActionMaxHeight) {
                irrText(builder);
                builder.append("a altura do acionamento da descarga, do tipo ").append(box.getToActDesc()).append(", possui altura superior à " + toiletActionMaxHeight + " m");
            }

            if (box.getToActObs() != null && box.getToActObs().length() > 0) {
                irrText(builder);
                builder.append("as seguintes observações devem ser feitas sobre o acionamento da descarga: ").append(box.getToActObs());
            }

            if (box.getHasPapHolder() == 0) {
                irrText(builder);
                builder.append("a bacia sanitária não possui papeleira");
            } else {
                if (box.getPapHolderType() == 0) {
                    if (box.getPapEmbDist() != embPaperHolderDistToilet) {
                        irrText(builder);
                        builder.append("a distância da papeleira embutida até a bacia sanitária é diferente de " + embPaperHolderDistToilet + " m");
                    }

                    if (box.getPapEmbHeight() != embPaperHolderHeight) {
                        irrText(builder);
                        builder.append("a altura do piso até papeleira embutida é diferente de " + embPaperHolderHeight + " m");
                    }
                } else {
                    if (box.getPapSupAlign() == 0) {
                        irrText(builder);
                        builder.append("a papeleira de sobrepor não está alinhada com a borda frontal do sanitário");
                    }
                    if (box.getPapSupHeight() != supPaperHolderHeight) {
                        irrText(builder);
                        builder.append("a altura do piso até papeleira de sobrepor é diferente de " + supPaperHolderHeight + " m");
                    }
                }
            }

            if (box.getPapHoldObs() != null && box.getPapHoldObs().length() > 0) {
                irrText(builder);
                builder.append("as seguintes observações devem ser feitas sobre a papeleira: ").append(box.getPapHoldObs());
            }

            if (box.getHasDouche() == 0) {
                irrText(builder);
                builder.append("o sanitário não possui ducha higiênica");
            } else {
                if (box.getDouchePressHeight() < minActionHeight) {
                    irrText(builder);
                    builder.append("o acionamento da válvula de pressão da ducha higiência está abaixo de " + minActionHeight + " m");
                } else if (box.getDouchePressHeight() > maxActionHeight) {
                    irrText(builder);
                    builder.append("o acionamento da válvula de pressão da ducha higiência está acima de " + maxActionHeight + " m");
                }

                if (box.getDoucheActHeight() < minActionHeight) {
                    irrText(builder);
                    builder.append("o acionamento da ducha higiência está abaixo de " + minActionHeight + " m");
                } else if (box.getDoucheActHeight() > maxActionHeight) {
                    irrText(builder);
                    builder.append("o acionamento da ducha higiência está acima de " + maxActionHeight + " m");
                }

                if (box.getDoucheObs() != null && box.getDoucheObs().length() > 0) {
                    irrText(builder);
                    builder.append("as seguintes observações podem ser feitas sobre a ducha higiênica: ").append(box.getDoucheObs());
                }

                if (box.getToiletObs() != null && box.getToiletObs().length() > 0) {
                    irrText(builder);
                    builder.append("as seguintes observações podem ser feitas sobre o sanitário: ").append(box.getToiletObs());
                }
            }

            if (box.getHasHanger() == 0) {
                irrText(builder);
                builder.append("o sanitário não possui cabide");
            } else {
                if (box.getHangerHeight() < minAccessHeight) {
                    irrText(builder);
                    builder.append("o cabide está instalado em uma altura inferior a " + minAccessHeight + " m");
                } else if (box.getHangerHeight() > maxAccessHeight) {
                    irrText(builder);
                    builder.append("o cabide está instalado em uma altura superior a " + maxAccessHeight + " m");
                }
            }
            if (box.getHangerObs() != null && box.getHangerObs().length() > 0) {
                irrText(builder);
                builder.append("as seguintes observações podem ser feitas sobre o cabide instalado: ").append(box.getHangerObs());
            }

            if (box.getHasObjHold() == 0) {
                irrText(builder);
                builder.append("o sanitário não possui porta-objetos");
            } else {
                if (box.getObjHoldCorrect() == 1) {
                    irrText(builder);
                    builder.append("o porta-objetos possui cantos agudos e/ou superfícies cortantes ou abrasivas");
                }
                if (box.getObjHoldHeight() < minAccessHeight) {
                    irrText(builder);
                    builder.append("o porta-objetos está instalado em uma altura inferior a " + minAccessHeight + " m");
                } else if (box.getObjHoldHeight() > maxAccessHeight) {
                    irrText(builder);
                    builder.append("o porta-objetos está instalado em uma altura superior a " + maxAccessHeight + " m");
                }
            }

            if (box.getObjHoldObs() != null && box.getObjHoldObs().length() > 0) {
                irrText(builder);
                builder.append("as seguintes observações podem ser feitas sobre o porta-objetos: ").append(box.getObjHoldObs());
            }

            if (box.getHasSoapHold() == 0) {
                irrText(builder);
                builder.append("o sanitário não possui saboneteira");
            } else {
                if (box.getSoapHoldHeight() < minAccessHeight) {
                    irrText(builder);
                    builder.append("a saboneteira está instalada em uma altura inferior a " + minAccessHeight + " m");
                } else if (box.getSoapHoldHeight() > maxAccessHeight) {
                    irrText(builder);
                    builder.append("a saboneteira está instalada em uma altura superior a " + maxAccessHeight + " m");
                }
            }

            if (box.getSoapHoldObs() != null && box.getSoapHoldObs().length() > 0) {
                irrText(builder);
                builder.append("as seguintes observações podem ser feitas sobre a saboneteira: ").append(box.getSoapHoldObs());
            }

            if (box.getHasTowelHold() == 0) {
                irrText(builder);
                builder.append("o sanitário não possui toalheiro");
            } else {
                if (box.getTowelHoldHeight() < minAccessHeight) {
                    irrText(builder);
                    builder.append("o toalheiro está instalado em uma altura inferior a " + minAccessHeight + " m");
                } else if (box.getTowelHoldHeight() > maxAccessHeight) {
                    irrText(builder);
                    builder.append("o toalheiro está instalado em uma altura superior a " + maxAccessHeight + " m");
                }
            }

            if (box.getTowelHoldObs() != null && box.getTowelHoldObs().length() > 0) {
                irrText(builder);
                builder.append("as seguintes observações podem ser feitas sobre o toalheiro: ").append(box.getTowelHoldObs());
            }

            if (box.getHasEmergencyButton() == 0) {
                irrText(builder);
                builder.append("o sanitário não possui alarme de emergência");
            } else {
                if (box.getEmergencyHeight() != emergencyButtonHeight) {
                    irrText(builder);
                    builder.append("o botão de emergência está a uma altura distinta de " + emergencyButtonHeight + " m");
                }

                if (box.getEmergencyObs() != null && box.getEmergencyObs().length() > 0) {
                    irrText(builder);
                    builder.append("as seguintes observações podem ser feitas sobre o botão de emergência: ").append(box.getTowelHoldObs());
                }
            }

            if (box.getHasWaterValve() == 0) {
                irrText(builder);
                builder.append("o sanitário não possui registro geral");
            } else {
                if (box.getWaterValveType() != 0) {
                    irrText(builder);
                    builder.append("a válvula do registro geral não é do tipo alavanca");
                }

                if (box.getWaterValveHeight() < lowerReach) {
                    irrText(builder);
                    builder.append("a válvula do registro geral está a uma altura inferior à " + lowerReach + " m");
                } else if (box.getWaterValveHeight() > upperReach) {
                    irrText(builder);
                    builder.append("a válvula do registro geral está a uma altura superior à " + upperReach + " m");
                }

                if (box.getWaterValveObs() != null && box.getWaterValveObs().length() > 0) {
                    irrText(builder);
                    builder.append("as seguintes observações podem ser feitas sobre o registro geral: ").append(box.getTowelHoldObs());
                }
            }

            if (box.getHasWallMirror() == 1) {
                if (box.getWallMirrorLow() != minLowerHeightWallMirror) {
                    irrText(builder);
                    builder.append("a altura do piso acabado até a base do espelho é diferente de " + minLowerHeightWallMirror + " m");
                }
                if (box.getWallMirrorHigh() < minUpperHeightWallMirror) {
                    irrText(builder);
                    builder.append("a altura do piso acabado até o topo do espelho é menor que " + minUpperHeightWallMirror + " m");
                }
                if (box.getWallMirrorHigh() - box.getWallMirrorLow() < 1.30) {
                    irrText(builder);
                    builder.append("o espelho possui altura inferior à 1.30 m");
                }
            }
            if (box.getWallMirrorObs() != null && box.getWallMirrorObs().length() > 0) {
                irrText(builder);
                builder.append("as seguintes observações podem ser feitas sobre o espelho instalado: ").append(box.getWallMirrorObs());
            }

            int sinkType = box.getSinkType();

            if (box.getApproxMeasureA() > sinkFaucetMaxDist) {
                irrText(builder);
                builder.append("o alcance manual da torneira ultrapassa o valor máximo permitido de " + sinkFaucetMaxDist + " m");
            }

            if (box.getApproxMeasureB() < sinkMinHeight) {
                irrText(builder);
                builder.append("a altura do lavatório é inferior à " + sinkMinHeight + " m");
            } else if (box.getApproxMeasureB() > sinkMaxHeight) {
                irrText(builder);
                builder.append("a altura do lavatório é superior à " + sinkMaxHeight + " m");
            }

            if (box.getApproxMeasureC() < sinkMinUnderSpace) {
                irrText(builder);
                builder.append("o espaço livre debaixo do lavatório é menor que " + sinkMinUnderSpace + " m");
            }

            if (box.getApproxMeasureD() < sinkFrontApproxKnee) {
                irrText(builder);
                builder.append("o espaço de aproximação frontal do lavatório na altura dos joelhos é inferior à " + sinkFrontApproxKnee + " m");
            }

            if (box.getApproxMeasureE() < sinkFrontApproxFeet) {
                irrText(builder);
                builder.append("o espaço de aproximação frontal do lavatório na altura dos pés é inferior à " + sinkFrontApproxFeet + " m");
            }

            if (box.getHasSinkBar() == 0) {
                irrText(builder);
                builder.append("o lavatório não possui barras de apoio");
            } else if (box.getHasSinkBar() == 2) {
                irrText(builder);
                builder.append("o lavatório possui barras de apoio que estão fora do padrão estipulado em norma");
            } else if (box.getHasSinkBar() == 1) {
                if (sinkType == 0) {
                    if (box.getHasLeftFrontHorBar() == 0) {
                        irrText(builder);
                        builder.append("o lavatório não possui barra de apoio instalada à esquerda");
                    } else {
                        if (box.getLeftFrontHorMeasureA() > sinkBarMaxFrontDist) {
                            irrText(builder);
                            builder.append("a distância entre a borda frontal do lavatório até a extremidade da barra esquerda é superior à " + sinkBarMaxFrontDist + " m");
                        }
                        if (box.getLeftFrontHorMeasureB() < sinkHorBarMinLatDist) {
                            irrText(builder);
                            builder.append("o espaçamento entre o lavatório e a barra esquerda é inferior à " + sinkHorBarMinLatDist + " m");
                        }
                        if (box.getLeftFrontHorMeasureC() < sinkHorBarMinUpperHeight) {
                            irrText(builder);
                            builder.append("a distância entre o piso e a face superior da barra esquerda é inferior à " + sinkHorBarMinUpperHeight + " m");
                        } else if (box.getLeftFrontHorMeasureC() > sinkHorBarMaxUpperHeight) {
                            irrText(builder);
                            builder.append("a distância entre o piso e a face superior da barra esquerda é superior à " + sinkHorBarMaxUpperHeight + " m");
                        }
                        if (box.getLeftFrontHorMeasureD() != sinkHorBarLowerHeight) {
                            irrText(builder);
                            builder.append("a distância entre o piso e a face inferior da barra esquerda é diferente de " + sinkHorBarLowerHeight + " m");
                        }

                        if (box.getLeftFrontHorDiam() < minHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm");
                        } else if (box.getLeftFrontHorDiam() > maxHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm");
                        }

                        if (box.getLeftFrontHorObs() != null && box.getLeftFrontHorObs().length() > 0) {
                            irrText(builder);
                            builder.append("as seguintes observações podem ser feitas sobre a barra horizontal esquerda: ").append(box.getLeftFrontHorObs());
                        }
                    }

                    if (box.getHasRightSideVertBar() == 0) {
                        irrText(builder);
                        builder.append("o lavatório não possui barra de apoio instalada à direita");
                    } else {
                        if (box.getRightSideVertMeasureA() > sinkBarMaxFrontDist) {
                            irrText(builder);
                            builder.append("a distância entre a borda frontal do lavatório até o eixo da barra direita é superior à " + sinkBarMaxFrontDist + " m");
                        }
                        if (box.getRightSideVertMeasureB() < sinkHorBarMinLatDist) {
                            irrText(builder);
                            builder.append("o espaçamento entre o lavatório e a barra direita é inferior à " + sinkHorBarMinLatDist + " m");
                        }
                        if (box.getRightSideVertMeasureC() < sinkHorBarMinUpperHeight) {
                            irrText(builder);
                            builder.append("a distância entre o piso e a face superior da barra direita é inferior à " + sinkHorBarMinUpperHeight + " m");
                        } else if (box.getRightSideVertMeasureC() > sinkHorBarMaxUpperHeight) {
                            irrText(builder);
                            builder.append("a distância entre o piso e a face superior da barra direita é superior à " + sinkHorBarMaxUpperHeight + " m");
                        }
                        if (box.getRightSideVertMeasureD() != sinkHorBarLowerHeight) {
                            irrText(builder);
                            builder.append("a distância entre o piso e a face inferior da barra direita é diferente de " + sinkHorBarLowerHeight + " m");
                        }

                        if (box.getRightSideVertDiam() < minHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm");
                        } else if (box.getRightSideVertDiam() > maxHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm");
                        }

                        if (box.getRightSideVertObs() != null && box.getRightSideVertObs().length() > 0) {
                            irrText(builder);
                            builder.append("as seguintes observações podem ser feitas sobre a barra horizontal direita: ").append(box.getRightSideVertObs());
                        }
                    }
                } else if (sinkType == 1 || sinkType == 3 || sinkType == 4) {
                    if (box.getHasLeftFrontHorBar() == 0) {
                        irrText(builder);
                        builder.append("o lavatório não possui barra de apoio vertical frontal instalada");
                    } else {
                        if (box.getLeftFrontHorMeasureA() > sinkVertBarMaxDistSinkAxis) {
                            irrText(builder);
                            builder.append("a distância do eixo do lavatório/cuba até o eixo da barra vertical frontal é superior à " + sinkVertBarMaxDistSinkAxis + " m");
                        }
                        if (box.getLeftFrontHorMeasureB() < sinkVertBarMinLength) {
                            irrText(builder);
                            builder.append("o comprimento da barra vertical frontal é inferior à " + sinkVertBarMinLength + " m");
                        }
                        if (box.getLeftFrontHorMeasureC() != sinkVertBarHeight) {
                            irrText(builder);
                            builder.append("a altura de instalação da barra vertical frontal é diferente de " + sinkVertBarHeight + " m");
                        }

                        if (box.getLeftFrontHorDiam() < minHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra vertical frontal do lavatório é inferior à " + minHandrailGrip + " mm");
                        } else if (box.getLeftFrontHorDiam() > maxHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra vertical frontal do lavatório é superior à " + maxHandrailGrip + " mm");
                        }

                        if (box.getLeftFrontHorDist() < minDistHandrail) {
                            irrText(builder);
                            builder.append("a distância entre a barra vertical frontal e a parede é inferior à " + minDistHandrail + " mm");
                        }

                        if (box.getLeftFrontHorObs() != null && box.getLeftFrontHorObs().length() > 0) {
                            irrText(builder);
                            builder.append("as seguintes observações podem ser feitas sobre a barra vertical frontal: ").append(box.getLeftFrontHorObs());
                        }
                    }

                    if (box.getHasRightSideVertBar() == 0) {
                        irrText(builder);
                        builder.append("o lavatório não possui barra de apoio vertical lateral instalada");
                    } else {
                        if (box.getRightSideVertMeasureA() < sinkVertBarMaxDistSinkAxis) {
                            irrText(builder);
                            builder.append("a distância do eixo do lavatório/cuba até o eixo da barra vertical lateral é superior à " + sinkVertBarMaxDistSinkAxis + " m");
                        }
                        if (box.getRightSideVertMeasureB() > sinkBarMaxFrontDist) {
                            irrText(builder);
                            builder.append("a distância entre a barra vertical lateral e a extremidade frontal do lavatório é superior à " + sinkBarMaxFrontDist + " m");
                        }
                        if (box.getRightSideVertMeasureC() < sinkVertBarMinLength) {
                            irrText(builder);
                            builder.append("o comprimento da barra vertical lateral é inferior à " + sinkVertBarMinLength + " m");
                        }
                        if (box.getRightSideVertMeasureD() != sinkVertBarHeight) {
                            irrText(builder);
                            builder.append("a altura de instalação da barra vertical lateral é diferente de " + sinkVertBarHeight + " m");
                        }

                        if (box.getRightSideVertMeasureE() != null && box.getRightSideVertMeasureE() < minDistHandrail) {
                            irrText(builder);
                            builder.append("a distância entre a barra vertical lateral e outros obstáculos é inferior à " + minDistHandrail + " mm");
                        }

                        if (box.getRightSideVertDiam() < minHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra vertical lateral do lavatório é inferior à " + minHandrailGrip + " mm");
                        } else if (box.getRightSideVertDiam() > maxHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra vertical lateral do lavatório é superior à " + maxHandrailGrip + " mm");
                        }
                        if (box.getRightSideVertDist() < minDistHandrail) {
                            irrText(builder);
                            builder.append("a distância entre a barra vertical lateral e a parede é inferior à " + minDistHandrail + " mm");
                        }
                        if (box.getRightSideVertObs() != null && box.getRightSideVertObs().length() > 0) {
                            irrText(builder);
                            builder.append("as seguintes observações podem ser feitas sobre a barra vertical lateral: ").append(box.getRightSideVertObs());
                        }
                    }
                } else if (sinkType == 2) {
                    if (box.getHasLeftFrontHorBar() == 0) {
                        irrText(builder);
                        builder.append("o lavatório não possui barra de apoio horizontal instalada");
                    } else {
                        if (box.getLeftFrontHorMeasureA() > sinkBarMaxFrontDist) {
                            irrText(builder);
                            builder.append("a distância entre a borda frontal do lavatório até a extremidade da barra horizontal é superior à "
                                    + sinkBarMaxFrontDist + " m");
                        }
                        if (box.getLeftFrontHorMeasureB() < sinkHorBarMinLatDist) {
                            irrText(builder);
                            builder.append("o espaçamento entre o lavatório e a barra horizontal é inferior à " + sinkHorBarMinLatDist + " m");
                        }
                        if (box.getLeftFrontHorMeasureC() < sinkHorBarMinUpperHeight) {
                            irrText(builder);
                            builder.append("a distância entre o piso e a face superior da barra horizontal é inferior à " + sinkHorBarMinUpperHeight + " m");
                        } else if (box.getLeftFrontHorMeasureC() > sinkHorBarMaxUpperHeight) {
                            irrText(builder);
                            builder.append("a distância entre o piso e a face superior da barra horizontal é superior à " + sinkHorBarMaxUpperHeight + " m");
                        }
                        if (box.getLeftFrontHorMeasureD() != sinkHorBarLowerHeight) {
                            irrText(builder);
                            builder.append("a distância entre o piso e a face inferior da barra horizontal é diferente de " + sinkHorBarLowerHeight + " m");
                        }

                        if (box.getLeftFrontHorDiam() < minHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra lateral horizontal do lavatório é inferior à " + minHandrailGrip + " mm");
                        } else if (box.getLeftFrontHorDiam() > maxHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra lateral horizontal do lavatório é superior à " + maxHandrailGrip + " mm");
                        }

                        if (box.getLeftFrontHorObs() != null && box.getLeftFrontHorObs().length() > 0) {
                            irrText(builder);
                            builder.append("as seguintes observações podem ser feitas sobre a barra horizontal horizontal: ").append(box.getLeftFrontHorObs());
                        }
                    }

                    if (box.getHasRightSideVertBar() == 0) {
                        irrText(builder);
                        builder.append("o lavatório não possui barra de apoio vertical instalada");
                    } else {
                        if (box.getRightSideVertMeasureA() < sinkVertBarMaxDistSinkAxis) {
                            irrText(builder);
                            builder.append("a distância do eixo do lavatório/cuba até o eixo da barra vertical é superior à " + sinkVertBarMaxDistSinkAxis + " m");
                        }
                        if (box.getRightSideVertMeasureB() < sinkVertBarMinLength) {
                            irrText(builder);
                            builder.append("o comprimento da barra vertical é inferior à " + sinkVertBarMinLength + " m");
                        }
                        if (box.getRightSideVertMeasureC() != sinkVertBarHeight) {
                            irrText(builder);
                            builder.append("a altura de instalação da barra vertical é diferente de " + sinkVertBarHeight + " m");
                        }
                        if (box.getRightSideVertMeasureD() > sinkBarMaxFrontDist) {
                            irrText(builder);
                            builder.append("a distância entre a barra vertical e a extremidade frontal do lavatório é superior à " + sinkBarMaxFrontDist + " m");
                        }
                        if (box.getRightSideVertMeasureE() != null && box.getRightSideVertMeasureE() < minDistHandrail) {
                            irrText(builder);
                            builder.append("a distância entre a barra vertical e outros obstáculos é inferior à " + minDistHandrail + " mm");
                        }

                        if (box.getRightSideVertDiam() < minHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra vertical do lavatório é inferior à " + minHandrailGrip + " mm");
                        } else if (box.getRightSideVertDiam() > maxHandrailGrip) {
                            irrText(builder);
                            builder.append("a seção transversal da barra vertical do lavatório é superior à " + maxHandrailGrip + " mm");
                        }
                        if (box.getRightSideVertDist() < minDistHandrail) {
                            irrText(builder);
                            builder.append("a distância entre a barra vertical e a parede é inferior à " + minDistHandrail + " mm");
                        }
                        if (box.getRightSideVertObs() != null && box.getRightSideVertObs().length() > 0) {
                            irrText(builder);
                            builder.append("as seguintes observações podem ser feitas sobre a barra vertical lateral: ").append(box.getRightSideVertObs());
                        }
                    }
                }
            }

            if (box.getSinkHasMirror() == 0) {
                irrText(builder);
                builder.append("o lavatório não possui espelho instalado");
            } else {
                if (box.getSinkMirrorLow() > sinkMirrorMaxLowerHeight) {
                    irrText(builder);
                    builder.append("a altura da base do espelho do lavatório é superior à " + sinkMirrorMaxLowerHeight + " m");
                }
                if (box.getSinkMirrorHigh() < minUpperHeightWallMirror) {
                    irrText(builder);
                    builder.append("a altura do espelho do lavatório é inferior à " + minUpperHeightWallMirror + " m");
                }
            }

            if (box.getSinkObs() != null && box.getSinkObs().length() > 0) {
                irrText(builder);
                builder.append("as seguintes observações podem ser feitas sobre o lavatório: ").append(box.getSinkObs());
            }
        }

        builder.replace(17, 18, String.valueOf(nBox));
        if (box.getTypeBox() == 0)
            builder.replace(20, 21, "comum");
        else
            builder.replace(20, 21, "acessível");


        return builder.toString();
    }

    private static void irrText(StringBuilder builder) {
        if (!irregularBox) {
            irregularBox = true;
            builder.append("Box Sanitário nº x, y, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }

    public static void soculoIrregular(StringBuilder builder) {
        if (!irrSoculo) {
            irrSoculo = true;
            builder.append("a bacia sanitária possui sóculo com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
