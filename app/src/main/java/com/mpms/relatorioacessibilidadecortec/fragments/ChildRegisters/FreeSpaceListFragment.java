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
import com.mpms.relatorioacessibilidadecortec.adapter.FreeSpaceRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class FreeSpaceListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeFreeList, addFreeSpace, continueButton;

    TextView fSpaceHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private FreeSpaceRecViewAdapter fSpaceAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle fSpaceListBundle = new Bundle();

    public FreeSpaceListFragment(){

    }

    public static FreeSpaceListFragment newInstance() {
        return new FreeSpaceListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            fSpaceListBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateFreeSpaceViews(view);

        if (fSpaceListBundle.getInt(RoomsRegisterFragment.ROOM_ID) == 0) {
            modelEntry.getLastDoorEntry().observe(getViewLifecycleOwner(), lastDoor ->
                    fSpaceListBundle.putInt(RoomsRegisterFragment.ROOM_ID, lastDoor.getRoomID()));
        }

        modelEntry.selectFreeSpaceFromRoom(fSpaceListBundle.getInt(RoomsRegisterFragment.ROOM_ID)).observe(getViewLifecycleOwner(), fSpaceList -> {
            fSpaceAdapter = new FreeSpaceRecViewAdapter(fSpaceList, requireActivity(), this);
            recyclerView.setAdapter(fSpaceAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            fSpaceAdapter.setListener(new ListClickListener() {
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

        closeFreeList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addFreeSpace.setOnClickListener(v -> openFreeSpaceFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        fSpaceListBundle.putInt(FreeSpaceFragment.FREE_SPACE_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewRoomID(fSpaceListBundle.getInt(RoomsRegisterFragment.ROOM_ID));
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
                        fSpaceAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        fSpaceAdapter.cancelSelection(recyclerView);
                    fSpaceAdapter.selectedItems.clear();
                    fSpaceAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = fSpaceAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void openFreeSpaceFragment() {
        FreeSpaceFragment fSpaceFragment = FreeSpaceFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fSpaceFragment.setArguments(fSpaceListBundle);
        if (actionMode != null)
            actionMode.finish();
        fragmentTransaction.replace(R.id.show_fragment_selected, fSpaceFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        FreeSpaceEntry fSpace = modelEntry.allFreeSpaces.getValue().get(position);
        fSpaceListBundle.putInt(FreeSpaceFragment.FREE_SPACE_ID, fSpace.getFreeSpaceID());
        openFreeSpaceFragment();
    }

    private void instantiateFreeSpaceViews(View view) {
//        MaterialButton
        closeFreeList = view.findViewById(R.id.cancel_child_items_entries);
        addFreeSpace = view.findViewById(R.id.add_child_items_entries);
        continueButton = view.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        TextView
        fSpaceHeader = view.findViewById(R.id.identifier_header);
        fSpaceHeader.setText(getString(R.string.header_free_space_register));
//        RecyclerView
        recyclerView = view.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }
}
