package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

import java.util.Objects;

public class WaterFountainFragment extends Fragment {

    private static int chosenOption;

    TextInputLayout waterFountainHeightField, cupHolderHeightField, approximationField;
    TextInputEditText waterFountainHeightValue, cupHolderHeightValue, approximationValue;

    public static int schoolID;

    public WaterFountainFragment() {
        // Required empty public constructor
    }

    public static WaterFountainFragment newInstance(int dropdownChoice) {
        WaterFountainFragment waterFountainFragment = new WaterFountainFragment();
        waterFountainFragment.setChosenOption(dropdownChoice);
        return waterFountainFragment;
    }

    public void setChosenOption(int choice) {
        WaterFountainFragment.chosenOption = choice;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle schoolBundle = this.getArguments();
        if (schoolBundle != null)
            schoolID = schoolBundle.getInt(InspectionActivity.SCHOOL_ID_VALUE);
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
        setHeaderText(view);

        Button cancelWaterfountain = view.findViewById(R.id.cancel_waterfountain);
        Button saveWaterFountain = view.findViewById(R.id.save_waterfountain);

//        saveWaterFountain.setOnClickListener(v -> {
//            if(verifyWaterFountainErrors()) {
//                WaterFountainEntry newWaterFountain = createWaterFountainEntry(schoolID);
//                ViewModelEntry.insertWaterFountain(newWaterFountain);
//                clearTextFields();
//            }
//        });

        cancelWaterfountain.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.water_fountain_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }

//    public boolean verifyWaterFountainErrors() {
//        clearWaterFountainErrors();
//        int i = 0;
//        if(TextUtils.isEmpty(waterFountainHeightValue.getText())){
//            waterFountainHeightField.setError(getString(R.string.blank_field_error));
//            i++;
//        }
//        if(TextUtils.isEmpty(cupHolderHeightValue.getText())){
//            cupHolderHeightField.setError(getString(R.string.blank_field_error));
//            i++;
//        }
//        if(TextUtils.isEmpty(approximationValue.getText())){
//            approximationField.setError(getString(R.string.blank_field_error));
//            i++;
//        }
//        return i <= 0;
//    }
//
//    public void clearWaterFountainErrors(){
//        waterFountainHeightField.setErrorEnabled(false);
//        cupHolderHeightField.setErrorEnabled(false);
//        approximationField.setErrorEnabled(false);
//    }

    public void clearTextFields() {
        waterFountainHeightValue.setText(null);
        cupHolderHeightValue.setText(null);
        approximationValue.setText(null);
    }

    public WaterFountainEntry createWaterFountainEntry(Integer schoolID) {
        return new WaterFountainEntry(schoolID, 0, 0, 0.00,
                0, 0.00, 0, 0.00,
                0.01, 0.02);
    }

//    (@NonNull Integer schoolEntryID, @NonNull Integer typeWaterFountain, Integer otherAllowSideApproximation,
//    Double otherFaucetHeight, Integer otherHasCupHolder, Double otherCupHolderHeight,
//    Integer spoutAllowFrontalApproximation, Double highestSpoutHeight, Double lowestSpoutHeight,
//    Double freeSpaceLowestSpout) {

}
