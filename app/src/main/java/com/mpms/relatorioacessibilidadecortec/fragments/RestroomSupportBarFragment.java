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
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSupportBarEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.ArrayList;

public class RestroomSupportBarFragment extends Fragment {

    public static final String OPENED_SUP_BAR = "OPENED_SUP_BAR";
    public static final String SUP_BAR_ID = "SUP_BAR_ID";

    ViewModelEntry modelEntry;

    Bundle restroomDataBundle, imgBundle;

    ImageButton supportBar;

    Button saveSupBar, returnUpView;

    TextView paperHolderError, emergencySignalError, bidetError;

    TextInputLayout supBarDiamField, measureFieldA, measureFieldB, measureFieldC, measureFieldD, measureFieldE,
    measureFieldF, measureFieldG, measureFieldH, measureFieldI, measureFieldJ, supBarObsField, toiletHeightField,
    tFlushHeightField, papDistanceField, papHeightField, papObsField, eButHeightField, eButObsField, bidetObsField;

    TextInputEditText supBarDiamValue, measureValueA, measureValueB, measureValueC, measureValueD, measureValueE,
            measureValueF, measureValueG, measureValueH, measureValueI, measureValueJ, supBarObsValue, toiletHeightValue,
            tFlushHeightValue, papDistanceValue, papHeightValue, papObsValue, eButHeightValue, eButObsValue, bidetObsValue;

    RadioGroup papTypeRadio, eButRadio, bidetRadio;

    Double supBarDiam, measureA, measureB, measureC, measureD, measureE, measureF, measureG, measureH, measureI,
            measureJ, toiletHeight, tFlushHeight, papDistance, papHeight, eButHeight;

    Integer papType, hasEmergencyButton, hasBidet;

    String supBarObs, papObs, eButObs, bidetObs;

    int recentSupBar = 0;

    ArrayList<TextInputEditText> supportBarObsArray = new ArrayList<>();


    public RestroomSupportBarFragment() {
        // Required empty public constructor
    }


    public static RestroomSupportBarFragment newInstance() {
        return new RestroomSupportBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restroomDataBundle = this.getArguments();
        imgBundle = new Bundle();
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restroom_support_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSupportView(view);
        allowSupBarObsScroll();

        Glide.with(this).load(R.drawable.supporthandle2).centerCrop().into(supportBar);

        supportBar.setOnClickListener( v -> {
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.supporthandle2);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
        });

        eButRadio.setOnCheckedChangeListener((radio, checkedId) -> {
            int index = getSupBarCheckedRadio(radio);
            if (index == 1) {
                eButHeightField.setEnabled(true);
            } else {
                eButHeightValue.setText(null);
                eButHeightField.setEnabled(false);
            }
        });

        modelEntry.getLastRestroomSupportBarEntry().observe(getViewLifecycleOwner(), supBar -> {
            if (recentSupBar == 1) {
                recentSupBar = 0;
                restroomDataBundle.putInt(SUP_BAR_ID, supBar.getSupBarID());
                callSinkOneFragment(restroomDataBundle);
            }
        });

        modelEntry.getRestSupportBarByRestroom(restroomDataBundle.getInt(RestroomFragment.RESTROOM_ID))
                .observe(getViewLifecycleOwner(), supBar -> {
                    if (supBar != null) {
                        restroomDataBundle.putInt(SUP_BAR_ID, supBar.getSupBarID());
                        modelEntry.getOneRestroomSupportBarEntry(restroomDataBundle.getInt(SUP_BAR_ID))
                                .observe(getViewLifecycleOwner(), this::gatherSupBar);
                    }

                });

