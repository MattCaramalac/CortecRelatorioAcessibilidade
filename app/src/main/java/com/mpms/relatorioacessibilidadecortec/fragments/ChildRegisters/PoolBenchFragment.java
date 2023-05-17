package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolBenchEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;


public class PoolBenchFragment extends Fragment implements RadioGroupInterface, TagInterface, ScrollEditText {

    TextInputLayout benchLocationField, benchExtField, leftBarDiamField, leftBarDistField, rightBarDiamField, rightBarDistField, fieldA, fieldB, fieldC, fieldD, fieldE,
            waterLevelField, obsField, photoField;
    TextInputEditText benchLocationValue, benchExtValue, leftBarDiamValue, leftBarDistValue, rightBarDiamValue, rightBarDistValue, valueA, valueB, valueC, valueD, valueE,
            waterLevelValue, obsValue, photoValue;
    RadioGroup areaRadio, leftBarRadio, rightBarRadio;
    TextView areaError, leftError, rightError;
    ImageButton img1, img2;
    MaterialButton returnList, saveBench;

    ViewModelEntry modelEntry;
    Bundle benchBundle, imgBundle;

    public PoolBenchFragment() {
        // Required empty public constructor
    }

    public static PoolBenchFragment newInstance() {
        return new PoolBenchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            benchBundle = new Bundle(this.getArguments());
        else
            benchBundle = new Bundle();

        imgBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pool_bench, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);

