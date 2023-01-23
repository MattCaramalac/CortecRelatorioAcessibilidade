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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillInclinationFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillSlopeFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillStepFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class PlaygroundFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout playLocaleField, floorTypeField, playGateWidthField, playTrackHeightField, trackMeasureField1, trackMeasureField2,
            trackMeasureField3, trackMeasureField4, playGateSillObsField, floorObsField, toyObsField, playObsField,
            trackGapField1, trackGapField2, trackGapField3, trackGapField4;
    TextInputEditText playLocaleValue, floorTypeValue, playGateWidthValue, playTrackHeightValue, trackMeasureValue1, trackMeasureValue2,
            trackMeasureValue3, trackMeasureValue4, playGateSillObsValue, floorObsValue, toyObsValue, playObsValue,
            trackGapValue1, trackGapValue2, trackGapValue3, trackGapValue4;
    RadioGroup playGateTrackRadio, playGateTrackRampRadio, playFloorGapRadio, accessibleFloorRadio, accessibleToyRadio;
    MultiLineRadioGroup gateSillRadio;
    MaterialButton addTrackRampButton, addTrackGapButton, savePlay, cancelPlay;
    ImageButton delTrackRampButton, delTrackGapButton;
    TextView playGateTrackError, hasTrackRampHeader, playTrackRampError, playSillTypeError, playAccessFloorError, playAccessToyError, playFloorGapError, floorGapHeader;

    FragmentManager manager;

    ViewModelEntry modelEntry;

    Bundle playBundle = new Bundle();
    Bundle childData = new Bundle();

    ArrayList<TextInputLayout> rampTrackFields = new ArrayList<>();
    ArrayList<TextInputLayout> floorGapFields = new ArrayList<>();
    ArrayList<TextInputEditText> eText = new ArrayList<>();

    int rampCounter = 0;
    int gapCounter = 0;

    public PlaygroundFragment() {
        // Required empty public constructor
    }

    public static PlaygroundFragment newInstance() {
        return new PlaygroundFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            playBundle.putInt(BLOCK_ID, this.getArguments().getInt(BLOCK_ID));
            playBundle.putInt(PLAY_ID, this.getArguments().getInt(PLAY_ID, 0));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playground, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiatePlayViews(view);

        if (playBundle.getInt(PLAY_ID) > 0)
            modelEntry.getOnePlayground(playBundle.getInt(PLAY_ID)).observe(getViewLifecycleOwner(), this::loadPlaygroundData);

        savePlay.setOnClickListener(v -> {
            if (gateSillRadio.getCheckedRadioButtonIndex() > 0) {
                getChildFragmentManager().setFragmentResult(InspectionActivity.GATHER_CHILD_DATA, childData);
            } else {
                if (checkEmptyPlayFields()) {
                    PlaygroundEntry newPlayEntry = newPlayground(playBundle);
                    if (playBundle.getInt(PLAY_ID) > 0) {
                        newPlayEntry.setPlayID(playBundle.getInt(PLAY_ID));
                        ViewModelEntry.updatePlayground(newPlayEntry);
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack(PLAYGROUND_LIST, 0);
                    } else {
                        ViewModelEntry.insertPlayground(newPlayEntry);
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                        clearPlayFields();
                    }
                }
            }
        });

        cancelPlay.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        getChildFragmentManager().setFragmentResultListener(InspectionActivity.CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (checkEmptyPlayFields()) {
                bundle.putInt(BLOCK_ID, playBundle.getInt(BLOCK_ID));
                PlaygroundEntry newPlayEntry = newPlayground(bundle);
                if (playBundle.getInt(PLAY_ID) > 0) {
                    newPlayEntry.setPlayID(playBundle.getInt(PLAY_ID));
                    ViewModelEntry.updatePlayground(newPlayEntry);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else {
                    ViewModelEntry.insertPlayground(newPlayEntry);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearPlayFields();
                }
            }
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });
    }

    private void instantiatePlayViews(View view) {
//        TextInputLayout
        playLocaleField = view.findViewById(R.id.location_playground_field);
        floorTypeField = view.findViewById(R.id.floor_type_playground_field);
        playGateWidthField = view.findViewById(R.id.gate_width_play_field);
        playTrackHeightField = view.findViewById(R.id.play_tracks_height_field);
        trackMeasureField1 = view.findViewById(R.id.play_ramp_track_1_field);
        trackMeasureField2 = view.findViewById(R.id.play_ramp_track_2_field);
        trackMeasureField3 = view.findViewById(R.id.play_ramp_track_3_field);
        trackMeasureField4 = view.findViewById(R.id.play_ramp_track_4_field);
        trackGapField1 = view.findViewById(R.id.play_track_gap_1_field);
        trackGapField2 = view.findViewById(R.id.play_track_gap_2_field);
        trackGapField3 = view.findViewById(R.id.play_track_gap_3_field);
        trackGapField4 = view.findViewById(R.id.play_track_gap_4_field);
        playGateSillObsField = view.findViewById(R.id.playground_sill_obs_field);
        floorObsField = view.findViewById(R.id.playground_floor_obs_field);
        toyObsField = view.findViewById(R.id.playground_equips_obs_field);
        playObsField = view.findViewById(R.id.playground_obs_field);
//        TextInputEditText
        playLocaleValue = view.findViewById(R.id.location_playground_value);
        floorTypeValue = view.findViewById(R.id.floor_type_playground_value);
        playGateWidthValue = view.findViewById(R.id.gate_width_play_value);
        playTrackHeightValue = view.findViewById(R.id.play_tracks_height_value);
        trackMeasureValue1 = view.findViewById(R.id.play_ramp_track_1_value);
        trackMeasureValue2 = view.findViewById(R.id.play_ramp_track_2_value);
        trackMeasureValue3 = view.findViewById(R.id.play_ramp_track_3_value);
        trackMeasureValue4 = view.findViewById(R.id.play_ramp_track_4_value);
        trackGapValue1 = view.findViewById(R.id.play_track_gap_1_value);
        trackGapValue2 = view.findViewById(R.id.play_track_gap_2_value);
        trackGapValue3 = view.findViewById(R.id.play_track_gap_3_value);
        trackGapValue4 = view.findViewById(R.id.play_track_gap_4_value);
        playGateSillObsValue = view.findViewById(R.id.playground_sill_obs_value);
        floorObsValue = view.findViewById(R.id.playground_floor_obs_value);
        toyObsValue = view.findViewById(R.id.playground_equips_obs_value);
        playObsValue = view.findViewById(R.id.playground_obs_value);
//        RadioGroup
        playGateTrackRadio = view.findViewById(R.id.has_play_gate_tracks_radio);
        playGateTrackRampRadio = view.findViewById(R.id.gate_has_play_track_ramp_radio);
        accessibleFloorRadio = view.findViewById(R.id.play_has_accessible_floor_radio);
        accessibleToyRadio = view.findViewById(R.id.play_has_accessible_equips_radio);
        playFloorGapRadio = view.findViewById(R.id.has_play_track_gaps_radio);
//        Multiline RadioGroup
        gateSillRadio = view.findViewById(R.id.playground_type_sill_radio);
//        MaterialButton
        addTrackRampButton = view.findViewById(R.id.add_play_gate_track_ramp_button);
        addTrackGapButton = view.findViewById(R.id.add_play_track_gap_button);
        savePlay = view.findViewById(R.id.save_playground);
        cancelPlay = view.findViewById(R.id.cancel_playground);
//        ImageButton
        delTrackRampButton = view.findViewById(R.id.delete_play_ramp_track_measure);
        delTrackGapButton = view.findViewById(R.id.delete_play_track_gap_measure);
//        TextView
        playGateTrackError = view.findViewById(R.id.has_play_gate_tracks_error);
        hasTrackRampHeader = view.findViewById(R.id.label_play_gate_has_track_ramp);
        playTrackRampError = view.findViewById(R.id.has_play_track_ramp_error);
        playSillTypeError = view.findViewById(R.id.playground_sill_type_error);
        playAccessFloorError = view.findViewById(R.id.play_has_accessible_floor_error);
        playAccessToyError = view.findViewById(R.id.play_has_accessible_equips_error);
        playFloorGapError = view.findViewById(R.id.play_track_gap_radio_error);
        floorGapHeader = view.findViewById(R.id.label_play_has_track_gaps);
//        Fragment Manager
        manager = getChildFragmentManager();
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        addTrackGapButton.setOnClickListener(this::addFieldClickListener);
        addTrackRampButton.setOnClickListener(this::addFieldClickListener);
        delTrackGapButton.setOnClickListener(this::addFieldClickListener);
        delTrackRampButton.setOnClickListener(this::addFieldClickListener);
        playGateTrackRadio.setOnCheckedChangeListener(this::playRadioListener);
        playFloorGapRadio.setOnCheckedChangeListener(this::playRadioListener);
        playGateTrackRampRadio.setOnCheckedChangeListener(this::playRadioListener);
        gateSillRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener) (v, r) -> playMultiRadioListener(gateSillRadio));
