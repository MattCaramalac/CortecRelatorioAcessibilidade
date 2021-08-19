package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

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

    String stairsLocation;
    Integer quantityFlights;

    int recentStairs = 0;
    int updateStairs = 0;

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

        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);


//      Usado quando um novo cadastro é realizado, colocando o ID no bundle e chamando o próximo fragmento
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

//      Usado quando uma entrada deve ser atualizada,
        modelEntry.getStairsEntry(staircaseData.getInt(STAIRCASE_ID)).observe(getViewLifecycleOwner(), update -> {
            if (updateStairs == 1) {
                updateStairs = 0;
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                StairsFlightFragment flightFragment = StairsFlightFragment.newInstance();
                flightFragment.setArguments(staircaseData);
                fragmentTransaction.replace(R.id.show_fragment_selected, flightFragment).addToBackStack(null).commit();
            }
        });

//      Preenchimento dos campos da tela
        modelFragments.getStairsBundle().observe(getViewLifecycleOwner(), stairs -> {
            if (stairs != null) {
                modelEntry.getStairsEntry(stairs.getInt(STAIRCASE_ID)).observe(getViewLifecycleOwner(), this::gatherStairEntry);
            }
        });


        proceedEntry.setOnClickListener(v -> {
            if(checkStaircaseFields()) {
                int stairsID = staircaseData.getInt(STAIRCASE_ID, 0);
                if (stairsID == 0) {
                    StairsEntry newStair = newStairCase(staircaseData);
                    ViewModelEntry.insertStairs(newStair);
                    recentStairs = 1;
                } else if (stairsID > 0) {
                    StairsEntry upStairs = newStairCase(staircaseData);
                    upStairs.setStairsID(stairsID);
                    ViewModelEntry.updateStairs(upStairs);
                    updateStairs = 1;
                } else {
                    staircaseData = null;
                    recentStairs = 0;
                    updateStairs = 0;
                    clearStaircaseFields();
                    Toast.makeText(getContext(), "Houve um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        TODO - Atualizar método para retornar à listagem de escadas cadastradas quando ela for implementada (em vez de fechar o frag)
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

    public void gatherStairEntry(StairsEntry stairs) {
        stairsLocValue.setText(stairs.getStairsLocation());
//  Number formatting does not take into account locale settings. Consider using `String.format` instead. - Integer não tem pontos, não tem que se preocupar
        quantFlightValue.setText(Integer.toString(stairs.getFlightStairsQuantity()));
    }

}