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
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.RestroomRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class RestroomListFragment extends Fragment implements OnEntryClickListener {


    MaterialButton closeRestroomList, addRestroom;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private RestroomRecViewAdapter restroomAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle restListBundle = new Bundle();

    public RestroomListFragment() {
        // Required empty public constructor
    }

    public static RestroomListFragment newInstance() {
        return new RestroomListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            restListBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
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

        instantiateRestListViews(view);

        modelEntry.getAllSchoolRestroomEntries(restListBundle.getInt(SchoolRegisterActivity.SCHOOL_ID)).
                observe(getViewLifecycleOwner(), restEntry -> {
                    restroomAdapter = new RestroomRecViewAdapter(restEntry, requireActivity(), this);
                    recyclerView.setAdapter(restroomAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);
                });

        closeRestroomList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        addRestroom.setOnClickListener(v -> OpenRestFragment());

    }

    private void instantiateRestListViews(View v) {
        closeRestroomList = v.findViewById(R.id.close_items_entries_list);
        addRestroom = v.findViewById(R.id.add_items_entries);
        recyclerView = v.findViewById(R.id.items_entries_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    @Override
    public void OnEntryClick(int position) {
        RestroomEntry restroomEntry = modelEntry.allRestSchool.getValue().get(position);

        restListBundle.putInt(RestroomFragment.RESTROOM_ID, restroomEntry.getRestroomID());

        OpenRestFragment();
    }

    private void OpenRestFragment() {
        RestroomFragment restroomFragment = RestroomFragment.newInstance();
        restroomFragment.setArguments(restListBundle);
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, restroomFragment).addToBackStack(null).commit();
    }
}
