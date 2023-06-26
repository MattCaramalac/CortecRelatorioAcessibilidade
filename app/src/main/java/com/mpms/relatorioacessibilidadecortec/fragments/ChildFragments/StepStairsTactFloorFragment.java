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
import com.mpms.relatorioacessibilidadecortec.data.entities.SingleStepEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SingleStepTactParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class StepStairsTactFloorFragment extends Fragment implements TagInterface, RadioGroupInterface {

    TextInputLayout lowDistField, lowWidthField, upDistField, upWidthField;
    TextInputEditText lowDistValue, lowWidthValue, upDistValue, upWidthValue;
    RadioGroup lowTactRadio, upTactRadio, lowAntiRadio, lowFloorRadio, lowColorRadio, upAntiRadio, upFloorRadio, upColorRadio;
    TextView lowError, upError, lowAntiHeader, lowAntiError, lowFloorHeader, lowFloorError, lowColorHeader, lowColorError, upAntiHeader, upAntiError, upFloorHeader,
            upFloorError, upColorHeader, upColorError;

    ViewModelEntry modelEntry;
    Bundle tactBundle;


    public static StepStairsTactFloorFragment newInstance() {
        return new StepStairsTactFloorFragment();
    }

    public StepStairsTactFloorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            tactBundle = new Bundle(getArguments());
        else
            tactBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_stairs_tact_floor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateTactViews(view);

        if (tactBundle.getInt(STEP_ID) > 0)
            modelEntry.getOneSoleStep(tactBundle.getInt(STEP_ID)).observe(getViewLifecycleOwner(), this::loadTactFloorData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_2, getViewLifecycleOwner(), (key, bundle) -> {
            if (noEmptyField()) {
                bundle.putBoolean(CHILD_DATA_COMPLETE_2, true);
                SingleStepTactParcel parcel = newTactParcel();
                bundle.putParcelable(CHILD_PARCEL_2, Parcels.wrap(parcel));
            } else
                bundle.putBoolean(CHILD_DATA_COMPLETE_2, false);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_2, bundle);

        });
    }

    private void loadTactFloorData(SingleStepEntry step) {
        if (step.getHasLowTact() != null) {
            checkRadioGroup(lowTactRadio, step.getHasLowTact());
            if (step.getHasLowTact() == 1) {
                if (step.getLowTactDist() != null)
                    lowDistValue.setText(String.valueOf(step.getLowTactDist()));
                if (step.getLowTactWidth() != null)
                    lowWidthValue.setText(String.valueOf(step.getLowTactWidth()));
                if (step.getLowTactAntiDrift() != null)
                    checkRadioGroup(lowAntiRadio, step.getLowTactAntiDrift());
                if (step.getLowTactSoilContrast() != null)
                    checkRadioGroup(lowTactRadio, step.getLowTactSoilContrast());
                if (step.getLowTactVisualContrast() != null)
                    checkRadioGroup(lowColorRadio, step.getLowTactVisualContrast());
            }
        }

        if (step.getHasHighTact() != null) {
            checkRadioGroup(upTactRadio, step.getHasHighTact());
            if (step.getHasHighTact() == 1) {
                if (step.getHighTactDist() != null)
                    upDistValue.setText(String.valueOf(step.getHighTactDist()));
                if (step.getHighTactWidth() != null)
                    upWidthValue.setText(String.valueOf(step.getHighTactWidth()));
                if (step.getHighTactAntiDrift() != null)
                    checkRadioGroup(upAntiRadio, step.getHighTactAntiDrift());
                if (step.getHighTactSoilContrast() != null)
                    checkRadioGroup(upTactRadio, step.getHighTactSoilContrast());
                if (step.getHighTactVisualContrast() != null)
                    checkRadioGroup(upColorRadio, step.getHighTactVisualContrast());
            }
        }
    }

    private void instantiateTactViews(View view) {
//        TextInputLayout
        lowDistField = view.findViewById(R.id.lower_tact_dist_field);
        lowWidthField = view.findViewById(R.id.lower_tact_width_field);
        upDistField = view.findViewById(R.id.upper_tact_dist_field);
        upWidthField = view.findViewById(R.id.upper_tact_width_field);
//        TextInputEditText
        lowDistValue = view.findViewById(R.id.lower_tact_dist_value);
        lowWidthValue = view.findViewById(R.id.lower_tact_width_value);
        upDistValue = view.findViewById(R.id.upper_tact_dist_value);
        upWidthValue = view.findViewById(R.id.upper_tact_width_value);
//        RadioGroup
        lowTactRadio = view.findViewById(R.id.lower_tact_radio);
        upTactRadio = view.findViewById(R.id.upper_tact_radio);
        lowAntiRadio = view.findViewById(R.id.lower_tact_anti_drift_radio);
        lowFloorRadio = view.findViewById(R.id.lower_tact_soil_contrast_radio);
        lowColorRadio = view.findViewById(R.id.lower_tact_visual_contrast_radio);
        upAntiRadio = view.findViewById(R.id.upper_tact_anti_drift_radio);
        upFloorRadio = view.findViewById(R.id.upper_tact_soil_contrast_radio);
        upColorRadio = view.findViewById(R.id.upper_tact_visual_contrast_radio);
        lowTactRadio.setOnCheckedChangeListener(this::radioListener);
        upTactRadio.setOnCheckedChangeListener(this::radioListener);
//        TextView
        lowError = view.findViewById(R.id.lower_tact_error);
        upError = view.findViewById(R.id.upper_tact_error);
        lowAntiHeader = view.findViewById(R.id.lower_tact_anti_drift_header);
        lowAntiError = view.findViewById(R.id.lower_tact_anti_drift_error);
        lowFloorHeader = view.findViewById(R.id.lower_tact_soil_contrast_header);
        lowFloorError = view.findViewById(R.id.lower_tact_soil_contrast_error);
        lowColorHeader = view.findViewById(R.id.lower_tact_visual_contrast_header);
        lowColorError = view.findViewById(R.id.lower_tact_visual_contrast_error);
        upAntiHeader = view.findViewById(R.id.upper_tact_anti_drift_header);
        upAntiError = view.findViewById(R.id.upper_tact_anti_drift_error);
        upFloorHeader = view.findViewById(R.id.upper_tact_soil_contrast_header);
        upFloorError = view.findViewById(R.id.upper_tact_soil_contrast_error);
        upColorHeader = view.findViewById(R.id.upper_tact_visual_contrast_header);
        upColorError = view.findViewById(R.id.upper_tact_visual_contrast_error);
// ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private boolean noEmptyField() {
        clearErrors();
        int i = 0;
        if (indexRadio(lowTactRadio) == -1) {
            i++;
            lowError.setVisibility(View.VISIBLE);
        } else if (indexRadio(lowTactRadio) == 1) {
            if (TextUtils.isEmpty(lowDistValue.getText())) {
                i++;
                lowDistField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(lowWidthValue.getText())) {
                i++;
                lowWidthField.setError(getString(R.string.req_field_error));
            }
            if (indexRadio(lowAntiRadio) == -1) {
                i++;
                lowAntiError.setVisibility(View.VISIBLE);
            }
            if (indexRadio(lowFloorRadio) == -1) {
                i++;
                lowFloorError.setVisibility(View.VISIBLE);
            }
            if (indexRadio(lowColorRadio) == -1) {
                i++;
                lowColorError.setVisibility(View.VISIBLE);
            }
        }

        if (indexRadio(upTactRadio) == -1) {
            i++;
            upError.setVisibility(View.VISIBLE);
        } else if (indexRadio(upTactRadio) == 1) {
            if (TextUtils.isEmpty(upDistValue.getText())) {
                i++;
                upDistField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(upWidthValue.getText())) {
                i++;
                upWidthField.setError(getString(R.string.req_field_error));
            }
            if (indexRadio(upAntiRadio) == -1) {
                i++;
                upAntiError.setVisibility(View.VISIBLE);
            }
            if (indexRadio(upFloorRadio) == -1) {
                i++;
                upFloorError.setVisibility(View.VISIBLE);
            }
            if (indexRadio(upColorRadio) == -1) {
                i++;
                upColorError.setVisibility(View.VISIBLE);
            }
        }

        return i == 0;
    }

    private void clearErrors() {
        lowError.setVisibility(View.GONE);
        lowDistField.setErrorEnabled(false);
        lowWidthField.setErrorEnabled(false);
        lowAntiError.setVisibility(View.GONE);
        lowFloorError.setVisibility(View.GONE);
        lowColorError.setVisibility(View.GONE);
        upError.setVisibility(View.GONE);
        upDistField.setErrorEnabled(false);
        upWidthField.setErrorEnabled(false);
        upAntiError.setVisibility(View.GONE);
        upFloorError.setVisibility(View.GONE);
        upColorError.setVisibility(View.GONE);
    }

    private SingleStepTactParcel newTactParcel() {
        int low, up;
        Double lowDist = null, lowWidth = null, upDist = null, upWidth = null;
        Integer lowAnti = null, lowFloor = null, lowColor = null, upAnti = null, upFloor = null, upColor = null;

        low = indexRadio(lowTactRadio);
        if (low == 1) {
            lowDist = Double.parseDouble(String.valueOf(lowDistValue.getText()));
            lowWidth = Double.parseDouble(String.valueOf(lowWidthValue.getText()));
            lowAnti = indexRadio(lowAntiRadio);
            lowFloor = indexRadio(lowFloorRadio);
            lowColor = indexRadio(lowColorRadio);
        }
        up = indexRadio(upTactRadio);
        if (up == 1) {
            upDist = Double.parseDouble(String.valueOf(upDistValue.getText()));
            upWidth = Double.parseDouble(String.valueOf(upWidthValue.getText()));
            upAnti = indexRadio(upAntiRadio);
            upFloor = indexRadio(upFloorRadio);
            upColor = indexRadio(upColorRadio);
        }

        return new SingleStepTactParcel(low, lowDist, lowWidth, lowAnti, lowFloor, lowColor, up, upDist, upWidth, upAnti, upFloor, upColor);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);
        if (radio == lowTactRadio) {
            if (index == 1) {
                lowDistField.setVisibility(View.VISIBLE);
                lowWidthField.setVisibility(View.VISIBLE);
                lowAntiHeader.setVisibility(View.VISIBLE);
                lowAntiRadio.setVisibility(View.VISIBLE);
                lowFloorHeader.setVisibility(View.VISIBLE);
                lowFloorRadio.setVisibility(View.VISIBLE);
                lowColorHeader.setVisibility(View.VISIBLE);
                lowColorRadio.setVisibility(View.VISIBLE);
            } else if (index == 0) {
                lowDistValue.setText(null);
                lowWidthValue.setText(null);
                lowDistField.setVisibility(View.GONE);
                lowWidthField.setVisibility(View.GONE);
                lowAntiHeader.setVisibility(View.GONE);
                lowAntiRadio.clearCheck();
                lowAntiRadio.setVisibility(View.GONE);
                lowFloorHeader.setVisibility(View.GONE);
                lowFloorRadio.clearCheck();
                lowFloorRadio.setVisibility(View.GONE);
                lowColorHeader.setVisibility(View.GONE);
                lowColorRadio.clearCheck();
                lowColorRadio.setVisibility(View.GONE);
            }
        } else if (radio == upTactRadio) {
            if (index == 1) {
                upDistField.setVisibility(View.VISIBLE);
                upWidthField.setVisibility(View.VISIBLE);
                upAntiHeader.setVisibility(View.VISIBLE);
                upAntiRadio.setVisibility(View.VISIBLE);
                upFloorHeader.setVisibility(View.VISIBLE);
                upFloorRadio.setVisibility(View.VISIBLE);
                upColorHeader.setVisibility(View.VISIBLE);
                upColorRadio.setVisibility(View.VISIBLE);
            } else if (index == 0) {
                upDistValue.setText(null);
                upWidthValue.setText(null);
                upDistField.setVisibility(View.GONE);
                upWidthField.setVisibility(View.GONE);
                upAntiHeader.setVisibility(View.GONE);
                upAntiRadio.clearCheck();
                upAntiRadio.setVisibility(View.GONE);
                upFloorHeader.setVisibility(View.GONE);
                upFloorRadio.clearCheck();
                upFloorRadio.setVisibility(View.GONE);
                upColorHeader.setVisibility(View.GONE);
                upColorRadio.clearCheck();
                upColorRadio.setVisibility(View.GONE);
            }
        }
    }
}