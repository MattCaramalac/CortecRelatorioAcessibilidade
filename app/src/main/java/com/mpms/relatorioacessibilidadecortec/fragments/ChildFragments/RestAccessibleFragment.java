package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.RestAccessColParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import org.parceler.Parcels;

import java.util.ArrayList;

public class RestAccessibleFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout restLocationField, accessibleRouteObsField, integratedRestObsField, driftFloorObsField, restDrainObsField, restSwitchObsField,
            restSwitchHeightField, photoField;
    TextInputEditText restLocationValue, accessibleRouteObsValue, integratedRestObsValue, driftFloorObsValue, restDrainObsValue, restSwitchObsValue,
            restSwitchHeightValue, photoValue;
    TextView restroomTypeError, accessibleRouteError, integratedRestError, driftingFloorError, restroomDrainError, restroomSwitchError;
    RadioGroup accessibleRouteRadio, integratedRestRadio, driftFloorRadio, restDrainRadio, restSwitchRadio;
    MultiLineRadioGroup restTypeMulti;

    ArrayList<TextInputEditText> obsValues = new ArrayList<>();
    static Bundle restBundle = new Bundle();

    ViewModelEntry modelEntry;

    int recentEntry = 0, updateEntry = 0, registeredEntry = 0;

    public RestAccessibleFragment() {
        // Required empty public constructor
    }

    public static RestAccessibleFragment newInstance(Bundle bundle) {
        restBundle = new Bundle(bundle);
        return new RestAccessibleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_accessible, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRestroomViews(view);

        if (restBundle.getInt(REST_ID) > 0)
            modelEntry.getRestFirstData(restBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadAccessRestData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (checkRestroomFields())
                createAccessRestParcel(bundle);
            else
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });

    }

    private void instantiateRestroomViews(View view) {
//        TextInputLayout
        restLocationField = view.findViewById(R.id.restroom_location_field);
        accessibleRouteObsField = view.findViewById(R.id.restroom_accessible_route_obs_field);
        integratedRestObsField = view.findViewById(R.id.integrated_restroom_obs_field);
        driftFloorObsField = view.findViewById(R.id.restroom_drifting_obs_field);
        restDrainObsField = view.findViewById(R.id.restroom_drain_obs_field);
        restSwitchObsField = view.findViewById(R.id.restroom_switch_obs_field);
        restSwitchHeightField = view.findViewById(R.id.restroom_switch_height_field);
        photoField = view.findViewById(R.id.rest_first_photo_field);
//        TextInputEditText
        restLocationValue = view.findViewById(R.id.restroom_location_value);
        accessibleRouteObsValue = view.findViewById(R.id.restroom_accessible_route_obs_value);
        integratedRestObsValue = view.findViewById(R.id.integrated_restroom_obs_value);
        driftFloorObsValue = view.findViewById(R.id.restroom_drifting_obs_value);
        restDrainObsValue = view.findViewById(R.id.restroom_drain_obs_value);
        restSwitchObsValue = view.findViewById(R.id.restroom_switch_obs_value);
        restSwitchHeightValue = view.findViewById(R.id.restroom_switch_height_value);
        photoValue = view.findViewById(R.id.rest_first_photo_value);
//        RadioGroup
        accessibleRouteRadio = view.findViewById(R.id.accessible_route_radio);
        integratedRestRadio = view.findViewById(R.id.integrated_restroom_radio);
        driftFloorRadio = view.findViewById(R.id.restroom_drifting_radio);
        restDrainRadio = view.findViewById(R.id.restroom_drain_radio);
        restSwitchRadio = view.findViewById(R.id.restroom_switch_radio);
//        MultilineRadioGroup
        restTypeMulti = view.findViewById(R.id.restroom_gender_multiradio);
//        TextView
        restroomTypeError = view.findViewById(R.id.restroom_type_error);
        accessibleRouteError = view.findViewById(R.id.accessible_route_error);
        integratedRestError = view.findViewById(R.id.integrated_restroom_error);
        driftingFloorError = view.findViewById(R.id.restroom_drifting_error);
        restroomDrainError = view.findViewById(R.id.restroom_drain_error);
        restroomSwitchError = view.findViewById(R.id.restroom_switch_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        restSwitchRadio.setOnCheckedChangeListener(this::radioListener);

        addFieldsToArrays();
        allowObsScroll(obsValues);

    }

    private void addFieldsToArrays() {
        obsValues.add(accessibleRouteObsValue);
        obsValues.add(integratedRestObsValue);
        obsValues.add(driftFloorObsValue);
        obsValues.add(restDrainObsValue);
        obsValues.add(restSwitchObsValue);
        obsValues.add(restSwitchHeightValue);
    }

    private void loadAccessRestData(RestroomEntry rest) {
        if (rest.getRestType() == 0) {
            if (rest.getRestGender() != null)
                restTypeMulti.checkAt(rest.getRestGender());
            if (rest.getRestLocation() != null)
                restLocationValue.setText(rest.getRestLocation());
            if (rest.getAccessRoute() != null)
                accessibleRouteRadio.check(accessibleRouteRadio.getChildAt(rest.getAccessRoute()).getId());
            if (rest.getAccessRouteObs() != null && rest.getAccessRouteObs().length() > 0)
                accessibleRouteObsValue.setText(rest.getAccessRouteObs());
            if (rest.getIntRestroom() != null)
                integratedRestRadio.check(integratedRestRadio.getChildAt(rest.getIntRestroom()).getId());
            if (rest.getIntRestObs() != null && rest.getIntRestObs().length() > 0)
                integratedRestObsValue.setText(rest.getIntRestObs());
            if (rest.getAntiDriftFloor() != null)
                driftFloorRadio.check(driftFloorRadio.getChildAt(rest.getAntiDriftFloor()).getId());
            if (rest.getAntiDriftFloorObs() != null && rest.getAntiDriftFloorObs().length() > 0)
                driftFloorObsValue.setText(rest.getAntiDriftFloorObs());
            if (rest.getRestDrain() != null)
                restDrainRadio.check(restDrainRadio.getChildAt(rest.getRestDrain()).getId());
            if (rest.getRestDrainObs() != null && rest.getRestDrainObs().length() > 0)
                restDrainObsValue.setText(rest.getRestDrainObs());
            if (rest.getRestSwitch() != null) {
                restSwitchRadio.check(restSwitchRadio.getChildAt(rest.getRestSwitch()).getId());
                if (rest.getRestSwitch() == 1 && rest.getSwitchHeight() != null)
                    restSwitchHeightValue.setText(String.valueOf(rest.getSwitchHeight()));
            }
            if (rest.getSwitchObs() != null && rest.getSwitchObs().length() > 0)
                restSwitchObsValue.setText(rest.getSwitchObs());
            if (rest.getRestFirstPhoto() != null)
                photoValue.setText(rest.getRestFirstPhoto());
        }
    }

    private boolean checkRestroomFields() {
        clearRestroomErrors();
        int error = 0;
        if (TextUtils.isEmpty(restLocationValue.getText())) {
            error++;
            restLocationField.setError(getString(R.string.req_field_error));
        }
        if (restTypeMulti.getCheckedRadioButtonId() == -1) {
            restroomTypeError.setVisibility(View.VISIBLE);
            error++;
        }
        if (indexRadio(accessibleRouteRadio) == -1) {
            accessibleRouteError.setVisibility(View.VISIBLE);
            error++;
        }
        if (indexRadio(integratedRestRadio) == -1) {
            integratedRestError.setVisibility(View.VISIBLE);
            error++;
        }
        if (indexRadio(driftFloorRadio) == -1) {
            driftingFloorError.setVisibility(View.VISIBLE);
            error++;
        }
        if (indexRadio(restDrainRadio) == -1) {
            restroomDrainError.setVisibility(View.VISIBLE);
            error++;
        }
        if (indexRadio(restSwitchRadio) == -1) {
            restroomSwitchError.setVisibility(View.VISIBLE);
            error++;
        } else if (indexRadio(restSwitchRadio) == 1) {
            if (TextUtils.isEmpty(restSwitchHeightValue.getText())) {
                restSwitchHeightField.setError(getString(R.string.req_field_error));
            }
        }
        return error == 0;
    }

    private void clearRestroomErrors() {
        restLocationField.setErrorEnabled(false);
        restroomTypeError.setVisibility(View.GONE);
        accessibleRouteError.setVisibility(View.GONE);
        integratedRestError.setVisibility(View.GONE);
        driftingFloorError.setVisibility(View.GONE);
        driftingFloorError.setVisibility(View.GONE);
        restroomDrainError.setVisibility(View.GONE);
        restroomSwitchError.setVisibility(View.GONE);
        restSwitchHeightField.setErrorEnabled(false);
    }

    private void createAccessRestParcel(Bundle bundle) {
        int restType, accessRoute, antiDriftFloor, intRest, restDrain, restSwitch;
        Double switchHeight = null;
        String restLocation, accessRouteObs = null, intRestObs = null, antiDriftFloorObs = null, restDrainObs = null, switchObs = null, photo = null;

        restType = restTypeMulti.getCheckedRadioButtonIndex();
        restLocation = String.valueOf(restLocationValue.getText());
        accessRoute = indexRadio(accessibleRouteRadio);
        if (!TextUtils.isEmpty(accessibleRouteObsValue.getText()))
            accessRouteObs = String.valueOf(accessibleRouteObsValue.getText());
        intRest = indexRadio(integratedRestRadio);
        if (!TextUtils.isEmpty(integratedRestObsValue.getText()))
            intRestObs = String.valueOf(integratedRestObsValue.getText());
        antiDriftFloor = indexRadio(driftFloorRadio);
        if (!TextUtils.isEmpty(driftFloorObsValue.getText()))
            antiDriftFloorObs = String.valueOf(driftFloorObsValue.getText());
        restDrain = indexRadio(restDrainRadio);
        if (!TextUtils.isEmpty(restDrainObsValue.getText()))
            restDrainObs = String.valueOf(restDrainObsValue.getText());
        restSwitch = indexRadio(restSwitchRadio);
        if (restSwitch == 1) {
            if (!TextUtils.isEmpty(restSwitchHeightValue.getText()))
                switchHeight = Double.parseDouble(String.valueOf(restSwitchHeightValue.getText()));
        }
        if (!TextUtils.isEmpty(restSwitchObsValue.getText()))
            switchObs = String.valueOf(restSwitchObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        RestAccessColParcel parcel = new RestAccessColParcel(restType, restLocation, null, null, null,accessRoute, accessRouteObs, intRest,
                intRestObs, antiDriftFloor, antiDriftFloorObs, restDrain, restDrainObs, null, null, null, restSwitch,
                switchHeight, switchObs, null, null,null, null, null, null, null,
                null, null, photo);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
        bundle.putBoolean(CHILD_DATA_COMPLETE, true);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int checkIndex = indexRadio(radio);
        if (checkIndex == 1) {
            restSwitchHeightField.setVisibility(View.VISIBLE);
        } else {
            restSwitchHeightValue.setText(null);
            restSwitchHeightField.setVisibility(View.GONE);
        }
    }
}
