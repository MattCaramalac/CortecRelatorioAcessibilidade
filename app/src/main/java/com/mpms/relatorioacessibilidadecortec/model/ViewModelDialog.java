package com.mpms.relatorioacessibilidadecortec.model;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelDialog extends ViewModel {

    private final MutableLiveData<Integer> saveDoorAttempt = new MutableLiveData<>();
    public void setSaveDoorAttempt (Integer integer) { saveDoorAttempt.setValue(integer); }
    public LiveData<Integer> getSaveDoorAttempt() { return  saveDoorAttempt; }

    public final MutableLiveData<Bundle> doorInfo = new MutableLiveData<>();
    public void setDoorInfo(Bundle bundle) {doorInfo.setValue(bundle); }
    public LiveData<Bundle> getDoorInfo() { return doorInfo; }

    private final MutableLiveData<Integer> saveTableAttempt = new MutableLiveData<>();
    public void setSaveTableAttempt (Integer integer) { saveTableAttempt.setValue(integer); }
    public LiveData<Integer> getSaveTableAttempt() { return  saveTableAttempt; }

    public final MutableLiveData<Bundle> tableInfo = new MutableLiveData<>();
    public void setTableInfo(Bundle bundle) {tableInfo.setValue(bundle); }
    public LiveData<Bundle> getTableInfo() { return tableInfo; }

    private final MutableLiveData<Integer> saveGateObsAttemptOne = new MutableLiveData<>();
    public void setSaveGateObsAttemptOne(Integer integer) { saveGateObsAttemptOne.setValue(integer); }
    public LiveData<Integer> getSaveGateObsAttemptOne() { return saveGateObsAttemptOne; }

    private final MutableLiveData<Integer> saveGateObsAttemptTwo = new MutableLiveData<>();
    public void setSaveGateObsAttemptTwo(Integer integer) { saveGateObsAttemptTwo.setValue(integer); }
    public LiveData<Integer> getSaveGateObsAttemptTwo() { return saveGateObsAttemptTwo; }

    public final MutableLiveData<Bundle> gateObsInfo = new MutableLiveData<>();
    public void setGateObsInfo(Bundle bundle) {gateObsInfo.setValue(bundle); }
    public LiveData<Bundle> getGateObsInfo() { return gateObsInfo; }

    public final MutableLiveData<Bundle> tempGateObsInfo = new MutableLiveData<>();
    public void setTempGateObsInfo(Bundle bundle) { tempGateObsInfo.setValue(bundle); }
    public LiveData<Bundle> getTempGateObsInfo() { return tempGateObsInfo; }

    public final MutableLiveData<Bundle> restDoorBundle = new MutableLiveData<>();
    public void setRestDoorBundle(Bundle bundle) { restDoorBundle.setValue(bundle); }
    public LiveData<Bundle> getRestDoorBundle() { return restDoorBundle; }
}
