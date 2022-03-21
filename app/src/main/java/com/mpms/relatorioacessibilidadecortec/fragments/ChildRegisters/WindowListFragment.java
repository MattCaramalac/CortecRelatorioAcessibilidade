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
import com.mpms.relatorioacessibilidadecortec.adapter.WindowRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class WindowListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeWindowList, addWindow, continueButton;

    TextView windowHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private WindowRecViewAdapter windowAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle windowListBundle = new Bundle();

    public WindowListFragment(){

    }

    public static WindowListFragment newInstance(){
        return new WindowListFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            windowListBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
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

        if (windowListBundle.getInt(RoomsRegisterFragment.ROOM_ID) == 0) {
            modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom ->
                    windowListBundle.putInt(RoomsRegisterFragment.ROOM_ID, lastRoom.getRoomID()));
        }

        modelEntry.selectWindowsFromRoom(windowListBundle.getInt(RoomsRegisterFragment.ROOM_ID)).observe(getViewLifecycleOwner(), windowList -> {
            windowAdapter = new WindowRecViewAdapter(windowList, requireActivity(), this);
            recyclerView.setAdapter(windowAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);
        });


        closeWindowList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        addWindow.setOnClickListener(v -> openWindowFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        windowListBundle.putInt(WindowFragment.WINDOW_ID, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewRoomID(windowListBundle.getInt(RoomsRegisterFragment.ROOM_ID));
    }

    @Override
    public void OnEntryClick(int position) {
        WindowEntry windowEntry = modelEntry.allWindows.getValue().get(position);
        windowListBundle.putInt(WindowFragment.WINDOW_ID, windowEntry.getWindowID());
        openWindowFragment();
    }

    private void openWindowFragment() {
        WindowFragment windowFragment = WindowFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        windowFragment.setArguments(windowListBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, windowFragment).addToBackStack(null).commit();
    }

    private void instantiateWindowListViews(View v) {
        //        TextView
        windowHeader = v.findViewById(R.id.identifier_header);
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
