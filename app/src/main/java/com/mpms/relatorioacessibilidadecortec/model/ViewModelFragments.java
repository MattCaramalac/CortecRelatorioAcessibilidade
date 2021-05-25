package com.mpms.relatorioacessibilidadecortec.model;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelFragments extends ViewModel {

    private final MutableLiveData<Integer> saveAttemptWaterFountain = new MutableLiveData<>();

    public void saveAttemptTestWaterFountain(Integer integer) {
        saveAttemptWaterFountain.setValue(integer);
    }

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

}
