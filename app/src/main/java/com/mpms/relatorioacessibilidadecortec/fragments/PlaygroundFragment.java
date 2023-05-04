package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.InclinationParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SlopeParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.StepParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillInclinationFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillSlopeFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillStepFragment;
import com.mpms.relatorioacessibilidadecortec.model.InspectionViewModel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import org.parceler.Parcels;

import java.util.ArrayList;

public class PlaygroundFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout playLocaleField, playGateWidthField, playTrackHeightField, trackMeasureField1, trackMeasureField2,
            trackMeasureField3, trackMeasureField4, playGateSillObsField, floorObsField, toyObsField, playObsField,
            trackGapField1, trackGapField2, trackGapField3, trackGapField4, photoField;
    TextInputEditText playLocaleValue, playGateWidthValue, playTrackHeightValue, trackMeasureValue1, trackMeasureValue2,
            trackMeasureValue3, trackMeasureValue4, playGateSillObsValue, floorObsValue, toyObsValue, playObsValue,
            trackGapValue1, trackGapValue2, trackGapValue3, trackGapValue4, photoValue;
    RadioGroup playGateTrackRadio, playGateTrackRampRadio, playFloorGapRadio, accessibleFloorRadio, accessibleToyRadio, playHasGateRadio;
    MultiLineRadioGroup gateSillRadio;
    MaterialButton addTrackRampButton, addTrackGapButton, savePlay, cancelPlay;
    ImageButton delTrackRampButton, delTrackGapButton;
    TextView playGateTrackError, hasTrackRampHeader, playTrackRampError, playSillTypeError, playAccessFloorError, playAccessToyError, playFloorGapError, floorGapHeader,
            playHasGateError, gateTrackHeader, gateSillTypeHeader;
    FrameLayout sillFrame;

    FragmentManager manager;

    ViewModelEntry modelEntry;
    InspectionViewModel dataView;

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

        dataView.setVisible(false);

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
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });
    }

    private void instantiatePlayViews(View view) {
//        TextInputLayout
        playLocaleField = view.findViewById(R.id.location_playground_field);
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
        photoField = view.findViewById(R.id.play_photo_field);
//        TextInputEditText
        playLocaleValue = view.findViewById(R.id.location_playground_value);
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
        photoValue = view.findViewById(R.id.play_photo_value);
//        RadioGroup
        playHasGateRadio = view.findViewById(R.id.has_play_gate_radio);
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
        playHasGateError = view.findViewById(R.id.has_play_gate_error);
        gateTrackHeader = view.findViewById(R.id.label_play_has_gate_tracks);
        playGateTrackError = view.findViewById(R.id.has_play_gate_tracks_error);
        hasTrackRampHeader = view.findViewById(R.id.label_play_gate_has_track_ramp);
        playTrackRampError = view.findViewById(R.id.has_play_track_ramp_error);
        floorGapHeader = view.findViewById(R.id.label_play_has_track_gaps);
        playFloorGapError = view.findViewById(R.id.play_track_gap_radio_error);
        gateSillTypeHeader = view.findViewById(R.id.label_type_sill);
        playSillTypeError = view.findViewById(R.id.playground_sill_type_error);
        playAccessFloorError = view.findViewById(R.id.play_has_accessible_floor_error);
        playAccessToyError = view.findViewById(R.id.play_has_accessible_equips_error);
//        FrameLayout
        sillFrame = view.findViewById(R.id.playground_sill_fragment);
//        Fragment Manager
        manager = getChildFragmentManager();
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        dataView = new ViewModelProvider(requireActivity()).get(InspectionViewModel.class);
//        Listeners
        addTrackGapButton.setOnClickListener(this::addFieldClickListener);
        addTrackRampButton.setOnClickListener(this::addFieldClickListener);
        delTrackGapButton.setOnClickListener(this::addFieldClickListener);
        delTrackRampButton.setOnClickListener(this::addFieldClickListener);
        playHasGateRadio.setOnCheckedChangeListener(this::radioListener);
        playGateTrackRadio.setOnCheckedChangeListener(this::radioListener);
        playFloorGapRadio.setOnCheckedChangeListener(this::radioListener);
        playGateTrackRampRadio.setOnCheckedChangeListener(this::radioListener);
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

    private boolean checkEmptyPlayFields() {
        clearPlayErrors();
        int i = 0;
        if (TextUtils.isEmpty(playLocaleValue.getText())) {
            i++;
            playLocaleField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(playHasGateRadio) == -1) {
            i++;
            playHasGateError.setVisibility(View.VISIBLE);
        } else if (indexRadio(playHasGateRadio) == 1) {
            if (TextUtils.isEmpty(playGateWidthValue.getText())) {
                i++;
                playGateWidthField.setError(getString(R.string.req_field_error));
            }
            if (indexRadio(playGateTrackRadio) == -1) {
                i++;
                playGateTrackError.setVisibility(View.VISIBLE);
            } else if (indexRadio(playGateTrackRadio) == 1) {
                if (TextUtils.isEmpty(playTrackHeightValue.getText())) {
                    i++;
                    playTrackHeightField.setError(getString(R.string.req_field_error));
                }
                if (indexRadio(playGateTrackRampRadio) == -1) {
                    i++;
                    playTrackRampError.setVisibility(View.VISIBLE);
                } else if (indexRadio(playGateTrackRampRadio) == 1) {
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

                if (indexRadio(playFloorGapRadio) == -1) {
                    i++;
                    playFloorGapError.setVisibility(View.VISIBLE);
                } else if (indexRadio(playFloorGapRadio) == 1) {
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
        }

        if (indexRadio(accessibleFloorRadio) == -1) {
            i++;
            playTrackRampError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(accessibleToyRadio) == -1) {
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
        playGateWidthField.setErrorEnabled(false);
        playGateTrackError.setVisibility(View.GONE);
        playTrackHeightField.setErrorEnabled(false);
        playTrackRampError.setVisibility(View.GONE);
        playSillTypeError.setVisibility(View.GONE);
        playTrackRampError.setVisibility(View.GONE);
        playFloorGapError.setVisibility(View.GONE);
        playHasGateError.setVisibility(View.GONE);
    }

    private PlaygroundEntry newPlayground(Bundle bundle) {
        String playLocale, sillObs = null, floorObs = null, toyObs = null, playObs = null, playPhoto = null;
        Double playGateWidth = null, playTrackHeight = null, measure1 = null, measure2 = null, measure3 = null, measure4 = null, stepHeight = null,
                sillSlopeWidth = null, sillSlopeAngle = null, slopeAngle2 = null, slopeAngle3 = null, slopeAngle4 = null, inclHeight = null, inclAngle1 = null,
                inclAngle2 = null, inclAngle3 = null, inclAngle4 = null, slopeHeight = null,
                gap1 = null, gap2 = null, gap3 = null, gap4 = null;
        int hasGate, accessibleFloor, accessibleToy;
        Integer hasTrack = null, sillType = null, hasTrackRamp = null, hasFloorGap = null, slopeQnt = null, hasIncl = null, inclQnt = null;

        playLocale = String.valueOf(playLocaleValue.getText());
        hasGate = indexRadio(playHasGateRadio);
        if (hasGate == 1) {
            playGateWidth = Double.parseDouble(String.valueOf(playGateWidthValue.getText()));
            hasTrack = indexRadio(playGateTrackRadio);
            if (hasTrack == 1) {
                playTrackHeight = Double.valueOf(String.valueOf(playTrackHeightValue.getText()));
                hasTrackRamp = indexRadio(playGateTrackRampRadio);
                if (hasTrackRamp == 1) {
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
                hasFloorGap = indexRadio(playFloorGapRadio);
                if (hasFloorGap == 1) {
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

                sillType = gateSillRadio.getCheckedRadioButtonIndex();
                if (sillType == 1) {
                    InclinationParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

                    inclHeight = parcel.getInclHeight();
                    hasIncl = parcel.getHasInclSlope();
                    if (hasIncl == 1) {
                        inclQnt = parcel.getInclQnt();
                        if (parcel.getInclMeasure4() != null)
                            inclAngle4 = parcel.getInclMeasure4();
                        if (parcel.getInclMeasure3() != null)
                            inclAngle3 = parcel.getInclMeasure3();
                        if (parcel.getInclMeasure2() != null)
                            inclAngle2 = parcel.getInclMeasure2();
                        if (parcel.getInclMeasure1() != null)
                            inclAngle1 = parcel.getInclMeasure1();
                    }
                } else if (sillType == 2) {
                    StepParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
                    stepHeight = parcel.getStepHeight();
                }
                else if (sillType == 3) {
                    SlopeParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
                    slopeHeight = parcel.getSillSlopeHeight();
                    sillSlopeWidth = parcel.getSillSlopeWidth();
                    slopeQnt = parcel.getSillSlopeQnt();
                    if (parcel.getSillSlopeAngle4() != null)
                        inclAngle4 = parcel.getSillSlopeAngle4();
                    if (parcel.getSillSlopeAngle3() != null)
                        inclAngle3 = parcel.getSillSlopeAngle3();
                    if (parcel.getSillSlopeAngle2() != null)
                        inclAngle2 = parcel.getSillSlopeAngle2();
                    if (parcel.getSillSlopeAngle1() != null)
                        inclAngle1 = parcel.getSillSlopeAngle1();
                }

            }
        }
        if (!TextUtils.isEmpty(playGateSillObsValue.getText()))
            sillObs = String.valueOf(playGateSillObsValue.getText());

        accessibleFloor = indexRadio(accessibleFloorRadio);
        if (!TextUtils.isEmpty(floorObsValue.getText()))
            floorObs = String.valueOf(floorObsValue.getText());
        accessibleToy = indexRadio(accessibleFloorRadio);
        if (!TextUtils.isEmpty(toyObsValue.getText()))
            toyObs = String.valueOf(toyObsValue.getText());
        if (!TextUtils.isEmpty(playObsValue.getText()))
            playObs = String.valueOf(playObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            playPhoto = String.valueOf(photoValue.getText());

        return new PlaygroundEntry(bundle.getInt(BLOCK_ID), playLocale, hasGate, playGateWidth, hasTrack, playTrackHeight, hasTrackRamp, rampCounter, measure1, measure2,
                measure3, measure4, hasFloorGap, gapCounter, gap1, gap2, gap3, gap4, sillType, inclHeight, inclQnt, inclAngle1, inclAngle2, inclAngle3, inclAngle4, stepHeight,
                slopeQnt, sillSlopeAngle, slopeAngle2, slopeAngle3, slopeAngle4, sillSlopeWidth, slopeHeight, sillObs, accessibleFloor, floorObs, accessibleToy, toyObs, playObs,
                playPhoto, hasIncl);
    }

    private void clearPlayFields() {
        playLocaleValue.setText(null);
        playHasGateRadio.clearCheck();
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
        photoValue.setText(null);
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
        if (playEntry.getPlayLocation() != null)
            playLocaleValue.setText(playEntry.getPlayLocation());
        if (playEntry.getPlayHasGate() != null) {
            checkRadioGroup(playHasGateRadio, playEntry.getPlayHasGate());
            if (playEntry.getPlayHasGate() == 1) {
                if (playEntry.getPlayGateWidth() != null)
                    playGateWidthValue.setText(String.valueOf(playEntry.getPlayGateWidth()));
                if (playEntry.getGateHasFloorTrack() != null) {
                    checkRadioGroup(playGateTrackRadio, playEntry.getGateHasFloorTrack());
                    if (playEntry.getGateHasFloorTrack() == 1) {
                        if (playEntry.getFloorTrackHeight() != null)
                            playTrackHeightValue.setText(String.valueOf(playEntry.getFloorTrackHeight()));
                        if (playEntry.getFloorTrackHasRamp() != null) {
                            checkRadioGroup(playGateTrackRampRadio, playEntry.getFloorTrackHasRamp());
                            if (playEntry.getFloorTrackHasRamp() == 1) {
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
                        }
                        if (playEntry.getHasFloorGap() != null) {
                            checkRadioGroup(playFloorGapRadio, playEntry.getHasFloorGap());
                            if (playEntry.getHasFloorGap() == 1) {
                                if (playEntry.getFloorGapQnt() != null) {
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
                                        default:
                                            trackGapField1.setVisibility(View.VISIBLE);
                                            trackGapValue1.setText(String.valueOf(playEntry.getFloorGap1()));
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }

                if (playEntry.getGateSillType() != null) {
                    gateSillRadio.checkAt(playEntry.getGateSillType());
                    if (playEntry.getGateSillType() > 0)
                        getChildFragmentManager().setFragmentResult(InspectionActivity.LOAD_CHILD_DATA, playBundle);
                }
                if (playEntry.getSillObs() != null)
                    playGateSillObsValue.setText(playEntry.getSillObs());
            }
        }
        if (playEntry.getAccessibleFloor() != null)
            checkRadioGroup(accessibleFloorRadio, playEntry.getAccessibleFloor());
        if (playEntry.getAccessFloorObs() != null)
            floorObsValue.setText(playEntry.getAccessFloorObs());
        if (playEntry.getAccessibleEquip() != null)
            checkRadioGroup(accessibleToyRadio, playEntry.getAccessibleEquip());
        if (playEntry.getAccessEquipObs() != null)
            toyObsValue.setText(playEntry.getAccessEquipObs());
        if (playEntry.getPlaygroundObs() != null)
            playObsValue.setText(playEntry.getPlaygroundObs());
        if (playEntry.getPlayPhotoNumber() != null)
            photoValue.setText(playEntry.getPlayPhotoNumber());
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
            int index = indexRadio(radio);
            if (radio == playHasGateRadio) {
                if (index == 1) {
                    playGateWidthField.setVisibility(View.VISIBLE);
                    gateTrackHeader.setVisibility(View.VISIBLE);
                    playGateTrackRadio.setVisibility(View.VISIBLE);
                    gateSillTypeHeader.setVisibility(View.VISIBLE);
                    gateSillRadio.setVisibility(View.VISIBLE);
                    sillFrame.setVisibility(View.VISIBLE);
                    playGateSillObsField.setVisibility(View.VISIBLE);
                } else {
                    playGateWidthValue.setText(null);
                    playGateWidthField.setVisibility(View.GONE);
                    gateTrackHeader.setVisibility(View.GONE);
                    playTrackHeightValue.setText(null);
                    playTrackHeightField.setVisibility(View.GONE);
                    playGateTrackRadio.clearCheck();
                    playGateTrackRadio.setVisibility(View.GONE);
                    gateSillTypeHeader.setVisibility(View.GONE);
                    gateSillRadio.clearCheck();
                    gateSillRadio.setVisibility(View.GONE);
                    removeSillFragments();
                    sillFrame.setVisibility(View.GONE);
                    playGateSillObsValue.setText(null);
                    playGateSillObsField.setVisibility(View.GONE);
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
            } else if (radio == playGateTrackRadio) {
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
}