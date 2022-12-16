package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

public class ParkLotElderlyFragment extends Fragment implements TagInterface {

    TextView verticalSignError, floorSingError;
    RadioGroup hasVerticalSign, hasFloorSign;
    MaterialButton cancelParkingLotElderly, saveParkingLotElderly;
    TextInputLayout elderVertSignLengthField, elderVertSignWidthField, verticalSignObsField,
            elderVacancyLengthField, elderVacancyWidthField, elderVacancyLimitWidthField, elderVacancyObsField,
            elderVacLocalField, elderFloorSignWidthField, elderFloorSignObsField;
    TextInputEditText elderVertSignLengthValue, elderVertSignWidthValue, verticalSignObsValue,
            elderVacancyLengthValue, elderVacancyWidthValue, elderVacancyLimitWidthValue, elderVacancyObsValue,
            elderVacLocalValue, elderFloorSignWidthValue, elderFloorSignObsFieldValue;
    ArrayList<TextInputLayout> elderVertSignFields, elderFloorSignFields;
    ViewModelEntry modelEntry;

    public int saveAttempt = 0;

    Bundle elderlyBundle = new Bundle();


    public ParkLotElderlyFragment() {
        // Required empty public constructor
    }

    public static ParkLotElderlyFragment newInstance() {
        ParkLotElderlyFragment fragment = new ParkLotElderlyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            elderlyBundle.putInt(PARKING_ID, this.getArguments().getInt(PARKING_ID));
            elderlyBundle.putInt(ELDER_ID, this.getArguments().getInt(ELDER_ID));
        }
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

        instantiateElderlyViews(view);

        hasVerticalSign.setOnCheckedChangeListener(this::radioListener);
        hasFloorSign.setOnCheckedChangeListener(this::radioListener);

        if (elderlyBundle.getInt(ELDER_ID) > 0) {
            modelEntry.getOneElderVacancy(elderlyBundle.getInt(ELDER_ID))
                    .observe(getViewLifecycleOwner(), this::loadElderlyLotData);
        }

