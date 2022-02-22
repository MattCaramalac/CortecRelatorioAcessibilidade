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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.GateObstacleViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class GateObsListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeGateList, addGateObs, continueButton;

    TextView gateObsHeader;

    ViewModelFragments modelFragments;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private GateObstacleViewAdapter gateObsAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle gateObsBundle = new Bundle();

    public GateObsListFragment(){

    }

    public static GateObsListFragment newInstance(){
        return new GateObsListFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            gateObsBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID));
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateGateObsViews(view);

        if (gateObsBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) == 0) {
            modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), lastExtAccess ->
                    gateObsBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, lastExtAccess.getExternalAccessID()));
        }

        modelEntry.getAllGateObsEntries(gateObsBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), gateObsList -> {
            gateObsAdapter = new GateObstacleViewAdapter(gateObsList, requireActivity(), this);
            recyclerView.setAdapter(gateObsAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);
        });

        closeGateList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        addGateObs.setOnClickListener(v -> openGateObsFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        gateObsBundle.putInt(GateObsFragment.GATE_OBS_ID, 0);
    }

    @Override
    public void onDestroyView() {
        modelFragments.setExtAccessLoadInfo(gateObsBundle);
        super.onDestroyView();
    }

    public void instantiateGateObsViews (View v) {
//        TextView
        gateObsHeader = v.findViewById(R.id.identifier_header);
        gateObsHeader.setText(R.string.header_text_obstacle_register);
//        MaterialButton
        closeGateList = v.findViewById(R.id.cancel_child_items_entries);
        addGateObs = v.findViewById(R.id.add_child_items_entries);
        continueButton = v.findViewById(R.id.continue_child_items_entries);
        continueButton.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);

    }

    @Override
    public void OnEntryClick(int position) {
        GateObsEntry gateObsEntry = modelEntry.allGateObs.getValue().get(position);
        gateObsBundle.putInt(GateObsFragment.GATE_OBS_ID, gateObsEntry.getGateObsID());
        openGateObsFragment();
    }

    private void openGateObsFragment() {
        GateObsFragment gateObsFragment = GateObsFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        gateObsFragment.setArguments(gateObsBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, gateObsFragment).addToBackStack(null).commit();
    }
}
