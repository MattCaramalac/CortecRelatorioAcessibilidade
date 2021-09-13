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
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntryUpdate;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.ArrayList;
import java.util.Objects;

public class RestroomFragment extends Fragment {

    public static final String RESTROOM_ID = "RESTROOM_ID";

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
    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    int recentEntry = 0;
    int updateEntry = 0;
    int registeredEntry = 0;

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
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
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

        //      Usado quando um novo cadastro é realizado, colocando o ID no bundle e chamando o próximo fragmento
        modelEntry.getLastRestroomEntry().observe(getViewLifecycleOwner(), restroom -> {
            if (recentEntry == 1) {
                recentEntry = 0;
                int restroomID = restroom.getRestroomID();
                restroomBundle.putInt(RESTROOM_ID, restroomID);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RestroomDoorFragment restDoorFragment = RestroomDoorFragment.newInstance();
                restDoorFragment.setArguments(restroomBundle);
                fragmentTransaction.replace(R.id.show_fragment_selected, restDoorFragment).addToBackStack(null).commit();
            }
        });

//      Usado quando uma entrada deve ser atualizada,
        modelEntry.getOneRestroomEntry(restroomBundle.getInt(RESTROOM_ID)).observe(getViewLifecycleOwner(), update -> {
            if (updateEntry == 1) {
                updateEntry = 0;
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RestroomDoorFragment restDoorFragment = RestroomDoorFragment.newInstance();
                restDoorFragment.setArguments(restroomBundle);
                fragmentTransaction.replace(R.id.show_fragment_selected, restDoorFragment).addToBackStack(null).commit();
            }
        });

//      Preenchimento dos campos da tela
        modelFragments.getRestroomBundle().observe(getViewLifecycleOwner(), restBundle -> {
            if (restBundle != null) {
                modelEntry.getOneRestroomEntry(restBundle.getInt(RESTROOM_ID)).observe(getViewLifecycleOwner(), this::gatherRestroomData);
                registeredEntry = 1;
            }
        });

