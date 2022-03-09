package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class RampStairsRailingFragment extends Fragment {

    TextInputLayout railingHeightField, railingObsField, beaconHeightField, beaconObsField;
    TextInputEditText railingHeightValue, railingObsValue, beaconHeightValue, beaconObsValue;
    RadioGroup railingSideRadio, hasBeaconRadio;
    MultiLineRadioGroup hasRailingRadio;
    TextView railingSideError, hasRailingError, hasBeaconError;
    Button saveRailing, cancelRailing;

    Integer railingSide, hasRailing, hasBeacon;
    Double railingHeight, beaconHeight;
    String railingObs, beaconObs;

    Bundle railingBundle = new Bundle();
    Bundle flightBundle = new Bundle();
    ArrayList<TextInputEditText> obsRailingArray = new ArrayList<>();

    public RampStairsRailingFragment() {
        // Required empty public constructor
    }


    public static RampStairsRailingFragment newInstance() {
        return new RampStairsRailingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ramp_stairs_railing, container, false);

        flightBundle = this.getArguments();
        if (flightBundle != null)
            railingBundle.putInt(RampStairsFlightFragment.FLIGHT_ID, flightBundle.getInt(RampStairsFlightFragment.FLIGHT_ID));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRailingFragmentViews(view);
        allowRailingObsScroll();
        initializeRailingFragment();
        hasRailingRadio.setOnCheckedChangeListener(this::hasRailingListener);
        hasBeaconRadio.setOnCheckedChangeListener(this::hasBeaconListener);

        saveRailing.setOnClickListener(v -> {
            if (checkRailingEmptyFields()) {
                RampStairsRailingEntry newRailing = railingEntry(railingBundle);
                ViewModelEntry.insertRampStairsRailing(newRailing);
                Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                initializeRailingFragment();
            }
        });

        cancelRailing.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateRailingFragmentViews(View view) {
//        TextInputLayout
        railingHeightField = view.findViewById(R.id.railing_height_field);
        railingObsField = view.findViewById(R.id.railing_obs_field);
        beaconHeightField = view.findViewById(R.id.beacon_height_field);
        beaconObsField = view.findViewById(R.id.beacon_obs_field);
//        TextInputEditText
        railingHeightValue = view.findViewById(R.id.railing_height_value);
        railingObsValue = view.findViewById(R.id.railing_obs_value);
        beaconHeightValue = view.findViewById(R.id.beacon_height_value);
        beaconObsValue = view.findViewById(R.id.beacon_obs_value);
//        RadioGroup
        railingSideRadio = view.findViewById(R.id.railing_side_radio);
        hasBeaconRadio = view.findViewById(R.id.staircase_has_beacon_radio);
//        MultiLineRadioGroup
        hasRailingRadio = view.findViewById(R.id.staircase_has_railing_radio);
//        TextView
        railingSideError = view.findViewById(R.id.railing_side_error);
        hasRailingError = view.findViewById(R.id.staircase_railing_error);
        hasBeaconError = view.findViewById(R.id.staircase_beacon_error);
//        Button
        saveRailing = view.findViewById(R.id.save_railing);
        cancelRailing = view.findViewById(R.id.cancel_railing);
    }

    private void initializeRailingFragment() {
//        TextInputEditText
        railingHeightValue.setText(null);
        railingObsValue.setText(null);
        beaconHeightValue.setText(null);
        beaconObsValue.setText(null);
//        TextInputLayout
        railingHeightField.setEnabled(false);
        beaconHeightField.setEnabled(false);
//        RadioGroup
        railingSideRadio.clearCheck();
        hasBeaconRadio.clearCheck();
//        MultiLineRadioGroup
        hasRailingRadio.clearCheck();
    }

    public boolean scrollingDoorField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private void addRailingFieldsToArrays() {
        obsRailingArray.add(railingObsValue);
        obsRailingArray.add( beaconObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowRailingObsScroll() {
        addRailingFieldsToArrays();
        for (TextInputEditText obsScroll : obsRailingArray) {
            obsScroll.setOnTouchListener(this::scrollingDoorField);
        }
    }

    private int getCheckedRailingRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkRailingEmptyFields() {
        clearRailingEmptyFieldError();
        int i = 0;
        if (getCheckedRailingRadio(railingSideRadio) == -1) {
            i++;
            railingSideError.setVisibility(View.VISIBLE);
        }
        if (hasRailingRadio.getCheckedRadioButtonIndex() == -1) {
            i++;
            hasRailingError.setVisibility(View.VISIBLE);
        } else if (hasRailingRadio.getCheckedRadioButtonIndex() == 1 || hasRailingRadio.getCheckedRadioButtonIndex() == 2) {
            if (TextUtils.isEmpty(railingHeightValue.getText())) {
                i++;
                railingHeightField.setError(getString(R.string.blank_field_error));
            }
        }
        if (getCheckedRailingRadio(hasBeaconRadio) == -1) {
            i++;
            hasBeaconError.setVisibility(View.VISIBLE);
        } else if (getCheckedRailingRadio(hasBeaconRadio) != 0) {
            if (TextUtils.isEmpty(beaconHeightValue.getText())) {
                i++;
                beaconHeightField.setError(getString(R.string.blank_field_error));
            }
        }
        return i == 0;
    }

    private void clearRailingEmptyFieldError() {
        hasRailingError.setVisibility(View.GONE);
        railingSideError.setVisibility(View.GONE);
        hasBeaconError.setVisibility(View.GONE);

        railingHeightField.setErrorEnabled(false);
        beaconHeightField.setErrorEnabled(false);
    }

    private void hasRailingListener(ViewGroup multi, RadioButton rButton) {
        int index = hasRailingRadio.getCheckedRadioButtonIndex();
        if (index == 1 || index == 2) {
//        TextInputLayout
            railingHeightField.setEnabled(true);
            railingObsField.setEnabled(true);
        } else {
//        TextInputEditText
            railingHeightValue.setText(null);
//        TextInputLayout
            railingHeightField.setEnabled(false);
        }
    }

    private void hasBeaconListener(RadioGroup radio, int checkedID) {
        int index = getCheckedRailingRadio(radio);
        if (index > 0) {
//        TextInputLayout
            beaconHeightField.setEnabled(true);
        } else {
//        TextInputEditText
            beaconHeightValue.setText(null);
//        TextInputLayout
            beaconHeightField.setEnabled(false);
        }
    }

    private RampStairsRailingEntry railingEntry(Bundle bundle) {
        railingSide = getCheckedRailingRadio(railingSideRadio);
        hasRailing = hasRailingRadio.getCheckedRadioButtonIndex();
        if (hasRailing == 1 || hasRailing == 2)
            railingHeight = Double.valueOf(String.valueOf(railingHeightValue.getText()));
        railingObs = String.valueOf(railingObsValue.getText());
        hasBeacon = getCheckedRailingRadio(hasBeaconRadio);
        if (hasBeacon > 0)
            beaconHeight = Double.valueOf(String.valueOf(beaconHeightValue.getText()));
        beaconObs = String.valueOf(beaconObsValue.getText());

        return new RampStairsRailingEntry(bundle.getInt(RampStairsFlightFragment.FLIGHT_ID), railingSide, hasRailing, railingHeight,
                railingObs, hasBeacon, beaconHeight, beaconObs);
    }

}