package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
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

        instantiateCounterView(view);
        allowCounterObsScroll();

        addCounter.setOnClickListener(v -> {
            if(checkEmptyCounterFields()) {
                CounterEntry newCounter = newCounter(counterBundle);
                ViewModelEntry.insertCounter(newCounter);
                Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                clearCounterFields();
            }
        });

        cancelCounter.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
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

    private void instantiateCounterView(View view) {
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
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowCounterObsScroll() {
        counterObsValue.setOnTouchListener(this::scrollingField);
    }

    public boolean checkEmptyCounterFields() {
        clearErrorsCounterFields();
        int error = 0;
        if (TextUtils.isEmpty(counterLocationValue.getText())) {
            error++;
            counterLocationField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(upperEdgeValue.getText())) {
            error++;
            upperEdgeField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(lowerEdgeValue.getText())) {
            error++;
            lowerEdgeField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(frontalApproxValue.getText())) {
            error++;
            frontalApproxField.setError(getString(R.string.blank_field_error));
        }
        return error == 0;
    }

    public void clearErrorsCounterFields() {
        counterLocationField.setErrorEnabled(false);
        upperEdgeField.setErrorEnabled(false);
        lowerEdgeField.setErrorEnabled(false);
        frontalApproxField.setErrorEnabled(false);
    }

    public void clearCounterFields() {
        counterLocationValue.setText(null);
        upperEdgeValue.setText(null);
        lowerEdgeValue.setText(null);
        frontalApproxValue.setText(null);
        counterObsValue.setText(null);
    }

    public CounterEntry newCounter(Bundle bundle) {
        counterLocation = null;
        counterObs = null;
        upperEdge = null;
        lowerEdge = null;
        frontalApprox = null;

        if (!TextUtils.isEmpty(counterLocationValue.getText()))
            counterLocation = Objects.requireNonNull(counterLocationValue.getText()).toString();
        if (!TextUtils.isEmpty(upperEdgeValue.getText()))
            upperEdge = Double.parseDouble(Objects.requireNonNull(upperEdgeValue.getText()).toString());
        if (!TextUtils.isEmpty(lowerEdgeValue.getText()))
            lowerEdge = Double.parseDouble(Objects.requireNonNull(lowerEdgeValue.getText()).toString());
        if (!TextUtils.isEmpty(frontalApproxValue.getText()))
            frontalApprox = Double.parseDouble(Objects.requireNonNull(frontalApproxValue.getText()).toString());
        if (!TextUtils.isEmpty(counterObsValue.getText()))
            counterObs = Objects.requireNonNull(counterLocationValue.getText()).toString();

        return new CounterEntry(bundle.getInt(RoomsRegisterFragment.ROOM_ID_VALUE),counterLocation, upperEdge, lowerEdge, frontalApprox, counterObs);
    }
}