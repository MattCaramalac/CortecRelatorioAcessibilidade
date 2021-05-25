package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.ParkingLotInterface;

import java.util.ArrayList;
import java.util.Objects;


public class ParkingLotPdmrFragment extends Fragment implements ParkingLotInterface {

    ConstraintLayout layout;
    TextView fragHeader, vacancyHeader, verticalSignHeader, horizontalSignHeader, safetyZoneHeader, siaHeader;
    TextView pdmrVacancyError, verticalSignError, horizontalSignError, safetyZoneError,siaError;
    RadioGroup hasVacancy, hasVerticalSign, hasHorizontalSign, hasSafetyZone, hasSiaPdmr;
    Button cancelParkingLotPdmr, proceedParkingLotPdmr;
    TextInputLayout totalVacancyField, verticalSignObsField, horizontalSignWidthField, horizontalSignLengthField, horizontalSignObsField,
            safetyZoneWidthField, safetyZoneObsField, siaWidthField, siaLengthField, siaObsField;
    TextInputEditText totalVacancyValue, verticalSignObsValue, horizontalSignWidthValue, horizontalSignLengthValue, horizontalSignObsValue,
            safetyZoneWidthValue, safetyZoneObsValue, siaWidthValue, siaLengthValue, siaObsValue;
    ArrayList<TextInputLayout> verticalFields, horizontalFields, safetyFields, siaFields;
    ArrayList<TextInputEditText> verticalValues, horizontalValues, safetyValues, siaValues;

    public ParkingLotPdmrFragment() {
        // Required empty public constructor
    }

    public static ParkingLotPdmrFragment newInstance() {
        return new ParkingLotPdmrFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_lot_pdmr, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout = view.findViewById(R.id.parking_lot_PDMR_constraint_layout);

        fragHeader = view.findViewById(R.id.frag_parking_lot_PDMR_header);
        vacancyHeader = view.findViewById(R.id.parking_lot_PDMR_vacancy_header);
        verticalSignHeader = view.findViewById(R.id.vertical_sign_PDMR_header);
        horizontalSignHeader = view.findViewById(R.id.horizontal_sign_PDMR_header);
        safetyZoneHeader = view.findViewById(R.id.safety_zone_PDMR_header);
        siaHeader = view.findViewById(R.id.PDMR_SIA_header);
        pdmrVacancyError = view.findViewById(R.id.PDMR_vacancy_error);
        verticalSignError = view.findViewById(R.id.vertical_sign_error);
        horizontalSignError = view.findViewById(R.id.horizontal_sign_error);
        safetyZoneError = view.findViewById(R.id.safety_zone_error);
        siaError = view.findViewById(R.id.PDMR_SIA_error);

        hasVacancy = view.findViewById(R.id.parking_lot_PDMR_vacancy_radio);
        hasVerticalSign = view.findViewById(R.id.vertical_sign_PDMR_radio);
        hasHorizontalSign = view.findViewById(R.id.horizontal_sign_PDMR_radio);
        hasSafetyZone = view.findViewById(R.id.safety_zone_PDMR_radio);
        hasSiaPdmr = view.findViewById(R.id.PDMR_SIA_radio);

        totalVacancyField = view.findViewById(R.id.parking_lot_PDMR_vacancy_field);
        verticalSignObsField = view.findViewById(R.id.vertical_sign_PDMR_obs_field);
        horizontalSignWidthField = view.findViewById(R.id.PDMR_horizontal_sign_width_field);
        horizontalSignLengthField = view.findViewById(R.id.PDMR_horizontal_sign_length_field);
        horizontalSignObsField = view.findViewById(R.id.horizontal_sign_PDMR_obs_field);
        safetyZoneWidthField = view.findViewById(R.id.safety_zone_PDMR_width_field);
        safetyZoneObsField = view.findViewById(R.id.safety_zone_PDMR_obs_field);
        siaWidthField = view.findViewById(R.id.PDMR_SIA_width_field);
        siaLengthField = view.findViewById(R.id.PDMR_SIA_length_field);
        siaObsField = view.findViewById(R.id.PDMR_SIA_obs_field);

        totalVacancyValue = view.findViewById(R.id.parking_lot_PDMR_vacancy_value);
        verticalSignObsValue = view.findViewById(R.id.vertical_sign_PDMR_obs_value);
        horizontalSignWidthValue = view.findViewById(R.id.PDMR_horizontal_sign_width_value);
        horizontalSignLengthValue = view.findViewById(R.id.PDMR_horizontal_sign_length_value);
        horizontalSignObsValue = view.findViewById(R.id.horizontal_sign_PDMR_obs_value);
        safetyZoneWidthValue = view.findViewById(R.id.safety_zone_PDMR_width_value);
        safetyZoneObsValue = view.findViewById(R.id.safety_zone_PDMR_obs_value);
        siaWidthValue = view.findViewById(R.id.PDMR_SIA_width_value);
        siaLengthValue = view.findViewById(R.id.PDMR_SIA_length_value);
        siaObsValue = view.findViewById(R.id.PDMR_SIA_obs_value);

        cancelParkingLotPdmr = view.findViewById(R.id.cancel_parking_lot_pdmr);
        proceedParkingLotPdmr = view.findViewById(R.id.save_parking_lot_pdmr);

        addValuesToArrays();

        disableEverything(layout);

        hasVacancy.setOnCheckedChangeListener(this::hasParkingLotVacancy);
        hasVerticalSign.setOnCheckedChangeListener(this::enableFields);
        hasHorizontalSign.setOnCheckedChangeListener(this::enableFields);
        hasSafetyZone.setOnCheckedChangeListener(this::enableFields);
        hasSiaPdmr.setOnCheckedChangeListener(this::enableFields);

        cancelParkingLotPdmr.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        proceedParkingLotPdmr.setOnClickListener(v -> {
            if (verifyEmptyFields()) {
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ParkingLotElderlyFragment parkingLotElderlyFragment = ParkingLotElderlyFragment.newInstance();
                fragmentTransaction.replace(R.id.show_fragment_selected, parkingLotElderlyFragment).addToBackStack(null).commit();
            }
        });



    }

