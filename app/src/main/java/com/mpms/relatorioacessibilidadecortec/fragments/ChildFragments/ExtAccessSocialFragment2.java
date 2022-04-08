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
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialThree;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.GateObsListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.PayPhoneListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

public class ExtAccessSocialFragment2 extends Fragment {

    RadioGroup hasObstaclesRadio, hasPayphoneRadio, hasIntercomRadio;
    MultiLineRadioGroup sillTypeRadio;
    TextView sillTypeError, obstaclesError, payphoneError, intercomError;
    TextInputLayout sillObsField, intercomHeightField, accessObsField;
    TextInputEditText sillObsValue, intercomHeightValue, accessObsValue;
    MaterialButton addObstaclesButton, addPayphoneButton, saveSocialAccess, returnSocialAccess;
    FrameLayout sillFragment;

    ViewModelEntry modelEntry;

    Bundle socialTwoBundle = new Bundle();
    Bundle childFragBundle = new Bundle();

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
        if (getArguments() != null) {
            socialTwoBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID));
            socialTwoBundle.putBoolean(ExtAccessSocialFragment.NEW_REGISTER_ACCESS, this.getArguments().getBoolean(ExtAccessSocialFragment.NEW_REGISTER_ACCESS));
        }
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

        getChildFragmentManager().setFragmentResultListener(InspectionActivity.CHILD_DATA_LISTENER, this, (key,bundle) -> {
            if (buttonPressed > 0) {
                ExtAccessSocialThree socialThree = socialDataThree(bundle);
                ViewModelEntry.updateExtAccessRegThree(socialThree);
                Fragment fragment;
                if (buttonPressed == 1){
                    fragment = new GateObsListFragment();
                } else {
                    fragment = new PayPhoneListFragment();
                }
                fragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
            } else {
                if (checkSocialThreeNoEmptyField() && bundle.getBoolean(RoomsRegisterFragment.CHILD_DATA_COMPLETE)){
                    ExtAccessSocialThree socialThree = socialDataThree(bundle);
                    ViewModelEntry.updateExtAccessRegThree(socialThree);
                    if (bundle.getBoolean(ExtAccessSocialFragment.NEW_REGISTER_ACCESS))
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                }
                requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.EXTERNAL_LIST, 0);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        if (socialTwoBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) > 0 && !socialTwoBundle.getBoolean(ExtAccessSocialFragment.NEW_REGISTER_ACCESS))
            modelEntry.getOneExternalAccess(socialTwoBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), this::loadSocialData2);
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
//        MultilineRadioGroup
        sillTypeRadio = v.findViewById(R.id.type_sill_radio);
//        TextView
        sillTypeError = v.findViewById(R.id.sill_type_error);
        obstaclesError = v.findViewById(R.id.gate_has_obstacles_error);
        payphoneError = v.findViewById(R.id.gate_has_payphones_error);
        intercomError = v.findViewById(R.id.gate_has_intercom_error);
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
        saveSocialAccess = v.findViewById(R.id.save_ext_access_button);
        returnSocialAccess = v.findViewById(R.id.return_ext_access2_button);
//        FrameLayout
        sillFragment = v.findViewById(R.id.ext_access_sill_fragment);
