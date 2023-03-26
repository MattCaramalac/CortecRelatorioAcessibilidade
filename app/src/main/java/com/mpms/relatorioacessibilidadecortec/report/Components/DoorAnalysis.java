package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class DoorAnalysis implements StandardMeasurements {

    static boolean irregularDoor;

    public static List<String> doorVerification(int roomID, List<DoorEntry> doorList, List<DoorLockEntry> doorLockList) {

        List<String> doorText = new ArrayList<>();

        if (doorList.size() > 0) {
            for (DoorEntry door : doorList) {
                irregularDoor = false;
                String analysis = null;
                if (door.getRoomID() != null && door.getRoomID() == roomID)
                    analysis = doorTexts(door, doorLockList);
                if (analysis != null && analysis.length() > 0) {
                    doorText.add(analysis);
                }
            }
        }
        return doorText;
    }

    public static List<String> restBoxDoorVerification(int id, List<DoorEntry> doorList, boolean isBox) {

        List<String> doorText = new ArrayList<>();

        if (doorList.size() > 0) {
            for (DoorEntry door : doorList) {
                irregularDoor = false;
                String analysis = null;
                if (!isBox) {
                    if (door.getRestID() != null && door.getRestID() == id)
                        analysis = restBoxDoorText(door, false);
                } else {
                    if (door.getBoxID() != null && door.getBoxID() == id)
                        analysis = restBoxDoorText(door, true);
                }

                if (analysis != null && analysis.length() > 0) {
                    doorText.add(analysis);
                }

            }
        }
        return doorText;
    }

    public static String doorTexts(DoorEntry door, List<DoorLockEntry> dLockList) {
        StringBuilder doors = new StringBuilder();

        if (door.getDoorType() == 1) {
            if (door.getDoorWidth1() < freeSpaceGeneral && door.getDoorWidth2() < freeSpaceGeneral) {
                doorIrregular(doors);
                doors.append("as larguras dos vãos livres de ambas as folhas da porta são inferiores à " + freeSpaceGeneral + " m");
            }
        } else if (door.getDoorType() != 1 && door.getDoorWidth1() < freeSpaceGeneral) {
            doorIrregular(doors);
            doors.append("largura do vão livre da porta inferior à " + freeSpaceGeneral + " m");
        }

        if (door.getDoorHandleType() == 1) {
            doorIrregular(doors);
            doors.append("a maçaneta não é do tipo alavanca");
        }

        if (door.getDoorHandleHeight() < minDoorHandle) {
            doorIrregular(doors);
            if (door.getDoorHandleObs() != null && door.getDoorHandleObs().length() > 0)
                doors.append("a altura da maçaneta é inferior à " + minDoorHandle + "m e deve-se destacar as seguintes observações sobre a maçaneta: ")
                        .append(door.getDoorHandleObs());
            else
                doors.append("a altura da maçaneta é inferior à " + minDoorHandle + "m");
        } else if (door.getDoorHandleHeight() > maxDoorHandle) {
            doorIrregular(doors);
            if (door.getDoorHandleObs() != null && door.getDoorHandleObs().length() > 0)
                doors.append("a altura da maçaneta é superior à " + maxDoorHandle + "m e deve-se destacar as seguintes observações sobre a maçaneta: ")
                        .append(door.getDoorHandleObs());
            else
                doors.append("a altura da maçaneta é superior à " + maxDoorHandle + "m");
        } else {
            if (door.getDoorHandleObs() != null && door.getDoorHandleObs().length() > 0)
                doors.append("a altura da maçaneta está de acordo com a norma, porém deve-se destacar as seguintes observações sobre a maçaneta: ")
                        .append(door.getDoorHandleObs());
        }

        if (door.getDoorHasWindow() != null && door.getDoorHasWindow() == 1) {
            if (door.getDoorWinWidth() < doorWinMinWidth) {
                doorIrregular(doors);
                doors.append("a largura do visor da porta é inferior à " + doorWinMinWidth + " m");
            }

            if (door.getDoorWinInfHeight() < doorWinMinLowerHeight) {
                doorIrregular(doors);
                doors.append("a altura inferior do visor da porta está abaixo de " + doorWinMinLowerHeight + " m");
            } else if (door.getDoorWinInfHeight() > doorWinMaxLowerHeight) {
                doorIrregular(doors);
                doors.append("a altura inferior do visor da porta está acima de " + doorWinMaxLowerHeight + " m");
            }

            if (door.getDoorWinSupHeight() < doorWinMinUpperHeight) {
                doorIrregular(doors);
                doors.append("a altura superior do visor da porta está abaixo de " + doorWinMinUpperHeight + " m");
            }
        }

        if (door.getDoorWinObs() != null && door.getDoorWinObs().length() > 0) {
            doorIrregular(doors);
            doors.append("observações sobre o visor da porta: ").append(door.getDoorWinObs());
        }

        if (door.getDoorHasTactSign() == 0) {
            doorIrregular(doors);
            doors.append("a porta não possui sinalização tátil");
        } else if (door.getDoorHasTactSign() == 1) {
            if (door.getTactSignHeight() != null && door.getTactSignHeight() < doorTactSignMinHeight) {
                doorIrregular(doors);
                doors.append("a altura de instalação da sinalização tátil da porta é inferior à " + doorTactSignMinHeight + " m");
            } else if (door.getTactSignHeight() != null &&  door.getTactSignHeight() > doorTactSignMaxHeight) {
                doorIrregular(doors);
                doors.append("a altura de instalação da sinalização tátil da porta é superior à " + doorTactSignMaxHeight + " m");
            } else {
                if (door.getTactSignIncl() != null && door.getTactSignIncl() < doorTactSignMinAngle) {
                    doorIrregular(doors);
                    doors.append("o ângulo do plano inclinado da sinalização tátil é inferior à " + doorTactSignMinAngle + "º");
                } else if (door.getTactSignIncl() != null && door.getTactSignIncl() > doorTactSignMaxAngle) {
                    doorIrregular(doors);
                    doors.append("o ângulo do plano inclinado da sinalização tátil é superior à " + doorTactSignMaxAngle + "º");
                }
            }
        }

        if (door.getTactSignObs() != null && door.getTactSignObs().length() > 0)
            doors.append("as seguintes observações devem ser feitas sobre a sinalização tátil da porta: ").append(door.getTactSignObs());

        String doorSill = DoorSillAnalysis.doorSillVerification(door);
        if (doorSill != null && doorSill.length() > 0) {
            doorIrregular(doors);
            doors.append(doorSill);
        }

        if (dLockList != null) {
            String dLocks = DoorLockAnalysis.doorLockVerification(door.getDoorID(), dLockList);
            if (dLocks.length() > 0) {
                doorIrregular(doors);
                doors.append(dLocks);
            }
        }

        if (door.getDoorPhotos() != null) {
            doorIrregular(doors);
            doors.append("Registros fotográficos: ").append(door.getDoorPhotos());
        }

        if (doors.length() > 0)
            doors.replace(21, 22, door.getDoorLocation());
        return doors.toString();

    }

    public static String restBoxDoorText(DoorEntry door, boolean isBox) {
        StringBuilder restBoxDoors = new StringBuilder();

        if (door.getDoorWidth1() < freeSpaceGeneral) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("largura do vão livre da porta inferior à " + freeSpaceGeneral + " m");
        }

        if (door.getDoorHasPict() == 0) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("a porta não apresenta os pictogramas necessários");
        }
        if (door.getDoorPictObs() != null && door.getDoorPictObs().length() > 0) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("observações a serem apontadas sobre os pictogramas: ").append(door.getDoorPictObs());
        }

        if (door.getOpDirection() == 0) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("o sentido de abertura da porta é interno");
        }
        if (door.getOpDirectionObs() != null && door.getOpDirectionObs().length() > 0) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("observações a serem apontadas sobre o sendito de abertura da porta: ").append(door.getOpDirectionObs());
        }

        if (!isBox) {
            if (door.getDoorHasTactSign() == 0) {
                doorIrregular(restBoxDoors);
                restBoxDoors.append("a porta não possui sinalização tátil");
            } else if (door.getDoorHasTactSign() == 1) {
                if (door.getTactSignHeight() != null && door.getTactSignHeight() < doorTactSignMinHeight) {
                    doorIrregular(restBoxDoors);
                    restBoxDoors.append("a altura de instalação da sinalização tátil da porta é inferior à " + doorTactSignMinHeight + " m");
                } else if (door.getTactSignHeight() != null &&  door.getTactSignHeight() > doorTactSignMaxHeight) {
                    doorIrregular(restBoxDoors);
                    restBoxDoors.append("a altura de instalação da sinalização tátil da porta é superior à " + doorTactSignMaxHeight + " m");
                } else {
                    if (door.getTactSignIncl() != null && door.getTactSignIncl() < doorTactSignMinAngle) {
                        doorIrregular(restBoxDoors);
                        restBoxDoors.append("o ângulo do plano inclinado da sinalização tátil é inferior à " + doorTactSignMinAngle + "º");
                    } else if (door.getTactSignIncl() != null && door.getTactSignIncl() > doorTactSignMaxAngle) {
                        doorIrregular(restBoxDoors);
                        restBoxDoors.append("o ângulo do plano inclinado da sinalização tátil é superior à " + doorTactSignMaxAngle + "º");
                    }
                }
            }

            if (door.getTactSignObs() != null && door.getTactSignObs().length() > 0)
                restBoxDoors.append("as seguintes observações devem ser feitas sobre a sinalização tátil da porta: ").append(door.getTactSignObs());
        }

        if (door.getDoorHandleType() == 1) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("a maçaneta não é do tipo alavanca");
        }

        if (door.getDoorHandleHeight() < minDoorHandle) {
            restBoxDoorIrregular(restBoxDoors);
            if (door.getDoorHandleObs() != null && door.getDoorHandleObs().length() > 0)
                restBoxDoors.append("a altura da maçaneta é inferior à " + minDoorHandle + "m e deve-se destacar as seguintes observações sobre a maçaneta: ")
                        .append(door.getDoorHandleObs());
            else
                restBoxDoors.append("a altura da maçaneta é inferior à " + minDoorHandle + "m");
        } else if (door.getDoorHandleHeight() > maxDoorHandle) {
            restBoxDoorIrregular(restBoxDoors);
            if (door.getDoorHandleObs() != null && door.getDoorHandleObs().length() > 0)
                restBoxDoors.append("a altura da maçaneta é superior à " + maxDoorHandle + "m e deve-se destacar as seguintes observações sobre a maçaneta: ")
                        .append(door.getDoorHandleObs());
            else
                restBoxDoors.append("a altura da maçaneta é superior à " + maxDoorHandle + "m");
        } else {
            if (door.getDoorHandleObs() != null && door.getDoorHandleObs().length() > 0)
                restBoxDoors.append("a altura da maçaneta está de acordo com a norma, porém deve-se destacar as seguintes observações sobre a maçaneta: ")
                        .append(door.getDoorHandleObs());
        }

        if (door.getDoorHasHorBar() == 0) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("a porta não possui puxador horizontal");
        } else {
            if (door.getHorBarHeight() < doorHorBarMinHeight) {
                restBoxDoorIrregular(restBoxDoors);
                restBoxDoors.append("a altura de instalação do puxador horizontal é inferior à " + doorHorBarMinHeight + " m");
            } else if (door.getHorBarHeight() > doorHorBarMaxHeight) {
                restBoxDoorIrregular(restBoxDoors);
                restBoxDoors.append("a altura de instalação do puxador horizontal é superior à " + doorHorBarMaxHeight + " m");
            }

            if (door.getHorBarLength() < minHorBarLength) {
                restBoxDoorIrregular(restBoxDoors);
                restBoxDoors.append("o comprimento do puxador horizontal é inferior à " + minHorBarLength + " m");
            }

            if (door.getHorBarFrameDist() != horBarFrameDist) {
                restBoxDoorIrregular(restBoxDoors);
                restBoxDoors.append("a distância entre o batente da porta e o puxador horizontal é diferente de " + horBarFrameDist + " m");
            }

            if (door.getHorBarDoorDist() < minDistHandrail) {
                restBoxDoorIrregular(restBoxDoors);
                restBoxDoors.append("a distância entre a superfície da porta e o puxador horizontal é inferior de " + minDistHandrail + " m");
            }

            if (door.getHorBarDiam() < minHandleGrip) {
                restBoxDoorIrregular(restBoxDoors);
                restBoxDoors.append("o diâmetro do puxador horizontal é inferior à " + minHandleGrip + " mm");
            } else if (door.getHorBarDiam() > maxHandleGrip) {
                restBoxDoorIrregular(restBoxDoors);
                restBoxDoors.append("o diâmetro do puxador horizontal é superior à " + maxHandleGrip + " mm");
            }
        }

        if (door.getHorBarObs() != null && door.getHorBarObs().length() > 0) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("as seguintes observações devem ser apontadas sobre o puxador horizontal: ").append(door.getHorBarObs());
        }

        if (door.getDoorSillType() != 0) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("a soleira da porta não é nivelada");
        }
        if (door.getDoorSillObs() != null && door.getDoorSillObs().length() > 0) {
            restBoxDoorIrregular(restBoxDoors);
            restBoxDoors.append("as seguintes observações devem ser apontadas sobre a soleira da porta: ").append(door.getDoorSillObs());
        }

        return restBoxDoors.toString();
    }

    public static void doorIrregular(StringBuilder builder) {
        if (!irregularDoor) {
            irregularDoor = true;
            builder.append("Porta, localizada em x, apresenta as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }

    public static void restBoxDoorIrregular(StringBuilder builder) {
        if (!irregularDoor) {
            irregularDoor = true;
            builder.append("Porta apresenta as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
