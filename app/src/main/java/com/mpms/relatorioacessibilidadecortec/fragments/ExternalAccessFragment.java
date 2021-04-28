package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

public class ExternalAccessFragment extends Fragment {

    private static int chosenOption;

    public ExternalAccessFragment() {
        // Required empty public constructor
    }

    public void setChosenOption(int choice) {
        ExternalAccessFragment.chosenOption = choice;
    }

    public static ExternalAccessFragment newInstance(int dropdownChoice) {
        ExternalAccessFragment externalAccessFragment = new ExternalAccessFragment();
        externalAccessFragment.setChosenOption(dropdownChoice);
        return externalAccessFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_external_access, container, false);
        setHeaderText(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup hasGateObstacle = view.findViewById(R.id.radiogroup_gate_obstacle);
        RadioGroup hasPayPhone = view.findViewById(R.id.radiogroup_gate_payphone);
        Button addGateObstacle = view.findViewById(R.id.add_gate_obstacles);
        Button addPayPhone = view.findViewById(R.id.add_gate_payphone);

        radioGroupActtivation(hasGateObstacle, addGateObstacle);
        radioGroupActtivation(hasPayPhone, addPayPhone);

    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.external_access_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }

    public void radioGroupActtivation (RadioGroup radioGroup, Button button) {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            View radioButton = radioGroup.findViewById(checkedId);
            int index = radioGroup.indexOfChild(radioButton);

            switch (index) {
                case 0:
                    button.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    button.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        });
    }

}
