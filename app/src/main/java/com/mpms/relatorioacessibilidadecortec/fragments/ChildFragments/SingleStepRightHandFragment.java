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
import com.mpms.relatorioacessibilidadecortec.data.parcels.SingleStepHandParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class SingleStepRightHandFragment extends Fragment implements TagInterface, RadioGroupInterface {

    TextInputLayout upHandHeightField, lowHandHeightField, diamField, distField, upEndExtField, upStartExtField, lowEndExtField, lowStartExtField;
    TextInputEditText upHandHeightValue, lowHandHeightValue, diamValue, distValue, upEndExtValue, upStartExtValue, lowEndExtValue, lowStartExtValue;
    RadioGroup lowExtRadio, upExtRadio;
    TextView lowExtError, upExtError;

    ViewModelEntry modelEntry;
    Bundle handBundle;

    public SingleStepRightHandFragment() {
// Required empty public constructor
    }

    public static SingleStepRightHandFragment newInstance() {
        return new SingleStepRightHandFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            handBundle = new Bundle(getArguments());
        else
            handBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_step_two_hand, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateStepView(view);

        if (handBundle.getInt(STEP_ID) > 0)
            modelEntry.getOneSoleStep(handBundle.getInt(STEP_ID)).observe(getViewLifecycleOwner(), this::loadSingleStepHandData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_3, getViewLifecycleOwner(), (key, bundle) -> {
            if (noEmptyFields()) {
                SingleStepHandParcel parcel = newHandParcel();
                bundle.putBoolean(CHILD_DATA_COMPLETE_3, true);
                bundle.putParcelable(CHILD_PARCEL_3, Parcels.wrap(parcel));
            } else
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_3, bundle);
        });

    }

    private void instantiateStepView(View view) {
//        TextInputLayout
        upHandHeightField = view.findViewById(R.id.hand_upper_height_field);
        lowHandHeightField = view.findViewById(R.id.hand_lower_height_field);
        diamField = view.findViewById(R.id.hand_diam_field);
        distField = view.findViewById(R.id.hand_dist_field);
        upEndExtField = view.findViewById(R.id.hand_end_extension_upper_field);
        upStartExtField = view.findViewById(R.id.hand_end_extension_lower_field);
        lowEndExtField = view.findViewById(R.id.hand_start_extension_upper_field);
        lowStartExtField = view.findViewById(R.id.hand_start_extension_lower_field);
//        TextInputEditText
        upHandHeightValue = view.findViewById(R.id.hand_upper_height_value);
        lowHandHeightValue = view.findViewById(R.id.hand_lower_height_value);
        diamValue = view.findViewById(R.id.hand_diam_value);
        distValue = view.findViewById(R.id.hand_dist_value);
        upEndExtValue = view.findViewById(R.id.hand_end_extension_upper_value);
        upStartExtValue = view.findViewById(R.id.hand_start_extension_upper_value);
        lowEndExtValue = view.findViewById(R.id.hand_end_extension_lower_value);
        lowStartExtValue = view.findViewById(R.id.hand_start_extension_lower_value);
//        RadioGroup
        lowExtRadio = view.findViewById(R.id.hand_lower_extension_radio);
        upExtRadio = view.findViewById(R.id.hand_upper_extension_radio);
        lowExtRadio.setOnCheckedChangeListener(this::radioListener);
        upExtRadio.setOnCheckedChangeListener(this::radioListener);
//        TextView
        lowExtError = view.findViewById(R.id.hand_lower_extension_error);
        upExtError = view.findViewById(R.id.hand_upper_extension_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Layout
    }

    private void loadSingleStepHandData(SingleStepEntry step) {

        if (step.getRightHandUpHeight() != null)
            upHandHeightValue.setText(String.valueOf(step.getRightHandUpHeight()));
        if (step.getRightHandDownHeight() != null)
            lowHandHeightValue.setText(String.valueOf(step.getRightHandDownHeight()));
        if (step.getRightHandDiam() != null)
            diamValue.setText(String.valueOf(step.getRightHandDiam()));
        if (step.getRightHandDist() != null)
            distValue.setText(String.valueOf(step.getRightHandDist()));
        if (step.getRightHasLowerExt() != null) {
            checkRadioGroup(lowExtRadio, step.getRightHasLowerExt());
            if (step.getRightHasLowerExt() == 1) {
                if (step.getRightLowerUpLength() != null)
                    upStartExtValue.setText(String.valueOf(step.getRightLowerUpLength()));
                if (step.getRightLowerDownLength() != null)
                    lowStartExtValue.setText(String.valueOf(step.getRightLowerDownLength()));
            }
        }
        if (step.getRightHasUpperExt() != null) {
            checkRadioGroup(upExtRadio, step.getRightHasUpperExt());
            if (step.getRightHasLowerExt() == 1) {
                if (step.getRightUpperUpLength() != null)
                    upEndExtValue.setText(String.valueOf(step.getRightUpperUpLength()));
                if (step.getRightLowerDownLength() != null)
                    lowEndExtValue.setText(String.valueOf(step.getRightUpperDownLength()));
            }
        }
    }

    private boolean noEmptyFields() {
        clearErrors();
        int i = 0;
        if (TextUtils.isEmpty(upHandHeightValue.getText())) {
            i++;
            upHandHeightField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(diamValue.getText())) {
            i++;
            diamField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(distValue.getText())) {
            i++;
            distField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(lowExtRadio) == -1) {
            i++;
            lowExtError.setVisibility(View.VISIBLE);
        } else if (indexRadio(lowExtRadio) == 1) {
            if (TextUtils.isEmpty(upStartExtValue.getText())) {
                i++;
                upStartExtField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(lowStartExtValue.getText())) {
                i++;
                lowStartExtField.setError(getString(R.string.req_field_error));
            }
        }

        if (indexRadio(upExtRadio) == -1) {
            i++;
            upExtError.setVisibility(View.VISIBLE);
        } else if (indexRadio(upExtRadio) == 1) {
            if (TextUtils.isEmpty(upEndExtValue.getText())) {
                i++;
                upEndExtField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(lowEndExtValue.getText())) {
                i++;
                lowEndExtField.setError(getString(R.string.req_field_error));
            }
        }

        return i == 0;
    }

    private void clearErrors() {
        upHandHeightField.setErrorEnabled(false);
        diamField.setErrorEnabled(false);
        distField.setErrorEnabled(false);
        upStartExtField.setErrorEnabled(false);
        lowStartExtField.setErrorEnabled(false);
        upEndExtField.setErrorEnabled(false);
        lowEndExtField.setErrorEnabled(false);
        lowExtError.setVisibility(View.GONE);
        upExtError.setVisibility(View.GONE);
    }

    private SingleStepHandParcel newHandParcel() {
        double highHand, handDiam;
        Double lowHand = null, handDist = null, lowUpExtLength = null, lowDownExtLength = null, highDownExtLength = null, highUpExtLength = null;
        int hasLowExtension, hasHighExtension;

        highHand = Double.parseDouble(String.valueOf(upHandHeightValue.getText()));
        if (!TextUtils.isEmpty(lowHandHeightValue.getText()))
            lowHand = Double.parseDouble(String.valueOf(lowHandHeightValue.getText()));
        handDiam = Double.parseDouble(String.valueOf(diamValue.getText()));
        if (!TextUtils.isEmpty(distValue.getText()))
            handDist = Double.parseDouble(String.valueOf(distValue.getText()));
        hasLowExtension = indexRadio(lowExtRadio);
        if (hasLowExtension == 1) {
            lowDownExtLength = Double.parseDouble(String.valueOf(lowStartExtValue.getText()));
            lowUpExtLength = Double.parseDouble(String.valueOf(upStartExtValue.getText()));
        }
        hasHighExtension = indexRadio(upExtRadio);
        if (hasHighExtension == 1) {
            highDownExtLength = Double.parseDouble(String.valueOf(lowEndExtValue.getText()));
            highUpExtLength = Double.parseDouble(String.valueOf(upEndExtValue.getText()));
        }

        return new SingleStepHandParcel(highHand, lowHand, handDiam, handDist, hasLowExtension, lowUpExtLength, lowDownExtLength, hasHighExtension, highDownExtLength,
                highUpExtLength);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == lowExtRadio) {
            if (index == 1) {
                lowEndExtField.setVisibility(View.VISIBLE);
                lowStartExtField.setVisibility(View.VISIBLE);
            } else {
                lowEndExtValue.setText(null);
                lowStartExtValue.setText(null);
                lowEndExtField.setVisibility(View.GONE);
                lowStartExtField.setVisibility(View.GONE);
            }
        } else {
            if (index == 1) {
                upEndExtField.setVisibility(View.VISIBLE);
                upStartExtField.setVisibility(View.VISIBLE);
            } else {
                upEndExtValue.setText(null);
                upStartExtValue.setText(null);
                upEndExtField.setVisibility(View.GONE);
                upStartExtField.setVisibility(View.GONE);
            }
        }
    }
}