//        ArrayLists
        addRampTrackToArrays();
        allowObsScroll(eText);
    }

    private void addFieldClickListener(View v) {
        if (v == addTrackRampButton) {
            if (rampCounter < 1) {
                rampCounter = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (rampCounter == 1) {
                delTrackRampButton.setVisibility(View.VISIBLE);
                trackMeasureField2.setVisibility(View.VISIBLE);
                rampCounter++;
            } else if (rampCounter == 2) {
                trackMeasureField3.setVisibility(View.VISIBLE);
                rampCounter++;
            } else if (rampCounter == 3) {
                trackMeasureField4.setVisibility(View.VISIBLE);
                rampCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (v == delTrackRampButton) {
            if (rampCounter < 1) {
                rampCounter = 1;
                delTrackRampButton.setVisibility(View.GONE);
                trackMeasureValue2.setText(null);
                trackMeasureField2.setVisibility(View.GONE);
                trackMeasureValue3.setText(null);
                trackMeasureField3.setVisibility(View.GONE);
                trackMeasureValue4.setText(null);
                trackMeasureField4.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (rampCounter == 4) {
                trackMeasureValue4.setText(null);
                trackMeasureField4.setVisibility(View.GONE);
            } else if (rampCounter == 3) {
                trackMeasureValue3.setText(null);
                trackMeasureField3.setVisibility(View.GONE);
            } else if (rampCounter == 2) {
                trackMeasureValue2.setText(null);
                trackMeasureField2.setVisibility(View.GONE);
                delTrackRampButton.setVisibility(View.GONE);
            }
            if (rampCounter > 1)
                rampCounter--;
        } else if (v == addTrackGapButton) {
            if (gapCounter < 1) {
                gapCounter = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (gapCounter == 1) {
                delTrackGapButton.setVisibility(View.VISIBLE);
                trackGapField2.setVisibility(View.VISIBLE);
                gapCounter++;
            } else if (gapCounter == 2) {
                trackGapField3.setVisibility(View.VISIBLE);
                gapCounter++;
            } else if (gapCounter == 3) {
                trackGapField4.setVisibility(View.VISIBLE);
                gapCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (v == delTrackGapButton) {
            if (gapCounter < 1) {
                gapCounter = 1;
                delTrackGapButton.setVisibility(View.GONE);
                trackGapValue2.setText(null);
                trackGapField2.setVisibility(View.GONE);
                trackGapValue3.setText(null);
                trackGapField3.setVisibility(View.GONE);
                trackGapValue4.setText(null);
                trackGapField4.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (gapCounter == 4) {
                trackGapValue4.setText(null);
                trackGapField4.setVisibility(View.GONE);
            } else if (gapCounter == 3) {
                trackGapValue3.setText(null);
                trackGapField3.setVisibility(View.GONE);
            } else if (gapCounter == 2) {
                trackGapValue2.setText(null);
                trackGapField2.setVisibility(View.GONE);
                delTrackGapButton.setVisibility(View.GONE);
            }
            if (gapCounter > 1)
                gapCounter--;
        }
    }

    private void playMultiRadioListener(MultiLineRadioGroup multi) {
        int index = multi.getCheckedRadioButtonIndex();
        switch (index) {
            case 1:
                getChildFragmentManager().beginTransaction().replace(R.id.playground_sill_fragment, new SillInclinationFragment()).commit();
                break;
            case 2:
                getChildFragmentManager().beginTransaction().replace(R.id.playground_sill_fragment, new SillStepFragment()).commit();
                break;
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.playground_sill_fragment, new SillSlopeFragment()).commit();
                break;
            default:
                removeSillFragments();
                break;
        }
    }

    private void playRadioListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == playGateTrackRadio) {
            if (index == 1) {
                playTrackHeightField.setVisibility(View.VISIBLE);
                hasTrackRampHeader.setVisibility(View.VISIBLE);
                playGateTrackRampRadio.setVisibility(View.VISIBLE);
                floorGapHeader.setVisibility(View.VISIBLE);
                playFloorGapRadio.setVisibility(View.VISIBLE);
            } else {
                playTrackHeightValue.setText(null);
                playTrackHeightField.setVisibility(View.GONE);
                hasTrackRampHeader.setVisibility(View.GONE);
                playGateTrackRampRadio.clearCheck();
                clearRadioListener(rampTrackFields);
                addTrackRampButton.setVisibility(View.GONE);
                delTrackRampButton.setVisibility(View.GONE);
                rampCounter = 0;
                clearRadioListener(floorGapFields);
                playFloorGapRadio.clearCheck();
                floorGapHeader.setVisibility(View.GONE);
                addTrackGapButton.setVisibility(View.GONE);
                delTrackGapButton.setVisibility(View.GONE);
                gapCounter = 0;
            }
        } else if (radio == playGateTrackRampRadio) {
            if (index == 1) {
                addTrackRampButton.setVisibility(View.VISIBLE);
                trackMeasureField1.setVisibility(View.VISIBLE);
                rampCounter = 1;
            } else {
                clearRadioListener(rampTrackFields);
                addTrackRampButton.setVisibility(View.GONE);
                delTrackRampButton.setVisibility(View.GONE);
                rampCounter = 0;
            }
        } else if (radio == playFloorGapRadio) {
            if (index == 1) {
                addTrackGapButton.setVisibility(View.VISIBLE);
                trackGapField1.setVisibility(View.VISIBLE);
                gapCounter = 1;
            } else {
                clearRadioListener(floorGapFields);
                addTrackGapButton.setVisibility(View.GONE);
                delTrackGapButton.setVisibility(View.GONE);
                gapCounter = 0;
            }
        }

    }

    private void clearRadioListener(ArrayList<TextInputLayout> fieldList) {
        for (TextInputLayout field : fieldList) {
            field.getEditText().setText(null);
            field.setVisibility(View.GONE);
        }
    }

    private void removeSillFragments() {
        Fragment fragment = manager.findFragmentById(R.id.playground_sill_fragment);
        if (fragment != null)
            manager.beginTransaction().remove(fragment).commit();
    }

    private int getPlayCheckRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkEmptyPlayFields() {
        clearPlayErrors();
        int i = 0;
        if (TextUtils.isEmpty(playLocaleValue.getText())) {
            i++;
            playLocaleField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(floorTypeValue.getText())) {
            i++;
            floorTypeField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(playGateWidthValue.getText())) {
            i++;
            playGateWidthField.setError(getString(R.string.req_field_error));
        }
        if (getPlayCheckRadio(playGateTrackRadio) == -1) {
            i++;
            playGateTrackError.setVisibility(View.VISIBLE);
        } else if (getPlayCheckRadio(playGateTrackRadio) == 1) {
            if (TextUtils.isEmpty(playTrackHeightValue.getText())) {
                i++;
                playTrackHeightField.setError(getString(R.string.req_field_error));
            }
            if (getPlayCheckRadio(playGateTrackRampRadio) == -1) {
                i++;
                playTrackRampError.setVisibility(View.VISIBLE);
            } else if (getPlayCheckRadio(playGateTrackRampRadio) == 1) {
                switch (rampCounter) {
                    case 4:
                        if (TextUtils.isEmpty(trackMeasureValue4.getText())) {
                            i++;
                            trackMeasureField4.setError(getString(R.string.req_field_error));
                        }
                    case 3:
                        if (TextUtils.isEmpty(trackMeasureValue3.getText())) {
                            i++;
                            trackMeasureField3.setError(getString(R.string.req_field_error));
                        }
                    case 2:
                        if (TextUtils.isEmpty(trackMeasureValue2.getText())) {
                            i++;
                            trackMeasureField2.setError(getString(R.string.req_field_error));
                        }
                    case 1:
                        if (TextUtils.isEmpty(trackMeasureValue1.getText())) {
                            i++;
                            trackMeasureField1.setError(getString(R.string.req_field_error));
                        }
                        break;
                    default:
                        break;
                }
            }

            if (getPlayCheckRadio(playFloorGapRadio) == -1) {
                i++;
                playFloorGapError.setVisibility(View.VISIBLE);
            } else if (getPlayCheckRadio(playFloorGapRadio) == 1) {
                switch (gapCounter) {
                    case 4:
                        if (TextUtils.isEmpty(trackGapValue4.getText())) {
                            i++;
                            trackGapField4.setError(getString(R.string.req_field_error));
                        }
                    case 3:
                        if (TextUtils.isEmpty(trackGapValue3.getText())) {
                            i++;
                            trackGapField3.setError(getString(R.string.req_field_error));
                        }
                    case 2:
                        if (TextUtils.isEmpty(trackGapValue2.getText())) {
                            i++;
                            trackGapField2.setError(getString(R.string.req_field_error));
                        }
                    case 1:
                        if (TextUtils.isEmpty(trackGapValue1.getText())) {
                            i++;
                            trackGapField1.setError(getString(R.string.req_field_error));
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (gateSillRadio.getCheckedRadioButtonIndex() == -1) {
            i++;
            playSillTypeError.setVisibility(View.VISIBLE);
        }
        if (getPlayCheckRadio(accessibleFloorRadio) == -1) {
            i++;
            playTrackRampError.setVisibility(View.VISIBLE);
        }
        if (getPlayCheckRadio(accessibleToyRadio) == -1) {
            i++;
            playTrackRampError.setVisibility(View.VISIBLE);
        }

        return i <= 0;
    }

    private void clearPlayErrors() {
        trackMeasureField4.setErrorEnabled(false);
        trackMeasureField3.setErrorEnabled(false);
        trackMeasureField2.setErrorEnabled(false);
        trackMeasureField1.setErrorEnabled(false);
        trackGapField4.setErrorEnabled(false);
        trackGapField3.setErrorEnabled(false);
        trackGapField2.setErrorEnabled(false);
        trackGapField1.setErrorEnabled(false);
        playLocaleField.setErrorEnabled(false);
        floorTypeField.setErrorEnabled(false);
        playGateWidthField.setErrorEnabled(false);
        playGateTrackError.setVisibility(View.GONE);
        playTrackHeightField.setErrorEnabled(false);
        playTrackRampError.setVisibility(View.GONE);
        playSillTypeError.setVisibility(View.GONE);
        playTrackRampError.setVisibility(View.GONE);
        playFloorGapError.setVisibility(View.GONE);
    }

    private PlaygroundEntry newPlayground(Bundle bundle) {
        String playLocale, floorType, sillObs, floorObs, toyObs, playObs;
        double playGateWidth;
        Double playTrackHeight = null, measure1 = null, measure2 = null, measure3 = null, measure4 = null, sillStep = null,
                sillSlopeWidth = null, sillSlopeAngle = null, slopeAngle2 = null, slopeAngle3 = null, slopeAngle4 = null, sillInclination = null, sillSlopeHeight = null,
                gap1 = null, gap2 = null, gap3 = null, gap4 = null;
        int hasTrack, sillType, accessibleFloor, accessibleToy;
        Integer hasTrackRamp = null, hasFloorGap = null, slopeQnt = null;

        playLocale = String.valueOf(playLocaleValue.getText());
        floorType = String.valueOf(floorTypeValue.getText());
        playGateWidth = Double.parseDouble(String.valueOf(playGateWidthValue.getText()));
        hasTrack = getPlayCheckRadio(playGateTrackRadio);
        if (getPlayCheckRadio(playGateTrackRadio) == 1) {
            playTrackHeight = Double.valueOf(String.valueOf(playTrackHeightValue.getText()));
            hasTrackRamp = getPlayCheckRadio(playGateTrackRampRadio);
            if (getPlayCheckRadio(playGateTrackRampRadio) == 1) {
                switch (rampCounter) {
                    case 4:
                        measure4 = Double.valueOf(String.valueOf(trackMeasureValue4.getText()));
                    case 3:
                        measure3 = Double.valueOf(String.valueOf(trackMeasureValue3.getText()));
                    case 2:
                        measure2 = Double.valueOf(String.valueOf(trackMeasureValue2.getText()));
                    case 1:
                        measure1 = Double.valueOf(String.valueOf(trackMeasureValue1.getText()));
                    default:
                        break;
                }
            }
            hasFloorGap = getPlayCheckRadio(playFloorGapRadio);
            if (getPlayCheckRadio(playFloorGapRadio) == 1) {
                switch (gapCounter) {
                    case 4:
                        gap4 = Double.valueOf(String.valueOf(trackGapValue4.getText()));
                    case 3:
                        gap3 = Double.valueOf(String.valueOf(trackGapValue3.getText()));
                    case 2:
                        gap2 = Double.valueOf(String.valueOf(trackGapValue2.getText()));
                    case 1:
                        gap1 = Double.valueOf(String.valueOf(trackGapValue1.getText()));
                    default:
                        break;
                }
            }
        }


        sillType = gateSillRadio.getCheckedRadioButtonIndex();
        switch (sillType) {
            case 1:
                sillInclination = bundle.getDouble(HEIGHT_INCLINED_SILL);
                break;
            case 2:
                sillStep = bundle.getDouble(STEP_HEIGHT);
                break;
            case 3:
                slopeQnt = bundle.getInt(SLOPE_QNT);
                switch (slopeQnt) {
                    case 4:
                        slopeAngle4 = bundle.getDouble(SLOPE_ANGLE_4);
                    case 3:
                        slopeAngle3 = bundle.getDouble(SLOPE_ANGLE_3);
                    case 2:
                        slopeAngle2 = bundle.getDouble(SLOPE_ANGLE_2);
                    case 1:
                        sillSlopeAngle = bundle.getDouble(SLOPE_ANGLE_1);
                        sillSlopeWidth = bundle.getDouble(SLOPE_WIDTH);
                        sillSlopeHeight = bundle.getDouble(SLOPE_HEIGHT);
                        break;
                }
                break;
        }
        sillObs = String.valueOf(playGateSillObsValue.getText());
        accessibleFloor = getPlayCheckRadio(accessibleFloorRadio);
        floorObs = String.valueOf(floorObsValue.getText());
        accessibleToy = getPlayCheckRadio(accessibleFloorRadio);
        toyObs = String.valueOf(toyObsValue.getText());
        playObs = String.valueOf(playObsValue.getText());

        return new PlaygroundEntry(bundle.getInt(BLOCK_ID), playLocale, floorType, playGateWidth, hasTrack, playTrackHeight, hasTrackRamp, rampCounter,
                measure1, measure2, measure3, measure4, hasFloorGap, gapCounter, gap1, gap2, gap3, gap4, sillType, sillInclination, sillStep, slopeQnt, sillSlopeAngle,
                slopeAngle2, slopeAngle3, slopeAngle4, sillSlopeWidth, sillSlopeHeight, sillObs, accessibleFloor, floorObs, accessibleToy, toyObs, playObs);
    }

    private void clearPlayFields() {
        playLocaleValue.setText(null);
        floorTypeValue.setText(null);
        playGateWidthValue.setText(null);
        playGateTrackRadio.clearCheck();
        hasTrackRampHeader.setVisibility(View.GONE);
        playGateTrackRampRadio.clearCheck();
        playGateTrackRampRadio.setVisibility(View.GONE);
        addTrackRampButton.setVisibility(View.GONE);
        delTrackRampButton.setVisibility(View.GONE);
        hasTrackRampHeader.setVisibility(View.GONE);
        clearRadioListener(rampTrackFields);
        playFloorGapRadio.clearCheck();
        playFloorGapRadio.setVisibility(View.GONE);
        floorGapHeader.setVisibility(View.GONE);
        clearRadioListener(floorGapFields);
        gateSillRadio.clearCheck();
        removeSillFragments();
        playGateSillObsValue.setText(null);
        accessibleFloorRadio.clearCheck();
        floorObsValue.setText(null);
        accessibleToyRadio.clearCheck();
        toyObsValue.setText(null);
        playObsValue.setText(null);
    }

    private void addRampTrackToArrays() {
        rampTrackFields.add(trackMeasureField1);
        rampTrackFields.add(trackMeasureField2);
        rampTrackFields.add(trackMeasureField3);
        rampTrackFields.add(trackMeasureField4);

        floorGapFields.add(trackGapField1);
        floorGapFields.add(trackGapField2);
        floorGapFields.add(trackGapField3);
        floorGapFields.add(trackGapField4);

        eText.add(floorObsValue);
        eText.add(playObsValue);
        eText.add(toyObsValue);
        eText.add(playGateSillObsValue);
    }

    private void loadPlaygroundData(PlaygroundEntry playEntry) {
        playLocaleValue.setText(playEntry.getPlayLocation());
        floorTypeValue.setText(playEntry.getPlayFloorType());
        playGateWidthValue.setText(String.valueOf(playEntry.getPlayGateWidth()));
        playGateTrackRadio.check(playGateTrackRadio.getChildAt(playEntry.getPlayGateHasFloorTrack()).getId());
        if (playEntry.getPlayGateHasFloorTrack() == 1) {
            playTrackHeightValue.setText(String.valueOf(playEntry.getPlayFloorTrackHeight()));
            playGateTrackRampRadio.check(playGateTrackRampRadio.getChildAt(playEntry.getPlayFloorTrackHasRamp()).getId());
            if (playEntry.getPlayFloorTrackHasRamp() == 1) {
                rampCounter = playEntry.getRampMeasureQnt();
                switch (rampCounter) {
                    case 4:
                        delTrackRampButton.setVisibility(View.VISIBLE);
                        trackMeasureField4.setVisibility(View.VISIBLE);
                        trackMeasureValue4.setText(String.valueOf(playEntry.getRampMeasure4()));
                    case 3:
                        delTrackRampButton.setVisibility(View.VISIBLE);
                        trackMeasureField3.setVisibility(View.VISIBLE);
                        trackMeasureValue3.setText(String.valueOf(playEntry.getRampMeasure3()));
                    case 2:
                        delTrackRampButton.setVisibility(View.VISIBLE);
                        trackMeasureField2.setVisibility(View.VISIBLE);
                        trackMeasureValue2.setText(String.valueOf(playEntry.getRampMeasure2()));
                    case 1:
                        trackMeasureField1.setVisibility(View.VISIBLE);
                        trackMeasureValue1.setText(String.valueOf(playEntry.getRampMeasure1()));
                    default:
                        break;
                }
            }
            playFloorGapRadio.check(playFloorGapRadio.getChildAt(playEntry.getHasFloorGap()).getId());
            if (playEntry.getHasFloorGap() == 1) {
                gapCounter = playEntry.getFloorGapQnt();
                switch (gapCounter) {
                    case 4:
                        delTrackGapButton.setVisibility(View.VISIBLE);
                        trackGapField4.setVisibility(View.VISIBLE);
                        trackGapValue4.setText(String.valueOf(playEntry.getFloorGap4()));
                    case 3:
                        delTrackGapButton.setVisibility(View.VISIBLE);
                        trackGapField3.setVisibility(View.VISIBLE);
                        trackGapValue3.setText(String.valueOf(playEntry.getFloorGap3()));
                    case 2:
                        delTrackGapButton.setVisibility(View.VISIBLE);
                        trackGapField2.setVisibility(View.VISIBLE);
                        trackGapValue2.setText(String.valueOf(playEntry.getFloorGap2()));
                    case 1:
                        trackGapField1.setVisibility(View.VISIBLE);
                        trackGapValue1.setText(String.valueOf(playEntry.getFloorGap1()));
                    default:
                        break;
                }
            }
        }
        if (playEntry.getPlayGateSillType() != null) {
            gateSillRadio.checkAt(playEntry.getPlayGateSillType());
            if (playEntry.getPlayGateSillType() > 0)
                getChildFragmentManager().setFragmentResult(InspectionActivity.LOAD_CHILD_DATA, playBundle);
        }
        if (playEntry.getSillObs() != null)
            playGateSillObsValue.setText(playEntry.getSillObs());
        accessibleFloorRadio.check(accessibleFloorRadio.getChildAt(playEntry.getAccessibleFloor()).getId());
        if (playEntry.getAccessibleFloorObs() != null)
            floorObsValue.setText(playEntry.getAccessibleFloorObs());
        accessibleToyRadio.check(accessibleToyRadio.getChildAt(playEntry.getAccessibleEquip()).getId());
        if (playEntry.getAccessibleEquipObs() != null)
            toyObsValue.setText(playEntry.getAccessibleEquipObs());
        if (playEntry.getPlaygroundObs() != null)
            playObsValue.setText(playEntry.getPlaygroundObs());
    }
}