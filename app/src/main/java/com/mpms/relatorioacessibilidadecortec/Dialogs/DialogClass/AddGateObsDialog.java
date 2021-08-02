package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.GateObstacleTypeFragment;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.InclinationSillFragment;
import com.mpms.relatorioacessibilidadecortec.R;

import java.util.Objects;

public class AddGateObsDialog extends DialogFragment {

    private static final String SCHOOL_ID_VALUE = "SCHOOL_ID_VALUE";

    private Toolbar toolbar;

    TextInputLayout referencePointField, obsField;
    TextInputEditText referencePointValue, obsValue;
    RadioGroup gateObstacleSituation;
    Button saveGateObs, cancelGateObs;
    TextView gateObsError;

    FragmentManager manager;

    static Bundle roomBundle;

    public static AddGateObsDialog displayGateDialog(FragmentManager manager) {
        AddGateObsDialog addGateObstacleDialog = new AddGateObsDialog();
        addGateObstacleDialog.show(manager, "GATE_OBS_DIALOG");
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

        gateObstacleSituation.setOnCheckedChangeListener(((group, checkedId) -> {
            int index = getCheckedRadio(group);
            if (index == 1)
                getChildFragmentManager().beginTransaction().replace(R.id.gate_obs_type_child_fragment, new GateObstacleTypeFragment()).commit();
            else {
                removeObsFragment();
            }
        }));

        saveGateObs.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                if(getCheckedRadio(gateObstacleSituation) == 0) {

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


    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptyFields() {
        clearErrorsGateObs();
        int i = 0;
        if (gateObstacleSituation.getCheckedRadioButtonId() == -1) {
            gateObsError.setVisibility(View.VISIBLE);
            i++;
        }
        return i == 0;
    }

    public void clearErrorsGateObs() {
        gateObsError.setVisibility(View.GONE);
    }

    public void clearGateObsFields() {
        referencePointValue.setText(null);
    }

    public void removeObsFragment() {
        GateObstacleTypeFragment gateObstacle = (GateObstacleTypeFragment) manager.findFragmentById(R.id.gate_obs_type_child_fragment);
        if (gateObstacle != null)
            manager.beginTransaction().remove(gateObstacle).commit();
        }
}