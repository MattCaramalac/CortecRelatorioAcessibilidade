package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestUpViewUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class RestUpViewFragment extends Fragment implements TagInterface {

    ImageButton upperViewImgButton;
    Button saveUpMeasures, returnRestDoorData;
    Bundle restUpBundle, imgData;

    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, measureFieldE, restLengthField, restWidthField, upViewObsField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, measureValueE, restLengthValue, restWidthValue, upViewObsValue;

    ViewModelEntry modelEntry;

    int recentEntry = 0;

    public RestUpViewFragment() {
        // Required empty public constructor
    }

    public static RestUpViewFragment newInstance() {
        return new RestUpViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (this.getArguments() != null)
            restUpBundle = new Bundle(this.getArguments());
        else
            restUpBundle = new Bundle();
        imgData = new Bundle();
        return inflater.inflate(R.layout.fragment_restroom_upper_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateUpperViews(view);
        allowUpperViewObsScroll();

        Glide.with(this).load(R.drawable.upperview).centerCrop().into(upperViewImgButton);

        upperViewImgButton.setOnClickListener(v -> {
            imgData.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.upperview);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgData);
        });


//        modelEntry.getLastRestroomUpViewEntry().observe(getViewLifecycleOwner(), upViewEntry -> {
//            if (recentEntry == 1) {
//                recentEntry = 0;
//                restroomDataBundle.putInt(REST_UP_ID, upViewEntry.getUpViewID());
//                callSupBarFragment(restroomDataBundle);
//            }
//        });

        modelEntry.getRestUpViewData(restUpBundle.getInt(REST_ID))
                .observe(getViewLifecycleOwner(), upView -> {
                    if (upView != null)
                        loadUpViewData(upView);
                });

        returnRestDoorData.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveUpMeasures.setOnClickListener(v -> {
            if (checkEmptyMeasurementsFields()) {
                RestUpViewUpdate newUpView = newRestUpView(restUpBundle);
                ViewModelEntry.updateRestUpViewData(newUpView);
                callToiletFragment(restUpBundle);
            }
        });
    }

    private void instantiateUpperViews(View view) {
//        ImageButton
        upperViewImgButton = view.findViewById(R.id.rest_upper_view_image);
//        TextInputLayout
        measureFieldA = view.findViewById(R.id.upper_view_A_measurement_field);
        measureFieldB = view.findViewById(R.id.upper_view_B_measurement_field);
        measureFieldC = view.findViewById(R.id.upper_view_C_measurement_field);
        measureFieldD = view.findViewById(R.id.upper_view_D_measurement_field);
        measureFieldE = view.findViewById(R.id.upper_view_E_measurement_field);
        restLengthField = view.findViewById(R.id.upper_view_length_field);
        restWidthField = view.findViewById(R.id.upper_view_width_field);
        upViewObsField = view.findViewById(R.id.upper_view_obs_field);
//        TextInputEditText
        measureValueA = view.findViewById(R.id.upper_view_A_measurement_value);
        measureValueB = view.findViewById(R.id.upper_view_B_measurement_value);
        measureValueC = view.findViewById(R.id.upper_view_C_measurement_value);
        measureValueD = view.findViewById(R.id.upper_view_D_measurement_value);
        measureValueE = view.findViewById(R.id.upper_view_E_measurement_value);
        restLengthValue = view.findViewById(R.id.upper_view_length_value);
        restWidthValue = view.findViewById(R.id.upper_view_width_value);
        upViewObsValue = view.findViewById(R.id.upper_view_obs_value);
//        MaterialButton
        saveUpMeasures = view.findViewById(R.id.save_up_measurements);
        returnRestDoorData = view.findViewById(R.id.return_button);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }


    @SuppressLint("ClickableViewAccessibility")
    private void allowUpperViewObsScroll() {
        upViewObsValue.setOnTouchListener(this::scrollingField);
    }

    public void callToiletFragment(Bundle bundle) {
        RestToiletFragment barFragment = RestToiletFragment.newInstance();
        barFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.show_fragment_selected, barFragment).addToBackStack(null).commit();
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
        if (TextUtils.isEmpty(restLengthValue.getText())) {
            i++;
            restLengthField.setError(getText(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(restWidthValue.getText())) {
            i++;
            restWidthField.setError(getText(R.string.blank_field_error));
        }
        return i == 0;
    }

    public void clearEmptyMeasurementsErrors() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        restLengthField.setErrorEnabled(false);
        restWidthField.setErrorEnabled(false);
    }

    public RestUpViewUpdate newRestUpView(Bundle bundle) {
        double measureA, measureB, measureC, measureD, upLength, upWidth;
        Double measureE = null;
        String upViewObs;

        upLength = Double.parseDouble(String.valueOf(restLengthValue.getText()));
        upWidth = Double.parseDouble(String.valueOf(restWidthValue.getText()));
        measureA = Double.parseDouble(String.valueOf(measureValueA.getText()));
        measureB = Double.parseDouble(String.valueOf(measureValueB.getText()));
        measureC = Double.parseDouble(String.valueOf(measureValueC.getText()));
        measureD = Double.parseDouble(String.valueOf(measureValueD.getText()));

        if (!TextUtils.isEmpty(measureValueE.getText()))
            measureE = Double.valueOf(String.valueOf(measureValueE.getText()));

        upViewObs = String.valueOf(upViewObsValue.getText());

        return new RestUpViewUpdate(bundle.getInt(REST_ID), upLength, upWidth, measureA, measureB, measureC,
                measureD, measureE, upViewObs);
    }

    public void loadUpViewData(RestroomEntry upViewEntry) {
        if (upViewEntry.getUpViewLength() != null)
            restLengthValue.setText(String.valueOf(upViewEntry.getUpViewLength()));
        if (upViewEntry.getUpViewWidth() != null)
            restWidthValue.setText(String.valueOf(upViewEntry.getUpViewWidth()));
        if (upViewEntry.getUpViewMeasureA() != null)
            measureValueA.setText(String.valueOf(upViewEntry.getUpViewMeasureA()));
        if (upViewEntry.getUpViewMeasureB() != null)
            measureValueB.setText(String.valueOf(upViewEntry.getUpViewMeasureB()));
        if (upViewEntry.getUpViewMeasureC() != null)
            measureValueC.setText(String.valueOf(upViewEntry.getUpViewMeasureC()));
        if (upViewEntry.getUpViewMeasureD() != null)
            measureValueD.setText(String.valueOf(upViewEntry.getUpViewMeasureD()));
        if (upViewEntry.getUpViewMeasureE() != null)
            measureValueE.setText(String.valueOf(upViewEntry.getUpViewMeasureE()));
        if (upViewEntry.getUpViewObs() != null)
            upViewObsValue.setText(upViewEntry.getUpViewObs());
    }
}