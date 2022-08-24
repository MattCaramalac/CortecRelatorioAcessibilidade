package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.WaterFountainOtherFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.WaterFountainSpoutFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;


public class WaterFountainFragment extends Fragment implements TagInterface {

    TextInputLayout fountainLocationField, fountainTypeObsField, waterFountainObsField;
    TextInputEditText fountainLocationValue, fountainTypeObsValue, waterFountainObsValue;
    RadioGroup typeWaterFountain;
    TextView typeWaterFountainError;
    Button cancelWaterFountain, saveWaterFountain;

    Bundle waterFountainBundle = new Bundle();

    ViewModelEntry modelEntry;

    public WaterFountainFragment() {
        // Required empty public constructor
    }

    public static WaterFountainFragment newInstance() {
        return new WaterFountainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            waterFountainBundle.putInt(BLOCK_ID, this.getArguments().getInt(BLOCK_ID));
            waterFountainBundle.putInt(AMBIENT_ID, this.getArguments().getInt(AMBIENT_ID));
            waterFountainBundle.putInt(FOUNTAIN_ID, this.getArguments().getInt(FOUNTAIN_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_water_fountain, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateFountainViews(view);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (waterFountainBundle.getInt(FOUNTAIN_ID) > 0) {
            modelEntry.getOneWaterFountain(waterFountainBundle.getInt(FOUNTAIN_ID)).observe(getViewLifecycleOwner(), this::loadFountainInfo);
        }

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE) && checkNoEmptyFieldsFountain()) {
                WaterFountainEntry newFountain = createFountain(bundle);
                if (bundle.getInt(FOUNTAIN_ID) > 0) {
                    newFountain.setWaterFountainID(bundle.getInt(FOUNTAIN_ID));
                    ViewModelEntry.updateWaterFountain(newFountain);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.WATER_LIST, 0);
                } else if (bundle.getInt(FOUNTAIN_ID) == 0) {
                    ViewModelEntry.insertWaterFountain(newFountain);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearFountainFields();
                }
            }
        });

        saveWaterFountain.setOnClickListener(v -> {
            if (getCheckedFountainType(typeWaterFountain) != -1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, waterFountainBundle);
            else
                checkNoEmptyFieldsFountain();
        });

        cancelWaterFountain.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack(WATER_LIST, 0));
    }

    private void clearFountainFields() {
        fountainLocationValue.setText(null);
        typeWaterFountain.clearCheck();
        fountainTypeObsValue.setText(null);
        waterFountainObsValue.setText(null);
        removeFountainFragments();
    }

    private void removeFountainFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.fountain_type_radio);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();

    }

    private void instantiateFountainViews(View view) {
//        TextInputLayout
        fountainLocationField = view.findViewById(R.id.water_fountain_location_field);
        fountainTypeObsField = view.findViewById(R.id.water_fountain_type_obs_field);
        waterFountainObsField = view.findViewById(R.id.water_fountain_obs_field);
//        TextInputEditText
        fountainLocationValue = view.findViewById(R.id.water_fountain_location_value);
        fountainTypeObsValue = view.findViewById(R.id.water_fountain_type_obs_value);
        waterFountainObsValue = view.findViewById(R.id.water_fountain_obs_value);
//        RadioGroup
        typeWaterFountain = view.findViewById(R.id.fountain_type_radio);
//        TextView
        typeWaterFountainError = view.findViewById(R.id.water_fountain_type_error);
//        MaterialButtons
        cancelWaterFountain = view.findViewById(R.id.cancel_waterfountain);
        saveWaterFountain = view.findViewById(R.id.save_waterfountain);
//        ViewModels
//        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listener
        typeWaterFountain.setOnCheckedChangeListener(this::typeFountainListener);
        allowWaterFountainObsScroll();
    }

    public void typeFountainListener(RadioGroup group, int checkedID) {
        int index = getCheckedFountainType(group);
        Fragment fragment;
        switch (index) {
            case 0:
                fragment = new WaterFountainSpoutFragment();
                if (waterFountainBundle.getInt(FOUNTAIN_ID) > 0)
                    fragment.setArguments(waterFountainBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.water_fountain_info, fragment).commit();
                break;
            case 1:
                fragment = new WaterFountainOtherFragment();
                if (waterFountainBundle.getInt(FOUNTAIN_ID) > 0)
                    fragment.setArguments(waterFountainBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.water_fountain_info, fragment).commit();
                break;
            default:
                break;
        }

    }

    private void loadFountainInfo(WaterFountainEntry waterFountain) {
        fountainLocationValue.setText(waterFountain.getFountainLocation());
        typeWaterFountain.check(typeWaterFountain.getChildAt(waterFountain.getFountainType()).getId());
        if (waterFountain.getFountainTypeObs() != null)
            fountainTypeObsValue.setText(waterFountain.getFountainTypeObs());
        Fragment fragment;
        if (waterFountain.getFountainType() == 0) {
            fragment = new WaterFountainSpoutFragment();
            fragment.setArguments(waterFountainBundle);
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.water_fountain_info, fragment).commit();
        } else if (waterFountain.getFountainType() == 1) {
            fragment = new WaterFountainOtherFragment();
            fragment.setArguments(waterFountainBundle);
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.water_fountain_info, fragment).commit();
        }
    }

    public boolean checkNoEmptyFieldsFountain() {
        clearWaterFountainErrors();
        int errors = 0;
        if (TextUtils.isEmpty(fountainLocationValue.getText())) {
            errors++;
            fountainLocationField.setError(getString(R.string.blank_field_error));
        }
        if (typeWaterFountain.getCheckedRadioButtonId() == -1) {
            typeWaterFountainError.setVisibility(View.VISIBLE);
            errors++;
        }
        return errors == 0;
    }

    private int getCheckedFountainType(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void clearWaterFountainErrors(){
        fountainLocationField.setErrorEnabled(false);
        typeWaterFountainError.setVisibility(View.GONE);
    }

    public WaterFountainEntry createFountain(Bundle bundle) {
        String fountainLocation, fountainTypeObs = null, latApproxObs = null, fountainObs = null;
        Integer fountainType, spoutDifHeight = null, spoutFrontApprox = null, otherSideApprox = null, otherCupHolder = null;
        Double spoutHighest = null, spoutLowest = null, spoutFrontHeight = null, spoutFrontDepth = null, otherHeight = null, otherCupHeight = null;

        fountainLocation = String.valueOf(fountainLocationValue.getText());
        fountainType = getCheckedFountainType(typeWaterFountain);
        if (!TextUtils.isEmpty(fountainTypeObsValue.getText()))
            fountainTypeObs = String.valueOf(fountainTypeObsValue.getText());
        if (fountainType == 0) {
            spoutDifHeight = bundle.getInt(HAS_DIFFERENT_HEIGHTS);
            spoutHighest = bundle.getDouble(HIGHEST_SPOUT);
            if (bundle.getInt(LOWEST_SPOUT) != 0)
                spoutLowest = bundle.getDouble(LOWEST_SPOUT);
            spoutFrontApprox = bundle.getInt(ALLOW_FRONTAL);
            if (spoutFrontApprox == 1) {
                spoutFrontHeight = bundle.getDouble(FRONTAL_APPROX_HEIGHT);
                spoutFrontDepth = bundle.getDouble(FRONTAL_APPROX_DEPTH);
            }
        } else {
            otherSideApprox = bundle.getInt(ALLOW_LATERAL);
            if (bundle.getString(LAT_APPROX_OBS) != null)
                latApproxObs = bundle.getString(LAT_APPROX_OBS);
            otherHeight = bundle.getDouble(FAUCET_HEIGHT);
            otherCupHolder = bundle.getInt(HAS_CUP_HOLDER);
            if (otherCupHolder == 1) {
                otherCupHeight = bundle.getDouble(CUP_HOLDER_HEIGHT);
            }
        }
        if (!TextUtils.isEmpty(waterFountainObsValue.getText()))
            fountainObs = String.valueOf(waterFountainObsValue.getText());

        return new WaterFountainEntry(bundle.getInt(BLOCK_ID), bundle.getInt(AMBIENT_ID), fountainLocation, fountainType,
                fountainTypeObs, otherSideApprox, latApproxObs, otherHeight, otherCupHolder, otherCupHeight, spoutDifHeight, spoutHighest, spoutLowest, spoutFrontApprox,
                spoutFrontDepth, spoutFrontHeight, fountainObs);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowWaterFountainObsScroll() {
        fountainTypeObsValue.setOnTouchListener(this::scrollingField);
        waterFountainObsValue.setOnTouchListener(this::scrollingField);
    }

}
