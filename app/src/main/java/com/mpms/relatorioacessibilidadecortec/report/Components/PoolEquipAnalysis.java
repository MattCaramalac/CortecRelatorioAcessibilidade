package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEquipEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class PoolEquipAnalysis implements StandardMeasurements {

    static boolean irrEquip;

    public static List<String> poolEquipIrregular(int poolID, List<PoolEquipEntry> eqList) {

        List<String> irrList = new ArrayList<>();

        if (eqList.size() > 0) {
            for (PoolEquipEntry equip : eqList) {
                irrEquip = false;
                String analysis = null;
                if (equip.getPoolID() == poolID)
                    analysis = equipText(equip);
                if (analysis != null && analysis.length() > 0)
                    irrList.add(analysis);
            }
        }
        return irrList;
    }

    private static String equipText(PoolEquipEntry equip) {
        StringBuilder builder = new StringBuilder();
        if (equip.getTransfMeasureA() < mrLength || equip.getTransfMeasureB() < mrWidth) {
            equipIrregular(builder);
            builder.append("as dimensões do módulo de referência ao lado do equipamento estão abaixo dos valores estipulados em norma");
        }
        if (equip.getTransfMeasureC() != behindEquipLength) {
            equipIrregular(builder);
            builder.append("o espaço do módulo de referência localizado atrás do equipamento possui comprimento diferente de " + behindEquipLength + " m");
        }

        if (equip.getTransfMeasureD() != distanceAxisPool) {
            equipIrregular(builder);
            builder.append("a distância do eixo de rotação do equipamento até o tanque da piscina é diferente de " + distanceAxisPool + " m");
        }

        if (equip.getTransfMeasureD() < minHeightTransfEquip) {
            equipIrregular(builder);
            builder.append("a altura do assento do equipamento é inferior a " + minHeightTransfEquip + " m");
        } else if (equip.getTransfMeasureD() > maxHeightTransfEquip) {
            equipIrregular(builder);
            builder.append("a altura do assento do equipamento é superior a " + maxHeightTransfEquip + " m");
        }

        if (equip.getTransfObs() != null && equip.getTransfObs().length() > 0) {
            equipIrregular(builder);
            builder.append("as seguintes observações devem ser apontadas sobre o equipamento em questão: ").append(equip.getTransfObs());
        }

        if (equip.getTransfPhoto() != null && equip.getTransfPhoto().length() > 0) {
            equipIrregular(builder);
            builder.append("registros fotográficos: ").append(equip.getTransfPhoto());
        }

        if (builder.length() > 0) {
            builder.replace(49, 50, equip.getTransfLocation());
            builder.append(";");
        }

        return builder.toString();
    }

    private static void equipIrregular(StringBuilder builder) {
        if (!irrEquip) {
            irrEquip = true;
            builder.append("Presença de equipamento de acesso, localizado em x, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
