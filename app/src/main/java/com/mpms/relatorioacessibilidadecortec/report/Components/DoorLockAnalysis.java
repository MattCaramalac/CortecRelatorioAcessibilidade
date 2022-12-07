package com.mpms.relatorioacessibilidadecortec.report.Components;

import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.report.StandardMeasurements;

import java.util.ArrayList;
import java.util.List;

public class DoorLockAnalysis implements StandardMeasurements {

    static boolean irregularLock;

    public static List<String> gateLockVerification(int extID, List<DoorLockEntry> gateLock) {

        List<String> gLockText = new ArrayList<>();

        if (gateLock.size() > 0) {
            for (DoorLockEntry lock : gateLock) {
                irregularLock = false;
                String analysis = null;
                if (lock.getExtAccessID() != null && lock.getExtAccessID() == extID)
                    analysis = lockText(lock, 1);
                if (analysis != null && analysis.length() > 0)
                    gLockText.add(analysis);
            }
        }
        return gLockText;
    }

    public static String doorLockVerification(int doorID, List<DoorLockEntry> gateLock) {

       StringBuilder dLockText = new StringBuilder();

        if (gateLock.size() > 0) {
            for (DoorLockEntry lock : gateLock) {
                irregularLock = false;
                String analysis = null;
                if (lock.getDoorID() != null && lock.getDoorID() == doorID)
                    analysis = lockText(lock, 2);
                if (analysis != null && analysis.length() > 0)
                    dLockText.append(analysis).append(", ");
            }
        }
        return dLockText.toString();
    }

    //    doorType: 1 - gate; 2 - door
    public static String lockText(DoorLockEntry lock, int doorType) {
        StringBuilder builder = new StringBuilder();
        if (doorType == 1) {
            if (lock.getLockType() > 1) {
                lockIrregular(builder);
                builder.append("tipo de dispositivo de travamento não atende aos princípios do desenho universal");
            }
        } else {
            if (lock.getLockType() > 0) {
                lockIrregular(builder);
                builder.append("tipo de dispositivo de travamento não atende aos princípios do desenho universal");
            }
        }

        if (lock.getLockHeight() < minDoorHandle) {
            lockIrregular(builder);
            builder.append("altura do dispositivo inferior a " + minDoorHandle + " m");
        } else if (lock.getLockHeight() > maxDoorHandle) {
            lockIrregular(builder);
            builder.append("altura do dispositivo superior a " + maxDoorHandle + " m");
        }

        if (lock.getLockObs() != null) {
            lockIrregular(builder);
            builder.append("devem ser apontadas as seguintes observações: ").append(lock.getLockObs());
        }

        if (irregularLock && builder.length() != 0)
            builder.replace(46, 47, " " + lock.getLockType() + " ");

        return builder.toString();
    }


    public static void lockIrregular(StringBuilder builder) {
        if (!irregularLock) {
            irregularLock = true;
            builder.append("presença de dispositivo de travamento do tipo x com as seguintes irregularidades: "); //45 46
        } else {
            builder.append(", ");
        }
    }
}