        if (benchBundle.getInt(PBENCH_ID) > 0)
            modelEntry.getOnePoolBench(benchBundle.getInt(PBENCH_ID)).observe(getViewLifecycleOwner(), this::loadPoolBenchData);

    }

    private void instantiateViews(View view) {
//        TextInputLayout
        benchLocationField = view.findViewById(R.id.pool_bench_local_field);
        benchExtField = view.findViewById(R.id.pool_bench_extension_field);
        leftBarDiamField = view.findViewById(R.id.left_bar_diam_field);
        leftBarDistField = view.findViewById(R.id.left_bar_dist_field);
        rightBarDiamField = view.findViewById(R.id.right_bar_diam_field);
        rightBarDistField = view.findViewById(R.id.right_bar_dist_field);
        fieldA = view.findViewById(R.id.pool_bench_measure_A_field);
        fieldB = view.findViewById(R.id.pool_bench_measure_B_field);
        fieldC = view.findViewById(R.id.pool_bench_measure_C_field);
        fieldD = view.findViewById(R.id.pool_bench_measure_D_field);
        fieldE = view.findViewById(R.id.pool_bench_measure_E_field);
        waterLevelField = view.findViewById(R.id.pool_water_level_field);
        obsField = view.findViewById(R.id.pool_bench_obs_field);
        photoField = view.findViewById(R.id.pool_bench_photo_field);
//        TextInputEditText
        benchLocationValue = view.findViewById(R.id.pool_bench_local_value);
        benchExtValue = view.findViewById(R.id.pool_bench_extension_value);
        leftBarDiamValue = view.findViewById(R.id.left_bar_diam_value);
        leftBarDistValue = view.findViewById(R.id.left_bar_dist_value);
        rightBarDiamValue = view.findViewById(R.id.right_bar_diam_value);
        rightBarDistValue = view.findViewById(R.id.right_bar_dist_value);
        valueA = view.findViewById(R.id.pool_bench_measure_A_value);
        valueB = view.findViewById(R.id.pool_bench_measure_B_value);
        valueC = view.findViewById(R.id.pool_bench_measure_C_value);
        valueD = view.findViewById(R.id.pool_bench_measure_D_value);
        valueE = view.findViewById(R.id.pool_bench_measure_E_value);
        waterLevelValue = view.findViewById(R.id.pool_water_level_value);
        obsValue = view.findViewById(R.id.pool_bench_obs_value);
        photoValue = view.findViewById(R.id.pool_bench_photo_value);
//        RadioGroup
        areaRadio = view.findViewById(R.id.pool_bench_maneuver_radio);
        leftBarRadio = view.findViewById(R.id.pool_bench_has_left_bar_radio);
        rightBarRadio = view.findViewById(R.id.pool_bench_has_right_bar_radio);
        leftBarRadio.setOnCheckedChangeListener(this::radioListener);
        rightBarRadio.setOnCheckedChangeListener(this::radioListener);
//        TextView
        areaError = view.findViewById(R.id.pool_bench_maneuver_error);
        leftError = view.findViewById(R.id.pool_bench_has_left_bar_error);
        rightError = view.findViewById(R.id.pool_bench_has_right_bar_error);
//        ImageButton
        img1 = view.findViewById(R.id.pool_bench_img_1);
        img2 = view.findViewById(R.id.pool_bench_img_2);
        Glide.with(this).load(R.drawable.benchpool1).centerCrop().into(img1);
        Glide.with(this).load(R.drawable.benchpool2).centerCrop().into(img2);
        img1.setOnClickListener(this::imgExpandClick);
        img2.setOnClickListener(this::imgExpandClick);
//        MaterialButton
        returnList = view.findViewById(R.id.return_pool_bench_list);
        saveBench = view.findViewById(R.id.save_pool_bench);
        returnList.setOnClickListener(this::buttonClickListener);
        saveBench.setOnClickListener(this::buttonClickListener);
//        ViewModelEntry
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void imgExpandClick(View view) {
        if (view == img1)
            imgBundle.putInt(IMAGE_ID, R.drawable.benchpool1);
        else if (view == img2)
            imgBundle.putInt(IMAGE_ID, R.drawable.benchpool2);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == leftBarRadio) {
            if (index == 1) {
                leftBarDiamField.setVisibility(View.VISIBLE);
                leftBarDistField.setVisibility(View.VISIBLE);
            } else {
                leftBarDiamValue.setText(null);
                leftBarDistValue.setText(null);
                leftBarDiamField.setVisibility(View.GONE);
                leftBarDistField.setVisibility(View.GONE);
            }

            if (index == 1 && indexRadio(rightBarRadio) == 1)
                fieldA.setVisibility(View.VISIBLE);
            else {
                valueA.setText(null);
                fieldA.setVisibility(View.GONE);
            }
        } else if (radio == rightBarRadio) {
            if (index == 1) {
                rightBarDiamField.setVisibility(View.VISIBLE);
                rightBarDistField.setVisibility(View.VISIBLE);
            } else {
                rightBarDiamValue.setText(null);
                rightBarDistValue.setText(null);
                rightBarDiamField.setVisibility(View.GONE);
                rightBarDistField.setVisibility(View.GONE);
            }

            if (index == 1 && indexRadio(leftBarRadio) == 1)
                fieldA.setVisibility(View.VISIBLE);
            else {
                valueA.setText(null);
                fieldA.setVisibility(View.GONE);
            }
        }
    }

    private void buttonClickListener(View view) {
        if (view == saveBench && checkEmptyFields()) {
            PoolBenchEntry poolBench = newBench(benchBundle);
            if (benchBundle.getInt(PBENCH_ID) > 0) {
                poolBench.setPoolBenchID(benchBundle.getInt(PBENCH_ID));
                modelEntry.updatePoolBench(poolBench);
                Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            } else if (benchBundle.getInt(PBENCH_ID) == 0) {
                modelEntry.insertPoolBench(poolBench);
                Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                clearRegisterScreen();
            } else {
                benchBundle.putInt(PBENCH_ID, 0);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            }
        } else
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    private void clearRegisterScreen() {
        benchLocationValue.setText(null);
        benchExtValue.setText(null);
        valueB.setText(null);
        valueC.setText(null);
        valueD.setText(null);
        valueE.setText(null);
        waterLevelValue.setText(null);
        obsValue.setText(null);
        photoValue.setText(null);
        areaRadio.clearCheck();
        leftBarRadio.clearCheck();
        rightBarRadio.clearCheck();
    }

    private PoolBenchEntry newBench(Bundle bundle) {
        String local, obs = null, photo = null;
        int area, left, right;
        double extension, b, c, d, e, waterLevel;
        Double leftDiam = null, leftDist = null, rightDiam = null, rightDist = null, a = null;

        local = String.valueOf(benchLocationValue.getText());
        area = indexRadio(areaRadio);
        extension = Double.parseDouble(String.valueOf(benchExtValue.getText()));
        left = indexRadio(leftBarRadio);
        if (left == 1) {
            leftDiam = Double.parseDouble(String.valueOf(leftBarDiamValue.getText()));
            leftDist = Double.parseDouble(String.valueOf(leftBarDistValue.getText()));
        }
        right = indexRadio(rightBarRadio);
        if (right == 1) {
            rightDiam = Double.parseDouble(String.valueOf(rightBarDiamValue.getText()));
            rightDist = Double.parseDouble(String.valueOf(rightBarDistValue.getText()));
        }

        if (left == 1 && right == 1) {
            a = Double.parseDouble(String.valueOf(valueA.getText()));
        }
        b = Double.parseDouble(String.valueOf(valueB.getText()));
        c = Double.parseDouble(String.valueOf(valueC.getText()));
        d = Double.parseDouble(String.valueOf(valueD.getText()));
        e = Double.parseDouble(String.valueOf(valueE.getText()));
        waterLevel = Double.parseDouble(String.valueOf(waterLevelValue.getText()));

        if (!TextUtils.isEmpty(obsValue.getText()))
            obs = String.valueOf(obsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new PoolBenchEntry(bundle.getInt(POOL_ID), local, area, extension, left, leftDiam, leftDist, right, rightDiam, rightDist, a, b, c, d, e, waterLevel,
                photo, obs);
    }

    private boolean checkEmptyFields() {
        clearErrorMessages();
        int i = 0;
        if (TextUtils.isEmpty(benchLocationValue.getText())) {
            i++;
            benchLocationField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(areaRadio) == -1) {
            i++;
            areaError.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(benchExtValue.getText())) {
            i++;
            benchExtField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(leftBarRadio) == -1) {
            i++;
            leftError.setVisibility(View.VISIBLE);
        } else if (indexRadio(leftBarRadio) == 1) {
            if (TextUtils.isEmpty(leftBarDiamValue.getText())) {
                i++;
                leftBarDiamField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(leftBarDistValue.getText())) {
                i++;
                leftBarDistField.setError(getString(R.string.req_field_error));
            }
        }
        if (indexRadio(rightBarRadio) == -1) {
            i++;
            rightError.setVisibility(View.VISIBLE);
        } else if (indexRadio(rightBarRadio) == 1) {
            if (TextUtils.isEmpty(rightBarDiamValue.getText())) {
                i++;
                rightBarDiamField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(rightBarDistValue.getText())) {
                i++;
                rightBarDistField.setError(getString(R.string.req_field_error));
            }
        }
        if (indexRadio(leftBarRadio) == 1 && indexRadio(rightBarRadio) == 1) {
            if (TextUtils.isEmpty(valueA.getText())) {
                i++;
                fieldA.setError(getString(R.string.req_field_error));
            }
        }
        if (TextUtils.isEmpty(valueB.getText())) {
            i++;
            fieldB.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(valueC.getText())) {
            i++;
            fieldC.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(valueD.getText())) {
            i++;
            fieldD.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(valueE.getText())) {
            i++;
            fieldE.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(waterLevelValue.getText())) {
            i++;
            waterLevelField.setError(getString(R.string.req_field_error));
        }

        return i == 0;
    }

    private void clearErrorMessages() {
        benchLocationField.setErrorEnabled(false);
        areaError.setVisibility(View.GONE);
        benchExtField.setErrorEnabled(false);
        leftError.setVisibility(View.GONE);
        leftBarDiamField.setErrorEnabled(false);
        leftBarDistField.setErrorEnabled(false);
        rightError.setVisibility(View.GONE);
        rightBarDiamField.setErrorEnabled(false);
        rightBarDistField.setErrorEnabled(false);
        fieldA.setErrorEnabled(false);
        fieldB.setErrorEnabled(false);
        fieldC.setErrorEnabled(false);
        fieldD.setErrorEnabled(false);
        fieldE.setErrorEnabled(false);
        waterLevelField.setErrorEnabled(false);
    }

    private void loadPoolBenchData(PoolBenchEntry bench) {

        if (bench.getBenchLocation() != null)
            benchLocationValue.setText(bench.getBenchLocation());
        if (bench.getBenchAccessible() != null)
            checkRadioGroup(areaRadio, bench.getBenchAccessible());
        if (bench.getBenchExtension() != null)
            benchExtValue.setText(String.valueOf(bench.getBenchExtension()));
        if (bench.getBenchHasLeftBar() != null) {
            checkRadioGroup(leftBarRadio, bench.getBenchHasLeftBar());
            if (bench.getBenchHasLeftBar() == 1) {
                if (bench.getBenchLeftBarDiam() != null)
                    leftBarDiamValue.setText(String.valueOf(bench.getBenchLeftBarDiam()));
                if (bench.getBenchLeftBarDist() != null)
                    leftBarDistValue.setText(String.valueOf(bench.getBenchLeftBarDist()));
            }
        }
        if (bench.getBenchHasRightBar() != null) {
            checkRadioGroup(rightBarRadio, bench.getBenchHasRightBar());
            if (bench.getBenchHasRightBar() == 1) {
                if (bench.getBenchRightBarDiam() != null)
                    rightBarDiamValue.setText(String.valueOf(bench.getBenchRightBarDiam()));
                if (bench.getBenchRightBarDist() != null)
                    rightBarDistValue.setText(String.valueOf(bench.getBenchRightBarDist()));
            }
        }

        if (bench.getBenchMeasureA() != null)
            valueA.setText(String.valueOf(bench.getBenchMeasureA()));
        if (bench.getBenchMeasureB() != null)
            valueB.setText(String.valueOf(bench.getBenchMeasureB()));
        if (bench.getBenchMeasureC() != null)
            valueC.setText(String.valueOf(bench.getBenchMeasureC()));
        if (bench.getBenchMeasureD() != null)
            valueD.setText(String.valueOf(bench.getBenchMeasureD()));
        if (bench.getBenchMeasureE() != null)
            valueE.setText(String.valueOf(bench.getBenchMeasureE()));
        if (bench.getBenchWaterLevel() != null)
            waterLevelValue.setText(String.valueOf(bench.getBenchWaterLevel()));
        if (bench.getBenchObs() != null)
            obsValue.setText(bench.getBenchObs());
        if (bench.getBenchPhoto() != null)
            photoValue.setText(String.valueOf(bench.getBenchPhoto()));
    }
}