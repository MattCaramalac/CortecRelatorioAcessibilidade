package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.PoolBenchEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class PoolBenchAnalysis implements StandardMeasurements {

    static boolean irrBench;

    public static List<String> poolBenchIrregular(int poolID, List<PoolBenchEntry> bList) {

        List<String> irrList = new ArrayList<>();

        if (bList.size() > 0) {
            for (PoolBenchEntry bench : bList) {
                irrBench = false;
                String analysis = null;
                if (bench.getPoolID() == poolID)
                    analysis = benchText(bench);
                if (analysis != null && analysis.length() > 0)
                    irrList.add(analysis);
            }
        }
        return irrList;
    }

    private static String benchText(PoolBenchEntry bench) {
        StringBuilder builder = new StringBuilder();
        if (bench.getBenchAccessible() == 0) {
            benchIrregular(builder);
            builder.append("a instalação do banco de transferência não garante uma área de aproximação e manobra de 360º sem interferir na área de circulação");
        }
        if (bench.getBenchExtension() < minBenchExtension) {
            benchIrregular(builder);
            builder.append("a extensão do banco de transferência é inferior a " + minBenchExtension + " m");
        }
        if (bench.getBenchHasLeftBar() == 0) {
            benchIrregular(builder);
            builder.append("o banco de transferência não possui barra de apoio à esquerda do usuário");
        } else {
            if (bench.getBenchLeftBarDiam() < minHandrailGrip) {
                benchIrregular(builder);
                builder.append("o diâmetro da barra de apoio à esquerda é inferior a " + minHandrailGrip + " mm");
            } else if (bench.getBenchLeftBarDiam() > maxHandrailGrip) {
                benchIrregular(builder);
                builder.append("o diâmetro da barra de apoio à esquerda é superior a " + maxHandrailGrip + " mm");
            }

            if (bench.getBenchLeftBarDist() < minDistHandrail) {
                benchIrregular(builder);
                builder.append("a distância entre a superfície do assento do banco de transferência e a barra de apoio à esquerda é inferior à " + minDistHandrail + " mm");
            }
        }
        if (bench.getBenchHasRightBar() == 0) {
            benchIrregular(builder);
            builder.append("o banco de transferência não possui barra de apoio à direita do usuário");
        } else {
            if (bench.getBenchRightBarDiam() < minHandrailGrip) {
                benchIrregular(builder);
                builder.append("o diâmetro da barra de apoio à direita é inferior a " + minHandrailGrip + " mm");
            } else if (bench.getBenchRightBarDiam() > maxHandrailGrip) {
                benchIrregular(builder);
                builder.append("o diâmetro da barra de apoio à direita é superior a " + maxHandrailGrip + " mm");
            }

            if (bench.getBenchRightBarDist() < minDistHandrail) {
                benchIrregular(builder);
                builder.append("a distância entre a superfície do assento do banco de transferência e a barra de apoio à direita é inferior à " + minDistHandrail + " mm");
            }
        }

        if (bench.getBenchMeasureA() != null && bench.getBenchMeasureA() < minBenchBarDistance) {
            benchIrregular(builder);
            builder.append("a distância entre as barras de apoio instaladas no banco é inferior a " + minBenchBarDistance + " m");
        } else if (bench.getBenchMeasureA() != null && bench.getBenchMeasureA() > maxBenchBarDistance) {
            benchIrregular(builder);
            builder.append("a distância entre as barras de apoio instaladas no banco é superior a " + maxBenchBarDistance + " m");
        }

        if (bench.getBenchMeasureB() != benchWidth) {
            benchIrregular(builder);
            builder.append("a largura do banco de transferência é diferente de " + benchWidth + " m");
        }
        if (bench.getBenchMeasureC() < minBenchHeight) {
            benchIrregular(builder);
            builder.append("a altura do banco de transferência é inferior a " + minBenchHeight + " m");
        } else if (bench.getBenchMeasureC() > maxBenchHeight) {
            benchIrregular(builder);
            builder.append("a altura do banco de transferência é superior a " + maxBenchHeight + " m");
        }
        if (bench.getBenchMeasureD() < minBenchFreeSpaceHeight) {
            benchIrregular(builder);
            builder.append("a altura do espaço livre abaixo do banco de transferência é inferior a " + minBenchFreeSpaceHeight + " m");
        }
        if (bench.getBenchMeasureE() < benchFreeSpaceDepth) {
            benchIrregular(builder);
            builder.append("a profundidade do espaço livre abaixo do banco de transferência é diferente de " + benchFreeSpaceDepth + " m");
        }
        if (bench.getBenchWaterLevel() > maxBenchWaterLevel) {
            benchIrregular(builder);
            builder.append("o nível da água se encontra a mais de " + maxBenchWaterLevel + " m abaixo do nível do assento do banco");
        }
        if (bench.getBenchObs() != null && bench.getBenchObs().length() > 0) {
            benchIrregular(builder);
            builder.append("as seguintes observações devem ser apontadas sobre o banco de transferência: ").append(bench.getBenchObs());
        }
        if (bench.getBenchPhoto() != null && bench.getBenchPhoto().length() > 0) {
            benchIrregular(builder);
            builder.append("registros fotográficos: ").append(bench.getBenchPhoto());
        }


        if (builder.length() > 0) {
            builder.replace(50, 51, bench.getBenchLocation());
            builder.append(";");
        }

        return builder.toString();

    }

    private static void benchIrregular(StringBuilder builder) {
        if (!irrBench) {
            irrBench = true;
            builder.append("Presença de banco de transferência, localizado em x, com as seguintes irregularidades: ");
        } else {
            builder.append(", ");
        }
    }

}
