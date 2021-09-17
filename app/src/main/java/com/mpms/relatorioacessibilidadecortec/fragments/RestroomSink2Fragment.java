package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

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
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkTwo;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class RestroomSink2Fragment extends Fragment {

    public static final String OPENED_SINK_TWO = "OPENED_SINK_TWO";

    TextInputLayout measureFieldI, measureFieldJ, measureFieldK, measureFieldL, measureFieldM, measureFieldN, measureFieldO,
            measureFieldP, measureFieldQ, measureFieldR, measureFieldS, measureFieldT, obsSinkImgThreeField, obsSinkImgFourFiveField;
    TextInputEditText measureValueI, measureValueJ, measureValueK, measureValueL, measureValueM, measureValueN, measureValueO,
            measureValueP, measureValueQ, measureValueR, measureValueS, measureValueT, obsSinkImgThreeValue, obsSinkImgFourFiveValue;

    Double measureI, measureJ, measureK, measureL, measureM, measureN, measureO, measureP, measureQ, measureR, measureS, measureT;
    String obsSinkImgThree, obsSinkImgFourFive;

    Button saveSinkTwo, returnSinkOne;
    ImageButton sinkThree, sinkFour, sinkFive;
    Bundle restroomDataBundle, imgBundle;
    ViewModelEntry modelEntry;



    public RestroomSink2Fragment() {
        // Required empty public constructor
    }

    public static RestroomSink2Fragment newInstance() {
        return new RestroomSink2Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        restroomDataBundle = this.getArguments();
        imgBundle = new Bundle();
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        return inflater.inflate(R.layout.fragment_restroom_sink2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        measureFieldI = view.findViewById(R.id.sink_three_I_measurement_field);
        measureFieldJ = view.findViewById(R.id.sink_three_J_measurement_field);
        measureFieldK = view.findViewById(R.id.sink_three_K_measurement_field);
        measureFieldL = view.findViewById(R.id.sink_three_L_measurement_field);
        measureFieldM = view.findViewById(R.id.sink_three_M_measurement_field);
        measureFieldN = view.findViewById(R.id.sink_three_N_measurement_field);
        measureFieldO = view.findViewById(R.id.sink_four_O_measurement_field);
        measureFieldP = view.findViewById(R.id.sink_four_P_measurement_field);
        measureFieldQ = view.findViewById(R.id.sink_four_Q_measurement_field);
        measureFieldR = view.findViewById(R.id.sink_four_R_measurement_field);
        measureFieldS = view.findViewById(R.id.sink_five_S_measurement_field);
        measureFieldT = view.findViewById(R.id.sink_five_T_measurement_field);
        obsSinkImgThreeField = view.findViewById(R.id.sink_three_obs_field);
        obsSinkImgFourFiveField = view.findViewById(R.id.sink_four_five_obs_field);
        measureValueI = view.findViewById(R.id.sink_three_I_measurement_value);
        measureValueJ = view.findViewById(R.id.sink_three_J_measurement_value);
        measureValueK = view.findViewById(R.id.sink_three_K_measurement_value);
        measureValueL = view.findViewById(R.id.sink_three_L_measurement_value);
        measureValueM = view.findViewById(R.id.sink_three_M_measurement_value);
        measureValueN = view.findViewById(R.id.sink_three_N_measurement_value);
        measureValueO = view.findViewById(R.id.sink_four_O_measurement_value);
        measureValueP = view.findViewById(R.id.sink_four_P_measurement_value);
        measureValueQ = view.findViewById(R.id.sink_four_Q_measurement_value);
        measureValueR = view.findViewById(R.id.sink_four_R_measurement_value);
        measureValueS = view.findViewById(R.id.sink_five_S_measurement_value);
        measureValueT = view.findViewById(R.id.sink_five_T_measurement_value);
        obsSinkImgThreeValue = view.findViewById(R.id.sink_three_obs_value);
        obsSinkImgFourFiveValue = view.findViewById(R.id.sink_four_five_obs_value);

        saveSinkTwo = view.findViewById(R.id.save_sink_two);
        returnSinkOne = view.findViewById(R.id.return_sink_one);

        sinkThree = view.findViewById(R.id.sink_image_three);
        sinkFour = view.findViewById(R.id.sink_image_four);
        sinkFive = view.findViewById(R.id.sink_image_five);

        Glide.with(this).load(R.drawable.sink_3).fitCenter().into(sinkThree);
        Glide.with(this).load(R.drawable.sink_4).fitCenter().into(sinkFour);
        Glide.with(this).load(R.drawable.sink_5).fitCenter().into(sinkFive);

        sinkThree.setOnClickListener(v -> {
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_3);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
        });

        sinkFour.setOnClickListener(v -> {
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_4);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
        });

        sinkFive.setOnClickListener(v -> {
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_5);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
        });

        if (restroomDataBundle.getBoolean(RestroomMirrorUrinalFragment.OPENED_MIRROR)) {
            modelEntry.getOneRestroomSinkEntry(restroomDataBundle.getInt(RestroomSink1Fragment.SINK_ID)).observe(getViewLifecycleOwner(), this::gatherSinkTwoData);
        }

        saveSinkTwo.setOnClickListener( v-> {
            if(checkSinkTwoEmptyFields()) {
                RestroomSinkTwo sinkTwo = updateSinkTwo(restroomDataBundle);
                ViewModelEntry.updateSinkEntryTwo(sinkTwo);
                callMirrorFragment(restroomDataBundle);
            }
        });

        returnSinkOne.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    @Override
    public void onResume() {
        super.onResume();
        restroomDataBundle.putBoolean(OPENED_SINK_TWO, true);
    }

    public boolean checkSinkTwoEmptyFields() {
        clearSinkTwoEmptyFields();
        int i = 0;
        if(TextUtils.isEmpty(measureValueI.getText())){
            i++;
            measureFieldI.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueJ.getText())){
            i++;
            measureFieldJ.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueK.getText())){
            i++;
            measureFieldK.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueL.getText())){
            i++;
            measureFieldL.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueM.getText())){
            i++;
            measureFieldM.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueN.getText())){
            i++;
            measureFieldN.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueO.getText())){
            i++;
            measureFieldO.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueP.getText())){
            i++;
            measureFieldP.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueQ.getText())){
            i++;
            measureFieldQ.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueR.getText())){
            i++;
            measureFieldR.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueS.getText())){
            i++;
            measureFieldS.setError(getString(R.string.blank_field_error));
        }
        if(TextUtils.isEmpty(measureValueT.getText())){
            i++;
            measureFieldT.setError(getString(R.string.blank_field_error));
        }
        return  i == 0;
    }

    public void clearSinkTwoEmptyFields() {
        measureFieldI.setErrorEnabled(false);
        measureFieldJ.setErrorEnabled(false);
        measureFieldK.setErrorEnabled(false);
        measureFieldL.setErrorEnabled(false);
        measureFieldM.setErrorEnabled(false);
        measureFieldN.setErrorEnabled(false);
        measureFieldO.setErrorEnabled(false);
        measureFieldP.setErrorEnabled(false);
        measureFieldQ.setErrorEnabled(false);
        measureFieldR.setErrorEnabled(false);
        measureFieldS.setErrorEnabled(false);
        measureFieldT.setErrorEnabled(false);

    }

    public RestroomSinkTwo updateSinkTwo (Bundle bundle) {
        pickUpSinkTwoData();
        return new RestroomSinkTwo(restroomDataBundle.getInt(RestroomSink1Fragment.SINK_ID), measureI, measureJ, measureK, measureL,
                measureM, measureN, obsSinkImgThree, measureO, measureP, measureQ, measureR, measureS, measureT, obsSinkImgFourFive);
    }

    public void pickUpSinkTwoData() {
        measureI = Double.valueOf(String.valueOf(measureValueI.getText()));
        measureJ = Double.valueOf(String.valueOf(measureValueJ.getText()));
        measureK = Double.valueOf(String.valueOf(measureValueK.getText()));
        measureL = Double.valueOf(String.valueOf(measureValueL.getText()));
        measureM = Double.valueOf(String.valueOf(measureValueM.getText()));
        measureN = Double.valueOf(String.valueOf(measureValueN.getText()));
        measureO = Double.valueOf(String.valueOf(measureValueO.getText()));
        measureP = Double.valueOf(String.valueOf(measureValueP.getText()));
        measureQ = Double.valueOf(String.valueOf(measureValueQ.getText()));
        measureR = Double.valueOf(String.valueOf(measureValueR.getText()));
        measureS = Double.valueOf(String.valueOf(measureValueS.getText()));
        measureT = Double.valueOf(String.valueOf(measureValueT.getText()));
        obsSinkImgThree = String.valueOf(obsSinkImgThreeValue.getText());
        obsSinkImgFourFive = String.valueOf(obsSinkImgFourFiveValue.getText());
    }

    public void callMirrorFragment(Bundle bundle) {
        clearSinkTwoEmptyFields();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RestroomMirrorUrinalFragment mirror = RestroomMirrorUrinalFragment.newInstance();
        mirror.setArguments(bundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, mirror).addToBackStack(null).commit();
    }

    public void clearSinkTwoFields() {
        measureValueI.setText(null);
        measureValueJ.setText(null);
        measureValueK.setText(null);
        measureValueL.setText(null);
        measureValueM.setText(null);
        measureValueN.setText(null);
        measureValueO.setText(null);
        measureValueP.setText(null);
        measureValueQ.setText(null);
        measureValueR.setText(null);
        measureValueS.setText(null);
        measureValueT.setText(null);
        obsSinkImgThreeValue.setText(null);
        obsSinkImgFourFiveValue.setText(null);
    }

    public void gatherSinkTwoData(RestroomSinkEntry sinkEntry) {
        measureValueI.setText(String.valueOf(sinkEntry.getSinkMeasureI()));
        measureValueJ.setText(String.valueOf(sinkEntry.getSinkMeasureJ()));
        measureValueK.setText(String.valueOf(sinkEntry.getSinkMeasureK()));
        measureValueL.setText(String.valueOf(sinkEntry.getSinkMeasureL()));
        measureValueM.setText(String.valueOf(sinkEntry.getSinkMeasureM()));
        measureValueN.setText(String.valueOf(sinkEntry.getSinkMeasureN()));
        measureValueO.setText(String.valueOf(sinkEntry.getSinkMeasureO()));
        measureValueP.setText(String.valueOf(sinkEntry.getSinkMeasureP()));
        measureValueQ.setText(String.valueOf(sinkEntry.getSinkMeasureQ()));
        measureValueR.setText(String.valueOf(sinkEntry.getSinkMeasureR()));
        measureValueS.setText(String.valueOf(sinkEntry.getSinkMeasureS()));
        measureValueT.setText(String.valueOf(sinkEntry.getSinkMeasureT()));
        obsSinkImgThreeValue.setText(sinkEntry.getSinkObsItoN());
        obsSinkImgFourFiveValue.setText(sinkEntry.getSinkObsOtoT());
    }
}