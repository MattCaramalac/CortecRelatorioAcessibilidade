package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.PoolStairsEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class PoolStairsAnalysis implements StandardMeasurements {

    static boolean irrStairs;

    public static List<String> poolStairsIrregular(int poolID, List<PoolStairsEntry> sList) {

        List<String> irrList = new ArrayList<>();

        if (sList.size() > 0) {
            for (PoolStairsEntry stairs : sList) {
                irrStairs = false;
                String analysis = null;
                if (stairs.getPoolID() == poolID)
                    analysis = stairsText(stairs);
                if (analysis != null && analysis.length() > 0)
                    irrList.add(analysis);
            }
        }
        return irrList;
    }

    private static String stairsText(PoolStairsEntry stairs) {
        StringBuilder builder = new StringBuilder();
        if ((stairs.getMirror1() != null && stairs.getMirror1() > maxPoolStairsMirror) ||
                (stairs.getMirror2() != null && stairs.getMirror2() > maxPoolStairsMirror) ||
                (stairs.getMirror3() != null && stairs.getMirror3() > maxPoolStairsMirror) ||
                (stairs.getMirror4() != null && stairs.getMirror4() > maxPoolStairsMirror)) {
            stairsIrregular(builder);
            builder.append("a escada possui espelhos com altura superior a " + maxPoolStairsMirror + " m");
        }

        if ((stairs.getStep1() != null && stairs.getStep1() < minPoolStepLength) ||
                (stairs.getStep2() != null && stairs.getStep2() < minPoolStepLength) ||
                (stairs.getStep3() != null && stairs.getStep3() < minPoolStepLength) ||
                (stairs.getStep4() != null && stairs.getStep4() < minPoolStepLength)) {
            stairsIrregular(builder);
            builder.append("a escada possui pisos com largura inferior a " + minPoolStepLength + " m");
        }

        if ((stairs.getStep1() != null && stairs.getStep1() > maxPoolStepLength) ||
                (stairs.getStep2() != null && stairs.getStep2() > maxPoolStepLength) ||
                (stairs.getStep3() != null && stairs.getStep3() > maxPoolStepLength) ||
                (stairs.getStep4() != null && stairs.getStep4() > maxPoolStepLength)) {
            stairsIrregular(builder);
            builder.append("a escada possui pisos com largura superior a " + maxPoolStepLength + " m");
        }

        if (stairs.getStairsHasLeftHand() == 0) {
            stairsIrregular(builder);
            builder.append("a escada não possui corrimão instalado à sua esquerda");
        } else {
            if (stairs.getPoolLeftUpperHandHeight() != highHandrail) {
                stairsIrregular(builder);
                builder.append("a altura de instalação do corrimão superior esquerdo é diferente de " + highHandrail + " m");
            }
            if (stairs.getPoolLeftInterHandHeight() == null) {
                stairsIrregular(builder);
                builder.append("a escada não possui corrimão intermediário instalado à sua esquerda");
            } else if (stairs.getPoolLeftInterHandHeight() != null && stairs.getPoolLeftInterHandHeight() != highHandrail) {
                stairsIrregular(builder);
                builder.append("a altura de instalação do corrimão intermediário esquerdo é diferente de " + highHandrail + " m");
            }
            if (stairs.getPoolLeftLowerHandHeight() == null) {
                stairsIrregular(builder);
                builder.append("a escada não possui corrimão inferior instalado à sua esquerda");
            } else if (stairs.getPoolLeftLowerHandHeight() != null && stairs.getPoolLeftLowerHandHeight() != lowerPoolHandHeight) {
                stairsIrregular(builder);
                builder.append("a altura de instalação do corrimão inferior esquerdo é diferente de " + highHandrail + " m");
            }
            if (stairs.getPoolLeftHandDiam() < minHandrailGrip) {
                stairsIrregular(builder);
                builder.append("o diâmetro do corrimão instalado à esquerda é inferior à " + minHandrailGrip + " mm");
            } else if (stairs.getPoolLeftHandDiam() > maxHandrailGrip) {
                stairsIrregular(builder);
                builder.append("o diâmetro do corrimão instalado à esquerda é superior à " + maxHandrailGrip + " mm");
            }
            if (stairs.getPoolLeftHandDist() < minDistHandrail) {
                stairsIrregular(builder);
                builder.append("a distância entre a parede do tanque e o corrimão instalado à esquerda é inferior a " + minDistHandrail + " mm");
            }
        }

        if (stairs.getStairsHasRightHand() == 0) {
            stairsIrregular(builder);
            builder.append("a escada não possui corrimão instalado à sua direita");
        } else {
            if (stairs.getPoolRightUpperHandHeight() != highHandrail) {
                stairsIrregular(builder);
                builder.append("a altura de instalação do corrimão superior direito é diferente de " + highHandrail + " m");
            }
            if (stairs.getPoolRightInterHandHeight() == null) {
                stairsIrregular(builder);
                builder.append("a escada não possui corrimão intermediário instalado à sua direita");
            } else if (stairs.getPoolRightInterHandHeight() != null && stairs.getPoolRightInterHandHeight() != highHandrail) {
                stairsIrregular(builder);
                builder.append("a altura de instalação do corrimão intermediário direito é diferente de " + highHandrail + " m");
            }
            if (stairs.getPoolRightLowerHandHeight() == null) {
                stairsIrregular(builder);
                builder.append("a escada não possui corrimão inferior instalado à sua direita");
            } else if (stairs.getPoolRightLowerHandHeight() != null && stairs.getPoolRightLowerHandHeight() != lowerPoolHandHeight) {
                stairsIrregular(builder);
                builder.append("a altura de instalação do corrimão inferior direito é diferente de " + highHandrail + " m");
            }
            if (stairs.getPoolRightHandDiam() < minHandrailGrip) {
                stairsIrregular(builder);
                builder.append("o diâmetro do corrimão instalado à direita é inferior à " + minHandrailGrip + " mm");
            } else if (stairs.getPoolRightHandDiam() > maxHandrailGrip) {
                stairsIrregular(builder);
                builder.append("o diâmetro do corrimão instalado à direita é superior à " + maxHandrailGrip + " mm");
            }
            if (stairs.getPoolRightHandDist() < minDistHandrail) {
                stairsIrregular(builder);
                builder.append("a distância entre a parede do tanque e o corrimão instalado à direita é inferior a " + minDistHandrail + " mm");
            }
        }

        if (stairs.getPoolStairsObs() != null && stairs.getPoolStairsObs().length() > 0) {
            stairsIrregular(builder);
            builder.append("as seguintes observações relativas a escada de acesso ao tanque devem ser apontadas: ")
                    .append(stairs.getPoolStairsObs().trim());
        }

        if (stairs.getPoolStairsPhoto() != null && stairs.getPoolStairsPhoto().length() > 0) {
            stairsIrregular(builder);
            builder.append("registro fotográfico: ").append(stairs.getPoolStairsPhoto().trim());
        }



        if (builder.length() > 0) {
            builder.replace(43, 44, stairs.getStairsLocation().trim());
            builder.append(";");
        }

        return builder.toString();


    }

    private static void stairsIrregular(StringBuilder builder) {
        if (!irrStairs) {
            irrStairs = true;
            builder.append("Presença de escada de acesso, localizada em x, com as seguintes irregularidades: ");
        } else {
            builder.append(", ");
        }
    }
}
