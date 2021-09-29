package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddRampInclinationDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddStairsMirrorDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddStairsStepDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.FlightsRampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.ArrayList;
import java.util.Objects;

public class RampStairsFlightFragment extends Fragment {

    public static final String FLIGHT_ID = "FLIGHT_ID";

    TextView flightHeader, dimensionsButtonsHeader, borderSignHeader, identifiableBorderSignHeader,
    tactileSignRadioError, tactileFloorRadioError, borderSignRadioError, borderSignIdentifiableRadioError;
    TextInputLayout rampStairsWidthField, tactileSignObsField, tactileFloorObsField, borderSignWidthField,
    borderSignObsField;
    TextInputEditText rampStairsWidthValue, tactileSignObsValue, tactileFloorObsValue, borderSignWidthValue,
            borderSignObsValue;
    Button mirrorButton, stepButton, inclinationButton, railingButton, handrailButton, saveFlight, cancelFlight;
    RadioGroup radioTactileSign, radioTactileFloor, radioStepBorderSign, radioIdentifiableBorderSign;
    TextView flightNumber;

    Double rampStairsWidth, borderSignWidth;
    String tactileSignObs, tactileFloorObs, borderSignObs;
    Integer hasTactileSign, hasTactileFloor, stairsHaveBorderSign, identifiableBorderSign;

    Bundle rampStairsData = new Bundle();
    Bundle flightBundle = new Bundle();
    Bundle newEntryBundle = new Bundle();

    ArrayList<TextInputEditText> flightObsArray = new ArrayList<>();

    View buttonClicked;

    int recentFlight = 0;
    int updateFlight = 0;
    int flightID = 0;
    int numberFlights = 1;

    Integer stepCounter, mirrorCounter, slopeCounter, handrailCounter, railingCounter;

    ViewModelFragments modelFragments;
    ViewModelEntry modelEntry;
    ViewModelDialog modelDialog;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

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

       rampStairsData = this.getArguments();
       if (rampStairsData != null) {
           flightBundle.putInt(RampStairsFragment.RAMP_STAIRS_ID,rampStairsData.getInt(RampStairsFragment.RAMP_STAIRS_ID));
           flightBundle.putInt(RampStairsFragment.RAMP_OR_STAIRS,rampStairsData.getInt(RampStairsFragment.RAMP_OR_STAIRS));
           flightBundle.putInt(RampStairsFragment.NUMBER_FLIGHTS,rampStairsData.getInt(RampStairsFragment.NUMBER_FLIGHTS));
           flightID = rampStairsData.getInt(FLIGHT_ID, 0);
           newEntryBundle.putInt(RampStairsFragment.RAMP_OR_STAIRS,rampStairsData.getInt(RampStairsFragment.RAMP_OR_STAIRS));
           newEntryBundle.putInt(InspectionActivity.SCHOOL_ID_VALUE, rampStairsData.getInt(InspectionActivity.SCHOOL_ID_VALUE));
       }

        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateFlightViews(view);
        allowFlightObsScroll();
        setRampStairsFlightTemplate(flightBundle.getInt(RampStairsFragment.RAMP_OR_STAIRS));

        flightNumber.setText(String.valueOf((numberFlights)));

        mirrorButton.setOnClickListener(this::buttonListener);
        stepButton.setOnClickListener(this::buttonListener);
        inclinationButton.setOnClickListener(this::buttonListener);
        railingButton.setOnClickListener(this::buttonListener);
        handrailButton.setOnClickListener(this::buttonListener);

        if (flightBundle.getInt(FLIGHT_ID) != 0) {
            modelEntry.getRampStairsFlightEntry(flightBundle.getInt(FLIGHT_ID)).observe(getViewLifecycleOwner(), this::gatherFlightData);
        }

        rampStairsComponentObservers(flightBundle.getInt(RampStairsFragment.RAMP_OR_STAIRS));

        modelEntry.getLastRampStairsFlightEntry().observe(getViewLifecycleOwner(), flight -> {
            if (recentFlight == 1) {
                recentFlight = 0;
                flightBundle.putInt(FLIGHT_ID, flight.getFlightID());
                buttonTypeListener(buttonClicked);
            }
        });

