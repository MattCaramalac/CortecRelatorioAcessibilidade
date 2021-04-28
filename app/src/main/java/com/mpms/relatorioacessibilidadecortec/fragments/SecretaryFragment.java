package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;

public class SecretaryFragment extends Fragment {

    public SecretaryFragment() {
        // Required empty public constructor
    }

    public static SecretaryFragment newInstance(int dropdownChoice) {
        return new SecretaryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_secretary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup radioGroup = view.findViewById(R.id.radio_has_PCR_space);
        TextInputLayout firstField = view.findViewById(R.id.PCR_space_width_field);
        TextInputLayout secondField = view.findViewById(R.id.PCR_space_depth_field);
        TextInputEditText firstValue = view.findViewById(R.id.PCR_space_width_value);
        TextInputEditText secondValue = view.findViewById(R.id.PCR_space_depth_value);

        radioGroupActivation(radioGroup, firstField, secondField, firstValue, secondValue);
    }

    public void radioGroupActivation(RadioGroup radioGroup, TextInputLayout firstField, TextInputLayout secondField,
                                     TextInputEditText firstValue, TextInputEditText secondValue) {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            View radioButton = radioGroup.findViewById(checkedId);
            int index = radioGroup.indexOfChild(radioButton);

            switch (index) {
                case 0:
                    firstField.setEnabled(true);
                    secondField.setEnabled(true);
                    break;
                case 1:
                    firstField.setEnabled(false);
                    secondField.setEnabled(false);
                    firstValue.setText(null);
                    secondValue.setText(null);
                    break;
                default:
                    break;
            }
        });
    }
}