package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
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
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.Objects;

public class DoorLockListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeLockList, addLock, continueButton;

    TextView lockHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private DoorLockRecViewAdapter lockAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle lockListBundle = new Bundle();

    public DoorLockListFragment(){

    }

    public static DoorLockListFragment newInstance(){
        return new DoorLockListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            lockListBundle.putInt(DOOR_ID, this.getArguments().getInt(DOOR_ID));
            lockListBundle.putInt(AMBIENT_ID, this.getArguments().getInt(AMBIENT_ID));
        }
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

        if (lockListBundle.getInt(DOOR_ID) == 0 && lockListBundle.getInt(AMBIENT_ID) == 0) {
            modelEntry.getLastDoorEntry().observe(getViewLifecycleOwner(), lastDoor ->
                    lockListBundle.putInt(DOOR_ID, lastDoor.getDoorID()));
        }

//        Necessário para travar este observador. Quando trocar as tabelas, será mudado o método
        if (lockListBundle.getInt(AMBIENT_ID) == 0) {
            modelEntry.getDoorLocksFromDoor(lockListBundle.getInt(DOOR_ID)).observe(getViewLifecycleOwner(), lockList -> {
                lockAdapter = new DoorLockRecViewAdapter(lockList, requireActivity(), this);
                recyclerView.setAdapter(lockAdapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                recyclerView.addItemDecoration(dividerItemDecoration);

                lockAdapter.setListener(new ListClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (actionMode == null)
                            OnEntryClick(position);
                        else
                            enableActionMode();
                    }

                    @Override
                    public void onItemLongClick(int position) {
                        enableActionMode();
                    }
                });
            });
        }


        modelEntry.getDoorLocksFromGates(lockListBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), extLockList -> {
            lockAdapter = new DoorLockRecViewAdapter(extLockList, requireActivity(), this);
            recyclerView.setAdapter(lockAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            lockAdapter.setListener(new ListClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (actionMode == null)
                        OnEntryClick(position);
                    else
                        enableActionMode();
                }

                @Override
                public void onItemLongClick(int position) {
                    enableActionMode();
                }
            });
        });

        closeLockList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

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
        if (lockListBundle.getInt(AMBIENT_ID) == 0)
            RoomsRegisterFragment.roomModelFragments.setNewChildRegID(lockListBundle.getInt(DOOR_ID));
    }

    private void enableActionMode() {
        if (actionMode == null) {
            AppCompatActivity activity = (AppCompatActivity) requireActivity();
            actionMode = activity.startSupportActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return true;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    if (item.getItemId() == R.id.delete_button) {
                        delClick = 1;
                        lockAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        lockAdapter.cancelSelection(recyclerView);
                    lockAdapter.selectedItems.clear();
                    lockAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = lockAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
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
        DoorLockEntry lockEntry;
        if (lockListBundle.getInt(AMBIENT_ID) == 0) {
            lockEntry = modelEntry.allDoorLocks.getValue().get(position);
        }
        else {
            lockEntry = modelEntry.allDoorLocksGates.getValue().get(position);
        }
        lockListBundle.putInt(LOCK_ID, lockEntry.getLockID());
        openDoorLockFragment();
    }

    private void openDoorLockFragment() {
        DoorLockFragment lockFragment = DoorLockFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        lockFragment.setArguments(lockListBundle);
        if (actionMode != null)
            actionMode.finish();
        fragmentTransaction.replace(R.id.show_fragment_selected, lockFragment).addToBackStack(null).commit();
    }
}