        saveSupBar.setOnClickListener( v -> {
            if (checkEmptySupBarField()) {
                RestroomSupportBarEntry supBar = newSupBar(restroomDataBundle);
                if (restroomDataBundle.getBoolean(RestroomSink1Fragment.OPENED_SINK_ONE)
                        || restroomDataBundle.getInt(SUP_BAR_ID) > 0) {
                    supBar.setSupBarID(restroomDataBundle.getInt(SUP_BAR_ID));
                    ViewModelEntry.updateRestroomSupportBarEntry(supBar);
                    callSinkOneFragment(restroomDataBundle);
                } else {
                    ViewModelEntry.insertRestroomSupportBarEntry(supBar);
                    recentSupBar = 1;
                }
            }
        });

        returnUpView.setOnClickListener( v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private void addObsFieldsToArray() {
        supportBarObsArray.add(supBarObsValue);
        supportBarObsArray.add(papObsValue);
        supportBarObsArray.add(eButObsValue);
        supportBarObsArray.add(bidetObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSupBarObsScroll() {
        addObsFieldsToArray();
        for (TextInputEditText obsScroll :supportBarObsArray) {
            obsScroll.setOnTouchListener(this::scrollingField);
        }
    }

    private void instantiateSupportView(View view) {
        supportBar = view.findViewById(R.id.rest_support_bar_image);

        paperHolderError = view.findViewById(R.id.paper_holder_type_error);
        emergencySignalError = view.findViewById(R.id.emergency_signal_error);
        bidetError = view.findViewById(R.id.bidet_error);

        supBarDiamField = view.findViewById(R.id.sup_bar_diam_field);
        measureFieldA = view.findViewById(R.id.sup_bar_A_measurement_field);
        measureFieldB = view.findViewById(R.id.sup_bar_B_measurement_field);
        measureFieldC = view.findViewById(R.id.sup_bar_C_measurement_field);
        measureFieldD = view.findViewById(R.id.sup_bar_D_measurement_field);
        measureFieldE = view.findViewById(R.id.sup_bar_E_measurement_field);
        measureFieldF = view.findViewById(R.id.sup_bar_F_measurement_field);
        measureFieldG = view.findViewById(R.id.sup_bar_G_measurement_field);
        measureFieldH = view.findViewById(R.id.sup_bar_H_measurement_field);
        measureFieldI = view.findViewById(R.id.sup_bar_I_measurement_field);
        measureFieldJ = view.findViewById(R.id.sup_bar_J_measurement_field);
        supBarObsField = view.findViewById(R.id.sup_bar_obs_field);
        toiletHeightField = view.findViewById(R.id.toilet_height_field);
        tFlushHeightField = view.findViewById(R.id.toilet_flush_height_field);
        papDistanceField = view.findViewById(R.id.paper_holder_distance_field);
        papHeightField = view.findViewById(R.id.paper_holder_height_field);
        papObsField = view.findViewById(R.id.paper_holder_obs_field);
        eButHeightField = view.findViewById(R.id.emergency_signal_height_field);
        eButObsField = view.findViewById(R.id.emergency_signal_obs_field);
        bidetObsField = view.findViewById(R.id.bidet_obs_field);

        supBarDiamValue = view.findViewById(R.id.sup_bar_diam_value);
        measureValueA = view.findViewById(R.id.sup_bar_A_measurement_value);
        measureValueB = view.findViewById(R.id.sup_bar_B_measurement_value);
        measureValueC = view.findViewById(R.id.sup_bar_C_measurement_value);
        measureValueD = view.findViewById(R.id.sup_bar_D_measurement_value);
        measureValueE = view.findViewById(R.id.sup_bar_E_measurement_value);
        measureValueF = view.findViewById(R.id.sup_bar_F_measurement_value);
        measureValueG = view.findViewById(R.id.sup_bar_G_measurement_value);
        measureValueH = view.findViewById(R.id.sup_bar_H_measurement_value);
        measureValueI = view.findViewById(R.id.sup_bar_I_measurement_value);
        measureValueJ = view.findViewById(R.id.sup_bar_J_measurement_value);
        supBarObsValue = view.findViewById(R.id.sup_bar_obs_value);
        toiletHeightValue = view.findViewById(R.id.toilet_height_value);
        tFlushHeightValue = view.findViewById(R.id.toilet_flush_height_value);
        papDistanceValue = view.findViewById(R.id.paper_holder_distance_value);
        papHeightValue = view.findViewById(R.id.paper_holder_height_value);
        papObsValue = view.findViewById(R.id.paper_holder_obs_value);
        eButHeightValue = view.findViewById(R.id.emergency_signal_height_value);
        eButObsValue = view.findViewById(R.id.emergency_signal_obs_value);
        bidetObsValue = view.findViewById(R.id.bidet_obs_value);

        papTypeRadio = view.findViewById(R.id.paper_holder_type_radio);
        eButRadio = view.findViewById(R.id.emergency_signal_radio);
        bidetRadio = view.findViewById(R.id.bidet_radio);

        saveSupBar = view.findViewById(R.id.save_sup_bar);
        returnUpView = view.findViewById(R.id.return_up_view);
    }

    public void callSinkOneFragment(Bundle bundle) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RestroomSink1Fragment sinkOneFragment = RestroomSink1Fragment.newInstance();
        sinkOneFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, sinkOneFragment).addToBackStack(null).commit();
    }

    public int getSupBarCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptySupBarField() {
        clearEmptySupBarErrors();
        int i = 0;
        if (TextUtils.isEmpty(supBarDiamValue.getText())) {
            i++;
            supBarDiamField.setError(getString(R.string.blank_field_error));
        }
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
        if (TextUtils.isEmpty(toiletHeightValue.getText())) {
            i++;
            toiletHeightField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(tFlushHeightValue.getText())) {
            i++;
            tFlushHeightField.setError(getString(R.string.blank_field_error));
        }
        if (getSupBarCheckedRadio(papTypeRadio) == -1) {
            i++;
            paperHolderError.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(papDistanceValue.getText())) {
            i++;
            papDistanceField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(papHeightValue.getText())) {
            i++;
            papHeightField.setError(getString(R.string.blank_field_error));
        }
        if (getSupBarCheckedRadio(eButRadio) == -1) {
            i++;
            emergencySignalError.setVisibility(View.VISIBLE);
        }
        if (getSupBarCheckedRadio(eButRadio) == 1) {
            if (TextUtils.isEmpty(eButHeightValue.getText())) {
                i++;
                eButHeightField.setError(getString(R.string.blank_field_error));
            }
        }
        if (getSupBarCheckedRadio(bidetRadio) == -1) {
            i++;
           bidetError.setVisibility(View.VISIBLE);
        }

        return i == 0;
    }

    public void clearEmptySupBarErrors() {
        supBarDiamField.setErrorEnabled(false);
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
        toiletHeightField.setErrorEnabled(false);
        tFlushHeightField.setErrorEnabled(false);
        papDistanceField.setErrorEnabled(false);
        papHeightField.setErrorEnabled(false);
        eButHeightField.setErrorEnabled(false);

        paperHolderError.setVisibility(View.GONE);
        emergencySignalError.setVisibility(View.GONE);
        bidetError.setVisibility(View.GONE);
    }

    public RestroomSupportBarEntry newSupBar(Bundle bundle) {
        papType = getSupBarCheckedRadio(papTypeRadio);
        hasEmergencyButton = getSupBarCheckedRadio(eButRadio);
        hasBidet = getSupBarCheckedRadio(bidetRadio);

        supBarDiam = Double.valueOf(String.valueOf(supBarDiamValue.getText()));
        measureA = Double.valueOf(String.valueOf(measureValueA.getText()));
        measureB = Double.valueOf(String.valueOf(measureValueB.getText()));
        measureC = Double.valueOf(String.valueOf(measureValueC.getText()));
        measureD = Double.valueOf(String.valueOf(measureValueD.getText()));
        measureE = Double.valueOf(String.valueOf(measureValueE.getText()));
        measureF = Double.valueOf(String.valueOf(measureValueF.getText()));
        measureG = Double.valueOf(String.valueOf(measureValueG.getText()));
        measureH = Double.valueOf(String.valueOf(measureValueH.getText()));
        measureI = Double.valueOf(String.valueOf(measureValueI.getText()));
        measureJ = Double.valueOf(String.valueOf(measureValueJ.getText()));
        toiletHeight = Double.valueOf(String.valueOf(toiletHeightValue.getText()));
        tFlushHeight = Double.valueOf(String.valueOf(tFlushHeightValue.getText()));
        papDistance = Double.valueOf(String.valueOf(papDistanceValue.getText()));
        papHeight = Double.valueOf(String.valueOf(papHeightValue.getText()));

        if(hasEmergencyButton == 1) {
            eButHeight = Double.valueOf(String.valueOf(eButHeightValue.getText()));
        }

        supBarObs = String.valueOf(supBarObsValue.getText());
        papObs = String.valueOf(papObsValue.getText());
        eButObs = String.valueOf(eButObsValue.getText());
        bidetObs = String.valueOf(bidetObsValue.getText());

        return new RestroomSupportBarEntry(bundle.getInt(RestroomFragment.RESTROOM_ID),supBarDiam, measureA, measureB,
                measureC, measureD, measureE, measureF, measureG, measureH, measureI, measureJ, supBarObs, toiletHeight,
                tFlushHeight, papType, papDistance, papHeight, papObs, hasEmergencyButton, eButHeight, eButObs, hasBidet, bidetObs);
    }

    public void gatherSupBar(RestroomSupportBarEntry supportBarEntry) {
        restroomDataBundle.putInt(SUP_BAR_ID, supportBarEntry.getSupBarID());

        supBarDiamValue.setText(String.valueOf(supportBarEntry.getSupBarDiameter()));
        measureValueA.setText(String.valueOf(supportBarEntry.getSupBarMeasureA()));
        measureValueB.setText(String.valueOf(supportBarEntry.getSupBarMeasureB()));
        measureValueC.setText(String.valueOf(supportBarEntry.getSupBarMeasureC()));
        measureValueD.setText(String.valueOf(supportBarEntry.getSupBarMeasureD()));
        measureValueE.setText(String.valueOf(supportBarEntry.getSupBarMeasureE()));
        measureValueF.setText(String.valueOf(supportBarEntry.getSupBarMeasureF()));
        measureValueG.setText(String.valueOf(supportBarEntry.getSupBarMeasureG()));
        measureValueH.setText(String.valueOf(supportBarEntry.getSupBarMeasureH()));
        measureValueI.setText(String.valueOf(supportBarEntry.getSupBarMeasureI()));
        measureValueJ.setText(String.valueOf(supportBarEntry.getSupBarMeasureJ()));
        supBarObsValue.setText(String.valueOf(supportBarEntry.getSupBarObs()));
        toiletHeightValue.setText(String.valueOf(supportBarEntry.getToiletHeight()));
        tFlushHeightValue.setText(String.valueOf(supportBarEntry.getToiletFlushHeight()));
        papTypeRadio.check(papTypeRadio.getChildAt(supportBarEntry.getPaperHolderType()).getId());
        papDistanceValue.setText(String.valueOf(supportBarEntry.getPaperHolderDistance()));
        papHeightValue.setText(String.valueOf(supportBarEntry.getPaperHolderHeight()));
        papObsValue.setText(String.valueOf(supportBarEntry.getPaperHolderObs()));
        eButRadio.check(eButRadio.getChildAt(supportBarEntry.getHasEmergencySignal()).getId());
        if (supportBarEntry.getHasEmergencySignal() == 1)
            eButHeightValue.setText(String.valueOf(supportBarEntry.getEmergencySignalHeight()));
        eButObsValue.setText(String.valueOf(supportBarEntry.getEmergencySignalObs()));
        bidetRadio.check(bidetRadio.getChildAt(supportBarEntry.getHasBidet()).getId());
        bidetObsValue.setText(String.valueOf(supportBarEntry.getBidetObs()));
    }

}