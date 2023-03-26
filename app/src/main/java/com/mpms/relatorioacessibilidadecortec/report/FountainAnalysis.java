package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;

import java.util.ArrayList;
import java.util.List;

public class FountainAnalysis implements StandardMeasurements {

    private static int check;
    private static boolean irrFountain;

    public static void helpFountainVerification(List<BlockSpaceEntry> blockList, List<WaterFountainEntry> waterList) {

        int fHelp = 0;

        for (BlockSpaceEntry block : blockList) {
            int blockID = block.getBlockSpaceID();

            for (WaterFountainEntry fountain : waterList) {
                check = 0;
                AmbientAnalysis.err = false;
                List<String> waterIrr = new ArrayList<>();
                if (fountain.getBlockID() == blockID)
                    waterIrr = fountainIrregularities(fountain);

                String fType = fountainTyping(fountain);

//                Área de Apoio
                if (block.getBlockSpaceType() == 2) {
                    if (check > 0)
                        AmbientAnalysis.checkHelpAreaHeader();

                    if (AmbientAnalysis.err) {
                        AmbientAnalysis.helpFountainList.add("Bebedouro, " + fType + " e localizado em " + fountain.getFountainLocation()
                                + ", com as seguintes irregularidades: ");
                        AmbientAnalysis.helpFountainIrregular.put(fHelp, waterIrr);
                    }
                }
            }
        }
    }

    public static void blockFountainVerification(Integer blockID, List<WaterFountainEntry> waterList) {

        int fBlock = 0;

        for (WaterFountainEntry fountain : waterList) {
            check = 0;
            AmbientAnalysis.err = false;
            List<String> fIrr = new ArrayList<>();
            if (fountain.getBlockID() == blockID)
                fIrr = fountainIrregularities(fountain);

            if (check > 0) {
                String fType = fountainTyping(fountain);
                AmbientAnalysis.blockFountainList.add("Bebedouro, " + fType + " e localizado em " + fountain.getFountainLocation()
                        + ", com as seguintes irregularidades: ");
                AmbientAnalysis.blockFountainIrregular.put(fBlock, fIrr);
                fBlock++;
            }
        }
    }

    public static List<String> roomFountainVerification(int roomID, List<WaterFountainEntry> waterList) {

        List<String> rFountList = new ArrayList<>();

        for (WaterFountainEntry fountain : waterList) {
            List<String> analysisList = null;
            String analysis = null;
            if (fountain.getRoomID() == roomID)
                analysisList = fountainIrregularities(fountain);

            StringBuilder fBuild = new StringBuilder();
            if (analysisList != null && analysisList.size() > 0) {
                String fType = fountainTyping(fountain);
                fBuild.append("Bebeouro, ").append(fType).append(" e localizado em ").append(fountain.getFountainLocation());
                if (analysisList.size() > 1) {
                    fBuild.append(", com as seguintes irregularidades: ");
                    for (int i = 0; i < analysisList.size(); i++) {
                        String text = analysisList.get(i);
                        fBuild.append(text);
                        if (i == analysisList.size() - 1)
                            fBuild.append(";");
                        else
                            fBuild.append(", ");
                    }
                } else {
                    fBuild.append(", com a seguinte irregularidade: ");
                    fBuild.append(analysisList.get(0)).append(";");
                }
                analysis = fBuild.toString();
            }

            if (analysis != null && analysis.length() > 0)
                rFountList.add(analysis);
        }
        return rFountList;
    }


