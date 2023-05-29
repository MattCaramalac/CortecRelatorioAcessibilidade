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
import com.mpms.relatorioacessibilidadecortec.adapter.FallProtectRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.FallProtectionEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class FallProtectListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeProtectList, addProtect, continueButton;

    TextView protectHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private FallProtectRecViewAdapter protectAdapter;
    private ActionMode actionMode;

    private int delClick = 0;

    Bundle protectListBundle = new Bundle();

    public FallProtectListFragment() {

    }

    public static FallProtectListFragment newInstance() {
        return new FallProtectListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            protectListBundle = new Bundle(this.getArguments());
        else
            protectListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateProtectViews(view);

        if (protectListBundle.getInt(CIRC_ID) > 0)
            modelEntry.getFallProtectFromCirc(protectListBundle.getInt(CIRC_ID)).observe(getViewLifecycleOwner(), protectList -> listCreator(protectList, this));

        closeProtectList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addProtect.setOnClickListener(v -> openProtectFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        protectListBundle.putInt(PROTECT_ID, 0);
        protectListBundle.putBoolean(RECENT_ENTRY, false);
    }

    private void instantiateProtectViews(View v) {
//        TextView
        protectHeader = v.findViewById(R.id.identifier_header);
        protectHeader.setVisibility(View.VISIBLE);
        protectHeader.setText(getString(R.string.header_fall_protection));
//        MaterialButton
        closeProtectList = v.findViewById(R.id.cancel_child_items_entries);
        addProtect = v.findViewById(R.id.add_child_items_entries);
        continueButton = v.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
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


    private void listCreator(List<FallProtectionEntry> list, OnEntryClickListener listener) {
        protectAdapter = new FallProtectRecViewAdapter(list, requireActivity(), listener);
        recyclerView.setAdapter(protectAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
        protectAdapter.setListener(clickListener(list));
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
                        protectAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        protectAdapter.cancelSelection(recyclerView, entries, protectAdapter);
                    protectAdapter.selectedItems.clear();
                    protectAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = protectAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void openProtectFragment() {
        FallProtectFragment protection = FallProtectFragment.newInstance();
        protection.setArguments(protectListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, protection).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        FallProtectionEntry protect = modelEntry.allFallProtect.getValue().get(position);
        protectListBundle.putInt(PROTECT_ID, protect.getProtectID());
        openProtectFragment();
    }
}
