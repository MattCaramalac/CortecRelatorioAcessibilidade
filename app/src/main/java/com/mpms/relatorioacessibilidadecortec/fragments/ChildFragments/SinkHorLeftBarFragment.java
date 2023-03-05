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

public class SinkHorLeftBarFragment extends Fragment implements TagInterface {

    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, diamField, obsField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, diamValue, obsValue;
    ImageButton horImage, horImage2;

    Bundle imgBundle = new Bundle();
    Bundle horBarBundle = new Bundle();

    ViewModelEntry modelEntry;


    public SinkHorLeftBarFragment() {
        // Required empty public constructor
    }

    public static SinkHorLeftBarFragment newInstance() {
        return new SinkHorLeftBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            horBarBundle = new Bundle(this.getArguments());
        else
            horBarBundle = new Bundle();
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


        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_2, this, (key, bundle) -> {
            if (checkEmptyField(bundle)) {
                createHorBarParcel(bundle);
            }
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_2, bundle);
        });

        if(horBarBundle.getBoolean(FROM_BOX))
            modelEntry.getBoxSinkData(horBarBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadBoxLeftHorBarData);
        else
            modelEntry.getRestSinkData(horBarBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadLeftHorBarData);

    }

    private void loadLeftHorBarData(RestroomEntry entry) {
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


    private void loadBoxLeftHorBarData(RestBoxEntry entry) {
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

        HorSinkBarParcel parcel = new HorSinkBarParcel(true, measureA, measureB, measureC, measureD, diam, obs);

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