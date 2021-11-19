package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;


public class CafeteriaFragment extends Fragment {

    MaterialButton addCounter;

    ViewModelFragments modelFragments;

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

        cafeteriaInstantiateView(view);

        addCounter.setOnClickListener(v -> modelFragments.setCounterClick(1));

    }

    private void cafeteriaInstantiateView(View view) {
        addCounter = view.findViewById(R.id.cafeteria_add_counter);
    }

    public int getCheckedRadioCafe(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }
}