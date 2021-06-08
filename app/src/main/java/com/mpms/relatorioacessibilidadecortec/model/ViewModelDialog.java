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
}
