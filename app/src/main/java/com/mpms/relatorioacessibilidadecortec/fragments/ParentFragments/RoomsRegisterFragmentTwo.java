package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.BlackboardListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.CounterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.EquipmentListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.FreeSpaceListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.SingleStepListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.SlopeListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.SwitchListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.TableListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.WindowListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.CounterInterface;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

public class RoomsRegisterFragmentTwo extends Fragment implements CounterInterface, TagInterface {

    MaterialButton addDoor, addSwitch, addWindow, addTable, addBoard, addFreeSpace, addStairs, addRamps, addSteps, addSlopes, addFountain, addEquip, addCounter,
            returnButton, saveButton;
    ViewModelEntry modelEntry;
    TextView doorCounter, switchCounter, windowCounter, tableCounter, boardCounter, spaceCounter, stairCounter, rampCounter, stepCounter, slopeCounter, waterCounter,
            equipCounter, counter, roomHeader, counterHeader;
    Bundle roomTwoBundle;

    public RoomsRegisterFragmentTwo() {
        // Required empty public constructor
    }

    public static RoomsRegisterFragmentTwo newInstance() {
        return new RoomsRegisterFragmentTwo();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            roomTwoBundle = new Bundle(this.getArguments());
        else
            roomTwoBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rooms_register_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);
    }

    private void instantiateViews(View view) {
//        Material Button
        addDoor = view.findViewById(R.id.room_add_door_button);
        addSwitch = view.findViewById(R.id.room_add_switch_button);
        addWindow = view.findViewById(R.id.room_add_window_button);
        addTable = view.findViewById(R.id.room_add_tables_button);
        addBoard = view.findViewById(R.id.room_add_blackboard_button);
        addFreeSpace = view.findViewById(R.id.room_add_free_space_button);
        addStairs = view.findViewById(R.id.room_add_stairs_button);
        addRamps = view.findViewById(R.id.room_add_ramps_button);
        addSteps = view.findViewById(R.id.room_add_single_step_button);
        addSlopes = view.findViewById(R.id.room_add_slopes_button);
        addFountain = view.findViewById(R.id.room_add_fountain_button);
        addEquip = view.findViewById(R.id.room_add_equip_button);
        addCounter = view.findViewById(R.id.room_add_counter_button);
        returnButton = view.findViewById(R.id.return_room_one);
        saveButton = view.findViewById(R.id.save_room);
        addDoor.setOnClickListener(this::buttonListener);
        addSwitch.setOnClickListener(this::buttonListener);
        addWindow.setOnClickListener(this::buttonListener);
        addTable.setOnClickListener(this::buttonListener);
        addBoard.setOnClickListener(this::buttonListener);
        addFreeSpace.setOnClickListener(this::buttonListener);
        addStairs.setOnClickListener(this::buttonListener);
        addRamps.setOnClickListener(this::buttonListener);
        addSteps.setOnClickListener(this::buttonListener);
        addSlopes.setOnClickListener(this::buttonListener);
        addFountain.setOnClickListener(this::buttonListener);
        addEquip.setOnClickListener(this::buttonListener);
        addCounter.setOnClickListener(this::buttonListener);
        returnButton.setOnClickListener(this::buttonListener);
        saveButton.setOnClickListener(this::buttonListener);
//        TextView
        roomHeader = view.findViewById(R.id.room_register_header_two);
        doorCounter = view.findViewById(R.id.room_door_counter);
        switchCounter = view.findViewById(R.id.room_switch_counter);
        windowCounter = view.findViewById(R.id.room_window_counter);
        tableCounter = view.findViewById(R.id.room_table_counter);
        boardCounter = view.findViewById(R.id.room_blackboard_counter);
        spaceCounter = view.findViewById(R.id.room_free_space_counter);
        stairCounter = view.findViewById(R.id.room_stairs_counter);
        rampCounter = view.findViewById(R.id.room_ramps_counter);
        slopeCounter = view.findViewById(R.id.room_slope_counter);
        waterCounter = view.findViewById(R.id.room_fountain_counter);
        equipCounter = view.findViewById(R.id.room_equip_counter);
        counterHeader = view.findViewById(R.id.label_room_counter_register);
        counter = view.findViewById(R.id.room_counter_counter);
        stepCounter = view.findViewById(R.id.room_step_counter);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        if (roomTwoBundle.getInt(ROOM_TYPE) == NUM_CAFE || roomTwoBundle.getInt(ROOM_TYPE) == NUM_OTHER) {
            counter.setVisibility(View.VISIBLE);
            counterHeader.setVisibility(View.VISIBLE);
            addCounter.setVisibility(View.VISIBLE);
        }

        counterListener();
    }

    private void counterListener() {

        modelEntry.getDoorsFromRoom(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), doorCounter, list.size());
            else
                clearCounter(getContext(), doorCounter);
        });

        modelEntry.getSwitchesFromRoom(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), switchCounter, list.size());
            else
                clearCounter(getContext(), switchCounter);
        });

        modelEntry.getWindowsFromRoom(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), windowCounter, list.size());
            else
                clearCounter(getContext(), windowCounter);
        });

        modelEntry.getTablesFromRoom(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), tableCounter, list.size());
            else
                clearCounter(getContext(), tableCounter);
        });

        modelEntry.getAllBlackboardsFromRoom(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), boardCounter, list.size());
            else
                clearCounter(getContext(), boardCounter);
        });

        modelEntry.getFreeSpaceFromRoom(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), spaceCounter, list.size());
            else
                clearCounter(getContext(), spaceCounter);
        });

        modelEntry.getAllRoomSingleSteps(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), stepCounter, list.size());
            else
                clearCounter(getContext(), stepCounter);
        });

        modelEntry.getAllRoomSlopes(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), slopeCounter, list.size());
            else
                clearCounter(getContext(), slopeCounter);
        });

        modelEntry.getRoomWaterFountains(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), waterCounter, list.size());
            else
                clearCounter(getContext(), waterCounter);
        });

        modelEntry.getEquipmentFromRoom(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), equipCounter, list.size());
            else
                clearCounter(getContext(), equipCounter);
        });

        modelEntry.getCountersFromRoom(roomTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), counter, list.size());
            else
                clearCounter(getContext(), counter);
        });

        modelEntry.getStairsRampFromRoom(roomTwoBundle.getInt(AMBIENT_ID), 1).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), stairCounter, list.size());
            else
                clearCounter(getContext(), stairCounter);
        });

        modelEntry.getStairsRampFromRoom(roomTwoBundle.getInt(AMBIENT_ID), 2).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0)
                setCounter(getContext(), rampCounter, list.size());
            else
                clearCounter(getContext(), rampCounter);
        });
    }

    private void buttonListener(View v) {
        if (v == returnButton)
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        else if (v == saveButton) {
            if (roomTwoBundle.getBoolean(RECENT_ENTRY))
                Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack(ROOM_LIST, 0);
        } else {
            Fragment fragment = new Fragment();
            if (v == addDoor)
                fragment = new DoorListFragment();
            if (v == addSwitch)
                fragment = new SwitchListFragment();
            if (v == addWindow)
                fragment = new WindowListFragment();
            if (v == addTable)
                fragment = new TableListFragment();
            if (v == addBoard)
                fragment = new BlackboardListFragment();
            if (v == addFreeSpace)
                fragment = new FreeSpaceListFragment();
            if (v == addStairs) {
                fragment = new RampStairsListFragment();
                roomTwoBundle.putInt(RAMP_OR_STAIRS, 1);
            }
            if (v == addRamps) {
                fragment = new RampStairsListFragment();
                roomTwoBundle.putInt(RAMP_OR_STAIRS, 2);
            }
            if (v == addSteps)
                fragment = new SingleStepListFragment();
            if (v == addSlopes)
                fragment = new SlopeListFragment();
            if (v == addFountain)
                fragment = new WaterFountainListFragment();
            if (v == addEquip)
                fragment = new EquipmentListFragment();
            if (v == addCounter)
                fragment = new CounterListFragment();

        fragment.setArguments(roomTwoBundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, fragment).addToBackStack(OTHER_OBJ_LIST).commit();

        }
    }
}