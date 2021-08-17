package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddCounterDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddDoorDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class SecretariatFragment extends Fragment {


    TextInputLayout fixedSeatsField, pcrSpaceWidthField, pcrSpaceDepthField;
    TextInputEditText fixedSeatsValue, pcrSpaceWidthValue, pcrSpaceDepthValue;
    Button addCounter;
    RadioGroup hasPcrSpace, turnAroundPossible;

    ViewModelFragments modelFragments;

    Bundle bundle = new Bundle();

    public static final String FIXED_SEATS = "FIXED_SEATS";
    public static final String HAS_PCR_SPACE = "HAS_PCR_SPACE";
    public static final String PCR_WIDTH = "PCR_WIDTH";
    public static final String PCR_DEPTH = "PCR_DEPTH";
    public static final String SECRETARIAT_SPIN = "SECRETARIAT_SPIN";

    public SecretariatFragment() {
        // Required empty public constructor
    }

    public static SecretariatFragment newInstance(int dropdownChoice) {
        return new SecretariatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_secretariat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hasPcrSpace = view.findViewById(R.id.has_PCR_space_radio);
        turnAroundPossible = view.findViewById(R.id.secretariat_allows_180_turns_radio);

        fixedSeatsField = view.findViewById(R.id.secretariat_waiting_seats_field);
        pcrSpaceWidthField = view.findViewById(R.id.PCR_space_width_field);
        pcrSpaceDepthField = view.findViewById(R.id.PCR_space_depth_field);

        fixedSeatsValue = view.findViewById(R.id.secretariat_waiting_seats_value);
        pcrSpaceWidthValue = view.findViewById(R.id.PCR_space_width_value);
        pcrSpaceDepthValue = view.findViewById(R.id.PCR_space_depth_value);

        addCounter = view.findViewById(R.id.secretariat_add_counter_button);

        radioGroupActivation(hasPcrSpace);

        addCounter.setOnClickListener( v-> addCounterDialog());

        modelFragments.getSaveAttemptRoom().observe(getViewLifecycleOwner(), saveAttempt -> {
           if (saveAttempt == 1) {
               if (checkEmptySecretariatFields()) {
                   Bundle secretBundle = new Bundle();
                   secretBundle.putInt(FIXED_SEATS, Integer.parseInt(Objects.requireNonNull(fixedSeatsValue.getText()).toString()));
                   secretBundle.putInt(HAS_PCR_SPACE, getCheckedRadio(hasPcrSpace));
                   if (getCheckedRadio(hasPcrSpace) == 1) {
                       secretBundle.putDouble(PCR_WIDTH, Double.parseDouble(Objects.requireNonNull(pcrSpaceWidthValue.getText()).toString()));
                       secretBundle.putDouble(PCR_DEPTH, Double.parseDouble(Objects.requireNonNull(pcrSpaceDepthValue.getText()).toString()));
                   }
                   secretBundle.putInt(SECRETARIAT_SPIN, getCheckedRadio(turnAroundPossible));
                   modelFragments.setRoomBundle(secretBundle);
                   clearSecretariatFields();
               } else
                   Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();

               modelFragments.setSaveAttemptRooms(0);
           }
        });
    }

    public void radioGroupActivation(RadioGroup radioGroup) {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int index = radioGroup.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));

            if (index == 1) {
                pcrSpaceWidthField.setEnabled(true);
                pcrSpaceDepthField.setEnabled(true);
            } else {
                pcrSpaceWidthField.setEnabled(false);
                pcrSpaceDepthField.setEnabled(false);
                pcrSpaceWidthValue.setText(null);
                pcrSpaceDepthValue.setText(null);
            }
        });
    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptySecretariatFields() {
        clearSecretariatErrors();
        int error = 0;
        if (TextUtils.isEmpty(fixedSeatsValue.getText())) {
            fixedSeatsField.setError(getText(R.string.blank_field_error));
            error++;
        }
        if (hasPcrSpace.getCheckedRadioButtonId() == -1) {
            error++;
        }
        if (getCheckedRadio(hasPcrSpace) == 1) {
            if (TextUtils.isEmpty(pcrSpaceWidthValue.getText())) {
                pcrSpaceWidthField.setError(getText(R.string.blank_field_error));
                error++;
            }
            if (TextUtils.isEmpty(pcrSpaceDepthValue.getText())) {
                pcrSpaceDepthValue.setError(getText(R.string.blank_field_error));
                error++;
            }
        }
        if (turnAroundPossible.getCheckedRadioButtonId() == -1) {
            error++;
        }

        return error == 0;
    }

    public void clearSecretariatErrors() {
        fixedSeatsField.setErrorEnabled(false);
        pcrSpaceWidthField.setErrorEnabled(false);
        pcrSpaceDepthField.setErrorEnabled(false);
    }

    public void clearSecretariatFields() {
        fixedSeatsValue.setText(null);
        hasPcrSpace.clearCheck();
        pcrSpaceWidthValue.setText(null);
        pcrSpaceDepthValue.setText(null);
        turnAroundPossible.clearCheck();
    }

//    TODO - Colocar dados no bundle para permitir gravação correta dos balcões
    private void addCounterDialog() {
        AddCounterDialog.displayCounterDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), bundle);
    }

//    MÉTODO MAIS GENÉRICO!!!

//    public void radioGroupActivation(RadioGroup radioGroup, TextInputLayout firstField, TextInputLayout secondField,
//                                     TextInputEditText firstValue, TextInputEditText secondValue) {
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            int index = radioGroup.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
//
//            if (index == 1) {
//                firstField.setEnabled(true);
//                secondField.setEnabled(true);
//            } else {
//                firstField.setEnabled(false);
//                secondField.setEnabled(false);
//                firstValue.setText(null);
//                secondValue.setText(null);
//            }
//        });
}