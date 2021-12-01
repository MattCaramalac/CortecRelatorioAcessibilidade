package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.SillInclinationFragment;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.SillSlopeFragment;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.SillStepFragment;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment.EXT_ACCESS_SAVE_ATTEMPT;

public class ExtAccessSocialFragment extends Fragment {

    public static final String SOCIAL_FRAG = "SOCIAL_FRAG";
    public static final String  FRAG_DATA = "FRAG_DATA";

    RadioGroup hasSIARadio, hasGateTracksRadio, hasTrackRampRadio,  hasObstaclesRadio, hasPayphoneRadio,
            hasIntercomRadio;
    MultiLineRadioGroup sillTypeRadio;
    TextView siaError, gateTrackError, hasTrackRampHeader, trackRampError, sillTypeError, obstaclesError, payphoneError, intercomError;
    TextInputLayout siaObsField, floorTypeField, gateWidthField, trackHeightField, sillObsField, intercomHeightField;
    TextInputEditText siaObsValue, floorTypeValue, gateWidthValue, trackHeightValue, sillObsValue, intercomHeightValue;
    MaterialButton addTrackRampButton, addObstaclesButton, addPayphoneButton;
    FrameLayout sillFragment;

    ViewModelFragments modelFragments;

    FragmentManager manager;

    ArrayList<String> socialFrag = new ArrayList<>(Arrays.
            asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));

    public ExtAccessSocialFragment() {
        // Required empty public constructor
    }

    public static ExtAccessSocialFragment newInstance() {
        return new ExtAccessSocialFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//        FragmentManager agora possui "Result Listener", não necessitando mais do VM para poder passar dados
        getParentFragmentManager().setFragmentResultListener(EXT_ACCESS_SAVE_ATTEMPT, this, (key, bundle) -> {
            if (sillTypeRadio.getCheckedRadioButtonIndex() > 0) {
                getParentFragmentManager().setFragmentResult(EXT_ACCESS_SAVE_ATTEMPT, bundle);
            } else {
                if (socialFragDoesNotHaveEmptyFields()) {
                    bundle.putStringArrayList(SOCIAL_FRAG, socialFrag);
                    getParentFragmentManager().setFragmentResult(FRAG_DATA, bundle);
                }
            }
        });

//        TODO - Implementar o resultListener para childFrags de soleiras

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
//        TextInputLayouts
        siaObsField = v.findViewById(R.id.social_entrance_sia_obs_field);
        floorTypeField = v.findViewById(R.id.floor_type_ext_field);
        gateWidthField = v.findViewById(R.id.gate_width_ext_field);
        trackHeightField = v.findViewById(R.id.ext_tracks_height_field);
        sillObsField = v.findViewById(R.id.gate_sill_obs_field);
        intercomHeightField = v.findViewById(R.id.intercom_height_field);
//        TextInputEditText
        siaObsValue = v.findViewById(R.id.social_entrance_sia_obs_value);
        floorTypeValue = v.findViewById(R.id.floor_type_ext_value);
        gateWidthValue = v.findViewById(R.id.gate_width_ext_value);
        trackHeightValue = v.findViewById(R.id.ext_tracks_height_value);
        sillObsValue = v.findViewById(R.id.gate_sill_obs_value);
        intercomHeightValue = v.findViewById(R.id.intercom_height_value);
//        MaterialButtons
        addTrackRampButton = v.findViewById(R.id.add_gate_track_ramp_button);
        addObstaclesButton = v.findViewById(R.id.add_gate_obstacles_button);
        addPayphoneButton = v.findViewById(R.id.add_gate_payphones_button);
//        FrameLayout
        sillFragment = v.findViewById(R.id.ext_access_sill_fragment);
//        ViewModel
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

    }

    private void extAccessMultiRadioListener (MultiLineRadioGroup multi) {
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

    private boolean socialFragDoesNotHaveEmptyFields() {
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
        if (getCheckedRadioIndex(hasTrackRampRadio) == 1) {
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

    private int getCheckedRadioIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowObsScroll() {
        siaObsValue.setOnTouchListener(this::scrollingField);
        sillObsValue.setOnTouchListener(this::scrollingField);
    }
}