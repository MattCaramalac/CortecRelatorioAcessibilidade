package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class ExtAccessSocialFragment extends Fragment {

    public static final String TEMP_SOCIAL_FRAG = "TEMP_SOCIAL_FRAG";
    public static final String TEMP_FRAG_DATA = "TEMP_FRAG_DATA";
    public static final String CHILD_TEMP_DATA = "CHILD_TEMP_DATA";

    RadioGroup hasSIARadio, hasGateTracksRadio, hasTrackRampRadio, hasObstaclesRadio, hasPayphoneRadio,
            hasIntercomRadio;
    MultiLineRadioGroup sillTypeRadio;
    TextView siaError, gateTrackError, hasTrackRampHeader, trackRampError, sillTypeError, obstaclesError, payphoneError,
            intercomError, trackRampValueError;
    TextInputLayout siaObsField, floorTypeField, gateWidthField, trackHeightField, sillObsField, intercomHeightField,
        trackRampField1, trackRampField2, trackRampField3, trackRampField4;
    TextInputEditText siaObsValue, floorTypeValue, gateWidthValue, trackHeightValue, sillObsValue, intercomHeightValue,
            trackRampValue1, trackRampValue2, trackRampValue3, trackRampValue4;
    MaterialButton addTrackRampButton, addObstaclesButton, addPayphoneButton;
    ImageButton delTrackRampButton;
    FrameLayout sillFragment;

    ViewModelEntry modelEntry;

    ViewModelFragments modelFragments;

    FragmentManager manager;

    int rampTrackCounter = 0;

    ArrayList<String> socialFrag = new ArrayList<>();
    ArrayList<String> tempSocialFrag = new ArrayList<>();
    ArrayList<TextInputLayout> rampTrackFields = new ArrayList<>();

    Bundle extAccessDataInfo = new Bundle();

    public ExtAccessSocialFragment() {
        // Required empty public constructor
    }

    public static ExtAccessSocialFragment newInstance() {
        return new ExtAccessSocialFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            extAccessDataInfo.putInt(ExternalAccessFragment.EXT_ACCESS_ID, this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ext_access_social, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //    TODO - Implementar contadores de registros, impedir gravação sem ao menos um registro salvo (quando marcado sim)
        super.onViewCreated(view, savedInstanceState);
        instantiateSocialViews(view);

        if (extAccessDataInfo != null && extAccessDataInfo.getInt(ExternalAccessFragment.EXT_ACCESS_ID) != 0) {
            modelEntry.getOneExternalAccess(extAccessDataInfo.getInt(ExternalAccessFragment.EXT_ACCESS_ID))
                    .observe(getViewLifecycleOwner(), this::loadSocialFragData);
        }

        getParentFragmentManager().setFragmentResultListener(ExternalAccessFragment.EXT_ACCESS_SAVE_ATTEMPT, this, (key, bundle) -> {
            if (sillTypeRadio.getCheckedRadioButtonIndex() > 0) {
                getChildFragmentManager().setFragmentResult(ExternalAccessFragment.EXT_ACCESS_SAVE_ATTEMPT, bundle);
            } else {
                socialFrag = bundle.getStringArrayList(ExternalAccessFragment.EXT_ARRAY);
                if (!socialFragDoesNotHaveEmptyFields())
                    socialFrag.set(18, "false");
                bundle.putStringArrayList(ExternalAccessFragment.CHILD_FRAG_DATA, socialFrag);
                getParentFragmentManager().setFragmentResult(ExternalAccessFragment.FRAG_DATA, bundle);

            }
        });

        getChildFragmentManager().setFragmentResultListener(ExternalAccessFragment.FRAG_DATA, this, (key, bundle) -> {
            socialFrag = bundle.getStringArrayList(ExternalAccessFragment.EXT_ARRAY);
            if (socialFragDoesNotHaveEmptyFields()) {
                bundle.putStringArrayList(ExternalAccessFragment.CHILD_FRAG_DATA, socialFrag);
                getParentFragmentManager().setFragmentResult(ExternalAccessFragment.FRAG_DATA, bundle);
            }
        });

        getChildFragmentManager().setFragmentResultListener(CHILD_TEMP_DATA, this, (key, bundle) ->
                getParentFragmentManager().setFragmentResult(TEMP_SOCIAL_FRAG, bundle)
        );

        addTrackRampButton.setOnClickListener(v -> {
            if (rampTrackCounter < 0) {
                rampTrackCounter = 0;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (rampTrackCounter < 4) {
                if (rampTrackCounter == 0)
                    delTrackRampButton.setVisibility(View.VISIBLE);
                rampTrackFields.get(rampTrackCounter).setVisibility(View.VISIBLE);
                rampTrackCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        });

        delTrackRampButton.setOnClickListener(v -> {
            if (rampTrackCounter > 0) {
                rampTrackFields.get(rampTrackCounter - 1).getEditText().setText(null);
                rampTrackFields.get(rampTrackCounter - 1).setVisibility(View.GONE);
                rampTrackCounter--;
                if (rampTrackCounter == 0)
                    delTrackRampButton.setVisibility(View.GONE);
            }
        });

        addObstaclesButton.setOnClickListener(v -> addButtonClicked(1));

        addPayphoneButton.setOnClickListener(v -> addButtonClicked(2));

    }

    private void addButtonClicked(int i) {
        tempSocialFrag = gatherSocialData(i);
        Bundle tempSocialDataBundle = new Bundle();
        tempSocialDataBundle.putStringArrayList(TEMP_FRAG_DATA, tempSocialFrag);
        if (sillTypeRadio.getCheckedRadioButtonIndex() > 0)
            getChildFragmentManager().setFragmentResult(TEMP_SOCIAL_FRAG, tempSocialDataBundle);
        else
            getParentFragmentManager().setFragmentResult(TEMP_SOCIAL_FRAG, tempSocialDataBundle);
    }

    private void instantiateSocialViews(View v) {
//        RadioGroups
        hasSIARadio = v.findViewById(R.id.has_SIA_radio);
        hasGateTracksRadio = v.findViewById(R.id.has_gate_tracks_radio);
        hasTrackRampRadio = v.findViewById(R.id.gate_has_track_ramp_radio);
        hasObstaclesRadio = v.findViewById(R.id.gate_has_obstacles_radio);
        hasPayphoneRadio = v.findViewById(R.id.gate_has_payphones_radio);
        hasIntercomRadio = v.findViewById(R.id.gate_has_intercom_radio);
//        MultilineRadioGroup
        sillTypeRadio = v.findViewById(R.id.type_sill_radio);
//        TextViews
        siaError = v.findViewById(R.id.has_SIA_error);
        gateTrackError = v.findViewById(R.id.has_gate_tracks_error);
        hasTrackRampHeader = v.findViewById(R.id.label_gate_has_track_ramp);
        trackRampError = v.findViewById(R.id.has_track_ramp_error);
        sillTypeError = v.findViewById(R.id.sill_type_error);
        obstaclesError = v.findViewById(R.id.gate_has_obstacles_error);
        payphoneError = v.findViewById(R.id.gate_has_payphones_error);
        intercomError = v.findViewById(R.id.gate_has_intercom_error);
        trackRampValueError = v.findViewById(R.id.ramp_track_values_error);
//        TextInputLayouts
        siaObsField = v.findViewById(R.id.social_entrance_sia_obs_field);
        floorTypeField = v.findViewById(R.id.floor_type_ext_field);
        gateWidthField = v.findViewById(R.id.gate_width_ext_field);
        trackHeightField = v.findViewById(R.id.ext_tracks_height_field);
        sillObsField = v.findViewById(R.id.gate_sill_obs_field);
        intercomHeightField = v.findViewById(R.id.intercom_height_field);
        trackRampField1 = v.findViewById(R.id.ramp_track_1_field);
        trackRampField2 = v.findViewById(R.id.ramp_track_2_field);
        trackRampField3 = v.findViewById(R.id.ramp_track_3_field);
        trackRampField4 = v.findViewById(R.id.ramp_track_4_field);
//        TextInputEditText
        siaObsValue = v.findViewById(R.id.social_entrance_sia_obs_value);
        floorTypeValue = v.findViewById(R.id.floor_type_ext_value);
        gateWidthValue = v.findViewById(R.id.gate_width_ext_value);
        trackHeightValue = v.findViewById(R.id.ext_tracks_height_value);
        sillObsValue = v.findViewById(R.id.gate_sill_obs_value);
        intercomHeightValue = v.findViewById(R.id.intercom_height_value);
        trackRampValue1 = v.findViewById(R.id.ramp_track_1_value);
        trackRampValue2 = v.findViewById(R.id.ramp_track_2_value);
        trackRampValue3 = v.findViewById(R.id.ramp_track_3_value);
        trackRampValue4 = v.findViewById(R.id.ramp_track_4_value);
//        MaterialButtons
        addTrackRampButton = v.findViewById(R.id.add_gate_track_ramp_button);
        addObstaclesButton = v.findViewById(R.id.add_gate_obstacles_button);
        addPayphoneButton = v.findViewById(R.id.add_gate_payphones_button);
//        ImageButton
        delTrackRampButton = v.findViewById(R.id.delete_ramp_track_measure);
//        FrameLayout
        sillFragment = v.findViewById(R.id.ext_access_sill_fragment);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        Fragment Manager
        manager = getChildFragmentManager();
//        Obs Fields Scrolling
        allowObsScroll();
//        RadioListeners
        hasGateTracksRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasTrackRampRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasObstaclesRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasPayphoneRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasIntercomRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
//        MultiRadio Listeners
        sillTypeRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (view, r) -> extAccessMultiRadioListener(sillTypeRadio));
//        ArrayList
        addRampTrackToArrays();
    }

    private void addRampTrackToArrays() {
        rampTrackFields.add(trackRampField1);
        rampTrackFields.add(trackRampField2);
        rampTrackFields.add(trackRampField3);
        rampTrackFields.add(trackRampField4);
    }

    private void extAccessMultiRadioListener(MultiLineRadioGroup multi) {
        int index = multi.getCheckedRadioButtonIndex();
        switch (index) {
            case 1:
                getChildFragmentManager().beginTransaction().replace(R.id.ext_access_sill_fragment, new SillInclinationFragment()).commit();
                break;
            case 2:
                getChildFragmentManager().beginTransaction().replace(R.id.ext_access_sill_fragment, new SillStepFragment()).commit();
                break;
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.ext_access_sill_fragment, new SillSlopeFragment()).commit();
                break;
            default:
                removeSillFragments();
                break;
        }
    }

    private void extAccessRadioListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == hasGateTracksRadio) {
            if (index == 1) {
                trackHeightField.setVisibility(View.VISIBLE);
                hasTrackRampHeader.setVisibility(View.VISIBLE);
                hasTrackRampRadio.setVisibility(View.VISIBLE);
            } else {
                trackHeightValue.setText(null);
                trackHeightField.setVisibility(View.GONE);
                hasTrackRampHeader.setVisibility(View.GONE);
                hasTrackRampRadio.clearCheck();
                hasTrackRampRadio.setVisibility(View.GONE);
            }
        } else if (radio == hasTrackRampRadio) {
            if (index == 1)
                addTrackRampButton.setVisibility(View.VISIBLE);
            else
                addTrackRampButton.setVisibility(View.GONE);
        } else if (radio == hasObstaclesRadio) {
            if (index == 1)
                addObstaclesButton.setVisibility(View.VISIBLE);
            else
                addObstaclesButton.setVisibility(View.GONE);
        } else if (radio == hasPayphoneRadio) {
            if (index == 1)
                addPayphoneButton.setVisibility(View.VISIBLE);
            else
                addPayphoneButton.setVisibility(View.GONE);
        } else if (radio == hasIntercomRadio) {
            if (index == 1)
                intercomHeightField.setVisibility(View.VISIBLE);
            else {
                intercomHeightValue.setText(null);
                intercomHeightField.setVisibility(View.GONE);
            }
        }
    }

    private void loadSocialFragData(ExternalAccess access) {
        if (access.getHasSIA() != null)
            hasSIARadio.check(hasSIARadio.getChildAt(access.getHasSIA()).getId());
        if (access.getObsSIA() != null)
            siaObsValue.setText(access.getObsSIA());
        if (access.getFloorType() != null)
            floorTypeValue.setText(access.getFloorType());
        if (access.getGateWidth() != null)
            gateWidthValue.setText(String.valueOf(access.getGateWidth()));
        if (access.getGateHasTracks() != null) {
            hasGateTracksRadio.check(hasGateTracksRadio.getChildAt(access.getGateHasTracks()).getId());
            if (access.getGateHasTracks() == 1) {
                if (access.getGateTrackHeight() != null)
                    trackHeightValue.setText(String.valueOf(access.getGateTrackHeight()));
                if (access.getGateHasTrackRamp() != null) {
                    hasTrackRampRadio.check(hasTrackRampRadio.getChildAt(access.getGateHasTrackRamp()).getId());
                    rampTrackCounter = access.getTrackRampQuantity();
                    if (rampTrackCounter > 0)
                        delTrackRampButton.setVisibility(View.VISIBLE);
                    switch (rampTrackCounter) {
                        case 4:
                            trackRampField4.setVisibility(View.VISIBLE);
                            trackRampValue4.setText(String.valueOf(access.getTrackRampMeasure4()));
                        case 3:
                            trackRampField3.setVisibility(View.VISIBLE);
                            trackRampValue3.setText(String.valueOf(access.getTrackRampMeasure3()));
                        case 2:
                            trackRampField2.setVisibility(View.VISIBLE);
                            trackRampValue2.setText(String.valueOf(access.getTrackRampMeasure2()));
                        case 1:
                            trackRampField1.setVisibility(View.VISIBLE);
                            trackRampValue1.setText(String.valueOf(access.getTrackRampMeasure1()));
                    }
                }
            }
        }

        if (access.getGateSillType() != null)
            sillTypeRadio.checkAt(access.getGateSillType());
        if (access.getGateSillObs() != null)
            sillObsValue.setText(access.getGateSillObs());
        if (access.getGateHasObstacles() != null)
            hasObstaclesRadio.check(hasObstaclesRadio.getChildAt(access.getGateHasObstacles()).getId());
        if (access.getGateHasPayphones() != null)
            hasPayphoneRadio.check(hasPayphoneRadio.getChildAt(access.getGateHasPayphones()).getId());
        if (access.getGateHasIntercom() != null) {
            hasIntercomRadio.check(hasIntercomRadio.getChildAt(access.getGateHasIntercom()).getId());
            if (access.getGateHasIntercom() == 1)
                if (access.getIntercomHeight() != null)
                    intercomHeightValue.setText(String.valueOf(access.getIntercomHeight()));
        }
    }

    private void removeSillFragments() {
        Fragment fragment = manager.findFragmentById(R.id.ext_access_sill_fragment);
        if (fragment != null)
            manager.beginTransaction().remove(fragment).commit();
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private ArrayList<String> gatherSocialData(int i) {
        ArrayList<String> tempSocialData = new ArrayList<>(Arrays.
                asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null));
        if (hasSIARadio.getCheckedRadioButtonId() != -1)
            tempSocialData.set(0, String.valueOf(getCheckedRadioIndex(hasSIARadio)));
        if (siaObsValue.getText() != null)
            tempSocialData.set(1, String.valueOf(siaObsValue.getText()));
        if (!TextUtils.isEmpty(floorTypeValue.getText()))
            tempSocialData.set(2, String.valueOf(floorTypeValue.getText()));
        if (!TextUtils.isEmpty(gateWidthValue.getText()))
            tempSocialData.set(3, String.valueOf(gateWidthValue.getText()));
        if (hasGateTracksRadio.getCheckedRadioButtonId() != -1)
            tempSocialData.set(4, String.valueOf(getCheckedRadioIndex(hasGateTracksRadio)));
        if (getCheckedRadioIndex(hasGateTracksRadio) == 1) {
            if (!TextUtils.isEmpty(trackHeightValue.getText()))
                tempSocialData.set(5, String.valueOf(trackHeightValue.getText()));
            if (getCheckedRadioIndex(hasTrackRampRadio) != -1)
                tempSocialData.set(6, String.valueOf(getCheckedRadioIndex(hasTrackRampRadio)));
            tempSocialData.set(20, String.valueOf(rampTrackCounter));
            if (!TextUtils.isEmpty(trackRampValue1.getText()))
                tempSocialData.set(21, String.valueOf(trackRampValue1.getText()));
            if (!TextUtils.isEmpty(trackRampValue2.getText()))
                tempSocialData.set(22, String.valueOf(trackRampValue2.getText()));
            if (!TextUtils.isEmpty(trackRampValue3.getText()))
                tempSocialData.set(23, String.valueOf(trackRampValue3.getText()));
            if (!TextUtils.isEmpty(trackRampValue4.getText()))
                tempSocialData.set(24, String.valueOf(trackRampValue4.getText()));
        }
        if (sillTypeRadio.getCheckedRadioButtonIndex() != -1)
            tempSocialData.set(7, String.valueOf(sillTypeRadio.getCheckedRadioButtonIndex()));
        if (sillObsValue.getText() != null)
            tempSocialData.set(12, String.valueOf(sillObsValue.getText()));
        if (hasObstaclesRadio.getCheckedRadioButtonId() != -1)
            tempSocialData.set(13, String.valueOf(getCheckedRadioIndex(hasObstaclesRadio)));
        if (hasPayphoneRadio.getCheckedRadioButtonId() != -1)
            tempSocialData.set(14, String.valueOf(getCheckedRadioIndex(hasPayphoneRadio)));
        if (hasIntercomRadio.getCheckedRadioButtonId() != -1)
            tempSocialData.set(15, String.valueOf(getCheckedRadioIndex(hasIntercomRadio)));
        if (getCheckedRadioIndex(hasIntercomRadio) == 1) {
            if (!TextUtils.isEmpty(intercomHeightValue.getText()))
                tempSocialData.set(16, String.valueOf(intercomHeightValue.getText()));
        }
        tempSocialData.set(19, String.valueOf(i));
        return tempSocialData;
    }

    private boolean socialFragDoesNotHaveEmptyFields() {
        clearSocialFragErrors();
        int i = 0;

        if (hasSIARadio.getCheckedRadioButtonId() == -1) {
            i++;
            siaError.setVisibility(View.VISIBLE);
        } else
            socialFrag.set(0, String.valueOf(getCheckedRadioIndex(hasSIARadio)));
        socialFrag.set(1, String.valueOf(siaObsValue.getText()));
        if (TextUtils.isEmpty(floorTypeValue.getText())) {
            i++;
            floorTypeField.setError(getString(R.string.blank_field_error));
        } else
            socialFrag.set(2, String.valueOf(floorTypeValue.getText()));
        if (TextUtils.isEmpty(gateWidthValue.getText())) {
            i++;
            gateWidthField.setError(getString(R.string.blank_field_error));
        } else
            socialFrag.set(3, String.valueOf(gateWidthValue.getText()));
        if (hasGateTracksRadio.getCheckedRadioButtonId() == -1) {
            i++;
            gateTrackError.setVisibility(View.VISIBLE);
        } else
            socialFrag.set(4, String.valueOf(getCheckedRadioIndex(hasGateTracksRadio)));
        if (getCheckedRadioIndex(hasGateTracksRadio) == 1) {
            if (TextUtils.isEmpty(trackHeightValue.getText())) {
                i++;
                trackHeightField.setError(getString(R.string.blank_field_error));
            } else
                socialFrag.set(5, String.valueOf(trackHeightValue.getText()));
            if (getCheckedRadioIndex(hasTrackRampRadio) == -1) {
                i++;
                trackRampError.setVisibility(View.VISIBLE);
            } else
                socialFrag.set(6, String.valueOf(getCheckedRadioIndex(hasTrackRampRadio)));
        }
        socialFrag.set(20, String.valueOf(rampTrackCounter));
        if (TextUtils.isEmpty(trackRampValue1.getText()) && rampTrackCounter >= 1) {
            i++;
            trackRampValueError.setVisibility(View.VISIBLE);
        } else
            if (trackRampValue1.getText() != null)
                socialFrag.set(21, String.valueOf(trackRampValue1.getText()));
        if (TextUtils.isEmpty(trackRampValue2.getText()) && rampTrackCounter >= 2) {
            i++;
            trackRampValueError.setVisibility(View.VISIBLE);
        } else
            if (trackRampValue2.getText() != null)
                socialFrag.set(22, String.valueOf(trackRampValue2.getText()));
        if (TextUtils.isEmpty(trackRampValue3.getText()) && rampTrackCounter >= 3) {
            i++;
            trackRampValueError.setVisibility(View.VISIBLE);
        } else
            if (trackRampValue3.getText() != null)
                socialFrag.set(23, String.valueOf(trackRampValue3.getText()));
        if (TextUtils.isEmpty(trackRampValue4.getText()) && rampTrackCounter == 4) {
            i++;
            trackRampValueError.setVisibility(View.VISIBLE);
        } else
            if (trackRampValue4.getText() != null)
                socialFrag.set(24, String.valueOf(trackRampValue4.getText()));
        if (sillTypeRadio.getCheckedRadioButtonIndex() == -1) {
            i++;
            sillTypeError.setVisibility(View.VISIBLE);
        } else
            socialFrag.set(7, String.valueOf(sillTypeRadio.getCheckedRadioButtonIndex()));
        socialFrag.set(12, String.valueOf(sillObsValue.getText()));
        if (hasObstaclesRadio.getCheckedRadioButtonId() == -1) {
            i++;
            obstaclesError.setVisibility(View.VISIBLE);
        } else
            socialFrag.set(13, String.valueOf(getCheckedRadioIndex(hasObstaclesRadio)));
        if (hasPayphoneRadio.getCheckedRadioButtonId() == -1) {
            i++;
            payphoneError.setVisibility(View.VISIBLE);
        } else
            socialFrag.set(14, String.valueOf(getCheckedRadioIndex(hasPayphoneRadio)));
        if (hasIntercomRadio.getCheckedRadioButtonId() == -1) {
            i++;
            intercomError.setVisibility(View.VISIBLE);
        } else
            socialFrag.set(15, String.valueOf(getCheckedRadioIndex(hasIntercomRadio)));
        if (getCheckedRadioIndex(hasIntercomRadio) == 1) {
            if (TextUtils.isEmpty(intercomHeightValue.getText())) {
                i++;
                intercomHeightField.setError(getString(R.string.blank_field_error));
            } else
                socialFrag.set(16, String.valueOf(intercomHeightValue.getText()));
        }
        return i == 0;
    }

    private void clearSocialFragErrors() {
        siaError.setVisibility(View.GONE);
        floorTypeField.setErrorEnabled(false);
        gateWidthField.setErrorEnabled(false);
        gateTrackError.setVisibility(View.GONE);
        trackHeightField.setErrorEnabled(false);
        trackRampError.setVisibility(View.GONE);
        sillTypeError.setVisibility(View.GONE);
        obstaclesError.setVisibility(View.GONE);
        payphoneError.setVisibility(View.GONE);
        intercomError.setVisibility(View.GONE);
        intercomHeightField.setErrorEnabled(false);
        trackRampError.setVisibility(View.GONE);
    }

    private int getCheckedRadioIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowObsScroll() {
        siaObsValue.setOnTouchListener(this::scrollingField);
        sillObsValue.setOnTouchListener(this::scrollingField);
    }
}