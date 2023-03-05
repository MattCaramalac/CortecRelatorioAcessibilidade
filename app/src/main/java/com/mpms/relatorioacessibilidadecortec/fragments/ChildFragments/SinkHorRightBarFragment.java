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
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.HorSinkBarParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

public class SinkHorRightBarFragment extends Fragment implements TagInterface {

    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, diamField, obsField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, diamValue, obsValue;
    ImageButton horImage, horImage2;

    Bundle imgBundle = new Bundle();
    Bundle horBundle;

    ViewModelEntry modelEntry;

    public SinkHorRightBarFragment() {
        // Required empty public constructor
    }

    public static SinkHorRightBarFragment newInstance() {
        return new SinkHorRightBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            horBundle = new Bundle(this.getArguments());
        else
            horBundle = new Bundle();
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

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_3, getViewLifecycleOwner(), (key, bundle) -> {
            if (checkEmptyField(bundle)) {
                createHorBarParcel(bundle);
            }
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_3, bundle);
        });

        if (horBundle.getBoolean(FROM_BOX))
            modelEntry.getBoxSinkData(horBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadBoxRightHorBarData);
        else
            modelEntry.getRestSinkData(horBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadRightHorBarData);

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

        HorSinkBarParcel parcel = new HorSinkBarParcel(false, measureA, measureB, measureC, measureD, diam, obs);

        bundle.putParcelable(CHILD_PARCEL_3, Parcels.wrap(parcel));
    }

    private void loadRightHorBarData(RestroomEntry entry) {
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

    private void loadBoxRightHorBarData(RestBoxEntry entry) {
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

        bundle.putBoolean(CHILD_DATA_COMPLETE_3, i == 0);

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
