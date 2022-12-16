package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class ParkPcdAnalysis implements StandardMeasurements {

    static boolean irregularPcd;

    public static List<String> pcdTextList(int parkID, List<ParkingLotPCDEntry> pList) {

        List<String> pcdList = new ArrayList<>();

        if (pList.size() > 0) {
            for (ParkingLotPCDEntry pcdEntry : pList) {
                irregularPcd = false;
                String analysis = null;
                if (pcdEntry.getParkID() == parkID)
                    analysis = pcdText(pcdEntry);
                if (analysis != null && analysis.length() > 0)
                    pcdList.add(analysis);
            }
        }
        return pcdList;
    }

    private static String pcdText(ParkingLotPCDEntry pcdEntry) {
        StringBuilder builder = new StringBuilder();
        if (pcdEntry.getHasVisualPcdVertSign() == 0) {
            pcdIrregular(builder);
            builder.append("a vaga não possui sinalização vertical");
        } else if (pcdEntry.getHasVisualPcdVertSign() == 1) {
//       Não achei tamanhos, verificar amanhã
        }
        if (pcdEntry.getVacancyPosition() == 0) {
            if (pcdEntry.getPcdVacancyLength() < minLengthVacParallelPCD) {
                pcdIrregular(builder);
                builder.append("a vaga possui comprimento inferior à " + minLengthVacParallelPCD + " m");
            }
            if (pcdEntry.getPcdVacancyWidth() < minWidthVac) {
                pcdIrregular(builder);
                builder.append("a vaga possui largura inferior à " + minWidthVac + " m");
            } else if (pcdEntry.getPcdVacancyWidth() > maxWidthVac) {
                pcdIrregular(builder);
                builder.append("a vaga possui largura superior à " + maxWidthVac + " m");
            }
        }
        else {
            if (pcdEntry.getPcdVacancyLength() < minWidthVacOthersPCD) {
                pcdIrregular(builder);
                builder.append("a vaga possui largura inferior à " + minWidthVacOthersPCD + " m");
            }
        }

        if (pcdEntry.getPcdVacancyLimitWidth() < minVacLimWidthPCD) {
            pcdIrregular(builder);
            builder.append("a vaga possui faixas delimitadoras com largura inferior à " + minVacLimWidthPCD + " m");
        }

        if (pcdEntry.getPcdVacancyObs() != null && pcdEntry.getPcdVacancyObs().length() > 0) {
            pcdIrregular(builder);
            builder.append("as seguintes observações devem ser feitas referente à vaga: ").append(pcdEntry.getPcdVacancyObs());
        }

        if (pcdEntry.getHasSecurityZone() == 0) {
            pcdIrregular(builder);
            builder.append("a vaga para PCD não possui faixa adicional de segurança");
        } else {
            if (pcdEntry.getSecurityZoneWidth() < minVacFreeSpace) {
                builder.append("a faixa adicional de segurança possui largura inferior à " + minVacFreeSpace + " m");
            }
        }
        if (pcdEntry.getSecurityZoneObs() != null && pcdEntry.getSecurityZoneObs().length() > 0) {
            pcdIrregular(builder);
            builder.append("as seguintes observações devem ser feitas referente à faixa adicional de segurança: ").append(pcdEntry.getSecurityZoneObs());
        }

        if (pcdEntry.getHasPcdSia() == 0) {
            pcdIrregular(builder);
            builder.append("a vaga não possui SIA");
        }
        else {
            if (pcdEntry.getPcdSiaLength() != siaDimensions || pcdEntry.getPcdSiaWidth() != siaDimensions) {
                pcdIrregular(builder);
                builder.append("as dimensões da SIA não seguem o padrão da norma de ser um quadrado com " + siaDimensions + " m de lateral");
            }
        }
        if (pcdEntry.getPcdSiaObs() != null && pcdEntry.getPcdSiaObs().length() > 0) {
            pcdIrregular(builder);
            builder.append("as seguintes observações devem ser feitas referents à SIA: ").append(pcdEntry.getPcdSiaObs());
        }

        if (irregularPcd && builder.length() != 0)
            builder.replace(41, 42, String.valueOf(pcdEntry.getPcdVacancyLocal()));

        return builder.toString();
    }

    private static void pcdIrregular(StringBuilder builder) {
        if (!irregularPcd) {
            irregularPcd = true;
            builder.append("Presença de vaga para PCD, localizada em x, com as seguintes irregularidades: "); //41, 42
        } else
            builder.append(", ");
    }
}
