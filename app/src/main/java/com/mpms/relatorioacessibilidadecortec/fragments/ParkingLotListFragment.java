package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.ParkingRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class ParkingLotListFragment extends Fragment implements OnEntryClickListener {

    public static final String PARKING_ID = "PARKING_ID";
    public static final String NEW_PARKING_ENTRY = "NEW_PARKING_ENTRY";

    MaterialButton closeParkingList, addParkingLot;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private ParkingRecViewAdapter parkingAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle parkingBundle = new Bundle();

    public ParkingLotListFragment() {
        // Required empty public constructor
    }

    public static ParkingLotListFragment newInstance() {
        return new ParkingLotListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            parkingBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items_entries_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateParkingListViews(view);

        modelEntry.getAllParkingLots(parkingBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER))
                .observe(getViewLifecycleOwner(), parkingLot -> {
                    parkingAdapter = new ParkingRecViewAdapter(parkingLot, requireActivity(), this);
                    recyclerView.setAdapter(parkingAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    parkingAdapter.setListener(new ListClickListener() {
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

        addParkingLot.setOnClickListener(v -> openParkingLotFragment());

        closeParkingList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });
    }

    private void instantiateParkingListViews(View v) {
        closeParkingList = v.findViewById(R.id.close_items_entries_list);
        addParkingLot = v.findViewById(R.id.add_items_entries);
        recyclerView = v.findViewById(R.id.items_entries_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);

        parkingBundle.putInt(PARKING_ID, 0);
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
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (actionMode != null)
            actionMode.finish();
        if (parkingBundle.getInt(PARKING_ID) > 0) {
            parkingLot.setArguments(parkingBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, parkingLot).addToBackStack(null).commit();
        }
        else {
            parkingBundle.putInt(PARKING_ID, 0);
            parkingLot.setArguments(parkingBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, parkingLot).addToBackStack(NEW_PARKING_ENTRY).commit();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        ParkingLotEntry parkingEntry = modelEntry.allParkingLots.getValue().get(position);
        parkingBundle.putInt(PARKING_ID, parkingEntry.getParkingLotID());
        openParkingLotFragment();
    }
}
