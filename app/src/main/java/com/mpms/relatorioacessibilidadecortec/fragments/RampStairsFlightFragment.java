package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
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

import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.FlightsRampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;


public class RampStairsFlightFragment extends Fragment {

    TextView flightHeader, dimensionsButtonsHeader, borderSignHeader, identifiableBorderSignHeader,
    tactileSignRadioError, tactileFloorRadioError, borderSignRadioError, borderSignIdentifiableRadioError;
    TextInputLayout rampStairsWidthField, tactileSignObsField, tactileFloorObsField, borderSignWidthField,
    borderSignObsField;
    TextInputEditText rampStairsWidthValue, tactileSignObsValue, tactileFloorObsValue, borderSignWidthValue,
            borderSignObsValue;
    Button mirrorButton, stepButton, inclinationButton, railingButton, handrailButton, saveFlight, cancelFlight;
    RadioGroup radioTactileSign, radioTactileFloor, radioStepBorderSign, radioIdentifiableBorderSign;

    Double rampStairsWidth, borderSignWidth;
    String tactileSignObs, tactileFloorObs, borderSignObs;
    Integer hasTactileSign, hasTactileFloor, stairsHaveBorderSign, identifiableBorderSign;

    Bundle rampStairsBundle = new Bundle();

    int rampOrStairs;

    ViewModelFragments modelFragments;
    ViewModelEntry modelEntry;

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
       if (rampStairsBundle != null)
           rampOrStairs = rampStairsBundle.getInt(RampStairsFragment.RAMP_OR_STAIRS);

        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        flightHeader = view.findViewById(R.id.flight_header);
        dimensionsButtonsHeader = view.findViewById(R.id.dimensions_header);
        borderSignHeader = view.findViewById(R.id.border_sign_header);
        identifiableBorderSignHeader = view.findViewById(R.id.border_sign_identifiable_header);
        tactileSignRadioError = view.findViewById(R.id.tactile_sign_error);
        tactileFloorRadioError = view.findViewById(R.id.tactile_floor_error);
        borderSignRadioError = view.findViewById(R.id.border_sign_error);
        borderSignIdentifiableRadioError = view.findViewById(R.id.border_sign_identifiable_error);

        rampStairsWidthField = view.findViewById(R.id.ramp_staircase_width_field);
        tactileSignObsField = view.findViewById(R.id.tactile_sign_obs_field);
        tactileFloorObsField = view.findViewById(R.id.tactile_floor_obs_field);
        borderSignWidthField = view.findViewById(R.id.border_signal_width_field);
        borderSignObsField = view.findViewById(R.id.border_signal_obs_field);

        rampStairsWidthValue = view.findViewById(R.id.ramp_staircase_width_value);
        tactileSignObsValue = view.findViewById(R.id.pavement_tact_sign_obs_value);
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

        setRampStairsFlightTemplate(rampOrStairs);

