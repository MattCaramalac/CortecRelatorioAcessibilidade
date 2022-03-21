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
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.TableRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomRegisterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class TableListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeTableList, addTable, continueButton;

    TextView windowHeader;

    private ViewModelEntry modelEntry;
    private ViewModelFragments modelFragments;
    private RecyclerView recyclerView;
    private TableRecViewAdapter tableAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle tableListBundle = new Bundle();

    public TableListFragment(){

    }

    public static TableListFragment newInstance(){
        return new TableListFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            tableListBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
            tableListBundle.putInt(RoomRegisterListFragment.ROOM_TYPE, this.getArguments().getInt(RoomRegisterListFragment.ROOM_TYPE));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateTableListViews(view);

        if (tableListBundle.getInt(RoomsRegisterFragment.ROOM_ID) == 0) {
            modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom ->
                    tableListBundle.putInt(RoomsRegisterFragment.ROOM_ID, lastRoom.getRoomID()));
        }

        modelEntry.selectTablesFromRoom(tableListBundle.getInt(RoomsRegisterFragment.ROOM_ID)).observe(getViewLifecycleOwner(), tableList -> {
            tableAdapter = new TableRecViewAdapter(tableList, requireActivity(), this);
            recyclerView.setAdapter(tableAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);
        });


        closeTableList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        addTable.setOnClickListener(v -> openTableFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        tableListBundle.putInt(TableFragment.TABLE_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewRoomID(tableListBundle.getInt(RoomsRegisterFragment.ROOM_ID));
    }

    @Override
    public void OnEntryClick(int position) {
        TableEntry tableEntry = modelEntry.allTables.getValue().get(position);
        tableListBundle.putInt(TableFragment.TABLE_ID, tableEntry.getTableID());
        openTableFragment();
    }

    private void openTableFragment() {
        TableFragment tableFragment = TableFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        tableFragment.setArguments(tableListBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, tableFragment).addToBackStack(null).commit();
    }

    private void instantiateTableListViews(View v) {
        //        TextView
        windowHeader = v.findViewById(R.id.identifier_header);
        windowHeader.setText(R.string.header_table_register);
//        MaterialButton
        closeTableList = v.findViewById(R.id.cancel_child_items_entries);
        addTable = v.findViewById(R.id.add_child_items_entries);
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
