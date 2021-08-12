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

public class GateObsBarrierType extends Fragment {

    public static final String OBS_BARRIER_HEIGHT = "OBS_BARRIER_HEIGHT";
    public static final String OBS_BARRIER_WIDTH = "OBS_BARRIER_WIDTH";

    TextInputLayout barrierHeightField, barrierWidthField;
    TextInputEditText barrierHeightValue, barrierWidthValue;

    ViewModelDialog modelDialog;

    public GateObsBarrierType() {
        // Required empty public constructor
    }

    public static GateObsBarrierType newInstance() {
        return new GateObsBarrierType();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gate_obs_barrier_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        barrierHeightField = view.findViewById(R.id.gate_obs_barrier_height_field);
        barrierWidthField = view.findViewById(R.id.gate_obs_barrier_width_field);
        barrierHeightValue = view.findViewById(R.id.gate_obs_barrier_height_value);
        barrierWidthValue = view.findViewById(R.id.gate_obs_barrier_width_value);

        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        modelDialog.getSaveGateObsAttemptTwo().observe(getViewLifecycleOwner(), partTwo -> {
            if(Objects.equals(modelDialog.getSaveGateObsAttemptTwo().getValue(),1)) {
                if (checkEmptyBarrierObsField()) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble(OBS_BARRIER_HEIGHT, Double.parseDouble(Objects.requireNonNull(barrierHeightValue.getText()).toString()));
                    bundle.putDouble(OBS_BARRIER_WIDTH, Double.parseDouble(Objects.requireNonNull(barrierWidthValue.getText()).toString()));
                    modelDialog.setTempGateObsInfo(bundle);
                }
                modelDialog.setSaveGateObsAttemptTwo(0);
            }
        });
    }

    public boolean checkEmptyBarrierObsField() {
        clearEmptyBarrierFieldsErrors();
        int error = 0;
        if (TextUtils.isEmpty(barrierHeightValue.getText())){
            barrierHeightField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(barrierWidthValue.getText())){
            barrierWidthField.setError(getString(R.string.blank_field_error));
            error++;
        }
        return error == 0;
    }

    public void clearEmptyBarrierFieldsErrors() {
        barrierWidthField.setErrorEnabled(false);
        barrierHeightField.setErrorEnabled(false);
    }
}