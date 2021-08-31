package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mpms.relatorioacessibilidadecortec.R;

import java.util.Objects;


public class RestroomDoorFragment extends Fragment {

    Button cancelEntry;



    public RestroomDoorFragment() {
        // Required empty public constructor
    }

    public static RestroomDoorFragment newInstance() {
        return new RestroomDoorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restroom_door, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cancelEntry = view.findViewById(R.id.return_restroom);

        cancelEntry.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStackImmediate());
    }
}