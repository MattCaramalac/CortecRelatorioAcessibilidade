package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class AddCounterDialog extends DialogFragment {

    private Toolbar toolbar;

    TextInputLayout counterLocationField, upperEdgeField, lowerEdgeField, frontalApproxField, counterObsField;
    TextInputEditText counterLocationValue, upperEdgeValue, lowerEdgeValue, frontalApproxValue, counterObsValue;
    Button addCounter, cancelCounter;

    String counterLocation, counterObs;
    Double upperEdge, lowerEdge, frontalApprox;

    FragmentManager manager;

    static Bundle counterBundle;

    ViewModelDialog modelDialog;
    ViewModelEntry modelEntry;

    public static AddCounterDialog displayCounterDialog(FragmentManager manager, Bundle bundle) {
        AddCounterDialog counterDialog = new AddCounterDialog();
        counterDialog.show(manager, "DOOR_DIALOG");
        counterBundle = bundle;
        return counterDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_counter_dialog, container, false);
        toolbar = view.findViewById(R.id.room_counter_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.dialog_add_counter_header);

        counterLocationField = view.findViewById(R.id.counter_ref_location_field);
        upperEdgeField = view.findViewById(R.id.counter_upper_edge_field);
        lowerEdgeField = view.findViewById(R.id.counter_lower_edge_field);
        frontalApproxField = view.findViewById(R.id.counter_frontal_approx_field);
        counterObsField = view.findViewById(R.id.counter_obs_field);
        counterLocationValue = view.findViewById(R.id.counter_ref_location_value);
        upperEdgeValue = view.findViewById(R.id.counter_upper_edge_value);
        lowerEdgeValue = view.findViewById(R.id.counter_lower_edge_value);
        frontalApproxValue = view.findViewById(R.id.counter_frontal_approx_value);
        counterObsValue = view.findViewById(R.id.counter_obs_value);
        addCounter = view.findViewById(R.id.save_counter_button);
        cancelCounter = view.findViewById(R.id.cancel_counter_button);

        cancelCounter.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int length = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, length);
        }
    }
}