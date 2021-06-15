package com.mpms.relatorioacessibilidadecortec.model;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelFragments extends ViewModel {

    private final MutableLiveData<Integer> saveAttemptWaterFountain = new MutableLiveData<>();
    public void setSaveAttemptFountain(Integer integer) { saveAttemptWaterFountain.setValue(integer); }
    public LiveData<Integer> getSaveAttempt() {
        return saveAttemptWaterFountain;
    }

    private final MutableLiveData<Bundle> fountainBundle = new MutableLiveData<>();
    public void setFountainBundle(Bundle bundle) {
        fountainBundle.setValue(bundle);
    }
    public LiveData<Bundle> getFountainBundle() {
        return fountainBundle;
    }

    public final MutableLiveData<Integer> saveAttemptRooms = new MutableLiveData<>();
    public void setSaveAttemptRooms (Integer integer) {
        saveAttemptRooms.setValue(integer);
    }
    public LiveData<Integer> getSaveAttemptRoom() {
        return saveAttemptRooms;
    }

    private final MutableLiveData<Bundle> roomBundle = new MutableLiveData<>();
    public void setRoomBundle(Bundle bundle) {
        roomBundle.setValue(bundle);
    }
    public LiveData<Bundle> getRoomBundle() {
        return roomBundle;
    }

    public final MutableLiveData<Integer> updateRooms = new MutableLiveData<>();
    public void setUpdateRooms (Integer integer) {
        updateRooms.setValue(integer);
    }
    public LiveData<Integer> getUpdateRoom() {
        return updateRooms;
    }

}
