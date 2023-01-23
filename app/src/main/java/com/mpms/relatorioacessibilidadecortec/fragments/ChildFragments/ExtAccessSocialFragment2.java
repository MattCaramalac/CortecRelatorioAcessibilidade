package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

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
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialThree;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.GateObsListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.PayPhoneListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RampStairsListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class ExtAccessSocialFragment2 extends Fragment implements TagInterface, ScrollEditText {

    RadioGroup hasObstaclesRadio, hasPayphoneRadio, hasIntercomRadio, hasStairsRadio, hasRampsRadio;
    MultiLineRadioGroup sillTypeRadio;
    TextView sillTypeError, obstaclesError, payphoneError, intercomError, stairsError, rampError;
    TextInputLayout sillObsField, intercomHeightField, accessObsField;
    TextInputEditText sillObsValue, intercomHeightValue, accessObsValue;
    MaterialButton addObstaclesButton, addPayphoneButton, addRamps, addStairs, saveSocialAccess, returnSocialAccess;
    FrameLayout sillFragment;

    ViewModelEntry modelEntry;

    Bundle socialTwoBundle;
    Bundle childFragBundle = new Bundle();

    ArrayList<TextInputEditText> eText = new ArrayList<>();

    int buttonPressed = 0;


    public ExtAccessSocialFragment2() {
        // Required empty public constructor
    }

    public static ExtAccessSocialFragment2 newInstance() {
        return new ExtAccessSocialFragment2();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TODO - Verificar a possibilidade de unificar este bundle, pelamordedeus
        if (this.getArguments() != null) {
            socialTwoBundle = new Bundle(this.getArguments());
            socialTwoBundle.putBoolean(FROM_EXT_ACCESS, true);

            childFragBundle.putInt(BLOCK_ID, this.getArguments().getInt(BLOCK_ID));
            childFragBundle.putInt(AMBIENT_ID, this.getArguments().getInt(AMBIENT_ID));
        }
        else
            socialTwoBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ext_access_social2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSocialAccess2(view);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key,bundle) -> {
            if (buttonPressed > 0) {
                ExtAccessSocialThree socialThree = socialDataThree(bundle);
                ViewModelEntry.updateExtAccessRegThree(socialThree);
                Fragment fragment;
                bundle.putBoolean(FROM_EXT_ACCESS, true);
                if (buttonPressed == 1){
                    fragment = new GateObsListFragment();
                } else if  (buttonPressed == 2) {
                    fragment = new PayPhoneListFragment();
                } else {
                    if (buttonPressed == 3)
                        bundle.putInt(RAMP_OR_STAIRS, 1);
                    else
                        bundle.putInt(RAMP_OR_STAIRS, 2);
                    fragment = new RampStairsListFragment();
                    bundle.putInt(RampStairsListFragment.AMBIENT_TYPE, 1);
                }
                fragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
            } else {
                if (checkSocialThreeNoEmptyField() && bundle.getBoolean(CHILD_DATA_COMPLETE)){
                    ExtAccessSocialThree socialThree = socialDataThree(bundle);
                    ViewModelEntry.updateExtAccessRegThree(socialThree);
                    if (bundle.getBoolean(RECENT_ENTRY))
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();

                    requireActivity().getSupportFragmentManager().popBackStack(EXTERNAL_LIST, 0);
                } else
                    Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        if (socialTwoBundle.getInt(AMBIENT_ID) > 0 && !socialTwoBundle.getBoolean(RECENT_ENTRY))
            modelEntry.getOneExternalAccess(socialTwoBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), this::loadSocialData2);
    }

    @Override
    public void onResume() {
        super.onResume();

        buttonPressed = 0;
    }

    private void instantiateSocialAccess2(View v) {
//        RadioGroup
        hasObstaclesRadio = v.findViewById(R.id.gate_has_obstacles_radio);
        hasPayphoneRadio = v.findViewById(R.id.gate_has_payphones_radio);
        hasIntercomRadio = v.findViewById(R.id.gate_has_intercom_radio);
        hasStairsRadio = v.findViewById(R.id.gate_has_stairs_radio);
        hasRampsRadio = v.findViewById(R.id.gate_has_ramps_radio);
//        MultilineRadioGroup
        sillTypeRadio = v.findViewById(R.id.type_sill_radio);
//        TextView
        sillTypeError = v.findViewById(R.id.sill_type_error);
        obstaclesError = v.findViewById(R.id.gate_has_obstacles_error);
        payphoneError = v.findViewById(R.id.gate_has_payphones_error);
        intercomError = v.findViewById(R.id.gate_has_intercom_error);
        stairsError = v.findViewById(R.id.gate_has_stairs_error);
        rampError = v.findViewById(R.id.gate_has_ramps_error);
//        TextInputLayout
        sillObsField = v.findViewById(R.id.gate_sill_obs_field);
        intercomHeightField = v.findViewById(R.id.intercom_height_field);
        accessObsField = v.findViewById(R.id.external_access_social_obs_field);
//        TextInputEditText
        sillObsValue = v.findViewById(R.id.gate_sill_obs_value);
        intercomHeightValue = v.findViewById(R.id.intercom_height_value);
        accessObsValue = v.findViewById(R.id.external_access_social_obs_value);
//        MaterialButton
        addObstaclesButton = v.findViewById(R.id.add_gate_obstacles_button);
        addPayphoneButton = v.findViewById(R.id.add_gate_payphones_button);
        addRamps = v.findViewById(R.id.add_gate_ramps_button);
        addStairs = v.findViewById(R.id.add_gate_stairs_button);
        saveSocialAccess = v.findViewById(R.id.save_ext_access_button);
        returnSocialAccess = v.findViewById(R.id.return_ext_access2_button);
//        FrameLayout
        sillFragment = v.findViewById(R.id.ext_access_sill_fragment);
//        Listeners
        hasObstaclesRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasPayphoneRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasIntercomRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasRampsRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasStairsRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        addObstaclesButton.setOnClickListener(this::addButtonClicked);
        addPayphoneButton.setOnClickListener(this::addButtonClicked);
        addRamps.setOnClickListener(this::addButtonClicked);
        addStairs.setOnClickListener(this::addButtonClicked);
        saveSocialAccess.setOnClickListener(this::addButtonClicked);
        returnSocialAccess.setOnClickListener(view -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
        sillTypeRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (view, r) -> extAccessTwoMultiRadioListener(sillTypeRadio));
        eText.add(sillObsValue);
        eText.add(accessObsValue);
        allowObsScroll(eText);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void extAccessTwoMultiRadioListener(MultiLineRadioGroup multi) {
        int index = multi.getCheckedRadioButtonIndex();
        switch (index) {
            case 1:
                getChildFragmentManager().beginTransaction().replace(R.id.ext_access_sill_fragment, new SillInclinationFragment()).commit();
                break;
            case 2:
                getChildFragmentManager().beginTransaction().replace(R.id.ext_access_sill_fragment, new SillStepFragment()).commit();
                break;
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.ext_access_sill_fragment, new SillSlopeFragment()).commit();
                break;
            default:
                removeSillFragments();
                break;
        }
    }

    private void removeSillFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.ext_access_sill_fragment);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private void extAccessRadioListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == hasObstaclesRadio) {
            if (index == 1)
                addObstaclesButton.setVisibility(View.VISIBLE);
            else
                addObstaclesButton.setVisibility(View.GONE);
        } else if (radio == hasPayphoneRadio) {
            if (index == 1)
                addPayphoneButton.setVisibility(View.VISIBLE);
            else
                addPayphoneButton.setVisibility(View.GONE);
        } else if (radio == hasIntercomRadio) {
            if (index == 1)
                intercomHeightField.setVisibility(View.VISIBLE);
            else {
                intercomHeightValue.setText(null);
                intercomHeightField.setVisibility(View.GONE);
            }
        } else if (radio == hasRampsRadio) {
            if (index == 1)
                addRamps.setVisibility(View.VISIBLE);
            else
                addRamps.setVisibility(View.GONE);
        } else if (radio == hasStairsRadio) {
            if (index == 1)
                addStairs.setVisibility(View.VISIBLE);
            else
                addStairs.setVisibility(View.GONE);
        }
    }

    private void addButtonClicked(View view) {
        clearBundle(childFragBundle);
        if (view == addObstaclesButton)
            buttonPressed = 1;
        else if (view == addPayphoneButton)
            buttonPressed = 2;
        else if (view == addStairs)
            buttonPressed = 3;
        else if (view == addRamps)
            buttonPressed = 4;

        if (sillTypeRadio.getCheckedRadioButtonIndex() > 0) {
            childFragBundle.putBoolean(ADD_ITEM_REQUEST, buttonPressed > 0);
            getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, childFragBundle);
        } else {
            if (buttonPressed > 0) {
                ExtAccessSocialThree socialThree = socialDataThree(childFragBundle);
                ViewModelEntry.updateExtAccessRegThree(socialThree);
                Fragment fragment;
                childFragBundle.putBoolean(FROM_EXT_ACCESS, true);
                if (buttonPressed == 1){
                    fragment = new GateObsListFragment();
                } else  if (buttonPressed == 2) {
                    fragment = new PayPhoneListFragment();
                } else {
                    if (buttonPressed == 3)
                        childFragBundle.putInt(RAMP_OR_STAIRS, 1);
                    else
                        childFragBundle.putInt(RAMP_OR_STAIRS, 2);
                    fragment = new RampStairsListFragment();
                    childFragBundle.putInt(AMBIENT_TYPE, 1);
                }
                fragment.setArguments(childFragBundle);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
            } else {
                if (checkSocialThreeNoEmptyField()){
                    ExtAccessSocialThree socialThree = socialDataThree(childFragBundle);
                    ViewModelEntry.updateExtAccessRegThree(socialThree);
                    if (childFragBundle.getBoolean(RECENT_ENTRY))
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                }
                requireActivity().getSupportFragmentManager().popBackStack(EXTERNAL_LIST, 0);
            }
        }
    }

    private void loadSocialData2(ExternalAccess access) {
        if (access.getGateSillType() != null) {
            sillTypeRadio.checkAt(access.getGateSillType());
            if (access.getGateSillType() > 0)
                getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, socialTwoBundle);
        }
        if (access.getGateSillObs() != null)
            sillObsValue.setText(access.getGateSillObs());
        if (access.getGateHasObstacles() != null)
            hasObstaclesRadio.check(hasObstaclesRadio.getChildAt(access.getGateHasObstacles()).getId());
        if (access.getGateHasPayphones() != null)
            hasPayphoneRadio.check(hasPayphoneRadio.getChildAt(access.getGateHasPayphones()).getId());
        if (access.getGateHasIntercom() != null) {
            hasIntercomRadio.check(hasIntercomRadio.getChildAt(access.getGateHasIntercom()).getId());
            if (access.getGateHasIntercom() == 1)
                if (access.getIntercomHeight() != null)
                    intercomHeightValue.setText(String.valueOf(access.getIntercomHeight()));
        }
        if (access.getGateHasStairs() != null)
            hasStairsRadio.check(hasStairsRadio.getChildAt(access.getGateHasStairs()).getId());
        if (access.getGateHasRamps() != null)
            hasRampsRadio.check(hasRampsRadio.getChildAt(access.getGateHasRamps()).getId());
        if (access.getExtAccessObs() != null)
            accessObsValue.setText(access.getExtAccessObs());

    }

    private int getCheckedRadioIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkSocialThreeNoEmptyField() {
        int i = 0;
        if (sillTypeRadio.getCheckedRadioButtonIndex() == -1) {
            i++;
            sillTypeError.setVisibility(View.VISIBLE);
        }
        if (getCheckedRadioIndex(hasObstaclesRadio) == -1) {
            i++;
            obstaclesError.setVisibility(View.VISIBLE);
        }
        if (getCheckedRadioIndex(hasPayphoneRadio) == -1) {
            i++;
            payphoneError.setVisibility(View.VISIBLE);
        }
        if (getCheckedRadioIndex(hasIntercomRadio) == -1) {
            i++;
            intercomError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadioIndex(hasIntercomRadio) == 1) {
            if (TextUtils.isEmpty(intercomHeightValue.getText())){
                i++;
                intercomHeightField.setError(getString(R.string.req_field_error));
            }
        }
        if (getCheckedRadioIndex(hasRampsRadio) == -1) {
            i++;
            rampError.setVisibility(View.VISIBLE);
        }
        if (getCheckedRadioIndex(hasStairsRadio) == -1) {
            i++;
            stairsError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private ExtAccessSocialThree socialDataThree(Bundle bundle) {
        Integer gateSill = null, slopeQnt = null, hasObstacles = null, hasPayphones = null, hasIntercom = null, hasRamp = null, hasStairs = null;
        String sillObs = null, extAccess = null;
        Double inclHeight = null, stepHeight = null, angle1 = null, angle2 = null, angle3 = null, angle4= null, slopeWidth = null, intercomHeight = null;

        if (sillTypeRadio.getCheckedRadioButtonIndex() > -1) {
            gateSill = sillTypeRadio.getCheckedRadioButtonIndex();
            switch (gateSill) {
                case 3:
                    slopeQnt = bundle.getInt(SLOPE_QNT);
                    if (bundle.getDouble(SLOPE_WIDTH) != 0)
                        slopeWidth = bundle.getDouble(SLOPE_WIDTH);
                    switch (slopeQnt) {
                        case 4:
                            if (bundle.getDouble(SLOPE_ANGLE_4) != 0)
                                angle4 = bundle.getDouble(SLOPE_ANGLE_4);
                        case 3:
                            if (bundle.getDouble(SLOPE_ANGLE_3) != 0)
                                angle3 = bundle.getDouble(SLOPE_ANGLE_3);
                        case 2:
                            if (bundle.getDouble(SLOPE_ANGLE_2) != 0)
                                angle2 = bundle.getDouble(SLOPE_ANGLE_2);
                        case 1:
                            if (bundle.getDouble(SLOPE_ANGLE_1) != 0)
                                angle1 = bundle.getDouble(SLOPE_ANGLE_1);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    if (bundle.getDouble(STEP_HEIGHT) != 0)
                        stepHeight = bundle.getDouble(STEP_HEIGHT);
                case 1:
                    if (bundle.getDouble(HEIGHT_INCLINED_SILL) != 0)
                        inclHeight = bundle.getDouble(HEIGHT_INCLINED_SILL);
                default:
                    break;
            }
        }
        if (!TextUtils.isEmpty(sillObsValue.getText()))
            sillObs = String.valueOf(sillObsValue.getText());
        if (getCheckedRadioIndex(hasObstaclesRadio) > -1)
            hasObstacles = getCheckedRadioIndex(hasObstaclesRadio);
        if (getCheckedRadioIndex(hasPayphoneRadio) > -1)
            hasPayphones = getCheckedRadioIndex(hasPayphoneRadio);
        if (getCheckedRadioIndex(hasIntercomRadio) > -1) {
            hasIntercom = getCheckedRadioIndex(hasIntercomRadio);
            if (hasIntercom == 1) {
                if (!TextUtils.isEmpty(intercomHeightValue.getText()))
                    intercomHeight = Double.parseDouble(String.valueOf(intercomHeightValue.getText()));
            }
        }
        if (!TextUtils.isEmpty(accessObsValue.getText()))
            extAccess = String.valueOf(accessObsValue.getText());
        if (getCheckedRadioIndex(hasStairsRadio) > -1)
            hasStairs = getCheckedRadioIndex(hasStairsRadio);
        if (getCheckedRadioIndex(hasRampsRadio) > -1)
            hasRamp = getCheckedRadioIndex(hasRampsRadio);

        return new ExtAccessSocialThree(bundle.getInt(AMBIENT_ID), gateSill, inclHeight, stepHeight, slopeQnt, angle1, angle2, angle3, angle4, slopeWidth,
                sillObs, hasObstacles, hasPayphones, hasIntercom, intercomHeight, extAccess, hasStairs, hasRamp);
    }

    private Bundle clearBundle(Bundle bundle) {
        bundle.clear();
        bundle.putInt(BLOCK_ID, socialTwoBundle.getInt(BLOCK_ID));
        bundle.putInt(AMBIENT_ID, socialTwoBundle.getInt(AMBIENT_ID));
        bundle.putBoolean(RECENT_ENTRY, socialTwoBundle.getBoolean(RECENT_ENTRY));
        return bundle;
    }
}