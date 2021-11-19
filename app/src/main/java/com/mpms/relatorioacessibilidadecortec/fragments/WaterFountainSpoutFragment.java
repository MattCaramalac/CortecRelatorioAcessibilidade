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

public class WaterFountainSpoutFragment extends Fragment {

    static final String HAS_DIFFERENT_HEIGHTS = "HAS_DIFFERENT_HEIGHTS";
    static final String HIGHEST_SPOUT = "HIGHEST_SPOUT";
    static final String LOWEST_SPOUT = "LOWEST_SPOUT";
    static final String ALLOW_FRONTAL = "ALLOW_FRONTAL";
    static final String FRONTAL_APPROX_SPOUT = "FRONTAL_APPROX_SPOUT";
    static final String SPOUT_FOUNTAIN_OBS = "SPOUT_FOUNTAIN_OBS";

    TextInputLayout highestSpoutField, lowestSpoutField, frontalApproxSpoutField, spoutObsField;
    TextInputEditText highestSpoutValue, lowestSpoutValue, frontalApproxSpoutValue, spoutObsValue;
    TextView diffHeightsError, allowApproxError;
    RadioGroup hasDiffHeightsSpouts, allowFrontalApprox;

    ViewModelFragments modelFragments;

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
        hasDiffHeightsSpouts.setOnCheckedChangeListener(this::waterSpoutListener);
        allowFrontalApprox.setOnCheckedChangeListener(this::waterSpoutListener);
        allowSpoutFountainObsScroll();

        modelFragments.getFountainFragData().observe(getViewLifecycleOwner(), waterFrag -> {
            if (waterFrag != null) {
                gatherSpoutFountainData(waterFrag);
            }
        });

        modelFragments.getSaveAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (Objects.equals(modelFragments.getSaveAttempt().getValue(), 1)) {
                if (hasNoEmptyFields()) {
                    Bundle spoutData = new Bundle();
                    spoutData.putInt(HAS_DIFFERENT_HEIGHTS, getCheckedIndex(hasDiffHeightsSpouts));
                    spoutData.putDouble(HIGHEST_SPOUT, Double.parseDouble(String.valueOf(highestSpoutValue.getText())));
                    if (getCheckedIndex(hasDiffHeightsSpouts) == 1)
                        spoutData.putDouble(LOWEST_SPOUT, Double.parseDouble(String.valueOf(lowestSpoutValue.getText())));
                    spoutData.putInt(ALLOW_FRONTAL, getCheckedIndex(allowFrontalApprox));
                    if (getCheckedIndex(allowFrontalApprox) == 1)
                        spoutData.putDouble(FRONTAL_APPROX_SPOUT, Double.parseDouble(String.valueOf(frontalApproxSpoutValue.getText())));
                    spoutData.putString(SPOUT_FOUNTAIN_OBS, String.valueOf(spoutObsValue.getText()));
                    modelFragments.setFountainBundle(spoutData);
                    clearFields();
                    requireParentFragment().getChildFragmentManager().beginTransaction().remove(this).commit();
                }
                modelFragments.setSaveAttemptFountain(0);
            }
        });
    }



    private void instantiateSpoutFountainViews(View view) {
//        TextInputLayout
        highestSpoutField = view.findViewById(R.id.highest_spout_height_field);
        lowestSpoutField = view.findViewById(R.id.lowest_spout_height_field);
        frontalApproxSpoutField = view.findViewById(R.id.free_space_height_field);
        spoutObsField = view.findViewById(R.id.spout_water_fountain_obs_field);
//        TextInputEditText
        highestSpoutValue = view.findViewById(R.id.highest_spout_height_value);
        lowestSpoutValue = view.findViewById(R.id.lowest_spout_height_value);
        frontalApproxSpoutValue = view.findViewById(R.id.free_space_height_value);
        spoutObsValue = view.findViewById(R.id.spout_water_fountain_obs_value);
//        RadioGroup
        allowFrontalApprox = view.findViewById(R.id.spout_allow_approx_radio);
        hasDiffHeightsSpouts = view.findViewById(R.id.spout_different_heights_radio);
//        TextView
        allowApproxError = view.findViewById(R.id.water_fountain_frontal_approx_error);
        diffHeightsError = view.findViewById(R.id.different_heights_fountain_error);
//        ViewModel
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        Initial Layout
        highestSpoutField.setHint("Altura da Bica (m)");
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
        }
        else if (radio == allowFrontalApprox) {
            if (checkIndex == 1)
                frontalApproxSpoutField.setVisibility(View.VISIBLE);
            else {
                frontalApproxSpoutValue.setText(null);
                frontalApproxSpoutField.setVisibility(View.GONE);
            }
        }

    }

    private void gatherSpoutFountainData(Bundle bundle) {
        hasDiffHeightsSpouts.check(hasDiffHeightsSpouts.getChildAt(bundle.getInt(HAS_DIFFERENT_HEIGHTS)).getId());
        highestSpoutValue.setText(String.valueOf(bundle.getDouble(HIGHEST_SPOUT)));
        lowestSpoutValue.setText(String.valueOf(bundle.getDouble(LOWEST_SPOUT)));
        allowFrontalApprox.check(allowFrontalApprox.getChildAt(bundle.getInt(ALLOW_FRONTAL)).getId());
        frontalApproxSpoutValue.setText(String.valueOf(bundle.getDouble(FRONTAL_APPROX_SPOUT)));
        spoutObsValue.setText(bundle.getString(SPOUT_FOUNTAIN_OBS));
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSpoutFountainObsScroll() {
        spoutObsValue.setOnTouchListener(this::scrollingField);
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
            if (TextUtils.isEmpty(frontalApproxSpoutValue.getText())) {
                frontalApproxSpoutField.setError(getString(R.string.blank_field_error));
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
        frontalApproxSpoutField.setErrorEnabled(false);
    }

    public void clearFields() {
        hasDiffHeightsSpouts.clearCheck();
        allowFrontalApprox.clearCheck();
        highestSpoutValue.setText(null);
        lowestSpoutValue.setText(null);
        frontalApproxSpoutValue.setText(null);
    }

    public int getCheckedIndex(RadioGroup group) {
        return group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
    }

}