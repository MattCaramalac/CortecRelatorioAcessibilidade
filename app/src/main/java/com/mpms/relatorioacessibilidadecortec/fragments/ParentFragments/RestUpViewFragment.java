package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

public class RestUpViewFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    ImageButton upperViewImgButton;
    Button saveUpMeasures, returnRestDoorData;
    Bundle restUpBundle, imgData;

    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, restLengthField, restWidthField, upViewObsField, photoField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, restLengthValue, restWidthValue, upViewObsValue, photoValue;

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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
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

        upperViewImgButton.setOnClickListener(v -> {
            imgData.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.upperview2);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgData);
        });


        modelEntry.getRestUpViewData(restUpBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), upView -> {
            if (upView != null)
                loadUpViewData(upView);
        });


        returnRestDoorData.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveUpMeasures.setOnClickListener(v -> {
            if (checkEmptyMeasurementsFields()) {
                RestUpViewUpdate newUpView = newRestUpView(restUpBundle);
                ViewModelEntry.updateRestUpViewData(newUpView);
                callToiletFragment(restUpBundle);
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });
    }

    private void instantiateUpperViews(View view) {
//        ImageButton
        upperViewImgButton = view.findViewById(R.id.rest_upper_view_image);
        Glide.with(this).load(R.drawable.upperview2).centerCrop().into(upperViewImgButton);
//        TextInputLayout
        measureFieldA = view.findViewById(R.id.upper_view_A_measurement_field);
        measureFieldB = view.findViewById(R.id.upper_view_B_measurement_field);
        measureFieldC = view.findViewById(R.id.upper_view_C_measurement_field);
        measureFieldD = view.findViewById(R.id.upper_view_D_measurement_field);
        restLengthField = view.findViewById(R.id.upper_view_length_field);
        restWidthField = view.findViewById(R.id.upper_view_width_field);
        upViewObsField = view.findViewById(R.id.upper_view_obs_field);
        photoField = view.findViewById(R.id.rest_up_photo_field);
//        TextInputEditText
        measureValueA = view.findViewById(R.id.upper_view_A_measurement_value);
        measureValueB = view.findViewById(R.id.upper_view_B_measurement_value);
        measureValueC = view.findViewById(R.id.upper_view_C_measurement_value);
        measureValueD = view.findViewById(R.id.upper_view_D_measurement_value);
        restLengthValue = view.findViewById(R.id.upper_view_length_value);
        restWidthValue = view.findViewById(R.id.upper_view_width_value);
        upViewObsValue = view.findViewById(R.id.upper_view_obs_value);
        photoValue = view.findViewById(R.id.rest_up_photo_value);
//        MaterialButton
        saveUpMeasures = view.findViewById(R.id.save_up_measurements);
        returnRestDoorData = view.findViewById(R.id.return_button);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        allowObsScroll(upViewObsValue);

    }

    private void callToiletFragment(Bundle bundle) {
        RestToiletFragment barFragment = RestToiletFragment.newInstance();
        barFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.show_fragment_selected, barFragment).addToBackStack(null).commit();
    }

    private boolean checkEmptyMeasurementsFields() {
        clearEmptyMeasurementsErrors();
        int i = 0;
        if (TextUtils.isEmpty(measureValueA.getText())) {
            i++;
            measureFieldA.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(measureValueC.getText())) {
            i++;
            measureFieldC.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(restLengthValue.getText())) {
            i++;
            restLengthField.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(restWidthValue.getText())) {
            i++;
            restWidthField.setError(getText(R.string.req_field_error));
        }
        return i == 0;
    }

    private void clearEmptyMeasurementsErrors() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        restLengthField.setErrorEnabled(false);
        restWidthField.setErrorEnabled(false);
    }

    private RestUpViewUpdate newRestUpView(Bundle bundle) {
        Double measureB = null, measureD = null;
        double measureA, measureC, upLength, upWidth;
        String upViewObs, photo = null;

        upLength = Double.parseDouble(String.valueOf(restLengthValue.getText()));
        upWidth = Double.parseDouble(String.valueOf(restWidthValue.getText()));
        measureA = Double.parseDouble(String.valueOf(measureValueA.getText()));
        if (!TextUtils.isEmpty(measureValueB.getText()))
            measureB = Double.parseDouble(String.valueOf(measureValueB.getText()));
        measureC = Double.parseDouble(String.valueOf(measureValueC.getText()));
        if (!TextUtils.isEmpty(measureValueD.getText()))
            measureD = Double.valueOf(String.valueOf(measureValueD.getText()));

        upViewObs = String.valueOf(upViewObsValue.getText());
        if (photoValue.getText() != null)
            photo = String.valueOf(photoValue.getText());

        return new RestUpViewUpdate(bundle.getInt(REST_ID), upLength, upWidth, measureA, measureB, measureC, measureD, upViewObs, photo);
    }

    private void loadUpViewData(RestroomEntry upViewEntry) {
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
        if (upViewEntry.getUpViewObs() != null)
            upViewObsValue.setText(upViewEntry.getUpViewObs());
        if (upViewEntry.getRestUpperPhoto() != null)
            photoValue.setText(upViewEntry.getRestUpperPhoto());
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {

    }
}