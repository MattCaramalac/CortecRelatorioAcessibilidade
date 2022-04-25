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
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.RestroomRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class RestroomListFragment extends Fragment implements OnEntryClickListener {


    MaterialButton closeRestroomList, addRestroom;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private RestroomRecViewAdapter restroomAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle restListBundle = new Bundle();

    public RestroomListFragment() {
        // Required empty public constructor
    }

    public static RestroomListFragment newInstance() {
        return new RestroomListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            restListBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items_entries_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRestListViews(view);

        modelEntry.getAllSchoolRestroomEntries(restListBundle.getInt(SchoolRegisterActivity.SCHOOL_ID)).
                observe(getViewLifecycleOwner(), restEntry -> {
                    restroomAdapter = new RestroomRecViewAdapter(restEntry, requireActivity(), this);
                    recyclerView.setAdapter(restroomAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    restroomAdapter.setListener(new ListClickListener() {
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

        closeRestroomList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

        addRestroom.setOnClickListener(v -> OpenRestFragment());

    }

    private void instantiateRestListViews(View v) {
        closeRestroomList = v.findViewById(R.id.close_items_entries_list);
        addRestroom = v.findViewById(R.id.add_items_entries);
        recyclerView = v.findViewById(R.id.items_entries_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);

        restListBundle.putInt(RestroomFragment.RESTROOM_ID,0);
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
                        restroomAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        restroomAdapter.cancelSelection(recyclerView);
                    restroomAdapter.selectedItems.clear();
                    restroomAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = restroomAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        RestroomEntry restroomEntry = modelEntry.allRestSchool.getValue().get(position);

        restListBundle.putInt(RestroomFragment.RESTROOM_ID, restroomEntry.getRestroomID());

        OpenRestFragment();
    }

    private void OpenRestFragment() {
        RestroomFragment restroomFragment = RestroomFragment.newInstance();
        restroomFragment.setArguments(restListBundle);
        if (actionMode != null)
            actionMode.finish();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, restroomFragment).addToBackStack(null).commit();
    }
}
