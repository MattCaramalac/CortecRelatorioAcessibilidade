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
                if (table.getRoomID() != null && table.getRoomID() == roomID)
                    analysis = tableText(table);
                if (analysis != null && analysis.length() > 0)
                    tableListing.add(analysis);
            }
        }
        return tableListing;
    }

    public static List<String> circTableList(int circID, List<TableEntry> tableList) {

        List<String> tableListing = new ArrayList<>();

        if (tableList.size() > 0) {
            for (TableEntry table : tableList) {
                irregularTable = false;
                String analysis = null;
                if (table.getCircID() != null && table.getCircID() == circID)
                    analysis = tableText(table);
                if (analysis != null && analysis.length() > 0)
                    tableListing.add(analysis);
            }
        }
        return tableListing;
    }

    private static String tableText(TableEntry table) {
        StringBuilder builder = new StringBuilder();

        /*
        Atualmente, será considerado que mesas do tipo "infantil" não gerarão erros no cadastro de mesas pela ausência de determinação
        na norma de alturas compatíveis. Será feito posteriormente análise dos materiais do MEC para adaptações da regra, garantindo
        que as mesas infantis também seguirão as alturas estipuladas pelo Ministério
         */
        if (table.getTableSize() == 1) {

            if (table.getSuperiorBorderHeight() < minUpperTableHeight) {
                tableIrregular(builder);
                builder.append("a superfície superior do tampo da mesa está a uma altura do piso acabado inferior a " + minUpperTableHeight + " m");
            } else if (table.getSuperiorBorderHeight() > maxUpperTableHeight) {
                tableIrregular(builder);
                builder.append("a superfície superior do tampo da mesa está a uma altura do piso acabado superior a " + maxUpperTableHeight + " m");
            }

            if (table.getInferiorBorderHeight() < minUnderTableHeight) {
                tableIrregular(builder);
                builder.append("a altura livre sob o tampo da mesa é inferior a " + minUnderTableHeight + " m");
            }


            if (table.getTableWidth() < minTableWidth) {
                tableIrregular(builder);
                builder.append("a largura da mesa é inferior ao mínimo de " + minTableWidth + " m");
            }

            if (table.getTableFreeWidth() < minTableFreeWidth) {
                tableIrregular(builder);
                builder.append("a largura livre da mesa é inferior ao mínimo de " + minTableFreeWidth + " m");
            }

            if (table.getTableFrontalApprox() < underTableDepth) {
                tableIrregular(builder);
                builder.append("o comprimento da área de aproximação frontal sob a mesa é inferior ao mínimo de " + underTableDepth + " m");
            }

        }


        if (table.getTableObs() != null && table.getTableObs() .length() > 0) {
            tableIrregular(builder);
            builder.append("as seguintes observações devem ser apontadas sobre a mesa em questão: ").append(table.getTableObs());
        }

        if (table.getRoomType() == 6) {
            StringBuilder tableDesc = new StringBuilder();
            if (table.getTableType() == 0)
                tableDesc.append(" de professor ");
            else
                tableDesc.append(" de aluno ");

            if (table.getTableDesc() != null)
                tableDesc.append(table.getTableDesc()).append(" ");

            if (builder.length() > 0)
                builder.replace(16, 18, tableDesc.toString());
        } else {
            if (builder.length() > 0 && table.getTableDesc() != null)
                builder.replace(17, 18, table.getTableDesc());
            else if (builder.length() > 0)
                builder.replace(16, 18, " ");
        }

        if (table.getTablePhoto() != null) {
            tableIrregular(builder);
            builder.append("Registros fotográficos: ").append(table.getTablePhoto());
        }

        if (builder.length() > 0)
            return builder.toString();
        else
            return null;
    }

    private static void tableIrregular(StringBuilder builder) {
        if (!irregularTable) {
            irregularTable = true;
            builder.append("Presença de mesa x com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
