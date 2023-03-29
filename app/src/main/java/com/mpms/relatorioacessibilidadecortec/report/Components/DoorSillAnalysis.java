package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

public class DoorSillAnalysis implements StandardMeasurements {

    public static String doorSillVerification(DoorEntry door) {
        String irrDoorSill = null;
        if (door.getDoorSillType() > 0 && door.getDoorSillType() < 4) {
            if (door.getDoorSillType() == 1) {
                if (door.getHasSillIncl() == 0) {
                    irrDoorSill = "o desnível da soleira não é vencido por rampa";
                } else {
                    if (door.getInclAngle1() != null && door.getInclAngle1() > maxSlopePerc ||
                            door.getInclAngle2() != null && door.getInclAngle2() > maxSlopePerc ||
                            door.getInclAngle3() != null && door.getInclAngle3() > maxSlopePerc ||
                            door.getInclAngle4() != null && door.getInclAngle4() > maxSlopePerc) {
                        irrDoorSill = "desnível possui altura permitida para norma para compor rota acessível, entretnão não se encontra vencido por rampa com inclinação " +
                                "máxima de " + maxSlopePerc + "%";
                    }
                }
            }
            else if (door.getDoorSillType() == 2) {
                if (door.getDoorSillObs() == null)
                    irrDoorSill = "a soleira é composta por um degrau de " + door.getStepHeight() + " cm;";
                else if (door.getDoorSillObs() != null && door.getDoorSillObs().length() > 0)
                    irrDoorSill = "a soleira é composta por um degrau de " + door.getStepHeight() + " cm e possui as seguintes observações: "
                            + door.getDoorSillObs() + ";";
            } else if (door.getDoorSillType() == 3) {
                StringBuilder irregular = new StringBuilder();
                if (door.getSlopeWidth() < minSillSlopeWidth) {
                    irregular.append("a largura da rampa na soleira do portão é inferior a " + minSillSlopeWidth + " m; ");
                }

                if (door.getSlopeHeight() > highestRampHeight) {
                    irregular.append("altura da rampa da soleira acima de " + highestRampHeight + " m;");
                } else if (door.getSlopeHeight() <= highestRampHeight && door.getSlopeHeight() > medRampHeight) {
                    if (door.getSlopeAngle1() != null && door.getSlopeAngle1() > minAngleRamp ||
                            door.getSlopeAngle2() != null && door.getSlopeAngle2() > minAngleRamp ||
                            door.getSlopeAngle3() != null && door.getSlopeAngle3() > minAngleRamp ||
                            door.getSlopeAngle4() != null && door.getSlopeAngle4() > minAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + minAngleRamp + "%");
                    }
                } else if (door.getSlopeHeight() <= medRampHeight && door.getSlopeHeight() > lowestRampHeight) {
                    if (door.getSlopeAngle1() != null && door.getSlopeAngle1() > medAngleRamp ||
                            door.getSlopeAngle2() != null && door.getSlopeAngle2() > medAngleRamp ||
                            door.getSlopeAngle3() != null && door.getSlopeAngle3() > medAngleRamp ||
                            door.getSlopeAngle4() != null && door.getSlopeAngle4() > medAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                    }
                } else if (door.getSlopeHeight() <= lowestRampHeight) {
                    if (door.getSlopeAngle1() != null && door.getSlopeAngle1() < medAngleRamp ||
                            door.getSlopeAngle2() != null && door.getSlopeAngle2() < medAngleRamp ||
                            door.getSlopeAngle3() != null && door.getSlopeAngle3() < medAngleRamp ||
                            door.getSlopeAngle4() != null && door.getSlopeAngle4() < medAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                    } else if (door.getSlopeAngle1() != null && door.getSlopeAngle1() > maxAngleRamp ||
                            door.getSlopeAngle2() != null && door.getSlopeAngle2() > maxAngleRamp ||
                            door.getSlopeAngle3() != null && door.getSlopeAngle3() > maxAngleRamp ||
                            door.getSlopeAngle4() != null && door.getSlopeAngle4() > maxAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + maxAngleRamp + "%");
                    }
                }
                if (door.getDoorSillObs() != null && door.getDoorSillObs().length() > 0)
                    irregular.append("observações sobre a soleira da porta: ").append(door.getDoorSillObs());

                irrDoorSill = irregular.toString();
            }
        }
        return irrDoorSill;
    }

}
