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
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.PoolRampViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolRampEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class PoolRampListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeItemList, addItem, invisible;
    TextView itemHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private PoolRampViewAdapter poolRampAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle itemBundle;

    public PoolRampListFragment() {
//        Required Empty
    }

    public static PoolRampListFragment newInstance() {
        return new PoolRampListFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            itemBundle = new Bundle(this.getArguments());
        else
            itemBundle = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateItemListViews(view);

        if (itemBundle.getInt(POOL_ID) > 0)
            modelEntry.getPoolRamps(itemBundle.getInt(POOL_ID)).observe(getViewLifecycleOwner(), list -> listCreator(list, this));

        closeItemList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addItem.setOnClickListener(v -> openFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        itemBundle.putInt(PRAMP_ID, 0);
        itemBundle.putInt(RECENT_ENTRY, 0);
    }

    private void listCreator(List<PoolRampEntry> list, OnEntryClickListener listener) {
        poolRampAdapter = new PoolRampViewAdapter(list, requireActivity(), listener);
        recyclerView.setAdapter(poolRampAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
        poolRampAdapter.setListener(clickListener(list));
    }

    private <T> ListClickListener clickListener(List<T> entries) {
        return new ListClickListener() {
            @Override
            public void onItemClick(int position) {
                if (actionMode == null)
                    OnEntryClick(position);
                else
                    enableActionMode(entries);
            }

            @Override
            public void onItemLongClick(int position) {
                enableActionMode(entries);
            }
        };
    }

    private <T> void enableActionMode(List<T> entries) {
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
                        poolRampAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        poolRampAdapter.cancelSelection(recyclerView, entries, poolRampAdapter);
                    poolRampAdapter.selectedItems.clear();
                    poolRampAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = poolRampAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void instantiateItemListViews(View v) {
//        MaterialButton
        closeItemList = v.findViewById(R.id.cancel_child_items_entries);
        addItem = v.findViewById(R.id.add_child_items_entries);
        invisible = v.findViewById(R.id.continue_child_items_entries);
        invisible.setVisibility(View.GONE);
//        TextView
        itemHeader = v.findViewById(R.id.identifier_header);
        itemHeader.setVisibility(View.VISIBLE);
        itemHeader.setText("Cadstro de Rampas para Piscina");
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    @Override
    public void OnEntryClick(int position) {
        PoolRampEntry ramp = modelEntry.allPoolRamps.getValue().get(position);
        itemBundle.putInt(PRAMP_ID, ramp.getPoolRampID());
        openFragment();
    }

    private void openFragment() {
        PoolRampFragment fragment = PoolRampFragment.newInstance();
        fragment.setArguments(itemBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
    }
}
