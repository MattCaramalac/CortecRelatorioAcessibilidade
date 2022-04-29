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
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class WaterFountainSpoutFragment extends Fragment {

    public static final String HAS_DIFFERENT_HEIGHTS = "HAS_DIFFERENT_HEIGHTS";
    public static final String HIGHEST_SPOUT = "HIGHEST_SPOUT";
    public static final String LOWEST_SPOUT = "LOWEST_SPOUT";
    public static final String ALLOW_FRONTAL = "ALLOW_FRONTAL";
    public static final String FRONTAL_APPROX_HEIGHT = "FRONTAL_APPROX_HEIGHT";
    public static final String FRONTAL_APPROX_DEPTH = "FRONTAL_APPROX_DEPTH";

    TextInputLayout highestSpoutField, lowestSpoutField, frontalApproxHeightField, frontalApproxDepthField;
    TextInputEditText highestSpoutValue, lowestSpoutValue, frontalApproxHeightValue, frontalApproxDepthValue;
    TextView diffHeightsError, allowApproxError;
    RadioGroup hasDiffHeightsSpouts, allowFrontalApprox;

    ViewModelEntry modelEntry;

    Bundle childBundle = new Bundle();

    public WaterFountainSpoutFragment() {
        // Required empty public constructor
    }

    public static WaterFountainSpoutFragment newInstance() {
        return new WaterFountainSpoutFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            childBundle.putInt(WaterFountainFragment.FOUNTAIN_ID, this.getArguments().getInt(WaterFountainFragment.FOUNTAIN_ID));
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

        instantiateSpoutFountainViews(view);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (childBundle.getInt(WaterFountainFragment.FOUNTAIN_ID) > 0)
            modelEntry.getOneWaterFountain(childBundle.getInt(WaterFountainFragment.FOUNTAIN_ID)).observe(getViewLifecycleOwner(), this::loadSpoutFountainData);

//        getParentFragmentManager().setFragmentResultListener(InspectionActivity.LOAD_CHILD_DATA, this, (key, bundle) -> {
//            modelEntry.getOneWaterFountain(bundle.getInt(WaterFountainFragment.FOUNTAIN_ID)).observe(getViewLifecycleOwner(), this::loadSpoutFountainData);
//        });

        getParentFragmentManager().setFragmentResultListener(InspectionActivity.GATHER_CHILD_DATA, this, (key, bundle) -> {
            gatherSpoutFountainData(bundle);
            getParentFragmentManager().setFragmentResult(InspectionActivity.CHILD_DATA_LISTENER, bundle);
        });
    }


    private void instantiateSpoutFountainViews(View view) {
//        TextInputLayout
        highestSpoutField = view.findViewById(R.id.highest_spout_height_field);
        lowestSpoutField = view.findViewById(R.id.lowest_spout_height_field);
        frontalApproxHeightField = view.findViewById(R.id.frontal_approx_height_field);
        frontalApproxDepthField = view.findViewById(R.id.frontal_approx_depth_field);
//        TextInputEditText
        highestSpoutValue = view.findViewById(R.id.highest_spout_height_value);
        lowestSpoutValue = view.findViewById(R.id.lowest_spout_height_value);
        frontalApproxHeightValue = view.findViewById(R.id.frontal_approx_height_value);
        frontalApproxDepthValue = view.findViewById(R.id.frontal_approx_depth_value);
//        RadioGroup
        allowFrontalApprox = view.findViewById(R.id.spout_allow_approx_radio);
        hasDiffHeightsSpouts = view.findViewById(R.id.spout_different_heights_radio);
//        TextView
        allowApproxError = view.findViewById(R.id.water_fountain_frontal_approx_error);
        diffHeightsError = view.findViewById(R.id.different_heights_fountain_error);
//        ViewModel
//        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Initial Layout
        highestSpoutField.setHint("Altura da Bica (m)");
//        Listeners
        hasDiffHeightsSpouts.setOnCheckedChangeListener(this::waterSpoutListener);
        allowFrontalApprox.setOnCheckedChangeListener(this::waterSpoutListener);
    }

    private void waterSpoutListener(RadioGroup radio, int checkedID) {
        int checkIndex = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == hasDiffHeightsSpouts) {
            if (checkIndex == 1) {
                highestSpoutField.setHint("Altura da Bica Mais Alta (m)");
                lowestSpoutField.setVisibility(View.VISIBLE);
            } else {
                highestSpoutField.setHint("Altura da Bica (m)");
                lowestSpoutValue.setText(null);
                lowestSpoutField.setVisibility(View.GONE);
            }
        } else if (radio == allowFrontalApprox) {
            if (checkIndex == 1) {
                frontalApproxHeightField.setVisibility(View.VISIBLE);
                frontalApproxDepthField.setVisibility(View.VISIBLE);
            } else {
                frontalApproxHeightValue.setText(null);
                frontalApproxHeightField.setVisibility(View.GONE);
                frontalApproxDepthValue.setText(null);
                frontalApproxDepthField.setVisibility(View.GONE);
            }
        }

    }

    private void loadSpoutFountainData(WaterFountainEntry fountainEntry) {
        hasDiffHeightsSpouts.check(hasDiffHeightsSpouts.getChildAt(fountainEntry.getHasSpoutsDifferentHeights()).getId());
        switch (fountainEntry.getHasSpoutsDifferentHeights()) {
            case 1:
                lowestSpoutValue.setText(String.valueOf(fountainEntry.getLowestSpoutHeight()));
            case 0:
                highestSpoutValue.setText(String.valueOf(fountainEntry.getHighestSpoutHeight()));
                break;
        }
        allowFrontalApprox.check(allowFrontalApprox.getChildAt(fountainEntry.getAllowFrontApprox()).getId());
        if (fountainEntry.getAllowFrontApprox() == 1) {
            frontalApproxHeightValue.setText(String.valueOf(fountainEntry.getFrontalApproxLowestSpout()));
            frontalApproxDepthValue.setText(String.valueOf(fountainEntry.getFrontalApproxDepth()));
        }
    }

    private void gatherSpoutFountainData(Bundle bundle) {
        bundle.putBoolean(InspectionActivity.CHILD_DATA_COMPLETE, hasNoEmptyFields());
        if (hasNoEmptyFields()) {
            bundle.putInt(HAS_DIFFERENT_HEIGHTS, getCheckedIndex(hasDiffHeightsSpouts));
            bundle.putDouble(HIGHEST_SPOUT, Double.parseDouble(String.valueOf(highestSpoutValue.getText())));
            if (getCheckedIndex(hasDiffHeightsSpouts) == 1)
                bundle.putDouble(LOWEST_SPOUT, Double.parseDouble(String.valueOf(lowestSpoutValue.getText())));
            bundle.putInt(ALLOW_FRONTAL, getCheckedIndex(allowFrontalApprox));
            if (getCheckedIndex(allowFrontalApprox) == 1) {
                bundle.putDouble(FRONTAL_APPROX_HEIGHT, Double.parseDouble(String.valueOf(frontalApproxHeightValue.getText())));
                bundle.putDouble(FRONTAL_APPROX_DEPTH, Double.parseDouble(String.valueOf(frontalApproxDepthValue.getText())));
            }
        }
    }


    public boolean hasNoEmptyFields() {
        clearErrorMessages();
        int errors = 0;
        if (getCheckedIndex(hasDiffHeightsSpouts) == -1) {
            errors++;
            diffHeightsError.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(highestSpoutValue.getText())) {
            highestSpoutField.setError(getString(R.string.blank_field_error));
            errors++;
        }
        if (getCheckedIndex(hasDiffHeightsSpouts) == 1) {
            if (TextUtils.isEmpty(lowestSpoutValue.getText())) {
                lowestSpoutField.setError(getString(R.string.blank_field_error));
                errors++;
            }
        }
        if (getCheckedIndex(allowFrontalApprox) == -1) {
            allowApproxError.setVisibility(View.VISIBLE);
            errors++;
        } else if (getCheckedIndex(allowFrontalApprox) == 1) {
            if (TextUtils.isEmpty(frontalApproxHeightValue.getText())) {
                frontalApproxHeightField.setError(getString(R.string.blank_field_error));
                errors++;
            }
            if (TextUtils.isEmpty(frontalApproxDepthValue.getText())) {
                frontalApproxDepthField.setError(getString(R.string.blank_field_error));
                errors++;
            }
        }
        return errors == 0;
    }

    public void clearErrorMessages() {
        diffHeightsError.setVisibility(View.GONE);
        allowApproxError.setVisibility(View.GONE);
        highestSpoutField.setErrorEnabled(false);
        lowestSpoutField.setErrorEnabled(false);
        frontalApproxHeightField.setErrorEnabled(false);
    }

    public int getCheckedIndex(RadioGroup group) {
        return group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
    }

}