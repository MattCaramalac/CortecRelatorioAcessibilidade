package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.ArrayList;

public class ParkingLotPcdFragment extends Fragment {

    public static final String PCD_ID = "PCD_ID";

    TextView pcdVertError, pcdSafetyZoneError, pcdSiaError;
    RadioGroup hasVerticalSign, hasSafetyZone, hasSiaPcd;
    Button cancelParkingLotPcd, saveParkingLotPcd;
    TextInputLayout pcdVertLengthField, pcdVertWidthField, pcdVertSignObsField, pcdVacancyLengthField,
            pcdVacancyWidthField, pcdVacLimiterWidthField, pcdVacancyObsField, safetyZoneWidthField,
            safetyZoneObsField, siaLengthField, siaWidthField, siaObsField;
    TextInputEditText pcdVertLengthValue, pcdVertWidthValue, pcdVertSignObsValue, pcdVacancyLengthValue,
            pcdVacancyWidthValue, pcdVacLimiterWidthValue, pcdVacancyObsValue, safetyZoneWidthValue,
            safetyZoneObsValue, siaLengthValue, siaWidthValue, siaObsValue;
    ArrayList<TextInputLayout> verticalFields, safetyFields, siaFields;

    public Bundle pcdBundle = new Bundle();

    ViewModelEntry modelEntry;

    public ParkingLotPcdFragment() {
        // Required empty public constructor
    }

