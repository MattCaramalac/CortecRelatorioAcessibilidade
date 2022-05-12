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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.HandrailRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.Objects;

public class RampStairsHandrailList extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeHandrail, addHandrail, invisible;
    TextView handrailHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private HandrailRecViewAdapter handrailAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    Bundle handListBundle;

    public RampStairsHandrailList() {
        // Required empty public constructor
    }

    public static RampStairsHandrailList newInstance() {
        return new RampStairsHandrailList();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            handListBundle = new Bundle(this.getArguments());
        else
            handListBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateHandrailViews(view);

        if (handListBundle.getInt(FLIGHT_ID) == 0)
            modelEntry.getLastRampStairsFlightEntry().observe(getViewLifecycleOwner(), newFlight ->
                    handListBundle.putInt(FLIGHT_ID, newFlight.getFlightID()));


        modelEntry.getAllRampStairsHandrails(handListBundle.getInt(FLIGHT_ID)).observe(getViewLifecycleOwner(), handList -> {
            handrailAdapter = new HandrailRecViewAdapter(handList, requireActivity(), this);
            listCreator(handrailAdapter);
        });

        addHandrail.setOnClickListener(v -> openHandrailFragment());

        closeHandrail.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });


    }

    private void instantiateHandrailViews(View v) {
        //        MaterialButton
        closeHandrail = v.findViewById(R.id.cancel_child_items_entries);
        addHandrail = v.findViewById(R.id.add_child_items_entries);
        invisible = v.findViewById(R.id.continue_child_items_entries);
        invisible.setVisibility(View.GONE);
//        TextView
        handrailHeader = v.findViewById(R.id.identifier_header);
        handrailHeader.setText(R.string.header_handrail_register);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        handListBundle.putInt(HANDRAIL_ID, 0);
    }

    private void listCreator(HandrailRecViewAdapter adapter) {
        adapter.setListener(clickListener());

        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private ListClickListener clickListener() {
        return new ListClickListener() {
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
        };
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
                        handrailAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        handrailAdapter.cancelSelection(recyclerView);
                    handrailAdapter.selectedItems.clear();
                    handrailAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = handrailAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void openHandrailFragment() {
        RampStairsHandrailFragment handrailFrag = RampStairsHandrailFragment.newInstance();
        handrailFrag.setArguments(handListBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, handrailFrag).addToBackStack(null).commit();
    }

    @Override
    public void OnEntryClick(int position) {
        RampStairsHandrailEntry handrailEntry = modelEntry.allHandrails.getValue().get(position);
        handListBundle.putInt(HANDRAIL_ID, handrailEntry.getHandrailID());
        openHandrailFragment();
    }
}
