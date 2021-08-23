package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;

import com.mpms.relatorioacessibilidadecortec.fragments.RampStairsFragment;

import java.util.Objects;


public class RampStairsFlightFragment extends Fragment {

    TextView flightHeader, dimensionsButtonsHeader, stairsBorderSignHeader, identifiableBorderSignHeader;
    TextInputLayout rampStairsWidthField, tactileSignObsField, tactileFloorObsField, borderSignWidthField,
    borderSignObsField;
    TextInputEditText rampStairsWidthValue, tactileSignObsValue, tactileFloorObsValue, borderSignWidthValue,
            borderSignObsValue;
    Button mirrorButton, stepButton, inclinationButton, railingButton, handrailButton, saveFlight, cancelFlight;
    RadioGroup radioTactileSign, radioTactileFloor, radioStepBorderSign, radioIdentifiableBorderSign;

    Double rampStairsWidth, borderSignWidth;
    String tactileSignObs, tactileFloorObs, borderSignObs;
    Integer stairsHaveBorderSign, identifiableBorderSign;

    Bundle rampStairsBundle = new Bundle();

    int rampOrStairs;

    public RampStairsFlightFragment() {
        // Required empty public constructor
    }

    public static RampStairsFlightFragment newInstance() {
        return new RampStairsFlightFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.fragment_ramp_stairs_flight, container, false);

       rampStairsBundle = this.getArguments();
       if (rampStairsBundle != null) {
           rampOrStairs = rampStairsBundle.getInt(RampStairsFragment.RAMP_OR_STAIRS);
       }

       return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        flightHeader = view.findViewById(R.id.flight_header);
        dimensionsButtonsHeader = view.findViewById(R.id.dimensions_header);
        stairsBorderSignHeader = view.findViewById(R.id.border_sign_header);
        identifiableBorderSignHeader = view.findViewById(R.id.border_sign_identifiable_header);

        rampStairsWidthField = view.findViewById(R.id.ramp_staircase_width_field);
        tactileSignObsField = view.findViewById(R.id.tactile_sign_obs_field);
        tactileFloorObsField = view.findViewById(R.id.tactile_floor_obs_field);
        borderSignWidthField = view.findViewById(R.id.border_signal_width_field);
        borderSignObsField = view.findViewById(R.id.border_signal_obs_field);

        rampStairsWidthValue = view.findViewById(R.id.ramp_staircase_width_value);
        tactileSignObsValue = view.findViewById(R.id.tactile_sign_obs_value);
        tactileFloorObsValue = view.findViewById(R.id.tactile_floor_obs_value);
        borderSignWidthValue = view.findViewById(R.id.border_signal_width_value);
        borderSignObsValue = view.findViewById(R.id.border_signal_obs_value);

        mirrorButton = view.findViewById(R.id.mirror_button);
        stepButton = view.findViewById(R.id.step_button);
        inclinationButton = view.findViewById(R.id.inclination_button);
        railingButton = view.findViewById(R.id.railing_beacon_button);
        handrailButton = view.findViewById(R.id.handrail_button);
        saveFlight = view.findViewById(R.id.save_flight);
        cancelFlight = view.findViewById(R.id.cancel_flight);

        radioTactileSign = view.findViewById(R.id.pavement_tactile_sign_radio);
        radioTactileFloor = view.findViewById(R.id.tactile_floor_radio);
        radioStepBorderSign = view.findViewById(R.id.border_sign_radio);
        radioIdentifiableBorderSign = view.findViewById(R.id.border_sign_identifiable_radio);

        switch (rampOrStairs) {
            case 7:
                flightHeader.setText(getString(R.string.label_staircase_register_header));
                rampStairsWidthField.setHint(getString(R.string.hint_stairs_width));
                dimensionsButtonsHeader.setText(getString(R.string.label_step_dimensions_header));
                mirrorButton.setVisibility(View.VISIBLE);
                stepButton.setVisibility(View.VISIBLE);
                stairsBorderSignHeader.setVisibility(View.VISIBLE);
                radioStepBorderSign.setVisibility(View.VISIBLE);
                radioIdentifiableBorderSign.setVisibility(View.VISIBLE);
                borderSignWidthField.setVisibility(View.VISIBLE);
                borderSignObsField.setVisibility(View.VISIBLE);
                break;
            case 9:
                flightHeader.setText(getString(R.string.label_ramp_flights_header));
                rampStairsWidthField.setHint(getString(R.string.hint_ramp_width));
                dimensionsButtonsHeader.setText(getString(R.string.label_inclination_ramp_header));
                inclinationButton.setVisibility(View.VISIBLE);
                break;
            default:
                errorFlightProcedure();
                break;
        }
    }

    public void errorFlightProcedure(){
        Toast.makeText(getContext(), "Houve um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

}