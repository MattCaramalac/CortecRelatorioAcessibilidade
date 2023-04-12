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
import com.mpms.relatorioacessibilidadecortec.adapter.StepRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.SingleStepEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.Objects;

public class SingleStepListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeStepList, addStep, continueButton;

    TextView stepHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private StepRecViewAdapter stepAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle stepListBundle;

    public SingleStepListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            stepListBundle = new Bundle(this.getArguments());
        else
            stepListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateStepListViews(view);

        if (stepListBundle.getInt(CIRC_ID) == 0) {
            modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom ->
                    stepListBundle.putInt(CIRC_ID, lastRoom.getRoomID()));
        }

        modelEntry.getAllCircSingleSteps(stepListBundle.getInt(CIRC_ID)).observe(getViewLifecycleOwner(), stepList -> {
            stepAdapter = new StepRecViewAdapter(stepList, requireActivity(), this);
            recyclerView.setAdapter(stepAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            stepAdapter.setListener(new ListClickListener() {
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

        closeStepList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addStep.setOnClickListener(v -> openSwitchFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        stepListBundle.putInt(STEP_ID, 0);
        stepListBundle.putInt(RECENT_ENTRY, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        RoomsRegisterFragment.roomModelFragments.setNewRoomID(stepListBundle.getInt(AMBIENT_ID));
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
                        stepAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        stepAdapter.cancelSelection(recyclerView);
                    stepAdapter.selectedItems.clear();
                    stepAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = stepAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void instantiateStepListViews(View v) {
//        TextView
        stepHeader = v.findViewById(R.id.identifier_header);
        stepHeader.setVisibility(View.VISIBLE);
        stepHeader.setText("Cadastro de Degraus Isolados");
//        MaterialButton
        closeStepList = v.findViewById(R.id.cancel_child_items_entries);
        addStep = v.findViewById(R.id.add_child_items_entries);
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
        SingleStepEntry step = modelEntry.allSteps.getValue().get(position);
        stepListBundle.putInt(STEP_ID, step.getStepID());
        openSwitchFragment();
    }

    private void openSwitchFragment() {
        SingleStepFragment stepFragment = SingleStepFragment.newInstance();
        stepFragment.setArguments(stepListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, stepFragment).addToBackStack(null).commit();
    }
}
