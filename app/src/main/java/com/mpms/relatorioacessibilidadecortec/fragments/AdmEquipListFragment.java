package com.mpms.relatorioacessibilidadecortec.fragments;

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
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.AdmEquipRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.AdmEquipEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class AdmEquipListFragment extends Fragment implements OnEntryClickListener {

    MaterialButton closeEquipList, addEquip, continueEquip;

    TextView registerHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private AdmEquipRecViewAdapter equipAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle equipBundle = new Bundle();

    public AdmEquipListFragment() {
        // Required empty public constructor
    }

    public static AdmEquipListFragment newInstance() {
        return new AdmEquipListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            equipBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateAdmEquipListViews(view);

        modelEntry.getAllAdmEquipsPerBlock(equipBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER))
                .observe(getViewLifecycleOwner(), admEquipList -> {
                    equipAdapter = new AdmEquipRecViewAdapter(admEquipList, requireActivity(), this);
                    recyclerView.setAdapter(equipAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    equipAdapter.setListener(new ListClickListener() {
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

        addEquip.setOnClickListener(v -> openAdmEquipFragment());

        closeEquipList.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    private void enableActionMode() {
        if (actionMode == null){
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
                        equipAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        equipAdapter.cancelSelection(recyclerView);
                    equipAdapter.selectedItems.clear();
                    equipAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = equipAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size+"");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        AdmEquipEntry admEquip = modelEntry.allAdmEquip.getValue().get(position);
        equipBundle.putInt(AdmEquipFragment.EQUIP_ID, admEquip.getAdmEquipID());
        openAdmEquipFragment();
    }

    private void openAdmEquipFragment() {
        AdmEquipFragment newEquip = AdmEquipFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        newEquip.setArguments(equipBundle);
        if (actionMode != null)
            actionMode.finish();
        fragmentTransaction.replace(R.id.show_fragment_selected, newEquip).addToBackStack(null).commit();
    }

    private void instantiateAdmEquipListViews(View v) {
//        MaterialButton
        closeEquipList = v.findViewById(R.id.cancel_child_items_entries);
        addEquip = v.findViewById(R.id.add_child_items_entries);
        continueEquip = v.findViewById(R.id.continue_child_items_entries);
        continueEquip.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        TextView
        registerHeader = v.findViewById(R.id.identifier_header);
        registerHeader.setText(R.string.header_adm_equip_register);
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);

//        Verificar pq o up/down n funfa
//        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHandler(0, ItemTouchHelper.ANIMATION_TYPE_SWIPE_CANCEL));
//        touchHelper.attachToRecyclerView(recyclerView);

        equipBundle.putInt(AdmEquipFragment.EQUIP_ID, 0);
    }

//    private class TouchHandler extends ItemTouchHelper.SimpleCallback {
//
//        public TouchHandler(int dragDirs, int swipeDirs) {
//            super(dragDirs, swipeDirs);
//        }
//
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
//            int from = viewHolder.getAdapterPosition();
//            int to = viewHolder1.getAdapterPosition();
//
//            Collections.swap(equipAdapter.getEntryList(), from, to);
//            equipAdapter.notifyItemMoved(from, to);
//
//            return true;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int animType) {
//            AdmEquipEntry admEquip = modelEntry.allAdmEquip.getValue().get(viewHolder.getAdapterPosition());
//            equipAdapter.getEntryList().remove(viewHolder.getAdapterPosition());
//            ViewModelEntry.deleteOneAdmEquip(admEquip.getAdmEquipID());
//            equipAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//        }
//    }
}
