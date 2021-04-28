package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

public class StairsRampFragment extends Fragment {

    public static int chosenOption;
    public static final String RAMP = "Rampa";
    public static final int RAMP_POSITION = HeaderNames.indexPosition(RAMP);

    public StairsRampFragment() {
        // Required empty public constructor
    }

    public void setChosenOption(int choice) {
        StairsRampFragment.chosenOption = choice;
    }

    public static StairsRampFragment newInstance(int dropdownChoice) {
        StairsRampFragment stairsRampFragment = new StairsRampFragment();
        stairsRampFragment.setChosenOption(dropdownChoice);
        return stairsRampFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stairs_and_ramp, container, false);
        setHeaderText(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputLayout stairsWidth = view.findViewById(R.id.stairs_width_field);

        if (RAMP_POSITION == chosenOption){
            stairsWidth.setVisibility(View.GONE);
            getChildFragmentManager().beginTransaction().replace(R.id.ramp_child_fragment, new RampFragment()).commit();
        }

    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.stairs_and_ramp_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }
}