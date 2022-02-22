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
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.PayPhoneViewAdapter;
import com.mpms.relatorioacessibilidadecortec.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class PayPhoneListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closePayPhoneList, addPayPhone, continueButton;

    TextView payPhoneHeader;

    ViewModelFragments modelFragments;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private PayPhoneViewAdapter payPhoneAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle payPhoneBundle = new Bundle();

    public PayPhoneListFragment() {
        // Required empty public constructor
    }

    public static PayPhoneListFragment newInstance() {
        return new PayPhoneListFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            payPhoneBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiatePayPhoneViews(view);

        if (payPhoneBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) == 0) {
            modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), lastExtAccess ->
                    payPhoneBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, lastExtAccess.getExternalAccessID()));
        }

        modelEntry.getAllPayPhones(payPhoneBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), payPhoneList -> {
            payPhoneAdapter = new PayPhoneViewAdapter(payPhoneList, requireActivity(), this);
            recyclerView.setAdapter(payPhoneAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);
        });

        closePayPhoneList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        addPayPhone.setOnClickListener(v -> openPayphoneFragment());

    }

    @Override
    public void onResume() {
        super.onResume();
        payPhoneBundle.putInt(PayPhoneFragment.PHONE_ID, 0);
    }

    @Override
    public void onDestroyView() {
        modelFragments.setExtAccessLoadInfo(payPhoneBundle);
        super.onDestroyView();

    }

    @Override
    public void OnEntryClick(int position) {
        PayPhoneEntry phoneEntry = modelEntry.allPayPhones.getValue().get(position);
        payPhoneBundle.putInt(PayPhoneFragment.PHONE_ID, phoneEntry.getPayPhoneID());
        openPayphoneFragment();
    }

    public void instantiatePayPhoneViews (View v) {
//        TextView
        payPhoneHeader = v.findViewById(R.id.identifier_header);
        payPhoneHeader.setText(R.string.header_text_payphone_register);
//        MaterialButton
        closePayPhoneList = v.findViewById(R.id.cancel_child_items_entries);
        addPayPhone = v.findViewById(R.id.add_child_items_entries);
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

    private void openPayphoneFragment() {
        PayPhoneFragment phoneFragment = PayPhoneFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        phoneFragment.setArguments(payPhoneBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, phoneFragment).addToBackStack(null).commit();
    }
}
