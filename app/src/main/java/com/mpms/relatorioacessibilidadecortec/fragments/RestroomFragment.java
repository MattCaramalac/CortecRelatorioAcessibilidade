package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntry;

import java.util.ArrayList;
import java.util.Objects;

public class RestroomFragment extends Fragment {

    TextInputLayout restroomLocationField, accessibleRouteObsField, integratedRestroomObsField, restroomDistanceObsField,
            exclusiveEntranceObsField, driftingFloorObsField, restroomDrainObsField, restroomSwitchObsField,
            restroomSwitchHeightField;
    TextInputEditText restroomLocationValue, accessibleRouteObsValue, integratedRestroomObsValue, restroomDistanceObsValue,
            exclusiveEntranceObsValue, driftingFloorObsValue, restroomDrainObsValue, restroomSwitchObsValue,
            restroomSwitchHeightValue;
    TextView restroomTypeError, accessibleRouteError, integratedRestroomError, restroomDistanceError,
            exclusiveEntranceError, driftingFloorError, restroomDrainError, restroomSwitchError;
    RadioGroup restroomTypeRadio, accessibleRouteRadio, integratedRestroomRadio, restroomDistanceRadio,
            exclusiveEntranceRadio, driftingFloorRadio, restroomDrainRadio, restroomSwitchRadio;
    Button cancelRestroom, continueRegister;

    Integer restroomType, accessibleRoute, integratedRestroom, restroomDistance, exclusiveEntrance, antiDriftingFloor,
            restroomDrain, restroomSwitch;
    String restroomLocation, accessibleRouteObs, integratedRestroomObs, restroomDistanceObs, exclusiveEntranceObs,
            antiDriftingFloorObs, restroomDrainObs, switchObs;
    Double switchHeight;

    ArrayList<TextInputEditText> obsValues = new ArrayList<>();
    Bundle restroomBundle = new Bundle();

    public RestroomFragment() {
        // Required empty public constructor
    }

    public static RestroomFragment newInstance() {
        return new RestroomFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_restroom, container, false);
        restroomBundle = this.getArguments();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restroomLocationField = view.findViewById(R.id.restroom_location_field);
        accessibleRouteObsField = view.findViewById(R.id.restroom_accessible_route_obs_field);
        integratedRestroomObsField = view.findViewById(R.id.integrated_restroom_obs_field);
        restroomDistanceObsField = view.findViewById(R.id.restroom_distance_obs_field);
        exclusiveEntranceObsField = view.findViewById(R.id.exclusive_entrance_obs_field);
        driftingFloorObsField = view.findViewById(R.id.restroom_drifting_obs_field);
        restroomDrainObsField = view.findViewById(R.id.restroom_drain_obs_field);
        restroomSwitchObsField = view.findViewById(R.id.restroom_switch_obs_field);
        restroomSwitchHeightField = view.findViewById(R.id.restroom_switch_height_field);

        restroomLocationValue = view.findViewById(R.id.restroom_location_value);
        accessibleRouteObsValue = view.findViewById(R.id.restroom_accessible_route_obs_value);
        integratedRestroomObsValue = view.findViewById(R.id.integrated_restroom_obs_value);
        restroomDistanceObsValue = view.findViewById(R.id.restroom_distance_obs_value);
        exclusiveEntranceObsValue = view.findViewById(R.id.exclusive_entrance_obs_value);
        driftingFloorObsValue = view.findViewById(R.id.restroom_drifting_obs_value);
        restroomDrainObsValue = view.findViewById(R.id.restroom_drain_obs_value);
        restroomSwitchObsValue = view.findViewById(R.id.restroom_switch_obs_value);
        restroomSwitchHeightValue = view.findViewById(R.id.restroom_switch_height_value);

        restroomTypeRadio = view.findViewById(R.id.restroom_type_radio);
        accessibleRouteRadio = view.findViewById(R.id.accessible_route_radio);
        integratedRestroomRadio = view.findViewById(R.id.integrated_restroom_radio);
        restroomDistanceRadio = view.findViewById(R.id.restroom_distance_radio);
        exclusiveEntranceRadio = view.findViewById(R.id.exclusive_entrance_radio);
        driftingFloorRadio = view.findViewById(R.id.restroom_drifting_radio);
        restroomDrainRadio = view.findViewById(R.id.restroom_drain_radio);
        restroomSwitchRadio = view.findViewById(R.id.restroom_switch_radio);

