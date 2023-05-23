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
import com.mpms.relatorioacessibilidadecortec.data.parcels.StepParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;


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
            if (bundle.getInt(DOOR_ID) > 0) {
                modelEntry.getSpecificDoor(bundle.getInt(DOOR_ID)).observe(getViewLifecycleOwner(), this::loadStepDoorData);
            } else if (bundle.getBoolean(FROM_EXT_ACCESS)) {
                modelEntry.getOneExternalAccess(bundle.getInt(AMBIENT_ID))
                        .observe(getViewLifecycleOwner(), this::loadStepExtAccData);
            } else if (bundle.getInt(PLAY_ID) > 0) {
                modelEntry.getOnePlayground(bundle.getInt(PLAY_ID))
                        .observe(getViewLifecycleOwner(), this::loadStepPlayData);
            }
        });

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (checkStepNoEmptyFields(bundle))
                createStepParcel(bundle);
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
        } else if (Double.parseDouble(String.valueOf(stepHeightValue.getText())) < 2) {
            stepHeightField.setError(getString(R.string.help_step_height));
            i++;
        }
        if (!bundle.getBoolean(ADD_ITEM_REQUEST))
            bundle.putBoolean(CHILD_DATA_COMPLETE, i == 0);

        return i == 0;
    }

    private void createStepParcel(Bundle bundle) {
        Double stepHeight = null;

        if (!TextUtils.isEmpty(stepHeightValue.getText()))
            stepHeight = Double.parseDouble(String.valueOf(stepHeightValue.getText()));

        StepParcel parcel = new StepParcel(stepHeight);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
    }

    private void clearEmptyErrorStepField() {
        stepHeightField.setErrorEnabled(false);
    }

    private void loadStepExtAccData(ExternalAccess access) {
        if (access.getSillStepHeight() != null)
            stepHeightValue.setText(String.valueOf(access.getSillStepHeight()));
    }

    private void loadStepPlayData(PlaygroundEntry playEntry) {
        if (playEntry.getStepHeight() != null)
            stepHeightValue.setText(String.valueOf(playEntry.getStepHeight()));
    }

    private void loadStepDoorData(DoorEntry doorEntry) {
        if (doorEntry.getStepHeight() != null)
            stepHeightValue.setText(String.valueOf(doorEntry.getStepHeight()));
    }

}