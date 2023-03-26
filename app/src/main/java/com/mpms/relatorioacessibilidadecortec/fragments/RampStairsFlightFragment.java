package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.RampStairsHandrailList;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.RampStairsRailingList;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

public class RampStairsFlightFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextView flightHeader, flightNumberHeader, dimensionsButtonsHeader, borderSignHeader, identifiableBorderSignHeader,
            tactileSignRadioError, lowerTactFloorRadioError, upperTactFloorRadioError, interLevelRadioError,
            borderSignRadioError, borderSignIdentifiableRadioError, mirInclError, stepError, flightNumber;
    TextInputLayout rampStairsWidthField, pavementObsField, tactileFloorObsField, borderSignWidthField, borderSignLengthField, borderSignObsField,
            mirIncField1, mirIncField2, mirIncField3, mirIncField4, stepField1, stepField2, stepField3, stepField4,
            flightLengthField, flightHeightField, lowTactWidthField, lowTactDistField, upTactWidthField, upTactDistField,
            interLvLengthField, interLvObsField, flightObsField, photoField;
    TextInputEditText rampStairsWidthValue, pavementObsValue, tactileFloorObsValue, borderSignWidthValue,borderSignLengthValue, borderSignObsValue,
            mirIncValue1, mirIncValue2, mirIncValue3, mirIncValue4, stepValue1, stepValue2, stepValue3, stepValue4,
            flightLengthValue, flightHeightValue, lowTactWidthValue, lowTactDistValue, upTactWidthValue, upTactDistValue,
            interLvLengthValue, interLvObsValue, flightObsValue, photoValue;
    MaterialButton mirIncButton, stepButton, railingButton, handrailButton, saveFlight, cancelFlight;
    ImageButton deleteMirInc, deleteStep;
    RadioGroup hasPavementSignRadio, lowTactFloorRadio, upTactFloorRadio, interLevelRadio, radioStepBorderSign, radioIdentifiableBorderSign;
    int fNumber;

    Bundle flightBundle;

    ArrayList<TextInputEditText> flightObsArray = new ArrayList<>();

    View buttonClicked;

    int recentFlight = 0;
    int updateFlight = 0;
    int mirIncCounter = 0;
    int stepCounter = 0;

    Integer handrailCounter, railingCounter;

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
        if (this.getArguments() != null)
            flightBundle = new Bundle(this.getArguments());
        else
            flightBundle = new Bundle();

        getActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                cancelClick();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ramp_stairs_flight, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateFlightViews(view);

        if (flightBundle.getInt(FLIGHT_ID) != 0) {
            modelEntry.getRampStairsFlightEntry(flightBundle.getInt(FLIGHT_ID)).observe(getViewLifecycleOwner(), this::loadFlightData);
        }

        rampStairsComponentObservers();

        modelEntry.getLastRampStairsFlightEntry().observe(getViewLifecycleOwner(), flight -> {
            if (recentFlight == 1) {
                recentFlight = 0;
                flightBundle.putInt(FLIGHT_ID, flight.getFlightID());
                openChildFragment(buttonClicked);
            }
        });

        saveFlight.setOnClickListener(v -> {
            if (checkEmptyFlightFields()) {
                if (flightBundle.getInt(FLIGHT_ID) == 0) {
                    if (updateFlight == 0 && !flightBundle.getBoolean(FROM_SIDEWALK)) {
                        Toast.makeText(getContext(), getString(R.string.lack_flight_comp_message), Toast.LENGTH_LONG).show();
                    }
                    else if (updateFlight == 0 && flightBundle.getBoolean(FROM_SIDEWALK)) {
                        RampStairsFlightEntry newFlight = newFlightEntry(flightBundle);
                        ViewModelEntry.insertRampsStairsFlight(newFlight);
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                    else if (updateFlight > 0 || flightBundle.getBoolean(FROM_SIDEWALK)) {
                        if (checkRampStairsComponents() || flightBundle.getBoolean(FROM_SIDEWALK)) {
                            updateFlight(flightBundle);
                            Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                            requireActivity().getSupportFragmentManager().popBackStackImmediate();
                        } else {
                            Toast.makeText(getContext(), getString(R.string.lack_flight_comp_message), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        errorFlightProcedure();
                    }
                } else if (flightBundle.getInt(FLIGHT_ID) > 0) {
                    if (checkRampStairsComponents() || flightBundle.getBoolean(FROM_SIDEWALK)) {
                        updateFlight(flightBundle);
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStackImmediate();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.lack_flight_comp_message), Toast.LENGTH_LONG).show();
                    }
                } else {
                    errorFlightProcedure();
                }
            }
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();

        });

//        Comando faz retornar a tela de cadastro da escadaria
        cancelFlight.setOnClickListener(v -> cancelClick());

    }

    private void cancelClick() {
        if (updateFlight > 0 || recentFlight > 0) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteOneFlight(flightBundle.getInt(FLIGHT_ID));
                flightBundle = null;
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    private void addObsFieldsToArray() {
        if (flightObsArray.size() == 0) {
            flightObsArray.add(pavementObsValue);
            flightObsArray.add(tactileFloorObsValue);
            flightObsArray.add(borderSignObsValue);
        }
    }

    private void updateFlight(Bundle bundle) {
        RampStairsFlightEntry upFlight = newFlightEntry(bundle);
        upFlight.setFlightID(bundle.getInt(FLIGHT_ID));
        ViewModelEntry.updateFlightRampStairs(upFlight);
    }

    public void setRampStairsFlightTemplate(Bundle bundle) {
        switch (bundle.getInt(RAMP_OR_STAIRS)) {
            case 1:
                flightHeader.setText(getString(R.string.label_staircase_register_header));
                rampStairsWidthField.setHint(getString(R.string.hint_stairs_width));
                dimensionsButtonsHeader.setText(getString(R.string.label_step_dimensions_header));
                mirIncButton.setText(R.string.label_mirror_register_text);
                stepButton.setVisibility(View.VISIBLE);
                borderSignHeader.setVisibility(View.VISIBLE);
                radioStepBorderSign.setVisibility(View.VISIBLE);
                radioStepBorderSign.setOnCheckedChangeListener(this::radioListener);
                mirIncField1.setHint(getString(R.string.measurement_m_1));
                mirIncField2.setHint(getString(R.string.measurement_m_2));
                mirIncField3.setHint(getString(R.string.measurement_m_3));
                mirIncField4.setHint(getString(R.string.measurement_m_4));
                break;
            case 2:
                flightLengthField.setVisibility(View.VISIBLE);
                flightHeader.setText(getString(R.string.label_ramp_flights_header));
                rampStairsWidthField.setHint(getString(R.string.hint_ramp_width));
                dimensionsButtonsHeader.setText(getString(R.string.label_inclination_ramp_header));
                mirIncButton.setText(R.string.label_inclination_button);
                mirIncField1.setHint(getString(R.string.first_measurement_perc));
                mirIncField2.setHint(getString(R.string.second_measure_perc));
                mirIncField3.setHint(getString(R.string.third_measure_perc));
                mirIncField4.setHint(getString(R.string.fourth_measure_perc));
                break;
            default:
                errorFlightProcedure();
                break;
        }
    }

    public void errorFlightProcedure() {
        Toast.makeText(getContext(), "Houve um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private void instantiateFlightViews(View view) {
//        TextViews - Headers
        flightHeader = view.findViewById(R.id.flight_header);
        flightNumberHeader = view.findViewById(R.id.flight_number_header);
        dimensionsButtonsHeader = view.findViewById(R.id.dimensions_header);
        borderSignHeader = view.findViewById(R.id.border_sign_header);
        identifiableBorderSignHeader = view.findViewById(R.id.border_sign_identifiable_header);
        flightNumber = view.findViewById(R.id.flight_number);
        if (flightBundle.getInt(NEXT_FLIGHT) > 0)
            flightNumber.setText(String.valueOf(flightBundle.getInt(NEXT_FLIGHT)));
//        TextViews - Errors
        tactileSignRadioError = view.findViewById(R.id.tactile_sign_error);
        lowerTactFloorRadioError = view.findViewById(R.id.lower_tactile_floor_error);
        borderSignRadioError = view.findViewById(R.id.border_sign_error);
        borderSignIdentifiableRadioError = view.findViewById(R.id.border_sign_identifiable_error);
        mirInclError = view.findViewById(R.id.mirror_inclination_values_error);
        stepError = view.findViewById(R.id.step_values_error);
        upperTactFloorRadioError = view.findViewById(R.id.upper_tactile_floor_error);
        interLevelRadioError = view.findViewById(R.id.inter_level_error);
//        TextInputLayouts
        rampStairsWidthField = view.findViewById(R.id.ramp_staircase_width_field);
        pavementObsField = view.findViewById(R.id.pavement_tact_sign_obs_field);
        tactileFloorObsField = view.findViewById(R.id.tactile_floor_obs_field);
        borderSignWidthField = view.findViewById(R.id.border_signal_width_field);
        borderSignLengthField = view.findViewById(R.id.border_signal_length_field);
        borderSignObsField = view.findViewById(R.id.border_signal_obs_field);
        mirIncField1 = view.findViewById(R.id.mirror_inclination_1_field);
        mirIncField2 = view.findViewById(R.id.mirror_inclination_2_field);
        mirIncField3 = view.findViewById(R.id.mirror_inclination_3_field);
        mirIncField4 = view.findViewById(R.id.mirror_inclination_4_field);
        stepField1 = view.findViewById(R.id.step_1_field);
        stepField2 = view.findViewById(R.id.step_2_field);
        stepField3 = view.findViewById(R.id.step_3_field);
        stepField4 = view.findViewById(R.id.step_4_field);
        flightLengthField = view.findViewById(R.id.lance_length_field);
        flightHeightField = view.findViewById(R.id.lance_height_field);
        lowTactWidthField = view.findViewById(R.id.lower_tact_floor_width_field);
        lowTactDistField = view.findViewById(R.id.lower_tact_floor_dist_field);
        upTactWidthField = view.findViewById(R.id.upper_tact_floor_width_field);
        upTactDistField = view.findViewById(R.id.upper_tact_floor_dist_field);
        interLvLengthField = view.findViewById(R.id.inter_level_length_field);
        interLvObsField = view.findViewById(R.id.inter_level_obs_field);
        flightObsField = view.findViewById(R.id.ramp_stairs_obs_field);
        photoField = view.findViewById(R.id.ramp_stairs_flight_photo_field);
//        TextInputEditTexts
        rampStairsWidthValue = view.findViewById(R.id.ramp_staircase_width_value);
        pavementObsValue = view.findViewById(R.id.pavement_tact_sign_obs_value);
        tactileFloorObsValue = view.findViewById(R.id.tactile_floor_obs_value);
        borderSignWidthValue = view.findViewById(R.id.border_signal_width_value);
        borderSignLengthValue = view.findViewById(R.id.border_signal_length_value);
        borderSignObsValue = view.findViewById(R.id.border_signal_obs_value);
        mirIncValue1 = view.findViewById(R.id.mirror_inclination_1_value);
        mirIncValue2 = view.findViewById(R.id.mirror_inclination_2_value);
        mirIncValue3 = view.findViewById(R.id.mirror_inclination_3_value);
        mirIncValue4 = view.findViewById(R.id.mirror_inclination_4_value);
        stepValue1 = view.findViewById(R.id.step_1_value);
        stepValue2 = view.findViewById(R.id.step_2_value);
        stepValue3 = view.findViewById(R.id.step_3_value);
        stepValue4 = view.findViewById(R.id.step_4_value);
        flightLengthValue = view.findViewById(R.id.lance_length_value);
        flightHeightValue = view.findViewById(R.id.lance_height_value);
        lowTactWidthValue = view.findViewById(R.id.lower_tact_floor_width_value);
        lowTactDistValue = view.findViewById(R.id.lower_tact_floor_dist_value);
        upTactWidthValue = view.findViewById(R.id.upper_tact_floor_width_value);
        upTactDistValue = view.findViewById(R.id.upper_tact_floor_dist_value);
        interLvLengthValue = view.findViewById(R.id.inter_level_length_value);
        interLvObsValue = view.findViewById(R.id.inter_level_obs_value);
        flightObsValue = view.findViewById(R.id.ramp_stairs_obs_value);
        photoValue = view.findViewById(R.id.ramp_stairs_flight_photo_value);
//        MaterialButton
        mirIncButton = view.findViewById(R.id.mirror_inclination_button);
        stepButton = view.findViewById(R.id.step_button);
        railingButton = view.findViewById(R.id.railing_beacon_button);
        handrailButton = view.findViewById(R.id.handrail_button);
        saveFlight = view.findViewById(R.id.save_flight);
        cancelFlight = view.findViewById(R.id.cancel_flight);
//        ImageButtons
        deleteMirInc = view.findViewById(R.id.delete_mirror_inclination_measure);
        deleteStep = view.findViewById(R.id.delete_step_measure);
//        RadioButton
        hasPavementSignRadio = view.findViewById(R.id.pavement_tactile_sign_radio);
        lowTactFloorRadio = view.findViewById(R.id.lower_tactile_floor_radio);
        radioStepBorderSign = view.findViewById(R.id.border_sign_radio);
        radioIdentifiableBorderSign = view.findViewById(R.id.border_sign_identifiable_radio);
        upTactFloorRadio = view.findViewById(R.id.upper_tactile_floor_radio);
        interLevelRadio = view.findViewById(R.id.inter_level_radio);

        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
//        ViewModels
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);
//        ButtonListeners
        mirIncButton.setOnClickListener(this::buttonClickListener);
        stepButton.setOnClickListener(this::buttonClickListener);
        deleteStep.setOnClickListener(this::buttonClickListener);
        deleteMirInc.setOnClickListener(this::buttonClickListener);
        railingButton.setOnClickListener(this::buttonClickListener);
        handrailButton.setOnClickListener(this::buttonClickListener);
        lowTactFloorRadio.setOnCheckedChangeListener(this::radioListener);
        upTactFloorRadio.setOnCheckedChangeListener(this::radioListener);
        interLevelRadio.setOnCheckedChangeListener(this::radioListener);

        addObsFieldsToArray();
        allowObsScroll(flightObsArray);
        setRampStairsFlightTemplate(flightBundle);

    }

    private void rampStairsComponentObservers() {
        modelEntry.countRampStairsRailings(flightBundle.getInt(FLIGHT_ID))
                .observe(getViewLifecycleOwner(), railCount -> railingCounter = railCount);
        modelEntry.countRampStairsHandrails(flightBundle.getInt(FLIGHT_ID))
                .observe(getViewLifecycleOwner(), handrailCount -> handrailCounter = handrailCount);
    }

    private void buttonClickListener(View view) {
        if (view == mirIncButton) {
            if (mirIncCounter < 0) {
                mirIncCounter = 0;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (mirIncCounter == 0) {
                mirIncField1.setVisibility(View.VISIBLE);
                mirIncCounter++;
            } else if (mirIncCounter == 1) {
                deleteMirInc.setVisibility(View.VISIBLE);
                mirIncField2.setVisibility(View.VISIBLE);
                mirIncCounter++;
            } else if (mirIncCounter == 2) {
                mirIncField3.setVisibility(View.VISIBLE);
                mirIncCounter++;
            } else if (mirIncCounter == 3) {
                mirIncField4.setVisibility(View.VISIBLE);
                mirIncCounter++;
            }else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        }
        else if (view == deleteMirInc) {
            if (mirIncCounter < 1) {
                mirIncCounter = 1;
                deleteMirInc.setVisibility(View.GONE);
                mirIncValue2.setText(null);
                mirIncField2.setVisibility(View.GONE);
                mirIncValue3.setText(null);
                mirIncField3.setVisibility(View.GONE);
                mirIncValue4.setText(null);
                mirIncField4.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (mirIncCounter == 4) {
                mirIncValue4.setText(null);
                mirIncField4.setVisibility(View.GONE);
            } else if (mirIncCounter == 3) {
                mirIncValue3.setText(null);
                mirIncField3.setVisibility(View.GONE);
            } else if (mirIncCounter == 2) {
                mirIncValue2.setText(null);
                mirIncField2.setVisibility(View.GONE);
                deleteMirInc.setVisibility(View.GONE);
            }
            if (mirIncCounter > 1)
                mirIncCounter--;
        }
        else if (view == stepButton) {
            if (stepCounter < 0) {
                stepCounter = 0;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (stepCounter == 0) {
                stepField1.setVisibility(View.VISIBLE);
                stepCounter++;
            } else if (stepCounter == 1) {
                deleteStep.setVisibility(View.VISIBLE);
                stepField2.setVisibility(View.VISIBLE);
                stepCounter++;
            } else if (stepCounter == 2) {
                stepField3.setVisibility(View.VISIBLE);
                stepCounter++;
            } else if (stepCounter == 3) {
                stepField4.setVisibility(View.VISIBLE);
                stepCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        }
        else if (view == deleteStep) {
            if (stepCounter < 1) {
                stepCounter = 1;
                deleteStep.setVisibility(View.GONE);
                stepValue2.setText(null);
                stepField2.setVisibility(View.GONE);
                stepValue3.setText(null);
                stepField3.setVisibility(View.GONE);
                stepValue4.setText(null);
                stepField4.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (stepCounter == 4) {
                stepValue4.setText(null);
                stepField4.setVisibility(View.GONE);
            } else if (stepCounter == 3) {
                stepValue3.setText(null);
                stepField3.setVisibility(View.GONE);
            } else if (stepCounter == 2) {
                stepValue2.setText(null);
                stepField2.setVisibility(View.GONE);
                deleteStep.setVisibility(View.GONE);
            }
            if (stepCounter > 1)
                stepCounter--;
        }
        else {
            buttonClicked = view;
            if (flightBundle.getInt(FLIGHT_ID) == 0) {
                if (updateFlight == 0) {
                    recentFlight++;
                    updateFlight++;
                    RampStairsFlightEntry newFlight = newFlightEntry(flightBundle);
                    ViewModelEntry.insertRampsStairsFlight(newFlight);
                } else if (updateFlight > 0) {
                    updateFlight++;
                    updateFlight(flightBundle);
                    openChildFragment(view);
                } else {
                    recentFlight = 0;
                    updateFlight = 0;
                    Toast.makeText(getContext(), "Algo deu Errado. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                }
            } else if (flightBundle.getInt(FLIGHT_ID) > 0) {
                if (updateFlight >= 0) {
                    updateFlight++;
                    updateFlight(flightBundle);
                    openChildFragment(view);
                } else {
                    recentFlight = 0;
                    updateFlight = 0;
                    Toast.makeText(getContext(), "Algo deu Errado. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                    errorFlightProcedure();
                }
            } else
                errorFlightProcedure();
        }
    }

    private void openChildFragment(View view) {
        Fragment fragment;
        if (view == handrailButton)
            fragment = RampStairsHandrailList.newInstance();
        else
            fragment = RampStairsRailingList.newInstance();
        fragment.setArguments(flightBundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment)
                .addToBackStack(null).commit();

    }

    public RampStairsFlightEntry newFlightEntry(Bundle bundle) {
        Integer mirrorCounter = null, inclinationCounter = null, hasPavementSign = null, hasLowTactileFloor = null, hasUpTactFloor = null,
                hasInterLevel = null, hasBorderSign = null, borderSignVisible = null;
        Double flightWidth = null, flightLength = null, rampHeight = null, stairMirror1 = null, stairMirror2 = null, stairMirror3 = null, stairMirror4 = null,
                stairStep1 = null, stairStep2 = null, stairStep3 = null, stairStep4 = null, rampSlope1 = null, rampSlope2 = null, rampSlope3 = null,
                rampSlope4 = null, lowTactWidth = null, lowTactDist = null, upTactWidth = null, upTactDist = null, interLvLength = null, borderSignWidth = null,
                borderSignLength = null;
        String signPavementObs = null, tactileFloorObs = null, interLvObs = null, borderSignObs = null, flightObs = null, photo = null;

        int numberFlights;

        if (bundle.getInt(FLIGHT_ID) <= 0)
            numberFlights = bundle.getInt(NEXT_FLIGHT);
        else
            numberFlights = fNumber;

        if (!TextUtils.isEmpty(rampStairsWidthValue.getText()))
            flightWidth = Double.parseDouble(String.valueOf(rampStairsWidthValue.getText()));
        if (!TextUtils.isEmpty(flightHeightValue.getText()))
            rampHeight = Double.parseDouble(String.valueOf(flightHeightValue.getText()));


        if (bundle.getInt(RAMP_OR_STAIRS) == 1) {
            mirrorCounter = mirIncCounter;
            if (mirIncCounter > 0) {
                switch (mirIncCounter) {
                    case 4:
                        if (!TextUtils.isEmpty(mirIncValue4.getText()))
                            stairMirror4 = Double.parseDouble(String.valueOf(mirIncValue4.getText()));
                    case 3:
                        if (!TextUtils.isEmpty(mirIncValue3.getText()))
                            stairMirror3 = Double.parseDouble(String.valueOf(mirIncValue3.getText()));
                    case 2:
                        if (!TextUtils.isEmpty(mirIncValue2.getText()))
                            stairMirror2 = Double.parseDouble(String.valueOf(mirIncValue2.getText()));
                    case 1:
                        if (!TextUtils.isEmpty(mirIncValue1.getText()))
                            stairMirror1 = Double.parseDouble(String.valueOf(mirIncValue1.getText()));
                        break;
                }
            }

            if (stepCounter > 0) {
                switch (stepCounter) {
                    case 4:
                        if (!TextUtils.isEmpty(stepValue4.getText()))
                            stairStep4 = Double.parseDouble(String.valueOf(stepValue4.getText()));
                    case 3:
                        if (!TextUtils.isEmpty(stepValue3.getText()))
                            stairStep3 = Double.parseDouble(String.valueOf(stepValue3.getText()));
                    case 2:
                        if (!TextUtils.isEmpty(stepValue2.getText()))
                            stairStep2 = Double.parseDouble(String.valueOf(stepValue2.getText()));
                    case 1:
                        if (!TextUtils.isEmpty(stepValue1.getText()))
                            stairStep1 = Double.parseDouble(String.valueOf(stepValue1.getText()));
                        break;
                }
            }

            if (indexRadio(radioStepBorderSign) > -1) {
                hasBorderSign = indexRadio(radioStepBorderSign);
                if (hasBorderSign == 1) {
                    if (!TextUtils.isEmpty(borderSignWidthValue.getText()))
                        borderSignWidth = Double.parseDouble(String.valueOf(borderSignWidthValue.getText()));
                    if (!TextUtils.isEmpty(borderSignLengthValue.getText()))
                        borderSignLength = Double.parseDouble(String.valueOf(borderSignLengthValue.getText()));
                    if (indexRadio(radioIdentifiableBorderSign) > -1)
                        borderSignVisible = indexRadio(radioIdentifiableBorderSign);
                    if (!TextUtils.isEmpty(borderSignObsValue.getText()))
                        borderSignObs = String.valueOf(borderSignObsValue.getText());
                }
            }
        }

        else if (bundle.getInt(RAMP_OR_STAIRS) == 2) {

            if (!TextUtils.isEmpty(flightLengthValue.getText()))
                flightLength = Double.parseDouble(String.valueOf(flightLengthValue.getText()));

            inclinationCounter = mirIncCounter;
            if (mirIncCounter > 0) {
                switch (mirIncCounter) {
                    case 4:
                        if (!TextUtils.isEmpty(mirIncValue4.getText()))
                            rampSlope4 = Double.parseDouble(String.valueOf(mirIncValue4.getText()));
                    case 3:
                        if (!TextUtils.isEmpty(mirIncValue3.getText()))
                            rampSlope3 = Double.parseDouble(String.valueOf(mirIncValue3.getText()));
                    case 2:
                        if (!TextUtils.isEmpty(mirIncValue2.getText()))
                            rampSlope2 = Double.parseDouble(String.valueOf(mirIncValue2.getText()));
                    case 1:
                        if (!TextUtils.isEmpty(mirIncValue1.getText()))
                            rampSlope1 = Double.parseDouble(String.valueOf(mirIncValue1.getText()));
                        break;
                }
            }
        }

        if (indexRadio(hasPavementSignRadio) > -1)
            hasPavementSign = indexRadio(hasPavementSignRadio);

        if (!TextUtils.isEmpty(pavementObsValue.getText()))
            signPavementObs = String.valueOf(pavementObsValue.getText());

        if (indexRadio(lowTactFloorRadio) > -1) {
            hasLowTactileFloor = indexRadio(lowTactFloorRadio);
            if (hasLowTactileFloor == 1) {
                if (!TextUtils.isEmpty(lowTactWidthValue.getText()))
                    lowTactWidth = Double.parseDouble(String.valueOf(lowTactWidthValue.getText()));
                if (!TextUtils.isEmpty(lowTactDistValue.getText()))
                    lowTactDist = Double.parseDouble(String.valueOf(lowTactDistValue.getText()));
            }
        }

        if (indexRadio(upTactFloorRadio) > -1) {
            hasUpTactFloor = indexRadio(upTactFloorRadio);
            if (hasUpTactFloor == 1) {
                if (!TextUtils.isEmpty(upTactWidthValue.getText()))
                    upTactWidth = Double.parseDouble(String.valueOf(upTactWidthValue.getText()));
                if (!TextUtils.isEmpty(upTactDistValue.getText()))
                    upTactDist = Double.parseDouble(String.valueOf(upTactDistValue.getText()));
            }
        }

        if (!TextUtils.isEmpty(tactileFloorObsValue.getText()))
            tactileFloorObs = String.valueOf(pavementObsValue.getText());

        if (indexRadio(interLevelRadio) > -1) {
            hasInterLevel = indexRadio(interLevelRadio);
            if (hasInterLevel == 1) {
                if (!TextUtils.isEmpty(interLvLengthValue.getText()))
                    interLvLength = Double.parseDouble(String.valueOf(interLvLengthValue.getText()));
            }
        }

        if (!TextUtils.isEmpty(interLvObsValue.getText()))
            interLvObs = String.valueOf(interLvObsValue.getText());

        if (!TextUtils.isEmpty(flightObsValue.getText()))
            flightObs = String.valueOf(flightObsValue.getText());

        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new RampStairsFlightEntry(bundle.getInt(RAMP_STAIRS_ID), numberFlights, flightWidth, flightLength, rampHeight, mirrorCounter, stairMirror1, stairMirror2,
                stairMirror3, stairMirror4, stepCounter, stairStep1, stairStep2, stairStep3, stairStep4, inclinationCounter, rampSlope1, rampSlope2, rampSlope3, rampSlope4,
                hasPavementSign, signPavementObs, hasLowTactileFloor, lowTactWidth, lowTactDist, hasUpTactFloor, upTactWidth, upTactDist, tactileFloorObs, hasInterLevel,
                interLvLength, interLvObs, hasBorderSign, borderSignWidth, borderSignVisible, borderSignObs, flightObs, borderSignLength, photo);
    }

    public boolean checkEmptyFlightFields() {
        clearEmptyFlightFieldsErrors();
        int error = 0;
        if (TextUtils.isEmpty(rampStairsWidthValue.getText())) {
            error++;
            rampStairsWidthField.setError(getString(R.string.req_field_error));
        }

        if (TextUtils.isEmpty(flightHeightValue.getText())) {
            error++;
            flightHeightField.setError(getString(R.string.req_field_error));
        }

        switch (mirIncCounter) {
            case 4:
                if (mirIncValue4.getText() == null) {
                    error++;
                    mirInclError.setVisibility(View.VISIBLE);
                }
            case 3:
                if (mirIncValue3.getText() == null) {
                    error++;
                    mirInclError.setVisibility(View.VISIBLE);
                }
            case 2:
                if (mirIncValue2.getText() == null) {
                    error++;
                    mirInclError.setVisibility(View.VISIBLE);
                }
            case 1:
                if (mirIncValue1.getText() == null) {
                    error++;
                    mirInclError.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
        switch (stepCounter) {
            case 4:
                if (stepValue4.getText() == null) {
                    error++;
                    stepError.setVisibility(View.VISIBLE);
                }
            case 3:
                if (stepValue3.getText() == null) {
                    error++;
                    stepError.setVisibility(View.VISIBLE);
                }
            case 2:
                if (stepValue2.getText() == null) {
                    error++;
                    stepError.setVisibility(View.VISIBLE);
                }
            case 1:
                if (stepValue1.getText() == null) {
                    error++;
                    stepError.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
        if (indexRadio(hasPavementSignRadio) == -1) {
            error++;
            tactileSignRadioError.setVisibility(View.VISIBLE);
        }

        if (indexRadio(lowTactFloorRadio) == -1) {
            error++;
            lowerTactFloorRadioError.setVisibility(View.VISIBLE);
        } else if (indexRadio(lowTactFloorRadio) == 1) {
            if (TextUtils.isEmpty(lowTactWidthValue.getText())) {
                error++;
                lowTactWidthField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(lowTactDistValue.getText())) {
                error++;
                lowTactDistField.setError(getString(R.string.req_field_error));
            }
        }

        if (indexRadio(upTactFloorRadio) == -1) {
            error++;
            upperTactFloorRadioError.setVisibility(View.VISIBLE);
        } else if (indexRadio(upTactFloorRadio) == 1) {
            if (TextUtils.isEmpty(upTactWidthValue.getText())) {
                error++;
                upTactWidthField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(upTactDistValue.getText())) {
                error++;
                upTactDistField.setError(getString(R.string.req_field_error));
            }
        }

        if (indexRadio(interLevelRadio) == -1) {
            error++;
            interLevelRadioError.setVisibility(View.VISIBLE);
        } else if (indexRadio(interLevelRadio) == 1) {
            if (TextUtils.isEmpty(interLvLengthValue.getText())) {
                error++;
                interLvLengthField.setError(getString(R.string.req_field_error));
            }
        }

        if (flightBundle.getInt(RAMP_OR_STAIRS) == 0) {
            if (TextUtils.isEmpty(flightLengthValue.getText())) {
                error++;
                flightLengthField.setError(getString(R.string.req_field_error));
            }
        }

        if (flightBundle.getInt(RAMP_OR_STAIRS) == 1) {
            if (indexRadio(radioStepBorderSign) == -1) {
                error++;
                borderSignRadioError.setVisibility(View.VISIBLE);
            } else if (indexRadio(radioStepBorderSign) == 1) {
                if (TextUtils.isEmpty(borderSignWidthValue.getText())) {
                    error++;
                    borderSignWidthField.setError(getString(R.string.req_field_error));
                }
                if (TextUtils.isEmpty(borderSignLengthValue.getText())) {
                    error++;
                    borderSignLengthField.setError(getString(R.string.req_field_error));
                }
                if (indexRadio(radioIdentifiableBorderSign) == -1) {
                    error++;
                    borderSignIdentifiableRadioError.setVisibility(View.VISIBLE);
                }
            }
        }

        return error == 0;
    }

    public void clearEmptyFlightFieldsErrors() {
        rampStairsWidthField.setErrorEnabled(false);
        flightLengthField.setErrorEnabled(false);
        flightHeightField.setErrorEnabled(false);
        tactileSignRadioError.setVisibility(View.GONE);
        lowerTactFloorRadioError.setVisibility(View.GONE);
        lowTactWidthField.setErrorEnabled(false);
        lowTactDistField.setErrorEnabled(false);
        upperTactFloorRadioError.setVisibility(View.INVISIBLE);
        upTactDistField.setErrorEnabled(false);
        upTactWidthField.setErrorEnabled(false);
        interLevelRadioError.setVisibility(View.GONE);
        interLvLengthField.setErrorEnabled(false);
        borderSignRadioError.setVisibility(View.GONE);
        borderSignWidthField.setErrorEnabled(false);
        borderSignLengthField.setErrorEnabled(false);
        borderSignIdentifiableRadioError.setVisibility(View.GONE);
        mirInclError.setVisibility(View.GONE);
        stepError.setVisibility(View.GONE);
    }

    @Override
    public void radioListener(RadioGroup radio, int checkedID) {
        int index = indexRadio(radio);
        if (radio == radioStepBorderSign) {
            if (index == 1) {
                borderSignWidthField.setVisibility(View.VISIBLE);
                borderSignLengthField.setVisibility(View.VISIBLE);
                identifiableBorderSignHeader.setVisibility(View.VISIBLE);
                radioIdentifiableBorderSign.setVisibility(View.VISIBLE);
                borderSignObsField.setVisibility(View.VISIBLE);
            } else {
                borderSignWidthValue.setText(null);
                borderSignWidthField.setVisibility(View.GONE);
                borderSignLengthValue.setText(null);
                borderSignLengthField.setVisibility(View.GONE);
                identifiableBorderSignHeader.setVisibility(View.GONE);
                radioIdentifiableBorderSign.clearCheck();
                radioIdentifiableBorderSign.setVisibility(View.GONE);
                borderSignObsValue.setText(null);
                borderSignObsField.setVisibility(View.GONE);
            }
        }
        else if (radio == lowTactFloorRadio) {
            if (index == 1) {
                lowTactWidthField.setVisibility(View.VISIBLE);
                lowTactDistField.setVisibility(View.VISIBLE);
            } else {
                lowTactWidthValue.setText(null);
                lowTactDistValue.setText(null);
                lowTactWidthField.setVisibility(View.GONE);
                lowTactDistField.setVisibility(View.GONE);
            }
        }
        else if (radio == upTactFloorRadio) {
            if (index == 1) {
                upTactWidthField.setVisibility(View.VISIBLE);
                upTactDistField.setVisibility(View.VISIBLE);
            } else {
                upTactWidthValue.setText(null);
                upTactDistValue.setText(null);
                upTactWidthField.setVisibility(View.GONE);
                upTactDistField.setVisibility(View.GONE);
            }
        }
        else if (radio == interLevelRadio) {
            if (index == 1) {
                interLvLengthField.setVisibility(View.VISIBLE);
                interLvObsField.setVisibility(View.VISIBLE);
            } else {
                interLvLengthValue.setText(null);
                interLvObsValue.setText(null);
                interLvLengthField.setVisibility(View.GONE);
                interLvObsField.setVisibility(View.GONE);
            }
        }


    }

    private void loadFlightData(RampStairsFlightEntry rampStairs) {
        fNumber = rampStairs.getFlightNumber();
        flightNumber.setText(String.valueOf(rampStairs.getFlightNumber()));

        if (rampStairs.getFlightWidth() != null)
            rampStairsWidthValue.setText(String.valueOf(rampStairs.getFlightWidth()));

        if (rampStairs.getRampHeight() != null)
            flightHeightValue.setText(String.valueOf(rampStairs.getRampHeight()));

        if (flightBundle.getInt(RAMP_OR_STAIRS) == 1) {
            mirIncCounter = rampStairs.getMirrorCounter();
            switch (mirIncCounter) {
                case 4:
                    mirIncField4.setVisibility(View.VISIBLE);
                    if (rampStairs.getStairMirror4() != null)
                        mirIncValue4.setText(String.valueOf(rampStairs.getStairMirror4()));
                case 3:
                    mirIncField3.setVisibility(View.VISIBLE);
                    if (rampStairs.getStairMirror3() != null)
                        mirIncValue3.setText(String.valueOf(rampStairs.getStairMirror3()));
                case 2:
                    mirIncField2.setVisibility(View.VISIBLE);
                    if (rampStairs.getStairMirror2() != null)
                        mirIncValue2.setText(String.valueOf(rampStairs.getStairMirror2()));
                case 1:
                    deleteMirInc.setVisibility(View.VISIBLE);
                    mirIncField1.setVisibility(View.VISIBLE);
                    if (rampStairs.getStairMirror1() != null)
                        mirIncValue1.setText(String.valueOf(rampStairs.getStairMirror1()));
                    break;
                default:
                    break;
            }

            stepCounter = rampStairs.getStepCounter();
            switch (stepCounter) {
                case 4:
                    stepField4.setVisibility(View.VISIBLE);
                    if (rampStairs.getStairStep4() != null)
                        stepValue4.setText(String.valueOf(rampStairs.getStairStep4()));
                case 3:
                    stepField3.setVisibility(View.VISIBLE);
                    if (rampStairs.getStairStep3() != null)
                        stepValue3.setText(String.valueOf(rampStairs.getStairStep3()));
                case 2:
                    stepField2.setVisibility(View.VISIBLE);
                    if (rampStairs.getStairStep2() != null)
                        stepValue2.setText(String.valueOf(rampStairs.getStairStep2()));
                case 1:
                    deleteStep.setVisibility(View.VISIBLE);
                    stepField1.setVisibility(View.VISIBLE);
                    if (rampStairs.getStairStep1() != null)
                        stepValue1.setText(String.valueOf(rampStairs.getStairStep1()));
                    break;
                default:
                    break;
            }

            if (rampStairs.getBorderSign() != null && rampStairs.getBorderSign() != -1) {
                checkRadioGroup(radioStepBorderSign, rampStairs.getBorderSign());
                if (rampStairs.getBorderSign() == 1) {
                    if (rampStairs.getBorderSignWidth() != null)
                        borderSignWidthValue.setText(String.valueOf(rampStairs.getBorderSignWidth()));
                    if (rampStairs.getBorderSignIdentifiable() != null && rampStairs.getBorderSignIdentifiable() != -1)
                        checkRadioGroup(radioIdentifiableBorderSign, rampStairs.getBorderSignIdentifiable());
                    if (rampStairs.getBorderSignObs() != null)
                        borderSignObsValue.setText(rampStairs.getBorderSignObs());
                }
            }

        }
        else if (flightBundle.getInt(RAMP_OR_STAIRS) == 2) {
            if (rampStairs.getFlightLength() != null)
                flightLengthValue.setText(String.valueOf(rampStairs.getFlightLength()));

            mirIncCounter = rampStairs.getSlopeCounter();
            switch (mirIncCounter) {
                case 4:
                    mirIncField4.setVisibility(View.VISIBLE);
                    if (rampStairs.getRampSlope4() != null)
                        mirIncValue4.setText(String.valueOf(rampStairs.getRampSlope4()));
                case 3:
                    mirIncField3.setVisibility(View.VISIBLE);
                    if (rampStairs.getRampSlope3() != null)
                        mirIncValue3.setText(String.valueOf(rampStairs.getRampSlope3()));
                case 2:
                    mirIncField2.setVisibility(View.VISIBLE);
                    if (rampStairs.getRampSlope2() != null)
                        mirIncValue2.setText(String.valueOf(rampStairs.getRampSlope2()));
                case 1:
                    deleteMirInc.setVisibility(View.VISIBLE);
                    mirIncField1.setVisibility(View.VISIBLE);
                    if (rampStairs.getRampSlope1() != null)
                        mirIncValue1.setText(String.valueOf(rampStairs.getRampSlope1()));
                    break;
                default:
                    break;
            }
        }

        if (rampStairs.getSignPavement() != null && rampStairs.getSignPavement() != -1)
            checkRadioGroup(hasPavementSignRadio, rampStairs.getSignPavement());

        if (rampStairs.getSignPavementObs() != null)
            pavementObsValue.setText(rampStairs.getSignPavementObs());

        if (rampStairs.getHasLowTactFloor() != null && rampStairs.getHasLowTactFloor() != -1)
           checkRadioGroup(lowTactFloorRadio, rampStairs.getHasLowTactFloor());

        if (rampStairs.getLowTactWidth() != null)
            lowTactWidthValue.setText(String.valueOf(rampStairs.getLowTactWidth()));

        if (rampStairs.getLowTactDist() != null)
            lowTactDistValue.setText(String.valueOf(rampStairs.getLowTactDist()));

        if (rampStairs.getHasUpTactFloor() != null && rampStairs.getHasUpTactFloor() != -1)
            checkRadioGroup(upTactFloorRadio, rampStairs.getHasUpTactFloor());

        if (rampStairs.getUpTactWidth() != null)
            upTactWidthValue.setText(String.valueOf(rampStairs.getUpTactWidth()));

        if (rampStairs.getUpTactDist() != null)
            upTactDistValue.setText(String.valueOf(rampStairs.getUpTactDist()));

        if (rampStairs.getTactileFloorObs() != null)
            tactileFloorObsValue.setText(rampStairs.getTactileFloorObs());

        if (rampStairs.getHasInterLevel() != null && rampStairs.getHasInterLevel() != -1)
            checkRadioGroup(interLevelRadio, rampStairs.getHasInterLevel());

        if (rampStairs.getInterLevelLength() != null)
            interLvLengthValue.setText(String.valueOf(rampStairs.getInterLevelLength()));

        if (rampStairs.getInterLevelObs() != null)
            interLvObsValue.setText(rampStairs.getInterLevelObs());

        if (rampStairs.getFlightObs() != null)
            flightObsValue.setText(rampStairs.getFlightObs());

        if (rampStairs.getRampStairPhoto() != null)
            photoValue.setText(rampStairs.getRampStairPhoto());

    }

    private boolean checkRampStairsComponents() {
        int i = 0;
        if (handrailCounter == null || handrailCounter <= 0)
            i++;
        if (railingCounter == null || railingCounter <= 0)
            i++;
        return i == 0;

    }

}