package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
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

    public static String restSillVerification(RestroomEntry rest) {
        String irrRestSill = null;
        if (rest.getSillType() > 0 && rest.getSillType() < 4) {
            if (rest.getSillType() == 1) {

            }
            if (rest.getSillType() == 2) {
                if (rest.getSillTypeObs() == null)
                    irrRestSill = "a soleira é composta por um degrau de " + rest.getSillStepHeight() + " cm;";
                else if (rest.getSillTypeObs() != null && rest.getSillTypeObs().length() > 0)
                    irrRestSill = "a soleira é composta por um degrau de " + rest.getSillStepHeight() + " cm e possui as seguintes observações: "
                            + rest.getSillTypeObs() + ";";
            } else if (rest.getSillType() == 3) {
                StringBuilder irregular = new StringBuilder();
                if (rest.getSillSlopeWidth() < minSillSlopeWidth) {
                    irregular.append("a largura da rampa na soleira do portão é inferior a " + minSillSlopeWidth + " m; ");
                }

                if (rest.getSillSlopeHeight() > highestRampHeight) {
                    irregular.append("altura da rampa da soleira acima de " + highestRampHeight + " m;");
                } else if (rest.getSillSlopeHeight() <= highestRampHeight && rest.getSillSlopeHeight() > medRampHeight) {
                    if (rest.getSillSlopeAngle1() != null && rest.getSillSlopeAngle1() > minAngleRamp ||
                            rest.getSillSlopeAngle2() != null && rest.getSillSlopeAngle2() > minAngleRamp ||
                            rest.getSillSlopeAngle3() != null && rest.getSillSlopeAngle3() > minAngleRamp ||
                            rest.getSillSlopeAngle4() != null && rest.getSillSlopeAngle4() > minAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + minAngleRamp + "%");
                    }
                } else if (rest.getSillSlopeHeight() <= medRampHeight && rest.getSillSlopeHeight() > lowestRampHeight) {
                    if (rest.getSillSlopeAngle1() != null && rest.getSillSlopeAngle1() > medAngleRamp ||
                            rest.getSillSlopeAngle2() != null && rest.getSillSlopeAngle2() > medAngleRamp ||
                            rest.getSillSlopeAngle3() != null && rest.getSillSlopeAngle3() > medAngleRamp ||
                            rest.getSillSlopeAngle4() != null && rest.getSillSlopeAngle4() > medAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                    }
                } else if (rest.getSillSlopeHeight() <= lowestRampHeight) {
                    if (rest.getSillSlopeAngle1() != null && rest.getSillSlopeAngle1() < medAngleRamp ||
                            rest.getSillSlopeAngle2() != null && rest.getSillSlopeAngle2() < medAngleRamp ||
                            rest.getSillSlopeAngle3() != null && rest.getSillSlopeAngle3() < medAngleRamp ||
                            rest.getSillSlopeAngle4() != null && rest.getSillSlopeAngle4() < medAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
                    } else if (rest.getSillSlopeAngle1() != null && rest.getSillSlopeAngle1() > maxAngleRamp ||
                            rest.getSillSlopeAngle2() != null && rest.getSillSlopeAngle2() > maxAngleRamp ||
                            rest.getSillSlopeAngle3() != null && rest.getSillSlopeAngle3() > maxAngleRamp ||
                            rest.getSillSlopeAngle4() != null && rest.getSillSlopeAngle4() > maxAngleRamp) {
                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + maxAngleRamp + "%");
                    }
                }
                if (rest.getSillTypeObs() != null && rest.getSillTypeObs().length() > 0)
                    irregular.append("observações sobre a soleira da porta: ").append(rest.getSillTypeObs());

                irrRestSill = irregular.toString();
            }
        }
        return irrRestSill;
    }



}
