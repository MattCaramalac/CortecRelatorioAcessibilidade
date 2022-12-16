package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class ParkElderAnalysis implements StandardMeasurements {

    static boolean irregularElder;

    public static List<String> elderTextList(int parkID, List<ParkingLotElderlyEntry> eList) {

        List<String> elderList = new ArrayList<>();

        if (eList.size() > 0) {
            for (ParkingLotElderlyEntry elder : eList) {
                irregularElder = false;
                String analysis = null;
                if (elder.getParkID() == parkID)
                    analysis = elderText(elder);
                if (analysis != null && analysis.length() > 0)
                    elderList.add(analysis);
            }
        }
        return elderList;
    }

    private static String elderText(ParkingLotElderlyEntry elder) {
        StringBuilder builder = new StringBuilder();
        if (elder.getHasElderlyVertSign() == 0) {
            elderIrregular(builder);
            builder.append("a vaga não possui sinalização vertical");
        } else if (elder.getHasElderlyVertSign() == 1) {
//       Não achei tamanhos, verificar amanhã
        }

        if (elder.getElderlyVacancyWidth() < minWidthVac) {
            elderIrregular(builder);
            builder.append("a vaga possui largura inferior à " + minWidthVac + " m");
        } else if (elder.getElderlyVacancyWidth() > maxWidthVac) {
            elderIrregular(builder);
            builder.append("a vaga possui largura superior à " + maxWidthVac + " m");
        }


        if (elder.getElderlyVacancyLimiterWidth() < minVacLimWidth) {
            elderIrregular(builder);
            builder.append("a vaga possui faixas delimitadoras com largura inferior à" + minVacLimWidth + " m");
        } else if (elder.getElderlyVacancyLimiterWidth() > maxVacLimWidth) {
            elderIrregular(builder);
            builder.append("a vaga possui faixas delimitadoras com largura superior à" + maxVacLimWidth + " m");
        }

        if (elder.getElderlyVacancyObs() != null && elder.getElderlyVacancyObs().length() > 0) {
            elderIrregular(builder);
            builder.append("as seguintes observações devem ser feitas referente à vaga: ").append(elder.getElderlyVacancyObs());
        }

        if (elder.getHasElderlyFloorIndicator() == 0) {
            elderIrregular(builder);
            builder.append("a vaga não possui indicação do tipo de vaga no piso");
        } else {
            if (elder.getFloorIndicatorHeight() < minLetterHeight) {
                elderIrregular(builder);
                builder.append("a altura do texto indicativo do tipo de vaga é inferior à " + minLetterHeight + " m");
            } else if (elder.getFloorIndicatorHeight() > maxLetterHeight) {
                elderIrregular(builder);
                builder.append("a altura do texto indicativo de vaga é superior à " + maxLetterHeight + " m");
            }
        }
        if (elder.getFloorIndicatorObs() != null && elder.getFloorIndicatorObs().length() > 0) {
            elderIrregular(builder);
            builder.append("as seguintes observações devem ser feitas referents ao texto indicativo de vaga: ").append(elder.getFloorIndicatorObs());
        }

        if (irregularElder && builder.length() != 0)
            builder.replace(44, 45, String.valueOf(elder.getElderVacLocation()));

        return builder.toString();
    }

    private static void elderIrregular(StringBuilder builder) {
        if (!irregularElder) {
            irregularElder = true;
            builder.append("Presença de vaga para idosos, localizada em x, com as seguintes irregularidades: "); //41, 42
        } else
            builder.append(", ");
    }
}
