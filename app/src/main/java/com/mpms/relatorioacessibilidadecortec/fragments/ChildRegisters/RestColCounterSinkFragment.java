package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.CounterSinkParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class RestColCounterSinkFragment extends Fragment implements TagInterface, ScrollEditText {

    RadioGroup lowerSinkRadio, sinkBarRadio;
    TextView lowerSinkError, sinkBarError;
    TextInputLayout counterHeightField, freeSpaceField, obsField;
    TextInputEditText counterHeightValue, freeSpaceValue, obsValue;

    Bundle cSinkBundle;

    ViewModelEntry modelEntry;

    public RestColCounterSinkFragment() {
        // Required empty public constructor
    }

    public static RestColCounterSinkFragment newInstance() {
        return new RestColCounterSinkFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            cSinkBundle = new Bundle(this.getArguments());
        else
            cSinkBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_col_counter_sink, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, getViewLifecycleOwner(), (key, bundle) -> {
            if (checkEmptyFields()) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                CounterSinkParcel parcel = createCounterParcel();
                bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
            } else
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });

        if (cSinkBundle.getInt(REST_ID) > 0)
            modelEntry.getRestCounterSinkData(cSinkBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadCounterSinkData);
    }

    private void instantiateViews(View view) {
//        RadioGroup
        lowerSinkRadio = view.findViewById(R.id.has_lower_sink_radio);
        sinkBarRadio = view.findViewById(R.id.has_counter_bar_sink_radio);
//        TextView
        lowerSinkError = view.findViewById(R.id.has_lower_sink_error);
        sinkBarError = view.findViewById(R.id.has_counter_bar_sink_error);
//        TextInputLayout
        counterHeightField = view.findViewById(R.id.lower_sink_upper_height_field);
        freeSpaceField = view.findViewById(R.id.lower_sink_lower_height_field);
        obsField = view.findViewById(R.id.sink_counter_obs_field);
//        TextInputEditText
        counterHeightValue = view.findViewById(R.id.lower_sink_upper_height_value);
        freeSpaceValue = view.findViewById(R.id.lower_sink_lower_height_value);
        obsValue = view.findViewById(R.id.sink_counter_obs_value);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listener
        lowerSinkRadio.setOnCheckedChangeListener(this::radioListener);
    }

    private int getCheckRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void radioListener(RadioGroup radio, int checkID) {
        int index = getCheckRadio(radio);

        if (index == 1) {
            counterHeightField.setVisibility(View.VISIBLE);
            freeSpaceField.setVisibility(View.VISIBLE);
        } else {
            counterHeightValue.setText(null);
            freeSpaceValue.setText(null);
            counterHeightField.setVisibility(View.GONE);
            freeSpaceField.setVisibility(View.GONE);
        }
    }

    private void loadCounterSinkData(RestroomEntry rest) {
        if (rest.getHasLowerSink() != null) {
            lowerSinkRadio.check(lowerSinkRadio.getChildAt(rest.getHasLowerSink()).getId());
            if (rest.getHasLowerSink() == 1) {
                if (rest.getApproxMeasureB() != null)
                    counterHeightValue.setText(String.valueOf(rest.getApproxMeasureB()));
                if (rest.getApproxMeasureC() != null)
                    freeSpaceValue.setText(String.valueOf(rest.getApproxMeasureC()));
            }
        }
        if (rest.getHasSinkBar() != null)
            sinkBarRadio.check(sinkBarRadio.getChildAt(rest.getHasSinkBar()).getId());
        if (rest.getSinkObs() != null)
            obsValue.setText(rest.getSinkObs());
    }

    private boolean checkEmptyFields() {
        clearErrors();
        int i = 0;
        if (getCheckRadio(lowerSinkRadio) == -1) {
            i++;
            lowerSinkError.setVisibility(View.VISIBLE);
        } else if (getCheckRadio(lowerSinkRadio) == 1) {
            if (TextUtils.isEmpty(counterHeightValue.getText())) {
                i++;
                counterHeightField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(freeSpaceValue.getText())) {
                i++;
                freeSpaceField.setError(getString(R.string.req_field_error));
            }
        }
        if (getCheckRadio(sinkBarRadio) == -1) {
            i++;
            sinkBarError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void clearErrors() {
        lowerSinkError.setVisibility(View.GONE);
        sinkBarError.setVisibility(View.GONE);
        counterHeightField.setErrorEnabled(false);
        freeSpaceField.setErrorEnabled(false);
    }

    private CounterSinkParcel createCounterParcel() {
        int hasLowSink, hasBar;
        Double upperCounter = null, freeSpace = null;
        String sinkObs = null;

        hasLowSink = getCheckRadio(lowerSinkRadio);
        if (hasLowSink == 1) {
            upperCounter = Double.parseDouble(String.valueOf(counterHeightValue.getText()));
            freeSpace = Double.parseDouble(String.valueOf(freeSpaceValue.getText()));
        }
        hasBar = getCheckRadio(sinkBarRadio);
        if (!TextUtils.isEmpty(obsValue.getText()))
            sinkObs = String.valueOf(obsValue.getText());

        return new CounterSinkParcel(hasLowSink, upperCounter, freeSpace, hasBar, sinkObs);
    }
}