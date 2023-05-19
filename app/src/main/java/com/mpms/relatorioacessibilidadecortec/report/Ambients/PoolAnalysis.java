package com.mpms.relatorioacessibilidadecortec.report.Ambients;

import com.mpms.relatorioacessibilidadecortec.data.entities.PoolBenchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEquipEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolRampEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolStairsEntry;
import com.mpms.relatorioacessibilidadecortec.report.AmbientAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.PoolBenchAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.PoolEquipAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.PoolRampAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Components.PoolStairsAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class PoolAnalysis implements StandardMeasurements {

    static boolean irrPool;

    public static void poolVerification(List<PoolEntry> pList, List<PoolRampEntry> rampList, List<PoolStairsEntry> stairsList, List<PoolBenchEntry> benchList,
                                        List<PoolEquipEntry> equipList) {
        int i = 0;
        for (PoolEntry pool : pList) {
            int check = 0;
            AmbientAnalysis.err = false;
            List<String> pIrregular = new ArrayList<>();

            if (pool.getAllowPoolAccess() == 0 && (pool.getPoolAccessObs() != null && pool.getPoolAccessObs().length() > 0)) {
                check++;
                pIrregular.add("As áreas circundantes à piscina não permitem acesso ao tanque e outras área de uso da piscina e devem ser apontadas as seguintes " +
                        "observações sobre o local: " + pool.getPoolAccessObs().trim() + ";");
            } else if (pool.getAllowPoolAccess() == 0) {
                check++;
                pIrregular.add("As áreas circundantes à piscina não permitem acesso ao tanque e outras área de uso da piscina;");
            } else if (pool.getAllowPoolAccess() == 1 && (pool.getPoolAccessObs() != null && pool.getPoolAccessObs().length() > 0)) {
                check++;
                pIrregular.add("As áreas circundantes à piscina permitem acesso ao tanque e outras área de uso da piscina, porém as seguintes " +
                        "observações devem ser apontadas: " + pool.getPoolAccessObs().trim() + ";");
            }

            if (pool.getAllowWaterFlow() == 0 && (pool.getWaterFlowObs() != null && pool.getWaterFlowObs().length() > 0)) {
                check++;
                pIrregular.add("As áreas circundantes à piscina não permitem o escoamento de água e devem ser apontadas as seguintes " +
                        "observações sobre o local: " + pool.getWaterFlowObs().trim() + ";");
            } else if (pool.getAllowWaterFlow() == 0) {
                check++;
                pIrregular.add("As áreas circundantes à piscina não permitem o escoamento de água;");
            } else if (pool.getAllowWaterFlow() == 1 && (pool.getWaterFlowObs() != null && pool.getWaterFlowObs().length() > 0)) {
                check++;
                pIrregular.add("As áreas circundantes à piscina permitem o escoamento de água, porém devem ser apontadas as seguintes " +
                        "observações sobre o local: " + pool.getWaterFlowObs().trim() + ";");
            }

            if (pool.getFloorAccessible() == 0) {
                check++;
                pIrregular.add("O piso ao redor do tanque da piscina não pode ser considerado acessível devido aos seguintes fatores: "
                        + pool.getFloorAccessObs().trim() + ";");
            } else if (pool.getFloorAccessible() == 1 && (pool.getFloorAccessObs() != null && pool.getFloorAccessObs().length() > 0)) {
                check++;
                pIrregular.add("O piso ao redor do tanque da piscina pode ser considerado acessível, porém as seguintes observações devem ser apontadas: " +
                        pool.getFloorAccessObs().trim() + ";");
            }

            if (pool.getPoolHasShower() == 0 && (pool.getShowerObs() != null && pool.getShowerObs().length() > 0)) {
                check++;
                pIrregular.add("A piscina não possui duchas na proximidade do tanque e deve-se apontar as seguintes observações: " + pool.getShowerObs().trim() + ";");
            } else if (pool.getPoolHasShower() == 1 && pool.getHasAccessShower() == 0 && (pool.getShowerObs() != null && pool.getShowerObs().length() > 0)) {
                check++;
                pIrregular.add("A piscina não possui duchas acessíveis próximas ao tanque da piscina e devem ser apontadas as seguintes observações: " +
                        pool.getShowerObs().trim() + ";");
            } else if (pool.getPoolHasShower() == 1 && pool.getHasAccessShower() == 1 && (pool.getShowerObs() != null && pool.getShowerObs().length() > 0)) {
                check++;
                pIrregular.add("A piscina possui duchas acessíveis próximas ao tanque da piscina, porém devem ser apontadas as seguintes observações: " +
                        pool.getShowerObs().trim() + ";");
            }

            if (pool.getPoolHasFence() == 0 && (pool.getGateObs() != null && pool.getGateObs().length() > 0)) {
                check++;
                pIrregular.add("A piscina não possui isolamento físico para proteção e restrição de acesso de pessoas e devem ser apontadas as seguintes observações: " +
                        pool.getGateObs().trim() + ";");
            } else if (pool.getPoolHasFence() == 0) {
                check++;
                pIrregular.add("A piscina não possui isolamento físico para proteção e restrição de acesso de pessoas;");
            } else {
                if (pool.getFenceHeight() < minPoolFenceHeight) {
                    check++;
                    pIrregular.add("O isolamento físico de proteção da piscina possui altura inferior a " + minPoolFenceHeight + " m;");
                }

                if (pool.getFenceGapWidth() < maxPoolFenceGaps) {
                    check++;
                    pIrregular.add("O isolamento físico de proteção da piscina possui aberturas com dimensão superior a " + maxPoolFenceGaps + " cm;");
                }

                if (pool.getFenceHasAutoGate() == 0) {
                    check++;
                    pIrregular.add("O isolamento físico de proteção não possui portão com dipositivo de fechamento e travamento automático");
                } else if (pool.getAutoGateHandleHeight() < minPrecisionCom) {
                    check++;
                    pIrregular.add("O dispositivo de liberação do sistema de travamento se encontra instalado em uma altura inferior a " + minPrecisionCom + " m;");
                } else if (pool.getAutoGateHandleHeight() > maxPrecisionCom) {
                    check++;
                    pIrregular.add("O dispositivo de liberação do sistema de travamento se encontra instalado em uma altura superior a " + maxPrecisionCom + " m;");
                }

                if (pool.getGateHasAntiRust() == 0) {
                    check++;
                    pIrregular.add("O isolamento físico de proteção não possui tratamento anticorrosivo;");
                }
                if (pool.getGateAllowVision() == 0) {
                    check++;
                    pIrregular.add("O isolamento físico de proteção não permite visibilidade da área da piscina;");
                }
                if (pool.getGateObs() != null && pool.getGateObs().length() > 0) {
                    check++;
                    pIrregular.add("Os seguintes apontamentos devem ser feitos sobre o isolamento físico da piscina: " + pool.getGateObs().trim() + ";");
                }
            }

            if (pool.getPoolPhoto() != null) {
                check++;
                pIrregular.add("Registros Fotográficos I: " + pool.getPoolPhoto().trim() + ";");
            }

            if (pool.getIsSportsPool() == 1 && (pool.getPavementObs() != null && pool.getPavementObs().length() > 0)) {
                check++;
                pIrregular.add("A piscina, do tipo desportiva, possui as seguintes observações: " + pool.getPavementObs().trim() + ";");
            } else if (pool.getIsSportsPool() == 0) {
                if (pool.getHasPavementedSide() == 0 && (pool.getPavementObs() != null && pool.getPavementObs().length() > 0)) {
                    check++;
                    pIrregular.add("A piscina não possui em nenhum dos lados de seu tanque uma faixa pavimentada contínua, e as seguintes observações podem " +
                            "ser feitas: " + pool.getPavementObs().trim() + ";");
                } else if (pool.getHasPavementedSide() == 0) {
                    check++;
                    pIrregular.add("A piscina não possui em nenhum dos lados de seu tanque uma faixa pavimentada contínua;");
                } else {
                    if (pool.getPavementWidth() < minPublicSideLaneWidth) {
                        check++;
                        pIrregular.add("A faixa pavimentada contínua ao redor da piscina possui largura livre inferior a " + minPublicSideLaneWidth + " m;");
                    }
                    if (pool.getIsPavementAccess() == 0) {
                        check++;
                        pIrregular.add("A faixa pavimentada contínua ao redor da piscina não pode ser considerada acessível pelos seguintes fatores: " +
                                pool.getPavementObs().trim() + ";");
                    }
                    if (pool.getPavementObs() != null && pool.getPavementObs().length() > 0) {
                        check++;
                        pIrregular.add("As seguintes observações podem ser apontadas sobre o pavimento ao redor do tanque da piscina: " + pool.getPavementObs().trim() + ";");
                    }
                }
            }

            int poolType = pool.getPoolType();

            if (pool.getUsedInCompetitions() == 0) {
                if (pool.getPoolDepth() > minDepthPool) {
                    if (poolType == 0 && pool.getPoolHasRamp() == 0 && pool.getPoolHasEquip() == 0) {
                        check++;
                        pIrregular.add("Piscina com tanque com perímetro de até 90 m não possui rampa nem equipamento de acesso");
                    } else if ((poolType == 1 && ((pool.getPoolHasRamp() == 0 && pool.getPoolHasEquip() == 0) && (pool.getPoolHasRamp() == 0 && pool.getPoolHasBench() == 0) &&
                            (pool.getPoolHasRamp() == 0 && pool.getPoolHasStairs() == 0) && (pool.getPoolHasEquip() == 0 && pool.getPoolHasBench() == 0) &&
                            (pool.getPoolHasEquip() == 0 && pool.getPoolHasStairs() == 0)))) {
                        check++;
                        pIrregular.add("Piscina com tanque com perímetro superior a 90 m não possui conjunto de meios de acessibilidade para o tanque;");
                    } else if (poolType == 2 && pool.getPoolHasRamp() == 0 && pool.getPoolHasEquip() == 0) {
                        check++;
                        pIrregular.add("Piscina com tanque com tanque de onda, correnteza artificial ou acesso limitado à uma área não possui rampa nem equipamento de acesso");
                    } else if (poolType == 3 && pool.getPoolHasBench() == 0 && pool.getPoolHasEquip() == 0) {
                        check++;
                        pIrregular.add("Piscina com tanque tipo spa, ofurô ou similar não possui banco de transferência nem equipamento de acesso");
                    }
                }

                if (rampList.size() > 0) {
                    List<String> rampError = PoolRampAnalysis.poolRampIrregular(pool.getPoolID(), rampList);
                    if (rampError.size() > 0) {
                        check++;
                        pIrregular.addAll(rampError);
                    }
                }

                if (stairsList.size() > 0) {
                    List<String> stairsError = PoolStairsAnalysis.poolStairsIrregular(pool.getPoolID(), stairsList);
                    if (stairsError.size() > 0) {
                        check++;
                        pIrregular.addAll(stairsError);
                    }
                }

                if (equipList.size() > 0) {
                    List<String> equipError = PoolEquipAnalysis.poolEquipIrregular(pool.getPoolID(), equipList);
                    if (equipError.size() > 0) {
                        check++;
                        pIrregular.addAll(equipError);
                    }
                }

                if (benchList.size() > 0) {
                    List<String> benchError = PoolBenchAnalysis.poolBenchIrregular(pool.getPoolID(), benchList);
                    if (benchError.size() > 0) {
                        check++;
                        pIrregular.addAll(benchError);
                    }
                }


            }

            if (pool.getPoolObs() != null) {
                check++;
                pIrregular.add("As seguintes observações podem ser feitas sobre o tanque da piscina: " + pool.getPoolObs().trim() + ";");
            }

            if (pool.getPoolPhoto2() != null) {
                check++;
                pIrregular.add("Registros Fotográficos II: " + pool.getPoolPhoto2().trim() + ";");
            }

            if (check > 0)
                AmbientAnalysis.checkHelpAreaHeader();

            if (AmbientAnalysis.err) {
                AmbientAnalysis.poolList.add("Piscina, localizada em " + pool.getPoolLocation().trim() + ", com as seguintes irregularidades:");
                AmbientAnalysis.poolIrregular.put(i, pIrregular);
            }
        }
    }
}
