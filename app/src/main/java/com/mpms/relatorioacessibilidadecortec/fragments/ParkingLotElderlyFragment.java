package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ParkingLotInterface;

import java.util.ArrayList;
import java.util.Objects;

public class ParkingLotElderlyFragment extends Fragment implements ParkingLotInterface {

    ConstraintLayout layout;
    TextView fragHeader, vacancyHeader, verticalSignHeader, horizontalSignHeader, pictogramHeader;
    TextView vacancyError, verticalSignError, horizontalSignError, pictogramError;
    RadioGroup hasVacancy, hasVerticalSign, hasHorizontalSign, hasPictogram;
    Button cancelParkingLotElderly, saveParkingLotElderly;
    TextInputLayout totalVacancyField, verticalSignObsField, horizontalSignWidthField, horizontalSignLengthField, horizontalSignObsField,
            pictogramWidthField, pictogramLengthField, pictogramObsField;
    TextInputEditText totalVacancyValue, verticalSignObsValue, horizontalSignWidthValue, horizontalSignLengthValue, horizontalSignObsValue,
            pictogramWidthValue, pictogramLengthValue, pictogramObsValue;
    ArrayList<TextInputLayout> verticalFields, horizontalFields, pictogramFields;
    ArrayList<TextInputEditText> verticalValues, horizontalValues, pictogramValues, elderlyObsArray;
    ViewModelEntry recentEntry;

    public int saveAttempt = 0;

    private static int schoolID, parkingLotID;

