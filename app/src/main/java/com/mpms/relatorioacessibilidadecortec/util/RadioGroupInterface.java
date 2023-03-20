package com.mpms.relatorioacessibilidadecortec.util;

import android.widget.RadioGroup;

public interface RadioGroupInterface {

    default int indexCheckRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    default void checkRadioGroup(RadioGroup radio, int index) {
        radio.check(radio.getChildAt(index).getId());
    }

    void radioListener(RadioGroup radio, int id);
}
