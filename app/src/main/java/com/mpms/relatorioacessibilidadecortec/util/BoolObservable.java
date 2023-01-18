package com.mpms.relatorioacessibilidadecortec.util;

import java.util.Observable;

public class BoolObservable extends Observable {
    private boolean finished;

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
        setChanged();
        notifyObservers();
    }
}
