package com.mpms.relatorioacessibilidadecortec.util;

import android.view.View;
import android.widget.RadioGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

public interface ParkingLotInterface {

    void disableEverything(ConstraintLayout layout);

    void enableAllRadioGroups(ConstraintLayout layout);

    void disableRadioGroup(RadioGroup radioGroup);

    void enableRadioGroup(RadioGroup radioGroup);

    void clearFields();

    void hasParkingLotVacancy(RadioGroup group, int checkedID);

    void addValuesToArrays();

    void enableFields(RadioGroup group, int checkedID);
}