        saveFlight.setOnClickListener( v-> {
            if(checkEmptyFlightFields()) {
                if (flightID == 0) {
                    if (updateFlight == 0) {
                        Toast.makeText(getContext(), "Por favor, cadastre os componentes deste local", Toast.LENGTH_SHORT).show();
                    } else if (updateFlight > 0) {
                        if(checkRampStairsComponents(flightBundle.getInt(FLIGHT_ID))) {
                           updateFlight(flightBundle);
                           Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                           celarFlightFragment();
                        } else {
                            Toast.makeText(getContext(), "Por favor, cadastre os componentes deste local", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        errorFlightProcedure();
                    }
                } else if (flightID > 0) {
                    if(checkRampStairsComponents(flightBundle.getInt(FLIGHT_ID))) {
                        updateFlight(flightBundle);
                        Toast.makeText(getContext(), "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                        celarFlightFragment();
                    } else {
                        Toast.makeText(getContext(), "Por favor, cadastre os componentes deste local", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    errorFlightProcedure();
                }
            }

        });

//        Comando faz retornar a tela de cadastro da escadaria
//        TODO - Inserir dialog de perda de dados cadastrados ao tentar retornar para a tela inicial de cadastro
        cancelFlight.setOnClickListener(v-> requireActivity().getSupportFragmentManager().popBackStackImmediate());

    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private void addObsFieldsToArray() {
        flightObsArray.add(tactileSignObsValue);
        flightObsArray.add(tactileFloorObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowFlightObsScroll() {
        addObsFieldsToArray();
        for (TextInputEditText obsScroll :flightObsArray) {
            obsScroll.setOnTouchListener(this::scrollingField);
        }
    }

    private void celarFlightFragment() {
        if (numberFlights < flightBundle.getInt(RampStairsFragment.NUMBER_FLIGHTS)) {
            numberFlights++;
            flightBundle.putInt(FLIGHT_ID, 0);
            flightNumber.setText(String.valueOf(numberFlights));
            stepCounter = 0;
            mirrorCounter = 0;
            slopeCounter = 0;
            handrailCounter = 0;
            railingCounter = 0;
            updateFlight = 0;
            clearFlightFields();
        } else {
            RampStairsFragment rampStairs = RampStairsFragment.newInstance(newEntryBundle.getInt(RampStairsFragment.RAMP_OR_STAIRS));
            rampStairs.setArguments(newEntryBundle);
            Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
            fragmentTransaction.replace(R.id.show_fragment_selected, rampStairs).addToBackStack(null).commit();
        }
    }

    private void clearFlightFields() {
        rampStairsWidthValue.setText(null);
        tactileSignObsValue.setText(null);
        tactileFloorObsValue.setText(null);
        borderSignWidthValue.setText(null);
        borderSignObsValue.setText(null);
        radioTactileSign.clearCheck();
        radioTactileFloor.clearCheck();
        radioStepBorderSign.clearCheck();
        radioIdentifiableBorderSign.clearCheck();
    }

    private void updateFlight(Bundle bundle) {
        FlightsRampStairsEntry upFlight = newFlightEntry(bundle);
        upFlight.setFlightID(bundle.getInt(FLIGHT_ID));
        ViewModelEntry.updateFlightRampStairs(upFlight);
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

    private void instantiateFlightViews(View view) {
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

        flightNumber = view.findViewById(R.id.flight_number);
    }

    private void rampStairsComponentObservers(int template) {
        if (template == 7) {
            modelDialog.getStairsMirrorCounter().observe(getViewLifecycleOwner(), mirrorUnits -> mirrorCounter = mirrorUnits);
            modelDialog.getStairsStepCounter().observe(getViewLifecycleOwner(), stepUnits -> stepCounter = stepUnits);
        } else if (template == 9) {
            modelDialog.getRampSlopeCounter().observe(getViewLifecycleOwner(), rampUnits -> slopeCounter = rampUnits);
        }
        modelEntry.countRampStairsRailings(flightBundle.getInt(FLIGHT_ID))
                .observe(getViewLifecycleOwner(), railCount -> railingCounter = railCount);
        modelEntry.countRampStairsHandrails(flightBundle.getInt(FLIGHT_ID))
                .observe(getViewLifecycleOwner(), handrailCount -> handrailCounter = handrailCount);
    }

    private void buttonListener(View view) {
        buttonClicked = view;
        if (flightID == 0) {
            if (updateFlight == 0) {
                recentFlight++;
                updateFlight++;
                FlightsRampStairsEntry newFlight = newFlightEntry(flightBundle);
                ViewModelEntry.insertRampsStairsFlight(newFlight);
            } else if (updateFlight > 0) {
                updateFlight++;
                updateFlight(flightBundle);
                buttonTypeListener(view);
            } else {
                recentFlight = 0;
                updateFlight = 0;
                Toast.makeText(getContext(), "Algo deu Errado. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
            }
        } else if (flightID > 0) {
            if (updateFlight >= 0) {
                updateFlight++;
                updateFlight(flightBundle);
                buttonTypeListener(view);
            } else {
                recentFlight = 0;
                updateFlight = 0;
                Toast.makeText(getContext(), "Algo deu Errado. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                errorFlightProcedure();
            }
        } else {
            errorFlightProcedure();
        }
    }

    private void buttonTypeListener(View view) {
        if (view == mirrorButton) {
            addStairsMirrorDialog();
        } else if (view == stepButton) {
            addStairsStepDialog();
        } else if (view == inclinationButton) {
            addRampInclinationDialog();
        } else {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (view == handrailButton) {
                RampStairsHandrailFragment handrailFragment = RampStairsHandrailFragment.newInstance();
                handrailFragment.setArguments(flightBundle);
                fragmentTransaction.replace(R.id.show_fragment_selected, handrailFragment).addToBackStack(null).commit();
            } else if (view == railingButton) {
                RampStairsRailingFragment railingFragment = RampStairsRailingFragment.newInstance();
                railingFragment.setArguments(flightBundle);
                fragmentTransaction.replace(R.id.show_fragment_selected, railingFragment).addToBackStack(null).commit();
            }
        }
    }

    public FlightsRampStairsEntry newFlightEntry(Bundle bundle) {
        if (!TextUtils.isEmpty(rampStairsWidthValue.getText()))
            rampStairsWidth = Double.valueOf(String.valueOf(rampStairsWidthValue.getText()));
        hasTactileSign = getFlightRadioCheckedIndex(radioTactileSign);
        if(!TextUtils.isEmpty(tactileSignObsValue.getText()))
            tactileSignObs = Objects.requireNonNull(tactileSignObsValue.getText()).toString();
        hasTactileFloor = getFlightRadioCheckedIndex(radioTactileFloor);
        if (!TextUtils.isEmpty(tactileFloorObsValue.getText()))
            tactileFloorObs = Objects.requireNonNull(tactileFloorObsValue.getText()).toString();
        if (bundle.getInt(RampStairsFragment.RAMP_OR_STAIRS) == 7) {
            stairsHaveBorderSign = getFlightRadioCheckedIndex(radioStepBorderSign);
            if (stairsHaveBorderSign == 1) {
                if (!TextUtils.isEmpty(borderSignWidthValue.getText()))
                    borderSignWidth = Double.valueOf(String.valueOf(borderSignWidthValue.getText()));
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
        if (getFlightRadioCheckedIndex(radioStepBorderSign) == -1 && flightBundle.getInt(RampStairsFragment.RAMP_OR_STAIRS) == 7) {
            error++;
            borderSignRadioError.setVisibility(View.VISIBLE);
        } else if (getFlightRadioCheckedIndex(radioStepBorderSign) == 1 && flightBundle.getInt(RampStairsFragment.RAMP_OR_STAIRS) == 7) {
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

    private void addStairsMirrorDialog() {
        AddStairsMirrorDialog.displayMirrorDialog(requireActivity().getSupportFragmentManager(), flightBundle);
    }

    private void addStairsStepDialog() {
        AddStairsStepDialog.displayStepDialog(requireActivity().getSupportFragmentManager(), flightBundle);
    }

    private void addRampInclinationDialog() {
        AddRampInclinationDialog.inclinationDialog(requireActivity().getSupportFragmentManager(), flightBundle);
    }

    private void gatherFlightData(FlightsRampStairsEntry rampStairs) {
//        TODO - Fazer um método mais elegante, isso tem muito cara de gambiarra - remover observer talvez?
        if (flightBundle.getInt(FLIGHT_ID) != 0) {
            if (rampStairs.getFlightWidth() != null)
                rampStairsWidthValue.setText(String.valueOf(rampStairs.getFlightWidth()));
            if (rampStairs.getSignPavement() != null && rampStairs.getSignPavement() != -1)
                radioTactileSign.check(radioTactileSign.getChildAt(rampStairs.getSignPavement()).getId());
            tactileSignObsValue.setText(rampStairs.getSignPavementObs());
            if (rampStairs.getTactileFloor() != null && rampStairs.getTactileFloor() != -1)
                radioTactileFloor.check(radioTactileFloor.getChildAt(rampStairs.getTactileFloor()).getId());
            tactileFloorObsValue.setText(rampStairs.getTactileFloorObs());
            if (flightBundle.getInt(RampStairsFragment.RAMP_OR_STAIRS) == 7) {
                if (rampStairs.getBorderSign() != null && rampStairs.getBorderSign() != -1) {
                    radioStepBorderSign.check(radioStepBorderSign.getChildAt(rampStairs.getBorderSign()).getId());
                    if (rampStairs.getBorderSign() == 1) {
                        if (rampStairs.getBorderSignWidth() != null)
                            borderSignWidthValue.setText(String.valueOf(rampStairs.getBorderSignWidth()));
                        if (rampStairs.getBorderSignIdentifiable() != null && rampStairs.getBorderSignIdentifiable() != -1)
                            radioIdentifiableBorderSign.check(radioIdentifiableBorderSign.getChildAt(rampStairs.getBorderSignIdentifiable()).getId());
                        borderSignObsValue.setText(rampStairs.getBorderSignObs());
                    }
                }
            }
        }
    }

    private boolean checkRampStairsComponents(int template) {
        int i = 0;
        switch (template) {
            case 7:
                if (stepCounter == null || stepCounter <= 0)
                    i++;
                if (mirrorCounter == null || mirrorCounter <= 0)
                    i++;
                break;
            case 9:
                if (slopeCounter == null || slopeCounter <= 0)
                    i++;
                break;
        }
        if (handrailCounter == null || handrailCounter <= 0)
            i++;
        if (railingCounter == null || railingCounter <= 0)
            i++;
        return i == 0;

    }

}