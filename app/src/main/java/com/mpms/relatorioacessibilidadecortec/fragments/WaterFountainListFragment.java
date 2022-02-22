package com.mpms.relatorioacessibilidadecortec.fragments;

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
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.WaterRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class WaterFountainListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeFountainList, addFountain;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private WaterRecViewAdapter fountainAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle fountainBundle = new Bundle();

    public WaterFountainListFragment() {
        // Required empty public constructor
    }

    public static WaterFountainListFragment newInstance() {
        return new WaterFountainListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            fountainBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items_entries_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateWaterListViews(view);

        modelEntry.getAllFountainsInSchool(fountainBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER)).
                observe(getViewLifecycleOwner(), fountainEntry -> {
                    fountainAdapter = new WaterRecViewAdapter(fountainEntry, requireActivity(), this);
                    recyclerView.setAdapter(fountainAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);
                });

        addFountain.setOnClickListener(v -> OpenFountainFragment());

        closeFountainList.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    private void instantiateWaterListViews(View v) {
        closeFountainList = v.findViewById(R.id.close_items_entries_list);
        addFountain = v.findViewById(R.id.add_items_entries);
        recyclerView = v.findViewById(R.id.items_entries_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);

        fountainBundle.putInt(WaterFountainFragment.FOUNTAIN_ID, 0);
    }

    @Override
    public void OnEntryClick(int position) {
        WaterFountainEntry fountainEntry = modelEntry.allFountainsInSchool.getValue().get(position);

        fountainBundle.putInt(WaterFountainFragment.FOUNTAIN_ID, fountainEntry.getWaterFountainID());

        OpenFountainFragment();
    }

    private void OpenFountainFragment() {
        WaterFountainFragment fountainFragment = WaterFountainFragment.newInstance();
        fountainFragment.setArguments(fountainBundle);
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, fountainFragment).addToBackStack(null).commit();
    }
}
