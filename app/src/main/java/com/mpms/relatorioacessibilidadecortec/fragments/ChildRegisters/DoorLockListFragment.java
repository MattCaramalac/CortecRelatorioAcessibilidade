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
import com.mpms.relatorioacessibilidadecortec.adapter.DoorLockRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class DoorLockListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeLockList, addLock, continueButton;

    TextView lockHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private DoorLockRecViewAdapter lockAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle lockListBundle = new Bundle();

    public DoorLockListFragment(){

    }

    public static DoorLockListFragment newInstance(){
        return new DoorLockListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            lockListBundle.putInt(DoorFragment.DOOR_ID, this.getArguments().getInt(DoorFragment.DOOR_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateGateObsViews(view);

        if (lockListBundle.getInt(DoorFragment.DOOR_ID) == 0) {
            modelEntry.getLastDoorEntry().observe(getViewLifecycleOwner(), lastDoor ->
                    lockListBundle.putInt(DoorFragment.DOOR_ID, lastDoor.getDoorID()));
        }

        modelEntry.getDoorLocksFromDoor(lockListBundle.getInt(DoorFragment.DOOR_ID)).observe(getViewLifecycleOwner(), lockList -> {
            lockAdapter = new DoorLockRecViewAdapter(lockList, requireActivity(), this);
            recyclerView.setAdapter(lockAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);
        });

        closeLockList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        addLock.setOnClickListener(v -> openDoorLockFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        lockListBundle.putInt(DoorLockFragment.LOCK_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewChildRegID(lockListBundle.getInt(DoorFragment.DOOR_ID));
    }

    public void instantiateGateObsViews (View v) {
//        TextView
        lockHeader = v.findViewById(R.id.identifier_header);
        lockHeader.setText(R.string.header_door_lock_register);
//        MaterialButton
        closeLockList = v.findViewById(R.id.cancel_child_items_entries);
        addLock = v.findViewById(R.id.add_child_items_entries);
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
        DoorLockEntry lockEntry = modelEntry.allDoorLocks.getValue().get(position);
        lockListBundle.putInt(DoorLockFragment.LOCK_ID, lockEntry.getLockID());
        openDoorLockFragment();
    }

    private void openDoorLockFragment() {
        DoorLockFragment lockFragment = DoorLockFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        lockFragment.setArguments(lockListBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, lockFragment).addToBackStack(null).commit();
    }
}
