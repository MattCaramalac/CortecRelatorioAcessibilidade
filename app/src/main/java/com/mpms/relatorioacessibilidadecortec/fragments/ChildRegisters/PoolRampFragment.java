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
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolRampEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class PoolRampFragment extends Fragment implements ScrollEditText, RadioGroupInterface, TagInterface {

    TextInputLayout localField, inclField1, inclField2, inclField3, inclField4, accessObsField, leftHeightField, rightHeightField, handObsField, photoField, obsField,
            leftDiamField, leftDistField, rightDiamField, rightDistField;
    TextInputEditText localValue, inclValue1, inclValue2, inclValue3, inclValue4, accessObsValue, leftHeightValue, rightHeightValue, handObsValue, photoValue, obsValue,
            leftDiamValue, leftDistValue, rightDiamValue, rightDistValue;
    MaterialButton addIncl, returnRampList, saveRamp;
    ImageButton delIncl;
    RadioGroup accessRadio, leftRadio, rightRadio;
    TextView inclError, accessError, leftError, rightError;
    ArrayList<TextInputLayout> inclFields = new ArrayList<>();

    ViewModelEntry modelEntry;
    Bundle pRampBundle;

    int inclQnt = 1;

    public PoolRampFragment() {
        // Required empty public constructor
    }

    public static PoolRampFragment newInstance() {
        return new PoolRampFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            pRampBundle = new Bundle(this.getArguments());
        else
            pRampBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pool_ramp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);

        if (pRampBundle.getInt(PRAMP_ID) > 0)
            modelEntry.getOnePoolRamp(pRampBundle.getInt(PRAMP_ID)).observe(getViewLifecycleOwner(), this::loadPoolRampData);

    }

    private void instantiateViews(View view) {
//        TextInputLayout
        localField = view.findViewById(R.id.pool_ramp_location_field);
        inclField1 = view.findViewById(R.id.pool_ramp_inclination_1_field);
        inclField2 = view.findViewById(R.id.pool_ramp_inclination_2_field);
        inclField3 = view.findViewById(R.id.pool_ramp_inclination_3_field);
        inclField4 = view.findViewById(R.id.pool_ramp_inclination_4_field);
        inclFields.add(inclField1);
        inclFields.add(inclField2);
        inclFields.add(inclField3);
        inclFields.add(inclField4);
        accessObsField = view.findViewById(R.id.pool_ramp_accessible_floor_obs_field);
        leftHeightField = view.findViewById(R.id.left_handrail_height_field);
        rightHeightField = view.findViewById(R.id.right_handrail_height_field);
        handObsField = view.findViewById(R.id.pool_ramp_handrail_obs_field);
        photoField = view.findViewById(R.id.pool_ramp_photo_field);
        obsField = view.findViewById(R.id.pool_ramp_obs_field);
        leftDiamField = view.findViewById(R.id.left_ramp_pool_handrail_diam_field);
        leftDistField = view.findViewById(R.id.left_ramp_pool_handrail_dist_field);
        rightDiamField = view.findViewById(R.id.right_ramp_pool_handrail_diam_field);
        rightDistField = view.findViewById(R.id.right_ramp_pool_handrail_dist_field);
//        TextInputEditText
        localValue = view.findViewById(R.id.pool_ramp_location_value);
        inclValue1 = view.findViewById(R.id.pool_ramp_inclination_1_value);
        inclValue2 = view.findViewById(R.id.pool_ramp_inclination_2_value);
        inclValue3 = view.findViewById(R.id.pool_ramp_inclination_3_value);
        inclValue4 = view.findViewById(R.id.pool_ramp_inclination_4_value);
        accessObsValue = view.findViewById(R.id.pool_ramp_accessible_floor_obs_value);
        leftHeightValue = view.findViewById(R.id.left_handrail_height_value);
        rightHeightValue = view.findViewById(R.id.right_handrail_height_value);
        handObsValue = view.findViewById(R.id.pool_ramp_handrail_obs_value);
        photoValue = view.findViewById(R.id.pool_ramp_photo_value);
        obsValue = view.findViewById(R.id.pool_ramp_obs_value);
        leftDiamValue = view.findViewById(R.id.left_ramp_pool_handrail_diam_value);
        leftDistValue = view.findViewById(R.id.left_ramp_pool_handrail_dist_value);
        rightDiamValue = view.findViewById(R.id.right_ramp_pool_handrail_diam_value);
        rightDistValue = view.findViewById(R.id.right_ramp_pool_handrail_dist_value);
//        MaterialButton
        addIncl = view.findViewById(R.id.pool_ramp_inclination_button);
        returnRampList = view.findViewById(R.id.return_pool_ramp_list);
        saveRamp = view.findViewById(R.id.proceed_save_pool_ramp);
        addIncl.setOnClickListener(this::buttonClickListener);
        returnRampList.setOnClickListener(this::buttonClickListener);
        saveRamp.setOnClickListener(this::buttonClickListener);
//        ImageButton
        delIncl = view.findViewById(R.id.delete_pool_ramp_inclination_measure);
        delIncl.setOnClickListener(this::buttonClickListener);
//        RadioGroup
        accessRadio = view.findViewById(R.id.pool_ramp_accessible_floor_radio);
        leftRadio = view.findViewById(R.id.pool_ramp_left_radio);
        rightRadio = view.findViewById(R.id.pool_ramp_right_radio);
        leftRadio.setOnCheckedChangeListener(this::radioListener);
        rightRadio.setOnCheckedChangeListener(this::radioListener);
//        TextView
        inclError = view.findViewById(R.id.pool_ramp_inclination_values_error);
        accessError = view.findViewById(R.id.pool_ramp_accessible_floor_error);
        leftError = view.findViewById(R.id.pool_ramp_left_error);
        rightError = view.findViewById(R.id.pool_ramp_right_error);
//        ViewModelEntry
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        allowObsScroll(obsValue);
        allowObsScroll(accessObsValue);
        allowObsScroll(handObsValue);
    }

    private boolean checkEmptyFields() {
        clearErrors();
        int i = 0;
        if (TextUtils.isEmpty(localValue.getText())) {
            i++;
            localField.setError(getString(R.string.req_field_error));
        }

        switch (inclQnt) {
            case 4:
                if (inclValue4.getText() == null) {
                    i++;
                    inclError.setVisibility(View.VISIBLE);
                }
            case 3:
                if (inclValue3.getText() == null) {
                    i++;
                    inclError.setVisibility(View.VISIBLE);
                }
            case 2:
                if (inclValue2.getText() == null) {
                    i++;
                    inclError.setVisibility(View.VISIBLE);
                }
            case 1:
                if (inclValue1.getText() == null) {
                    i++;
                    inclError.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
        if (indexRadio(accessRadio) == -1) {
            i++;
            accessError.setVisibility(View.VISIBLE);
        } else if (indexRadio(accessRadio) == 0 && TextUtils.isEmpty(accessObsValue.getText())) {
            i++;
            accessObsField.setError(getString(R.string.req_field_error));
        }

        if (indexRadio(leftRadio) == -1) {
            i++;
            leftError.setVisibility(View.VISIBLE);
        } else if (indexRadio(leftRadio) == 1) {
            if (TextUtils.isEmpty(leftHeightValue.getText())) {
                i++;
                leftHeightField.setError(getString(R.string.req_field_error));
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
            if (TextUtils.isEmpty(rightHeightValue.getText())) {
                i++;
                rightHeightField.setError(getString(R.string.req_field_error));
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
        inclError.setVisibility(View.GONE);
        accessError.setVisibility(View.GONE);
        accessObsField.setErrorEnabled(false);
        leftError.setVisibility(View.GONE);
        leftHeightField.setErrorEnabled(false);
        leftDiamField.setErrorEnabled(false);
        leftDistField.setErrorEnabled(false);
        rightError.setVisibility(View.GONE);
        rightHeightField.setErrorEnabled(false);
        rightDiamField.setErrorEnabled(false);
        rightDistField.setErrorEnabled(false);
    }

    private void loadPoolRampData(PoolRampEntry ramp) {

        if (ramp.getPoolRampLocale() != null)
            localValue.setText(ramp.getPoolRampLocale());
        if (ramp.getRampInclQnt() < 1 || ramp.getRampInclQnt() > 4)
            inclQnt = 1;
        else
            inclQnt = ramp.getRampInclQnt();

        switch (inclQnt) {
            case 4:
                inclField4.setVisibility(View.VISIBLE);
                inclValue4.setText(String.valueOf(ramp.getRampIncl4()));
            case 3:
                inclField3.setVisibility(View.VISIBLE);
                inclValue3.setText(String.valueOf(ramp.getRampIncl3()));
            case 2:
                delIncl.setVisibility(View.VISIBLE);
                inclField2.setVisibility(View.VISIBLE);
                inclValue2.setText(String.valueOf(ramp.getRampIncl2()));
            default:
                inclField1.setVisibility(View.VISIBLE);
                inclValue1.setText(String.valueOf(ramp.getRampIncl1()));
                break;
        }

        if (ramp.getRampAccessFloor() != null)
            checkRadioGroup(accessRadio, ramp.getRampAccessFloor());
        if (ramp.getAccessFloorObs() != null)
            accessObsValue.setText(ramp.getAccessFloorObs());
        if (ramp.getHasLeftHand() != null) {
            checkRadioGroup(leftRadio, ramp.getHasLeftHand());
            if (ramp.getHasLeftHand() == 1 && ramp.getLeftHandHeight() != null) {
                leftHeightValue.setText(String.valueOf(ramp.getLeftHandHeight()));
                leftDiamValue.setText(String.valueOf(ramp.getLeftHandDiam()));
                leftDistValue.setText(String.valueOf(ramp.getLeftHandDist()));
            }
        }

        if (ramp.getHasRightHand() != null) {
            checkRadioGroup(rightRadio, ramp.getHasRightHand());
            if (ramp.getHasRightHand() == 1 && ramp.getRightHandHeight() != null) {
                rightHeightValue.setText(String.valueOf(ramp.getRightHandHeight()));
                rightDiamValue.setText(String.valueOf(ramp.getRightHandDiam()));
                rightDistValue.setText(String.valueOf(ramp.getRightHandDist()));
            }
        }

        if (ramp.getPoolHandObs() != null)
            handObsValue.setText(ramp.getPoolHandObs());
        if (ramp.getPoolRampPhoto() != null)
            photoValue.setText(ramp.getPoolRampPhoto());
        if (ramp.getPoolRampObs() != null)
            obsValue.setText(ramp.getPoolRampObs());
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == leftRadio) {
            if (index == 1) {
                leftHeightField.setVisibility(View.VISIBLE);
                leftDiamField.setVisibility(View.VISIBLE);
                leftDistField.setVisibility(View.VISIBLE);
            }
            else {
                leftHeightValue.setText(null);
                leftHeightField.setVisibility(View.GONE);
                leftDiamValue.setText(null);
                leftDiamField.setVisibility(View.GONE);
                leftDistValue.setText(null);
                leftDistField.setVisibility(View.GONE);
            }
        }
        if (radio == rightRadio) {
            if (index == 1) {
                rightHeightField.setVisibility(View.VISIBLE);
                rightDiamField.setVisibility(View.VISIBLE);
                rightDistField.setVisibility(View.VISIBLE);
            }

            else {
                rightHeightValue.setText(null);
                rightHeightField.setVisibility(View.GONE);
                rightDiamValue.setText(null);
                rightDiamField.setVisibility(View.GONE);
                rightDistValue.setText(null);
                rightDistField.setVisibility(View.GONE);
            }
        }
    }

    private void buttonClickListener(View view) {
        if (view == addIncl) {
            if (inclQnt < 1) {
                inclQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (inclQnt < 4) {
                if (inclQnt == 1)
                    delIncl.setVisibility(View.VISIBLE);
                inclFields.get(inclQnt).setVisibility(View.VISIBLE);
                inclQnt++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (view == delIncl) {
            if (inclQnt < 1) {
                delIncl.setVisibility(View.GONE);
                for (int i = 1; i < 4; i++) {
                    inclFields.get(i).getEditText().setText(null);
                    inclFields.get(i).setVisibility(View.GONE);
                }
                inclQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (inclQnt >= 2) {
                inclFields.get(inclQnt - 1).getEditText().setText(null);
                inclFields.get(inclQnt - 1).setVisibility(View.GONE);
                inclQnt--;
                if (inclQnt == 1)
                    delIncl.setVisibility(View.GONE);
            }
        } else if (view == saveRamp) {
            if (checkEmptyFields()) {
                PoolRampEntry newPoolRamp = newPoolRamp(pRampBundle);
                if (pRampBundle.getInt(PRAMP_ID) > 0) {
                    newPoolRamp.setPoolRampID(pRampBundle.getInt(PRAMP_ID));
                    modelEntry.updatePoolRamp(newPoolRamp);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (pRampBundle.getInt(PRAMP_ID) == 0) {
                    modelEntry.insertPoolRamp(newPoolRamp);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearRegisterScreen();
                } else {
                    pRampBundle.putInt(PRAMP_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        } else
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    private PoolRampEntry newPoolRamp(Bundle bundle) {
        String local, accessObs = null, handObs = null, photo = null, obs = null;
        double incl1 = 0;
        Double incl2 = null, incl3 = null, incl4 = null, leftHeight = null, leftDiam = null, leftDist = null, rightHeight = null, rightDiam = null, rightDist = null;
        int access, left, right;

        local = String.valueOf(localValue.getText());
        switch (inclQnt) {
            case 4:
                if (!TextUtils.isEmpty(inclValue4.getText()))
                    incl4 = Double.parseDouble(String.valueOf(inclValue4.getText()));
            case 3:
                if (!TextUtils.isEmpty(inclValue3.getText()))
                    incl3 = Double.parseDouble(String.valueOf(inclValue3.getText()));
            case 2:
                if (!TextUtils.isEmpty(inclValue2.getText()))
                    incl2 = Double.parseDouble(String.valueOf(inclValue2.getText()));
            default:
                if (!TextUtils.isEmpty(inclValue1.getText()))
                    incl1 = Double.parseDouble(String.valueOf(inclValue1.getText()));
                break;
        }
        access = indexRadio(accessRadio);
        if (access == 1 && !TextUtils.isEmpty(accessObsValue.getText()))
            accessObs = String.valueOf(accessObsValue.getText());

        left = indexRadio(leftRadio);
        if (left == 1) {
            if (!TextUtils.isEmpty(leftHeightValue.getText()))
                leftHeight = Double.parseDouble(String.valueOf(leftHeightValue.getText()));
            if (!TextUtils.isEmpty(leftDiamValue.getText()))
                leftDiam = Double.parseDouble(String.valueOf(leftDiamValue.getText()));
            if (!TextUtils.isEmpty(leftDistValue.getText()))
                leftDist = Double.parseDouble(String.valueOf(leftDistValue.getText()));
        }

        right = indexRadio(rightRadio);
        if (right == 1) {
            if (!TextUtils.isEmpty(rightHeightValue.getText()))
                rightHeight = Double.parseDouble(String.valueOf(rightHeightValue.getText()));
            if (!TextUtils.isEmpty(rightDiamValue.getText()))
                rightDiam = Double.parseDouble(String.valueOf(rightDiamValue.getText()));
            if (!TextUtils.isEmpty(rightDistValue.getText()))
                rightDist = Double.parseDouble(String.valueOf(rightDistValue.getText()));
        }



        if (!TextUtils.isEmpty(handObsValue.getText()))
            handObs = String.valueOf(handObsValue.getText());

        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        if (!TextUtils.isEmpty(obsValue.getText()))
            obs = String.valueOf(obsValue.getText());

        return new PoolRampEntry(bundle.getInt(POOL_ID), local, inclQnt, incl1, incl2, incl3, incl4, access, accessObs, left, leftHeight, leftDiam, leftDist, right, rightHeight,
                rightDiam, rightDist, handObs, photo, obs);
    }

    private void clearRegisterScreen() {
        localValue.setText(null);
        for (int i = 0; i < 4; i++) {
            inclFields.get(i).getEditText().setText(null);
            if (i > 0)
                inclFields.get(i).setVisibility(View.GONE);
        }
        inclQnt = 1;
        delIncl.setVisibility(View.GONE);
        accessRadio.clearCheck();
        accessObsValue.setText(null);
        leftRadio.clearCheck();
        rightRadio.clearCheck();
        handObsValue.setText(null);
        photoValue.setText(null);
        obsValue.setText(null);
    }
}