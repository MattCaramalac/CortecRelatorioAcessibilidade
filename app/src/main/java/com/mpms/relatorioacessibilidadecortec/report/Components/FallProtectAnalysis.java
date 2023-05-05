package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.FallProtectionEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class FallProtectAnalysis implements StandardMeasurements {

    static boolean irregularProtect;

    public static List<String> fallProtectList (int circID, List<FallProtectionEntry> fList) {

        List<String> protectList = new ArrayList<>();

        if (fList.size() > 0) {
            for (FallProtectionEntry fall : fList) {
                irregularProtect = false;
                String analysis = null;
                if (fall.getCircID() == circID)
                    analysis = protectText(fall);
                if (analysis != null && analysis.length() > 0) {
                    String analysis2 = analysis.trim();
                    protectList.add(analysis2.concat(";"));
                }
            }
        }
        return protectList;
    }

    private static String protectText(FallProtectionEntry fall) {
        String irregular = null;
        StringBuilder builder = new StringBuilder();

        if (fall.getUnevenHeight() >= minHeightProtect && fall.getHasFallProtect() == 0) {
            irregularProtect = true;
            return "Ausência de proteção contra queda em circulação com desnível entre pavimentos maior ou igual a " + minHeightProtect +
                    " m, localizada em " + fall.getProtectLocal();
        }

        if (fall.getFallProtectType() == 0) {
            if (fall.getProtectWidthLength() < safetyLaneWidth) {
                fallIrregular(builder);
                builder.append("A largura da margem plana ao lado da faixa de circulação tem largura inferior a " + safetyLaneWidth + " m");
            }
            if (fall.getHasVisualContrast() == 0) {
                fallIrregular(builder);
                builder.append("A margem plana ao lado da faixa de circulação não possui contraste visual em relação ao piso da faixa de circulação");
            }
            if (fall.getHasTactileContrast() == 0) {
                fallIrregular(builder);
                builder.append("A margem plana ao lado da faixa de circulação não possui contraste tátil em relação ao piso da faixa de circulação");
            }
        } else if (fall.getFallProtectType() == 1) {
            if (fall.getProtectWidthLength() < latProtectHeight) {
                fallIrregular(builder);
                builder.append("A altura da proteção vertical ao lado da faixa de circulação é inferior a " + latProtectHeight + " m");
            }
            if (fall.getHasVisualContrast() == 0) {
                fallIrregular(builder);
                builder.append("O topo da proteção vertical não possui contraste visual em relação ao piso da faixa de circulação");
            }
        }

//        else if (fall.getFallProtectType() == 2) {
//          será usado eventualmente?
//        }

        if (fall.getFallProtectObs() != null && fall.getFallProtectObs().length() > 0) {
            fallIrregular(builder);
            builder.append("As seguintes observações podem ser feitas sobre a proteção contra queda: ").append(fall.getFallProtectObs());
        }
        if (fall.getProtectPhoto() != null && fall.getProtectPhoto().length() > 0) {
            fallIrregular(builder);
            builder.append("Registros Fotográficos: ").append(fall.getProtectPhoto());
        }
        if (fall.getProtectObs() != null && fall.getProtectObs().length() > 0) {
            fallIrregular(builder);
            builder.append("Outras observações que podem ser feitas sobre a circulação e sua proteção: ").append(fall.getProtectObs());
        }

        if (builder.length() > 0) {
            builder.replace(61, 62, fall.getProtectLocal());
            builder.replace(43, 44, protectType(fall.getFallProtectType()));
            irregular = builder.toString();
        }

        return irregular;
    }

    private static void fallIrregular(StringBuilder builder) {
        if (!irregularProtect) {
            irregularProtect = true;
            builder.append("Presença de proteção contra queda, do tipo x e localizada em y, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }

    private static String protectType(int i) {
        switch (i) {
            case 2:
                return "com guarda-corpo";
            case 1:
                return "com proteção vertical";
            default:
                return "com margem plana ao lado da circulação";
        }
    }
}
