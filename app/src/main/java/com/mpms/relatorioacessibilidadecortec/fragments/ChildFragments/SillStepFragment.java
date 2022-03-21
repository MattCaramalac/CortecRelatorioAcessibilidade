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
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.PlaygroundFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.ArrayList;


public class SillStepFragment extends Fragment {

    public static final String STEP_HEIGHT = "STEP_HEIGHT";

    TextInputLayout stepHeightField;
    TextInputEditText stepHeightValue;

    ViewModelEntry modelEntry;

    ArrayList<String> childData = new ArrayList<>();

    Bundle sillStepBundle = new Bundle();

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

        getParentFragmentManager().setFragmentResultListener(InspectionActivity.LOAD_CHILD_DATA, this, (key, bundle) -> {
            if (bundle.getInt(DoorFragment.DOOR_ID) > 0) {
                modelEntry.getSpecificDoor(bundle.getInt(DoorFragment.DOOR_ID)).observe(getViewLifecycleOwner(), this::gatherStepDoorData);
            } else if (bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) > 0) {
                modelEntry.getOneExternalAccess(bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID))
                        .observe(getViewLifecycleOwner(), this::gatherStepExtAccData);
            } else if (bundle.getInt(PlaygroundFragment.PLAY_ID) > 0) {
                modelEntry.getOnePlayground(bundle.getInt(PlaygroundFragment.PLAY_ID))
                        .observe(getViewLifecycleOwner(), this::gatherStepPlayData);
            }
        });

        getParentFragmentManager().setFragmentResultListener(InspectionActivity.GATHER_CHILD_DATA, this, (key, bundle) -> {
            checkStepNoEmptyFields(bundle);
            getParentFragmentManager().setFragmentResult(InspectionActivity.CHILD_DATA_LISTENER, bundle);
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
            stepHeightField.setError(getString(R.string.blank_field_error));
            i++;
        } else {
            bundle.putDouble(STEP_HEIGHT, Double.parseDouble(String.valueOf(stepHeightValue.getText())));
        }

        if (!bundle.getBoolean(InspectionActivity.ADD_ITEM_REQUEST)) {
            bundle.putBoolean(InspectionActivity.CHILD_DATA_COMPLETE, i == 0);
        }
        return i == 0;
    }

    private void clearEmptyErrorStepField() {
        stepHeightField.setErrorEnabled(false);
    }

    private void gatherStepExtAccData(ExternalAccess access) {
        if (access.getSillStepHeight() != null)
            stepHeightValue.setText(String.valueOf(access.getSillStepHeight()));
    }

    private void gatherStepPlayData(PlaygroundEntry playEntry) {
        if (playEntry.getStepSillHeight() != null)
            stepHeightValue.setText(String.valueOf(playEntry.getStepSillHeight()));
    }

    private void gatherStepDoorData(DoorEntry doorEntry) {
        if (doorEntry.getSillStepHeight() != null)
            stepHeightValue.setText(String.valueOf(doorEntry.getSillStepHeight()));
    }
}