package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

public class DoorSillAnalysis implements StandardMeasurements {

    public static String doorSillVerification(DoorEntry door) {
        String irrDoorSill = null;
        if (door.getDoorSillType() > 0 && door.getDoorSillType() < 4) {
            if (door.getDoorSillType() == 1) {

            }
            if (door.getDoorSillType() == 2) {
                if (door.getDoorSillObs() == null)
                    irrDoorSill = "a soleira é composta por um degrau de " + door.getSillStepHeight() + " cm;";
                else if (door.getDoorSillObs() != null && door.getDoorSillObs().length() > 0)
                    irrDoorSill = "a soleira é composta por um degrau de " + door.getSillStepHeight() + " cm e possui as seguintes observações: "
                            + door.getDoorSillObs() + ";";
            } else if (door.getDoorSillType() == 3) {
                StringBuilder irregular = new StringBuilder();
                if (door.getSillSlopeWidth() < minSillSlopeWidth) {
                    irregular.append("a largura da rampa na soleira do portão é inferior a " + minSillSlopeWidth + " m; ");
                }

                if (door.getSillSlopeHeight() > highestRampHeight) {
                    irregular.append("altura da rampa da soleira acima de " + highestRampHeight + " m;");
                } else if (door.getSillSlopeHeight() <= highestRampHeight && door.getSillSlopeHeight() > medRampHeight) {
                    if (door.getSillSlopeAngle1() != null && door.getSillSlopeAngle1() > minAngleRamp ||
                            door.getSillSlopeAngle2() != null && door.getSillSlopeAngle2() > minAngleRamp ||
                            door.getSillSlopeAngle3() != null && door.getSillSlopeAngle3() > minAngleRamp ||
                            door.getSillSlopeAngle4() != null && door.getSillSlopeAngle4() > minAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + minAngleRamp + "%");
                    }
                } else if (door.getSillSlopeHeight() <= medRampHeight && door.getSillSlopeHeight() > lowestRampHeight) {
                    if (door.getSillSlopeAngle1() != null && door.getSillSlopeAngle1() > medAngleRamp ||
                            door.getSillSlopeAngle2() != null && door.getSillSlopeAngle2() > medAngleRamp ||
                            door.getSillSlopeAngle3() != null && door.getSillSlopeAngle3() > medAngleRamp ||
                            door.getSillSlopeAngle4() != null && door.getSillSlopeAngle4() > medAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                    }
                } else if (door.getSillSlopeHeight() <= lowestRampHeight) {
                    if (door.getSillSlopeAngle1() != null && door.getSillSlopeAngle1() < medAngleRamp ||
                            door.getSillSlopeAngle2() != null && door.getSillSlopeAngle2() < medAngleRamp ||
                            door.getSillSlopeAngle3() != null && door.getSillSlopeAngle3() < medAngleRamp ||
                            door.getSillSlopeAngle4() != null && door.getSillSlopeAngle4() < medAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                    } else if (door.getSillSlopeAngle1() != null && door.getSillSlopeAngle1() > maxAngleRamp ||
                            door.getSillSlopeAngle2() != null && door.getSillSlopeAngle2() > maxAngleRamp ||
                            door.getSillSlopeAngle3() != null && door.getSillSlopeAngle3() > maxAngleRamp ||
                            door.getSillSlopeAngle4() != null && door.getSillSlopeAngle4() > maxAngleRamp) {
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
