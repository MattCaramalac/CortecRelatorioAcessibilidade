package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.SidewalkSlopeRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class SidewalkSlopeListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeSideSlopeList, addSideSlope, c;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private SidewalkSlopeRecViewAdapter sideSlopeAdapter;
    private ActionMode actionMode;

    TextView registerHeader;

    int delClick = 0;

    Bundle sideSlopeBundle;

    public SidewalkSlopeListFragment() {
        // Required empty public constructor
    }

    public static SidewalkSlopeListFragment newInstance() {
        return new SidewalkSlopeListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            sideSlopeBundle = new Bundle(this.getArguments());
        else
            sideSlopeBundle = new Bundle();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSideSlopeViews(view);

        modelEntry.getSideSlopes(sideSlopeBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), sideSlopeList -> {
                    sideSlopeAdapter = new SidewalkSlopeRecViewAdapter(sideSlopeList, requireActivity(), this);
                    recyclerView.setAdapter(sideSlopeAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    sideSlopeAdapter.setListener(new ListClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            if (actionMode == null)
                                OnEntryClick(position);
                            else
                                enableActionMode(sideSlopeList);
                        }

                        @Override
                        public void onItemLongClick(int position) {
                            enableActionMode(sideSlopeList);
                        }
                    });
                });

        addSideSlope.setOnClickListener(v -> openSideSlopeFragment());

        closeSideSlopeList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        sideSlopeBundle.putInt(SIDEWALK_SLOPE_ID, 0);
    }

    private void instantiateSideSlopeViews (View v) {
        closeSideSlopeList = v.findViewById(R.id.cancel_child_items_entries);
        addSideSlope = v.findViewById(R.id.add_child_items_entries);
        c = v.findViewById(R.id.continue_child_items_entries);
        c.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        TextView
        registerHeader = v.findViewById(R.id.identifier_header);
        registerHeader.setVisibility(View.VISIBLE);
        registerHeader.setText(R.string.header_slope_list_register);
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
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
                        sideSlopeAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        sideSlopeAdapter.cancelSelection(recyclerView, entries, sideSlopeAdapter);
                    sideSlopeAdapter.selectedItems.clear();
                    sideSlopeAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = sideSlopeAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void openSideSlopeFragment() {
        SidewalkSlopeFragment slopeFragment = SidewalkSlopeFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        slopeFragment.setArguments(sideSlopeBundle);
        if (actionMode != null)
            actionMode.finish();
        fragmentTransaction.replace(R.id.show_fragment_selected, slopeFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        SidewalkSlopeEntry slopeEntry = modelEntry.allSidewalkSlopes.getValue().get(position);
        sideSlopeBundle.putInt(SIDEWALK_SLOPE_ID, slopeEntry.getSideSlopeID());
        openSideSlopeFragment();
    }


}
