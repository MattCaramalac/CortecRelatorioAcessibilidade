package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;

import java.util.Objects;


public class StepSillFragment extends Fragment {

    public static final String STEP_HEIGHT = "STEP_HEIGHT";

    TextInputLayout stepHeightField;
    TextInputEditText stepHeightValue;

    ViewModelDialog modelDialog;


    public StepSillFragment() {
        // Required empty public constructor
    }

    public static StepSillFragment newInstance() {
        return new StepSillFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sill_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stepHeightField = view.findViewById(R.id.sill_step_height_field);
        stepHeightValue = view.findViewById(R.id.sill_step_height_value);

        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        modelDialog.getSaveDoorAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if(Objects.equals(modelDialog.getSaveDoorAttempt().getValue(), 1)) {
                if (checkEmptySillStepField()) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble(STEP_HEIGHT, Double.parseDouble(Objects.requireNonNull(stepHeightValue.getText()).toString()));
                    modelDialog.setDoorInfo(bundle);
                    clearStepField();
                }
                modelDialog.setSaveTableAttempt(0);
            }
        });
    }

    public boolean checkEmptySillStepField() {
        clearEmptyErrorStepField();
        int error = 0;
        if (TextUtils.isEmpty(stepHeightValue.getText())) {
            stepHeightField.setError(getString(R.string.blank_field_error));
            error++;
        }
        return error == 0;
    }

    public void clearEmptyErrorStepField() {
        stepHeightField.setErrorEnabled(false);
    }

    public void clearStepField() {
        stepHeightValue.setText(null);
    }
}