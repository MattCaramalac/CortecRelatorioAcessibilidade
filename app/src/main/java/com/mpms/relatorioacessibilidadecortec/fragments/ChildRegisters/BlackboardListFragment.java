package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

import java.util.Objects;

public class BlackboardListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeBoardList, addBoard, continueButton;

    TextView boardHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private BlackboardRecViewAdapter boardAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

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
            boardListBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateGateObsViews(view);

        if (boardListBundle.getInt(RoomsRegisterFragment.ROOM_ID) == 0) {
            modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom ->
                    boardListBundle.putInt(RoomsRegisterFragment.ROOM_ID, lastRoom.getRoomID()));
        }

        modelEntry.getAllBlackboardsFromRoom(boardListBundle.getInt(RoomsRegisterFragment.ROOM_ID)).observe(getViewLifecycleOwner(), boardList -> {
            boardAdapter = new BlackboardRecViewAdapter(boardList, requireActivity(), this);
            recyclerView.setAdapter(boardAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);
        });

        closeBoardList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        addBoard.setOnClickListener(v -> openSwitchFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        boardListBundle.putInt(BlackboardFragment.BOARD_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewRoomID(boardListBundle.getInt(RoomsRegisterFragment.ROOM_ID));
    }

    public void instantiateGateObsViews (View v) {
//        TextView
        boardHeader = v.findViewById(R.id.identifier_header);
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
        boardListBundle.putInt(BlackboardFragment.BOARD_ID, boardEntry.getBoardID());
        openSwitchFragment();
    }

    private void openSwitchFragment() {
        BlackboardFragment boardFragment = BlackboardFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        boardFragment.setArguments(boardListBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, boardFragment).addToBackStack(null).commit();
    }
}
