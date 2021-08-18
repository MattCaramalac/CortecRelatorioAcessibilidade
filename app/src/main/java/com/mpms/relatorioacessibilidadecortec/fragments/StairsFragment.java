package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.StairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class StairsFragment extends Fragment {

    public static final String STAIRCASE_ID = "STAIRCASE_ID";
    public static final String NUMBER_FLIGHTS = "NUMBER_FLIGHTS";

    TextInputLayout stairsLocField, quantFlightField;
    TextInputEditText stairsLocValue, quantFlightValue;
    Button cancelStairs, proceedEntry;

    LinearLayout focusRequest;

    String stairsLocation;
    Integer quantityFlights;

    int recentStairs = 0;

    Bundle staircaseData = new Bundle();

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    public StairsFragment() {
        // Required empty public constructor
    }

    public static StairsFragment newInstance() { return new StairsFragment(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        staircaseData = this.getArguments();

        return inflater.inflate(R.layout.fragment_stairs, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stairsLocField = view.findViewById(R.id.staircase_location_field);
        quantFlightField = view.findViewById(R.id.flight_stairs_field);
        stairsLocValue = view.findViewById(R.id.staircase_location_value);
        quantFlightValue = view.findViewById(R.id.flight_stairs_value);
        cancelStairs = view.findViewById(R.id.cancel_stairs);
        proceedEntry = view.findViewById(R.id.save_stairs);
        focusRequest = view.findViewById(R.id.focus_layout);

        modelEntry = new ViewModelEntry(requireActivity().getApplication());

//        TODO - Necessário criar observador para captar dados de itens já gravados, não só de recentes
        modelEntry.getLastStairsEntry().observe(getViewLifecycleOwner(), stairs -> {
            if (recentStairs == 1) {
                recentStairs = 0;
                int stairID = stairs.getStairsID();
                staircaseData.putInt(STAIRCASE_ID, stairID);
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                StairsFlightFragment flightFragment = StairsFlightFragment.newInstance();
                flightFragment.setArguments(staircaseData);
                fragmentTransaction.replace(R.id.show_fragment_selected, flightFragment).addToBackStack(null).commit();
            }
        });

        proceedEntry.setOnClickListener(v -> {
            if(checkStaircaseFields()) {
                StairsEntry newStair = newStairCase(staircaseData);
                ViewModelEntry.insertStairs(newStair);
                clearStaircaseFields();
                recentStairs = 1;
//                clearFieldFocus();
            }
        });

        cancelStairs.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    public StairsEntry newStairCase(Bundle bundle) {
            stairsLocation = Objects.requireNonNull(stairsLocValue.getText()).toString();
            quantityFlights = Integer.parseInt(Objects.requireNonNull(quantFlightValue.getText()).toString());
            staircaseData.putInt(NUMBER_FLIGHTS, quantityFlights);

        return new StairsEntry(bundle.getInt(InspectionActivity.SCHOOL_ID_VALUE),stairsLocation,quantityFlights);
    }

    public boolean checkStaircaseFields() {
        clearStaircaseFieldError();
        int error = 0;
        if (TextUtils.isEmpty(stairsLocValue.getText())){
            error++;
            stairsLocField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(quantFlightValue.getText())){
            error++;
            quantFlightField.setError(getString(R.string.blank_field_error));
        }
        return error == 0;
    }

    public void clearStaircaseFieldError() {
        stairsLocField.setErrorEnabled(false);
        quantFlightField.setErrorEnabled(false);
    }

    public void clearStaircaseFields() {
        stairsLocValue.setText(null);
        quantFlightValue.setText(null);
    }

    public void clearFieldFocus() {
        stairsLocValue.clearFocus();
        quantFlightValue.clearFocus();
        focusRequest.requestFocus();
    }

}