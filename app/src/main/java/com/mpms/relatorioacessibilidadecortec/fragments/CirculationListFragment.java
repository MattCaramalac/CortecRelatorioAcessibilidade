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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.CirculationRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.CirculationEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CirculationListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeCircList, addCirc, continueButton;

//    TextView circHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private CirculationRecViewAdapter circAdapter;
    private ActionMode actionMode;

    private int delClick = 0;

    Bundle circListBundle = new Bundle();

    public CirculationListFragment() {

    }

    public static CirculationListFragment newInstance() {
        return new CirculationListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            circListBundle = new Bundle(this.getArguments());
        else
            circListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateCircViews(view);

        modelEntry.getAllCirculations(circListBundle.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), circList -> {
            circAdapter = new CirculationRecViewAdapter(circList, requireActivity(), this);
            recyclerView.setAdapter(circAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            circAdapter.setListener(new ListClickListener() {
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

        closeCircList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().setFragmentResult(CLOSE_ACTIVITY, circListBundle);
        });

        addCirc.setOnClickListener(v -> openBoardFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        circListBundle.putInt(CIRC_ID, 0);
        circListBundle.putBoolean(RECENT_ENTRY, false);
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
                        circAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        circAdapter.cancelSelection(recyclerView);
                    circAdapter.selectedItems.clear();
                    circAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = circAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void instantiateCircViews(View view) {
//        //        TextView
//        circHeader = view.findViewById(R.id.identifier_header);
//        circHeader.setVisibility(View.VISIBLE);
//        circHeader.setText(R.string.header_circulation_register);
//        MaterialButton
        closeCircList = view.findViewById(R.id.cancel_child_items_entries);
        addCirc = view.findViewById(R.id.add_child_items_entries);
        continueButton = view.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = view.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    @Override
    public void OnEntryClick(int position) {
        CirculationEntry circulation = modelEntry.allCirculations.getValue().get(position);
        circListBundle.putInt(CIRC_ID, circulation.getCircID());
        openBoardFragment();
    }

    private void openBoardFragment() {
        CirculationFragment circFragment = CirculationFragment.newInstance();
        circFragment.setArguments(circListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, circFragment).addToBackStack(null).commit();
    }
}
