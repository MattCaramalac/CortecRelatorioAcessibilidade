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
import com.mpms.relatorioacessibilidadecortec.adapter.OtherSpacesRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class OtherSpacesListFragment extends Fragment implements OnEntryClickListener {

    public static final String OTHER_SPACE_ID = "OTHER_SPACE_ID";

    MaterialButton closeOthersList, addOthers;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private OtherSpacesRecViewAdapter othersAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle otherBundle = new Bundle();

    public OtherSpacesListFragment() {
        // Required empty public constructor
    }

    public static OtherSpacesListFragment newInstance() {
        return new OtherSpacesListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            otherBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
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

        instantiateOtherListViews(view);

        modelEntry.getAllOtherSpaces(otherBundle.getInt(SchoolRegisterActivity.SCHOOL_ID)).
                observe(getViewLifecycleOwner(), otherSpaces -> {
                    othersAdapter = new OtherSpacesRecViewAdapter(otherSpaces, requireActivity(), this);
                    recyclerView.setAdapter(othersAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);
                });

        addOthers.setOnClickListener(v -> OpenOtherSpacesFragment());

        closeOthersList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit());
    }

    private void instantiateOtherListViews(View v) {
        closeOthersList = v.findViewById(R.id.close_items_entries_list);
        addOthers = v.findViewById(R.id.add_items_entries);
        recyclerView = v.findViewById(R.id.items_entries_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);

        otherBundle.putInt(OTHER_SPACE_ID, 0);
    }

    private void OpenOtherSpacesFragment() {
        OtherSpacesFragment otherFrag = OtherSpacesFragment.newInstance();
        otherFrag.setArguments(otherBundle);
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, otherFrag).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        OtherSpaces otherSpaces = modelEntry.allOtherSpaces.getValue().get(position);
        otherBundle.putInt(OTHER_SPACE_ID, otherSpaces.getOtherSpacesID());
        OpenOtherSpacesFragment();
    }
}
