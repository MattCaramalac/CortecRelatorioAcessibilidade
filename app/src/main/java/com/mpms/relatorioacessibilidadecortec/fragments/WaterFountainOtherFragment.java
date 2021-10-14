package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
    static final String OTHER_FOUNTAIN_OBS = "OTHER_FOUNTAIN_OBS";

    ViewModelFragments modelFragments;
    TextView lateralApproxError, hasCupHolderError;
    RadioGroup allowLateralApprox, hasCupHolder;
    TextInputLayout faucetHeightField, cupHolderHeightField, fountainObsField;
    TextInputEditText faucetHeightValue, cupHolderHeightValue, fountainObsValue;

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

        instantiateOtherViews(view);
        allowOtherFountainObsScroll();

        hasCupHolder.setOnCheckedChangeListener(this::hasCupHolderListener);

        modelFragments.getFountainFragData().observe(getViewLifecycleOwner(), waterFrag -> {
            if (waterFrag != null) {
                gatherOtherFountainData(waterFrag);
            }
        });

        modelFragments.getSaveAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (Objects.equals(modelFragments.getSaveAttempt().getValue(), 1)) {
                if (hasNoEmptyFields()) {
                    Bundle otherData = new Bundle();
                    otherData.putInt(ALLOW_LATERAL, getCheckedIndex(allowLateralApprox));
                    otherData.putDouble(FAUCET_HEIGHT, Double.parseDouble(String.valueOf(faucetHeightValue.getText())));
                    otherData.putInt(HAS_CUP_HOLDER, getCheckedIndex(hasCupHolder));
                    if (getCheckedIndex(hasCupHolder) == 1) {
                        otherData.putDouble(CUP_HOLDER_HEIGHT, Double.parseDouble(String.valueOf(cupHolderHeightValue.getText())));
                    }
                    if (!TextUtils.isEmpty(fountainObsValue.getText()))
                        otherData.putString(OTHER_FOUNTAIN_OBS,String.valueOf(fountainObsValue.getText()));
                    modelFragments.setFountainBundle(otherData);
                    clearFields();
                    requireParentFragment().getChildFragmentManager().beginTransaction().remove(this).commit();
                }
                modelFragments.setSaveAttemptFountain(0);

            }

        });
    }

    private void gatherOtherFountainData(Bundle bundle) {
        allowLateralApprox.check(allowLateralApprox.getChildAt(bundle.getInt(ALLOW_LATERAL)).getId());
        faucetHeightValue.setText(String.valueOf(bundle.getDouble(FAUCET_HEIGHT)));
        hasCupHolder.check(hasCupHolder.getChildAt(bundle.getInt(HAS_CUP_HOLDER)).getId());
        if (bundle.getInt(HAS_CUP_HOLDER) > 0)
            cupHolderHeightValue.setText(String.valueOf(bundle.getDouble(CUP_HOLDER_HEIGHT)));
        fountainObsValue.setText(bundle.getString(OTHER_FOUNTAIN_OBS));
    }

    private void instantiateOtherViews(View view) {
        lateralApproxError = view.findViewById(R.id.water_fountain_lateral_approx_error);
        hasCupHolderError = view.findViewById(R.id.water_fountain_has_cup_holder_error);

        allowLateralApprox = view.findViewById(R.id.other_allow_approx_radio);
        hasCupHolder = view.findViewById(R.id.other_has_cup_holder_radio);

        faucetHeightField = view.findViewById(R.id.other_water_fountain_faucet_height_field);
        cupHolderHeightField = view.findViewById(R.id.other_water_fountain_cup_holder_height_field);
        fountainObsField = view.findViewById(R.id.other_water_fountain_obs_field);

        faucetHeightValue = view.findViewById(R.id.other_water_fountain_faucet_height_value);
        cupHolderHeightValue = view.findViewById(R.id.other_water_fountain_cup_holder_height_value);
        fountainObsValue = view.findViewById(R.id.other_water_fountain_obs_value);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowOtherFountainObsScroll() {
            fountainObsValue.setOnTouchListener(this::scrollingField);
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
        return group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
    }

}