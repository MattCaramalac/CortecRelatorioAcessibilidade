package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

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
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.PayPhoneViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

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
    private ActionMode actionMode;

    int delClick = 0;

    Bundle payPhoneBundle = new Bundle();

    public PayPhoneListFragment() {
        // Required empty public constructor
    }

    public static PayPhoneListFragment newInstance() {
        return new PayPhoneListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            payPhoneBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID));
            payPhoneBundle.putInt(SidewalkFragment.SIDEWALK_ID, this.getArguments().getInt(SidewalkFragment.SIDEWALK_ID));
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiatePayPhoneViews(view);

        if (payPhoneBundle.getInt(SidewalkFragment.SIDEWALK_ID) == 0) {
            modelEntry.getAllPayPhonesExtAccess(payPhoneBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), payPhoneList -> {
                payPhoneAdapter = new PayPhoneViewAdapter(payPhoneList, requireActivity(), this);
                recyclerView.setAdapter(payPhoneAdapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                recyclerView.addItemDecoration(dividerItemDecoration);

                payPhoneAdapter.setListener(new ListClickListener() {
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
        } else if (payPhoneBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) == 0) {
            modelEntry.getAllPayPhonesSidewalk(payPhoneBundle.getInt(SidewalkFragment.SIDEWALK_ID)).observe(getViewLifecycleOwner(), payPhoneSideList -> {
                payPhoneAdapter = new PayPhoneViewAdapter(payPhoneSideList, requireActivity(), this);
                recyclerView.setAdapter(payPhoneAdapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                recyclerView.addItemDecoration(dividerItemDecoration);

                payPhoneAdapter.setListener(new ListClickListener() {
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
        }

        closePayPhoneList.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        addPayPhone.setOnClickListener(v -> openPayphoneFragment());

    }

    @Override
    public void onResume() {
        super.onResume();
        payPhoneBundle.putInt(PayPhoneFragment.PHONE_ID, 0);
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
                        payPhoneAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        payPhoneAdapter.cancelSelection(recyclerView);
                    payPhoneAdapter.selectedItems.clear();
                    payPhoneAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = payPhoneAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        PayPhoneEntry phoneEntry;
        if (payPhoneBundle.getInt(SidewalkFragment.SIDEWALK_ID) > 0)
            phoneEntry = modelEntry.allPayPhonesSidewalk.getValue().get(position);
        else if (payPhoneBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) > 0)
            phoneEntry = modelEntry.allPayPhonesSidewalk.getValue().get(position);
        else
            return;
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
        if (actionMode != null)
            actionMode.finish();
        fragmentTransaction.replace(R.id.show_fragment_selected, phoneFragment).addToBackStack(null).commit();
    }
}
