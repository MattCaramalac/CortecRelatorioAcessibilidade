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
import com.mpms.relatorioacessibilidadecortec.adapter.SidewalkRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class SidewalkListFragment extends Fragment implements OnEntryClickListener {

    public static final String NEW_SIDEWALK_ENTRY = "NEW_SIDEWALK_ENTRY";

    MaterialButton closeSidewalkList, addSidewalk;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private SidewalkRecViewAdapter sidewalkAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle sidewalkBundle = new Bundle();

    public SidewalkListFragment() {
        // Required empty public constructor
    }

    public static SidewalkListFragment newInstance() {
        return new SidewalkListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            sidewalkBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
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

        instantiateSidewalkListViews(view);

        modelEntry.getAllSidewalks(sidewalkBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER))
                .observe(getViewLifecycleOwner(), sidewalkEntryList -> {
                    sidewalkAdapter = new SidewalkRecViewAdapter(sidewalkEntryList, requireActivity(), this);
                    recyclerView.setAdapter(sidewalkAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    sidewalkAdapter.setListener(new ListClickListener() {
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

        addSidewalk.setOnClickListener(v -> openSidewalkFragment());

        closeSidewalkList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });
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
                        sidewalkAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        sidewalkAdapter.cancelSelection(recyclerView);
                    sidewalkAdapter.selectedItems.clear();
                    sidewalkAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = sidewalkAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        SidewalkEntry sidewalk =  modelEntry.allSidewalks.getValue().get(position);
        sidewalkBundle.putInt(SidewalkFragment.SIDEWALK_ID, sidewalk.getSidewalkID());
        openSidewalkFragment();
    }

    private void openSidewalkFragment() {
        SidewalkFragment sidewalkFrag = SidewalkFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        if (actionMode != null)
            actionMode.finish();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (sidewalkBundle.getInt(SidewalkFragment.SIDEWALK_ID) > 0) {
            sidewalkFrag.setArguments(sidewalkBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, sidewalkFrag).addToBackStack(null).commit();
        }
        else {
            sidewalkBundle.putInt(SidewalkFragment.SIDEWALK_ID, 0);
            sidewalkFrag.setArguments(sidewalkBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, sidewalkFrag).addToBackStack(NEW_SIDEWALK_ENTRY).commit();
        }
    }

    private void instantiateSidewalkListViews(View v) {
        closeSidewalkList = v.findViewById(R.id.close_items_entries_list);
        addSidewalk = v.findViewById(R.id.add_items_entries);
        recyclerView = v.findViewById(R.id.items_entries_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);

        sidewalkBundle.putInt(SidewalkFragment.SIDEWALK_ID, 0);
    }
}
