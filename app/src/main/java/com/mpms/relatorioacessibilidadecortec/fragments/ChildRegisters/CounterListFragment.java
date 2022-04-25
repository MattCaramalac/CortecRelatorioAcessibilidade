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
import com.mpms.relatorioacessibilidadecortec.adapter.CounterRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class CounterListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeCounterList, addCounter, continueButton;

    TextView counterHeader;


    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private CounterRecViewAdapter counterAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle counterListBundle = new Bundle();

    public CounterListFragment(){

    }

    public static CounterListFragment newInstance(){
        return new CounterListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            counterListBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
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

        if (counterListBundle.getInt(RoomsRegisterFragment.ROOM_ID) == 0) {
            modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom ->
                    counterListBundle.putInt(RoomsRegisterFragment.ROOM_ID, lastRoom.getRoomID()));
        }

        modelEntry.getCountersFromRoom(counterListBundle.getInt(RoomsRegisterFragment.ROOM_ID)).observe(getViewLifecycleOwner(), counterList -> {
            counterAdapter = new CounterRecViewAdapter(counterList, requireActivity(), this);
            recyclerView.setAdapter(counterAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            counterAdapter.setListener(new ListClickListener() {
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

        closeCounterList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addCounter.setOnClickListener(v -> openSwitchFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        counterListBundle.putInt(CounterFragment.COUNTER_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewRoomID(counterListBundle.getInt(RoomsRegisterFragment.ROOM_ID));
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
                        counterAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        counterAdapter.cancelSelection(recyclerView);
                    counterAdapter.selectedItems.clear();
                    counterAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = counterAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    public void instantiateGateObsViews (View v) {
//        TextView
        counterHeader = v.findViewById(R.id.identifier_header);
        counterHeader.setText(R.string.header_counter_register);
//        MaterialButton
        closeCounterList = v.findViewById(R.id.cancel_child_items_entries);
        addCounter = v.findViewById(R.id.add_child_items_entries);
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
        CounterEntry counterEntry = modelEntry.allCounters.getValue().get(position);
        counterListBundle.putInt(CounterFragment.COUNTER_ID, counterEntry.getCounterID());
        openSwitchFragment();
    }

    private void openSwitchFragment() {
        CounterFragment counterFragment = CounterFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        counterFragment.setArguments(counterListBundle);
        if (actionMode != null)
            actionMode.finish();
        fragmentTransaction.replace(R.id.show_fragment_selected, counterFragment).addToBackStack(null).commit();
    }
}
