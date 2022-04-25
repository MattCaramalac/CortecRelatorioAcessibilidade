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
import com.mpms.relatorioacessibilidadecortec.adapter.ParkPcdRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class ParkLotPcdListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closePcdList, addPcdLot, nextScreen;
    TextView pcdListIdentifier;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private ParkPcdRecViewAdapter pcdAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle pcdBundle = new Bundle();

    public ParkLotPcdListFragment() {
        // Required empty public constructor
    }

    public static ParkLotPcdListFragment newInstance() {
        return new ParkLotPcdListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            pcdBundle.putInt(ParkingLotListFragment.PARKING_ID, this.getArguments().getInt(ParkingLotListFragment.PARKING_ID));
            pcdBundle.putBoolean(ParkingLotFragment.HAS_ELDERLY, this.getArguments().getBoolean(ParkingLotFragment.HAS_ELDERLY));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiatePcdListViews(view);

        modelEntry.getAllPcdParkingLot(pcdBundle.getInt(ParkingLotListFragment.PARKING_ID))
                .observe(getViewLifecycleOwner(), pcdList -> {
                    pcdAdapter = new ParkPcdRecViewAdapter(pcdList, requireActivity(), this);
                    recyclerView.setAdapter(pcdAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    pcdAdapter.setListener(new ListClickListener() {
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

        addPcdLot.setOnClickListener(v -> {
            pcdBundle.putInt(ParkingLotPcdFragment.PCD_ID, 0);
            openPcdFragment();
        });

        nextScreen.setOnClickListener(v -> nextScreenController());

        closePcdList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            if (!pcdBundle.getBoolean(ParkingLotFragment.HAS_ELDERLY)) {
                requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.PARKING_LIST,0);
            } else
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });
    }

    private void instantiatePcdListViews(View view) {
//        TextView & its definitions
        pcdListIdentifier = view.findViewById(R.id.identifier_header);
        pcdListIdentifier.setText("Cadastro Vagas PCD e PMR");
//        MaterialButton & its definitions
        closePcdList = view.findViewById(R.id.cancel_child_items_entries);
        addPcdLot = view.findViewById(R.id.add_child_items_entries);
        nextScreen = view.findViewById(R.id.continue_child_items_entries);

        if (!pcdBundle.getBoolean(ParkingLotFragment.HAS_ELDERLY)) {
            nextScreen.setVisibility(View.GONE);
            closePcdList.setText("FINALIZAR");
        }
//        RecyclerView & its definitions
        recyclerView = view.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
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
                        pcdAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        pcdAdapter.cancelSelection(recyclerView);
                    pcdAdapter.selectedItems.clear();
                    pcdAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = pcdAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void openPcdFragment() {
        ParkingLotPcdFragment pcdFragment = ParkingLotPcdFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        pcdFragment.setArguments(pcdBundle);
        if (actionMode != null)
            actionMode.finish();
        fragmentTransaction.replace(R.id.show_fragment_selected, pcdFragment).addToBackStack(null).commit();
    }

    private void nextScreenController(){
        if (pcdBundle.getBoolean(ParkingLotFragment.HAS_ELDERLY)) {
            ParkLotElderListFragment elderListFragment = ParkLotElderListFragment.newInstance();
            fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            elderListFragment.setArguments(pcdBundle);
            if (actionMode != null)
                actionMode.finish();
            fragmentTransaction.replace(R.id.show_fragment_selected, elderListFragment)
                    .addToBackStack(ParkingLotFragment.ELDER_LIST).commit();
        } else
            requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.PARKING_LIST,0);

    }

    @Override
    public void OnEntryClick(int position) {
        ParkingLotPCDEntry pcdEntry = modelEntry.allPcdLots.getValue().get(position);
        pcdBundle.putInt(ParkingLotPcdFragment.PCD_ID, pcdEntry.getParkingPcdID());
        openPcdFragment();
    }
}
