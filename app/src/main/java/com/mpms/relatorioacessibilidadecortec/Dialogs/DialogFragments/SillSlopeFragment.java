package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;

import java.util.Objects;


public class SillSlopeFragment extends Fragment {

    public static final String SLOPE_INCLINATION = "SLOPE_INCLINATION";
    public static final String SLOPE_WIDTH = "SLOPE_WIDTH";

    TextInputLayout sillSlopeInclinationField, sillSlopeWidthField;
    TextInputEditText sillSlopeInclinationValue, sillSlopeWidthValue;

    ViewModelDialog modelDialog;

    public SillSlopeFragment() {
        // Required empty public constructor
    }


    public static SillSlopeFragment newInstance() {
        return new SillSlopeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sill_slope, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sillSlopeInclinationField = view.findViewById(R.id.sill_slope_field);
        sillSlopeWidthField = view.findViewById(R.id.sill_slope_width_field);

        sillSlopeInclinationValue = view.findViewById(R.id.sill_slope_value);
        sillSlopeWidthValue = view.findViewById(R.id.sill_slope_width_value);

        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        modelDialog.getRestDoorBundle().observe(getViewLifecycleOwner(), this::gatherSlopeData);

        modelDialog.getSaveDoorAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (Objects.equals(modelDialog.getSaveDoorAttempt().getValue(), 1)) {
                if (checkEmptySlopeFields()) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble(SLOPE_INCLINATION, Double.parseDouble(Objects.requireNonNull(sillSlopeInclinationValue.getText()).toString()));
                    bundle.putDouble(SLOPE_WIDTH, Double.parseDouble(Objects.requireNonNull(sillSlopeWidthValue.getText()).toString()));
                    modelDialog.setDoorInfo(bundle);
                    clearSlopeFields();
                }
                modelDialog.setSaveDoorAttempt(0);
            }
        });
    }

    public boolean checkEmptySlopeFields() {
        clearEmptyFieldErrors();
        int error = 0;
        if (TextUtils.isEmpty(sillSlopeInclinationValue.getText())) {
            sillSlopeInclinationField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(sillSlopeWidthValue.getText())) {
            sillSlopeWidthField.setError(getString(R.string.blank_field_error));
            error++;
        }
        return error == 0;
    }

    public void clearEmptyFieldErrors() {
        sillSlopeInclinationField.setErrorEnabled(false);
        sillSlopeWidthField.setErrorEnabled(false);
    }

    public void clearSlopeFields() {
        sillSlopeWidthValue.setText(null);
        sillSlopeInclinationValue.setText(null);
    }

    public void gatherSlopeData(Bundle bundle) {
        sillSlopeWidthValue.setText(String.valueOf(bundle.getDouble(SLOPE_WIDTH)));
        sillSlopeInclinationValue.setText(String.valueOf(bundle.getDouble(SLOPE_INCLINATION)));
    }
}