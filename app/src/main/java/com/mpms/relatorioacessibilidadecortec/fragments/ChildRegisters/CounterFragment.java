package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.Objects;

public class CounterFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout counterLocationField, counterSupHeightField, counterInfHeightField, counterFrontApproxField, counterObsField,
            counterWidthField, counterFreeWidthField, photoField;
    TextInputEditText counterLocationValue, counterSupHeightValue, counterInfHeightValue, counterFrontApproxValue, counterObsValue,
            counterWidthValue, counterFreeWidthValue, photoValue;
    MaterialButton saveCounter, cancelCounter;

    Bundle counterBundle;

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
        if (this.getArguments() != null)
            counterBundle = new Bundle(this.getArguments());
        else
            counterBundle = new Bundle();
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
                } else if (counterBundle.getInt(COUNTER_ID) == 0) {
                    ViewModelEntry.insertCounter(newCounter);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearCounterFields();
                } else {
                    counterBundle.putInt(COUNTER_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
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
        counterWidthField = view.findViewById(R.id.counter_width_field);
        counterFreeWidthField = view.findViewById(R.id.counter_fs_width_field);
        photoField = view.findViewById(R.id.counter_photo_field);
//        TextInputEditText
        counterLocationValue = view.findViewById(R.id.counter_ref_location_value);
        counterSupHeightValue = view.findViewById(R.id.counter_upper_edge_value);
        counterInfHeightValue = view.findViewById(R.id.counter_lower_edge_value);
        counterFrontApproxValue = view.findViewById(R.id.counter_frontal_approx_value);
        counterObsValue = view.findViewById(R.id.counter_obs_value);
        counterWidthValue = view.findViewById(R.id.counter_width_value);
        counterFreeWidthValue = view.findViewById(R.id.counter_fs_width_value);
        photoValue = view.findViewById(R.id.counter_photo_value);
//        MaterialButton
        saveCounter = view.findViewById(R.id.save_counter_button);
        cancelCounter = view.findViewById(R.id.cancel_counter_button);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        InitializingMethods
        allowObsScroll(counterObsValue);
    }

    private void loadCounterData(CounterEntry counter) {
        if (counter.getCounterLocation() != null)
            counterLocationValue.setText(counter.getCounterLocation());
        counterSupHeightValue.setText(String.valueOf(counter.getCounterUpperEdge()));
        counterInfHeightValue.setText(String.valueOf(counter.getCounterLowerEdge()));
        counterWidthValue.setText(String.valueOf(counter.getCounterWidth()));
        counterFreeWidthValue.setText(String.valueOf(counter.getCounterFreeWidth()));
        counterFrontApproxValue.setText(String.valueOf(counter.getCounterFrontalApprox()));
        if (counter.getCounterObs() != null)
            counterObsValue.setText(counter.getCounterObs());
        if (counter.getCounterPhoto() != null)
            photoValue.setText(counter.getCounterPhoto());
    }

    public boolean checkEmptyCounterFields() {
        clearErrorsCounterFields();
        int error = 0;
        if (TextUtils.isEmpty(counterLocationValue.getText())) {
            error++;
            counterLocationField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(counterWidthValue.getText())) {
            error++;
            counterWidthField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(counterFreeWidthValue.getText())) {
            error++;
            counterFreeWidthField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(counterSupHeightValue.getText())) {
            error++;
            counterSupHeightField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(counterInfHeightValue.getText())) {
            error++;
            counterInfHeightField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(counterFrontApproxValue.getText())) {
            error++;
            counterFrontApproxField.setError(getString(R.string.req_field_error));
        }
        return error == 0;
    }

    public void clearErrorsCounterFields() {
        counterLocationField.setErrorEnabled(false);
        counterSupHeightField.setErrorEnabled(false);
        counterInfHeightField.setErrorEnabled(false);
        counterWidthField.setErrorEnabled(false);
        counterFreeWidthField.setErrorEnabled(false);
        counterFrontApproxField.setErrorEnabled(false);
    }

    public void clearCounterFields() {
        counterLocationValue.setText(null);
        counterSupHeightValue.setText(null);
        counterInfHeightValue.setText(null);
        counterFrontApproxValue.setText(null);
        counterWidthValue.setText(null);
        counterFreeWidthValue.setText(null);
        counterObsValue.setText(null);
        photoValue.setText(null);
    }

    public CounterEntry newCounter(Bundle bundle) {
        Integer room = null, circ = null;
        String counterLocale, counterObs = null, photo = null;
        double counterSupHeight, counterInfHeight, counterFrontApprox, counterWidth, counterFreeWidth;

        if (bundle.getInt(AMBIENT_ID) > 0)
            room = bundle.getInt(AMBIENT_ID);
        else if (bundle.getInt(CIRC_ID) > 0)
            circ = bundle.getInt(CIRC_ID);

        counterLocale = String.valueOf(counterLocationValue.getText());
        counterSupHeight = Double.parseDouble(String.valueOf(counterSupHeightValue.getText()));
        counterInfHeight = Double.parseDouble(String.valueOf(counterInfHeightValue.getText()));
        counterWidth = Double.parseDouble(String.valueOf(counterWidthValue.getText()));
        counterFreeWidth = Double.parseDouble(String.valueOf(counterFreeWidthValue.getText()));
        counterFrontApprox = Double.parseDouble(Objects.requireNonNull(counterFrontApproxValue.getText()).toString());
        if (!TextUtils.isEmpty(counterObsValue.getText()))
            counterObs = Objects.requireNonNull(counterLocationValue.getText()).toString();
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = Objects.requireNonNull(photoValue.getText()).toString();

        return new CounterEntry(room, circ, counterLocale, counterSupHeight, counterInfHeight, counterFrontApprox, counterObs, counterWidth,
                counterFreeWidth, photo);
    }
}