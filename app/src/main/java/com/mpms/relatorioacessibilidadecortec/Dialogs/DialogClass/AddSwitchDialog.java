package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;

import java.util.Objects;

public class AddSwitchDialog extends DialogFragment {

    TextInputLayout switchPlaceField, switchTypeField, switchHeightField, switchObsField;
    TextInputEditText switchPlaceValue, switchTypeValue, switchHeightValue, switchObsValue;
    Button saveSwitch, cancelSwitch;

    private static final String SCHOOL_ID_VALUE = "SCHOOL_ID_VALUE";
    private static final String ROOM_TYPE = "ROOM_TYPE";

    private Toolbar toolbar;

    public static AddSwitchDialog displaySwitchDialog(FragmentManager fragmentManager) {
        AddSwitchDialog addSwitchDialog = new AddSwitchDialog();
        addSwitchDialog.show(fragmentManager, "SWITCH_DIALOG");
        return  addSwitchDialog;
    }

//    public AddSwitchDialog() {
//        // Required empty public constructor
//    }
//
//    public static AddSwitchDialog newInstance(String param1, String param2) {
//        return new AddSwitchDialog();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_switch_dialog, container, false);
        toolbar = view.findViewById(R.id.switch_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.dialog_add_switch_header);

        switchPlaceField = view.findViewById(R.id.switch_placement_field);
        switchTypeField = view.findViewById(R.id.switch_type_field);
        switchHeightField = view.findViewById(R.id.switch_height_field);
        switchObsField = view.findViewById(R.id.switch_obs_field);

        switchPlaceValue = view.findViewById(R.id.switch_placement_value);
        switchTypeValue = view.findViewById(R.id.switch_type_value);
        switchHeightValue = view.findViewById(R.id.switch_height_value);
        switchObsValue = view.findViewById(R.id.switch_obs_value);

        saveSwitch = view.findViewById(R.id.save_switch);
        cancelSwitch = view.findViewById(R.id.cancel_switch);

        saveSwitch.setOnClickListener(v -> {
            checkEmptySwitchFields();
        });

        cancelSwitch.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int lenght = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width,lenght);
        }
    }

    public boolean checkEmptySwitchFields() {
        clearEmptySwitchFields();
        int error = 0;
        if (TextUtils.isEmpty(switchPlaceValue.getText())) {
            switchPlaceField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(switchTypeValue.getText())) {
            switchTypeField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(switchHeightValue.getText())) {
            switchHeightField.setError(getString(R.string.blank_field_error));
            error++;
        }
        return error == 0;
    }

    public void clearEmptySwitchFields() {
        switchPlaceField.setErrorEnabled(false);
        switchTypeField.setErrorEnabled(false);
        switchHeightField.setErrorEnabled(false);
    }
}