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
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class WaterFountainOtherFragment extends Fragment implements TagInterface, ScrollEditText {

    TextView lateralApproxError, hasCupHolderError;
    RadioGroup allowLateralApprox, hasCupHolder;
    TextInputLayout faucetHeightField, cupHolderHeightField, latApproxObsField;
    TextInputEditText faucetHeightValue, cupHolderHeightValue, latApproxObsValue;

    ViewModelEntry modelEntry;

    Bundle childBundle = new Bundle();

    public WaterFountainOtherFragment() {
        // Required empty public constructor
    }

    public static WaterFountainOtherFragment newInstance() {
        return new WaterFountainOtherFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            childBundle.putInt(FOUNTAIN_ID, this.getArguments().getInt(FOUNTAIN_ID));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_water_fountain_other, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateOtherViews(view);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (childBundle.getInt(FOUNTAIN_ID) > 0)
            modelEntry.getOneWaterFountain(childBundle.getInt(FOUNTAIN_ID)).observe(getViewLifecycleOwner(), this::loadOtherFountainData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            gatherOtherFountainData(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });
    }

    private void loadOtherFountainData(WaterFountainEntry fountainEntry) {
        allowLateralApprox.check(allowLateralApprox.getChildAt(fountainEntry.getAllowSideApprox()).getId());
        if (fountainEntry.getAllowSideApprox() != null)
            latApproxObsValue.setText(fountainEntry.getSideApproxObs());
        faucetHeightValue.setText(String.valueOf(fountainEntry.getFaucetHeight()));
        hasCupHolder.check(hasCupHolder.getChildAt(fountainEntry.getHasCupHolder()).getId());
        if (fountainEntry.getHasCupHolder() == 1)
            cupHolderHeightValue.setText(String.valueOf(fountainEntry.getCupHolderHeight()));
    }

    private void gatherOtherFountainData(Bundle bundle) {
        bundle.putBoolean(InspectionActivity.CHILD_DATA_COMPLETE, hasNoEmptyFields());
        if (hasNoEmptyFields()) {
            bundle.putInt(ALLOW_LATERAL, getCheckedIndex(allowLateralApprox));
            if (!TextUtils.isEmpty(latApproxObsValue.getText()))
                bundle.putString(LAT_APPROX_OBS, String.valueOf(latApproxObsValue.getText()));
            bundle.putDouble(FAUCET_HEIGHT, Double.parseDouble(String.valueOf(faucetHeightValue.getText())));
            bundle.putInt(HAS_CUP_HOLDER, getCheckedIndex(hasCupHolder));
            if (getCheckedIndex(hasCupHolder) == 1) {
                bundle.putDouble(CUP_HOLDER_HEIGHT, Double.parseDouble(String.valueOf(cupHolderHeightValue.getText())));
            }
        }

    }

    private void instantiateOtherViews(View view) {
//        TextView
        lateralApproxError = view.findViewById(R.id.water_fountain_lateral_approx_error);
        hasCupHolderError = view.findViewById(R.id.water_fountain_has_cup_holder_error);
//        RadioGroup
        allowLateralApprox = view.findViewById(R.id.other_allow_approx_radio);
        hasCupHolder = view.findViewById(R.id.other_has_cup_holder_radio);
//        TextInputLayout
        faucetHeightField = view.findViewById(R.id.other_water_fountain_faucet_height_field);
        cupHolderHeightField = view.findViewById(R.id.other_water_fountain_cup_holder_height_field);
        latApproxObsField = view.findViewById(R.id.lateral_approx_obs_field);
//        TextInputEditText
        faucetHeightValue = view.findViewById(R.id.other_water_fountain_faucet_height_value);
        cupHolderHeightValue = view.findViewById(R.id.other_water_fountain_cup_holder_height_value);
        latApproxObsValue = view.findViewById(R.id.lateral_approx_obs_value);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        hasCupHolder.setOnCheckedChangeListener(this::otherRadioListener);
        allowLateralApprox.setOnCheckedChangeListener(this::otherRadioListener);

        allowObsScroll(latApproxObsValue);
    }

    public void otherRadioListener(RadioGroup group, int checkedID) {
        View radioButton = group.findViewById(checkedID);
        int index = group.indexOfChild(radioButton);

        if (group == hasCupHolder) {
            switch (index) {
                case 0:
                    cupHolderHeightValue.setText(null);
                    cupHolderHeightField.setVisibility(View.GONE);
                    break;
                case 1:
                    cupHolderHeightField.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        } else if (group == allowLateralApprox) {
            if (index == 0)
                latApproxObsField.setVisibility(View.VISIBLE);
            else {
                latApproxObsValue.setText(null);
                latApproxObsField.setVisibility(View.GONE);
            }
        }
    }

    public boolean hasNoEmptyFields() {
        clearErrorMessages();
        int errors = 0;
        if (allowLateralApprox.getCheckedRadioButtonId() == -1) {
            lateralApproxError.setVisibility(View.VISIBLE);
            errors++;
        }
        if (TextUtils.isEmpty(faucetHeightValue.getText())) {
            faucetHeightField.setError(getString(R.string.req_field_error));
            errors++;
        }
        if (hasCupHolder.getCheckedRadioButtonId() == -1) {
            hasCupHolderError.setVisibility(View.VISIBLE);
            errors++;
        }
        if (getCheckedIndex(hasCupHolder) == 1 && TextUtils.isEmpty(cupHolderHeightValue.getText())) {
            cupHolderHeightField.setError(getString(R.string.req_field_error));
            errors++;
        }
        return errors == 0;
    }

    public void clearErrorMessages() {
        lateralApproxError.setVisibility(View.GONE);
        hasCupHolderError.setVisibility(View.GONE);
        faucetHeightField.setErrorEnabled(false);
        cupHolderHeightField.setErrorEnabled(false);
    }

    public int getCheckedIndex(RadioGroup group) {
        return group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
    }

}