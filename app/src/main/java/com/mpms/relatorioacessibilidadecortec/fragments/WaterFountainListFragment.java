package com.mpms.relatorioacessibilidadecortec.fragments;

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
import com.mpms.relatorioacessibilidadecortec.adapter.WaterRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class WaterFountainListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeFountainList, addFountain, invisible;
    TextView fountHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private WaterRecViewAdapter fountainAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle fountainBundle = new Bundle();

    public WaterFountainListFragment() {
        // Required empty public constructor
    }

    public static WaterFountainListFragment newInstance() {
        return new WaterFountainListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            fountainBundle = new Bundle(getArguments());
        else
            fountainBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateWaterListViews(view);

        if (fountainBundle.getBoolean(FROM_ROOMS) || fountainBundle.getBoolean(CIRCULATION)) {
            fountainBundle.putBoolean(VISIBLE_MEMORIAL, false);
            getParentFragmentManager().setFragmentResult(MEMORIAL, fountainBundle);
        }

        if (fountainBundle.getInt(CIRC_ID) > 0)
            modelEntry.getCircWaterFountains(fountainBundle.getInt(CIRC_ID)).observe(getViewLifecycleOwner(), list -> listCreator(list, this));
        else if (fountainBundle.getBoolean(FROM_ROOMS)) {
            if (fountainBundle.getInt(AMBIENT_ID) == 0)
                modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), room -> fountainBundle.putInt(AMBIENT_ID, room.getRoomID()));

            modelEntry.getRoomWaterFountains(fountainBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), fountain -> listCreator(fountain, this));
        }
        else
            modelEntry.getAllBlockWaterFountain(fountainBundle.getInt(BLOCK_ID)).observe(getViewLifecycleOwner(), list -> listCreator(list, this));


        addFountain.setOnClickListener(v -> OpenFountainFragment());

        closeFountainList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            if (fountainBundle.getBoolean(FROM_ROOMS) || fountainBundle.getBoolean(CIRCULATION))
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            else
                requireActivity().getSupportFragmentManager().setFragmentResult(CLOSE_ACTIVITY, fountainBundle);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        fountainBundle.putInt(FOUNTAIN_ID, 0);
        if (!fountainBundle.getBoolean(FROM_ROOMS) && !fountainBundle.getBoolean(CIRCULATION))
            fountainBundle.putBoolean(VISIBLE_MEMORIAL, true);
        getParentFragmentManager().setFragmentResult(MEMORIAL, fountainBundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (fountainBundle.getBoolean(FROM_ROOMS))
            RoomsRegisterFragment.roomModelFragments.setNewRoomID(fountainBundle.getInt(AMBIENT_ID));
    }

    private void listCreator(List<WaterFountainEntry> list, OnEntryClickListener listener) {
        fountainAdapter = new WaterRecViewAdapter(list, requireActivity(), listener);
        recyclerView.setAdapter(fountainAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
        fountainAdapter.setListener(clickListener());
    }

    private ListClickListener clickListener() {
        return new ListClickListener() {
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
        };
    }

    private void instantiateWaterListViews(View v) {
//        MaterialButton
        closeFountainList = v.findViewById(R.id.cancel_child_items_entries);
        addFountain = v.findViewById(R.id.add_child_items_entries);
        invisible = v.findViewById(R.id.continue_child_items_entries);
        invisible.setVisibility(View.GONE);
//        TextView
        fountHeader = v.findViewById(R.id.identifier_header);
        if (fountainBundle.getBoolean(FROM_ROOMS) || fountainBundle.getBoolean(CIRCULATION)) {
            fountHeader.setVisibility(View.VISIBLE);
            fountHeader.setText(getText(R.string.fountain_reg_header));
        }
//        RecyclerView & methods
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
//        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());


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
                        fountainAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        fountainAdapter.cancelSelection(recyclerView);
                    fountainAdapter.selectedItems.clear();
                    fountainAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = fountainAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        WaterFountainEntry fountainEntry;
        fountainEntry = modelEntry.allFountains.getValue().get(position);
        fountainBundle.putInt(FOUNTAIN_ID, fountainEntry.getWaterFountainID());
        OpenFountainFragment();
    }

    private void OpenFountainFragment() {
        fountainBundle.putBoolean(VISIBLE_MEMORIAL, false);
        WaterFountainFragment fountainFragment = WaterFountainFragment.newInstance();
        fountainFragment.setArguments(fountainBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.show_fragment_selected, fountainFragment).addToBackStack(null).commit();
    }
}