    public void clearErrorMessages() {
        pdmrVacancyError.setVisibility(View.GONE);
        verticalSignError.setVisibility(View.GONE);
        horizontalSignError.setVisibility(View.GONE);
        safetyZoneError.setVisibility(View.GONE);
        siaError.setVisibility(View.GONE);
        totalVacancyField.setError(null);
        horizontalSignLengthField.setError(null);
        horizontalSignWidthField.setError(null);
        safetyZoneWidthField.setError(null);
        siaWidthField.setError(null);
        siaLengthField.setError(null);
    }

    public boolean verifyEmptyFields() {
        clearErrorMessages();
        int i = 0;
        if (hasVacancy.getCheckedRadioButtonId() == -1) {
            pdmrVacancyError.setEnabled(true);
            pdmrVacancyError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadio(hasVacancy) == 1) {
            if (TextUtils.isEmpty(totalVacancyValue.getText()))  {
                totalVacancyField.setError(getString(R.string.blank_field_error));
                i++;
            }
            if (hasVerticalSign.getCheckedRadioButtonId() == -1) {
                verticalSignError.setEnabled(true);
                verticalSignError.setVisibility(View.VISIBLE);
                i++;
            }
            if (hasHorizontalSign.getCheckedRadioButtonId() == -1) {
                horizontalSignError.setEnabled(true);
                horizontalSignError.setVisibility(View.VISIBLE);
                i++;
            } else if (getCheckedRadio(hasHorizontalSign) == 1) {
                if (TextUtils.isEmpty(horizontalSignWidthValue.getText())) {
                    horizontalSignWidthField.setError(getString(R.string.blank_field_error));
                    i++;
                }
                if (TextUtils.isEmpty(horizontalSignLengthValue.getText())) {
                    horizontalSignLengthField.setError(getString(R.string.blank_field_error));
                    i++;
                }
            }
            if (hasSafetyZone.getCheckedRadioButtonId() == -1) {
                safetyZoneError.setEnabled(true);
                safetyZoneError.setVisibility(View.VISIBLE);
                i++;
            } else if (getCheckedRadio(hasSafetyZone) == 1) {
                if (TextUtils.isEmpty(safetyZoneWidthValue.getText())) {
                    safetyZoneWidthField.setError(getString(R.string.blank_field_error));
                    i++;
                }
            }
            if (hasSiaPdmr.getCheckedRadioButtonId() == -1) {
                siaError.setEnabled(true);
                siaError.setVisibility(View.VISIBLE);
                i++;
            } else if (getCheckedRadio(hasSiaPdmr) == 1) {
                if (TextUtils.isEmpty(siaWidthValue.getText())) {
                    siaWidthField.setError(getString(R.string.blank_field_error));
                    i++;
                }
                if (TextUtils.isEmpty(siaLengthValue.getText())) {
                    siaLengthField.setError(getString(R.string.blank_field_error));
                    i++;
                }
            }
        }
        return i == 0;
    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    @Override
    public void disableEverything(ConstraintLayout layout) {
        clearFields();
        for (int i = 0; i < layout.getChildCount(); i++) {
            View layoutView = layout.getChildAt(i);

            if (layoutView.getId() == hasVerticalSign.getId())
                disableRadioGroup(hasVerticalSign);
            else if (layoutView.getId() == hasHorizontalSign.getId())
                disableRadioGroup(hasHorizontalSign);
            else if (layoutView.getId() == hasSafetyZone.getId())
                disableRadioGroup(hasSafetyZone);
            else if (layoutView.getId() == hasSiaPdmr.getId())
                disableRadioGroup(hasSiaPdmr);
            else if (layoutView.getId() != fragHeader.getId() && layoutView.getId() != vacancyHeader.getId() &&
                    layoutView.getId() != cancelParkingLotPdmr.getId() && layoutView.getId() != proceedParkingLotPdmr.getId()) {
                layoutView.setEnabled(false);
            }
        }
    }

    @Override
    public void enableAllRadioGroups(ConstraintLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View layoutView = layout.getChildAt(i);

            if (layoutView.getId() == hasVerticalSign.getId()){
                verticalSignHeader.setEnabled(true);
                enableRadioGroup(hasVerticalSign);
            }
            else if (layoutView.getId() == hasHorizontalSign.getId()) {
                horizontalSignHeader.setEnabled(true);
                enableRadioGroup(hasHorizontalSign);
            }
            else if (layoutView.getId() == hasSafetyZone.getId()){
                safetyZoneHeader.setEnabled(true);
                enableRadioGroup(hasSafetyZone);
            }
            else if (layoutView.getId() == hasSiaPdmr.getId()) {
                siaHeader.setEnabled(true);
                enableRadioGroup(hasSiaPdmr);
            }
        }
    }

