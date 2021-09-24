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
import com.mpms.relatorioacessibilidadecortec.entities.StairsStepEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RampStairsFlightFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;


public class AddStairsStepDialog extends DialogFragment {

    TextInputLayout stairsStepWidthField;
    TextInputEditText stairsStepWidthValue;
    Button saveStairsStep, cancelStairsStep;
    Toolbar toolbar;

    FragmentManager manager;

    ViewModelEntry modelEntry;

    static Bundle stairsStepBundle = new Bundle();

    int stepMeasurements = 0;

    public static AddStairsStepDialog displayStepDialog(FragmentManager manager, Bundle bundle) {
        AddStairsStepDialog stepDialog = new AddStairsStepDialog();
        stepDialog.show(manager, "STAIRS_STEP_DIALOG");
        stairsStepBundle = bundle;
        return stepDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_stairs_step_dialog, container, false);
        toolbar = view.findViewById(R.id.stairs_step_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("Cadastrar Espelhos do Lance");

        stairsStepWidthField = view.findViewById(R.id.stairs_step_size_field);
        stairsStepWidthValue = view.findViewById(R.id.stairs_step_size_value);

        saveStairsStep = view.findViewById(R.id.save_stairs_step);
        cancelStairsStep = view.findViewById(R.id.cancel_stairs_step);

        manager = getChildFragmentManager();

        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        saveStairsStep.setOnClickListener(v -> {
            if(checkStairsStepEmptyField()) {
                StairsStepEntry stairsStepEntry = stepEntry(stairsStepBundle);
                ViewModelEntry.insertStairsStepEntry(stairsStepEntry);
                stepMeasurements++;
                clearStairsStepFields();
            }
        });

        cancelStairsStep.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
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

    private boolean checkStairsStepEmptyField() {
        clearStairsStepEmptyFieldError();
        int i = 0;
        if (TextUtils.isEmpty(stairsStepWidthValue.getText())){
            i++;
            stairsStepWidthField.setError(getString(R.string.blank_field_error));
        }
        return i == 0;
    }

    private void clearStairsStepEmptyFieldError() {
        stairsStepWidthField.setErrorEnabled(false);
    }

    private void clearStairsStepFields() {
        stairsStepWidthValue.setText(null);
    }

    private StairsStepEntry stepEntry(Bundle bundle) {
        double stepWidth = Double.parseDouble(String.valueOf(stairsStepWidthValue.getText()));
        return new StairsStepEntry(bundle.getInt(RampStairsFlightFragment.FLIGHT_ID), (stepMeasurements+1), stepWidth);
    }
}