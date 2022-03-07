package com.mpms.relatorioacessibilidadecortec.fragments;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.RoomRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class RoomRegisterListFragment extends Fragment implements OnEntryClickListener {

    public static final String ROOM_TYPE = "ROOM_TYPE";
    private static int chosenOption;

    MaterialButton closeRoomList, addRoom, finish;
    TextView roomListIdentifier;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private RoomRecViewAdapter roomAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle roomBundle = new Bundle();

    public RoomRegisterListFragment() {
        // Required empty public constructor
    }

    public static RoomRegisterListFragment newInstance(int dropdownChoice) {
        RoomRegisterListFragment roomList = new RoomRegisterListFragment();
        roomList.setChosenOption(dropdownChoice);
        return roomList;
    }

    public void setChosenOption(int choice) {
        RoomRegisterListFragment.chosenOption = choice;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            roomBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
            roomBundle.putInt(ROOM_TYPE, chosenOption);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateRoomListViews(view);

        modelEntry.getAllRoomsInBlock(roomBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER), roomBundle.getInt(ROOM_TYPE))
                .observe(getViewLifecycleOwner(), rooms -> {
                    roomAdapter = new RoomRecViewAdapter(rooms, requireActivity(), this);
                    recyclerView.setAdapter(roomAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);
                });

        addRoom.setOnClickListener(v -> {
            roomBundle.putInt(RoomsRegisterFragment.ROOM_ID_VALUE, 0);
            openRoomFragment();
        });

        closeRoomList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit());
    }

    private void instantiateRoomListViews(View view) {
//        TextView
        roomListIdentifier = view.findViewById(R.id.identifier_header);
        roomListIdentifier.setText(roomHeader(roomBundle));
//        MaterialButtons
        closeRoomList = view.findViewById(R.id.cancel_child_items_entries);
        addRoom = view.findViewById(R.id.add_child_items_entries);
        finish = view.findViewById(R.id.continue_child_items_entries);
        finish.setVisibility(View.GONE);
//        RecyclerView && Configs
        recyclerView = view.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    private String roomHeader(Bundle bundle) {
        if (bundle.getInt(ROOM_TYPE) == 3)
            return "Cadastro de Bibliotecas";
        else if (bundle.getInt(ROOM_TYPE) == 5)
            return "Cadastro de Coordenações";
        else if (bundle.getInt(ROOM_TYPE) == 6)
            return "Cadastro de Diretorias";
        else if (bundle.getInt(ROOM_TYPE) == 10)
            return "Cadastro de Refeitórios";
        else if (bundle.getInt(ROOM_TYPE) == 11)
            return "Cadastro de Salas de Aula";
        else if (bundle.getInt(ROOM_TYPE) == 12)
            return "Cadastro Salas de Tecnologia";
        else if (bundle.getInt(ROOM_TYPE) == 13)
            return "Cadastro Salas de Recursos";
        else if (bundle.getInt(ROOM_TYPE) == 14)
            return "Cadastro Salas dos Professores";
        else
            return "Cadastro de Secretarias";
    }

    private void openRoomFragment() {
        RoomsRegisterFragment roomFragment = RoomsRegisterFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        roomFragment.setArguments(roomBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, roomFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        RoomEntry roomEntry = modelEntry.allRooms.getValue().get(position);
        roomBundle.putInt(RoomsRegisterFragment.ROOM_ID_VALUE, roomEntry.getRoomID());
        openRoomFragment();
    }
}
