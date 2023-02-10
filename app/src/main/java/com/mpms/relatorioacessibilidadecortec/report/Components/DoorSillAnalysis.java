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

//    TODO - Trocar a análise para refletir as soleiras do banheiro
    public static String restSillVerification(RestroomEntry rest) {
        String irrRestSill = null;
//        if (rest.getEntranceDoorSill() > 0 && rest.getEntranceDoorSill() < 4) {
////            if (rest.getSillType() == 1) {
//
////            }
//            if (rest.getSillType() == 2) {
//                if (rest.getSillTypeObs() == null)
//                    irrRestSill = "a soleira é composta por um degrau de " + rest.getSillStepHeight() + " cm;";
//                else if (rest.getSillTypeObs() != null && rest.getSillTypeObs().length() > 0)
//                    irrRestSill = "a soleira é composta por um degrau de " + rest.getSillStepHeight() + " cm e possui as seguintes observações: "
//                            + rest.getSillTypeObs() + ";";
//            } else if (rest.getSillType() == 3) {
//                StringBuilder irregular = new StringBuilder();
//                if (rest.getSillSlopeWidth() < minSillSlopeWidth) {
//                    irregular.append("a largura da rampa na soleira do portão é inferior a " + minSillSlopeWidth + " m; ");
//                }

//                if (rest.getSillSlopeHeight() > highestRampHeight) {
//                    irregular.append("altura da rampa da soleira acima de " + highestRampHeight + " m;");
//                } else if (rest.getSillSlopeHeight() <= highestRampHeight && rest.getSillSlopeHeight() > medRampHeight) {
//                    if (rest.getSillSlopeAngle1() != null && rest.getSillSlopeAngle1() > minAngleRamp ||
//                            rest.getSillSlopeAngle2() != null && rest.getSillSlopeAngle2() > minAngleRamp ||
//                            rest.getSillSlopeAngle3() != null && rest.getSillSlopeAngle3() > minAngleRamp ||
//                            rest.getSillSlopeAngle4() != null && rest.getSillSlopeAngle4() > minAngleRamp) {
//                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + minAngleRamp + "%");
//                    }
//                } else if (rest.getSillSlopeHeight() <= medRampHeight && rest.getSillSlopeHeight() > lowestRampHeight) {
//                    if (rest.getSillSlopeAngle1() != null && rest.getSillSlopeAngle1() > medAngleRamp ||
//                            rest.getSillSlopeAngle2() != null && rest.getSillSlopeAngle2() > medAngleRamp ||
//                            rest.getSillSlopeAngle3() != null && rest.getSillSlopeAngle3() > medAngleRamp ||
//                            rest.getSillSlopeAngle4() != null && rest.getSillSlopeAngle4() > medAngleRamp) {
//                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
//                    }
//                } else if (rest.getSillSlopeHeight() <= lowestRampHeight) {
//                    if (rest.getSillSlopeAngle1() != null && rest.getSillSlopeAngle1() < medAngleRamp ||
//                            rest.getSillSlopeAngle2() != null && rest.getSillSlopeAngle2() < medAngleRamp ||
//                            rest.getSillSlopeAngle3() != null && rest.getSillSlopeAngle3() < medAngleRamp ||
//                            rest.getSillSlopeAngle4() != null && rest.getSillSlopeAngle4() < medAngleRamp) {
//                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + medAngleRamp + "%");
//                    } else if (rest.getSillSlopeAngle1() != null && rest.getSillSlopeAngle1() > maxAngleRamp ||
//                            rest.getSillSlopeAngle2() != null && rest.getSillSlopeAngle2() > maxAngleRamp ||
//                            rest.getSillSlopeAngle3() != null && rest.getSillSlopeAngle3() > maxAngleRamp ||
//                            rest.getSillSlopeAngle4() != null && rest.getSillSlopeAngle4() > maxAngleRamp) {
//                        irregular.append("inclinação da rampa da soleira acima do máximo permitido de " + maxAngleRamp + "%");
//                    }
//                }
//                if (rest.getSillTypeObs() != null && rest.getSillTypeObs().length() > 0)
//                    irregular.append("observações sobre a soleira da porta: ").append(rest.getSillTypeObs());

//                irrRestSill = irregular.toString();
//            }
//        }
        return irrRestSill;
    }



}
