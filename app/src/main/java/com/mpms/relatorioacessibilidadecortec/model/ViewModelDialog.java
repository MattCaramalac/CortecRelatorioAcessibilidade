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
}
