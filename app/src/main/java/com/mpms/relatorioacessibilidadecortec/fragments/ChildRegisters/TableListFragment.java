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
import com.mpms.relatorioacessibilidadecortec.adapter.TableRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class TableListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeTableList, addTable, continueButton;

    TextView tableHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private TableRecViewAdapter tableAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle tableListBundle;

    public TableListFragment() {

    }

    public static TableListFragment newInstance() {
        return new TableListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            tableListBundle = new Bundle(this.getArguments());
        else
            tableListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateTableListViews(view);

        if (tableListBundle.getInt(CIRC_ID) > 0)
            modelEntry.getTablesFromCirc(tableListBundle.getInt(CIRC_ID)).observe(getViewLifecycleOwner(), list -> listCreator(list, this));
        else {
            if (tableListBundle.getInt(AMBIENT_ID) == 0)
                modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom -> tableListBundle.putInt(AMBIENT_ID, lastRoom.getRoomID()));

            modelEntry.getTablesFromRoom(tableListBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(),list -> listCreator(list, this));
        }

        closeTableList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addTable.setOnClickListener(v -> openTableFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        tableListBundle.putInt(TABLE_ID, 0);
    }

    private void listCreator(List<TableEntry> list, OnEntryClickListener listener) {
        tableAdapter = new TableRecViewAdapter(list, requireActivity(), listener);
        recyclerView.setAdapter(tableAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
        tableAdapter.setListener(clickListener());
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
                        tableAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        tableAdapter.cancelSelection(recyclerView);
                    tableAdapter.selectedItems.clear();
                    tableAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = tableAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        TableEntry tableEntry = modelEntry.allTables.getValue().get(position);
        tableListBundle.putInt(TABLE_ID, tableEntry.getTableID());
        openTableFragment();
    }

    private void openTableFragment() {
        TableFragment tableFragment = TableFragment.newInstance();
        tableFragment.setArguments(tableListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, tableFragment).addToBackStack(null).commit();
    }

    private void instantiateTableListViews(View v) {
        //        TextView
        tableHeader = v.findViewById(R.id.identifier_header);
        tableHeader.setVisibility(View.VISIBLE);
        tableHeader.setText(R.string.header_table_register);
//        MaterialButton
        closeTableList = v.findViewById(R.id.cancel_child_items_entries);
        addTable = v.findViewById(R.id.add_child_items_entries);
        continueButton = v.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }
}
