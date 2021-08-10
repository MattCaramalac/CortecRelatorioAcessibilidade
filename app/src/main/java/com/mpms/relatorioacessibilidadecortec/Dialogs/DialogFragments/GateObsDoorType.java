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

public class GateObsDoorType extends Fragment {

    public static final String OBS_GATE_WIDTH = "OBS_GATE_WIDTH";

    TextInputLayout obsGateWidthField;
    TextInputEditText obsGateWidthValue;

    ViewModelDialog modelDialog;

    public GateObsDoorType() {
        // Required empty public constructor
    }


    public static GateObsDoorType newInstance() {
        return new GateObsDoorType();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gate_obs_door_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        obsGateWidthField = view.findViewById(R.id.gate_obs_door_width_field);
        obsGateWidthValue = view.findViewById(R.id.gate_obs_door_width_value);

        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        modelDialog.getSaveGateObsAttemptTwo().observe(getViewLifecycleOwner(), partTwo -> {
            if(Objects.equals(modelDialog.getSaveGateObsAttemptTwo().getValue(),1)) {
                if (checkEmptyGateObsField()) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble(OBS_GATE_WIDTH, Double.parseDouble(Objects.requireNonNull(obsGateWidthValue.getText()).toString()));
                    modelDialog.setTempGateObsInfo(bundle);
                }
                modelDialog.setSaveGateObsAttemptTwo(0);
            }
        });
    }

    public boolean checkEmptyGateObsField() {
        int error = 0;
        if (TextUtils.isEmpty(obsGateWidthValue.getText())){
            error++;
            obsGateWidthField.setError(getString(R.string.blank_field_error));
        }
        return error == 0;
    }
}