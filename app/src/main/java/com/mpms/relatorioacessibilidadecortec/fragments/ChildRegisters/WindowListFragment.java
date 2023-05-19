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
import com.mpms.relatorioacessibilidadecortec.adapter.WindowRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class WindowListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeWindowList, addWindow, continueButton;

    TextView windowHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private WindowRecViewAdapter windowAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle windowListBundle = new Bundle();

    public WindowListFragment() {

    }

    public static WindowListFragment newInstance() {
        return new WindowListFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            windowListBundle = new Bundle(this.getArguments());
        else
            windowListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateWindowListViews(view);

        if (windowListBundle.getInt(CIRC_ID) > 0) {
            modelEntry.getWindowsFromCirc(windowListBundle.getInt(CIRC_ID)).observe(getViewLifecycleOwner(), windowList -> listCreator(windowList, this));
        } else {
            if (windowListBundle.getInt(AMBIENT_ID) == 0)
                modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom -> windowListBundle.putInt(AMBIENT_ID, lastRoom.getRoomID()));

            modelEntry.getWindowsFromRoom(windowListBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), windowList -> listCreator(windowList, this));
        }

        closeWindowList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addWindow.setOnClickListener(v -> openWindowFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        windowListBundle.putInt(WINDOW_ID, 0);
    }

    private void listCreator(List<WindowEntry> list, OnEntryClickListener listener) {
        windowAdapter = new WindowRecViewAdapter(list, requireActivity(), listener);
        recyclerView.setAdapter(windowAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
        windowAdapter.setListener(clickListener());
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
                        windowAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        windowAdapter.cancelSelection(recyclerView);
                    windowAdapter.selectedItems.clear();
                    windowAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = windowAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        WindowEntry windowEntry = modelEntry.allWindows.getValue().get(position);
        windowListBundle.putInt(WINDOW_ID, windowEntry.getWindowID());
        openWindowFragment();
    }

    private void openWindowFragment() {
        WindowFragment windowFragment = WindowFragment.newInstance();
        windowFragment.setArguments(windowListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, windowFragment).addToBackStack(null).commit();
    }

    private void instantiateWindowListViews(View v) {
        //        TextView
        windowHeader = v.findViewById(R.id.identifier_header);
        windowHeader.setVisibility(View.VISIBLE);
        windowHeader.setText(R.string.header_window_register);
//        MaterialButton
        closeWindowList = v.findViewById(R.id.cancel_child_items_entries);
        addWindow = v.findViewById(R.id.add_child_items_entries);
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
