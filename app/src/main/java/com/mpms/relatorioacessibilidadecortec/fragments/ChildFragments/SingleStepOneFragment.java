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
import com.mpms.relatorioacessibilidadecortec.data.parcels.SingleStepInfoParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class SingleStepOneFragment extends Fragment implements TagInterface, RadioGroupInterface {

    TextInputLayout mirrorHeightField, handHeightField, handLengthField, handDiamField, handDistField;
    TextInputEditText mirrorHeightValue, handHeightValue, handLengthValue, handDiamValue, handDistValue;
    TextView handHeader, handError;
    RadioGroup handRadio;

    ViewModelEntry modelEntry;
    Bundle oneBundle;

    public SingleStepOneFragment() {
        // Required empty public constructor
    }

    public static SingleStepOneFragment newInstance() {
        return new SingleStepOneFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            oneBundle = new Bundle(getArguments());
        else
            oneBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_step_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateOneStepView(view);

        if (oneBundle.getInt(STEP_ID) > 0)
            modelEntry.getOneSoleStep(oneBundle.getInt(STEP_ID)).observe(getViewLifecycleOwner(), this::loadSingleStepOneData);


        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, getViewLifecycleOwner(), (key, bundle) -> {
            if (noEmptyFields()) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                SingleStepInfoParcel parcel = newStepParcel();
                bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
            }
            else
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);

        });
    }

    private void instantiateOneStepView(View view) {
//        TextInputLayout
        mirrorHeightField = view.findViewById(R.id.single_step_one_mirror_height_field);
        handHeightField = view.findViewById(R.id.single_step_one_handrail_height_field);
        handLengthField = view.findViewById(R.id.single_step_one_handrail_length_field);
        handDiamField = view.findViewById(R.id.single_step_one_handrail_diam_field);
        handDistField = view.findViewById(R.id.single_step_one_handrail_dist_field);
//        TextInputEditText
        mirrorHeightValue = view.findViewById(R.id.single_step_one_mirror_height_value);
        handHeightValue = view.findViewById(R.id.single_step_one_handrail_height_value);
        handLengthValue = view.findViewById(R.id.single_step_one_handrail_length_value);
        handDiamValue = view.findViewById(R.id.single_step_one_handrail_diam_value);
        handDistValue = view.findViewById(R.id.single_step_one_handrail_dist_value);
//        TextView
        handHeader = view.findViewById(R.id.single_step_one_has_handrail_header);
        handError = view.findViewById(R.id.single_step_one_has_handrail_error);
//        RadioGroup
        handRadio = view.findViewById(R.id.single_step_one_has_handrail_radio);
        handRadio.setOnCheckedChangeListener(this::radioListener);
//                ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private boolean noEmptyFields() {
        clearErrors();
        int i = 0;
        if (TextUtils.isEmpty(mirrorHeightValue.getText())) {
            i++;
            mirrorHeightField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(handRadio) == -1) {
            i++;
            handError.setVisibility(View.VISIBLE);
        } else if (indexRadio(handRadio) == 1) {
            if (TextUtils.isEmpty(handHeightValue.getText())) {
                i++;
                handHeightField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(handLengthValue.getText())) {
                i++;
                handLengthField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(handDiamValue.getText())) {
                i++;
                handDiamField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(handDistValue.getText())) {
                i++;
                handDistField.setError(getString(R.string.req_field_error));
            }
        }
        return i == 0;
    }

    private void clearErrors() {
        handError.setVisibility(View.GONE);
        mirrorHeightField.setErrorEnabled(false);
        handHeightField.setErrorEnabled(false);
        handLengthField.setErrorEnabled(false);
        handDiamField.setErrorEnabled(false);
        handDistField.setErrorEnabled(false);
    }

    private SingleStepInfoParcel newStepParcel() {
        double mirror;
        int hasHand;
        Double handHeight = null, handLength = null, handDiam = null, handDist = null;

        mirror = Double.parseDouble(String.valueOf(mirrorHeightValue.getText()));
        hasHand = indexRadio(handRadio);
        if (hasHand == 1) {
            handHeight = Double.parseDouble(String.valueOf(handHeightValue.getText()));
            handLength = Double.parseDouble(String.valueOf(handLengthValue.getText()));
            handDiam = Double.parseDouble(String.valueOf(handDiamValue.getText()));
            handDist = Double.parseDouble(String.valueOf(handDistValue.getText()));
        }

        return new SingleStepInfoParcel(mirror, null, null, null, hasHand, handHeight, null, handLength, handDiam, handDist,
                null, null, null,null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null);
    }

    private void loadSingleStepOneData(SingleStepEntry step) {
        mirrorHeightValue.setText(String.valueOf(step.getFirstMirror()));
        checkRadioGroup(handRadio, step.getHasLeftHand());
        if (step.getHasLeftHand() == 1) {
            if (step.getLeftHandUpHeight() != null)
                handHeightValue.setText(String.valueOf(step.getLeftHandUpHeight()));
            if (step.getLeftHandLength() != null)
                handLengthValue.setText(String.valueOf(step.getLeftHandLength()));
            if (step.getLeftHandDiam() != null)
                handDiamValue.setText(String.valueOf(step.getLeftHandDiam()));
            if (step.getLeftHandDist() != null)
                handDistValue.setText(String.valueOf(step.getLeftHandDist()));
        }
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        if (indexRadio(radio) == 1) {
            handHeightField.setVisibility(View.VISIBLE);
            handLengthField.setVisibility(View.VISIBLE);
            handDiamField.setVisibility(View.VISIBLE);
            handDistField.setVisibility(View.VISIBLE);
        } else {
            handHeightValue.setText(null);
            handLengthValue.setText(null);
            handDiamValue.setText(null);
            handDistValue.setText(null);
            handHeightField.setVisibility(View.GONE);
            handLengthField.setVisibility(View.GONE);
            handDiamField.setVisibility(View.GONE);
            handDistField.setVisibility(View.GONE);
        }
    }
}