        cancelParkingLotElderly.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                        .popBackStack(ELDER_LIST,0));

        saveParkingLotElderly.setOnClickListener(v -> elderSaveClick());
    }

    private void instantiateElderlyViews(View view) {
//        TextInputLayout
        elderVertSignLengthField = view.findViewById(R.id.elder_vertical_sign_length_field);
        elderVertSignWidthField = view.findViewById(R.id.elder_vertical_sign_width_field);
        verticalSignObsField = view.findViewById(R.id.elder_vertical_sign_obs_field);
        elderVacancyLengthField = view.findViewById(R.id.elder_vacancy_length_field);
        elderVacancyWidthField = view.findViewById(R.id.elder_vacancy_width_field);
        elderVacancyLimitWidthField = view.findViewById(R.id.elderly_vacancy_limiter_width_field);
        elderVacancyObsField = view.findViewById(R.id.elder_horizontal_sign_obs_field);
        elderVacLocalField = view.findViewById(R.id.elderly_vacancy_locale_field);
        elderFloorSignWidthField = view.findViewById(R.id.elder_floor_sign_width_field);
        elderFloorSignObsField = view.findViewById(R.id.elder_floor_sign_obs_field);
//        TextInputEditText
        elderVertSignLengthValue = view.findViewById(R.id.elder_vertical_sign_length_value);
        elderVertSignWidthValue = view.findViewById(R.id.elder_vertical_sign_width_value);
        verticalSignObsValue = view.findViewById(R.id.elder_vertical_sign_obs_value);
        elderVacancyLengthValue = view.findViewById(R.id.elder_vacancy_length_value);
        elderVacancyWidthValue = view.findViewById(R.id.elder_vacancy_width_value);
        elderVacancyLimitWidthValue = view.findViewById(R.id.elderly_vacancy_limiter_width_value);
        elderVacancyObsValue = view.findViewById(R.id.elder_horizontal_sign_obs_value);
        elderVacLocalValue = view.findViewById(R.id.elderly_vacancy_locale_value);
        elderFloorSignWidthValue = view.findViewById(R.id.elder_floor_sign_width_value);
        elderFloorSignObsFieldValue = view.findViewById(R.id.elder_floor_sign_obs_value);
//        RadioGroup
        hasVerticalSign = view.findViewById(R.id.elder_vertical_sign_radio);
        hasFloorSign = view.findViewById(R.id.elder_floor_sign_radio);
//        TextView
        verticalSignError = view.findViewById(R.id.elderly_vertical_sign_error);
        floorSingError = view.findViewById(R.id.elder_floor_sign_error);
//        MaterialButton
        cancelParkingLotElderly = view.findViewById(R.id.cancel_parking_lot_elderly);
        saveParkingLotElderly = view.findViewById(R.id.save_parking_lot_elderly);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Methods
        addFieldsToArrays();
    }

    public void clearErrorMessages() {
        verticalSignError.setVisibility(View.GONE);
        floorSingError.setVisibility(View.GONE);
        elderVertSignLengthField.setErrorEnabled(false);
        elderVertSignWidthField.setErrorEnabled(false);
        elderVacancyLengthField.setErrorEnabled(false);
        elderVacancyWidthField.setErrorEnabled(false);
        elderVacancyLimitWidthField.setErrorEnabled(false);
        elderVacLocalField.setErrorEnabled(false);
        elderFloorSignWidthField.setErrorEnabled(false);
    }

    public boolean verifyEmptyFields() {
        clearErrorMessages();
        int i = 0;
        if (TextUtils.isEmpty(elderVacLocalValue.getText())) {
            elderVacLocalField.setError(getString(R.string.req_field_error));
            i++;
        }
        if (getCheckedRadio(hasVerticalSign) == -1) {
            verticalSignError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadio(hasVerticalSign) == 1) {
            if (TextUtils.isEmpty(elderVertSignLengthValue.getText())) {
                elderVertSignLengthField.setError(getString(R.string.req_field_error));
                i++;
            }
            if (TextUtils.isEmpty(elderVertSignWidthValue.getText())) {
                elderVertSignWidthField.setError(getString(R.string.req_field_error));
                i++;
            }
        }
        if (TextUtils.isEmpty(elderVacancyLengthValue.getText())) {
            elderVacancyLengthField.setError(getString(R.string.req_field_error));
            i++;
        }
        if (TextUtils.isEmpty(elderVacancyWidthValue.getText())) {
            elderVacancyWidthField.setError(getString(R.string.req_field_error));
            i++;
        }
        if (TextUtils.isEmpty(elderVacancyLimitWidthValue.getText())) {
            elderVacancyLimitWidthField.setError(getString(R.string.req_field_error));
            i++;
        }
        if (getCheckedRadio(hasFloorSign) == -1) {
            floorSingError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadio(hasFloorSign) == 1) {
            if (TextUtils.isEmpty(elderFloorSignWidthValue.getText())) {
                elderFloorSignWidthField.setError(getString(R.string.req_field_error));
                i++;
            }
        }
        return i == 0;
    }

    private void loadElderlyLotData(ParkingLotElderlyEntry elderlyEntry) {
        elderVacLocalValue.setText(String.valueOf(elderlyEntry.getElderVacLocation()));
        hasVerticalSign.check(hasVerticalSign.getChildAt(elderlyEntry.getHasElderlyVertSign()).getId());
        if (elderlyEntry.getHasElderlyVertSign() == 1) {
            elderVertSignLengthValue.setText(String.valueOf(elderlyEntry.getElderlyVertSignLength()));
            elderVertSignWidthValue.setText(String.valueOf(elderlyEntry.getElderlyVertSingWidth()));
        }
        verticalSignObsValue.setText(elderlyEntry.getElderlyVertSignObs());
        elderVacancyLengthValue.setText(String.valueOf(elderlyEntry.getElderlyVacancyLength()));
        elderVacancyWidthValue.setText(String.valueOf(elderlyEntry.getElderlyVacancyWidth()));
        elderVacancyLimitWidthValue.setText(String.valueOf(elderlyEntry.getElderlyVacancyLimiterWidth()));
        elderVacancyObsValue.setText(elderlyEntry.getElderlyVacancyObs());
        hasFloorSign.check(hasFloorSign.getChildAt(elderlyEntry.getHasElderlyFloorIndicator()).getId());
        if (elderlyEntry.getHasElderlyFloorIndicator() == 1) {
            elderFloorSignWidthValue.setText(String.valueOf(elderlyEntry.getFloorIndicatorHeight()));
        }
        elderFloorSignObsFieldValue.setText(elderlyEntry.getFloorIndicatorObs());
    }

    private void elderSaveClick() {
        if (verifyEmptyFields()) {
            ParkingLotElderlyEntry newEntry = newElderlyEntry(elderlyBundle);
            if (elderlyBundle.getInt(ELDER_ID) > 0) {
                newEntry.setParkElderID(elderlyBundle.getInt(ELDER_ID));
                ViewModelEntry.updateElderlyParkingLot(newEntry);
                Toast.makeText(getContext(), "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStack(ParkingLotFragment.ELDER_LIST, 0);
            } else if (elderlyBundle.getInt(ELDER_ID) == 0) {
                ViewModelEntry.insertElderlyParkingLot(newEntry);
                Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                clearElderFields();
            } else{
                elderlyBundle.putInt(ELDER_ID, 0);
                Toast.makeText(getContext(), "Algo deu errado. Por favor, tente novamente!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void addFieldsToArrays() {
        elderVertSignFields = new ArrayList<>();
        elderFloorSignFields = new ArrayList<>();

        elderVertSignFields.add(elderVertSignLengthField);
        elderVertSignFields.add(elderVertSignWidthField);

        elderFloorSignFields.add(elderFloorSignWidthField);
    }

    private void radioListener(RadioGroup group, int checkedID) {
        int index = group.indexOfChild(group.findViewById(checkedID));
        if (group == hasVerticalSign) {
            if (index == 1)
                for (TextInputLayout fields : elderVertSignFields)
                    fields.setVisibility(View.VISIBLE);
            else {
                for (TextInputLayout fields : elderVertSignFields) {
                    fields.getEditText().setText(null);
                    fields.setVisibility(View.GONE);
                }
            }
        } else if (group == hasFloorSign) {
            if (index == 1)
                for (TextInputLayout fields : elderFloorSignFields)
                    fields.setVisibility(View.VISIBLE);
            else {
                for (TextInputLayout fields : elderFloorSignFields) {
                    fields.getEditText().setText(null);
                    fields.setVisibility(View.GONE);
                }
            }
        }

    }

    private void clearElderFields() {
        hasVerticalSign.clearCheck();
        hasFloorSign.clearCheck();
        elderVertSignLengthValue.setText(null);
        elderVertSignWidthValue.setText(null);
        elderVacancyLengthValue.setText(null);
        elderVacancyWidthValue.setText(null);
        elderVacancyLimitWidthValue.setText(null);
        elderVacLocalValue.setText(null);
        elderFloorSignWidthValue.setText(null);
    }

    public ParkingLotElderlyEntry newElderlyEntry(Bundle bundle) {
        int hasVertSign, hasElderFloorSign;
        double elderVacancyLength, elderVacancyWidth, elderVacancyLimitWidth;
        Double elderVertLength = null, elderVertWidth = null, floorHeight = null;
        String elderLoc, elderVertObs, elderVacancyObs, elderFloorObs;

        elderLoc = String.valueOf(elderVacLocalValue.getText());
        hasVertSign = getCheckedRadio(hasVerticalSign);
        if (hasVertSign == 1) {
            elderVertLength = Double.valueOf(String.valueOf(elderVertSignLengthValue.getText()));
            elderVertWidth = Double.valueOf(String.valueOf(elderVertSignWidthValue.getText()));
        }
        elderVertObs = String.valueOf(verticalSignObsValue.getText());
        elderVacancyLength = Double.parseDouble(String.valueOf(elderVacancyLengthValue.getText()));
        elderVacancyWidth = Double.parseDouble(String.valueOf(elderVacancyWidthValue.getText()));
        elderVacancyLimitWidth = Double.parseDouble(String.valueOf(elderVacancyLimitWidthValue.getText()));
        elderVacancyObs = String.valueOf(elderVacancyObsValue.getText());
        hasElderFloorSign = getCheckedRadio(hasFloorSign);
        if (hasElderFloorSign == 1) {
            floorHeight = Double.valueOf(String.valueOf(elderFloorSignWidthValue.getText()));
        }
        elderFloorObs = String.valueOf(verticalSignObsValue.getText());

        return new ParkingLotElderlyEntry(bundle.getInt(PARKING_ID), elderLoc ,hasVertSign, elderVertLength,
                elderVertWidth, elderVertObs, elderVacancyLength, elderVacancyWidth, elderVacancyLimitWidth,
                elderVacancyObs, hasElderFloorSign, floorHeight, elderFloorObs);
    }

}