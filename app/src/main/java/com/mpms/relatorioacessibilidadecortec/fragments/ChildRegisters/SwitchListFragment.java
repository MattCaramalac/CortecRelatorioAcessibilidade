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
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.Objects;

public class SwitchListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeSwitchList, addSwitch, continueButton;

    TextView switchHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private SwitchRecViewAdapter switchAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle switchListBundle;

    public SwitchListFragment(){

    }

    public static SwitchListFragment newInstance(){
        return new SwitchListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            switchListBundle = new Bundle(this.getArguments());
        else
            switchListBundle = new Bundle();
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

        if (switchListBundle.getInt(AMBIENT_ID) == 0) {
            modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom ->
                    switchListBundle.putInt(AMBIENT_ID, lastRoom.getRoomID()));
        }

        modelEntry.getSwitchesFromRoom(switchListBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), switchList -> {
            switchAdapter = new SwitchRecViewAdapter(switchList, requireActivity(), this);
            recyclerView.setAdapter(switchAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            switchAdapter.setListener(new ListClickListener() {
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

        closeSwitchList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addSwitch.setOnClickListener(v -> openSwitchFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        switchListBundle.putInt(SWITCH_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewRoomID(switchListBundle.getInt(AMBIENT_ID));
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
                        switchAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        switchAdapter.cancelSelection(recyclerView);
                    switchAdapter.selectedItems.clear();
                    switchAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = switchAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    public void instantiateGateObsViews (View v) {
//        TextView
        switchHeader = v.findViewById(R.id.identifier_header);
        switchHeader.setVisibility(View.VISIBLE);
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
        switchListBundle.putInt(SWITCH_ID, switchEntry.getSwitchID());
        openSwitchFragment();
    }

    private void openSwitchFragment() {
        SwitchFragment switchFragment = SwitchFragment.newInstance();
        switchFragment.setArguments(switchListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, switchFragment).addToBackStack(null).commit();
    }
}
