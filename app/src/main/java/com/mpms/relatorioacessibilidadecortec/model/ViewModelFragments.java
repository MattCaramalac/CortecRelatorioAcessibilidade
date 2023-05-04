package com.mpms.relatorioacessibilidadecortec.model;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelFragments extends ViewModel {

    public final MutableLiveData<Bundle> saveUpdateSchoolRegBundle = new MutableLiveData<>();
    public void setSaveUpdateSchoolReg(Bundle bundle) { saveUpdateSchoolRegBundle.setValue(bundle); }
    public LiveData<Bundle> getSaveUpdateSchoolReg() {
        return saveUpdateSchoolRegBundle;
    }

    public final MutableLiveData<Bundle> dataFromFragToActivity = new MutableLiveData<>();
    public void setDataFromFragToActivity(Bundle bundle) { dataFromFragToActivity.setValue(bundle); }
    public LiveData<Bundle> getDataFromFragToActivity() {
        return dataFromFragToActivity;
    }

    public final MutableLiveData<Bundle> dataFromActivityToFrag = new MutableLiveData<>();
    public void setDataFromActivityToFrag(Bundle bundle) { dataFromActivityToFrag.setValue(bundle); }
    public LiveData<Bundle> getDataFromActivityToFrag() {
        return dataFromActivityToFrag;
    }

    public final MutableLiveData<Integer> newRoomID = new MutableLiveData<>();
    public void setNewRoomID(Integer i) { newRoomID.setValue(i); }
    public LiveData<Integer> getNewRoomID() {
        return newRoomID;
    }

    public final MutableLiveData<Integer> newChildRegID = new MutableLiveData<>();
    public void setNewChildRegID(Integer i) { newChildRegID.setValue(i); }
    public LiveData<Integer> getNewChildRegID() {
        return newChildRegID;
    }

}
