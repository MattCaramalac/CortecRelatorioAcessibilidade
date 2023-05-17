package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolStairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PoolStairsFragment extends Fragment implements RadioGroupInterface, TagInterface, ScrollEditText {

    TextInputLayout localField, widthField, mirrorField1, mirrorField2, mirrorField3, mirrorField4, stepField1, stepField2, stepField3, stepField4, leftHighField,
            leftInterField, leftBottomField, leftDiamField, leftDistField, rightHighField, rightInterField, rightBottomField, rightDiamField, rightDistField, obsField,
            photoField;
    TextInputEditText localValue, widthValue, mirrorValue1, mirrorValue2, mirrorValue3, mirrorValue4, stepValue1, stepValue2, stepValue3, stepValue4, leftHighValue,
            leftInterValue, leftBottomValue, leftDiamValue, leftDistValue, rightHighValue, rightInterValue, rightBottomValue, rightDiamValue, rightDistValue, obsValue,
            photoValue;
    RadioGroup leftRadio, rightRadio;
    TextView mirrorError, stepError, leftError, rightError;
    MaterialButton saveStairs, returnList, addMirror, addStep;
    ImageButton delMirror, delStep;
    ArrayList<TextInputLayout> mirrorFields = new ArrayList<>();
    ArrayList<TextInputLayout> stepFields = new ArrayList<>();

    int stepQnt = 1, mirrorQnt = 1;

    ViewModelEntry modelEntry;
    Bundle stairsBundle;

    public PoolStairsFragment() {
        // Required empty public constructor
    }

    public static PoolStairsFragment newInstance() {
        return new PoolStairsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            stairsBundle = new Bundle(this.getArguments());
        else
            stairsBundle = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pool_stairs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);

        if (stairsBundle.getInt(PSTAIRS_ID) > 0)
            modelEntry.getOnePoolStairs(stairsBundle.getInt(PSTAIRS_ID)).observe(getViewLifecycleOwner(), this::loadPoolStairsData);

    }

    private void instantiateViews(View view) {
//        TextInputLayout
        localField = view.findViewById(R.id.pool_stairs_location_field);
        widthField = view.findViewById(R.id.pool_stairs_width_field);
        mirrorField1 = view.findViewById(R.id.pool_mirror_1_field);
        mirrorField2 = view.findViewById(R.id.pool_mirror_2_field);
        mirrorField3 = view.findViewById(R.id.pool_mirror_3_field);
        mirrorField4 = view.findViewById(R.id.pool_mirror_4_field);
        mirrorFields.add(mirrorField1);
        mirrorFields.add(mirrorField2);
        mirrorFields.add(mirrorField3);
        mirrorFields.add(mirrorField4);
        stepField1 = view.findViewById(R.id.pool_step_1_field);
        stepField2 = view.findViewById(R.id.pool_step_2_field);
        stepField3 = view.findViewById(R.id.pool_step_3_field);
        stepField4 = view.findViewById(R.id.pool_step_4_field);
        stepFields.add(stepField1);
        stepFields.add(stepField2);
        stepFields.add(stepField3);
        stepFields.add(stepField4);
        leftHighField = view.findViewById(R.id.pool_left_hand_upper_height_field);
        leftInterField = view.findViewById(R.id.pool_left_hand_inter_height_field);
        leftBottomField = view.findViewById(R.id.pool_left_hand_lower_height_field);
        leftDiamField = view.findViewById(R.id.left_bar_diam_field);
        leftDistField = view.findViewById(R.id.left_bar_dist_field);
        rightHighField = view.findViewById(R.id.pool_right_hand_upper_height_field);
        rightInterField = view.findViewById(R.id.pool_right_hand_inter_height_field);
        rightBottomField = view.findViewById(R.id.pool_right_hand_lower_height_field);
        rightDiamField = view.findViewById(R.id.right_bar_diam_field);
        rightDistField = view.findViewById(R.id.right_bar_dist_field);
        obsField = view.findViewById(R.id.pool_stairs_obs_field);
        photoField = view.findViewById(R.id.pool_stairs_photo_field);
//        TextInputEditText
        localValue = view.findViewById(R.id.pool_stairs_location_value);
        widthValue = view.findViewById(R.id.pool_stairs_width_value);
        mirrorValue1 = view.findViewById(R.id.pool_mirror_1_value);
        mirrorValue2 = view.findViewById(R.id.pool_mirror_2_value);
        mirrorValue3 = view.findViewById(R.id.pool_mirror_3_value);
        mirrorValue4 = view.findViewById(R.id.pool_mirror_4_value);
        stepValue1 = view.findViewById(R.id.pool_step_1_value);
        stepValue2 = view.findViewById(R.id.pool_step_2_value);
        stepValue3 = view.findViewById(R.id.pool_step_3_value);
        stepValue4 = view.findViewById(R.id.pool_step_4_value);
        leftHighValue = view.findViewById(R.id.pool_left_hand_upper_height_value);
        leftInterValue = view.findViewById(R.id.pool_left_hand_inter_height_value);
        leftBottomValue = view.findViewById(R.id.pool_left_hand_lower_height_value);
        leftDiamValue = view.findViewById(R.id.left_bar_diam_value);
        leftDistValue = view.findViewById(R.id.left_bar_dist_value);
        rightHighValue = view.findViewById(R.id.pool_right_hand_upper_height_value);
        rightInterValue = view.findViewById(R.id.pool_right_hand_inter_height_value);
        rightBottomValue = view.findViewById(R.id.pool_right_hand_lower_height_value);
        rightDiamValue = view.findViewById(R.id.right_bar_diam_value);
        rightDistValue = view.findViewById(R.id.right_bar_dist_value);
        obsValue = view.findViewById(R.id.pool_stairs_obs_value);
        photoValue = view.findViewById(R.id.pool_stairs_photo_value);
//        RadioGroup
        leftRadio = view.findViewById(R.id.pool_stairs_has_left_bar_radio);
        rightRadio = view.findViewById(R.id.pool_stairs_has_right_bar_radio);
        leftRadio.setOnCheckedChangeListener(this::radioListener);
        rightRadio.setOnCheckedChangeListener(this::radioListener);
//        TextView
        mirrorError = view.findViewById(R.id.pool_mirror_values_error);
        stepError = view.findViewById(R.id.pool_stairs_step_values_error);
        leftError = view.findViewById(R.id.pool_stairs_has_left_bar_error);
        rightError = view.findViewById(R.id.pool_stairs_has_right_bar_error);
//        MaterialButton
        saveStairs = view.findViewById(R.id.save_pool_stairs);
        returnList = view.findViewById(R.id.return_pool_stairs_list);
        addMirror = view.findViewById(R.id.pool_mirror_button);
        addStep = view.findViewById(R.id.pool_step_button);
        saveStairs.setOnClickListener(this::buttonClickListener);
        returnList.setOnClickListener(this::buttonClickListener);
        addMirror.setOnClickListener(this::buttonClickListener);
        addStep.setOnClickListener(this::buttonClickListener);
//        ImageButton
        delMirror = view.findViewById(R.id.delete_pool_mirror_measure);
        delStep = view.findViewById(R.id.delete_pool_step_measure);
        delMirror.setOnClickListener(this::buttonClickListener);
        delStep.setOnClickListener(this::buttonClickListener);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == leftRadio) {
            if (index == 1) {
                leftHighField.setVisibility(View.VISIBLE);
                leftInterField.setVisibility(View.VISIBLE);
                leftBottomField.setVisibility(View.VISIBLE);
                leftDiamField.setVisibility(View.VISIBLE);
                leftDistField.setVisibility(View.VISIBLE);
            } else {
                leftHighValue.setText(null);
                leftInterValue.setText(null);
                leftBottomValue.setText(null);
                leftDiamValue.setText(null);
                leftDistValue.setText(null);
                leftHighField.setVisibility(View.GONE);
                leftInterField.setVisibility(View.GONE);
                leftBottomField.setVisibility(View.GONE);
                leftDiamField.setVisibility(View.GONE);
                leftDistField.setVisibility(View.GONE);

            }
        } else if (radio == rightRadio) {
            if (index == 1) {
                rightHighField.setVisibility(View.VISIBLE);
                rightInterField.setVisibility(View.VISIBLE);
                rightBottomField.setVisibility(View.VISIBLE);
                rightDiamField.setVisibility(View.VISIBLE);
                rightDistField.setVisibility(View.VISIBLE);
            } else {
                rightHighValue.setText(null);
                rightInterValue.setText(null);
                rightBottomValue.setText(null);
                rightDiamValue.setText(null);
                rightDistValue.setText(null);
                rightHighField.setVisibility(View.GONE);
                rightInterField.setVisibility(View.GONE);
                rightBottomField.setVisibility(View.GONE);
                rightDiamField.setVisibility(View.GONE);
                rightDistField.setVisibility(View.GONE);

            }
        }
    }

    private void buttonClickListener(View view) {
        if (view == addMirror) {
            if (mirrorQnt < 1) {
                mirrorQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (mirrorQnt < 4) {
                if (mirrorQnt == 1)
                    delMirror.setVisibility(View.VISIBLE);
                mirrorFields.get(mirrorQnt).setVisibility(View.VISIBLE);
                mirrorQnt++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (view == delMirror) {
            if (mirrorQnt < 1) {
                delMirror.setVisibility(View.GONE);
                for (int i = 1; i < 4; i++) {
                    mirrorFields.get(i).getEditText().setText(null);
                    mirrorFields.get(i).setVisibility(View.GONE);
                }
                mirrorQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (mirrorQnt >= 2) {
                mirrorFields.get(mirrorQnt - 1).getEditText().setText(null);
                mirrorFields.get(mirrorQnt - 1).setVisibility(View.GONE);
                mirrorQnt--;
                if (mirrorQnt == 1)
                    delMirror.setVisibility(View.GONE);
            }
        } else if (view == addStep) {
            if (stepQnt < 1) {
                stepQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (stepQnt < 4) {
                if (stepQnt == 1)
                    delStep.setVisibility(View.VISIBLE);
                stepFields.get(stepQnt).setVisibility(View.VISIBLE);
                stepQnt++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (view == delStep) {
            if (stepQnt < 1) {
                delStep.setVisibility(View.GONE);
                for (int i = 1; i < 4; i++) {
                    stepFields.get(i).getEditText().setText(null);
                    stepFields.get(i).setVisibility(View.GONE);
                }
                stepQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (stepQnt >= 2) {
                stepFields.get(stepQnt - 1).getEditText().setText(null);
                stepFields.get(stepQnt - 1).setVisibility(View.GONE);
                stepQnt--;
                if (stepQnt == 1)
                    delStep.setVisibility(View.GONE);
            }
        } else if (view == saveStairs) {
            if (checkEmptyFields()) {
                PoolStairsEntry newStairs = newPoolStair(stairsBundle);
                if (stairsBundle.getInt(PSTAIRS_ID) > 0) {
                    newStairs.setPoolStairsID(stairsBundle.getInt(PSTAIRS_ID));
                    modelEntry.updatePoolStairs(newStairs);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (stairsBundle.getInt(PSTAIRS_ID) == 0) {
                    modelEntry.insertPoolStairs(newStairs);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearRegisterScreen();
                } else {
                    stairsBundle.putInt(PSTAIRS_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        } else
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    private void clearRegisterScreen() {
        localValue.setText(null);
        widthValue.setText(null);
        for (int i = 0; i < 4; i++) {
            mirrorFields.get(i).getEditText().setText(null);
            if (i > 0)
                mirrorFields.get(i).setVisibility(View.GONE);
        }
        mirrorQnt = 1;
        delMirror.setVisibility(View.GONE);
        for (int i = 0; i < 4; i++) {
            stepFields.get(i).getEditText().setText(null);
            if (i > 0)
                stepFields.get(i).setVisibility(View.GONE);
        }
        stepQnt = 1;
        delStep.setVisibility(View.GONE);
        leftRadio.clearCheck();
        rightRadio.clearCheck();
        obsValue.setText(null);
        photoValue.setText(null);
    }

    private boolean checkEmptyFields() {
        clearErrors();
        int i = 0;
        if (TextUtils.isEmpty(localValue.getText())) {
            i++;
            localField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(widthValue.getText())) {
            i++;
            widthField.setError(getString(R.string.req_field_error));
        }

        switch (mirrorQnt) {
            case 4:
                if (mirrorValue4.getText() == null) {
                    i++;
                    mirrorError.setVisibility(View.VISIBLE);
                }
            case 3:
                if (mirrorValue3.getText() == null) {
                    i++;
                    mirrorError.setVisibility(View.VISIBLE);
                }
            case 2:
                if (mirrorValue2.getText() == null) {
                    i++;
                    mirrorError.setVisibility(View.VISIBLE);
                }
            default:
                if (mirrorValue1.getText() == null) {
                    i++;
                    mirrorError.setVisibility(View.VISIBLE);
                }
                break;
        }

        switch (stepQnt) {
            case 4:
                if (stepValue4.getText() == null) {
                    i++;
                    stepError.setVisibility(View.VISIBLE);
                }
            case 3:
                if (stepValue3.getText() == null) {
                    i++;
                    stepError.setVisibility(View.VISIBLE);
                }
            case 2:
                if (stepValue2.getText() == null) {
                    i++;
                    stepError.setVisibility(View.VISIBLE);
                }
            default:
                if (stepValue1.getText() == null) {
                    i++;
                    stepError.setVisibility(View.VISIBLE);
                }
                break;
        }

        if (indexRadio(leftRadio) == -1) {
            i++;
            leftError.setVisibility(View.VISIBLE);
        } else if (indexRadio(leftRadio) == 1) {
            if (TextUtils.isEmpty(leftHighValue.getText())) {
                i++;
                leftHighField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(leftDiamValue.getText())) {
                i++;
                leftDiamField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(leftDistValue.getText())) {
                i++;
                leftDistField.setError(getString(R.string.req_field_error));
            }
        }

        if (indexRadio(rightRadio) == -1) {
            i++;
            rightError.setVisibility(View.VISIBLE);
        } else if (indexRadio(rightRadio) == 1) {
            if (TextUtils.isEmpty(rightHighValue.getText())) {
                i++;
                rightHighField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(rightDiamValue.getText())) {
                i++;
                rightDiamField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(rightDistValue.getText())) {
                i++;
                rightDistField.setError(getString(R.string.req_field_error));
            }
        }

        return i == 0;
    }

    private void clearErrors() {
        localField.setErrorEnabled(false);
        widthField.setErrorEnabled(false);
        leftHighField.setErrorEnabled(false);
        leftInterField.setErrorEnabled(false);
        leftBottomField.setErrorEnabled(false);
        leftDiamField.setErrorEnabled(false);
        leftDistField.setErrorEnabled(false);
        rightHighField.setErrorEnabled(false);
        rightInterField.setErrorEnabled(false);
        rightBottomField.setErrorEnabled(false);
        rightDiamField.setErrorEnabled(false);
        rightDistField.setErrorEnabled(false);
        mirrorError.setVisibility(View.GONE);
        stepError.setVisibility(View.GONE);
        leftError.setVisibility(View.GONE);
        rightError.setVisibility(View.GONE);
    }

    private PoolStairsEntry newPoolStair(Bundle bundle) {
        String local, obs = null, photo = null;
        double width, step1, mirror1;
        int hasLeft, hasRight;
        Double mirror2 = null, mirror3 = null, mirror4 = null, step2 = null, step3 = null, step4 = null, leftHigh = null, leftInter = null, leftLow = null, leftDiam = null,
                leftDist = null, rightHigh = null, rightInter = null, rightLow = null, rightDiam = null, rightDist = null;

        local = String.valueOf(localValue.getText());
        width = Double.parseDouble(String.valueOf(widthValue.getText()));

        switch (mirrorQnt) {
            case 4:
                mirror4 = Double.parseDouble(String.valueOf(mirrorValue4.getText()));
            case 3:
                mirror3 = Double.parseDouble(String.valueOf(mirrorValue3.getText()));
            case 2:
                mirror2 = Double.parseDouble(String.valueOf(mirrorValue2.getText()));
            default:
                mirror1 = Double.parseDouble(String.valueOf(mirrorValue1.getText()));
                break;
        }

        switch (stepQnt) {
            case 4:
                step4 = Double.parseDouble(String.valueOf(stepValue4.getText()));
            case 3:
                step3 = Double.parseDouble(String.valueOf(stepValue3.getText()));
            case 2:
                step2 = Double.parseDouble(String.valueOf(stepValue2.getText()));
            default:
                step1 = Double.parseDouble(String.valueOf(stepValue1.getText()));
                break;
        }

        hasLeft = indexRadio(leftRadio);
        if (hasLeft == 1) {
            leftHigh = Double.parseDouble(String.valueOf(leftHighValue.getText()));
            if (!TextUtils.isEmpty(leftInterValue.getText()))
                leftInter = Double.parseDouble(String.valueOf(leftInterValue.getText()));
            if (!TextUtils.isEmpty(leftBottomValue.getText()))
                leftLow = Double.parseDouble(String.valueOf(leftBottomValue.getText()));
            leftDiam = Double.parseDouble(String.valueOf(leftDiamValue.getText()));
            leftDist = Double.parseDouble(String.valueOf(leftDistValue.getText()));
        }

        hasRight = indexRadio(rightRadio);
        if (hasRight == 1) {
            rightHigh = Double.parseDouble(String.valueOf(rightHighValue.getText()));
            if (!TextUtils.isEmpty(rightInterValue.getText()))
                rightInter = Double.parseDouble(String.valueOf(rightInterValue.getText()));
            if (!TextUtils.isEmpty(rightBottomValue.getText()))
                rightLow = Double.parseDouble(String.valueOf(rightBottomValue.getText()));
            rightDiam = Double.parseDouble(String.valueOf(rightDiamValue.getText()));
            rightDist = Double.parseDouble(String.valueOf(rightDistValue.getText()));
        }

        if (!TextUtils.isEmpty(obsValue.getText()))
            obs = String.valueOf(obsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new PoolStairsEntry(bundle.getInt(POOL_ID), local, width, mirrorQnt, mirror1, mirror2, mirror3, mirror4, stepQnt, step1, step2, step3, step4,
                hasLeft, leftHigh, leftInter, leftLow, leftDiam, leftDist, hasRight, rightHigh, rightInter, rightLow, rightDiam, rightDist, photo, obs);
    }

    private void loadPoolStairsData(PoolStairsEntry stairs) {

        if (stairs.getStairsLocation() != null)
            localValue.setText(stairs.getStairsLocation());
        if (stairs.getStairsWidth() != null)
            widthValue.setText(String.valueOf(stairs.getStairsWidth()));
        if (stairs.getMirrorQnt() < 1 || stairs.getMirrorQnt() > 4)
            mirrorQnt = 1;
        else
            mirrorQnt = stairs.getMirrorQnt();

        switch (mirrorQnt) {
            case 4:
                mirrorField4.setVisibility(View.VISIBLE);
                mirrorValue4.setText(String.valueOf(stairs.getMirror4()));
            case 3:
                mirrorField3.setVisibility(View.VISIBLE);
                mirrorValue3.setText(String.valueOf(stairs.getMirror3()));
            case 2:
                delMirror.setVisibility(View.VISIBLE);
                mirrorField2.setVisibility(View.VISIBLE);
                mirrorValue2.setText(String.valueOf(stairs.getMirror2()));
            default:
                mirrorField1.setVisibility(View.VISIBLE);
                mirrorValue1.setText(String.valueOf(stairs.getMirror1()));
                break;
        }

        if (stairs.getStepQnt() < 1 || stairs.getStepQnt() > 4)
            stepQnt = 1;
        else
            stepQnt = stairs.getStepQnt();

        switch (stepQnt) {
            case 4:
                stepField4.setVisibility(View.VISIBLE);
                stepValue4.setText(String.valueOf(stairs.getStep4()));
            case 3:
                stepField3.setVisibility(View.VISIBLE);
                stepValue3.setText(String.valueOf(stairs.getStep3()));
            case 2:
                delStep.setVisibility(View.VISIBLE);
                stepField2.setVisibility(View.VISIBLE);
                stepValue2.setText(String.valueOf(stairs.getStep2()));
            default:
                stepField1.setVisibility(View.VISIBLE);
                stepValue1.setText(String.valueOf(stairs.getStep1()));
                break;
        }

        if (stairs.getStairsHasLeftHand() != null) {
            checkRadioGroup(leftRadio, stairs.getStairsHasLeftHand());
            if (stairs.getStairsHasLeftHand() == 1) {
                if (stairs.getPoolLeftUpperHandHeight() != null)
                    leftHighValue.setText(String.valueOf(stairs.getPoolLeftUpperHandHeight()));
                if (stairs.getPoolLeftInterHandHeight() != null)
                    leftInterValue.setText(String.valueOf(stairs.getPoolLeftInterHandHeight()));
                if (stairs.getPoolLeftLowerHandHeight() != null)
                    leftBottomValue.setText(String.valueOf(stairs.getPoolLeftLowerHandHeight()));
                if (stairs.getPoolLeftHandDiam() != null)
                    leftDiamValue.setText(String.valueOf(stairs.getPoolLeftHandDiam()));
                if (stairs.getPoolLeftHandDist() != null)
                    leftDistValue.setText(String.valueOf(stairs.getPoolLeftHandDist()));
            }
        }

        if (stairs.getStairsHasRightHand() != null) {
            checkRadioGroup(rightRadio, stairs.getStairsHasRightHand());
            if (stairs.getStairsHasRightHand() == 1) {
                if (stairs.getPoolRightUpperHandHeight() != null)
                    rightHighValue.setText(String.valueOf(stairs.getPoolRightUpperHandHeight()));
                if (stairs.getPoolRightInterHandHeight() != null)
                    rightInterValue.setText(String.valueOf(stairs.getPoolRightInterHandHeight()));
                if (stairs.getPoolRightLowerHandHeight() != null)
                    rightBottomValue.setText(String.valueOf(stairs.getPoolRightLowerHandHeight()));
                if (stairs.getPoolRightHandDiam() != null)
                    rightDiamValue.setText(String.valueOf(stairs.getPoolRightHandDiam()));
                if (stairs.getPoolRightHandDist() != null)
                    rightDistValue.setText(String.valueOf(stairs.getPoolRightHandDist()));
            }
        }


        if (stairs.getPoolStairsObs() != null)
            obsValue.setText(stairs.getPoolStairsObs());
        if (stairs.getPoolStairsPhoto() != null)
            photoValue.setText(stairs.getPoolStairsPhoto());
    }
}