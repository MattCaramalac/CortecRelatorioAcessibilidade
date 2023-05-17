package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.PoolRampEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class PoolRampAnalysis implements StandardMeasurements {

    static boolean irrRamp;

    public static List<String> poolRampIrregular(int poolID, List<PoolRampEntry> rList) {

        List<String> irrList = new ArrayList<>();

        if (rList.size() > 0) {
            for (PoolRampEntry ramp : rList) {
                irrRamp = false;
                String analysis = null;
                if (ramp.getPoolID() == poolID)
                    analysis = rampText(ramp);
                if (analysis != null && analysis.length() > 0)
                    irrList.add(analysis);
            }
        }
        return irrList;
    }

    private static String rampText(PoolRampEntry ramp) {
        StringBuilder builder = new StringBuilder();
        if ((ramp.getRampIncl1() != null && ramp.getRampIncl1() > maxAngleRamp) ||
                (ramp.getRampIncl2() != null && ramp.getRampIncl2() > maxAngleRamp) ||
                (ramp.getRampIncl3() != null && ramp.getRampIncl3() > maxAngleRamp) ||
                (ramp.getRampIncl4() != null && ramp.getRampIncl4() > maxAngleRamp)) {
            rampIrregular(builder);
            builder.append("a rampa de acesso ao tanque possui inclinação superior a " + maxAngleRamp + "%");
        }
        if (ramp.getRampAccessFloor() == 0) {
            rampIrregular(builder);
            builder.append("o piso da rampa de acesso ao tanque não pode ser considerado acessível pelos seguintes fatores: ")
                    .append(ramp.getAccessFloorObs().trim());
        } else if (ramp.getRampAccessFloor() == 1 && ramp.getAccessFloorObs() != null && ramp.getAccessFloorObs().length() > 0) {
            rampIrregular(builder);
            builder.append("o piso da rampa de acesso ao tanque pode ser considerado acessível, porém deve-se apontar às seguintes observações: ")
                    .append(ramp.getAccessFloorObs().trim());
        }

        if (ramp.getHasLeftHand() == 0) {
            rampIrregular(builder);
            builder.append("a rampa de acesso ao tanque não possui corrimão à sua esquerda");
        } else {
            if (ramp.getLeftHandHeight() != lowHandrail) {
                rampIrregular(builder);
                builder.append("a altura do corrimão instalado à esquerda é diferente de " + lowHandrail + " m");
            }
            if (ramp.getLeftHandDiam() < minHandrailGrip) {
                rampIrregular(builder);
                builder.append("o diâmetro do corrimão instalado à esquerda é inferior à " + minHandrailGrip + " mm");
            } else if (ramp.getLeftHandDiam() > maxHandrailGrip) {
                rampIrregular(builder);
                builder.append("o diâmetro do corrimão instalado à esquerda é superior à " + maxHandrailGrip + " mm");
            }
            if (ramp.getLeftHandDist() < minDistHandrail) {
                rampIrregular(builder);
                builder.append("a distância entre a parede do tanque e o corrimão instalado à esquerda da rampa é inferior a " + minDistHandrail + " mm");
            }
        }

        if (ramp.getHasRightHand() == 0) {
            rampIrregular(builder);
            builder.append("a rampa de acesso ao tanque não possui corrimão à sua direita");
        } else {
            if (ramp.getRightHandHeight() != lowHandrail) {
                rampIrregular(builder);
                builder.append("a altura do corrimão instalado à direita é diferente de " + lowHandrail + " m");
            }
            if (ramp.getRightHandDiam() < minHandrailGrip) {
                rampIrregular(builder);
                builder.append("o diâmetro do corrimão instalado à direita é inferior à " + minHandrailGrip + " mm");
            } else if (ramp.getRightHandDiam() > maxHandrailGrip) {
                rampIrregular(builder);
                builder.append("o diâmetro do corrimão instalado à direita é superior à " + maxHandrailGrip + " mm");
            }
            if (ramp.getRightHandDist() < minDistHandrail) {
                rampIrregular(builder);
                builder.append("a distância entre a parede do tanque e o corrimão instalado à direita da rampa é inferior a " + minDistHandrail + " mm");
            }
        }

        if (ramp.getPoolHandObs() != null && ramp.getPoolHandObs().length() > 0) {
            rampIrregular(builder);
            builder.append("as seguintes observações relativas aos corrimãos da rampa de acesso ao tanque devem ser apontadas: ")
                    .append(ramp.getPoolHandObs().trim());
        }

        if (ramp.getPoolRampObs() != null && ramp.getPoolRampObs().length() > 0) {
            rampIrregular(builder);
            builder.append("as seguintes observações relativas a rampa de acesso ao tanque devem ser apontadas: ")
                    .append(ramp.getPoolRampObs().trim());
        }

        if (ramp.getPoolRampPhoto() != null && ramp.getPoolRampPhoto().length() > 0) {
            rampIrregular(builder);
            builder.append("registro fotográfico: ").append(ramp.getPoolRampPhoto().trim());
        }


        if (builder.length() > 0) {
            builder.replace(43, 44, ramp.getPoolRampLocale().trim());
            builder.append(";");
        }

        return builder.toString();

    }



    private static void rampIrregular(StringBuilder builder) {
        if (!irrRamp) {
            irrRamp = true;
            builder.append("Presença de rampa de acesso, localizada em x, com as seguintes irregularidades: ");
        } else {
            builder.append(", ");
        }
    }

}
