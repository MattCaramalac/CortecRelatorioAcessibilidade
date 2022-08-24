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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.ParkingRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.Objects;

public class ParkingLotListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    //    TODO - Trocar por AmbientID?
    public static final String PARKING_ID = "PARKING_ID";

    MaterialButton closeParkingList, addParkingLot, invisible;
    TextView parkingHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private ParkingRecViewAdapter parkingAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle parkListBundle;

    public ParkingLotListFragment() {
        // Required empty public constructor
    }

    public static ParkingLotListFragment newInstance() {
        return new ParkingLotListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            parkListBundle = new Bundle(this.getArguments());
        else
            parkListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateParkingListViews(view);

        if (parkListBundle.getBoolean(FROM_SIDEWALK))
            modelEntry.getParkingLotFromSide(parkListBundle.getInt(BLOCK_ID), parkListBundle.getInt(AMBIENT_ID))
                    .observe(getViewLifecycleOwner(), parkingLot -> {
                        parkingAdapter = new ParkingRecViewAdapter(parkingLot, requireActivity(), this);
                        listCreator(parkingAdapter);
                    });
        else
            modelEntry.getParkingLotsFromBlocks(parkListBundle.getInt(BLOCK_ID))
                    .observe(getViewLifecycleOwner(), parkingLot -> {
                        parkingAdapter = new ParkingRecViewAdapter(parkingLot, requireActivity(), this);
                        listCreator(parkingAdapter);
                    });

        addParkingLot.setOnClickListener(v -> openParkingLotFragment());

        closeParkingList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            if (parkListBundle.getBoolean(FROM_SIDEWALK))
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            else
                requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        parkListBundle.putInt(PARKING_ID, 0);
    }

    private void instantiateParkingListViews(View v) {
//        MaterialButton
        closeParkingList = v.findViewById(R.id.cancel_child_items_entries);
        addParkingLot = v.findViewById(R.id.add_child_items_entries);
        invisible = v.findViewById(R.id.continue_child_items_entries);
        invisible.setVisibility(View.GONE);
//        TextView
        parkingHeader = v.findViewById(R.id.identifier_header);
        if (parkListBundle.getBoolean(EXT_AREA_REG))
            parkingHeader.setText(R.string.ext_park_reg_header);
        else if (parkListBundle.getBoolean(SUP_AREA_REG))
            parkingHeader.setText(R.string.int_park_reg_header);
        else
            parkingHeader.setText(R.string.side_park_reg_header);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    private void listCreator(ParkingRecViewAdapter adapter) {
        adapter.setListener(clickListener());

        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
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
                        parkingAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        parkingAdapter.cancelSelection(recyclerView);
                    parkingAdapter.selectedItems.clear();
                    parkingAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = parkingAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void openParkingLotFragment() {
        ParkingLotFragment parkingLot = ParkingLotFragment.newInstance();
        parkingLot.setArguments(parkListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, parkingLot).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        ParkingLotEntry parkingEntry = modelEntry.allParkingLots.getValue().get(position);
        parkListBundle.putInt(PARKING_ID, parkingEntry.getParkingID());
        openParkingLotFragment();
    }
}
