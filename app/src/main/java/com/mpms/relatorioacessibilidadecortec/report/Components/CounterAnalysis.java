package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class CounterAnalysis implements StandardMeasurements {

    static boolean irregularCounter;

    public static List<String> counterTextList(int roomID, List<CounterEntry> cList) {

        List<String> counterList = new ArrayList<>();

        if (cList.size() > 0) {
            for (CounterEntry counter : cList) {
                irregularCounter = false;
                String analysis = null;
                if (counter.getRoomID() == roomID)
                    analysis = counterText(counter);
                if (analysis != null && analysis.length() > 0)
                    counterList.add(analysis);
            }
        }
        return counterList;
    }

    private static String counterText(CounterEntry counter) {
        StringBuilder builder = new StringBuilder();
        if (counter.getCounterLowerEdge() < minUnderCounterHeight) {
            counterIrregular(builder);
            builder.append("a altura livre sob o balcão é inferior a " + minUnderCounterHeight + " m");
        }

        if (counter.getCounterUpperEdge() < minUpperCounterHeight) {
            counterIrregular(builder);
            builder.append("o tampo do balcão está a uma altura do piso acabado inferior a " + minUpperCounterHeight + " m");
        } else if (counter.getCounterUpperEdge() > maxUpperCounterHeight) {
            counterIrregular(builder);
            builder.append("o tampo do balcão está a uma altura do piso acabado superior a " + maxUpperCounterHeight + " m");
        }

        if (counter.getCounterWidth() < minCounterWidth) {
            counterIrregular(builder);
            builder.append("a largura do balcão é inferior ao mínimo de " + minCounterWidth + " m");
        }

        if (counter.getCounterFreeWidth() < minCounterFreeWidth) {
            counterIrregular(builder);
            builder.append("a largura livre do balcão é inferior ao mínimo de " + minCounterFreeWidth + " m");
        }

        if (counter.getCounterFrontalApprox() < underCounterDepth) {
            counterIrregular(builder);
            builder.append("a profundidade livre sob o balcão é inferior ao mínimo de " + underCounterDepth + " m");
        }

        if (counter.getCounterObs() != null) {
            counterIrregular(builder);
            builder.append("as seguintes observações devem ser apontadas sobre o balcão em questão: ").append(counter.getCounterObs());
        }

        if (counter.getCounterPhoto() != null) {
            counterIrregular(builder);
            builder.append("Registros fotográficos: ").append(counter.getCounterPhoto());
        }

        if (builder.length() > 0)
            builder.replace(34, 35, counter.getCounterLocation());

        return builder.toString();
    }

    private static void counterIrregular(StringBuilder builder) {
        if (!irregularCounter) {
            irregularCounter = true;
            builder.append("Presença de balcão, localizado em x, com as seguintes irregularidades: ");
        } else
            builder.append(", ");

    }
}
