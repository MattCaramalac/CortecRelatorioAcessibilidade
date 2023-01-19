package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestEntryUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class RestFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout restroomLocationField, accessibleRouteObsField, integratedRestroomObsField, restroomDistanceObsField,
            exclusiveEntranceObsField, driftingFloorObsField, restroomDrainObsField, restroomSwitchObsField,
            restroomSwitchHeightField;
    TextInputEditText restroomLocationValue, accessibleRouteObsValue, integratedRestroomObsValue, restroomDistanceObsValue,
            exclusiveEntranceObsValue, driftingFloorObsValue, restroomDrainObsValue, restroomSwitchObsValue,
            restroomSwitchHeightValue;
    TextView restroomTypeError, accessibleRouteError, integratedRestroomError, restroomDistanceError,
            exclusiveEntranceError, driftingFloorError, restroomDrainError, restroomSwitchError;
    RadioGroup  accessibleRouteRadio, integratedRestroomRadio, restroomDistanceRadio,
            exclusiveEntranceRadio, driftingFloorRadio, restroomDrainRadio, restroomSwitchRadio;
    MultiLineRadioGroup restroomTypeRadio;
    Button cancelRestroom, continueRegister;

    Integer restroomType, accessibleRoute, integratedRestroom, restroomDistance, exclusiveEntrance, antiDriftingFloor,
            restroomDrain, restroomSwitch;
    String restroomLocation, accessibleRouteObs, integratedRestroomObs, restroomDistanceObs, exclusiveEntranceObs,
            antiDriftingFloorObs, restroomDrainObs, switchObs;
    Double switchHeight;

    ArrayList<TextInputEditText> obsValues = new ArrayList<>();
    Bundle restBundle = new Bundle();

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    int recentEntry = 0, updateEntry = 0, registeredEntry = 0;

    public RestFragment() {
        // Required empty public constructor
    }

    public static RestFragment newInstance() {
        return new RestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            restBundle = new Bundle(this.getArguments());
        else
            restBundle = new Bundle();

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (restBundle.getBoolean(RECENT_ENTRY))
                    cancelClick();
                else {
                    setEnabled(false);
                    requireActivity().onBackPressed();
                }
                setEnabled(true);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restroom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRestroomViews(view);

        //      Usado quando um novo cadastro é realizado, colocando o ID no bundle e chamando o próximo fragmento
        modelEntry.getLastRestroomEntry().observe(getViewLifecycleOwner(), restroom -> {
            if (recentEntry == 1) {
                recentEntry = 0;
                int restroomID = restroom.getRestroomID();
                restBundle.putInt(REST_ID, restroomID);
                RestDoorFragment restDoorFragment = RestDoorFragment.newInstance();
                restDoorFragment.setArguments(restBundle);
                requireActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.show_fragment_selected, restDoorFragment).addToBackStack(null).commit();
            }
        });

