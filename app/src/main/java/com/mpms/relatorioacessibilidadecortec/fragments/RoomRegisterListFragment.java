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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.RoomRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.Objects;

public class RoomRegisterListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    private static int chosenOption;

    MaterialButton closeRoomList, addRoom, finish;
    TextView roomListIdentifier;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private RoomRecViewAdapter roomAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle roomListBundle;

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
            roomListBundle = new Bundle(this.getArguments());
            roomListBundle.putInt(ROOM_TYPE, chosenOption);
        } else
            roomListBundle = new Bundle();
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

        modelEntry.getAllRoomsInBlock(roomListBundle.getInt(BLOCK_ID), roomListBundle.getInt(ROOM_TYPE))
                .observe(getViewLifecycleOwner(), rooms -> {
                    roomAdapter = new RoomRecViewAdapter(rooms, requireActivity(), this);
                    recyclerView.setAdapter(roomAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    roomAdapter.setListener(new ListClickListener() {
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

        addRoom.setOnClickListener(v -> openRoomFragment());

        closeRoomList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        roomListBundle.putInt(AMBIENT_ID, 0);
    }

    private void instantiateRoomListViews(View view) {
//        TextView
        roomListIdentifier = view.findViewById(R.id.identifier_header);
        roomListIdentifier.setText(roomHeader(roomListBundle));
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
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
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
                        roomAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        roomAdapter.cancelSelection(recyclerView);
                    roomAdapter.selectedItems.clear();
                    roomAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = roomAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    public static String roomHeader(Bundle bundle) {
        if (bundle.getBoolean(EXT_AREA_REG) || bundle.getBoolean(SUP_AREA_REG)) {
            bundle.putInt(ROOM_TYPE, 12);
            return "Cadastro de Outros Ambientes";
        }  else {
            switch (bundle.getInt(ROOM_TYPE)) {
                case 2:
                    return "Cadastro de Bibliotecas";
                case 3:
                    return "Cadastro de Coordenações";
                case 4:
                    return "Cadastro de Diretorias";
                case 5:
                    return "Cadastro de Refeitórios";
                case 6:
                    return "Cadastro de Salas de Aula";
                case 7:
                    return "Cadastro Salas de Tecnologia";
                case 8:
                    return "Cadastro Salas de Recursos";
                case 9:
                    return "Cadastro Salas dos Professores";
                case 11:
                    return "Cadastro de Secretarias";
                case 12:
                    return "Cadastro de Outros Ambientes";
                default:
                    return "";
            }
        }
    }


    private void openRoomFragment() {
        RoomsRegisterFragment roomFragment = RoomsRegisterFragment.newInstance();
        roomFragment.setArguments(roomListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, roomFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        RoomEntry roomEntry = modelEntry.allRooms.getValue().get(position);
        roomListBundle.putInt(AMBIENT_ID, roomEntry.getRoomID());
        openRoomFragment();
    }
}
