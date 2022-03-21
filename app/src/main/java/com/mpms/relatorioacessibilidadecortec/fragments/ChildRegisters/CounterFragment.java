package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class CounterFragment extends Fragment {

    public static final String COUNTER_ID = "COUNTER_ID";

    TextInputLayout counterLocationField, counterSupHeightField, counterInfHeightField, counterFrontApproxField, counterObsField;
    TextInputEditText counterLocationValue, counterSupHeightValue, counterInfHeightValue, counterFrontApproxValue, counterObsValue;
    MaterialButton saveCounter, cancelCounter;

    Bundle counterBundle = new Bundle();

    ViewModelEntry modelEntry;

    public CounterFragment() {
        // Required empty public constructor
    }

    public static CounterFragment newInstance() {
        return new CounterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            counterBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
            counterBundle.putInt(COUNTER_ID, this.getArguments().getInt(COUNTER_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateCounterView(view);

        if (counterBundle.getInt(COUNTER_ID) > 0)
            modelEntry.getSpecificCounter(counterBundle.getInt(COUNTER_ID)).observe(getViewLifecycleOwner(), this::loadCounterData);

        saveCounter.setOnClickListener(v -> {
            if (checkEmptyCounterFields()) {
                CounterEntry newCounter = newCounter(counterBundle);
                if (counterBundle.getInt(COUNTER_ID) > 0) {
                    newCounter.setCounterID(counterBundle.getInt(COUNTER_ID));
                    ViewModelEntry.updateCounter(newCounter);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (counterBundle.getInt(COUNTER_ID)  == 0) {
                    ViewModelEntry.insertCounter(newCounter);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearCounterFields();
                } else {
                    counterBundle.putInt(COUNTER_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }

            }
        });

        cancelCounter.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateCounterView(View view) {
//        TextInputLayout
        counterLocationField = view.findViewById(R.id.counter_ref_location_field);
        counterSupHeightField = view.findViewById(R.id.counter_upper_edge_field);
        counterInfHeightField = view.findViewById(R.id.counter_lower_edge_field);
        counterFrontApproxField = view.findViewById(R.id.counter_frontal_approx_field);
        counterObsField = view.findViewById(R.id.counter_obs_field);
//        TextInputEditText
        counterLocationValue = view.findViewById(R.id.counter_ref_location_value);
        counterSupHeightValue = view.findViewById(R.id.counter_upper_edge_value);
        counterInfHeightValue = view.findViewById(R.id.counter_lower_edge_value);
        counterFrontApproxValue = view.findViewById(R.id.counter_frontal_approx_value);
        counterObsValue = view.findViewById(R.id.counter_obs_value);
//        MaterialButton
        saveCounter = view.findViewById(R.id.save_counter_button);
        cancelCounter = view.findViewById(R.id.cancel_counter_button);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        InitializingMethods
        allowCounterObsScroll();
    }

    private void loadCounterData(CounterEntry counter) {
        counterLocationValue.setText(counter.getCounterLocation());
        counterSupHeightValue.setText(String.valueOf(counter.getCounterUpperEdge()));
        counterInfHeightValue.setText(String.valueOf(counter.getCounterLowerEdge()));
        counterFrontApproxValue.setText(String.valueOf(counter.getCounterFrontalApprox()));
        if (counter.getCounterObs() != null)
            counterObsValue.setText(counter.getCounterObs());
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
        if (TextUtils.isEmpty(counterSupHeightValue.getText())) {
            error++;
            counterSupHeightField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(counterInfHeightValue.getText())) {
            error++;
            counterInfHeightField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(counterFrontApproxValue.getText())) {
            error++;
            counterFrontApproxField.setError(getString(R.string.blank_field_error));
        }
        return error == 0;
    }

    public void clearErrorsCounterFields() {
        counterLocationField.setErrorEnabled(false);
        counterSupHeightField.setErrorEnabled(false);
        counterInfHeightField.setErrorEnabled(false);
        counterFrontApproxField.setErrorEnabled(false);
    }

    public void clearCounterFields() {
        counterLocationValue.setText(null);
        counterSupHeightValue.setText(null);
        counterInfHeightValue.setText(null);
        counterFrontApproxValue.setText(null);
        counterObsValue.setText(null);
    }

    public CounterEntry newCounter(Bundle bundle) {
        String counterLocale, counterObs = null;
        double counterSupHeight, counterInfHeight, counterFrontApprox;

        counterLocale = Objects.requireNonNull(counterLocationValue.getText()).toString();
        counterSupHeight = Double.parseDouble(Objects.requireNonNull(counterSupHeightValue.getText()).toString());
        counterInfHeight = Double.parseDouble(Objects.requireNonNull(counterInfHeightValue.getText()).toString());
        counterFrontApprox = Double.parseDouble(Objects.requireNonNull(counterFrontApproxValue.getText()).toString());
        if (!TextUtils.isEmpty(counterObsValue.getText()))
            counterObs = Objects.requireNonNull(counterLocationValue.getText()).toString();

        return new CounterEntry(bundle.getInt(RoomsRegisterFragment.ROOM_ID), counterLocale, counterSupHeight, counterInfHeight, counterFrontApprox, counterObs);
    }
}