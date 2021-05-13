package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.ParkingLotInterface;

import java.util.ArrayList;
import java.util.Objects;

public class ParkingLotElderlyFragment extends Fragment implements ParkingLotInterface {

    ConstraintLayout layout;
    TextView fragHeader, vacancyHeader, verticalSignHeader, horizontalSignHeader, pictogramHeader;
    RadioGroup hasVacancy, hasVerticalSign, hasHorizontalSign, hasPictogram;
    Button cancelParkingLotElderly, saveParkingLotElderly;
    TextInputLayout totalVacancyField, verticalSignObsField, horizontalSignWidthField, horizontalSignLengthField, horizontalSignObsField,
            pictogramWidthField, pictogramLengthField, pictogramObsField;
    TextInputEditText totalVacancyValue, verticalSignObsValue, horizontalSignWidthValue, horizontalSignLengthValue, horizontalSignObsValue,
            pictogramWidthValue, pictogramLengthValue, pictogramObsValue;
    ArrayList<TextInputLayout> verticalFields, horizontalFields, pictogramFields;
    ArrayList<TextInputEditText> verticalValues, horizontalValues, pictogramValues;


    public ParkingLotElderlyFragment() {
        // Required empty public constructor
    }

    public static ParkingLotElderlyFragment newInstance() {
        ParkingLotElderlyFragment fragment = new ParkingLotElderlyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_lot_elderly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout = view.findViewById(R.id.parking_lot_elderly_constraint_layout);

        fragHeader = view.findViewById(R.id.frag_parking_lot_elderly_header);
        vacancyHeader = view.findViewById(R.id.parking_lot_elderly_vacancy_header);
        verticalSignHeader = view.findViewById(R.id.vertical_sign_elderly_header);
        horizontalSignHeader = view.findViewById(R.id.horizontal_sign_elderly_header);
        pictogramHeader = view.findViewById(R.id.elderly_pictogram_header);

        hasVacancy = view.findViewById(R.id.parking_lot_elderly_vacancy_radio);
        hasVerticalSign = view.findViewById(R.id.vertical_sign_elderly_radio);
        hasHorizontalSign = view.findViewById(R.id.horizontal_sign_elderly_radio);
        hasPictogram = view.findViewById(R.id.elderly_pictogram_radio);

        totalVacancyField = view.findViewById(R.id.parking_lot_elderly_vacancy_field);
        verticalSignObsField = view.findViewById(R.id.vertical_sign_elderly_obs_field);
        horizontalSignWidthField = view.findViewById(R.id.elderly_horizontal_sign_width_field);
        horizontalSignLengthField = view.findViewById(R.id.elderly_horizontal_sign_length_field);
        horizontalSignObsField = view.findViewById(R.id.horizontal_sign_elderly_obs_field);
        pictogramWidthField = view.findViewById(R.id.elderly_pictogram_width_field);
        pictogramLengthField = view.findViewById(R.id.elderly_pictogram_length_field);
        pictogramObsField = view.findViewById(R.id.elderly_pictogram_obs_field);

        totalVacancyValue = view.findViewById(R.id.parking_lot_elderly_vacancy_value);
        verticalSignObsValue = view.findViewById(R.id.vertical_sign_elderly_obs_value);
        horizontalSignWidthValue = view.findViewById(R.id.elderly_horizontal_sign_width_value);
        horizontalSignLengthValue = view.findViewById(R.id.elderly_horizontal_sign_length_value);
        horizontalSignObsValue = view.findViewById(R.id.horizontal_sign_elderly_obs_value);
        pictogramWidthValue = view.findViewById(R.id.elderly_pictogram_width_value);
        pictogramLengthValue = view.findViewById(R.id.elderly_pictogram_length_value);
        pictogramObsValue = view.findViewById(R.id.elderly_pictogram_obs_value);

        cancelParkingLotElderly  = view.findViewById(R.id.cancel_parking_lot_elderly);
        saveParkingLotElderly = view.findViewById(R.id.save_parking_lot_elderly);

        addValuesToArrays();

        disableEverything(layout);

        hasVacancy.setOnCheckedChangeListener(this::hasParkingLotVacancy);
        hasVerticalSign.setOnCheckedChangeListener(this::enableFields);
        hasHorizontalSign.setOnCheckedChangeListener(this::enableFields);
        hasPictogram.setOnCheckedChangeListener(this::enableFields);

        cancelParkingLotElderly.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        saveParkingLotElderly.setOnClickListener(v -> {
            FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ParkingLotFragment parkingLotFragment = ParkingLotFragment.newInstance();
            fragmentTransaction.replace(R.id.show_fragment_selected, parkingLotFragment).addToBackStack(null).commit();
        });
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
            else if (layoutView.getId() == hasPictogram.getId())
                disableRadioGroup(hasPictogram);
            else if (layoutView.getId() != fragHeader.getId() && layoutView.getId() != vacancyHeader.getId() &&
                    layoutView.getId() != cancelParkingLotElderly.getId() && layoutView.getId() != saveParkingLotElderly.getId()) {
                layoutView.setEnabled(false);
            }
        }
    }

    @Override
    public void enableAllRadioGroups(ConstraintLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View layoutView = layout.getChildAt(i);

            if (layoutView.getId() == hasVerticalSign.getId()) {
                verticalSignHeader.setEnabled(true);
                enableRadioGroup(hasVerticalSign);
            } else if (layoutView.getId() == hasHorizontalSign.getId()) {
                horizontalSignHeader.setEnabled(true);
                enableRadioGroup(hasHorizontalSign);
            } else if (layoutView.getId() == hasPictogram.getId()) {
                pictogramHeader.setEnabled(true);
                enableRadioGroup(hasPictogram);
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
        hasPictogram.clearCheck();

        totalVacancyValue.setText(null);
        verticalSignObsValue.setText(null);
        horizontalSignWidthValue.setText(null);
        horizontalSignLengthValue.setText(null);
        horizontalSignObsValue.setText(null);
        pictogramLengthValue.setText(null);
        pictogramWidthValue.setText(null);
        pictogramObsValue.setText(null);


    }

    @Override
    public void hasParkingLotVacancy(RadioGroup group, int checkedID) {
        RadioButton radioButton = group.findViewById(checkedID);
        int index = group.indexOfChild(radioButton);

        if (index == 0) {
            enableAllRadioGroups(layout);
            totalVacancyField.setEnabled(true);
        } else {
            disableEverything(layout);
        }
    }

    @Override
    public void addValuesToArrays() {
        verticalFields = new ArrayList<>();
        verticalValues = new ArrayList<>();
        horizontalValues = new ArrayList<>();
        horizontalFields = new ArrayList<>();
        pictogramValues = new ArrayList<>();
        pictogramFields = new ArrayList<>();

        verticalFields.add(verticalSignObsField);

        horizontalFields.add(horizontalSignWidthField);
        horizontalFields.add(horizontalSignLengthField);
        horizontalFields.add(horizontalSignObsField);

        pictogramFields.add(pictogramLengthField);
        pictogramFields.add(pictogramWidthField);
        pictogramFields.add(pictogramObsField);

        verticalValues.add(verticalSignObsValue);

        horizontalValues.add(horizontalSignWidthValue);
        horizontalValues.add(horizontalSignLengthValue);
        horizontalValues.add(horizontalSignObsValue);

        pictogramValues.add(pictogramLengthValue);
        pictogramValues.add(pictogramWidthValue);
        pictogramValues.add(pictogramObsValue);
    }

    @Override
    public void enableFields(RadioGroup group, int checkedID) {
        RadioButton radioButton = group.findViewById(checkedID);
        int index = group.indexOfChild(radioButton);

        if (index == 0) {
            if (group == hasVerticalSign) {
                for (TextInputLayout field : verticalFields) {
                    field.setEnabled(true);
                }
            } else if (group == hasHorizontalSign) {
                for (TextInputLayout field : horizontalFields) {
                    field.setEnabled(true);
                }
            } else {
                for (TextInputLayout field : pictogramFields) {
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
            } else {
                for (TextInputEditText value : pictogramValues) {
                    value.setEnabled(false);
                }
                for (TextInputLayout field : pictogramFields) {
                    field.setEnabled(false);
                }
            }
        }
    }
}