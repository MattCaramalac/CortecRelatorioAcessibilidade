package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.GateObsBarrierType;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.GateObsDoorType;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.GateObstacleTypeFragment;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class AddGateObsDialog extends DialogFragment {

    private Toolbar toolbar;

    TextInputLayout referencePointField, obsField;
    TextInputEditText referencePointValue, obsValue;
    RadioGroup gateObstacleSituation;
    Button saveGateObs, cancelGateObs;
    TextView gateObsError;

    FragmentManager manager;

    static Bundle extBundle;

    ViewModelDialog modelDialog;

    String referencePoint, obstacleObs;
    Integer accessibleEntrance, accessType;
    Double entranceGateWidth, gateBarrierHeight, gateBarrierWidth;

    public static AddGateObsDialog displayGateDialog(FragmentManager manager, Bundle bundle) {
        AddGateObsDialog addGateObstacleDialog = new AddGateObsDialog();
        addGateObstacleDialog.show(manager, "GATE_OBS_DIALOG");
        extBundle = bundle;
        return addGateObstacleDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_gate_obs_dialog, container, false);
        toolbar = view.findViewById(R.id.gate_obstacles_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(getString(R.string.dialog_add_gate_obstacle_header));

        referencePointField = view.findViewById(R.id.gate_obstacle_location_field);
        obsField = view.findViewById(R.id.gate_obstacle_obs_field);

        referencePointValue = view.findViewById(R.id.gate_obstacle_location_value);
        obsValue = view.findViewById(R.id.gate_obstacle_obs_value);

        gateObstacleSituation = view.findViewById(R.id.gate_obstacle_situation_radio);

        saveGateObs = view.findViewById(R.id.save_gate_obstacle);
        cancelGateObs = view.findViewById(R.id.cancel_gate_obstacle);

        gateObsError = view.findViewById(R.id.gate_obstacle_situation_error);

        manager = getChildFragmentManager();

        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        gateObstacleSituation.setOnCheckedChangeListener(((group, checkedId) -> {
            int index = getCheckedRadio(group);
            if (index == 1)
                getChildFragmentManager().beginTransaction().replace(R.id.gate_obs_type_child_fragment, new GateObstacleTypeFragment()).commit();
            else {
                removeObsFragment();
            }
        }));

        modelDialog.getGateObsInfo().observe(getViewLifecycleOwner(), gateBundle -> {
            if (gateBundle != null) {
                gateBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, extBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID));
                GateObsEntry newObs = newObstacle(gateBundle);
                ViewModelEntry.insertGateObs(newObs);
                modelDialog.setGateObsInfo(null);
                removeObsFragment();
                clearGateObsFields();
            }
        });

        saveGateObs.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                if(getCheckedRadio(gateObstacleSituation) == 0) {
                    GateObsEntry newObs = newObstacle(extBundle);
                    ViewModelEntry.insertGateObs(newObs);
                    clearGateObsFields();
                } else {
                    modelDialog.setSaveGateObsAttemptOne(1);
                }
            }
        });

        cancelGateObs.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int length = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, length);
        }
    }

    public GateObsEntry newObstacle(Bundle bundle) {
        referencePoint = null;
        accessType = null;
        gateBarrierHeight = null;
        gateBarrierWidth = null;
        entranceGateWidth = null;
        obstacleObs = null;

        if (referencePointValue.getText() != null)
            referencePoint = Objects.requireNonNull(referencePointValue.getText()).toString();

        accessibleEntrance = getCheckedRadio(gateObstacleSituation);

        if(accessibleEntrance == 1) {
            accessType = bundle.getInt(GateObstacleTypeFragment.GATE_OBS_TYPE);
            if (accessType == 0) {
                gateBarrierHeight = bundle.getDouble(GateObsBarrierType.OBS_BARRIER_HEIGHT);
                gateBarrierWidth = bundle.getDouble(GateObsBarrierType.OBS_BARRIER_WIDTH);

            } else if (accessType == 1)
                entranceGateWidth = bundle.getDouble(GateObsDoorType.OBS_GATE_WIDTH);
        }

        if (!TextUtils.isEmpty(obsValue.getText()))
            obstacleObs = Objects.requireNonNull(obsValue.getText()).toString();

        return new GateObsEntry(bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID), referencePoint, accessibleEntrance, accessType,
                entranceGateWidth, gateBarrierHeight, gateBarrierWidth, obstacleObs);

    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptyFields() {
        clearErrorsGateObs();
        int i = 0;
        if (TextUtils.isEmpty(referencePointValue.getText())) {
            referencePointField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (gateObstacleSituation.getCheckedRadioButtonId() == -1) {
            gateObsError.setVisibility(View.VISIBLE);
            i++;
        }
        return i == 0;
    }

    public void clearErrorsGateObs() {
        referencePointField.setErrorEnabled(false);
        gateObsError.setVisibility(View.GONE);
    }

    public void clearGateObsFields() {
        referencePointValue.setText(null);
        obsValue.setText(null);
        gateObstacleSituation.clearCheck();
    }

    public void removeObsFragment() {
        GateObstacleTypeFragment gateObstacle = (GateObstacleTypeFragment) manager.findFragmentById(R.id.gate_obs_type_child_fragment);
        if (gateObstacle != null)
            manager.beginTransaction().remove(gateObstacle).commit();
        }
}