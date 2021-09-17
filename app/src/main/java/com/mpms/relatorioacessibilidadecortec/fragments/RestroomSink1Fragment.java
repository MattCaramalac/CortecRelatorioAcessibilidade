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
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkOne;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class RestroomSink1Fragment extends Fragment {

    public static final String OPENED_SINK_ONE = "OPENED_SINK_ONE";
    public static final String SINK_ID = "SINK_ID";

    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, measureFieldE, measureFieldF,
            measureFieldG, measureFieldH, obsImgOneField, obsImgTwoField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, measureValueE, measureValueF,
            measureValueG, measureValueH, obsImgOneValue, obsImgTwoValue;

    Double measureA, measureB, measureC, measureD, measureE, measureF, measureG, measureH;

    String imgOneObs, imgTwoObs;

    ImageButton sinkOne, sinkTwo;
    Button returnSupBar, saveSinkOne;

    Bundle restroomDataBundle, imgBundle;

    int recentSinkEntry = 0;

    ViewModelEntry modelEntry;


    public RestroomSink1Fragment() {
        // Required empty public constructor
    }

    public static RestroomSink1Fragment newInstance() {
        return new RestroomSink1Fragment();
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
        return inflater.inflate(R.layout.fragment_restroom_sink1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        measureFieldA = view.findViewById(R.id.sink_one_A_measurement_field);
        measureFieldB = view.findViewById(R.id.sink_one_B_measurement_field);
        measureFieldC = view.findViewById(R.id.sink_one_C_measurement_field);
        measureFieldD = view.findViewById(R.id.sink_one_D_measurement_field);
        measureFieldE = view.findViewById(R.id.sink_one_E_measurement_field);
        measureFieldF = view.findViewById(R.id.sink_two_F_measurement_field);
        measureFieldG = view.findViewById(R.id.sink_two_G_measurement_field);
        measureFieldH = view.findViewById(R.id.sink_two_H_measurement_field);
        obsImgOneField = view.findViewById(R.id.sink_one_obs_field);
        obsImgTwoField = view.findViewById(R.id.sink_two_obs_field);
        measureValueA = view.findViewById(R.id.sink_one_A_measurement_value);
        measureValueB = view.findViewById(R.id.sink_one_B_measurement_value);
        measureValueC = view.findViewById(R.id.sink_one_C_measurement_value);
        measureValueD = view.findViewById(R.id.sink_one_D_measurement_value);
        measureValueE = view.findViewById(R.id.sink_one_E_measurement_value);
        measureValueF = view.findViewById(R.id.sink_two_F_measurement_value);
        measureValueG = view.findViewById(R.id.sink_two_G_measurement_value);
        measureValueH = view.findViewById(R.id.sink_two_H_measurement_value);
        obsImgOneValue = view.findViewById(R.id.sink_one_obs_value);
        obsImgTwoValue = view.findViewById(R.id.sink_two_obs_value);


        sinkOne = view.findViewById(R.id.sink_image_one);
        sinkTwo = view.findViewById(R.id.sink_image_two);

        returnSupBar = view.findViewById(R.id.return_sup_bar);
        saveSinkOne = view.findViewById(R.id.save_sink_one);

        Glide.with(this).load(R.drawable.sink_1).fitCenter().into(sinkOne);
        Glide.with(this).load(R.drawable.sink_2).fitCenter().into(sinkTwo);

        sinkOne.setOnClickListener(v -> {
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_1);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
        });

        sinkTwo.setOnClickListener(v -> {
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_2);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
        });

        if (restroomDataBundle.getBoolean(RestroomSink2Fragment.OPENED_SINK_TWO)) {
            modelEntry.getOneRestroomSinkEntry(restroomDataBundle.getInt(SINK_ID)).observe(getViewLifecycleOwner(), this::gatherRestroomSinkOneData);
        }

        modelEntry.getLastRestroomSinkEntry().observe(getViewLifecycleOwner(), lastSink -> {
            if (recentSinkEntry == 1) {
                recentSinkEntry = 0;
                restroomDataBundle.putInt(SINK_ID, lastSink.getSinkID());
                callSinkTwoFragment(restroomDataBundle);
            }
        });

        saveSinkOne.setOnClickListener(v-> {
            //REGISTERED_ROOM deverá ser usado quando acessar dados da tabela através da escolha do usuário
            if (restroomDataBundle.getBoolean(RestroomFragment.REGISTERED_ROOM) ||
                    restroomDataBundle.getBoolean(RestroomSink2Fragment.OPENED_SINK_TWO)) {
                if (checkSinkOneEmptyFields()) {
                    RestroomSinkOne updateSinkOne = updateSinkOne(restroomDataBundle);
                    ViewModelEntry.updateSinkEntryOne(updateSinkOne);
                    callSinkTwoFragment(restroomDataBundle);
                }

            }  else {
                if (checkSinkOneEmptyFields()) {
                    RestroomSinkEntry newSink = newSink(restroomDataBundle);
                    ViewModelEntry.insertRestroomSinkEntry(newSink);
                    recentSinkEntry = 1;
                }
            }
        });

        returnSupBar.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    @Override
    public void onResume() {
        super.onResume();
        restroomDataBundle.putBoolean(OPENED_SINK_ONE, true);
    }

    public boolean checkSinkOneEmptyFields() {
        clearSinkOneErrors();
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

        return i == 0;
    }

    public void clearSinkOneErrors() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        measureFieldE.setErrorEnabled(false);
        measureFieldF.setErrorEnabled(false);
        measureFieldG.setErrorEnabled(false);
        measureFieldH.setErrorEnabled(false);
    }

    public void pickUpSinkOneFields() {
        measureA = Double.valueOf(String.valueOf(measureValueA.getText()));
        measureB = Double.valueOf(String.valueOf(measureValueB.getText()));
        measureC = Double.valueOf(String.valueOf(measureValueC.getText()));
        measureD = Double.valueOf(String.valueOf(measureValueD.getText()));
        measureE = Double.valueOf(String.valueOf(measureValueE.getText()));
        measureF = Double.valueOf(String.valueOf(measureValueF.getText()));
        measureG = Double.valueOf(String.valueOf(measureValueG.getText()));
        measureH = Double.valueOf(String.valueOf(measureValueH.getText()));
        imgOneObs = String.valueOf(obsImgOneValue.getText());
        imgTwoObs = String.valueOf(obsImgTwoValue.getText());
    }

    public void callSinkTwoFragment(Bundle bundle) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RestroomSink2Fragment sinkTwoFragment = RestroomSink2Fragment.newInstance();
        sinkTwoFragment.setArguments(bundle);
        clearRestroomSinkOneFields();
        fragmentTransaction.replace(R.id.show_fragment_selected, sinkTwoFragment).addToBackStack(null).commit();
    }

    public RestroomSinkEntry newSink(Bundle bundle) {
        pickUpSinkOneFields();

        return new RestroomSinkEntry(restroomDataBundle.getInt(RestroomFragment.RESTROOM_ID), measureA, measureB, measureC,
                measureD, measureE, imgOneObs, measureF, measureG, measureH, imgTwoObs, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null);
    }

    public RestroomSinkOne updateSinkOne(Bundle bundle) {
        pickUpSinkOneFields();
        return new RestroomSinkOne(bundle.getInt(SINK_ID), measureA, measureB, measureC, measureD, measureE,
                imgOneObs, measureF, measureG, measureH, imgTwoObs);

    }

    public void gatherRestroomSinkOneData(RestroomSinkEntry sinkEntry) {
        measureValueA.setText(String.valueOf(sinkEntry.getSinkMeasureA()));
        measureValueB.setText(String.valueOf(sinkEntry.getSinkMeasureB()));
        measureValueC.setText(String.valueOf(sinkEntry.getSinkMeasureC()));
        measureValueD.setText(String.valueOf(sinkEntry.getSinkMeasureD()));
        measureValueE.setText(String.valueOf(sinkEntry.getSinkMeasureE()));
        measureValueF.setText(String.valueOf(sinkEntry.getSinkMeasureF()));
        measureValueG.setText(String.valueOf(sinkEntry.getSinkMeasureG()));
        measureValueH.setText(String.valueOf(sinkEntry.getSinkMeasureH()));
        obsImgOneValue.setText(String.valueOf(sinkEntry.getSinkObsAtoE()));
        obsImgTwoValue.setText(String.valueOf(sinkEntry.getSinkObsFtoH()));
    }

    public void clearRestroomSinkOneFields() {
        measureValueA.setText(null);
        measureValueB.setText(null);
        measureValueC.setText(null);
        measureValueD.setText(null);
        measureValueE.setText(null);
        measureValueF.setText(null);
        measureValueG.setText(null);
        measureValueH.setText(null);
        obsImgOneValue.setText(null);
        obsImgTwoValue.setText(null);
    }

}