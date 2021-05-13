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
import android.widget.TextView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

import java.util.Objects;

public class ParkingLotFragment extends Fragment {

    public ParkingLotFragment() {
        // Required empty public constructor
    }

    public static ParkingLotFragment newInstance() {
        ParkingLotFragment parkingLotFragment = new ParkingLotFragment();
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
        return inflater.inflate(R.layout.fragment_parking_lot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button cancelParkingLotRegister = view.findViewById(R.id.cancel_parking_lot);
        Button saveParkingLotRegister = view.findViewById(R.id.save_parking_lot);

        cancelParkingLotRegister.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        saveParkingLotRegister.setOnClickListener(v -> {
            FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ParkingLotPdmrFragment parkingLotPdmrFragment = ParkingLotPdmrFragment.newInstance();
            fragmentTransaction.replace(R.id.show_fragment_selected, parkingLotPdmrFragment).addToBackStack(null).commit();
        });
    }

}