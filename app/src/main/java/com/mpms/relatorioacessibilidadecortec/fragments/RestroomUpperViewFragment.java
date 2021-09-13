package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomUpViewEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class RestroomUpperViewFragment extends Fragment {

    public static final String GATHER_UP_VIEW = "GATHER_UP_VIEW";

    ImageButton upperViewImgButton;
    Button saveUpMeasures, returnRestDoorData;
    Bundle restroomDataBundle, imgData;

    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, measureFieldE, upViewObsField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, measureValueE, upViewObsValue;

    Double measureA, measureB, measureC, measureD, measureE;
    String upViewObs;

    ViewModelEntry modelEntry;

    public RestroomUpperViewFragment() {
        // Required empty public constructor
    }

    public static RestroomUpperViewFragment newInstance() {
        return new RestroomUpperViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        restroomDataBundle = this.getArguments();
        return inflater.inflate(R.layout.fragment_restroom_upper_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        upperViewImgButton = view.findViewById(R.id.rest_upper_view_image);

        measureFieldA = view.findViewById(R.id.upper_view_A_measurement_field);
        measureFieldB = view.findViewById(R.id.upper_view_B_measurement_field);
        measureFieldC = view.findViewById(R.id.upper_view_C_measurement_field);
        measureFieldD = view.findViewById(R.id.upper_view_D_measurement_field);
        measureFieldE = view.findViewById(R.id.upper_view_E_measurement_field);
        upViewObsField = view.findViewById(R.id.upper_view_obs_field);

        measureValueA = view.findViewById(R.id.upper_view_A_measurement_value);
        measureValueB = view.findViewById(R.id.upper_view_B_measurement_value);
        measureValueC = view.findViewById(R.id.upper_view_C_measurement_value);
        measureValueD = view.findViewById(R.id.upper_view_D_measurement_value);
        measureValueE = view.findViewById(R.id.upper_view_E_measurement_value);
        upViewObsValue = view.findViewById(R.id.upper_view_obs_value);

        saveUpMeasures = view.findViewById(R.id.save_up_measurements);
        returnRestDoorData = view.findViewById(R.id.return_button);

        Glide.with(this).load(R.drawable.upperview).centerCrop().into(upperViewImgButton);

        upperViewImgButton.setOnClickListener( v -> {
            imgData.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.upperview);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgData);
        });

        if (restroomDataBundle.getBoolean(GATHER_UP_VIEW)) {
            modelEntry.getOneRestroomUpViewEntry(restroomDataBundle.getInt(RestroomFragment.RESTROOM_ID)).observe(getViewLifecycleOwner(), this::gatherUpViewData);
        }

        returnRestDoorData.setOnClickListener( v-> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveUpMeasures.setOnClickListener( v -> {
            if (checkEmptyMeasurementsFields()) {
                RestroomUpViewEntry newUpView = newRestUpView(restroomDataBundle);
                ViewModelEntry.insertRestroomUpViewEntry(newUpView);
                restroomDataBundle.putBoolean(GATHER_UP_VIEW, true);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RestroomSupportBarFragment barFragment = RestroomSupportBarFragment.newInstance();
                barFragment.setArguments(restroomDataBundle);
                fragmentTransaction.replace(R.id.show_fragment_selected, barFragment).addToBackStack(null).commit();
            }
        });
    }

    public boolean checkEmptyMeasurementsFields() {
        clearEmptyMeasurementsErrors();
        int i = 0;
        if (TextUtils.isEmpty(measureValueA.getText())) {
            i++;
            measureFieldA.setError(getText(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueB.getText())) {
            i++;
            measureFieldB.setError(getText(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueC.getText())) {
            i++;
            measureFieldC.setError(getText(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueD.getText())) {
            i++;
            measureFieldD.setError(getText(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueE.getText())) {
            i++;
            measureFieldE.setError(getText(R.string.blank_field_error));
        }
        return i == 0;
    }

    public void clearEmptyMeasurementsErrors() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        measureFieldE.setErrorEnabled(false);
    }

    public RestroomUpViewEntry newRestUpView(Bundle bundle) {
        measureA = Double.valueOf(String.valueOf(measureValueA.getText()));
        measureB = Double.valueOf(String.valueOf(measureValueB.getText()));
        measureC = Double.valueOf(String.valueOf(measureValueC.getText()));
        measureD = Double.valueOf(String.valueOf(measureValueD.getText()));
        measureE = Double.valueOf(String.valueOf(measureValueE.getText()));

        if(!TextUtils.isEmpty(upViewObsValue.getText()))
        upViewObs = String.valueOf(upViewObsValue.getText());

        return new RestroomUpViewEntry(bundle.getInt(RestroomFragment.RESTROOM_ID), measureA, measureB, measureC,
                measureD, measureE, upViewObs);
    }

    public void gatherUpViewData(RestroomUpViewEntry upViewEntry) {
        measureValueA.setText(String.valueOf(upViewEntry.getUpViewMeasureA()));
        measureValueB.setText(String.valueOf(upViewEntry.getUpViewMeasureB()));
        measureValueC.setText(String.valueOf(upViewEntry.getUpViewMeasureC()));
        measureValueD.setText(String.valueOf(upViewEntry.getUpViewMeasureD()));
        measureValueE.setText(String.valueOf(upViewEntry.getUpViewMeasureE()));
        upViewObsValue.setText(upViewEntry.getUpViewObs());
    }
}