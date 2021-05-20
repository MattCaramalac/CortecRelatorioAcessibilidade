package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;

import java.util.Objects;

public class SidewalkFragment extends Fragment {

    TextInputLayout sidewalkLocationField, sidewalkStatusField, sidewalkWidthField, sidewalkSpecialFloorObsField;
    TextInputEditText sidewalkLocationValue, sidewalkStatusValue, sidewalkWidthValue, sidewalkSpecialFloorObsValue;
    RadioGroup hasSpecialFloor, statusSpecialFloor, obligatorySlope, hasSlope;
    TextView slopeRegisterLabel;
    Button saveSidewalk, cancelSidewalk, addSlope;

    public SidewalkFragment() {
        // Required empty public constructor
    }

    public static SidewalkFragment newInstance() {
        return new SidewalkFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sidewalk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sidewalkLocationField = view.findViewById(R.id.sidewalk_location_field);
        sidewalkStatusField = view.findViewById(R.id.sidewalk_status_field);
        sidewalkWidthField = view.findViewById(R.id.sidewalk_width_field);
        sidewalkSpecialFloorObsField = view.findViewById(R.id.sidewalk_special_floor_obs_field);

        sidewalkLocationValue = view.findViewById(R.id.sidewalk_location_value);
        sidewalkStatusValue = view.findViewById(R.id.sidewalk_status_value);
        sidewalkWidthValue = view.findViewById(R.id.sidewalk_width_value);
        sidewalkSpecialFloorObsValue = view.findViewById(R.id.sidewalk_special_floor_obs_value);

        hasSpecialFloor = view.findViewById(R.id.radio_sidewalk_special_floor);
        statusSpecialFloor = view.findViewById(R.id.radio_status_special_floor);
        obligatorySlope = view.findViewById(R.id.radio_obligatory_sidewalk_slope);
        hasSlope = view.findViewById(R.id.radio_sidewalk_slope);

        slopeRegisterLabel = view.findViewById(R.id.label_sidewalk_slope_register);

        saveSidewalk = view.findViewById(R.id.save_sidewalk);
        cancelSidewalk = view.findViewById(R.id.cancel_sidewalk);
        addSlope = view.findViewById(R.id.add_sidewalk_slope);

        firstRegister();

        hasSpecialFloor.setOnCheckedChangeListener(this::hasSpecialFloorListener);
        hasSlope.setOnCheckedChangeListener(this::hasSlopeListener);

        cancelSidewalk.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        saveSidewalk.setOnClickListener(v -> {
            //Método de Salvar calçadas
            FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ParkingLotFragment parkingLotFragment = ParkingLotFragment.newInstance();
            fragmentTransaction.replace(R.id.show_fragment_selected, parkingLotFragment).addToBackStack(null).commit();
        });


    }

    public void firstRegister() {
        disableRadioGroup(statusSpecialFloor);
        sidewalkSpecialFloorObsField.setEnabled(false);
    }

    public void disableRadioGroup(RadioGroup radioGroup) {
        for (int j = 0; j < radioGroup.getChildCount(); j++) {
            radioGroup.getChildAt(j).setEnabled(false);
        }
    }

    public void enableRadioGroup(RadioGroup radioGroup) {
        for (int j = 0; j < radioGroup.getChildCount(); j++) {
            radioGroup.getChildAt(j).setEnabled(true);
        }
    }

    public void hasSpecialFloorListener(RadioGroup radioGroup, int checkedID) {
        View radioButton = radioGroup.findViewById(checkedID);
        int index = radioGroup.indexOfChild(radioButton);

        if (index == 1) {
            enableRadioGroup(statusSpecialFloor);
            sidewalkSpecialFloorObsField.setEnabled(true);
        } else {
            statusSpecialFloor.clearCheck();
            disableRadioGroup(statusSpecialFloor);
            sidewalkSpecialFloorObsValue.setText(null);
            sidewalkSpecialFloorObsField.setEnabled(false);
        }
    }

    public void hasSlopeListener(RadioGroup radioGroup, int checkedID) {
        View radioButton = radioGroup.findViewById(checkedID);
        int index = radioGroup.indexOfChild(radioButton);

        if (index == 1) {
            slopeRegisterLabel.setVisibility(View.VISIBLE);
            addSlope.setVisibility(View.VISIBLE);
        } else {
            slopeRegisterLabel.setVisibility(View.GONE);
            addSlope.setVisibility(View.GONE);

        }
    }
}