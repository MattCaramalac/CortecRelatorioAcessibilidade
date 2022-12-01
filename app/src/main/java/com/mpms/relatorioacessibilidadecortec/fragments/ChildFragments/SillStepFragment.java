package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;


public class SillStepFragment extends Fragment implements TagInterface {

    TextInputLayout stepHeightField;
    TextInputEditText stepHeightValue;

    ViewModelEntry modelEntry;

    public SillStepFragment() {
        // Required empty public constructor
    }

    public static SillStepFragment newInstance() {
        return new SillStepFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sill_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSillStepViews(view);

        getParentFragmentManager().setFragmentResultListener(LOAD_CHILD_DATA, this, (key, bundle) -> {
            if (bundle.getInt(DoorFragment.DOOR_ID) > 0) {
                modelEntry.getSpecificDoor(bundle.getInt(DoorFragment.DOOR_ID)).observe(getViewLifecycleOwner(), this::loadStepDoorData);
            } else if (bundle.getBoolean(FROM_EXT_ACCESS)) {
                modelEntry.getOneExternalAccess(bundle.getInt(AMBIENT_ID))
                        .observe(getViewLifecycleOwner(), this::loadStepExtAccData);
            } else if (bundle.getInt(PLAY_ID) > 0) {
                modelEntry.getOnePlayground(bundle.getInt(PLAY_ID))
                        .observe(getViewLifecycleOwner(), this::loadStepPlayData);
            } else if (bundle.getInt(SIDEWALK_SLOPE_ID) > 0) {
                modelEntry.getSidewalkSlopeEntry(bundle.getInt(SIDEWALK_SLOPE_ID))
                        .observe(getViewLifecycleOwner(), this::loadStepStreetSlopeData);
            } else if (bundle.getInt(REST_ID) > 0) {
                modelEntry.getOneRestroomEntry(bundle.getInt(REST_ID))
                        .observe(getViewLifecycleOwner(), this::loadStepRestData);
            }
        });

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            checkStepNoEmptyFields(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });
    }

    private void instantiateSillStepViews(View view) {
//        TextInputLayout
        stepHeightField = view.findViewById(R.id.sill_step_height_field);
//        TextInputEditText
        stepHeightValue = view.findViewById(R.id.sill_step_height_value);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private boolean checkStepNoEmptyFields(Bundle bundle) {
        clearEmptyErrorStepField();
        int i = 0;
        if (TextUtils.isEmpty(stepHeightValue.getText())) {
            stepHeightField.setError(getString(R.string.req_field_error));
            i++;
        } else {
            bundle.putDouble(STEP_HEIGHT, Double.parseDouble(String.valueOf(stepHeightValue.getText())));
        }

        if (!bundle.getBoolean(ADD_ITEM_REQUEST)) {
            bundle.putBoolean(CHILD_DATA_COMPLETE, i == 0);
        }
        return i == 0;
    }

    private void clearEmptyErrorStepField() {
        stepHeightField.setErrorEnabled(false);
    }

    private void loadStepExtAccData(ExternalAccess access) {
        if (access.getSillStepHeight() != null)
            stepHeightValue.setText(String.valueOf(access.getSillStepHeight()));
    }

    private void loadStepPlayData(PlaygroundEntry playEntry) {
        if (playEntry.getStepSillHeight() != null)
            stepHeightValue.setText(String.valueOf(playEntry.getStepSillHeight()));
    }

    private void loadStepDoorData(DoorEntry doorEntry) {
        if (doorEntry.getSillStepHeight() != null)
            stepHeightValue.setText(String.valueOf(doorEntry.getSillStepHeight()));
    }

    private void loadStepStreetSlopeData(SidewalkSlopeEntry slopeEntry) {
            stepHeightValue.setText(String.valueOf(slopeEntry.getStepJunctionHeight()));
    }

    private void loadStepRestData(RestroomEntry entry) {
        if (entry.getSillStepHeight() != null)
            stepHeightValue.setText(String.valueOf(entry.getSillStepHeight()));
    }
}