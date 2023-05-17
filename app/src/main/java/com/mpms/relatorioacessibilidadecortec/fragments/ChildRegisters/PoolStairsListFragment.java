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
import com.mpms.relatorioacessibilidadecortec.adapter.PoolStairsViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolStairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class PoolStairsListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeItemList, addItem, invisible;
    TextView itemHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private PoolStairsViewAdapter poolStairsAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle itemBundle;

    public PoolStairsListFragment() {
    }

    public static PoolStairsListFragment newInstance() {
        return new PoolStairsListFragment();
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
            modelEntry.getPoolStairs(itemBundle.getInt(POOL_ID)).observe(getViewLifecycleOwner(), list -> listCreator(list, this));

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
        itemBundle.putInt(PSTAIRS_ID, 0);
        itemBundle.putInt(RECENT_ENTRY, 0);
    }

    private void listCreator(List<PoolStairsEntry> list, OnEntryClickListener listener) {
        poolStairsAdapter = new PoolStairsViewAdapter(list, requireActivity(), listener);
        recyclerView.setAdapter(poolStairsAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
        poolStairsAdapter.setListener(clickListener());
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
                        poolStairsAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        poolStairsAdapter.cancelSelection(recyclerView);
                    poolStairsAdapter.selectedItems.clear();
                    poolStairsAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = poolStairsAdapter.selectedItems.size();
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
        itemHeader.setText("Cadstro de Escadas para Piscina");
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    @Override
    public void OnEntryClick(int position) {
        PoolStairsEntry stairs = modelEntry.allPoolStairs.getValue().get(position);
        itemBundle.putInt(PSTAIRS_ID, stairs.getPoolStairsID());
        openFragment();
    }

    private void openFragment() {
        PoolStairsFragment fragment = PoolStairsFragment.newInstance();
        fragment.setArguments(itemBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
    }
}
