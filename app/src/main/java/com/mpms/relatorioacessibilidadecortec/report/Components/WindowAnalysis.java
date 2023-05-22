package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class WindowAnalysis implements StandardMeasurements {

    static boolean irregularWindow;

    public static List<String> winTextList(int roomID, List<WindowEntry> winList) {

        List<String> winListing = new ArrayList<>();

        for (WindowEntry window : winList) {
            irregularWindow = false;
            String analysis = null;
            if (window.getRoomID() == roomID)
                analysis = winText(window);
            if (analysis != null && analysis.length() > 0)
                winListing.add(analysis);
        }
        return winListing;
    }

    public static List<String> circWinList(int circID, List<WindowEntry> winList) {

        List<String> winListing = new ArrayList<>();

        for (WindowEntry window : winList) {
            irregularWindow = false;
            String analysis = null;
            if (window.getCircID() == circID)
                analysis = winText(window);
            if (analysis != null && analysis.length() > 0)
                winListing.add(analysis);
        }
        return winListing;
    }


    private static String winText(WindowEntry window) {
        StringBuilder builder = new StringBuilder();
        if (window.getComHeight1() > maxWinHeight) {
            windowIrregular(builder);
            builder.append(" a altura do comando da janela, do tipo ").append(window.getComType1()).append(", é superior a " + maxWinHeight + " m");
        } else if (window.getComHeight1() < minWinHeight) {
            windowIrregular(builder);
            builder.append(" a altura do comando da janela, do tipo ").append(window.getComType1()).append(", é inferior a " + minWinHeight + " m");
        }

        if (window.getComHeight2() != null && window.getComHeight2() > maxWinHeight) {
            windowIrregular(builder);
            builder.append(" a altura do comando da janela, do tipo ").append(window.getComType2()).append(", é superior a " + maxWinHeight + " m");
        } else if (window.getComHeight2() != null && window.getComHeight2() < minWinHeight) {
            windowIrregular(builder);
            builder.append(" a altura do comando da janela, do tipo ").append(window.getComType2()).append(", é inferior a " + minWinHeight + " m");
        }

        if (window.getComHeight3() != null && window.getComHeight3() > maxWinHeight) {
            windowIrregular(builder);
            builder.append(" a altura do comando da janela, do tipo ").append(window.getComType3()).append(", é superior a " + maxWinHeight + " m");
        } else if (window.getComHeight3() != null && window.getComHeight3() < minWinHeight) {
            windowIrregular(builder);
            builder.append(" a altura do comando da janela, do tipo ").append(window.getComType3()).append(", é inferior a " + minWinHeight + " m");
        }

        if (window.getWindowObs() != null && window.getWindowObs().length() > 0) {
            if (builder.length() > 0) {
                builder.append(" e as seguintes observações devem ser apontadas sobre a janela: ").append(window.getWindowObs());
            }
            else
                windowIrregular(builder);
                builder.append(" observações a ser apontadas sobre a janela: ").append(window.getWindowObs());
        }

        if (window.getWindowPhoto() != null) {
            windowIrregular(builder);
            builder.append("Registros fotográficos: ").append(window.getWindowPhoto());
        }

        if (builder.length() > 0) {
            if (window.getWindowLocation().equals("única"))
                builder.replace(7, 22, window.getWindowLocation());
            else
                builder.replace(21, 22, window.getWindowLocation());
        }

        return builder.toString();
    }

    private static void windowIrregular(StringBuilder builder) {
        if (!irregularWindow) {
            irregularWindow = true;
            builder.append("Janela localizada em x, com as seguintes irregularidades:");
        } else
            builder.append(",");
    }
}
