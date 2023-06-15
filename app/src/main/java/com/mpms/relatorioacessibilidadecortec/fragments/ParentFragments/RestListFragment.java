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
import com.mpms.relatorioacessibilidadecortec.adapter.RestroomRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.InspectionViewModel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class RestListFragment extends Fragment implements OnEntryClickListener, TagInterface {


    MaterialButton closeRestroomList, addRestroom, invisible;
    TextView restroomIdentifier;

    private ViewModelEntry modelEntry;
    private InspectionViewModel dataView;
    private RecyclerView recyclerView;
    private RestroomRecViewAdapter restroomAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle restListBundle = new Bundle();

    public RestListFragment() {
        // Required empty public constructor
    }

    public static RestListFragment newInstance() {
        return new RestListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            restListBundle = new Bundle(this.getArguments());
            restListBundle.putBoolean(RECENT_ENTRY, false);
        }
        else
            restListBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRestListViews(view);

        modelEntry.getAllRestEntriesInBlock(restListBundle.getInt(BLOCK_ID)).observe(getViewLifecycleOwner(), restEntry -> {
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
                                enableActionMode(restEntry);
                        }

                        @Override
                        public void onItemLongClick(int position) {
                            enableActionMode(restEntry);
                        }
                    });
                });

        closeRestroomList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().setFragmentResult(CLOSE_ACTIVITY, restListBundle);
        });

        addRestroom.setOnClickListener(v -> OpenRestFragment());

    }

    @Override
    public void onResume() {
        super.onResume();
        restListBundle.putInt(REST_ID,0);
        dataView.setVisible(true);
    }

    private void instantiateRestListViews(View v) {
//        MaterialButton
        closeRestroomList = v.findViewById(R.id.cancel_child_items_entries);
        addRestroom = v.findViewById(R.id.add_child_items_entries);
        invisible = v.findViewById(R.id.continue_child_items_entries);
        invisible.setVisibility(View.GONE);
//        TextView
        restroomIdentifier = v.findViewById(R.id.identifier_header);
        restroomIdentifier.setVisibility(View.GONE);

//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
        dataView = new ViewModelProvider(requireActivity()).get(InspectionViewModel.class);
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
                        restroomAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        restroomAdapter.cancelSelection(recyclerView, entries, restroomAdapter);
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
        restListBundle.putInt(REST_ID, restroomEntry.getRestroomID());
        OpenRestFragment();
    }

    private void OpenRestFragment() {
        restListBundle.putBoolean(VISIBLE_MEMORIAL, false);
        RestFragment restFragment = RestFragment.newInstance();
        restFragment.setArguments(restListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.show_fragment_selected, restFragment).addToBackStack(null).commit();
    }
}