    public static String PARKING_LOT_ID = "PARKING_LOT_ID";
    public static String SCHOOL_ID = "SCHOOL_ID";


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
        Bundle schoolBundle = this.getArguments();
        if (schoolBundle != null) {
            schoolID = schoolBundle.getInt(SCHOOL_ID);
            parkingLotID = schoolBundle.getInt(PARKING_LOT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recentEntry = new ViewModelEntry(requireActivity().getApplication());
        return inflater.inflate(R.layout.fragment_parking_lot_elderly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateElderlyViews(view);
        addValuesToArrays();
        allowElderlyObsScroll();
        disableEverything(layout);

        hasVacancy.setOnCheckedChangeListener(this::hasParkingLotVacancy);
        hasVerticalSign.setOnCheckedChangeListener(this::enableFields);
        hasHorizontalSign.setOnCheckedChangeListener(this::enableFields);
        hasPictogram.setOnCheckedChangeListener(this::enableFields);

        recentEntry.selectElderlyParkingLot(parkingLotID).observe(getViewLifecycleOwner(), elderlyEntry -> {
            if (saveAttempt == 1) {
                saveAttempt = 0;
                clearFields();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ParkingLotFragment parkingLotFragment = ParkingLotFragment.newInstance();
                fragmentTransaction.replace(R.id.show_fragment_selected, parkingLotFragment).addToBackStack(null).commit();
            }
        });

        cancelParkingLotElderly.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        saveParkingLotElderly.setOnClickListener(v -> {
            if (verifyEmptyFields()) {
                ParkingLotElderlyEntry newEntry = newElderlyEntry();
                ViewModelEntry.insertElderlyParkingLot(newEntry);
                saveAttempt = 1;
                Toast.makeText(getContext(), "Informações Salvas com Sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void instantiateElderlyViews(View view) {
        layout = view.findViewById(R.id.parking_lot_elderly_constraint_layout);

        fragHeader = view.findViewById(R.id.frag_parking_lot_elderly_header);
        vacancyHeader = view.findViewById(R.id.parking_lot_elderly_vacancy_header);
        verticalSignHeader = view.findViewById(R.id.vertical_sign_elderly_header);
        horizontalSignHeader = view.findViewById(R.id.horizontal_sign_elderly_header);
        pictogramHeader = view.findViewById(R.id.elderly_pictogram_header);
        vacancyError = view.findViewById(R.id.elderly_vacancy_error);
        verticalSignError = view.findViewById(R.id.elderly_vertical_sign_error);
        horizontalSignError = view.findViewById(R.id.elderly_horizontal_sign_error);
        pictogramError = view.findViewById(R.id.elderly_pictogram_error);

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

        cancelParkingLotElderly = view.findViewById(R.id.cancel_parking_lot_elderly);
        saveParkingLotElderly = view.findViewById(R.id.save_parking_lot_elderly);
    }

    public void clearErrorMessages() {
        vacancyError.setVisibility(View.GONE);
        verticalSignError.setVisibility(View.GONE);
        horizontalSignError.setVisibility(View.GONE);
        pictogramError.setVisibility(View.GONE);
        totalVacancyField.setErrorEnabled(false);
        horizontalSignLengthField.setErrorEnabled(false);
        horizontalSignWidthField.setErrorEnabled(false);
        pictogramWidthField.setErrorEnabled(false);
        pictogramLengthField.setErrorEnabled(false);
    }

    public boolean verifyEmptyFields() {
        clearErrorMessages();
        int i = 0;
        if (hasVacancy.getCheckedRadioButtonId() == -1) {
            vacancyError.setEnabled(true);
            vacancyError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadio(hasVacancy) == 1) {
            if (TextUtils.isEmpty(totalVacancyValue.getText())) {
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
            if (hasPictogram.getCheckedRadioButtonId() == -1) {
                pictogramError.setEnabled(true);
                pictogramError.setVisibility(View.VISIBLE);
                i++;
            } else if (getCheckedRadio(hasPictogram) == 1) {
                if (TextUtils.isEmpty(pictogramLengthValue.getText())) {
                    pictogramLengthField.setError(getString(R.string.blank_field_error));
                    i++;
                }
                if (TextUtils.isEmpty(pictogramWidthValue.getText())) {
                    pictogramWidthField.setError(getString(R.string.blank_field_error));
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
        clearErrorMessages();
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

        if (index == 1) {
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
        elderlyObsArray = new ArrayList<>();

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

        elderlyObsArray.add(verticalSignObsValue);
        elderlyObsArray.add(horizontalSignObsValue);
        elderlyObsArray.add(pictogramObsValue);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowElderlyObsScroll() {
        for (TextInputEditText obsScroll : elderlyObsArray) {
            obsScroll.setOnTouchListener(this::scrollingField);
        }
    }

    @Override
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
            } else {
                for (TextInputLayout field : pictogramFields) {
                    field.setEnabled(true);
                }
            }
        } else {
            if (group == hasVerticalSign) {
                for (TextInputEditText value : verticalValues) {
                    value.setText(null);
                }
                for (TextInputLayout field : verticalFields) {
                    field.setEnabled(false);
                    field.setErrorEnabled(false);
                }
            } else if (group == hasHorizontalSign) {
                for (TextInputEditText value : horizontalValues) {
                    value.setText(null);
                }
                for (TextInputLayout field : horizontalFields) {
                    field.setEnabled(false);
                    field.setErrorEnabled(false);
                }
            } else {
                for (TextInputEditText value : pictogramValues) {
                    value.setText(null);
                }
                for (TextInputLayout field : pictogramFields) {
                    field.setEnabled(false);
                    field.setErrorEnabled(false);
                }
            }
        }
    }

    public ParkingLotElderlyEntry newElderlyEntry() {
        int totalVacancy;
        Double horizontalSignWidth, horizontalSignLength, pictogramWidth, pictogramLength;
        String vertSingObs, horizontalSignObs, pictogramObs;
        ParkingLotElderlyEntry newEntry;
        if (getCheckedRadio(hasVacancy) == 0) {
            newEntry = new ParkingLotElderlyEntry(schoolID, parkingLotID, getCheckedRadio(hasVacancy), null,
                    null, null, null, null, null, null,
                    null, null, null, null);
        } else {
            totalVacancy = Integer.parseInt(Objects.requireNonNull(totalVacancyValue.getText()).toString());
            if (getCheckedRadio(hasVerticalSign) == 0)
                vertSingObs = null;
            else {
                if (TextUtils.isEmpty(verticalSignObsValue.getText()))
                    vertSingObs = null;
                else
                    vertSingObs = Objects.requireNonNull(verticalSignObsValue.getText()).toString();
            }
            if (getCheckedRadio(hasHorizontalSign) == 0) {
                horizontalSignWidth = null;
                horizontalSignLength = null;
                horizontalSignObs = null;
            } else {
                horizontalSignLength = Double.parseDouble(Objects.requireNonNull(horizontalSignLengthValue.getText()).toString());
                horizontalSignWidth = Double.parseDouble(Objects.requireNonNull(horizontalSignWidthValue.getText()).toString());
                if (TextUtils.isEmpty(horizontalSignObsValue.getText()))
                    horizontalSignObs = null;
                else
                    horizontalSignObs = Objects.requireNonNull(horizontalSignObsValue.getText()).toString();
            }
            if (getCheckedRadio(hasPictogram) == 0) {
                pictogramLength = null;
                pictogramWidth = null;
                pictogramObs = null;
            } else {
                pictogramLength = Double.parseDouble(Objects.requireNonNull(pictogramLengthValue.getText()).toString());
                pictogramWidth = Double.parseDouble(Objects.requireNonNull(pictogramWidthValue.getText()).toString());
                if (TextUtils.isEmpty(pictogramObsValue.getText()))
                    pictogramObs = null;
                else
                    pictogramObs = Objects.requireNonNull(pictogramObsValue.getText()).toString();
            }

            newEntry = new ParkingLotElderlyEntry(schoolID, parkingLotID, getCheckedRadio(hasVacancy), totalVacancy, getCheckedRadio(hasVerticalSign), vertSingObs,
                    getCheckedRadio(hasHorizontalSign), horizontalSignWidth, horizontalSignLength, horizontalSignObs, getCheckedRadio(hasPictogram), pictogramWidth,
                    pictogramLength, pictogramObs);

        }
        return newEntry;
    }
}