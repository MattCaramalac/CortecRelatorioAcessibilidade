package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.SingleStepEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class SingleStepAnalysis implements StandardMeasurements {

    static boolean irregularStep;

    public static List<String> circStepList (int circID, List<SingleStepEntry> sList) {

        List<String> slopeList = new ArrayList<>();

        if (sList.size() > 0) {
            for (SingleStepEntry step : sList) {
                irregularStep = false;
                String analysis = null;
                if (step.getCircID() == circID)
                    analysis = stepText(step);
                if (analysis != null && analysis.length() > 0) {
                    String analysis2 = analysis.trim();
                    slopeList.add(analysis2.concat(";"));
                }
            }
        }
        return slopeList;
    }

    private static String stepText(SingleStepEntry step) {
        String irregular = null;
        StringBuilder builder = new StringBuilder();

        if (step.getStepQnt() == 1) {
            if (step.getFirstMirror() < minStepHeight) {
                stepIrregular(builder);
                builder.append("a altura do espelho do espelho do degrau isolado é inferior à " + minStepHeight + " m");
            } else if (step.getFirstMirror() > maxStepHeight) {
                stepIrregular(builder);
                builder.append("a altura do espelho do espelho do degrau isolado é superior à " + maxStepHeight + " m");
            }

            if (step.getHasLeftHand() == 0) {
                stepIrregular(builder);
                builder.append("o degrau isolado não possui corrimão instalado");
            } else {
                if (step.getLeftHandUpHeight() != handHeight) {
                    stepIrregular(builder);
                    builder.append("o corrimão se encontra instalado a uma altura diferente de " + handHeight + " m");
                }

                if (step.getLeftHandLength() <  minHandLength) {
                    stepIrregular(builder);
                    builder.append("o corrimão instalado apresenta um comprimento inferior a " + minHandLength + " m");
                }

                if (step.getLeftHandDiam() < minHandleGrip) {
                    stepIrregular(builder);
                    builder.append("o diâmetro do corrimão instalado é inferior a " + minHandrailGrip + " mm");
                } else if (step.getLeftHandDiam() > maxHandrailGrip) {
                    stepIrregular(builder);
                    builder.append("o diâmetro do corrimão instalado é superior a " + maxHandrailGrip + " mm");
                }

                if (step.getLeftHandDist() < minDistHandrail) {
                    stepIrregular(builder);
                    builder.append("a distância entre o corrimão instalado e a parede/obstáculos próximos é inferior a " + minDistHandrail + " mm");
                }
            }

        } else {
            if (step.getFirstMirror() < minStepHeight) {
                stepIrregular(builder);
                builder.append("a altura do primeiro espelho do degrau isolado é inferior à " + minStepHeight + " m");
            } else if (step.getFirstMirror() > maxStepHeight) {
                stepIrregular(builder);
                builder.append("a altura do primeiro do espelho do degrau isolado é superior à " + maxStepHeight + " m");
            }

            if (step.getStepLength() < minStepLength) {
                stepIrregular(builder);
                builder.append("o comprimento do piso do degrau isolado é inferior a " + minStepLength + " m");
            } else if (step.getStepLength() > maxStepLength) {
                stepIrregular(builder);
                builder.append("o comprimento do piso do degrau isolado é superior a " + maxStepLength + " m");
            }

            if (step.getSecondMirror() < minStepHeight) {
                stepIrregular(builder);
                builder.append("a altura do segundo espelho do degrau isolado é inferior à " + minStepHeight + " m");
            } else if (step.getSecondMirror() > maxStepHeight) {
                stepIrregular(builder);
                builder.append("a altura do segundo do espelho do degrau isolado é superior à " + maxStepHeight + " m");
            }

//            TODO - Corrigir este Texto
            if (step.getFirstMirror() + step.getStepLength() + step.getSecondMirror() < minStairsSum) {
                stepIrregular(builder);
                builder.append("o dimensionamento da união entre o piso e os espelhos do degrau isolado possui valor inferior a " + minStairsSum + " m");
            } else if (step.getFirstMirror() + step.getStepLength() + step.getSecondMirror() > maxStairsSum) {
                stepIrregular(builder);
                builder.append("o dimensionamento da união entre o piso e os espelhos do degrau isolado possui valor superior a " + maxStairsSum + " m");
            }

            if (step.getHasLeftHand() == 0 && step.getHasMiddleHand() == 0) {
                stepIrregular(builder);
                builder.append("ausência de corrimão instalado ao lado esquerdo do degrau isolado");
            } else if (step.getHasLeftHand() == 1) {
                if (step.getLeftHandUpHeight() != highHandrail) {
                    stepIrregular(builder);
                    builder.append("o corrimão superior do lado esquerdo do degrau isolado se encontra instalado a uma altura diferente de " + highHandrail + " m");
                }

                if (step.getLeftHandDownHeight() == null) {
                    stepIrregular(builder);
                    builder.append("ausência de instalação do corrimão inferior ao lado esquerdo do degrau isolado");
                } else if (step.getLeftHandDownHeight() != lowHandrail) {
                    stepIrregular(builder);
                    builder.append("o corrimão inferior do lado esquerdo do degrau isolado se encontra instalado a uma altura diferente de " + lowHandrail + " m");
                }

                if (step.getLeftHandDiam() < minHandleGrip) {
                    stepIrregular(builder);
                    builder.append("o diâmetro do corrimão instalado é inferior a " + minHandrailGrip + " mm");
                } else if (step.getLeftHandDiam() > maxHandrailGrip) {
                    stepIrregular(builder);
                    builder.append("o diâmetro do corrimão instalado é superior a " + maxHandrailGrip + " mm");
                }

                if (step.getLeftHandDist() < minDistHandrail) {
                    stepIrregular(builder);
                    builder.append("a distância entre o corrimão instalado e a parede/obstáculos próximos é inferior a " + minDistHandrail + " mm");
                }

                if (step.getLeftHasLowerExt() == 0) {
                    stepIrregular(builder);
                    builder.append("o corrimão instalado ao lado esquerdo do degrau isolado não possui prolongamento em seu nível inferior");
                } else {
                    if (step.getLeftLowerUpLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão superior instalado ao lado esquerdo e no nível inferior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                    if (step.getLeftLowerDownLength() != null && step.getLeftLowerDownLength() != null &&  step.getLeftLowerDownLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão inferior instalado ao lado esquerdo e no nível inferior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                }

                if (step.getLeftHasUpperExt() == 0) {
                    stepIrregular(builder);
                    builder.append("o corrimão instalado ao lado esquerdo do degrau isolado não possui prolongamento em seu nível superior");
                } else {
                    if (step.getLeftUpperUpLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão superior instalado ao lado esquerdo e no nível superior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                    if (step.getLeftUpperDownLength() != null && step.getLeftLowerDownLength() != null &&  step.getLeftLowerDownLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão inferior instalado ao lado esquerdo e no nível superior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                }
            }
//            ----------------------------------------------

            if (step.getHasRightHand() == 0 && step.getHasMiddleHand() == 0) {
                stepIrregular(builder);
                builder.append("ausência de corrimão instalado ao lado direito do degrau isolado");
            } else if (step.getHasRightHand() == 1) {
                if (step.getRightHandUpHeight() != highHandrail) {
                    stepIrregular(builder);
                    builder.append("o corrimão superior do lado direito do degrau isolado se encontra instalado a uma altura diferente de " + highHandrail + " m");
                }

                if (step.getRightHandDownHeight() == null) {
                    stepIrregular(builder);
                    builder.append("ausência de instalação do corrimão inferior ao lado direito do degrau isolado");
                } else if (step.getRightHandDownHeight() != lowHandrail) {
                    stepIrregular(builder);
                    builder.append("o corrimão inferior do lado direito do degrau isolado se encontra instalado a uma altura diferente de " + lowHandrail + " m");
                }

                if (step.getRightHandDiam() < minHandleGrip) {
                    stepIrregular(builder);
                    builder.append("o diâmetro do corrimão instalado é inferior a " + minHandrailGrip + " mm");
                } else if (step.getRightHandDiam() > maxHandrailGrip) {
                    stepIrregular(builder);
                    builder.append("o diâmetro do corrimão instalado é superior a " + maxHandrailGrip + " mm");
                }

                if (step.getRightHandDist() < minDistHandrail) {
                    stepIrregular(builder);
                    builder.append("a distância entre o corrimão instalado e a parede/obstáculos próximos é inferior a " + minDistHandrail + " mm");
                }

                if (step.getRightHasLowerExt() == 0) {
                    stepIrregular(builder);
                    builder.append("o corrimão instalado ao lado direito do degrau isolado não possui prolongamento em seu nível inferior");
                } else {
                    if (step.getRightLowerUpLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão superior instalado ao lado direito e no nível inferior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                    if (step.getRightLowerDownLength() != null && step.getRightLowerDownLength() != null &&  step.getRightLowerDownLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão inferior instalado ao lado direito e no nível inferior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                }

                if (step.getRightHasUpperExt() == 0) {
                    stepIrregular(builder);
                    builder.append("o corrimão instalado ao lado direito do degrau isolado não possui prolongamento em seu nível superior");
                } else {
                    if (step.getRightUpperUpLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão superior instalado ao lado direito e no nível superior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                    if (step.getRightUpperDownLength() != null && step.getRightLowerDownLength() != null &&  step.getRightLowerDownLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão inferior instalado ao lado direito e no nível inferior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                }
            }

//            -------------------------------

            if (step.getHasMiddleHand() == 1) {
                if (step.getStepWidth() < minStepWidthInterHand) {
                    stepIrregular(builder);
                    builder.append("instalação de corrimão intermediário em degrau isolado com largura inferior a " + minStepWidthInterHand + " m");
                }

                if (step.getMiddleHandUpHeight() != highHandrail) {
                    stepIrregular(builder);
                    builder.append("o corrimão superior intermediário do degrau isolado se encontra instalado a uma altura diferente de " + highHandrail + " m");
                }

                if (step.getMiddleHandDownHeight() == null) {
                    stepIrregular(builder);
                    builder.append("ausência de instalação do corrimão inferior intermediário do degrau isolado");
                } else if (step.getMiddleHandDownHeight() != lowHandrail) {
                    stepIrregular(builder);
                    builder.append("o corrimão inferior intermediário do degrau isolado se encontra instalado a uma altura diferente de " + lowHandrail + " m");
                }

                if (step.getMiddleHandDiam() < minHandleGrip) {
                    stepIrregular(builder);
                    builder.append("o diâmetro do corrimão instalado é inferior a " + minHandrailGrip + " mm");
                } else if (step.getMiddleHandDiam() > maxHandrailGrip) {
                    stepIrregular(builder);
                    builder.append("o diâmetro do corrimão instalado é superior a " + maxHandrailGrip + " mm");
                }

                if (step.getMiddleHasLowerExt() == 0) {
                    stepIrregular(builder);
                    builder.append("o corrimão intermediário do degrau isolado não possui prolongamento em seu nível inferior");
                } else {
                    if (step.getMiddleLowerUpLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão superior intermediário no nível inferior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                    if (step.getMiddleLowerDownLength() != null && step.getMiddleLowerDownLength() != null &&  step.getMiddleLowerDownLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão inferior intermediário no nível inferior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                }

                if (step.getMiddleHasUpperExt() == 0) {
                    stepIrregular(builder);
                    builder.append("o corrimão intermediário do degrau isolado não possui prolongamento em seu nível superior");
                } else {
                    if (step.getMiddleUpperUpLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão superior intermediário no nível superior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                    if (step.getMiddleUpperDownLength() != null && step.getMiddleLowerDownLength() != null &&  step.getMiddleLowerDownLength() < extendHandrail) {
                        stepIrregular(builder);
                        builder.append("o comprimento do prolongamento do corrimão inferior intermediário no nível inferior do degrau isolado " +
                                "está abaixo de " + extendHandrail + " m");
                    }
                }
            }
        }

        if (step.getStepHasSign() == 0) {
            stepIrregular(builder);
            builder.append("o degrau isolado não se econtra sinalizado");
        } else {
            if (step.getStepSignWidth() < minBorderSignWidth) {
                stepIrregular(builder);
                builder.append("a sinalização do degrau isolado possui largura inferior a " + minBorderSignWidth + " cm");
            }
            if (step.getStepSignFullApp() == 0) {
                stepIrregular(builder);
                builder.append("a sinalizaão não está aplicada por toda a extensão do do degrau isolado");
            }
            if (step.getStepSignMirrorStep() == 0) {
                stepIrregular(builder);
                builder.append("a sinalizaão não está aplicada nos espelhos e piso do degrau isolado");
            }
        }

        if (step.getStepHasTactSign() == 0) {
            stepIrregular(builder);
            builder.append("o degrau isolado não possui sinalização tátil de alerta");
        } else {
            if (step.getHasLowTact() == 0) {
                stepIrregular(builder);
                builder.append("o degrau isolado não possui sinalização tátil de alerta no piso inferior");
            } else {
                if (step.getLowTactDist() > maxTactDistSingleStepLow) {
                    stepIrregular(builder);
                    builder.append("a distância entre a sinalização tátil de alerta e o espelho do degrau inferior é maior que " + maxTactDistSingleStepLow + " m");
                }
                if (step.getLowTactWidth() < minTactWidthSingleStepLow) {
                    stepIrregular(builder);
                    builder.append("a largura da sinalização tátil de alerta no piso inferior é menor que " + minTactWidthSingleStepLow + " m");
                }

                if (step.getLowTactDist() + step.getLowTactWidth() < minTactWidthDistSingleStepLow) {
                    stepIrregular(builder);
                    builder.append("a soma dos valores da largura da sinalização de alerta no piso inferior com a distância de sua instalação até o espelho do degrau inferior " +
                            "é menor que " + minTactWidthDistSingleStepLow + " m");
                } else if (step.getLowTactDist() + step.getLowTactWidth() > maxTactWidthDistSingleStepLow) {
                    stepIrregular(builder);
                    builder.append("a soma dos valores da largura da sinalização de alerta no piso inferior com a distância de sua instalação até o espelho do degrau inferior " +
                            "é maior que " + maxTactWidthDistSingleStepLow + " m");
                }

                if (step.getLowTactAntiDrift() == 0) {
                    stepIrregular(builder);
                    builder.append("a sinalização tátil de alerta no piso inferior não é antiderrapante");
                }
                if (step.getLowTactSoilContrast() == 0) {
                    stepIrregular(builder);
                    builder.append("a sinalização tátil de alerta no piso inferior não possui contraste de relevo com o piso adjacente");
                }
                if (step.getLowTactVisualContrast() == 0) {
                    stepIrregular(builder);
                    builder.append("a sinalização tátil de alerta no piso inferior não possui contraste de luminância com o piso adjacente");
                }
            }

            if (step.getHasHighTact() == 0) {
                stepIrregular(builder);
                builder.append("o degrau isolado não possui sinalização tátil de alerta no piso superior");
            } else {
                if (step.getHighTactDist() > minTactDistSingleStepHigh) {
                    stepIrregular(builder);
                    builder.append("a distância entre a sinalização tátil de alerta e o espelho do último degrau é menor que " + minTactDistSingleStepHigh + " m");
                }
                if (step.getHighTactWidth() < minTactWidthSingleStepHigh) {
                    stepIrregular(builder);
                    builder.append("a largura da sinalização tátil de alerta no piso superior é menor que " + minTactWidthSingleStepLow + " m");
                }

                if (step.getHighTactDist() + step.getHighTactWidth() > minTactWidthDistSingleStepHigh) {
                    stepIrregular(builder);
                    builder.append("a soma dos valores da largura da sinalização de alerta no piso superior com a distância de sua instalação até o espelho do último degrau " +
                            "é menor que " + minTactWidthDistSingleStepHigh + " m");
                }

                if (step.getHighTactAntiDrift() == 0) {
                    stepIrregular(builder);
                    builder.append("a sinalização tátil de alerta no piso superior não é antiderrapante");
                }
                if (step.getHighTactSoilContrast() == 0) {
                    stepIrregular(builder);
                    builder.append("a sinalização tátil de alerta no piso superior não possui contraste de relevo com o piso adjacente");
                }
                if (step.getHighTactVisualContrast() == 0) {
                    stepIrregular(builder);
                    builder.append("a sinalização tátil de alerta no piso superior não possui contraste de luminância com o piso adjacente");
                }
            }
        }

        if (step.getStepObs() != null && step.getStepObs().length() > 0) {
            stepIrregular(builder);
            builder.append("as seguintes observações podem ser apontadas: ").append(step.getStepObs()).append(";");
        }
        if (step.getStepPhoto() != null && step.getStepPhoto().length() > 0) {
            stepIrregular(builder);
            builder.append("registro fotográfico do degrau isolado: ").append(step.getStepPhoto()).append(";");
        }

        if (builder.length() > 0) {
            builder.replace(42,43, step.getStepLocation());
            irregular = builder.toString();
        }

        return irregular;
    }

    private static void stepIrregular(StringBuilder builder) {
        if (!irregularStep) {
            irregularStep = true;
            builder.append("Presença de degrau isolado, localizado em x, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
