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
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.ParkElderRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class ParkLotElderListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeElderList, addElderLot, finishParking;
    TextView elderListIdentifier;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private ParkElderRecViewAdapter elderAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

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
                });

        addElderLot.setOnClickListener(v -> openElderFragment());

        closeElderList.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .popBackStack(InspectionActivity.PARKING_LIST, 0));

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
        fragmentTransaction.replace(R.id.show_fragment_selected, elderFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        ParkingLotElderlyEntry elderEntry = modelEntry.allElderLots.getValue().get(position);
        elderBundle.putInt(ParkingLotElderlyFragment.ELDERLY_LOT_ID, elderEntry.getParkingElderlyID());
        openElderFragment();
    }
}
