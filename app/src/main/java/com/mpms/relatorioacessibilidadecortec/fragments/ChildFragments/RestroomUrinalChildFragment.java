package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.RestroomMirrorUrinalFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

public class RestroomUrinalChildFragment extends Fragment {

    public static final String URINAL_MEASURE_A = "URINAL_MEASURE_A";
    public static final String URINAL_MEASURE_B = "URINAL_MEASURE_B";
    public static final String URINAL_MEASURE_C = "URINAL_MEASURE_C";
    public static final String URINAL_MEASURE_D = "URINAL_MEASURE_D";
    public static final String URINAL_MEASURE_E = "URINAL_MEASURE_E";
    public static final String URINAL_MEASURE_F = "URINAL_MEASURE_F";
    public static final String URINAL_MEASURE_G = "URINAL_MEASURE_G";
    public static final String URINAL_MEASURE_H = "URINAL_MEASURE_H";
    public static final String URINAL_MEASURE_I = "URINAL_MEASURE_I";
    public static final String URINAL_MEASURE_J = "URINAL_MEASURE_J";
    public static final String URINAL_MEASURE_K = "URINAL_MEASURE_K";
    public static final String URINAL_OBS = "URINAL_OBS";

    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, measureFieldE, measureFieldF,
            measureFieldG, measureFieldH, measureFieldI, measureFieldJ, measureFieldK, urinalObsField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, measureValueE, measureValueF,
            measureValueG, measureValueH, measureValueI, measureValueJ, measureValueK, urinalObsValue;

    ImageButton urinalOne, urinalTwo;
    Bundle imgBundleUrinal = new Bundle();
    Bundle urinalDataBundle = new Bundle();

    ViewModelFragments modelFragments;

    public RestroomUrinalChildFragment() {
        // Required empty public constructor
    }

    public static RestroomUrinalChildFragment newInstance(String param1, String param2) {
        return new RestroomUrinalChildFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        return inflater.inflate(R.layout.fragment_restroom_urinal_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateUrinalViews(view);
        allowUrinalObsScroll();

        Glide.with(this).load(R.drawable.urinal_1).fitCenter().into(urinalOne);
        Glide.with(this).load(R.drawable.urinal_2).fitCenter().into(urinalTwo);

        urinalOne.setOnClickListener(v -> {
            imgBundleUrinal.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.urinal_1);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundleUrinal);
        });

