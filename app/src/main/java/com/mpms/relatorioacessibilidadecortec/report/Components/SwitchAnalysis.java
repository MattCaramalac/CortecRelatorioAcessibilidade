package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class SwitchAnalysis implements StandardMeasurements {

    static boolean irregularSwitch;

    public static List<String> switchList (int roomID, List<SwitchEntry> swList) {

        List<String> swListing = new ArrayList<>();

        if (swList.size() > 0) {
            for (SwitchEntry swEntry : swList) {
                irregularSwitch = false;
                String analysis = null;
                if (swEntry.getRoomID() == roomID)
                    analysis = swText(swEntry);
                if (analysis != null && analysis.length() > 0)
                    swListing.add(analysis);
            }
        }

        return swListing;
    }

    private static String swText(SwitchEntry swEntry) {
        StringBuilder builder = new StringBuilder();
        if (swEntry.getSwitchHeight() > maxSwHeight) {
            switchIrregular(builder);
            builder.append("a altura do interruptor é superior a " + maxSwHeight + " m");
        } else if (swEntry.getSwitchHeight() < minSwHeight) {
            switchIrregular(builder);
            builder.append("a altura do interruptor é inferior a " + minSwHeight + " m");
        }

        if (swEntry.getSwitchObs() != null && swEntry.getSwitchObs().length() > 0) {
            switchIrregular(builder);
            builder.append("as seguintes observações devem ser apontadas sobre o interruptor: ").append(swEntry.getSwitchObs());
        }

        if (builder.length() > 0) {
            builder.replace(39, 40, swEntry.getSwitchLocation());
            builder.replace(21, 22, swEntry.getSwitchType());
        }
        return builder.toString();
    }

    private static void switchIrregular(StringBuilder builder) {
        if (!irregularSwitch) {
            irregularSwitch = true;
            builder.append("Interruptor, do tipo y e localizado em x, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
