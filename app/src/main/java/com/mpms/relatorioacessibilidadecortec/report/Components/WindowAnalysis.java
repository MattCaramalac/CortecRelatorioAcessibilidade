package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class WindowAnalysis implements StandardMeasurements {

    static boolean irregularWindow;

    public static List<String> winTextList (int roomID, List<WindowEntry> winList) {

        List<String> winListing = new ArrayList<>();

        if (winList.size() > 0) {
            for (WindowEntry window : winList) {
                irregularWindow = false;
                String analysis = null;
                if (window.getRoomID() == roomID)
                    analysis = winText(window);
                if (analysis != null && analysis.length() > 0)
                    winListing.add(analysis);
            }
        }
        return winListing;
    }

    private static String winText(WindowEntry window) {
        StringBuilder builder = new StringBuilder();
        if (window.getWindowCommandHeight() > maxWinHeight) {
            windowIrregular(builder);
            builder.append("a altura do comando da janela é superior a " + maxWinHeight + " m");
        } else if (window.getWindowCommandHeight() < minWinHeight) {
            windowIrregular(builder);
            builder.append("a altura do comando da janela é inferior a " + minWinHeight + " m");
        }

        if (window.getWindowObs() != null) {
            windowIrregular(builder);
            builder.append("as seguintes observações devem ser apontadas sobre a janela: ").append(window.getWindowObs());
        }

        if (builder.length() > 0)
            builder.replace(21, 22, window.getWindowLocation());
        return builder.toString();
    }

    private static void windowIrregular(StringBuilder builder) {
        if (!irregularWindow) {
            irregularWindow = true;
            builder.append("Janela localizada em x, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
