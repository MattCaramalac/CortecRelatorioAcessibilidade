package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.mpms.relatorioacessibilidadecortec.data.parcels.SingleStepInfoParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;


public class SingleStepTwoFragment extends Fragment implements RadioGroupInterface, TagInterface, ScrollEditText {

    TextInputLayout fistMirrorField, stepLengthField, secondMirrorField, stepWidthField;
    TextInputEditText fistMirrorValue, stepLengthValue, secondMirrorValue, stepWidthValue;
    RadioGroup leftRadio, rightRadio, middleRadio;
    TextView leftError, rightError, middleError;
    FrameLayout leftFrame, rightFrame, middleFrame;

    ViewModelEntry modelEntry;
    Bundle twoBundle;

    boolean dataComplete = true;
    int stepID = 0;

    SingleStepHandParcel handLeft = null, handRight = null, handMiddle = null;

    public SingleStepTwoFragment() {
        // Required empty public constructor
    }


    public static SingleStepTwoFragment newInstance() {
        return new SingleStepTwoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            twoBundle = new Bundle(getArguments());
        else
            twoBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_step_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateStepTwoView(view);

        if (twoBundle.getInt(STEP_ID) > 0) {
            stepID = twoBundle.getInt(STEP_ID);
            modelEntry.getOneSoleStep(stepID).observe(getViewLifecycleOwner(), this::loadSingleStepTwoData);
        };

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, getViewLifecycleOwner(), (key, bundle) -> {
            dataComplete = true;
            if (indexRadio(leftRadio) == 1) {
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_2, bundle);
            } else if (indexRadio(rightRadio) == 1) {
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_3, bundle);
            } else if (indexRadio(middleRadio) == 1) {
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_4, bundle);
            } else {
                if (noEmptyFields()) {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                    SingleStepInfoParcel parcel = newStepParcel(handLeft, handRight, handMiddle);
                    bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
                } else
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
            }
        });

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER_2, getViewLifecycleOwner(), (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE_2))
                handLeft = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL_2));
            else
                dataComplete = false;


            if (indexRadio(rightRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_3, bundle);
            else if (indexRadio(middleRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_4, bundle);
            else {
                if (noEmptyFields() && dataComplete) {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                    SingleStepInfoParcel parcel = newStepParcel(handLeft, handRight, handMiddle);
                    bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
                } else
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
            }

        });

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER_3, getViewLifecycleOwner(), (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE_3))
                handRight = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL_3));
            else
                dataComplete = false;

            if (indexRadio(middleRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_4, bundle);
            else {
                if (noEmptyFields() && dataComplete) {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                    SingleStepInfoParcel parcel = newStepParcel(handLeft, handRight, handMiddle);
                    bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
                } else
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
            }

        });

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER_4, getViewLifecycleOwner(), (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE_4))
                handMiddle = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL_4));
            else
                dataComplete = false;

            if (noEmptyFields() && dataComplete) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                SingleStepInfoParcel parcel = newStepParcel(handLeft, handRight, handMiddle);
                bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
            } else
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);


        });
    }

    private void instantiateStepTwoView(View view) {
//        TextInputLayout
        fistMirrorField = view.findViewById(R.id.double_step_mirror_one_height_field);
        stepLengthField = view.findViewById(R.id.double_step_length_field);
        secondMirrorField = view.findViewById(R.id.double_step_mirror_two_height_field);
        stepWidthField = view.findViewById(R.id.double_step_width_field);
//        TextInputEditText
        fistMirrorValue = view.findViewById(R.id.double_step_mirror_one_height_value);
        stepLengthValue = view.findViewById(R.id.double_step_length_value);
        secondMirrorValue = view.findViewById(R.id.double_step_mirror_two_height_value);
        stepWidthValue = view.findViewById(R.id.double_step_width_value);
//        RadioGroup
        leftRadio = view.findViewById(R.id.left_handrail_radio);
        rightRadio = view.findViewById(R.id.right_handrail_radio);
        middleRadio = view.findViewById(R.id.middle_handrail_radio);
        leftRadio.setOnCheckedChangeListener(this::radioListener);
        rightRadio.setOnCheckedChangeListener(this::radioListener);
        middleRadio.setOnCheckedChangeListener(this::radioListener);
//        TextView
        leftError = view.findViewById(R.id.left_handrail_error);
        rightError = view.findViewById(R.id.right_handrail_error);
        middleError = view.findViewById(R.id.middle_handrail_error);
//        FrameLayout
        leftFrame = view.findViewById(R.id.left_hand_frame);
        rightFrame = view.findViewById(R.id.right_hand_frame);
        middleFrame = view.findViewById(R.id.middle_hand_frame);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private boolean noEmptyFields() {
        clearErrors();
        int i = 0;
        if (TextUtils.isEmpty(fistMirrorValue.getText())) {
            i++;
            fistMirrorField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(stepLengthValue.getText())) {
            i++;
            stepLengthField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(secondMirrorValue.getText())) {
            i++;
            secondMirrorField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(stepWidthValue.getText())) {
            i++;
            stepWidthField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(leftRadio) == -1) {
            i++;
            leftError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(rightRadio) == -1) {
            i++;
            rightError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(middleRadio) == -1) {
            i++;
            middleError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void loadSingleStepTwoData(SingleStepEntry step) {
        fistMirrorValue.setText(String.valueOf(step.getFirstMirror()));
        if (step.getStepLength() != null)
            stepLengthValue.setText(String.valueOf(step.getStepLength()));
        if (step.getSecondMirror() != null)
            secondMirrorValue.setText(String.valueOf(step.getSecondMirror()));
        if (step.getStepWidth() != null)
            stepWidthValue.setText(String.valueOf(step.getStepWidth()));

        checkRadioGroup(leftRadio, step.getHasLeftHand());
        if (step.getHasLeftHand() == 1) {

        }
        if (step.getHasRightHand() != null) {
            checkRadioGroup(rightRadio, step.getHasRightHand());
            if (step.getHasRightHand() == 1) {

            }
        }
        if (step.getHasMiddleHand() != null) {
            checkRadioGroup(middleRadio, step.getHasMiddleHand());
            if (step.getHasMiddleHand() == 1) {

            }
        }

    }

    private void clearErrors() {
        fistMirrorField.setErrorEnabled(false);
        stepLengthField.setErrorEnabled(false);
        secondMirrorField.setErrorEnabled(false);
        stepWidthField.setErrorEnabled(false);
        leftError.setVisibility(View.GONE);
        rightError.setVisibility(View.GONE);
        middleError.setVisibility(View.GONE);
    }

    private SingleStepInfoParcel newStepParcel(SingleStepHandParcel left, SingleStepHandParcel right, SingleStepHandParcel middle) {
        Double leftHigh = null, leftLow = null, leftDiam = null, leftDist = null,
                leftLowDownExtLength = null, leftLowUpExtLength = null, leftHighDownExtLength = null, leftHighUpExtLength = null, rightHigh = null, rightLow = null,
                rightDiam = null, rightDist = null, rightLowDownExtLength = null, rightLowUpExtLength = null, rightHighDownExtLength = null, rightHighUpExtLength = null,
                middleHigh = null, middleLow = null, middleDiam = null, middleLowDownExtLength = null, middleLowUpExtLength = null, middleHighDownExtLength = null,
                middleHighUpExtLength = null;
        double firstMirror, stepLength, secondMirror, stepWidth;
        Integer leftLowExt = null, leftHighExt = null, rightLowExt = null, rightHighExt = null, middleLowExt = null, middleHighExt = null;
        int hasLeft, hasRight, hasMiddle;

        firstMirror = Double.parseDouble(String.valueOf(fistMirrorValue.getText()));
        stepLength = Double.parseDouble(String.valueOf(stepLengthValue.getText()));
        secondMirror = Double.parseDouble(String.valueOf(secondMirrorValue.getText()));
        stepWidth = Double.parseDouble(String.valueOf(stepWidthValue.getText()));
        hasLeft = indexRadio(leftRadio);
        if (hasLeft == 1 && left != null) {
            leftHigh = left.getHighHand();
            leftLow = left.getLowHand();
            leftDiam = left.getHandDiam();
            leftDist = left.getHandDist();
            leftLowExt = left.getHasLowExtension();
            if (leftLowExt == 1) {
                leftLowDownExtLength = left.getLowDownExtLength();
                leftLowUpExtLength = left.getLowUpExtLength();
            }
            leftHighExt = left.getHasHighExtension();
            if (leftHighExt == 1) {
                leftHighDownExtLength = left.getHighDownExtLength();
                leftHighUpExtLength = left.getHighUpExtLength();
            }
        }
        hasRight = indexRadio(rightRadio);
        if (hasRight == 1 && right != null) {
            rightHigh = right.getHighHand();
            rightLow = right.getLowHand();
            rightDiam = right.getHandDiam();
            rightDist = right.getHandDist();
            rightLowExt = right.getHasLowExtension();
            if (rightLowExt == 1) {
                rightLowDownExtLength = right.getLowDownExtLength();
                rightLowUpExtLength = right.getLowUpExtLength();
            }
            rightHighExt = right.getHasHighExtension();
            if (rightHighExt == 1) {
                rightHighDownExtLength = right.getHighDownExtLength();
                rightHighUpExtLength = right.getHighUpExtLength();
            }
        }
        hasMiddle = indexRadio(middleRadio);
        if (hasMiddle == 1 && middle != null) {
            middleHigh = middle.getHighHand();
            middleLow = middle.getLowHand();
            middleDiam = middle.getHandDiam();
            middleLowExt = middle.getHasLowExtension();
            if (middleLowExt == 1) {
                middleLowDownExtLength = middle.getLowDownExtLength();
                middleLowUpExtLength = middle.getLowUpExtLength();
            }
            middleHighExt = middle.getHasHighExtension();
            if (middleHighExt == 1) {
                middleHighDownExtLength = middle.getHighDownExtLength();
                middleHighUpExtLength = middle.getHighUpExtLength();
            }
        }

        return new SingleStepInfoParcel(firstMirror, stepLength, secondMirror, stepWidth, hasLeft, leftHigh, leftLow, null, leftDiam, leftDist, leftLowExt, leftLowUpExtLength,
                leftLowDownExtLength, leftHighExt, leftHighDownExtLength, leftHighUpExtLength, hasRight, rightHigh, rightLow, rightDiam, rightDist, rightLowExt, rightLowUpExtLength,
                rightLowDownExtLength, rightHighExt, rightHighDownExtLength, rightHighUpExtLength, hasMiddle, middleHigh, middleLow, middleDiam, middleLowExt, middleLowUpExtLength,
                middleLowDownExtLength, middleHighExt, middleHighDownExtLength, middleHighUpExtLength);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);
        Fragment fragment;
        if (radio == leftRadio) {
            if (index == 1) {
                fragment = new SingleStepLeftHandFragment();
                fragment.setArguments(twoBundle);
                getChildFragmentManager().beginTransaction().replace(leftFrame.getId(), fragment).commit();
            }
            else
                removeFragments(leftFrame);
        } else if (radio == rightRadio) {
            if (index == 1) {
                fragment = new SingleStepRightHandFragment();
                fragment.setArguments(twoBundle);
                getChildFragmentManager().beginTransaction().replace(rightFrame.getId(), fragment).commit();
            }
            else
                removeFragments(rightFrame);
        } else {
            if (index == 1) {
                fragment = new SingleStepMiddleHandFragment();
                fragment.setArguments(twoBundle);
                getChildFragmentManager().beginTransaction().replace(middleFrame.getId(), fragment).commit();
            }
            else
                removeFragments(middleFrame);
        }
    }

    private void removeFragments(View view) {
        Fragment fragment = getChildFragmentManager().findFragmentById(view.getId());
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();

    }
}