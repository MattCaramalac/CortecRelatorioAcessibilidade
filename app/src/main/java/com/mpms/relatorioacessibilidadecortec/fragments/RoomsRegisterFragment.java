package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

public class RoomsRegisterFragment extends Fragment {

    private static int chosenOption;

    public RoomsRegisterFragment() {
        // Required empty public constructor
    }

    public void setChosenOption(int choice) {
        RoomsRegisterFragment.chosenOption = choice;
    }

    public static RoomsRegisterFragment newInstance(int dropdownChoice) {
        RoomsRegisterFragment roomsRegisterFragment = new RoomsRegisterFragment();
        roomsRegisterFragment.setChosenOption(dropdownChoice);
        return roomsRegisterFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rooms_register, container, false);
        setHeaderText(rootView);

        switch (chosenOption){
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new LibraryFragment()).commit();
                break;
            case 10:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new CafeteriaFragment()).commit();
                break;
            case 11:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new ClassroomFragment()).commit();
                break;
            case 15:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new SecretaryFragment()).commit();
                break;
            default:
                break;
        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup radioGroup = view.findViewById(R.id.door_has_ramp_group);
        TextInputLayout firstField = view.findViewById(R.id.door_ramp_width_field);
        TextInputLayout secondField = view.findViewById(R.id.door_ramp_slope_field);
        TextInputEditText firstValue = view.findViewById(R.id.door_ramp_width_value);
        TextInputEditText secondValue = view.findViewById(R.id.door_ramp_slope_value);

        radioGroupActtivation(radioGroup, firstField, secondField, firstValue, secondValue);
    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.room_register_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }

    public void radioGroupActtivation (RadioGroup radioGroup, TextInputLayout firstField, TextInputLayout secondField,
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