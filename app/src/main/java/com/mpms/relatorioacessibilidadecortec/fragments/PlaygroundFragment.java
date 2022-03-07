package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillInclinationFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillSlopeFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillStepFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class PlaygroundFragment extends Fragment {

    public static final String PLAY_ID = "PLAY_ID";
    public static final String ALLOW_PLAY_REGISTER = "ALLOW_REGISTER";
    public static final String PLAY_SAVE_ATTEMPT = "PLAY_SAVE_ATTEMPT";
    public static final String PLAY_SILL_DATA = "PLAY_SILL_DATA";

    TextInputLayout playLocaleField, floorTypeField, playGateWidthField, playTrackHeightField, trackMeasureField1, trackMeasureField2,
            trackMeasureField3, trackMeasureField4, playGateSillObsField, floorObsField, toyObsField, playObsField;
    TextInputEditText playLocaleValue, floorTypeValue, playGateWidthValue, playTrackHeightValue, trackMeasureValue1, trackMeasureValue2,
            trackMeasureValue3, trackMeasureValue4, playGateSillObsValue, floorObsValue, toyObsValue, playObsValue;
    RadioGroup playGateTrackRadio, playGateTrackRampRadio, accessibleFloorRadio, accessibleToyRadio;
    MultiLineRadioGroup gateSillRadio;
    MaterialButton addTrackRampButton, savePlay, cancelPlay;
    ImageButton deleteTrackRampButton;
    TextView playGateTrackError, hasTrackRampHeader, playTrackRampError, playRampTrackMeasureError, playSillTypeError, playAccessFloorError, playAccessToyError;

    FragmentManager manager;

    ViewModelEntry modelEntry;

    ViewModelFragments modelFragments;

    Bundle playBundle = new Bundle();
    Bundle childData = new Bundle();

    ArrayList<TextInputLayout> rampTrackFields = new ArrayList<>();

    int rampTrackCounter = 0;

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
            playBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
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
        allowExternalObsScroll();
        modelFragments.setPlaygroundLoadInfo(playBundle);

        if (playBundle.getInt(PLAY_ID) > 0)
            modelEntry.getOnePlayground(playBundle.getInt(PLAY_ID)).observe(getViewLifecycleOwner(), this::loadPlaygroundData);

        addTrackRampButton.setOnClickListener(v -> {
            if (rampTrackCounter < 0) {
                rampTrackCounter = 0;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (rampTrackCounter < 4) {
                if (rampTrackCounter == 0)
                    deleteTrackRampButton.setVisibility(View.VISIBLE);
                rampTrackFields.get(rampTrackCounter).setVisibility(View.VISIBLE);
                rampTrackCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        });

        deleteTrackRampButton.setOnClickListener(v -> {
            if (rampTrackCounter > 0) {
                rampTrackFields.get(rampTrackCounter - 1).getEditText().setText(null);
                rampTrackFields.get(rampTrackCounter - 1).setVisibility(View.GONE);
                rampTrackCounter--;
                if (rampTrackCounter == 0)
                    deleteTrackRampButton.setVisibility(View.GONE);
            }
        });

        savePlay.setOnClickListener(v -> {
            if (gateSillRadio.getCheckedRadioButtonIndex() > 0) {
                getChildFragmentManager().setFragmentResult(PLAY_SAVE_ATTEMPT, childData);
            } else {
                if (checkEmptyPlayFields()) {
                    PlaygroundEntry newPlayEntry = newPlayground(playBundle);
                    if (playBundle.getInt(PLAY_ID) > 0) {
                        newPlayEntry.setPlaygroundID(playBundle.getInt(PLAY_ID));
                        ViewModelEntry.updatePlayground(newPlayEntry);
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStackImmediate();
                    } else {
                        ViewModelEntry.insertPlayground(newPlayEntry);
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                        clearPlayFields();
                    }
                }

            }

        });

        cancelPlay.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        getChildFragmentManager().setFragmentResultListener(PLAY_SILL_DATA, this, (key, bundle) -> {
            if (checkEmptyPlayFields()) {
                bundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, playBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
                PlaygroundEntry newPlayEntry = newPlayground(bundle);
                if (playBundle.getInt(PLAY_ID) > 0) {
                    newPlayEntry.setPlaygroundID(playBundle.getInt(PLAY_ID));
                    ViewModelEntry.updatePlayground(newPlayEntry);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else {
                    ViewModelEntry.insertPlayground(newPlayEntry);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearPlayFields();
                }
            }
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
        playGateSillObsValue = view.findViewById(R.id.playground_sill_obs_value);
        floorObsValue = view.findViewById(R.id.playground_floor_obs_value);
        toyObsValue = view.findViewById(R.id.playground_equips_obs_value);
        playObsValue = view.findViewById(R.id.playground_obs_value);
//        RadioGroup
        playGateTrackRadio = view.findViewById(R.id.has_play_gate_tracks_radio);
        playGateTrackRampRadio = view.findViewById(R.id.gate_has_play_track_ramp_radio);
        accessibleFloorRadio = view.findViewById(R.id.play_has_accessible_floor_radio);
        accessibleToyRadio = view.findViewById(R.id.play_has_accessible_equips_radio);
//        Multiline RadioGroup
        gateSillRadio = view.findViewById(R.id.playground_type_sill_radio);
//        MaterialButton
        addTrackRampButton = view.findViewById(R.id.add_play_gate_track_ramp_button);
        savePlay = view.findViewById(R.id.save_playground);
        cancelPlay = view.findViewById(R.id.cancel_playground);
//        ImageButton
        deleteTrackRampButton = view.findViewById(R.id.delete_play_ramp_track_measure);
//        TextView
        playGateTrackError = view.findViewById(R.id.has_play_gate_tracks_error);
        hasTrackRampHeader = view.findViewById(R.id.label_play_gate_has_track_ramp);
        playTrackRampError = view.findViewById(R.id.has_play_track_ramp_error);
        playRampTrackMeasureError = view.findViewById(R.id.play_ramp_track_values_error);
        playSillTypeError = view.findViewById(R.id.playground_sill_type_error);
        playAccessFloorError = view.findViewById(R.id.play_has_accessible_floor_error);
        playAccessToyError = view.findViewById(R.id.play_has_accessible_equips_error);
//        Fragment Manager
        manager = getChildFragmentManager();
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        Listeners
        playGateTrackRadio.setOnCheckedChangeListener(this::playRadioListener);
        playGateTrackRampRadio.setOnCheckedChangeListener(this::playRadioListener);
        gateSillRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener) (v, r) -> playMultiRadioListener(gateSillRadio));
//        ArrayLists
        addRampTrackToArrays();
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
            } else {
                playTrackHeightValue.setText(null);
                playTrackHeightField.setVisibility(View.GONE);
                hasTrackRampHeader.setVisibility(View.GONE);
                playGateTrackRampRadio.clearCheck();
                playGateTrackRampRadio.setVisibility(View.GONE);
            }
        } else if (radio == playGateTrackRampRadio)
            if (index == 1)
                addTrackRampButton.setVisibility(View.VISIBLE);
            else
                addTrackRampButton.setVisibility(View.GONE);
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
            playLocaleField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(floorTypeValue.getText())) {
            i++;
            floorTypeField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(playGateWidthValue.getText())) {
            i++;
            playGateWidthField.setError(getString(R.string.blank_field_error));
        }
        if (getPlayCheckRadio(playGateTrackRadio) == -1) {
            i++;
            playGateTrackError.setVisibility(View.VISIBLE);
        } else if (getPlayCheckRadio(playGateTrackRadio) == 1) {
            if (TextUtils.isEmpty(playTrackHeightValue.getText())) {
                i++;
                playTrackHeightField.setError(getString(R.string.blank_field_error));
            }
            if (getPlayCheckRadio(playGateTrackRampRadio) == -1) {
                i++;
                playTrackRampError.setVisibility(View.VISIBLE);
            } else if (getPlayCheckRadio(playGateTrackRampRadio) == 1) {
                switch (rampTrackCounter) {
                    case 4:
                        if (TextUtils.isEmpty(trackMeasureValue4.getText())) {
                            i++;
                            playRampTrackMeasureError.setVisibility(View.VISIBLE);
                        }
                    case 3:
                        if (TextUtils.isEmpty(trackMeasureValue3.getText())) {
                            i++;
                            playRampTrackMeasureError.setVisibility(View.VISIBLE);
                        }
                    case 2:
                        if (TextUtils.isEmpty(trackMeasureValue2.getText())) {
                            i++;
                            playRampTrackMeasureError.setVisibility(View.VISIBLE);
                        }
                    case 1:
                        if (TextUtils.isEmpty(trackMeasureValue1.getText())) {
                            i++;
                            playRampTrackMeasureError.setVisibility(View.VISIBLE);
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
        playLocaleField.setErrorEnabled(false);
        floorTypeField.setErrorEnabled(false);
        playGateWidthField.setErrorEnabled(false);
        playGateTrackError.setVisibility(View.GONE);
        playTrackHeightField.setErrorEnabled(false);
        playTrackRampError.setVisibility(View.GONE);
        playRampTrackMeasureError.setVisibility(View.GONE);
        playSillTypeError.setVisibility(View.GONE);
        playTrackRampError.setVisibility(View.GONE);
        playTrackRampError.setVisibility(View.GONE);
    }

    private PlaygroundEntry newPlayground(Bundle bundle) {
        String playLocale, floorType, sillObs, floorObs, toyObs, playObs;
        Double playGateWidth, playTrackHeight = null, measure1 = null, measure2 = null, measure3 = null, measure4 = null, sillStep = null,
                sillSlopeWidth = null, sillSlopeAngle = null, sillInclination = null;
        Integer hasTrack, hasTrackRamp = null, sillType, accessibleFloor, accessibleToy;

        playLocale = String.valueOf(playLocaleValue.getText());
        floorType = String.valueOf(floorTypeValue.getText());
        playGateWidth = Double.valueOf(String.valueOf(playGateWidthValue.getText()));
        hasTrack = getPlayCheckRadio(playGateTrackRadio);
        if (getPlayCheckRadio(playGateTrackRadio) == 1) {
            playTrackHeight = Double.valueOf(String.valueOf(playTrackHeightValue.getText()));
            hasTrackRamp = getPlayCheckRadio(playGateTrackRampRadio);
            if (getPlayCheckRadio(playGateTrackRampRadio) == 1) {
                switch (rampTrackCounter) {
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
        }

        sillType = gateSillRadio.getCheckedRadioButtonIndex();
        switch (sillType) {
            case 1:
                sillInclination = bundle.getDouble(SillInclinationFragment.HEIGHT_INCLINED_SILL);
                break;
            case 2:
                sillStep = bundle.getDouble(SillStepFragment.STEP_HEIGHT);
                break;
            case 3:
                sillSlopeAngle = bundle.getDouble(SillSlopeFragment.SLOPE_INCLINATION);
                sillSlopeWidth = bundle.getDouble(SillSlopeFragment.SLOPE_WIDTH);
                break;
        }
        sillObs = String.valueOf(playGateSillObsValue.getText());
        accessibleFloor = getPlayCheckRadio(accessibleFloorRadio);
        floorObs = String.valueOf(floorObsValue.getText());
        accessibleToy = getPlayCheckRadio(accessibleFloorRadio);
        toyObs = String.valueOf(toyObsValue.getText());
        playObs = String.valueOf(playObsValue.getText());

        return new PlaygroundEntry(bundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER), playLocale, floorType, playGateWidth, hasTrack, playTrackHeight, hasTrackRamp,
                rampTrackCounter, measure1, measure2, measure3, measure4, sillType, sillInclination, sillStep, sillSlopeAngle, sillSlopeWidth, sillObs, accessibleFloor,
                floorObs, accessibleToy, toyObs, playObs);
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
        deleteTrackRampButton.setVisibility(View.GONE);
        trackMeasureValue1.setText(null);
        trackMeasureField1.setVisibility(View.GONE);
        trackMeasureValue2.setText(null);
        trackMeasureField2.setVisibility(View.GONE);
        trackMeasureValue3.setText(null);
        trackMeasureField3.setVisibility(View.GONE);
        trackMeasureValue4.setText(null);
        trackMeasureField4.setVisibility(View.GONE);
        gateSillRadio.clearCheck();
        removeSillFragments();
        playGateSillObsValue.setText(null);
        accessibleFloorRadio.clearCheck();
        floorObsValue.setText(null);
        accessibleToyRadio.clearCheck();
        toyObsValue.setText(null);
        playObsValue.setText(null);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowExternalObsScroll() {
        playGateSillObsValue.setOnTouchListener(this::scrollingField);
        floorObsValue.setOnTouchListener(this::scrollingField);
        toyObsValue.setOnTouchListener(this::scrollingField);
        playObsValue.setOnTouchListener(this::scrollingField);
    }

    private void addRampTrackToArrays() {
        rampTrackFields.add(trackMeasureField1);
        rampTrackFields.add(trackMeasureField2);
        rampTrackFields.add(trackMeasureField3);
        rampTrackFields.add(trackMeasureField4);
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
                rampTrackCounter = playEntry.getRampMeasureQnt();
                deleteTrackRampButton.setVisibility(View.VISIBLE);
                switch (rampTrackCounter) {
                    case 4:
                        trackMeasureField4.setVisibility(View.VISIBLE);
                        trackMeasureValue4.setText(String.valueOf(playEntry.getRampMeasure4()));
                    case 3:
                        trackMeasureField3.setVisibility(View.VISIBLE);
                        trackMeasureValue3.setText(String.valueOf(playEntry.getRampMeasure3()));
                    case 2:
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
        gateSillRadio.checkAt(playEntry.getPlayGateSillType());
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