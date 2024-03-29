package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class GateObsFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout referencePointField, commandHeightField, fSpaceWidthField, obsField, photoField;
    TextInputEditText referencePointValue, commandHeightValue, fSpaceWidthValue, obsValue, photoValue;
    RadioGroup gateObsTypeRadio, gateObsSiaRadio;
    Button saveGateObs, cancelGateObs;
    TextView gateObsTypeError, gateObsSiaError;

    ViewModelEntry modelEntry;

    Bundle obsBundle = new Bundle();

    public GateObsFragment() {
        // Required empty public constructor
    }

    public static GateObsFragment newInstance() {
        return new GateObsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            obsBundle.putInt(EXT_ACCESS_ID, this.getArguments().getInt(EXT_ACCESS_ID));
            obsBundle.putInt(GATE_OBS_ID, this.getArguments().getInt(GATE_OBS_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gate_obstacle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateGateObsViews(view);

        if (obsBundle.getInt(GATE_OBS_ID) > 0)
            modelEntry.getOneGateObsEntry(obsBundle.getInt(GATE_OBS_ID)).observe(getViewLifecycleOwner(), this::loadGateObsData);

        saveGateObs.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                GateObsEntry newObstacle = newGateObstacle(obsBundle);
                if (obsBundle.getInt(GATE_OBS_ID) > 0) {
                    newObstacle.setGateObsID(obsBundle.getInt(GATE_OBS_ID));
                    ViewModelEntry.updateGateObs(newObstacle);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (obsBundle.getInt(GATE_OBS_ID) == 0) {
                    ViewModelEntry.insertGateObs(newObstacle);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearGateObsFields();
                } else {
                    obsBundle.putInt(GATE_OBS_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelGateObs.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

    }

    private void instantiateGateObsViews(View view) {
//        TextInputLayout
        referencePointField = view.findViewById(R.id.gate_obstacle_location_field);
        commandHeightField = view.findViewById(R.id.gate_obs_height_field);
        fSpaceWidthField = view.findViewById(R.id.gate_obs_width_field);
        obsField = view.findViewById(R.id.gate_obstacle_obs_field);
        photoField = view.findViewById(R.id.gate_obstacle_photo_field);
//        TextInputEditText
        referencePointValue = view.findViewById(R.id.gate_obstacle_location_value);
        commandHeightValue = view.findViewById(R.id.gate_obs_height_value);
        fSpaceWidthValue = view.findViewById(R.id.gate_obs_width_value);
        obsValue = view.findViewById(R.id.gate_obstacle_obs_value);
        photoValue = view.findViewById(R.id.gate_obstacle_photo_value);
//        RadioGroup
        gateObsTypeRadio = view.findViewById(R.id.gate_obstacle_type_radio);
        gateObsSiaRadio = view.findViewById(R.id.obs_has_SIA_radio);
//        MaterialButton
        saveGateObs = view.findViewById(R.id.save_gate_obstacle);
        cancelGateObs = view.findViewById(R.id.cancel_gate_obstacle);
//        TextView
        gateObsTypeError = view.findViewById(R.id.gate_obstacle_type_error);
        gateObsSiaError = view.findViewById(R.id.obs_has_SIA_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        allowObsScroll(obsValue);
    }

    public GateObsEntry newGateObstacle(Bundle bundle) {
        int accessType, obsHasSia;
        double barrierHeight, barrierWidth;
        String referencePoint, obstacleObs = null, photo = null;

        referencePoint = String.valueOf(referencePointValue.getText());
        accessType = indexRadio(gateObsTypeRadio);
        barrierHeight = Double.parseDouble(String.valueOf(commandHeightValue.getText()));
        barrierWidth = Double.parseDouble(String.valueOf(fSpaceWidthValue.getText()));
        obsHasSia = indexRadio(gateObsSiaRadio);
        if (!TextUtils.isEmpty(obsValue.getText()))
            obstacleObs = String.valueOf(obsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new GateObsEntry(bundle.getInt(EXT_ACCESS_ID), referencePoint, accessType, barrierHeight, barrierWidth, obsHasSia, obstacleObs, photo);

    }

    public boolean checkEmptyFields() {
        clearErrorsGateObs();
        int i = 0;
        if (TextUtils.isEmpty(referencePointValue.getText())) {
            referencePointField.setError(getString(R.string.req_field_error));
            i++;
        }
        if (gateObsTypeRadio.getCheckedRadioButtonId() == -1) {
            gateObsTypeError.setVisibility(View.VISIBLE);
            i++;
        }
        if (TextUtils.isEmpty(commandHeightValue.getText())) {
            commandHeightField.setError(getString(R.string.req_field_error));
            i++;
        }
        if (TextUtils.isEmpty(fSpaceWidthValue.getText())) {
            fSpaceWidthField.setError(getString(R.string.req_field_error));
            i++;
        }
        if (gateObsSiaRadio.getCheckedRadioButtonId() == -1) {
            gateObsSiaError.setVisibility(View.VISIBLE);
            i++;
        }
        return i == 0;
    }

    public void clearErrorsGateObs() {
        referencePointField.setErrorEnabled(false);
        commandHeightField.setErrorEnabled(false);
        fSpaceWidthField.setErrorEnabled(false);
        gateObsTypeError.setVisibility(View.GONE);
        gateObsSiaError.setVisibility(View.GONE);
    }

    public void clearGateObsFields() {
        referencePointValue.setText(null);
        commandHeightValue.setText(null);
        fSpaceWidthValue.setText(null);
        obsValue.setText(null);
        gateObsTypeRadio.clearCheck();
        gateObsSiaRadio.clearCheck();
        photoValue.setText(null);
    }

    public void loadGateObsData(GateObsEntry gateObs) {
        if (gateObs.getAccessRefPoint() != null)
            referencePointValue.setText(gateObs.getAccessRefPoint());
        if (gateObs.getAccessType() != null)
            gateObsTypeRadio.check(gateObsTypeRadio.getChildAt(gateObs.getAccessType()).getId());
        if (gateObs.getObsHeight() != null)
            commandHeightValue.setText(String.valueOf(gateObs.getObsHeight()));
        if (gateObs.getObsWidth() != null)
            fSpaceWidthValue.setText(String.valueOf(gateObs.getObsWidth()));
        if (gateObs.getObsHasSia() != null)
            gateObsSiaRadio.check(gateObsSiaRadio.getChildAt(gateObs.getObsHasSia()).getId());
        if (gateObs.getGateObstacleObs() != null)
            obsValue.setText(gateObs.getGateObstacleObs());
        if (gateObs.getGateObsPhoto() != null)
            photoValue.setText(gateObs.getGateObsPhoto());

    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
    }
}