//        Listeners
        hasObstaclesRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasPayphoneRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasIntercomRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        addObstaclesButton.setOnClickListener(this::addButtonClicked);
        addPayphoneButton.setOnClickListener(this::addButtonClicked);
        saveSocialAccess.setOnClickListener(this::addButtonClicked);
        returnSocialAccess.setOnClickListener(view -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
        sillTypeRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (view, r) -> extAccessTwoMultiRadioListener(sillTypeRadio));
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
        Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.ext_access_sill_fragment);
        if (fragment != null)
            requireActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
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
        }
    }

    private void addButtonClicked(View view) {
        clearBundle(childFragBundle);
        if (view == addObstaclesButton)
            buttonPressed = 1;
        else if (view == addPayphoneButton)
            buttonPressed = 2;

        if (sillTypeRadio.getCheckedRadioButtonIndex() > 0) {
            childFragBundle.putBoolean(InspectionActivity.ADD_ITEM_REQUEST, buttonPressed > 0);
            getChildFragmentManager().setFragmentResult(InspectionActivity.GATHER_CHILD_DATA, childFragBundle);
        } else {
            if (buttonPressed > 0) {
                ExtAccessSocialThree socialThree = socialDataThree(childFragBundle);
                ViewModelEntry.updateExtAccessRegThree(socialThree);
                Fragment fragment;
                if (buttonPressed == 1){
                    fragment = new GateObsListFragment();
                } else {
                    fragment = new PayPhoneListFragment();
                }
                fragment.setArguments(childFragBundle);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
            } else {
                if (checkSocialThreeNoEmptyField()){
                    ExtAccessSocialThree socialThree = socialDataThree(childFragBundle);
                    ViewModelEntry.updateExtAccessRegThree(socialThree);
                    if (childFragBundle.getBoolean(ExtAccessSocialFragment.NEW_REGISTER_ACCESS))
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                }
                requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.EXTERNAL_LIST, 0);
            }
        }


    }

    private void loadSocialData2(ExternalAccess access) {
        if (access.getGateSillType() != null) {
            sillTypeRadio.checkAt(access.getGateSillType());
            if (access.getGateSillType() > 0)
                getChildFragmentManager().setFragmentResult(InspectionActivity.LOAD_CHILD_DATA, socialTwoBundle);
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
                intercomHeightField.setError(getString(R.string.blank_field_error));
            }
        }
        return i == 0;
    }

    private ExtAccessSocialThree socialDataThree(Bundle bundle) {
        Integer gateSill = null, slopeQnt = null, hasObstacles = null, hasPayphones = null, hasIntercom = null;
        String sillObs = null, extAccess = null;
        Double inclHeight = null, stepHeight = null, angle1 = null, angle2 = null, angle3 = null, angle4= null, slopeWidth = null, intercomHeight = null;

        if (sillTypeRadio.getCheckedRadioButtonIndex() > -1) {
            gateSill = sillTypeRadio.getCheckedRadioButtonIndex();
            switch (gateSill) {
                case 3:
                    slopeQnt = bundle.getInt(SillSlopeFragment.SLOPE_QNT);
                    if (bundle.getDouble(SillSlopeFragment.SLOPE_WIDTH) != 0)
                        slopeWidth = bundle.getDouble(SillSlopeFragment.SLOPE_WIDTH);
                    switch (slopeQnt) {
                        case 4:
                            if (bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_4) != 0)
                                angle4 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_4);
                        case 3:
                            if (bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_3) != 0)
                                angle3 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_3);
                        case 2:
                            if (bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_2) != 0)
                                angle2 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_2);
                        case 1:
                            if (bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_1) != 0)
                                angle1 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_1);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    if (bundle.getDouble(SillStepFragment.STEP_HEIGHT) != 0)
                        stepHeight = bundle.getDouble(SillStepFragment.STEP_HEIGHT);
                case 1:
                    if (bundle.getDouble(SillInclinationFragment.HEIGHT_INCLINED_SILL) != 0)
                        inclHeight = bundle.getDouble(SillInclinationFragment.HEIGHT_INCLINED_SILL);
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

        return new ExtAccessSocialThree(bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID), gateSill, inclHeight, stepHeight, slopeQnt, angle1, angle2, angle3, angle4, slopeWidth,
                sillObs, hasObstacles, hasPayphones, hasIntercom, intercomHeight, extAccess);
    }

    private Bundle clearBundle(Bundle bundle) {
        bundle.clear();
        bundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, socialTwoBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID));
        bundle.putBoolean(ExtAccessSocialFragment.NEW_REGISTER_ACCESS, socialTwoBundle.getBoolean(ExtAccessSocialFragment.NEW_REGISTER_ACCESS));
        return bundle;
    }
}