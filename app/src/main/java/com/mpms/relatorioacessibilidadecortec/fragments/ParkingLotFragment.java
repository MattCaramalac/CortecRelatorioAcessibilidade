package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

public class ParkingLotFragment extends Fragment {

    public static int chosenOption;

    public ParkingLotFragment() {
        // Required empty public constructor
    }

    public void setChosenOption(int choice) {
        ParkingLotFragment.chosenOption = choice;
    }

    public static ParkingLotFragment newInstance(int dropdownChoice) {
        ParkingLotFragment parkingLotFragment = new ParkingLotFragment();
        parkingLotFragment.setChosenOption(dropdownChoice);
        return parkingLotFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_parking_lot, container, false);
        setHeaderText(rootView);
        return rootView;
    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.parking_lot_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }
}