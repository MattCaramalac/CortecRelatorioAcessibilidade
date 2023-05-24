package com.mpms.relatorioacessibilidadecortec.report.Ambients;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.report.AmbientAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.DoorAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.FreeSpaceAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.RestBoxAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class RestAnalysis implements StandardMeasurements {

    static boolean irrSoculo;
    private static int check;
    private static int hasAccess;

    public static void restVerification(List<BlockSpaceEntry> blockList, List<RestroomEntry> restList, List<DoorEntry> doorList, List<FreeSpaceEntry> frSpaceList,
                                        List<RestBoxEntry> boxList, List<DoorEntry> boxDoor) {

        for (BlockSpaceEntry block : blockList) {
            if (block.getBlockSpaceType() == 2) {
                int blockID = block.getBlockSpaceID();
                hasAccess = 0;

                int i = 0;
                for (RestroomEntry rest : restList) {
                    check = 0;
                    AmbientAnalysis.err = false;
                    List<String> restIrr = new ArrayList<>();
                    if (rest.getBlockID() == blockID)
                        restIrr = checkRestIrregularities(rest, doorList, frSpaceList, boxList, boxDoor);

                    if (check > 0) {
                        AmbientAnalysis.checkHelpAreaHeader();
                        String type = restroomTyping(rest.getRestType()) + " " + restroomGender(rest.getRestGender());
                        AmbientAnalysis.helpRestList.add("Sanitário " + type + ", localizado em " + rest.getRestLocation() + ", com as seguintes irregularidades: ");
                        AmbientAnalysis.helpRestIrregular.put(i, restIrr);
                        i++;
                    }
                }
                if (hasAccess == 0)
                    AmbientAnalysis.helpNoAccessRestList.add("Esta área de apoio não possui sanitário acessível independente;");
            }
        }

    }

    public static void blockRestVerification(Integer blockID, List<RestroomEntry> restList, List<DoorEntry> doorList, List<FreeSpaceEntry> frSpaceList,
                                             List<RestBoxEntry> boxList, List<DoorEntry> boxDoor) {

        int blockRest = 0;
        hasAccess = 0;

        for (int i = 0; i < restList.size(); i++) {
            check = 0;
            AmbientAnalysis.err = false;
            List<String> restIrr = new ArrayList<>();
            RestroomEntry rest = restList.get(i);
            if (rest.getBlockID() == blockID)
                restIrr = checkRestIrregularities(rest, doorList, frSpaceList, boxList, boxDoor);

            if (check > 0) {
                String type = restroomTyping(rest.getRestType()) + " " + restroomGender(rest.getRestGender());
                if (rest.getRestLocation() != null && rest.getRestLocation().length() > 0)
                    AmbientAnalysis.blockRestList.add("Sanitário " + type + " " + rest.getRestLocation() + ", com as seguintes irregularidades: ");
                else
                    AmbientAnalysis.blockRestList.add("Sanitário " + type + ", com as seguintes irregularidades: ");
                AmbientAnalysis.blockRestIrregular.put(blockRest, restIrr);
                blockRest++;
            }
        }
        if (hasAccess == 0)
            AmbientAnalysis.blockNoAccessRestList.add("Este bloco não possui um sanitário acessível independente;");
    }

    public static List<String> checkRestIrregularities(RestroomEntry rest, List<DoorEntry> doorList, List<FreeSpaceEntry> frSpaceList,
                                                       List<RestBoxEntry> boxList, List<DoorEntry> boxDoor) {
        List<String> restIrr = new ArrayList<>();
        int restType = rest.getRestType();

        if (rest.getAntiDriftFloor() == 0) {
            check++;
            restIrr.add("ausência de piso antiderrapante;");
        } else if (rest.getAntiDriftFloor() == 0 && rest.getAntiDriftFloorObs() != null && rest.getAntiDriftFloorObs().length() > 0) {
            check++;
            restIrr.add("ausência de piso antiderrapante e as seguintes observações devem ser feitas: " + rest.getAntiDriftFloorObs() + ";");
        } else if (rest.getAntiDriftFloor() == 1 && rest.getAntiDriftFloorObs() != null && rest.getAntiDriftFloorObs().length() > 0) {
            check++;
            restIrr.add("presença de piso antiderrapante, mas possui as seguintes observações: " + rest.getAntiDriftFloorObs() + ";");
        }

        if (rest.getRestSwitch() == 0) {
            check++;
            restIrr.add("ausência de interruptor de energia;");
        } else if (rest.getRestSwitch() == 0 && rest.getSwitchObs() != null && rest.getSwitchObs().length() > 0) {
            check++;
            restIrr.add("ausência de interruptor de energia e as seguintes observações devem ser feitas: " + rest.getSwitchObs() + ";");
        } else {
            if (rest.getRestSwitch() == 1) {
                if (rest.getSwitchHeight() < minSwHeight) {
                    check++;
                    restIrr.add("presença de interruptor de energia com altura de comando inferior à " + minSwHeight + " m ;");
                } else if (rest.getSwitchHeight() > maxSwHeight) {
                    check++;
                    restIrr.add("presença de interruptor de energia com altura de comando superior à " + maxSwHeight + " m ;");
                }
            }
        }

        if (rest.getSwitchObs() != null && rest.getSwitchObs().length() > 0) {
            check++;
            restIrr.add("observações a serem feitas sobre o interruptor de energia: " + rest.getSwitchObs() + ";");
        }

        if (rest.getHasWindow() == 0) {
            check++;
            restIrr.add("o sanitário não possui janelas");
        } else {
            if (rest.getWinComHeight1() != null && rest.getWinComHeight1() > maxWinHeight) {
                restIrr.add("a altura do comando da janela, do tipo " + rest.getWinComType1() + " é superior a " + maxWinHeight + " m;");
            } else if (rest.getWinComHeight1() != null && rest.getWinComHeight1() < minWinHeight) {
                restIrr.add("a altura do comando da janela, do tipo " + rest.getWinComType1() + " é inferior a " + minWinHeight + " m;");
            }

            if (rest.getWinComHeight2() != null && rest.getWinComHeight2() > maxWinHeight) {
                restIrr.add("a altura do comando da janela, do tipo " + rest.getWinComType2() + " é superior a " + maxWinHeight + " m;");
            } else if (rest.getWinComHeight2() != null && rest.getWinComHeight2() < minWinHeight) {
                restIrr.add("a altura do comando da janela, do tipo " + rest.getWinComType2() + " é inferior a " + minWinHeight + " m;");
            }

            if (rest.getWinComHeight3() != null && rest.getWinComHeight3() > maxWinHeight) {
                restIrr.add("a altura do comando da janela, do tipo " + rest.getWinComType3() + " é superior a " + maxWinHeight + " m;");
            } else if (rest.getWinComHeight3() != null && rest.getWinComHeight3() < minWinHeight) {
                restIrr.add("a altura do comando da janela, do tipo " + rest.getWinComType3() + " é inferior a " + minWinHeight + " m;");
            }
        }

        if (rest.getWinObs() != null && rest.getWinObs().length() > 0)
            restIrr.add("observações a ser apontadas sobre a janela: " + rest.getWinObs() + ";");


        if (restType == 0) {
            hasAccess++;
            if (rest.getAccessRoute() == 0) {
                check++;
                restIrr.add("ausência de rota acessível ao sanitário;");
            } else if (rest.getAccessRoute() == 0 && rest.getAccessRouteObs() != null && rest.getAccessRouteObs().length() > 0) {
                check++;
                restIrr.add("ausência de rota acessível ao sanitário e as seguintes observações devem ser feitas: " + rest.getAccessRouteObs() + ";");
            } else if (rest.getAccessRoute() == 1 && rest.getAccessRouteObs() != null && rest.getAccessRouteObs().length() > 0) {
                check++;
                restIrr.add("presença de rota acessível ao sanitário, com as seguintes observações: " + rest.getAccessRouteObs() + ";");
            }

            if (rest.getIntRestroom() == 0) {
                check++;
                restIrr.add("não integração às demais instalações sanitárias;");
            } else if (rest.getIntRestroom() == 0 && rest.getIntRestObs() != null && rest.getIntRestObs().length() > 0) {
                check++;
                restIrr.add("não integração às demais instalações sanitárias e as seguintes observações devem ser feitas: " + rest.getIntRestObs() + ";");
            } else if (rest.getIntRestroom() == 1 && rest.getIntRestObs() != null && rest.getIntRestObs().length() > 0) {
                check++;
                restIrr.add("o sanitário está integrado às demais instalações sanitárias, com as seguintes observações: " + rest.getIntRestObs() + ";");
            }

            if (rest.getRestDrain() == 1) {
                check++;
                restIrr.add("presença de grelhas e/ou ralos na área de manobra;");
            } else if (rest.getRestDrain() == 1 && rest.getRestDrainObs() != null && rest.getRestDrainObs().length() > 0) {
                check++;
                restIrr.add("presença de grelhas e/ou ralos na área de manobra e as seguintes observações devem ser feitas: " + rest.getRestDrainObs() + ";");
            } else if (rest.getRestDrain() == 0 && rest.getRestDrainObs() != null && rest.getRestDrainObs().length() > 0) {
                check++;
                restIrr.add("ausência de grelhas e/ou ralos na área de manobra, porém as seguintes observações devem ser feitas: " + rest.getRestDrainObs() + ";");
            }

            if (doorList != null && doorList.size() > 0) {
                List<String> doorError = DoorAnalysis.restBoxDoorVerification(rest.getRestroomID(), doorList, false);
                if (doorError.size() > 0) {
                    check++;
                    restIrr.add(doorError.get(0) + ";");
                }
            }

            if (rest.getUpViewMeasureA() < minDistToiletWall) {
                check++;
                restIrr.add("distância entre o sanitário e a parede lateral é inferior à " + minDistToiletWall + " m;");
            }
            if (rest.getUpViewMeasureB() != null && rest.getUpViewMeasureB() > maxManUnderToilet) {
                check++;
                restIrr.add("a área de manobra utiliza mais do que " + maxManUnderToilet + " m sob a bacia sanitária;");
            }
            if (rest.getUpViewMeasureC() < maneuverArea) {
                check++;
                restIrr.add("a área de manobra possui diâmetro inferior à " + maneuverArea + " m;");
            }
            if (rest.getUpViewMeasureD() != null && rest.getUpViewMeasureD() > maxManUnderSink) {
                check++;
                restIrr.add("a área de manobra utiliza mais do que " + maxManUnderSink + " m sob o lavatório;");
            }
            if (rest.getUpViewObs() != null && rest.getUpViewObs().length() > 0) {
                check++;
                restIrr.add("as seguintes observações podem ser feitas sobre a vista superior do sanitário: " + rest.getUpViewObs() + ";");
            }

            int toiletType = rest.getToType();

            if (toiletType != 1 && rest.getToHasSoculo() == 1) {
                StringBuilder builder = new StringBuilder();
                if (rest.getFrSoculo() > maxSoculo) {
                    soculoIrregular(builder);
                    builder.append("projeção frontal superior à " + maxSoculo + " m");
                }
                if (rest.getLatSoculo() > maxSoculo) {
                    soculoIrregular(builder);
                    builder.append("projeção lateral superior à " + maxSoculo + " m");
                }
                if (rest.getSocCorners() == 1) {
                    soculoIrregular(builder);
                    builder.append("presença de cantos vivos;");
                }

                if (builder.toString().length() > 0) {
                    check++;
                    restIrr.add(builder.toString());
                }
            }

            if (rest.getToHeightNoSeat() < minToiletHeightAdult) {
                check++;
                restIrr.add("a altura da bacia sanitária é inferior à " + minToiletHeightAdult + " m;");
            } else if (rest.getToHeightNoSeat() > maxToiletHeightAdult) {
                check++;
                restIrr.add("a altura da bacia sanitária é superior à " + maxToiletHeightAdult + " m;");
            }

            if (rest.getToHasSeat() == 0) {
                check++;
                restIrr.add("a bacia sanitária não possui assento;");
            } else {
                if (rest.getToHeightSeat() > maxToiletHeightAdultSeat) {
                    check++;
                    restIrr.add("a altura da bacia sanitária com assento é superior à " + maxToiletHeightAdultSeat + " m;");
                }
            }

            if (toiletType == 2 && rest.getToHasFrontBar() == 0 && rest.getToHasWall() == 1) {
                check++;
                restIrr.add("O sanitário não possui barra de apoio posterior;");
            } else if (toiletType != 2 && rest.getToHasFrontBar() == 0) {
                check++;
                restIrr.add("O sanitário não possui barra de apoio posterior;");
            } else {
                if (toiletType == 2 && rest.getFrBarA() < frBarHeightAdult) {
                    check++;
                    restIrr.add("a altura da barra de apoio posterior está abaixo de " + frBarHeightAdult + " m;");
                } else if (toiletType == 2 && rest.getFrBarA() > frBarMaxHeightAdult) {
                    check++;
                    restIrr.add("a altura da barra de apoio posterior está acima de " + frBarMaxHeightAdult + " m;");
                } else if (toiletType != 2 && rest.getFrBarA() != frBarHeightAdult) {
                    check++;
                    restIrr.add("a altura da barra de apoio posterior é diferente de " + frBarHeightAdult + " m;");
                }

                if (rest.getFrBarC() != frBarToiletAxisAdult) {
                    check++;
                    restIrr.add("a distância entre o eixo da bacia sanitária até a extremidade da barra de apoio posterior em direção à parede lateral " +
                            "é diferente de " + frBarToiletAxisAdult + " m;");
                }


                if (rest.getFrBarB() < frBarMinLength) {
                    check++;
                    restIrr.add("o comprimento da barra de apoio posterior é inferior à " + frBarMinLength + " m;");
                }

                if (rest.getFrBarSect() < minHandrailGrip) {
                    check++;
                    restIrr.add("a seção transversal da barra de apoio posterior é inferior à " + minHandrailGrip + " mm;");
                } else if (rest.getFrBarSect() > maxHandrailGrip) {
                    check++;
                    restIrr.add("a seção transversal da barra de apoio posterior é superior à " + maxHandrailGrip + " mm;");
                }

                if (rest.getFrBarDist() < minDistHandrail) {
                    check++;
                    restIrr.add("a distância da parede até a barra de apoio posterior é inferior à " + minDistHandrail + " mm;");
                }
            }

            if (rest.getToHasWall() == 0) {
                if (rest.getHasSideBar() == 0) {
                    check++;
                    restIrr.add("o sanitário não possui barra reta lateral fixa;");
                } else if (rest.getToHasWall() == 1) {
                    if (rest.getSideBarD() < fixSideBarMinLengthToilet) {
                        check++;
                        restIrr.add("o comprimento mínimo da barra lateral fixa à frente da bacia sanitária é inferior à " + fixSideBarMinLengthToilet + " m;");
                    }

                    if (rest.getHorBarE() != frBarHeightAdult) {
                        check++;
                        restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightAdult + " m;");
                    }

                    if (rest.getSideBarDistG() != sideBarToiletAxisAdult) {
                        check++;
                        restIrr.add("a distância do eixo da bacia sanitária até a barra lateral fixa é diferente de " + sideBarToiletAxisAdult + " m;");
                    }

                    if (rest.getSideBarSect() < minHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra lateral fixa é inferior à " + minHandrailGrip + " mm;");
                    } else if (rest.getSideBarSect() > maxHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra lateral fixa é superior à " + maxHandrailGrip + " mm;");
                    }
                }

                if (rest.getHasArtBar() == 0) {
                    check++;
                    restIrr.add("a bacia sanitária não possui barra lateral articulada");
                } else if (rest.getHasArtBar() == 1) {
                    if (rest.getArtBarH() != frBarHeightAdult) {
                        check++;
                        restIrr.add("a altura da barra lateral articulada é diferente de " + frBarHeightAdult + " m;");
                    }

                    if (rest.getArtBarI() < artSideBarMinLengthToilet) {
                        check++;
                        restIrr.add("o comprimento mínimo da barra lateral articulada à frente da bacia sanitária é inferior à " + artSideBarMinLengthToilet + " m;");
                    }

                    if (rest.getArtBarJ() != sideBarToiletAxisAdult) {
                        check++;
                        restIrr.add("a distância do eixo da bacia sanitária até a barra lateral articulada é diferente de " + sideBarToiletAxisAdult + " m;");
                    }

                    if (rest.getArtBarSect() < minHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra lateral articulada é inferior à " + minHandrailGrip + " mm;");
                    } else if (rest.getArtBarSect() > maxHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra lateral articulada é superior à " + maxHandrailGrip + " mm;");
                    }
                }
            } else if (rest.getToHasWall() == 1) {
                if (rest.getHasHorBar() == 0) {
                    check++;
                    restIrr.add("o sanitário não possui barra reta lateral horizontal;");
                } else {
                    if (rest.getHorBarD() != frBarHeightAdult) {
                        check++;
                        restIrr.add("a altura da barra lateral horizontal é diferente de " + frBarHeightAdult + " m;");
                    }

                    if (rest.getHorBarE() < frBarMinLength) {
                        check++;
                        restIrr.add("o comprimento da barra lateral horizontal é inferior à " + frBarMinLength + " m;");
                    }

                    if (rest.getHorBarF() != wallFixBarDistToiletEndBar) {
                        check++;
                        restIrr.add("o comprimento da barra lateral horizontal à frente da bacia sanitária é diferente de " + wallFixBarDistToiletEndBar + " m;");
                    }

                    if (rest.getHorBarDistG() != sideBarToiletAxisAdult) {
                        check++;
                        restIrr.add("a distância do eixo da bacia sanitária até a barra lateral horizontal é diferente de " + sideBarToiletAxisAdult + " m;");
                    }

                    if (rest.getHorBarSect() < minHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra lateral horizontal é inferior à " + minHandrailGrip + " mm;");
                    } else if (rest.getHorBarSect() > maxHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra lateral horizontal é superior à " + maxHandrailGrip + " mm;");
                    }

                    if (rest.getHorBarDist() < minDistHandrail) {
                        check++;
                        restIrr.add("a distância da barra lateral horizontal até a parede é inferior à " + minDistHandrail + " mm;");
                    }
                }

                if (rest.getHasVertBar() == 0) {
                    check++;
                    restIrr.add("o sanitário não possui barra reta lateral vertical;");
                } else {
                    if (rest.getVertBarH() != wallFixBarVertDistToilet) {
                        check++;
                        restIrr.add("a distância entre a bacia sanitária e a barra lateral de apoio vertical é diferente de " + wallFixBarVertDistToilet + " m;");
                    }

                    if (rest.getVertBarJ() < wallFixBarVertMinLength) {
                        check++;
                        restIrr.add("o comprimento da barra lateral de apoio vertical é inferior a " + wallFixBarVertMinLength + " m;");
                    }

                    if (rest.getVertBarI() != wallFixBarDistInterBar) {
                        check++;
                        restIrr.add("a distância entre as barras laterais de apoio vertical e horizontal é diferente de " + wallFixBarDistInterBar + " m;");
                    }

                    if (rest.getVertBarSect() < minHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra lateral vertical é inferior à " + minHandrailGrip + " mm;");
                    } else if (rest.getVertBarSect() > maxHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra lateral vertical é superior à " + maxHandrailGrip + " mm;");
                    }

                    if (rest.getVertBarDist() < minDistHandrail) {
                        check++;
                        restIrr.add("a distância da barra lateral vertical até a parede é inferior à " + minDistHandrail + " mm;");
                    }
                }
            }

            if (toiletType == 2 && rest.getToActHeight() < minActionHeight) {
                check++;
                restIrr.add("a altura do acionamento da descarga, do tipo " + rest.getToActDesc() + ", está abaixo de " + minActionHeight + " m;");
            } else if (toiletType == 2 && rest.getToActHeight() > maxActionHeight) {
                check++;
                restIrr.add("a altura do acionamento da descarga, do tipo " + rest.getToActDesc() + ", está acima de " + maxActionHeight + " m;");
            } else if (rest.getToActHeight() > toiletActionMaxHeight) {
                check++;
                restIrr.add("a altura do acionamento da descarga, do tipo " + rest.getToActDesc() + ", possui altura superior à " + toiletActionMaxHeight + " m;");
            }

            if (rest.getToActObs() != null && rest.getToActObs().length() > 0) {
                check++;
                restIrr.add("as seguintes observações devem ser feitas sobre o acionamento da descarga: " + rest.getToActObs() + ";");
            }

            if (rest.getHasPapHolder() == 0) {
                check++;
                restIrr.add("a bacia sanitária não possui papeleira;");
            } else {
                if (rest.getPapHolderType() == 0) {
                    if (rest.getPapEmbDist() != embPaperHolderDistToilet) {
                        check++;
                        restIrr.add("a distância da papeleira embutida até a bacia sanitária é diferente de " + embPaperHolderDistToilet + " m;");
                    }

                    if (rest.getPapEmbHeight() != embPaperHolderHeight) {
                        check++;
                        restIrr.add("a altura do piso até papeleira embutida é diferente de " + embPaperHolderHeight + " m;");
                    }
                } else {
                    if (rest.getPapSupAlign() == 0) {
                        check++;
                        restIrr.add("a papeleira de sobrepor não está alinhada com a borda frontal do sanitário;");
                    }
                    if (rest.getPapSupHeight() != supPaperHolderHeight) {
                        check++;
                        restIrr.add("a altura do piso até papeleira de sobrepor é diferente de " + supPaperHolderHeight + " m;");
                    }
                }
            }

            if (rest.getPapHoldObs() != null && rest.getPapHoldObs().length() > 0) {
                check++;
                restIrr.add("as seguintes observações devem ser feitas sobre a papeleira: " + rest.getPapHoldObs() + ";");
            }

            if (rest.getHasDouche() == 0) {
                check++;
                restIrr.add("o sanitário não possui ducha higiênica;");
            } else {
                if (rest.getDouchePressHeight() < minActionHeight) {
                    check++;
                    restIrr.add("o acionamento da válvula de pressão da ducha higiência está abaixo de " + minActionHeight + " m;");
                } else if (rest.getDouchePressHeight() > maxActionHeight) {
                    check++;
                    restIrr.add("o acionamento da válvula de pressão da ducha higiência está acima de " + maxActionHeight + " m;");
                }

                if (rest.getDoucheActHeight() < minActionHeight) {
                    check++;
                    restIrr.add("o acionamento da ducha higiência está abaixo de " + minActionHeight + " m;");
                } else if (rest.getDoucheActHeight() > maxActionHeight) {
                    check++;
                    restIrr.add("o acionamento da ducha higiência está acima de " + maxActionHeight + " m;");
                }

                if (rest.getDoucheObs() != null && rest.getDoucheObs().length() > 0) {
                    check++;
                    restIrr.add("as seguintes observações podem ser feitas sobre a ducha higiênica: " + rest.getDoucheObs() + ";");
                }

                if (rest.getToiletObs() != null && rest.getToiletObs().length() > 0) {
                    check++;
                    restIrr.add("as seguintes observações podem ser feitas sobre o sanitário: " + rest.getToiletObs());
                }
            }

            if (rest.getHasHanger() == 0) {
                check++;
                restIrr.add("o sanitário não possui cabide;");
            } else {
                if (rest.getHangerHeight() < minAccessHeight) {
                    check++;
                    restIrr.add("o cabide está instalado em uma altura inferior a " + minAccessHeight + " m;");
                } else if (rest.getHangerHeight() > maxAccessHeight) {
                    check++;
                    restIrr.add("o cabide está instalado em uma altura superior a " + maxAccessHeight + " m;");
                }
            }
            if (rest.getHangerObs() != null && rest.getHangerObs().length() > 0) {
                check++;
                restIrr.add("as seguintes observações podem ser feitas sobre o cabide instalado: " + rest.getHangerObs() + ";");
            }

            if (rest.getHasObjHold() == 0) {
                check++;
                restIrr.add("o sanitário não possui porta-objetos;");
            } else {
                if (rest.getObjHoldCorrect() == 1) {
                    check++;
                    restIrr.add("o porta-objetos possui cantos agudos e/ou superfícies cortantes ou abrasivas;");
                }
                if (rest.getObjHoldHeight() < minAccessHeight) {
                    check++;
                    restIrr.add("o porta-objetos está instalado em uma altura inferior a " + minAccessHeight + " m;");
                } else if (rest.getObjHoldHeight() > maxAccessHeight) {
                    check++;
                    restIrr.add("o porta-objetos está instalado em uma altura superior a " + maxAccessHeight + " m;");
                }
            }

            if (rest.getObjHoldObs() != null && rest.getObjHoldObs().length() > 0) {
                check++;
                restIrr.add("as seguintes observações podem ser feitas sobre o porta-objetos: " + rest.getObjHoldObs() + ";");
            }

            if (rest.getHasSoapHold() == 0) {
                check++;
                restIrr.add("o sanitário não possui saboneteira;");
            } else {
                if (rest.getSoapHoldHeight() < minAccessHeight) {
                    check++;
                    restIrr.add("a saboneteira está instalada em uma altura inferior a " + minAccessHeight + " m;");
                } else if (rest.getSoapHoldHeight() > maxAccessHeight) {
                    check++;
                    restIrr.add("a saboneteira está instalada em uma altura superior a " + maxAccessHeight + " m;");
                }
            }

            if (rest.getSoapHoldObs() != null && rest.getSoapHoldObs().length() > 0) {
                check++;
                restIrr.add("as seguintes observações podem ser feitas sobre a saboneteira: " + rest.getSoapHoldObs() + ";");
            }

            if (rest.getHasTowelHold() == 0) {
                check++;
                restIrr.add("o sanitário não possui toalheiro;");
            } else {
                if (rest.getTowelHoldHeight() < minAccessHeight) {
                    check++;
                    restIrr.add("o toalheiro está instalado em uma altura inferior a " + minAccessHeight + " m;");
                } else if (rest.getTowelHoldHeight() > maxAccessHeight) {
                    check++;
                    restIrr.add("o toalheiro está instalado em uma altura superior a " + maxAccessHeight + " m;");
                }
            }

            if (rest.getTowelHoldObs() != null && rest.getTowelHoldObs().length() > 0) {
                check++;
                restIrr.add("as seguintes observações podem ser feitas sobre o toalheiro: " + rest.getTowelHoldObs() + ";");
            }

            if (rest.getHasEmergencyButton() == 0) {
                check++;
                restIrr.add("o sanitário não possui alarme de emergência;");
            } else {
                if (rest.getEmergencyHeight() != emergencyButtonHeight) {
                    check++;
                    restIrr.add("o botão de emergência está a uma altura distinta de " + emergencyButtonHeight + " m;");
                }

                if (rest.getEmergencyObs() != null && rest.getEmergencyObs().length() > 0) {
                    check++;
                    restIrr.add("as seguintes observações podem ser feitas sobre o botão de emergência: " + rest.getTowelHoldObs() + ";");
                }
            }

            if (rest.getHasWaterValve() == 0) {
                check++;
                restIrr.add("o sanitário não possui registro geral;");
            } else {
                if (rest.getWaterValveType() != 0) {
                    check++;
                    restIrr.add("a válvula do registro geral não é do tipo alavanca;");
                }

                if (rest.getWaterValveHeight() < lowerReach) {
                    check++;
                    restIrr.add("a válvula do registro geral está a uma altura inferior à " + lowerReach + " m;");
                } else if (rest.getWaterValveHeight() > upperReach) {
                    check++;
                    restIrr.add("a válvula do registro geral está a uma altura superior à " + upperReach + " m;");
                }

                if (rest.getWaterValveObs() != null && rest.getWaterValveObs().length() > 0) {
                    check++;
                    restIrr.add("as seguintes observações podem ser feitas sobre o registro geral: " + rest.getTowelHoldObs() + ";");
                }
            }

            if (rest.getHasWallMirror() == 1) {
                if (rest.getWallMirrorLow() != minLowerHeightWallMirror) {
                    check++;
                    restIrr.add("a altura do piso acabado até a base do espelho é diferente de " + minLowerHeightWallMirror + " m;");
                }
                if (rest.getWallMirrorHigh() < minUpperHeightWallMirror) {
                    check++;
                    restIrr.add("a altura do piso acabado até o topo do espelho é menor que " + minUpperHeightWallMirror + " m;");
                }
                if (rest.getWallMirrorHigh() - rest.getWallMirrorLow() < 1.30) {
                    check++;
                    restIrr.add("o espelho possui altura inferior à 1.30 m;");
                }
            }
            if (rest.getWallMirrorObs() != null && rest.getWallMirrorObs().length() > 0) {
                check++;
                restIrr.add("as seguintes observações podem ser feitas sobre o espelho instalado: " + rest.getWallMirrorObs() + ";");
            }



            if (rest.getHasSink() != null) {
                if (rest.getHasSink() == 0) {
                    check++;
                    restIrr.add("o sanitário não possui lavatório acessível;");
                } else if (rest.getHasSink() == 1) {
                    int sinkType = rest.getSinkType();

                    if (rest.getApproxMeasureA() > sinkFaucetMaxDist) {
                        check++;
                        restIrr.add("o alcance manual da torneira ultrapassa o valor máximo permitido de " + sinkFaucetMaxDist + " m;");
                    }

                    if (rest.getApproxMeasureB() < sinkMinHeight) {
                        check++;
                        restIrr.add("a altura do lavatório é inferior à " + sinkMinHeight + " m;");
                    } else if (rest.getApproxMeasureB() > sinkMaxHeight) {
                        check++;
                        restIrr.add("a altura do lavatório é superior à " + sinkMaxHeight + " m;");
                    }

                    if (rest.getApproxMeasureC() < sinkMinUnderSpace) {
                        check++;
                        restIrr.add("o espaço livre debaixo do lavatório é menor que " + sinkMinUnderSpace + " m;");
                    }

                    if (rest.getApproxMeasureD() < sinkFrontApproxKnee) {
                        check++;
                        restIrr.add("o espaço de aproximação frontal do lavatório na altura dos joelhos é inferior à " + sinkFrontApproxKnee + " m;");
                    }

                    if (rest.getApproxMeasureE() < sinkFrontApproxFeet) {
                        check++;
                        restIrr.add("o espaço de aproximação frontal do lavatório na altura dos pés é inferior à " + sinkFrontApproxFeet + " m;");
                    }

                    if (rest.getHasSinkBar() == 0) {
                        check++;
                        restIrr.add("o lavatório não possui barras de apoio;");
                    } else if (rest.getHasSinkBar() == 2) {
                        check++;
                        restIrr.add("o lavatório possui barras de apoio que estão fora do padrão estipulado em norma;");
                    } else if (rest.getHasSinkBar() == 1) {
                        if (sinkType == 0) {
                            if (rest.getHasLeftFrontHorBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                            } else {
                                if (rest.getLeftFrontHorMeasureA() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até a extremidade da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getLeftFrontHorMeasureB() < sinkHorBarMinLatDist) {
                                    check++;
                                    restIrr.add("o espaçamento entre o lavatório e a barra esquerda é inferior à " + sinkHorBarMinLatDist + " m;");
                                }
                                if (rest.getLeftFrontHorMeasureC() < sinkHorBarMinUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra esquerda é inferior à " + sinkHorBarMinUpperHeight + " m;");
                                } else if (rest.getLeftFrontHorMeasureC() > sinkHorBarMaxUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra esquerda é superior à " + sinkHorBarMaxUpperHeight + " m;");
                                }
                                if (rest.getLeftFrontHorMeasureD() != sinkHorBarLowerHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face inferior da barra esquerda é diferente de " + sinkHorBarLowerHeight + " m;");
                                }

                                if (rest.getLeftFrontHorDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getLeftFrontHorDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getLeftFrontHorObs() != null && rest.getLeftFrontHorObs().length() > 0) {
                                    check++;
                                    restIrr.add("as seguintes observações podem ser feitas sobre a barra horizontal esquerda: " + rest.getLeftFrontHorObs() + ";");
                                }
                            }

                            if (rest.getHasRightSideVertBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                            } else {
                                if (rest.getRightSideVertMeasureA() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra direita é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getRightSideVertMeasureB() < sinkHorBarMinLatDist) {
                                    check++;
                                    restIrr.add("o espaçamento entre o lavatório e a barra direita é inferior à " + sinkHorBarMinLatDist + " m;");
                                }
                                if (rest.getRightSideVertMeasureC() < sinkHorBarMinUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra direita é inferior à " + sinkHorBarMinUpperHeight + " m;");
                                } else if (rest.getRightSideVertMeasureC() > sinkHorBarMaxUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra direita é superior à " + sinkHorBarMaxUpperHeight + " m;");
                                }
                                if (rest.getRightSideVertMeasureD() != sinkHorBarLowerHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face inferior da barra direita é diferente de " + sinkHorBarLowerHeight + " m;");
                                }

                                if (rest.getRightSideVertDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getRightSideVertDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getRightSideVertObs() != null && rest.getRightSideVertObs().length() > 0) {
                                    check++;
                                    restIrr.add("as seguintes observações podem ser feitas sobre a barra horizontal direita: " + rest.getRightSideVertObs() + ";");
                                }
                            }
                        } else if (sinkType == 1 || sinkType == 3 || sinkType == 4) {
                            if (rest.getHasLeftFrontHorBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio vertical frontal instalada;");
                            } else {
                                if (rest.getLeftFrontHorMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical frontal é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }
                                if (rest.getLeftFrontHorMeasureB() < sinkVertBarMinLength) {
                                    check++;
                                    restIrr.add("o comprimento da barra vertical frontal é inferior à " + sinkVertBarMinLength + " m;");
                                }
                                if (rest.getLeftFrontHorMeasureC() != sinkVertBarHeight) {
                                    check++;
                                    restIrr.add("a altura de instalação da barra vertical frontal é diferente de " + sinkVertBarHeight + " m;");
                                }

                                if (rest.getLeftFrontHorDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra vertical frontal do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getLeftFrontHorDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra vertical frontal do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getLeftFrontHorDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância entre a barra vertical frontal e a parede é inferior à " + minDistHandrail + " mm;");
                                }

                                if (rest.getLeftFrontHorObs() != null && rest.getLeftFrontHorObs().length() > 0) {
                                    check++;
                                    restIrr.add("as seguintes observações podem ser feitas sobre a barra vertical frontal: " + rest.getLeftFrontHorObs() + ";");
                                }
                            }

                            if (rest.getHasRightSideVertBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra vertical lateral de apoio instalada;");
                            } else {
                                if (rest.getRightSideVertMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical lateral é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }
                                if (rest.getRightSideVertMeasureB() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a barra vertical lateral e a extremidade frontal do lavatório é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getRightSideVertMeasureC() > sinkVertBarMinLength) {
                                    check++;
                                    restIrr.add("o comprimento da barra vertical lateral é inferior à " + sinkVertBarMinLength + " m;");
                                }
                                if (rest.getRightSideVertMeasureD() != sinkVertBarHeight) {
                                    check++;
                                    restIrr.add("a altura de instalação da barra vertical lateral é diferente de " + sinkVertBarHeight + " m;");
                                }

                                if (rest.getRightSideVertMeasureE() != null && rest.getRightSideVertMeasureE() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância entre a barra vertical lateral e outros obstáculos é inferior à " + minDistHandrail + " mm;");
                                }

                                if (rest.getRightSideVertDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra vertical lateral do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getRightSideVertDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra vertical lateral do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }
                                if (rest.getRightSideVertDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância entre a barra vertical lateral e a parede é inferior à " + minDistHandrail + " mm;");
                                }
                                if (rest.getRightSideVertObs() != null && rest.getRightSideVertObs().length() > 0) {
                                    check++;
                                    restIrr.add("as seguintes observações podem ser feitas sobre a barra vertical lateral: " + rest.getRightSideVertObs() + ";");
                                }
                            }
                        } else if (sinkType == 2) {
                            if (rest.getHasLeftFrontHorBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio horizontal instalada;");
                            } else {
                                if (rest.getLeftFrontHorMeasureA() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até a extremidade da barra horizontal é superior à "
                                            + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getLeftFrontHorMeasureB() < sinkHorBarMinLatDist) {
                                    check++;
                                    restIrr.add("o espaçamento entre o lavatório e a barra horizontal é inferior à " + sinkHorBarMinLatDist + " m;");
                                }
                                if (rest.getLeftFrontHorMeasureC() < sinkHorBarMinUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra horizontal é inferior à " + sinkHorBarMinUpperHeight + " m;");
                                } else if (rest.getLeftFrontHorMeasureC() > sinkHorBarMaxUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra horizontal é superior à " + sinkHorBarMaxUpperHeight + " m;");
                                }
                                if (rest.getLeftFrontHorMeasureD() != sinkHorBarLowerHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face inferior da barra horizontal é diferente de " + sinkHorBarLowerHeight + " m;");
                                }

                                if (rest.getLeftFrontHorDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral horizontal do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getLeftFrontHorDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral horizontal do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getLeftFrontHorObs() != null && rest.getLeftFrontHorObs().length() > 0) {
                                    check++;
                                    restIrr.add("as seguintes observações podem ser feitas sobre a barra horizontal horizontal: " + rest.getLeftFrontHorObs() + ";");
                                }
                            }

                            if (rest.getHasRightSideVertBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio vertical instalada;");
                            } else {
                                if (rest.getRightSideVertMeasureA() < sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }
                                if (rest.getRightSideVertMeasureB() < sinkVertBarMinLength) {
                                    check++;
                                    restIrr.add("o comprimento da barra vertical é inferior à " + sinkVertBarMinLength + " m;");
                                }
                                if (rest.getRightSideVertMeasureC() != sinkVertBarHeight) {
                                    check++;
                                    restIrr.add("a altura de instalação da barra vertical é diferente de " + sinkVertBarHeight + " m;");
                                }
                                if (rest.getRightSideVertMeasureD() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a barra vertical e a extremidade frontal do lavatório é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getRightSideVertMeasureE() != null && rest.getRightSideVertMeasureE() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância entre a barra vertical e outros obstáculos é inferior à " + minDistHandrail + " mm;");
                                }

                                if (rest.getRightSideVertDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra vertical do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getRightSideVertDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra vertical do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }
                                if (rest.getRightSideVertDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância entre a barra vertical e a parede é inferior à " + minDistHandrail + " mm;");
                                }
                                if (rest.getRightSideVertObs() != null && rest.getRightSideVertObs().length() > 0) {
                                    check++;
                                    restIrr.add("as seguintes observações podem ser feitas sobre a barra vertical lateral: " + rest.getRightSideVertObs() + ";");
                                }
                            }
                        }
                    }

                    if (rest.getSinkHasMirror() == 0) {
                        check++;
                        restIrr.add("o lavatório não possui espelho instalado;");
                    } else {
                        if (rest.getSinkMirrorLow() > sinkMirrorMaxLowerHeight) {
                            check++;
                            restIrr.add("a altura da base do espelho do lavatório é superior à " + sinkMirrorMaxLowerHeight + " m;");
                        }
                        if (rest.getSinkMirrorHigh() < minUpperHeightWallMirror) {
                            check++;
                            restIrr.add("a altura do espelho do lavatório é inferior à " + minUpperHeightWallMirror + " m;");
                        }
                    }

                    if (rest.getSinkObs() != null && rest.getSinkObs().length() > 0) {
                        check++;
                        restIrr.add("as seguintes observações podem ser feitas sobre o lavatório: " + rest.getSinkObs() + ";");
                    }
                }
            }


        } else {

            if (restType == 1) {
                if (rest.getCollectiveHasDoor() == 0) {
                    if (rest.getEntranceWidth() != null && rest.getEntranceWidth() < freeSpaceGeneral) {
                        check++;
                        restIrr.add("a largura da entrada do sanitário coletivo é inferior à " + freeSpaceGeneral + " m;");
                    }
                    if (rest.getEntranceDoorSill() != null && rest.getEntranceDoorSill() != 0) {
                        check++;
                        restIrr.add("a soleira da entrada do sanitário não é nivelada;");
                    }
                    if (rest.getEntranceDoorSillObs() != null && rest.getEntranceDoorSillObs().length() > 0) {
                        check++;
                        restIrr.add("as seguintes observações podem ser feitas sobre a soleira do sanitário: " + rest.getEntranceDoorSillObs() + ";");
                    }
                } else {
                    if (doorList != null && doorList.size() > 0) {
                        List<String> doorError = DoorAnalysis.restBoxDoorVerification(rest.getRestroomID(), doorList, false);
                        if (doorError.size() > 0) {
                            check++;
                            restIrr.add(doorError.get(0) + ";");
                        }
                    }
                }

                if (rest.getRestDrain() == 1) {
                    check++;
                    restIrr.add("presença de grelhas e/ou ralos na área de manobra;");
                } else if (rest.getRestDrain() == 1 && rest.getRestDrainObs() != null && rest.getRestDrainObs().length() > 0) {
                    check++;
                    restIrr.add("presença de grelhas e/ou ralos na área de manobra e as seguintes observações devem ser feitas: " + rest.getRestDrainObs() + ";");
                } else if (rest.getRestDrain() == 0 && rest.getRestDrainObs() != null && rest.getRestDrainObs().length() > 0) {
                    check++;
                    restIrr.add("ausência de grelhas e/ou ralos na área de manobra, porém as seguintes observações devem ser feitas: " + rest.getRestDrainObs() + ";");
                }
            } else {
                if (rest.getUpViewWidth() < restMinWidthRemodel || rest.getUpViewLength() < restMinDiamRemodel) {
                    check++;
                    restIrr.add("As dimensões do ambiente são inferiores ao mínimo permitido pela norma para um sanitário acessível após reforma");
                }
                if (rest.getEntranceWidth() != null && rest.getEntranceWidth() < freeSpaceGeneral) {
                    check++;
                    restIrr.add("a largura da entrada do sanitário é inferior à " + freeSpaceGeneral + " m;");
                }
                if (rest.getEntranceDoorSill() != null && rest.getEntranceDoorSill() != 0) {
                    check++;
                    restIrr.add("a soleira da entrada do sanitário não é nivelada;");
                }
                if (rest.getEntranceDoorSillObs() != null && rest.getEntranceDoorSillObs().length() > 0) {
                    check++;
                    restIrr.add("as seguintes observações podem ser feitas sobre a soleira do sanitário: " + rest.getEntranceDoorSillObs() + ";");
                }
            }

            if (boxList.size() > 0) {
                List<String> boxError = RestBoxAnalysis.boxTextList(rest.getRestroomID(), boxList, boxDoor);
                if (boxError.size() > 0) {
                    check++;
                    restIrr.addAll(boxError);
                }
            }

            if (frSpaceList.size() > 0) {
                List<String> fsError = FreeSpaceAnalysis.restSpaceTextList(rest.getRestroomID(), frSpaceList);
                if (fsError.size() > 0) {
                    check++;
                    restIrr.addAll(fsError);
                }
            }

            if ((restType == 1 || restType == 2) && rest.getHasUrinal() == 1) {
                if (rest.getHasAccessUrinal() == 0) {
                    check++;
                    restIrr.add("o sanitário possui mictórios porém nenhum deles é acessível;");
                } else {
                    if (rest.getUrMeasureA() != urinalDistancePartitions) {
                        check++;
                        restIrr.add("a distância entre as divisórias do mictório acessível é diferente de " + urinalDistancePartitions + " m;");
                    }
                    if (rest.getUrMeasureB() != urinalDistanceBars) {
                        check++;
                        restIrr.add("a distância entre as barras de apoio do mictório acessível é diferente de " + urinalDistancePartitions + " m;");
                    }
                    if (rest.getUrMeasureC() != urinalDistAxisLeftBar) {
                        check++;
                        restIrr.add("a distância entre o eixo do mictório acessível até a barra de apoio esquerda é diferente de " + urinalDistAxisLeftBar + " m;");
                    }
                    if (rest.getUrMeasureD() != urinalDistAxisRightBar) {
                        check++;
                        restIrr.add("a distância entre o eixo do mictório acessível até a barra de apoio direita é diferente de " + urinalDistAxisRightBar + " m;");
                    }
                    if (rest.getUrMeasureE() < urinalVertBarMinLength) {
                        check++;
                        restIrr.add("o comprimento da barra de apoio esquerda do mictório acessível é inferior à " + urinalVertBarMinLength + " m;");
                    }
                    if (rest.getUrMeasureG() < urinalVertBarMinLength) {
                        check++;
                        restIrr.add("o comprimento da barra de apoio direita do mictório acessível é inferior à " + urinalVertBarMinLength + " m;");
                    }
                    if (rest.getUrMeasureF() < urinalVertBarFloorHeight) {
                        check++;
                        restIrr.add("a altura de instalação da barra de apoio esquerda do mictório acessível é diferente de " + urinalVertBarFloorHeight + " m;");
                    }
                    if (rest.getUrMeasureH() < urinalVertBarFloorHeight) {
                        check++;
                        restIrr.add("a altura de instalação da barra de apoio direita do mictório acessível é diferente de " + urinalVertBarFloorHeight + " m;");
                    }
                    if (rest.getUrMeasureI() != urinalPartitionWidth) {
                        check++;
                        restIrr.add("a largura da divisória do mictório acessível é diferente de " + urinalPartitionWidth + " m;");
                    }
                    if (rest.getUrMeasureJ() != urinalPartitionHeight) {
                        check++;
                        restIrr.add("a altura da divisória do mictório acessível é diferente de " + urinalPartitionHeight + " m;");
                    }
                    if (rest.getUrMeasureK() != urinalPartitionFloorHeight) {
                        check++;
                        restIrr.add("a altura do piso até a divisória do mictório acessível é diferente de " + urinalPartitionFloorHeight + " m;");
                    }

                    if (rest.getUrObs() != null && rest.getUrObs().length() > 0) {
                        check++;
                        restIrr.add("as seguintes observações podem ser feitas sobre os mictórios: " + rest.getUrObs() + " m;");
                    }
                }
            }

            if (restType == 3) {
                int toiletType = rest.getToType();

                if (toiletType != 1 && rest.getToHasSoculo() == 1) {
                    StringBuilder builder = new StringBuilder();
                    if (rest.getFrSoculo() > maxSoculo) {
                        soculoIrregular(builder);
                        builder.append("projeção frontal superior à " + maxSoculo + " m");
                    }
                    if (rest.getLatSoculo() > maxSoculo) {
                        soculoIrregular(builder);
                        builder.append("projeção lateral superior à " + maxSoculo + " m");
                    }
                    if (rest.getSocCorners() == 1) {
                        soculoIrregular(builder);
                        builder.append("presença de cantos vivos;");
                    }

                    if (builder.toString().length() > 0) {
                        check++;
                        restIrr.add(builder.toString());
                    }
                }

                if (rest.getToHeightNoSeat() < minToiletHeightChild || rest.getToHeightNoSeat() > maxToiletHeightChild) {
                    check++;
                    restIrr.add("a altura da bacia sanitária está fora dos padrões estipulados;");
                }
                if (rest.getToHasSeat() == 0) {
                    check++;
                    restIrr.add("a bacia sanitária não possui assento;");
                } else if (rest.getToHeightSeat() > maxToiletHeightChildSeat) {
                    check++;
                    restIrr.add("a altura da bacia sanitária com assento é superior à " + maxToiletHeightChildSeat + " m;");
                }

                if (toiletType == 2 && rest.getToHasFrontBar() == 0 && rest.getToHasWall() == 1) {
                    check++;
                    restIrr.add("O sanitário não possui barra de apoio posterior;");
                } else if (rest.getToHasFrontBar() == 0) {
                    check++;
                    restIrr.add("O sanitário não possui barra de apoio posterior;");
                } else {
                    if (toiletType == 2 && rest.getFrBarA() < frBarHeightChild) {
                        check++;
                        restIrr.add("a altura da barra de apoio posterior está abaixo de " + frBarHeightChild + " m;");
                    } else if (toiletType == 2 && rest.getFrBarA() > frBarMaxHeightChild) {
                        check++;
                        restIrr.add("a altura da barra de apoio posterior está acima de " + frBarMaxHeightChild + " m;");
                    } else if (toiletType != 2 && rest.getFrBarA() != frBarHeightChild) {
                        check++;
                        restIrr.add("a altura da barra de apoio posterior é diferente de " + frBarHeightChild + " m;");
                    }

                    if (rest.getFrBarC() != frBarToiletAxisChild) {
                        check++;
                        restIrr.add("a distância entre o eixo da bacia sanitária até a extremidade da barra de apoio posterior em direção à parede lateral " +
                                "é diferente de " + frBarToiletAxisChild + " m;");
                    }

                    if (rest.getFrBarB() < frBarMinLength) {
                        check++;
                        restIrr.add("o comprimento da barra de apoio posterior é inferior à " + frBarMinLength + " m;");
                    }

                    if (rest.getFrBarSect() < minHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra de apoio posterior é inferior à " + minHandrailGrip + " mm;");
                    } else if (rest.getFrBarSect() > maxHandrailGrip) {
                        check++;
                        restIrr.add("a seção transversal da barra de apoio posterior é superior à " + maxHandrailGrip + " mm;");
                    }

                    if (rest.getFrBarDist() < minDistHandrail) {
                        check++;
                        restIrr.add("a distância da parede até a barra de apoio posterior é inferior à " + minDistHandrail + " mm;");
                    }
                }


                if (rest.getToHasWall() == 0) {
                    if (rest.getHasSideBar() == 0) {
                        check++;
                        restIrr.add("o sanitário não possui barra reta lateral fixa;");
                    } else if (rest.getToHasWall() == 1) {
                        if (rest.getSideBarD() < fixSideBarMinLengthToilet) {
                            check++;
                            restIrr.add("o comprimento mínimo da barra lateral fixa à frente da bacia sanitária é inferior à " + fixSideBarMinLengthToilet + " m;");
                        }

                        if (rest.getSideBarE() != frBarHeightChild) {
                            check++;
                            restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightChild + " m;");
                        }

                        if (rest.getSideBarDistG() != sideBarToiletAxisChild) {
                            check++;
                            restIrr.add("a distância do eixo da bacia sanitária até a barra lateral fixa é diferente de " + sideBarToiletAxisChild + " m;");
                        }

                        if (rest.getSideBarSect() < minHandrailGrip) {
                            check++;
                            restIrr.add("a seção transversal da barra lateral fixa é inferior à " + minHandrailGrip + " mm;");
                        } else if (rest.getSideBarSect() > maxHandrailGrip) {
                            check++;
                            restIrr.add("a seção transversal da barra lateral fixa é superior à " + maxHandrailGrip + " mm;");
                        }
                    }

                    if (rest.getHasArtBar() == 0) {
                        check++;
                        restIrr.add("a bacia sanitária não possui barra lateral articulada");
                    } else if (rest.getHasArtBar() == 1) {
                        if (rest.getArtBarH() != frBarHeightChild) {
                            check++;
                            restIrr.add("a altura da barra lateral articulada é diferente de " + frBarHeightChild + " m;");
                        }

                        if (rest.getArtBarI() < artSideBarMinLengthToilet) {
                            check++;
                            restIrr.add("o comprimento mínimo da barra lateral articulada à frente da bacia sanitária é inferior à " + artSideBarMinLengthToilet + " m;");
                        }

                        if (rest.getArtBarJ() != sideBarToiletAxisChild) {
                            check++;
                            restIrr.add("a distância do eixo da bacia sanitária até a barra lateral articulada é diferente de " + sideBarToiletAxisChild + " m;");
                        }

                        if (rest.getArtBarSect() < minHandrailGrip) {
                            check++;
                            restIrr.add("a seção transversal da barra lateral articulada é inferior à " + minHandrailGrip + " mm;");
                        } else if (rest.getArtBarSect() > maxHandrailGrip) {
                            check++;
                            restIrr.add("a seção transversal da barra lateral articulada é superior à " + maxHandrailGrip + " mm;");
                        }
                    }

                } else if (rest.getToHasWall() == 1) {
                    if (rest.getHasHorBar() == 0) {
                        check++;
                        restIrr.add("o sanitário não possui barra reta lateral horizontal;");
                    } else if (rest.getHasHorBar() == 1) {
                        if (rest.getHorBarD() != frBarHeightChild) {
                            check++;
                            restIrr.add("a altura da barra lateral horizontal é diferente de " + frBarHeightChild + " m;");
                        }

                        if (rest.getHorBarE() < frBarMinLength) {
                            check++;
                            restIrr.add("o comprimento da barra lateral horizontal é inferior à " + frBarMinLength + " m;");
                        }

                        if (rest.getHorBarF() != wallFixBarDistToiletEndBar) {
                            check++;
                            restIrr.add("o comprimento da barra lateral horizontal à frente da bacia sanitária é diferente de " + wallFixBarDistToiletEndBar + " m;");
                        }

                        if (rest.getHorBarDistG() != sideBarToiletAxisChild) {
                            check++;
                            restIrr.add("a distância do eixo da bacia sanitária até a barra lateral horizontal é diferente de " + sideBarToiletAxisChild + " m;");
                        }

                        if (rest.getHorBarSect() < minHandrailGrip) {
                            check++;
                            restIrr.add("a seção transversal da barra lateral horizontal é inferior à " + minHandrailGrip + " mm;");
                        } else if (rest.getHorBarSect() > maxHandrailGrip) {
                            check++;
                            restIrr.add("a seção transversal da barra lateral horizontal é superior à " + maxHandrailGrip + " mm;");
                        }

                        if (rest.getHorBarDist() < minDistHandrail) {
                            check++;
                            restIrr.add("a distância da barra lateral horizontal até a parede é inferior à " + minDistHandrail + " mm;");
                        }
                    }

                    if (rest.getHasVertBar() == 0) {
                        check++;
                        restIrr.add("o sanitário não possui barra reta lateral vertical;");
                    } else if (rest.getHasVertBar() == 1) {
                        if (rest.getVertBarH() != wallFixBarVertDistToilet) {
                            check++;
                            restIrr.add("a distância entre a bacia sanitária e a barra lateral de apoio vertical é diferente de " + wallFixBarVertDistToilet + " m;");
                        }

                        if (rest.getVertBarJ() < wallFixBarVertMinLength) {
                            check++;
                            restIrr.add("o comprimento da barra lateral de apoio vertical é inferior a " + wallFixBarVertMinLength + " m;");
                        }

                        if (rest.getVertBarI() != wallFixBarDistInterBar) {
                            check++;
                            restIrr.add("a distância entre as barras laterais de apoio vertical e horizontal é diferente de " + wallFixBarDistInterBar + " m;");
                        }

                        if (rest.getVertBarSect() < minHandrailGrip) {
                            check++;
                            restIrr.add("a seção transversal da barra lateral vertical é inferior à " + minHandrailGrip + " mm;");
                        } else if (rest.getVertBarSect() > maxHandrailGrip) {
                            check++;
                            restIrr.add("a seção transversal da barra lateral vertical é superior à " + maxHandrailGrip + " mm;");
                        }

                        if (rest.getVertBarDist() < minDistHandrail) {
                            check++;
                            restIrr.add("a distância da barra lateral vertical até a parede é inferior à " + minDistHandrail + " mm;");
                        }
                    }
                }
            }
        }
        if (rest.getRestFirstPhoto() != null)
            restIrr.add("Fotos Acesso Sanitário: " + rest.getRestFirstPhoto());
        if (rest.getRestUpperPhoto() != null)
            restIrr.add("Fotos Gerais Sanitário: " + rest.getRestUpperPhoto());
        if (rest.getRestToiletPhoto() != null)
            restIrr.add("Fotos Bacia Sanitária: " + rest.getRestToiletPhoto());
        if (rest.getRestAccessPhoto() != null)
            restIrr.add("Fotos Acessórios 1: " + rest.getRestAccessPhoto());
        if (rest.getRestAccessPhoto2() != null)
            restIrr.add("Fotos Acessórios 2: " + rest.getRestAccessPhoto2());
        if (rest.getRestSinkPhoto() != null)
            restIrr.add("Fotos Lavatório: " + rest.getRestSinkPhoto());
        if (rest.getRestUrinalPhoto() != null)
            restIrr.add("Fotos Mictório: " + rest.getRestUrinalPhoto());

        return restIrr;
    }


    public static String restroomTyping(int i) {
        switch (i) {
            case 0:
                return "Acessível Independente";
            case 1:
                return "Coletívo Acessível";
            case 2:
                return "Coletivo Não Acessível";
            case 3:
                return "Infantil";
            default:
                return "";
        }
    }

    public static String restroomGender(int gender) {
        switch (gender) {
            case 0:
                return "Masculino";
            case 1:
                return "Feminino";
            case 2:
                return "Unissex";
            default:
                return "";
        }
    }

    public static void soculoIrregular(StringBuilder builder) {
        if (!irrSoculo) {
            irrSoculo = true;
            builder.append("a bacia sanitária possui sóculo com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
