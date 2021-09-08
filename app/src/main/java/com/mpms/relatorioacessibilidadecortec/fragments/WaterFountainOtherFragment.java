package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class WaterFountainOtherFragment extends Fragment {

    static final String ALLOW_LATERAL = "ALLOW_LATERAL";
    static final String FAUCET_HEIGHT = "FAUCET_HEIGHT";
    static final String HAS_CUP_HOLDER = "HAS_CUP_HOLDER";
    static final String CUP_HOLDER_HEIGHT = "CUP_HOLDER_HEIGHT";

    ViewModelFragments modelFragments;
    TextView lateralApproxError, hasCupHolderError;
    RadioGroup allowLateralApprox, hasCupHolder;
    TextInputLayout faucetHeightField, cupHolderHeightField;
    TextInputEditText faucetHeightValue, cupHolderHeightValue;

    public WaterFountainOtherFragment() {
        // Required empty public constructor
    }

    public static WaterFountainOtherFragment newInstance() {
        return new WaterFountainOtherFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
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

        lateralApproxError = view.findViewById(R.id.water_fountain_lateral_approx_error);
        hasCupHolderError = view.findViewById(R.id.water_fountain_has_cup_holder_error);

        allowLateralApprox = view.findViewById(R.id.other_allow_approx_radio);
        hasCupHolder = view.findViewById(R.id.other_has_cup_holder_radio);

        faucetHeightField = view.findViewById(R.id.other_water_fountain_faucet_height_field);
        cupHolderHeightField = view.findViewById(R.id.other_water_fountain_cup_holder_height_field);

        faucetHeightValue = view.findViewById(R.id.other_water_fountain_faucet_height_value);
        cupHolderHeightValue = view.findViewById(R.id.other_water_fountain_cup_holder_height_value);

        hasCupHolder.setOnCheckedChangeListener(this::hasCupHolderListener);

        modelFragments.getSaveAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (Objects.equals(modelFragments.getSaveAttempt().getValue(), 1)) {
                if (hasNoEmptyFields()) {
                    Bundle otherData = new Bundle();
                    otherData.putInt(ALLOW_LATERAL, getCheckedIndex(allowLateralApprox));
                    otherData.putDouble(FAUCET_HEIGHT, Double.parseDouble(Objects.requireNonNull(faucetHeightValue.getText()).toString()));
                    otherData.putInt(HAS_CUP_HOLDER, getCheckedIndex(hasCupHolder));
                    if (getCheckedIndex(hasCupHolder) == 1) {
                        otherData.putDouble(CUP_HOLDER_HEIGHT, Double.parseDouble(Objects.requireNonNull(cupHolderHeightValue.getText()).toString()));
                    }
                    modelFragments.setFountainBundle(otherData);
                    clearFields();
                    requireParentFragment().getChildFragmentManager().beginTransaction().remove(this).commit();
                }
                modelFragments.setSaveAttemptFountain(0);

            }

        });
    }

    public void hasCupHolderListener(RadioGroup group, int checkedID) {
        View radioButton = group.findViewById(checkedID);
        int index = group.indexOfChild(radioButton);

        switch (index) {
            case 0:
                cupHolderHeightValue.setText(null);
                cupHolderHeightField.setEnabled(false);
                break;
            case 1:
                cupHolderHeightField.setEnabled(true);
                break;
            default:
                break;
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
            faucetHeightField.setError(getString(R.string.blank_field_error));
            errors++;
        }
        if (hasCupHolder.getCheckedRadioButtonId() == -1) {
            hasCupHolderError.setVisibility(View.VISIBLE);
            errors++;
        }
        if (cupHolderHeightField.isEnabled() && TextUtils.isEmpty(cupHolderHeightValue.getText())) {
            cupHolderHeightField.setError(getString(R.string.blank_field_error));
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

    public void clearFields() {
        allowLateralApprox.clearCheck();
        hasCupHolder.clearCheck();
        faucetHeightValue.setText(null);
        cupHolderHeightValue.setText(null);
        cupHolderHeightField.setEnabled(false);
    }

    public int getCheckedIndex(RadioGroup group) {
        int buttonID = group.getCheckedRadioButtonId();
        View button = group.findViewById(buttonID);
        return group.indexOfChild(button);
    }

}