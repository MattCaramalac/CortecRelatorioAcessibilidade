package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.EquipmentEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class EquipmentAnalysis implements StandardMeasurements {

    static boolean irregularEquip;

    public static List<String> equipList(int roomID, List<EquipmentEntry> equipList) {

        List<String> eList = new ArrayList<>();

        if (equipList.size() > 0) {
            for (EquipmentEntry equip : equipList) {
                irregularEquip = false;
                String analysis = null;
                if (equip.getEquipID() == roomID)
                    analysis = equipText(equip);
                if (analysis != null && analysis.length() > 0)
                    eList.add(analysis);
            }
        }
        return eList;
    }

    private static String equipText(EquipmentEntry equip) {
        StringBuilder builder = new StringBuilder();

        if (equip.getEquipHeight() < lowestHeight)
            builder.append("Presença de equipamento do tipo x, localizada em y, instalado abaixo de " + lowestHeight + " m;");
        else if ((equip.getEquipHeight() > lowestHeight && equip.getEquipHeight() < lowestAcceptHeight) ||
                (equip.getEquipHeight() > highestAcceptHeight && equip.getEquipHeight() < highestHeight))
            builder.append("Recomenda-se a instalação do equipamento do tipo x, localizada em y, numa altura entre " + lowestAcceptHeight + " e " + highestAcceptHeight + " m;");
        else if (equip.getEquipHeight() > highestHeight)
            builder.append("Presença de equipamento do tipo x, localizada em y, instalado acima de " + highestHeight + " m;");

        if (builder.length() <= 0)
            return null;
        else {
            if (equip.getEquipLocale() != null && equip.getEquipLocale().length() > 0)
                builder.replace(49, 50, equip.getEquipLocale());
            else
                builder.replace(34, 50, "");

            builder.replace(32, 33, equip.getEquipName());

            return builder.toString();
        }
    }

}
