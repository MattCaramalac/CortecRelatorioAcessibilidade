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
import com.mpms.relatorioacessibilidadecortec.adapter.ExtAccRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class ExternalAccessListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeExtAccess, addExtAccess;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private ExtAccRecViewAdapter extAccAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ViewModelFragments modelFragments;

    Bundle extListBundle = new Bundle();


    public ExternalAccessListFragment() {
        // Required empty public constructor
    }

    public static ExternalAccessListFragment newInstance() {
        return new ExternalAccessListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            extListBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
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

        instantiateExtAccListViews(view);

        modelEntry.getAllExternalAccessesInOneBlock(extListBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER))
                .observe(getViewLifecycleOwner(), extAccess -> {
                    extAccAdapter = new ExtAccRecViewAdapter(extAccess, requireActivity(), this);
                    recyclerView.setAdapter(extAccAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);
                });

        closeExtAccess.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        addExtAccess.setOnClickListener(v -> {
            ExternalAccessFragment externalAccessFragment = ExternalAccessFragment.newInstance();
            externalAccessFragment.setArguments(extListBundle);
            fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.show_fragment_selected, externalAccessFragment).addToBackStack(null).commit();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        extListBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID,0);
        modelFragments.setExtAccessLoadInfo(null);
    }

    private void instantiateExtAccListViews(View v) {
        closeExtAccess = v.findViewById(R.id.close_items_entries_list);
        addExtAccess = v.findViewById(R.id.add_items_entries);
        recyclerView = v.findViewById(R.id.items_entries_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);

        extListBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID,0);
    }

    @Override
    public void OnEntryClick(int position) {
        ExternalAccess externalAccess = modelEntry.allExtAccSchool.getValue().get(position);

        extListBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, externalAccess.getExternalAccessID());

        ExternalAccessFragment externalAccessFragment = ExternalAccessFragment.newInstance();
        externalAccessFragment.setArguments(extListBundle);
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, externalAccessFragment).addToBackStack(null).commit();
    }
}