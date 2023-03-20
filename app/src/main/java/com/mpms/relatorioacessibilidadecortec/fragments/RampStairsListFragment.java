package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.RampStairsRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class RampStairsListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeRampStairsList, addRampStairs, invisible;
    TextView rampStairsHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private RampStairsRecViewAdapter rampStairsAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle rStListBundle;

    public RampStairsListFragment() {
        // Required empty public constructor
    }

    public static RampStairsListFragment newInstance() {
        return new RampStairsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            rStListBundle = new Bundle(this.getArguments());
        else
            rStListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRampStairsListViews(view);

        if (rStListBundle.getInt(RAMP_OR_STAIRS) == 1 || rStListBundle.getInt(RAMP_OR_STAIRS) == 2) {
            if (rStListBundle.getBoolean(FROM_EXT_ACCESS)) {
                modelEntry.getStairsRampFromExtAccess(rStListBundle.getInt(AMBIENT_ID), rStListBundle.getInt(RAMP_OR_STAIRS))
                        .observe(getViewLifecycleOwner(), list -> listLayoutCreator(list, this));
                if (rStListBundle.getInt(AMBIENT_ID) == 0)
                modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), lExt -> rStListBundle.putInt(AMBIENT_ID, lExt.getExternalAccessID()));
            } else if (rStListBundle.getBoolean(FROM_SIDEWALK)) {
                modelEntry.getStairsRampFromSidewalk(rStListBundle.getInt(AMBIENT_ID), rStListBundle.getInt(RAMP_OR_STAIRS))
                        .observe(getViewLifecycleOwner(), list -> listLayoutCreator(list, this));
                if (rStListBundle.getInt(AMBIENT_ID) == 0)
                    modelEntry.getLastSidewalkEntry().observe(getViewLifecycleOwner(), lSide -> rStListBundle.putInt(AMBIENT_ID, lSide.getSidewalkID()));
            } else if (rStListBundle.getBoolean(FROM_PARKING)) {
                modelEntry.getStairsRampFromParking(rStListBundle.getInt(PARKING_ID), rStListBundle.getInt(RAMP_OR_STAIRS))
                        .observe(getViewLifecycleOwner(), list -> listLayoutCreator(list, this));
                if (rStListBundle.getInt(AMBIENT_ID) == 0)
                    modelEntry.getLastInsertedParkingLot().observe(getViewLifecycleOwner(), lPark -> rStListBundle.putInt(AMBIENT_ID, lPark.getParkingID()));
            } else if (rStListBundle.getBoolean(FROM_ROOMS)) {
                modelEntry.getStairsRampFromRoom(rStListBundle.getInt(AMBIENT_ID), rStListBundle.getInt(RAMP_OR_STAIRS))
                        .observe(getViewLifecycleOwner(), list -> listLayoutCreator(list, this));
                if (rStListBundle.getInt(AMBIENT_ID) == 0)
                    modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lRoom -> rStListBundle.putInt(AMBIENT_ID, lRoom.getRoomID()));
            }
        } else {
            Toast.makeText(getContext(), "Algo deu errado. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        }


        addRampStairs.setOnClickListener(v -> openRampStairsFragment());

        closeRampStairsList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().setFragmentResult(CLOSE_ACTIVITY, rStListBundle);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        rStListBundle.putInt(RAMP_STAIRS_ID, 0);
    }

    private void listLayoutCreator(List<RampStairsEntry> list, OnEntryClickListener listener) {
        rampStairsAdapter = new RampStairsRecViewAdapter(list, requireActivity(), listener);
        recyclerView.setAdapter(rampStairsAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
        rampStairsAdapter.setListener(listener());
    }

    private void instantiateRampStairsListViews(View v) {
//        MaterialButton
        closeRampStairsList = v.findViewById(R.id.cancel_child_items_entries);
        addRampStairs = v.findViewById(R.id.add_child_items_entries);
        invisible = v.findViewById(R.id.continue_child_items_entries);
        invisible.setVisibility(View.GONE);
//        TextView
        rampStairsHeader = v.findViewById(R.id.identifier_header);
        rampStairsHeader.setVisibility(View.VISIBLE);
        if (rStListBundle.getInt(RAMP_OR_STAIRS) == 1)
            rampStairsHeader.setText(R.string.header_stairs_register);
        else
            rampStairsHeader.setText(R.string.header_ramp_register);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    private ListClickListener listener() {
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
                        rampStairsAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        rampStairsAdapter.cancelSelection(recyclerView);
                    rampStairsAdapter.selectedItems.clear();
                    rampStairsAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = rampStairsAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void openRampStairsFragment() {
        RampStairsFragment rampStairsFragment = RampStairsFragment.newInstance();
        rStListBundle.putInt(InspectionActivity.ALLOW_UPDATE, 0);
        rampStairsFragment.setArguments(rStListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, rampStairsFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        RampStairsEntry rampStairsEntry = modelEntry.allStairsRampsSchool.getValue().get(position);
        rStListBundle.putInt(RAMP_STAIRS_ID, rampStairsEntry.getRampStairsID());
        openRampStairsFragment();
    }
}
