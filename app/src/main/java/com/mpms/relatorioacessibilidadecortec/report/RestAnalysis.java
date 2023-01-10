package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.report.Components.DoorSillAnalysis;

import java.util.ArrayList;
import java.util.List;

public class RestAnalysis implements StandardMeasurements {

    static boolean irrSoculo;

    public static void restVerification(List<BlockSpaceEntry> blockList, List<RestroomEntry> restList) {

        for (BlockSpaceEntry block : blockList) {
            if (block.getBlockSpaceType() == 2) {
                int blockID = block.getBlockSpaceID();

                for (int i = 0; i < restList.size(); i++) {
                    int check = 0;
                    AmbientAnalysis.err = false;
                    List<String> restIrr = new ArrayList<>();
                    RestroomEntry rest = restList.get(i);
                    if (rest.getBlockID() == blockID) {
                        int restType = rest.getRestType();

                        if (rest.getAccessRoute() == 0) {
                            check++;
                            restIrr.add("ausência de rota acessível ao sanitário;");
                        } else if (rest.getAccessRoute() == 0 && rest.getAccessRouteObs() != null) {
                            check++;
                            restIrr.add("ausência de rota acessível ao sanitário e as seguintes observações devem ser feitas: " + rest.getAccessRouteObs() + ";");
                        } else if (rest.getAccessRoute() == 1 && rest.getAccessRouteObs() != null) {
                            check++;
                            restIrr.add("presença de rota acessível ao sanitário, com as seguintes observações: " + rest.getAccessRouteObs() + ";");
                        }

                        if (rest.getIntRestroom() == 0) {
                            check++;
                            restIrr.add("não integração às demais instalações sanitárias;");
                        } else if (rest.getIntRestroom() == 0 && rest.getIntRestObs() != null) {
                            check++;
                            restIrr.add("não integração às demais instalações sanitárias e as seguintes observações devem ser feitas: " + rest.getIntRestObs() + ";");
                        } else if (rest.getIntRestroom() == 1 && rest.getIntRestObs() != null) {
                            check++;
                            restIrr.add("o sanitário está integrado às demais instalações sanitárias, com as seguintes observações: " + rest.getIntRestObs() + ";");
                        }

                        if (rest.getRestDistance() == 0) {
                            check++;
                            restIrr.add("localizado a uma distância superior a 50m em relação aos outros ambientes da escola;");
                        } else if (rest.getRestDistance() == 0 && rest.getRestDistObs() != null) {
                            check++;
                            restIrr.add("localizado a uma distância superior a 50m em relação aos outros ambientes da escola e " +
                                    "as seguintes observações devem ser feitas: " + rest.getRestDistObs() + ";");
                        } else if (rest.getRestDistance() == 1 && rest.getRestDistObs() != null) {
                            check++;
                            restIrr.add("não está localizado a uma distância superior a 50m em relação aos outros ambientes da escola, " +
                                    "mas possui as seguintes observações: " + rest.getRestDistObs() + ";");
                        }

                        if (rest.getExEntrance() == 0) {
                            check++;
                            restIrr.add("ausência de entrada independente;");
                        } else if (rest.getExEntrance() == 0 && rest.getExEntObs() != null) {
                            check++;
                            restIrr.add("ausência de entrada independente e as seguintes observações devem ser feitas: " + rest.getExEntObs() + ";");
                        } else if (rest.getExEntrance() == 1 && rest.getExEntObs() != null) {
                            check++;
                            restIrr.add("presença de entrada independente, mas possui as seguintes observações: " + rest.getExEntObs() + ";");
                        }

                        if (rest.getAntiDriftFloor() == 0) {
                            check++;
                            restIrr.add("ausência de piso antiderrapante;");
                        } else if (rest.getAntiDriftFloor() == 0 && rest.getAntiDriftFloorObs() != null) {
                            check++;
                            restIrr.add("ausência de piso antiderrapante e as seguintes observações devem ser feitas: " + rest.getAntiDriftFloorObs() + ";");
                        } else if (rest.getAntiDriftFloor() == 1 && rest.getAntiDriftFloorObs() != null) {
                            check++;
                            restIrr.add("presença de piso antiderrapante, mas possui as seguintes observações: " + rest.getAntiDriftFloorObs() + ";");
                        }

                        if (rest.getRestDrain() == 0) {
                            check++;
                            restIrr.add("presença de grelhas e/ou ralos na área de manobra;");
                        } else if (rest.getRestDrain() == 0 && rest.getAntiDriftFloorObs() != null) {
                            check++;
                            restIrr.add("presença de grelhas e/ou ralos na área de manobra e as seguintes observações devem ser feitas: " + rest.getRestDistObs() + ";");
                        } else if (rest.getRestDrain() == 1 && rest.getAntiDriftFloorObs() != null) {
                            check++;
                            restIrr.add("presença de piso antiderrapante, mas possui as seguintes observações: " + rest.getRestDistObs() + ";");
                        }

                        if (rest.getRestSwitch() == 0) {
                            check++;
                            restIrr.add("ausência de interruptor de energia;");
                        } else if (rest.getRestSwitch() == 0 && rest.getSwitchObs() != null) {
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
                            if (rest.getSwitchObs() != null) {
                                check++;
                                restIrr.add("observações a serem feitas sobre o interruptor de energia: " + rest.getSwitchObs() + ";");
                            }
                        }

                        if (rest.getDoorWidth() < freeSpaceGeneral) {
                            check++;
                            restIrr.add("a largura do vão livre da porta é inferior à " + freeSpaceGeneral + " m;");
                        }

                        if (rest.getHasPict() == 0) {
                            check++;
                            restIrr.add("a porta do sanitário não possui os pictogramas necessários;");
                        } else if (rest.getHasPict() == 0 && rest.getPictObs() != null) {
                            check++;
                            restIrr.add("a porta do sanitário não possui os pictogramas necessários e as seguintes observações devem ser feitas: " + rest.getPictObs() + ";");
                        } else if (rest.getHasPict() == 1 && rest.getPictObs() != null) {
                            check++;
                            restIrr.add("a porta do sanitário possui os pictogramas necessários, mas as seguintes observações devem ser feitas: " + rest.getPictObs() + ";");
                        }

                        if (rest.getOpDirObs() != null) {
                            String dir;
                            if (rest.getOpDir() == 0)
                                dir = "interno";
                            else
                                dir = "externo";

                            check++;
                            restIrr.add("a porta, com sentido de abertura " + dir + ", possui as seguintes observações: " + rest.getOpDirObs() + ";");
                        }

                        if (rest.getHasCoat() == 0) {
                            check++;
                            restIrr.add("a porta do sanitário não possui revestimentos anti-impacto;");
                        } else if (rest.getHasCoat() == 0 && rest.getCoatObs() != null) {
                            check++;
                            restIrr.add("a porta do sanitário não possui revestimentos anti-impacto e as seguintes observações devem ser feitas: " + rest.getCoatObs() + ";");
                        } else if (rest.getHasCoat() == 1) {
                            if (rest.getCoatHeight() < antiImpactCoatHeight) {
                                check++;
                                restIrr.add("o revestimento anti-impacto da porta possui altura inferior à " + antiImpactCoatHeight + " m;");
                            }
                            if (rest.getCoatObs() != null) {
                                check++;
                                restIrr.add("observações sobre o revestimento anti-impacto da porta: " + rest.getCoatObs() + ";");
                            }
                        }

                        if (rest.getHasVertSign() == 0) {
                            check++;
                            restIrr.add("a porta do sanitário não possui sinalização vertical composta;");
                        } else if (rest.getHasVertSign() == 0 && rest.getVertSignObs() != null) {
                            check++;
                            restIrr.add("a porta do sanitário não possui sinalização vertical composta e as seguintes observações devem ser feitas: " + rest.getVertSignObs() + ";");
                        } else if (rest.getHasVertSign() == 1 && rest.getVertSignObs() != null) {
                            check++;
                            restIrr.add("a porta do sanitário possui sinalização vertical composta, mas as seguintes observações devem ser feitas: " + rest.getVertSignObs() + ";");
                        }

                        String doorSill = DoorSillAnalysis.restSillVerification(rest);
                        if (doorSill != null && doorSill.length() > 0) {
                            check++;
                            restIrr.add(doorSill);
                        }

                        if (rest.getHasTactSign() == 0) {
                            check++;
                            restIrr.add("ausência de sinalização tátil adjacente à porta;");
                        } else if (rest.getHasVertSign() == 0 && rest.getVertSignObs() != null) {
                            check++;
                            restIrr.add("ausência de sinalização tátil adjacente à porta e as seguintes observações devem ser feitas: " + rest.getVertSignObs() + ";");
                        } else if (rest.getHasVertSign() == 1 && rest.getVertSignObs() != null) {
                            check++;
                            restIrr.add("presença de sinalização tátil adjacente à porta, mas as seguintes observações devem ser feitas: " + rest.getVertSignObs() + ";");
                        }

                        if (rest.getHasHorHandle() == 0) {
                            check++;
                            restIrr.add("ausência de puxador horizontal na porta;");
                        } else if (rest.getHasHorHandle() == 0 && rest.getHandleObs() != null) {
                            check++;
                            restIrr.add("ausência de puxador horizontal na porta e as seguintes observações devem ser feitas: " + rest.getHandleObs() + ";");
                        } else if (rest.getHasHorHandle() == 1) {
                            if (rest.getHandleHeight() < minDoorHandle) {
                                check++;
                                restIrr.add("altura do puxador horizontal inferior à " + minDoorHandle + " m;");
                            } else if (rest.getHandleHeight() > maxDoorHandle) {
                                check++;
                                restIrr.add("altura do puxador horizontal superior à " + maxDoorHandle + " m;");
                            }

                            if (rest.getHandleLength() < minHorHandleLength) {
                                check++;
                                restIrr.add("o comprimento do puxador horizontal é inferior à " + minHorHandleLength + " m;");
                            }

                            if (rest.getHandleDiam() < minHandleGrip) {
                                check++;
                                restIrr.add("o diâmetro do puxador horizontal é inferior à " + minHandleGrip + " mm;");
                            } else if (rest.getHandleDiam() > maxHandleGrip) {
                                check++;
                                restIrr.add("o diâmetro do puxador horizontal é superior à " + maxHandleGrip + " mm;");
                            }

                            if (rest.getHandleObs() != null) {
                                check++;
                                restIrr.add("observações sobre o puxador horizontal: " + rest.getHandleObs() + ";");
                            }
                        }

//                        if (rest.getUpViewMeasureA() < 0.4) {
//                        }
                        if (rest.getUpViewMeasureB() < minDistToiletWall) {
                            check++;
                            restIrr.add("distância entre o sanitário e a parede lateral é inferior à " + minDistToiletWall + " m;");
                        }
                        if (rest.getUpViewMeasureC() > maxManUnderToilet) {
                            check++;
                            restIrr.add("a área de manobra utiliza mais do que " + maxManUnderToilet + " m sob a bacia sanitária;");
                        }
                        if (rest.getUpViewMeasureD() != maneuverArea) {
                            check++;
                            restIrr.add("a área de manobra possui diâmetro distinto de " + maneuverArea + " m;");
                        }
                        if (rest.getUpViewMeasureE() > maxManUnderSink) {
                            check++;
                            restIrr.add("a área de manobra utiliza mais do que " + maxManUnderSink + " m sob o lavatório;");
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
                                restIrr.add("presença de cantos vivos;");
                            }

                            if (builder.toString().length() > 0) {
                                check++;
                                restIrr.add(builder.toString());
                            }
                        }

                        if (restType == 3 && (rest.getToHeightNoSeat() < minToiletHeightChild || rest.getToHeightNoSeat() > maxToiletHeightChild)) {
                            check++;
                            restIrr.add("a altura da bacia sanitária está fora dos padrões estipulados;");
                        } else if (restType != 3 && rest.getToHeightNoSeat() < minToiletHeightAdult) {
                            check++;
                            restIrr.add("a altura da bacia sanitária é inferior à " + minToiletHeightAdult + " m;");
                        } else if (restType != 3 && rest.getToHeightNoSeat() > maxToiletHeightAdult) {
                            check++;
                            restIrr.add("a altura da bacia sanitária é superior à " + maxToiletHeightAdult + " m;");
                        }

                        if (rest.getToHasSeat() == 0) {
                            check++;
                            restIrr.add("a bacia sanitária não possui assento;");
                        } else {
                            if (restType == 3 && rest.getToHeightSeat() > maxToiletHeightChildSeat) {
                                check++;
                                restIrr.add("a altura da bacia sanitária com assento é superior à " + maxToiletHeightChildSeat + " m;");
                            } else if (restType != 3 && rest.getToHeightSeat() > maxToiletHeightAdultSeat) {
                                check++;
                                restIrr.add("a altura da bacia sanitária com assento é superior à " + maxToiletHeightAdultSeat + " m;");
                            }
                        }

                        if (rest.getToHasFrontBar() == 0) {
                            check++;
                            restIrr.add("ausência da barra de apoio posterior");
                        } else {
                            if (restType == 3) {
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
                                    restIrr.add("a distância entre o eixo da bacia sanitária em direção à parede lateral é diferente de " + frBarToiletAxisChild + " m;");
                                }
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
                                    restIrr.add("a distância entre o eixo da bacia sanitária em direção à parede lateral é diferente de " + frBarToiletAxisChild + " m;");
                                }
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
                            if (rest.getHasHorBar() == 0) {
                                check++;
                                restIrr.add("o sanitário não possui barra reta lateral fixa;");
                            } else {
                                if (rest.getHorBarD() < fixSideBarMinLengthToilet) {
                                    check++;
                                    restIrr.add("o comprimento mínimo da barra lateral fixa à frente da bacia sanitária é inferior à " + fixSideBarMinLengthToilet + " m;");
                                }

                                if (restType == 3 && rest.getHorBarE() != frBarHeightChild) {
                                    check++;
                                    restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightChild + " m;");
                                } else if (restType != 3 && rest.getHorBarE() != frBarHeightAdult) {
                                    check++;
                                    restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightAdult + " m;");
                                }

                                if (restType == 3 && rest.getHorBarDistG() != sideBarToiletAxisChild) {
                                    check++;
                                    restIrr.add("a distância do eixo da bacia sanitária até a barra lateral fixa é diferente de " + sideBarToiletAxisChild + " m;");
                                } else if (restType != 3 && rest.getHorBarDistG() != sideBarToiletAxisAdult) {
                                    check++;
                                    restIrr.add("a distância do eixo da bacia sanitária até a barra lateral fixa é diferente de " + sideBarToiletAxisAdult + " m;");
                                }

                                if (rest.getHorBarSect() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral fixa é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getHorBarSect() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral fixa é superior à " + maxHandrailGrip + " mm;");
                                }
                            }

                            if (rest.getHasArtBar() == 1) {
                                if (restType == 3 && rest.getArtBarH() != frBarHeightChild) {
                                    check++;
                                    restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightChild + " m;");
                                } else if (restType != 3 && rest.getArtBarH() != frBarHeightAdult) {
                                    check++;
                                    restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightAdult + " m;");
                                }

                                if (rest.getArtBarI() < artSideBarMinLengthToilet) {
                                    check++;
                                    restIrr.add("o comprimento mínimo da barra lateral articulada à frente da bacia sanitária é inferior à " + artSideBarMinLengthToilet + " m;");
                                }

                                if (restType == 3 && rest.getArtBarJ() != sideBarToiletAxisChild) {
                                    check++;
                                    restIrr.add("a distância do eixo da bacia sanitária até a barra lateral articulada é diferente de " + sideBarToiletAxisChild + " m;");
                                } else if (restType != 3 && rest.getArtBarJ() != sideBarToiletAxisAdult) {
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
                        } else {
                            if (rest.getHasHorBar() == 0) {
                                check++;
                                restIrr.add("o sanitário não possui barra reta lateral horizontal;");
                            } else {
                                if (restType == 3 && rest.getHorBarD() != frBarHeightChild) {
                                    check++;
                                    restIrr.add("a altura da barra lateral horizontal é diferente de " + frBarHeightChild + " m;");
                                } else if (restType != 3 && rest.getHorBarD() != frBarHeightAdult) {
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

                                if (restType == 3 && rest.getHorBarDistG() != sideBarToiletAxisChild) {
                                    check++;
                                    restIrr.add("a distância do eixo da bacia sanitária até a barra lateral horizontal é diferente de " + sideBarToiletAxisChild + " m;");
                                } else if (restType != 3 && rest.getHorBarDistG() != sideBarToiletAxisAdult) {
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
                                restIrr.add("o sanitário não possui a barra lateral de apoio vertical;");
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

                        if (rest.getToActObs() != null) {
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

                        if (rest.getPapHoldObs() != null) {
                            check++;
                            restIrr.add("as seguintes observações devem ser feitas sobre a papeleira: " + rest.getPapHoldObs() + ";");
                        }

                        if (rest.getHasDouche() == 0) {
                            check++;
                            restIrr.add("o sanitário não possui ducha higiênica;");
                        } else {
                            if (rest.getDoucheHeight() < minActionHeight) {
                                check++;
                                restIrr.add("o acionamento da ducha higiência está abaixo de " + minActionHeight + " m;");
                            } else if (rest.getDoucheHeight() > maxActionHeight) {
                                check++;
                                restIrr.add("o acionamento da ducha higiência está acima de " + maxActionHeight + " m;");
                            }
                        }
                        if (rest.getDoucheObs() != null) {
                            check++;
                            restIrr.add("as seguintes observações podem ser feitas sobre a ducha higiênica: " + rest.getDoucheObs() + ";");
                        }

                        if (rest.getToiletObs() != null) {
                            check++;
                            restIrr.add("as seguintes observações podem ser feitas sobre o sanitário: " + rest.getToiletObs());
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
                        if (rest.getHangerObs() != null) {
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

                        if (rest.getObjHoldObs() != null) {
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

                        if (rest.getSoapHoldObs() != null) {
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

                        if (rest.getTowelHoldHeight() != null) {
                            check++;
                            restIrr.add("as seguintes observações podem ser feitas sobre o toalheiro: " + rest.getSoapHoldObs() + ";");
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
                        if (rest.getWallMirrorObs() != null) {
                            check++;
                            restIrr.add("as seguintes observações podem ser feitas sobre o espelho instalado: " + rest.getWallMirrorObs() + ";");
                        }

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
                            restIrr.add("o espaço de aproximação frontal na altura dos joelhos é inferior à " + sinkFrontApproxKnee + " m;");
                        }

                        if (rest.getApproxMeasureD() < sinkFrontApproxFeet) {
                            check++;
                            restIrr.add("o espaço de aproximação frontal na altura dos pés é inferior à " + sinkFrontApproxFeet + " m;");
                        }

                        if (rest.getHasSinkBar() == 0) {
                            check++;
                            restIrr.add("o lavatório não possui barras de apoio;");
                        }
                        if (rest.getHasSinkBar() == 2) {
                            check++;
                            restIrr.add("o lavatório possui barras de apoio que estão fora do padrão estipulado em norma;");
                        } else {
                            if (sinkType == 0) {
                                if (rest.getHasLeftBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                                } else {
                                    if (rest.getLeftHorMeasureA() > sinkBarMaxFrontDist) {
                                        check++;
                                        restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                    }
                                    if (rest.getLeftHorMeasureB() < sinkHorBarMinLatDist) {
                                        check++;
                                        restIrr.add("o espaçamento entre o lavatório e a barra esquerda é inferior à " + sinkHorBarMinLatDist + " m;");
                                    }
                                    if (rest.getLeftHorMeasureC() < sinkHorBarMinUpperHeight) {
                                        check++;
                                        restIrr.add("a distância entre o piso e a face superior da barra esquerda é inferior à " + sinkHorBarMinUpperHeight + " m;");
                                    } else if (rest.getLeftHorMeasureC() > sinkHorBarMaxUpperHeight) {
                                        check++;
                                        restIrr.add("a distância entre o piso e a face superior da barra esquerda é superior à " + sinkHorBarMaxUpperHeight + " m;");
                                    }
                                    if (rest.getLeftHorMeasureD() != sinkHorBarLowerHeight) {
                                        check++;
                                        restIrr.add("a distância entre o piso e a face inferior da barra esquerda é diferente de " + sinkHorBarLowerHeight + " m;");
                                    }

                                    if (rest.getLeftBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getLeftBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }

                                if (rest.getHasRightBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                                } else {
                                    if (rest.getRightHorMeasureA() > sinkBarMaxFrontDist) {
                                        check++;
                                        restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra direita é superior à " + sinkBarMaxFrontDist + " m;");
                                    }
                                    if (rest.getRightHorMeasureB() < sinkHorBarMinLatDist) {
                                        check++;
                                        restIrr.add("o espaçamento entre o lavatório e a barra direita é inferior à " + sinkHorBarMinLatDist + " m;");
                                    }
                                    if (rest.getRightHorMeasureC() < sinkHorBarMinUpperHeight) {
                                        check++;
                                        restIrr.add("a distância entre o piso e a face superior da barra direita é inferior à " + sinkHorBarMinUpperHeight + " m;");
                                    } else if (rest.getRightHorMeasureC() > sinkHorBarMaxUpperHeight) {
                                        check++;
                                        restIrr.add("a distância entre o piso e a face superior da barra direita é superior à " + sinkHorBarMaxUpperHeight + " m;");
                                    }
                                    if (rest.getRightHorMeasureD() != sinkHorBarLowerHeight) {
                                        check++;
                                        restIrr.add("a distância entre o piso e a face inferior da barra direita é diferente de " + sinkHorBarLowerHeight + " m;");
                                    }

                                    if (rest.getRightBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getRightBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }
                            } else if (sinkType == 1) {
                                if (rest.getHasLeftBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                                } else {
                                    if (rest.getLeftVertMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                        check++;
                                        restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical esquerda é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                    }
                                    if (rest.getLeftVertMeasureB() > sinkBarMaxFrontDist) {
                                        check++;
                                        restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                    }
                                    if (rest.getLeftVertMeasureC() < sinkVertBarMinLength) {
                                        check++;
                                        restIrr.add("o comprimento da barra vertical esquerda é inferior à " + sinkVertBarMinLength + " m;");
                                    }
                                    if (rest.getLeftVertMeasureD() != sinkVertBarHeight) {
                                        check++;
                                        restIrr.add("a altura de instalação da barra vertical esquerda é diferente de " + sinkVertBarHeight + " m;");
                                    }

                                    if (rest.getLeftBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getLeftBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }

                                if (rest.getHasRightBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                                } else {
                                    if (rest.getRightVertMeasureA() < sinkVertBarMaxDistSinkAxis) {
                                        check++;
                                        restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical direita é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                    }

                                    if (rest.getRightBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getRightBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }
                            } else if (sinkType == 2) {
                                if (rest.getHasLeftBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                                } else {
                                    if (rest.getLeftVertMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                        check++;
                                        restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical esquerda é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                    }
                                    if (rest.getLeftVertMeasureB() > sinkBarMaxFrontDist) {
                                        check++;
                                        restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                    }
                                    if (rest.getLeftVertMeasureC() < sinkVertBarMinLength) {
                                        check++;
                                        restIrr.add("o comprimento da barra vertical esquerda é inferior à " + sinkVertBarMinLength + " m;");
                                    }
                                    if (rest.getLeftVertMeasureD() != sinkVertBarHeight) {
                                        check++;
                                        restIrr.add("a altura de instalação da barra vertical esquerda é diferente de " + sinkVertBarHeight + " m;");
                                    }

                                    if (rest.getLeftBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getLeftBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }

                                if (rest.getHasRightBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                                } else {
                                    if (rest.getRightHorMeasureA() > sinkBarMaxFrontDist) {
                                        check++;
                                        restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra direita é superior à " + sinkBarMaxFrontDist + " m;");
                                    }
                                    if (rest.getRightHorMeasureB() < sinkHorBarMinLatDist) {
                                        check++;
                                        restIrr.add("o espaçamento entre o lavatório e a barra direita é inferior à " + sinkHorBarMinLatDist + " m;");
                                    }
                                    if (rest.getRightHorMeasureC() < sinkHorBarMinUpperHeight) {
                                        check++;
                                        restIrr.add("a distância entre o piso e a face superior da barra direita é inferior à " + sinkHorBarMinUpperHeight + " m;");
                                    } else if (rest.getRightHorMeasureC() > sinkHorBarMaxUpperHeight) {
                                        check++;
                                        restIrr.add("a distância entre o piso e a face superior da barra direita é superior à " + sinkHorBarMaxUpperHeight + " m;");
                                    }
                                    if (rest.getRightHorMeasureD() != sinkHorBarLowerHeight) {
                                        check++;
                                        restIrr.add("a distância entre o piso e a face inferior da barra direita é diferente de " + sinkHorBarLowerHeight + " m;");
                                    }

                                    if (rest.getRightBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getRightBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }
                            } else if (sinkType == 3) {
                                if (rest.getHasLeftBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                                } else {
                                    if (rest.getLeftVertMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                        check++;
                                        restIrr.add("a distância do eixo da barra vertical esquerda até a parede é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                    }
                                    if (rest.getLeftVertMeasureB() > sinkBarMaxFrontDist) {
                                        check++;
                                        restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                    }
                                    if (rest.getLeftVertMeasureC() < sinkVertBarMinLength) {
                                        check++;
                                        restIrr.add("o comprimento da barra vertical esquerda é inferior à " + sinkVertBarMinLength + " m;");
                                    }
                                    if (rest.getLeftVertMeasureD() != sinkVertBarHeight) {
                                        check++;
                                        restIrr.add("a altura de instalação da barra vertical esquerda é diferente de " + sinkVertBarHeight + " m;");
                                    }

                                    if (rest.getLeftBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getLeftBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }

                                if (rest.getHasRightBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                                } else {
                                    if (rest.getRightVertMeasureA() < sinkVertBarMaxDistSinkAxis) {
                                        check++;
                                        restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical direita é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                    }

                                    if (rest.getRightBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getRightBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }
                            } else if (sinkType == 4) {
                                if (rest.getHasLeftBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                                } else {
                                    if (rest.getLeftVertMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                        check++;
                                        restIrr.add("a distância do eixo da barra vertical esquerda até a parede é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                    }
                                    if (rest.getLeftVertMeasureB() > sinkBarMaxFrontDist) {
                                        check++;
                                        restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                    }
                                    if (rest.getLeftVertMeasureC() < sinkVertBarMinLength) {
                                        check++;
                                        restIrr.add("o comprimento da barra vertical esquerda é inferior à " + sinkVertBarMinLength + " m;");
                                    }
                                    if (rest.getLeftVertMeasureD() != sinkVertBarHeight) {
                                        check++;
                                        restIrr.add("a altura de instalação da barra vertical esquerda é diferente de " + sinkVertBarHeight + " m;");
                                    }

                                    if (rest.getLeftBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getLeftBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }

                                if (rest.getHasRightBar() == 0) {
                                    check++;
                                    restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                                } else {
                                    if (rest.getRightVertMeasureA() < sinkVertBarMaxDistSinkAxis) {
                                        check++;
                                        restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical direita é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                    }

                                    if (rest.getRightBarDiam() < minHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                    } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                        check++;
                                        restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                    }

                                    if (rest.getRightBarDist() < minDistHandrail) {
                                        check++;
                                        restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                    }
                                }
                            }
                        }

                        if (rest.getSinkHasMirror() == 1) {
                            if (rest.getSiMirrorLow() > sinkMirrorMaxLowerHeight) {
                                check++;
                                restIrr.add("a altura da base do espelho do lavatório é superior à " + sinkMirrorMaxLowerHeight + " m;");
                            }

                            if (rest.getSiMirrorHigh() < minUpperHeightWallMirror) {
                                check++;
                                restIrr.add("a altura do espelho do lavatório é inferior à " + minUpperHeightWallMirror + " m;");
                            }
                        }

                        if (rest.getHasUrinal() == 1) {
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

                                if (rest.getUrinalType() == 0) {
                                    if (rest.getUrMeasureL() > urinalValveHeight) {
                                        check++;
                                        restIrr.add("a altura do piso até a válvula de acionamento do mictório acessível é superior a " + urinalValveHeight + " m;");
                                    }
                                    if (rest.getUrMeasureM() < urinalMinLowerOpenHeight) {
                                        check++;
                                        restIrr.add("a altura do piso até a abertura do mictório acessível é inferior a " + urinalMinLowerOpenHeight + " m;");
                                    } else if (rest.getUrMeasureM() > urinalMaxLowerOpenHeight) {
                                        check++;
                                        restIrr.add("a altura do piso até a abertura do mictório acessível é superior a " + urinalMaxLowerOpenHeight + " m;");
                                    }
                                }
                            }
                        }

                        if (rest.getUrObs() != null) {
                            check++;
                            restIrr.add("as seguintes observações podem ser feitas sobre os mictórios: " + rest.getUrObs() + " m;");
                        }
                    }

                    if (check > 0) {
                        AmbientAnalysis.checkHelpAreaHeader();
                        String type = restroomTyping(rest.getRestType());
                        AmbientAnalysis.helpRestList.add("Sanitário " + type + ", localizado em " + rest.getRestLocation() + ", com as seguintes irregularidades: ");
                        AmbientAnalysis.helpRestIrregular.put(i, restIrr);
                    }
                }
            }
        }

    }

    public static void blockRestVerification(Integer blockID, List<RestroomEntry> restList) {

        int blockRest = 0;

        for (int i = 0; i < restList.size(); i++) {
            int check = 0;
            AmbientAnalysis.err = false;
            List<String> restIrr = new ArrayList<>();
            RestroomEntry rest = restList.get(i);
            if (rest.getBlockID() == blockID) {
                {
                    int restType = rest.getRestType();

                    if (rest.getAccessRoute() == 0) {
                        check++;
                        restIrr.add("ausência de rota acessível ao sanitário;");
                    } else if (rest.getAccessRoute() == 0 && rest.getAccessRouteObs() != null) {
                        check++;
                        restIrr.add("ausência de rota acessível ao sanitário e as seguintes observações devem ser feitas: " + rest.getAccessRouteObs() + ";");
                    } else if (rest.getAccessRoute() == 1 && rest.getAccessRouteObs() != null) {
                        check++;
                        restIrr.add("presença de rota acessível ao sanitário, com as seguintes observações: " + rest.getAccessRouteObs() + ";");
                    }

                    if (rest.getIntRestroom() == 0) {
                        check++;
                        restIrr.add("não integração às demais instalações sanitárias;");
                    } else if (rest.getIntRestroom() == 0 && rest.getIntRestObs() != null) {
                        check++;
                        restIrr.add("não integração às demais instalações sanitárias e as seguintes observações devem ser feitas: " + rest.getIntRestObs() + ";");
                    } else if (rest.getIntRestroom() == 1 && rest.getIntRestObs() != null) {
                        check++;
                        restIrr.add("o sanitário está integrado às demais instalações sanitárias, com as seguintes observações: " + rest.getIntRestObs() + ";");
                    }

                    if (rest.getRestDistance() == 0) {
                        check++;
                        restIrr.add("localizado a uma distância superior a 50m em relação aos outros ambientes da escola;");
                    } else if (rest.getRestDistance() == 0 && rest.getRestDistObs() != null) {
                        check++;
                        restIrr.add("localizado a uma distância superior a 50m em relação aos outros ambientes da escola e " +
                                "as seguintes observações devem ser feitas: " + rest.getRestDistObs() + ";");
                    } else if (rest.getRestDistance() == 1 && rest.getRestDistObs() != null) {
                        check++;
                        restIrr.add("não está localizado a uma distância superior a 50m em relação aos outros ambientes da escola, " +
                                "mas possui as seguintes observações: " + rest.getRestDistObs() + ";");
                    }

                    if (rest.getExEntrance() == 0) {
                        check++;
                        restIrr.add("ausência de entrada independente;");
                    } else if (rest.getExEntrance() == 0 && rest.getExEntObs() != null) {
                        check++;
                        restIrr.add("ausência de entrada independente e as seguintes observações devem ser feitas: " + rest.getExEntObs() + ";");
                    } else if (rest.getExEntrance() == 1 && rest.getExEntObs() != null) {
                        check++;
                        restIrr.add("presença de entrada independente, mas possui as seguintes observações: " + rest.getExEntObs() + ";");
                    }

                    if (rest.getAntiDriftFloor() == 0) {
                        check++;
                        restIrr.add("ausência de piso antiderrapante;");
                    } else if (rest.getAntiDriftFloor() == 0 && rest.getAntiDriftFloorObs() != null) {
                        check++;
                        restIrr.add("ausência de piso antiderrapante e as seguintes observações devem ser feitas: " + rest.getAntiDriftFloorObs() + ";");
                    } else if (rest.getAntiDriftFloor() == 1 && rest.getAntiDriftFloorObs() != null) {
                        check++;
                        restIrr.add("presença de piso antiderrapante, mas possui as seguintes observações: " + rest.getAntiDriftFloorObs() + ";");
                    }

                    if (rest.getRestDrain() == 0) {
                        check++;
                        restIrr.add("presença de grelhas e/ou ralos na área de manobra;");
                    } else if (rest.getRestDrain() == 0 && rest.getAntiDriftFloorObs() != null) {
                        check++;
                        restIrr.add("presença de grelhas e/ou ralos na área de manobra e as seguintes observações devem ser feitas: " + rest.getRestDistObs() + ";");
                    } else if (rest.getRestDrain() == 1 && rest.getAntiDriftFloorObs() != null) {
                        check++;
                        restIrr.add("presença de piso antiderrapante, mas possui as seguintes observações: " + rest.getRestDistObs() + ";");
                    }

                    if (rest.getRestSwitch() == 0) {
                        check++;
                        restIrr.add("ausência de interruptor de energia;");
                    } else if (rest.getRestSwitch() == 0 && rest.getSwitchObs() != null) {
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
                        if (rest.getSwitchObs() != null) {
                            check++;
                            restIrr.add("observações a serem feitas sobre o interruptor de energia: " + rest.getSwitchObs() + ";");
                        }
                    }

                    if (rest.getDoorWidth() < freeSpaceGeneral) {
                        check++;
                        restIrr.add("a largura do vão livre da porta é inferior à " + freeSpaceGeneral + " m;");
                    }

                    if (rest.getHasPict() == 0) {
                        check++;
                        restIrr.add("a porta do sanitário não possui os pictogramas necessários;");
                    } else if (rest.getHasPict() == 0 && rest.getPictObs() != null) {
                        check++;
                        restIrr.add("a porta do sanitário não possui os pictogramas necessários e as seguintes observações devem ser feitas: " + rest.getPictObs() + ";");
                    } else if (rest.getHasPict() == 1 && rest.getPictObs() != null) {
                        check++;
                        restIrr.add("a porta do sanitário possui os pictogramas necessários, mas as seguintes observações devem ser feitas: " + rest.getPictObs() + ";");
                    }

                    if (rest.getOpDirObs() != null) {
                        String dir;
                        if (rest.getOpDir() == 0)
                            dir = "interno";
                        else
                            dir = "externo";

                        check++;
                        restIrr.add("a porta, com sentido de abertura " + dir + ", possui as seguintes observações: " + rest.getOpDirObs() + ";");
                    }

                    if (rest.getHasCoat() == 0) {
                        check++;
                        restIrr.add("a porta do sanitário não possui revestimentos anti-impacto;");
                    } else if (rest.getHasCoat() == 0 && rest.getCoatObs() != null) {
                        check++;
                        restIrr.add("a porta do sanitário não possui revestimentos anti-impacto e as seguintes observações devem ser feitas: " + rest.getCoatObs() + ";");
                    } else if (rest.getHasCoat() == 1) {
                        if (rest.getCoatHeight() < antiImpactCoatHeight) {
                            check++;
                            restIrr.add("o revestimento anti-impacto da porta possui altura inferior à " + antiImpactCoatHeight + " m;");
                        }
                        if (rest.getCoatObs() != null) {
                            check++;
                            restIrr.add("observações sobre o revestimento anti-impacto da porta: " + rest.getCoatObs() + ";");
                        }
                    }

                    if (rest.getHasVertSign() == 0) {
                        check++;
                        restIrr.add("a porta do sanitário não possui sinalização vertical composta;");
                    } else if (rest.getHasVertSign() == 0 && rest.getVertSignObs() != null) {
                        check++;
                        restIrr.add("a porta do sanitário não possui sinalização vertical composta e as seguintes observações devem ser feitas: " + rest.getVertSignObs() + ";");
                    } else if (rest.getHasVertSign() == 1 && rest.getVertSignObs() != null) {
                        check++;
                        restIrr.add("a porta do sanitário possui sinalização vertical composta, mas as seguintes observações devem ser feitas: " + rest.getVertSignObs() + ";");
                    }

                    String doorSill = DoorSillAnalysis.restSillVerification(rest);
                    if (doorSill != null && doorSill.length() > 0) {
                        check++;
                        restIrr.add(doorSill);
                    }

                    if (rest.getHasTactSign() == 0) {
                        check++;
                        restIrr.add("ausência de sinalização tátil adjacente à porta;");
                    } else if (rest.getHasVertSign() == 0 && rest.getVertSignObs() != null) {
                        check++;
                        restIrr.add("ausência de sinalização tátil adjacente à porta e as seguintes observações devem ser feitas: " + rest.getVertSignObs() + ";");
                    } else if (rest.getHasVertSign() == 1 && rest.getVertSignObs() != null) {
                        check++;
                        restIrr.add("presença de sinalização tátil adjacente à porta, mas as seguintes observações devem ser feitas: " + rest.getVertSignObs() + ";");
                    }

                    if (rest.getHasHorHandle() == 0) {
                        check++;
                        restIrr.add("ausência de puxador horizontal na porta;");
                    } else if (rest.getHasHorHandle() == 0 && rest.getHandleObs() != null) {
                        check++;
                        restIrr.add("ausência de puxador horizontal na porta e as seguintes observações devem ser feitas: " + rest.getHandleObs() + ";");
                    } else if (rest.getHasHorHandle() == 1) {
                        if (rest.getHandleHeight() < minDoorHandle) {
                            check++;
                            restIrr.add("altura do puxador horizontal inferior à " + minDoorHandle + " m;");
                        } else if (rest.getHandleHeight() > maxDoorHandle) {
                            check++;
                            restIrr.add("altura do puxador horizontal superior à " + maxDoorHandle + " m;");
                        }

                        if (rest.getHandleLength() < minHorHandleLength) {
                            check++;
                            restIrr.add("o comprimento do puxador horizontal é inferior à " + minHorHandleLength + " m;");
                        }

                        if (rest.getHandleDiam() < minHandleGrip) {
                            check++;
                            restIrr.add("o diâmetro do puxador horizontal é inferior à " + minHandleGrip + " mm;");
                        } else if (rest.getHandleDiam() > maxHandleGrip) {
                            check++;
                            restIrr.add("o diâmetro do puxador horizontal é superior à " + maxHandleGrip + " mm;");
                        }

                        if (rest.getHandleObs() != null) {
                            check++;
                            restIrr.add("observações sobre o puxador horizontal: " + rest.getHandleObs() + ";");
                        }
                    }

//                        if (rest.getUpViewMeasureA() < 0.4) {
//                        }
                    if (rest.getUpViewMeasureB() < minDistToiletWall) {
                        check++;
                        restIrr.add("distância entre o sanitário e a parede lateral é inferior à " + minDistToiletWall + " m;");
                    }
                    if (rest.getUpViewMeasureC() > maxManUnderToilet) {
                        check++;
                        restIrr.add("a área de manobra utiliza mais do que " + maxManUnderToilet + " m sob a bacia sanitária;");
                    }
                    if (rest.getUpViewMeasureD() != maneuverArea) {
                        check++;
                        restIrr.add("a área de manobra possui diâmetro distinto de " + maneuverArea + " m;");
                    }
                    if (rest.getUpViewMeasureE() > maxManUnderSink) {
                        check++;
                        restIrr.add("a área de manobra utiliza mais do que " + maxManUnderSink + " m sob o lavatório;");
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
                            restIrr.add("presença de cantos vivos;");
                        }

                        if (builder.toString().length() > 0) {
                            check++;
                            restIrr.add(builder.toString());
                        }
                    }

                    if (restType == 3 && (rest.getToHeightNoSeat() < minToiletHeightChild || rest.getToHeightNoSeat() > maxToiletHeightChild)) {
                        check++;
                        restIrr.add("a altura da bacia sanitária está fora dos padrões estipulados;");
                    } else if (restType != 3 && rest.getToHeightNoSeat() < minToiletHeightAdult) {
                        check++;
                        restIrr.add("a altura da bacia sanitária é inferior à " + minToiletHeightAdult + " m;");
                    } else if (restType != 3 && rest.getToHeightNoSeat() > maxToiletHeightAdult) {
                        check++;
                        restIrr.add("a altura da bacia sanitária é superior à " + maxToiletHeightAdult + " m;");
                    }

                    if (rest.getToHasSeat() == 0) {
                        check++;
                        restIrr.add("a bacia sanitária não possui assento;");
                    } else {
                        if (restType == 3 && rest.getToHeightSeat() > maxToiletHeightChildSeat) {
                            check++;
                            restIrr.add("a altura da bacia sanitária com assento é superior à " + maxToiletHeightChildSeat + " m;");
                        } else if (restType != 3 && rest.getToHeightSeat() > maxToiletHeightAdultSeat) {
                            check++;
                            restIrr.add("a altura da bacia sanitária com assento é superior à " + maxToiletHeightAdultSeat + " m;");
                        }
                    }

                    if (rest.getToHasFrontBar() == 0) {
                        check++;
                        restIrr.add("ausência da barra de apoio posterior");
                    } else {
                        if (restType == 3) {
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
                                restIrr.add("a distância entre o eixo da bacia sanitária em direção à parede lateral é diferente de " + frBarToiletAxisChild + " m;");
                            }
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
                                restIrr.add("a distância entre o eixo da bacia sanitária em direção à parede lateral é diferente de " + frBarToiletAxisChild + " m;");
                            }
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
                        if (rest.getHasHorBar() == 0) {
                            check++;
                            restIrr.add("o sanitário não possui barra reta lateral fixa;");
                        } else {
                            if (rest.getHorBarD() < fixSideBarMinLengthToilet) {
                                check++;
                                restIrr.add("o comprimento mínimo da barra lateral fixa à frente da bacia sanitária é inferior à " + fixSideBarMinLengthToilet + " m;");
                            }

                            if (restType == 3 && rest.getHorBarE() != frBarHeightChild) {
                                check++;
                                restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightChild + " m;");
                            } else if (restType != 3 && rest.getHorBarE() != frBarHeightAdult) {
                                check++;
                                restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightAdult + " m;");
                            }

                            if (restType == 3 && rest.getHorBarDistG() != sideBarToiletAxisChild) {
                                check++;
                                restIrr.add("a distância do eixo da bacia sanitária até a barra lateral fixa é diferente de " + sideBarToiletAxisChild + " m;");
                            } else if (restType != 3 && rest.getHorBarDistG() != sideBarToiletAxisAdult) {
                                check++;
                                restIrr.add("a distância do eixo da bacia sanitária até a barra lateral fixa é diferente de " + sideBarToiletAxisAdult + " m;");
                            }

                            if (rest.getHorBarSect() < minHandrailGrip) {
                                check++;
                                restIrr.add("a seção transversal da barra lateral fixa é inferior à " + minHandrailGrip + " mm;");
                            } else if (rest.getHorBarSect() > maxHandrailGrip) {
                                check++;
                                restIrr.add("a seção transversal da barra lateral fixa é superior à " + maxHandrailGrip + " mm;");
                            }
                        }

                        if (rest.getHasArtBar() == 1) {
                            if (restType == 3 && rest.getArtBarH() != frBarHeightChild) {
                                check++;
                                restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightChild + " m;");
                            } else if (restType != 3 && rest.getArtBarH() != frBarHeightAdult) {
                                check++;
                                restIrr.add("a altura da barra lateral fixa é diferente de " + frBarHeightAdult + " m;");
                            }

                            if (rest.getArtBarI() < artSideBarMinLengthToilet) {
                                check++;
                                restIrr.add("o comprimento mínimo da barra lateral articulada à frente da bacia sanitária é inferior à " + artSideBarMinLengthToilet + " m;");
                            }

                            if (restType == 3 && rest.getArtBarJ() != sideBarToiletAxisChild) {
                                check++;
                                restIrr.add("a distância do eixo da bacia sanitária até a barra lateral articulada é diferente de " + sideBarToiletAxisChild + " m;");
                            } else if (restType != 3 && rest.getArtBarJ() != sideBarToiletAxisAdult) {
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
                    } else {
                        if (rest.getHasHorBar() == 0) {
                            check++;
                            restIrr.add("o sanitário não possui barra reta lateral horizontal;");
                        } else {
                            if (restType == 3 && rest.getHorBarD() != frBarHeightChild) {
                                check++;
                                restIrr.add("a altura da barra lateral horizontal é diferente de " + frBarHeightChild + " m;");
                            } else if (restType != 3 && rest.getHorBarD() != frBarHeightAdult) {
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

                            if (restType == 3 && rest.getHorBarDistG() != sideBarToiletAxisChild) {
                                check++;
                                restIrr.add("a distância do eixo da bacia sanitária até a barra lateral horizontal é diferente de " + sideBarToiletAxisChild + " m;");
                            } else if (restType != 3 && rest.getHorBarDistG() != sideBarToiletAxisAdult) {
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
                            restIrr.add("o sanitário não possui a barra lateral de apoio vertical;");
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

                    if (rest.getToActObs() != null) {
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

                    if (rest.getPapHoldObs() != null) {
                        check++;
                        restIrr.add("as seguintes observações devem ser feitas sobre a papeleira: " + rest.getPapHoldObs() + ";");
                    }

                    if (rest.getHasDouche() == 0) {
                        check++;
                        restIrr.add("o sanitário não possui ducha higiênica;");
                    } else {
                        if (rest.getDoucheHeight() < minActionHeight) {
                            check++;
                            restIrr.add("o acionamento da ducha higiência está abaixo de " + minActionHeight + " m;");
                        } else if (rest.getDoucheHeight() > maxActionHeight) {
                            check++;
                            restIrr.add("o acionamento da ducha higiência está acima de " + maxActionHeight + " m;");
                        }
                    }
                    if (rest.getDoucheObs() != null) {
                        check++;
                        restIrr.add("as seguintes observações podem ser feitas sobre a ducha higiênica: " + rest.getDoucheObs() + ";");
                    }

                    if (rest.getToiletObs() != null) {
                        check++;
                        restIrr.add("as seguintes observações podem ser feitas sobre o sanitário: " + rest.getToiletObs());
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
                    if (rest.getHangerObs() != null) {
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

                    if (rest.getObjHoldObs() != null) {
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

                    if (rest.getSoapHoldObs() != null) {
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

                    if (rest.getTowelHoldHeight() != null) {
                        check++;
                        restIrr.add("as seguintes observações podem ser feitas sobre o toalheiro: " + rest.getSoapHoldObs() + ";");
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
                    if (rest.getWallMirrorObs() != null) {
                        check++;
                        restIrr.add("as seguintes observações podem ser feitas sobre o espelho instalado: " + rest.getWallMirrorObs() + ";");
                    }

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
                        restIrr.add("o espaço de aproximação frontal na altura dos joelhos é inferior à " + sinkFrontApproxKnee + " m;");
                    }

                    if (rest.getApproxMeasureD() < sinkFrontApproxFeet) {
                        check++;
                        restIrr.add("o espaço de aproximação frontal na altura dos pés é inferior à " + sinkFrontApproxFeet + " m;");
                    }

                    if (rest.getHasSinkBar() == 0) {
                        check++;
                        restIrr.add("o lavatório não possui barras de apoio;");
                    }
                    if (rest.getHasSinkBar() == 2) {
                        check++;
                        restIrr.add("o lavatório possui barras de apoio que estão fora do padrão estipulado em norma;");
                    } else {
                        if (sinkType == 0) {
                            if (rest.getHasLeftBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                            } else {
                                if (rest.getLeftHorMeasureA() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getLeftHorMeasureB() < sinkHorBarMinLatDist) {
                                    check++;
                                    restIrr.add("o espaçamento entre o lavatório e a barra esquerda é inferior à " + sinkHorBarMinLatDist + " m;");
                                }
                                if (rest.getLeftHorMeasureC() < sinkHorBarMinUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra esquerda é inferior à " + sinkHorBarMinUpperHeight + " m;");
                                } else if (rest.getLeftHorMeasureC() > sinkHorBarMaxUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra esquerda é superior à " + sinkHorBarMaxUpperHeight + " m;");
                                }
                                if (rest.getLeftHorMeasureD() != sinkHorBarLowerHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face inferior da barra esquerda é diferente de " + sinkHorBarLowerHeight + " m;");
                                }

                                if (rest.getLeftBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getVertBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }

                            if (rest.getHasRightBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                            } else {
                                if (rest.getRightHorMeasureA() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra direita é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getRightHorMeasureB() < sinkHorBarMinLatDist) {
                                    check++;
                                    restIrr.add("o espaçamento entre o lavatório e a barra direita é inferior à " + sinkHorBarMinLatDist + " m;");
                                }
                                if (rest.getRightHorMeasureC() < sinkHorBarMinUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra direita é inferior à " + sinkHorBarMinUpperHeight + " m;");
                                } else if (rest.getRightHorMeasureC() > sinkHorBarMaxUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra direita é superior à " + sinkHorBarMaxUpperHeight + " m;");
                                }
                                if (rest.getRightHorMeasureD() != sinkHorBarLowerHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face inferior da barra direita é diferente de " + sinkHorBarLowerHeight + " m;");
                                }

                                if (rest.getRightBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getRightBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }
                        } else if (sinkType == 1) {
                            if (rest.getHasLeftBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                            } else {
                                if (rest.getLeftVertMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical esquerda é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }
                                if (rest.getLeftVertMeasureB() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getLeftVertMeasureC() < sinkVertBarMinLength) {
                                    check++;
                                    restIrr.add("o comprimento da barra vertical esquerda é inferior à " + sinkVertBarMinLength + " m;");
                                }
                                if (rest.getLeftVertMeasureD() != sinkVertBarHeight) {
                                    check++;
                                    restIrr.add("a altura de instalação da barra vertical esquerda é diferente de " + sinkVertBarHeight + " m;");
                                }

                                if (rest.getLeftBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getVertBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }

                            if (rest.getHasRightBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                            } else {
                                if (rest.getRightVertMeasureA() < sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical direita é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }

                                if (rest.getRightBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getRightBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }
                        } else if (sinkType == 2) {
                            if (rest.getHasLeftBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                            } else {
                                if (rest.getLeftVertMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical esquerda é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }
                                if (rest.getLeftVertMeasureB() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getLeftVertMeasureC() < sinkVertBarMinLength) {
                                    check++;
                                    restIrr.add("o comprimento da barra vertical esquerda é inferior à " + sinkVertBarMinLength + " m;");
                                }
                                if (rest.getLeftVertMeasureD() != sinkVertBarHeight) {
                                    check++;
                                    restIrr.add("a altura de instalação da barra vertical esquerda é diferente de " + sinkVertBarHeight + " m;");
                                }

                                if (rest.getLeftBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getVertBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }

                            if (rest.getHasRightBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                            } else {
                                if (rest.getRightHorMeasureA() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra direita é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getRightHorMeasureB() < sinkHorBarMinLatDist) {
                                    check++;
                                    restIrr.add("o espaçamento entre o lavatório e a barra direita é inferior à " + sinkHorBarMinLatDist + " m;");
                                }
                                if (rest.getRightHorMeasureC() < sinkHorBarMinUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra direita é inferior à " + sinkHorBarMinUpperHeight + " m;");
                                } else if (rest.getRightHorMeasureC() > sinkHorBarMaxUpperHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face superior da barra direita é superior à " + sinkHorBarMaxUpperHeight + " m;");
                                }
                                if (rest.getRightHorMeasureD() != sinkHorBarLowerHeight) {
                                    check++;
                                    restIrr.add("a distância entre o piso e a face inferior da barra direita é diferente de " + sinkHorBarLowerHeight + " m;");
                                }

                                if (rest.getRightBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getRightBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }
                        } else if (sinkType == 3) {
                            if (rest.getHasLeftBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                            } else {
                                if (rest.getLeftVertMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo da barra vertical esquerda até a parede é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }
                                if (rest.getLeftVertMeasureB() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getLeftVertMeasureC() < sinkVertBarMinLength) {
                                    check++;
                                    restIrr.add("o comprimento da barra vertical esquerda é inferior à " + sinkVertBarMinLength + " m;");
                                }
                                if (rest.getLeftVertMeasureD() != sinkVertBarHeight) {
                                    check++;
                                    restIrr.add("a altura de instalação da barra vertical esquerda é diferente de " + sinkVertBarHeight + " m;");
                                }

                                if (rest.getLeftBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getVertBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }

                            if (rest.getHasRightBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                            } else {
                                if (rest.getRightVertMeasureA() < sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical direita é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }

                                if (rest.getRightBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getRightBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }
                        } else if (sinkType == 4) {
                            if (rest.getHasLeftBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à esquerda;");
                            } else {
                                if (rest.getLeftVertMeasureA() > sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo da barra vertical esquerda até a parede é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }
                                if (rest.getLeftVertMeasureB() > sinkBarMaxFrontDist) {
                                    check++;
                                    restIrr.add("a distância entre a borda frontal do lavatório até o eixo da barra esquerda é superior à " + sinkBarMaxFrontDist + " m;");
                                }
                                if (rest.getLeftVertMeasureC() < sinkVertBarMinLength) {
                                    check++;
                                    restIrr.add("o comprimento da barra vertical esquerda é inferior à " + sinkVertBarMinLength + " m;");
                                }
                                if (rest.getLeftVertMeasureD() != sinkVertBarHeight) {
                                    check++;
                                    restIrr.add("a altura de instalação da barra vertical esquerda é diferente de " + sinkVertBarHeight + " m;");
                                }

                                if (rest.getLeftBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getLeftBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral esquerda do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getVertBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral esquerda do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }

                            if (rest.getHasRightBar() == 0) {
                                check++;
                                restIrr.add("o lavatório não possui barra de apoio instalada à direita;");
                            } else {
                                if (rest.getRightVertMeasureA() < sinkVertBarMaxDistSinkAxis) {
                                    check++;
                                    restIrr.add("a distância do eixo do lavatório/cuba até o eixo da barra vertical direita é superior à " + sinkVertBarMaxDistSinkAxis + " m;");
                                }

                                if (rest.getRightBarDiam() < minHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é inferior à " + minHandrailGrip + " mm;");
                                } else if (rest.getRightBarDiam() > maxHandrailGrip) {
                                    check++;
                                    restIrr.add("a seção transversal da barra lateral direita do lavatório é superior à " + maxHandrailGrip + " mm;");
                                }

                                if (rest.getRightBarDist() < minDistHandrail) {
                                    check++;
                                    restIrr.add("a distância até a parede da barra lateral direita do lavatório é inferior à " + minDistHandrail + " mm;");
                                }
                            }
                        }
                    }

                    if (rest.getSinkHasMirror() == 1) {
                        if (rest.getSiMirrorLow() > sinkMirrorMaxLowerHeight) {
                            check++;
                            restIrr.add("a altura da base do espelho do lavatório é superior à " + sinkMirrorMaxLowerHeight + " m;");
                        }

                        if (rest.getSiMirrorHigh() < minUpperHeightWallMirror) {
                            check++;
                            restIrr.add("a altura do espelho do lavatório é inferior à " + minUpperHeightWallMirror + " m;");
                        }
                    }

                    if (rest.getHasUrinal() == 1) {
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

                            if (rest.getUrinalType() == 0) {
                                if (rest.getUrMeasureL() > urinalValveHeight) {
                                    check++;
                                    restIrr.add("a altura do piso até a válvula de acionamento do mictório acessível é superior a " + urinalValveHeight + " m;");
                                }
                                if (rest.getUrMeasureM() < urinalMinLowerOpenHeight) {
                                    check++;
                                    restIrr.add("a altura do piso até a abertura do mictório acessível é inferior a " + urinalMinLowerOpenHeight + " m;");
                                } else if (rest.getUrMeasureM() > urinalMaxLowerOpenHeight) {
                                    check++;
                                    restIrr.add("a altura do piso até a abertura do mictório acessível é superior a " + urinalMaxLowerOpenHeight + " m;");
                                }
                            }
                        }
                    }

                    if (rest.getUrObs() != null) {
                        check++;
                        restIrr.add("as seguintes observações podem ser feitas sobre os mictórios: " + rest.getUrObs() + ";");
                    }
                }
            }

            if (check > 0) {
                String type = restroomTyping(rest.getRestType());
                AmbientAnalysis.blockRestList.add("Sanitário " + type + ", localizado em " + rest.getRestLocation() + ", com as seguintes irregularidades: ");
                AmbientAnalysis.blockRestIrregular.put(blockRest, restIrr);
                blockRest++;
            }
        }
    }

    public static String restroomTyping(int i) {
        if (i == 0)
            return "Masculino";
        else if (i == 1)
            return "Feminino";
        else if (i == 2)
            return "Familiar";
        else
            return "Infantil";
    }

    public static void soculoIrregular(StringBuilder builder) {
        if (!irrSoculo) {
            irrSoculo = true;
            builder.append("a bacia sanitária possui sóculo com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
