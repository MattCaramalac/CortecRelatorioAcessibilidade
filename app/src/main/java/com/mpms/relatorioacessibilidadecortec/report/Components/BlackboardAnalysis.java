package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class BlackboardAnalysis implements StandardMeasurements {

    static boolean irregularBoard;

    public static List<String> boardTextList(int roomID, List<BlackboardEntry> bList) {

        List<String> boardList = new ArrayList<>();

        if (bList.size() > 0) {
            for (BlackboardEntry board : bList) {
                irregularBoard = false;
                String analysis = null;
                if (board.getRoomID() != null && board.getRoomID() == roomID)
                    analysis = boardText(board);
                if (analysis != null && analysis.length() > 0)
                    boardList.add(analysis);
            }
        }
        return boardList;
    }

    public static List<String> circBoardList(int circID, List<BlackboardEntry> bList) {

        List<String> boardList = new ArrayList<>();

        if (bList.size() > 0) {
            for (BlackboardEntry board : bList) {
                irregularBoard = false;
                String analysis = null;
                if (board.getCircID() != null && board.getCircID() == circID)
                    analysis = boardText(board);
                if (analysis != null && analysis.length() > 0)
                    boardList.add(analysis);
            }
        }
        return boardList;
    }

    private static String boardText(BlackboardEntry board) {
        StringBuilder builder = new StringBuilder();
        if (board.getInfBorderHeight() > maxBlackboardHeight) {
            boardIrregular(builder);
            builder.append("a altura da borda inferior da lousa é maior que " + maxBlackboardHeight + " m");
        }

        if (board.getBoardObs() != null) {
            boardIrregular(builder);
            builder.append("as seguintes observações devem ser apontadas sobre a lousa em questão: ").append(board.getBoardObs());
        }

        if (board.getBoardPhoto() != null) {
            boardIrregular(builder);
            builder.append("Registros fotográficos: ").append(board.getBoardPhoto());
        }

        if (builder.length() > 0) {
            if (board.getBoardLocation().equals("Única"))
                builder.replace(13, 31, " única ");
            else
                builder.replace(29, 30, board.getBoardLocation());


            if (board.getBoardType() == 0)
                builder.replace(12, 13, "lousa");
            else
                builder.replace(12, 13, "mural");
        }

        return builder.toString();
    }

    private static void boardIrregular(StringBuilder builder) {
        if (!irregularBoard) {
            irregularBoard = true;
            builder.append("Presença de x, localizada em y, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
