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
import android.widget.Toast;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;


public class CafeteriaFragment extends Fragment {

    RadioGroup turnAroundPossible;
    Button addCounter;

    ViewModelFragments modelFragments;

    public static final String CAFE_SPIN = "CAFE_SPIN";

    public CafeteriaFragment() {
        // Required empty public constructor
    }

    public static CafeteriaFragment newInstance() {
        return new CafeteriaFragment();
    }

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_cafeteria, container, false);
            return rootView;
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        turnAroundPossible = view.findViewById(R.id.cafeteria_able_to_turn_around_radio);
        addCounter = view.findViewById(R.id.cafeteria_add_counter);

        modelFragments.getSaveAttemptRoom().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (saveAttempt == 1) {
                if (checkEmptyCafeFields()) {
                    Bundle cafeBundle = new Bundle();
                    cafeBundle.putInt(CAFE_SPIN, getCheckedRadioCafe(turnAroundPossible));
                    modelFragments.setRoomBundle(cafeBundle);
                } else
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();

                modelFragments.setSaveAttemptRooms(0);
            }
        });

        //TODO - Adicionar funcionalidade botão Balcão!!!!
    }

    public boolean checkEmptyCafeFields() {
        int error = 0;
        if (turnAroundPossible.getCheckedRadioButtonId() == -1)
            error++;
        return error == 0;
    }

    public int getCheckedRadioCafe(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }
}