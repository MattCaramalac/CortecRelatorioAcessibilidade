package com.mpms.relatorioacessibilidadecortec.model;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InspectionViewModel extends ViewModel {

    private final MutableLiveData<String> headerTitle = new MutableLiveData<>();
    private final MutableLiveData<Boolean> visible = new MutableLiveData<>();
    private final MutableLiveData<Bundle> visibleData = new MutableLiveData<>();

    public LiveData<String> getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle.setValue(headerTitle);
    }

    public LiveData<Boolean> getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible.setValue(visible);
    }

    public LiveData<Bundle> getVisibleData() {
        return visibleData;
    }

    public void setVisibleData(Bundle bundle) {
        visibleData.setValue(bundle);
    }
}
