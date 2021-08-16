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
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddGateObsDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddPayPhoneDialog;
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

    Bundle extBundle = new Bundle();

    public static final String EXT_ACCESS_ID = "EXT_ACCESS_ID";
    public static final String SCHOOL_ID = "SCHOOL_ID";

    int saveAttempt = 0;

//    TODO - Será usado para os casos onde a informação já foi cadastrada, está sendo acessada novamente para possível alteração
    int existingEntry = 0;
//    TODO - Será usado para casos onde a informação foi gravada recentemente, diferente de informações que já existiam na tabela
    int recentEntry = 0;

    Integer typeExtAccess, hasSia, hasTrailRamp, hasGateObs, hasPayphone;
//    TODO - Implementar contadores de registros, impedir gravação sem ao menos um registro salvo (quando marcado sim)
    //Integer trailCounter, obsCounter, payphoneCounter (a implementar)
    String floorType;
    Double gateWidth, gateTrailHeight;

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

//      Como a verificação estava ocorrendo depois do dialogo ser criado, coloca-se o chamado do dialogo dentro do observer
//      Para garantir que o bundle receba o ID necessário. (existe solução mais elegante?)
//        TODO - Procurar solução possivelmente mais elegante
        modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), lastAccess -> {
            if (recentEntry == 1) {
                lastExtAccess = lastAccess.getExternalAccessID();
                extBundle.putInt(EXT_ACCESS_ID,lastExtAccess);

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

        saveExternalAccess.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                if (upCounter == 0) {
                    ExternalAccess newAccess = newExtAccess(extBundle);
                    ViewModelEntry.insertExternalAccess(newAccess);
                    clearFields();
                } else if (upCounter > 0) {
                    ExternalAccess upAccess = newExtAccess(extBundle);
                    if (recentEntry == 1) {
                        upAccess.setExternalAccessID(lastExtAccess);
                        recentEntry = 0;
                    }
                    if (existingEntry == 1) {
                        // TODO - Inserir método para captar ExternalID
//                        upAccess.setExternalAccessID(lastExtAccess);
                        existingEntry = 0;
                    }
                    ViewModelEntry.updateExternalAccess(upAccess);
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

    public ExternalAccess newExtAccess(Bundle bundle) {
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
            hasPayphone = getCheckedButton(hasGatePayphonesRadio);

        return new ExternalAccess(bundle.getInt(InspectionActivity.SCHOOL_ID_VALUE), typeExtAccess, hasSia, floorType, gateWidth,
                gateTrailHeight, hasTrailRamp, hasGateObs, hasPayphone);
    }

    public void saveUpdateDialogClick() {
        if (upCounter == 0) {
            ExternalAccess newAccess = newExtAccess(extBundle);
            ViewModelEntry.insertExternalAccess(newAccess);
            upCounter++;
            recentEntry = 1;
        } else if (upCounter > 0) {
            upCounter++;
            ExternalAccess upAccess = newExtAccess(extBundle);
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

        if (existingEntry == 1 || recentEntry == 1) {
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
    }

    private void addTrailRampDialog() {
//        AddDoorDialog.displayDoorDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), extBundle);
    }

    private void addGateObsDialog() {
        AddGateObsDialog.displayGateDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), extBundle);
    }

    private void addPayPhoneDialog() {
        AddPayPhoneDialog.displayPayPhoneDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), extBundle);
    }

}
