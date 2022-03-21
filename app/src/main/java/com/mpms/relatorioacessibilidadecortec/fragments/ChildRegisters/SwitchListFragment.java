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
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.SwitchRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class SwitchListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeSwitchList, addSwitch, continueButton;

    TextView switchHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private SwitchRecViewAdapter switchAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle switchListBundle = new Bundle();

    public SwitchListFragment(){

    }

    public static SwitchListFragment newInstance(){
        return new SwitchListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            switchListBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateGateObsViews(view);

        if (switchListBundle.getInt(RoomsRegisterFragment.ROOM_ID) == 0) {
            modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom ->
                    switchListBundle.putInt(RoomsRegisterFragment.ROOM_ID, lastRoom.getRoomID()));
        }

        modelEntry.getSwitchesFromRoom(switchListBundle.getInt(RoomsRegisterFragment.ROOM_ID)).observe(getViewLifecycleOwner(), switchList -> {
            switchAdapter = new SwitchRecViewAdapter(switchList, requireActivity(), this);
            recyclerView.setAdapter(switchAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);
        });

        closeSwitchList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        addSwitch.setOnClickListener(v -> openSwitchFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        switchListBundle.putInt(SwitchFragment.SWITCH_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewRoomID(switchListBundle.getInt(RoomsRegisterFragment.ROOM_ID));
    }

    public void instantiateGateObsViews (View v) {
//        TextView
        switchHeader = v.findViewById(R.id.identifier_header);
        switchHeader.setText(R.string.header_switch_register);
//        MaterialButton
        closeSwitchList = v.findViewById(R.id.cancel_child_items_entries);
        addSwitch = v.findViewById(R.id.add_child_items_entries);
        continueButton = v.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    @Override
    public void OnEntryClick(int position) {
        SwitchEntry switchEntry = modelEntry.allSwitches.getValue().get(position);
        switchListBundle.putInt(SwitchFragment.SWITCH_ID, switchEntry.getSwitchID());
        openSwitchFragment();
    }

    private void openSwitchFragment() {
        SwitchFragment switchFragment = SwitchFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switchFragment.setArguments(switchListBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, switchFragment).addToBackStack(null).commit();
    }
}
