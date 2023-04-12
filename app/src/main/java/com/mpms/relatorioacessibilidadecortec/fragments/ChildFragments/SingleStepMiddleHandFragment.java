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

public class SingleStepMiddleHandFragment extends Fragment implements TagInterface, RadioGroupInterface {

    TextInputLayout upHandHeightField, lowHandHeightField, diamField, distField, upEndExtField, upStartExtField, lowEndExtField, lowStartExtField;
    TextInputEditText upHandHeightValue, lowHandHeightValue, diamValue, distValue, upEndExtValue, upStartExtValue, lowEndExtValue, lowStartExtValue;
    RadioGroup lowExtRadio, upExtRadio;
    TextView lowExtError, upExtError;

    ViewModelEntry modelEntry;
    Bundle handBundle;

    public SingleStepMiddleHandFragment() {
        // Required empty public constructor
    }

    public static SingleStepMiddleHandFragment newInstance() {
        return new SingleStepMiddleHandFragment();
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

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_4, getViewLifecycleOwner(), (key, bundle) -> {
            if (noEmptyFields()) {
                SingleStepHandParcel parcel = newHandParcel();
                bundle.putBoolean(CHILD_DATA_COMPLETE_4, true);
                bundle.putParcelable(CHILD_PARCEL_4, Parcels.wrap(parcel));
            } else
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_4, bundle);
        });

    }

    private void instantiateStepView(View view) {
//        TextInputLayout
        upHandHeightField = view.findViewById(R.id.hand_upper_height_field);
        lowHandHeightField = view.findViewById(R.id.hand_lower_height_field);
        diamField = view.findViewById(R.id.hand_diam_field);
        distField = view.findViewById(R.id.hand_dist_field);
        distField.setVisibility(View.GONE);
        upEndExtField = view.findViewById(R.id.hand_end_extension_upper_field);
        upStartExtField = view.findViewById(R.id.hand_end_extension_lower_field);
        lowEndExtField = view.findViewById(R.id.hand_start_extension_upper_field);
        lowStartExtField = view.findViewById(R.id.hand_start_extension_lower_field);
//        TextInputEditText
        upHandHeightValue = view.findViewById(R.id.hand_upper_height_value);
        lowHandHeightValue = view.findViewById(R.id.hand_lower_height_value);
        diamValue = view.findViewById(R.id.hand_diam_value);
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
        if (step.getMiddleHandUpHeight() != null)
            upHandHeightValue.setText(String.valueOf(step.getMiddleHandUpHeight()));
        if (step.getMiddleHandDownHeight() != null)
            lowHandHeightValue.setText(String.valueOf(step.getMiddleHandDownHeight()));
        if (step.getMiddleHandDiam() != null)
            diamValue.setText(String.valueOf(step.getMiddleHandDiam()));
        if (step.getMiddleHasLowerExt() != null) {
            checkRadioGroup(lowExtRadio, step.getMiddleHasLowerExt());
            if (step.getMiddleHasLowerExt() == 1) {
                if (step.getMiddleLowerUpLength() != null)
                    upStartExtValue.setText(String.valueOf(step.getMiddleLowerUpLength()));
                if (step.getMiddleLowerDownLength() != null)
                    lowStartExtValue.setText(String.valueOf(step.getMiddleLowerDownLength()));
            }
        }
        if (step.getMiddleHasUpperExt() != null) {
            checkRadioGroup(upExtRadio, step.getMiddleHasUpperExt());
            if (step.getMiddleHasLowerExt() == 1) {
                if (step.getMiddleUpperUpLength() != null)
                    upEndExtValue.setText(String.valueOf(step.getMiddleUpperUpLength()));
                if (step.getMiddleLowerDownLength() != null)
                    lowEndExtValue.setText(String.valueOf(step.getMiddleUpperDownLength()));
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
        Double lowHand = null, lowUpExtLength = null, lowDownExtLength = null, highDownExtLength = null, highUpExtLength = null;
        int hasLowExtension, hasHighExtension;

        highHand = Double.parseDouble(String.valueOf(upHandHeightValue.getText()));
        if (!TextUtils.isEmpty(lowHandHeightValue.getText()))
            lowHand = Double.parseDouble(String.valueOf(lowHandHeightValue.getText()));
        handDiam = Double.parseDouble(String.valueOf(diamValue.getText()));
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

        return new SingleStepHandParcel(highHand, lowHand, handDiam, null, hasLowExtension, lowUpExtLength, lowDownExtLength, hasHighExtension, highDownExtLength,
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
