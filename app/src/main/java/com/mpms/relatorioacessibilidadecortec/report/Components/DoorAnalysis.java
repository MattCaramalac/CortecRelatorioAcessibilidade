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
                StringBuilder doorBuilder = new StringBuilder();
                String analysis = null;
                if (door.getRoomID() == roomID)
                    analysis = doorTexts(door, doorLockList);
                if (analysis != null && analysis.length() > 0) {
                    doorBuilder.append(analysis);
                    doorIrregular(doorBuilder);
                    doorBuilder.replace(21, 22, door.getDoorLocation());
                    doorText.add(doorBuilder.toString());
                }
            }
        }
        return doorText;
    }

    public static String doorTexts(DoorEntry door, List<DoorLockEntry> dLockList) {
        StringBuilder doors = new StringBuilder();
        if (door.getDoorWidth() < freeSpaceGeneral) {
            doorIrregular(doors);
            doors.append("largura do vão livre da porta inferior à " + freeSpaceGeneral + " m");
        }

        if (door.getDoorHandleType() == 1) {
            doorIrregular(doors);
            doors.append("a maçaneta não é do tipo alavanca");
        }

        if (door.getDoorHandleHeight() < minDoorHandle) {
            doorIrregular(doors);
            if (door.getDoorHandleObs() != null)
                doors.append("a altura da maçaneta é inferior à " + minDoorHandle + "m e deve-se destacar as seguintes observações sobre a maçaneta: ")
                        .append(door.getDoorHandleObs());
            else
                doors.append("a altura da maçaneta é inferior à " + minDoorHandle + "m");
        } else if (door.getDoorHandleHeight() > maxDoorHandle) {
            doorIrregular(doors);
            if (door.getDoorHandleObs() != null)
                doors.append("a altura da maçaneta é superior à " + maxDoorHandle + "m e deve-se destacar as seguintes observações sobre a maçaneta: ")
                        .append(door.getDoorHandleObs());
            else
                doors.append("a altura da maçaneta é superior à " + maxDoorHandle + "m");
        } else {
            if (door.getDoorHandleObs() != null)
                doors.append("a altura da maçaneta está de acordo com a norma, porém deve-se destacar as seguintes observações sobre a maçaneta: ")
                        .append(door.getDoorHandleObs());
        }

        if (dLockList.size() > 0) {
            String dLocks = DoorLockAnalysis.doorLockVerification(door.getDoorID(), dLockList);
            if (dLocks.length() > 0) {
                doorIrregular(doors);
                doors.append(dLocks);
            }
        }

        if (door.getDoorHasTactileSign() == 0) {
            doorIrregular(doors);
            if (door.getDoorTactileSignObs() != null) {
                doors.append("a porta não possui sinalização tátil e as seguintes observações devem ser feitas: ").append(door.getDoorTactileSignObs());
            }
        }
        else if (door.getDoorHasTactileSign() == 1) {
            if (door.getDoorTactileSignObs() != null) {
                doors.append("a porta possui sinalização tátil, porém as seguintes observações devem ser feitas: ").append(door.getDoorTactileSignObs());
            }
        }

        String doorSill = DoorSillAnalysis.doorSillVerification(door);
        if (doorSill != null && doorSill.length() > 0) {
            doorIrregular(doors);
            doors.append(doorSill);
        }

        return doors.toString();

    }

    public static void doorIrregular(StringBuilder builder) {
        if (!irregularDoor) {
            irregularDoor = true;
            builder.append("Porta, localizada em x, com as seguintes irregularidades: ");
        } else
            builder.append(", ");
    }
}
