package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

public class RampStairsFragment extends Fragment {

    public static final String RAMP_OR_STAIRS = "RAMP_OR_STAIRS";
    public static final String RAMP_STAIRS_ID = "RAMP_STAIRS_ID";
    public static final String NUMBER_FLIGHTS = "NUMBER_FLIGHTS";

    private static int chosenOption;

    private int recentEntry = 0;
    private int updateEntry = 0;

    Bundle rampStairsBundle = new Bundle();

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

    public static RampStairsFragment newInstance() {
        return new RampStairsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            rampStairsBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
            rampStairsBundle.putInt(RAMP_OR_STAIRS, this.getArguments().getInt(RAMP_OR_STAIRS));
            rampStairsBundle.putInt(RAMP_STAIRS_ID, this.getArguments().getInt(RAMP_STAIRS_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ramp_stairs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRampStairsViews(view);
        setRampStairsTemplate(rampStairsBundle.getInt(RAMP_OR_STAIRS));

//      Usado quando um novo cadastro é realizado, colocando o ID no bundle e chamando o próximo fragmento
        modelEntry.getLastRampStairsEntry().observe(getViewLifecycleOwner(), rampStairs -> {
            if (recentEntry == 1) {
                recentEntry = 0;
                int rampStairsID = rampStairs.getRampStairsID();
                rampStairsBundle.putInt(RAMP_STAIRS_ID, rampStairsID);
                openFlightFragment();
            }
        });

//      Usado quando uma entrada deve ser atualizada,
        modelEntry.getRampStairsEntry(rampStairsBundle.getInt(RAMP_STAIRS_ID)).observe(getViewLifecycleOwner(), update -> {
            if (updateEntry == 1) {
                updateEntry = 0;
                openFlightFragment();
            }
        });

//      Preenchimento dos campos da tela
        if (rampStairsBundle.getInt(RAMP_STAIRS_ID) > 0)
            modelEntry.getRampStairsEntry(rampStairsBundle.getInt(RAMP_STAIRS_ID))
                    .observe(getViewLifecycleOwner(), this::gatherRampStairsEntry);

//        modelFragments.getRampStairsBundle().observe(getViewLifecycleOwner(), rampStairs -> {
//            if (rampStairs != null) {
//                modelEntry.getRampStairsEntry(rampStairs.getInt(RAMP_STAIRS_ID)).observe(getViewLifecycleOwner(), this::gatherRampStairsEntry);
//            }
//        });

        proceedRegister.setOnClickListener(v -> {
            if (checkRampStairsFields()) {
                if (rampStairsBundle.getInt(RAMP_STAIRS_ID) == 0) {
                    rampStairsBundle.putInt(InspectionActivity.ALLOW_UPDATE, 1);
                    RampStairsEntry newEntry = newRampOrStaircase(rampStairsBundle);
                    ViewModelEntry.insertRampStairs(newEntry);
                    recentEntry = 1;
                } else if (rampStairsBundle.getInt(RAMP_STAIRS_ID) > 0) {
//                    Testar para verificar se não causa novas entradas
                    rampStairsBundle.putInt(InspectionActivity.ALLOW_UPDATE, 1);
                    RampStairsEntry upEntry = newRampOrStaircase(rampStairsBundle);
                    upEntry.setRampStairsID(rampStairsBundle.getInt(RAMP_STAIRS_ID));
                    ViewModelEntry.updateRampStairs(upEntry);
                    updateEntry = 1;
                } else {
                    errorEscape();
                }
            }
        });

        cancelRampStairs.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .popBackStack(InspectionActivity.RAMP_STAIRS_LIST, 0));
    }

    private void instantiateRampStairsViews(View view) {
        rampStairsLocField = view.findViewById(R.id.ramp_stairs_location_field);
        quantFlightField = view.findViewById(R.id.flight_ramp_stairs_field);

        rampStairsLocValue = view.findViewById(R.id.ramp_stairs_location_value);
        quantFlightValue = view.findViewById(R.id.flight_ramp_stairs_value);

        registerHeader = view.findViewById(R.id.ramp_stairs_header);

        cancelRampStairs = view.findViewById(R.id.cancel_ramp_stairs);
        proceedRegister = view.findViewById(R.id.save_ramp_stairs);

        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    public void setRampStairsTemplate(int pickedOption) {
        switch (pickedOption) {
            case 7:
                rampStairsLocField.setHint(getString(R.string.hint_staircase_location));
                quantFlightField.setHint(getString(R.string.hint_number_flight_stairs));
                registerHeader.setText(getText(R.string.label_staircase_register_header));
                break;
            case 9:
                rampStairsLocField.setHint(getString(R.string.hint_ramp_location));
                quantFlightField.setHint(getString(R.string.hint_ramp_flight_quantity));
                registerHeader.setText(getText(R.string.label_ramp_register_header));
                break;
            default:
                errorEscape();
                break;
        }
    }

    private void openFlightFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RampStairsFlightFragment flightFragment = RampStairsFlightFragment.newInstance();
        flightFragment.setArguments(rampStairsBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, flightFragment).addToBackStack(null).commit();
    }

    public RampStairsEntry newRampOrStaircase(Bundle bundle) {
        rampStairsLocation = String.valueOf(rampStairsLocValue.getText());
        rampStairsFlightQuantity = Integer.parseInt(String.valueOf(quantFlightValue.getText()));
        rampStairsBundle.putInt(NUMBER_FLIGHTS, rampStairsFlightQuantity);

        return new RampStairsEntry(bundle.getInt(SchoolRegisterActivity.SCHOOL_ID), bundle.getInt(RAMP_OR_STAIRS),
                rampStairsLocation, rampStairsFlightQuantity);
    }

    public boolean checkRampStairsFields() {
        clearRampStairsFieldError();
        int error = 0;
        if (TextUtils.isEmpty(rampStairsLocValue.getText())) {
            error++;
            rampStairsLocField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(quantFlightValue.getText())) {
            error++;
            quantFlightField.setError(getString(R.string.blank_field_error));
        }
        return error == 0;
    }

    public void clearRampStairsFieldError() {
        rampStairsLocField.setErrorEnabled(false);
        quantFlightField.setErrorEnabled(false);
    }

    public void gatherRampStairsEntry(RampStairsEntry rampStairs) {
        rampStairsLocValue.setText(rampStairs.getRampStairsLocation());
        quantFlightValue.setText(String.valueOf(rampStairs.getFlightsQuantity()));
    }

    public void errorEscape() {
        Toast.makeText(getContext(), "Houve um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


}