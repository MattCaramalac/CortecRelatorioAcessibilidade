package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpms.relatorioacessibilidadecortec.R;

public class ParkingLotElderlyFragment extends Fragment {


    public ParkingLotElderlyFragment() {
        // Required empty public constructor
    }

    public static ParkingLotElderlyFragment newInstance() {
        ParkingLotElderlyFragment fragment = new ParkingLotElderlyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_lot_elderly, container, false);
    }
}