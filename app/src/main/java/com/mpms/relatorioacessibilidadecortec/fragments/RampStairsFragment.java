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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.RampEntry;
import com.mpms.relatorioacessibilidadecortec.entities.StairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;


public class RampStairsFragment extends Fragment {

    private static final String STAIRS_OR_RAMP = "STAIRS_OR_RAMP";
    private static final String STAIRS_RAMP_ID = "STAIRS_RAMP_ID";
    private static final String NUMBER_FLIGHTS = "NUMBER_FLIGHTS";

    private static int chosenOption;

    private int recentEntry = 0;
    private int updateEntry = 0;

    private Bundle rampStairsBundle = new Bundle();

    TextInputLayout rampStairsLocField, quantFlightField;
    TextInputEditText rampStairsLocValue, quantFlightValue;
    TextView registerHeader;
    Button cancelRampStairs, proceedRegister;

    String rampStairsLocation;
    Integer rampStairsFlightQuantity;

    ViewModelFragments modelFragments;
    ViewModelEntry modelEntry;

    public RampStairsFragment() {
        // Required empty public constructor
    }

    public static RampStairsFragment newInstance(int dropdownChoice) {
        RampStairsFragment rampStairsFragment = new RampStairsFragment();
        rampStairsFragment.setChosenRaStOption(dropdownChoice);
        return rampStairsFragment;
    }

    public void setChosenRaStOption(int choice) {
        chosenOption = choice;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_ramp_stairs, container, false);

        rampStairsBundle = this.getArguments();
        if (rampStairsBundle != null)
            rampStairsBundle.putInt(STAIRS_OR_RAMP, chosenOption);

        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rampStairsLocField = view.findViewById(R.id.ramp_stairs_location_field);
        quantFlightField = view.findViewById(R.id.flight_ramp_stairs_field);

        rampStairsLocValue = view.findViewById(R.id.ramp_stairs_location_value);
        quantFlightValue = view.findViewById(R.id.flight_ramp_stairs_value);

        registerHeader = view.findViewById(R.id.ramp_stairs_header);

        cancelRampStairs = view.findViewById(R.id.cancel_ramp_stairs);
        proceedRegister = view.findViewById(R.id.save_ramp_stairs);

        setRampStairsTemplate(chosenOption);


        proceedRegister.setOnClickListener(v -> {
            if(checkRampStairsFields()) {
                int rampStairsID = rampStairsBundle.getInt(STAIRS_RAMP_ID, 0);
                if (rampStairsID == 0) {
                    switch (chosenOption) {
                        case 7:
                            StairsEntry newStaircase = newStaircase(rampStairsBundle);
                            ViewModelEntry.insertStairs(newStaircase);
                            recentEntry = 1;
                            break;
                        case 9:
                            RampEntry newRamp = newRamp(rampStairsBundle);
                            ViewModelEntry.insertRamp(newRamp);
                            recentEntry = 1;
                            break;
                        default:
                            errorEscape();
                            break;
                    }
                } else if (rampStairsID > 0) {
                    switch (chosenOption) {
                        case 7:
                            StairsEntry upStairs = newStaircase(rampStairsBundle);
                            upStairs.setStairsID(rampStairsID);
                            ViewModelEntry.updateStairs(upStairs);
                            updateEntry = 1;
                            break;
                        case 9:
                            RampEntry upRamp = newRamp(rampStairsBundle);
                            upRamp.setRampID(rampStairsID);
                            ViewModelEntry.updateRamp(upRamp);
                            updateEntry = 1;
                            break;
                        default:
                            errorEscape();
                            break;
                    }
                } else {
                    errorEscape();
                }

            }
        });

