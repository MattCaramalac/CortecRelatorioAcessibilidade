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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.ParkElderRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class ParkLotElderListFragment extends Fragment implements OnEntryClickListener {

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
            elderBundle.putInt(ParkingLotListFragment.PARKING_ID, this.getArguments().getInt(ParkingLotListFragment.PARKING_ID));
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

        modelEntry.getAllElderlyParkingLot(elderBundle.getInt(ParkingLotListFragment.PARKING_ID))
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

        addElderLot.setOnClickListener(v -> openElderFragment());

        closeElderList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.PARKING_LIST, 0);
        });






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
        elderListIdentifier.setText("Cadastro Vagas Idosos");
//        MaterialButton & its definitions
        closeElderList = view.findViewById(R.id.cancel_child_items_entries);
        addElderLot = view.findViewById(R.id.add_child_items_entries);
        finishParking = view.findViewById(R.id.continue_child_items_entries);

        finishParking.setVisibility(View.GONE);
        closeElderList.setText("FINALIZAR");
//        RecyclerView & its definitions
        recyclerView = view.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    private void openElderFragment() {
        ParkingLotElderlyFragment elderFragment = ParkingLotElderlyFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        elderFragment.setArguments(elderBundle);
        if (actionMode != null)
            actionMode.finish();
        fragmentTransaction.replace(R.id.show_fragment_selected, elderFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        ParkingLotElderlyEntry elderEntry = modelEntry.allElderLots.getValue().get(position);
        elderBundle.putInt(ParkingLotElderlyFragment.ELDERLY_LOT_ID, elderEntry.getParkingElderlyID());
        openElderFragment();
    }
}
