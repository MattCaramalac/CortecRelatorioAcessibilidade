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

public class WaterFountainSpoutFragment extends Fragment {

    static final String ALLOW_FRONTAL = "ALLOW_FRONTAL";
    static final String HIGHEST_SPOUT = "HIGHEST_SPOUT";
    static final String LOWEST_SPOUT = "LOWEST_SPOUT";
    static final String FREE_SPACE_SPOUT = "FREE_SPACE_SPOUT";

    ViewModelFragments modelFragments;
    TextView allowApproxError;
    RadioGroup allowFrontalApprox;
    TextInputLayout highestSpoutField, lowestSpoutField, freeSpaceSpoutField;
    TextInputEditText highestSpoutValue, lowestSpoutValue, freeSpaceSpoutValue;

    public WaterFountainSpoutFragment() {
        // Required empty public constructor
    }

    public static WaterFountainSpoutFragment newInstance() {
        WaterFountainSpoutFragment fragment = new WaterFountainSpoutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_water_fountain_spout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allowApproxError = view.findViewById(R.id.water_fountain_frontal_approx_error);

        allowFrontalApprox = view.findViewById(R.id.spout_allow_approx_radio);

        highestSpoutField = view.findViewById(R.id.highest_spout_height_field);
        lowestSpoutField = view.findViewById(R.id.lowest_spout_height_field);
        freeSpaceSpoutField = view.findViewById(R.id.free_space_height_field);

        highestSpoutValue = view.findViewById(R.id.highest_spout_height_value);
        lowestSpoutValue = view.findViewById(R.id.lowest_spout_height_value);
        freeSpaceSpoutValue = view.findViewById(R.id.free_space_height_value);

        modelFragments.getSaveAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (Objects.equals(modelFragments.getSaveAttempt().getValue(), 1)) {
                if (hasNoEmptyFields()) {
                    Bundle spoutData = new Bundle();
                    spoutData.putInt(ALLOW_FRONTAL, getCheckedIndex(allowFrontalApprox));
                    spoutData.putDouble(HIGHEST_SPOUT, Double.parseDouble(Objects.requireNonNull(highestSpoutValue.getText()).toString()));
                    spoutData.putDouble(LOWEST_SPOUT, Double.parseDouble(Objects.requireNonNull(lowestSpoutValue.getText()).toString()));
                    spoutData.putDouble(FREE_SPACE_SPOUT, Double.parseDouble(Objects.requireNonNull(freeSpaceSpoutValue.getText()).toString()));
                    modelFragments.setFountainBundle(spoutData);
                    clearFields();
                    Objects.requireNonNull(getParentFragment()).getChildFragmentManager().beginTransaction().remove(this).commit();
                }
                modelFragments.saveAttemptTestWaterFountain(0);

            }

        });


    }

    public boolean hasNoEmptyFields() {
        clearErrorMessages();
        int errors = 0;
        if (allowFrontalApprox.getCheckedRadioButtonId() == -1) {
            allowApproxError.setVisibility(View.VISIBLE);
            errors++;
        }
        if (TextUtils.isEmpty(highestSpoutValue.getText())) {
            highestSpoutField.setError(getString(R.string.blank_field_error));
            errors++;
        }
        if (TextUtils.isEmpty(lowestSpoutValue.getText())) {
            lowestSpoutField.setError(getString(R.string.blank_field_error));
            errors++;
        }
        if (TextUtils.isEmpty(freeSpaceSpoutValue.getText())) {
            freeSpaceSpoutField.setError(getString(R.string.blank_field_error));
            errors++;
        }
        return errors == 0;
    }

    public void clearErrorMessages() {
        allowApproxError.setVisibility(View.GONE);
        highestSpoutField.setErrorEnabled(false);
        lowestSpoutField.setErrorEnabled(false);
        freeSpaceSpoutField.setErrorEnabled(false);


    }

    public void clearFields() {
        allowFrontalApprox.clearCheck();
        highestSpoutValue.setText(null);
        lowestSpoutValue.setText(null);
        freeSpaceSpoutValue.setText(null);
    }

    public int getCheckedIndex(RadioGroup group) {
        int buttonID = group.getCheckedRadioButtonId();
        View button = group.findViewById(buttonID);
        return group.indexOfChild(button);
    }


}