        restroomTypeError = view.findViewById(R.id.restroom_type_error);
        accessibleRouteError = view.findViewById(R.id.accessible_route_error);
        integratedRestroomError = view.findViewById(R.id.integrated_restroom_error);
        restroomDistanceError = view.findViewById(R.id.restroom_distance_error);
        exclusiveEntranceError = view.findViewById(R.id.exclusive_entrance_error);
        driftingFloorError = view.findViewById(R.id.restroom_drifting_error);
        restroomDrainError = view.findViewById(R.id.restroom_drain_error);
        restroomSwitchError = view.findViewById(R.id.restroom_switch_error);

        cancelRestroom = view.findViewById(R.id.cancel_restroom);
        continueRegister = view.findViewById(R.id.continue_restroom_register);

        enableObsScrollingFields();

        restroomSwitchRadio.setOnCheckedChangeListener(this::activateSwitchHeight);

        continueRegister.setOnClickListener(v -> {
            if(checkRestroomFields()) {

            }
        });

        cancelRestroom.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    public void activateSwitchHeight(RadioGroup radio, int checkedID) {
        int checkIndex = getRestroomCheckedRadio(radio);
        if (checkIndex == 1) {
            restroomSwitchHeightField.setEnabled(true);
        } else {
            restroomSwitchHeightValue.setText(null);
            restroomSwitchHeightField.setEnabled(false);
        }
    }

    public boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    public void addFieldsToArrays() {
        obsValues.add(accessibleRouteObsValue);
        obsValues.add(integratedRestroomObsValue);
        obsValues.add(restroomDistanceObsValue);
        obsValues.add(exclusiveEntranceObsValue);
        obsValues.add(driftingFloorObsValue);
        obsValues.add(restroomDrainObsValue);
        obsValues.add(restroomSwitchObsValue);
        obsValues.add(restroomSwitchHeightValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void enableObsScrollingFields() {
        addFieldsToArrays();
        for (TextInputEditText obsScroll :obsValues) {
            obsScroll.setOnTouchListener(this::scrollingField);
        }
    }

    public int getRestroomCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkRestroomFields() {
        clearRestroomErrors();
        int error = 0;
        if (getRestroomCheckedRadio(restroomTypeRadio) == -1) {
            restroomTypeError.setVisibility(View.VISIBLE);
            error++;
        }
        if (getRestroomCheckedRadio(accessibleRouteRadio) == -1) {
            accessibleRouteError.setVisibility(View.VISIBLE);
            error++;
        }
        if (getRestroomCheckedRadio(integratedRestroomRadio) == -1) {
            integratedRestroomError.setVisibility(View.VISIBLE);
            error++;
        }
        if (getRestroomCheckedRadio(restroomDistanceRadio) == -1) {
            restroomDistanceError.setVisibility(View.VISIBLE);
            error++;
        }
        if (getRestroomCheckedRadio(exclusiveEntranceRadio) == -1) {
            exclusiveEntranceError.setVisibility(View.VISIBLE);
            error++;
        }
        if (getRestroomCheckedRadio(driftingFloorRadio) == -1) {
            driftingFloorError.setVisibility(View.VISIBLE);
            error++;
        }
        if (getRestroomCheckedRadio(restroomDrainRadio) == -1) {
            restroomDrainError.setVisibility(View.VISIBLE);
            error++;
        }
        if (getRestroomCheckedRadio(restroomSwitchRadio) == -1) {
            restroomSwitchError.setVisibility(View.VISIBLE);
            error++;
        } else if (getRestroomCheckedRadio(restroomSwitchRadio) == 1) {
            if (TextUtils.isEmpty(restroomSwitchHeightValue.getText())) {
                restroomSwitchHeightField.setError(getString(R.string.blank_field_error));
            }
        }
        return error == 0;
    }

    public void clearRestroomErrors() {
        restroomTypeError.setVisibility(View.GONE);
        accessibleRouteError.setVisibility(View.GONE);
        integratedRestroomError.setVisibility(View.GONE);
        restroomDistanceError.setVisibility(View.GONE);
        exclusiveEntranceError.setVisibility(View.GONE);
        driftingFloorError.setVisibility(View.GONE);
        driftingFloorError.setVisibility(View.GONE);
        restroomDrainError.setVisibility(View.GONE);
        restroomSwitchError.setVisibility(View.GONE);
        restroomSwitchHeightField.setErrorEnabled(false);
    }

//    public RestroomEntry newRestroom(Bundle bundle) {}
//

}

