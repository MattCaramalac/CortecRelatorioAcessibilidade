package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

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
import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class ExternalAccessFragment extends Fragment {

    RadioGroup entranceType, hasSiaRadio, hasTrailRampRadio, hasGateObstaclesRadio, hasGatePayphonesRadio;
    TextInputLayout floorTypeField, gateWidthField, gateTrailHeightField;
    TextInputEditText floorTypeValue, gateWidthValue, gateTrailHeightValue;
    Button hasTrailRampButton, hasGateObstaclesButton, hasGatePayphonesButton, saveExternalAccess, cancelExternalAccess;
    TextView accessTypeError, hasSiaError, hasTrailRampError, hasGateObstaclesError, hasGatePayphonesError;

    Bundle extBundle = new Bundle();

    int saveAttempt = 0;

//    TODO - Será usado para os casos onde a informação já foi cadastrada, está sendo acessada novamente para possível alteração
    int existingEntry = 0;

    Integer typeExtAccess, hasSia, hasTrailRamp, hasGateObs, hasPayphone;
//    TODO - Implementar contadores de registros, impedir gravação sem ao menos um registro salvo (quando marcado sim)
    //Integer trailCounter, obsCounter, payphoneCounter (a implementar)
    String floorType;
    Double gateWidth, gateTrailHeight;

    private static int schoolID;

    private int extButtonChoice = -1;
    private int upCounter = 0;
    private int lastExtAccess;

    private ViewModelEntry modelEntry;

    public ExternalAccessFragment() {
        // Required empty public constructor
    }

    public static ExternalAccessFragment newInstance() {
        return new ExternalAccessFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extBundle = this.getArguments();
        if(extBundle != null) {
            schoolID = extBundle.getInt(InspectionActivity.SCHOOL_ID_VALUE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

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

        modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), lastAccess -> {
            if (saveAttempt == 1) {
                lastExtAccess = lastAccess.getExternalAccessID();
                Toast.makeText(getContext(), "Teste Observer" + lastExtAccess, Toast.LENGTH_SHORT).show();
                saveAttempt = 0;
            }
        });

        hasTrailRampButton.setOnClickListener(v -> {
            extButtonChoice = 0;
            saveUpdateDialogClick();
        });

        hasGateObstaclesButton.setOnClickListener(v -> {
            extButtonChoice = 1;
            saveUpdateDialogClick();
        });

        hasGatePayphonesButton.setOnClickListener(v -> {
            extButtonChoice = 2;
            saveUpdateDialogClick();
        });

        cancelExternalAccess.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

////        TODO - Alterar o método de salvar para garantir gravação de dados ao clicar nos botões
//        saveExternalAccess.setOnClickListener(v -> {
//            if(checkEmptyFields()) {
//                ExternalAccess newAccess = createNewAccess();
//                ViewModelEntry.insertExternalAccess(newAccess);
//                clearFields();
//                Toast.makeText(getContext(), "Cadastro Efetuado com Sucesso", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        saveExternalAccess.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                if (upCounter == 0) {
                    ExternalAccess newAccess = newExtAccess();
                    ViewModelEntry.insertExternalAccess(newAccess);
                    clearFields();
                } else if (upCounter > 0) {
                    ExternalAccess upAccess = newExtAccess();
                    upAccess.setExternalAccessID(lastExtAccess);
                    ViewModelEntry.updateExternalAccess(upAccess);
                    existingEntry = 0;
                    clearFields();
                } else {
                    Toast.makeText(getContext(), "Algo inesperado ocorreu. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                    clearFields();
                }
            } else
                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
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

    public ExternalAccess newExtAccess() {
        if (entranceType.getCheckedRadioButtonId() != -1)
            typeExtAccess = getCheckedButton(entranceType);
        if (hasSiaRadio.getCheckedRadioButtonId() != -1)
            hasSia = getCheckedButton(hasSiaRadio);
        if (!TextUtils.isEmpty(floorTypeValue.getText()))
            floorType = Objects.requireNonNull(floorTypeValue.getText()).toString();
        if (!TextUtils.isEmpty(gateWidthValue.getText()))
            gateWidth = Double.parseDouble(Objects.requireNonNull(gateWidthValue.getText()).toString());
        if (!TextUtils.isEmpty(gateTrailHeightValue.getText()))
            gateTrailHeight = Double.parseDouble(Objects.requireNonNull(gateTrailHeightValue.getText()).toString());
        if (hasTrailRampRadio.getCheckedRadioButtonId() != -1)
            hasTrailRamp = getCheckedButton(hasTrailRampRadio);
        if (hasGateObstaclesRadio.getCheckedRadioButtonId() != -1)
            hasGateObs = getCheckedButton(hasGateObstaclesRadio);
        if (hasGatePayphonesRadio.getCheckedRadioButtonId() != -1)
            hasTrailRamp = getCheckedButton(hasGatePayphonesRadio);

        return new ExternalAccess(schoolID, typeExtAccess, hasSia, floorType, gateWidth, gateTrailHeight, hasTrailRamp,
                hasGateObs, hasPayphone);
    }

    public void saveUpdateDialogClick() {

        if (upCounter == 0) {
            ExternalAccess newAccess = newExtAccess();
            ViewModelEntry.insertExternalAccess(newAccess);
            upCounter++;
            saveAttempt = 1;
        } else if (upCounter > 0) {
            upCounter++;
            ExternalAccess upAccess = newExtAccess();
            if (existingEntry == 0) {
                upAccess.setExternalAccessID(lastExtAccess);
                ViewModelEntry.updateExternalAccess(upAccess);
            } else if (existingEntry == 1) {
// TODO - Inserir método para captar ExternalID
                ViewModelEntry.updateExternalAccess(upAccess);
            } else {
                existingEntry = 0;
                Toast.makeText(getContext(), "Algo deu errado, tente novamente", Toast.LENGTH_SHORT).show();
            }
        } else {
            upCounter = 0;
            Toast.makeText(getContext(), "Algo deu errado, tente novamente", Toast.LENGTH_SHORT).show();
        }

        switch (extButtonChoice) {
            case 0:
                addTrailRampDialog();
                break;
            case 1:
                addGateObsDialog();
                break;
            case 2:
                addPayPhoneDialog();
                break;
        }
        extButtonChoice = -1;
    }

    private void addTrailRampDialog() {
//        AddDoorDialog.displayDoorDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), roomBundleID);
    }

    private void addGateObsDialog() {
//        AddDoorDialog.displayDoorDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), roomBundleID);
    }

    private void addPayPhoneDialog() {
//        AddDoorDialog.displayDoorDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), roomBundleID);
    }

}
