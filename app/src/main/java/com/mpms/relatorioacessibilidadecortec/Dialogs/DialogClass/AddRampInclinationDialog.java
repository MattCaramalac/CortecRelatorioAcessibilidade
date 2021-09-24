package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RampInclinationEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RampStairsFlightFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;


public class AddRampInclinationDialog extends DialogFragment {

    TextInputLayout rampInclinationField;
    TextInputEditText rampInclinationValue;
    Button saveInclination, cancelInclination;
    Toolbar toolbar;

    FragmentManager manager;

    ViewModelEntry modelEntry;

    static Bundle inclinationBundle;

    int inclinationNumber = 0;

    public static AddRampInclinationDialog inclinationDialog(FragmentManager manager, Bundle bundle) {
        AddRampInclinationDialog inclinationDialog = new AddRampInclinationDialog();
        inclinationDialog.show(manager, "RAMP_INCLINATION_DIALOG");
        inclinationBundle = bundle;
        return inclinationDialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_ramp_inclination_dialog, container, false);
        toolbar = view.findViewById(R.id.ramp_inclination_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.toolbar_register_ramp_inclination);

        rampInclinationField = view.findViewById(R.id.ramp_inclination_field);
        rampInclinationValue = view.findViewById(R.id.ramp_inclination_value);

        saveInclination = view.findViewById(R.id.save_ramp_inclination);
        cancelInclination = view.findViewById(R.id.cancel_ramp_inclination);

        manager = getChildFragmentManager();

        saveInclination.setOnClickListener(v -> {
            if(checkRampInclinationEmptyField()) {
                RampInclinationEntry rampEntry = newInclination(inclinationBundle);
                ViewModelEntry.insertRampInclinationEntry(rampEntry);
                inclinationNumber++;
                clearRampInclinationFields();
            }
        });

        cancelInclination.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int length = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width,length);
        }
    }

    private boolean checkRampInclinationEmptyField() {
        clearRampInclinationEmptyFieldError();
        int i = 0;
        if (TextUtils.isEmpty(rampInclinationValue.getText())){
            i++;
            rampInclinationField.setError(getString(R.string.blank_field_error));
        }
        return i == 0;
    }

    private void clearRampInclinationEmptyFieldError() {
        rampInclinationField.setErrorEnabled(false);
    }

    private void clearRampInclinationFields() {
        rampInclinationValue.setText(null);
    }

    private RampInclinationEntry newInclination(Bundle bundle) {
        double inclination = Double.parseDouble(String.valueOf(rampInclinationValue.getText()));
        return new RampInclinationEntry(bundle.getInt(RampStairsFlightFragment.FLIGHT_ID), (inclinationNumber+1), inclination);
    }
}