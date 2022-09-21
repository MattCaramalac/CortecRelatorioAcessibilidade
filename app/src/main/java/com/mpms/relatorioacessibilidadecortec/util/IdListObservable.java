package com.mpms.relatorioacessibilidadecortec.util;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class IdListObservable extends java.util.Observable {
        private List<Integer> idList;

        public List<Integer> getIdList() {
            return idList;
        }

        public void setIdList(List<Integer> idList) {
            this.idList = idList;
            setChanged();
            notifyObservers();
        }
}
