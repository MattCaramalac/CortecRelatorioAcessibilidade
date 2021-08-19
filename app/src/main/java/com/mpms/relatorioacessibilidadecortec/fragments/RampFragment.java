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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.StairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;


public class RampFragment extends Fragment {

    public static final String STAIRCASE_ID = "STAIRCASE_ID";
    public static final String NUMBER_FLIGHTS = "NUMBER_FLIGHTS";

    TextInputLayout rampLocField, quantFlightField;
    TextInputEditText rampLocValue, quantFlightValue;
    Button cancelRamp, proceedRampEntry;

    String rampLocation;
    Integer quantityFlights;

    int recentRamp = 0;

    Bundle staircaseData = new Bundle();

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    public RampFragment() {
        // Required empty public constructor
    }

    public static RampFragment newInstance() {
        return new RampFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ramp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rampLocField = view.findViewById(R.id.ramp_location_field);
        quantFlightField = view.findViewById(R.id.flight_ramp_field);
        rampLocValue = view.findViewById(R.id.ramp_location_value);
        quantFlightValue = view.findViewById(R.id.flight_ramp_value);
        cancelRamp = view.findViewById(R.id.cancel_ramp);
        proceedRampEntry = view.findViewById(R.id.save_ramp);





        cancelRamp.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

//    public StairsEntry newStairCase(Bundle bundle) {
//        stairsLocation = Objects.requireNonNull(stairsLocValue.getText()).toString();
//        quantityFlights = Integer.parseInt(Objects.requireNonNull(quantFlightValue.getText()).toString());
//        staircaseData.putInt(NUMBER_FLIGHTS, quantityFlights);
//
//        return new StairsEntry(bundle.getInt(InspectionActivity.SCHOOL_ID_VALUE),stairsLocation,quantityFlights);
//    }

    public boolean checkRampFields() {
        clearRampFieldError();
        int error = 0;
        if (TextUtils.isEmpty(rampLocValue.getText())){
            error++;
            rampLocField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(quantFlightValue.getText())){
            error++;
            quantFlightField.setError(getString(R.string.blank_field_error));
        }
        return error == 0;
    }

    public void clearRampFieldError() {
        rampLocField.setErrorEnabled(false);
        quantFlightField.setErrorEnabled(false);
    }

    public void clearRampFields() {
        rampLocValue.setText(null);
        quantFlightValue.setText(null);
    }
}