    public static List<String> fountainIrregularities(WaterFountainEntry fountain) {
        List<String> fIrr = new ArrayList<>();

        if (fountain.getFountainType() == 0) {
            if (fountain.getHasSpoutsDifferentHeights() == 0) {
                check++;
                if (fountain.getHighestSpoutHeight() == lowestSpout)
                    fIrr.add("o bebedouro apresenta somente a bica de altura mais baixa, faltando a bica com altura entre" + minHighestSpout +
                            " m e " + maxHighestSpout + " m em relação ao piso acabado");
                else if (fountain.getHighestSpoutHeight() >= minHighestSpout && fountain.getHighestSpoutHeight() <= maxHighestSpout)
                    fIrr.add("o bebedouro apresenta somente a bica de altura mais elevada, faltando a bica com altura de " + lowestSpout + " m em relação ao piso acabado");
                else
                    fIrr.add("o bebedouro apresenta somente uma bica, cuja altura é distinta do permitido em norma tanto para as bicas mais altas quanto para as mais baixas");
            } else {
                if (fountain.getHighestSpoutHeight() >= minHighestSpout && fountain.getHighestSpoutHeight() <= maxHighestSpout && fountain.getLowestSpoutHeight() != lowestSpout) {
                    check++;
                    fIrr.add("o bebedouro apresenta duas ou mais bicas, porém a bica mais baixa tem altura diferente de " + lowestSpout + " m");
                } else if (fountain.getHighestSpoutHeight() < minHighestSpout && fountain.getLowestSpoutHeight() == lowestSpout) {
                    check++;
                    fIrr.add("o bebedouro apresenta duas ou mais bicas, porém a bica mais alta tem altura inferior à " + minHighestSpout + " m");
                } else if (fountain.getHighestSpoutHeight() > maxHighestSpout && fountain.getLowestSpoutHeight() == lowestSpout) {
                    check++;
                    fIrr.add("o bebedouro apresenta duas ou mais bicas, porém a bica mais alta tem altura superior à " + maxHighestSpout + " m");
                } else if ((fountain.getHighestSpoutHeight() < minHighestSpout || fountain.getHighestSpoutHeight() > maxHighestSpout) && fountain.getLowestSpoutHeight() != lowestSpout) {
                    check++;
                    fIrr.add("o bebedouro apresenta duas ou mais bicas, porém todas apresentam alturas distintas dos valores estipulados em norma");
                }
            }

            if (fountain.getAllowFrontApprox() == 0) {
                check++;
                fIrr.add("o bebedouro não permite aproximação frontal");
            } else {
                if (fountain.getFrontalApproxLowestSpout() < lowestSpoutFreeHeight) {
                    check++;
                    fIrr.add("a altura livre inferior da bica mais baixa é menor que " + lowestSpoutFreeHeight + " m");
                }
                if (fountain.getFrontalApproxDepth() < underTableDepth) {
                    check++;
                    fIrr.add("a profundidade da aproximação frontal é inferior à " + underTableDepth + " m");
                }
            }
        } else {
            if (fountain.getAllowSideApprox() == 0) {
                check++;
                if (fountain.getSideApproxObs() != null && fountain.getSideApproxObs().length() > 0)
                    fIrr.add("o bebedouro não permite a aproximação lateral pelos seguintes motivos: " + fountain.getSideApproxObs());
                else
                    fIrr.add("o bebedouro não permite a aproximação lateral");
            }
//            TODO - adicionar campo para altura do acionamento do bebedouro

            if (fountain.getHasCupHolder() == 0) {
                check++;
                fIrr.add("não há presença de porta-copos junto ao bebedouro");
            } else {
                if (fountain.getCupHolderHeight() < oFountMinActionHeight) {
                    check++;
                    fIrr.add("a altura de instalação do porta-copos é inferior à " + oFountMinActionHeight + " m");
                } else if (fountain.getCupHolderHeight() > oFountMaxActionHeight) {
                    check++;
                    fIrr.add("a altura de instalação do porta-copos é superior à " + oFountMaxActionHeight + " m");
                }
            }
        }

        if (fountain.getFountainObs() != null && fountain.getFountainObs().length() > 0) {
            check++;
            fIrr.add("as seguintes observações podem ser feitas sobre o bebedouro: " + fountain.getFountainObs());
        }

        if (fountain.getFountainPhoto() != null) {
            check++;
            fIrr.add("Registros fotográficos Bebedouro: " + fountain.getFountainPhoto());
        }

        return fIrr;
    }

    public static String fountainTyping(WaterFountainEntry fountain) {
        if (fountain.getFountainType() == 0)
            return "do tipo bica";
        else
            return "do tipo " + fountain.getFountainTypeObs();
    }
}
