package com.mpms.relatorioacessibilidadecortec.util;

import android.text.Editable;
import android.text.TextWatcher;

//TODO - Ver método de implementar esta classe de forma "dinâmica", selecionando máscaras conforme o número de dígitos
public class MaskWatcher implements TextWatcher {

    private boolean isRunning = false;
    private boolean isDeleting = false;
    private final String mask;

    public MaskWatcher(String mask) {
        this.mask = mask;
    }

    public MaskWatcher buildPhoneNumber() {
        return new MaskWatcher("(##)####-####");
    }

    public MaskWatcher buildCellPhone() {
        return new MaskWatcher("(##)#####-####");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        isDeleting = count > after;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (isRunning || isDeleting) {
            return;
        }
        isRunning = true;

        int editableLength = editable.length();
        if (editableLength < mask.length()) {
            if (mask.charAt(editableLength) != '#') {
                editable.append(mask.charAt(editableLength));
            } else if (mask.charAt(editableLength-1) != '#') {
                editable.insert(editableLength-1, mask, editableLength-1, editableLength);
            }
        }
        isRunning = false;
    }
}
