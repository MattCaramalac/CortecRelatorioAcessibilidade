package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

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
import com.mpms.relatorioacessibilidadecortec.adapter.PoolRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.InspectionViewModel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class PoolListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closePoolList, addPool, continuePool;

    TextView registerHeader;

    private ViewModelEntry modelEntry;
    private InspectionViewModel dataView;
    private RecyclerView recyclerView;
    private PoolRecViewAdapter poolAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle poolBundle = new Bundle();

    public PoolListFragment() {
//        Required empty public construct
    }

    public static PoolListFragment newInstance() {
        return new PoolListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            poolBundle.putInt(BLOCK_ID, this.getArguments().getInt(BLOCK_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiatePlayListViews(view);

        modelEntry.getAllPoolsPerBlock(poolBundle.getInt(BLOCK_ID)).observe(getViewLifecycleOwner(), poolList -> {
                    poolAdapter = new PoolRecViewAdapter(poolList, requireActivity(), this);
                    recyclerView.setAdapter(poolAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    poolAdapter.setListener(new ListClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            if (actionMode == null)
                                OnEntryClick(position);
                            else
                                enableActionMode(poolList);
                        }

                        @Override
                        public void onItemLongClick(int position) {
                            enableActionMode(poolList);
                        }
                    });
                });

        addPool.setOnClickListener(v -> openPoolFragment());

        closePoolList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().setFragmentResult(CLOSE_ACTIVITY, poolBundle);
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        poolBundle.putInt(POOL_ID, 0);
        poolBundle.putBoolean(RECENT_ENTRY, false);
        dataView.setVisible(true);
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
                        poolAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        poolAdapter.cancelSelection(recyclerView, entries, poolAdapter);
                    poolAdapter.selectedItems.clear();
                    poolAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }
    }

    @Override
    public void OnEntryClick(int position) {
        PoolEntry poolEntry = modelEntry.allPools.getValue().get(position);
        poolBundle.putInt(POOL_ID, poolEntry.getPoolID());
        openPoolFragment();
    }

    private void openPoolFragment() {
        PoolFragment pool = PoolFragment.newInstance();
        pool.setArguments(poolBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, pool).addToBackStack(null).commit();
    }


    private void instantiatePlayListViews(View v) {
//        MaterialButton
        closePoolList = v.findViewById(R.id.cancel_child_items_entries);
        addPool = v.findViewById(R.id.add_child_items_entries);
        continuePool = v.findViewById(R.id.continue_child_items_entries);
        continuePool.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        TextView
        registerHeader = v.findViewById(R.id.identifier_header);
        registerHeader.setVisibility(View.GONE);

//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
        dataView = new ViewModelProvider(requireActivity()).get(InspectionViewModel.class);
    }


}
