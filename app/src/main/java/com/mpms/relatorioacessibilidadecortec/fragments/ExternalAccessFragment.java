package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class ExternalAccessFragment extends Fragment {

    RadioGroup entranceType, hasSiaRadio, hasTrailRampRadio, hasGateObstaclesRadio, hasGatePayphonesRadio;
    TextInputLayout floorTypeField, gateWidthField, gateTrailHeightField;
    TextInputEditText floorTypeValue, gateWidthValue, gateTrailHeightValue;
    Button hasTrailRampButton, hasGateObstaclesButton, hasGatePayphonesButton, saveExternalAccess, cancelExternalAccess;
    TextView accessTypeError, hasSiaError, hasTrailRampError, hasGateObstaclesError, hasGatePayphonesError;

    private static int schoolID;

    public ExternalAccessFragment() {
        // Required empty public constructor
    }

    public static ExternalAccessFragment newInstance() {
        return new ExternalAccessFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle schoolBundle = this.getArguments();
        if(schoolBundle != null) {
            schoolID = schoolBundle.getInt(InspectionActivity.SCHOOL_ID_VALUE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_external_access, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        entranceType = view.findViewById(R.id.external_access_type_radio);
        hasSiaRadio = view.findViewById(R.id.has_SIA_radio);
        hasTrailRampRadio = view.findViewById(R.id.gate_has_trail_ramp_radio);
        hasGateObstaclesRadio = view.findViewById(R.id.gate_has_obstacles_radio);
        hasGatePayphonesRadio = view.findViewById(R.id.gate_has_payphones_radio);

        floorTypeField = view.findViewById(R.id.floor_type_ext_field);
        gateWidthField = view.findViewById(R.id.gate_width_ext_field);
        gateTrailHeightField = view.findViewById(R.id.ext_trail_height_field);

        floorTypeValue = view.findViewById(R.id.floor_type_ext_value);
        gateWidthValue = view.findViewById(R.id.gate_width_ext_value);
        gateTrailHeightValue = view.findViewById(R.id.ext_trail_height_value);

        hasTrailRampButton = view.findViewById(R.id.add_gate_trail_ramp_button);
        hasGateObstaclesButton = view.findViewById(R.id.add_gate_obstacles_button);
        hasGatePayphonesButton = view.findViewById(R.id.add_gate_payphones_button);
        saveExternalAccess = view.findViewById(R.id.save_ext_access);
        cancelExternalAccess = view.findViewById(R.id.cancel_ext_access);

        accessTypeError = view.findViewById(R.id.external_access_type_error);
        hasSiaError = view.findViewById(R.id.has_SIA_error);
        hasTrailRampError = view.findViewById(R.id.has_trail_ramp_error);
        hasGateObstaclesError = view.findViewById(R.id.gate_has_obstacles_error);
        hasGatePayphonesError = view.findViewById(R.id.gate_has_payphones_error);

        radioGroupActivation(hasTrailRampRadio, hasTrailRampButton);
        radioGroupActivation(hasGateObstaclesRadio, hasGateObstaclesButton);
        radioGroupActivation(hasGatePayphonesRadio, hasGatePayphonesButton);

        cancelExternalAccess.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        saveExternalAccess.setOnClickListener(v -> {
            if(checkEmptyFields()) {
                ExternalAccess newAccess = createNewAccess();
                ViewModelEntry.insertExternalAccess(newAccess);
                clearFields();
                Toast.makeText(getContext(), "Cadastro Efetuado com Sucesso", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void radioGroupActivation(RadioGroup radioGroup, Button button) {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            View radioButton = radioGroup.findViewById(checkedId);
            int index = radioGroup.indexOfChild(radioButton);

            if (index == 1)
                button.setVisibility(View.VISIBLE);
            else
                button.setVisibility(View.GONE);
        });
    }

    public int getCheckedButton(RadioGroup radioGroup) {
        return radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
    }

    public boolean checkEmptyFields() {
        clearExternalAccessErrors();
        int errors = 0;
        if(entranceType.getCheckedRadioButtonId() == -1) {
            accessTypeError.setVisibility(View.VISIBLE);
            errors++;
        }
        if(hasSiaRadio.getCheckedRadioButtonId() == -1) {
            hasSiaError.setVisibility(View.VISIBLE);
            errors++;
        }
        if(hasTrailRampRadio.getCheckedRadioButtonId() == -1) {
            hasTrailRampError.setVisibility(View.VISIBLE);
            errors++;
        }
        if(hasGateObstaclesRadio.getCheckedRadioButtonId() == -1) {
            hasGateObstaclesError.setVisibility(View.VISIBLE);
            errors++;
        }
        if(hasGatePayphonesRadio.getCheckedRadioButtonId() == -1) {
            hasGatePayphonesError.setVisibility(View.VISIBLE);
            errors++;
        }
        if(TextUtils.isEmpty(floorTypeValue.getText())) {
            floorTypeField.setError(getString(R.string.blank_field_error));
            errors++;
        }
        if(TextUtils.isEmpty(gateWidthValue.getText())) {
            gateWidthField.setError(getString(R.string.blank_field_error));
            errors++;
        }
        if(TextUtils.isEmpty(gateTrailHeightValue.getText())) {
            gateTrailHeightField.setError(getString(R.string.blank_field_error));
            errors++;
        }
        return errors == 0;
    }

    public void clearExternalAccessErrors() {
        accessTypeError.setVisibility(View.GONE);
        hasSiaError.setVisibility(View.GONE);
        hasTrailRampError.setVisibility(View.GONE);
        hasGateObstaclesError.setVisibility(View.GONE);
        hasGatePayphonesError.setVisibility(View.GONE);
        floorTypeField.setErrorEnabled(false);
        gateWidthField.setErrorEnabled(false);
        gateTrailHeightField.setErrorEnabled(false);
    }

    public ExternalAccess createNewAccess() {
        return new ExternalAccess(schoolID,
                getCheckedButton(entranceType),
                getCheckedButton(hasSiaRadio),
                Objects.requireNonNull(floorTypeValue.getText()).toString(),
                Double.parseDouble(Objects.requireNonNull(gateWidthValue.getText()).toString()),
                Double.parseDouble(Objects.requireNonNull(gateTrailHeightValue.getText()).toString()),
                getCheckedButton(hasTrailRampRadio),
                getCheckedButton(hasGateObstaclesRadio),
                getCheckedButton(hasGatePayphonesRadio));
    }

    public void clearFields() {
        entranceType.clearCheck();
        hasSiaRadio.clearCheck();
        floorTypeValue.setText(null);
        gateWidthValue.setText(null);
        gateTrailHeightValue.setText(null);
        hasTrailRampRadio.clearCheck();
        hasGateObstaclesRadio.clearCheck();
        hasGatePayphonesRadio.clearCheck();
    }

}
