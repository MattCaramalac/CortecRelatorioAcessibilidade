package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.SingleStepListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;


public class CirculationTwoFragment extends Fragment implements TagInterface {

    MaterialButton addDoor, addSwitch, addWindow, addTable, addBoard, addFreeSpace, addStairs, addRamps, addSteps, addSlopes, addFountain, addEquip, addCounter, addProtect,
            returnButton, saveButton;
    ViewModelEntry modelEntry;
    TextView stepCounter;
    Bundle circTwoBundle;

    public CirculationTwoFragment() {
        // Required empty public constructor
    }


    public static CirculationTwoFragment newInstance() {
        return new CirculationTwoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            circTwoBundle = new Bundle(getArguments());
        else
            circTwoBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_circulation_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);

        if (circTwoBundle.getInt(CIRC_ID) == 0)
            modelEntry.getLastCirculation().observe(getViewLifecycleOwner(), circ -> circTwoBundle.putInt(CIRC_ID, circ.getCircID()));

        modelEntry.getAllCircSingleSteps(circTwoBundle.getInt(CIRC_ID)).observe(getViewLifecycleOwner(), stepList -> {
            if (stepList != null && stepList.size() > 0) {
                stepCounter.setText(new StringBuilder().append("(").append(stepList.size()).append(")").toString());
            }
        });

        addSteps.setOnClickListener(v -> {
            SingleStepListFragment frag = new SingleStepListFragment();
            frag.setArguments(circTwoBundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.show_fragment_selected, frag).addToBackStack(null).commit();
        });
    }

    private void instantiateViews(View view) {
//        Material Button
        addDoor = view.findViewById(R.id.circ_add_door_button);
        addSwitch = view.findViewById(R.id.circ_add_switch_button);
        addWindow = view.findViewById(R.id.circ_add_window_button);
        addTable = view.findViewById(R.id.circ_add_tables_button);
        addBoard = view.findViewById(R.id.circ_add_blackboard_button);
        addFreeSpace = view.findViewById(R.id.circ_add_free_space_button);
        addStairs = view.findViewById(R.id.circ_add_stairs_button);
        addRamps = view.findViewById(R.id.circ_add_ramps_button);
        addSteps = view.findViewById(R.id.circ_add_single_step_button);
        addSlopes = view.findViewById(R.id.circ_add_slopes_button);
        addFountain = view.findViewById(R.id.circ_add_fountain_button);
        addEquip = view.findViewById(R.id.circ_add_equip_button);
        addCounter = view.findViewById(R.id.circ_add_counter_button);
        addProtect = view.findViewById(R.id.circ_add_fall_protect_button);
        returnButton = view.findViewById(R.id.return_circ);
        saveButton = view.findViewById(R.id.save_circ);
//        TextView
        stepCounter = view.findViewById(R.id.circ_step_counter);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }
}