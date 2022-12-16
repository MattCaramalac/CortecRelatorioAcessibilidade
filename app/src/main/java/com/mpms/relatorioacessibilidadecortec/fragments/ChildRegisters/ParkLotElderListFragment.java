package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
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
import com.mpms.relatorioacessibilidadecortec.adapter.ParkElderRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.Objects;

public class ParkLotElderListFragment extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeElderList, addElderLot, finishParking;
    TextView elderListIdentifier;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private ParkElderRecViewAdapter elderAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle elderBundle = new Bundle();

    public ParkLotElderListFragment() {
        // Required empty public constructor
    }

    public static ParkLotElderListFragment newInstance() {
        return new ParkLotElderListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            elderBundle.putInt(PARKING_ID, this.getArguments().getInt(PARKING_ID));
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

        instantiateElderListViews(view);

        modelEntry.getElderVacanciesPark(elderBundle.getInt(ParkingLotListFragment.PARKING_ID))
                .observe(getViewLifecycleOwner(), elderList -> {
                    elderAdapter = new ParkElderRecViewAdapter(elderList, requireActivity(), this);
                    recyclerView.setAdapter(elderAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    elderAdapter.setListener(new ListClickListener() {
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

    }

    @Override
    public void onResume() {
        super.onResume();
        elderBundle.putInt(ELDER_ID, 0);
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
                        elderAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        elderAdapter.cancelSelection(recyclerView);
                    elderAdapter.selectedItems.clear();
                    elderAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = elderAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void instantiateElderListViews(View view) {
//        TextView
        elderListIdentifier = view.findViewById(R.id.identifier_header);
        elderListIdentifier.setText(R.string.parking_elderly_header);
//        MaterialButton & its definitions
        closeElderList = view.findViewById(R.id.cancel_child_items_entries);
        addElderLot = view.findViewById(R.id.add_child_items_entries);
        finishParking = view.findViewById(R.id.continue_child_items_entries);

        finishParking.setText(getString(R.string.label_button_finish));
//        RecyclerView & its definitions
        recyclerView = view.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        addElderLot.setOnClickListener(v -> openElderFragment());
        closeElderList.setOnClickListener(this::cancelFinishClick);
        finishParking.setOnClickListener(this::cancelFinishClick);
    }

    private void cancelFinishClick(View v) {
        if (actionMode != null)
            actionMode.finish();

        if (v == closeElderList)
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        else {
            Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack(PARKING_LIST, 0);
        }
    }

    private void openElderFragment() {
        ParkLotElderlyFragment elderFragment = ParkLotElderlyFragment.newInstance();
        elderFragment.setArguments(elderBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.show_fragment_selected, elderFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        ParkingLotElderlyEntry elderEntry = modelEntry.allElderLots.getValue().get(position);
        elderBundle.putInt(ELDER_ID, elderEntry.getParkElderID());
        openElderFragment();
    }
}
