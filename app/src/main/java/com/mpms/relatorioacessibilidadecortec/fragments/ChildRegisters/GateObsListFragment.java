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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.GateObstacleViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class GateObsListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeGateList, addGateObs, continueButton;

    TextView gateObsHeader;

    ViewModelFragments modelFragments;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private GateObstacleViewAdapter gateObsAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle gateObsBundle = new Bundle();

    public GateObsListFragment(){

    }

    public static GateObsListFragment newInstance(){
        return new GateObsListFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            gateObsBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateGateObsViews(view);

        if (gateObsBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) == 0) {
            modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), lastExtAccess ->
                    gateObsBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, lastExtAccess.getExternalAccessID()));
        }

        modelEntry.getAllGateObsEntries(gateObsBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), gateObsList -> {
            gateObsAdapter = new GateObstacleViewAdapter(gateObsList, requireActivity(), this);
            recyclerView.setAdapter(gateObsAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            gateObsAdapter.setListener(new ListClickListener() {
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

        closeGateList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addGateObs.setOnClickListener(v -> openGateObsFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        gateObsBundle.putInt(GateObsFragment.GATE_OBS_ID, 0);
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
                        gateObsAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        gateObsAdapter.cancelSelection(recyclerView);
                    gateObsAdapter.selectedItems.clear();
                    gateObsAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = gateObsAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    public void instantiateGateObsViews (View v) {
//        TextView
        gateObsHeader = v.findViewById(R.id.identifier_header);
        gateObsHeader.setVisibility(View.VISIBLE);
        gateObsHeader.setText(R.string.header_text_obstacle_register);
//        MaterialButton
        closeGateList = v.findViewById(R.id.cancel_child_items_entries);
        addGateObs = v.findViewById(R.id.add_child_items_entries);
        continueButton = v.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);

    }

    @Override
    public void OnEntryClick(int position) {
        GateObsEntry gateObsEntry = modelEntry.allGateObs.getValue().get(position);
        gateObsBundle.putInt(GateObsFragment.GATE_OBS_ID, gateObsEntry.getGateObsID());
        openGateObsFragment();
    }

    private void openGateObsFragment() {
        GateObsFragment gateObsFragment = GateObsFragment.newInstance();
        gateObsFragment.setArguments(gateObsBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, gateObsFragment).addToBackStack(null).commit();
    }
}
