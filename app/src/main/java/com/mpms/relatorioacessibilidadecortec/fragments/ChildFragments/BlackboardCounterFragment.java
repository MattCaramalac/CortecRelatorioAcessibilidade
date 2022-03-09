package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

public class BlackboardCounterFragment extends Fragment {

    ViewModelFragments modelFragments;
    TextView fragmentHeader;
    MaterialButton addRegister;

    private static int chosenOption;

    public BlackboardCounterFragment() {
        // Required empty public constructor
    }

    public static BlackboardCounterFragment newInstance(int dropdownChoice) {
        BlackboardCounterFragment classCafe = new BlackboardCounterFragment();
        classCafe.setChosenOption(dropdownChoice);
        return classCafe;
    }

    public void setChosenOption(int choice) {
        BlackboardCounterFragment.chosenOption = choice;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blackboard_counter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        classCafeViews(view);

        addRegister.setOnClickListener(v -> modelFragments.setAddRegister(true));
    }

    private void classCafeViews(View v) {
//        TextView
        fragmentHeader = v.findViewById(R.id.label_blackboard_counter_header);
//        MaterialButton
        addRegister= v.findViewById(R.id.blackboard_counter_button);
//        ViewModel
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        VisualDefinitions
        if (chosenOption == 10)
            fragmentHeader.setText(getString(R.string.label_counter_register));
        else
            fragmentHeader.setText(getString(R.string.label_blackboard_register));

    }
}