package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.HorSinkBarParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

public class SinkHorBarFragment extends Fragment implements TagInterface {

    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, diamField, obsField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, diamValue, obsValue;
    ImageButton horImage, horImage2;

    Bundle imgBundle = new Bundle();

    ViewModelEntry modelEntry;

    static int restID;
    static boolean frontLeftHor;

    public SinkHorBarFragment() {
        // Required empty public constructor
    }

    public static SinkHorBarFragment newInstance(int restroomID, boolean isFrontLeftHor) {
        restID = restroomID;
        frontLeftHor = isFrontLeftHor;
        return new SinkHorBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sink_hor_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);

//        Fragmento barra horizontal à esquerda OU Barra Horizontal (opção 2)
        if (frontLeftHor) {
            getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
                if(checkEmptyField(bundle)) {
                    createHorBarParcel(bundle);
                }
                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
            });
        }
//        Fragmento barra horizontal à direita
       else {
            getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_2, this, (key, bundle) -> {
                if(checkEmptyField(bundle)) {
                    createHorBarParcel(bundle);
                }
                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_2, bundle);
            });
        }

        modelEntry.getRestSinkData(restID).observe(getViewLifecycleOwner(), this::loadLeftHorBarData);

    }

    private void loadLeftHorBarData(RestroomEntry entry) {
        if (frontLeftHor) {
            if (entry.getLeftFrontHorMeasureA() != null)
                measureValueA.setText(String.valueOf(entry.getLeftFrontHorMeasureA()));
            if (entry.getLeftFrontHorMeasureB() != null)
                measureValueB.setText(String.valueOf(entry.getLeftFrontHorMeasureB()));
            if (entry.getLeftFrontHorMeasureC() != null)
                measureValueC.setText(String.valueOf(entry.getLeftFrontHorMeasureC()));
            if (entry.getLeftFrontHorMeasureD() != null)
                measureValueD.setText(String.valueOf(entry.getLeftFrontHorMeasureD()));
            if (entry.getLeftFrontHorDiam() != null)
                diamValue.setText(String.valueOf(entry.getLeftFrontHorDiam()));
            if (entry.getLeftFrontHorObs() != null)
                obsValue.setText(String.valueOf(entry.getLeftFrontHorObs()));
        }
        else {
            if (entry.getRightSideVertMeasureA() != null)
                measureValueA.setText(String.valueOf(entry.getRightSideVertMeasureA()));
            if (entry.getRightSideVertMeasureB() != null)
                measureValueB.setText(String.valueOf(entry.getRightSideVertMeasureB()));
            if (entry.getRightSideVertMeasureC() != null)
                measureValueC.setText(String.valueOf(entry.getRightSideVertMeasureC()));
            if (entry.getRightSideVertMeasureD() != null)
                measureValueD.setText(String.valueOf(entry.getRightSideVertMeasureD()));
            if (entry.getRightSideVertDiam() != null)
                diamValue.setText(String.valueOf(entry.getRightSideVertDiam()));
            if (entry.getRightSideVertObs() != null)
                obsValue.setText(String.valueOf(entry.getRightSideVertObs()));
        }

    }

    private void createHorBarParcel(Bundle bundle) {
        double measureA, measureB, measureC, measureD, diam;
        String obs = null;

        measureA = Double.parseDouble(String.valueOf(measureValueA.getText()));
        measureB = Double.parseDouble(String.valueOf(measureValueB.getText()));
        measureC = Double.parseDouble(String.valueOf(measureValueC.getText()));
        measureD = Double.parseDouble(String.valueOf(measureValueD.getText()));
        diam = Double.parseDouble(String.valueOf(diamValue.getText()));
        if (!TextUtils.isEmpty(obsValue.getText()))
            obs = String.valueOf(obsValue.getText());

        HorSinkBarParcel parcel = new HorSinkBarParcel(frontLeftHor, measureA, measureB, measureC, measureD, diam, obs);
        if (frontLeftHor)
            bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
        else
            bundle.putParcelable(CHILD_PARCEL_2, Parcels.wrap(parcel));
    }

    private void instantiateViews(View view) {
//        TextInputLayout
        measureFieldA = view.findViewById(R.id.sink_hor_measureA_field);
        measureFieldB = view.findViewById(R.id.sink_hor_measureB_field);
        measureFieldC = view.findViewById(R.id.sink_hor_measureC_field);
        measureFieldD = view.findViewById(R.id.sink_hor_measureD_field);
        diamField = view.findViewById(R.id.sink_hor_diam_field);
        obsField = view.findViewById(R.id.hor_bar_obs_field);
//        TextInputEditText
        measureValueA = view.findViewById(R.id.sink_hor_measureA_value);
        measureValueB = view.findViewById(R.id.sink_hor_measureB_value);
        measureValueC = view.findViewById(R.id.sink_hor_measureC_value);
        measureValueD = view.findViewById(R.id.sink_hor_measureD_value);
        diamValue = view.findViewById(R.id.sink_hor_diam_value);
        obsValue = view.findViewById(R.id.hor_bar_obs_value);
//        ImageButton
        horImage = view.findViewById(R.id.hor_bar_img);
        horImage2 = view.findViewById(R.id.hor_bar_img2);
        Glide.with(this).load(R.drawable.sinkhorbar).centerCrop().into(horImage);
        Glide.with(this).load(R.drawable.sinkhorbar2).centerCrop().into(horImage2);
//        Listener
        horImage.setOnClickListener(this::imgExpandClick);
        horImage2.setOnClickListener(this::imgExpandClick);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void imgExpandClick(View view) {
        if (view == horImage)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkhorbar);
        if (view == horImage2)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkhorbar2);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private boolean checkEmptyField(Bundle bundle) {
        clearEmptyFieldError();
        int i = 0;
        if (TextUtils.isEmpty(measureValueA.getText())) {
            i++;
            measureFieldA.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(measureValueB.getText())) {
            i++;
            measureFieldB.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(measureValueC.getText())) {
            i++;
            measureFieldC.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(measureValueD.getText())) {
            i++;
            measureFieldD.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(diamValue.getText())) {
            i++;
            diamField.setError(getText(R.string.req_field_error));
        }

        if (frontLeftHor)
            bundle.putBoolean(CHILD_DATA_COMPLETE, i == 0);
        else
            bundle.putBoolean(CHILD_DATA_COMPLETE_2, i == 0);
        return i == 0;
    }

    private void clearEmptyFieldError() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        diamField.setErrorEnabled(false);
        obsField.setErrorEnabled(false);
    }
}