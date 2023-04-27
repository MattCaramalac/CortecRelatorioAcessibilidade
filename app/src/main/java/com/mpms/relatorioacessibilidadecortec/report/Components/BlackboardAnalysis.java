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
                if (board.getRoomID() == roomID)
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
                if (board.getCircID() == circID)
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

        if (builder.length() > 0)
            builder.replace(33, 34, board.getBoardLocation());

        return builder.toString();
    }

    private static void boardIrregular(StringBuilder builder) {
        if (!irregularBoard) {
            irregularBoard = true;
            builder.append("Presença de lousa, localizada em x, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
