package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

public class SidewalkFragment extends Fragment {

    private static int chosenOption;

    public SidewalkFragment() {
        // Required empty public constructor
    }

    public void setChosenOption(int choice) {
        SidewalkFragment.chosenOption = choice;
    }

    public static SidewalkFragment newInstance(int dropdownChoice) {
        SidewalkFragment sidewalkFragment = new SidewalkFragment();
        sidewalkFragment.setChosenOption(dropdownChoice);
        return sidewalkFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sidewalk, container, false);
        setHeaderText(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup hasSpecialFloor = view.findViewById(R.id.radio_sidewalk_special_floor);
        RadioGroup hasRamp = view.findViewById(R.id.radio_sidewalk_ramp);
        TextInputLayout conservationSpecialFloorField = view.findViewById(R.id.sidewalk_special_floor_field);
        TextInputLayout sidewalkRampWidthField = view.findViewById(R.id.sidewalk_ramp_width_field);
        TextInputLayout sidewalkRampSlopeField = view.findViewById(R.id.sidewalk_ramp_slope_field);
        TextInputEditText conservationSpecialFloorValue = view.findViewById(R.id.sidewalk_special_floor_value);
        TextInputEditText sidewalkRampWidthValue = view.findViewById(R.id.sidewalk_ramp_width_value);
        TextInputEditText sidewalkRampSlopeValue = view.findViewById(R.id.sidewalk_ramp_slope_value);

        radioGroupActivation(hasSpecialFloor, conservationSpecialFloorField, conservationSpecialFloorValue);
        radioGroupActivation(hasRamp, sidewalkRampWidthField, sidewalkRampSlopeField, sidewalkRampWidthValue,sidewalkRampSlopeValue);
    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.sidewalk_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }

    public void radioGroupActivation(RadioGroup radioGroup, TextInputLayout firstField, TextInputEditText firstValue) {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            View radioButton = radioGroup.findViewById(checkedId);
            int index = radioGroup.indexOfChild(radioButton);

            switch (index) {
                case 0:
                    firstField.setEnabled(true);
                    break;
                case 1:
                    firstField.setEnabled(false);
                    firstValue.setText(null);
                    break;
                default:
                    break;
            }
        });
    }

    public void radioGroupActivation (RadioGroup radioGroup, TextInputLayout firstField, TextInputLayout secondField,
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