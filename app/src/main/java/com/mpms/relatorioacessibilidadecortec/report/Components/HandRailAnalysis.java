package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.List;

public class HandRailAnalysis implements StandardMeasurements {

//    TODO - adicionar um método para adição de vírgulas e espaços em vez de fazer isso manualmente nos textos, assim como no Ramp/StairAnalysis
    public static String handRailVerification(int flightID, List<RampStairsRailingEntry> rStRail, List<RampStairsHandrailEntry> rStHandrail) {

        StringBuilder builder = new StringBuilder();

        if (rStRail.size() > 0) {
            for (RampStairsRailingEntry railing : rStRail) {
                boolean leftB = false;
                if (railing.getFlightID() == flightID) {
                    if (railing.getRailingSide() == 0)
                        leftB = true;

                    if (railing.getHasRailing() == 0) {
                        builder.append(" o lance não possui guarda-corpo");
                        if (leftB)
                            builder.append(" a sua esquerda");
                        else
                            builder.append(" a sua direita");
                        if (railing.getRailingObs() != null && railing.getRailingObs().length() > 0)
                            builder.append(" e as seguintes observações devem ser apontadas: ").append(railing.getRailingObs());
                    } else if (railing.getHasRailing() == 1) {
                        if (railing.getRailingHeight() < minRailHeight) {
                            builder.append(" o lance possui guarda-corpo");
                            if (leftB)
                                builder.append(" a sua esquerda");
                            else
                                builder.append(" a sua direita");
                            builder.append(" com altura inferior à " + minRailHeight + " m,");
                            if (railing.getRailingObs() != null && railing.getRailingObs().length() > 0)
                                builder.append(" e as seguintes observações devem ser apontadas: ").append(railing.getRailingObs());
                        }
//                                        todo - esperar confirmação de medições do Jean
                    } else if (railing.getHasRailing() == 2) {
                        if (railing.getRailingHeight() < minRailHeight) {
                            builder.append(" o lance possui guarda-corpo do tipo meia-parede");
                            if (leftB)
                                builder.append(" a sua esquerda");
                            else
                                builder.append(" a sua direita");
                            builder.append(" com altura inferior à " + minRailHeight + " m,");
                            if (railing.getRailingObs() != null && railing.getRailingObs().length() > 0)
                                builder.append(" e as seguintes observações devem ser apontadas: ").append(railing.getRailingObs());
                        }
                        //                                        todo - esperar confirmação de medições do Jean
                    } else {
                        if (railing.getRailingObs() != null && railing.getRailingObs().length() > 0)
                            builder.append(" o lance possui uma parede ao lado ");
                        if (leftB)
                            builder.append(" esquerdo");
                        else
                            builder.append(" direito");
                        builder.append(" da escada e as seguintes observações devem ser apontadas: ").append(railing.getRailingObs()).append(",");
                    }
                    if (railing.getHasRailing() == 0 || railing.getHasRailing() == 1) {
                        if (railing.getHasBeacon() == 0) {
                            builder.append(" o lance não possui guia de balizamento em seu lado ");
                            if (leftB)
                                builder.append("esquerdo,");
                            else
                                builder.append("direito,");

                            if (railing.getBeaconObs() != null && railing.getBeaconObs().length() > 0)
                                builder.append(" e as seguintes observações devem ser apontadas: ").append(railing.getBeaconObs()).append(",");
                        } else {
                            if (railing.getBeaconHeight() < minBeaconHeight) {
                                builder.append(" o lance possui guia de balizamento em seu lado ");
                                if (leftB)
                                    builder.append("esquerdo");
                                else
                                    builder.append("direito");
                                builder.append(" com altura inferior ao mínimo de " + minBeaconHeight + " m,");
                                if (railing.getBeaconObs() != null && railing.getBeaconObs().length() > 0)
                                    builder.append(" e as seguintes observações devem ser apontadas: ").append(railing.getBeaconObs()).append(",");
                            } else {
                                if (railing.getBeaconObs() != null && railing.getBeaconObs().length() > 0) {
                                    builder.append(" o lance possui guia de balizamento em seu lado ");
                                    if (leftB)
                                        builder.append("esquerdo");
                                    else
                                        builder.append("direito");
                                }
                                builder.append("com altura de acordo com a norma, porém as seguintes observações devem ser apontadas: ")
                                        .append(railing.getBeaconObs()).append(",");
                            }
                        }
                    }

                    if (railing.getBeaconPhoto() != null) {
                        builder.append("Registros fotográficos: ").append(railing.getBeaconPhoto());
                    }
                }
            }
        } else {
            builder.append(" não há cadastro de guarda-corpos e guias de balizamento para esta escada,");
        }

        if (rStHandrail.size() > 0) {
            for (RampStairsHandrailEntry handrail : rStHandrail) {
                if (handrail.getFlightID() == flightID) {

                    if (handrail.getHasHandrail() == 0) {
                        builder.append(" o lance não possui ");
                        switch (handrail.getHandrailPlacement()) {
                            case 3:
                                builder.append("o corrimão inferior direito,");
                                break;
                            case 2:
                                builder.append("o corrimão inferior esquerdo,");
                                break;
                            case 1:
                                builder.append("o corrimão superior direito,");
                                break;
                            default:
                                builder.append("o corrimão superior esquerdo,");
                                break;
                        }
                    } else {
                        if (handrail.getHandrailPlacement() == 0 || handrail.getHandrailPlacement() == 1) {
                            if (handrail.getHandrailHeight() != highHandrail) {
                                if (handrail.getHandrailPlacement() == 0)
                                    builder.append( "o corrimão superior esquerdo ");
                                else
                                    builder.append(" o corrimão superior direito ");
                                builder.append("possui altura diferente de " + highHandrail + " m,");
                            }
                        } else {
                            if (handrail.getHandrailHeight() != lowHandrail) {
                                if (handrail.getHandrailPlacement() == 2)
                                    builder.append(" o corrimão inferior esquerdo ");
                                else
                                    builder.append(" o corrimão inferior direito ");
                                builder.append("possui altura diferente de " + lowHandrail + " m,");
                            }
                        }

                        if (handrail.getHandrailGrip() < minHandrailGrip) {
                            switch (handrail.getHandrailPlacement()) {
                                case 3:
                                    builder.append(" o corrimão inferior direito");
                                    break;
                                case 2:
                                    builder.append(" o corrimão inferior esquerdo");
                                    break;
                                case 1:
                                    builder.append(" o corrimão superior direito");
                                    break;
                                default:
                                    builder.append(" o corrimão superior esquerdo");
                                    break;
                            }
                            builder.append(" possui diâmetro da empunhadura inferior à " + minHandrailGrip + " mm,");
                        } else if (handrail.getHandrailGrip() > maxHandrailGrip) {
                            switch (handrail.getHandrailPlacement()) {
                                case 3:
                                    builder.append(" o corrimão inferior direito");
                                    break;
                                case 2:
                                    builder.append(" o corrimão inferior esquerdo");
                                    break;
                                case 1:
                                    builder.append(" o corrimão superior direito");
                                    break;
                                default:
                                    builder.append(" o corrimão superior esquerdo");
                                    break;
                            }
                            builder.append(" possui diâmetro da empunhadura superior à " + maxHandrailGrip + " mm,");
                        }

                        if (handrail.getHandrailDist() < minDistHandrail) {
                            switch (handrail.getHandrailPlacement()) {
                                case 3:
                                    builder.append(" o corrimão inferior direito");
                                    break;
                                case 2:
                                    builder.append(" o corrimão inferior esquerdo");
                                    break;
                                case 1:
                                    builder.append(" o corrimão superior direito");
                                    break;
                                default:
                                    builder.append(" o corrimão superior esquerdo");
                                    break;
                            }
                            builder.append(" possui afastamento da parede inferior à" + minDistHandrail + " mm,");
                        }

                        if (handrail.getHasInitExtension() == 0) {
                            switch (handrail.getHandrailPlacement()) {
                                case 3:
                                    builder.append(" o corrimão inferior direito");
                                    break;
                                case 2:
                                    builder.append(" o corrimão inferior esquerdo");
                                    break;
                                case 1:
                                    builder.append(" o corrimão superior direito");
                                    break;
                                default:
                                    builder.append(" o corrimão superior esquerdo");
                                    break;
                            }
                            builder.append(" não possui prolongamento em seu início,");
                        } else {
                            if (handrail.getInitExtLength() < extendHandrail) {
                                switch (handrail.getHandrailPlacement()) {
                                    case 3:
                                        builder.append(" o corrimão inferior direito");
                                        break;
                                    case 2:
                                        builder.append(" o corrimão inferior esquerdo");
                                        break;
                                    case 1:
                                        builder.append(" o corrimão superior direito");
                                        break;
                                    default:
                                        builder.append(" o corrimão superior esquerdo");
                                        break;
                                }
                                builder.append(" possui prolongamento em seu início inferior à " + extendHandrail + " m,");
                            }
                        }

                        if (handrail.getHasFinalExtension() == 0) {
                            switch (handrail.getHandrailPlacement()) {
                                case 3:
                                    builder.append(" o corrimão inferior direito");
                                    break;
                                case 2:
                                    builder.append(" o corrimão inferior esquerdo");
                                    break;
                                case 1:
                                    builder.append(" o corrimão superior direito");
                                    break;
                                default:
                                    builder.append(" o corrimão superior esquerdo");
                                    break;
                            }
                            builder.append(" não possui prolongamento em seu final,");
                        } else {
                            if (handrail.getFinalExtLength() < extendHandrail) {
                                switch (handrail.getHandrailPlacement()) {
                                    case 3:
                                        builder.append(" o corrimão inferior direito");
                                        break;
                                    case 2:
                                        builder.append(" o corrimão inferior esquerdo");
                                        break;
                                    case 1:
                                        builder.append(" o corrimão superior direito");
                                        break;
                                    default:
                                        builder.append(" o corrimão superior esquerdo");
                                        break;
                                }
                                builder.append(" possui prolongamento em seu final inferior à " + extendHandrail + " m,");
                            }
                        }

                        if (handrail.getHandrailPhoto() != null) {
                            builder.append(" Registros fotográficos: ").append(handrail.getHandrailPhoto());
                        }
                    }
                    if (handrail.getHandrailObs() != null && handrail.getHandrailObs().length() > 0)
                        builder.append("e as seguintes observações podem ser feitas: ").append(handrail.getHandrailObs()).append(", ");


                }
            }

        } else {
            builder.append(" não há cadastro de corrimãos para esta escada,");
        }

        String analysis = null;
        if (builder.length() != 0)
            analysis = builder.toString();
        return analysis;


    }
}
