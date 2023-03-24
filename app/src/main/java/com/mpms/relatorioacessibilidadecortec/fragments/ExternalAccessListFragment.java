package com.mpms.relatorioacessibilidadecortec.fragments;

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
import com.mpms.relatorioacessibilidadecortec.adapter.ExtAccRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.Objects;

public class ExternalAccessListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeExtAccess, addExtAccess, invisible;
    TextView extAccessHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private ExtAccRecViewAdapter extAccAdapter;
    ViewModelFragments modelFragments;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle extListBundle;


    public ExternalAccessListFragment() {
        // Required empty public constructor
    }

    public static ExternalAccessListFragment newInstance() {
        return new ExternalAccessListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            extListBundle = new Bundle(this.getArguments());
        else
            extListBundle = new Bundle();
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

        instantiateExtAccListViews(view);

        modelEntry.getAllExternalAccessesInOneBlock(extListBundle.getInt(BLOCK_ID)).observe(getViewLifecycleOwner(), extAccess -> {
            extAccAdapter = new ExtAccRecViewAdapter(extAccess, requireActivity(), this);
            recyclerView.setAdapter(extAccAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            extAccAdapter.setListener(new ListClickListener() {
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

        closeExtAccess.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().setFragmentResult(CLOSE_ACTIVITY, extListBundle);
        });

        addExtAccess.setOnClickListener(v -> openExtAccessFrag());
    }

    @Override
    public void onResume() {
        super.onResume();
        extListBundle.putInt(AMBIENT_ID, 0);
        extListBundle.putBoolean(VISIBLE_MEMORIAL, true);
        getParentFragmentManager().setFragmentResult(MEMORIAL, extListBundle);
    }

    private void instantiateExtAccListViews(View v) {
        closeExtAccess = v.findViewById(R.id.cancel_child_items_entries);
        addExtAccess = v.findViewById(R.id.add_child_items_entries);
        invisible = v.findViewById(R.id.continue_child_items_entries);
        invisible.setVisibility(View.GONE);
//        TextView
        extAccessHeader = v.findViewById(R.id.identifier_header);
        extAccessHeader.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
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
                        extAccAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        extAccAdapter.cancelSelection(recyclerView);
                    extAccAdapter.selectedItems.clear();
                    extAccAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = extAccAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void openExtAccessFrag() {
        extListBundle.putBoolean(VISIBLE_MEMORIAL, false);
        ExternalAccessFragment extFrag = ExternalAccessFragment.newInstance();
        extFrag.setArguments(extListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, extFrag).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        ExternalAccess externalAccess = modelEntry.allExtAccSchool.getValue().get(position);
        extListBundle.putInt(AMBIENT_ID, externalAccess.getExternalAccessID());
        openExtAccessFrag();

    }
}