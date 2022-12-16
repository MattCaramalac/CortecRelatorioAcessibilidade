package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class TableAnalysis implements StandardMeasurements {

    static boolean irregularTable;

    public static List<String> tableTextList(int roomID, List<TableEntry> tableList) {

        List<String> tableListing = new ArrayList<>();

        if (tableList.size() > 0) {
            for (TableEntry table : tableList) {
                irregularTable = false;
                String analysis = null;
                if (table.getRoomID() == roomID)
                    analysis = tableText(table);
                if (analysis != null && analysis.length() > 0)
                    tableListing.add(analysis);
            }
        }
        return tableListing;
    }

    private static String tableText(TableEntry table) {
        StringBuilder builder = new StringBuilder();
        if (table.getInferiorBorderHeight() < minUnderTableHeight) {
            tableIrregular(builder);
            builder.append("a altura livre sob o tampo da mesa é inferior a " + minUnderTableHeight + " m");
        }

        if (table.getSuperiorBorderHeight() < minUpperTableHeight) {
            tableIrregular(builder);
            builder.append("o tampo da mesa está a uma altura do piso acabado inferior a " + minUpperTableHeight + " m");
        } else if (table.getSuperiorBorderHeight() > maxUpperTableHeight) {
            tableIrregular(builder);
            builder.append("o tampo da mesa está a uma altura do piso acabado superior a " + maxUpperTableHeight + " m");
        }

        if (table.getTableFrontalApprox() < underTableDepth) {
            tableIrregular(builder);
            builder.append("a profundidade livre mínima sob a mesa é inferior ao mínimo de " + underTableDepth + " m");
        }

        if (table.getTableWidth() < minTableWidth) {
            tableIrregular(builder);
            builder.append("a largura da mesa é inferior ao mínimo de " + minTableWidth + " m");
        }

        if (table.getTableFreeWidth() < minTableFreeWidth) {
            tableIrregular(builder);
            builder.append("a largura livre da mesa é inferior ao mínimo de " + minTableFreeWidth + " m");
        }

        if (table.getTableObs() != null && table.getTableObs() .length() > 0) {
            tableIrregular(builder);
            builder.append("as seguintes observações devem ser apontadas sobre a mesa em questão: ").append(table.getTableObs());
        }

        return builder.toString();
    }

    private static void tableIrregular(StringBuilder builder) {
        if (!irregularTable) {
            irregularTable = true;
            builder.append("Presença de mesa com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