        urinalTwo.setOnClickListener(v -> {
            imgBundleUrinal.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.urinal_2);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundleUrinal);
        });

        modelFragments.getCheckMirUrFrags().observe(getViewLifecycleOwner(), checkFrag -> {
            if (checkFrag.getBoolean(RestroomMirrorUrinalFragment.HAS_URINAL)) {
                if(checkEmptyUrinalFields(checkFrag)) {
                    modelFragments.setRestChildFragBundle(checkFrag);
                }
            }
        });
    }

    private void instantiateUrinalViews(View view) {
        measureFieldA = view.findViewById(R.id.urinal_A_measurement_field);
        measureFieldB = view.findViewById(R.id.urinal_B_measurement_field);
        measureFieldC = view.findViewById(R.id.urinal_C_measurement_field);
        measureFieldD = view.findViewById(R.id.urinal_D_measurement_field);
        measureFieldE = view.findViewById(R.id.urinal_E_measurement_field);
        measureFieldF = view.findViewById(R.id.urinal_F_measurement_field);
        measureFieldG = view.findViewById(R.id.urinal_G_measurement_field);
        measureFieldH = view.findViewById(R.id.urinal_H_measurement_field);
        measureFieldI = view.findViewById(R.id.urinal_I_measurement_field);
        measureFieldJ = view.findViewById(R.id.urinal_J_measurement_field);
        measureFieldK = view.findViewById(R.id.urinal_K_measurement_field);
        urinalObsField = view.findViewById(R.id.urinal_obs_field);

        measureValueA = view.findViewById(R.id.urinal_A_measurement_value);
        measureValueB = view.findViewById(R.id.urinal_B_measurement_value);
        measureValueC = view.findViewById(R.id.urinal_C_measurement_value);
        measureValueD = view.findViewById(R.id.urinal_D_measurement_value);
        measureValueE = view.findViewById(R.id.urinal_E_measurement_value);
        measureValueF = view.findViewById(R.id.urinal_F_measurement_value);
        measureValueG = view.findViewById(R.id.urinal_G_measurement_value);
        measureValueH = view.findViewById(R.id.urinal_H_measurement_value);
        measureValueI = view.findViewById(R.id.urinal_I_measurement_value);
        measureValueJ = view.findViewById(R.id.urinal_J_measurement_value);
        measureValueK = view.findViewById(R.id.urinal_K_measurement_value);
        urinalObsValue = view.findViewById(R.id.urinal_obs_value);

        urinalOne = view.findViewById(R.id.urinal_image_one);
        urinalTwo = view.findViewById(R.id.urinal_image_two);
    }

    public boolean checkEmptyUrinalFields(Bundle bundle) {
        clearUrinalEmptyFieldErrors();
        int i = 0;
        if (TextUtils.isEmpty(measureValueA.getText())) {
            i++;
            measureFieldA.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueB.getText())) {
            i++;
            measureFieldB.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueC.getText())) {
            i++;
            measureFieldC.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueD.getText())) {
            i++;
            measureFieldD.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueE.getText())) {
            i++;
            measureFieldE.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueF.getText())) {
            i++;
            measureFieldF.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueG.getText())) {
            i++;
            measureFieldG.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueH.getText())) {
            i++;
            measureFieldH.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueI.getText())) {
            i++;
            measureFieldI.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueJ.getText())) {
            i++;
            measureFieldJ.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(measureValueK.getText())) {
            i++;
            measureFieldK.setError(getString(R.string.blank_field_error));
        }

        if (i == 0)
            pickUrinalData(bundle);

        return i == 0;
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowUrinalObsScroll() {
            urinalObsValue.setOnTouchListener(this::scrollingField);
    }

    public void clearUrinalEmptyFieldErrors() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        measureFieldE.setErrorEnabled(false);
        measureFieldF.setErrorEnabled(false);
        measureFieldG.setErrorEnabled(false);
        measureFieldH.setErrorEnabled(false);
        measureFieldI.setErrorEnabled(false);
        measureFieldJ.setErrorEnabled(false);
        measureFieldK.setErrorEnabled(false);
    }

    public void pickUrinalData(Bundle bundle) {
        bundle.putDouble(URINAL_MEASURE_A, Double.parseDouble(String.valueOf(measureValueA.getText())));
        bundle.putDouble(URINAL_MEASURE_B, Double.parseDouble(String.valueOf(measureValueB.getText())));
        bundle.putDouble(URINAL_MEASURE_C, Double.parseDouble(String.valueOf(measureValueC.getText())));
        bundle.putDouble(URINAL_MEASURE_D, Double.parseDouble(String.valueOf(measureValueD.getText())));
        bundle.putDouble(URINAL_MEASURE_E, Double.parseDouble(String.valueOf(measureValueE.getText())));
        bundle.putDouble(URINAL_MEASURE_F, Double.parseDouble(String.valueOf(measureValueF.getText())));
        bundle.putDouble(URINAL_MEASURE_G, Double.parseDouble(String.valueOf(measureValueG.getText())));
        bundle.putDouble(URINAL_MEASURE_H, Double.parseDouble(String.valueOf(measureValueH.getText())));
        bundle.putDouble(URINAL_MEASURE_I, Double.parseDouble(String.valueOf(measureValueI.getText())));
        bundle.putDouble(URINAL_MEASURE_J, Double.parseDouble(String.valueOf(measureValueJ.getText())));
        bundle.putDouble(URINAL_MEASURE_K, Double.parseDouble(String.valueOf(measureValueK.getText())));
        bundle.putString(URINAL_OBS, String.valueOf(urinalObsValue.getText()));
    }
}