        continueRegister.setOnClickListener(v -> {
            if(checkRestroomFields()) {
                if (registeredEntry == 0 && recentEntry == 0) {
                    RestroomEntry newRestroom = newRestroom(restroomBundle);
                    ViewModelEntry.insertRestroomEntry(newRestroom);
                    recentEntry = 1;
                    clearRestroomDataFields();
                } else if (registeredEntry == 1) {
                    RestroomEntryUpdate update = updateRestroom(restroomBundle);
                    ViewModelEntry.updateRestroomData(update);
                    updateEntry = 1;
                    clearRestroomDataFields();
                } else {
                    clearRestroomDataFields();
                    updateEntry = 0;
                    recentEntry = 0;
                    restroomBundle.putBoolean(RestroomDoorFragment.OPENED_DOOR_FRAG, false);
                    Toast.makeText(getContext(), "Houve um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelRestroom.setOnClickListener(v -> {
            restroomBundle = null;
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (restroomBundle.getBoolean(RestroomDoorFragment.OPENED_DOOR_FRAG))
            modelFragments.setRestroomBundle(restroomBundle);
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
        if (TextUtils.isEmpty(restroomLocationValue.getText())) {
            error++;
            restroomLocationField.setError(getString(R.string.blank_field_error));
        }
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
        restroomLocationField.setErrorEnabled(false);
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

    public RestroomEntry newRestroom(Bundle bundle) {
        pickUpRestroomDataFields();

        return new RestroomEntry(bundle.getInt(InspectionActivity.SCHOOL_ID_VALUE),restroomType, restroomLocation, accessibleRoute,
                accessibleRouteObs, integratedRestroom, integratedRestroomObs, restroomDistance, restroomDistanceObs, exclusiveEntrance,
                exclusiveEntranceObs, antiDriftingFloor, antiDriftingFloorObs, restroomDrain, restroomDrainObs, restroomSwitch,
                switchHeight, switchObs, null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null);
    }

    public RestroomEntryUpdate updateRestroom(Bundle bundle) {
        pickUpRestroomDataFields();

        return new RestroomEntryUpdate(bundle.getInt(RESTROOM_ID),restroomType, restroomLocation, accessibleRoute,
                accessibleRouteObs, integratedRestroom, integratedRestroomObs, restroomDistance, restroomDistanceObs, exclusiveEntrance,
                exclusiveEntranceObs, antiDriftingFloor, antiDriftingFloorObs, restroomDrain, restroomDrainObs, restroomSwitch,
                switchHeight, switchObs);
    }

    public void pickUpRestroomDataFields() {
        restroomType = getRestroomCheckedRadio(restroomTypeRadio);
        accessibleRoute = getRestroomCheckedRadio(accessibleRouteRadio);
        integratedRestroom = getRestroomCheckedRadio(integratedRestroomRadio);
        restroomDistance = getRestroomCheckedRadio(restroomDistanceRadio);
        exclusiveEntrance = getRestroomCheckedRadio(exclusiveEntranceRadio);
        antiDriftingFloor = getRestroomCheckedRadio(driftingFloorRadio);
        restroomDrain = getRestroomCheckedRadio(restroomDrainRadio);
        restroomSwitch = getRestroomCheckedRadio(restroomSwitchRadio);

        restroomLocation = Objects.requireNonNull(restroomLocationValue.getText()).toString();
        accessibleRouteObs = Objects.requireNonNull(accessibleRouteObsValue.getText()).toString();
        integratedRestroomObs = Objects.requireNonNull(integratedRestroomObsValue.getText()).toString();
        restroomDistanceObs = Objects.requireNonNull(restroomDistanceObsValue.getText()).toString();
        exclusiveEntranceObs = Objects.requireNonNull(exclusiveEntranceObsValue.getText()).toString();
        antiDriftingFloorObs = Objects.requireNonNull(driftingFloorObsValue.getText()).toString();
        restroomDrainObs = Objects.requireNonNull(restroomDrainObsValue.getText()).toString();
        switchObs = Objects.requireNonNull(restroomSwitchObsValue.getText()).toString();

        if (restroomSwitch == 1) {
            switchHeight = Double.parseDouble(Objects.requireNonNull(restroomSwitchHeightValue.getText()).toString());
        }

    }

    public void gatherRestroomData(RestroomEntry restroomEntry) {
        restroomTypeRadio.check((restroomTypeRadio.getChildAt(restroomEntry.getRestroomType())).getId());
        restroomLocationValue.setText(restroomEntry.getRestroomLocation());

        accessibleRouteRadio.check((accessibleRouteRadio.getChildAt(restroomEntry.getAccessibleRoute())).getId());
        if (restroomEntry.getAccessibleRouteObs() != null)
            accessibleRouteObsValue.setText(restroomEntry.getAccessibleRouteObs());

        integratedRestroomRadio.check((integratedRestroomRadio.getChildAt(restroomEntry.getIntegratedRestroom())).getId());
        if (restroomEntry.getIntegratedRestroomObs() != null)
            integratedRestroomObsValue.setText(restroomEntry.getIntegratedRestroomObs());

        restroomDistanceRadio.check((restroomDistanceRadio.getChildAt(restroomEntry.getRestroomDistance())).getId());
        if (restroomEntry.getRestroomDistanceObs() != null)
            restroomDistanceObsValue.setText(restroomEntry.getRestroomDistanceObs());

        exclusiveEntranceRadio.check((exclusiveEntranceRadio.getChildAt(restroomEntry.getExclusiveEntrance())).getId());
        if (restroomEntry.getExclusiveEntranceObs() != null)
            exclusiveEntranceObsValue.setText(restroomEntry.getExclusiveEntranceObs());

        driftingFloorRadio.check((driftingFloorRadio.getChildAt(restroomEntry.getAntiDriftingFloor())).getId());
        if (restroomEntry.getAntiDriftingFloorObs() != null)
            driftingFloorObsValue.setText(restroomEntry.getAntiDriftingFloorObs());

        restroomDrainRadio.check((restroomDrainRadio.getChildAt(restroomEntry.getRestroomDrain())).getId());
        if (restroomEntry.getRestroomDrainObs() != null)
            restroomDrainObsValue.setText(restroomEntry.getRestroomDrainObs());

        restroomSwitchRadio.check((restroomSwitchRadio.getChildAt(restroomEntry.getRestroomSwitch())).getId());
        if (restroomEntry.getRestroomSwitch() == 1) {
            restroomSwitchHeightField.setEnabled(true);
            restroomSwitchHeightValue.setText(String.valueOf(restroomEntry.getSwitchHeight()));
            if (restroomEntry.getSwitchObs() != null)
                restroomSwitchObsValue.setText(restroomEntry.getSwitchObs());
        }
    }

    public void clearRestroomDataFields() {
        restroomLocationValue.setText(null);
        accessibleRouteObsValue.setText(null);
        integratedRestroomObsValue.setText(null);
        restroomDistanceObsValue.setText(null);
        exclusiveEntranceObsValue.setText(null);
        driftingFloorObsValue.setText(null);
        restroomDrainObsValue.setText(null);
        restroomSwitchObsValue.setText(null);
        restroomSwitchHeightValue.setText(null);

        restroomTypeRadio.clearCheck();
        accessibleRouteRadio.clearCheck();
        integratedRestroomRadio.clearCheck();
        restroomDistanceRadio.clearCheck();
        exclusiveEntranceRadio.clearCheck();
        driftingFloorRadio.clearCheck();
        restroomDrainRadio.clearCheck();
        restroomSwitchRadio.clearCheck();

        restroomSwitchHeightField.setEnabled(false);
    }


}
