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
import com.mpms.relatorioacessibilidadecortec.adapter.BlackboardRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class BlackboardListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeBoardList, addBoard, continueButton;

    TextView boardHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private BlackboardRecViewAdapter boardAdapter;
    private ActionMode actionMode;

    private int delClick = 0;

    Bundle boardListBundle = new Bundle();

    public BlackboardListFragment(){

    }

    public static BlackboardListFragment newInstance(){
        return new BlackboardListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            boardListBundle = new Bundle(this.getArguments());
        else
            boardListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateBoardViews(view);

        if (boardListBundle.getInt(CIRC_ID) > 0) {
            modelEntry.getAllBlackboardsFromCirc(boardListBundle.getInt(CIRC_ID)).observe(getViewLifecycleOwner(), list -> listCreator(list, this));
        } else {
            if (boardListBundle.getInt(AMBIENT_ID) == 0)
                modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom -> boardListBundle.putInt(AMBIENT_ID, lastRoom.getRoomID()));

            modelEntry.getAllBlackboardsFromRoom(boardListBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(),list -> listCreator(list, this));
        }

        closeBoardList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addBoard.setOnClickListener(v -> openBoardFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        boardListBundle.putInt(BOARD_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (boardListBundle.getInt(CIRC_ID) == 0)
            RoomsRegisterFragment.roomModelFragments.setNewRoomID(boardListBundle.getInt(AMBIENT_ID));
    }

    private void listCreator(List<BlackboardEntry> list, OnEntryClickListener listener) {
        boardAdapter = new BlackboardRecViewAdapter(list, requireActivity(), listener);
        recyclerView.setAdapter(boardAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
        boardAdapter.setListener(clickListener());
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
                        boardAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        boardAdapter.cancelSelection(recyclerView);
                    boardAdapter.selectedItems.clear();
                    boardAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = boardAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void instantiateBoardViews(View v) {
//        TextView
        boardHeader = v.findViewById(R.id.identifier_header);
        boardHeader.setVisibility(View.VISIBLE);
        boardHeader.setText(R.string.label_blackboard_register);
//        MaterialButton
        closeBoardList = v.findViewById(R.id.cancel_child_items_entries);
        addBoard = v.findViewById(R.id.add_child_items_entries);
        continueButton = v.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

    }

    @Override
    public void OnEntryClick(int position) {
        BlackboardEntry boardEntry = modelEntry.allBlackboards.getValue().get(position);
        boardListBundle.putInt(BOARD_ID, boardEntry.getBoardID());
        openBoardFragment();
    }

    private void openBoardFragment() {
        BlackboardFragment boardFragment = BlackboardFragment.newInstance();
        boardFragment.setArguments(boardListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, boardFragment).addToBackStack(null).commit();
    }
}
