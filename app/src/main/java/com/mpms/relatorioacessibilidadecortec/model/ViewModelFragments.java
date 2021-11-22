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

    public final MutableLiveData<Integer> gatherRoomData = new MutableLiveData<>();
    public void setGatherRoomData (Integer integer) {
        gatherRoomData.setValue(integer);
    }
    public LiveData<Integer> getGatherRoomData() {
        return gatherRoomData;
    }

    private final MutableLiveData<Bundle> roomBundle = new MutableLiveData<>();
    public void setRoomBundle(Bundle bundle) {
        roomBundle.setValue(bundle);
    }
    public LiveData<Bundle> getRoomBundle() {
        return roomBundle;
    }

    public final MutableLiveData<Boolean> addRegister = new MutableLiveData<>();
    public void setAddRegister(Boolean bool) {
        addRegister.setValue(bool);
    }
    public LiveData<Boolean> getAddRegister() {
        return addRegister;
    }

    public final MutableLiveData<Bundle> rampStairsBundle = new MutableLiveData<>();
    public void setRampStairsBundle(Bundle bundle) {
        rampStairsBundle.setValue(bundle);
    }
    public LiveData<Bundle> getRampStairsBundle() {
        return rampStairsBundle;
    }

    public final MutableLiveData<Bundle> restroomBundle = new MutableLiveData<>();
    public void setRestroomBundle(Bundle bundle) {
        restroomBundle.setValue(bundle);
    }
    public LiveData<Bundle> getRestroomBundle() {
        return restroomBundle;
    }

    public final MutableLiveData<Bundle> checkMirUrFrags = new MutableLiveData<>();
    public void setCheckMirUrFrags(Bundle bundle) { checkMirUrFrags.setValue(bundle); }
    public LiveData<Bundle> getCheckMirUrFrags() {
        return checkMirUrFrags;
    }

    public final MutableLiveData<Bundle> sendMirrorFragData = new MutableLiveData<>();
    public void setSendMirrorFragData(Bundle bundle) { sendMirrorFragData.setValue(bundle); }
    public LiveData<Bundle> getSendMirrorFragData() {
        return sendMirrorFragData;
    }

    public final MutableLiveData<Bundle> sendUrinalFragData = new MutableLiveData<>();
    public void setSendUrinalFragData(Bundle bundle) { sendUrinalFragData.setValue(bundle); }
    public LiveData<Bundle> getSendUrinalFragData() {
        return sendUrinalFragData;
    }

    public final MutableLiveData<Bundle> restChildFragBundle = new MutableLiveData<>();
    public void setRestChildFragBundle(Bundle bundle) {
        restChildFragBundle.setValue(bundle);
    }
    public LiveData<Bundle> getRestChildFragBundle() {
        return restChildFragBundle;
    }

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

    public final MutableLiveData<Bundle> sendFountainFragData = new MutableLiveData<>();
    public void setFountainFragData(Bundle bundle) { sendFountainFragData.setValue(bundle); }
    public LiveData<Bundle> getFountainFragData() {
        return sendFountainFragData;
    }

}
