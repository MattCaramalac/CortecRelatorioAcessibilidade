package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SingleStepEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SingleStepInfoParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SingleStepTactParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SingleStepOneFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SingleStepTwoFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.StepStairsTactFloorFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class SingleStepFragment extends Fragment implements ScrollEditText, TagInterface, RadioGroupInterface {

    TextInputLayout stepLocationField, stepSignWidthField, obsField, photoField;
    TextInputEditText stepLocationValue, stepSignWidthValue, obsValue, photoValue;
    TextView stepQntError, stepSignError, stepSignAppHeader, stepSignAppError, stepMirrorHeader, stepMirrorError, tactFloorHeader, tactFloorError;
    RadioGroup stepQntRadio, stepSignRadio, stepSignAppRadio, stepMirrorRadio, tactFloorRadio;
    FrameLayout stepQntFrame, tactFloorFrame;
    MaterialButton cancelStep, saveStep;

    ViewModelEntry modelEntry;

    Bundle stepBundle;
    SingleStepInfoParcel stepInfo = null;
    SingleStepTactParcel tactInfo = null;
    boolean dataComplete = true;

    public SingleStepFragment() {
        // Required empty public constructor
    }

    public static SingleStepFragment newInstance() {
        return new SingleStepFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            stepBundle = new Bundle(getArguments());
        else
            stepBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateStepViews(view);

        if (stepBundle.getInt(STEP_ID) > 0)
            modelEntry.getOneSoleStep(stepBundle.getInt(STEP_ID)).observe(getViewLifecycleOwner(), this::loadStepData);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, getViewLifecycleOwner(), (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE))
                stepInfo = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
            else
                dataComplete = false;

            if (indexRadio(tactFloorRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_2, stepBundle);
            else if (noEmptyFields() && dataComplete) {
                SingleStepEntry step = newStep(stepInfo, tactInfo, bundle);
                if (bundle.getInt(STEP_ID) > 0) {
                    step.setStepID(bundle.getInt(STEP_ID));
                    ViewModelEntry.updateSoleStep(step);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                } else {
                    ViewModelEntry.insertSoleStep(step);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                }
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER_2, getViewLifecycleOwner(), (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE_2))
                tactInfo = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL_2));
            else
                dataComplete = false;

            if (noEmptyFields() && dataComplete) {
                SingleStepEntry step = newStep(stepInfo, tactInfo, bundle);
                if (bundle.getInt(STEP_ID) > 0) {
                    step.setStepID(bundle.getInt(STEP_ID));
                    ViewModelEntry.updateSoleStep(step);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                } else {
                    ViewModelEntry.insertSoleStep(step);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                }
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelStep.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveStep.setOnClickListener(v -> {
            dataComplete = true;
            if (indexRadio(stepQntRadio) != -1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, stepBundle);
            else if (indexRadio(tactFloorRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_2, stepBundle);
            else {
                noEmptyFields();
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void instantiateStepViews(View view) {
//        TextInputLayout
        stepLocationField = view.findViewById(R.id.single_step_location_field);
        stepSignWidthField = view.findViewById(R.id.single_step_signal_width_field);
        obsField = view.findViewById(R.id.single_step_obs_field);
        photoField = view.findViewById(R.id.single_step_photo_field);
//        TextInputEditText
        stepLocationValue = view.findViewById(R.id.single_step_location_value);
        stepSignWidthValue = view.findViewById(R.id.single_step_signal_width_value);
        obsValue = view.findViewById(R.id.single_step_obs_value);
        photoValue = view.findViewById(R.id.single_step_photo_value);
//        TextView
        stepQntError = view.findViewById(R.id.single_step_qnt_error);
        stepSignError = view.findViewById(R.id.single_step_signal_error);
        stepSignAppHeader = view.findViewById(R.id.single_step_entire_signal_header);
        stepSignAppError = view.findViewById(R.id.single_step_entire_signal_error);
        stepMirrorHeader = view.findViewById(R.id.single_step_mirror_signal_header);
        stepMirrorError = view.findViewById(R.id.single_step_mirror_signal_error);
        tactFloorHeader = view.findViewById(R.id.single_step_tact_header);
        tactFloorError = view.findViewById(R.id.single_step_tact_error);

//        RadioGroup
        stepQntRadio = view.findViewById(R.id.single_step_qnt_radio);
        stepSignRadio = view.findViewById(R.id.single_step_signal_radio);
        stepSignAppRadio = view.findViewById(R.id.single_step_entire_signal_radio);
        stepMirrorRadio = view.findViewById(R.id.single_step_mirror_signal_radio);
        tactFloorRadio = view.findViewById(R.id.single_step_tact_radio);
        stepQntRadio.setOnCheckedChangeListener(this::radioListener);
        stepSignRadio.setOnCheckedChangeListener(this::radioListener);
        tactFloorRadio.setOnCheckedChangeListener(this::radioListener);
//        FrameLayout
        stepQntFrame = view.findViewById(R.id.single_step_frame);
        tactFloorFrame = view.findViewById(R.id.single_step_tact_frame);
//        MaterialButton
        saveStep = view.findViewById(R.id.save_single_step);
        cancelStep = view.findViewById(R.id.cancel_single_step);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void loadStepData(SingleStepEntry step) {
        if (step.getStepLocation() != null)
            stepLocationValue.setText(step.getStepLocation());
        checkRadioGroup(stepQntRadio, step.getStepQnt() - 1);
        checkRadioGroup(stepSignRadio, step.getStepHasSign());
        if (step.getStepHasSign() == 1) {
            if (step.getStepSignWidth() != null)
                stepSignWidthValue.setText(String.valueOf(step.getStepSignWidth()));
            if (step.getStepSignFullApp() != null)
                checkRadioGroup(stepSignAppRadio, step.getStepSignFullApp());
            if (step.getStepSignMirrorStep() != null)
                checkRadioGroup(stepMirrorRadio, step.getStepSignMirrorStep());
        }
        checkRadioGroup(tactFloorRadio, step.getStepHasTactSign());
//        if (step.getStepHasTactSign() == 1)
//            getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA_2, stepBundle);

        if (step.getStepObs() != null)
            obsValue.setText(step.getStepObs());
        if (step.getStepPhoto() != null)
            photoValue.setText(step.getStepPhoto());

        getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, stepBundle);
    }

    private boolean noEmptyFields() {
        int i = 0;
        clearErrors();
        if (TextUtils.isEmpty(stepLocationValue.getText())) {
            i++;
            stepLocationField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(stepQntRadio) == -1) {
            i++;
            stepQntError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(stepSignRadio) == -1) {
            i++;
            stepSignError.setVisibility(View.VISIBLE);
        } else if (indexRadio(stepSignRadio) == 1) {
            if (TextUtils.isEmpty(stepSignWidthValue.getText())) {
                i++;
                stepSignWidthField.setError(getString(R.string.req_field_error));
            }
            if (indexRadio(stepSignAppRadio) == -1) {
                i++;
                stepSignAppError.setVisibility(View.VISIBLE);
            }
            if (indexRadio(stepMirrorRadio) == -1) {
                i++;
                stepMirrorError.setVisibility(View.VISIBLE);
            }

        }
        if (indexRadio(tactFloorRadio) == -1) {
            i++;
            tactFloorError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void clearErrors() {
        stepLocationField.setErrorEnabled(false);
        stepSignWidthField.setErrorEnabled(false);
        stepQntError.setVisibility(View.GONE);
        stepSignAppError.setVisibility(View.GONE);
        stepMirrorError.setVisibility(View.GONE);
        tactFloorError.setVisibility(View.GONE);
    }

    private SingleStepEntry newStep(SingleStepInfoParcel step, SingleStepTactParcel tact, Bundle bundle) {
        String stepLocation, stepObs = null, stepPhoto = null;
        int stepQnt, hasLeftHand = 0, stepHasSign, stepHasTactSign;
        double firstMirror = 0;
        Integer circID = null, roomID = null, leftHasLowerExt = null, leftHasUpperExt = null, hasRightHand = null, rightHasLowerExt = null, rightHasUpperExt = null,
                hasMiddleHand = null, middleHasLowerExt = null, middleHasUpperExt = null, stepSignFullApp = null, stepSignMirrorStep = null, hasLowTact = null,
                lowTactAntiDrift = null, lowTactSoilContrast = null, lowTactVisualContrast = null, hasHighTact = null, highTactAntiDrift = null, highTactSoilContrast = null,
                highTactVisualContrast = null;
        Double stepLength = null, secondMirror = null, stepWidth = null, leftHandUpHeight = null, leftHandDownHeight = null, leftHandLength = null, leftHandDiam = null,
                leftHandDist = null, leftLowerUpLength = null, leftLowerDownLength = null, leftUpperUpLength = null, leftUpperDownLength = null, rightHandUpHeight = null,
                rightHandDownHeight = null, rightHandLength = null, rightHandDiam = null, rightHandDist = null, rightLowerUpLength = null, rightLowerDownLength = null,
                rightUpperUpLength = null, rightUpperDownLength = null, middleHandUpHeight = null, middleHandDownHeight = null, middleHandLength = null, middleHandDiam = null,
                middleLowerUpLength = null, middleLowerDownLength = null, middleUpperUpLength = null, middleUpperDownLength = null, stepSignWidth = null, lowTactDist = null,
                lowTactWidth = null, highTactDist = null, highTactWidth = null;

        if (bundle.getInt(CIRC_ID) > 0)
            circID = bundle.getInt(CIRC_ID);
        else if (bundle.getInt(AMBIENT_ID) > 0)
            roomID = bundle.getInt(AMBIENT_ID);

        stepLocation = String.valueOf(stepLocationValue.getText());
        stepQnt = (indexRadio(stepQntRadio) + 1);

        if (step != null) {
            if (stepQnt == 1) {
                firstMirror = step.getFirstMirror();
                hasLeftHand = step.getHasLeftHand();
                if (hasLeftHand == 1) {
                    leftHandUpHeight = step.getLeftHighHand();
                    leftHandLength = step.getLeftHandLength();
                    leftHandDiam = step.getLeftHandDiam();
                    leftHandDist = step.getLeftHandDist();
                }
            }
            else if (stepQnt == 2) {
                firstMirror = step.getFirstMirror();
                stepLength = step.getFirstStep();
                secondMirror = step.getSecondMirror();
                stepWidth = step.getStepWidth();
                hasLeftHand = step.getHasLeftHand();
                if (hasLeftHand == 1) {
                    leftHandUpHeight = step.getLeftHighHand();
                    leftHandDownHeight = step.getLeftLowHand();
                    leftHandDiam = step.getLeftHandDiam();
                    leftHandDist = step.getLeftHandDist();
                    leftHasLowerExt = step.getLeftHasLowExtension();
                    if (leftHasLowerExt == 1) {
                        leftLowerUpLength = step.getLeftLowUpExtLength();
                        leftLowerDownLength = step.getLeftLowDownExtLength();
                    }
                    leftHasUpperExt = step.getLeftHasHighExtension();
                    if (leftHasUpperExt == 1) {
                        leftUpperUpLength = step.getLeftHighUpExtLength();
                        leftUpperDownLength = step.getLeftHighDownExtLength();
                    }
                }

                hasRightHand = step.getHasRightHand();
                if (hasRightHand == 1) {
                    rightHandUpHeight = step.getRightHighHand();
                    rightHandDownHeight = step.getRightLowHand();
                    rightHandDiam = step.getRightHandDiam();
                    rightHandDist = step.getRightHandDist();
                    rightHasLowerExt = step.getRightHasLowExtension();
                    if (rightHasLowerExt == 1) {
                        rightLowerUpLength = step.getRightLowUpExtLength();
                        rightLowerDownLength = step.getRightLowDownExtLength();
                    }
                    rightHasUpperExt = step.getRightHasHighExtension();
                    if (rightHasUpperExt == 1) {
                        rightUpperUpLength = step.getRightHighUpExtLength();
                        rightUpperDownLength = step.getRightHighDownExtLength();
                    }
                }

                hasMiddleHand = step.getHasMiddleHand();
                if (hasMiddleHand == 1) {
                    middleHandUpHeight = step.getMiddleHighHand();
                    middleHandDownHeight = step.getMiddleLowHand();
                    middleHandDiam = step.getMiddleHandDiam();
                    middleHasLowerExt = step.getMiddleHasLowExtension();
                    if (middleHasLowerExt == 1) {
                        middleLowerUpLength = step.getMiddleLowUpExtLength();
                        middleLowerDownLength = step.getMiddleLowDownExtLength();
                    }
                    middleHasUpperExt = step.getMiddleHasHighExtension();
                    if (middleHasUpperExt == 1) {
                        middleUpperUpLength = step.getMiddleHighUpExtLength();
                        middleUpperDownLength = step.getMiddleHighDownExtLength();
                    }
                }
            }
        }

        stepHasSign = indexRadio(stepSignRadio);
        if (stepHasSign == 1) {
            stepSignWidth = Double.parseDouble(String.valueOf(stepSignWidthValue.getText()));
            stepSignFullApp = indexRadio(stepSignAppRadio);
            stepSignMirrorStep = indexRadio(stepMirrorRadio);
        }
        stepHasTactSign = indexRadio(tactFloorRadio);

        if (stepHasTactSign == 1 && tact != null) {
            hasLowTact = tact.getLowTact();
            if (hasLowTact == 1) {
                lowTactDist = tact.getLowDist();
                lowTactWidth = tact.getLowWidth();
                lowTactAntiDrift = tact.getLowAntiDrift();
                lowTactSoilContrast = tact.getLowTactContrast();
                lowTactVisualContrast = tact.getLowVisualContrast();
            }

            hasHighTact = tact.getUpTact();
            if (hasHighTact == 1) {
                highTactDist = tact.getUpDist();
                highTactWidth = tact.getUpWidth();
                highTactAntiDrift = tact.getUpAntiDrift();
                highTactSoilContrast = tact.getUpTactContrast();
                highTactVisualContrast = tact.getUpVisualContrast();
            }
        }


        if (!TextUtils.isEmpty(obsValue.getText()))
            stepObs = String.valueOf(obsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            stepPhoto = String.valueOf(photoValue.getText());


        return new SingleStepEntry(circID,roomID, stepLocation, stepQnt, firstMirror, stepLength, secondMirror, stepWidth, hasLeftHand, leftHandUpHeight, leftHandDownHeight,
                leftHandLength, leftHandDiam, leftHandDist, leftHasLowerExt, leftLowerUpLength, leftLowerDownLength, leftHasUpperExt, leftUpperUpLength, leftUpperDownLength,
                hasRightHand, rightHandUpHeight, rightHandDownHeight, rightHandLength, rightHandDiam, rightHandDist, rightHasLowerExt, rightLowerUpLength, rightLowerDownLength,
                rightHasUpperExt, rightUpperUpLength, rightUpperDownLength, hasMiddleHand, middleHandUpHeight, middleHandDownHeight, middleHandLength, middleHandDiam,
                middleHasLowerExt, middleLowerUpLength, middleLowerDownLength, middleHasUpperExt, middleUpperUpLength, middleUpperDownLength, stepHasSign, stepSignWidth,
                stepSignFullApp, stepSignMirrorStep, stepHasTactSign, hasLowTact, lowTactDist, lowTactWidth, lowTactAntiDrift, lowTactSoilContrast, lowTactVisualContrast,
                hasHighTact, highTactDist, highTactWidth, highTactAntiDrift, highTactSoilContrast, highTactVisualContrast, stepObs, stepPhoto);
    }


    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == stepQntRadio) {
            Fragment fragment;
            switch (index) {
                case 0:
                    fragment = SingleStepOneFragment.newInstance();
                    fragment.setArguments(stepBundle);
                    getChildFragmentManager().beginTransaction().replace(stepQntFrame.getId(), fragment).commit();
                    break;
                case 1:
                    fragment = SingleStepTwoFragment.newInstance();
                    fragment.setArguments(stepBundle);
                    getChildFragmentManager().beginTransaction().replace(stepQntFrame.getId(), fragment).commit();
                    break;
                default:
                    removeFragments(stepQntFrame);
                    break;
            }
        } else if (radio == stepSignRadio) {
            if (index == 1) {
                stepSignWidthField.setVisibility(View.VISIBLE);
                stepSignAppHeader.setVisibility(View.VISIBLE);
                stepSignAppRadio.setVisibility(View.VISIBLE);
                stepMirrorHeader.setVisibility(View.VISIBLE);
                stepMirrorRadio.setVisibility(View.VISIBLE);
            } else {
                stepSignWidthValue.setText(null);
                stepSignWidthField.setVisibility(View.GONE);
                stepSignAppHeader.setVisibility(View.GONE);
                stepSignAppRadio.clearCheck();
                stepSignAppRadio.setVisibility(View.GONE);
                stepMirrorHeader.setVisibility(View.GONE);
                stepMirrorRadio.clearCheck();
                stepMirrorRadio.setVisibility(View.GONE);
            }
        } else {
            if (index == 1) {
                tactFloorFrame.setVisibility(View.VISIBLE);
                Fragment fragment = new StepStairsTactFloorFragment();
                fragment.setArguments(stepBundle);
                getChildFragmentManager().beginTransaction().replace(tactFloorFrame.getId(), fragment).commit();
            } else {
                removeFragments(tactFloorFrame);
            }
        }
    }

    private void removeFragments(View view) {
        Fragment fragment = getChildFragmentManager().findFragmentById(view.getId());
        if (fragment != null) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
            if (view != stepQntFrame)
                view.setVisibility(View.GONE);
        }
    }
}