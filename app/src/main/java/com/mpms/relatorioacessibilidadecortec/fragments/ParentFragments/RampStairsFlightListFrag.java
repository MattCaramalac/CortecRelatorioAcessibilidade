package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.FlightListRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class RampStairsFlightListFrag extends Fragment implements OnEntryClickListener, TagInterface {

    MaterialButton closeFlights, addFlights, finishFlights;
    TextView flightsHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private FlightListRecViewAdapter flightAdapter;
    private ActionMode actionMode;

    int delClick = 0;
    int entryCounter = 0;

    Bundle flightBundle;

    public RampStairsFlightListFrag() {
        // Required empty public constructor
    }

    public static RampStairsFlightListFrag newInstance() {
        return new RampStairsFlightListFrag();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            flightBundle = new Bundle(this.getArguments());
        else
            flightBundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRampStairsListViews(view);

        modelEntry.getRampStairsFlights(flightBundle.getInt(RAMP_STAIRS_ID)).
                observe(getViewLifecycleOwner(), flightList -> {
                    entryCounter = flightList.size();
                    flightAdapter = new FlightListRecViewAdapter(flightList, requireActivity(), this);
                    recyclerView.setAdapter(flightAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);

                    flightAdapter.setListener(listener(flightList));
                });

        addFlights.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            flightBundle.putInt(NEXT_FLIGHT, entryCounter+1);
            openFlightFragment();
        });

        closeFlights.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        });

        finishFlights.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();

            Toast.makeText(getContext(), "Cadastro concluído com Sucesso!", Toast.LENGTH_SHORT).show();

            if (flightBundle.getBoolean(FROM_ROOMS))
                requireActivity().getSupportFragmentManager().popBackStack(ROOM_OBJ_LIST, 0);
            else
                requireActivity().getSupportFragmentManager().popBackStack(OTHER_OBJ_LIST, 0);
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        flightBundle.putInt(FLIGHT_ID, 0);
    }

    private void instantiateRampStairsListViews(View v) {
//        MaterialButton
        closeFlights = v.findViewById(R.id.cancel_child_items_entries);
        addFlights = v.findViewById(R.id.add_child_items_entries);
        finishFlights = v.findViewById(R.id.continue_child_items_entries);
        finishFlights.setText(R.string.button_conclusion);
//        TextView
        flightsHeader = v.findViewById(R.id.identifier_header);
        flightsHeader.setVisibility(View.VISIBLE);
        flightsHeader.setText(R.string.header_flight_register);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }

    private <T> ListClickListener listener(List<T> entries) {
        return new ListClickListener() {
            @Override
            public void onItemClick(int position) {
                if (actionMode == null)
                    OnEntryClick(position);
                else
                    enableActionMode(entries);
            }

            @Override
            public void onItemLongClick(int position) {
                enableActionMode(entries);
            }
        };
    }

    private <T> void enableActionMode(List<T> entries) {
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
                        flightAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        flightAdapter.cancelSelection(recyclerView, entries, flightAdapter);
                    flightAdapter.selectedItems.clear();
                    flightAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = flightAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private void openFlightFragment() {
        RampStairsFlightFragment flightFrag = RampStairsFlightFragment.newInstance();
        flightFrag.setArguments(flightBundle);
        if (actionMode != null)
            actionMode.finish();
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.show_fragment_selected, flightFrag).addToBackStack(null).commit();
    }


    @Override
    public void OnEntryClick(int position) {
        RampStairsFlightEntry flightEntry = modelEntry.allFlights.getValue().get(position);
        flightBundle.putInt(FLIGHT_ID, flightEntry.getFlightID());
        openFlightFragment();
    }
}
