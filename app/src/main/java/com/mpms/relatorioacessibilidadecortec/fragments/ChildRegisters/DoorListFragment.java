package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.DoorRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class DoorListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeDoorList, addDoor, continueButton;

    TextView doorHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private DoorRecViewAdapter doorAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle doorListBundle = new Bundle();

    public DoorListFragment(){

    }

    public static DoorListFragment newInstance(){
        return new DoorListFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            doorListBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateDoorViews(view);

        if (doorListBundle.getInt(RoomsRegisterFragment.ROOM_ID) == 0) {
            modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom ->
                    doorListBundle.putInt(RoomsRegisterFragment.ROOM_ID, lastRoom.getRoomID()));
        }

        modelEntry.getDoorsFromRoom(doorListBundle.getInt(RoomsRegisterFragment.ROOM_ID)).observe(getViewLifecycleOwner(), doorList -> {
            doorAdapter = new DoorRecViewAdapter(doorList, requireActivity(), this);
            recyclerView.setAdapter(doorAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);
        });

        closeDoorList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        addDoor.setOnClickListener(v -> openDoorFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        doorListBundle.putInt(DoorFragment.DOOR_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewRoomID(doorListBundle.getInt(RoomsRegisterFragment.ROOM_ID));
    }

    private void instantiateDoorViews (View v) {
//        TextView
        doorHeader = v.findViewById(R.id.identifier_header);
        doorHeader.setText(getString(R.string.header_door_register));
//        MaterialButton
        closeDoorList = v.findViewById(R.id.cancel_child_items_entries);
        addDoor = v.findViewById(R.id.add_child_items_entries);
        continueButton = v.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void openDoorFragment() {
        DoorFragment doorFragment = DoorFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        doorFragment.setArguments(doorListBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, doorFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        DoorEntry doorEntry = modelEntry.allDoors.getValue().get(position);
        doorListBundle.putInt(DoorFragment.DOOR_ID, doorEntry.getDoorID());
        openDoorFragment();
    }
}