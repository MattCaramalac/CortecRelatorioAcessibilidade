package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;


public class WaterFountainFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout fountainLocationField, fountainTypeObsField, waterFountainObsField, photoField;
    TextInputEditText fountainLocationValue, fountainTypeObsValue, waterFountainObsValue, photoValue;
    RadioGroup typeWaterFountain;
    TextView typeWaterFountainError;
    Button cancelWaterFountain, saveWaterFountain;

    Bundle waterBundle = new Bundle();
    ArrayList<TextInputEditText> eText = new ArrayList<>();

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
            waterBundle = this.getArguments();
        } else
            waterBundle = new Bundle();
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

        getParentFragmentManager().setFragmentResult(MEMORIAL, waterBundle);

        if (waterBundle.getInt(FOUNTAIN_ID) > 0) {
            modelEntry.getOneWaterFountain(waterBundle.getInt(FOUNTAIN_ID)).observe(getViewLifecycleOwner(), this::loadFountainInfo);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

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
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        saveWaterFountain.setOnClickListener(v -> {
            if (indexRadio(typeWaterFountain) != -1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, waterBundle);
            else
                checkNoEmptyFieldsFountain();
        });

        cancelWaterFountain.setOnClickListener(v -> {
            if (waterBundle.getBoolean(FROM_ROOMS))
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            else
                requireActivity().getSupportFragmentManager().popBackStack(WATER_LIST, 0);
        });

    }

    private void clearFountainFields() {
        fountainLocationValue.setText(null);
        typeWaterFountain.clearCheck();
        fountainTypeObsValue.setText(null);
        waterFountainObsValue.setText(null);
        photoValue.setText(null);
        removeFountainFragments();
    }

    private void removeFountainFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.water_fountain_info);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private void instantiateFountainViews(View view) {
//        TextInputLayout
        fountainLocationField = view.findViewById(R.id.water_fountain_location_field);
        fountainTypeObsField = view.findViewById(R.id.water_fountain_type_obs_field);
        waterFountainObsField = view.findViewById(R.id.water_fountain_obs_field);
        photoField = view.findViewById(R.id.fountain_photo_field);
//        TextInputEditText
        fountainLocationValue = view.findViewById(R.id.water_fountain_location_value);
        fountainTypeObsValue = view.findViewById(R.id.water_fountain_type_obs_value);
        waterFountainObsValue = view.findViewById(R.id.water_fountain_obs_value);
        photoValue = view.findViewById(R.id.fountain_photo_value);
//        RadioGroup
        typeWaterFountain = view.findViewById(R.id.fountain_type_radio);
//        TextView
        typeWaterFountainError = view.findViewById(R.id.water_fountain_type_error);
//        MaterialButtons
        cancelWaterFountain = view.findViewById(R.id.cancel_waterfountain);
        saveWaterFountain = view.findViewById(R.id.save_waterfountain);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listener
        typeWaterFountain.setOnCheckedChangeListener(this::radioListener);
        eText.add(fountainTypeObsValue);
        eText.add(waterFountainObsValue);
        allowObsScroll(eText);
    }

    private void loadFountainInfo(WaterFountainEntry waterFountain) {
        if (waterFountain.getFountainLocation() != null)
            fountainLocationValue.setText(waterFountain.getFountainLocation());
        if (waterFountain.getFountainType() != null && waterFountain.getFountainType() > -1) {
            typeWaterFountain.check(typeWaterFountain.getChildAt(waterFountain.getFountainType()).getId());
            getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, waterBundle);
        }
        if (waterFountain.getFountainTypeObs() != null)
            fountainTypeObsValue.setText(waterFountain.getFountainTypeObs());
        if (waterFountain.getFountainObs() != null)
            waterFountainObsValue.setText(waterFountain.getFountainObs());
        if (waterFountain.getFountainPhoto() != null)
            photoValue.setText(waterFountain.getFountainPhoto());
    }

    public boolean checkNoEmptyFieldsFountain() {
        clearWaterFountainErrors();
        int errors = 0;
        if (TextUtils.isEmpty(fountainLocationValue.getText())) {
            errors++;
            fountainLocationField.setError(getString(R.string.req_field_error));
        }
        if (typeWaterFountain.getCheckedRadioButtonId() == -1) {
            typeWaterFountainError.setVisibility(View.VISIBLE);
            errors++;
        }
        if (indexRadio(typeWaterFountain) == 1 && TextUtils.isEmpty(fountainTypeObsValue.getText())) {
            errors++;
            fountainTypeObsField.setError(getString(R.string.req_field_error));
        }
        return errors == 0;
    }

    public void clearWaterFountainErrors() {
        fountainLocationField.setErrorEnabled(false);
        fountainTypeObsField.setErrorEnabled(false);
        typeWaterFountainError.setVisibility(View.GONE);
    }

    public WaterFountainEntry createFountain(Bundle bundle) {
        String fountainLocation, fountainTypeObs = null, latApproxObs = null, fountainObs = null, photo = null;
        int fountainType;
        Integer roomID = null, spoutDifHeight = null, spoutFrontApprox = null, otherSideApprox = null, otherCupHolder = null;
        Double spoutHighest = null, spoutLowest = null, spoutFrontHeight = null, spoutFrontDepth = null, otherHeight = null, otherCupHeight = null;

        if (bundle.getInt(AMBIENT_ID) != 0)
            roomID = bundle.getInt(AMBIENT_ID);
        fountainLocation = String.valueOf(fountainLocationValue.getText());
        fountainType = indexRadio(typeWaterFountain);
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

        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new WaterFountainEntry(bundle.getInt(BLOCK_ID), roomID, fountainLocation, fountainType,
                fountainTypeObs, otherSideApprox, latApproxObs, otherHeight, otherCupHolder, otherCupHeight, spoutDifHeight, spoutHighest, spoutLowest, spoutFrontApprox,
                spoutFrontDepth, spoutFrontHeight, fountainObs, photo);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);
        switch (index) {
            case 0:
                removeFountainFragments();
                fountainTypeObsValue.setText(null);
                fountainTypeObsField.setVisibility(View.GONE);
                Fragment fragment = new WaterFountainSpoutFragment();
                if (waterBundle.getInt(FOUNTAIN_ID) > 0)
                    fragment.setArguments(waterBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.water_fountain_info, fragment).commit();
                break;
            case 1:
                removeFountainFragments();
                fountainTypeObsField.setVisibility(View.VISIBLE);
                Fragment fragment2 = new WaterFountainOtherFragment();
                if (waterBundle.getInt(FOUNTAIN_ID) > 0)
                    fragment2.setArguments(waterBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.water_fountain_info, fragment2).commit();
                break;
            default:
                break;
        }
    }
}