        //        TODO - Atualizar método para retornar à listagem de escadas cadastradas quando ela for implementada (em vez de fechar o frag)
        cancelRampStairs.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());


        }

    public void setRampStairsTemplate(int pickedOption) {
        switch (pickedOption) {
            case 7:
                rampStairsLocField.setHint(getString(R.string.hint_staircase_location));
                quantFlightField.setHint(getString(R.string.hint_number_flight_stairs));
                registerHeader.setText(getText(R.string.label_staircase_register_header));

                modelEntry.getLastStairsEntry().observe(getViewLifecycleOwner(), stairs -> {
                    if (recentEntry == 1) {
                        recentEntry = 0;
                        int stairID = stairs.getStairsID();
                        rampStairsBundle.putInt(STAIRS_RAMP_ID, stairID);
                        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        StairsFlightFragment flightFragment = StairsFlightFragment.newInstance();
                        flightFragment.setArguments(rampStairsBundle);
                        fragmentTransaction.replace(R.id.show_fragment_selected, flightFragment).addToBackStack(null).commit();
                    }
                });

//      Usado quando uma entrada deve ser atualizada,
                modelEntry.getStairsEntry(rampStairsBundle.getInt(STAIRS_RAMP_ID)).observe(getViewLifecycleOwner(), update -> {
                    if (updateEntry == 1) {
                        updateEntry = 0;
                        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        StairsFlightFragment flightFragment = StairsFlightFragment.newInstance();
                        flightFragment.setArguments(rampStairsBundle);
                        fragmentTransaction.replace(R.id.show_fragment_selected, flightFragment).addToBackStack(null).commit();
                    }
                });

//      Preenchimento dos campos da tela
                modelFragments.getRampStairsBundle().observe(getViewLifecycleOwner(), stairs -> {
                    if (stairs != null) {
                        modelEntry.getStairsEntry(stairs.getInt(STAIRS_RAMP_ID)).observe(getViewLifecycleOwner(), this::gatherStairsEntry);
                    }
                });

                break;
            case 9:
                rampStairsLocField.setHint(getString(R.string.hint_ramp_location));
                quantFlightField.setHint(getString(R.string.hint_ramp_flight_quantity));
                registerHeader.setText(getText(R.string.label_ramp_register_header));

//      Usado quando um novo cadastro é realizado, colocando o ID no bundle e chamando o próximo fragmento
                modelEntry.getLastRampEntry().observe(getViewLifecycleOwner(), ramp -> {
                    if (recentEntry == 1) {
                        recentEntry = 0;
                        int rampID = ramp.getRampID();
                        rampStairsBundle.putInt(STAIRS_RAMP_ID, rampID);
                        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        TODO - Trocar por fragmento que possa ser usado por ambos
                        StairsFlightFragment flightFragment = StairsFlightFragment.newInstance();
                        flightFragment.setArguments(rampStairsBundle);
                        fragmentTransaction.replace(R.id.show_fragment_selected, flightFragment).addToBackStack(null).commit();
                    }
                });

//      Usado quando uma entrada deve ser atualizada,
                modelEntry.getRampEntry(rampStairsBundle.getInt(STAIRS_RAMP_ID)).observe(getViewLifecycleOwner(), update -> {
                    if (updateEntry == 1) {
                        updateEntry = 0;
                        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        TODO - Trocar por fragmento que possa ser usado por ambos
                        RampFlightFragment flightFragment = RampFlightFragment.newInstance();
                        flightFragment.setArguments(rampStairsBundle);
                        fragmentTransaction.replace(R.id.show_fragment_selected, flightFragment).addToBackStack(null).commit();
                    }
                });

//      Preenchimento dos campos da tela
                modelFragments.getRampStairsBundle().observe(getViewLifecycleOwner(), ramp -> {
                    if (ramp != null) {
                        modelEntry.getRampEntry(ramp.getInt(STAIRS_RAMP_ID)).observe(getViewLifecycleOwner(), this::gatherRampEntry);
                    }
                });

                break;
            default:
                errorEscape();
                break;
        }

    }

    public StairsEntry newStaircase(Bundle bundle) {
        rampStairsLocation = Objects.requireNonNull(rampStairsLocValue.getText()).toString();
        rampStairsFlightQuantity = Integer.parseInt(Objects.requireNonNull(quantFlightValue.getText()).toString());
        rampStairsBundle.putInt(NUMBER_FLIGHTS, rampStairsFlightQuantity);

        return new StairsEntry(bundle.getInt(InspectionActivity.SCHOOL_ID_VALUE), rampStairsLocation, rampStairsFlightQuantity);
    }

    public RampEntry newRamp(Bundle bundle) {
        rampStairsLocation = Objects.requireNonNull(rampStairsLocValue.getText()).toString();
        rampStairsFlightQuantity = Integer.parseInt(Objects.requireNonNull(quantFlightValue.getText()).toString());
        rampStairsBundle.putInt(NUMBER_FLIGHTS, rampStairsFlightQuantity);

        return new RampEntry(bundle.getInt(InspectionActivity.SCHOOL_ID_VALUE), rampStairsLocation, rampStairsFlightQuantity);
    }

    public boolean checkRampStairsFields() {
        clearRampStairsFieldError();
        int error = 0;
        if (TextUtils.isEmpty(rampStairsLocValue.getText())){
            error++;
            rampStairsLocField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(quantFlightValue.getText())){
            error++;
            quantFlightField.setError(getString(R.string.blank_field_error));
        }
        return error == 0;
    }

    public void clearRampStairsFieldError() {
        rampStairsLocField.setErrorEnabled(false);
        quantFlightField.setErrorEnabled(false);
    }

    public void gatherStairsEntry(StairsEntry stairs) {
        rampStairsLocValue.setText(stairs.getStairsLocation());
//  Number formatting does not take into account locale settings. Consider using `String.format` instead. - Integer não tem pontos, não tem que se preocupar
        quantFlightValue.setText(Integer.toString(stairs.getFlightStairsQuantity()));
    }

    public void gatherRampEntry(RampEntry ramp) {
        rampStairsLocValue.setText(ramp.getRampLocation());
//  Number formatting does not take into account locale settings. Consider using `String.format` instead. - Integer não tem pontos, não tem que se preocupar
        quantFlightValue.setText(Integer.toString(ramp.getFlightRampQuantity()));
    }

    public void errorEscape(){
        Toast.makeText(getContext(), "Houve um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().remove(this).commit();
    }



}