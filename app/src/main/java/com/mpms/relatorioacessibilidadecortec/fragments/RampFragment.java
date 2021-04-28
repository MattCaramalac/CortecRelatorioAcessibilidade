package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;


public class RampFragment extends Fragment {

    private static int chosenOption;

    public RampFragment() {
        // Required empty public constructor
    }

    public void setChosenOption(int choice) {
        RampFragment.chosenOption = choice;
    }

    public static RampFragment newInstance(int dropdownChoice) {
        RampFragment rampFragment = new RampFragment();
        rampFragment.setChosenOption(dropdownChoice);
        return rampFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ramp, container, false);
        setHeaderText(rootView);
        return rootView;
    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.ramp_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }
}