//      Usado quando uma entrada deve ser atualizada,
        if (restBundle.getInt(REST_ID) != 0) {
            modelEntry.getRestFirstData(restBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), update -> {
                if (updateEntry == 1) {
                    updateEntry = 0;
                    RestDoorFragment restDoorFragment = RestDoorFragment.newInstance();
                    restDoorFragment.setArguments(restBundle);
                    requireActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.show_fragment_selected, restDoorFragment).addToBackStack(null).commit();
                }
            });
        }

        if (restBundle.getInt(REST_ID) > 0) {
            modelEntry.getRestFirstData(restBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), restroomEntry -> {
                gatherRestroomData(restroomEntry);
                registeredEntry = 1;
            });
        }

        continueRegister.setOnClickListener(v -> {
            if(checkRestroomFields()) {
                if (registeredEntry == 0 && recentEntry == 0) {
                    RestroomEntry newRestroom = newRestroom(restBundle);
                    ViewModelEntry.insertRestroomEntry(newRestroom);
                    recentEntry = 1;
                    restBundle.putBoolean(RECENT_ENTRY, true);
                    clearRestroomDataFields();
                } else if (registeredEntry == 1) {
                    RestEntryUpdate update = updateRestroom(restBundle);
                    ViewModelEntry.updateRestroomData(update);
                    updateEntry = 1;
                    clearRestroomDataFields();
                } else {
                    clearRestroomDataFields();
                    updateEntry = 0;
                    recentEntry = 0;
                    Toast.makeText(getContext(), "Houve um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

    }

    private void instantiateRestroomViews(View view) {
//        TextInputLayout
        restroomLocationField = view.findViewById(R.id.restroom_location_field);
        accessibleRouteObsField = view.findViewById(R.id.restroom_accessible_route_obs_field);
        integratedRestroomObsField = view.findViewById(R.id.integrated_restroom_obs_field);
        restroomDistanceObsField = view.findViewById(R.id.restroom_distance_obs_field);
        exclusiveEntranceObsField = view.findViewById(R.id.exclusive_entrance_obs_field);
        driftingFloorObsField = view.findViewById(R.id.restroom_drifting_obs_field);
        restroomDrainObsField = view.findViewById(R.id.restroom_drain_obs_field);
        restroomSwitchObsField = view.findViewById(R.id.restroom_switch_obs_field);
        restroomSwitchHeightField = view.findViewById(R.id.restroom_switch_height_field);
//        TextInputEditText
        restroomLocationValue = view.findViewById(R.id.restroom_location_value);
        accessibleRouteObsValue = view.findViewById(R.id.restroom_accessible_route_obs_value);
        integratedRestroomObsValue = view.findViewById(R.id.integrated_restroom_obs_value);
        restroomDistanceObsValue = view.findViewById(R.id.restroom_distance_obs_value);
        exclusiveEntranceObsValue = view.findViewById(R.id.exclusive_entrance_obs_value);
        driftingFloorObsValue = view.findViewById(R.id.restroom_drifting_obs_value);
        restroomDrainObsValue = view.findViewById(R.id.restroom_drain_obs_value);
        restroomSwitchObsValue = view.findViewById(R.id.restroom_switch_obs_value);
        restroomSwitchHeightValue = view.findViewById(R.id.restroom_switch_height_value);
//        Multiline RadioGroup
        restroomTypeRadio = view.findViewById(R.id.restroom_type_multiradio);
//        RadioGroup
        accessibleRouteRadio = view.findViewById(R.id.accessible_route_radio);
        integratedRestroomRadio = view.findViewById(R.id.integrated_restroom_radio);
        restroomDistanceRadio = view.findViewById(R.id.restroom_distance_radio);
        exclusiveEntranceRadio = view.findViewById(R.id.exclusive_entrance_radio);
        driftingFloorRadio = view.findViewById(R.id.restroom_drifting_radio);
        restroomDrainRadio = view.findViewById(R.id.restroom_drain_radio);
        restroomSwitchRadio = view.findViewById(R.id.restroom_switch_radio);
//        TextView
        restroomTypeError = view.findViewById(R.id.restroom_type_error);
        accessibleRouteError = view.findViewById(R.id.accessible_route_error);
        integratedRestroomError = view.findViewById(R.id.integrated_restroom_error);
        restroomDistanceError = view.findViewById(R.id.restroom_distance_error);
        exclusiveEntranceError = view.findViewById(R.id.exclusive_entrance_error);
        driftingFloorError = view.findViewById(R.id.restroom_drifting_error);
        restroomDrainError = view.findViewById(R.id.restroom_drain_error);
        restroomSwitchError = view.findViewById(R.id.restroom_switch_error);
//        Material Button
        cancelRestroom = view.findViewById(R.id.cancel_restroom);
        continueRegister = view.findViewById(R.id.continue_restroom_register);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        Listeners
        restroomSwitchRadio.setOnCheckedChangeListener(this::activateSwitchHeight);
        cancelRestroom.setOnClickListener(v -> cancelClick());

        addFieldsToArrays();
        allowObsScroll(obsValues);

    }

    private void cancelClick() {
        if (restBundle.getBoolean(RECENT_ENTRY) && restBundle.getInt(REST_ID) > 0) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteOneRestroomEntry(restBundle.getInt(REST_ID));
                restBundle = null;
                requireActivity().getSupportFragmentManager().popBackStack(REST_LIST, 0);
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStack(REST_LIST, 0);
    }

    public void activateSwitchHeight(RadioGroup radio, int checkedID) {
        int checkIndex = getRestroomCheckedRadio(radio);
        if (checkIndex == 1) {
            restroomSwitchHeightField.setVisibility(View.VISIBLE);
        } else {
            restroomSwitchHeightValue.setText(null);
            restroomSwitchHeightField.setVisibility(View.GONE);
        }
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

    public int getRestroomCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkRestroomFields() {
        clearRestroomErrors();
        int error = 0;
        if (TextUtils.isEmpty(restroomLocationValue.getText())) {
            error++;
            restroomLocationField.setError(getString(R.string.req_field_error));
        }
        if (restroomTypeRadio.getCheckedRadioButtonIndex() == -1) {
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
                restroomSwitchHeightField.setError(getString(R.string.req_field_error));
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

        return new RestroomEntry(bundle.getInt(BLOCK_ID), restroomType, restroomLocation, accessibleRoute,
                accessibleRouteObs, integratedRestroom, integratedRestroomObs, restroomDistance, restroomDistanceObs, exclusiveEntrance,
                exclusiveEntranceObs, antiDriftingFloor, antiDriftingFloorObs, restroomDrain, restroomDrainObs, restroomSwitch,
                switchHeight, switchObs, null, null, null, null, null, null,null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null);
    }

    public RestEntryUpdate updateRestroom(Bundle bundle) {
        pickUpRestroomDataFields();

        return new RestEntryUpdate(bundle.getInt(REST_ID),restroomType, restroomLocation, accessibleRoute,
                accessibleRouteObs, integratedRestroom, integratedRestroomObs, restroomDistance, restroomDistanceObs, exclusiveEntrance,
                exclusiveEntranceObs, antiDriftingFloor, antiDriftingFloorObs, restroomDrain, restroomDrainObs, restroomSwitch,
                switchHeight, switchObs);
    }

    public void pickUpRestroomDataFields() {
        restroomType = restroomTypeRadio.getCheckedRadioButtonIndex();
        accessibleRoute = getRestroomCheckedRadio(accessibleRouteRadio);
        integratedRestroom = getRestroomCheckedRadio(integratedRestroomRadio);
        restroomDistance = getRestroomCheckedRadio(restroomDistanceRadio);
        exclusiveEntrance = getRestroomCheckedRadio(exclusiveEntranceRadio);
        antiDriftingFloor = getRestroomCheckedRadio(driftingFloorRadio);
        restroomDrain = getRestroomCheckedRadio(restroomDrainRadio);
        restroomSwitch = getRestroomCheckedRadio(restroomSwitchRadio);

        restroomLocation = String.valueOf(restroomLocationValue.getText());
        accessibleRouteObs = String.valueOf(accessibleRouteObsValue.getText());
        integratedRestroomObs = String.valueOf(integratedRestroomObsValue.getText());
        restroomDistanceObs = String.valueOf(restroomDistanceObsValue.getText());
        exclusiveEntranceObs = String.valueOf(exclusiveEntranceObsValue.getText());
        antiDriftingFloorObs = String.valueOf(driftingFloorObsValue.getText());
        restroomDrainObs = String.valueOf(restroomDrainObsValue.getText());
        switchObs = String.valueOf(restroomSwitchObsValue.getText());

        if (restroomSwitch == 1) {
            switchHeight = Double.parseDouble(String.valueOf(restroomSwitchHeightValue.getText()));
        }

    }

    public void gatherRestroomData(RestroomEntry restroomEntry) {
        restroomTypeRadio.checkAt(restroomEntry.getRestType());
        restroomLocationValue.setText(restroomEntry.getRestLocation());

        accessibleRouteRadio.check((accessibleRouteRadio.getChildAt(restroomEntry.getAccessRoute())).getId());
        if (restroomEntry.getAccessRouteObs() != null)
            accessibleRouteObsValue.setText(restroomEntry.getAccessRouteObs());

        integratedRestroomRadio.check((integratedRestroomRadio.getChildAt(restroomEntry.getIntRestroom())).getId());
        if (restroomEntry.getIntRestObs() != null)
            integratedRestroomObsValue.setText(restroomEntry.getIntRestObs());

        restroomDistanceRadio.check((restroomDistanceRadio.getChildAt(restroomEntry.getRestDistance())).getId());
        if (restroomEntry.getRestDistObs() != null)
            restroomDistanceObsValue.setText(restroomEntry.getRestDistObs());

        exclusiveEntranceRadio.check((exclusiveEntranceRadio.getChildAt(restroomEntry.getExEntrance())).getId());
        if (restroomEntry.getExEntObs() != null)
            exclusiveEntranceObsValue.setText(restroomEntry.getExEntObs());

        driftingFloorRadio.check((driftingFloorRadio.getChildAt(restroomEntry.getAntiDriftFloor())).getId());
        if (restroomEntry.getAntiDriftFloorObs() != null)
            driftingFloorObsValue.setText(restroomEntry.getAntiDriftFloorObs());

        restroomDrainRadio.check((restroomDrainRadio.getChildAt(restroomEntry.getRestDrain())).getId());
        if (restroomEntry.getRestDrainObs() != null)
            restroomDrainObsValue.setText(restroomEntry.getRestDrainObs());

        restroomSwitchRadio.check((restroomSwitchRadio.getChildAt(restroomEntry.getRestSwitch())).getId());
        if (restroomEntry.getRestSwitch() == 1) {
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
