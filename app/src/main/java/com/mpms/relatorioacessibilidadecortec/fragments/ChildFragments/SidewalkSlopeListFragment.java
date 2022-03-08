package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.SidewalkSlopeRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class SidewalkSlopeListFragment extends Fragment implements OnEntryClickListener {



    MaterialButton closeSideSlopeList, addSideSlope;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private SidewalkSlopeRecViewAdapter sideSlopeAdapter;

    Bundle sideSlopeBundle = new Bundle();

    public SidewalkSlopeListFragment() {
        // Required empty public constructor
    }

    public static SidewalkSlopeListFragment newInstance() {
        return new SidewalkSlopeListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            sideSlopeBundle.putInt(SidewalkFragment.SIDEWALK_ID, this.getArguments().getInt(SidewalkFragment.SIDEWALK_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items_entries_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSideSlopeViews(view);

        modelEntry.getAllSidewalkSlopes(sideSlopeBundle.getInt(SidewalkFragment.SIDEWALK_ID)).
                observe(getViewLifecycleOwner(), sideSlopeList -> {
                    sideSlopeAdapter = new SidewalkSlopeRecViewAdapter(sideSlopeList, requireActivity(), this);
                    recyclerView.setAdapter(sideSlopeAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);
                });

        addSideSlope.setOnClickListener(v -> openSideSlopeFragment());

        closeSideSlopeList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateSideSlopeViews (View v) {
        closeSideSlopeList = v.findViewById(R.id.close_items_entries_list);
        addSideSlope = v.findViewById(R.id.add_items_entries);
        recyclerView = v.findViewById(R.id.items_entries_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    private void openSideSlopeFragment() {
        SidewalkSlopeFragment slopeFragment = SidewalkSlopeFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        slopeFragment.setArguments(sideSlopeBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, slopeFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        SidewalkSlopeEntry slopeEntry = modelEntry.allSidewalkSlopes.getValue().get(position);
        sideSlopeBundle.putInt(SidewalkSlopeFragment.SIDEWALK_SLOPE_ID, slopeEntry.getSidewalkSlopeID());
        openSideSlopeFragment();
    }
}
