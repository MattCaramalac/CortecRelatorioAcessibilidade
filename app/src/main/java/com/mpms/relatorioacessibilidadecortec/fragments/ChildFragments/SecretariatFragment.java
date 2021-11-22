package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

public class SecretariatFragment extends Fragment {

    public static final String HAS_FIXED_SEATS = "HAS_FIXED_SEATS";
    public static final String HAS_PCR_SPACE = "HAS_PCR_SPACE";
    public static final String PCR_WIDTH = "PCR_WIDTH";
    public static final String PCR_DEPTH = "PCR_DEPTH";
    public static final String PCR_OBS = "PCR_OBS";

    TextInputLayout pcrSpaceWidthField, pcrSpaceDepthField, pcrSpaceObsField;
    TextInputEditText pcrSpaceWidthValue, pcrSpaceDepthValue, pcrSpaceObsValue;
    MaterialButton addCounter;
    RadioGroup hasFixedSeatsRadio, hasPCRSpaceRadio;
    TextView fixedSeatsError, PCRSpaceHeader, PCRSpaceError;

    ViewModelFragments modelFragments;

    public SecretariatFragment() {
        // Required empty public constructor
    }

    public static SecretariatFragment newInstance(int dropdownChoice) {
        return new SecretariatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        secretariatInstantiateView(view);
        hasFixedSeatsRadio.setOnCheckedChangeListener(this::secretariatRadioListener);
        hasPCRSpaceRadio.setOnCheckedChangeListener(this::secretariatRadioListener);

        addCounter.setOnClickListener( v-> modelFragments.setAddRegister(true));

        modelFragments.getSaveAttemptRoom().observe(getViewLifecycleOwner(), saveAttempt -> {
           if (saveAttempt == 1) {
               if (checkEmptySecretariatFields()) {
                   Bundle secretBundle = new Bundle();
                   secretBundle.putInt(HAS_FIXED_SEATS, getCheckedRadio(hasFixedSeatsRadio));
                   if (getCheckedRadio(hasFixedSeatsRadio) == 1) {
                       secretBundle.putInt(HAS_PCR_SPACE, getCheckedRadio(hasPCRSpaceRadio));
                       if (getCheckedRadio(hasPCRSpaceRadio) == 1) {
                           secretBundle.putDouble(PCR_WIDTH, Double.parseDouble(String.valueOf(pcrSpaceWidthValue.getText())));
                           secretBundle.putDouble(PCR_DEPTH, Double.parseDouble(String.valueOf(pcrSpaceDepthValue.getText())));
                       }
                       secretBundle.putString(PCR_OBS, String.valueOf(pcrSpaceObsValue.getText()));
                   }
                   modelFragments.setRoomBundle(secretBundle);
                   clearSecretariatFields();
               } else
                   Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
               modelFragments.setSaveAttemptRooms(0);
           }
        });
    }

    public void secretariatInstantiateView(View view) {
//        TextInputLayout
        pcrSpaceWidthField = view.findViewById(R.id.PCR_space_width_field);
        pcrSpaceDepthField = view.findViewById(R.id.PCR_space_depth_field);
        pcrSpaceObsField = view.findViewById(R.id.PCR_space_obs_field);
//        TextInputEditText
        pcrSpaceWidthValue = view.findViewById(R.id.PCR_space_width_value);
        pcrSpaceDepthValue = view.findViewById(R.id.PCR_space_depth_value);
        pcrSpaceObsValue = view.findViewById(R.id.PCR_space_obs_value);
//        TextView
        fixedSeatsError = view.findViewById(R.id.fixed_seats_error);
        PCRSpaceHeader = view.findViewById(R.id.label_has_PCR_space);
        PCRSpaceError = view.findViewById(R.id.PCR_space_error);
//        RadioGroup
        hasFixedSeatsRadio = view.findViewById(R.id.has_fixed_seats_radio);
        hasPCRSpaceRadio = view.findViewById(R.id.has_PCR_space_radio);
//        MaterialButton
        addCounter = view.findViewById(R.id.secretariat_add_counter_button);
//        ViewModel
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
    }

    public void secretariatRadioListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == hasFixedSeatsRadio) {
            if (index == 1) {
                PCRSpaceHeader.setVisibility(View.VISIBLE);
                hasPCRSpaceRadio.setVisibility(View.VISIBLE);
                pcrSpaceObsField.setVisibility(View.VISIBLE);
            } else {
                hasPCRSpaceRadio.clearCheck();
                pcrSpaceWidthValue.setText(null);
                pcrSpaceDepthValue.setText(null);
                pcrSpaceObsValue.setText(null);
                PCRSpaceHeader.setVisibility(View.GONE);
                hasPCRSpaceRadio.setVisibility(View.GONE);
                pcrSpaceWidthField.setVisibility(View.GONE);
                pcrSpaceDepthField.setVisibility(View.GONE);
                pcrSpaceObsField.setVisibility(View.GONE);
            }
        } else if (radio == hasPCRSpaceRadio) {
            if (index == 1) {
                pcrSpaceWidthField.setVisibility(View.VISIBLE);
                pcrSpaceDepthField.setVisibility(View.VISIBLE);
            } else {
                pcrSpaceWidthValue.setText(null);
                pcrSpaceDepthValue.setText(null);
                pcrSpaceWidthField.setVisibility(View.GONE);
                pcrSpaceDepthField.setVisibility(View.GONE);
            }
        }
    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptySecretariatFields() {
        clearSecretariatErrors();
        int error = 0;
        if (getCheckedRadio(hasFixedSeatsRadio) == -1) {
            error++;
            fixedSeatsError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(hasFixedSeatsRadio) == 1) {
            if (getCheckedRadio(hasPCRSpaceRadio) == -1) {
                error++;
                PCRSpaceError.setVisibility(View.VISIBLE);
            } else if (getCheckedRadio(hasPCRSpaceRadio) == 1) {
                if (TextUtils.isEmpty(pcrSpaceWidthValue.getText())){
                    error++;
                    pcrSpaceWidthField.setError(getText(R.string.blank_field_error));
                }
                if (TextUtils.isEmpty(pcrSpaceDepthValue.getText())) {
                    pcrSpaceDepthValue.setError(getText(R.string.blank_field_error));
                    error++;
                }
            }
        }
        return error == 0;
    }

    public void clearSecretariatErrors() {
        fixedSeatsError.setVisibility(View.GONE);
        PCRSpaceError.setVisibility(View.GONE);
        pcrSpaceWidthField.setErrorEnabled(false);
        pcrSpaceDepthField.setErrorEnabled(false);
    }

    public void clearSecretariatFields() {
        hasFixedSeatsRadio.clearCheck();
        hasPCRSpaceRadio.clearCheck();
        pcrSpaceWidthValue.setText(null);
        pcrSpaceDepthValue.setText(null);
        pcrSpaceObsValue.setText(null);
        PCRSpaceHeader.setVisibility(View.GONE);
        hasPCRSpaceRadio.setVisibility(View.GONE);
        pcrSpaceWidthField.setVisibility(View.GONE);
        pcrSpaceDepthField.setVisibility(View.GONE);
        pcrSpaceObsField.setVisibility(View.GONE);
    }
}