    @Override
    public void disableRadioGroup(RadioGroup radioGroup) {
        for (int j = 0; j < radioGroup.getChildCount(); j++) {
            radioGroup.getChildAt(j).setEnabled(false);
        }
    }

    @Override
    public void enableRadioGroup(RadioGroup radioGroup) {
        for (int j = 0; j < radioGroup.getChildCount(); j++) {
            radioGroup.getChildAt(j).setEnabled(true);
        }
    }

    @Override
    public void clearFields() {
        hasVerticalSign.clearCheck();
        hasHorizontalSign.clearCheck();
        hasSafetyZone.clearCheck();
        hasSiaPdmr.clearCheck();

        totalVacancyValue.setText(null);
        verticalSignObsValue.setText(null);
        horizontalSignWidthValue.setText(null);
        horizontalSignLengthValue.setText(null);
        horizontalSignObsValue.setText(null);
        safetyZoneWidthValue.setText(null);
        safetyZoneObsValue.setText(null);
        siaWidthValue.setText(null);
        siaLengthValue.setText(null);
        siaObsValue.setText(null);
    }

    @Override
    public void hasParkingLotVacancy(RadioGroup group, int checkedID) {
        RadioButton radioButton = group.findViewById(checkedID);
        int index = group.indexOfChild(radioButton);

        if (index == 1) {
            enableAllRadioGroups(layout);
            totalVacancyField.setEnabled(true);
        } else {
            disableEverything(layout);
        }

    }

    public void addValuesToArrays() {
        verticalFields = new ArrayList<>();
        verticalValues = new ArrayList<>();
        horizontalValues = new ArrayList<>();
        horizontalFields = new ArrayList<>();
        safetyValues = new ArrayList<>();
        safetyFields = new ArrayList<>();
        siaValues = new ArrayList<>();
        siaFields = new ArrayList<>();

        verticalFields.add(verticalSignObsField);

        horizontalFields.add(horizontalSignWidthField);
        horizontalFields.add(horizontalSignLengthField);
        horizontalFields.add(horizontalSignObsField);

        safetyFields.add(safetyZoneWidthField);
        safetyFields.add(safetyZoneObsField);

        siaFields.add(siaWidthField);
        siaFields.add(siaLengthField);
        siaFields.add(siaObsField);

        verticalValues.add(verticalSignObsValue);

        horizontalValues.add(horizontalSignWidthValue);
        horizontalValues.add(horizontalSignLengthValue);
        horizontalValues.add(horizontalSignObsValue);

        safetyValues.add(safetyZoneWidthValue);
        safetyValues.add(safetyZoneObsValue);

        siaValues.add(siaWidthValue);
        siaValues.add(siaLengthValue);
        siaValues.add(siaObsValue);
    }

    public void enableFields(RadioGroup group, int checkedID) {
        RadioButton radioButton = group.findViewById(checkedID);
        int index = group.indexOfChild(radioButton);

        if (index == 1) {
            if (group == hasVerticalSign) {
                for (TextInputLayout field : verticalFields) {
                    field.setEnabled(true);
                }
            } else if (group == hasHorizontalSign) {
                for (TextInputLayout field : horizontalFields) {
                    field.setEnabled(true);
                }
            } else if (group == hasSafetyZone) {
                for (TextInputLayout field : safetyFields) {
                    field.setEnabled(true);
                }
            } else {
                for (TextInputLayout field : siaFields) {
                    field.setEnabled(true);
                }
            }
        } else {
            if (group == hasVerticalSign) {
                for (TextInputEditText value : verticalValues) {
                    value.setEnabled(false);
                }
                for (TextInputLayout field : verticalFields) {
                    field.setEnabled(false);
                }
            } else if (group == hasHorizontalSign) {
                for (TextInputEditText value : horizontalValues) {
                    value.setEnabled(false);
                }
                for (TextInputLayout field : horizontalFields) {
                    field.setEnabled(false);
                }
            } else if (group == hasSafetyZone) {
                for (TextInputEditText value : safetyValues) {
                    value.setEnabled(false);
                }
                for (TextInputLayout field : safetyFields) {
                    field.setEnabled(false);
                }
            } else {
                for (TextInputEditText value : siaValues) {
                    value.setEnabled(false);
                }
                for (TextInputLayout field : siaFields) {
                    field.setEnabled(false);
                }
            }
        }
    }



}