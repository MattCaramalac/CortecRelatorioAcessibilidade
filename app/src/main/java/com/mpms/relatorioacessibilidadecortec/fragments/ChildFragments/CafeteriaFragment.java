package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;


public class CafeteriaFragment extends Fragment {

    RadioGroup turnAroundPossible;
    Button addCounter;

    ViewModelFragments modelFragments;

    Bundle bundle = new Bundle();

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
            modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_cafeteria, container, false);
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
                    clearCafeteriaField();
                } else
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();

                modelFragments.setSaveAttemptRooms(0);
            }
        });

        addCounter.setOnClickListener(v -> modelFragments.setCounterClick(1));

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

    public void clearCafeteriaField() {
        turnAroundPossible.clearCheck();
    }

}