        saveFlight.setOnClickListener( v-> {
            if(checkEmptyFlightFields()) {
                FlightsRampStairsEntry newFlight = newFlightEntry(rampStairsBundle);
                ViewModelEntry.insertRampsStairsFlight(newFlight);
                Toast.makeText(getContext(), "Informações Salvas com Sucesso!", Toast.LENGTH_SHORT).show();
                rampStairsBundle.putInt(InspectionActivity.ALLOW_UPDATE,0);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RampStairsFragment rampStairs = RampStairsFragment.newInstance(rampStairsBundle.getInt(RampStairsFragment.RAMP_OR_STAIRS));
                rampStairs.setArguments(rampStairsBundle);
                fragmentTransaction.replace(R.id.show_fragment_selected, rampStairs).commit();
            }
//            TODO - Permitir gravar todos os lances em vez de só um!!!!!!!!!!
        });

//        Comando faz retornar a tela de cadastro da escadaria
//        TODO - Inserir dialog de perda de dados cadastrados ao tentar retornar para a tela inicial de cadastro
        cancelFlight.setOnClickListener(v-> requireActivity().getSupportFragmentManager().popBackStackImmediate());

    }

    public void setRampStairsFlightTemplate(int template) {
        switch (template) {
            case 7:
                flightHeader.setText(getString(R.string.label_staircase_register_header));
                rampStairsWidthField.setHint(getString(R.string.hint_stairs_width));
                dimensionsButtonsHeader.setText(getString(R.string.label_step_dimensions_header));
                mirrorButton.setVisibility(View.VISIBLE);
                stepButton.setVisibility(View.VISIBLE);
                borderSignHeader.setVisibility(View.VISIBLE);
                radioStepBorderSign.setVisibility(View.VISIBLE);
                radioStepBorderSign.setOnCheckedChangeListener(this::borderSignListener);
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
        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    public FlightsRampStairsEntry newFlightEntry(Bundle bundle) {
        rampStairsWidth = null;
        borderSignWidth = null;
        tactileSignObs = null;
        tactileFloorObs = null;
        borderSignObs = null;
        hasTactileSign = null;
        hasTactileFloor = null;
        stairsHaveBorderSign = null;
        identifiableBorderSign = null;

        rampStairsWidth = Double.parseDouble(Objects.requireNonNull(rampStairsWidthValue.getText()).toString());
        hasTactileSign = getFlightRadioCheckedIndex(radioTactileSign);
        if(!TextUtils.isEmpty(tactileSignObsValue.getText()))
            tactileSignObs = Objects.requireNonNull(tactileSignObsValue.getText()).toString();
        hasTactileFloor = getFlightRadioCheckedIndex(radioTactileFloor);
        if (!TextUtils.isEmpty(tactileFloorObsValue.getText()))
            tactileFloorObs = Objects.requireNonNull(tactileFloorObsValue.getText()).toString();
        if (bundle.getInt(RampStairsFragment.RAMP_OR_STAIRS) == 7) {
            stairsHaveBorderSign = getFlightRadioCheckedIndex(radioStepBorderSign);
            if (stairsHaveBorderSign == 1) {
                borderSignWidth = Double.parseDouble(Objects.requireNonNull(borderSignWidthValue.getText()).toString());
                identifiableBorderSign = getFlightRadioCheckedIndex(radioIdentifiableBorderSign);
                if (!TextUtils.isEmpty(borderSignObsValue.getText()))
                    borderSignObs = Objects.requireNonNull(borderSignObsValue.getText()).toString();
            }
        }
        return new FlightsRampStairsEntry(bundle.getInt(RampStairsFragment.RAMP_STAIRS_ID),rampStairsWidth,hasTactileSign,tactileSignObs,
                hasTactileFloor, tactileFloorObs, stairsHaveBorderSign, borderSignWidth, identifiableBorderSign, borderSignObs);
    }

    public int getFlightRadioCheckedIndex(RadioGroup radio){
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptyFlightFields() {
        clearEmptyFlightFieldsErrors();
        int error = 0;
        if (TextUtils.isEmpty(rampStairsWidthValue.getText())) {
            error++;
            rampStairsWidthField.setError(getString(R.string.blank_field_error));
        }
        if (getFlightRadioCheckedIndex(radioTactileSign) == -1) {
            error++;
            tactileSignRadioError.setVisibility(View.VISIBLE);
        }
        if (getFlightRadioCheckedIndex(radioTactileFloor) == -1) {
            error++;
            tactileFloorRadioError.setVisibility(View.VISIBLE);
        }
        if (getFlightRadioCheckedIndex(radioStepBorderSign) == -1 && rampOrStairs == 7) {
            error++;
            borderSignRadioError.setVisibility(View.VISIBLE);
        } else if (getFlightRadioCheckedIndex(radioStepBorderSign) == 1 && rampOrStairs == 7) {
            if (TextUtils.isEmpty(borderSignWidthValue.getText())) {
                error++;
                borderSignWidthField.setError(getString(R.string.blank_field_error));
            }
            if (getFlightRadioCheckedIndex(radioIdentifiableBorderSign) == -1) {
                error++;
                borderSignIdentifiableRadioError.setVisibility(View.VISIBLE);
            }
        }
        return error == 0;
    }

    public void clearEmptyFlightFieldsErrors() {
        rampStairsWidthField.setErrorEnabled(false);
        tactileSignRadioError.setVisibility(View.GONE);
        tactileFloorRadioError.setVisibility(View.GONE);
        borderSignRadioError.setVisibility(View.GONE);
        borderSignWidthField.setErrorEnabled(false);
        borderSignIdentifiableRadioError.setVisibility(View.GONE);
    }

    public void borderSignListener(RadioGroup radio, int checkedID) {
        int index = getFlightRadioCheckedIndex(radio);
        if (index == 1) {
            borderSignWidthField.setVisibility(View.VISIBLE);
            identifiableBorderSignHeader.setVisibility(View.VISIBLE);
            radioIdentifiableBorderSign.setVisibility(View.VISIBLE);
            borderSignObsField.setVisibility(View.VISIBLE);
        } else {
            borderSignWidthValue.setText(null);
            borderSignWidthField.setVisibility(View.GONE);
            identifiableBorderSignHeader.setVisibility(View.GONE);
            radioIdentifiableBorderSign.clearCheck();
            radioIdentifiableBorderSign.setVisibility(View.GONE);
            borderSignObsValue.setText(null);
            borderSignObsField.setVisibility(View.GONE);
        }
    }

//    public void clearFlightFields() {
//        radioTactileSign.clearCheck();
//        radioTactileFloor.clearCheck();
//        radioStepBorderSign.clearCheck();
//        radioIdentifiableBorderSign.clearCheck();
//    }

}