package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.RampStairsRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class RampStairsListFragment extends Fragment implements OnEntryClickListener {

    public static final String RAMP_OR_STAIRS = "RAMP_OR_STAIRS";

    MaterialButton closeRampStairsList, addRampStairs;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private RampStairsRecViewAdapter rampStairsAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle rampStairsListBundle = new Bundle();

    public RampStairsListFragment(){
        // Required empty public constructor
    }

    public static RampStairsListFragment newInstance(int dropdownChoice) {
        RampStairsListFragment rampStairsList = new RampStairsListFragment();
        rampStairsList.chosenOption(dropdownChoice);
        return rampStairsList;
    }

    private void chosenOption(int choice) {
        rampStairsListBundle.putInt(RAMP_OR_STAIRS, choice);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            rampStairsListBundle.putInt(SchoolRegisterActivity.SCHOOL_ID,
                    this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items_entries_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRampStairsListViews(view);

//        TODO - Trocar o Dao/Repository/ViewModel para apenas uma opção, seguir exemplo das salas
        if (rampStairsListBundle.getInt(RAMP_OR_STAIRS) == 7) {
            modelEntry.getAllStairsFromSchool(rampStairsListBundle.getInt(SchoolRegisterActivity.SCHOOL_ID),
                    rampStairsListBundle.getInt(RAMP_OR_STAIRS)).observe(getViewLifecycleOwner(), stairsList -> {
                rampStairsAdapter = new RampStairsRecViewAdapter(stairsList, requireActivity(), this);
                recyclerView.setAdapter(rampStairsAdapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                recyclerView.addItemDecoration(dividerItemDecoration);
            });
        } else if (rampStairsListBundle.getInt(RAMP_OR_STAIRS) == 9) {
            modelEntry.getAllRampsFromSchool(rampStairsListBundle.getInt(SchoolRegisterActivity.SCHOOL_ID),
                    rampStairsListBundle.getInt(RAMP_OR_STAIRS)).observe(getViewLifecycleOwner(), rampList -> {
                rampStairsAdapter = new RampStairsRecViewAdapter(rampList, requireActivity(), this);
                recyclerView.setAdapter(rampStairsAdapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                recyclerView.addItemDecoration(dividerItemDecoration);
            });
        } else {
            Toast.makeText(getContext(), "Algo deu errado. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }

        addRampStairs.setOnClickListener(v -> openRampStairsFragment());

        closeRampStairsList.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    private void instantiateRampStairsListViews(View v) {
        closeRampStairsList = v.findViewById(R.id.close_items_entries_list);
        addRampStairs = v.findViewById(R.id.add_items_entries);
        recyclerView = v.findViewById(R.id.items_entries_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    private void openRampStairsFragment() {
        RampStairsFragment rampStairsFragment = RampStairsFragment.newInstance();
        rampStairsListBundle.putInt(InspectionActivity.ALLOW_UPDATE, 0);
        rampStairsFragment.setArguments(rampStairsListBundle);
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, rampStairsFragment).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        RampStairsEntry rampStairsEntry = null;
        if (rampStairsListBundle.getInt(RAMP_OR_STAIRS) == 7)
            rampStairsEntry = modelEntry.allStairsSchool.getValue().get(position);
        else if (rampStairsListBundle.getInt(RAMP_OR_STAIRS) == 9)
            rampStairsEntry = modelEntry.allRampsSchool.getValue().get(position);
        rampStairsListBundle.putInt(RampStairsFragment.RAMP_STAIRS_ID, rampStairsEntry.getRampStairsID());
        openRampStairsFragment();
    }
}
