package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class SlopeAnalysis implements StandardMeasurements {

    static boolean irregularSlope;

    public static List<String>  circSlopeList (int circID, List<SlopeEntry> sList) {

        List<String> slopeList = new ArrayList<>();

        if (sList.size() > 0) {
            for (SlopeEntry slope : sList) {
                irregularSlope = false;
                String analysis = null;
                if (slope.getCircID() != null && slope.getCircID() == circID)
                    analysis = slopeText(slope);
                if (analysis != null && analysis.length() > 0) {
                    String analysis2 = analysis.trim();
                    slopeList.add(analysis2.concat(";"));
                }
            }
        }
        return slopeList;
    }

    public static List<String> roomSlopeList (int roomID, List<SlopeEntry> sList) {

        List<String> slopeList = new ArrayList<>();

        if (sList.size() > 0) {
            for (SlopeEntry slope : sList) {
                irregularSlope = false;
                String analysis = null;
                if (slope.getRoomID() != null && slope.getRoomID() == roomID)
                    analysis = slopeText(slope);
                if (analysis != null && analysis.length() > 0) {
                    String analysis2 = analysis.trim();
                    slopeList.add(analysis2.concat(";"));
                }
            }
        }
        return slopeList;
    }

    private static String slopeText(SlopeEntry slope) {
        String irregular = null;
        StringBuilder builder = new StringBuilder();
        if (slope.getSlopeHasRamp() == 0) {
            slopeIrregular(builder);
            builder.append("ausência de tratamento especial (inclinação máxima de até " + maxSlopePerc + "%)");
        } else {
            if (slope.getInclAngle1() != null && slope.getInclAngle1() > maxSlopePerc ||
                    slope.getInclAngle2() != null && slope.getInclAngle2() > maxSlopePerc ||
                    slope.getInclAngle3() != null && slope.getInclAngle3() > maxSlopePerc ||
                    slope.getInclAngle4() != null && slope.getInclAngle4() > maxSlopePerc) {
                slopeIrregular(builder);
                builder.append("desnível possui altura permitida pela norma para compor rota acessível, entretanto se encontra vencido por inclinação " +
                        "superior à " + maxSlopePerc + "%");
            }
        }

        if (slope.getSlopeObs() != null && slope.getSlopeObs().length() > 0) {
            slopeIrregular(builder);
            builder.append("as seguintes observações podem ser feitas sobre este desnível: ").append(slope.getSlopeObs());
        }

        if (slope.getSlopePhoto() != null && slope.getSlopePhoto().length() > 0) {
            slopeIrregular(builder);
            builder.append("registros fotográficos do desnível: ").append(slope.getSlopePhoto());
        }
        if (builder.length() > 0) {
            builder.replace(36,37, slope.getSlopeLocation());
            irregular = builder.toString();
        }

        return irregular;
    }

    private static void slopeIrregular(StringBuilder builder) {
        if (!irregularSlope) {
            irregularSlope = true;
            builder.append("Presença de desnível, localizado em x, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
