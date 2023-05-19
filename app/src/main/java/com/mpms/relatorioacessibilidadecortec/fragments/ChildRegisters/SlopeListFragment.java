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
import com.mpms.relatorioacessibilidadecortec.adapter.SlopeRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class SlopeListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeSlopeList, addSlope, continueButton;

    TextView slopeHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private SlopeRecViewAdapter slopeAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle slopeListBundle;

    public SlopeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            slopeListBundle = new Bundle(this.getArguments());
        else
            slopeListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSlopeListViews(view);

        if (slopeListBundle.getInt(CIRC_ID) > 0) {
            modelEntry.getAllCircSlopes(slopeListBundle.getInt(CIRC_ID)).observe(getViewLifecycleOwner(), list -> listCreator(list, this));
        } else {
            if (slopeListBundle.getInt(AMBIENT_ID) == 0)
                modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom -> slopeListBundle.putInt(AMBIENT_ID, lastRoom.getRoomID()));

            modelEntry.getAllRoomSlopes(slopeListBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), list -> listCreator(list, this));
        }

        closeSlopeList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addSlope.setOnClickListener(v -> openSlopeFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        slopeListBundle.putInt(SLOPE_ID, 0);
    }

    private void listCreator(List<SlopeEntry> list, OnEntryClickListener listener) {
        slopeAdapter = new SlopeRecViewAdapter(list, requireActivity(), listener);
        recyclerView.setAdapter(slopeAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
        slopeAdapter.setListener(clickListener());

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
                        slopeAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        slopeAdapter.cancelSelection(recyclerView);
                    slopeAdapter.selectedItems.clear();
                    slopeAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = slopeAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void instantiateSlopeListViews(View v) {
//        TextView
        slopeHeader = v.findViewById(R.id.identifier_header);
        slopeHeader.setVisibility(View.VISIBLE);
        slopeHeader.setText(getString(R.string.header_slope_register));
//        MaterialButton
        closeSlopeList = v.findViewById(R.id.cancel_child_items_entries);
        addSlope = v.findViewById(R.id.add_child_items_entries);
        continueButton = v.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void openSlopeFragment() {
        slopeListBundle.putBoolean(VISIBLE_MEMORIAL, false);
        SlopeFragment slopeFragment = new SlopeFragment();
        slopeFragment.setArguments(slopeListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, slopeFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        SlopeEntry slopeEntry = modelEntry.allSlopes.getValue().get(position);
        slopeListBundle.putInt(SLOPE_ID, slopeEntry.getSlopeID());
        openSlopeFragment();
    }
}
