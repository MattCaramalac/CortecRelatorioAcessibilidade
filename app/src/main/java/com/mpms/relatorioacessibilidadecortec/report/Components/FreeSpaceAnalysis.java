package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class FreeSpaceAnalysis implements StandardMeasurements {

    static boolean irregularSpace;

    public static List<String> spaceTextList(int roomID, List<FreeSpaceEntry> sList) {

        List<String> spaceList = new ArrayList<>();

        if (sList.size() > 0) {
            for (FreeSpaceEntry space : sList) {
                irregularSpace = false;
                String analysis = null;
                if (space.getRoomID() != null && space.getRoomID() == roomID)
                    analysis = spaceText(space);
                if (analysis != null && analysis.length() > 0)
                    spaceList.add(analysis);
            }
        }
        return spaceList;
    }

    public static List<String> restSpaceTextList(int restID, List<FreeSpaceEntry> sList) {

        List<String> spaceList = new ArrayList<>();

        if (sList.size() > 0) {
            for (FreeSpaceEntry space : sList) {
                irregularSpace = false;
                String analysis = null;
                if (space.getRestID() != null && space.getRestID() == restID)
                    analysis = spaceText(space);
                if (analysis != null && analysis.length() > 0)
                    spaceList.add(analysis);
            }
        }
        return spaceList;
    }

    public static List<String> circSpaceTextList(int circID, List<FreeSpaceEntry> sList) {

        List<String> spaceList = new ArrayList<>();

        if (sList.size() > 0) {
            for (FreeSpaceEntry space : sList) {
                irregularSpace = false;
                String analysis = null;
                if (space.getCircID() != null && space.getCircID() == circID)
                    analysis = spaceText(space);
                if (analysis != null && analysis.length() > 0)
                    spaceList.add(analysis);
            }
        }
        return spaceList;
    }

    private static String spaceText(FreeSpaceEntry space) {
        StringBuilder builder = new StringBuilder();
        if (space.getObstacleWidth() <= limitWidthObstacle && space.getFrSpaceWidth() < lowerFreeWidth) {
            spaceIrregular(builder);
            builder.append("a largura da faixa livre é inferior a " + lowerFreeWidth + " m na presença de obstáculos de até " + limitWidthObstacle + "m de largura");
        } else if (space.getObstacleWidth() > limitWidthObstacle && space.getFrSpaceWidth() < higherFreeWidth) {
            spaceIrregular(builder);
            builder.append("a largura da faixa livre é inferior a " + higherFreeWidth + " m na presença de obstáculos acima de " + limitWidthObstacle + "m de largura");
        }

        if (space.getFrSpaceObs() != null) {
            spaceIrregular(builder);
            builder.append("as seguintes observações devem ser apontadas sobre a faixa livre em questão: ").append(space.getFrSpaceObs());
        }

        if (space.getFrSpacePhoto() != null) {
            spaceIrregular(builder);
            builder.append("Registros fotográficos: ").append(space.getFrSpacePhoto());
        }

        if (builder.length() > 0)
            builder.replace(39, 40, space.getFrSpaceLocation());

        return builder.toString();
    }

    private static void spaceIrregular(StringBuilder builder) {
        if (!irregularSpace) {
            irregularSpace = true;
            builder.append("Presença de faixa livre, localizada em x, com as seguintes irregularidades: ");
        } else
            builder.append(", ");

    }
}