    public static ParkingLotPcdFragment newInstance() {
        return new ParkingLotPcdFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            pcdBundle.putInt(ParkingLotListFragment.PARKING_ID, this.getArguments().getInt(ParkingLotListFragment.PARKING_ID));
            pcdBundle.putInt(PCD_ID, this.getArguments().getInt(PCD_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_lot_pcd, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiatePcdViews(view);

        hasVerticalSign.setOnCheckedChangeListener(this::radioListener);
        hasSafetyZone.setOnCheckedChangeListener(this::radioListener);
        hasSiaPcd.setOnCheckedChangeListener(this::radioListener);

        if (pcdBundle.getInt(PCD_ID) > 0)
            modelEntry.getOnePcdParkingLot(pcdBundle.getInt(PCD_ID)).observe(getViewLifecycleOwner(), this::gatherPcdLotData);

        cancelParkingLotPcd.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveParkingLotPcd.setOnClickListener(v -> {
            if (verifyEmptyPcdFields()) {
                ParkingLotPCDEntry pcdEntry = newPcdEntry(pcdBundle);
                if (pcdBundle.getInt(PCD_ID) == 0) {
                    ViewModelEntry.insertPcdParkingLot(pcdEntry);
                    clearPcdFields();
                    Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                } else if (pcdBundle.getInt(PCD_ID) > 0) {
                    pcdEntry.setParkingPcdID(pcdBundle.getInt(PCD_ID));
                    ViewModelEntry.updatePcdParkingLot(pcdEntry);
                    clearPcdFields();
                    Toast.makeText(getContext(), "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack(ParkingLotFragment.PCD_LIST, 0);
                } else {
                    pcdBundle.putInt(PCD_ID, 0);
                    Toast.makeText(getContext(), "Algo deu errado. Por favor, tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void instantiatePcdViews(View view) {
//        TextViews

        pcdVertError = view.findViewById(R.id.vertical_sign_error);
        pcdSafetyZoneError = view.findViewById(R.id.safety_zone_error);
        pcdSiaError = view.findViewById(R.id.PCD_SIA_error);
//        RadioGroups
        hasVerticalSign = view.findViewById(R.id.vertical_sign_PDMR_radio);
        hasSafetyZone = view.findViewById(R.id.safety_zone_PCD_radio);
        hasSiaPcd = view.findViewById(R.id.PCD_SIA_radio);
//        TextInputLayout
        pcdVertLengthField = view.findViewById(R.id.vertical_sign_PCD_length_field);
        pcdVertWidthField = view.findViewById(R.id.vertical_sign_PCD_width_field);
        pcdVertSignObsField = view.findViewById(R.id.vertical_sign_PCD_obs_field);
        pcdVacancyLengthField = view.findViewById(R.id.PCD_vacancy_length_field);
        pcdVacancyWidthField = view.findViewById(R.id.PCD_vacancy_width_field);
        pcdVacLimiterWidthField = view.findViewById(R.id.PCD_vacancy_limiter_width_field);
        pcdVacancyObsField = view.findViewById(R.id.PCD_vacancy_obs_field);
        safetyZoneWidthField = view.findViewById(R.id.safety_zone_PCD_width_field);
        safetyZoneObsField = view.findViewById(R.id.safety_zone_PCD_obs_field);
        siaLengthField = view.findViewById(R.id.PCD_SIA_length_field);
        siaWidthField = view.findViewById(R.id.PCD_SIA_width_field);
        siaObsField = view.findViewById(R.id.PCD_SIA_obs_field);
//        TextInputEditText
        pcdVertLengthValue = view.findViewById(R.id.vertical_sign_PCD_length_value);
        pcdVertWidthValue = view.findViewById(R.id.vertical_sign_PCD_width_value);
        pcdVertSignObsValue = view.findViewById(R.id.vertical_sign_PCD_obs_value);
        pcdVacancyLengthValue = view.findViewById(R.id.PCD_vacancy_length_value);
        pcdVacancyWidthValue = view.findViewById(R.id.PCD_vacancy_width_value);
        pcdVacLimiterWidthValue = view.findViewById(R.id.PCD_vacancy_limiter_width_value);
        pcdVacancyObsValue = view.findViewById(R.id.PCD_vacancy_obs_value);
        safetyZoneWidthValue = view.findViewById(R.id.safety_zone_PCD_width_value);
        safetyZoneObsValue = view.findViewById(R.id.safety_zone_PCD_obs_value);
        siaLengthValue = view.findViewById(R.id.PCD_SIA_length_value);
        siaWidthValue = view.findViewById(R.id.PCD_SIA_width_value);
        siaObsValue = view.findViewById(R.id.PCD_SIA_obs_value);
//        MaterialButton
        cancelParkingLotPcd = view.findViewById(R.id.cancel_parking_lot_pcd);
        saveParkingLotPcd = view.findViewById(R.id.save_parking_lot_pcd);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        createArrays();
    }

    private void createArrays() {
        verticalFields = new ArrayList<>();
        safetyFields = new ArrayList<>();
        siaFields = new ArrayList<>();

        verticalFields.add(pcdVertLengthField);
        verticalFields.add(pcdVertWidthField);

        safetyFields.add(safetyZoneWidthField);

        siaFields.add(siaLengthField);
        siaFields.add(siaWidthField);

    }

    private void radioListener(RadioGroup group, int checkedID) {
        int index = group.indexOfChild(group.findViewById(checkedID));
        if (group == hasVerticalSign) {
            if (index == 1)
                for (TextInputLayout field : verticalFields)
                    field.setVisibility(View.VISIBLE);
            else {
                for (TextInputLayout field : verticalFields) {
                    field.getEditText().setText(null);
                    field.setVisibility(View.GONE);
                }
            }
        } else if (group == hasSafetyZone) {
            if (index == 1)
                for (TextInputLayout field : safetyFields)
                    field.setVisibility(View.VISIBLE);
            else {
                for (TextInputLayout field : safetyFields) {
                    field.getEditText().setText(null);
                    field.setVisibility(View.GONE);
                }
            }
        } else if (group == hasSiaPcd) {
            if (index == 1)
                for (TextInputLayout field : siaFields)
                    field.setVisibility(View.VISIBLE);
            else {
                for (TextInputLayout field : siaFields) {
                    field.getEditText().setText(null);
                    field.setVisibility(View.GONE);
                }
            }
        } else {
            Toast.makeText(getContext(), "Algo deu errado. Por favor, tente novamente!", Toast.LENGTH_SHORT).show();
        }
    }

    private void gatherPcdLotData(ParkingLotPCDEntry pcdEntry) {
        hasVerticalSign.check(hasVerticalSign.getChildAt(pcdEntry.getHasVisualPcdVertSign()).getId());
        if (pcdEntry.getHasVisualPcdVertSign() == 1) {
            pcdVertLengthValue.setText(String.valueOf(pcdEntry.getVertPcdSignLength()));
            pcdVertWidthValue.setText(String.valueOf(pcdEntry.getVertPcdSignWidth()));
        }
        pcdVertSignObsValue.setText(pcdEntry.getVertPcdSignObs());
        pcdVacancyLengthValue.setText(String.valueOf(pcdEntry.getPcdVacancyLength()));
        pcdVacancyWidthValue.setText(String.valueOf(pcdEntry.getPcdVacancyWidth()));
        pcdVacLimiterWidthValue.setText(String.valueOf(pcdEntry.getPcdVacancyLimitWidth()));
        pcdVacancyObsValue.setText(pcdEntry.getPcdVacancyObs());
        hasSafetyZone.check(hasSafetyZone.getChildAt(pcdEntry.getHasSecurityZone()).getId());
        if (pcdEntry.getHasSecurityZone() == 1)
            safetyZoneWidthValue.setText(String.valueOf(pcdEntry.getSecurityZoneWidth()));
        hasSiaPcd.check(hasSiaPcd.getChildAt(pcdEntry.getHasPcdSia()).getId());
        if (pcdEntry.getHasPcdSia() == 1) {
            siaLengthValue.setText(String.valueOf(pcdEntry.getPcdSiaLength()));
            siaWidthValue.setText(String.valueOf(pcdEntry.getPcdSiaWidth()));
        }
        siaObsValue.setText(pcdEntry.getPcdSiaObs());
    }

    private void clearErrorMessages() {
        pcdVertError.setVisibility(View.GONE);
        pcdSafetyZoneError.setVisibility(View.GONE);
        pcdSiaError.setVisibility(View.GONE);
        pcdVertLengthField.setErrorEnabled(false);
        pcdVertWidthField.setErrorEnabled(false);
        pcdVacancyLengthField.setErrorEnabled(false);
        pcdVacancyWidthField.setErrorEnabled(false);
        pcdVacLimiterWidthField.setErrorEnabled(false);
        safetyZoneWidthField.setErrorEnabled(false);
        siaWidthField.setErrorEnabled(false);
        siaLengthField.setErrorEnabled(false);
    }

    private void clearPcdFields() {
        hasVerticalSign.clearCheck();
        hasSiaPcd.clearCheck();
        hasSafetyZone.clearCheck();
        pcdVertLengthValue.setText(null);
        pcdVertWidthValue.setText(null);
        pcdVertSignObsValue.setText(null);
        pcdVacancyLengthValue.setText(null);
        pcdVacancyWidthValue.setText(null);
        pcdVacLimiterWidthValue.setText(null);
        pcdVacancyObsValue.setText(null);
        safetyZoneWidthValue.setText(null);
        safetyZoneObsValue.setText(null);
        siaLengthValue.setText(null);
        siaWidthValue.setText(null);
        siaObsValue.setText(null);
    }

    private boolean verifyEmptyPcdFields() {
        clearErrorMessages();
        int i = 0;
        if (getCheckedRadio(hasVerticalSign) == -1) {
            i++;
            pcdVertError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(hasVerticalSign) == 1) {
            if (TextUtils.isEmpty(pcdVertLengthValue.getText())) {
                i++;
                pcdVacancyLengthField.setError(getString(R.string.blank_field_error));
            }
            if (TextUtils.isEmpty(pcdVertWidthValue.getText())) {
                i++;
                pcdVacancyWidthField.setError(getString(R.string.blank_field_error));
            }
        }
        if (TextUtils.isEmpty(pcdVacancyLengthValue.getText())) {
            i++;
            pcdVacancyLengthField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(pcdVacancyWidthValue.getText())) {
            i++;
            pcdVacancyWidthField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(pcdVacLimiterWidthValue.getText())) {
            i++;
            pcdVacLimiterWidthField.setError(getString(R.string.blank_field_error));
        }
        if (getCheckedRadio(hasSafetyZone) == -1) {
            i++;
            pcdSafetyZoneError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(hasSafetyZone) == 1) {
            if (TextUtils.isEmpty(safetyZoneWidthValue.getText())) {
                i++;
                safetyZoneWidthField.setError(getString(R.string.blank_field_error));
            }
        }
        if (getCheckedRadio(hasSiaPcd) == -1) {
            i++;
            pcdSiaError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(hasSiaPcd) == 1) {
            if (TextUtils.isEmpty(siaLengthValue.getText())) {
                i++;
                siaLengthField.setError(getString(R.string.blank_field_error));
            }
            if (TextUtils.isEmpty(siaWidthValue.getText())) {
                i++;
                siaWidthField.setError(getString(R.string.blank_field_error));
            }
        }
        return i == 0;
    }

    private int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public ParkingLotPCDEntry newPcdEntry(Bundle bundle) {
        int hasPcdVertSign, hasPcdSafety, hasPcdSia;
        double vacancyLength, vacancyWidth, vacancyLimiterWidth;
        Double pcdVertSingLength = null, pcdVertSignWidth = null,
                safetyZoneWidth = null, pcdSiaLength = null, pcdSiaWidth = null;
        String pcdVertSignObs, pcdVacancyObs, safetyZoneObs, pcdSiaObs;

        hasPcdVertSign = getCheckedRadio(hasVerticalSign);
        if (hasPcdVertSign == 1) {
            pcdVertSingLength = Double.valueOf(String.valueOf(pcdVertLengthValue.getText()));
            pcdVertSignWidth = Double.valueOf(String.valueOf(pcdVertWidthValue.getText()));
        }
        pcdVertSignObs = String.valueOf(pcdVertSignObsValue.getText());
        vacancyLength = Double.parseDouble(String.valueOf(pcdVacancyLengthValue.getText()));
        vacancyWidth = Double.parseDouble(String.valueOf(pcdVacancyWidthValue.getText()));
        vacancyLimiterWidth = Double.parseDouble(String.valueOf(pcdVacLimiterWidthValue.getText()));
        pcdVacancyObs = String.valueOf(pcdVacancyObsValue.getText());
        hasPcdSafety = getCheckedRadio(hasSafetyZone);
        if (hasPcdSafety == 1) {
            safetyZoneWidth = Double.valueOf(String.valueOf(safetyZoneWidthValue.getText()));
        }
        safetyZoneObs = String.valueOf(safetyZoneObsValue.getText());
        hasPcdSia = getCheckedRadio(hasSiaPcd);
        if (hasPcdSia == 1) {
            pcdSiaLength = Double.valueOf(String.valueOf(siaLengthValue.getText()));
            pcdSiaWidth = Double.valueOf(String.valueOf(siaWidthValue.getText()));
        }
        pcdSiaObs = String.valueOf(siaObsValue.getText());

        return new ParkingLotPCDEntry(bundle.getInt(ParkingLotListFragment.PARKING_ID), hasPcdVertSign,
                pcdVertSingLength, pcdVertSignWidth, pcdVertSignObs, vacancyLength, vacancyWidth, vacancyLimiterWidth,
                pcdVacancyObs, hasPcdSafety, safetyZoneWidth, safetyZoneObs, hasPcdSia, pcdSiaLength,
                pcdSiaWidth, pcdSiaObs);
    }


}

